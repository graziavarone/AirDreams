package gestionevolo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

/**
 * La servlet gestisce tutte le operazioni per la ricerca AJAX di aeroporti nel sistema
 */
@WebServlet(name = "RicercaAeroportiServlet", urlPatterns = {"/RicercaAeroportiServlet","/gestoreVoli/RicercaAeroportiServlet"})
public class RicercaAeroportiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	AeroportoManager model = null;

		model = new AeroportoManager();			
		
		JSONArray prodJson = new JSONArray();
		String query = request.getParameter("city");
		String query1 = request.getParameter("cityArrivals");
		
		if (query != null) {
			List<Aeroporto> aeroporti;
			aeroporti = model.getAeroportiByCity(query);
			
			for (Aeroporto a : aeroporti) {
				prodJson.put(a.getCodice()+ " - " +a.getCity()+", " +a.getStato());
			}
		}
		
		if (query1 != null) {
			List<Aeroporto> aeroporti;
			aeroporti = model.getAeroportiByCity(query1);
			
			for (Aeroporto a : aeroporti) {
				prodJson.put(a.getCodice()+ " - " +a.getCity()+", " +a.getStato());
				
			}
		}
		response.setContentType("application/json");
		response.getWriter().append(prodJson.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}