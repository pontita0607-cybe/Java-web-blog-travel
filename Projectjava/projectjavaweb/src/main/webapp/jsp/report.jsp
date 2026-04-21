<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Report</title>
<link rel="stylesheet" type="text/css" href="/css/report.css">
<style>
.alink:nth-of-type(3) {
	color: #0783FF;
	font-weight: bold;
	text-decoration: underline;
}
</style>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<!-- เพิ่มฟอร์มเพื่อเพิ่มข้อมูล -->
	<div class="add-report-container">
		<h2>Add New Report</h2>
		<form action="/addReport" method="GET">
			<div class="forum-item">
				<span class="forum-label">Title:</span> <input type="text"
					name="title" class="text">
			</div>
			<div class="forum-item">
				<span class="forum-label" style="margin-top: 30px;">Detail:</span>
				<textarea name="detail" class="text" style="height: 100px;"></textarea>
			</div>
			<button type="submit" class="sub">Add Report</button>
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>