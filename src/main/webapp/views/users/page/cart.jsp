<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" ></link>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

</head>
<body>
	<div>
		<jsp:include page="/views/users/common/nav.jsp"></jsp:include>
	</div>

	<div class="mt-4 col-10 offset-1"  style="min-height: 610px">
	<h2>Shopping Cart</h2>
	<a class="btn btn-outline-secondary" href="${ pageContext.request.contextPath }/users/home" role="button">Sản phẩm khác</a>
	<hr>
	<table class="table table-strip table-dark mt-3">
	<thead>
		<tr>
			<td>Id</td>
			<td>Name</td>
			<td>Image</td>
			<td>Price</td>
			<td>Quantity</td>
			<td>Amount</td>
			<td></td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${cart.items }">
		<form action="${ pageContext.request.contextPath }/users/home/cart/update/${item.id}" method="post">
				<input type="hidden" name="id" value="${item.id }"/>
				<tr>
					<td>${item.id}</td>
					<td>${item.name }</td>
					<td><img src="${ pageContext.request.contextPath }/storage/${item.image}" alt="${item.image}" height="60px" width="80px"></td>
					<td>${item.price }</td>
					<td>
						
						<input value="${item.quantity }" name="quantity" type="number" min="1"
						onblur="this.form.submit()"/>
					</td>
					<td>${item.price * item.quantity }</td>
					<td>
						<a class="btn btn-danger" href="${ pageContext.request.contextPath }/users/home/cart/remove/${item.id}">Remove</a>
					</td>
				</tr>
		</form>
		</c:forEach>
	</tbody>
	
	</table>
	<h5 align="center">${mess }</h5>
	<hr>
	<hr>
		<div class="col-4 mt-3 border border-gray p-4 mb-5" style="display: ${cart.getCount()==0?"none":""}">
			<form:form modelAttribute="order" method="POST"
				action="${ pageContext.request.contextPath }/users/home/cart/order">
				<h4 align="center">Thanh toán</h4>
				<b>Số lượng sản phẩm: </b>${cart.getCount()}<br>
				<b>Tổng số tiền là: </b>${cart.getAmount()}<br>
				<b>Email: </b>${user.email }<br>
				<div class="form-group mt-3">
					<label for="phone">Phone</label>
					<form:input path="phone" name="phone" id="username"
						class="form-control" />
					<form:errors path="phone" element="span" cssClass="text-danger" />
				</div>
				<div class="form-group mt-3">
					<label for="address">Address</label>
					<form:input path="address" name="address" id="username"
						class="form-control" />
					<form:errors path="address" element="span" cssClass="text-danger" />
				</div>
				<div class="form-group mt-3">
					<button class="btn btn-primary">Thanh toán</button>
				</div>
			</form:form>
		</div>

	</div>
	
	
	
		<div>
			<jsp:include page="/views/users/common/footer.jsp"></jsp:include>
		</div>
</body>
</html>