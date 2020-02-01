package gestionevolo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestionecompagniaaerea.CompagniaAerea;
import gestionecompagniaaerea.CompagniaAereaManager;

public class VoloManager {
	private DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	//si dovrebbe controllare posti disponibili e orario partenza tra un volo e un altro(?)
	public ArrayList<Volo> cercaDiretti(String aeroportoPartenza, String aeroportoArrivo, LocalDate dataDepartureLd, int passeggeri) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Volo> arrayList=new ArrayList<Volo>();
		AeroportoManager aeroportoManager=new AeroportoManager();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			st = con.createStatement();
		
			String sql= "SELECT * FROM volo v WHERE v.aeroportoPart=? and v.aeroportoArr=? and v.dataPart=?";
			PreparedStatement ps = con.prepareStatement(sql);
		
			ps.setString(1, aeroportoPartenza);
			ps.setString(2, aeroportoArrivo);
			ps.setString(3, dataDepartureLd.format(FORMATO_DIA));
			
			System.out.println("CercaVoliDiretti "+ps.toString());
			rs=ps.executeQuery();
			
			while (rs.next()) {
				Volo volo=new Volo();
				
				volo.setId(rs.getInt("idVolo"));
				
				Aeroporto aeroportoP=aeroportoManager.findAeroportoById(rs.getString("aeroportoPart"));
				Aeroporto aeroportoA=aeroportoManager.findAeroportoById(rs.getString("aeroportoArr"));
				
				volo.setId(rs.getInt("idVolo"));
				volo.setOtherDayDate(LocalDate.parse(rs.getString("dataPart"), FORMATO_DIA));
				volo.setPrezzo(rs.getFloat("prezzo"));
				volo.setSeats(rs.getInt("postiDisponibili"));
				volo.setDurataVolo(LocalTime.parse(rs.getString("durata")));
				volo.setOrarioPartenza(LocalTime.parse(rs.getString("orarioPart")));
				volo.setCompreso(rs.getBoolean("bagaglioStivaCompreso"));
				
				CompagniaAereaManager compagniaAereaManager=new CompagniaAereaManager();
				CompagniaAerea compagniaAerea=compagniaAereaManager.visualizzaInfoCompagniaAerea(rs.getString("compagniaAerea"));
				volo.setCa(compagniaAerea);
				
				volo.setAeroportoP(aeroportoP);
				volo.setAeroportoA(aeroportoA);

				int postiRimasti=volo.getSeats()-passeggeri;
				System.out.println("I posti rimasti del volo "+volo+" sono "+postiRimasti);
				if(postiRimasti>=0)
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

	public ArrayList<Volo[]> cercaUnoScalo(String aeroportoPartenza, String aeroportoArrivo,LocalDate dataDepartureLd,int passeggeri) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Volo[]> arrayList=new ArrayList<Volo[]>();
		AeroportoManager aeroportoManager=new AeroportoManager();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			st = con.createStatement();
		
			String sql="select f1.*, f2.* from volo f1, volo f2 where f1.aeroportoPart=? and "
			+ "f1.aeroportoArr<>? and f1.aeroportoArr=f2.aeroportoPart and f2.aeroportoArr=? and f1.dataPart=?" ;
			PreparedStatement ps = con.prepareStatement(sql);
		
			ps.setString(1, aeroportoPartenza);
			ps.setString(2, aeroportoArrivo);
			ps.setString(3, aeroportoArrivo);
			ps.setString(4, dataDepartureLd.format(FORMATO_DIA));
			rs=ps.executeQuery();
			
			while (rs.next()) {
				Volo[] volo=new Volo[2];


				volo[0]=new Volo();
				volo[0].setId(rs.getInt("f1.idVolo"));
				volo[0].setOtherDayDate(LocalDate.parse(rs.getString("f1.dataPart"), FORMATO_DIA));
				volo[0].setPrezzo(rs.getFloat("f1.prezzo"));
				volo[0].setSeats(rs.getInt("f1.postiDisponibili"));
				volo[0].setDurataVolo(LocalTime.parse(rs.getString("f1.durata")));
				volo[0].setOrarioPartenza(LocalTime.parse(rs.getString("f1.orarioPart")));
				volo[0].setCompreso(rs.getBoolean("f1.bagaglioStivaCompreso"));
				CompagniaAereaManager compagniaAereaManager=new CompagniaAereaManager();
				CompagniaAerea compagniaAerea=compagniaAereaManager.visualizzaInfoCompagniaAerea(rs.getString("f1.compagniaAerea"));
				volo[0].setCa(compagniaAerea);
				Aeroporto aeroportoP=aeroportoManager.findAeroportoById(rs.getString("f1.aeroportoPart"));
				Aeroporto aeroportoA=aeroportoManager.findAeroportoById(rs.getString("f1.aeroportoArr"));
				volo[0].setAeroportoP(aeroportoP);
				volo[0].setAeroportoA(aeroportoA);
				
				volo[1]=new Volo();
				volo[1].setId(rs.getInt("f2.idVolo"));
				volo[1].setOtherDayDate(LocalDate.parse(rs.getString("f2.dataPart"), FORMATO_DIA));
				volo[1].setPrezzo(rs.getFloat("f2.prezzo"));
				volo[1].setSeats(rs.getInt("f2.postiDisponibili"));
				volo[1].setDurataVolo(LocalTime.parse(rs.getString("f2.durata")));
				volo[1].setOrarioPartenza(LocalTime.parse(rs.getString("f2.orarioPart")));
				volo[1].setCompreso(rs.getBoolean("f2.bagaglioStivaCompreso"));
				compagniaAerea=compagniaAereaManager.visualizzaInfoCompagniaAerea(rs.getString("f2.compagniaAerea"));
				volo[1].setCa(compagniaAerea);
				aeroportoP=aeroportoManager.findAeroportoById(rs.getString("f2.aeroportoPart"));
				aeroportoA=aeroportoManager.findAeroportoById(rs.getString("f2.aeroportoArr"));
				volo[1].setAeroportoP(aeroportoP);
				volo[1].setAeroportoA(aeroportoA);
				
				
				int postiPrimoVolo=volo[0].getSeats()-passeggeri;
				int postiSecondoVolo=volo[1].getSeats()-passeggeri;
				
				
				LocalDateTime dataArrivoPrimoVolo=volo[0].getDataArrivo();
				LocalDateTime dataPartenzaSecondoVolo=LocalDateTime.of(volo[1].getDataPartenza(),volo[1].getOrarioPartenza());
				
				LocalDateTime tempDateTime = LocalDateTime.from( dataArrivoPrimoVolo );
				
				long days = tempDateTime.until( dataPartenzaSecondoVolo, ChronoUnit.DAYS );
				


				
				if((postiPrimoVolo>=0 && postiSecondoVolo>=0) &&
						(dataArrivoPrimoVolo.isBefore(dataPartenzaSecondoVolo) ) && days<=1)
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

	public ArrayList<Volo[]> cercaDueScali(String aeroportoPartenza, String aeroportoArrivo,LocalDate dataDepartureLd,int passeggeri) {
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
					+ "f2.aeroportoArr<>? and f3.aeroportoArr=? and f1.dataPart=?";
			PreparedStatement ps = con.prepareStatement(sql);
		
			ps.setString(1, aeroportoPartenza);
			ps.setString(2, aeroportoArrivo);
			ps.setString(3, aeroportoArrivo);
			ps.setString(4, aeroportoArrivo);
			ps.setString(5,dataDepartureLd.format(FORMATO_DIA));
			rs=ps.executeQuery();
			
			while (rs.next()) {
				Volo[] volo=new Volo[3];


				volo[0]=new Volo();
				volo[0].setId(rs.getInt("f1.idVolo"));
				volo[0].setOtherDayDate(LocalDate.parse(rs.getString("f1.dataPart"), FORMATO_DIA));
				volo[0].setPrezzo(rs.getFloat("f1.prezzo"));
				volo[0].setSeats(rs.getInt("f1.postiDisponibili"));
				volo[0].setDurataVolo(LocalTime.parse(rs.getString("f1.durata")));
				volo[0].setOrarioPartenza(LocalTime.parse(rs.getString("f1.orarioPart")));
				volo[0].setCompreso(rs.getBoolean("f1.bagaglioStivaCompreso"));
				CompagniaAereaManager compagniaAereaManager=new CompagniaAereaManager();
				CompagniaAerea compagniaAerea=compagniaAereaManager.visualizzaInfoCompagniaAerea(rs.getString("f1.compagniaAerea"));
				volo[0].setCa(compagniaAerea);
				Aeroporto aeroportoP=aeroportoManager.findAeroportoById(rs.getString("f1.aeroportoPart"));
				Aeroporto aeroportoA=aeroportoManager.findAeroportoById(rs.getString("f1.aeroportoArr"));
				volo[0].setAeroportoP(aeroportoP);
				volo[0].setAeroportoA(aeroportoA);
				
				volo[1]=new Volo();
				volo[1].setId(rs.getInt("f2.idVolo"));
				volo[1].setOtherDayDate(LocalDate.parse(rs.getString("f2.dataPart"), FORMATO_DIA));
				volo[1].setPrezzo(rs.getFloat("f2.prezzo"));
				volo[1].setSeats(rs.getInt("f2.postiDisponibili"));
				volo[1].setDurataVolo(LocalTime.parse(rs.getString("f2.durata")));
				volo[1].setOrarioPartenza(LocalTime.parse(rs.getString("f2.orarioPart")));
				volo[1].setCompreso(rs.getBoolean("f2.bagaglioStivaCompreso"));
				compagniaAerea=compagniaAereaManager.visualizzaInfoCompagniaAerea(rs.getString("f2.compagniaAerea"));
				volo[1].setCa(compagniaAerea);
				aeroportoP=aeroportoManager.findAeroportoById(rs.getString("f2.aeroportoPart"));
				aeroportoA=aeroportoManager.findAeroportoById(rs.getString("f2.aeroportoArr"));
				volo[1].setAeroportoP(aeroportoP);
				volo[1].setAeroportoA(aeroportoA);
				
				volo[2]=new Volo();
				volo[2].setId(rs.getInt("f3.idVolo"));
				volo[2].setOtherDayDate(LocalDate.parse(rs.getString("f3.dataPart"), FORMATO_DIA));
				volo[2].setPrezzo(rs.getFloat("f3.prezzo"));
				volo[2].setSeats(rs.getInt("f3.postiDisponibili"));
				volo[2].setDurataVolo(LocalTime.parse(rs.getString("f3.durata")));
				volo[2].setOrarioPartenza(LocalTime.parse(rs.getString("f3.orarioPart")));
				volo[2].setCompreso(rs.getBoolean("f3.bagaglioStivaCompreso"));
				compagniaAerea=compagniaAereaManager.visualizzaInfoCompagniaAerea(rs.getString("f3.compagniaAerea"));
				volo[2].setCa(compagniaAerea);
				aeroportoP=aeroportoManager.findAeroportoById(rs.getString("f3.aeroportoPart"));
				aeroportoA=aeroportoManager.findAeroportoById(rs.getString("f3.aeroportoArr"));
				volo[2].setAeroportoP(aeroportoP);
				volo[2].setAeroportoA(aeroportoA);
				
				
				int postiPrimoVolo=volo[0].getSeats()-passeggeri;
				int postiSecondoVolo=volo[1].getSeats()-passeggeri;
				int postiTerzoVolo=volo[2].getSeats()-passeggeri;
				
		
				
				LocalDateTime dataArrivoPrimoVolo=volo[0].getDataArrivo();
				LocalDateTime dataPartenzaSecondoVolo=LocalDateTime.of(volo[1].getDataPartenza(),volo[1].getOrarioPartenza());
				
				LocalDateTime tempDateTimeUno = LocalDateTime.from( dataArrivoPrimoVolo );
				long daysPrimoScalo = tempDateTimeUno.until( dataPartenzaSecondoVolo, ChronoUnit.DAYS );

				

				LocalDateTime dataArrivoSecondoVolo=volo[1].getDataArrivo();
				LocalDateTime dataPartenzaTerzoVolo=LocalDateTime.of(volo[2].getDataPartenza(),volo[2].getOrarioPartenza());
				
				
				LocalDateTime tempDateTimeDue = LocalDateTime.from( dataArrivoSecondoVolo );
				long daysSecondoScalo = tempDateTimeDue.until( dataPartenzaTerzoVolo, ChronoUnit.DAYS );
				
			
				
				if((postiPrimoVolo>=0 && postiSecondoVolo>=0 && postiTerzoVolo>=0) && 
						(dataArrivoPrimoVolo.isBefore(dataPartenzaSecondoVolo)) && 
						(dataArrivoSecondoVolo.isBefore(dataPartenzaTerzoVolo)) && daysPrimoScalo<=1 && daysSecondoScalo<=1) 
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
