<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blog</title>
<link rel="stylesheet" href="/css/showBlog.css">

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
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

<link href="https://fonts.googleapis.com/css2?family=Prompt:wght@300&display=swap" rel="stylesheet">

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
	<p class="blog">All BLOG</p>


	<div class="card-group">
		<c:forEach items="${blogList}" var="blog">
			<div class="classa">
				<a href="/blog/${blog.id}" class="card-col" >
				    <a href="/viewCount/${blog.id}" class="button-love"> 
					<div class="card mb-4">

						<img src="data:image/jpg;base64,${blog.imageDataAsBase64}"
							width="327px" height="180px" />

						<div class="card-body">
							<h5 class="card-title">${blog.title}</h5>
							<p><a href="/blog/${blog.id}" class="read">Read More</a></p>
							<p>จำนวนการกดไลค์: ${blog.likeCount}</p>
							<p>จำนวนการเข้าชม: ${blog.viewCount}</p>

							<a class="Like" href="/like/${blog.id}"><span class="material-symbols-outlined">thumb_up</span></a> 
							<a id="bookmark-${blog.id}" class="Bookmark" href="/bookmark/${blog.id}" onclick="toggleBookmarkColor(${blog.id})"> 
								<span class="material-symbols-outlined">bookmark</span>
							</a>
						</div>

						<div class="card-footer">
							<small class="text-muted">${blog.post_date}</small>
						</div>
					</div>
					</a>
				</a>
			</div>
		</c:forEach>
	</div>

	<%@ include file="footer.jsp"%>
	
	
	
</body>
</html>
