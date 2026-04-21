<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Submitted News</title>
<link rel="stylesheet" type="text/css" href="/css/shownews.css">
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
<style>
.alink:nth-of-type(2) {
	color: #0783FF;
	font-weight: bold;
	text-decoration: underline;
}
</style>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div class="travelclass">
		<p class="blog">All News</p>
	</div>
	
	<div class="card-group">
		<!-- Iterate through blogList and create cards -->
		<c:forEach items="${newslist}" var="news">
			<div class="classa">
				<a href="news/${news.id}" class="card-col">
					<div class="card mb-4">
							<c:if test="${news.imageData != null}">
								<img src="data:image/jpg;base64,${news.imageDataAsBase64}" class="card-img-top"
								style="height: 210px; width: 100%;">
							</c:if>
						
						<div class="card-body">
							<div class="news-info">
								<h6><c:out value="${news.title}" /></h6>
								<p><a href="/news/${news.id}" class="read">Read More</a></p>
							</div>
						</div>
						
						<div class="card-footer">
							<small class="text-muted">${news.post_date}</small>
						</div>
					</div>
				</a>
			</div>
		</c:forEach>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>

