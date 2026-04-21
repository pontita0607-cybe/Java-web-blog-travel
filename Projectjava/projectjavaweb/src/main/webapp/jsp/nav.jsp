<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
	
<link href="https://fonts.googleapis.com/css2?family=Prompt:wght@300&display=swap"rel="stylesheet">

<style>
body {
	font-family: 'Prompt', sans-serif;
}

nav {
	display: flex;
	justify-content: space-between;
	align-items: center;
	color: black;
	margin-top: 5px;
	margin-bottom: 5px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	/* เพิ่มเงาด้านล่างเท่านั้น */
}

.linkmenu {
	margin-left: 20px;
}

.alink2 {
	text-decoration: none;
	color: black;
}

.alink2:hover {
	text-decoration: none;
	color: black;
}

.alink {
	color: black;
	text-decoration: none;
	margin-top: 15px;
	font-size: 18px;
	margin-left: 20px
}

nav .alink:hover {
	text-decoration: underline;
	color: #0783FF;
}

nav div {
	display: flex;
	align-items: center;
}

.Travel {
	font-weight: bold;
	font-size: 45px; /* ปรับขนาดตัวอักษรของข้อความ "Travel" */
	font-weight: bold; /* ทำให้ตัวอักษรหนาขึ้น */
	margin-left: 50px;
	margin-top: 15px;
}

.boxContainer {
	margin: auto;
	margin-top: o;
	position: relative;;
	width: 300px;
	height: 40px;
	border: 1px solid #0783FF;
	padding: 0px 10px;
	border-radius: 50px;
	margin-left: 280px;
}

.elementContainer {
	height: 100%;
	width: 100%;
	vertical-align: middle;
}

.search {
	border: none;
	height: 110%;
	width: calc(135% - 80px);
	/* ลบ 40px จากความกว้างเพื่อให้มีพื้นที่สำหรับปุ่มค้นหา */
	padding: 0px 10px;
	border-radius: 20px;
	font-size: 18px;
	color: #424242;
	font-weight: 500;
	outline: none;
}

.search:focus {
	outline: none; /* เอาเส้นขอบออก */
}

.search-button {
	background-color: transparent;
	border: none;
	height: 100%;
	width: 40px;
	cursor: pointer;
}

.material-icons {
	font-size: 26px;
	color: #0783FF;
	margin-top: -5px;
	vertical-align: middle; /* จัดให้ไอคอนอยู่ตรงกลาง */
}

.nav-link {
	text-decoration: none;
	color: #000000;
	margin: 0 10px;
	font-size: 18px;
}

.nav-link2 {
	background-color: #0783FF;
	font-size: 18px;
	color: #fff;
	padding: 10px 50px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
	border-radius: 5px;
	margin-left: 20px;
	margin-right: 50px;
	transition: background-color 0.3s ease;
	text-decoration: none;
}

.nav-link2:hover {
	background-color: #0a9dff;
	cursor: pointer;
}

.nav-link3 {
	margin-right: 40px;
	font-size: 18px;
	margin-top: 10px
}

.nav-link4 {
	margin-right: 100px;
	color: #000000; /* สีของตัวอักษร */
	transition: background-color 0.3s ease; /* อนิเมชันเมื่อ hover */
	text-decoration: none;
	font-size: 18px;
}

.nav-link4:hover {
	color: #6C2C80; /* เปลี่ยนสีเมื่อ hover */
}
</style>
</head>
<body>
	<nav>
		<div>
			<a class="alink2" href="/"><p class="Travel">Travel</p></a>
			<div class="linkmenu">
				<a class="alink" href="/blog">Blog</a> <a class="alink"
					href="/shownews">News</a> <a class="alink" href="/report">Report</a>
				<a class="alink" href="/user/bookmarks">Bookmark</a> 
				<a class="alink" href="/user/likeuser">likeUser</a>
			</div>
			<div class="boxContainer">
				<form action="${pageContext.request.contextPath}/searchBlog"
					method="get" class="search-form">
					<table class="elementContainer">
						<tr>
							<td><input type="text" id="keyword" name="keyword"
								class="search" placeholder="Search"></td>
							<td>
								<button type="submit" class="search-button">
									<i class="material-icons">search</i>
								</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="navbarNav" class="nav-collapse">
			<c:choose>
				<c:when test="${empty name}">
					<%-- หากยังไม่มีการเข้าสู่ระบบ แสดงลิงก์ "Login" และ "Register" --%>
					<a href="/login" class="nav-link">Login</a>
					<a href="/register" class="nav-link2">Register</a>
				</c:when>
				<c:otherwise>
					<h1 class="nav-link3">${name}</h1>
					<%-- หากมีการเข้าสู่ระบบแล้ว แสดงลิงก์ "Logout" --%>
					<a href="/logout" class="nav-link4">Logout</a>
				</c:otherwise>
			</c:choose>
		</div>
	</nav>
</body>
</html>
