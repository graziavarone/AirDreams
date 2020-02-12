package gestioneordine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import db.DriverManagerConnectionPool;
import gestioneutente.Ruolo;
import gestionevolo.Volo;
import gestionevolo.VoloManager;

public class BigliettoManager {
	
	public Biglietto aggiungiBiglietto(Biglietto biglietto) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		PreparedStatement preparedStatement1=null;
	
		String updateSQL="INSERT into biglietto (nome,cognome,sesso, prezzoBiglietto,ordine,volo) values (?,?,?,?,?,?)";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(updateSQL);
               

                preparedStatement.setString(1, biglietto.getNome());
                preparedStatement.setString(2, biglietto.getCognome());
                preparedStatement.setString(3, biglietto.getSesso().toString());
                preparedStatement.setFloat(4, biglietto.getPrezzoBiglietto());
                preparedStatement.setInt(5,biglietto.getOrdine());
                preparedStatement.setInt(6, biglietto.getVolo().getId());
             
            	System.out.println("aggiungiBiglietto: "+ preparedStatement.toString());
                preparedStatement.executeUpdate();
                
                String sql1="SELECT codBiglietto FROM biglietto ORDER BY codBiglietto DESC LIMIT 1";
                preparedStatement1=connection.prepareStatement(sql1);
                ResultSet rs = preparedStatement1.executeQuery();
                
                if(rs.next()) {
        			biglietto.setCodBiglietto(rs.getInt("codBiglietto"));
        			
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
        
        
        return biglietto;
    }

	
	public ArrayList<Biglietto> trovaBigliettiOrdine(int codOrdine) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		VoloManager voloManager=new VoloManager();
		BagaglioManager bagaglioManager=new BagaglioManager();
		ArrayList<Biglietto> biglietti=new ArrayList<Biglietto>();
	
		String selectSQL="SELECT * from biglietto WHERE ordine=?";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(selectSQL);
               

                preparedStatement.setInt(1, codOrdine);
          
             
            	System.out.println("cercaBigliettiOrdine: "+ preparedStatement.toString());
               
                ResultSet rs = preparedStatement.executeQuery();
                
                while(rs.next()) {
        			Biglietto biglietto=new Biglietto();
        	
        			
        		
        			biglietto.setCodBiglietto(rs.getInt("codBiglietto"));
        			biglietto.setNome(rs.getString("nome"));
        			biglietto.setCognome(rs.getString("cognome"));
        			
        			if(rs.getString("sesso")!=null) {
    					Sesso sesso= Sesso.valueOf(rs.getString("sesso"));
    		
    					biglietto.setSesso(sesso);
    				}
        			
        			biglietto.setPrezzoBiglietto(rs.getFloat("prezzoBiglietto"));
        			biglietto.setOrdine(rs.getInt("ordine"));
        			
        			Volo volo=voloManager.findByID(rs.getString("volo"));
        			
        			biglietto.setVolo(volo);
        			
        			
        			biglietto.setBagaglioMano(bagaglioManager.cercaBagaglioManoBiglietto(biglietto));
        		
        			biglietto.setBagagliStiva(bagaglioManager.cercaBagagliStivaBiglietto(biglietto));
        			
        			biglietti.add(biglietto);
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
        
        
        return biglietti;
    }

}