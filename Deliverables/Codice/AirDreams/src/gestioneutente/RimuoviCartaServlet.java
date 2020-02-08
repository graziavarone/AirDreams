package gestioneutente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RimuoviCartaServlet
 */
@WebServlet(name="/RimuoviCartaServlet",
urlPatterns= {"/cliente/RimuoviCartaServlet"})
public class RimuoviCartaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimuoviCartaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account account=(Account) request.getSession().getAttribute("account");
		
		CartaDiCreditoManager cartaDiCreditoManager=new CartaDiCreditoManager();
		String nCarta=request.getParameter("nCarta");
		cartaDiCreditoManager.eliminaCarta(nCarta,account.getEmail());
		
		request.setAttribute("message", "Carta di credito eliminata");
		try {
			request.setAttribute("carte", cartaDiCreditoManager.findAll(account.getEmail()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getServletContext().getRequestDispatcher("/cliente/profilo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}