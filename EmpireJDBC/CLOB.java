package com.EmpireJDBC;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class CLOB {

	private static Connection connection;
	private static PreparedStatement statement;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String url = "jdbc:mysql://localhost:3306/Radha";
		String username = "root";
		String password = "2236";

		String Query = "UPDATE `blobclob` SET `Intro` = ? WHERE `ID` = ?;";

		try {
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.prepareStatement(Query);
			System.out.println("ID: ");
			int id = sc.nextInt();
			statement.setInt(2, id);
			FileReader file = new FileReader("C:\\Users\\psiva\\eclipse-workspace\\TheMernNexusIndia_JDBC\\Files\\Intro.txt"); // throws FileNotFoundExeceptions
			statement.setCharacterStream(1, file);
			int i = statement.executeUpdate();
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}
