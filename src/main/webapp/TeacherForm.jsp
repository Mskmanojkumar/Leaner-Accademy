

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
				<c:if test="${teacherbean != null}">
					<form action="tupdate" method="post">
				</c:if>
				<c:if test="${teacherbean == null}">
					<form action="tinsert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${teacherbean != null}">
            			Edit Teacher Details
            		</c:if>
						<c:if test="${teacherbean == null}">
            			Add New teacher
            		</c:if>
					</h2>
				</caption>

				<c:if test="${teacherbean != null}">
					<input type="hidden" name="tid" value="<c:out value='${teacherbean.tid}' />" />
				</c:if>

				<fieldset class="form-group">
					<label> Teacher First Name</label> <input type="text"
						value="<c:out value='${teacherbean.fname}' />" class="form-control"
						name="fname" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label> Teacher Last name</label> <input type="text"
						value="<c:out value='${teacherbean.lname}' />" class="form-control"
						name="lname">
				</fieldset>
				<fieldset class="form-group">
					<label> Teacher Dob</label>  <input type="date"
						value="<c:out value='${teacherbean.dob}' />" class="form-control" 
						name="dob">
				<fieldset class="form-group">
					<label> Teacher Address</label> <input type="text"
						value="<c:out value='${teacherbean.address}' />" class="form-control"
						name="address">
				</fieldset>
				
				
				</fieldset>
				<fieldset class="form-group">
					<label> Teacher Education Qualification</label> <input type="text"
						value="<c:out value='${teacherbean.edueq}' />" class="form-control"
						name="eq">
				</fieldset>
				
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>