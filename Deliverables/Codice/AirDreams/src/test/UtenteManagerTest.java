package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import db.DriverManagerConnectionPool;
import gestioneutente.Account;
import gestioneutente.CartaDiCredito;
import gestioneutente.UtenteManager;

public class UtenteManagerTest {
	private UtenteManager utenteManager=new UtenteManager();
	
	@Before
	public void setUp() throws Exception {	
		//DbPopulator.initializeDatabase();
		DriverManagerConnectionPool.setTest(true);
	}
	
	@Test
	public void signIn() throws Exception {
		Account account=new Account();
		account.setEmail("rosaria@gmail.com");
		account.setPassword("Rosaria1998");
		
		Account accountNuovo=utenteManager.signIn(account);
		
		assertNotNull(accountNuovo);
	}
	
	@Test
	public void signUp() throws Exception {
		Account account=new Account("Madonna", "Mia", "madonnaMia@gmail.com", "madonnaMado123");
		
		boolean result=utenteManager.signUp(account);
		
		utenteManager.eliminaAccount("madonnaMia@gmail.com");
		
		assertTrue(result);
	}
	
	@Test
	public void findAccountByEmail() throws Exception {

		Account account=utenteManager.findAccountByEmail("rosaria@gmail.com");
		
		assertNotNull(account);
		
	}
	
	@Test
	public void eliminaAccount() throws Exception {
		Account account=new Account("Madonna", "Mia", "madonnaMia@gmail.com", "madonnaMado123");
		
		utenteManager.signUp(account);
		
		boolean result=utenteManager.eliminaAccount("madonnaMia@gmail.com");
		
		assertTrue(result);
	}
	
	@Test
	public void visualizzaInfoUtente() throws Exception {

		Account account=utenteManager.visualizzaInfoUtente("rosaria@gmail.com");
		
		assertNotNull(account);
		
	}
	
	@Test
	public void aggiornaProfilo() throws Exception {
		Account accountVecchio=utenteManager.findAccountByEmail("rosaria@gmail.com");
		Account accountNuovo=new Account("RosRosRos", "Rossi", "rosaria@gmail.com", "Rosaria1998");
		boolean result=utenteManager.aggiornaProfilo(accountVecchio, accountNuovo);
		
		assertTrue(result);
		
	}
	
	@Test
	public void getAllUsers() throws Exception {

		ArrayList<Account> account=utenteManager.getAllUsers();
		
		assertEquals(3, account.size());
		
	}
	
	@Test
	public void findAccountByLetter() throws Exception {

		ArrayList<Account> account=utenteManager.findAccountByLetter("R", "R");
		
		assertEquals(1, account.size());
		
	}
	
	
	@Test
	public void modificaAccount() throws Exception {
		Account accountVecchio=utenteManager.findAccountByEmail("rosaria@gmail.com");
		Account accountNuovo=new Account("RosRosRos2", "Rossi", "rosaria@gmail.com", "Rosaria1998");
		boolean result=utenteManager.modificaAccount(accountVecchio.getEmail(), accountNuovo);
		
		assertTrue(result);
		
	}
}
