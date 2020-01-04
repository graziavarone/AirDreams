package gestoreutente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DriverManagerConnectionPool;

public class UtenteManager {

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

}
