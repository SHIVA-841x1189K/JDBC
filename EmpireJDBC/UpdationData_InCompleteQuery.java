package com.EmpireJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class UpdationData_InCompleteQuery {

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

		String Query = "UPDATE `Students` SET `college` = ? WHERE `ID` = ? ";

		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Old Students Table: ");
			InsertionData_InCompleteQuery.Display(connection, statement, res);
			statement2 = connection.prepareStatement(Query);
			do {
				System.out.println("College Name: ");
				String sCollegeName = sc.next();
				System.out.println("ID: ");
				int sId = sc.nextInt();
				statement2.setString(1, sCollegeName);
				statement2.setInt(2, sId);
				statement2.executeUpdate();
				System.out.println("Updated Students Table: ");
				InsertionData_InCompleteQuery.Display(connection, statement, res);
				System.out.println("Status: Data Updated SuccessFully.");
				System.out.println("Do Want To Update More Students Details? ( Yes / No): ");
			} while (sc.next().equalsIgnoreCase("Yes"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}
