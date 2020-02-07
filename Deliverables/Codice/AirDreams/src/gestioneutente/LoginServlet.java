package gestioneutente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		System.out.println("Ho ricevuto la sessione"+request.getSession());
		
		//recupero dati inviati dal form e creo un account apposito per il controllo
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		Account a=new Account();
		a.setEmail(email);
		a.setPassword(password);
		
		//effettuo il controllo delle credenziali e creo la sessione se tutto va a buon fine
		Account account=manager.signIn(a);
	
		if (account!=null) {
			System.out.println("Salvo nella sessione");
			System.out.println("Ho ricevuto "+account);
			request.getSession().setAttribute("account",account);
			System.out.println("Ho salvato "+account);
			request.getSession().setAttribute("roles",account.getRuolo());
			System.out.println("Ho salvato");
			
			
			if(request.getSession().getAttribute("voli")!=null) {
				redirectedPage="/cliente/AggiungiAlCarrelloServlet";
			} else {
			redirectedPage="/index.jsp";
			}
			response.getWriter().write("Success");
		} else {
			String error="autenticazione fallita";
			request.setAttribute("message", error);
			response.getWriter().write("Failed");
			redirectedPage="/errorPage.jsp";
		}
		
		//redirect alla pagina
			response.sendRedirect(request.getContextPath() + redirectedPage);
		//request.getServletContext().getRequestDispatcher(redirectedPage).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
