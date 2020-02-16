package gestioneordine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionecarrello.Carrello;
import gestionecompagniaaerea.PoliticaBagaglioManager;
import gestionecompagniaaerea.PoliticaBagaglioMano;
import gestionecompagniaaerea.PoliticaBagaglioStiva;
import gestionevolo.Volo;

/**
 * La servlet gestisce tutte le operazioni per la creazione di biglietti per i voli
 * presenti nel carrello di un utente correntemente loggato al sistem
 */
@WebServlet(name="/BigliettiServlet", urlPatterns= {"/cliente/BigliettiServlet"})
public class BigliettiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger= Logger.getLogger("global");
	private String expNome="^[A-Za-z]{1,}$";
	private String expCognome="^[A-Za-z]{1,}$";
	int seats = 0;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Carrello carrello=(Carrello)request.getSession().getAttribute("carrello");
		HashMap<Volo,Integer> voliCarrello=carrello.getVoli();
		ArrayList<Biglietto> biglietti=new ArrayList<Biglietto>();
		ArrayList<BagaglioMano> bagagliMano=new ArrayList<BagaglioMano>();
		PoliticaBagaglioManager politicaBagaglioManager=new PoliticaBagaglioManager();
		String redirect=null;

		for(Entry<Volo, Integer> entry : voliCarrello.entrySet()) 
	   		 seats=entry.getValue();
		System.out.println("seats e' "+seats);
		
		for(int i=0;i<seats;i++) {
			String nomePasseggero=request.getParameter("nomePasseggero"+(i+1));
			String cognomePasseggero=request.getParameter("cognomePasseggero"+(i+1));
			String sesso=request.getParameter("sesso"+(i+1));
			String nBagagli=request.getParameter("bagaglioStiva"+(i+1));
			int numeroBagagli=Integer.parseInt(nBagagli);
			
			if(valida(nomePasseggero, cognomePasseggero)) {
				for(Entry<Volo, Integer> entry : voliCarrello.entrySet()) {
					try {
						Biglietto biglietto=new Biglietto(nomePasseggero, cognomePasseggero, Sesso.valueOf(sesso), entry.getKey().getPrezzo(),
						entry.getKey());
				
						PoliticaBagaglioMano politicaBagaglioMano=politicaBagaglioManager.trovaPoliticaCompagniaMano(entry.getKey().getCa().getNome());
						BagaglioMano bagaglioMano=new BagaglioMano(politicaBagaglioMano.getPeso(), politicaBagaglioMano.getDimensioni(), biglietto);
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
				response.getWriter().write("Success");
				request.getSession().setAttribute("biglietti",biglietti);
				redirect="/cliente/DettagliAccountServlet";
			} else {
				response.getWriter().write("Failed");
				request.setAttribute("message", "Formato errato dati");
				redirect="/cliente/nominativiBiglietti.jsp?seats="+seats;	
			}
		}
	
		for(Biglietto biglietto: biglietti) {
			System.out.println("############ Mano per il biglietto di "+biglietto.getNome()+"..."+biglietto.getBagaglioMano());
		}
		
		for(Biglietto biglietto: biglietti) {
			System.out.println("############ Stiva per il biglietto di "+biglietto.getNome()+"..."+biglietto.getBagagliStiva());
		}
		
		request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean valida(String nome, String cognome) {
		boolean valido=true;
	
		if (!Pattern.matches(expNome, nome)) {
			logger.info("Nome non corrisponde");
			valido=false;
			System.out.print(nome);
		}
		
		if (!Pattern.matches(expCognome, cognome)) {
			logger.info("Cognome non corrisponde");
			valido=false;
			System.out.print(cognome);
		}
	
		return valido;
	}
}
