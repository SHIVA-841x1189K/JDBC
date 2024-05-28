package com.EmpireJDBC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;
public class StoreProcedure_3 {
	private static Connection connection;
	private static CallableStatement statement;
	private static ResultSet res;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/Radha";
		String username = "root";
		String password = "2236";

		try {
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.prepareCall("{call getStudentDetails(?)}");
			System.out.println("Enter a Student Age: ");
			int Age = sc.nextInt();
			statement.setInt(1, Age);
			statement.execute();
			res = statement.getResultSet();
			System.out.println(
					"+-------------------------------------------------------------------------------------------------+");
			while (res.next()) {
				int sID = res.getInt("ID");
				String sName = res.getString("Name");
				int sAge = res.getInt("Age");
				String sEmail = res.getString("EmailId");
				int sCourseID = res.getInt("CourseId");
				String sCollege = res.getString("college");
				System.out.printf("| %-5d | %-20s | %-5d | %-30s | %-10d | %-10s | \n", sID, sName, sAge, sEmail,
						sCourseID, sCollege);
			}
			System.out.println(
					"+-------------------------------------------------------------------------------------------------+");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}
