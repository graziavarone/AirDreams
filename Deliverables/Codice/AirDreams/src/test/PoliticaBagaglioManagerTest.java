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
import gestionecompagniaaerea.PoliticaBagaglioManager;
import gestionecompagniaaerea.PoliticaBagaglioMano;
import gestionecompagniaaerea.PoliticaBagaglioStiva;
import gestionevolo.Volo;
import junit.framework.TestCase;

	public class PoliticaBagaglioManagerTest extends TestCase {
		private PoliticaBagaglioManager politicaBagaglioManager=new PoliticaBagaglioManager();
		private CompagniaAereaManager compagniaAereaManager=new CompagniaAereaManager();
		
		@Before
		public void setUp() throws Exception {	
			//DriverManagerConnectionPool.setTest(true);
			DbPopulator.initializeDatabase();
		}
		
		@Test
		public void testAggiungiPoliticaBagaglioStiva() throws Exception{
		//	CompagniaAerea compagniaAerea=new CompagniaAerea("EasyJet","www.easyjet.com");
			//compagniaAereaManager.aggiungiCompagnia(compagniaAerea);
			boolean result=politicaBagaglioManager.aggiungiPoliticaBagaglioStiva(new PoliticaBagaglioStiva(20, "20x20x20", 30, compagniaAereaManager.visualizzaInfoCompagniaAerea("Ryanair")));
			
			//compagniaAereaManager.eliminaCompagnia("EasyJet");
			
			assertTrue(result);

		} 
		
		@Test
		public void testAggiungiPoliticaBagaglioMano() throws Exception{
		//	CompagniaAerea compagniaAerea=new CompagniaAerea("EasyJet","www.easyjet.com");
		//	compagniaAereaManager.aggiungiCompagnia(compagniaAerea);
			boolean result=politicaBagaglioManager.aggiungiPoliticaBagaglioMano(new PoliticaBagaglioMano(20, "20x20x20", 
					compagniaAereaManager.visualizzaInfoCompagniaAerea("Ryanair")));
			
		//	compagniaAereaManager.eliminaCompagnia("EasyJet");
			
			assertTrue(result);

		} 
		
		@Test
		public void testTrovaPoliticaCompagniaStiva() throws Exception{
			assertNotNull(politicaBagaglioManager.trovaPoliticaCompagniaStiva("Ryanair"));
		}
		
		@Test
		public void testTrovaPoliticaCompagniaMano() throws Exception{
			assertNotNull(politicaBagaglioManager.trovaPoliticaCompagniaMano("Ryanair"));
		}
		
		@Test
		public void testAggiornaPoliticaBagaglioStiva() throws Exception{
			//CompagniaAerea compagniaAerea=new CompagniaAerea("EasyJet","www.easyjet.com");
			//compagniaAereaManager.aggiungiCompagnia(compagniaAerea);
			boolean result=politicaBagaglioManager.aggiornaPoliticaBagaglioStiva(new PoliticaBagaglioStiva(20, "20x20x20", 30,
					compagniaAereaManager.visualizzaInfoCompagniaAerea("Ryanair")));
			
		//	compagniaAereaManager.eliminaCompagnia("EasyJet");
			
			assertTrue(result);
		}
		
		@Test
		public void testAggiornaPoliticaBagaglioMano() throws Exception{
		//	CompagniaAerea compagniaAerea=new CompagniaAerea("EasyJet","www.easyjet.com");
		//	compagniaAereaManager.aggiungiCompagnia(compagniaAerea);
			boolean result=politicaBagaglioManager.aggiornaPoliticaBagaglioMano(new PoliticaBagaglioMano(20, "20x20x20",
					compagniaAereaManager.visualizzaInfoCompagniaAerea("Ryanair")));
			
		//	compagniaAereaManager.eliminaCompagnia("EasyJet");
			
			assertTrue(result);
		}

		
		
		@After
		public void tearDown() throws Exception{
			DriverManagerConnectionPool.setTest(false);
		}
	}


