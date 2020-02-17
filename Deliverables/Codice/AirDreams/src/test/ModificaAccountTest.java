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
import org.springframework.mock.web.MockServletContext;

import db.DriverManagerConnectionPool;
import gestioneutente.ModificaAccountServlet;

public class ModificaAccountTest {

	@Mock
	MockHttpServletRequest request;

	@Mock
	MockHttpServletResponse response;
	
	@Mock
	MockRequestDispatcher dispatcher;

	@Mock
	MockServletContext context;
	
	private ModificaAccountServlet servlet;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(request.getServletContext()).thenReturn(context);
        servlet = new ModificaAccountServlet();
        DbPopulator.initializeDatabase();
	}
	
	//TC_6.1_1 nome sbagliato
	@Test
	public void testCase_1() throws IOException, ServletException {		
		when(request.getParameter("nome")).thenReturn("123");
		when(request.getParameter("cognome")).thenReturn("Varone");
		when(request.getParameter("email")).thenReturn("rosaria@gmail.com");
		when(request.getParameter("emailVecchia")).thenReturn("rosaria@gmail.com");
		when(request.getParameter("combo")).thenReturn("Nessuna");
		when(request.getServletContext().getRequestDispatcher("/gestoreCompagnie/DettagliUtenteServlet?email=rosaria@gmail.com")).thenReturn(dispatcher);
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Failed");
	}
	
	//TC_6.1_2 cognome sbagliato
	@Test
	public void testCase_2() throws IOException, ServletException {		
		when(request.getParameter("nome")).thenReturn("Grazia");
		when(request.getParameter("cognome")).thenReturn("123");
		when(request.getParameter("email")).thenReturn("rosaria@gmail.com");
		when(request.getParameter("emailVecchia")).thenReturn("rosaria@gmail.com");
		when(request.getParameter("combo")).thenReturn("Nessuna");
		when(request.getServletContext().getRequestDispatcher("/gestoreCompagnie/DettagliUtenteServlet?email=rosaria@gmail.com")).thenReturn(dispatcher);
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Failed");
	}
	
	//TC_6.1_3 email sbagliato
	@Test
	public void testCase_3() throws IOException, ServletException {		
		when(request.getParameter("nome")).thenReturn("Grazia");
		when(request.getParameter("cognome")).thenReturn("Varone");
		when(request.getParameter("email")).thenReturn("ro@saria@gmail.com");
		when(request.getParameter("emailVecchia")).thenReturn("rosaria@gmail.com");
		when(request.getParameter("combo")).thenReturn("Nessuna");
		when(request.getServletContext().getRequestDispatcher("/gestoreCompagnie/DettagliUtenteServlet?email=rosaria@gmail.com")).thenReturn(dispatcher);
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Failed");
	}
	
	//TC_6.1_4 email gi� presente
	@Test
	public void testCase_4() throws IOException, ServletException {		
		when(request.getParameter("nome")).thenReturn("Grazia");
		when(request.getParameter("cognome")).thenReturn("Varone");
		when(request.getParameter("email")).thenReturn("noemi@gmail.com");
		when(request.getParameter("emailVecchia")).thenReturn("rosaria@gmail.com");
		when(request.getParameter("combo")).thenReturn("Nessuna");
		when(request.getServletContext().getRequestDispatcher("/gestoreCompagnie/DettagliUtenteServlet?email=rosaria@gmail.com")).thenReturn(dispatcher);
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Failed");
	}
	
	//TC_6.1_4 success
	@Test
	public void testCase_5() throws IOException, ServletException {		
		when(request.getParameter("nome")).thenReturn("Grazia");
		when(request.getParameter("cognome")).thenReturn("Varone");
		when(request.getParameter("email")).thenReturn("grazia@gmail.com");
		when(request.getParameter("emailVecchia")).thenReturn("rosaria@gmail.com");
		when(request.getParameter("combo")).thenReturn("Nessuna");
		when(request.getServletContext().getRequestDispatcher("/gestoreCompagnie/DettagliUtenteServlet?email=grazia@gmail.com")).thenReturn(dispatcher);
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
