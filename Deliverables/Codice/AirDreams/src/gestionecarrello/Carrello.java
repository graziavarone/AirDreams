package gestionecarrello;

import java.util.HashMap;

import gestionevolo.Volo;

public class Carrello {
	private String account;
	private HashMap<Volo, Integer> voli;
	
	public Carrello() {
		voli=new HashMap<Volo, Integer>();
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public HashMap<Volo, Integer> getVoli() {
		return voli;
	}

	public void setVoli(HashMap<Volo, Integer> voli) {
		this.voli = voli;
	}


	@Override
	public String toString() {
		return "Carrello [account=" + account + ", voli=" + voli + "]";
	}
	
	
	

}
