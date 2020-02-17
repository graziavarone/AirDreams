package gestioneutente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestionecompagniaaerea.CompagniaAerea;
import gestionecompagniaaerea.CompagniaAereaManager;

/**
 * La classe si occupa di implementare i metodi per effettuare l'inserimento,
 * la cancellazione, la modifica e la ricerca di un utente registrato al sistema,
 * memorizzato nel DB
 */
public class UtenteManager {

	/**
	 * metodo che permette ad un utente di accedere al sistema
	 * @param account account "fittizio" con le credenziali inserite dall'utente per effettuare il login
	 * @return Account account dell'utente che ha effettuato il login
	 */
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

	/**
	 * metodo che permette ad un utente di disconnettersi dal sistema
	 * @param account account dell'utente che vuole effettuare il logout dal sistema
	 * @return boolean true se l'inserimento nel DB va a buon fine, false in caso contrario
	 * @throws SQLException
	 */
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
        } finally {
        	try {
        		if(preparedStatement!=null)
        			preparedStatement.close();
            } finally {
            	DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        
        return b;
    }

	/**
	 * metodo che permette di recuperare l'account di un utente data la email di accesso
	 * @param email email dell'utente
	 * @return Account account dell'utente ricercato
	 * @throws SQLException
	 */
	public Account findAccountByEmail(String email) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Account account=null;
		Ruolo ruolo=null;
	
		String selectSQL = "SELECT * FROM utente WHERE email = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			
			System.out.println("findAccountByEmail: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				CompagniaAereaManager manager=new CompagniaAereaManager();
				
				account = new Account();
				
				account.setNome(rs.getString("nome"));
				account.setCognome(rs.getString("cognome"));
				account.setEmail(rs.getString("email"));
				account.setPassword(rs.getString("passwordUtente"));	
				CompagniaAerea compagniaAerea=manager.visualizzaInfoCompagniaAerea(rs.getString("compagniaAerea"));
				account.setCompagniaAerea(compagniaAerea);
				if(rs.getString("ruolo")!=null) {
					ruolo= Ruolo.valueOf(rs.getString("ruolo"));
				}
				System.out.println("Ho ricevuto "+ruolo);
				account.setRuolo(ruolo);
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
	
	/**
	 * metodo che permette di eliminare un account dal sistema
	 * @param email email di accesso dell'account da rimuovere
	 * @return boolean true se l'inserimento nel DB va a buon fine, false in caso contrario
	 */
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
			
			return true; //se la cancellazione e' andata a buon fine
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
	
	/**
	 * metodo che permette di recuperare la lista di tutti gli utente registrati al sistema
	 * @return ArrayList<Account> lista di tutti gli utenti
	 * @throws SQLException
	 */
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
        		if(preparedStatement!=null)
        			preparedStatement.close();
        	} finally {
        		DriverManagerConnectionPool.releaseConnection(connection);
        	}
        }
        
        return allUtenti; 
    }

	/**
	 * metodo che permette di ricercare la lista di tutti gli utenti registrati con date iniziali per nome, cognome o entrambi
	 * @param nome iniziale del nome da usare come criterio di ricerca
	 * @param cognome iniziale del cognome da usare come criterio di ricerca
	 * @return ArrayList<Account> lista degli utenti che rispetta i criteri di ricerca
	 * @throws SQLException
	 */
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
	
	/**
	 * metodo che permette di modificare l'account di un utente
	 * @param oldAccount attuale account dell'utente
	 * @param newAccount nuovo account da impostare dell'utente
	 * @return boolean true se l'inserimento nel DB va a buon fine, false in caso contrario
	 */
	public boolean aggiornaProfilo(Account oldAccount, Account newAccount) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			st = con.createStatement();
		
			String sql= "UPDATE utente SET nome=?, cognome=?, email=?, passwordUtente=?, compagniaAerea=?, ruolo=? WHERE email=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,newAccount.getNome());
			ps.setString(2,newAccount.getCognome());
			ps.setString(3,newAccount.getEmail());
			ps.setString(4,newAccount.getPassword());
			
			if (newAccount.getCompagniaAerea()==null)
				ps.setString(5,null);
			else
				ps.setString(5,newAccount.getCompagniaAerea().getNome());
			
			if (newAccount.getRuolo()==null) {
				ps.setString(6,null);
			} else if (newAccount.getRuolo()==Ruolo.gestoreCompagnie) {
				ps.setString(6,"gestoreCompagnie");
			} else {
				ps.setString(6,"gestoreVoli");
			}
			
			ps.setString(7,newAccount.getEmail());
			
			System.out.println("aggiornaProfilo "+ps.toString());
			ps.executeUpdate();
			
			return true; //se l'update va a buon fine
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
}