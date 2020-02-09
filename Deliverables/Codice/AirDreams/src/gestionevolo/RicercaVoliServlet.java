package gestionevolo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RicercaVoliServlet
 */
@WebServlet("/RicercaVoliServlet")
public class RicercaVoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private String expAeroporto="^[A-Z]{3} - [A-Za-z  ]{1,}, [A-Za-z ]{1,}$";
	private String expData= "^\\s*(0?[1-9]|1[0-9]|2[2-9]|3[01])\\/\\s*(1[012]|0?[1-9])\\/\\d{4}\\s*$";
	private DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String cityP = null; //citta partenza
	private String statoP = null; //stato partenza
	private String cityA = null; //citta arrivo
	private String statoA = null; //stato arrivo
	private String message = null; //messaggio in caso di successo/errore
       
	 ArrayList<Volo> voliAndataDiretti=null;
	 ArrayList<Volo[]> voliAndataUnoScalo=null;
	 ArrayList<Volo[]> voliAndataDueScali=null;
	 ArrayList<Volo> voliRitornoDiretti=null;
	 ArrayList<Volo[]> voliRitornoUnoScalo=null;
	 ArrayList<Volo[]> voliRitornoDueScali=null;
	 

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaVoliServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aeroportoPartenza=request.getParameter("city");
		String aeroportoArrivo=request.getParameter("cityArrivals");
		String numPasseggeri=request.getParameter("seats");
		String dateDeparture=request.getParameter("dateDeparture");
		String dateReturn=request.getParameter("dateReturn");
		String checkbox=request.getParameter("Diretto");
		String durataAndataFiltro=request.getParameter("durataAndataFiltro");
		String prezzoAndataFiltro=request.getParameter("prezzoAndataFiltro");
		String durataRitornoFiltro=request.getParameter("durataRitornoFiltro");
		String prezzoRitornoFiltro=request.getParameter("prezzoRitornoFiltro");

		  LocalDate dataDepartureLd = LocalDate.parse(dateDeparture, FORMATO_DIA);
		  
		  VoloManager voloManager=new VoloManager();

		  //volo di andata e ritorno diretti
		  voliAndataDiretti=voloManager.cercaDiretti(aeroportoPartenza, aeroportoArrivo,
				  dataDepartureLd ,Integer.parseInt(numPasseggeri),Integer.parseInt(durataAndataFiltro),Integer.parseInt(prezzoAndataFiltro));
		 
		  
		  request.setAttribute("voliAndataDiretti", voliAndataDiretti);
		  
		System.out.println("voliAndataDiretti "+voliAndataDiretti);
		  
		  if(checkbox==null) {
		  voliAndataUnoScalo=voloManager.cercaUnoScalo(aeroportoPartenza, aeroportoArrivo,
				  dataDepartureLd, Integer.parseInt(numPasseggeri),Integer.parseInt(durataAndataFiltro),Integer.parseInt(prezzoAndataFiltro));
		  
		
		  
		   voliAndataDueScali=voloManager.cercaDueScali(aeroportoPartenza, aeroportoArrivo,
				  dataDepartureLd, Integer.parseInt(numPasseggeri),Integer.parseInt(durataAndataFiltro),Integer.parseInt(prezzoAndataFiltro));
		   
			  request.setAttribute("voliAndataUno", voliAndataUnoScalo);
			  request.setAttribute("voliAndataDue", voliAndataDueScali);
			  
				System.out.println("voliAndataUno "+voliAndataUnoScalo);
				System.out.println("voliAndataDue "+voliAndataDueScali);

		  }
		  
		 
			if(dateReturn!=null) {
			
				  LocalDate dataReturnLd = LocalDate.parse(dateReturn, FORMATO_DIA);
				  
				   voliRitornoDiretti=voloManager.cercaDiretti(aeroportoArrivo, aeroportoPartenza,
						  dataReturnLd,Integer.parseInt(numPasseggeri),Integer.parseInt(durataRitornoFiltro),Integer.parseInt(prezzoRitornoFiltro));
				  
					System.out.println("voliRitornoDiretti "+voliRitornoDiretti);
				if(checkbox==null) {
				 voliRitornoUnoScalo=voloManager.cercaUnoScalo(aeroportoArrivo, aeroportoPartenza,
						  dataReturnLd, Integer.parseInt(numPasseggeri),Integer.parseInt(durataRitornoFiltro),Integer.parseInt(prezzoRitornoFiltro));
			
				 voliRitornoDueScali=voloManager.cercaDueScali(aeroportoArrivo, aeroportoPartenza,
						  dataReturnLd, Integer.parseInt(numPasseggeri),Integer.parseInt(durataRitornoFiltro),Integer.parseInt(prezzoRitornoFiltro));
		
					request.setAttribute("voliRitornoUno", voliRitornoUnoScalo);
					  request.setAttribute("voliRitornoDue", voliRitornoDueScali);
					  
						System.out.println("voliRitornoUno "+voliRitornoUnoScalo);
						System.out.println("voliRitornoDue "+voliRitornoDueScali);
					  
				
				}
	
				
				 request.setAttribute("voliRitornoDiretti", voliRitornoDiretti);
				  request.setAttribute("durataRitorno", Integer.parseInt(durataRitornoFiltro));
				  request.setAttribute("prezzoRitorno", Integer.parseInt(prezzoRitornoFiltro));
			}
		

		 
		  request.setAttribute("city",aeroportoPartenza);
		  request.setAttribute("cityArrivals", aeroportoArrivo);
		  request.setAttribute("seats", numPasseggeri);
		  request.setAttribute("dateDeparture", dateDeparture);
		  request.setAttribute("dateReturn", dateReturn);
		  request.setAttribute("Diretto", checkbox);
		  
		  request.setAttribute("durataAndata", Integer.parseInt(durataAndataFiltro));
		  request.setAttribute("prezzoAndata", Integer.parseInt(prezzoAndataFiltro));
		
		  
		

			
		  String redirect = "risultatiRicerca.jsp";
		

			RequestDispatcher dispatcher = request.getRequestDispatcher(redirect);
			dispatcher.forward(request, response);
		
	}

	


	

		
	

	private String controlloAeroporti(Aeroporto aeroportoP, Aeroporto aeroportoA) {
		if (aeroportoP==null || aeroportoA==null) {
			return "Aeroporto di partenza e/o arrivo non esiste";
		}
		
		if(aeroportoP.equals(aeroportoA)) 
			return "Aeroporto partenza uguale a quello di arrivo";
		
		 if(!(aeroportoP.getCity().equalsIgnoreCase(cityP) && aeroportoP.getStato().equalsIgnoreCase(statoP) && 
				  aeroportoA.getCity().equalsIgnoreCase(cityA) && aeroportoA.getStato().equalsIgnoreCase(statoA)))
			return "Città e stato di aeroporto di partenza e/o arrivo non consistenti con il codice dell'aeroporto";
		 
		return "Success";
	}

	private boolean valida(String aeroportoPartenza, String aeroportoArrivo, String dateDeparture, String dateReturn) {
		boolean valido=true;
		if (!Pattern.matches(expAeroporto, aeroportoPartenza)) {
			valido=false;
			System.out.println("aeroporto partenza non corrisponde");

		}
		if (!Pattern.matches(expAeroporto, aeroportoArrivo)) {
			valido=false;
			System.out.println("aeroporto arrivo non corrisponde");
		}
		
		if (!Pattern.matches(expData, dateDeparture)) {
			valido=false;
			System.out.println("dataPartenza non corrisponde");
		}
		
		if(dateReturn!=null) {
		if (!Pattern.matches(expData, dateReturn)) {
			valido=false;
			System.out.println("data ritorno non corrisponde");
		}
		}
		
		return valido;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AeroportoManager aeroportoManager=new AeroportoManager();
		VoloManager voloManager=new VoloManager();
		
		String aeroportoPartenza=request.getParameter("city");
		String aeroportoArrivo=request.getParameter("cityArrivals");
		String numPasseggeri=request.getParameter("seats");
		String dateDeparture=request.getParameter("dateDeparture");
		String dateReturn=request.getParameter("dateReturn");
		String checkbox=request.getParameter("Diretto");

		
		
		String redirect = null;
		
		Aeroporto aeroportoP=null;//partenza
		Aeroporto aeroportoA=null;//arrivo
		String codAeroportoP = "";
		String codAeroportoA ="";

		int indTrattinoP = aeroportoPartenza.indexOf("-");//cerco trattino e virgola per ottenere citta e stato
		int indTrattinoA = aeroportoArrivo.indexOf("-");
		int indVirgolaP = aeroportoPartenza.indexOf(",");
		int indVirgolaA = aeroportoArrivo.indexOf(",");
		
		if(aeroportoPartenza.length()>=3 && aeroportoArrivo.length()>=3) {
			 codAeroportoP = aeroportoPartenza.substring(0,3);
			 codAeroportoA = aeroportoArrivo.substring(0,3);
			 
				if(indTrattinoP != -1 && indVirgolaP != -1) { //se virgola e trattino non ci sono mi viene restituito -1
					cityP = aeroportoPartenza.substring(indTrattinoP+2, indVirgolaP);
					statoP = aeroportoPartenza.substring(indVirgolaP+2, aeroportoPartenza.length());
				}
				
				if(indTrattinoA != -1 && indVirgolaA != -1) {
					cityA = aeroportoArrivo.substring(indTrattinoA+2, indVirgolaA);
					statoA = aeroportoArrivo.substring(indVirgolaA+2,aeroportoArrivo.length());
				}

			}
		
		if (!valida(aeroportoPartenza,aeroportoArrivo,dateDeparture,dateReturn)) {
			message="Formato campi non valido";
			response.getWriter().write("Formato errato dati");
			request.setAttribute("message", message);
			redirect="index.jsp";
		}
		
		else {
			 try {
				aeroportoP = aeroportoManager.findAeroportoById(codAeroportoP);
				aeroportoA = aeroportoManager.findAeroportoById(codAeroportoA);
				
				String success=null;
				success=controlloAeroporti(aeroportoP, aeroportoA);
			
				if(!success.equals("Success")) {
					response.getWriter().write("Aeroporto non esistente");
					request.setAttribute("message",success);
					redirect = "/index.jsp";
				} 	else {
			
					  LocalDate dataDepartureLd = LocalDate.parse(dateDeparture, FORMATO_DIA);

					  //volo di andata e ritorno diretti
					  ArrayList<Volo> voliAndataDiretti=voloManager.cercaDiretti(aeroportoP.getCodice(), aeroportoA.getCodice(),
							  dataDepartureLd ,Integer.parseInt(numPasseggeri),0,0);
					  request.setAttribute("voliAndataDiretti", voliAndataDiretti);
					  
					  if(checkbox==null) {
					  ArrayList<Volo[]> voliAndataUnoScalo=voloManager.cercaUnoScalo(aeroportoP.getCodice(), aeroportoA.getCodice(),
							  dataDepartureLd, Integer.parseInt(numPasseggeri),0,0);
					  
					  
					  ArrayList<Volo[]> voliAndataDueScali=voloManager.cercaDueScali(aeroportoP.getCodice(), aeroportoA.getCodice(),
							  dataDepartureLd, Integer.parseInt(numPasseggeri),0,0);
					  
					  
					  request.setAttribute("voliAndataUno", voliAndataUnoScalo);
					 
					  
					  request.setAttribute("voliAndataDue", voliAndataDueScali);
					  }
					  
					 
						if(dateReturn!=null) {
							  LocalDate dataReturnLd = LocalDate.parse(dateReturn, FORMATO_DIA);
							  
							  ArrayList<Volo> voliRitornoDiretti=voloManager.cercaDiretti(aeroportoA.getCodice(), aeroportoP.getCodice(),
									  dataReturnLd,Integer.parseInt(numPasseggeri),0,0);
							  
							if(checkbox==null) {
							  ArrayList<Volo[]> voliRitornoUnoScalo=voloManager.cercaUnoScalo(aeroportoA.getCodice(), aeroportoP.getCodice(),
									  dataReturnLd, Integer.parseInt(numPasseggeri),0,0);
						
							ArrayList<Volo[]> voliRitornoDueScali=voloManager.cercaDueScali(aeroportoA.getCodice(), aeroportoP.getCodice(),
									  dataReturnLd, Integer.parseInt(numPasseggeri), 0,0);
							
							 request.setAttribute("voliRitornoUno", voliRitornoUnoScalo);
							  request.setAttribute("voliRitornoDue", voliRitornoDueScali);
							
							}
							
							request.setAttribute("voliRitornoDiretti", voliRitornoDiretti);
							
							  
						}
		
					  
					  response.getWriter().write("Success");
					  request.setAttribute("city",aeroportoP.getCodice());
					  request.setAttribute("cityArrivals", aeroportoA.getCodice());
					  request.getSession().setAttribute("seats", numPasseggeri);
					  request.setAttribute("dateDeparture", dateDeparture);
					  request.setAttribute("dateReturn", dateReturn);
					  request.setAttribute("Diretto", checkbox);
					  redirect = "/risultatiRicerca.jsp";
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
					
		 RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(redirect);
	     dispatcher.forward(request, response);
	}

}
