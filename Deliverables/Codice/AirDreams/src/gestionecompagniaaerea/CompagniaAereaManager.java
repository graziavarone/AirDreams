package gestionecompagniaaerea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestioneutente.Account;

public class CompagniaAereaManager {

	public CompagniaAerea visualizzaInfoCompagniaAerea(String nome) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			st = con.createStatement();
		
			String sql= "SELECT * FROM compagniaAerea WHERE nome=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,nome);
			rs=ps.executeQuery();
			
			if (rs.next()) { 
				//se trova la compagnia, ne crea un istanza e la restituisce
				CompagniaAerea compagniaAerea=new CompagniaAerea();
				
				compagniaAerea.setNome(rs.getString("nome"));
				compagniaAerea.setSito(rs.getString("sito"));
				
				return compagniaAerea;
			} else {
				return null;
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
		
		return null;
	}
	
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
        		if(preparedStatement!=null) preparedStatement.close();
        		}
        		finally {
        			DriverManagerConnectionPool.releaseConnection(connection);
        		}
        	}
        return allComA; 
    }
}
