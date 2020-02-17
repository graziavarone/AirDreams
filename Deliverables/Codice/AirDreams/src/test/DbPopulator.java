package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import db.DriverManagerConnectionPool;

import org.apache.ibatis.jdbc.ScriptRunner;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;


import db.DriverManagerConnectionPool;

public class DbPopulator {
	public static void initializeDatabase() throws SQLException, FileNotFoundException {

		DriverManagerConnectionPool.setTest(true);
		System.out.println("In dpPopulator test Ë "+DriverManagerConnectionPool.isTest());
		Connection conn =  DriverManagerConnectionPool.getConnection();
		ScriptRunner sr = new ScriptRunner(conn);
		java.io.Reader reader = new BufferedReader(new FileReader("inserimento.sql"));
		sr.runScript(reader);
		
		//DriverManagerConnectionPool.releaseConnection(conn);
		conn.close();
		
	}

}