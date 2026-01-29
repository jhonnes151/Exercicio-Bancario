package bancoAvancado.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static final String URL = "Jdbc:mysql://localhost:3306/banco";
	private static final String USER = "root";
	private static final String PASS = "1234";
	
	public static Connection getConnection() throws Exception {
		return DriverManager.getConnection(URL, USER, PASS);
	}
	
}
