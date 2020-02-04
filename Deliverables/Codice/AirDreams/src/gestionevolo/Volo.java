package gestionevolo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import gestionecompagniaaerea.CompagniaAerea;

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
	public Volo(LocalDate otherDayDate, float prezzo, int seats, LocalTime durataVolo, LocalTime orarioPartenza,
			boolean compreso, CompagniaAerea ca, Aeroporto aeroportoP, Aeroporto aeroportoA) {
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
	public Volo() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int i) {
		id=i;
	}
	
	public LocalTime getOrarioArrivo() {
		int minuti=durataVolo.getHour()*60+durataVolo.getMinute();
		LocalTime orarioArrivo=orarioPartenza.plusMinutes(minuti);
		return orarioArrivo;
		
	}
	
	public LocalDateTime getDataArrivo() {
		LocalDateTime d=LocalDateTime.of(dataPart, orarioPartenza);
		int minuti=durataVolo.getHour()*60+durataVolo.getMinute();
		LocalDateTime dataArrivo=d.plusMinutes(minuti);
		return dataArrivo;
	}
	public LocalDate getDataPartenza() {
		return dataPart;
	}
	public void setOtherDayDate(LocalDate otherDayDate) {
		this.dataPart = otherDayDate;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public LocalTime getDurataVolo() {
		return durataVolo;
	}
	public void setDurataVolo(LocalTime durataVolo) {
		this.durataVolo = durataVolo;
	}
	public LocalTime getOrarioPartenza() {
		return orarioPartenza;
	}
	public void setOrarioPartenza(LocalTime orarioPartenza) {
		this.orarioPartenza = orarioPartenza;
	}
	public boolean isCompreso() {
		return compreso;
	}
	public void setCompreso(boolean compreso) {
		this.compreso = compreso;
	}
	public CompagniaAerea getCa() {
		return ca;
	}
	public void setCa(CompagniaAerea ca) {
		this.ca = ca;
	}
	public Aeroporto getAeroportoP() {
		return aeroportoP;
	}
	public void setAeroportoP(Aeroporto aeroportoP) {
		this.aeroportoP = aeroportoP;
	}
	public Aeroporto getAeroportoA() {
		return aeroportoA;
	}
	public void setAeroportoA(Aeroporto aeroportoA) {
		this.aeroportoA = aeroportoA;
	}
	@Override
	public String toString() {
		return "Volo [id=" + id + ", dataPart=" + dataPart + ", prezzo=" + prezzo + ", seats=" + seats + ", durataVolo="
				+ durataVolo + ", orarioPartenza=" + orarioPartenza + ", compreso=" + compreso + ", ca=" + ca
				+ ", aeroportoP=" + aeroportoP + ", aeroportoA=" + aeroportoA + "]";
	}

	
	
	

}