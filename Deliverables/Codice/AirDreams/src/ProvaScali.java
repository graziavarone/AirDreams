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
		ArrayList<Volo> voli=new ArrayList<Volo>();
		
		voli.add(new Volo("FRA", "AMS"));
		voli.add(new Volo("FRA", "CPH"));
		voli.add(new Volo("FRA", "MAD"));
		voli.add(new Volo("CPH", "AMS"));
		voli.add(new Volo("CPH", "MAD"));     
		voli.add(new Volo("MAD","AMS"));

		
		//finire di vedere https://stackoverflow.com/questions/42806094/combine-flights-between-two-cities

	}




}
