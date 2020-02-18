package testintegrazione;

import junit.framework.Test;
import junit.textui.TestRunner;
import test.AggiungiCartaTest;
import test.CartaDiCreditoManagerTest;
import test.LoginTest;
import test.ModificaAccountTest;
import test.ModificaInfoPersonaliTest;
import test.RegistrazioneTest;
import test.UtenteManagerTest;
import junit.framework.TestSuite;

public class TestGestioneUtente {
	public static void main(String[] args) throws Exception {
		TestRunner.run(TestGestioneUtente.suite());
	}
	
	public static Test suite() throws Exception {
		TestSuite test = new TestSuite();
		test.addTestSuite(AggiungiCartaTest.class);
		test.addTestSuite(CartaDiCreditoManagerTest.class);
		test.addTestSuite(LoginTest.class);
		test.addTestSuite(ModificaAccountTest.class);
		test.addTestSuite(ModificaInfoPersonaliTest.class);
		test.addTestSuite(RegistrazioneTest.class);
		test.addTestSuite(UtenteManagerTest.class);
		return test;
		
	}
}
