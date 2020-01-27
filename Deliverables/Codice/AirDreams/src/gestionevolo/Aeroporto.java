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
	
	@Override public boolean equals(Object obj) { 	
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
