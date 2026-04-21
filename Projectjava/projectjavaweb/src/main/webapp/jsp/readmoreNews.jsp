<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Read More</title>
<style>
h2 {
text-align: center;
margin-bottom: 20px;
}
.news-info {
margin-bottom: 20px;
}
.news-info label {
font-weight: bold;
}
.news-info p {
margin: 5px 0;
}
.news-image {
text-align: center;
margin: 40px 0;
}
.news-image img {
max-width: 50%;
box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
.detail{
			margin-top: 30px;
			font-size: 17px;
		}
</style>
</head>
<body>
<%@ include file="nav.jsp"%>
<div class="container">
<h1 style="margin-top: 50px; font-size: 40px; font-weight: bold;"><c:out value="${news.title}" /></h1>
<p class="detail">บทความโดย : <c:out value="${news.writer}" /></p>
<div class="news-image">
<%-- Check if image data is not null before rendering --%>
<c:if test="${not empty news.imageData}">
<img src="data:image/jpg;base64,${news.imageDataAsBase64}" alt="News Image">
</c:if>
</div>
<div class="news-info">
<p class="detail"><c:out value="${news.detail}" /></p><br>
<h6 style="text-align: right; font-size: 14px;">เวลา : <c:out value="${news.post_date}" /></h6>
</div>
<a href="/shownews">Back to previous</a>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>





