package gestioneordine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DriverManagerConnectionPool;

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

}
