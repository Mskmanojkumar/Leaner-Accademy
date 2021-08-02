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
			<h3 class="text-center">List of Classes</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/cnew" class="btn btn-success">Add
					New Class</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr class="table-success">
						<th>Class_ID</th>
						<th>Class_Name</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="classbean" items="${listUser}">

						<tr>
							<td><c:out value="${classbean.cid}" /></td>
							<td><c:out value="${classbean.cname}" /></td>
							<td><a href="cedit?cid=<c:out value='${classbean.cid}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="cdelete?cid=<c:out value='${classbean.cid}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>