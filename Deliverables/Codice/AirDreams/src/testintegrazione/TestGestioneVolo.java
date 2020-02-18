package testintegrazione;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import test.AeroportoManagerTest;

import test.AggiungiVoloTest;

import test.ModificaVoloTest;

import test.RicercaVoliTest;
import test.RicercaVoloGestoreTest;

import test.VoloManagerTest;

public class TestGestioneVolo {
	public static void main(String[] args) throws Exception {
		TestRunner.run(TestGestioneVolo.suite());
	}
	
	public static Test suite() throws Exception {
		TestSuite test = new TestSuite();
		test.addTestSuite(AeroportoManagerTest.class);
		test.addTestSuite(AggiungiVoloTest.class);
		test.addTestSuite(ModificaVoloTest.class);
		test.addTestSuite(RicercaVoliTest.class);
		test.addTestSuite(RicercaVoloGestoreTest.class);
		test.addTestSuite(VoloManagerTest.class);
	
		return test;
	}
}

