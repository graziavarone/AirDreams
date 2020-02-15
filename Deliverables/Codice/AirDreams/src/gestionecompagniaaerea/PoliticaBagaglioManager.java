package gestionecompagniaaerea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DriverManagerConnectionPool;

/*
 * La classe si occupa di implementare i metodi per effettuare l'inserimento
 * la cancellazione e la ricerca di una politica bagaglio relativa
 * ad una data compagnia aerea, memorizzata all'interno del DB
 */
public class PoliticaBagaglioManager {
	
	/**
	 * metodo che permette di aggiungere una politica bagaglio a stiva per la compagnia aerea,
	 * ovvero dimensioni e costo previsti per ogni bagaglio a stiva
	 * @param politicaBagaglio politica bagaglio a stiva da memorizzare nel DB
	 * @return boolean true se la l'inserimento nel DB va a buon fine, false in caso contrario
	 * @throws SQLException
	 */
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
	 * metodo che permette di aggiungere una politica bagaglio a mano per la compagnia aerea,
	 * ovvero dimensioni previste per ogni bagaglio a stiva
	 * @param politicaBagaglio politica bagaglio a mano da memorizzare nel DB
	 * @return boolean true se la l'inserimento nel DB va a buon fine, false in caso contrario
	 * @throws SQLException
	 */
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
	 * metodo che permette di recuperare una politica bagaglio a stiva per una data compagnia aerea
	 * @param nome nome della compagnia aerea 
	 * @return PoliticaBagaglioStiva politica a bagaglio a stiva per la compagnia aerea con il nome indicato
	 * @throws SQLException
	 */
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

	/**
	 * metodo che permette di recuperare una politica bagaglio a mano per una data compagnia aerea
	 * @param nome nome della compagnia aerea 
	 * @return PoliticaBagaglioStiva politica a bagaglio a mano per la compagnia aerea con il nome indicato
	 * @throws SQLException
	 */
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
	
	/**
	 * metodo che permette di aggiornare una politica bagaglio a stiva per una data compagnia aerea
	 * @param bagaglio politica bagaglio a stiva da aggiornare nel D
	 * @return boolean true se la l'aggiornamento nel DB va a buon fine, false in caso contrario
	 * @throws SQLException
	 */
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
	 * metodo che permette di aggiornare una politica bagaglio a mano per una data compagnia aerea
	 * @param bagaglio politica bagaglio a mano da aggiornare nel D
	 * @return boolean true se la l'aggiornamento nel DB va a buon fine, false in caso contrario
	 * @throws SQLException
	 */
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
