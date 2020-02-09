package gestionecarrello;

import java.util.HashMap;

import gestionevolo.Volo;

/**
 * La classe rappresenta il carrello assegnato ad ogni cliente
 */
public class Carrello {
	
	private String account;
	private HashMap<Volo, Integer> voli;
	
	/**
	 * metodo costruttore della classe Carrello
	 */
	public Carrello() {
		voli=new HashMap<Volo, Integer>();
	}

	/**
	 * metodo getter per la restituzione dell'Account relativo al carrello
	 * @return String nome dell'utente a cui il carrello è relato
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * metodo setter per la modifica dell'account relativo al carrello
	 * @param account nuovo account da impostare del carrello
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * metodo getter per la restituzione dei voli presenti nel carrello
	 * @return HashMap<Volo, Integer> lista dei voli presenti nel carrello
	 */
	public HashMap<Volo, Integer> getVoli() {
		return voli;
	}

	/**
	 * metodo setter per la modifica della lista presente nel carrello
	 * @param voli nuova lista di voli da impostare nel carrello
	 */
	public void setVoli(HashMap<Volo, Integer> voli) {
		this.voli = voli;
	}

	/**
	 * metodo ereditato dalla classe Object che permette di visualizzare le informazioni relative ad un oggetto di tipo Carrello
	 * @return String contenente lo stato dell'oggetto Carrello
	 */
	@Override
	public String toString() {
		return "Carrello [account=" + account + ", voli=" + voli + "]";
	}
}
