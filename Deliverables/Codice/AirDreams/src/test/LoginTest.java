package test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockRequestDispatcher;

import db.DriverManagerConnectionPool;
import gestioneutente.Account;
import gestioneutente.LoginServlet;
import gestioneutente.UtenteManager;

public class LoginTest {

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

	
	private LoginServlet servlet;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(request.getRequestDispatcher("errorPage.jsp")).thenReturn(dispatcher);
		when(request.getRequestDispatcher("index.jsp")).thenReturn(dispatcherSuccess);

        servlet = new LoginServlet();
        DbPopulator.initializeDatabase();
	}
	
	//TC_7.1_1 email sbagliata
	@Test
	public void testCase_1() throws IOException, ServletException {		
		when(request.getParameter("email")).thenReturn("grazia_varone@hotmail.it");
		when(request.getParameter("password")).thenReturn("@Emanuele2009");
		
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Failed");
	}
	
	//TC_7.1_2 password sbagliata
	@Test
	public void testCase_2() throws IOException, ServletException {		
		when(request.getParameter("email")).thenReturn("rosaria@gmail.com");
		when(request.getParameter("password")).thenReturn("@Emanuele2009");
		
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Failed");
	}
	
	//TC_7.1_3 success
	@Test
	public void testCase_3() throws IOException, ServletException {		
		when(request.getParameter("email")).thenReturn("rosaria@gmail.com");
		when(request.getParameter("password")).thenReturn("Rosaria1998");
		
		when(request.getSession()).thenReturn(session);
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Success");
	}
}