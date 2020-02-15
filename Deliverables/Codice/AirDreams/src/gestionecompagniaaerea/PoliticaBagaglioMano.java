package gestionecompagniaaerea;
import gestionecompagniaaerea.CompagniaAerea;

/**
 * La classe definisce la politica sul bagaglio a mano di una singola compagnia aerea
 */
public class PoliticaBagaglioMano {
	private int peso;
	private String dimensioni;
	private CompagniaAerea compagnia;
	
	/**
	 * metodo costruttore della classe PoliticaBagaglioMano
	 * @param peso peso massimo previsto per il bagaglio a mano dalla politica della compagnia aerea
	 * @param dimensioni dimensioni previste per il bagaglio a mano dalla politica della compagnia aerea
	 * @param compagnia compagnia aerea a cui la politica bagaglio a mano � relata
	 * @return
	 */
	public PoliticaBagaglioMano(int peso, String dimensioni, CompagniaAerea compagnia) {
		super();
		this.peso = peso;
		this.dimensioni = dimensioni;
		this.compagnia = compagnia;
	}
	
	/**
	 * metodo costruttore della classe PoliticaBagaglioMano privo di argomenti
	 */
	public PoliticaBagaglioMano() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * metodo getter di restituzione del peso massimo previsto per il bagaglio a mano dalla politica della compagnia aerea
	 * @return int peso massimo previsto per il bagaglio a mano dalla politica della compagnia aerea
	 */
	public int getPeso() {
		return peso;
	}
	
	/**
	 * metodo setter di modifica del peso massimo previsto per il bagaglio a mano dalla politica della compagnia aerea
	 * @param peso nuovo peso massimo previsto per il bagaglio a mano dalla politica della compagnia aerea
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	/**
	 * metodo getter di resituzione delle dimensioni previste per il bagaglio a mano dalla politica della compagnia aerea
	 * @return String dimensioni previste per il bagaglio a mano dalla politica della compagnia aerea
	 */
	public String getDimensioni() {
		return dimensioni;
	}
	
	/**
	 * metodo setter di modifica delle dimensioni previste per il bagaglio a mano dalla politica della compagnia aerea
	 * @param dimensioni nuove dimensioni previste per il bagaglio a mano dalla politica della compagnia aerea
	 */
	public void setDimensioni(String dimensioni) {
		this.dimensioni = dimensioni;
	}

	/**
	 * metodo getter di restituzione della compagnia aerea a cui la politica bagaglio a mano e' relata
	 * @return CompagniaAerea compagnia aerea a cui la politica bagaglio a mano e' relata
	 */
	public CompagniaAerea getCompagnia() {
		return compagnia;
	}
	
	/**
	 * metodo setter di modifica della compagnia aerea a cui la politica bagaglio a mano e' relata
	 * @param compagnia nuova compagnia aerea a cui la politica bagaglio a mano e' relata
	 */
	public void setCompagnia(CompagniaAerea compagnia) {
		this.compagnia = compagnia;
	}
	
	/**
	 * metodo ereditato dalla classe Object che permette di visualizzare le informazioni relative ad un oggetto di tipo PoliticaBagaglioMano
	 * @return String contenente lo stato dell'oggetto PoliticaBagaglioMano
	 */
	@Override
	public String toString() {
		return "PoliticaBagaglioMano [peso=" + peso + ", dimensioni=" + dimensioni + ", compagnia="
				+ compagnia + "]";
	}
}
