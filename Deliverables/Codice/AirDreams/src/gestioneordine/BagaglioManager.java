package gestioneordine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DriverManagerConnectionPool;


public class BagaglioManager {

	public boolean aggiungiBagaglioMano(BagaglioMano bagaglioMano) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="INSERT into bagaglioMano (peso,dimensioni,biglietto) values (?,?,?)";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(updateSQL);
               

                preparedStatement.setInt(1, bagaglioMano.getPeso());
                preparedStatement.setString(2, bagaglioMano.getDimensioni());
                preparedStatement.setInt(3, bagaglioMano.getBiglietto().getCodBiglietto());
                
            	System.out.println("aggiungiBagaglioMano: "+ preparedStatement.toString());
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
	
	public boolean aggiungiBagaglioStiva(BagaglioStiva bagaglioStiva) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="INSERT into bagaglioStiva (peso,dimensioni,biglietto,prezzo,quantity) values (?,?,?,?,?)";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(updateSQL);
               

                preparedStatement.setInt(1, bagaglioStiva.getPeso());
                preparedStatement.setString(2, bagaglioStiva.getDimensioni());
                preparedStatement.setInt(3, bagaglioStiva.getBiglietto().getCodBiglietto());
                preparedStatement.setFloat(4, bagaglioStiva.getPrezzo());
                preparedStatement.setInt(5, bagaglioStiva.getQuantity());
                
            	System.out.println("aggiungiBagaglioStiva: "+ preparedStatement.toString());
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
