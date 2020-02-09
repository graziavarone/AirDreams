package gestioneordine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import db.DriverManagerConnectionPool;

public class OrdineManager {
	private DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Ordine aggiungiOrdine(Ordine ordine) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		PreparedStatement preparedStatement1=null;

	
		String updateSQL="INSERT into ordine (dataAcquisto,cartaDiCredito,email) values (?,?,?)";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(updateSQL);
               

                preparedStatement.setString(1, ordine.getDataAcquisto().format(FORMATO_DIA));
                preparedStatement.setString(2, ordine.getCartaDiCredito().getnCarta());
                preparedStatement.setString(3, ordine.getAccount().getEmail());
              
             
            	System.out.println("aggiungiOrdine: "+ preparedStatement.toString());
                preparedStatement.executeUpdate();
                

                String sql1="SELECT codOrdine FROM ordine ORDER BY codOrdine DESC LIMIT 1";
                preparedStatement1=connection.prepareStatement(sql1);
                ResultSet rs = preparedStatement1.executeQuery();
                
                if(rs.next()) {
                	System.out.println("Ho trovato ordine con id "+rs.getInt("codOrdine"));
        			ordine.setCodOrdine(rs.getInt("codOrdine"));
        			
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
        
        
        return ordine;
    }
	
	

}
