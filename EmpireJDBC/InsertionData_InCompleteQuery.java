package com.EmpireJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertionData_InCompleteQuery {

	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement statement2;
	private static ResultSet res;
	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/Radha";
		String username = "root";
		String password = "2236";

		String Query = "INSERT INTO `Students` (`Name`, `Age`, `EmailId`, `CourseId`, `college`) VALUES (?,?,?,?,?);";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Old Students Table: ");
			Display(connection, statement, res);
			statement2 = connection.prepareStatement(Query);
			do {
				sc.nextLine();
				System.out.println("Name: ");
				String sName = sc.nextLine();
				System.out.println("Age: ");
				int sAge = sc.nextInt();
				System.out.println("Email: ");
				String sEmail = sc.next();
				System.out.println("Course Id: ");
				String sCourseId = sc.next();
				System.out.println("College: ");
				String sCollege = sc.next();
				statement2.setString(1, sName);
				statement2.setInt(2, sAge);
				statement2.setString(3, sEmail);
				statement2.setString(4, sCourseId);
				statement2.setString(5, sCollege);	
				statement2.addBatch();	
				System.out.println("Do You Want To Insert More Students Details? ( Yes / No ): ");
			} while (sc.next().equalsIgnoreCase("Yes"));
			statement2.executeBatch();	
			Display(connection, statement, res);
			System.out.println("Status: Data Updated SuccessFully. #Happy Coding.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
			Close(connection, statement2, statement, res);
		}

	}

	static void Display(Connection connection, Statement statement, ResultSet res) throws SQLException {
		String Query = "SELECT * FROM `Students`;";
		statement = connection.createStatement();
		res = statement.executeQuery(Query);
		System.out.println(
				"+-------------------------------------------------------------------------------------------------+");
		while (res.next()) {
			int sID = res.getInt("ID");
			String sName = res.getString("Name");
			int sAge = res.getInt("Age");                                           //ERORR
			String sEmail = res.getString("EmailId");
			int sCourseID = res.getInt("CourseId");
			String sCollege = res.getString("college");
			System.out.printf("| %-5d | %-20s | %-5d | %-30s | %-10d | %-10s | \n", sID, sName, sAge, sEmail, sCourseID,
					sCollege);
		}
		System.out.println(
				"+-------------------------------------------------------------------------------------------------+");
	}

	static void Close(Connection connection, PreparedStatement statement2, Statement statement, ResultSet res) {
		try {
			if (res != null) {
				res.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (res != null) {
				statement2.close();
				statement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (res != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
