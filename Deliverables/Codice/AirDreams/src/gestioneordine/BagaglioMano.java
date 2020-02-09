package gestioneordine;


public class BagaglioMano {
	private int peso;
	private String dimensioni;
	private Biglietto biglietto;
	

	public BagaglioMano(int peso, String dimensioni, Biglietto biglietto) {
		super();
		this.peso = peso;
		this.dimensioni = dimensioni;
		this.biglietto = biglietto;
	}
	public BagaglioMano() {
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

	public Biglietto getBiglietto() {
		return biglietto;
	}
	public void setBiglietto(Biglietto biglietto) {
		this.biglietto = biglietto;
	}
	@Override
	public String toString() {
		return "BagaglioMano [peso=" + peso + ", dimensioni=" + dimensioni + ", biglietto="
				+ biglietto + "]";
	}
	
	
	

}
