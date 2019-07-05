package com.journaldev.spring.controller;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.apis.login.AccessLoginDB;
import api.apis.login.LoginBean;



/**
 * Servlet implementation class Login_Ser
 */
@WebServlet("/Login_Ser")
public class Login_Ser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login_Ser() {
        super();
        // TODO Auto-generated constructor stub
    }
    
     
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		AccessLoginDB accessLoginDB = new AccessLoginDB(); 	//Connecting to DB and Business logic to verify the user
		LoginBean loginBean=new LoginBean();  				// POJO class with getter and setter method

		String username = request.getParameter("username");	// Get the user entered details from Login.jsp
		String password = request.getParameter("password"); // Get the user entered details from Login.jsp
		
		if(username.isEmpty() || password.isEmpty())		// Validation
		{
			RequestDispatcher req = request.getRequestDispatcher("Login.jsp");
			req.include(request, response);
		}
		else
		{
			loginBean.setUsername(username);  				// Set the user details to POJO bean 
		    loginBean.setPassword(password);  
		    request.setAttribute("bean",loginBean);
				    
		    String accessValidate = accessLoginDB.readLogin(loginBean); //Connecting to DB and Business logic to verify the user
		    System.out.println("DB Value ::::"+accessValidate);
	    
		    if(accessValidate.equals("SUCCESS")) //If function returns success then user will be rooted to Home page
		    {
			    request.setAttribute("userName", username); //with setAttribute() you can define a "key" and value pair so that you can get it in future using getAttribute("key")
			    request.setAttribute("Password", password);
			    request.getRequestDispatcher("/LoginAction.jsp").forward(request, response);//RequestDispatcher is used to send the control to the invoked page.
		    }
		    else
		    {
			    request.setAttribute("errMessage", accessValidate); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.
			    request.getRequestDispatcher("/Login.jsp").forward(request, response);//forwarding the request
		    }
	    
		}
		
	}

}
