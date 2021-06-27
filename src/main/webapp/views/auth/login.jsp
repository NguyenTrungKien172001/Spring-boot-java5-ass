<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Shoprick</title>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" ></link>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

</head>
<body>
	<div style="background-image: url('https://i.pinimg.com/originals/c3/9b/f8/c39bf8796d0dfa8cc42e56d93d5e30b3.png');">

	<div class="col-10 offset-1 mt-1">
		<c:if test="${not empty sessionScope.error }">
			<div class="alert alert-danger">${sessionScope.error  }</div>
			<c:remove var="error" scope="session"/>
		</c:if>
	</div>
	<div style="min-height: 610px;padding-top: 70px;">
		<div class="col-7 offset-4 mt-5 ">
	    <div class="col-6 mt-5 border border-gray p-4">
	    <h3 align="center">Sign in</h3>
	        <form method="POST" action="${ pageContext.request.contextPath }/login">
	            <div class="mt-3">
	                <label for="email">Email</label>
	
	                <input type="email" name="email" id="email" class="form-control" />
	            </div>
	
	            <div class="mt-3">
	                <label for="password">Password</label>
	
	                <input type="password" name="password" id="password" class="form-control" />
	            </div>
	            
	            <div  class="mt-4" align="center">
	            	<a href="${ pageContext.request.contextPath }/createNew">Create account?</a>
	            </div>
	            
	            <div class="mt-2 " align="center">
	                <button class="btn btn-primary col-6">Sign in</button>
	            </div>
	        </form>
	    </div>
		</div>
		</div>
	</div>
</body>
</html>