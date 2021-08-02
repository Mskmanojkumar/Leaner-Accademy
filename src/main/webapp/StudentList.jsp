<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
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
	<jsp:include page="navbar.jsp"></jsp:include>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Student</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/snew" class="btn btn-success">Add
					New Student</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr class="table-success">
						<th>ID</th>
						<th>First_name</th>
						<th>Last_Nmae</th>
						<th>Date-Of-Birth</th>
						<th>Address</th>
						<th>Class</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
			
					<c:forEach var="studentbean" items="${listUser}">
						
						<tr>
							<td><c:out value="${studentbean.id}" /></td>
							<td><c:out value="${studentbean.fname}" /></td>
							<td><c:out value="${studentbean.lname}" /></td>
							<td><c:out value="${studentbean.dob}" /></td>
							<td><c:out value="${studentbean.address}" /></td>
							<td><c:out value="${studentbean.cname}" /></td>
							<td><a href="sedit?id=<c:out value='${studentbean.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="sdelete?id=<c:out value='${studentbean.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>