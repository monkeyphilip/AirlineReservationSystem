package Database01;

import java.sql.*;


public class TestDB {
	
	public static void main(String[] args) throws Exception {
		
		getConnection();
	}
	
	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/airlinedatabase";
			String username = "root";
			String password = "confident";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url,username, password);
			System.out.println("Connected");
			return conn;
			}
		catch(Exception ex) {
			System.out.println("e");
		}
		
		return null;
		
	}
	
	

}
