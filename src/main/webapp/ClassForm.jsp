

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
				<c:if test="${classbean != null}">
					<form action="cupdate" method="post">
				</c:if>
				<c:if test="${classbean == null}">
					<form action="cinsert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${classbean != null}">
            			Edit Class Details
            		</c:if>
						<c:if test="${classbean == null}">
            			Add New Class
            		</c:if>
					</h2>
				</caption>

				<c:if test="${classbean != null}">
					<input type="hidden" name="cid" value="<c:out value='${classbean.cid}' />" />
				</c:if>

				<fieldset class="form-group">
					<label> Class Name</label> <input type="text"
						value="<c:out value='${classbean.cname}' />" class="form-control"
						name="cname" required="required">
				</fieldset>
		
				
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>