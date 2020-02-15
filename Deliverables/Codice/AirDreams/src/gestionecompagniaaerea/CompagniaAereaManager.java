package gestionecompagniaaerea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import db.DriverManagerConnectionPool;

/**
 * La classe si occupa di implementare i metodi per effettuare l'inserimento,
 * la cancellazione e la ricerca di una compagnia aerea in cui
 * il sistema offre i voli, memorizzata all'interno del DB
 */
public class CompagniaAereaManager {

	/**
	 * metodo che permette di visualizzare le informazioni di una compagnia aerea
	 * @param nome nome della compagnia aerea
	 * @return CompagniaAerea compagnia aerea avente il nome indicato
	 */
	public CompagniaAerea visualizzaInfoCompagniaAerea(String nome) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		CompagniaAerea compagniaAerea=null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			st = con.createStatement();
		
			String sql= "SELECT * FROM compagniaAerea WHERE nome=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,nome);
			rs=ps.executeQuery();
			
			if (rs.next()) { 
				//se trova la compagnia, ne crea un istanza e la restituisce
				compagniaAerea=new CompagniaAerea();
				
				compagniaAerea.setNome(rs.getString("nome"));
				compagniaAerea.setSito(rs.getString("sito"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
				DriverManagerConnectionPool.releaseConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Ho trovato"+ compagniaAerea);
		return compagniaAerea;
	}

	/**
	 * metodo che permette di aggiungere una compagnia aerea al sistema
	 * @param compagniaAerea compagnia aerea da aggiungere al DB
	 * @return boolean true se l'inserimento nel DB va a buon fine, false in caso contrario
	 * @throws SQLException
	 */
	public boolean aggiungiCompagnia(CompagniaAerea compagniaAerea) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="INSERT into compagniaAerea values (?,?)";
        
        try {
        	connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);
               
            preparedStatement.setString(1, compagniaAerea.getNome());
            preparedStatement.setString(2, compagniaAerea.getSito());
      
            System.out.println("AddCompagnia: "+ preparedStatement.toString());
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
	
	/*public ArrayList<CompagniaAerea> getCompagnie() throws SQLException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<CompagniaAerea> lista=new ArrayList<CompagniaAerea>();
		CompagniaAerea compagniaAerea=null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			st = con.createStatement();
		
			String sql= "SELECT * FROM compagniaAerea";
			PreparedStatement ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while (rs.next()) { 
				compagniaAerea=new CompagniaAerea();
				
				compagniaAerea.setNome(rs.getString("nome"));
				compagniaAerea.setSito(rs.getString("sito"));
	
				
				lista.add(compagniaAerea);

			}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
				DriverManagerConnectionPool.releaseConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lista;
	}*/

	/**
	 * metodo che permette di aggiornare una compagnia aerea di gestione del sistema
	 * @param compagnia compagnia aerea modificata
	 * @return boolean true se l'inserimento nel DB va a buon fine, false in caso contrario
	 * @throws SQLException
	 */
	public boolean aggiornaCompagnia(CompagniaAerea compagnia) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="UPDATE compagniaAerea set sito=? where nome=?";
        
        try {
        	connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);
              
            preparedStatement.setString(1, compagnia.getSito());
            preparedStatement.setString(2, compagnia.getNome());
                
            System.out.println("AggiornaCompagnia: "+ preparedStatement.toString());
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
	 * metodo che permette di recuperare tutte le compagnie aeree di gestione del sistema
	 * @return ArrayList<CompagniaAerea> lista delle compagnie aeree recuperate da DB
	 * @throws SQLException
	 */
	public ArrayList<CompagniaAerea> getAllCompanies() throws SQLException {
		ArrayList<CompagniaAerea> allComA = new ArrayList<CompagniaAerea>();
        Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String selectSQL="SELECT * FROM compagniaAerea";
		
        try {
        	connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
            	CompagniaAerea ca = new CompagniaAerea();
            	
            	ca.setNome(rs.getString("nome"));
				ca.setSito(rs.getString("sito"));
				
				allComA.add(ca);
            }
        } finally {
        	try {
        		if(preparedStatement!=null)
        			preparedStatement.close();
        	} finally {
        		DriverManagerConnectionPool.releaseConnection(connection);
        	}
        }
        
        return allComA; 
    }
	
	/**
	 * metodo che permette di eliminare una compagnia aerea dalla gestione del sistema
	 * @param nome nome della compagnia aerea da eliminare
	 * @return boolean true se la cancellazione nel DB va a buon fine, false in caso contrario
	 * @throws SQLException
	 */
	public boolean eliminaCompagnia(String nome) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="DELETE from compagniaAerea where nome=?";
        
        try {
        	connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);

            preparedStatement.setString(1, nome);
                
            System.out.println("eliminaCompagnia: "+ preparedStatement.toString());
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
