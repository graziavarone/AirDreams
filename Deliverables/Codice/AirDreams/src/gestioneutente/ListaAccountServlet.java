package gestioneutente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet gestisce le operazioni per la visualizzazione di tutti gli account 
 * registrati al sistema da parte del gestore compagnie
 */
@WebServlet(name="/ListaAccountServlet", urlPatterns= {"/gestoreCompagnie/ListaAccountServlet"})
public class ListaAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Account> allUtenti = new ArrayList<Account>();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ListaAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UtenteManager model = null;
		model = new UtenteManager();	
		String message = request.getParameter("message");
	
		try {
			allUtenti = model.getAllUsers();
			System.out.println(allUtenti);
			request.setAttribute("allUtentiAdmin", allUtenti);
			request.setAttribute("message", message);
			String nextJSP = "/gestoreCompagnie/listaAccount.jsp";
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
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
