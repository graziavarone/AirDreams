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
import gestionevolo.ModificaVoloServlet;

public class ModificaVoloTest {

	@Mock
	MockHttpServletRequest request;

	@Mock
	MockHttpServletResponse response;
	
	@Mock
	MockRequestDispatcher dispatcher;
	
	@Mock
	MockServletContext context;
	
	@Mock
	MockHttpSession session;

	
	private ModificaVoloServlet servlet;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(request.getServletContext()).thenReturn(context);
		when(request.getServletContext().getRequestDispatcher("/gestoreVoli/dettagliVoloServlet?idVolo=1")).thenReturn(dispatcher);
	

        servlet = new ModificaVoloServlet();
        DbPopulator.initializeDatabase();
	}
	
	//TC_4.2_1 aeroporto di partenza sbagliato
	@Test
	public void testCase_1() throws IOException, ServletException {		
		when(request.getParameter("idVolo")).thenReturn("1");
		when(request.getParameter("city")).thenReturn("AKDJI");
		when(request.getParameter("cityArrivals")).thenReturn("FSC - Figari, FRANCE");
		when(request.getParameter("dateDeparture")).thenReturn("05/09/2020");
		when(request.getParameter("price")).thenReturn("87.50");
		when(request.getParameter("seats")).thenReturn("4");
		when(request.getParameter("hFly")).thenReturn("02");
		when(request.getParameter("minFly")).thenReturn("40");
		when(request.getParameter("hDeparture")).thenReturn("13");
		when(request.getParameter("minDeparture")).thenReturn("50");
		when(request.getParameter("baggage")).thenReturn("compreso");
		when(request.getParameter("airline")).thenReturn("Ryanair");

		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Failed");
	}
	
	//TC_4.2_2 aeroporto di arrivo sbagliato
		@Test
		public void testCase_2() throws IOException, ServletException {		
			when(request.getParameter("idVolo")).thenReturn("1");
			when(request.getParameter("city")).thenReturn("NAP - Naples, ITALY");
			when(request.getParameter("cityArrivals")).thenReturn("MSKD");
			when(request.getParameter("dateDeparture")).thenReturn("05/09/2020");
			when(request.getParameter("price")).thenReturn("87.50");
			when(request.getParameter("seats")).thenReturn("4");
			when(request.getParameter("hFly")).thenReturn("02");
			when(request.getParameter("minFly")).thenReturn("40");
			when(request.getParameter("hDeparture")).thenReturn("13");
			when(request.getParameter("minDeparture")).thenReturn("50");
			when(request.getParameter("baggage")).thenReturn("compreso");
			when(request.getParameter("airline")).thenReturn("Ryanair");
			
			
			PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
			when(response.getWriter()).thenReturn(MyWriter);
			servlet.doPost(request, response);	
			Mockito.verify(MyWriter).write("Failed");
		}

	
	//TC_4.2_3 data partenza sbagliata
		@Test
		public void testCase_3() throws IOException, ServletException {		
			when(request.getParameter("idVolo")).thenReturn("1");
			when(request.getParameter("city")).thenReturn("NAP - Naples, ITALY");
			when(request.getParameter("cityArrivals")).thenReturn("FSC - Figari, FRANCE");
			when(request.getParameter("dateDeparture")).thenReturn("5784");
			when(request.getParameter("price")).thenReturn("87.50");
			when(request.getParameter("seats")).thenReturn("4");
			when(request.getParameter("hFly")).thenReturn("02");
			when(request.getParameter("minFly")).thenReturn("40");
			when(request.getParameter("hDeparture")).thenReturn("13");
			when(request.getParameter("minDeparture")).thenReturn("50");
			when(request.getParameter("baggage")).thenReturn("compreso");
			when(request.getParameter("airline")).thenReturn("Ryanair");
			
			
			PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
			when(response.getWriter()).thenReturn(MyWriter);
			servlet.doPost(request, response);	
			Mockito.verify(MyWriter).write("Failed");
		}
		

		
		//TC_4.2_5 aeroporto di partenza non esiste
		@Test
		public void testCase_4() throws IOException, ServletException {		
			when(request.getParameter("idVolo")).thenReturn("1");
			when(request.getParameter("city")).thenReturn("NAP - Njksl, ITALY");
			when(request.getParameter("cityArrivals")).thenReturn("FSC - Figari, FRANCE");
			when(request.getParameter("dateDeparture")).thenReturn("05/09/2020");
			when(request.getParameter("price")).thenReturn("87.50");
			when(request.getParameter("seats")).thenReturn("4");
			when(request.getParameter("hFly")).thenReturn("02");
			when(request.getParameter("minFly")).thenReturn("40");
			when(request.getParameter("hDeparture")).thenReturn("13");
			when(request.getParameter("minDeparture")).thenReturn("50");
			when(request.getParameter("baggage")).thenReturn("compreso");
			when(request.getParameter("airline")).thenReturn("Ryanair");

			
			PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
			when(response.getWriter()).thenReturn(MyWriter);
			servlet.doPost(request, response);	
			Mockito.verify(MyWriter).write("L'aeroporto non esiste");
		}
		
		//TC_4.2_6 aeroporto di arrivo non esiste
		@Test
		public void testCase_5() throws IOException, ServletException {		
			when(request.getParameter("idVolo")).thenReturn("1");
			when(request.getParameter("city")).thenReturn("NAP - Naples, ITALY");
			when(request.getParameter("cityArrivals")).thenReturn("FSC - Flsh, FRANCE");
			when(request.getParameter("dateDeparture")).thenReturn("05/09/2020");
			when(request.getParameter("price")).thenReturn("87.50");
			when(request.getParameter("seats")).thenReturn("4");
			when(request.getParameter("hFly")).thenReturn("02");
			when(request.getParameter("minFly")).thenReturn("40");
			when(request.getParameter("hDeparture")).thenReturn("13");
			when(request.getParameter("minDeparture")).thenReturn("50");
			when(request.getParameter("baggage")).thenReturn("compreso");
			when(request.getParameter("airline")).thenReturn("Ryanair");

			
			PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
			when(response.getWriter()).thenReturn(MyWriter);
			servlet.doPost(request, response);	
			Mockito.verify(MyWriter).write("L'aeroporto non esiste");
		}

	
	//TC_4.2_7 success
		@Test
		public void testCase_6() throws IOException, ServletException {		
			when(request.getParameter("idVolo")).thenReturn("1");
			when(request.getParameter("city")).thenReturn("NAP - Naples, ITALY");
			when(request.getParameter("cityArrivals")).thenReturn("FSC - Figari, FRANCE");
			when(request.getParameter("dateDeparture")).thenReturn("05/09/2020");
			when(request.getParameter("price")).thenReturn("87.50");
			when(request.getParameter("seats")).thenReturn("4");
			when(request.getParameter("hFly")).thenReturn("02");
			when(request.getParameter("minFly")).thenReturn("40");
			when(request.getParameter("hDeparture")).thenReturn("13");
			when(request.getParameter("minDeparture")).thenReturn("50");
			when(request.getParameter("baggage")).thenReturn("compreso");
			when(request.getParameter("airline")).thenReturn("Ryanair");
		
			when(request.getSession()).thenReturn(session);
		
		
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