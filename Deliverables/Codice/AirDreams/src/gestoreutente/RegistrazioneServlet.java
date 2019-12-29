package gestoreutente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//controllare che i parametri siano diversi da null
		//controllare che non ci sia già un'email, fare query select * from account where email=..., se
		//ritorna null allora possiamo registrare, altrimenti no perchè l'email esiste già
		
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String cPassword=request.getParameter("CPassword");
		
		UtenteManager utenteManager=new UtenteManager();
		Account account=null;
		String redirect="";
		boolean risultato=false;
		
		
		account=new Account(nome, cognome, email, password);
		risultato=utenteManager.signUp(account);
		
		if (!risultato) {
			request.setAttribute("message", "Failed");
			redirect="registrazione.jsp";
		}
		else {
			redirect="login.jsp";
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
		requestDispatcher.forward(request, response);
	}

}
