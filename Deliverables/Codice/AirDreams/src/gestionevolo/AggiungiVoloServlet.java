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

@WebServlet("/AggiungiVoloServlet")
public class AggiungiVoloServlet extends HttpServlet {
	private static Logger logger= Logger.getLogger("global");
	private String expAeroporto="^[A-Z]{3} - [A-Za-z  ]{1,}, [A-Za-z ]{1,}$";
	
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String city=request.getParameter("city");
			
			
			if(valida(city)) {
				System.out.println("Formato valido");
			
			}
			
			else{
				System.out.println("Formato non valido");
				
			}
		}
		

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
		
		private boolean valida(String aeroporto) {
			boolean valido=true;
			logger.info("Ho ricevuto "+aeroporto);
			if (!Pattern.matches(expAeroporto, aeroporto)) {
				logger.info("Aeroporto non corrisponde");
				valido=false;
				System.out.print(aeroporto);
			}
			return valido;
			
		}
		
	}

