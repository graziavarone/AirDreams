package gestionevolo;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionecompagniaaerea.CompagniaAerea;
import gestionecompagniaaerea.CompagniaAereaManager;
import gestioneutente.Account;
import gestioneutente.UtenteManager;

@WebServlet("/AggiungiVoloServlet")
public class AggiungiVoloServlet extends HttpServlet {
	private static Logger logger= Logger.getLogger("global");
	private String expAeroporto="^[A-Z]{3} - [A-Za-z  ]{1,}, [A-Za-z ]{1,}$";
	private String expData= "^\\s*(0?[1-9]|1[1-9]|2[2-9]|3[01])\\/\\s*(1[012]|0?[1-9])\\/\\d{4}\\s*$";
	private String expPrice="^([0-9]){1,}[.]([0-9]){1,2}$";
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			AeroportoManager manager=new AeroportoManager();
			CompagniaAereaManager cManager = new CompagniaAereaManager();
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
			
			
			String codAeroportoP = city.substring(0,3);
			String codAeroportoA = cityArrivals.substring(0,3);
			int codiceP = codAeroportoP.length();
			int codiceA = codAeroportoA.length();
			String cityP = null;
			String statoP = null;
			String cityA = null;
			String statoA = null;
			String nome = null;
			String message = null;
			String redirect = null;
			boolean compreso = false;
	
			int indTrattinoP = city.indexOf("-");
			int indTrattinoA = cityArrivals.indexOf("-");
			int indVirgolaP = city.indexOf(",");
			int indVirgolaA = cityArrivals.indexOf(",");
			
			if(indTrattinoP != -1 && indVirgolaP != -1) {
				cityP = city.substring(indTrattinoP+2, indVirgolaP);
				statoP = city.substring(indVirgolaP+2, city.length());
			}
			
			if(indTrattinoA != -1 && indVirgolaA != -1) {
				cityA = cityArrivals.substring(indTrattinoA+2, indVirgolaA);
				statoA = cityArrivals.substring(indVirgolaP+2, cityArrivals.length());
			}
			
			CompagniaAerea ca = cManager.visualizzaInfoCompagniaAerea(airline);
			
			
			if(baggage.equals("compreso")) {
				compreso=true;
			}
			
			System.out.println(compreso);
			System.out.println(seats);
			
			if(valida(city, cityArrivals, dateDeparture, price)) {
				
				try {
					Aeroporto aeroportoP = manager.findAeroportoById(codAeroportoP);
					Aeroporto aeroportoA = manager.findAeroportoById(codAeroportoA);
					
					if(aeroportoP.equals(aeroportoA)) {
						message="L'aeroporto di arrivo non può essere uguale a quello di partenza";
						System.out.println(message);
						request.setAttribute("message",message);
						redirect = "/aggiungiVolo.jsp";

					} else {
						
						
						if(aeroportoP.getCity().equalsIgnoreCase(cityP) && aeroportoP.getStato().equalsIgnoreCase(statoP) && 
							  aeroportoA.getCity().equalsIgnoreCase(cityA) && aeroportoA.getStato().equalsIgnoreCase(statoA)) {
						
						   
						   DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");          
						   LocalDate otherDayDate = LocalDate.parse(dateDeparture, FORMATO_DIA);
					      
						if(hDeparture.equals("h") || minDeparture.equals("min") || hFly.equals("h") || minFly.equals("min")) {
								message="Inserire orario di partenza";
								System.out.println(message);
								request.setAttribute("message",message);
								redirect = "/aggiungiVolo.jsp";

						 } else {
							 int ore = Integer.parseInt(hDeparture);
							 int minuti = Integer.parseInt(minDeparture);
							 LocalTime orarioPartenza = LocalTime.of(ore, minuti);
							 int oreDurata = Integer.parseInt(hFly);
							 int minutiDurata = Integer.parseInt(minFly);
							 LocalTime durataVolo = LocalTime.of(oreDurata, minutiDurata);
							 float prezzo = Float.parseFloat(price); 
						       
						     Volo volo = new Volo(otherDayDate, prezzo, seats, durataVolo, orarioPartenza, compreso, ca, aeroportoP, aeroportoA);
						     VoloManager vManager = new VoloManager();
						     vManager.aggiungiVolo(volo);
						     redirect = "/listaVoli.jsp";
						 }
						
					} else {
						  	message="città e stato della partenza/arrivo non corrispondono";
							System.out.println(message);
							request.setAttribute("message", message);
							redirect = "/aggiungiVolo.jsp";

					 
					} else {
						message="l'aeroporto della partenza/arrivo non esistente";
						System.out.println(message);
						request.setAttribute("message", message);
						redirect = "/aggiungiVolo.jsp";
					}
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
			else{
				message="Il formato non è valido";
				System.out.println(message);
				request.setAttribute("message", message);
				redirect = "/aggiungiVolo.jsp";
			}   
			
			 RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(redirect);
		     dispatcher.forward(request, response);
			
			}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
		
		private boolean valida(String aeroportoP, String aeroportoA, String dateDeparture, String price) {
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
			
			if (!Pattern.matches(expPrice, price)) {
				valido=false;
				System.out.println("il prezzo non corrisponde");
			}
			
			return valido;
			
		}
		
		


	}

