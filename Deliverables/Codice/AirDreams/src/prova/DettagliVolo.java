package prova;

public class DettagliVolo {
	//sarebbe l'attuale oggetto VOLO del nostro class diagram
	//volo che avevamo pensato all'inizio
	//da un aeroporto si arriva ad un altro eeeeeeee STOP!
	private int diVolo;
	private String dataPartenza;
	private float price;
	private int postiDisponibili;
	private String durata;
	private String orarioPartenza;
	private Aeroporto aeroportoPartenza;
	private Aeroporto aeroportoDestinazione;
	
	public DettagliVolo(int diVolo, String dataPartenza, float price, int postiDisponibili, String durata,
			String orarioPartenza, Aeroporto aeroportoPartenza, Aeroporto aeroportoDestinazione) {
		this.diVolo = diVolo;
		this.dataPartenza = dataPartenza;
		this.price = price;
		this.postiDisponibili = postiDisponibili;
		this.durata = durata;
		this.orarioPartenza = orarioPartenza;
		this.aeroportoPartenza = aeroportoPartenza;
		this.aeroportoDestinazione = aeroportoDestinazione;
	}

	public int getDiVolo() {
		return diVolo;
	}
	
	public void setDiVolo(int diVolo) {
		this.diVolo = diVolo;
	}
	
	public String getDataPartenza() {
		return dataPartenza;
	}
	
	public void setDataPartenza(String dataPartenza) {
		this.dataPartenza = dataPartenza;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public int getPostiDisponibili() {
		return postiDisponibili;
	}
	
	public void setPostiDisponibili(int postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
	}
	
	public String getDurata() {
		return durata;
	}
	
	public void setDurata(String durata) {
		this.durata = durata;
	}
	
	public String getOrarioPartenza() {
		return orarioPartenza;
	}
	
	public void setOrarioPartenza(String orarioPartenza) {
		this.orarioPartenza = orarioPartenza;
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
	
	@Override
	public String toString() {
		return "DettagliVolo [diVolo=" + diVolo + ", dataPartenza=" + dataPartenza + ", price=" + price
				+ ", postiDisponibili=" + postiDisponibili + ", durata=" + durata + ", orarioPartenza=" + orarioPartenza
				+ ", aeroportoPartenza=" + aeroportoPartenza + ", aeroportoDestinazione=" + aeroportoDestinazione + "]";
	}
}
