package gestoreutente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

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
	private static Logger logger= Logger.getLogger("global");
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
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		//String cPassword=request.getParameter("CPassword");
		
		UtenteManager utenteManager=new UtenteManager();
		Account account=null;
		String redirect="";
		boolean risultato=false;
		
		try {
			account=utenteManager.findAccountByEmail(email);
			
			logger.info("Sono uscito da findAccountByEmail..");
			if(account!=null) {
				logger.info("Esiste già un account..");
				request.setAttribute("message", "Email already exists");
				redirect="registrazione.jsp";
			}
			else {
				logger.info("Non esiste un account..");
				
				account=new Account(nome, cognome, email, password);
				risultato=utenteManager.signUp(account);
				logger.info("Sono uscito da signUp..");
				if (!risultato) {
					request.setAttribute("message", "Signup failed");
					redirect="registrazione.jsp";
				}
				else {
					request.setAttribute("message", "Signup success");
					redirect="loginFauzzo.jsp";
				}
			}
		}
			
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
		requestDispatcher.forward(request, response);
	}

}
