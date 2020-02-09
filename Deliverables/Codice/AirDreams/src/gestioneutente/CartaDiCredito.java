package gestioneutente;

/**
 * La classe rappresenta una CartaDiCredito memorizzata per un cliente 
 * e che può essere utilizzata per l'acquisto di biglietti aerei
 */
public class CartaDiCredito {
	
	private String nCarta;
	private String titolare;
	private String dataScadenza;
	private int cvc;
	private Account account;
	
	/**
	 * metodo costruttore della classe CartaDiCredito privo di argomenti
	 */
	public CartaDiCredito() {
		
	}
	
	/**
	 * metodo costruttore della classe CartaDiCredito
	 * @param nCarta nCarta della carta di credito
	 * @param titolare titolare della carta di credito
	 * @param dataScadenza dataScadenza della carta di credito
	 * @param cvc cvc della carta di credito
	 * @return 
	 */
	public CartaDiCredito(String nCarta, String titolare, String dataScadenza, int cvc) {
		super();
		this.nCarta = nCarta;
		this.titolare = titolare;
		this.dataScadenza = dataScadenza;
		this.cvc = cvc;
	}

	/**
	 * metodo getter di restituzione del numero della carta di credito
	 * @return String numero della carta di credito
	 */
	public String getnCarta() {
		return nCarta;
	}

	/**
	 * metodo setter di modifica del numero della carta di credito
	 * @param nCarta nuovo numero della carta di credito da impostare
	 */
	public void setnCarta(String nCarta) {
		this.nCarta = nCarta;
	}

	/**
	 * metodo getter di restituzione del nome del titolare della carta di credito
	 * @return String nome del titolare della carta di credito
	 */
	public String getTitolare() {
		return titolare;
	}

	/**
	 * metodo setter di modifica del nome del titolare della carta di credito
	 * @param titolare nuovo nome del titolare della carta di credito da impostare
	 */
	public void setTitolare(String titolare) {
		this.titolare = titolare;
	}

	/**
	 * metodo getter di restituzione della data di scadenza della carta di credito
	 * @return String data di scadenza della carta di credito
	 */
	public String getDataScadenza() {
		return dataScadenza;
	}

	/**
	 * metodo setter di modifica della data di scadenza della carta di credito
	 * @param dataScadenza nuova data di scadenza della carta di credito
	 */
	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	/**
	 * metodo getter di restituzione del CVC della carta di credito
	 * @return int CVC della carta di credito
	 */
	public int getCvc() {
		return cvc;
	}

	/**
	 * metodo setter di modifica del CVC della carta di credito
	 * @param cvc nuovo CVC della carta di credito da impostare
	 */
	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	/**
	 * metodo getter di restituzione dell'account a cui la carta di credito è relata
	 * @return Account account relato alla carta di credito
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * metodo setter di modifica dell'account a cui la carta di credito è relata
	 * @param account nuovo account relato alla carta di credito da impostare
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	
	/**
	 * metodo ereditato dalla classe Object che permette di visualizzare le informazioni relative ad un oggetto di tipo CartaDiCredito
	 * @return String contenente lo stato dell'oggetto CartaDiCredito
	 */
	@Override
	public String toString() {
		return "CartaDiCredito [nCarta=" + nCarta + ", titolare=" + titolare + ", dataScadenza=" + dataScadenza
				+ ", cvc=" + cvc + ", account=" + account + "]";
	}
}
