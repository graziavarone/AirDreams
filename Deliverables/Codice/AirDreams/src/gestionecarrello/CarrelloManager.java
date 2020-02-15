package gestionecarrello;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DriverManagerConnectionPool;
import gestionevolo.Volo;
import gestionevolo.VoloManager;

/**
 * La classe si occupa di implementare i metodi per effettuare l’inserimento,
 * la cancellazione e la ricerca di un carrello relativo ad un particolare utente
 * memorizzato all’interno del DB
 */
public class CarrelloManager {
	
	/**
	 * metodo che restituisce il carrello e relativo contenuto, assegnato ad un utente
	 * @param email email dell'utente a cui il carrello è collegato
	 * @return Carrello carrello relato all'utente
	 * @throws SQLException
	 */
	public Carrello getCarrelloUtente(String email) throws SQLException {
		Carrello carrello = new Carrello();
        Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String selectSQL="SELECT * FROM carrello WHERE utente=?";
		
        try {
        	connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
            	String idVolo=rs.getString("volo");
            	
            	VoloManager voloManager=new VoloManager();
            	Volo volo = voloManager.findByID(idVolo);
            	
            	carrello.getVoli().put(volo, rs.getInt("quantity"));	
            }
            carrello.setAccount(email);
        } finally {
        	try {
        		if(preparedStatement!=null) 
        			preparedStatement.close();
        	} finally {
        		DriverManagerConnectionPool.releaseConnection(connection);
        	}
        }
        return carrello; 
    }

	/**
	 * metodo che permette di inserire un volo al carrello dell'utente
	 * @param email email dell'utente a cui il carrello è collegato
	 * @param id id identificativo del volo da inserire nel carrello
	 * @param quantity numero di istanze del volo che devono essere inserite nel carrello
	 * (corrispondente al numero di passeggeri per il volo indicato)
	 * @return boolean true se l'inserimento nel carrello va a buon fine, false in caso contrario
	 * @throws SQLException
	 */
	public boolean aggiungiVoloAlCarrello(String email, int id, int quantity) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="INSERT into carrello (utente,volo,quantity) values (?,?,?)";
        
        try {
        	connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);
               
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);
            preparedStatement.setInt(3,quantity );
       
            System.out.println("aggiungiAlCarrello: "+ preparedStatement.toString());
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
	 * metodo che permette di modificare il numero di istanze di un dato volo presente nel carrello,
	 * ossia il numero di passeggeri previsto
	 * @param email email dell'utente a cui il carrello è collegato
	 * @param id id identificativo del volo da modificare
	 * @param quantity nuovo numero di istanze del volo che devono essere inserite nel carrello
	 * (nuovo numero di passeggeri previsto)
	 * @return boolean true se l'aggiornamento nel carrello va a buon fine, false in caso contrario
	 * @throws SQLException
	 */
	public boolean updateQuantity(String email, int id, int quantity) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="UPDATE carrello set quantity=? where utente=? and volo=?";
        
        try {
        	connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);
               
            preparedStatement.setInt(1,quantity );
            preparedStatement.setString(2, email);
            preparedStatement.setInt(3, id);
 
            System.out.println("updateQuantity: "+ preparedStatement.toString());
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
	 * metodo che permette di ricercare un volo nel carrello dato l'id identificativo
	 * @param email email di accesso dell'utente a cui il carrello è collegato
	 * @param idVolo id identificativo del volo da ricercare nel carrello
	 * @return Volo volo ricercato con id identificativo indicato da parametro
	 * @throws SQLException
	 */
	public Volo cercaVoloNelCarrello(String email, int idVolo) throws SQLException {
		Volo volo = null;
        Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String selectSQL="SELECT * FROM carrello WHERE utente=? and volo=?";
		
        try {
        	connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			preparedStatement.setInt(2, idVolo);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
            	String id=rs.getString("volo");
            	
            	VoloManager voloManager=new VoloManager();
            	volo = voloManager.findByID(id);		
            }
        } finally {
        	try {
        		if(preparedStatement!=null) 
        			preparedStatement.close();
        	} finally {
        		DriverManagerConnectionPool.releaseConnection(connection);
        	}
        }
        return volo; 
	}

	/**
	 * metodo che permette di rimuovere un volo dal carrello dato l'id identificativo
	 * @param id id identificativo del volo da rimuovere dal carrello
	 * @param email email di accesso dell'utente a cui il carrello è collegato
	 * @return boolean true se la cancellazione dal carrello va a buon fine, false in caso contrario
	 * @throws SQLException
	 */
	public boolean rimuoviVoloDalCarrello(String id, String email) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="DELETE from carrello WHERE volo=? and utente=?";
        
        try {
        	connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);
               
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, email);
             
            System.out.println("rimuoviDalCarrello: "+ preparedStatement.toString());
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
	 * metodo che permette di svuotare un carrello di tutti i voli presenti
	 * @param email email di accesso dell'utente a cui il carrello è collegato
	 * @return boolean true se l'operazione nel carrello va a buon fine, false in caso contrario
	 * @throws SQLException
	 */
	public boolean svuotaCarrello(String email) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="DELETE from carrello WHERE utente=?";
        
        try {
        	connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);
               
            preparedStatement.setString(1, email);
               
            System.out.println("svuotaCarrello: "+ preparedStatement.toString());
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
}
