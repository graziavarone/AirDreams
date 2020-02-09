package gestionevolo;

/**
 * La classe rappresenta un aeroporto, ovvero un luogo dove sono installati 
 * tutti gli impianti necessari al decollo, atterraggio e allo stazionamento 
 * degli aerei per i voli offerti dal sistema
 */
public class Aeroporto {
	
	private String codice;
	private String nome;
	private String city;
	private String stato;
	
	/**
	 * metodo costruttore della classe Aeroporto privo di argomenti
	 */
	public Aeroporto(){
		
	}
	
	/**
	 * metodo costruttore della classe Aeroporto
	 * @param codice id identificativo dell'aeroporto
	 * @param nome nome dell'aeroporto
	 * @param city citta' in cui è situato l'aeroporto
	 * @param stato stato in cui è situato l'aeroporto
	 */
	public Aeroporto(String codice, String nome, String city, String stato) {
		super();
		this.codice = codice;
		this.nome = nome;
		this.city = city;
		this.stato = stato;
	}

	/**
	 * metodo getter di restituzione dell'id identificativo dell'aeroporto
	 * @return String id identificativo dell'aeroporto
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * metodo setter di modifica dell'id identificativo dell'aeroporto
	 * @param codice nuovo id identificativo dell'aeroporto
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}

	/**
	 * metodo getter di restituzione del nome dell'aeroporto
	 * @return String nome dell'aeroporto
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * metodo setter di modifica del nome dell'aeroporto
	 * @param nome nuovo nome dell'aeroporto da impostare
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * metodo getter di restituzione della citta' di locazione dell'aeroporto
	 * @return String citta' di locazione dell'aeroporto
	 */
	public String getCity() {
		return city;
	}

	/**
	 * metodo setter di modifica della citta' di locazione dell'aeroporto
	 * @param city nuova citta' di locazione dell'aeroporto da impostare
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * metodo getter di restituzione dello stato di appartenenza dell'aeroporto
	 * @return String stato di appartenenza dell'aeroporto
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * metodo setter di modifica dello stato di appartenenza dell'aeroporto
	 * @param stato nuovo stato di appartenenza dell'aeroporto da impostare
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

	/**
	 * metodo ereditato dalla classe Object che permette di visualizzare le informazioni relative ad un oggetto di tipo Aeroporto
	 * @return String contenente lo stato dell'oggetto Aeroporto
	 */
	@Override
	public String toString() {
		return "Aeroporto [codice=" + codice + ", nome=" + nome + ", city=" + city + ", stato=" + stato + "]";
	}
	
	/**
	 * metodo ereditato dalla classe Object che permette di verificare se due oggetti di tipo Aeroporto sono uguali 
	 * @param o oggetto da confrontare
	 * @return boolean true se i due oggetti sono uguali, false altrimenti
	 */
	@Override 
	public boolean equals(Object obj) { 	
		if (obj == null) 		
			return false; 	
		if (getClass() != obj.getClass()) 		
			return false; 	
		Aeroporto other = (Aeroporto) obj; 	
		if (codice.equals(other.codice) && nome.equals(other.nome) && city.equals(other.city) && stato.equals(other.stato)) 		
			return true; 	
		return false; 
	}
}