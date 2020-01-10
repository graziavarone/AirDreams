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





@WebServlet(name="/AggiungiCompagniaAereaServlet",
			urlPatterns= {"/gestoreCompagnie/AggiungiCompagniaAereaServlet"})

public class AggiungiCompagniaAereaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeCompagnia=request.getParameter("nome");
		System.out.println("Nomec compagnia è "+nomeCompagnia);
		String sitoCompagnia=request.getParameter("sitoCompagnia");
		
		String pesoBagaglioMano=request.getParameter("pesoMano");
		String dimensioniBagaglioMano=request.getParameter("dimensioniMano");
		
		String pesoBagaglioStiva=request.getParameter("pesoStiva");
		String dimensioniBagaglioStiva=request.getParameter("dimensioniStiva");
		String prezzoStiva=request.getParameter("prezzoStiva");

		String nextJSP="";
	
				
				CompagniaAereaManager compagniaAereaManager=new CompagniaAereaManager();
				PoliticaBagaglioManager politicaBagaglioManager=new PoliticaBagaglioManager();
				CompagniaAerea compagniaExists=compagniaAereaManager.visualizzaInfoCompagniaAerea(nomeCompagnia);
				
				if(compagniaExists==null) {
					System.out.println("Posso aggiungere compagnia");
					
					try {
						CompagniaAerea compagnia=new CompagniaAerea(nomeCompagnia, sitoCompagnia);
						compagniaAereaManager.aggiungiCompagnia(compagnia);
						
						PoliticaBagaglio bagaglioAMano=new PoliticaBagaglio(Integer.parseInt(pesoBagaglioMano), dimensioniBagaglioMano, 0, compagnia);
						politicaBagaglioManager.aggiungiPoliticaBagaglio(bagaglioAMano);
						
						PoliticaBagaglio bagaglioAStiva=new PoliticaBagaglio(Integer.parseInt(pesoBagaglioStiva), dimensioniBagaglioStiva,Float.parseFloat(prezzoStiva), compagnia);
						politicaBagaglioManager.aggiungiPoliticaBagaglio(bagaglioAStiva);
						System.out.println("Compagnia aggiunta");
					
							
						  nextJSP = "gestoreCompagnie/listaCompagnie.jsp";
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					System.out.println("Non posso aggiungere compagnia");
					request.setAttribute("message","La compagnia già esiste");
					  nextJSP = "gestoreCompagnie/aggiungiCompagnia.jsp";
				}
		
	
       
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+nextJSP);
        dispatcher.forward(request, response);


	}


}
