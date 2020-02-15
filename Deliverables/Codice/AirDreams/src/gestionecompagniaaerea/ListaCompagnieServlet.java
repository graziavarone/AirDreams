package gestionecompagniaaerea;

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
 * La servlet gestisce tutte le operazioni per la visualizzazione
 * di tutta la lista delle compagnie aeree i cui voli sono offerti dal sistema
 * e singolarmente gestiti da un determinato gestore voli
 */
@WebServlet(name="/ListaCompagnieServlet", urlPatterns= {"/gestoreCompagnie/ListaCompagnieServlet"})
public class ListaCompagnieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<CompagniaAerea> allComA = new ArrayList<CompagniaAerea>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaCompagnieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CompagniaAereaManager manager = null;
		manager = new CompagniaAereaManager();	
		String message = request.getParameter("message");
	
		try {
			allComA = manager.getAllCompanies();
			System.out.println(allComA);
			request.setAttribute("allComA", allComA);
			request.setAttribute("message", message);
			String nextJSP = "/gestoreCompagnie/listaCompagnie.jsp";
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
