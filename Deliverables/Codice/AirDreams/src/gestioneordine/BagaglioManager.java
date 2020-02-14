package gestioneordine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

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
                
                System.out.println("Ho eseguito query per aggiungere bagaglio");
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
	
	public BagaglioMano cercaBagaglioManoBiglietto(Biglietto biglietto) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		BagaglioMano bagaglioMano=null;
		
	
		String selectSQL="SELECT * from bagaglioMano where biglietto=?";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(selectSQL);
               

                preparedStatement.setInt(1, biglietto.getCodBiglietto());
                
            	System.out.println("cercaBagagliManoBiglietto: "+ preparedStatement.toString());
                rs=preparedStatement.executeQuery();
               
                
                if(rs.next()) {
                		 bagaglioMano=new BagaglioMano();
                		
                		bagaglioMano.setBiglietto(biglietto);
                		 bagaglioMano.setDimensioni(rs.getString("dimensioni"));
                		 bagaglioMano.setPeso(rs.getInt("peso"));
                		
                	}
            
                }
                
            
              finally {
            	try {
            		if(preparedStatement!=null) preparedStatement.close();
            		}
            		finally {
            			DriverManagerConnectionPool.releaseConnection(connection);
            		}
            	}
        
        
        return bagaglioMano;
		
	}
	
	public HashSet<BagaglioStiva> cercaBagagliStivaBiglietto(Biglietto biglietto) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		HashSet<BagaglioStiva> bagagliStiva=new HashSet<BagaglioStiva>();
		
	
		String selectSQL="SELECT * from bagaglioStiva where biglietto=?";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(selectSQL);
               

                preparedStatement.setInt(1, biglietto.getCodBiglietto());
                
            	System.out.println("cercaBagagliStivaBiglietto: "+ preparedStatement.toString());
                rs=preparedStatement.executeQuery();
               
                
                if(rs.next()) {
                	int quantity=rs.getInt("quantity");
                	
                	for(int i=0;i<quantity;i++) {
                		BagaglioStiva bagaglioStiva=new BagaglioStiva();
                		
                		bagaglioStiva.setBiglietto(biglietto);
                		bagaglioStiva.setDimensioni(rs.getString("dimensioni"));
                		bagaglioStiva.setPeso(rs.getInt("peso"));
                		bagaglioStiva.setPrezzo(rs.getFloat("prezzo"));
                		
                		bagagliStiva.add(bagaglioStiva);
                	}
            
                }
                
            }
              finally {
            	try {
            		if(preparedStatement!=null) preparedStatement.close();
            		}
            		finally {
            			DriverManagerConnectionPool.releaseConnection(connection);
            		}
            	}
        
        
        return bagagliStiva;
		
	}


}
