package gestioneordine;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionecarrello.CarrelloManager;
import gestioneutente.Account;
import gestioneutente.CartaDiCredito;
import gestioneutente.CartaDiCreditoManager;

/**
 * Servlet implementation class PagamentoServlet
 */

@WebServlet(name="/PagamentoServlet", urlPatterns= {"/cliente/PagamentoServlet"})
public class PagamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagamentoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Biglietto> biglietti=(ArrayList<Biglietto>)request.getSession().getAttribute("biglietti");
	
		Account account=(Account)request.getSession().getAttribute("account");
		
		String numeroCarta=request.getParameter("carta");
		CartaDiCreditoManager cartaDiCreditoManager=new CartaDiCreditoManager();
	
		OrdineManager ordineManager=new OrdineManager();
		BigliettoManager bigliettoManager=new BigliettoManager();
		BagaglioManager bagaglioManager=new BagaglioManager();

		CartaDiCredito carta=null;
		
		try {
			
			if(numeroCarta!=null) {
			carta=cartaDiCreditoManager.cercaCarta(numeroCarta, account.getEmail());
			} else {
				String nCarta=request.getParameter("nCarta");
				String titolare=request.getParameter("titolare");
				String dataScadenza=request.getParameter("dataScadenza");
				String cvc=request.getParameter("cvc");
				
				carta=new CartaDiCredito(nCarta, titolare, dataScadenza, Integer.parseInt(cvc));
				
				System.out.println("Ho creato una nuova carta");
				
			}
			Ordine ordine=new Ordine(LocalDate.now(), account, carta);
			ordine=ordineManager.aggiungiOrdine(ordine);
		
			for(Biglietto biglietto: biglietti) {
				//creo prima il biglietto
				biglietto.setOrdine(ordine.getCodOrdine());
				biglietto=bigliettoManager.aggiungiBiglietto(biglietto);
				
				
					System.out.println("Bagaglio a mano di biglietto "+biglietto.getCodBiglietto()+" per il volo "
							+ ""+biglietto.getVolo().getId());
			
					bagaglioManager.aggiungiBagaglioMano(biglietto.getBagaglioMano());
				
				
					Iterator<BagaglioStiva> iterator=biglietto.getBagagliStiva().iterator();
					
					while(iterator.hasNext()) {
						BagaglioStiva bagaglioStiva=iterator.next();
						
						bagaglioManager.aggiungiBagaglioStiva(bagaglioStiva);
					}
	
				
			}
			
			CarrelloManager carrelloManager=new CarrelloManager();
			carrelloManager.svuotaCarrello(account.getEmail());
			
			request.getServletContext().getRequestDispatcher("/cliente/acquisto.jsp").forward(request, response);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
