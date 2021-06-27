<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bill Management</title>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" ></link>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</head>
<body>
	<div>
		<jsp:include page="/views/admin/common/nav.jsp"></jsp:include>
	</div>
	<div style="min-height: 610px" class="mb-5">
	<div class="col-10 offset-1 mt-4"  >
	<h2>Quản lý hóa đơn</h2>
	</div>
	<div class="col-10 offset-1 mt-5 border border-gray p-2">
		<form method="GET" action="${ pageContext.request.contextPath }/admin/users/productInvoice">
			<div class="row col-12 mt-2">
				<div class="col-6">
					<label>Sắp xếp theo</label>
					<select name="sort_by" class="form-control">
						<option value="id">Mặc định</option>
						<option value="address" >Address</option>
					</select>
				</div>
				<div class="col-6">
					<label>Thứ tự</label>
					<select name="sort_direction" class="form-control">
						<option value="asc">Tăng dần</option>
						<option value="desc">Giảm dần</option>
					</select>
				</div>
			</div>
			
			<div>
				<button class="btn btn-primary mt-4">Lọc</button>
				<a href="${ pageContext.request.contextPath }/admin/users/productInvoice"
					class="btn btn-danger mt-4" type="reset">
					Reset
				</a>
			</div>
		</form>
	</div>

	<div class="mt-5 col-10 offset-1 border border-gray p-2">
		  <table class="table table-strip table-dark mt-3">
			<thead>
				<tr>
					<td>Id</td>
					<td>UserID</td>
					<td>Create Date</td>
					<td>Address</td>
					<td>Phone</td>
					<td>Trạng thái</td>
					<td colspan="2">Thao tác</td>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${ pageData.content }" var="order">
				<tr>
						<td>${ order.id }</td>
						<td>${ order.user.id }</td>
						<td>${ order.createDate }</td>
						<td>${ order.address}</td>
						<td>${ order.phone}</td>
						<form action="${ pageContext.request.contextPath }/admin/users/productInvoice/${order.id}" method="POST">
							<td>
								<select name="activate"  class="form-control">
									<option value="1" ${order.activate==1 ? "selected" : "" }>Đang xử lý</option>
									<option value="2" ${order.activate==2 ? "selected" : "" }>Đang giao</option>
									<option value="3" ${order.activate==3 ? "selected" : "" }>Hoàn thành</option>
									<option value="4" ${order.activate==4 ? "selected" : "" }>Đã hủy</option>
								</select>
							</td>
							<td>
								<button class="btn btn-primary">Update</button>
							</td>
						</form>
					</tr>
			</c:forEach>
			</tbody>
		</table>
		
		<div>
			<ul class="pagination">
				<c:forEach begin="0" end="${ pageData.totalPages>0?pageData.totalPages-1:0}" varStatus="page">
					<li class="page-item">
						<a class="page-link" href="${ pageContext.request.contextPath }/admin/users/productInvoice?page=${page.index}&limit=3&sort_direction=${sort}">${ page.index + 1 }</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	</div>
</body>
</html>