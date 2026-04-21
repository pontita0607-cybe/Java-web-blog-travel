<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/add.css">
<title>เพิ่มสถานที่ท่องเที่ยว</title>
</head>
<body>
	<h1>Blog Form</h1>
	<form action="/blogs" method="post" enctype="multipart/form-data">
		<label for="title">หัวข้อ:</label> <input type="text" id="title"
			name="title" required><br> <label for="image">รูปภาพ:</label>
		<input type="file" id="image" name="imageData" accept="image/*"
			required><br> <label for="content">เนื้อหา:</label>
		<textarea id="content" name="detail" rows="6" required></textarea>
		<br> <label for="author">ผู้แต่ง:</label> <input type="text"
			id="author" name="writer" required><br> <label
			for="typeBlog">ประเภท:</label> <select id="typeBlog" name="type"
			required>
			<option value="" disabled selected>โปรดเลือกประเภทสถานที่ท่องเที่ยว</option>
			<option value="ภูเขา">ภูเขา</option>
			<option value="ทะเล">ทะเล</option>
			<option value="น้ำตก">น้ำตก</option>
			<option value="วัด">วัด</option>
			<option value="อุทยานแห่งชาติ">อุทยานแห่งชาติ</option>
			<option value="สวนสาธารณะ">สวนสาธารณะ</option>
			<option value="เขื่อน">เขื่อน/บึง</option>
			<option value="พิพิธภัณฑ์">พิพิธภัณฑ์</option>
			<option value="สวนสนุก">สวนสนุก</option>
			<option value="สวนสัตว์">สวนสัตว์</option>
			<option value="คาเฟ่">คาเฟ่</option>
			<option value="ตลาด">ตลาด/ถนนคนเดิน</option>
			<option value="อื่นๆ">อื่นๆ</option>
		</select> <input type="submit" value="Submit">
	</form>
</body>
</html>

