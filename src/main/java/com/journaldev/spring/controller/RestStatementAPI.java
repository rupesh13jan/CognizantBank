package com.journaldev.spring.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class RestStatementAPI {
	
	
	public  Map<Integer, LinkedList<Users> > getUsersDetails(Connection con) throws Exception {
		Map<Integer, LinkedList<Users> > allData = new LinkedHashMap<Integer, LinkedList<Users> >();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from bookapp.user");
		while (rs.next()) {
			LinkedList<Users> allUsersList = new LinkedList<Users>();			
			allUsersList.add(new Users(rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"),rs.getString("contact"), rs.getString("address"),
					rs.getString("email"), rs.getInt("user_id"), rs.getInt("usertype")));			
			allData.put(rs.getInt("user_id"),allUsersList );	
		}
		return allData;

	}
	
	
	public  Map<Integer, LinkedList<Users>> displayUsersBasedOnID(Connection con,int id) throws Exception {
		Map<Integer, LinkedList<Users> > allData = new LinkedHashMap<Integer, LinkedList<Users> >();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from bookapp.user where usertype="+id);
		while (rs.next()) {
			LinkedList<Users> allUsersList = new LinkedList<Users>();			
			allUsersList.add(new Users(rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"),rs.getString("contact"), rs.getString("address"),
					rs.getString("email"), rs.getInt("user_id"), rs.getInt("usertype")));			
			allData.put(rs.getInt("user_id"),allUsersList );	
		}
		return allData;
	}
	
	public int insertUsers(Connection con,Users usersjson) throws Exception {
		PreparedStatement preparedStatement = null;		
		String query = "insert into bookapp.user(username,firstname,lastname,email,password,address,contact,usertype) values (?,?,?,?,?,?,?,1)"; //Insert user details into the table 'USERS'
		preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
		preparedStatement.setString(1,usersjson.getUsername() );
		preparedStatement.setString(2, usersjson.getFirstname());
		preparedStatement.setString(3, usersjson.getLastname());
		preparedStatement.setString(4, usersjson.getEmail());
		preparedStatement.setString(5, usersjson.getPassword());
		preparedStatement.setString(6, usersjson.getAddress());
		preparedStatement.setString(7, usersjson.getContact());
		int i= preparedStatement.executeUpdate();
		System.out.println(i);
		
		return i;
	}
	
	public int updateUsers(Connection con,Users usersjson,String email) throws Exception {
		PreparedStatement preparedStatement = null;			
		String query = "update user set usertype=2 where email='"+email+"'"; //Insert user details into the table 'USERS'
		preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
		int i= preparedStatement.executeUpdate();
		System.out.println(i);
		
		return i;
	}
	

}
