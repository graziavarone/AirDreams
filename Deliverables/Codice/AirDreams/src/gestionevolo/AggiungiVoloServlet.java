package gestionevolo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestioneutente.Account;
import gestioneutente.UtenteManager;

@WebServlet("/AggiungiVoloServlet")
public class AggiungiVoloServlet extends HttpServlet {
	private static Logger logger= Logger.getLogger("global");
	private String expAeroporto="^[A-Z]{3} - [A-Za-z  ]{1,}, [A-Za-z ]{1,}$";
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			AeroportoManager manager=new AeroportoManager();
			String city=request.getParameter("city");
			String cityArrivals=request.getParameter("cityArrivals");
			String codAeroportoP = city.substring(0,3);
			String codAeroportoA = cityArrivals.substring(0,3);
			int codiceP = codAeroportoP.length();
			int codiceA = codAeroportoA.length();

			
			if(valida(city, cityArrivals)) {
				System.out.println("Formato valido");
				
				try {
					Aeroporto aeroporto = manager.findAeroportoById(codAeroportoP);
					System.out.println("L'aeroporto restituito è:"+ aeroporto);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else{
				System.out.println("Formato non valido");
			}
			
			System.out.println(codAeroportoP);
			System.out.println(+codiceP);
			System.out.println(codAeroportoA);
			System.out.println(+codiceA);
		
			int indTrattinoP = city.indexOf("-");
			int indTrattinoA = cityArrivals.indexOf("-");
			int indVirgolaP = city.indexOf(",");
			int indVirgolaA = cityArrivals.indexOf(",");
			
			String cityP = city.substring(indTrattinoP+2, indVirgolaP);
			String cityA = cityArrivals.substring(indTrattinoA+2, indVirgolaA);
			
			System.out.println(cityP+ "" +cityP.length());
			System.out.println(cityA+ "" +cityA.length());
			
			String statoP = city.substring(indVirgolaP+2, city.length());
			String statoA = cityArrivals.substring(indVirgolaP+2, cityArrivals.length());

			System.out.println(statoP+ "" +statoP.length());
			System.out.println(statoA+ "" +statoA.length());
			
			
		}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
		
		private boolean valida(String aeroportoP, String aeroportoA) {
			boolean valido=true;
			if (!Pattern.matches(expAeroporto, aeroportoP)) {
				valido=false;
				System.out.println("aeroporto partenza non corrisponde");
	
			}
			if (!Pattern.matches(expAeroporto, aeroportoA)) {
				valido=false;
				System.out.println("aeroporto arrivo non corrisponde");
			}
			return valido;
			
		}
		
		


	}

