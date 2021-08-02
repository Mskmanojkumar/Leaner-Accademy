

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

	<jsp:include page="navbar.jsp"></jsp:include>	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${subjectbean != null}">
					<form action="subupdate" method="post">
				</c:if>
				<c:if test="${subjectbean == null}">
					<form action="subinsert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${subjectbean != null}">
            			Edit Subject Details
            		</c:if>
						<c:if test="${subjectbean == null}">
            			Add New Subject
            		</c:if>
					</h2>
				</caption>

				<c:if test="${subjectbean != null}">
					<input type="hidden" name="subid" value="<c:out value='${subjectbean.subid}' />" />
				</c:if>

				<fieldset class="form-group">
					<label> Subject Name</label> <input type="text"
						value="<c:out value='${subjectbean.subname}' />" class="form-control"
						name="subname" required="required">
				</fieldset>
		
				
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>