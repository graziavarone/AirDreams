package gestionevolo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import gestionecompagniaaerea.CompagniaAerea;

/**
 * La classe contiene tutte le informazioni di un possibile volo offerto dal sistema
 */
public class Volo {
	
	private int id;
	private LocalDate dataPart;
	private float prezzo;
	private int seats;
	private LocalTime durataVolo;
	private LocalTime orarioPartenza;
	private boolean compreso;
	private CompagniaAerea ca;
	private Aeroporto aeroportoP;
	private Aeroporto aeroportoA;
	
	/**
	 * metodo costruttore della classe Volo
	 * @param otherDayDate data del volo
	 * @param prezzo prezzo del biglietto a passeggero del volo
	 * @param seats numero posti disponibili per il volo
	 * @param durataVolo durata del volo
	 * @param orarioPartenza orario di partenza del volo
	 * @param compreso booleano indicativo di bagaglio a stiva compreso o meno nel costo del biglietto del volo
	 * @param ca compagnia aerea che offre il volo
	 * @param aeroportoP aeroporto di partenza del volo
	 * @param aeroportoA aeroporto di destinazione del volo
	 */
	public Volo(LocalDate otherDayDate, float prezzo, int seats, LocalTime durataVolo, LocalTime orarioPartenza,boolean compreso, CompagniaAerea ca, Aeroporto aeroportoP, Aeroporto aeroportoA) {
		super();
		this.dataPart = otherDayDate;
		this.prezzo = prezzo;
		this.seats = seats;
		this.durataVolo = durataVolo;
		this.orarioPartenza = orarioPartenza;
		this.compreso = compreso;
		this.ca = ca;
		this.aeroportoP = aeroportoP;
		this.aeroportoA = aeroportoA;
	}
	
	/**
	 * metodo costruttore della classe Volo privo di parametri
	 */
	public Volo() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * metogo getter di restituzione dell'id identificativo del volo
	 * @return int codice del volo
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * metodo setter di modifica dell'id identificativo del volo
	 * @param i nuovo codice del volo da impostare
	 */
	public void setId(int i) {
		id=i;
	}
	
	/**
	 * metodo getter di restituzione dell'orario di arrivo a destinazione del volo
	 * @return LocalTime orario di arrivo del volo
	 */
	public LocalTime getOrarioArrivo() {
		int minuti=durataVolo.getHour()*60+durataVolo.getMinute();
		LocalTime orarioArrivo=orarioPartenza.plusMinutes(minuti);
		return orarioArrivo;
		
	}
	
	/**
	 * metodo getter di restituzione della data di arrivo del volo
	 * @return LocalDateTime data di arrivo a destinazione del volo
	 */
	public LocalDateTime getDataArrivo() {
		LocalDateTime d=LocalDateTime.of(dataPart, orarioPartenza);
		int minuti=durataVolo.getHour()*60+durataVolo.getMinute();
		LocalDateTime dataArrivo=d.plusMinutes(minuti);
		return dataArrivo;
	}
	
	/**
	 * metodo getter di restituzione della data di partenza del volo
	 * @return LocalDate data di partenza del volo
	 */
	public LocalDate getDataPartenza() {
		return dataPart;
	}
	
	/**
	 * metodo setter di modifica della data di partenza del volo
	 * @param otherDayDate nuova data di partenza del volo
	 */
	public void setOtherDayDate(LocalDate otherDayDate) {
		this.dataPart = otherDayDate;
	}
	
	/**
	 * metodo getter di restituzione del prezzo del biglietto del volo per passeggero
	 * @return float prezzo a passeggero del biglietto del volo
	 */
	public float getPrezzo() {
		return prezzo;
	}
	
	/**
	 * metodo setter di modifica del prezzo del biglietto del volo per passeggero
	 * @param prezzo nuovo prezzo a passeggero del biglietto del volo da impostare
	 */
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	/**
	 * metodo getter di restituzione dei posti disponibili per il volo
	 * @return int numero di posti disponibili per il volo
	 */
	public int getSeats() {
		return seats;
	}
	
