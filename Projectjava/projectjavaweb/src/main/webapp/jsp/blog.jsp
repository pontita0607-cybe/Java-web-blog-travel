<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blog</title>
<link rel="stylesheet" href="/css/blog.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>

<link href="https://fonts.googleapis.com/css2?family=Prompt:wght@300&display=swap" rel="stylesheet">

<!-- Icon -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />



</head>
<body>

	<div class="container-fluid">
		<div class="row flex-nowrap">
			<div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
				<div
					class="d-flex flex-column align-items-center align-items-sm-start px-1 pt-4 text-white min-vh-100 ">
					<a href="/homeAdmin"
						class="d-flex1 align-items-center pb-3 mb-md-0 me-md-auto">
						<h1>Home</h1>
					</a>

					<ul
						class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start"
						id="menu">

						<li><a href="/showEditBlog"
							class="nav-link px-0 align-middle"> <i class="fs-4 bi-table"></i>
								<span class="material-symbols-outlined">view_comfy_alt</span>
								<h5 class="ms-1 d-none d-sm-inline">Blog</h5></a></li>
						<li><a href="/showNewsEdit"
							class="nav-link px-0 align-middle"> <i class="fs-4 bi-people"></i>
								<span class="material-symbols-outlined">fiber_new</span>
								<h5 class="ms-1 d-none d-sm-inline">News</h5>
						</a></li>
						<li><a href="/showReport" class="nav-link px-0 align-middle">
								<i class="fs-4 bi-people"></i> <span
								class="material-symbols-outlined">flag</span>
								<h5 class="ms-1 d-none d-sm-inline">Report</h5>
						</a></li>
					</ul>
					<hr>
					<c:if test="${not empty sessionScope.user}">
						<div class="dropdown pb-1">
							<a href="#"
								class="d-flex align-items-center text-white text-decoration-none dropdown-toggle"
								id="dropdownUser1" data-bs-toggle="dropdown"
								aria-expanded="false"> <span class="d-none d-sm-inline mx-1">
									<p>${name}</p></a>

							<ul class="dropdown-menu dropdown-menu-dark text-small shadow"
								aria-labelledby="dropdownUser1">

								<li><a class="dropdown-item" href="/logout">Logout</a></li>
							</ul>
						</div>
					</c:if>
				</div>
			</div>

			<div class="content">
				<p class="blog">BLOG</p>
				<div class="container">
					<a href="/insert" class="add"><button>+ เพิ่ม</button></a>
					<table>
						<tr>
							<th>ID</th>
							<th>Title</th>
							<th>Image</th>
							<th>Writer</th>
							<th>Upload Time</th>
							<th>Type</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
						<c:forEach items="${blogList}" var="blog">
							<tr>
								<td>${blog.id}</td>
								<td>${blog.title}</td>
								<td><img
									src="data:image/jpg;base64,${blog.imageDataAsBase64}"
									width="150px" height="100px" /></td>
								<td>${blog.writer}</td>
								<td>${blog.post_date}</td>
								<td>${blog.type}</td>
								<td><a class="edit" href="/edit/${blog.id}"><buttontype
											="submit" style="background-color: orange;">Edit
										</button></a></td>
								<td><a class="delete" href="/delete/${blog.id}"><buttontype
											="submit" style="background-color: red;">Delete
										</button></a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
</body>
</html>