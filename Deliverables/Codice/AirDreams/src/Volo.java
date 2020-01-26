import java.sql.Date;
import java.time.LocalDate;

public class Volo {
	int id;
	public  String aeroportoPartenza; //dovrebbe essere classe aeroporto
	public String aeroportoArrivo;
	
	public Volo(String aeroportoPartenza, String aeroportoArrivo) {
		this.aeroportoPartenza = aeroportoPartenza;
		this.aeroportoArrivo = aeroportoArrivo;
	}


	public Volo() {
		// TODO Auto-generated constructor stub
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
	
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Volo [id=" + id + ", aeroportoPartenza=" + aeroportoPartenza + ", aeroportoArrivo=" + aeroportoArrivo
				+ "]";
	}





	
	
	
	


}
