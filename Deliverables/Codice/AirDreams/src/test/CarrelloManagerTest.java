package test;

import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DriverManagerConnectionPool;
import gestionecarrello.CarrelloManager;

import gestionevolo.Volo;

public class CarrelloManagerTest  {
	private CarrelloManager carrelloManager=new CarrelloManager();
		
	@Before
	public void setUp() throws Exception {	
		DriverManagerConnectionPool.setTest(true);			
	}
		
	@Test
	public void getCarrelloUtente() throws Exception{
		System.out.println("getCarrelloUtente");
		assertNotNull(carrelloManager.getCarrelloUtente("rosaria@gmail.com"));
	} 
		
	@Test
	public void aggiungiVoloAlCarrello() throws Exception{
		System.out.println("aggiungiVoloAlCarrello");
		boolean result=carrelloManager.aggiungiVoloAlCarrello("rosaria@gmail.com",2, 1);
			
		carrelloManager.rimuoviVoloDalCarrello(""+2, "rosaria@gmail.com");
		assertTrue(result);
	}
		
	@Test
	public void updateQuantity() throws Exception {
		System.out.println("updateQuantity");
		carrelloManager.aggiungiVoloAlCarrello("rosaria@gmail.com",2, 1);
			
		boolean result=carrelloManager.updateQuantity("rosaria@gmail.com", 1, 1);
		carrelloManager.rimuoviVoloDalCarrello(""+2, "rosaria@gmail.com");
		assertTrue(result);
	}
		
	@Test
	public void cercaVoloNelCarrello() throws Exception{
		carrelloManager.aggiungiVoloAlCarrello("rosaria@gmail.com",2, 1);
		Volo volo=carrelloManager.cercaVoloNelCarrello("rosaria@gmail.com", 2);
			
		carrelloManager.rimuoviVoloDalCarrello(""+2, "rosaria@gmail.com");
		assertNotNull(volo);
	}
		
	@Test
	public void rimuoviVoloDalCarrello() throws Exception{
		System.out.println("rimuoviVoloDalCarrello");
		carrelloManager.aggiungiVoloAlCarrello("rosaria@gmail.com",2, 1);
		boolean result=carrelloManager.rimuoviVoloDalCarrello(""+2, "rosaria@gmail.com");
		assertTrue(result);
	}
		
	@Test
	public void svuotaCarrello() throws Exception{
		System.out.println("svuotaCarrello");
		boolean result=carrelloManager.svuotaCarrello("rosaria@gmail.com");
		assertTrue(result);
	}
		
	@After
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
}