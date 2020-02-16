package gestionecompagniaaerea;


import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet gestisce tutte le operazioni per l'aggiornamento
 * delle informazioni relative ad una compagnia aerea i cui voli
 * sono offerti nel catalogo di sistema e gestiti da un determinato gestore voli
 */
@WebServlet(name="/AggiornaCompagniaAereaServlet", urlPatterns= {"/gestoreCompagnie/AggiornaCompagniaAereaServlet"})
public class AggiornaCompagniaAereaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeCompagnia=request.getParameter("nome");
		
		String sitoCompagnia=request.getParameter("sitoCompagnia");
		
		String pesoBagaglioMano=request.getParameter("pesoMano");
		String dimensioniBagaglioMano=request.getParameter("dimensioniMano");
		
		String pesoBagaglioStiva=request.getParameter("pesoStiva");
		String dimensioniBagaglioStiva=request.getParameter("dimensioniStiva");
		String prezzoStiva=request.getParameter("prezzoStiva");

		String nextJSP="";
		
		CompagniaAereaManager compagniaAereaManager=new CompagniaAereaManager();
		PoliticaBagaglioManager politicaBagaglioManager=new PoliticaBagaglioManager();
					
		CompagniaAerea compagnia=new CompagniaAerea(nomeCompagnia, sitoCompagnia);
		
		try {
			compagniaAereaManager.aggiornaCompagnia(compagnia);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
		PoliticaBagaglioMano bagaglioAMano=new PoliticaBagaglioMano(Integer.parseInt(pesoBagaglioMano), dimensioniBagaglioMano, compagnia);
		try {
			politicaBagaglioManager.aggiornaPoliticaBagaglioMano(bagaglioAMano);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
		PoliticaBagaglioStiva bagaglioAStiva =new PoliticaBagaglioStiva(Integer.parseInt(pesoBagaglioStiva), dimensioniBagaglioStiva,Float.parseFloat(prezzoStiva), compagnia);
		try {
			politicaBagaglioManager.aggiornaPoliticaBagaglioStiva(bagaglioAStiva);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("politicaMano", bagaglioAMano);
	
		request.setAttribute("politicaStiva", bagaglioAStiva);
		request.setAttribute("message", "Informazioni aggiornate");
		nextJSP = "/gestoreCompagnie/dettagliCompagnia.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
	}
}
