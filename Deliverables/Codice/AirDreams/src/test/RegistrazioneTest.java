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
import org.springframework.mock.web.MockRequestDispatcher;

import db.DriverManagerConnectionPool;
import gestioneutente.Account;
import gestioneutente.RegistrazioneServlet;
import gestioneutente.UtenteManager;
import junit.framework.TestCase;

public class RegistrazioneTest extends TestCase {

	@Mock
	MockHttpServletRequest request;

	@Mock
	MockHttpServletResponse response;
	
	@Mock
	MockRequestDispatcher dispatcher;
	
	@Mock
	MockRequestDispatcher dispatcherSuccess;

	
	private RegistrazioneServlet servlet;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(request.getRequestDispatcher("registrazione.jsp")).thenReturn(dispatcher);
		when(request.getRequestDispatcher("login.jsp?message=Registrazione effettuata con successo")).thenReturn(dispatcherSuccess);
        servlet = new RegistrazioneServlet();
        DbPopulator.initializeDatabase();
	}
	
	//TC_7.2_1 nome non valido
	@Test
	public void testCase_1() throws IOException, ServletException {		
		when(request.getParameter("nome")).thenReturn("123");
		when(request.getParameter("cognome")).thenReturn("Varone");
		when(request.getParameter("email")).thenReturn("grazia_varone@hotmail.it");
		when(request.getParameter("password")).thenReturn("@Emanuele2009");
		when(request.getParameter("Cpassword")).thenReturn("@Emanuele2009");
		
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}
	
	//TC_7.2_2 cognome sbagliato
	@Test
	public void testCase_2() throws IOException, ServletException {		
		when(request.getParameter("nome")).thenReturn("Grazia");
		when(request.getParameter("cognome")).thenReturn("123");
		when(request.getParameter("email")).thenReturn("grazia_varone@hotmail.it");
		when(request.getParameter("password")).thenReturn("@Emanuele2009");
		when(request.getParameter("Cpassword")).thenReturn("@Emanuele2009");
		
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}
	
	//TC_7.2_3 email sbagliata
	@Test
	public void testCase_3() throws IOException, ServletException {		
		when(request.getParameter("nome")).thenReturn("Grazia");
		when(request.getParameter("cognome")).thenReturn("Varone");
		when(request.getParameter("email")).thenReturn("grazia@varone@hotmail.it");
		when(request.getParameter("password")).thenReturn("@Emanuele2009");
		when(request.getParameter("Cpassword")).thenReturn("@Emanuele2009");
		
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}

	
	//TC_7.2_4 email gi‡ presente
	@Test
	public void testCase_4() throws IOException, ServletException{	
		when(request.getParameter("nome")).thenReturn("Grazia");
		when(request.getParameter("cognome")).thenReturn("Varone");
		when(request.getParameter("email")).thenReturn("teresa@hotmail.it");
		when(request.getParameter("password")).thenReturn("@Emanuele2009");
		when(request.getParameter("Cpassword")).thenReturn("@Emanuele2009");
		
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Email gia' esistente");
	}
	
	//TC_7.2_5 password non rispetta il formato
	@Test
	public void testCase_5() throws IOException, ServletException {	
		when(request.getParameter("nome")).thenReturn("Grazia");
		when(request.getParameter("cognome")).thenReturn("Varone");
		when(request.getParameter("email")).thenReturn("qualcosa@hotmail.it");
		when(request.getParameter("password")).thenReturn("123");
		when(request.getParameter("Cpassword")).thenReturn("123");
		
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Formato errato dati");
	}
	
	//TC_7.2_6 conferma password diverso
	@Test
	public void testCase_6() throws IOException, ServletException {	
		when(request.getParameter("nome")).thenReturn("Grazia");
		when(request.getParameter("cognome")).thenReturn("Varone");
		when(request.getParameter("email")).thenReturn("qualcosa@hotmail.it");
		when(request.getParameter("password")).thenReturn("@Emanuele2009");
		when(request.getParameter("Cpassword")).thenReturn("123");
		
		
		
		PrintWriter MyWriter = Mockito.mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		Mockito.verify(MyWriter).write("Password non corrisponde");
	}
	
	//TC_7.2_7 success
	@Test
	public void testCase_7() throws IOException, ServletException {	
		when(request.getParameter("nome")).thenReturn("Grazia");
		when(request.getParameter("cognome")).thenReturn("Varone");
		when(request.getParameter("email")).thenReturn("grazia_varone@hotmail.it");
		when(request.getParameter("password")).thenReturn("@Emanuele2009");
		when(request.getParameter("Cpassword")).thenReturn("@Emanuele2009");
		
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
