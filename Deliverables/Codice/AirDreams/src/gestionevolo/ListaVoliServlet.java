package gestionevolo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "ListaVoliServlet", urlPatterns = {"/gestoreVoli/ListaVoliServlet"})
public class ListaVoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String expAeroporto="^[A-Z]{3} - [A-Za-z  ]{1,}, [A-Za-z ]{1,}$";
	private String expData= "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$";
	private String message = null; //messaggio in caso di successo/errore
	private String[] ricercaSalvata= {"","",""};
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		VoloManager voloManager=new VoloManager();
		
		String action=request.getParameter("action");
		int pagina=Integer.parseInt(request.getParameter("page"));
		
		String redirect = null;
		
		if (action.equals("null")) {
			try {
				//recupero tutti i voli dal DB
				List<Volo> voli=voloManager.cercaVoli();
				
				request.setAttribute("voli",voli);
				request.setAttribute("page",pagina);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			redirect="listaVoli.jsp";
		} else if (action.equals("ricerca")) {
			
			String aeroportoP=request.getParameter("city");
			String aeroportoA=request.getParameter("cityArrivals");
			String data=request.getParameter("data");
			
			String codAeroportoP ="";
			String codAeroportoA ="";
			List<Volo> voli=null;
			String[] ricerca=new String[3];
			
			boolean valida=true;
			
			if (pagina==1) { //recuperiamo i dati appena inseriti nel form
				ricercaSalvata=new String[3];
				ricercaSalvata[0]="";
				ricercaSalvata[1]="";
				ricercaSalvata[2]="";
				
				if (valida==true) {
					
					if(aeroportoP.length()>=3)
						codAeroportoP = aeroportoP.substring(0,3);
					if(aeroportoA.length()>=3)
						codAeroportoA= aeroportoA.substring(0,3);
					
					try {
						ricerca[0]="";
						ricerca[1]="";
						ricerca[2]="";
				
						if (codAeroportoP.length()!=0) {
							ricerca[0]=codAeroportoP;
							ricercaSalvata[0]=codAeroportoP;
						}
						if (codAeroportoA.length()!=0) {
							ricerca[1]=codAeroportoA;
							ricercaSalvata[1]=codAeroportoA;
						}
						if (data.length()!=0) {
							ricerca[2]=data;
							ricercaSalvata[2]=data;
						}
				
						voli=voloManager.cercaVoli(ricerca);
					} catch (SQLException e) {
						e.printStackTrace();
					} 
				}
			} else {
				//recupero i dati della precedente richiesta
				ricerca[0]=ricercaSalvata[0];
				ricerca[1]=ricercaSalvata[1];
				ricerca[2]=ricercaSalvata[2];
					
				try {
					voli=voloManager.cercaVoli(ricerca);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			request.setAttribute("voli",voli);
			request.setAttribute("page",pagina);
			redirect="listaVoli.jsp";
		}
			
		RequestDispatcher dispatcher = request.getRequestDispatcher(redirect);
	    dispatcher.forward(request, response);
	}

	private boolean validaAeroporto(String aeroporto) {
		boolean valido=true;
		
		if (!Pattern.matches(expAeroporto, aeroporto)) {
			valido=false;
			System.out.println("aeroporto non corrisponde");
		}
		
		return valido;
	}
	
	private boolean validaData(String dateDeparture) {
		boolean valido=true;
		
		if (!Pattern.matches(expData, dateDeparture)) {
			valido=false;
			System.out.println("dataPartenza non corrisponde");
		}
		
		return valido;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}