<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shows Products</title>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" ></link>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</head>
<body>
	<div>
		<jsp:include page="/views/users/common/nav.jsp"></jsp:include>
	</div>
	<div style="min-height: 610px">
		<a class="btn btn-outline-secondary offset-2 mt-5" href="${ pageContext.request.contextPath }/users/home" role="button">Sản phẩm khác</a>
		<div class="mt-2 col-10 offset-2 row" style="height: 400px">
			<div class="col-4 border border-gray p-2 mb-5">
				<img src="${ pageContext.request.contextPath }/storage/${product.image}" class="card-img-top" alt="${product.image}" height="300px" width="200px">
			</div>
			<div class="col-6 border border-gray p-4 mb-5">
				<h3 class="card-title">${product.name}</h3>
				<p></p>
				<p class="card-text"style="color: red;">${product.price } $</p>
				
				
				<div class="form-group" style="padding-top: 150px">
					<a class="btn btn-success" href="${ pageContext.request.contextPath }/users/home/cart/add/${product.id}">Add Cart</a>
				</div>
				
			</div>
		</div>
	</div>
	<div>
		<jsp:include page="/views/users/common/footer.jsp"></jsp:include>
	</div>
</body>
</html>