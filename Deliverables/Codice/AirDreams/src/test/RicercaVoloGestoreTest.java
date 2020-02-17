package test;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

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
import gestioneutente.Account;
import gestioneutente.UtenteManager;
import gestionevolo.ListaVoliServlet;

public class RicercaVoloGestoreTest {

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
	
	private ListaVoliServlet servlet;
	private UtenteManager utente;
	private Account account;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(request.getServletContext()).thenReturn(context); 	
		when(request.getRequestDispatcher("/gestoreVoli/listaVoli.jsp")).thenReturn(dispatcher);
		when(request.getSession()).thenReturn(session);
        servlet = new  ListaVoliServlet();
        DbPopulator.initializeDatabase();
        
        utente = new UtenteManager();
        account = utente.findAccountByEmail("gestoreVoli@gmail.com");
        
	}
	
	//TC_4.3_1 aeroporto di partenza inesistente
	@Test
	public void testCase_1() throws IOException, ServletException {		
		when(request.getParameter("action")).thenReturn("ricerca");
		when(request.getParameter("page")).thenReturn("1");
		when(request.getSession().getAttribute("account")).thenReturn(account);
		when(request.getParameter("city")).thenReturn("AAAA");
		when(request.getParameter("cityArrivals")).thenReturn("ANW - Ainsworth, UNITED STATES");
		when(request.getParameter("data")).thenReturn("12/02/2020");
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Failed");
	}
	
	//TC_4.3_2 aeroporto di destinazione inesistente
	@Test
	public void testCase_2() throws IOException, ServletException {		
		when(request.getParameter("action")).thenReturn("ricerca");
		when(request.getParameter("page")).thenReturn("1");
		when(request.getSession().getAttribute("account")).thenReturn(account);
		when(request.getParameter("city")).thenReturn("NAP - Naples, ITALY");
		when(request.getParameter("cityArrivals")).thenReturn("AAAAA");
		when(request.getParameter("data")).thenReturn("12/02/2020");

		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Failed");
	}
	
	//TC_4.3_3 data del volo non valida
	@Test
	public void testCase_3() throws IOException, ServletException {		
		when(request.getParameter("action")).thenReturn("ricerca");
		when(request.getParameter("page")).thenReturn("1");
		when(request.getSession().getAttribute("account")).thenReturn(account);
		when(request.getParameter("city")).thenReturn("NAP - Naples, ITALY");
		when(request.getParameter("cityArrivals")).thenReturn("ANW - Ainsworth, UNITED STATES");
		when(request.getParameter("data")).thenReturn("12/022020");
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Failed");
	}
	
	//TC_4.3_4 success
	@Test
	public void testCase_4() throws IOException, ServletException {		
		when(request.getParameter("action")).thenReturn("ricerca");
		when(request.getParameter("page")).thenReturn("1");
		when(request.getSession().getAttribute("account")).thenReturn(account);
		when(request.getParameter("city")).thenReturn("NAP - Naples, ITALY");
		when(request.getParameter("cityArrivals")).thenReturn("ANW - Ainsworth, UNITED STATES");
		when(request.getParameter("data")).thenReturn("12/02/2020");
		
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
