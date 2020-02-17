package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import db.DriverManagerConnectionPool;
import gestioneordine.Ordine;
import gestioneutente.CartaDiCredito;
import gestioneutente.CartaDiCreditoManager;
import gestioneutente.UtenteManager;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CartaDiCreditoManagerTest extends TestCase {

	private CartaDiCreditoManager cartaDiCreditoManager=new CartaDiCreditoManager();
	private UtenteManager utenteManager=new UtenteManager();
	
	@Before
	public void setUp() throws Exception {	
		DbPopulator.initializeDatabase();
		//DriverManagerConnectionPool.setTest(true);
	}
	

	
	@Test
	public void testFindAll() throws Exception {
		//DbPopulator.initializeDatabase();
		System.out.println("findAll");
		ArrayList<CartaDiCredito> carte=cartaDiCreditoManager.findAll("rosaria@gmail.com");
		//DriverManagerConnectionPool.setTest(false);
		assertEquals(1, carte.size());
	}
	
	@Test
	public void testCercaCarta() throws Exception {

		CartaDiCredito carta=cartaDiCreditoManager.cercaCarta("1111 1111 1111 1111","rosaria@gmail.com");
		
		assertNotNull(carta);
	}
	
	@Test
	public void testCreaCarta() throws Exception{
		CartaDiCredito cartaDiCredito=new CartaDiCredito("2222 2222 2222 2222", "Pinco Pallino", "12/22", 123);
		cartaDiCredito.setAccount(utenteManager.findAccountByEmail("noemi@gmail.com"));
		boolean result=cartaDiCreditoManager.creaCartaDiCredito(cartaDiCredito);
		
		//cartaDiCreditoManager.eliminaCarta(cartaDiCredito.getnCarta(), "noemi@gmail.com");
		
		assertTrue(result);
		
	}
	
	@Test
	public void testEliminaCarta() throws Exception{
//		CartaDiCredito cartaDiCredito=new CartaDiCredito("2222 2222 2222 2222", "Pinco Pallino", "12/22", 123);
	//	cartaDiCredito.setAccount(utenteManager.findAccountByEmail("noemi@gmail.com"));
		//cartaDiCreditoManager.creaCartaDiCredito(cartaDiCredito);
		
		boolean result=cartaDiCreditoManager.eliminaCarta("1111 1111 1111 1111", "rosaria@gmail.com");
		
		assertTrue(result);
		
	} 
	
	
	@After
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
	 


}
