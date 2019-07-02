package api.apis.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.journaldev.spring.dbconnection.DBConnection;

public class AccessLoginDB {

	DBConnection dbConnection = new DBConnection(); //Class to set up connection with mysql database
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	final private String database = "bookapp";
	
	public String readLogin(LoginBean loginBean) {
		try {
			
			connect = dbConnection.connectToDB();
	
			System.out.println("CHECK POINT 1");
	
			String userName = loginBean.getUsername(); //Keeping user entered values in temporary variables.
			String password = loginBean.getPassword();

			statement = connect.createStatement();

			System.out.println("CHECK POINT 2");

			resultSet = statement.executeQuery("select * from " + database + ".user");
	
			System.out.println("CHECK POINT 3");
	
			while (resultSet.next()) {
				System.out.println("CHECK POINT 4");
				//int Id = resultSet.getInt("id");
				String userNameDB = resultSet.getString("username");
				String passwordDB = resultSet.getString("password");
				if(userName.equals(userNameDB) && password.equals(passwordDB))
				{
					return "SUCCESS"; ////If the user entered values are already present in the database, then SUCCESS message will be sent.
				}

				System.out.println("CHECK POINT 5");

			}
		} catch (Exception e) {
			
			System.out.println("Error in TRY"+e);

		}
		return "ERROR in readStudent";
	}
	
	public void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
