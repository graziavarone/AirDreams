package gestioneutente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import compagniaaerea.CompagniaAerea;
import compagniaaerea.CompagniaAereaManager;
import db.DriverManagerConnectionPool;

public class UtenteManager {

	public Account signIn(Account account) {
		Account acc=null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			st = con.createStatement();
		
			String sql= "SELECT * FROM utente WHERE email=? AND passwordUtente=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,account.getEmail());
			ps.setString(2, account.getPassword());
			rs=ps.executeQuery();
			
			if (rs.next()) {
				CompagniaAereaManager manager=new CompagniaAereaManager();
				acc=new Account();
				acc.setNome(rs.getString("nome"));
				acc.setCognome(rs.getString("cognome"));
				acc.setEmail(rs.getString("email"));
				acc.setPassword(rs.getString("passwordUtente"));
				CompagniaAerea compagniaAerea=manager.visualizzaInfoCompagniaAerea(rs.getString("compagniaAerea"));
				acc.setCompagniaAerea(compagniaAerea);
				Ruolo ruolo= Ruolo.valueOf(rs.getString("ruolo"));
				acc.setRuolo(ruolo);
				
				return acc;
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
		
		return acc;
	}
}
