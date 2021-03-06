package gestionevolo;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet gestisce le operazioni per la cancellazione di un volo dal catalogo di sistema,
 * operazione eseguibile solo dal gestore voli
 */
@WebServlet(name = "EliminaVoloServlet", urlPatterns = {"/gestoreVoli/EliminaVoloServlet"})
public class EliminaVoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idVolo = request.getParameter("idVolo");
		VoloManager vManager = new VoloManager();
		
		try {
			if(vManager.eliminaVolo(Integer.parseInt(idVolo))) {
				request.setAttribute("message", "il volo e' stato eliminato");
				
				request.getServletContext().getRequestDispatcher("/gestoreVoli/ListaVoliServlet?page=1&action=null").forward(request, response);
			}
		} catch (NumberFormatException | SQLException e) {
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
