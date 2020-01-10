package gestionecompagniaaerea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DriverManagerConnectionPool;
import gestioneutente.Account;

public class PoliticaBagaglioManager {
	
	public boolean aggiungiPoliticaBagaglio(PoliticaBagaglio politicaBagaglio) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="INSERT into politicaBagaglio (peso,dimensioni,prezzo, compagniaAerea) values (?,?,?,?)";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(updateSQL);
               

                preparedStatement.setInt(1, politicaBagaglio.getPeso());
                preparedStatement.setString(2, politicaBagaglio.getDimensioni());
                preparedStatement.setFloat(3, politicaBagaglio.getPrezzo());
                preparedStatement.setString(4, politicaBagaglio.getCompagnia().getNome());
                
            	System.out.println("aggiungiPoliticaBagaglio: "+ preparedStatement.toString());
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

	public PoliticaBagaglio[] trovaPoliticheCompagnia(String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PoliticaBagaglio[] politicaBagagli=new PoliticaBagaglio[2];
		int i=0;
	
		String selectSQL = "SELECT * FROM politicaBagaglio WHERE compagniaAerea = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
			
			System.out.println("trovaPoliticheCompagnia: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				PoliticaBagaglio bagaglio = new PoliticaBagaglio();
				
				bagaglio.setPeso(rs.getInt("peso"));
				bagaglio.setDimensioni(rs.getString("dimensioni"));
				bagaglio.setPrezzo(rs.getFloat("prezzo"));
				CompagniaAereaManager manager=new CompagniaAereaManager();
				CompagniaAerea compagniaAerea=manager.visualizzaInfoCompagniaAerea(rs.getString("compagniaAerea"));
				bagaglio.setCompagnia(compagniaAerea);	
				
				politicaBagagli[i++]=bagaglio;
			}
			
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}			
		}
		return politicaBagagli;
	}

	public boolean aggiornaPoliticaBagaglio(PoliticaBagaglio bagaglio) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="UPDATE politicaBagaglio set peso=? dimensioni=? prezzo=? where codice=?";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(updateSQL);
               

                preparedStatement.setInt(1, bagaglio.getPeso());
                preparedStatement.setString(2, bagaglio.getDimensioni());
                preparedStatement.setFloat(3,bagaglio.getPrezzo());
                preparedStatement.setInt(4,bagaglio.getCodice());
                
            	System.out.println("AggiornaBagaglio: "+ preparedStatement.toString());
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
