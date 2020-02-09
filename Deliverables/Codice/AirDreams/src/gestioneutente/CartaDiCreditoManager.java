package gestioneutente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestionecompagniaaerea.CompagniaAerea;
import gestionecompagniaaerea.CompagniaAereaManager;

public class CartaDiCreditoManager {
	
	public ArrayList<CartaDiCredito> findAll(String email) throws SQLException{
		ArrayList<CartaDiCredito> allCarte = new ArrayList<CartaDiCredito>();
        Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String selectSQL="SELECT * FROM cartaDiCredito WHERE utente=?";
		
        try {
        	connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
            	CartaDiCredito a = new CartaDiCredito();
            	
            	a.setnCarta(rs.getString("nCarta"));
            	a.setTitolare(rs.getString("titolare"));
            	a.setDataScadenza(rs.getString("dataScadenza"));
            	a.setCvc(rs.getInt("cvc"));
            	UtenteManager utenteManager=new UtenteManager();
            	Account account=utenteManager.findAccountByEmail(email);
            	a.setAccount(account);
				
				allCarte.add(a);
            }
        } finally {
        	try {
        		if(preparedStatement!=null) preparedStatement.close();
        		}
        		finally {
        			DriverManagerConnectionPool.releaseConnection(connection);
        		}
        	}
        return allCarte; 
		
	}
	
	public CartaDiCredito cercaCarta(String numeroCarta,String email) throws SQLException{
		CartaDiCredito carta = null;
        Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String selectSQL="SELECT * FROM cartaDiCredito WHERE utente=? and nCarta=?";
		
        try {
        	connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, numeroCarta);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
            	 carta = new CartaDiCredito();
            	
            	 carta.setnCarta(rs.getString("nCarta"));
            	 carta.setTitolare(rs.getString("titolare"));
            	 carta.setDataScadenza(rs.getString("dataScadenza"));
            	 carta.setCvc(rs.getInt("cvc"));
            	UtenteManager utenteManager=new UtenteManager();
            	Account account=utenteManager.findAccountByEmail(email);
            	carta.setAccount(account);
				
				
            }
        } finally {
        	try {
        		if(preparedStatement!=null) preparedStatement.close();
        		}
        		finally {
        			DriverManagerConnectionPool.releaseConnection(connection);
        		}
        	}
        return carta; 
		
	}
	public boolean creaCartaDiCredito(CartaDiCredito cc) throws SQLException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			st = con.createStatement();
		
			String sql= "INSERT INTO cartaDiCredito (nCarta, titolare, dataScadenza, cvc, utente) values (?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,cc.getnCarta());
			ps.setString(2,cc.getTitolare());
			ps.setString(3,cc.getDataScadenza());
			ps.setInt(4,cc.getCvc());
			ps.setString(5, cc.getAccount().getEmail());
			
			
			System.out.println("creaCartaDiCredito: "+ ps.toString());
            ps.executeUpdate();
       
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
		
		return true;
	}
	public boolean eliminaCarta(String nCarta, String email) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			st = con.createStatement();
		
			String sql= "DELETE FROM cartaDiCredito WHERE nCarta=? and utente=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,nCarta);
			ps.setString(2,email);

			
			
			System.out.println("eliminaCarta: "+ ps.toString());
            ps.executeUpdate();
       
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
		
		return true;
		
	}

}
