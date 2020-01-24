package prova;

import java.util.ArrayList;

public class Volo {

	private Aeroporto aeroportoPartenza; //aeroporto da dove si parte
	private Aeroporto aeroportoDestinazione; //aeroporto a dove si arriva (il finale finale...quello dell'ultimo scalo per intenderci)
	private ArrayList<DettagliVolo> voli; //lista di tutti i voli che devono essere presi (possono essere 1,2,3...infiniti)
	private int numeroScali; //recuperato tramite la dimensione dell'arrayList di voli
	
	public Volo(Aeroporto aeroportoPartenza, Aeroporto aeroportoDestinazione, ArrayList<DettagliVolo> voli) {
		this.aeroportoPartenza = aeroportoPartenza;
		this.aeroportoDestinazione = aeroportoDestinazione;
		this.voli = voli;
		this.numeroScali = voli.size();
	}

	public Aeroporto getAeroportoPartenza() {
		return aeroportoPartenza;
	}
	
	public void setAeroportoPartenza(Aeroporto aeroportoPartenza) {
		this.aeroportoPartenza = aeroportoPartenza;
	}
	
	public Aeroporto getAeroportoDestinazione() {
		return aeroportoDestinazione;
	}
	
	public void setAeroportoDestinazione(Aeroporto aeroportoDestinazione) {
		this.aeroportoDestinazione = aeroportoDestinazione;
	}
	
	public ArrayList<DettagliVolo> getVoli() {
		return voli;
	}
	
	public void setVoli(ArrayList<DettagliVolo> voli) {
		this.voli = voli;
	}
	
	public int getNumeroScali() {
		return numeroScali;
	}
	
	public void setNumeroScali(int numeroScali) {
		this.numeroScali = numeroScali;
	}
	
	@Override
	public String toString() {
		return "Volo [aeroportoPartenza=" + aeroportoPartenza + ", aeroportoDestinazione=" + aeroportoDestinazione
				+ ", voli=" + voli + ", numeroScali=" + numeroScali + "]";
	}
}
