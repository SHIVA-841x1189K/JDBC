package com.EmpireJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeletionData_InCompleteQuery {

	private static Scanner sc;
	private static Connection connection;
	private static Statement statement ;
	private static ResultSet res;
	private static PreparedStatement statement2;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/Radha";
		String username = "root";
		String password = "2236";

		String Query = "DELETE FROM `Students` WHERE `ID` =?;";

		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Old Students Table: ");
			Display2(connection, statement, res);
			statement2 = connection.prepareStatement(Query);
			do {
				System.out.println("ID: ");
				int Id = sc.nextInt();
				statement2.setInt(1, Id);
				statement2.executeUpdate();
				System.out.println("Updated Students Table: ");
				Display2(connection, statement, res);
				System.out.println("Do You Want To Delete More Students Details? ( Yes / No ): ");
			} while (sc.next().equalsIgnoreCase("Yes"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
			Close(connection, statement2, statement, res);
		}
	}
	static void Display2(Connection connection, Statement statement, ResultSet res) throws SQLException {
		String Query = "SELECT * FROM `Students`;";
		statement = connection.createStatement();
		res = statement.executeQuery(Query);
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
	static void Close(Connection connection, PreparedStatement statement2,Statement statement, ResultSet res) {
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
