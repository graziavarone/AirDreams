package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DriverManagerConnectionPool;
import gestioneordine.Biglietto;
import gestioneordine.BigliettoManager;
import gestioneordine.Sesso;
import gestionevolo.Volo;
import gestionevolo.VoloManager;
import junit.framework.TestCase;

public class BigliettoManagerTest extends TestCase{
private BigliettoManager bigliettoManager=new BigliettoManager();
private VoloManager voloManager=new VoloManager();
	
	@Before
	public void setUp() throws Exception {	
		DbPopulator.initializeDatabase();
		//DriverManagerConnectionPool.setTest(true);
	}
	
	@Test
	public void testAggiungiBiglietto() throws Exception {
		Volo volo=voloManager.findByID("1");
		Biglietto biglietto=new Biglietto("Rosaria", "Rossi", Sesso.F, 26,volo);
		biglietto.setOrdine(1);
		 biglietto=bigliettoManager.aggiungiBiglietto(biglietto);
		 
		 assertTrue(biglietto.getCodBiglietto()!=0);
	}
	
	@Test
	public void testTrovaBigliettiOrdine() throws Exception {
		ArrayList<Biglietto> biglietti=bigliettoManager.trovaBigliettiOrdine(1);
		 
		 assertTrue(biglietti.size()>0);
	}
	
	
	
	
	
	
	@After
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
}
