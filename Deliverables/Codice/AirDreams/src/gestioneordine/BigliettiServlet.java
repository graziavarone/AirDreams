package gestioneordine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionecarrello.Carrello;
import gestionecarrello.CarrelloManager;
import gestionecompagniaaerea.PoliticaBagaglioManager;
import gestionecompagniaaerea.PoliticaBagaglioMano;
import gestionecompagniaaerea.PoliticaBagaglioStiva;
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
		ArrayList<Biglietto> biglietti=new ArrayList<Biglietto>();
		ArrayList<BagaglioMano> bagagliMano=new ArrayList<BagaglioMano>();
		ArrayList<BagaglioStiva> bagagliStiva=new ArrayList<BagaglioStiva>();
		PoliticaBagaglioManager politicaBagaglioManager=new PoliticaBagaglioManager();
		
		int seats=0;
		for(Entry<Volo, Integer> entry : voliCarrello.entrySet()) 
	   		 seats=entry.getValue();
		
		for(int i=0;i<seats;i++) {
			String nomePasseggero=request.getParameter("nomePasseggero"+(i+1));
			String cognomePasseggero=request.getParameter("cognomePasseggero"+(i+1));
			String sesso=request.getParameter("sesso"+(i+1));
			String nBagagli=request.getParameter("bagaglioStiva"+(i+1));
			int numeroBagagli=Integer.parseInt(nBagagli);
	
			for(Entry<Volo, Integer> entry : voliCarrello.entrySet()) {
				
				try {
				Biglietto biglietto=new Biglietto(nomePasseggero, cognomePasseggero, Sesso.valueOf(sesso), entry.getKey().getPrezzo(),
						entry.getKey());
				//biglietti.add(biglietto);
				
				PoliticaBagaglioMano politicaBagaglioMano=politicaBagaglioManager.trovaPoliticaCompagniaMano(entry.getKey().getCa().getNome());
				BagaglioMano bagaglioMano=new BagaglioMano(politicaBagaglioMano.getPeso(), politicaBagaglioMano.getDimensioni(), biglietto 
						);
				bagagliMano.add(bagaglioMano);
				biglietto.setBagaglioMano(bagaglioMano);
	
				
				if(numeroBagagli!=0) {
					
					
					PoliticaBagaglioStiva politicaBagaglioStiva=politicaBagaglioManager.trovaPoliticaCompagniaStiva(entry.getKey().getCa().getNome());
					BagaglioStiva bagaglioStiva=new BagaglioStiva(politicaBagaglioStiva.getPeso(), politicaBagaglioStiva.getDimensioni(), biglietto, 
							politicaBagaglioStiva.getPrezzo(),numeroBagagli);
					
					HashSet<BagaglioStiva> bagagli=new HashSet<BagaglioStiva>();
					
					for(int j=0;j<numeroBagagli;j++) {
						bagagli.add(bagaglioStiva);
					}
						
					biglietto.setBagagliStiva(bagagli);
					
					if(entry.getKey().isCompreso() && numeroBagagli==1) {
						biglietto.setPrezzoBiglietto(entry.getKey().getPrezzo());
					} else if(entry.getKey().isCompreso() && numeroBagagli>1) {
						biglietto.setPrezzoBiglietto(entry.getKey().getPrezzo()+politicaBagaglioStiva.getPrezzo());
					} else {
						System.out.println("Non ho il bagaglio compreso pago anche l'aria");
						biglietto.setPrezzoBiglietto(entry.getKey().getPrezzo()+politicaBagaglioStiva.getPrezzo());
					}
				}
				
			
				biglietti.add(biglietto);
				
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
		request.getSession().setAttribute("biglietti",biglietti);
	
		
		for(Biglietto biglietto: biglietti) {
			System.out.println("############ Mano per il biglietto di "+biglietto.getNome()+"..."+biglietto.getBagaglioMano());
		}
		
		for(Biglietto biglietto: biglietti) {
			System.out.println("############ Stiva per il biglietto di "+biglietto.getNome()+"..."+biglietto.getBagagliStiva());
		}


		
		request.getServletContext().getRequestDispatcher("/cliente/DettagliAccountServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
