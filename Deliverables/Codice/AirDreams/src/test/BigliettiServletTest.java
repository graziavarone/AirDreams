package test;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

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
import gestioneordine.BigliettiServlet;
import gestionevolo.Volo;
import gestionevolo.VoloManager;

public class BigliettiServletTest {
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

	private BigliettiServlet servlet;
	
	private Carrello carrello;
	
	private HashMap<Volo, Integer> voliCarrello=new HashMap<Volo,Integer> ();

	@Before
	public void setUp() throws Exception {
		int seats=1;
		MockitoAnnotations.initMocks(this);

		when(request.getServletContext()).thenReturn(context);
		
		when(request.getSession()).thenReturn(session);
        servlet = new BigliettiServlet();
        DbPopulator.initializeDatabase();
        
		
		CarrelloManager carrelloManager=new CarrelloManager();
		VoloManager voloManager=new VoloManager();
		ArrayList<Volo> voli=voloManager.cercaVoli("Ryanair");
		carrelloManager.svuotaCarrello("rosaria@gmail.com");
		carrelloManager.aggiungiVoloAlCarrello("rosaria@gmail.com", voli.get(0).getId(), 1);
		voliCarrello.put(voli.get(0), 1);
		carrello=carrelloManager.getCarrelloUtente("rosaria@gmail.com");
		carrello.setVoli(voliCarrello);
		
		when(request.getSession().getAttribute("carrello")).thenReturn(carrello);
		when(request.getServletContext().getRequestDispatcher("/cliente/DettagliAccountServlet")).thenReturn(dispatcherSuccess);
		when(request.getServletContext().getRequestDispatcher("/cliente/nominativiBiglietti.jsp?seats="+seats)).thenReturn(dispatcher);
	
	
	}
	
	//nome sbagliato
	@Test
	public void testCase_1() throws IOException, ServletException {	
		when(request.getParameter("nomePasseggero1")).thenReturn("Grazia3");
		when(request.getParameter("cognomePasseggero1")).thenReturn("Varone");
		when(request.getParameter("sesso1")).thenReturn("F");
		when(request.getParameter("bagaglioStiva1")).thenReturn("1");

		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Failed");
		
	}
	
	//cognome sbagliato
	@Test
	public void testCase_2() throws IOException, ServletException {	
		when(request.getParameter("nomePasseggero1")).thenReturn("Grazia");
		when(request.getParameter("cognomePasseggero1")).thenReturn("Varone3");
		when(request.getParameter("sesso1")).thenReturn("F");
		when(request.getParameter("bagaglioStiva1")).thenReturn("1");

		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Failed");
		
	}
	
	//success
	@Test
	public void testCase_3() throws IOException, ServletException {	
		when(request.getParameter("nomePasseggero1")).thenReturn("Grazia");
		when(request.getParameter("cognomePasseggero1")).thenReturn("Varone");
		when(request.getParameter("sesso1")).thenReturn("F");
		when(request.getParameter("bagaglioStiva1")).thenReturn("1");

		
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
