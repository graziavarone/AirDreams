package gestionevolo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestionecompagniaaerea.CompagniaAerea;
import gestionecompagniaaerea.CompagniaAereaManager;
import gestioneutente.Account;
import gestioneutente.Ruolo;

public class VoloManager {
	
	public ArrayList<Volo> findAll(){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Volo> arrayList=new ArrayList<Volo>();
		AeroportoManager aeroportoManager=new AeroportoManager();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			st = con.createStatement();
		
			String sql= "SELECT * FROM volo";
			PreparedStatement ps = con.prepareStatement(sql);
		
			rs=ps.executeQuery();
			
			while (rs.next()) {
				Volo volo=new Volo();
				
				Aeroporto aeroportoPartenza=aeroportoManager.findAeroportoById(rs.getString("aeroportoPart"));
				Aeroporto aeroportoArrivo=aeroportoManager.findAeroportoById(rs.getString("aeroportoArr"));
				
				volo.setAeroportoP(aeroportoPartenza);
				volo.setAeroportoA(aeroportoArrivo);

				 arrayList.add(volo);
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
		
		return arrayList;
	}

	//si dovrebbe controllare posti disponibili e orario partenza tra un volo e un altro(?)
	public ArrayList<Volo> cercaDiretti(String aeroportoPartenza, String aeroportoArrivo) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Volo> arrayList=new ArrayList<Volo>();
		AeroportoManager aeroportoManager=new AeroportoManager();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			st = con.createStatement();
		
			String sql= "SELECT * FROM volo v WHERE v.aeroportoPart=? and v.aeroportoArr=?";
			PreparedStatement ps = con.prepareStatement(sql);
		
			ps.setString(1, aeroportoPartenza);
			ps.setString(2, aeroportoArrivo);
			rs=ps.executeQuery();
			
			while (rs.next()) {
				Volo volo=new Volo();
				
				volo.setId(rs.getInt("idVolo"));
				
				Aeroporto aeroportoP=aeroportoManager.findAeroportoById(rs.getString("aeroportoPart"));
				Aeroporto aeroportoA=aeroportoManager.findAeroportoById(rs.getString("aeroportoArr"));
				
				volo.setAeroportoP(aeroportoP);
				volo.setAeroportoA(aeroportoA);

				 arrayList.add(volo);
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
		
		return arrayList;
	}

	public ArrayList<Volo[]> cercaUnoScalo(String aeroportoPartenza, String aeroportoArrivo) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Volo[]> arrayList=new ArrayList<Volo[]>();
		AeroportoManager aeroportoManager=new AeroportoManager();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			st = con.createStatement();
		
			String sql="select f1.*, f2.* from volo f1, volo f2 where f1.aeroportoPart=? and "
			+ "f1.aeroportoArr<>? and f1.aeroportoArr=f2.aeroportoPart and f2.aeroportoArr=?" ;
			PreparedStatement ps = con.prepareStatement(sql);
		
			ps.setString(1, aeroportoPartenza);
			ps.setString(2, aeroportoArrivo);
			ps.setString(3, aeroportoArrivo);
			rs=ps.executeQuery();
			
			while (rs.next()) {
				Volo[] volo=new Volo[2];


				volo[0]=new Volo();
				volo[0].setId(rs.getInt("f1.idVolo"));
				Aeroporto aeroportoP=aeroportoManager.findAeroportoById(rs.getString("f1.aeroportoPart"));
				Aeroporto aeroportoA=aeroportoManager.findAeroportoById(rs.getString("f1.aeroportoArr"));
				volo[0].setAeroportoP(aeroportoP);
				volo[0].setAeroportoA(aeroportoA);
				
				volo[1]=new Volo();
				volo[1].setId(rs.getInt("f2.idVolo"));
				Aeroporto aeroportoP2=aeroportoManager.findAeroportoById(rs.getString("f2.aeroportoPart"));
				Aeroporto aeroportoA2=aeroportoManager.findAeroportoById(rs.getString("f2.aeroportoArr"));
				volo[1].setAeroportoP(aeroportoP2);
				volo[1].setAeroportoA(aeroportoA2);
				
				arrayList.add(volo);
				
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
		
		return arrayList;
	}

	public ArrayList<Volo[]> cercaDueScali(String aeroportoPartenza, String aeroportoArrivo) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Volo[]> arrayList=new ArrayList<Volo[]>();
		AeroportoManager aeroportoManager=new AeroportoManager();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			st = con.createStatement();
		
			String sql="select f1.*, f2.*, f3.* from volo f1, volo f2, volo f3 "
					+ "where f1.aeroportoPart=? and f1.aeroportoArr=f2.aeroportoPart and "
					+ " f1.aeroportoArr<>? and f2.aeroportoArr=f3.aeroportoPart and "
					+ "f2.aeroportoArr<>? and f3.aeroportoArr=?";
			PreparedStatement ps = con.prepareStatement(sql);
		
			ps.setString(1, aeroportoPartenza);
			ps.setString(2, aeroportoArrivo);
			ps.setString(3, aeroportoArrivo);
			ps.setString(4, aeroportoArrivo);
			rs=ps.executeQuery();
			
			while (rs.next()) {
				Volo[] volo=new Volo[3];

				volo[0]=new Volo();
				volo[0].setId(rs.getInt("f1.idVolo"));
				Aeroporto aeroportoP=aeroportoManager.findAeroportoById(rs.getString("f1.aeroportoPart"));
				Aeroporto aeroportoA=aeroportoManager.findAeroportoById(rs.getString("f1.aeroportoArr"));
				volo[0].setAeroportoP(aeroportoP);
				volo[0].setAeroportoA(aeroportoA);
				
				volo[1]=new Volo();
				volo[1].setId(rs.getInt("f2.idVolo"));
				Aeroporto aeroportoP2=aeroportoManager.findAeroportoById(rs.getString("f2.aeroportoPart"));
				Aeroporto aeroportoA2=aeroportoManager.findAeroportoById(rs.getString("f2.aeroportoArr"));
				volo[1].setAeroportoP(aeroportoP2);
				volo[1].setAeroportoA(aeroportoA2);
				
				volo[2]=new Volo();
				volo[2].setId(rs.getInt("f3.idVolo"));
				Aeroporto aeroportoP3=aeroportoManager.findAeroportoById(rs.getString("f3.aeroportoPart"));
				Aeroporto aeroportoA3=aeroportoManager.findAeroportoById(rs.getString("f3.aeroportoArr"));
				volo[2].setAeroportoP(aeroportoP3);
				volo[2].setAeroportoA(aeroportoA3);
				
				arrayList.add(volo);
				
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
		
		return arrayList;
	}

}
