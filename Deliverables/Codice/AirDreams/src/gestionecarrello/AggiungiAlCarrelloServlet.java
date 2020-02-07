package gestionecarrello;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestioneutente.Account;
import gestionevolo.Volo;

/**
 * Servlet implementation class AggiungiAlCarrelloServlet
 */
@WebServlet(name="/AggiungiAlCarrelloServlet", urlPatterns= {"/cliente/AggiungiAlCarrelloServlet"})
public class AggiungiAlCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiAlCarrelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account account=(Account)request.getSession().getAttribute("account");
		ArrayList<Volo> voli=(ArrayList<Volo>)request.getSession().getAttribute("voli");
		String seats=(String)request.getSession().getAttribute("seats");
		
		CarrelloManager carrelloManager=new CarrelloManager();
		
		for(Volo volo: voli) {
			try {
				
				Volo voloPresente=carrelloManager.cercaVoloNelCarrello(account.getEmail(), volo.getId());
				
				if(voloPresente==null) {
					System.out.println("Non presnete nel carrello");
					carrelloManager.aggiungiVoloAlCarrello(account.getEmail(),volo.getId(),Integer.parseInt(seats));
				} else {
					System.out.println("Già presnete nel carrello");
					carrelloManager.updateQuantity(account.getEmail(), volo.getId(), Integer.parseInt(seats));
				}
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.getServletContext().getRequestDispatcher("/cliente/CarrelloServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
