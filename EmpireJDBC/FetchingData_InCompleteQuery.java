package com.EmpireJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FetchingData_InCompleteQuery {

	private static Scanner sc;
	private static Connection connection;
	private static PreparedStatement statement;
	private static ResultSet res;
	private static Statement statement2;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/Radha";
		String username = "root";
		String password = "2236";

		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Old Student Table: ");
			Display2(connection, statement2, res);
			System.out.println();
			do {
				Display(connection, statement, res);
				System.out.println("Do You Want To Get More Student Details? ( Yes / No): ");
			} while (sc.next().equalsIgnoreCase("Yes"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
			Close(connection, statement, res);
		}
	}

	static void Display(Connection connection, PreparedStatement statement, ResultSet res) throws SQLException {
		String Query = "SELECT * FROM `Students` WHERE `ID` = ?;";
		statement = connection.prepareStatement(Query);
		System.out.println("ID: ");
		int Id = sc.nextInt();
		statement.setInt(1, Id);
		res = statement.executeQuery();
		System.out.println("+------------------------------------------------------------------------------------+");
		while (res.next()) {
			int sID = res.getInt("ID");
			String sName = res.getString("Name");
			int sAge = res.getInt("Age");
			String sEmail = res.getString("EmailId");
			int sCourseID = res.getInt("CourseId");
			System.out.printf("| %-5d | %-20s | %-5d | %-30s | %-10d | \n", sID, sName, sAge, sEmail, sCourseID);
		}
		System.out.println("+------------------------------------------------------------------------------------+");
	}

	static void Close(Connection connection, PreparedStatement statement, ResultSet res) {
		try {
			if (res != null) {
				res.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (res != null) {
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

	static void Display2(Connection connection, Statement statement2, ResultSet res) throws SQLException {
		String Query = "SELECT * FROM `Students`;";
		statement2 = connection.createStatement();
		res = statement2.executeQuery(Query);
		System.out.println("+------------------------------------------------------------------------------------+");
		while (res.next()) {
			int sID = res.getInt("ID");
			String sName = res.getString("Name");
			int sAge = res.getInt("Age");
			String sEmail = res.getString("EmailId");
			int sCourseID = res.getInt("CourseId");
			System.out.printf("| %-5d | %-20s | %-5d | %-30s | %-10d | \n", sID, sName, sAge, sEmail, sCourseID);
		}
		System.out.println("+------------------------------------------------------------------------------------+");
	}
}
