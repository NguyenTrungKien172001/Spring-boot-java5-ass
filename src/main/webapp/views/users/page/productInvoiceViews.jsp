<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Invoice Views</title>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" ></link>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</head>
<body>
	<div>
		<jsp:include page="/views/users/common/nav.jsp"></jsp:include>
	</div>
	<div style="min-height: 610px" class="mb-5">
	<div class="col-10 offset-1 mt-4">
	<h2>Xem hóa đơn</h2>
	</div>
	<a class="btn btn-outline-secondary offset-1 mt-3" href="${ pageContext.request.contextPath }/users/home/productInvoice" >Hóa đơn khác</a>
		

	<div class="mt-5 col-10 offset-1 border border-gray p-2">
		  <table class="table table-strip table-dark mt-3">
			<thead>
				<tr>
					<td>Id</td>
					<td>OrderID</td>
					<td>ProductID</td>
					<td>Quantity</td>
					<td>Price</td>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${ pageData.content }" var="order_detalls">
				<tr>
						<td>${ order_detalls.id }</td>
						<td>${ order_detalls.order.id }</td>
						<td>${ order_detalls.product.id }</td>
						<td>${ order_detalls.quantity}</td>
						<td>${ order_detalls.price}</td>
					</tr>
			</c:forEach>
			</tbody>
		</table>
		
		<div>
			<ul class="pagination">
				<c:forEach begin="0" end="${ pageData.totalPages>0?pageData.totalPages-1:0}" varStatus="page">
					<li class="page-item">
						<a class="page-link" href="${ pageContext.request.contextPath }/users/home/productInvoice/${id}?page=${page.index}&limit=3">${ page.index + 1 }</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>	</div>
	<div>

			<jsp:include page="/views/users/common/footer.jsp"></jsp:include>
		</div>
</body>
</html>