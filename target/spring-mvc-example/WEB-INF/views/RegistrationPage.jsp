<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="Style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Screen</title>
</head>
<body>
	<form action="Register_Ser" method="post">
		<H1 align="center">Create Your Account</H1>
		<table style="with: 100%" align="center">
	
			<tr>
				<td>First Name</td>
				<td><input type="text" name="first_name" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="last_name" /></td>
			</tr>
			<tr>
				<td>UserName</td>
				<td><input type="text" name="username" /></td>
			</tr>
				<tr>
				<td>Password</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td>Confirm Password</td>
				<td><input type="password" name="confirmpassword" /></td>
			</tr>
			<tr>
				<td>Contact No</td>
				<td><input type="text" name="contact" /></td>
			</tr>
			<tr>
				<td><h6><a href="Login.jsp">Back</a></h6></td>
				<td> <input type="submit" value="Register" /></td>
			</tr>
			
		</table>
	</form>
</body>
</html>