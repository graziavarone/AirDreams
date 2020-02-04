package gestioneutente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DriverManagerConnectionPool;
import gestionecompagniaaerea.CompagniaAerea;
import gestionecompagniaaerea.CompagniaAereaManager;

public class UtenteManager {

	public Account signIn(Account account) {
		Account acc=null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Ruolo ruolo=null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			st = con.createStatement();
		
			String sql= "SELECT * FROM utente WHERE email=? AND passwordUtente=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,account.getEmail());
			ps.setString(2, account.getPassword());
			rs=ps.executeQuery();
			
			if (rs.next()) {
				CompagniaAereaManager manager=new CompagniaAereaManager();
				acc=new Account();
				acc.setNome(rs.getString("nome"));
				acc.setCognome(rs.getString("cognome"));
				acc.setEmail(rs.getString("email"));
				acc.setPassword(rs.getString("passwordUtente"));
				CompagniaAerea compagniaAerea=manager.visualizzaInfoCompagniaAerea(rs.getString("compagniaAerea"));
				acc.setCompagniaAerea(compagniaAerea);
				if(rs.getString("ruolo")!=null) {
				ruolo= Ruolo.valueOf(rs.getString("ruolo"));
				System.out.println("Ho ricevuto "+ruolo);
				acc.setRuolo(ruolo);
				}
				
				return acc;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
				DriverManagerConnectionPool.releaseConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return acc;
	}

	public boolean signUp(Account account) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="INSERT into utente (nome,cognome,email, passwordUtente) values (?,?,?,?)";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(updateSQL);
               

                preparedStatement.setString(1, account.getNome());
                preparedStatement.setString(2, account.getCognome());
                preparedStatement.setString(3, account.getEmail());
                preparedStatement.setString(4, account.getPassword());
                
            	System.out.println("signUp: "+ preparedStatement.toString());
                preparedStatement.executeUpdate();
                b=true;
                
            }
              finally {
            	try {
            		if(preparedStatement!=null) preparedStatement.close();
            		}
            		finally {
            			DriverManagerConnectionPool.releaseConnection(connection);
            		}
            	}
        
        return b;
    }

	public Account findAccountByEmail(String email) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Account account=null;
	
		String selectSQL = "SELECT * FROM utente WHERE email = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			
			System.out.println("findAccountByEmail: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				account = new Account();
				
				account.setNome(rs.getString("nome"));
				account.setCognome(rs.getString("cognome"));
				account.setEmail(rs.getString("email"));
				account.setPassword(rs.getString("passwordUtente"));			
			}
			
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}			
		}
		return account;
	}
	
	public boolean eliminaAccount(String email) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			st = con.createStatement();
		
			String sql= "DELETE FROM utente WHERE email=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,email);
			ps.executeUpdate();
			
			return true; //se la cancellazione è andata a buon fine
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
				DriverManagerConnectionPool.releaseConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public ArrayList<Account> getAllUsers() throws SQLException {
		ArrayList<Account> allUtenti = new ArrayList<Account>();
        Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String selectSQL="SELECT * FROM utente";
		
        try {
        	connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
            	Account a = new Account();
            	
            	a.setNome(rs.getString("nome"));
				a.setCognome(rs.getString("cognome"));
				a.setEmail(rs.getString("email"));
				a.setPassword(rs.getString("passwordUtente"));
				
				allUtenti.add(a);
            }
        } finally {
        	try {
        		if(preparedStatement!=null) preparedStatement.close();
        		}
        		finally {
        			DriverManagerConnectionPool.releaseConnection(connection);
        		}
        	}
        return allUtenti; 
    }

	public ArrayList<Account> findAccountByLetter(String nome, String cognome) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Account> account= new ArrayList<Account>();
		String selectSQL=null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			if(cognome.equals("-")) {
				selectSQL = "SELECT * FROM utente WHERE nome LIKE ?";
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, nome + "%");
			} else if(nome.equals("-")) {
				selectSQL = "SELECT * FROM utente WHERE cognome LIKE ?";
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, cognome + "%");
			} else {
				selectSQL = "SELECT * FROM utente WHERE nome LIKE ? AND cognome LIKE ?";
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, nome + "%");
				preparedStatement.setString(2, cognome + "%");
			}
			
			
			System.out.println("findAccountByLetter: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Account account1 = new Account();
				
				account1.setNome(rs.getString("nome"));
				account1.setCognome(rs.getString("cognome"));
				account1.setEmail(rs.getString("email"));
				account1.setPassword(rs.getString("passwordUtente"));	
				
				account.add(account1);	
			}
			return account;
			
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}			
		}
		
	}
	

}