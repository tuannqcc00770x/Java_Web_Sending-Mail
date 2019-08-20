package connection;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseContext {
	public Connection getConnection(String hostName, String dbName, String username, String password) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useUnicode=true&characterEncoding=UTF-8";
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
	
	public Connection getConnection() throws Exception {
		String hostName = "localhost";
		String dbName= "cc00770xmail";
		String username = "root";
		String password = "1234";
		return getConnection(hostName, dbName, username, password);
	}	
}
