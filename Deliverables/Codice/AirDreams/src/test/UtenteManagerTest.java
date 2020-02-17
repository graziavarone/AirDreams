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
import junit.framework.TestCase;

public class UtenteManagerTest extends TestCase{
	private UtenteManager utenteManager=new UtenteManager();
	
	@Before
	public void setUp() throws Exception {	
		DbPopulator.initializeDatabase();
		//DriverManagerConnectionPool.setTest(true);
	}
	
	@Test
	public void testSignIn() throws Exception {
		Account account=new Account();
		account.setEmail("rosaria@gmail.com");
		account.setPassword("Rosaria1998");
		
		Account accountNuovo=utenteManager.signIn(account);
		
		assertNotNull(accountNuovo);
	}
	
	@Test
	public void testSignUp() throws Exception {
		Account account=new Account("Madonna", "Mia", "madonnaMia@gmail.com", "madonnaMado123");
		
		boolean result=utenteManager.signUp(account);
		
		utenteManager.eliminaAccount("madonnaMia@gmail.com");
		
		assertTrue(result);
	}
	
	@Test
	public void testFindAccountByEmail() throws Exception {

		Account account=utenteManager.findAccountByEmail("rosaria@gmail.com");
		
		assertNotNull(account);
		
	}
	
	@Test
	public void testEliminaAccount() throws Exception {
		Account account=new Account("Madonna", "Mia", "madonnaMia@gmail.com", "madonnaMado123");
		
		utenteManager.signUp(account);
		
		boolean result=utenteManager.eliminaAccount("madonnaMia@gmail.com");
		
		assertTrue(result);
	}
	
	@Test
	public void testVisualizzaInfoUtente() throws Exception {

		Account account=utenteManager.findAccountByEmail("rosaria@gmail.com");
		
		assertNotNull(account);
		
	}
	
	@Test
	public void testAggiornaProfilo() throws Exception {
		Account accountVecchio=utenteManager.findAccountByEmail("rosaria@gmail.com");
		Account accountNuovo=new Account("RosRosRos", "Rossi", "rosaria@gmail.com", "Rosaria1998");
		boolean result=utenteManager.aggiornaProfilo(accountVecchio, accountNuovo);
		
		assertTrue(result);
		
	}
	
	@Test
	public void testGetAllUsers() throws Exception {

		ArrayList<Account> account=utenteManager.getAllUsers();
		
		assertEquals(4, account.size());
		
	}
	
	@Test
	public void testFindAccountByLetter() throws Exception {

		ArrayList<Account> account=utenteManager.findAccountByLetter("R", "R");
		
		assertEquals(1, account.size());
		
	}
	
	
	@Test
	public void testModificaAccount() throws Exception {
		Account accountVecchio=utenteManager.findAccountByEmail("rosaria@gmail.com");
		Account accountNuovo=new Account("RosRosRos2", "Rossi", "rosaria@gmail.com", "Rosaria1998");
		boolean result=utenteManager.aggiornaProfilo(accountVecchio, accountNuovo);
		
		assertTrue(result);
		
	}
}
