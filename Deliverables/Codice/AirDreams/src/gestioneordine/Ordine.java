package gestioneordine;

import java.time.LocalDate;


import gestioneutente.Account;
import gestioneutente.CartaDiCredito;

public class Ordine {
	private int codOrdine;
	private LocalDate dataAcquisto;
	private Account account;
	private CartaDiCredito cartaDiCredito;
	
	public Ordine(LocalDate dataAcquisto, Account account, CartaDiCredito cartaDiCredito
			) {
		super();
		this.dataAcquisto = dataAcquisto;
		this.account = account;
		this.cartaDiCredito = cartaDiCredito;

	}

	public int getCodOrdine() {
		return codOrdine;
	}

	public void setCodOrdine(int codOrdine) {
		this.codOrdine = codOrdine;
	}

	public LocalDate getDataAcquisto() {
		return dataAcquisto;
	}

	public void setDataAcquisto(LocalDate dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public CartaDiCredito getCartaDiCredito() {
		return cartaDiCredito;
	}

	public void setCartaDiCredito(CartaDiCredito cartaDiCredito) {
		this.cartaDiCredito = cartaDiCredito;
	}

	
	@Override
	public String toString() {
		return "Ordine [codOrdine=" + codOrdine + ", dataAcquisto=" + dataAcquisto + ", account=" + account
				+ ", cartaDiCredito=" + cartaDiCredito + "]";
	}
	
	
	
	
	
}
