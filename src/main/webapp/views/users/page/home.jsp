<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<style type="text/css">
	ul.a {
	  	list-style-type: circle;
	}
	li>a{
		text-decoration: none;
	  	color: black;
	}
	.card_A{
		width: 16rem;float: left;height: 18rem;margin: 14px;position: relative;
		text-decoration: none;
		color: black;
	}
	.card_A:hover{
		color: black;
		transform: scale(1.05);
	  	box-shadow: 0 10px 20px rgba(0,0,0,.12), 0 4px 8px rgba(0,0,0,.06);
	}
 </style>
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
		<div class="mt-4 col-10 offset-1 row"  >
			<h2>Home</h2>
			<div align="right" class=" mb-2">
				<form method="GET" action="${ pageContext.request.contextPath }/users/home">
					<input type="text" name="keyword" id="keyword" size="30" value="${keyword}" placeholder="Search.."/>
				    &nbsp;
				    <input type="submit" value="Search" />
				</form>
			</div>
			<hr>
			<div class="col-2 border border-gray p-2 mb-5">
				<h3>Categories</h3>
				<div>
				<ul class="a">
					<li ><a href="${ pageContext.request.contextPath }/users/home">All</a></li>
					<c:forEach items="${ categoryData }" var="category">
						<li><a href="${ pageContext.request.contextPath }/users/home?dm=${category.id}" >${category.name}</a></li>
					</c:forEach>
				</ul>
				</div>
			</div>
			<div class="col-9 offset-1 border border-gray p-2 mb-5">
				<h3>Products</h3>
				<c:forEach items="${ tesst }" var="product">
				<div class="card_A">
				<!--  <a href="${ pageContext.request.contextPath }/users/home/${product.id}" class="card_A">-->
					<div class="card">
					  <img src="${ pageContext.request.contextPath }/storage/${product.image}" class="card-img-top" alt="${product.image}" height="150px">
					  <div class="card-body">
					    <h5 class="card-title">${product.name}</h5>
					    <p class="card-text"style="color: red;">${product.price } $</p>
					    <a class="btn btn-success" href="${ pageContext.request.contextPath }/users/home/cart/add/${product.id}">Add Cart</a>
					    <a class="btn btn-primary" href="${ pageContext.request.contextPath }/users/home/${product.id}">Views</a>
					  </div>
					</div>
				<!--  </a>-->
				</div>
				</c:forEach>	
				<h5 align="center">${proNull}</h5>
			</div>
			
		</div>
	</div>
		<div>
			<jsp:include page="/views/users/common/footer.jsp"></jsp:include>
		</div>
</body>
</html>