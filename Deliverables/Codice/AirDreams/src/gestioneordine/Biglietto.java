package gestioneordine;

import java.util.Collection;
import java.util.HashSet;

import gestionevolo.Volo;

/**
 * La classe Biglietto rappresenta il biglietto per un determinato volo con le sue informazioni.
 */
public class Biglietto {
	private String nome;
	private String cognome;
	private Sesso sesso;
	private float prezzoBiglietto;
	private int codBiglietto;
	private int ordine;
	private Volo volo;
	private HashSet<BagaglioStiva> bagagliStiva;
	private BagaglioMano bagaglioMano;
	
	/**
	 * metodo costruttore della classe Biglietto
	 * @param nome nome del passeggero al quale � assegnato il biglietto
	 * @param cognome cognome del passeggero al quale � assegnato il biglietto
	 * @param sesso sesso del passeggero al quale � assegnato il biglietto
	 * @param prezzoBiglietto prezzo del biglietto
	 * @param volo informazioni del volo relativo al biglietto
	 * @return
	 */
	public Biglietto(String nome, String cognome, Sesso sesso, float prezzoBiglietto, Volo volo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.prezzoBiglietto = prezzoBiglietto;
		this.volo=volo;
		bagagliStiva=new HashSet<BagaglioStiva>();
	}
	
	/**
	 * metodo costruttore della classe Biglietto privo di argomenti
	 */
	public Biglietto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * metodo getter di restituzione delle informazioni riguardanti il bagaglio a mano previsto dal biglietto
	 * @return BagaglioMano informazioni politica bagaglio a mano prevista dal biglietto
	 */
	public BagaglioMano getBagaglioMano() {
		return bagaglioMano;
	}

	/**
	 * metodo setter di modifica delle informazioni riguardanti il bagaglio a mano previsto dal biglietto
	 * @param bagaglioMano nuove informazioni da impostare riguardanti la politica bagaglio a mano prevista dal biglietto
	 */
	public void setBagaglioMano(BagaglioMano bagaglioMano) {
		this.bagaglioMano = bagaglioMano;
	}

	/**
	 * metodo getter di restituzione delle informazioni riguardanti il/i bagaglio/i a stiva incluso/i nel biglietto
	 * @return Collection<BagaglioStiva> lista delle informazioni politica bagaglio/i a stiva incluso/i nel biglietto
	 */
	public Collection<BagaglioStiva> getBagagliStiva() {
		return bagagliStiva;
	}

	/**
	 * metodo setter di modifica delle informazioni riguardanti il/i bagaglio/i a stiva incluso/i nel biglietto
	 * @param bagagliStiva nuova lista da impostare delle informazioni politica bagaglio/i a stiva incluso/i nel biglietto  
	 */
	public void setBagagliStiva(HashSet<BagaglioStiva> bagagliStiva) {
		this.bagagliStiva = bagagliStiva;
	}

	/** 
	 * metodo getter di restituzione delle informazioni del volo relativo al biglietto
	 * @return Volo informazioni del volo relativo al biglietto
	 */
	public Volo getVolo() {
		return volo;
	}

	/**
	 * metodo setter di modifica delle informazioni del volo relativo al biglietto
	 * @param volo nuove informazioni del volo relativo al biglietto
	 */
	public void setVolo(Volo volo) {
		this.volo = volo;
	}

	/**
	 * metodo getter di restituzione dell'ID identificativo dell'ordine relativo all'acquisto di un biglietto
	 * @return int ID identificativo dell'ordine relativo all'acquisto di un biglietto
	 */
	public int getOrdine() {
		return ordine;
	}

	/**
	 * metodo setter di modifica dell'ID identificativo dell'ordine relativo all'acquisto di un biglietto
	 * @param ordine nuovo ID identificativo dell'ordine relativo all'acquisto di un biglietto
	 */
	public void setOrdine(int ordine) {
		this.ordine = ordine;
	}

	/**
	 * metodo getter di restituzione del nome del passeggero al quale � assegnato il biglietto
	 * @return String nome del passeggero al quale � assegnato il biglietto
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * metodo setter di modifica del nome del passeggero al quale � assegnato il biglietto
	 * @param nome nuovo nome del passeggero al quale � assegnato il biglietto
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * metodo getter di restituzione del cognome del passeggero al quale � assegnato il biglietto
	 * @return String cognome del passeggero al quale � assegnato il biglietto
	 */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * metodo setter di modifica del cognome del passeggero al quale � assegnato il biglietto
	 * @param cognome nuovo cognome del passeggero al quale � assegnato il biglietto
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * metodo getter di restituzione del sesso del passeggero al quale � assegnato il biglietto
	 * @return Sesso sesso del passeggero al quale � assegnato il biglietto
	 */
	public Sesso getSesso() {
		return sesso;
	}
	
	/**
	 * metodo setter di modifica del sesso del passeggero al quale � assegnato il biglietto
	 * @param sesso nuovo sesso del passeggero al quale � assegnato il biglietto
	 */
	public void setSesso(Sesso sesso) {
		this.sesso = sesso;
	}

	/**
	 * metodo getter di restituzione del prezzo del biglietto
	 * @return float prezzo del biglietto
	 */
	public float getPrezzoBiglietto() {
		return prezzoBiglietto;
	}

	/**
	 * metodo setter di modifica del prezzo del biglietto
	 * @param prezzoBiglietto nuovo prezzo del biglietto
	 */
	public void setPrezzoBiglietto(float prezzoBiglietto) {
		this.prezzoBiglietto = prezzoBiglietto;
	}

	/**
	 * metodo getter di restituzione del codice identificativo del biglietto
	 * @return int codice identificativo del biglietto
	 */
	public int getCodBiglietto() {
		return codBiglietto;
	}

	/**
	 * metodo setter di modifica del codice identificativo del biglietto
	 * @param codBiglietto nuovo codice identificativo del biglietto
	 */
	public void setCodBiglietto(int codBiglietto) {
		this.codBiglietto = codBiglietto;
	}

	/**
	 * metodo ereditato dalla classe Object che permette di visualizzare le informazioni relative ad un oggetto di tipo Biglietto
	 * @return String contenente lo stato dell'oggetto Biglietto
	 */
	@Override
	public String toString() {
		return "Biglietto [nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso + ", prezzoBiglietto="
				+ prezzoBiglietto + ", codBiglietto=" + codBiglietto + ", ordine=" + ordine + ", volo=" + volo + "]";
	}
}
