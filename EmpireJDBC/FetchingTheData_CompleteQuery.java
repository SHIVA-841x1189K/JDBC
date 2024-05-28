package com.EmpireJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FetchingTheData_CompleteQuery {
	private static Connection connection;
	private static Statement statement;
	private static ResultSet res;

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/Radha";
		String username = "root";
		String password = "2236";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Students Table: ");
			Display(connection, statement, res);
			System.out.println("Status: Fetching Data SuccessFully. #Happy Coding.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseConnections(connection, statement, res);
		}
		
	}
	
	static void Display(Connection connection, Statement statement, ResultSet res) throws SQLException {
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
	static void CloseConnections(Connection connection, Statement statement, ResultSet res) {
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
	
	
}
