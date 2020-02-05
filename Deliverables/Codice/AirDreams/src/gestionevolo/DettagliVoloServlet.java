package gestionevolo;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DettagliVoloServlet
 */
@WebServlet(name = "dettagliVoloServlet", urlPatterns = {"/gestoreVoli/dettagliVoloServlet"})
public class DettagliVoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    VoloManager vManager = new VoloManager();
	    String idVolo=request.getParameter("idVolo");
	    Volo volo = null;
	    
		try {
		volo = vManager.findByID(idVolo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(volo);
		
		if(volo == null) {
			response.sendRedirect("../errorPage.jsp");
		} else {
			request.setAttribute("volo", volo);
			request.getServletContext().getRequestDispatcher("/gestoreVoli/dettagliVolo.jsp").forward(request,response);
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
