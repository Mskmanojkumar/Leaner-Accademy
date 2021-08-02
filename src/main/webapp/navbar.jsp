<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

		<%
		response.setHeader("Cache-Control", "no-cache,no-store, must-revalidate"); // its work on espcially work on Http 1.1
		
		if(session.getAttribute("username")==null){
			
			response.sendRedirect("LoginPage.jsp");
			
			}
	%> 

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="" class="navbar-brand"> Admin Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/studentlist"
					class="nav-link">Student</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/teacherlist"
					class="nav-link">Teacher</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/subjectlist"
					class="nav-link">Subject</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/classlist"
					class="nav-link">classes</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/Assignedlist"
					class="nav-link">Assign</a></li>
			</ul>
	
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/report"
					class="nav-link">Class Report</a></li>
			</ul>
			<ul class="navbar-nav" >
				<li><a href="<%=request.getContextPath()%>/Logout"
					class="nav-link">Logout</a></li>
			</ul>
		</nav>
	</header>
</body>
</html>