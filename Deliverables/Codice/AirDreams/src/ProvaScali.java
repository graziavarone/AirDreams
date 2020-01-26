import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import db.DriverManagerConnectionPool;
import gestioneutente.Account;

public class ProvaScali {
	

	public static void main(String[] args) throws SQLException {
		VoloManager manager=new VoloManager();
		
		String aeroportoPartenza="NAP";
		String aeroportoArrivo="NCE";
		
		//trovo voli diretti
		ArrayList<Volo> voliDiretti=manager.cercaDiretti(aeroportoPartenza,aeroportoArrivo);
		
		for(Volo v: voliDiretti)
			System.out.println("Volo "+v);
		
		System.out.println("###################");
		
		ArrayList<Volo[]> voliUnoScalo=manager.cercaUnoScalo(aeroportoPartenza,aeroportoArrivo);
		
		for(Volo[] v: voliUnoScalo)
			System.out.println("Volo con uno scalo... primo volo "+v[0]+"..secondo volo "+v[1]);
		
		
		System.out.println("###################");
		
		ArrayList<Volo[]> voliDueScali=manager.cercaDueScali(aeroportoPartenza,aeroportoArrivo);
		
		for(Volo[] v: voliDueScali)
			System.out.println("Volo con due scali... primo volo "+v[0]+"..secondo volo "+v[1]+"..ultimo volo"+v[2]);

	}




}
