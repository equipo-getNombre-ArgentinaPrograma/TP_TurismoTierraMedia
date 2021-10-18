package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:sqlite:db/tierra-media.db";
		if (connection == null)
			connection = DriverManager.getConnection(url);
		return connection;
	}
}
