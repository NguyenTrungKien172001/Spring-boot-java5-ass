<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #111111;">
  <div class="container-fluid">
    <a class="navbar-brand" href="">Admin</a>
   
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
       	<li class="nav-item active">
	        <a class="nav-link active" href="${ pageContext.request.contextPath }/admin/users">Users</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link active" href="${ pageContext.request.contextPath }/admin/categories">Categories</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link active" href="${ pageContext.request.contextPath }/admin/products">Products</a>
	      </li>
	      <li class="nav-item active">
	        <a class="nav-link active" href="${ pageContext.request.contextPath }/admin/users/productInvoice">Bill Management</a>
	     </li>
	     
	      <li class="nav-item dropdown">
		   <a class="nav-link dropdown-toggle active" href="#" data-bs-toggle="dropdown">Bin</a>
		    <ul class="dropdown-menu dropdown-menu-dark">
			  <li><a class="dropdown-item" href="${ pageContext.request.contextPath }/admin/users/bin">User Bin</a></li>
			  <li><a class="dropdown-item" href="${ pageContext.request.contextPath }/admin/categories/bin">Categories Bin</a></li>
			  <li><a class="dropdown-item" href="${ pageContext.request.contextPath }/admin/products/bin">Products Bin</a></li>
		    </ul>
		  </li>
		  
	      <li class="nav-item dropdown">
		   <a class="nav-link dropdown-toggle active" href="#" data-bs-toggle="dropdown">Accout</a>
		    <ul class="dropdown-menu dropdown-menu-dark">
			  <li><a class="dropdown-item" href="${ pageContext.request.contextPath }/admin/users/changePW">Change Password</a></li>
			  <li><hr class="dropdown-divider"></li>
			  <li> <a class="dropdown-item" href="${ pageContext.request.contextPath }/login">Logout</a></li>
		    </ul>
		  </li>
	      
      </ul>
      <form class="d-flex">
       </form>
    </div>
  </div>
</nav>