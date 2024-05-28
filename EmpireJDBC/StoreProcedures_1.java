package com.EmpireJDBC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class StoreProcedures_1 {

	private static Scanner sc;
	private static Connection connection;
	private static CallableStatement Statement;

	public static void main(String[] args) {
		 sc = new Scanner(System.in);

		String url = "jdbc:mysql://localhost:3306/Radha";
		String username = "root";
		String password = "2236";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			Statement = connection.prepareCall("{call countStudents(?,?)}");
			System.out.println("Age: ");
			int sAge = sc.nextInt();
			Statement.setInt(1, sAge);
			Statement.registerOutParameter(2, Types.INTEGER);
			Statement.execute();			
			int noOfStudents = Statement.getInt(2);
			System.out.println("noOfStudents: " + noOfStudents + " [Age: " + sAge + " ].");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}

	}

}
