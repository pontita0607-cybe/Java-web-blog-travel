<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/blogReadmore.css">
<title>Museum Type</title>

<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<scrip
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"> </script>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>

<body>
	<%@ include file="nav.jsp"%>
	<h1 style="font-weight: bold; margin: 50px;">สถานที่ท่องเที่ยวประเภทพิพิธภัณฑ์</h1>

	<c:forEach var="blog" items="${museumBlogs}">
		<div class="container">
			<div>
				<h1
					style="text-align: center; margin-top: 50px; font-size: 50px; font-weight: bold;">
					<c:out value="${blog.title}" />
				</h1>
				<h6 class="writer">
					ผู้แต่ง :
					<c:out value="${blog.writer}" />
				</h6>
				<div class="blogs-image">
					<c:if test="${not empty blog.imageData}">
						<div style="text-align: center; clear: both;">
							<img src="data:image/jpg;base64,${blog.imageDataAsBase64}"
								alt="blogs Image"
								style="width: 50%; height: 50%; margin-top: 20px;">
						</div>
					</c:if>
				</div>
				<div class="blogs-info">
					<p class="detail">
						<c:out value="${blog.detail}" />
					</p>
					<p class="detail">
						ประเภท :
						<c:out value="${blog.type}" />
					</p>
					<p class="detail">จำนวนการกดไลค์ : ${blog.likeCount}</p>
					<p class="detail">จำนวนการเข้าชม : ${blog.viewCount}</p>
				</div>
				<h6 class="date">
					เวลา :
					<c:out value="${blog.post_date}" />
				</h6>
			</div>
			<a class="Like" href="/like/${blog.id}"><span
				class="material-symbols-outlined">thumb_up</span></a> <a
				class="Bookmark" href="/bookmark/${blog.id}"><span
				class="material-symbols-outlined">bookmark</span></a> <br> <a
				href="/">Back to previous</a>
		</div>
		<hr>
		</div>
	</c:forEach>

</body>
</html>