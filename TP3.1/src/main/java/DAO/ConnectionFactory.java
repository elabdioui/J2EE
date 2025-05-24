package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static Connection cnx;
	private static String USER_NAME = "root"; 
	 private static String PASSWORD = "0000"; 
	 private static String URL= "jdbc:mysql://localhost:3306/bdiwm2024";
	 
	 private  ConnectionFactory() {} 
	   
	 static { 
		  try { 
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   cnx = DriverManager.getConnection(URL, USER_NAME, PASSWORD); 
		  } catch (Exception ex) {
		   ex.printStackTrace(); 
		  } 
		}
	 public static Connection getConnection() { 
	      return cnx; 
	    } 
	 
	 
	}
	 
	 
	 


