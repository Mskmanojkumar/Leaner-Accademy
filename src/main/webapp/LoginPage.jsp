<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AdminLoginpage</title>
</head>
<body>
		<form action="LoginServlet" method="post">
		<h1 align="center">Login Page</h1> 
			<table align="center" cellspacing="6">
				<tr>
					<td> Enter UserName: </td>
					<td><input type="text" name="uname"></td>
				</tr>
				<tr>

					<td> Enter the Password:</td><td><input type="password" name="pass"/></td>
				</tr>
				<tr></tr>
				<tr>
				<td></td>
					<td ><input type="submit" value="Login"> <br></td>
				</tr>

			</table>
		</form>
	</div>
</body>
</html>