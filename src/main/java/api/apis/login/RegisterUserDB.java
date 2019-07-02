package api.apis.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.journaldev.spring.dbconnection.DBConnection;

public class RegisterUserDB {
	public static int i=10;
	DBConnection dbConnection = new DBConnection();
	public String registerUser(RegisterBean registerBean)
	{
	String firstName = registerBean.getFirstName();
	String lastName = registerBean.getLastName();
	String userName = registerBean.getUserName();
	String password = registerBean.getPassword();
	String contact = registerBean.getContact();
	
	String email =registerBean.getEmail() ;
	String address=registerBean.getAddress();
	Connection con = null;
	PreparedStatement preparedStatement = null;
	try
	{
	con = dbConnection.connectToDB();
	String query = "insert into bookapp.user(user_id,username,firstname,lastname,email,password,address,usertype) values (?,?,?,?,?,?,?,'0')"; //Insert user details into the table 'USERS'
	preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
	preparedStatement.setString(1, String.valueOf(i));
	preparedStatement.setString(2, userName);
	preparedStatement.setString(3, firstName);
	preparedStatement.setString(4, lastName);
	preparedStatement.setString(5, email);
	preparedStatement.setString(6, password);
	preparedStatement.setString(7, address);
	int i= preparedStatement.executeUpdate();
	if (i!=0)  //Just to ensure data has been inserted into the database
	return "SUCCESS"; 
	}
	catch(SQLException e)
	{
	e.printStackTrace();
	}
	return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
	}
}
