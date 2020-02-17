package test;

import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertTrue;

import java.awt.SecondaryLoop;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.After;
	import org.junit.Before;
	import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DataValidationException;

import db.DriverManagerConnectionPool;
import gestionecarrello.Carrello;
import gestionecarrello.CarrelloManager;
import gestionecompagniaaerea.CompagniaAerea;
import gestionecompagniaaerea.CompagniaAereaManager;
import gestioneutente.Account;
import gestioneutente.UtenteManager;
import gestionevolo.Aeroporto;
import gestionevolo.AeroportoManager;
import gestionevolo.Volo;
import gestionevolo.VoloManager;
import junit.framework.TestCase;


	public class CarrelloManagerTest extends TestCase  {
		private CarrelloManager carrelloManager=new CarrelloManager();

		
		@Before
		public void setUp() throws Exception {	
			//DriverManagerConnectionPool.setTest(true);		
			DbPopulator.initializeDatabase();
		
		}
		
		@Test
		public void testGetCarrelloUtente() throws Exception{
			System.out.println("getCarrelloUtente");
			assertNotNull(carrelloManager.getCarrelloUtente("rosaria@gmail.com"));

		} 
		
		@Test
		public void testAggiungiVoloAlCarrello() throws Exception{
			System.out.println("aggiungiVoloAlCarrello");
			boolean result=carrelloManager.aggiungiVoloAlCarrello("rosaria@gmail.com",2, 1);
			
			//carrelloManager.rimuoviVoloDalCarrello(""+2, "rosaria@gmail.com");
			assertTrue(result);
		}
		
		@Test
		public void testUpdateQuantity() throws Exception {
			System.out.println("updateQuantity");
			//carrelloManager.aggiungiVoloAlCarrello("rosaria@gmail.com",2, 1);
			
			boolean result=carrelloManager.updateQuantity("rosaria@gmail.com", 1, 1);
			//carrelloManager.rimuoviVoloDalCarrello(""+2, "rosaria@gmail.com");
			assertTrue(result);
		}
		
		
		@Test
		public void testCercaVoloNelCarrello() throws Exception{
			//carrelloManager.aggiungiVoloAlCarrello("rosaria@gmail.com",2, 1);
			Volo volo=carrelloManager.cercaVoloNelCarrello("rosaria@gmail.com", 1);
			
			//carrelloManager.rimuoviVoloDalCarrello(""+2, "rosaria@gmail.com");
			assertNotNull(volo);
		}
		
		@Test
		public void testRimuoviVoloDalCarrello() throws Exception{
			System.out.println("rimuoviVoloDalCarrello");
			//carrelloManager.aggiungiVoloAlCarrello("rosaria@gmail.com",2, 1);
			boolean result=carrelloManager.rimuoviVoloDalCarrello(""+1, "rosaria@gmail.com");
			assertTrue(result);
		}
		
		@Test
		public void testSvuotaCarrello() throws Exception{
			System.out.println("svuotaCarrello");
			boolean result=carrelloManager.svuotaCarrello("rosaria@gmail.com");
			assertTrue(result);
		}
		
		
		@AfterEach
		public void tearDown() throws Exception{
			DriverManagerConnectionPool.setTest(false);
		}
	}


