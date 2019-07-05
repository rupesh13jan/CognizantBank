<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="Style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Success</title>
</head>
<body>

	<% String firstName = request.getParameter("first_name"); %>
	<% String lastName = request.getParameter("last_name"); %>
	<% String userName = request.getParameter("username"); %>
	<% String pasword = request.getParameter("password"); %>
	<% String contact = request.getParameter("contact"); %>
		
	<a>Welcome   <% out.println(firstName); %></a> <BR>
	<a>Last Name	:::   <% out.println(lastName); %></a><BR>
	<a>UserName 	:::   <% out.println(userName); %></a><BR>
	<a>Address 		:::   <% out.println(pasword); %></a><BR>
	<a>Contact 		:::   <% out.println(contact); %></a><BR>
	
	<H3 align="center">Hey Brother! Registration successful. <BR> You better have a wonderful day!</H3>
</body>
</html>