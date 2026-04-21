<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	background-color: #f8f9fa; /* สีพื้นหลังของหน้าเว็บ */
	color: #333; /* สีของเนื้อหา */
	margin: 0; /* ลบระยะขอบของ body */
	padding: 0; /* ลบระยะห่างภายในขอบของ body */
}

h1 {
	text-align: center;
}

a.login {
	display: block; /* ทำให้ลิงก์เป็นพื้นที่กดได้ทั้งหมด */
	width: 200px; /* กำหนดความกว้าง */
	margin: 32px auto; /* จัดข้อความตรงกลางแนวนอน */
	text-align: center; /* จัดข้อความตรงกลาง */
	padding: 10px; /* เพิ่มระยะห่างภายในลิงก์ */
	background-color: #007bff; /* สีพื้นหลังของลิงก์ */
	color: #fff; /* สีของข้อความลิงก์ */
	text-decoration: none; /* ลบเส้นใต้ของลิงก์ */
	border-radius: 5px; /* เพิ่มมุมโค้งให้ลิงก์ */
	margin-top: 30px font-size: 30px;
}

a.login:hover {
	background-color: #0056b3; /* เปลี่ยนสีพื้นหลังของลิงก์เมื่อโฮเวอร์ */
}

.alink:nth-of-type(4) {
	color: #0783FF;
	font-weight: bold;
	text-decoration: underline;
}
</style>
</head>
<body>
	<%@ include file="nav.jsp"%>

	<h1 style="margin-top: 70px; font-size: 50px;">คุณต้องเข้าสู่ระบบก่อน</h1>
	<a href="/login" class="login">login</a>

	<%@ include file="footer.jsp"%>
</body>
</html>
