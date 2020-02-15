package gestioneordine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import db.DriverManagerConnectionPool;

/**
 * La classe si occupa di implementare i metodi per effettuare l'inserimento,
 * la cancellazione e la ricerca di un bagaglio relativo ad un dato biglietto
 * presente in un ordine di un particolare cliente, memorizzato nel DB
 */
public class BagaglioManager {

	/**
	 * metodo che permette di aggiungere un bagaglio a mano ad un biglietto
	 * @param bagaglioMano bagaglio a mano da assegnare al biglietto
	 * @return boolean true se la l'inserimento nel DB va a buon fine, false in caso contrario
	 * @throws SQLException
	 */
	public boolean aggiungiBagaglioMano(BagaglioMano bagaglioMano) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="INSERT into bagaglioMano (peso,dimensioni,biglietto) values (?,?,?)";
        
        try {
        	connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);
               
            preparedStatement.setInt(1, bagaglioMano.getPeso());
            preparedStatement.setString(2, bagaglioMano.getDimensioni());
            preparedStatement.setInt(3, bagaglioMano.getBiglietto().getCodBiglietto());
                
            System.out.println("aggiungiBagaglioMano: "+ preparedStatement.toString());
            preparedStatement.executeUpdate();
                
            System.out.println("Ho eseguito query per aggiungere bagaglio");
            b=true;
        } finally {
        	try {
        		if(preparedStatement!=null)
        			preparedStatement.close();
            } finally {
            	DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
       
        return b;
	}
	
	/**
	 * metodo che permette di aggiungere un bagaglio a stiva ad un biglietto
	 * @param bagaglioMano bagaglio a stiva da assegnare al biglietto
	 * @return boolean true se la l'inserimento nel DB va a buon fine, false in caso contrario
	 * @throws SQLException
	 */
	public boolean aggiungiBagaglioStiva(BagaglioStiva bagaglioStiva) throws SQLException {
		boolean b = false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	
		String updateSQL="INSERT into bagaglioStiva (peso,dimensioni,biglietto,prezzo,quantity) values (?,?,?,?,?)";
        
        try {
        	connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);
               
            preparedStatement.setInt(1, bagaglioStiva.getPeso());
            preparedStatement.setString(2, bagaglioStiva.getDimensioni());
            preparedStatement.setInt(3, bagaglioStiva.getBiglietto().getCodBiglietto());
            preparedStatement.setFloat(4, bagaglioStiva.getPrezzo());
            preparedStatement.setInt(5, bagaglioStiva.getQuantity());
                
            System.out.println("aggiungiBagaglioStiva: "+ preparedStatement.toString());
            preparedStatement.executeUpdate();
            b=true;
        } finally {
        	try {
        		if(preparedStatement!=null)
        			preparedStatement.close();
            } finally {
            	DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        
        return b;
	}
	
	/**
	 * metodo che permette di recuperare il bagaglio a mano relativo ad un determinato biglietto
	 * @param biglietto biglietto di cui ricercare il bagaglio a mano
	 * @return BagaglioMano bagaglio a mano associato al biglietto indicato
	 * @throws SQLException
	 */
	public BagaglioMano cercaBagaglioManoBiglietto(Biglietto biglietto) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		BagaglioMano bagaglioMano=null;
		
		String selectSQL="SELECT * from bagaglioMano where biglietto=?";
        
        try {
        	connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
               
            preparedStatement.setInt(1, biglietto.getCodBiglietto());
                
            System.out.println("cercaBagagliManoBiglietto: "+ preparedStatement.toString());
            rs=preparedStatement.executeQuery();
               
            if(rs.next()) {
            	bagaglioMano=new BagaglioMano();
                		
                bagaglioMano.setBiglietto(biglietto);
                bagaglioMano.setDimensioni(rs.getString("dimensioni"));
                bagaglioMano.setPeso(rs.getInt("peso"));
            }
        } finally {
        	try {
        		if(preparedStatement!=null)
        			preparedStatement.close();
            } finally {
            	DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return bagaglioMano;
	}
	
	/**
	 * metodo che permette di recuperare il bagaglio a stiva relativo ad un determinato biglietto
	 * @param biglietto biglietto di cui ricercare il bagaglio a mano
	 * @return BagaglioStiva bagaglio a stiva associato al biglietto indicato
	 * @throws SQLException
	 */
	public HashSet<BagaglioStiva> cercaBagagliStivaBiglietto(Biglietto biglietto) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		HashSet<BagaglioStiva> bagagliStiva=null;
		
		String selectSQL="SELECT * from bagaglioStiva where biglietto=?";
        
		try {
			connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            
            preparedStatement.setInt(1, biglietto.getCodBiglietto());
                
            System.out.println("cercaBagagliStivaBiglietto: "+ preparedStatement.toString());
            rs=preparedStatement.executeQuery();
            
            if(rs.next()) {
            	bagagliStiva=new HashSet<BagaglioStiva>();
                int quantity=rs.getInt("quantity");
                	
                for(int i=0;i<quantity;i++) {
                	BagaglioStiva bagaglioStiva=new BagaglioStiva();
                		
                	bagaglioStiva.setBiglietto(biglietto);
                	bagaglioStiva.setDimensioni(rs.getString("dimensioni"));
                	bagaglioStiva.setPeso(rs.getInt("peso"));
                	bagaglioStiva.setPrezzo(rs.getFloat("prezzo"));
                	bagagliStiva.add(bagaglioStiva);
                }
            }
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {
            	DriverManagerConnectionPool.releaseConnection(connection);
			}
        }
       
        return bagagliStiva;
	}
}
