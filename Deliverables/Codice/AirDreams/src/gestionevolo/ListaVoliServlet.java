package gestionevolo;

import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestioneutente.Account;

/**
 * Servlet implementation class RicercaVoliServlet
 */
@WebServlet(name = "ListaVoliServlet", urlPatterns = {"/gestoreVoli/ListaVoliServlet"})
public class ListaVoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String expAeroporto="^[A-Z]{3} - [A-Za-z  ]{1,}, [A-Za-z ]{1,}$";
	private String expData= "^\\s*(0?[1-9]|1[0-9]|2[2-9]|3[01])\\/\\s*(1[012]|0?[1-9])\\/\\d{4}\\s*$";


	private String[] ricercaSalvata= {"","",""};
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VoloManager voloManager=new VoloManager();
		
		String action=request.getParameter("action");
		int pagina=Integer.parseInt(request.getParameter("page"));
		Account utente= (Account) request.getSession().getAttribute("account");
		String redirect = null;
		
		if (action.equals("null")) {
			try {
				//recupero tutti i voli dal DB
				List<Volo> voli=voloManager.cercaVoli(utente.getCompagniaAerea().getNome());
				
				request.setAttribute("voli",voli);
				request.setAttribute("page",pagina);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			redirect="/gestoreVoli/listaVoli.jsp";
		} else if (action.equals("ricerca")) {
			
			String aeroportoP=request.getParameter("city");
			System.out.println("Partenza "+aeroportoP);
			String aeroportoA=request.getParameter("cityArrivals");
			System.out.println("Arrivo "+aeroportoA);
			String data=request.getParameter("data");
			System.out.println("Data "+data);
			
			String codAeroportoP =null;
			String codAeroportoA =null;
			
			List<Volo> voli=new ArrayList<Volo>();
			String[] ricerca=new String[3];
			
			if (!valida(aeroportoP,aeroportoA,data)) {
					System.out.println("Formato non valido");
					response.getWriter().write("Failed");
					request.setAttribute("page",pagina);
				
					request.setAttribute("voli",voli);
					request.setAttribute("message","Formato non valido");
					redirect = "/gestoreVoli/listaVoli.jsp";
			} else {
				try {
					if(pagina==1) {
						if(aeroportoP.length()>=3)
							codAeroportoP = aeroportoP.substring(0,3);
						if(aeroportoA.length()>=3)
							codAeroportoA= aeroportoA.substring(0,3);
							
						ricerca[0]="";
						ricerca[1]="";
						ricerca[2]="";
						
						if (codAeroportoP!=null && codAeroportoP.length()!=0) {
							ricerca[0]=codAeroportoP;
							ricercaSalvata[0]=codAeroportoP;
						}
						if (codAeroportoA!=null && codAeroportoA.length()!=0) {
							ricerca[1]=codAeroportoA;
							ricercaSalvata[1]=codAeroportoA;
						}
						if (data!=null && data.length()!=0) {
							ricerca[2]=data;
							ricercaSalvata[2]=data;
						}
						
						System.out.println("Utente è: "+utente);
						System.out.println("Compagnia è: "+utente.getCompagniaAerea());
						String c=utente.getCompagniaAerea().getNome();
						System.out.println("compagnia aerea di cui il gestore e' tenuto: " + c);
						voli=voloManager.cercaVoli(ricerca,c);
						
							 
					}  else {

						ricerca[0]=ricercaSalvata[0];
						ricerca[1]=ricercaSalvata[1];
						ricerca[2]=ricercaSalvata[2];
								
						String c=utente.getCompagniaAerea().getNome();
						System.out.println("compagnia aerea di cui il gestore e' tenuto: " + c);
						voli=voloManager.cercaVoli(ricerca,c);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				response.getWriter().write("Success");
				request.setAttribute("voli", voli);
				request.setAttribute("page",pagina);
				redirect="/gestoreVoli/listaVoli.jsp";
			
			}				
		}	
		RequestDispatcher dispatcher = request.getRequestDispatcher(redirect);
	    dispatcher.forward(request, response);
		
	}
			
	private boolean valida(String aeroportoP, String aeroportoA, String dateDeparture) {
		boolean valido=true;
		
		if(aeroportoP!=null && !aeroportoP.equals("")) {
		if (!Pattern.matches(expAeroporto, aeroportoP)) {
			valido=false;
			System.out.println("aeroporto partenza non corrisponde");
			}
		}
		
		if(aeroportoA!=null && !aeroportoA.equals(""))  {
		if (!Pattern.matches(expAeroporto, aeroportoA)) {
			valido=false;
			System.out.println("aeroporto arrivo non corrisponde");
			}
		}
		
		
		if(dateDeparture!=null && !dateDeparture.equals("")) {
		if (!Pattern.matches(expData, dateDeparture)) {
			valido=false;
			System.out.println("dataPartenza non corrisponde");
			}
		}

		return valido;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}