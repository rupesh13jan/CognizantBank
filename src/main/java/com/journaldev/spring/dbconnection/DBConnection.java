package com.journaldev.spring.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	private Connection connect = null;

	
	final private String host = "localhost:3306";
	final private String user = "root";
	final private String passwd = "Password-1";
	final private String database = "bookapp";
	
	public Connection connectToDB() {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");

			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://" + host + "/"
					+ database + "?" + "user=" + user + "&password=" + passwd);
			System.out.println("Inside TRY connectToDB");
			return connect;
		} catch (Exception e) {
			System.out.println("Error in TRY connectToDB"+e);
		}
		return connect;
	}
}

