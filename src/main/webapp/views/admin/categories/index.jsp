<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Views Category</title>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" ></link>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<script type="text/javascript">
	function oncClickDelete(event) {
		var check=confirm('Bạn muốn xóa?');
		if(check===false){
			event.preventDefault()
		}else if(check===true){
			alert("Xóa thành công!")
		}
	}

</script>
</head>
<body>
	<div>
		<jsp:include page="/views/admin/common/nav.jsp"></jsp:include>
	</div>
	<div class="col-10 offset-1 mt-4">
	<h2>Danh mục</h2>
	</div>
	<div class="col-10 offset-1 mt-5 border border-gray p-2">
		<form method="GET" action="${ pageContext.request.contextPath }/admin/categories">
			<div class="row col-12 mt-2">
				<div class="col-6">
					<label>Sắp xếp theo</label>
					<select name="sort_by" class="form-control">
						<option value="id">Mặc định</option>
						<option value="name" >Họ Tên</option>
						<option value="activate" >Trạng thái</option>
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
				<a href="${ pageContext.request.contextPath }/admin/categories"
					class="btn btn-danger mt-4" type="reset">
					Reset
				</a>
			</div>
		</form>
	</div>

	<div class="mt-5 col-10 offset-1 border border-gray p-2">
		<div class="">
			<a
				class="btn btn-success col-1"
				href="${ pageContext.request.contextPath }/admin/categories/create">Create</a>
		</div>
		  <table class="table table-strip table-dark mt-3">
			<thead>
				<tr>
					<td>Id</td>
					<td>Username</td>
					<td>Trạng thái</td>
					<td colspan="2">Thao tác</td>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${ pageData.content }" var="category">
				<tr>
						<td>${ category.id }</td>
						<td>${ category.name }</td>
						<td>${ category.activate == 1 ? "Đang hoạt động" : "Vô hiệu hóa" }</td>
						<td>
							<a
								class="btn btn-primary"
								href="${ pageContext.request.contextPath }/admin/categories/edit/${category.id}">Update</a>
						</td>
						<td>
							<form action="${ pageContext.request.contextPath }/admin/categories/delete/${category.id}" method="POST">
								<button class="btn btn-danger" onclick="oncClickDelete(event)">Delete</button>
							</form>
						</td>
					</tr>
			</c:forEach>
			</tbody>
		</table>
		
		<div>
			<ul class="pagination">
				<c:forEach begin="0" end="${pageData.totalPages>0?pageData.totalPages-1:0}" varStatus="page">
					<li class="page-item">
						<a class="page-link" href="${ pageContext.request.contextPath }/admin/categories?page=${page.index}&limit=3&sort_direction=${sort}">${ page.index + 1 }</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>