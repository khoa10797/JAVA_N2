package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	public static Connection getConnection() throws SQLException{
		final String USERNAME = "sa";
		final String PASSWORD = "1234";
		final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThiSinh";
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
