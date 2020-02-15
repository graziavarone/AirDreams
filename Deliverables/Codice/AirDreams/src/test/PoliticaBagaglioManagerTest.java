package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DriverManagerConnectionPool;
import gestionecompagniaaerea.CompagniaAerea;
import gestionecompagniaaerea.CompagniaAereaManager;
import gestionecompagniaaerea.PoliticaBagaglioManager;
import gestionecompagniaaerea.PoliticaBagaglioMano;
import gestionecompagniaaerea.PoliticaBagaglioStiva;


public class PoliticaBagaglioManagerTest {
	private PoliticaBagaglioManager politicaBagaglioManager=new PoliticaBagaglioManager();
	private CompagniaAereaManager compagniaAereaManager=new CompagniaAereaManager();
	
	@Before
	public void setUp() throws Exception {	
		DriverManagerConnectionPool.setTest(true);
	}
	
	@Test
	public void aggiungiPoliticaBagaglioStiva() throws Exception{
		CompagniaAerea compagniaAerea=new CompagniaAerea("EasyJet","www.easyjet.com");
		compagniaAereaManager.aggiungiCompagnia(compagniaAerea);
		boolean result=politicaBagaglioManager.aggiungiPoliticaBagaglioStiva(new PoliticaBagaglioStiva(20, "20x20x20", 30, compagniaAerea));
			
		compagniaAereaManager.eliminaCompagnia("EasyJet");
			
		assertTrue(result);
	} 
		
	@Test
	public void aggiungiPoliticaBagaglioMano() throws Exception{
		CompagniaAerea compagniaAerea=new CompagniaAerea("EasyJet","www.easyjet.com");
		compagniaAereaManager.aggiungiCompagnia(compagniaAerea);
		boolean result=politicaBagaglioManager.aggiungiPoliticaBagaglioMano(new PoliticaBagaglioMano(20, "20x20x20", compagniaAerea));
			
		compagniaAereaManager.eliminaCompagnia("EasyJet");
			
		assertTrue(result);
	} 
		
	@Test
	public void trovaPoliticaCompagniaStiva() throws Exception{
		assertNotNull(politicaBagaglioManager.trovaPoliticaCompagniaStiva("Ryanair"));
	}
		
	@Test
	public void trovaPoliticaCompagniaMano() throws Exception{
		assertNotNull(politicaBagaglioManager.trovaPoliticaCompagniaMano("Ryanair"));
	}
		
	@Test
	public void aggiornaPoliticaBagaglioStiva() throws Exception{
		CompagniaAerea compagniaAerea=new CompagniaAerea("EasyJet","www.easyjet.com");
		compagniaAereaManager.aggiungiCompagnia(compagniaAerea);
		boolean result=politicaBagaglioManager.aggiornaPoliticaBagaglioStiva(new PoliticaBagaglioStiva(20, "20x20x20", 30,compagniaAerea));
			
		compagniaAereaManager.eliminaCompagnia("EasyJet");
			
		assertTrue(result);
	}
		
	@Test
	public void aggiornaPoliticaBagaglioMano() throws Exception{
		CompagniaAerea compagniaAerea=new CompagniaAerea("EasyJet","www.easyjet.com");
		compagniaAereaManager.aggiungiCompagnia(compagniaAerea);
		boolean result=politicaBagaglioManager.aggiornaPoliticaBagaglioMano(new PoliticaBagaglioMano(20, "20x20x20", compagniaAerea));
			
		compagniaAereaManager.eliminaCompagnia("EasyJet");
			
		assertTrue(result);
	}

	@After
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
}