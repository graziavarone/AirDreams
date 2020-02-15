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
import gestioneutente.AggiungiCartaServlet;
import gestioneutente.UtenteManager;

public class AggiungiCartaTest {
	
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
	private AggiungiCartaServlet servlet;
	private Account account;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(request.getServletContext()).thenReturn(context); 		
		when(request.getServletContext().getRequestDispatcher("/cliente/aggiungiCarta.jsp")).thenReturn(dispatcher);
		when(request.getServletContext().getRequestDispatcher("/cliente/DettagliAccountServlet")).thenReturn(dispatcherSuccess);
		when(request.getSession()).thenReturn(session);
        servlet = new AggiungiCartaServlet();
        DbPopulator.initializeDatabase();
        utente = new UtenteManager();
        account = utente.findAccountByEmail("rosaria@gmail.com");
	}
	
	//TC_2.1_1 formato numero carta errato
	@Test
	public void testCase_1() throws IOException, ServletException {		
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
		when(request.getParameter("nCarta")).thenReturn("3456 6009 7566 8008");
		when(request.getParameter("titolare")).thenReturn("Rosaria78");
		when(request.getParameter("dataScadenza")).thenReturn("02/22");
		when(request.getParameter("cvc")).thenReturn("676");
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}
	
	//TC_2.1_3 formato scadenza errato
	@Test
	public void testCase_3() throws IOException, ServletException {		
		when(request.getParameter("nCarta")).thenReturn("3456 6009 7566 8008");
		when(request.getParameter("titolare")).thenReturn("Rosaria Rossi");
		when(request.getParameter("dataScadenza")).thenReturn("02-2022");
		when(request.getParameter("cvc")).thenReturn("676");
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}
	
	//TC_2.1_4 carta scaduta
	@Test
	public void testCase_4() throws IOException, ServletException {		
		when(request.getParameter("nCarta")).thenReturn("3456 6009 7566 8008");
		when(request.getParameter("titolare")).thenReturn("Rosaria Rossi");
		when(request.getParameter("dataScadenza")).thenReturn("11/18");
		when(request.getParameter("cvc")).thenReturn("676");
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Carta scaduta");
	}
	
	//TC_2.1_5 formato cvc errato
	@Test
	public void testCase_5() throws IOException, ServletException {		
		when(request.getParameter("nCarta")).thenReturn("3456 6009 7566 8008");
		when(request.getParameter("titolare")).thenReturn("Rosaria Rossi");
		when(request.getParameter("dataScadenza")).thenReturn("02/22");
		when(request.getParameter("cvc")).thenReturn("1");
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}
	
	//TC_2.1_6 success
	@Test
	public void testCase_6() throws IOException, ServletException {		
		when(request.getParameter("nCarta")).thenReturn("3456 6009 7566 8008");
		when(request.getParameter("titolare")).thenReturn("Rosaria Rossi");
		when(request.getParameter("dataScadenza")).thenReturn("02/22");
		when(request.getParameter("cvc")).thenReturn("676");
		when(request.getSession().getAttribute("account")).thenReturn(account);
		
		
		
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
