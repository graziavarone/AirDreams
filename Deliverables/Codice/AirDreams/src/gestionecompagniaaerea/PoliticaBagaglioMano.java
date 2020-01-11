package gestionecompagniaaerea;
import gestionecompagniaaerea.CompagniaAerea;

public class PoliticaBagaglioMano {
	private int peso;
	private String dimensioni;
	private CompagniaAerea compagnia;
	
	

	public PoliticaBagaglioMano(int peso, String dimensioni, CompagniaAerea compagnia) {
		super();
		this.peso = peso;
		this.dimensioni = dimensioni;
		this.compagnia = compagnia;
	}
	public PoliticaBagaglioMano() {
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

	public CompagniaAerea getCompagnia() {
		return compagnia;
	}
	public void setCompagnia(CompagniaAerea compagnia) {
		this.compagnia = compagnia;
	}
	@Override
	public String toString() {
		return "PoliticaBagaglio [peso=" + peso + ", dimensioni=" + dimensioni + ", compagnia="
				+ compagnia + "]";
	}
	
	
	

}
