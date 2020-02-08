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
 * Servlet implementation class CarrelloServlet
 */

@WebServlet(name="/CarrelloServlet",
urlPatterns= {"/cliente/CarrelloServlet"})
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarrelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account account=(Account)request.getSession().getAttribute("account");
		
		CarrelloManager carrelloManager=new CarrelloManager();
		
		try {
			Carrello carrello=carrelloManager.getCarrelloUtente(account.getEmail());
			
			request.getSession().setAttribute("carrello", carrello);
			
			request.getServletContext().getRequestDispatcher("/cliente/carrello.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
