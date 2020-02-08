package gestioneordine;

public class Biglietto {
	private String nome;
	private String cognome;
	private Sesso sesso;
	private float prezzoBiglietto;
	private int codBiglietto;
	private int ordine;
	
	public Biglietto(String nome, String cognome, Sesso sesso, float prezzoBiglietto) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.prezzoBiglietto = prezzoBiglietto;
	}

	
	public int getOrdine() {
		return ordine;
	}


	public void setOrdine(int ordine) {
		this.ordine = ordine;
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

	public Sesso getSesso() {
		return sesso;
	}

	public void setSesso(Sesso sesso) {
		this.sesso = sesso;
	}

	public float getPrezzoBiglietto() {
		return prezzoBiglietto;
	}

	public void setPrezzoBiglietto(float prezzoBiglietto) {
		this.prezzoBiglietto = prezzoBiglietto;
	}

	public int getCodBiglietto() {
		return codBiglietto;
	}

	public void setCodBiglietto(int codBiglietto) {
		this.codBiglietto = codBiglietto;
	}

	@Override
	public String toString() {
		return "Biglietto [nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso + ", prezzoBiglietto="
				+ prezzoBiglietto + ", codBiglietto=" + codBiglietto + "]";
	}
	
	
	
	
}
