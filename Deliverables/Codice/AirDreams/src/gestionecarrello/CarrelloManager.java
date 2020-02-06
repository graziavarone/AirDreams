package gestionecarrello;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestionecompagniaaerea.CompagniaAerea;
import gestionecompagniaaerea.CompagniaAereaManager;
import gestioneutente.Account;
import gestioneutente.UtenteManager;
import gestionevolo.Aeroporto;
import gestionevolo.AeroportoManager;
import gestionevolo.Volo;
import gestionevolo.VoloManager;

public class CarrelloManager {
	private DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Carrello getCarrelloUtente(String email) throws SQLException {
		Carrello carrello = new Carrello();
        Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String selectSQL="SELECT * FROM carrello WHERE utente=?";
		
        try {
        	connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
            	String idVolo=rs.getString("volo");
            	
            	VoloManager voloManager=new VoloManager();
            	Volo volo = voloManager.findByID(idVolo);
            	
            	carrello.getVoli().put(volo, rs.getInt("quantity"));	
				
            	
            }
            carrello.setAccount(email);
        } finally {
        	try {
        		if(preparedStatement!=null) preparedStatement.close();
        		}
        		finally {
        			DriverManagerConnectionPool.releaseConnection(connection);
        		}
        	}
        return carrello; 
    }
}
