package gestioneutente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestioneordine.BagaglioMano;
import gestioneordine.BagaglioStiva;
import gestioneordine.Biglietto;
import gestioneordine.BigliettoManager;
import gestioneordine.Ordine;
import gestioneordine.OrdineManager;

/**
 * Servlet implementation class DettagliAccountServlet
 */
@WebServlet(name="/DettagliAccountServlet",
urlPatterns= {"/cliente/DettagliAccountServlet"})
public class DettagliAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//prelevo l'utente dalla sessione
		Account account=(Account) request.getSession().getAttribute("account");
		String redirect=null;
		
		CartaDiCreditoManager cm=new CartaDiCreditoManager();
		  OrdineManager ordineManager= new OrdineManager();
		  BigliettoManager bigliettoManager=new BigliettoManager();
		 
		try {
			ArrayList<CartaDiCredito> carteUtente=cm.findAll(account.getEmail());
			ArrayList<Ordine> ordini=	ordineManager.cercaOrdiniUtente(account.getEmail());
			
			for(Ordine ordine: ordini) {
			ArrayList<Biglietto> bigliettiOrdine=bigliettoManager.trovaBigliettiOrdine(ordine.getCodOrdine());
			
			
			request.setAttribute("bigliettiOrdine"+ordine.getCodOrdine(), bigliettiOrdine);
			
			}
			request.setAttribute("carte", carteUtente);
			request.setAttribute("ordini", ordini);
			 redirect="/cliente/profilo.jsp";
			
			if (request.getSession().getAttribute("biglietti")!=null) {
				ArrayList<Biglietto> biglietti=(ArrayList<Biglietto>)request.getSession().getAttribute("biglietti");
	
				
				request.getSession().setAttribute("biglietti",biglietti);
				
				 redirect="/cliente/pagamento.jsp";
			}
			else {
			 redirect="/cliente/profilo.jsp";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//da implementare successivamente quando si implementeranno le funzionalità ordine e carta di credito
	
		request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
