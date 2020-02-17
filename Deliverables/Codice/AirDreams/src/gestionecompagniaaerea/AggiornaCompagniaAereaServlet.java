package gestionecompagniaaerea;


import java.io.IOException;

import java.sql.SQLException;
import java.util.regex.Pattern;

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
@WebServlet(name="/AggiornaCompagniaAereaServlet",
			urlPatterns= {"/gestoreCompagnie/AggiornaCompagniaAereaServlet"})

public class AggiornaCompagniaAereaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String expSito="^www.[a-z]{1,}.com$";
	private String expDimensioni="^\\d{2,4}x\\d{2,4}x\\d{2,4}$";
	
   
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
		String message="";
	
				
		CompagniaAereaManager compagniaAereaManager=new CompagniaAereaManager();
		PoliticaBagaglioManager politicaBagaglioManager=new PoliticaBagaglioManager();
		
		if(!valida(sitoCompagnia,dimensioniBagaglioMano,dimensioniBagaglioStiva)) {
			
			if(!Pattern.matches(expSito, sitoCompagnia)) {
				message+="Formato non valido per il sito, il nome della compagnia non deve contenere caratteri maiuscoli  (es. www.ryanair.com)<br/>";
				System.out.println(message);
				response.getWriter().write("Formato errato dati");
				 request.setAttribute("message",message);
				 nextJSP = "/gestoreCompagnie/DettagliCompagniaAereaServlet?nome="+nomeCompagnia;
			}  
			if(!Pattern.matches(expDimensioni, dimensioniBagaglioMano)) {
				message+="Formato non valido per le dimensioni (es. 12x132x14)<br/>";
				System.out.println(message);
				response.getWriter().write("Formato errato dati");
				 request.setAttribute("message",message);
				 	 nextJSP = "/gestoreCompagnie/DettagliCompagniaAereaServlet?nome="+nomeCompagnia;
			}  
			if(!Pattern.matches(expDimensioni, dimensioniBagaglioStiva)) {
				message+="Formato non valido per le dimensioni (es. 12x132x14)<br/>";
				System.out.println(message);
				response.getWriter().write("Formato errato dati");
				 request.setAttribute("message",message);
				 	 nextJSP = "/gestoreCompagnie/DettagliCompagniaAereaServlet?nome="+nomeCompagnia;
			}  
		} else {
				
				
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
		response.getWriter().write("Success");
		request.setAttribute("politicaMano", bagaglioAMano);
	
		request.setAttribute("politicaStiva", bagaglioAStiva);
		request.setAttribute("message", "Informazioni aggiornate");
			 nextJSP = "/gestoreCompagnie/DettagliCompagniaAereaServlet?nome="+nomeCompagnia;
		}
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);


	}
	
	private boolean valida(String sito, String dimensioniMano, String dimensioniStiva) {
		boolean valido=true;

		if (!Pattern.matches(expSito, sito)) {
			valido=false;
			System.out.print(sito);
		}
	
		if (!Pattern.matches(expDimensioni, dimensioniMano)) {
			valido=false;
			System.out.print(dimensioniMano);
		}
		if (!Pattern.matches(expDimensioni, dimensioniStiva)) {
			valido=false;
			System.out.print(dimensioniStiva);
		}

		
		return valido;
	
	}


}