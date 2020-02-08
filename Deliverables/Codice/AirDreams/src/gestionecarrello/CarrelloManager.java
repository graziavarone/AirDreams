package gestionecarrello;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestionecompagniaaerea.CompagniaAerea;
import gestionecompagniaaerea.CompagniaAereaManager;
import gestioneutente.Account;
import gestioneutente.UtenteManager;
import gestionevolo.Aeroporto;
import gestionevolo.AeroportoManager;
import gestionevolo.Volo;
import gestionevolo.VoloManager;

public class CarrelloManager {
	
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
        		if(preparedStatement!=null) preparedStatement.close();
        		}
        		finally {
        			DriverManagerConnectionPool.releaseConnection(connection);
        		}
        	}
        return carrello; 
    }

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
        		if(preparedStatement!=null) preparedStatement.close();
        		}
        		finally {
        			DriverManagerConnectionPool.releaseConnection(connection);
        		}
        	}
        return volo; 
	}

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


}
