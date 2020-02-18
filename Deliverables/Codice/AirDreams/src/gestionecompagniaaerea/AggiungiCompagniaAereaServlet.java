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
 * La servlet gestisce tutte le operazioni per l'aggiunta di una nuova
 * compagnia aerea al sistema, i cui voli potranno essere aggiunti
 * al catalogo di sistema e gestiti da un determinato gestore voli
 */
@WebServlet(name="/AggiungiCompagniaAereaServlet",
			urlPatterns= {"/gestoreCompagnie/AggiungiCompagniaAereaServlet"})

public class AggiungiCompagniaAereaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String expNome="^[A-Za-z  ]{1,}$";
	private String expSito="^www.[a-z]{1,}.com$";
	private String expDimensioni="^\\d{2,4}x\\d{2,4}x\\d{2,4}$";
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeCompagnia=request.getParameter("nome");
		System.out.println("Nomec compagnia Ë "+nomeCompagnia);
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
				CompagniaAerea compagniaExists=compagniaAereaManager.visualizzaInfoCompagniaAerea(nomeCompagnia);
				
				if(!valida(nomeCompagnia,sitoCompagnia,dimensioniBagaglioMano,dimensioniBagaglioStiva)) {
					if(!Pattern.matches(expNome, nomeCompagnia)) {
						 message+="Il nome della compagnia non puo' contenere altri caratteri oltre lettere<br/>";
						 System.out.println(message);
						 response.getWriter().write("Formato errato dati");
						 request.setAttribute("message",message);
						 nextJSP = "/gestoreCompagnie/aggiungiCompagnia.jsp";
						 
					}  
					if(!Pattern.matches(expSito, sitoCompagnia)) {
						message+="Formato non valido per il sito, il nome della compagnia non deve contenere caratteri maiuscoli  (es. www.ryanair.com)<br/>";
						System.out.println(message);
						response.getWriter().write("Formato errato dati");
						 request.setAttribute("message",message);
						 nextJSP = "/gestoreCompagnie/aggiungiCompagnia.jsp";
					}  
					if(!Pattern.matches(expDimensioni, dimensioniBagaglioMano)) {
						message+="Formato non valido per le dimensioni (es. 12x132x14)<br/>";
						System.out.println(message);
						response.getWriter().write("Formato errato dati");
						 request.setAttribute("message",message);
						 nextJSP = "/gestoreCompagnie/aggiungiCompagnia.jsp";
					}  
					if(!Pattern.matches(expDimensioni, dimensioniBagaglioStiva)) {
						message+="Formato non valido per le dimensioni (es. 12x132x14)<br/>";
						System.out.println(message);
						response.getWriter().write("Formato errato dati");
						 request.setAttribute("message",message);
						 nextJSP = "/gestoreCompagnie/aggiungiCompagnia.jsp";
					}  
				} else {
				
				if(compagniaExists==null) {
					System.out.println("Posso aggiungere compagnia");
					
					try {
						CompagniaAerea compagnia=new CompagniaAerea(nomeCompagnia, sitoCompagnia);
						compagniaAereaManager.aggiungiCompagnia(compagnia);
						
						PoliticaBagaglioMano bagaglioAMano=new PoliticaBagaglioMano(Integer.parseInt(pesoBagaglioMano), dimensioniBagaglioMano, compagnia);
						politicaBagaglioManager.aggiungiPoliticaBagaglioMano(bagaglioAMano);
						
						PoliticaBagaglioStiva bagaglioAStiva=new PoliticaBagaglioStiva(Integer.parseInt(pesoBagaglioStiva), dimensioniBagaglioStiva,Float.parseFloat(prezzoStiva), compagnia);
						politicaBagaglioManager.aggiungiPoliticaBagaglioStiva(bagaglioAStiva);
						System.out.println("Compagnia aggiunta");
					
						response.getWriter().write("Success");
						  nextJSP = "/gestoreCompagnie/ListaCompagnieServlet";
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					System.out.println("Non posso aggiungere compagnia");
					response.getWriter().write("Compagnia esistente");
					request.setAttribute("message","La compagnia gi‡ esiste");
					 nextJSP = "/gestoreCompagnie/aggiungiCompagnia.jsp";
				}
				}
		
	
       
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);


	}
	
	private boolean valida(String nome, String sito, String dimensioniMano, String dimensioniStiva) {
		boolean valido=true;

		if (!Pattern.matches(expNome, nome)) {
			valido=false;
			System.out.print(nome);
		}
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
