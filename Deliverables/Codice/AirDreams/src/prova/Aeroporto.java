package prova;

public class Aeroporto {

	private String codice;
	private String citta;
	private String nome;
	private String stato;
	
	public Aeroporto(String codice, String citta, String nome, String stato) {
		this.codice = codice;
		this.citta = citta;
		this.nome = nome;
		this.stato = stato;
	}

	public String getCodice() {
		return codice;
	}
	
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	public String getCitta() {
		return citta;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getStato() {
		return stato;
	}
	
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	@Override
	public String toString() {
		return "Aeroporto [codice=" + codice + ", citta=" + citta + ", nome=" + nome + ", stato=" + stato + "]";
	}
}
