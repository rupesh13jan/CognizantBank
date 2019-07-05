package com.journaldev.spring.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
public class MySQLAccess {
	public static int i=0;
	public static void main(String args[]){  
//	public void readStudents() {
		try
		{  
			i++;
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookapp","root","Password-1");  
			//here Ramkumar is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from bookapp.user");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+" --- "+rs.getString(2)+ "----"+rs.getString(2));  
			con.close(); 
			String firstName = "abc";
			String lastName = "xyz";
			String userName = "viswa";
			String password = "viswa";
			String contact = "999999";
			String email = "abc@gmail.com";
			String address="aaa";
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookapp","root","Password-1");  
			
		
			PreparedStatement preparedStatement = null;
		
			String query = "insert into bookapp.user(user_id,username,firstname,lastname,email,password,address,usertype) values (?,?,?,?,?,?,?,'0')"; //Insert user details into the table 'USERS'
			preparedStatement = con1.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
			preparedStatement.setString(1, String.valueOf(i));
			preparedStatement.setString(2, userName);
			preparedStatement.setString(3, firstName);
			preparedStatement.setString(4, lastName);
			preparedStatement.setString(5, email);
			preparedStatement.setString(6, password);
			preparedStatement.setString(7, address);
			int i= preparedStatement.executeUpdate();
			System.out.println(i);
			con1.close();
		}
		catch(Exception e){ System.out.println("EXCEPTION IN MYSQLACCESS CLASS"+e);}  
	}  
}
