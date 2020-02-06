package gestionecompagniaaerea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DriverManagerConnectionPool;

public class PoliticaBagaglioManager {
	
	public boolean aggiungiPoliticaBagaglioStiva(PoliticaBagaglioStiva politicaBagaglio) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="INSERT into politicaBagaglioStiva (peso,dimensioni,prezzo, compagniaAerea) values (?,?,?,?)";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(updateSQL);
               

                preparedStatement.setInt(1, politicaBagaglio.getPeso());
                preparedStatement.setString(2, politicaBagaglio.getDimensioni());
                preparedStatement.setFloat(3, politicaBagaglio.getPrezzo());
                preparedStatement.setString(4, politicaBagaglio.getCompagnia().getNome());
                
            	System.out.println("aggiungiPoliticaBagaglioStiva: "+ preparedStatement.toString());
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
	
	public boolean aggiungiPoliticaBagaglioMano(PoliticaBagaglioMano politicaBagaglio) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="INSERT into politicaBagaglioMano (peso,dimensioni,compagniaAerea) values (?,?,?)";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(updateSQL);
               

                preparedStatement.setInt(1, politicaBagaglio.getPeso());
                preparedStatement.setString(2, politicaBagaglio.getDimensioni());
    
                preparedStatement.setString(3, politicaBagaglio.getCompagnia().getNome());
                
            	System.out.println("aggiungiPoliticaBagaglioMano: "+ preparedStatement.toString());
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


	public PoliticaBagaglioStiva trovaPoliticaCompagniaStiva(String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PoliticaBagaglioStiva bagaglio=null;
	
	
		String selectSQL = "SELECT * FROM politicaBagaglioStiva WHERE compagniaAerea = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
			
			System.out.println("trovaPoliticaCompagniaStiva: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				bagaglio = new PoliticaBagaglioStiva();
				
				bagaglio.setPeso(rs.getInt("peso"));
				bagaglio.setDimensioni(rs.getString("dimensioni"));
				bagaglio.setPrezzo(rs.getFloat("prezzo"));
				CompagniaAereaManager manager=new CompagniaAereaManager();
				CompagniaAerea compagniaAerea=manager.visualizzaInfoCompagniaAerea(rs.getString("compagniaAerea"));
				bagaglio.setCompagnia(compagniaAerea);	
			}
			
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}			
		}
		return bagaglio;
	}

	public PoliticaBagaglioMano trovaPoliticaCompagniaMano(String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PoliticaBagaglioMano bagaglio=null;
	
	
		String selectSQL = "SELECT * FROM politicaBagaglioMano WHERE compagniaAerea = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
			
			System.out.println("trovaPoliticaCompagniaMano: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				bagaglio = new PoliticaBagaglioMano();
				
				bagaglio.setPeso(rs.getInt("peso"));
				bagaglio.setDimensioni(rs.getString("dimensioni"));
				CompagniaAereaManager manager=new CompagniaAereaManager();
				CompagniaAerea compagniaAerea=manager.visualizzaInfoCompagniaAerea(rs.getString("compagniaAerea"));
				bagaglio.setCompagnia(compagniaAerea);	
			}
			
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}			
		}
		return bagaglio;
	}
	
	public boolean aggiornaPoliticaBagaglioStiva(PoliticaBagaglioStiva bagaglio) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="UPDATE politicaBagaglioStiva set peso=? ,dimensioni=?, prezzo=? where compagniaAerea=?";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(updateSQL);
               

                preparedStatement.setInt(1, bagaglio.getPeso());
                preparedStatement.setString(2, bagaglio.getDimensioni());
                preparedStatement.setFloat(3,bagaglio.getPrezzo());
                preparedStatement.setString(4,bagaglio.getCompagnia().getNome());
            	System.out.println("AggiornaBagaglioStiva: "+ preparedStatement.toString());
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
	
	
	public boolean aggiornaPoliticaBagaglioMano(PoliticaBagaglioMano bagaglio) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="UPDATE politicaBagaglioMano set peso=?,dimensioni=? where compagniaAerea=?";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(updateSQL);
               

                preparedStatement.setInt(1, bagaglio.getPeso());
                preparedStatement.setString(2, bagaglio.getDimensioni());
                preparedStatement.setString(3, bagaglio.getCompagnia().getNome());
            	System.out.println("AggiornaBagaglioMano: "+ preparedStatement.toString());
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
