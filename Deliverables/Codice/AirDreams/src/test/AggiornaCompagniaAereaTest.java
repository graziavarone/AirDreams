package test;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockRequestDispatcher;
import org.springframework.mock.web.MockServletContext;

import db.DriverManagerConnectionPool;
import gestionecompagniaaerea.AggiornaCompagniaAereaServlet;
import junit.framework.TestCase;

public class AggiornaCompagniaAereaTest extends TestCase {
	@Mock
	MockHttpServletRequest request;

	@Mock
	MockHttpServletResponse response;
	
	@Mock
	MockRequestDispatcher dispatcher;
	
	
	@Mock
	MockServletContext servletContext;
	
	private AggiornaCompagniaAereaServlet servlet;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(request.getServletContext()).thenReturn(servletContext);
		when(request.getServletContext().getRequestDispatcher( "/gestoreCompagnie/DettagliCompagniaAereaServlet?nome=Ryanair")).thenReturn(dispatcher);
        servlet = new AggiornaCompagniaAereaServlet();
        DbPopulator.initializeDatabase();
	}
	
	//sito sbagliato
	@Test
	public void testCase_1() throws IOException, ServletException {		
		when(request.getParameter("nome")).thenReturn("Ryanair");
		when(request.getParameter("sitoCompagnia")).thenReturn("www.Ryanair.com");
		when(request.getParameter("pesoMano")).thenReturn("1");
		when(request.getParameter("dimensioniMano")).thenReturn("123x123x123");
		when(request.getParameter("pesoStiva")).thenReturn("2");
		when(request.getParameter("dimensioniStiva")).thenReturn("321x321x321");
		when(request.getParameter("prezzoStiva")).thenReturn("56");
		
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}
	
	
	//dimensioni mano
	@Test
	public void testCase_2() throws IOException, ServletException {		
		when(request.getParameter("nome")).thenReturn("Ryanair");
		when(request.getParameter("sitoCompagnia")).thenReturn("www.ryanair.com");
		when(request.getParameter("pesoMano")).thenReturn("1");
		when(request.getParameter("dimensioniMano")).thenReturn("123");
		when(request.getParameter("pesoStiva")).thenReturn("2");
		when(request.getParameter("dimensioniStiva")).thenReturn("321x321x321");
		when(request.getParameter("prezzoStiva")).thenReturn("56");
		
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}
	
	//dimensioni stiva
	@Test
	public void testCase_3() throws IOException, ServletException {		
		when(request.getParameter("nome")).thenReturn("Ryanair");
		when(request.getParameter("sitoCompagnia")).thenReturn("www.easyjet.com");
		when(request.getParameter("pesoMano")).thenReturn("1");
		when(request.getParameter("dimensioniMano")).thenReturn("123x123x123");
		when(request.getParameter("pesoStiva")).thenReturn("2");
		when(request.getParameter("dimensioniStiva")).thenReturn("321");
		when(request.getParameter("prezzoStiva")).thenReturn("56");
		
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}
	

	
	//success
	@Test
	public void testCase_4() throws IOException, ServletException {		
		when(request.getParameter("nome")).thenReturn("Ryanair");
		when(request.getParameter("sitoCompagnia")).thenReturn("www.ryanair.com");
		when(request.getParameter("pesoMano")).thenReturn("1");
		when(request.getParameter("dimensioniMano")).thenReturn("123x123x123");
		when(request.getParameter("pesoStiva")).thenReturn("2");
		when(request.getParameter("dimensioniStiva")).thenReturn("321x321x321");
		when(request.getParameter("prezzoStiva")).thenReturn("56");
		
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Success");
	} 
	
	@AfterEach
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}

}
