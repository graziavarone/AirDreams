package gestioneordine;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionecarrello.CarrelloManager;
import gestioneutente.Account;
import gestioneutente.CartaDiCredito;
import gestioneutente.CartaDiCreditoManager;
import gestionevolo.Volo;
import gestionevolo.VoloManager;

/**
 * La servlet gestisce tutte le operazioni per il pagamento di biglietti
 * da parte di un utente correntemente loggato al sistema
 */
@WebServlet(name="/PagamentoServlet", urlPatterns= {"/cliente/PagamentoServlet"})
public class PagamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger= Logger.getLogger("global");
	private String expNCarta ="^([0-9]{4}( |\\-)){3}[0-9]{4}$";
	private String expTitolare = "^[A-Za-z' ]{1,}$";
	private String expDataScadenza = "^(0[1-9]|1[0-2]|[1-9])\\/(1[4-9]|[2-9][0-9]|20[1-9][1-9])$";
	private String expCvc = "^[0-9]{3}$";
	boolean cartaScaduta=false;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Biglietto> biglietti=(ArrayList<Biglietto>)request.getSession().getAttribute("biglietti");
	
		Account account=(Account)request.getSession().getAttribute("account");
		
		String numeroCarta=request.getParameter("carta");
		CartaDiCreditoManager cartaDiCreditoManager=new CartaDiCreditoManager();
	
		OrdineManager ordineManager=new OrdineManager();
		BigliettoManager bigliettoManager=new BigliettoManager();
		BagaglioManager bagaglioManager=new BagaglioManager();

		CartaDiCredito carta=null;
		String message=null;
		String redirect=null;
		
		try {
			ArrayList<CartaDiCredito> carte=cartaDiCreditoManager.findAll(account.getEmail());
			request.setAttribute("carte", carte);
			
			if(numeroCarta!=null) {
				carta=cartaDiCreditoManager.cercaCarta(numeroCarta, account.getEmail());
			} else {
				String nCarta=request.getParameter("nCarta");
				String titolare=request.getParameter("titolare");
				String dataScadenza=request.getParameter("dataScadenza");
				String cvc=request.getParameter("cvc");
				
				if(!valida(nCarta, titolare, dataScadenza, cvc)) {
					if(!Pattern.matches(expNCarta, nCarta)) {
						 message+="Il numero della carta deve contenere solo cifre<br/>";
						 System.out.println(message);
						 response.getWriter().write("Formato errato dati");

						 redirect="/cliente/pagamento.jsp";
					} 
					
					if(!Pattern.matches(expTitolare, titolare)) {
						message+="Il titolare deve contenere solo lettere dell'alfabeto<br/>";
						System.out.println(message);
						response.getWriter().write("Formato errato dati");

						redirect="/cliente/pagamento.jsp";
					} 
					
					if(!Pattern.matches(expDataScadenza, dataScadenza)) {
						message+="Formato dataScadenza non valido<br/>";
					
						//vedere se scaduta
						System.out.println(message);
						response.getWriter().write("Formato errato dati");

						redirect="/cliente/pagamento.jsp";
						
					} 
					
					if(!Pattern.matches(expCvc, cvc)) {
						message+="Formato cvc non valido<br/>";
						System.out.println(message);
						response.getWriter().write("Formato errato dati");

						redirect="/cliente/pagamento.jsp";
					}  	
				} else{
				
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
						cartaScaduta=true;
						redirect="/cliente/pagamento.jsp";
						
					} else if(anno==annoCorrente){
						System.out.println("mese corrente"+meseCorrente);
						System.out.println("mese carta"+mese);
						System.out.println("scaduta"+(mese<=meseCorrente));
						if(mese<=meseCorrente) {
							response.getWriter().write("Carta scaduta");
						
							message+="Carta di credito scaduta, gentilmente inserirne un'altra!<br/>";
							cartaScaduta=true;
							redirect="/cliente/pagamento.jsp";
						}
					}
					
					if(!cartaScaduta) {
						carta=new CartaDiCredito();
						carta.setnCarta(nCarta);
						carta.setTitolare(titolare);
						carta.setDataScadenza(dataScadenza);
						carta.setCvc(Integer.parseInt(cvc));
						carta.setAccount(account);
			
						cartaDiCreditoManager.creaCartaDiCredito(carta);
					}
				
					if(carta!=null) {
						System.out.println("CARTA NON E' NULL MA E'"+carta);
						Ordine ordine=new Ordine(LocalDate.now(), account, carta);
						System.out.println(LocalDate.now());
						ordine=ordineManager.aggiungiOrdine(ordine);
					
						for(Biglietto biglietto: biglietti) {
							//creo prima il biglietto
							biglietto.setOrdine(ordine.getCodOrdine());
							biglietto=bigliettoManager.aggiungiBiglietto(biglietto);
							
							System.out.println("Bagaglio a mano di biglietto "+biglietto.getCodBiglietto()+" per il volo "
										+ ""+biglietto.getVolo().getId());
						
							bagaglioManager.aggiungiBagaglioMano(biglietto.getBagaglioMano());
							
							Iterator<BagaglioStiva> iterator=biglietto.getBagagliStiva().iterator();
								
							while(iterator.hasNext()) {
								BagaglioStiva bagaglioStiva=iterator.next();
									
								bagaglioManager.aggiungiBagaglioStiva(bagaglioStiva);
							}
								
							Volo voloBiglietto=biglietto.getVolo();
							voloBiglietto.setSeats(voloBiglietto.getSeats()-1);
							VoloManager voloManager = new VoloManager();
							voloManager.modificaVolo(voloBiglietto);
								
							CarrelloManager carrelloManager=new CarrelloManager();
							carrelloManager.svuotaCarrello(account.getEmail());
						}
			
						response.getWriter().write("Success");
						request.getSession().removeAttribute("biglietti");
						System.out.println("REMOVE ATTRIBUTE");
						redirect="/cliente/acquisto.jsp";
					} 	
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("message", message);
		System.out.println(redirect);
		request.getServletContext().getRequestDispatcher(redirect).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean valida(String nCarta, String titolare, String dataScadenza, String cvc) throws ParseException {
		boolean valido=true;

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
