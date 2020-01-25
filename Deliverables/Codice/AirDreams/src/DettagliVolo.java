import java.util.ArrayList;

public class DettagliVolo {
	ArrayList<Volo> voli;



	public DettagliVolo() {
		voli=new ArrayList<Volo>();
	}

	public ArrayList<Volo> getVoli() {
		return voli;
	}

	public void setVoli(ArrayList<Volo> voli) {
		this.voli = voli;
	}
	
	public void add(Volo v) {
		voli.add(v);
	}
	
	@Override
	public String toString() {
		return "DettagliVolo [voli=" + voli + "]";
	}

	
	
}
