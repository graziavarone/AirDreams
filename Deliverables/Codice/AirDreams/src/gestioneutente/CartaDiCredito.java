package gestioneutente;

import java.sql.Date;

/**
 * Questa classe rappresenta l'entita CartaDiCredito associata a un cliente registrato.
 */

public class CartaDiCredito {
	private String nCarta;
	private String titolare;
	private Date dataScadenza;
	private int cvc;
	private Account account;
	
	public CartaDiCredito() {
		
	}
	
	/**
	 * @param nCarta nCarta della carta di credito
	 * @param titolare titolare della carta di credito
	 * @param dataScadenza dataScadenza della carta di credito
	 * @param cvc cvc della carta di credito
	 * @return 
	 */
	public CartaDiCredito(String nCarta, String titolare, Date dataScadenza, int cvc) {
		super();
		this.nCarta = nCarta;
		this.titolare = titolare;
		this.dataScadenza = dataScadenza;
		this.cvc = cvc;
	}

	public String getnCarta() {
		return nCarta;
	}

	public void setnCarta(String nCarta) {
		this.nCarta = nCarta;
	}

	public String getTitolare() {
		return titolare;
	}

	public void setTitolare(String titolare) {
		this.titolare = titolare;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "CartaDiCredito [nCarta=" + nCarta + ", titolare=" + titolare + ", dataScadenza=" + dataScadenza
				+ ", cvc=" + cvc + ", account=" + account + "]";
	}
	
}
