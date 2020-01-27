package gestionevolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import db.DriverManagerConnectionPool;

public class VoloManager {
	private DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public boolean aggiungiVolo(Volo volo) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="INSERT into volo (dataPart,prezzo,postiDisponibili,durata,orarioPart,bagaglioStivaCompreso,aeroportoPart,"
						+ "aeroportoArr,compagniaAerea) values (?,?,?,?,?,?,?,?,?)";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(updateSQL);
               

                preparedStatement.setString(1,volo.getDataPartenza().format(FORMATO_DIA));
                preparedStatement.setFloat(2, volo.getPrezzo());
                preparedStatement.setInt(3, volo.getSeats());
                preparedStatement.setString(4, volo.getDurataVolo().toString());
                preparedStatement.setString(5, volo.getOrarioPartenza().toString());
                preparedStatement.setBoolean(6, volo.isCompreso());
                preparedStatement.setString(7, volo.getAeroportoP().getCodice());
                preparedStatement.setString(8, volo.getAeroportoA().getCodice());
                preparedStatement.setString(9, volo.getCa().getNome());
                
            	System.out.println("AggiungiVolo: "+ preparedStatement.toString());
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
