package gestionecompagniaaerea;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;





@WebServlet(name="/AggiornaCompagniaAereaServlet",
			urlPatterns= {"/gestoreCompagnie/AggiornaCompagniaAereaServlet"})

public class AggiornaCompagniaAereaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompagniaAerea compagniaExists;
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codMano=Integer.parseInt(request.getParameter("idMano"));
		System.out.println("id mano "+codMano);
		int codStiva=Integer.parseInt(request.getParameter("idStiva"));
		System.out.println("id stiva "+codStiva);
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
						
		PoliticaBagaglio bagaglioAMano=new PoliticaBagaglio(Integer.parseInt(pesoBagaglioMano), dimensioniBagaglioMano, 0, compagnia);
		bagaglioAMano.setCodice(codMano);
		try {
			politicaBagaglioManager.aggiornaPoliticaBagaglio(bagaglioAMano);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
		PoliticaBagaglio bagaglioAStiva=new PoliticaBagaglio(Integer.parseInt(pesoBagaglioStiva), dimensioniBagaglioStiva,Float.parseFloat(prezzoStiva), compagnia);
		bagaglioAStiva.setCodice(codStiva);
		try {
			politicaBagaglioManager.aggiornaPoliticaBagaglio(bagaglioAStiva);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
					
		
			
		
			 
				
		
	
		nextJSP = "gestoreCompagnie/listaCompagnie.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+nextJSP);
        dispatcher.forward(request, response);


	}


}
