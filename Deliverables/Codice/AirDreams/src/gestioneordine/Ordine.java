package gestioneordine;

import java.time.LocalDate;


import gestioneutente.Account;
import gestioneutente.CartaDiCredito;

/**
 * La classe rappresenta un ordine concluso da un cliente
 */
public class Ordine {
	
	private int codOrdine;
	private LocalDate dataAcquisto;
	private Account account;
	private CartaDiCredito cartaDiCredito;
	
	/**
	 * metodo costruttore della classe Ordine
	 * @param dataAcquisto data in cui è stato effettuato l'ordine
	 * @param account account dell'utente che ha effettuato l'ordine
	 * @param cartaDiCredito carta di credito usata dall'utente per l'acquisto
	 */
	public Ordine(LocalDate dataAcquisto, Account account, CartaDiCredito cartaDiCredito) {
		super();
		this.dataAcquisto = dataAcquisto;
		this.account = account;
		this.cartaDiCredito = cartaDiCredito;
	}

	/**
	 * metodo costruttore della classe Ordine privo di argomenti
	 */
	public Ordine() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * metodo getter per la restituzione del codice dell'ordine
	 * @return int codice dell'ordine
	 */
	public int getCodOrdine() {
		return codOrdine;
	}

	/**
	 * metodo setter per la modifica del codice dell'ordine
	 * @param codOrdine nuovo codice da impostare dell'ordine 
	 */
	public void setCodOrdine(int codOrdine) {
		this.codOrdine = codOrdine;
	}

	/**
	 * metodo getter per la restituzione della data dell'ordine
	 * @return LocalDate data in cui è stato effettuato l'ordine
	 */
	public LocalDate getDataAcquisto() {
		return dataAcquisto;
	}

	/**
	 * metodo setter per la modifica della data dell'ordine
	 * @param dataAcquisto nuova data dell'ordine 
	 */
	public void setDataAcquisto(LocalDate dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	/**
	 * metodo getter per la restituzione dell'account dell'utente che ha effettuato l'ordine
	 * @return Account account dell'utente che ha effettuato l'ordine
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * metodo setter per la modifica dell'account che ha effettuato l'ordine
	 * @param account nuovo account da impostare
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * metodo getter per la restituzione della carta di credito usata per effettuare il pagamento dell'ordine
	 * @return CartaDiCredito carta di credito usata per effettuare l'ordine
	 */
	public CartaDiCredito getCartaDiCredito() {
		return cartaDiCredito;
	}

	/**
	 * metodo setter per la modifica della carta di credito usata per effettuare l'ordine
	 * @param cartaDiCredito nuova carta di credito da impostare
	 */
	public void setCartaDiCredito(CartaDiCredito cartaDiCredito) {
		this.cartaDiCredito = cartaDiCredito;
	}

	/**
	 * metodo ereditato dalla classe Object che permette di visualizzare le informazioni relative ad un oggetto di tipo Ordine
	 * @return String contenente lo stato dell'oggetto Ordine
	 */
	@Override
	public String toString() {
		return "Ordine [codOrdine=" + codOrdine + ", dataAcquisto=" + dataAcquisto + ", account=" + account
				+ ", cartaDiCredito=" + cartaDiCredito + "]";
	}
}
