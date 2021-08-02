

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
		<div class="card">
			<div class="card-body">
				<c:if test="${studentbean != null}">
					<form action="supdate" method="post">
				</c:if>
				<c:if test="${studentbean == null}">
					<form action="sinsert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${studentbean != null}">
            			Edit Student Details
            		</c:if>
						<c:if test="${studentbean == null}">
            			Add New Student
            		</c:if>
					</h2>
				</caption>

				<c:if test="${studentbean != null}">
					<input type="hidden" name="id" value="<c:out value='${studentbean.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label> Student First Name</label> <input type="text"
						value="<c:out value='${studentbean.fname}' />" class="form-control"
						name="fname" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label> Student Last name</label> <input type="text"
						value="<c:out value='${studentbean.lname}' />" class="form-control"
						name="lname">
				</fieldset>

				<fieldset class="form-group">
					<label> Student DOB</label> <input type="date"
						value="<c:out value='${studentbean.dob}' />" class="form-control"
						name="dob">
				</fieldset>
				
				<fieldset class="form-group">
					<label> Student Address</label>  <input type="text"
						value="<c:out value='${studentbean.address}' />" class="form-control" 
						name="address">
				</fieldset>
				
				
				<fieldset class="form-group">
				
				<select class="form-select" name="cname" aria-label="Default select example">
					
					<option selected> select Classes</option>
					
					<c:forEach var="classbean" items="${ClistUser}">
					
					<option value="${classbean.cname}">${classbean.cname}</option>
					
				</c:forEach>
				
				</select>
				
				</fieldset>
				
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>