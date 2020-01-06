package gestioneutente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.regex.*;


/**
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static Logger logger= Logger.getLogger("global");
	private static final long serialVersionUID = 1L;
	private String expNome="^[A-Za-z]{1,}$";
	private String expCognome="^[A-Za-z]{1,}$";
	private String expEmail="^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
	private String expPassword="^(?=.{6,}$)(?=.*[A-Z])(?=.*[0-9])([\\.-]?\\w+)*.*$";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String cPassword=request.getParameter("Cpassword");
		
		UtenteManager utenteManager=new UtenteManager();
		Account account=null;
		String redirect="";
		String message="";
		
		try {
			if (utenteManager.findAccountByEmail(email)!=null) {
					message+="Email già esistente.<br/>";
					System.out.println(message);
					response.getWriter().write("Email già esistente");
					redirect="registrazione.jsp";
				} 
			
			else if(!valida(nome, cognome, email, password, cPassword)) {
				if(!Pattern.matches(expNome, nome)) {
					 message+="Il nome deve contenere solo lettere dell'alfabeto<br/>";
					 System.out.println(message);
					 response.getWriter().write("Formato errato dati");
				
						
					redirect="registrazione.jsp";
				}  
				if(!Pattern.matches(expCognome, cognome)) {
					message+="Il cognome deve contenere solo lettere dell'alfabeto<br/>";
					System.out.println(message);
					response.getWriter().write("Formato errato dati");
					redirect="registrazione.jsp";
				}  
				if(!Pattern.matches(expEmail, email)) {
					message+="Formato email non valido, formato email: mariorossi@gmail.com<br/>";
					System.out.println(message);
					response.getWriter().write("Formato errato dati");
					redirect="registrazione.jsp";
				}  
				if(!Pattern.matches(expPassword, password)) {
					message+="Formato password non valido, deve contenere almeno 6 caratteri, almeno una lettera maiuscola e almeno una cifra<br/>";
					System.out.println(message);
					response.getWriter().write("Formato errato dati");
					redirect="registrazione.jsp";
				}  
				if(!password.equals(cPassword)) {
					message+="La password non corrisponde a quella inserita<br/>";
					System.out.println(message);
					response.getWriter().write("Formato errato dati");
					redirect="registrazione.jsp";
				}
				
			}
			
			else{
				message+="Registrazione effettuata con successo<br/>";
				System.out.println(message);
				utenteManager.signUp(account);
				redirect="loginFauzzo.jsp";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		

		
		request.setAttribute("message", message);
		request.getRequestDispatcher(redirect).forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

	
	private boolean valida(String nome, String cognome, String email, String password, String cPassword) {
		boolean valido=true;
		logger.info("Ho ricevuto "+nome);
		logger.info("Ho ricevuto "+password);
		logger.info("Ho ricevuto "+cPassword);
		if (!Pattern.matches(expNome, nome)) {
			logger.info("Nome non corrisponde");
			valido=false;
			System.out.print(nome);
		}
		if (!Pattern.matches(expCognome, cognome)) {
			logger.info("Cognome non corrisponde");
			valido=false;
			System.out.print(cognome);
		}
	
		if (!Pattern.matches(expPassword, password)) {
			logger.info("password non corrisponde");
			valido=false;
			System.out.print(password);
		}
		if (!Pattern.matches(expEmail, email)) {
			valido=false;
			System.out.print(email);
		}
		
		if(!password.equals(cPassword)) {
			logger.info("Cpassword non corrisponde");
			valido=false;
			System.out.print(cPassword);
		}
		
		return valido;
	
	}
	



}
