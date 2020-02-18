package gestioneordine;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionevolo.Volo;
import gestionevolo.VoloManager;

/**
 * La servlet gestisce tutte le operazioni per la cancellazione di un ordine
 * richiesto da un utente correntemente loggato al sistema
 */
@WebServlet(name="/AnnullaOrdineServlet", urlPatterns= {"/cliente/AnnullaOrdineServlet"})
public class AnnullaOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOrdine=request.getParameter("idOrdine");
		
		OrdineManager ordineManager=new OrdineManager();
		BigliettoManager bigliettoManager=new BigliettoManager();
		VoloManager voloManager=new VoloManager();
		
		try {
			LocalDateTime oggi=LocalDateTime.now();
			
			LocalDateTime tempoCheManca = LocalDateTime.from(oggi);
			
			LocalDateTime dataPartenza=LocalDateTime.of(bigliettoManager.trovaBigliettiOrdine(Integer.parseInt(idOrdine)).get(0).getVolo().getDataPartenza(), bigliettoManager.trovaBigliettiOrdine(Integer.parseInt(idOrdine)).get(0).getVolo().getOrarioPartenza());
				
			long giorniCheMancano = tempoCheManca.until(dataPartenza , ChronoUnit.DAYS);
			System.out.println("Mancano "+giorniCheMancano+" giorni");
			
			if(giorniCheMancano>7) {
				ArrayList<Biglietto> biglietti=bigliettoManager.trovaBigliettiOrdine(Integer.parseInt(idOrdine));
				for(Biglietto biglietto: biglietti) {
					Volo volo=biglietto.getVolo();
					
					volo.setSeats(volo.getSeats()+1);
					
					voloManager.modificaVolo(volo);
					
				}
				ordineManager.annullaOrdine(Integer.parseInt(idOrdine));
				
				request.setAttribute("messageOrdine", "Ordine annullato con successo");
			} else {
				request.setAttribute("messageOrdine", "Non e' possibile annullare l'ordine perch� mancano meno di 7 giorni alla partenza");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
