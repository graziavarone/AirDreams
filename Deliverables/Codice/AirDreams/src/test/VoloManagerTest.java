package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DriverManagerConnectionPool;
import gestionecompagniaaerea.CompagniaAereaManager;
import gestionevolo.AeroportoManager;
import gestionevolo.Volo;
import gestionevolo.VoloManager;
import junit.framework.TestCase;

public class VoloManagerTest extends TestCase {
	private VoloManager voloManager=new VoloManager();
	
	@Before
	public void setUp() throws Exception {	
		DbPopulator.initializeDatabase();
		//DriverManagerConnectionPool.setTest(true);
	}
	
	@Test 
	public void testCercaDiretti() {
		ArrayList<Volo> voli=voloManager.cercaDiretti("NAP", "TXL", LocalDate.of(2020, 2, 10), 1, 0, 0);
		
		assertTrue(voli.size()>0);
	}
	
	
	@Test 
	public void testCercaUnoScalo() {
		ArrayList<Volo[]> voli=voloManager.cercaUnoScalo("NAP", "TXL", LocalDate.of(2020, 2, 10), 1, 0, 0);
		
		assertTrue(voli.size()>0);
	}
	
	@Test 
	public void testCercaDueScali() {
		ArrayList<Volo[]> voli=voloManager.cercaDueScali("TXL", "NAP", LocalDate.of(2020, 2, 12), 1, 0, 0);
		
		assertTrue(voli.size()>0);
	}
	
	@Test 
	public void testAggiungiVolo() throws SQLException {
		CompagniaAereaManager compagniaAereaManager=new CompagniaAereaManager();
		AeroportoManager aeroportoManager=new AeroportoManager();
		Volo volo=new Volo(LocalDate.of(2020, 2, 12), 1, 1, LocalTime.of(1, 1), LocalTime.of(1, 1), true,
				compagniaAereaManager.visualizzaInfoCompagniaAerea("Ryanair"), aeroportoManager.findAeroportoById("NAP"), aeroportoManager.findAeroportoById("TXL"));
		boolean result=voloManager.aggiungiVolo(volo);
		
		assertTrue(result);
	}
	
	@Test 
	public void testCercaVoli() throws SQLException {
		
		ArrayList<Volo> voli=voloManager.cercaVoli("Ryanair");
		
		assertTrue(voli.size()>0);
	}
	
	
	@Test 
	public void testCercaVoliRicerca() throws SQLException {
		String[] ricerca=new String[3];
		ricerca[0]="NAP";
		ricerca[1]="";
		ricerca[2]="";
		ArrayList<Volo> voli=voloManager.cercaVoli(ricerca,"Ryanair");
		
		assertTrue(voli.size()>0);
	}
	
	
	@Test 
	public void testFindById() throws SQLException {
		
		Volo volo=voloManager.findByID("1");
		
		assertNotNull(volo);
	}
	
	
	@Test 
	public void testModificaVolo() throws SQLException {
		Volo volo= voloManager.findByID("1");
		volo.setSeats(54);
		boolean result=voloManager.modificaVolo(volo);
		
		assertTrue(result);
	}
	
	
	
	@Test 
	public void testEliminaVolo() throws SQLException {
		
		
		boolean result=voloManager.eliminaVolo(1);
		
		assertTrue(result);
	}
	
	
	@After
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
}
