package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DriverManagerConnectionPool;
import gestioneordine.Biglietto;
import gestioneordine.Ordine;
import gestioneordine.OrdineManager;
import gestioneordine.Sesso;
import gestioneutente.CartaDiCreditoManager;
import gestioneutente.UtenteManager;
import gestionevolo.Volo;
import junit.framework.TestCase;

public class OrdineManagerTest extends TestCase {
	private OrdineManager ordineManager=new OrdineManager();
	private UtenteManager utenteManager=new UtenteManager();
	private CartaDiCreditoManager cartaDiCreditoManager=new CartaDiCreditoManager();
	
	@Before
	public void setUp() throws Exception {	
		DbPopulator.initializeDatabase();
		//DriverManagerConnectionPool.setTest(true);
	}
	
	@Test
	public void testAggiungiOrdine() throws Exception {
		Ordine ordine=ordineManager.aggiungiOrdine(new Ordine(LocalDate.now(), utenteManager.findAccountByEmail("rosaria@gmail.com"), cartaDiCreditoManager.cercaCarta("1111 1111 1111 1111", "rosaria@gmail.com")));
		
		assertTrue(ordine.getCodOrdine()!=0);
	}
	
	@Test
	public void testAnnullaOrdine() throws Exception {
		boolean result=ordineManager.annullaOrdine(1);
		assertTrue(result);
	}
	
	
	
	@After
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}

}
