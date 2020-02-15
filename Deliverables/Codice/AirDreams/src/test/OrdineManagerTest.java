package test;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DriverManagerConnectionPool;
import gestioneordine.Ordine;
import gestioneordine.OrdineManager;
import gestioneutente.CartaDiCreditoManager;
import gestioneutente.UtenteManager;

public class OrdineManagerTest {
	private OrdineManager ordineManager=new OrdineManager();
	private UtenteManager utenteManager=new UtenteManager();
	private CartaDiCreditoManager cartaDiCreditoManager=new CartaDiCreditoManager();
	
	@Before
	public void setUp() throws Exception {	
		//DbPopulator.initializeDatabase();
		DriverManagerConnectionPool.setTest(true);
	}
	
	@Test
	public void aggiungiOrdine() throws Exception {
		Ordine ordine=ordineManager.aggiungiOrdine(new Ordine(LocalDate.now(), utenteManager.findAccountByEmail("rosaria@gmail.com"), cartaDiCreditoManager.cercaCarta("1111 1111 1111 1111", "rosaria@gmail.com")));
		
		assertTrue(ordine.getCodOrdine()!=0);
	}
	
	@Test
	public void annullaOrdine() throws Exception {
		boolean result=ordineManager.annullaOrdine(1);
		assertTrue(result);
	}
	
	@After
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
}
