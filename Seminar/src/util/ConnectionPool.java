package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

	private static ConnectionPool pool = null;
	private static Connection connection = null;

	private ConnectionPool() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURL = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9372045";
			String username = "username";
			String password = "password";
			connection = DriverManager.getConnection(dbURL, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static synchronized ConnectionPool getInstance() {
		if (pool == null) {
			pool = new ConnectionPool();
		}
		return pool;
	}

	public Connection getConnection() {
		return connection;
	}

	public void freeConnection(Connection c) {
//		try {
//			c.close();
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
	}
}