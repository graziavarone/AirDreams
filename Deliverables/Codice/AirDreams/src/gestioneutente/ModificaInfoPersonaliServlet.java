package gestioneutente;

import java.io.IOException;
import java.sql.SQLException;

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
		newAccount.setCompagniaAerea(oldAccount.getCompagniaAerea());
		newAccount.setRuolo(oldAccount.getRuolo());
		System.out.println("Ho creato questo account con i dati inseriti dall'utente: " + newAccount);

		
		//controllo che la mail inviata da form (se è stata modificata) non sia uguale ad una già presente nel DB
		if(newAccount.getEmail().equals(oldAccount.getEmail())) {
			//l'utente non ha modificato l'email-> si e' sicuri che la sua è unica
			manager.aggiornaProfilo(oldAccount, newAccount);
			message+="aggiornamento dati eseguito";
			request.setAttribute("message",message);
			System.out.println("Modifica eseguita direttamente poiche' la email e' rimasta invariata");
			request.getSession().setAttribute("account",newAccount);
		} else {
			try {
				Account a=manager.findAccountByEmail(newAccount.getEmail());
				if (a!=null) { //trovato un account con la email appena inserita
					message+="la email inserita è gia' presente, inserirne una nuova";
					request.setAttribute("message",message);
					System.out.println("Non ho modificato l'utente poiche' esiste gia' la mail appena inserita");
				} else {
					manager.aggiornaProfilo(oldAccount, newAccount);
					message+="aggiornamento dati eseguito";
					request.setAttribute("message",message);
					System.out.println("Modifica eseguita poiche' la mail inserita non è gia' presente nel DB");
					request.getSession().setAttribute("account",newAccount);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.getRequestDispatcher("profilo.jsp").forward(request, response);
		//response.sendRedirect(request.getContextPath()+ "/profilo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
