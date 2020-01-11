package gestioneutente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DriverManagerConnectionPool;

/**
 * Servlet implementation class listaAccountServlet
 */
@WebServlet("/listaAccountServlet")
public class ListaAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Account> allUtenti = new ArrayList<Account>();
	
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
	
		try {
			allUtenti = model.getAllUsers();
			request.setAttribute("allUtentiAdmin", allUtenti);
			String nextJSP = "listaAccount.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
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
