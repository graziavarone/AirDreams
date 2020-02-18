package testintegrazione;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import test.CarrelloManagerTest;


public class TestGestioneCarrello {
	public static void main(String[] args) throws Exception {
		TestRunner.run(TestGestioneCarrello.suite());
		
	}
	
	public static Test suite() throws Exception {
		TestSuite test = new TestSuite();
		test.addTestSuite(CarrelloManagerTest.class);

	
		return test;
		
	}

}
