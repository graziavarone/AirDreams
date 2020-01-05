package gestioneutente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UtenteManager manager=new UtenteManager();
		String redirectedPage="";
		
		//recupero dati inviati dal form e creo un account apposito per il controllo
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		Account a=new Account();
		a.setEmail(email);
		a.setPassword(password);
		
		//effettuo il controllo delle credenziali e creo la sessione se tutto va a buon fine
		Account account=manager.signIn(a);
		if (account!=null) {
			request.getSession().setAttribute("account",account);
			request.getSession().setAttribute("roles",account.getRuolo());
		} else {
			String error="autenticazione fallita";
			redirectedPage="/errorPage.jsp?message=" + error;
		}
		
		//redirect alla pagina
		response.sendRedirect(request.getContextPath() + redirectedPage);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
