<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Read More</title>
<link rel="stylesheet" href="/css/blogReadmore.css">
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
<style>
.alink:nth-of-type(1) {
	color: #0783FF;
	font-weight: bold;
	text-decoration: underline;
}
</style>
</head>
<body>
	<%@ include file="nav.jsp"%>
	
	<div class="container">
		<h1 style="text-align: center; margin-top: 50px; font-size: 50px; font-weight: bold;">
			<c:out value="${blog.title}" />
		</h1>
		<h6 class="writer">ผู้แต่ง : <c:out value="${blog.writer}" /></h6>
		<div class="blogs-image">
			<c:if test="${not empty blog.imageData}">
				<div style="text-align: center; clear: both;">
					<img src="data:image/jpg;base64,${blog.imageDataAsBase64}"
					alt="blogs Image" style="width: 50%; height: 50%; margin-top: 20px;">
				</div>
			</c:if>
		</div>
		<div class="blogs-info">
			<p class="detail">
				<c:out value="${blog.detail}" />
			</p>
			<p class="detail">ประเภท : <c:out value="${blog.type}" />
			</p>
			<p class="detail">จำนวนการกดไลค์ : ${blog.likeCount}</p>
			<p class="detail">จำนวนการเข้าชม : ${blog.viewCount}</p>
		</div>
		<h6 class="date">เวลา : <c:out value="${blog.post_date}" /></h6>
<a href="/">Back to previous</a>
		<br><br>
		
		
		
		
		<div class="row d-flex justify-content-center">
			<div class="col-md-8 col-lg-12">
				<div class="card shadow-0 border" style="background-color: #f0f2f5;">
					<div class="card-body p-4">
						<h4>Comment</h4>
						<br>
						<div class="form-outline mb-4">
							<form action="/addComment" method="post">
									<input type="hidden" name="blogId" value="${blog.id}">
									<input type="hidden" name="userId" value="${user.id}">
									<input name="comment" type="text" id="addANote" class="form-control" placeholder="What do you think?" />
									<input type="submit" value="Submit" class="form-submit">
							</form>
						</div>
						<c:forEach var="comment" items="${commentList}">
							<div class="card mb-4">
								<div class="card-body">
									<p>${comment.content}</p>
									<div class="d-flex justify-content-between">
										<div class="d-flex flex-row align-items-center">
											<span class="material-symbols-outlined">account_circle</span>
											<p class="small mb-0 ms-2">${comment.user.name}</p>
										</div>
										<!-- Check if the logged-in user is the owner of the comment -->
										<c:if test="${comment.user.id == user.id}">
											<!-- Add a form for deleting the comment -->
											<form action="/deleteComment/${comment.id}" method="post">
												<input type="hidden" name="blogId" value="${blog.id}">
												<button type="submit" class="btn btn-link">
													<span class="material-symbols-outlined" style="color: red;">delete</span>
												</button>
											</form>
										</c:if>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>

