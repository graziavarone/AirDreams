package gestioneutente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionecompagniaaerea.CompagniaAerea;
import gestionecompagniaaerea.CompagniaAereaManager;
import gestionecompagniaaerea.PoliticaBagaglioManager;
import gestionecompagniaaerea.PoliticaBagaglioMano;
import gestionecompagniaaerea.PoliticaBagaglioStiva;

/**
 * Servlet implementation class DettagliUtenteServlet
 */
@WebServlet(name="/DettagliUtenteServlet",
urlPatterns= {"/gestoreCompagnie/DettagliUtenteServlet"})
public class DettagliUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettagliUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");

		String redirect="";
        
        UtenteManager utenteManager=new UtenteManager();
        CompagniaAereaManager compagniaAereaManager=new CompagniaAereaManager();
        try {
			Account utente=utenteManager.findAccountByEmail(email);
			
	    	request.setAttribute("utente", utente);
	    	request.setAttribute("compagnie", compagniaAereaManager.getCompagnie());
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
        
    
			request.getRequestDispatcher("dettagliAccount.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
