package gestioneordine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import db.DriverManagerConnectionPool;
import gestioneutente.CartaDiCreditoManager;
import gestioneutente.UtenteManager;

public class OrdineManager {
	private DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Ordine aggiungiOrdine(Ordine ordine) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		PreparedStatement preparedStatement1=null;

	
		String updateSQL="INSERT into ordine (dataAcquisto,cartaDiCredito,email) values (?,?,?)";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(updateSQL);
               

                preparedStatement.setString(1, ordine.getDataAcquisto().format(FORMATO_DIA));
                preparedStatement.setString(2, ordine.getCartaDiCredito().getnCarta());
                preparedStatement.setString(3, ordine.getAccount().getEmail());
              
             
            	System.out.println("aggiungiOrdine: "+ preparedStatement.toString());
                preparedStatement.executeUpdate();
                

                String sql1="SELECT codOrdine FROM ordine ORDER BY codOrdine DESC LIMIT 1";
                preparedStatement1=connection.prepareStatement(sql1);
                ResultSet rs = preparedStatement1.executeQuery();
                
                if(rs.next()) {
                	System.out.println("Ho trovato ordine con id "+rs.getInt("codOrdine"));
        			ordine.setCodOrdine(rs.getInt("codOrdine"));
        			
        		}
         		
                
            }
              finally {
            	try {
            		if(preparedStatement!=null) preparedStatement.close();
            		}
            		finally {
            			DriverManagerConnectionPool.releaseConnection(connection);
            		}
            	}
        
        
        return ordine;
    }
	
	public ArrayList<Ordine> cercaOrdiniUtente(String email) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		PreparedStatement preparedStatement1=null;
		ResultSet rs=null;
		ArrayList <Ordine> ordini= new ArrayList <Ordine>();
		UtenteManager utenteManager= new UtenteManager();
		CartaDiCreditoManager cartaDiCreditoManager=new CartaDiCreditoManager();
		String selectSQL="SELECT * from ordine WHERE email=?";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(selectSQL);
               
                preparedStatement.setString(1, email);
              
             
            	System.out.println("cercaOrdiniUtente: "+ preparedStatement.toString());
                rs=preparedStatement.executeQuery();
                
                while(rs.next()) {
                	Ordine ordine= new Ordine();
                	System.out.println("Ho instanziato ordine");
                	ordine.setCodOrdine(rs.getInt("codOrdine"));
                	ordine.setDataAcquisto(LocalDate.parse(rs.getString("dataAcquisto"),FORMATO_DIA));
                	ordine.setAccount(utenteManager.findAccountByEmail(email));
        			ordine.setCartaDiCredito(cartaDiCreditoManager.cercaCarta(rs.getString("cartaDiCredito"), email));
        			
        			ordini.add (ordine);
        		}
         		
                
            }
              finally {
            	try {
            		if(preparedStatement!=null) preparedStatement.close();
            		}
            		finally {
            			DriverManagerConnectionPool.releaseConnection(connection);
            		}
            	}
        
        
        return ordini;
    }

	public boolean annullaOrdine(int codice) throws SQLException {
		boolean b=false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		PreparedStatement preparedStatement1=null;

	
		String updateSQL="DELETE from ordine WHERE codOrdine=?";
        
        
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(updateSQL);
               

                preparedStatement.setInt(1, codice);

              
             
            	System.out.println("annullaOrdine: "+ preparedStatement.toString());
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
