package gestioneordine;


public class BagaglioStiva {
	private int peso;
	private String dimensioni;
	private Biglietto biglietto;
	private float prezzo;
	private int quantity;
	

	public BagaglioStiva(int peso, String dimensioni, Biglietto biglietto, float prezzo,int quantity) {
		super();
		this.peso = peso;
		this.dimensioni = dimensioni;
		this.biglietto = biglietto;
		this.prezzo=prezzo;
		this.quantity=quantity;
	}
	public BagaglioStiva() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	
	
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	@Override
	public String toString() {
		return "BagaglioStiva [peso=" + peso + ", dimensioni=" + dimensioni + ", biglietto=" + biglietto + ", prezzo="
				+ prezzo + ", quantity=" + quantity + "]";
	}

	
	
	

}
