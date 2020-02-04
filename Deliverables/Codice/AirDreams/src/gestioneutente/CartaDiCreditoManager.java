package gestioneutente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DriverManagerConnectionPool;
import gestionecompagniaaerea.CompagniaAerea;
import gestionecompagniaaerea.CompagniaAereaManager;

public class CartaDiCreditoManager {
	
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

}
