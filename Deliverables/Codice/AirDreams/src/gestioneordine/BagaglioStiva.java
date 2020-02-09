package gestioneordine;

/**
 * La classe rappresenta il bagaglio a stiva incluso nel biglietto di un singolo passeggero 
 * con le relative dimensioni e prezzo
 */
public class BagaglioStiva {
	private int peso;
	private String dimensioni;
	private Biglietto biglietto;
	private float prezzo;
	private int quantity;
	
	/**
	 * metodo costruttore della classe BagaglioStiva
	 * @param peso peso massimo per il bagaglio a stiva
	 * @param dimensioni dimensioni consentite per il bagaglio a stiva
	 * @param biglietto biglietto del volo a cui il bagaglio a stiva è collegato 
	 * @param prezzo prezzo per il singolo bagaglio a stiva
	 * @param quantity numero di bagagli a stiva assegnati al biglietto
	 * @return
	 */
	public BagaglioStiva(int peso, String dimensioni, Biglietto biglietto, float prezzo,int quantity) {
		super();
		this.peso = peso;
		this.dimensioni = dimensioni;
		this.biglietto = biglietto;
		this.prezzo=prezzo;
		this.quantity=quantity;
	}
	
	/**
	 * metodo costruttore della classe BagaglioStiva privo di argomenti
	 */
	public BagaglioStiva() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * metodo getter di restituzione del numero di bagagli a stiva assegnati al biglietto
	 * @return int numero di bagagli a stiva assegnati al biglietto
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * metodo setter di modifica del numero di bagagli a stiva assegnati al biglietto
	 * @param quantity nuovo numero di bagagli a stiva assegnati al biglietto
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * metodo getter di restituzione del peso massimo per il bagaglio a stiva
	 * @return int peso massimo per il bagaglio a stiva
	 */
	public int getPeso() {
		return peso;
	}
	
	/**
	 * metodo setter di modifica del peso massimo per il bagaglio a stiva
	 * @param peso nuovo peso massimo per il bagaglio a stiva
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	/**
	 * metodo getter di restituzione delle dimensioni consentite per il bagaglio a stiva
	 * @return String dimensioni consentite per il bagaglio a stiva
	 */
	public String getDimensioni() {
		return dimensioni;
	}
	
	/**
	 * metodo setter di modifica delle dimensioni consentite per il bagaglio a stiva
	 * @param dimensioni nuove dimensioni consentite per il bagaglio a stiva
	 */
	public void setDimensioni(String dimensioni) {
		this.dimensioni = dimensioni;
	}
	
	/**
	 * metodo getter di restituzione delle informazioni relative al biglietto del volo a cui il bagaglio a stiva è collegato
	 * @return Biglietto informazioni relative al biglietto del volo a cui il bagaglio a stiva è collegato
	 */
	public Biglietto getBiglietto() {
		return biglietto;
	}
	
	/**
	 * metodo setter di modifica delle informazioni relative al biglietto del volo a cui il bagaglio a stiva è collegato
	 * @param biglietto nuove informazioni relative al biglietto del volo a cui il bagaglio a stiva è collegato
	 */
	public void setBiglietto(Biglietto biglietto) {
		this.biglietto = biglietto;
	}
	
	/**
	 * metodo getter di restituzione del prezzo per il singolo bagaglio a stiva
	 * @return float prezzo per il singolo bagaglio a stiva
	 */
	public float getPrezzo() {
		return prezzo;
	}
	
	/**
	 * metodo setter di modifica del prezzo per il singolo bagaglio a stiva
	 * @param prezzo nuovo prezzo per il singolo bagaglio a stiva
	 */
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	/**
	 * metodo ereditato dalla classe Object che permette di visualizzare le informazioni relative ad un oggetto di tipo BagaglioStiva
	 * @return String contenente lo stato dell'oggetto BagaglioStiva
	 */
	@Override
	public String toString() {
		return "BagaglioStiva [peso=" + peso + ", dimensioni=" + dimensioni + ", biglietto=" + biglietto + ", prezzo="
				+ prezzo + ", quantity=" + quantity + "]";
	}
}
