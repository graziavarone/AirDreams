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
import org.springframework.mock.web.MockRequestDispatcher;

import db.DriverManagerConnectionPool;
import gestioneutente.AggiungiCartaServlet;
import gestioneutente.RegistrazioneServlet;

public class AggiungiCartaTest {
	@Mock
	MockHttpServletRequest request;

	@Mock
	MockHttpServletResponse response;
	
	@Mock
	MockRequestDispatcher dispatcher;
	
	@Mock
	MockRequestDispatcher dispatcherSuccess;

	
	private AggiungiCartaServlet servlet;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(request.getRequestDispatcher("aggiungiCarta.jsp")).thenReturn(dispatcher);
		when(request.getRequestDispatcher("aggiungiCarta.jsp")).thenReturn(dispatcherSuccess);
        servlet = new AggiungiCartaServlet();
        DbPopulator.initializeDatabase();
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
		when(request.getParameter("cvc")).thenReturn("7890RR");
		
		
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
