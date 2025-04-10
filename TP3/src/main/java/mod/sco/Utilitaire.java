package mod.sco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utilitaire {
	
	private static Connection conn;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/SCOLARITE","root","");
			
		}catch(ClassNotFoundException e) {
			System.out.println("driver introuvable");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	

	public static Connection getConnection() {
		return conn;
		}
	
}
	
