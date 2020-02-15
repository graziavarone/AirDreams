package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DriverManagerConnectionPool;
import gestioneutente.CartaDiCredito;
import gestioneutente.CartaDiCreditoManager;
import gestioneutente.UtenteManager;

public class CartaDiCreditoManagerTest {
	private CartaDiCreditoManager cartaDiCreditoManager=new CartaDiCreditoManager();
	private UtenteManager utenteManager=new UtenteManager();
	
	@Before
	public void setUp() throws Exception {	
		//DbPopulator.initializeDatabase();
		DriverManagerConnectionPool.setTest(true);
	}
	
	@Test
	public void findAll() throws Exception {

		ArrayList<CartaDiCredito> carte=cartaDiCreditoManager.findAll("rosaria@gmail.com");
		
		assertEquals(1, carte.size());
	}
	
	@Test
	public void cercaCarta() throws Exception {

		CartaDiCredito carta=cartaDiCreditoManager.cercaCarta("1111 1111 1111 1111","rosaria@gmail.com");
		
		assertNotNull(carta);
	}
	
	@Test
	public void creaCarta() throws Exception{
		CartaDiCredito cartaDiCredito=new CartaDiCredito("2222 2222 2222 2222", "Pinco Pallino", "12/22", 123);
		cartaDiCredito.setAccount(utenteManager.findAccountByEmail("noemi@gmail.com"));
		boolean result=cartaDiCreditoManager.creaCartaDiCredito(cartaDiCredito);
		
		cartaDiCreditoManager.eliminaCarta(cartaDiCredito.getnCarta(), "noemi@gmail.com");
		
		assertTrue(result);
		
	}
	
	@Test
	public void eliminaCarta() throws Exception{
		CartaDiCredito cartaDiCredito=new CartaDiCredito("2222 2222 2222 2222", "Pinco Pallino", "12/22", 123);
		cartaDiCredito.setAccount(utenteManager.findAccountByEmail("noemi@gmail.com"));
		cartaDiCreditoManager.creaCartaDiCredito(cartaDiCredito);
		
		boolean result=cartaDiCreditoManager.eliminaCarta(cartaDiCredito.getnCarta(), "noemi@gmail.com");
		
		assertTrue(result);
		
	}
		
	@After
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
}
