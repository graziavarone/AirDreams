package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import db.DriverManagerConnectionPool;
import gestionecarrello.CarrelloManager;
import gestionecompagniaaerea.CompagniaAerea;
import gestionecompagniaaerea.CompagniaAereaManager;
import gestionevolo.Volo;
import junit.framework.TestCase;

public class CompagniaAereaManagerTest extends TestCase {
	private CompagniaAereaManager compagniaAereaManager=new CompagniaAereaManager();
	
	@Before
	public void setUp() throws Exception {	
		DbPopulator.initializeDatabase();
	}
	
	@Test
	public void testVisualizzaInfoCompagniaAerea() throws Exception{
		System.out.println("visualizzaInfoCompagniaAerea");
		assertNotNull(compagniaAereaManager.visualizzaInfoCompagniaAerea("Ryanair"));

	} 
	
	@Test
	public void testAggiungiCompagnia() throws Exception{
		System.out.println("aggiungiCompagnia");
		boolean result=compagniaAereaManager.aggiungiCompagnia(new CompagniaAerea("EasyJet","www.easyjet.com"));
		
		assertTrue(result);
	} 
	
	@Test
	public void testGetCompagnie() throws Exception{
		System.out.println("getCompagnie");
		ArrayList<CompagniaAerea> compagnie=compagniaAereaManager.getAllCompanies();
		
		assertEquals(1, compagnie.size());
	} 
	
	@Test
	public void testGetAllCompagnies() throws Exception{
		System.out.println("getAllCompagnies");
		ArrayList<CompagniaAerea> compagnie=compagniaAereaManager.getAllCompanies();
		
		assertEquals(1, compagnie.size());
	} 
	
	@After
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
}
