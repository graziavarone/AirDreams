package gestioneordine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionecarrello.Carrello;
import gestionecarrello.CarrelloManager;
import gestioneutente.Account;
import gestionevolo.Volo;

/**
 * Servlet implementation class NominativiBigliettiServlet
 */

@WebServlet(name="/BigliettiServlet", urlPatterns= {"/cliente/BigliettiServlet"})
public class BigliettiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BigliettiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Carrello carrello=(Carrello)request.getSession().getAttribute("carrello");
		HashMap<Volo,Integer> voliCarrello=carrello.getVoli();
		
		int seats=0;
		for(Entry<Volo, Integer> entry : voliCarrello.entrySet()) 
	   		 seats=entry.getValue();
		
		for(int i=0;i<seats;i++) {
			String nomePasseggero=request.getParameter("nomePasseggero"+(i+1));
			String cognomePasseggero=request.getParameter("cognomePasseggero"+(i+1));
			String sesso=request.getParameter("sesso"+(i+1));
			String nBagagli=request.getParameter("bagaglioStiva"+(i+1));
			
			System.out.println(
					"Passeggero n. "+(i+1)+": "
							+ "Nome "+nomePasseggero+" cognome "+cognomePasseggero+" sesso "+sesso+"  Bagagli "+nBagagli
					
					);
			
		
			for(Entry<Volo, Integer> entry : voliCarrello.entrySet()) {
				Biglietto biglietto=new Biglietto(nomePasseggero, cognomePasseggero, Sesso.valueOf(sesso), entry.getKey().getPrezzo());
				
				System.out.println("Sto creando il biglietto "+biglietto+"per il passseggero"+(i+1));
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
