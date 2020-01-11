package gestioneutente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModificaInfoPersonaliServlet
 */
@WebServlet("/ModificaInfoPersonaliServlet")
public class ModificaInfoPersonaliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger= Logger.getLogger("global");
	private String expNome="^[A-Za-z]{1,}$";
	private String expCognome="^[A-Za-z]{1,}$";
	private String expEmail="^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
	private String expPassword="^(?=.{6,}$)(?=.*[A-Z])(?=.*[0-9])([\\.-]?\\w+)*.*$";
	private String messageValidation="";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UtenteManager manager=new UtenteManager();
		Account oldAccount=(Account) request.getSession().getAttribute("account");
		System.out.println("Ho letto questo account dalla sessione: " + oldAccount);
		String message="";
		
		//prelevo i dati del form
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		Account newAccount=new Account();
		newAccount.setNome(nome);
		newAccount.setCognome(cognome);
		newAccount.setEmail(email);
		newAccount.setPassword(password);
		System.out.println("Ho creato questo account con i dati inseriti dall'utente: " + newAccount);
		
		//solo se la validazione dei valori va a buon fine, eseguo quel che devo
		if (valida(nome,cognome,email,password)) {
			
			//controllo che la mail inserita (se è stata modificata) non sia uguale ad una già presente nel DB
			if (newAccount.getEmail().equals(oldAccount.getEmail())) {
				//l'utente non ha modificato l'email -> si e' sicuri che la sua è unica
				manager.aggiornaProfilo(oldAccount, newAccount);
				message="aggiornamento dati eseguito";
				response.getWriter().write("success");
				request.setAttribute("message",message);
				System.out.println("Modifica eseguita direttamente poiche' la email e' rimasta invariata");
				request.getSession().setAttribute("account",newAccount);
			} else {
				try {
					Account a=manager.findAccountByEmail(newAccount.getEmail());
					if (a!=null) { 
						
						//e' stato trovato un account con la email appena inserita
						message="L'email inserita è gia' presente, inserirne una nuova";
						response.getWriter().write("Failed");
						request.setAttribute("message",message);
						System.out.println("Non ho modificato l'utente poiche' esiste gia' la mail appena inserita");
					} else {
						manager.aggiornaProfilo(oldAccount, newAccount);
						message="aggiornamento dati eseguito";
						response.getWriter().write("Success");
						request.setAttribute("message",message);
						System.out.println("Modifica eseguita poiche' la mail inserita non è gia' presente nel DB");
						request.getSession().setAttribute("account",newAccount);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			response.getWriter().write("Failed");
			request.setAttribute("message",messageValidation);
		}
		
		request.getRequestDispatcher("profilo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean valida(String nome, String cognome, String email, String password) {
		boolean valido=true;
		messageValidation="";
		logger.info("Ho ricevuto " + nome);
		logger.info("Ho ricevuto " + cognome);
		logger.info("Ho ricevuto " + email);
		logger.info("Ho ricevuto " + password);
		
		if (!Pattern.matches(expNome, nome)) {
			logger.info("Nome non corrisponde");
			messageValidation+="Il nome deve contenere solo lettere dell'alfabeto";
			valido=false;
			System.out.print(nome);
		}
		if (!Pattern.matches(expCognome, cognome)) {
			logger.info("Cognome non corrisponde");
			valido=false;
			messageValidation+="Il cognome deve contenere solo lettere dell'alfabeto";
			System.out.print(cognome);
		}
	
		if (!Pattern.matches(expPassword, password)) {
			logger.info("password non corrisponde,");
			valido=false;
			messageValidation+="La password non rispetta il formato previsto (deve contenere almeno una cifra)";
			System.out.print(password);
		}
		if (!Pattern.matches(expEmail, email)) {
			valido=false;
			messageValidation+="L'email non rispetta il formato previsto (esempio: mariorossi@gmail.com)";
			System.out.print(email);
		}
		
		return valido;
	}
}
