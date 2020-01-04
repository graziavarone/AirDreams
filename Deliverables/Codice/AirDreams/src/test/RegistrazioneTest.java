package test;

import gestoreutente.RegistrazioneServlet;

public class RegistrazioneTest {
	private RegistrazioneServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String message = "Formato errato dati";
	final String successMessage="successo";
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet=new RegistrazioneServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
}
