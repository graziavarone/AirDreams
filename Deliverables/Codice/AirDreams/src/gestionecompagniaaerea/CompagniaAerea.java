package gestionecompagniaaerea;

/**
 * La classe tiene traccia della compagnia aerea con le relative informazioni
 */
public class CompagniaAerea {
	private String nome;
	private String sito;
	
	/**
	 * metodo costruttore della classe CompagniaAerea privo di argomenti
	 */
	public CompagniaAerea() {
		super();
	}
	
	/**
	 * metodo costruttore della classe CompagniaAerea
	 * @param nome nome della compagnia aerea
	 * @param sito sito web relativo alla compagnia aerea
	 * @return
	 */
	public CompagniaAerea(String nome,  String sito) {
		super();
		this.nome = nome;
		this.sito = sito;
	}

	/**
	 * metodo getter di restituzione del nome della compagnia aerea
	 * @return String nome della compagnia aerea
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * metodo setter di modifica del nome della compagnia aerea
	 * @param nome nuovo nome della compagnia aerea
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * metodo getter di restituzione del sito web relativo alla compagnia aerea
	 * @return String sito web relativo alla compagnia aerea
	 */
	public String getSito() {
		return sito;
	}
	
	/**
	 * metodo setter di modifica del sito web relativo alla compagnia aerea
	 * @param sito nuovo sito web relativo alla compagnia aerea
	 */
	public void setSito(String sito) {
		this.sito = sito;
	}
	
	/**
	 * metodo ereditato dalla classe Object che permette di visualizzare le informazioni relative ad un oggetto di tipo CompagniaAerea
	 * @return String contenente lo stato dell'oggetto CompagniaAerea
	 */
	@Override
	public String toString() {
		return "CompagniaAerea [nome=" + nome + ", sito=" + sito + "]";
	}
}
