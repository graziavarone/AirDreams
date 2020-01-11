package gestioneutente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EliminaAccountServlet
 */
@WebServlet("/EliminaAccountServlet")
public class EliminaAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UtenteManager manager=new UtenteManager();
		Account account=(Account) request.getSession().getAttribute("account");
		String message="";
		
		System.out.println("L'utente che ha richiesto l'eliminazione dell'account e' :" + account);
		boolean conferma=manager.eliminaAccount(account.getEmail());
		if (conferma) {
			message+="account eliminato";
			request.setAttribute("message",message);
			
		} else {
			message+="cancellazione account fallita";
			request.setAttribute("message",message);
		}
		
		//chiudo la sessione rimuovendo gli attributi impostati al login
		request.getSession().removeAttribute("roles");
		request.getSession().removeAttribute("account");
		request.getSession().invalidate();
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
