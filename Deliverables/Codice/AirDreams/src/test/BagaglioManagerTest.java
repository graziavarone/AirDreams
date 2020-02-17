package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DriverManagerConnectionPool;
import gestioneordine.BagaglioManager;
import gestioneordine.BagaglioMano;
import gestioneordine.BagaglioStiva;
import gestioneordine.Biglietto;
import gestioneordine.BigliettoManager;

public class BagaglioManagerTest {
	
	private BagaglioManager bagaglioManager=new BagaglioManager();
	private BigliettoManager bigliettoManager=new BigliettoManager();
	
	@Before
	public void setUp() throws Exception {	
		DbPopulator.initializeDatabase();
		//DriverManagerConnectionPool.setTest(true);
	}
	
	@Test
	public void aggiungiBagaglioMano() throws Exception{
		Biglietto biglietto=bigliettoManager.trovaBigliettiOrdine(1).get(0);
		boolean result=bagaglioManager.aggiungiBagaglioMano(new BagaglioMano(10, "10x10x10", biglietto));
		
		assertTrue(result);

	} 
	
	@Test
	public void aggiungiBagaglioStiva() throws Exception{
		Biglietto biglietto=bigliettoManager.trovaBigliettiOrdine(1).get(0);
		boolean result=bagaglioManager.aggiungiBagaglioStiva(new BagaglioStiva(10, "10x10x10", biglietto,33,1));
		
		assertTrue(result);

	} 
	
	@Test
	public void cercaBagaglioManoBiglietto() throws Exception{
		Biglietto biglietto=bigliettoManager.trovaBigliettiOrdine(1).get(0);
		BagaglioMano bagaglioMano=bagaglioManager.cercaBagaglioManoBiglietto(biglietto);
		
		assertNotNull(bagaglioMano);

	} 
	
	
	@Test
	public void cercaBagagliStivaBiglietto() throws Exception{
		Biglietto biglietto=bigliettoManager.trovaBigliettiOrdine(1).get(0);
		bagaglioManager.aggiungiBagaglioStiva(new BagaglioStiva(10, "10x10x10", biglietto,33,1));
		HashSet<BagaglioStiva> bagagli=bagaglioManager.cercaBagagliStivaBiglietto(biglietto);
		
		assertTrue(bagagli.size()>0);

	} 
	
	
	@After
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}

}
