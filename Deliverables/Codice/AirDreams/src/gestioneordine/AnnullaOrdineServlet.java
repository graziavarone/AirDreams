package gestioneordine;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AnnullaOrdineServlet
 */

@WebServlet(name="/AnnullaOrdineServlet", urlPatterns= {"/cliente/AnnullaOrdineServlet"})
public class AnnullaOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnnullaOrdineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOrdine=request.getParameter("idOrdine");
		
		OrdineManager ordineManager=new OrdineManager();
		BigliettoManager bigliettoManager=new BigliettoManager();
		
		try {
			LocalDateTime oggi=LocalDateTime.now();
			
			LocalDateTime tempoCheManca = LocalDateTime.from(oggi);
			
		
			
			LocalDateTime dataPartenza=LocalDateTime.of(bigliettoManager.trovaBigliettiOrdine(Integer.parseInt(idOrdine)).get(0).getVolo().getDataPartenza(), 
					bigliettoManager.trovaBigliettiOrdine(Integer.parseInt(idOrdine)).get(0).getVolo().getOrarioPartenza());
				
			long giorniCheMancano = tempoCheManca.until(dataPartenza , ChronoUnit.DAYS);
			System.out.println("Mancano "+giorniCheMancano+" giorni");
			
			if(giorniCheMancano>7) {
				ordineManager.annullaOrdine(Integer.parseInt(idOrdine));
				
				request.setAttribute("messageOrdine", "Ordine annullato con successo");
			} else {
				request.setAttribute("messageOrdine", "Non � possibile annullare l'ordine perch� mancano meno di 7 giorni alla partenza");
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