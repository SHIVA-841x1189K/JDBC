package com.EmpireJDBC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class StoreProcedure_2 {

	private static Connection connection;
	private static CallableStatement statement;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/Radha";
		String username = "root";
		String password = "2236";
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.prepareCall("{call countStudentsSpecificAge(?,?)}");
			System.out.println("Enter a Age: ");
			int sAge = sc.nextInt();
			statement.setInt(1, sAge);
			statement.registerOutParameter(2, Types.INTEGER);
			statement.execute();
			int NoOfStudents = statement.getInt(2);
			System.out.println("NoOfStudents: " + NoOfStudents + " [ Age: " + sAge + " ]." );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}

}
