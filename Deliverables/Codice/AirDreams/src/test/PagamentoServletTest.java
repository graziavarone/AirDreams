package test;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockRequestDispatcher;
import org.springframework.mock.web.MockServletContext;

import db.DriverManagerConnectionPool;
import gestionecarrello.Carrello;
import gestionecarrello.CarrelloManager;
import gestioneordine.BagaglioMano;
import gestioneordine.BagaglioStiva;
import gestioneordine.Biglietto;
import gestioneordine.PagamentoServlet;
import gestioneordine.Sesso;
import gestioneutente.Account;

import gestioneutente.UtenteManager;
import gestionevolo.Volo;
import gestionevolo.VoloManager;

public class PagamentoServletTest {
	@Mock
	MockHttpServletRequest request;

	@Mock
	MockHttpServletResponse response;
	
	@Mock
	MockRequestDispatcher dispatcher;
	
	@Mock
	MockRequestDispatcher dispatcherSuccess;
	
	@Mock 	
	MockHttpSession session;
	
	@Mock 	
	MockServletContext context;

	private UtenteManager utente;
	private PagamentoServlet servlet;
	private Account account;
	private ArrayList<Biglietto> biglietti=new ArrayList<Biglietto>();
	private HashMap<Volo, Integer> voliCarrello=new HashMap<Volo,Integer> ();
	private Carrello carrello;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(request.getServletContext()).thenReturn(context); 		
		when(request.getServletContext().getRequestDispatcher("/cliente/pagamento.jsp")).thenReturn(dispatcher);
		when(request.getServletContext().getRequestDispatcher("/cliente/acquisto.jsp")).thenReturn(dispatcherSuccess);
		when(request.getSession()).thenReturn(session);
        servlet = new PagamentoServlet();
        DbPopulator.initializeDatabase();
        utente = new UtenteManager();
        account = utente.findAccountByEmail("rosaria@gmail.com");
		when(request.getSession().getAttribute("account")).thenReturn(account);
		
	
	}
	
	//TC_2.1_1 formato numero carta errato
	@Test
	public void testCase_1() throws IOException, ServletException {		
		when(request.getParameter("carta")).thenReturn(null);
		when(request.getParameter("nCarta")).thenReturn("123R SS56 3456 0000");
		when(request.getParameter("titolare")).thenReturn("Rosaria Rossi");
		when(request.getParameter("dataScadenza")).thenReturn("02/22");
		when(request.getParameter("cvc")).thenReturn("676");
		

		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}
	
	//TC_2.1_2 formato titolare errato
	@Test
	public void testCase_2() throws IOException, ServletException {		
		when(request.getParameter("carta")).thenReturn(null);
		when(request.getParameter("nCarta")).thenReturn("3456 6009 7566 8008");
		when(request.getParameter("titolare")).thenReturn("Rosaria78");
		when(request.getParameter("dataScadenza")).thenReturn("02/22");
		when(request.getParameter("cvc")).thenReturn("676");
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}
	
	//formato scadenza errato
	@Test
	public void testCase_3() throws IOException, ServletException {
		when(request.getParameter("carta")).thenReturn(null);
		when(request.getParameter("nCarta")).thenReturn("3456 6009 7566 8008");
		when(request.getParameter("titolare")).thenReturn("Rosaria Rossi");
		when(request.getParameter("dataScadenza")).thenReturn("02-2022");
		when(request.getParameter("cvc")).thenReturn("676");
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}
	
	// carta scaduta
	@Test
	public void testCase_4() throws IOException, ServletException {	
		when(request.getParameter("carta")).thenReturn(null);
		when(request.getParameter("nCarta")).thenReturn("3456 6009 7566 8008");
		when(request.getParameter("titolare")).thenReturn("Rosaria Rossi");
		when(request.getParameter("dataScadenza")).thenReturn("11/18");
		when(request.getParameter("cvc")).thenReturn("676");
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Carta scaduta");
	}
	
	// formato cvc errato
	@Test
	public void testCase_5() throws IOException, ServletException {	
		when(request.getParameter("carta")).thenReturn(null);
		when(request.getParameter("nCarta")).thenReturn("3456 6009 7566 8008");
		when(request.getParameter("titolare")).thenReturn("Rosaria Rossi");
		when(request.getParameter("dataScadenza")).thenReturn("02/22");
		when(request.getParameter("cvc")).thenReturn("1");
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	} 
	
	// success
	@Test
		public void testCase_6() throws IOException, ServletException, SQLException {	
		when(request.getParameter("carta")).thenReturn(null);
		when(request.getParameter("nCarta")).thenReturn("3456 6009 7566 8008");
		when(request.getParameter("titolare")).thenReturn("Rosaria Rossi");
		when(request.getParameter("dataScadenza")).thenReturn("02/22");
		when(request.getParameter("cvc")).thenReturn("676");
		
			CarrelloManager carrelloManager=new CarrelloManager();
	    	VoloManager voloManager=new VoloManager();
			ArrayList<Volo> voli=voloManager.cercaVoli("Ryanair");
			carrelloManager.svuotaCarrello("rosaria@gmail.com");
			carrelloManager.aggiungiVoloAlCarrello("rosaria@gmail.com", voli.get(0).getId(), 1);
			voliCarrello.put(voli.get(0), 1);
			carrello=carrelloManager.getCarrelloUtente("rosaria@gmail.com");
			carrello.setVoli(voliCarrello);
			
			Biglietto biglietto=new Biglietto("Rosaria", "Rossi", Sesso.F, 50, voli.get(0));
			biglietto.setBagaglioMano(new BagaglioMano(10, "22x22x22", biglietto));
			HashSet<BagaglioStiva> bagaglioStivas=new HashSet<BagaglioStiva>();
			bagaglioStivas.add(new BagaglioStiva(20, "33x33x33", biglietto,25,1));
			biglietto.setBagagliStiva(bagaglioStivas);
			biglietti.add(biglietto);
			
			when(request.getSession().getAttribute("biglietti")).thenReturn(biglietti);  
		
		
			PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
			when(response.getWriter()).thenReturn(MyWriter);
			servlet.doPost(request, response);	
			Mockito.verify(MyWriter).write("Success");
		}
	
	@After
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}

}
