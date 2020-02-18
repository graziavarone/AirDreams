package testintegrazione;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import test.BagaglioManagerTest;
import test.BigliettiServletTest;
import test.BigliettoManagerTest;
import test.OrdineManagerTest;
import test.PagamentoServletTest;

public class TestGestioneOrdine {
	public static void main(String[] args) throws Exception {
		TestRunner.run(TestGestioneOrdine.suite());
	}
	
	public static Test suite() throws Exception {
		TestSuite test = new TestSuite();
		test.addTestSuite(BagaglioManagerTest.class);
		test.addTestSuite(BigliettiServletTest.class);
		test.addTestSuite(BigliettoManagerTest.class);
		test.addTestSuite(OrdineManagerTest.class);
		test.addTestSuite(BagaglioManagerTest.class);
		test.addTestSuite(PagamentoServletTest.class);
	
		return test;
	}
}