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

import gestionecompagniaaerea.CompagniaAereaManager;

/**
 * La servlet gestisce le operazioni per la modifica delle informazioni personali
 * di un utente, relativamente al suo account
 */
@WebServlet(name="/ModificaAccountServlet",
urlPatterns= {"/gestoreCompagnie/ModificaAccountServlet"})
public class ModificaAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger= Logger.getLogger("global");
	private String expNome="^[A-Za-z]{1,}$";
	private String expCognome="^[A-Za-z]{1,}$";
	private String expEmail="^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteManager manager=new UtenteManager();
		CompagniaAereaManager compagniaAereaManager=new CompagniaAereaManager();
		String message="";
		
		//prelevo i dati del form
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String email=request.getParameter("email");
		String emailVecchia=request.getParameter("emailVecchia");
		
		String compagniaAerea=request.getParameter("combo");
		System.out.println(compagniaAerea);
		Account newAccount=new Account();
		newAccount.setNome(nome);
		newAccount.setCognome(cognome);
		newAccount.setEmail(email);
		
		Account accountVecchio=null;
		Account a=null;
		try {
			accountVecchio = manager.findAccountByEmail(emailVecchia);
	
			a = manager.findAccountByEmail(email);
			
			if(compagniaAerea.equals("Nessuna")) {
				newAccount.setCompagniaAerea(null);
				newAccount.setRuolo(null);
			} else {
				newAccount.setCompagniaAerea(compagniaAereaManager.visualizzaInfoCompagniaAerea(compagniaAerea));
				newAccount.setRuolo(Ruolo.gestoreVoli);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//controllo che la mail inviata da form (se e' stata modificata) non sia uguale ad una gi� presente nel DB
		if(email.equals(emailVecchia)) {
			newAccount.setPassword(accountVecchio.getPassword());
			
			if(valida(nome,cognome,email)) {
			manager.aggiornaProfilo(accountVecchio, newAccount);
			} else response.getWriter().write("Failed");
			
			message+="aggiornamento dati eseguito";
			request.setAttribute("message",message);
			System.out.println("Modifica eseguita direttamente poiche' la email e' rimasta invariata");
			request.setAttribute("utente",newAccount);
		} else {
			if (a!=null) { //trovato un account con la email appena inserita
				message+="la email inserita e' gia' presente, inserirne una nuova";
				request.setAttribute("message",message);
				request.setAttribute("utente", accountVecchio);
				System.out.println("Non ho modificato l'utente poiche' esiste gia' la mail appena inserita");
				response.getWriter().write("Failed");
			} else {
				newAccount.setPassword(accountVecchio.getPassword());

				if(valida(nome,cognome,email)) {
					manager.aggiornaProfilo(accountVecchio, newAccount);
					} else response.getWriter().write("Failed");
				message+="aggiornamento dati eseguito";
				request.setAttribute("message",message);
				System.out.println("Modifica eseguita poiche' la mail inserita non � gia' presente nel DB");
				response.getWriter().write("Success");
				request.setAttribute("utente",newAccount);
			}
		}
	
		try {
			request.setAttribute("compagnie", compagniaAereaManager.getAllCompanies());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getServletContext().getRequestDispatcher("/gestoreCompagnie/dettagliAccount.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean valida(String nome, String cognome, String email) {
		boolean valido=true;
		logger.info("Ho ricevuto "+nome);
		
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
	
		if (!Pattern.matches(expEmail, email)) {
			valido=false;
			System.out.print(email);
		}

		return valido;
	}
}
