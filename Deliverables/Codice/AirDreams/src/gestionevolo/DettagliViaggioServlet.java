package gestionevolo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DettagliViaggioServlet
 */
@WebServlet("/DettagliViaggioServlet")
public class DettagliViaggioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettagliViaggioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idPrimoVolo=request.getParameter("primoVolo");
		String idSecondoVolo=request.getParameter("secondoVolo");
		String idTerzoVolo=request.getParameter("terzoVolo");
		String idQuartoVolo=request.getParameter("quartoVolo");
		String idQuintoVolo=request.getParameter("quintoVolo");
		String idSestoVolo=request.getParameter("sestoVolo");
		
		VoloManager voloManager=new VoloManager();
		ArrayList<Volo> voliViaggio=new ArrayList<Volo>();
		
		if(idPrimoVolo!=null){
			try {
				Volo primoVolo=voloManager.findByID(idPrimoVolo);
				
				if(primoVolo!=null)
					voliViaggio.add(primoVolo);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(idSecondoVolo!=null){
			try {
				Volo secondoVolo=voloManager.findByID(idSecondoVolo);
				
				if(secondoVolo!=null)
					voliViaggio.add(secondoVolo);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(idTerzoVolo!=null){
			try {
				Volo terzoVolo=voloManager.findByID(idTerzoVolo);
				
				if(terzoVolo!=null)
					voliViaggio.add(terzoVolo);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(idQuartoVolo!=null){
			try {
				Volo quartoVolo=voloManager.findByID(idQuartoVolo);
				
				if(quartoVolo!=null)
					voliViaggio.add(quartoVolo);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(idQuintoVolo!=null){
			try {
				Volo quintoVolo=voloManager.findByID(idQuintoVolo);
				
				if(quintoVolo!=null)
					voliViaggio.add(quintoVolo);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(idSestoVolo!=null){
			try {
				Volo sestoVolo=voloManager.findByID(idSestoVolo);
				
				if(idSestoVolo!=null)
					voliViaggio.add(sestoVolo);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.getSession().setAttribute("voli", voliViaggio);
		
		request.getServletContext().getRequestDispatcher("/dettagliViaggio.jsp").forward(request, response);
		

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
