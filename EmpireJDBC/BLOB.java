package com.EmpireJDBC;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class BLOB {

	private static Connection connection;
	private static PreparedStatement statement;
	private static FileInputStream file;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/Radha";
		String username = "root";
		String password = "2236";

		String Query = "UPDATE `blobclob` SET `DP` = ? WHERE `ID` = ?;";
		String Path = "C:\\Users\\psiva\\eclipse-workspace\\TheMernNexusIndia_JDBC\\Images\\DP.jpg";

		try {
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.prepareStatement(Query);
			System.out.println("ID: ");
			int sId = sc.nextInt();
			statement.setInt(2, sId);
			file = new FileInputStream(Path);  // throws FileNotFoundExeceptions
			statement.setBinaryStream(1, file);
			int i = statement.executeUpdate();
			System.out.println(i);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}

	}

}
