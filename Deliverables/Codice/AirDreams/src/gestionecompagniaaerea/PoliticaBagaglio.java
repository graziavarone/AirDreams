package gestionecompagniaaerea;

public class PoliticaBagaglio {
	int codice;
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	private int peso;
	private String dimensioni;
	private float prezzo;
	private CompagniaAerea compagnia;
	
	

	public PoliticaBagaglio(int peso, String dimensioni, float prezzo, CompagniaAerea compagnia) {
		super();
		this.peso = peso;
		this.dimensioni = dimensioni;
		this.prezzo = prezzo;
		this.compagnia = compagnia;
	}
	public PoliticaBagaglio() {
		// TODO Auto-generated constructor stub
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public String getDimensioni() {
		return dimensioni;
	}
	public void setDimensioni(String dimensioni) {
		this.dimensioni = dimensioni;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public CompagniaAerea getCompagnia() {
		return compagnia;
	}
	public void setCompagnia(CompagniaAerea compagnia) {
		this.compagnia = compagnia;
	}
	@Override
	public String toString() {
		return "PoliticaBagaglio [peso=" + peso + ", dimensioni=" + dimensioni + ", prezzo=" + prezzo + ", compagnia="
				+ compagnia + "]";
	}
	
	
	
	
	

}
