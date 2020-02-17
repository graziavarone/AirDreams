package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DriverManagerConnectionPool;
import gestionevolo.Aeroporto;
import gestionevolo.AeroportoManager;

public class AeroportoManagerTest {
	private AeroportoManager aeroportoManager=new AeroportoManager();
	
	@Before
	public void setUp() throws Exception {	
		DbPopulator.initializeDatabase();
		//DriverManagerConnectionPool.setTest(true);
	}
	
	@Test 
	public void getAeroportyByCity() throws Exception{
		ArrayList<Aeroporto> aeroporti=aeroportoManager.getAeroportiByCity("N");
		
		assertTrue(aeroporti.size()>0);
	}
	
	@Test 
	public void findAeroportoById() throws Exception{
		Aeroporto aeroporto=aeroportoManager.findAeroportoById("NAP");
		
		assertNotNull(aeroporto);
	}
	
	@After
	public void tearDown() throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
}