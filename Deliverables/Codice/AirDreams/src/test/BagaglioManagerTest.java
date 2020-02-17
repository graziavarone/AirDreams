package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DriverManagerConnectionPool;
import gestionecompagniaaerea.CompagniaAerea;
import gestionecompagniaaerea.CompagniaAereaManager;
import gestionecompagniaaerea.PoliticaBagaglioManager;
import gestionecompagniaaerea.PoliticaBagaglioMano;
import gestionecompagniaaerea.PoliticaBagaglioStiva;
import gestioneordine.BagaglioManager;
import gestioneordine.BagaglioMano;
import gestioneordine.BagaglioStiva;
import gestioneordine.Biglietto;
import gestioneordine.BigliettoManager;
import junit.framework.TestCase;

public class BagaglioManagerTest extends TestCase {
	
	private BagaglioManager bagaglioManager=new BagaglioManager();
	private BigliettoManager bigliettoManager=new BigliettoManager();
	
	@Before
	public void setUp() throws Exception {	
		DbPopulator.initializeDatabase();
		//DriverManagerConnectionPool.setTest(true);
	}
	
	@Test
	public void testAggiungiBagaglioMano() throws Exception{
		Biglietto biglietto=bigliettoManager.trovaBigliettiOrdine(1).get(0);
		boolean result=bagaglioManager.aggiungiBagaglioMano(new BagaglioMano(10, "10x10x10", biglietto));
		
		assertTrue(result);

	} 
	
	@Test
	public void testAggiungiBagaglioStiva() throws Exception{
		Biglietto biglietto=bigliettoManager.trovaBigliettiOrdine(1).get(0);
		boolean result=bagaglioManager.aggiungiBagaglioStiva(new BagaglioStiva(10, "10x10x10", biglietto,33,1));
		
		assertTrue(result);

	} 
	
	@Test
	public void testCercaBagaglioManoBiglietto() throws Exception{
		Biglietto biglietto=bigliettoManager.trovaBigliettiOrdine(1).get(0);
		BagaglioMano bagaglioMano=bagaglioManager.cercaBagaglioManoBiglietto(biglietto);
		
		assertNotNull(bagaglioMano);

	} 
	
	
	@Test
	public void testCercaBagagliStivaBiglietto() throws Exception{
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
