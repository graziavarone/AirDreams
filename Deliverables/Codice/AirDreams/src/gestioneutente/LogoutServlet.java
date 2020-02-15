package gestioneutente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet gestisce le operazioni per il logout di un utente dal sistema
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//chiudo la sessione rimuovendo gli attributi impostati al login
		request.getSession().removeAttribute("roles");
		request.getSession().removeAttribute("account");
		request.getSession().invalidate();
		
		//si è sempre reindirizzati alla index
		String redirectedPage="/index.jsp";
		response.sendRedirect(request.getContextPath()+ redirectedPage);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
