package test;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockRequestDispatcher;

import gestionevolo.RicercaVoliServlet;

public class RicercaVoliTest {
	@Mock
	MockHttpServletRequest request;

	@Mock
	MockHttpServletResponse response;
	
	@Mock
	MockRequestDispatcher dispatcher;
	
	@Mock
	MockRequestDispatcher dispatcherSuccess;

	private RicercaVoliServlet servlet;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(request.getRequestDispatcher("index.jsp")).thenReturn(dispatcher);
		when(request.getRequestDispatcher("risultatiRicerca.jsp")).thenReturn(dispatcherSuccess);
        servlet = new RicercaVoliServlet();
        DbPopulator.initializeDatabase();
	}
	
	//TC_5.1_1 data partenza non valida
	@Test
	public void testCase_1() throws IOException, ServletException {		
		when(request.getParameter("city")).thenReturn("NAP - Naples, ITALY");
		when(request.getParameter("cityArrivals")).thenReturn("TXL - Berlin, GERMANY");
		when(request.getParameter("seats")).thenReturn("13");
		when(request.getParameter("dateDeparture")).thenReturn("123");
		when(request.getParameter("dateReturn")).thenReturn("12/02/2020");
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}
	
	//TC_5.1_2 data ritorno non valida
	@Test
	public void testCase_2() throws IOException, ServletException {		
		when(request.getParameter("city")).thenReturn("NAP - Naples, ITALY");
		when(request.getParameter("cityArrivals")).thenReturn("TXL - Berlin, GERMANY");
		when(request.getParameter("seats")).thenReturn("13");
		when(request.getParameter("dateDeparture")).thenReturn("10/02/2020");
		when(request.getParameter("dateReturn")).thenReturn("123");
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}
	
	//TC_5.1_3 formato aeroporto partenza non valido
	@Test
	public void testCase_3() throws IOException, ServletException {		
		when(request.getParameter("city")).thenReturn("Napoli");
		when(request.getParameter("cityArrivals")).thenReturn("TXL - Berlin, GERMANY");
		when(request.getParameter("seats")).thenReturn("13");
		when(request.getParameter("dateDeparture")).thenReturn("10/02/2020");
		when(request.getParameter("dateReturn")).thenReturn("12/02/2020");
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}
	
	//TC_5.1_4 formato aeroporto arrivo non valido
	@Test
	public void testCase_4() throws IOException, ServletException {		
		when(request.getParameter("city")).thenReturn("NAP - Naples, ITALY");
		when(request.getParameter("cityArrivals")).thenReturn("Berlino");
		when(request.getParameter("seats")).thenReturn("13");
		when(request.getParameter("dateDeparture")).thenReturn("10/02/2020");
		when(request.getParameter("dateReturn")).thenReturn("12/02/2020");
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}
	
	//TC_5.1_5  aeroporto partenza non esiste
	@Test
	public void testCase_5() throws IOException, ServletException {		
		when(request.getParameter("city")).thenReturn("WWW - Naples, ITALY");
		when(request.getParameter("cityArrivals")).thenReturn("TXL - Berlin, GERMANY");
		when(request.getParameter("seats")).thenReturn("13");
		when(request.getParameter("dateDeparture")).thenReturn("10/02/2020");
		when(request.getParameter("dateReturn")).thenReturn("12/02/2020");
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Aeroporto non esistente");
	}
	
	//TC_5.1_6  aeroporto arrivo non esiste
	@Test
	public void testCase_6() throws IOException, ServletException {		
		when(request.getParameter("city")).thenReturn("NAP - Naples, ITALY");
		when(request.getParameter("cityArrivals")).thenReturn("WWW - Berlin, GERMANY");
		when(request.getParameter("seats")).thenReturn("13");
		when(request.getParameter("dateDeparture")).thenReturn("10/02/2020");
		when(request.getParameter("dateReturn")).thenReturn("12/02/2020");
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Aeroporto non esistente");
	}
	
	//TC_5.1_7  success
	@Test
	public void testCase_7() throws IOException, ServletException {		
		when(request.getParameter("city")).thenReturn("NAP - Naples, ITALY");
		when(request.getParameter("cityArrivals")).thenReturn("TXL - Berlin, GERMANY");
		when(request.getParameter("seats")).thenReturn("2");
		when(request.getParameter("dateDeparture")).thenReturn("10/02/2020");
		when(request.getParameter("dateReturn")).thenReturn("12/02/2020");
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Success");
	}
}