package gestionecompagniaaerea;

public class PoliticaBagaglioStiva {
	private int peso;
	private String dimensioni;
	private float prezzo;
	private CompagniaAerea compagnia;
	
	

	public PoliticaBagaglioStiva(int peso, String dimensioni, float prezzo, CompagniaAerea compagnia) {
		super();
		this.peso = peso;
		this.dimensioni = dimensioni;
		this.prezzo = prezzo;
		this.compagnia = compagnia;
	}
	public PoliticaBagaglioStiva() {
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
