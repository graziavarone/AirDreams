package gestionevolo;

public class Aeroporto {
	private String codice;
	private String nome;
	private String city;
	private String stato;
	
	public Aeroporto(){
		
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	@Override
	public String toString() {
		return "Aeroporto [codice=" + codice + ", nome=" + nome + ", city=" + city + ", stato=" + stato + "]";
	}
	
}
