package gestioneutente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DettagliAccountServlet
 */
@WebServlet(name="/DettagliAccountServlet",
urlPatterns= {"/cliente/DettagliAccountServlet"})
public class DettagliAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//prelevo l'utente dalla sessione
		Account account=(Account) request.getSession().getAttribute("account");
		
		CartaDiCreditoManager cm=new CartaDiCreditoManager();
		try {
			ArrayList<CartaDiCredito> carteUtente=cm.findAll(account.getEmail());
			
			request.setAttribute("carte", carteUtente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//da implementare successivamente quando si implementeranno le funzionalità ordine e carta di credito
	
		request.getServletContext().getRequestDispatcher("/cliente/profilo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
