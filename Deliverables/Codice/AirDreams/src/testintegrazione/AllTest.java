package testintegrazione;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import test.CarrelloManagerTest;

public class AllTest {
	public static void main(String[] args) throws Exception {
		TestRunner.run(AllTest.suite());
		
	}
	
	public static Test suite() throws Exception {
		TestSuite test = new TestSuite();
		test.addTest(TestGestioneCarrello.suite());
		test.addTest(TestGestioneCompagniaAerea.suite());
		test.addTest(TestGestioneOrdine.suite());
		test.addTest(TestGestioneUtente.suite());
		test.addTest(TestGestioneVolo.suite());

		return test;
	}
}
