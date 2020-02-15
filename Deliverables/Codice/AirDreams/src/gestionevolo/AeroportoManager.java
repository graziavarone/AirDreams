package gestionevolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;

/**
 * La classe si occupa di implementare i metodi per effettuare l’inserimento, 
 * la cancellazione e la ricerca di un aeroporto 
 * possibilmente utilizzato in un volo come luogo di partenza 
 * o destinazione, memorizzato all’interno del DB
 */
public class AeroportoManager {

	/**
	 * metodo che permette di recuperare la lista degli aeroporti di una data città
	 * @param a città di cui ricercare gli aeroporti
	 * @return ArrayList<Aeroporto> lista degli aeroporti individuati
	 */
	public ArrayList<Aeroporto> getAeroportiByCity(String a) {
		 ArrayList<Aeroporto> listAeroportiByCity = new ArrayList<Aeroporto>();
		 Connection con = null;
		 PreparedStatement preparedStatement=null;
		 
		 String selectSQL="SELECT * FROM aeroporto WHERE city LIKE ?";
		 try { 
			 con = DriverManagerConnectionPool.getConnection();
		     preparedStatement= con.prepareStatement(selectSQL);
		        
		     preparedStatement.setString(1, a + "%");
		     ResultSet rs = preparedStatement.executeQuery();
		     while (rs.next()) {
		    	 Aeroporto bean= new Aeroporto();
		 		 bean.setCodice(rs.getString("codice"));
		 		 bean.setNome(rs.getString("nome"));
		 		 bean.setCity(rs.getString("city"));
		 		 bean.setStato(rs.getString("stato"));
		 			
		 		 listAeroportiByCity.add(bean);
		     }
		     System.out.println( preparedStatement.toString());
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
		 return listAeroportiByCity;
	}
	
	/**
	 * metodo che permette di recuperare un aeroporto dato l'id identificativo
	 * @param c id identificativo dell'aeroporto da ricercare
	 * @return Aeroporto aeroporto avente l'id indicato da parametro
	 * @throws SQLException
	 */
	public Aeroporto findAeroportoById(String c) throws SQLException {
		 Aeroporto aeroporto = null;
		 Connection con = null;
		 PreparedStatement preparedStatement=null;
		 
		 String selectSQL="SELECT * FROM aeroporto WHERE codice = ?";
		 
		 try { 
			 con = DriverManagerConnectionPool.getConnection();
		     preparedStatement= con.prepareStatement(selectSQL);
		        
		     preparedStatement.setString(1, c);
		     ResultSet rs = preparedStatement.executeQuery();
		     if (rs.next()) {
		    	 aeroporto = new Aeroporto();
		 			
		 		 aeroporto.setCodice(rs.getString("codice"));
		 		 aeroporto.setNome(rs.getString("nome"));
		 		 aeroporto.setCity(rs.getString("city"));
		 		 aeroporto.setStato(rs.getString("stato"));
		 	 }
		     System.out.println( preparedStatement.toString());
		 } catch (SQLException e) {
				e.printStackTrace();
		 }
		 
		 return aeroporto;
	}
}