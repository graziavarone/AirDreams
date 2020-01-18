package test;

import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockRequestDispatcher;

import db.DriverManagerConnectionPool;

public class RicercaVoloTest {
	@Mock
	MockHttpServletRequest request;

	@Mock
	MockHttpServletResponse response;
	
	@Mock
	MockRequestDispatcher dispatcher;
	
	@Mock
	MockHttpSession session;

	//private RicercaVoloServlet servlet;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(request.getRequestDispatcher("")).thenReturn(dispatcher);

        //servlet = new RicercaVoloServlet();
        DbPopulator.initializeDatabase();
	}
	
	//TC_5.1_1 la data di partenza selezionata non è valida per formato
	//TC_5.1_2 la data di partenza selezionata è valida per formato ma precedente a quella della richiesta
	//TC_5.1_3 la data di ritorno selezionata non è valida per formato
	//TC_5.1_4 la data di ritorno selezionata è valida per formato ma precedente a quella della richiesta
	//TC_5.1_5 la data di ritorno è precedente alla data di partenza
	//TC_5.1_6 l'aeroporto di partenza inserito non esiste
	//TC_5.1_7 l'aeroporto di arrivo inserito non esiste
	//TC_5.1_8 success
	
	@After
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
}
