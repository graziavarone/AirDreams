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
import gestioneutente.ModificaInfoPersonaliServlet;

public class ModificaInfoPersonaliTest {
	@Mock
	MockHttpServletRequest request;

	@Mock
	MockHttpServletResponse response;
	
	@Mock
	MockRequestDispatcher dispatcher;
	
	@Mock
	MockHttpSession session;
	
	@Mock
	MockServletContext context;

	private ModificaInfoPersonaliServlet servlet;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(request.getServletContext()).thenReturn(context);
		when(request.getServletContext().getRequestDispatcher("/cliente/DettagliAccountServlet")).thenReturn(dispatcher);

        servlet = new ModificaInfoPersonaliServlet();
        DbPopulator.initializeDatabase();
	}
	
	//TC_6.1_1 nome non rispetta il formato
	@Test
	public void testCase_1() throws IOException, ServletException {	
		when(request.getParameter("nome")).thenReturn("Grazia3");
		when(request.getParameter("cognome")).thenReturn("Varone");
		when(request.getParameter("email")).thenReturn("grazia@virgilio.it");
		when(request.getParameter("password")).thenReturn("Grazia1998");
		
		when(request.getSession()).thenReturn(session);
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Failed");
	}
	
	//TC_6.1_2 cognome non rispetta il formato
	@Test
	public void testCase_2() throws IOException, ServletException {		
		when(request.getParameter("nome")).thenReturn("Grazia");
		when(request.getParameter("cognome")).thenReturn("Varone3");
		when(request.getParameter("email")).thenReturn("grazia@virgilio.it");
		when(request.getParameter("password")).thenReturn("Grazia1998");
		
		when(request.getSession()).thenReturn(session);
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Failed");
	}
	
	//TC_6.1_3 email non rispetta il formato
	@Test
	public void testCase_3() throws IOException, ServletException {		
		when(request.getParameter("nome")).thenReturn("Grazia");
		when(request.getParameter("cognome")).thenReturn("Varone");
		when(request.getParameter("email")).thenReturn("grazia@virgilioit");
		when(request.getParameter("password")).thenReturn("Grazia1998");
		
		when(request.getSession()).thenReturn(session);
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Failed");
	}
	
	//TC_6.1_4 email già presente nel DB
	@Test
	public void testCase_4() throws IOException, ServletException {		
		when(request.getParameter("nome")).thenReturn("Grazia");
		when(request.getParameter("cognome")).thenReturn("Varone");
		when(request.getParameter("email")).thenReturn("noemi@gmail.com");
		when(request.getParameter("password")).thenReturn("Grazia1998");
		
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("account")).thenReturn(new Account("Grazia","Varone","grazia@virgilio.it","Grazia1998"));
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Failed");
	}
	
	//TC_6.1_5 success
	@Test
	public void testCase_5() throws IOException, ServletException {		
		when(request.getParameter("nome")).thenReturn("Grazia");
		when(request.getParameter("cognome")).thenReturn("Varone");
		when(request.getParameter("email")).thenReturn("grazia@hotmail.it");
		when(request.getParameter("password")).thenReturn("Grazia1998!");
		
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("account")).thenReturn(new Account("Grazia","Varone","grazia@virgilio.it","Grazia1998"));
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
