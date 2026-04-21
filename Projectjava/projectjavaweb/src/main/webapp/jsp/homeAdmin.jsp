<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Admin</title>
<link rel="stylesheet" type="text/css" href="/css/howeAdmin.css">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<!-- Font Google Prompt -->
<link
	href="https://fonts.googleapis.com/css2?family=Prompt:wght@300&display=swap"
	rel="stylesheet">

<!-- Bootstrap JS -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>

<!-- Icon -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

<!-- Font Google Prompt -->
<link
	href="https://fonts.googleapis.com/css2?family=Prompt:wght@300&display=swap"
	rel="stylesheet">
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
				<!-- <h3><p>Admin : ${email}</p></h3> -->

				<p class="lead">Welcome Admin Page</p>
				<img src="/images/admin.png" alt="Admin" class="img">
			</div>
		</div>

	</div>


</body>
</html>