package gestioneutente;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet gestisce le operazioni di inserimento di una carta di credito
 * all'account dell'utente correntemente loggato, che potrà utilizzarla per l'acquisto di biglietti
 */
@WebServlet(name="/AggiungiCartaServlet", urlPatterns= {"/cliente/AggiungiCartaServlet"})
public class AggiungiCartaServlet extends HttpServlet {
	private static Logger logger= Logger.getLogger("global");
	private static final long serialVersionUID = 1L;
	private String redirect=null;
	private String expNCarta ="^([0-9]{4}( |\\-)){3}[0-9]{4}$";
	private String expTitolare = "^[A-Za-z' ]{1,}$";
	private String expDataScadenza = "^(0[1-9]|1[0-2]|[1-9])\\/(1[4-9]|[2-9][0-9]|20[1-9][1-9])$";
	private String expCvc = "^[0-9]{3}$";
	CartaDiCredito cardCred = new CartaDiCredito();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nCarta=request.getParameter("nCarta");
		String titolare=request.getParameter("titolare");
		String dataScadenza=request.getParameter("dataScadenza");
		String cvc=request.getParameter("cvc");
		Account a = (Account) request.getSession().getAttribute("account");
		String message=null;
		CartaDiCreditoManager cm = new CartaDiCreditoManager();
		
		try {
			if(!valida(nCarta, titolare, dataScadenza, cvc)) {
				if(!Pattern.matches(expNCarta, nCarta)) {
					 message+="La carta da inserire deve rispettare il formato 1111 1111 1111 1111<br/>";
					 System.out.println(message);
					 response.getWriter().write("Formato errato dati");
					 redirect="/cliente/aggiungiCarta.jsp";
				} 
				
				if(!Pattern.matches(expTitolare, titolare)) {
					message+="Il nome/cognome non puo' contenere altri caratteri oltre lettere<br/>";
					System.out.println(message);
					response.getWriter().write("Formato errato dati");
					redirect="/cliente/aggiungiCarta.jsp";
				} 
				
				if(!Pattern.matches(expDataScadenza, dataScadenza)) {
					message+="La data inserita non e' valida, deve essere del formato mm/yy<br/>";
					//vedere se scaduta
					System.out.println(message);
					response.getWriter().write("Formato errato dati");
					redirect="/cliente/aggiungiCarta.jsp";
					
				}  
				
				if(!Pattern.matches(expCvc, cvc)) {
					message+="Il cvc inserito non e' valido, esso contiene solo tre cifre<br/>";
			
					System.out.println(message);
					response.getWriter().write("Formato errato dati");
					redirect="/cliente/aggiungiCarta.jsp";
				}  
			} else {
				System.out.println(message);
				
				int mese = Integer.parseInt(dataScadenza.substring(0,2));
				System.out.println(mese);
				int anno = Integer.parseInt(dataScadenza.substring(3,5));
				System.out.println(anno);
				
				LocalDate dataOggi=LocalDate.now();
				anno=Integer.parseInt("20"+anno);
				int meseCorrente = dataOggi.getMonthValue();
				int annoCorrente = dataOggi.getYear();
				
				if(anno<annoCorrente){
					response.getWriter().write("Carta scaduta");
					message+="Data Scadenza non valida<br/>";
					redirect="/cliente/aggiungiCarta.jsp";
				} else if(anno==annoCorrente && mese<=meseCorrente){
					response.getWriter().write("Carta scaduta");
					redirect="/cliente/aggiungiCarta.jsp";
					message+="Carta di credito scaduta, gentilmente inserirne un'altra!<br/>";
				} else {
					if(cm.cercaCarta(nCarta, a.getEmail())!=null) {
						message="Carta di credito gia' registrata<br/>";
						redirect="/cliente/aggiungiCarta.jsp";
					} else {
						cardCred.setnCarta(nCarta);
						cardCred.setTitolare(titolare);
						cardCred.setDataScadenza(dataScadenza);
						cardCred.setCvc(Integer.parseInt(cvc));
						cardCred.setAccount(a);
	
						cm.creaCartaDiCredito(cardCred);
						message="Inserimento carta effettuato con successo<br/>";
						response.getWriter().write("Success");
				
						redirect="/cliente/DettagliAccountServlet";
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("message", message);
		request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
	}
	
	private boolean valida(String nCarta, String titolare, String dataScadenza, String cvc) throws ParseException {
		boolean valido=true;
		logger.info("Ho ricevuto "+nCarta);
		logger.info("Ho ricevuto "+titolare);
		logger.info("Ho ricevuto "+dataScadenza);
		logger.info("Ho ricevuto "+cvc);
		
		if (!Pattern.matches(expNCarta, nCarta)) {
			logger.info("nCarta non corrisponde");
			valido=false;
			System.out.print(nCarta);
		}
		
		if (!Pattern.matches(expTitolare, titolare)) {
			logger.info("Titolare non corrisponde");
			valido=false;
			System.out.print(titolare);
		}
	
		if (!Pattern.matches(expDataScadenza, dataScadenza)) {
			logger.info("dataScadenza non corrisponde");
			valido=false;
			System.out.print(dataScadenza);
		}
		
		if (!Pattern.matches(expCvc, cvc)) {
			logger.info("cvc non corrisponde");
			valido=false;
			System.out.print(cvc);
		}
		
		return valido;
	}
}
