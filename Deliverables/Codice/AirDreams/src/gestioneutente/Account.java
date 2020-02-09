package gestioneutente;

import gestionecompagniaaerea.CompagniaAerea;

/**
 * La classe rappresenta l’utente registrato, ovvero un qualsiasi utente che si è registrato al sistema 
 * e può effettuare il login in qualunque momento, accedendo alla propria area personale.
 */

public class Account {
	private String nome;
	private String cognome;
	private String password;
	private String email;
	private CompagniaAerea compagniaAerea;
	private Ruolo ruolo;
	
	public Account() {
		
	}
	
	/**
	 * metodo costuttore della classe Account
	 * @param nome nome dell'utente
	 * @param cognome cognome dell'utente
	 * @param email email di accesso dell'utente
	 * @param password password di accesso dell'utente
	 * @return 
	 */
	public Account(String nome, String cognome, String email, String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
		this.email = email;
	}
	
	/**
	 * metodo getter di restituzione del nome dell'utente
	 * @return nome nome dell'utente
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * metodo setter di modifica del nome dell'utente
	 * @param nome nuovo nome dell'utente
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * metodo getter di restituzione del cognome dell'utente
	 * @return cognome cognome dell'utente
	 */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * metodo setter di modifica del cognome dell'utente
	 * @param cognome nuovo cognome dell'utente 
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	/**
	 * metodo getter di restituzione della password di accesso dell'utente
	 * @return password password di accesso dell'utente
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * metodo setter di modifica della password di accesso dell'utente
	 * @param password nuova password di accesso dell'utente 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * metodo getter di restituzione della email di accesso dell'utente
	 * @return email email di accesso dell'utente
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * metodo setter di modifica della email di accesso dell'utente 
	 * @param email nuova email di accesso dell'utente
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * metodo getter di restituzione della eventuale compagnia aerea di gestione dell'utente, 
	 * se quest ultimo riveste il ruolo di gestore voli
	 * @return CompagniaAerea compagnia aerea di gestione dell'utente
	 */
	public CompagniaAerea getCompagniaAerea() {
		return compagniaAerea;
	}

	/**
	 * metodo setter di modifica della eventuale compagnia aerea di gestione dell'utente, 
	 * se quest ultimo riveste il ruolo di gestore voli
	 * @param compagniaAerea nuova compagnia aerea di gestione dell'utente
	 */
	public void setCompagniaAerea(CompagniaAerea compagniaAerea) {
		this.compagniaAerea = compagniaAerea;
	}

	/**
	 * metodo getter di restituzione del ruolo rivestito dall'utente
	 * @return Ruolo ruolo rivestito dall'utente nel sistema
	 */
	public Ruolo getRuolo() {
		return ruolo;
	}

	/**
	 * metodo setter di modifica del ruolo rivestito dall'utente
	 * @param ruolo nuovo ruolo rivestito dall'utente nel sistema
	 */
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	/**
	 * metodo ereditato dalla classe Object che permette di visualizzare le informazioni relative ad un oggetto di tipo Account
	 * @return String contenente lo stato dell'oggetto Account
	 */
	@Override
	public String toString() {
		return "Account [nome=" + nome + ", cognome=" + cognome + ", password=" + password + ", email=" + email
				+ ", ruolo=" + ruolo + "]";
	}
}