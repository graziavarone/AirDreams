package gestionevolo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionecompagniaaerea.CompagniaAerea;
import gestionecompagniaaerea.CompagniaAereaManager;

/**
 * La servlet gestisce le operazioni per la modifica dei dettagli di un determinato volo
 * correntemente consultato nel sistema
 */
@WebServlet(name = "ModificaVoloServlet", urlPatterns = {"/gestoreVoli/ModificaVoloServlet"})
public class ModificaVoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String expAeroporto="^[A-Z]{3} - [A-Za-z  ]{1,}, [A-Za-z ]{1,}$";
	private String expData= "^\\s*(0?[1-9]|1[0-9]|2[2-9]|3[01])\\/\\s*(1[012]|0?[1-9])\\/\\d{4}\\s*$";

	private DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	//globali per poterle utilizare nella funzione
	private String cityP = null; //citta partenza
	private String statoP = null; //stato partenza
	private String cityA = null; //citta arrivo
	private String statoA = null; //stato arrivo

	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//instanzio i manager che mi servono
			 String message = null; //messaggio in caso di successo/errore
			AeroportoManager manager=new AeroportoManager();
			CompagniaAereaManager cManager = new CompagniaAereaManager();
		    VoloManager vManager = new VoloManager();
			Volo voloVecchio = null;
			//ottengo i parametri che mi servono
		    String idVolo = request.getParameter("idVolo");
		    try {
				voloVecchio = vManager.findByID(idVolo);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String city=request.getParameter("city");
			String cityArrivals=request.getParameter("cityArrivals");
			String dateDeparture = request.getParameter("dateDeparture");
			String airline = request.getParameter("airline");
			String price = request.getParameter("price");
			String hDeparture = request.getParameter("hDeparture");
			String minDeparture = request.getParameter("minDeparture");
			String hFly = request.getParameter("hFly");
			String minFly = request.getParameter("minFly");
			String baggage = request.getParameter("baggage");
			int seats = Integer.parseInt(request.getParameter("seats"));
		
			String redirect = null; //pagina di redirect per fare il forward
			boolean compreso = false; //bagaglio stiva compreso
			Aeroporto aeroportoP=null;//partenza
			Aeroporto aeroportoA=null;//arrivo
			String codAeroportoP = "";
			String codAeroportoA ="";
	
			int indTrattinoP = city.indexOf("-");//cerco trattino e virgola per ottenere citta e stato
			int indTrattinoA = cityArrivals.indexOf("-");
			int indVirgolaP = city.indexOf(",");
			int indVirgolaA = cityArrivals.indexOf(",");
			
			if(baggage.equals("compreso")) {
				compreso=true;
			}
			
			//se le stringhe hanno lunghezza maggiore di 3 allora posso fare il substring
			if(city.length()>=3 && cityArrivals.length()>=3) {
			 codAeroportoP = city.substring(0,3);
			 codAeroportoA = cityArrivals.substring(0,3);
			 
				if(indTrattinoP != -1 && indVirgolaP != -1) { //se virgola e trattino non ci sono mi viene restituito -1
					cityP = city.substring(indTrattinoP+2, indVirgolaP);
					statoP = city.substring(indVirgolaP+2, city.length());
				}
				
				if(indTrattinoA != -1 && indVirgolaA != -1) {
					cityA = cityArrivals.substring(indTrattinoA+2, indVirgolaA);
					statoA = cityArrivals.substring(indVirgolaA+2, cityArrivals.length());
				}

			}
			
			if (!valida(city,cityArrivals,dateDeparture)) {
				if(!Pattern.matches(expAeroporto, city)) {
					message+="Formato aeroporto partenza non valido, inserire nel formato CODICE - citt‡, stato<br>";
				response.getWriter().write("Failed");
				request.setAttribute("message", message);
				redirect="/gestoreVoli/dettagliVoloServlet?idVolo="+idVolo;
				}
				
				if(!Pattern.matches(expAeroporto, cityArrivals)) {
					message+="Formato aeroporto arrivo non valido,  inserire nel formato CODICE - citt‡, stato<br>";
				response.getWriter().write("Failed");
				request.setAttribute("message", message);
				redirect="/gestoreVoli/dettagliVoloServlet?idVolo="+idVolo;
				}
				
				if(!Pattern.matches(expData, dateDeparture)) {
					message+="Formato data partenza non valido, inserire nel formato dd/mm/aa<br>";
				response.getWriter().write("Failed");
				request.setAttribute("message", message);
				redirect="/gestoreVoli/dettagliVoloServlet?idVolo="+idVolo;
				}
				
		
			} else {
				 try {
					aeroportoP = manager.findAeroportoById(codAeroportoP);
					aeroportoA = manager.findAeroportoById(codAeroportoA);
					
					String success=null;
					success=controlloAeroporti(aeroportoP, aeroportoA);
				
					if(!success.equals("Success")) {
						response.getWriter().write("L'aeroporto non esiste");
						request.setAttribute("volo", voloVecchio);
						request.setAttribute("message",success);
						redirect="/gestoreVoli/dettagliVoloServlet?idVolo="+idVolo;
					} else if(!controllaComboBox(hDeparture,minDeparture,hFly,minFly)) {
						request.setAttribute("message","Inserire orarioPartenza e/o durata volo");
						request.setAttribute("volo", voloVecchio);
						redirect="/gestoreVoli/dettagliVoloServlet?idVolo="+idVolo;
					}	
						else {
						  LocalDate otherDayDate = LocalDate.parse(dateDeparture, FORMATO_DIA);
						  
						  int ore = Integer.parseInt(hDeparture);
						  int minuti = Integer.parseInt(minDeparture);
						  LocalTime orarioPartenza = LocalTime.of(ore, minuti);
						  
						  int oreDurata = Integer.parseInt(hFly);
						  int minutiDurata = Integer.parseInt(minFly);
						  LocalTime durataVolo = LocalTime.of(oreDurata, minutiDurata);
						  
						  float prezzo = Float.parseFloat(price);
						  
						  CompagniaAerea ca = cManager.visualizzaInfoCompagniaAerea(airline);
						  Volo volo = new Volo(otherDayDate, prezzo, seats, durataVolo, orarioPartenza, compreso, ca, aeroportoP, aeroportoA);
						  
						  
						  volo.setId(Integer.parseInt(idVolo));
						  vManager.modificaVolo(volo);
						  response.getWriter().write("Success");
						  request.setAttribute("volo", volo);
						  message = "Informazioni modificate";
						  request.setAttribute("message", message);
						  redirect="/gestoreVoli/dettagliVoloServlet?idVolo="+idVolo;
					}
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				 
			}
			
			 
						
			 RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(redirect);
		     dispatcher.forward(request, response);
			
			}
		
		

		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
		
		private boolean valida(String aeroportoP, String aeroportoA, String dateDeparture) {
			boolean valido=true;
			if (!Pattern.matches(expAeroporto, aeroportoP)) {
				valido=false;
				System.out.println("aeroporto partenza non corrisponde");
	
			}
			if (!Pattern.matches(expAeroporto, aeroportoA)) {
				valido=false;
				System.out.println("aeroporto arrivo non corrisponde");
			}
			
			if (!Pattern.matches(expData, dateDeparture)) {
				valido=false;
				System.out.println("dataPartenza non corrisponde");
			}
			
		
			return valido;
			
		}
		
		
		
		private String controlloAeroporti(Aeroporto aeroportoP, Aeroporto aeroportoA) {

			if (aeroportoP==null || aeroportoA==null) 
				return "Aeroporto di partenza e/o arrivo non esiste";
			
			if(aeroportoP.equals(aeroportoA)) 
				return "Aeroporto partenza uguale a quello di arrivo";
			
			 if(!(aeroportoP.getCity().equalsIgnoreCase(cityP) && aeroportoP.getStato().equalsIgnoreCase(statoP) && 
					  aeroportoA.getCity().equalsIgnoreCase(cityA) && aeroportoA.getStato().equalsIgnoreCase(statoA)))
				return "Citt‡ e stato di aeroporto di partenza e/o arrivo non consistenti con il codice dell'aeroporto";
			 
			return "Success";
			
			
		}
		
		

		private boolean controllaComboBox(String hDeparture, String minDeparture, String hFly, String minFly) {
			if(hDeparture.equals("h") || minDeparture.equals("min") || hFly.equals("h") || minFly.equals("min")) return false;
			
			return true;
				
		}
		

	}

