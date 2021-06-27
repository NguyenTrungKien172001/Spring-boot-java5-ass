<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" ></link>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

</head>
<body>
	<div>
		<jsp:include page="/views/users/common/nav.jsp"></jsp:include>
	</div>
	<div class="col-10 offset-1 mt-1">
		<c:if test="${not empty sessionScope.error }">
			<div class="alert alert-danger">${sessionScope.error  }</div>
			<c:remove var="error" scope="session"/>
		</c:if>
	</div>
	<div style="min-height: 610px;">
		<div class="col-7 offset-4 mt-5 ">
		    <div class="col-6 mt-5 border border-gray p-4">
		    <h3 align="center">Change password</h3>
		        <form method="POST" action="${ pageContext.request.contextPath }/users/home/changePassword">
		        	<div class="mt-3">
		                <label for="email">Email</label>
		                <input type="email" value="${userCpw.email}" name="email" id="email" class="form-control" />
		            </div>
		        
		            <div class="mt-3">
		                <label for="password">Password</label>
		                <input type="password" name="password" id="password" class="form-control" />
		            </div>
		
		            <div class="mt-3">
		                <label for="newpassword">New password</label>
		                <input type="password" name="newpassword" id="newpassword" class="form-control" />
		            </div>
		            
		            <div  class="mt-3">
		            	<label for="CPpassword">Confirm password</label>
		                <input type="password" name="CPpassword" id="CPpassword" class="form-control" />
		            </div>
		            
		            <div class="mt-4" align="center">
		                <button class="btn btn-primary col-6">Ok</button>
		            </div>
		        </form>
		    </div>
		</div>
	</div>
</body>
</html>