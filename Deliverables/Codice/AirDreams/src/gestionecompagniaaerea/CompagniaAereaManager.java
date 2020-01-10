package gestionecompagniaaerea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

import db.DriverManagerConnectionPool;

public class CompagniaAereaManager {

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
	
	public ArrayList<CompagniaAerea> getCompagnie() throws SQLException {
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
	}

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
