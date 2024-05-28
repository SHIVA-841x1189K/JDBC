package com.EmpireJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PaymentApplication {

	private static Connection connection;
	private static Scanner sc;
	private static PreparedStatement statement;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/Radha";
		String username = "root";
		String password = "2236";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(false);
			transcation();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}		
	}
	public static void transcation() throws SQLException {
		System.out.println("Enter a Sender Name: ");
		String sender = sc.nextLine();
		System.out.println("Enter a Receiver Name: ");
		String receiver = sc.nextLine();
		System.out.println("Enter a Amount: ");
		int amount = sc.nextInt();
		int n1 = update(-amount,sender);
		int n2 = update(amount,receiver);
		conform(n1,n2);
	}
	
	public static int update(int amount,String User) throws SQLException {
		String Query = "UPDATE `payment` SET `salary` = `salary`+ ? WHERE `name` = ? ";
		statement = connection.prepareStatement(Query);
		statement.setInt(1, amount);
		statement.setString(2, User);
		return statement.executeUpdate();	
	}
	public static void conform(int n1, int n2) throws SQLException {
		System.out.println("Do You Want To Make a Payment? ( Yes / No ): ");
		String choice = sc.next();
		if(choice.equalsIgnoreCase("Yes") && n1 == 1 && n2 == 1) {
			connection.commit();
			System.out.println("Status: Transcation SuccessFully.");
		}
		else {
			connection.rollback();
			System.out.println("Status: Transcation Failure And Try Again Later.");
		}
	}
}
