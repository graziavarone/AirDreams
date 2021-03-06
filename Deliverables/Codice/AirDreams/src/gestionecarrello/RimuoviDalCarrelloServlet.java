package gestionecarrello;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestioneutente.Account;

/**
 * La servlet gestisce tutte le operazioni per la rimozione di un volo
 * dal carrello di un utente correttemente loggato al sistema
 */
@WebServlet(name="/RimuoviDalCarrelloServlet", urlPatterns= {"/cliente/RimuoviDalCarrelloServlet"})
public class RimuoviDalCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("idVolo");
		Account account=(Account)request.getSession().getAttribute("account");
		
		CarrelloManager carrelloManager=new CarrelloManager();
		
		try {
			carrelloManager.rimuoviVoloDalCarrello(id,account.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("message", "Volo/i rimosso/i dal tuo carrello");
		request.getServletContext().getRequestDispatcher("/cliente/CarrelloServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
