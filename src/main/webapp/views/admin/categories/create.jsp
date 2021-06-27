<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Category</title>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" ></link>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</head>
<body>
	<div>
		<jsp:include page="/views/admin/common/nav.jsp"></jsp:include>
	</div>
	
	<div class="mt-5 col-10 offset-1">
	<h2>Thêm danh mục</h2>
		<form:form
			modelAttribute="category"
			method="POST"
			action="${ pageContext.request.contextPath }/admin/categories/store">
			
			<div class="form-group mt-3">
				<label for="name">Name</label>
			    <form:input  path="name" class="form-control" id="name" name="name" autocomplete="off" />
			    <form:errors path="name" element="span" cssClass="text-danger" />
			</div>
			
			<div class="form-group mt-3">
				<label for="activate">Activated</label>
				<form:select path="activate" id="activate" class="form-control" >
					<form:option value="1" >Active</form:option>
					<form:option value="0" >Inactive</form:option>
				</form:select>
				<form:errors path="activate" element="span" cssClass="text-danger" />
			</div>
			
			<div class="form-group mt-3">
				<button class="btn btn-primary">Submit</button>
				<button type="reset" class="btn btn-danger">Clear</button>
			</div>
			
		</form:form>
	</div>
</body>
</html>