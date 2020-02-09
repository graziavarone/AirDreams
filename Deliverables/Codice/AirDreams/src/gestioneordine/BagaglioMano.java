package gestioneordine;

/**
 * La classe rappresenta il bagaglio a mano incluso nel biglietto di un singolo passeggero con le relative dimensioni
 */
public class BagaglioMano {
	private int peso;
	private String dimensioni;
	private Biglietto biglietto;
	
	/**
	 * metodo costruttore della classe BagaglioMano
	 * @param peso peso massimo per il bagaglio a mano
	 * @param dimensioni dimensioni consentite per il bagaglio a mano
	 * @param biglietto biglietto del volo a cui il bagaglio a mano è collegato 
	 * @return
	 */
	public BagaglioMano(int peso, String dimensioni, Biglietto biglietto) {
		super();
		this.peso = peso;
		this.dimensioni = dimensioni;
		this.biglietto = biglietto;
	}
	
	/**
	 * metodo costruttore della classe BagaglioMano privo di argomenti
	 */
	public BagaglioMano() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * metodo getter di restituzione del peso massimo per il bagaglio a mano
	 * @return int peso massimo per il bagaglio a mano
	 */
	public int getPeso() {
		return peso;
	}
	
	/**
	 * metodo setter di modifica del peso massimo per il bagaglio a mano
	 * @param peso nuovo peso massimo per il bagaglio a mano
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	/**
	 * metodo getter di restituzione delle dimensioni consentite per il bagaglio a mano
	 * @return String dimensioni consentite per il bagaglio a mano
	 */
	public String getDimensioni() {
		return dimensioni;
	}
	
	/**
	 * metodo setter di modifica delle dimensioni consentite per il bagaglio a mano
	 * @param dimensioni nuove dimensioni consentite per il bagaglio a mano
	 */
	public void setDimensioni(String dimensioni) {
		this.dimensioni = dimensioni;
	}

	/**
	 * metodo getter di restituzione delle informazioni relative al biglietto del volo a cui il bagaglio a mano è collegato
	 * @return Biglietto informazioni relative al biglietto del volo a cui il bagaglio a mano è collegato
	 */
	public Biglietto getBiglietto() {
		return biglietto;
	}
	
	/**
	 * metodo setter di modifica delle informazioni relative al biglietto del volo a cui il bagaglio a mano è collegato
	 * @param biglietto nuove informazioni relative al biglietto del volo a cui il bagaglio a mano è collegato
	 */
	public void setBiglietto(Biglietto biglietto) {
		this.biglietto = biglietto;
	}
	
	/**
	 * metodo ereditato dalla classe Object che permette di visualizzare le informazioni relative ad un oggetto di tipo BagaglioMano
	 * @return String contenente lo stato dell'oggetto BagaglioMano
	 */
	@Override
	public String toString() {
		return "BagaglioMano [peso=" + peso + ", dimensioni=" + dimensioni + ", biglietto="
				+ biglietto + "]";
	}
}
