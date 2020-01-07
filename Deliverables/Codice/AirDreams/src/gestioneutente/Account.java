package gestioneutente;

/**
 * Questa classe rappresenta l'entità Account.
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
	 * @param nome nome dell'utente
	 * @param cognome cognome dell'utente
	 * @param email email dell'utente
	 * @param password password dell'utente
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
	 * @return nome nome dell'utente
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome nome da assegnare all'utente
	 * @return 
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return cognome cognome dell'utente
	 */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * @param cognome cognome da assegnare all'utente
	 * @return 
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * @return password password dell'utente
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password password da assegnare all'utente
	 * @return 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return email email dell'utente
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email email da assegnare all'utente
	 * @return 
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Account [nome=" + nome + ", cognome=" + cognome + ", password=" + password + ", email=" + email
				+ ", ruolo=" + ruolo + "]";
	}

	
	
	
	
	
	
	
	
	
	
	

}
