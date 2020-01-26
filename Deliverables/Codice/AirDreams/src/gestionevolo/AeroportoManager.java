package gestionevolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestioneutente.Account;



public class AeroportoManager {

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
		        	Aeroporto bean= new Aeroporto();
		 			
		 			bean.setCodice(rs.getString("codice"));
		 			bean.setNome(rs.getString("nome"));
		 			bean.setCity(rs.getString("city"));
		 			bean.setStato(rs.getString("stato"));
		 		

		        }
		       System.out.println( preparedStatement.toString());
		    } catch (SQLException e) {
				e.printStackTrace();
		    }
		    return aeroporto;
		}
	
	}

