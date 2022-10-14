package dao;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBHandler {
	public static Connection estConnection() {
		Connection connect = null;
		try {
			Class.forName("org.postgresql.Driver");
			connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/HolidayPackage","postgres","shiv");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return connect;
	}
}
