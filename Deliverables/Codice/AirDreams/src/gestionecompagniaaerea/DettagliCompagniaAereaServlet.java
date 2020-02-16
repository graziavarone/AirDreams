package gestionecompagniaaerea;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet gestisce tutte le operazioni per la visualizzazione
 * delle informazioni di una determinata compagnia aerea
 */
@WebServlet(name="/DettagliCompagniaAereaServlet",
urlPatterns= {"/gestoreCompagnie/DettagliCompagniaAereaServlet"})

public class DettagliCompagniaAereaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome=request.getParameter("nome");
        
        CompagniaAereaManager compagniaAereaManager=new CompagniaAereaManager();
        
        CompagniaAerea compagniaAerea=compagniaAereaManager.visualizzaInfoCompagniaAerea(nome);
        
        PoliticaBagaglioManager politicaBagaglioManager=new PoliticaBagaglioManager();
      
        try {
			PoliticaBagaglioMano politicaMano=politicaBagaglioManager.trovaPoliticaCompagniaMano(nome);
			request.setAttribute("politicaMano", politicaMano);
			
			PoliticaBagaglioStiva politicaStiva=politicaBagaglioManager.trovaPoliticaCompagniaStiva(nome);
			request.setAttribute("politicaStiva", politicaStiva);
			
			request.getRequestDispatcher("dettagliCompagnia.jsp").forward(request, response);
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
