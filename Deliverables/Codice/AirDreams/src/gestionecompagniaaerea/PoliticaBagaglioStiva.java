package gestionecompagniaaerea;

/**
 * La classe definisce la politica sul bagaglio a stiva di una singola compagnia aerea.
 */
public class PoliticaBagaglioStiva {
	private int peso;
	private String dimensioni;
	private float prezzo;
	private CompagniaAerea compagnia;
	
	/**
	 * metodo costruttore della classe PoliticaBagaglioStiva
	 * @param peso peso massimo previsto per il bagaglio a stiva dalla politica della compagnia aerea
	 * @param dimensioni dimensioni previste per il bagaglio a stiva dalla politica della compagnia aerea
	 * @param prezzo prezzo per il singolo bagaglio a stiva previsto dalla politica della compagnia aerea
	 * @param compagnia compagnia aerea a cui la politica bagaglio a stiva è relata
	 * @return
	 */
	public PoliticaBagaglioStiva(int peso, String dimensioni, float prezzo, CompagniaAerea compagnia) {
		super();
		this.peso = peso;
		this.dimensioni = dimensioni;
		this.prezzo = prezzo;
		this.compagnia = compagnia;
	}
	
	/**
	 * metodo costruttore della classe PoliticaBagaglioStiva privo di argomenti
	 */
	public PoliticaBagaglioStiva() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * metodo getter di restituzione del peso massimo previsto per il bagaglio a stiva dalla politica della compagnia aerea
	 * @return int peso massimo previsto per il bagaglio a stiva dalla politica della compagnia aerea
	 */
	public int getPeso() {
		return peso;
	}
	
	/**
	 * metodo setter di modifica del peso massimo previsto per il bagaglio a stiva dalla politica della compagnia aerea
	 * @param peso nuovo peso massimo previsto per il bagaglio a stiva dalla politica della compagnia aerea
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	/**
	 * metodo getter di resituzione delle dimensioni previste per il bagaglio a stiva dalla politica della compagnia aerea
	 * @return String dimensioni previste per il bagaglio a stiva dalla politica della compagnia aerea
	 */
	public String getDimensioni() {
		return dimensioni;
	}
	
	/**
	 * metodo setter di modifica delle dimensioni previste per il bagaglio a stiva dalla politica della compagnia aerea
	 * @param dimensioni nuove dimensioni previste per il bagaglio a stiva dalla politica della compagnia aerea
	 */
	public void setDimensioni(String dimensioni) {
		this.dimensioni = dimensioni;
	}
	
	/**
	 * metodo getter di restituzione del prezzo per il singolo bagaglio a stiva previsto dalla politica della compagnia aerea
	 * @return float prezzo per il singolo bagaglio a stiva previsto dalla politica della compagnia aerea
	 */
	public float getPrezzo() {
		return prezzo;
	}
	
	/**
	 * metodo setter di modifica del prezzo per il singolo bagaglio a stiva previsto dalla politica della compagnia aerea
	 * @param prezzo nuovo prezzo per il singolo bagaglio a stiva previsto dalla politica della compagnia aerea
	 */
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	/**
	 * metodo getter di restituzione della compagnia aerea a cui la politica bagaglio a stiva è relata
	 * @return CompagniaAerea compagnia aerea a cui la politica bagaglio a stiva è relata
	 */
	public CompagniaAerea getCompagnia() {
		return compagnia;
	}
	
	/**
	 * metodo setter di modifica della compagnia aerea a cui la politica bagaglio a stiva è relata
	 * @param compagnia nuova compagnia aerea a cui la politica bagaglio a stiva è relata
	 */
	public void setCompagnia(CompagniaAerea compagnia) {
		this.compagnia = compagnia;
	}
	
	/**
	 * metodo ereditato dalla classe Object che permette di visualizzare le informazioni relative ad un oggetto di tipo PoliticaBagaglioStiva
	 * @return String contenente lo stato dell'oggetto PoliticaBagaglioStiva
	 */
	@Override
	public String toString() {
		return "PoliticaBagaglioStiva [peso=" + peso + ", dimensioni=" + dimensioni + ", prezzo=" + prezzo + ", compagnia="
				+ compagnia + "]";
	}
}
