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
 * La servlet gestisce le operazioni per la ricerca di account, secondo differenti criteri,
 * da parte del gestore compagnie
 */
@WebServlet(name="/RicercaAccountServlet", urlPatterns= {"/gestoreCompagnie/RicercaAccountServlet"})
public class RicercaAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Account> lettera = new ArrayList<Account>();
	String message;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaAccountServlet() {
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
		
		String nome = request.getParameter("searchName");
		System.out.println("Stampa nomi:" +nome);
		String cognome = request.getParameter("searchSurname");
		System.out.println("Stampa cognomi:" +cognome);
		String pagina = request.getParameter("page");
		
		if (nome != null && cognome != null) {
			try {
				lettera = model.findAccountByLetter(nome, cognome);
				if(lettera.size() == 0)  {
					message = "Non ci sono utenti che iniziano per questa lettera";	
					request.setAttribute("messageRicerca", message);		
				}
					
				request.setAttribute("allUtentiAdmin", lettera);

				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/gestoreCompagnie/listaAccount.jsp?page=" + pagina);
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
