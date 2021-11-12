package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	// Connection is declared here because we want to use it 
	// accross methods, also make sure there is only one connection
	// to the DB at any point of time
	static Connection conn = null;
	
	// the static block makes sure that the driver is loaded only once
	// because static blocks are executed only once after the class
	// is loaded in memory 
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// this method is static so that we can call the method with 
	// the class name
	public static Connection makeConnection() {
		// implemented singleton pattern here
		if (conn == null) {
			try {
				conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shoppingapp", "postgres", "boomorboo12");
		           if (conn != null) {
		                System.out.println("Connected to the PostgreSQL server successfully.");
		            } else {
		                System.out.println("Failed to make connection!");
		            }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
