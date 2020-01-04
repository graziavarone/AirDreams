package gestioneUtente;

public class Account {

	private String nome;
	private String cognome;
	private String email;
	private String password;
	private CompagniaAerea compagniaAerea;
	private Ruolo ruolo;
	
	public Account() {
		super();
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public CompagniaAerea getCompagniaAerea() {
		return compagniaAerea;
	}
	
	public void setCompagniaAerea(CompagniaAerea compagniaAerea) {
		this.compagniaAerea = compagniaAerea;
	}
	
	public Ruolo getRuolo() {
		return ruolo;
	}
	
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	
	@Override
	public String toString() {
		return "Account [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", password=" + password
				+ ", compagniaAerea=" + compagniaAerea + ", ruolo=" + ruolo + "]";
	}
}