	/**
	 * metodo setter di modifica del numero di posti disponibili per il volo
	 * @param seats nuovo numero di posti disponibili per il volo
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	/**
	 * metodo getter di restituzione della durata del volo
	 * @return LocalTime durata del volo
	 */ 
	public LocalTime getDurataVolo() {
		return durataVolo;
	}
	
	/**
	 * metodo setter di modifica della durata del volo
	 * @param durataVolo nuova durata del volo da impostare
	 */
	public void setDurataVolo(LocalTime durataVolo) {
		this.durataVolo = durataVolo;
	}
	
	/**
	 * metodo getter di restituzione dell'orario di partenza del volo
	 * @return LocalTime orario di partenza del volo
	 */
	public LocalTime getOrarioPartenza() {
		return orarioPartenza;
	}
	
	/**
	 * metodo setter di modifica dell'orario di partenza del volo
	 * @param orarioPartenza nuovo orario di partenza del volo da impostare
	 */
	public void setOrarioPartenza(LocalTime orarioPartenza) {
		this.orarioPartenza = orarioPartenza;
	}
	
	/**
	 * metodo getter di restituzione del booleano indicativo il bagaglio a stiva compreso o meno nel costo del biglietto del volo
	 * @return boolean true se il bagaglio a stiva compreso nel biglietto del volo, false in caso contrario
	 */
	public boolean isCompreso() {
		return compreso;
	}
	
	/**
	 * metodo setter di modifica del booleano indicativo il bagaglio a stiva compreso o meno nel costo del biglietto del volo
	 * @param compreso nuovo valore booleano indicativo del bagaglio a stiva compreso o meno nel costo del biglietto del volo
	 */
	public void setCompreso(boolean compreso) {
		this.compreso = compreso;
	}
	
	/**
	 * metodo getter di restituzione della compagnia aerea che offre il volo
	 * @return CompagniaAerea compagnia aerea che offre il volo
	 */
	public CompagniaAerea getCa() {
		return ca;
	}
	
	/**
	 * metodo setter di modifica della compagnia aerea che offre il volo
	 * @param ca nuova compagnia aerea che offre il volo da impostare
	 */
	public void setCa(CompagniaAerea ca) {
		this.ca = ca;
	}
	
	/**
	 * metodo getter di restituzione dell'aeroporto di partenza del volo
	 * @return Aeroporto aeroporto di partenza del volo
	 */
	public Aeroporto getAeroportoP() {
		return aeroportoP;
	}
	
	/**
	 * metodo setter di modifica dell'aeroporto di partenza del volo
	 * @param aeroportoP nuovo aeroporto di partenza del volo da impostare
	 */
	public void setAeroportoP(Aeroporto aeroportoP) {
		this.aeroportoP = aeroportoP;
	}
	
	/**
	 * metodo getter di restituzione dell'aeroporto di destinazione del volo
	 * @return Aeroporto aeroporto di destinazione del volo
	 */
	public Aeroporto getAeroportoA() {
		return aeroportoA;
	}
	
	/**
	 * metodo setter di modifica dell'aeroporto di destinazione del volo
	 * @param aeroportoA nuovo aeroporto di destinazione del volo da impostare
	 */
	public void setAeroportoA(Aeroporto aeroportoA) {
		this.aeroportoA = aeroportoA;
	}
	
	/**
	 * metodo ereditato dalla classe Object che permette di visualizzare le informazioni relative ad un oggetto di tipo Volo
	 * @return String contenente lo stato dell'oggetto Volo
	 */
	@Override
	public String toString() {
		return "Volo [id=" + id + ", dataPart=" + dataPart + ", prezzo=" + prezzo + ", seats=" + seats + ", durataVolo="
				+ durataVolo + ", orarioPartenza=" + orarioPartenza + ", compreso=" + compreso + ", ca=" + ca
				+ ", aeroportoP=" + aeroportoP + ", aeroportoA=" + aeroportoA + "]";
	}
}

