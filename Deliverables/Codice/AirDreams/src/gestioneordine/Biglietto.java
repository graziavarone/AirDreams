package gestioneordine;

import java.util.Collection;
import java.util.HashSet;

import gestionevolo.Volo;

public class Biglietto {
	private String nome;
	private String cognome;
	private Sesso sesso;
	private float prezzoBiglietto;
	private int codBiglietto;
	private int ordine;
	private Volo volo;
	private HashSet<BagaglioStiva> bagagliStiva;
	private BagaglioMano bagaglioMano;
	
	public Biglietto(String nome, String cognome, Sesso sesso, float prezzoBiglietto, Volo volo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.prezzoBiglietto = prezzoBiglietto;
		this.volo=volo;
		bagagliStiva=new HashSet<BagaglioStiva>();
	}
	
	
	

	
	public BagaglioMano getBagaglioMano() {
		return bagaglioMano;
	}





	public void setBagaglioMano(BagaglioMano bagaglioMano) {
		this.bagaglioMano = bagaglioMano;
	}





	public Collection<BagaglioStiva> getBagagliStiva() {
		return bagagliStiva;
	}





	public void setBagagliStiva(HashSet<BagaglioStiva> bagagliStiva) {
		this.bagagliStiva = bagagliStiva;
	}





	public Volo getVolo() {
		return volo;
	}




	public void setVolo(Volo volo) {
		this.volo = volo;
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
				+ prezzoBiglietto + ", codBiglietto=" + codBiglietto + ", ordine=" + ordine + ", volo=" + volo + "]";
	}


	
	
}
