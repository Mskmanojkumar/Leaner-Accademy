<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Report</title>
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
			<div class="container">
			<c:forEach var="classbean" items="${ClistUser}">
			<h1 align="center">Class Report For <c:out value="${classbean.cname}" /> </h1>
				<table class="table table-bordered border-primary">
				<thead>
					<tr class="table table-dark">
						
						<th >Class_name</th>
						<th>Subject_Name</th>
						<th>Teacher_Name</th>
						
					</tr>
				</thead>
				<tbody>
			
					<c:forEach var="assignbean" items="${AlistUser}">
						<c:if test="${classbean.cname.equals(assignbean.cname)}">
						<tr class="table-success">
							
							<td><c:out value="${assignbean.cname}" /></td>
							<td><c:out value="${assignbean.subname}" /></td>
							<td><c:out value="${assignbean.tname}" /></td>
							
						</tr>
						</c:if>
					</c:forEach>
		
				</tbody>

			</table>
			<br><hr>
			<h1 align="center">Student List</h1>
			<table class="table table-bordered border-primary">
				<thead>
					<tr class="table table-dark table-hover">
						<th>ID</th>
						<th>First_name</th>
						<th>Last_Nmae</th>
						<th>Date-Of-Birth</th>
						<th>Address</th>
						<th>Class</th>
						
					</tr>
				</thead>
				<tbody>
			
					<c:forEach var="studentbean" items="${listStudent}">
						<c:if test="${studentbean.cname.equals(classbean.cname)}">
						<tr class="table-success">
							<td><c:out value="${studentbean.id}" /></td>
							<td><c:out value="${studentbean.fname}" /></td>
							<td><c:out value="${studentbean.lname}" /></td>
							<td><c:out value="${studentbean.dob}" /></td>
							<td><c:out value="${studentbean.address}" /></td>
							<td><c:out value="${studentbean.cname}" /></td>
							
						</tr>
						
						</c:if>
					</c:forEach>

		</tbody> 
		</table>
	</c:forEach>
</div>
</body>
</html>