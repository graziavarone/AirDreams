import java.sql.Date;
import java.time.LocalDate;

public class Volo {
	public  String aeroportoPartenza; //dovrebbe essere classe aeroporto
	public String aeroportoArrivo;
	
	public Volo(String aeroportoPartenza, String aeroportoArrivo) {
		this.aeroportoPartenza = aeroportoPartenza;
		this.aeroportoArrivo = aeroportoArrivo;
	}


	public String getAeroportoPartenza() {
		return aeroportoPartenza;
	}

	public void setAeroportoPartenza(String aeroportoPartenza) {
		this.aeroportoPartenza = aeroportoPartenza;
	}

	public String getAeroportoArrivo() {
		return aeroportoArrivo;
	}

	public void setAeroportoArrivo(String aeroportoArrivo) {
		this.aeroportoArrivo = aeroportoArrivo;
	}


	@Override
	public String toString() {
		return "Volo [aeroportoPartenza=" + aeroportoPartenza + ", aeroportoArrivo=" + aeroportoArrivo + "]";
	}


	
	
	
	


}
