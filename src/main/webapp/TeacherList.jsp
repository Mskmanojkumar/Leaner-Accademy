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
			<h3 class="text-center">List of Teacher</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/tnew" class="btn btn-success">Add
					New Teacher</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr class="table-success">
						<th>Teacher_ID</th>
						<th>First_name</th>
						<th>Last_Nmae</th>
						<th>Address</th>
						<th>Date-Of-Birth</th>
						<th>Education_Qalification</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="teacherbean" items="${listUser}">

						<tr>
							<td><c:out value="${teacherbean.tid}" /></td>
							<td><c:out value="${teacherbean.fname}" /></td>
							<td><c:out value="${teacherbean.lname}" /></td>
							<td><c:out value="${teacherbean.dob}" /></td>
							<td><c:out value="${teacherbean.address}" /></td>
							<td><c:out value="${teacherbean.edueq}" /></td>
							<td><a href="tedit?tid=<c:out value='${teacherbean.tid}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="tdelete?tid=<c:out value='${teacherbean.tid}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>