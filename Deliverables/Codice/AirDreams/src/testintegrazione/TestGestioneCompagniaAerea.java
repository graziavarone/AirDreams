package testintegrazione;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import test.AggiornaCompagniaAereaTest;
import test.AggiungiCompagniaAereaTest;
import test.CompagniaAereaManagerTest;
import test.PoliticaBagaglioManagerTest;;

public class TestGestioneCompagniaAerea {
	public static void main(String[] args) throws Exception {
		TestRunner.run(TestGestioneCompagniaAerea.suite());
		
	}
	
	public static Test suite() throws Exception {
		TestSuite test = new TestSuite();
		test.addTestSuite(AggiornaCompagniaAereaTest.class);
		test.addTestSuite(AggiungiCompagniaAereaTest.class);
		test.addTestSuite(CompagniaAereaManagerTest.class);
		test.addTestSuite(PoliticaBagaglioManagerTest.class);

	
		return test;
		
	}
}
