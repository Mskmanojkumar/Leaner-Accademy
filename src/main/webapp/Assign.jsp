

<%@page import="com.bean.ClassBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Admin Management Application</title>
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
	
	<div class="container col-md-5">
			<form action="ainsert" method="post">
				
				
				<fieldset class="form-group">
				
				<select class="form-select" name="cname" aria-label="Default select example">
					
					<option selected> select Classes</option>
					
					<c:forEach var="classbean" items="${ClistUser}">
					
					<option value="${classbean.cname}">${classbean.cname}</option>
					
				</c:forEach>
				
				</select>&nbsp;&nbsp;&nbsp;&nbsp;
				
				<select class="form-select" name="subname" aria-label="Default select example">
					
					<option selected> select Subject</option>
					
					<c:forEach var="subjectbean" items="${SlistUser}">
					
					<option value="${subjectbean.subname}">${subjectbean.subname}</option>
					
				</c:forEach>
				
				</select>&nbsp;&nbsp;&nbsp;&nbsp;
				<select class="form-select" name="tname" aria-label="Default select example">
					
					<option selected> select Teacher</option>
					
					<c:forEach var="teacherbean" items="${TlistUser}">
					
					<option value="${teacherbean.fname}">${teacherbean.fname}</option>
					
				</c:forEach>
				
				</select>&nbsp;&nbsp;&nbsp;&nbsp;
				</fieldset>
				
				<button type="submit" class="btn btn-success">Save</button>
				
				</form>
			</div>
		</div>
	</div><br><br><br><br><h1 align="center">Assigned Details</h1>
	<div class="container">
				<table class="table table-bordered">
				<thead>
					<tr class="table-success">
						<th>ID</th>
						<th>Class_name</th>
						<th>Subject_Name</th>
						<th>Teacher_Name</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
			
					<c:forEach var="assignbean" items="${AlistUser}">
						
						<tr>
							<td><c:out value="${assignbean.id}" /></td>
							<td><c:out value="${assignbean.cname}" /></td>
							<td><c:out value="${assignbean.subname}" /></td>
							<td><c:out value="${assignbean.tname}" /></td>
							<td><a href="adelete?id=<c:out value='${assignbean.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
	</div>
</body>
</html>