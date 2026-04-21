<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home User</title>
<link rel="stylesheet" type="text/css" href="/css/homeUser.css">

<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

</head>
<body>
	<%@ include file="nav.jsp"%>

	<div id="carouselExampleDark" class="carousel carousel-dark slide">
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#carouselExampleDark"
				data-bs-slide-to="0" class="active" aria-current="true"
				aria-label="Slide 1"></button>
			<button type="button" data-bs-target="#carouselExampleDark"
				data-bs-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-bs-target="#carouselExampleDark"
				data-bs-slide-to="2" aria-label="Slide 3"></button>
			<button type="button" data-bs-target="#carouselExampleDark"
				data-bs-slide-to="3" aria-label="Slide 3"></button>
			<button type="button" data-bs-target="#carouselExampleDark"
				data-bs-slide-to="4" aria-label="Slide 4"></button>
		</div>
		<div class="carousel-inner">
			<div class="carousel-item active" data-bs-interval="10000">
				<img src="./images/img4.png" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item" data-bs-interval="2000">
				<img src="./images/imgNorth.png" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="./images/imgCentral.png" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="./images/imgIsan.png" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="./images/imgSouth.png" class="d-block w-100" alt="...">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleDark" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleDark" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>

	<div class="divnamecategories">
		<p class="namecategories">Categories</p>
	</div>
	<div class="categories">
		<div>
			<a href="/seaType">ทะเล</a>
		</div>
		<div>
			<a href="/mountainType">ภูเขา</a>
		</div>
		<div>
			<a href="/waterfallType">น้ำตก</a>
		</div>
		<div>
			<a href="/templeType">วัด</a>
		</div>
		<div>
			<a href="/nationalparkType">อุทยานแห่งชาติ</a>
		</div>
		<div>
			<a href="/dampondType">เขื่อน/บึง</a>
		</div>
		<div>
			<a href="/museumType">พิพิธภัณฑ์</a>
		</div>
		<div>
			<a href="/themeparkType">สวนสนุก</a>
		</div>
		<div>
			<a href="/otherType">อื่นๆ</a>
		</div>
		<div>
			<a href="/cafeType">คาเฟ่</a>
		</div>
		<div>
			<a href="/marketwalkingstreetType">ตลาด/ถนนคนเดิน</a>
		</div>
		<div>
			<a href="/zooType">สวนสัตว์</a>
		</div>
	</div>




	<div class="travelclass">
		<h2 class="popular">Travel Popular</h2>
	</div>

	<div class="card-container">
    <!-- Iterate through top 4 blogs with highest viewCount -->
    <c:forEach items="${blogList}" var="blog" varStatus="loop">
        <div class="classa">
            <a href="/blog/${blog.id}" class="card-col">
                <div class="card mb-4">
                    <a href="/viewCount/${blog.id}" class="button-love"> 
                        <img src="data:image/jpg;base64,${blog.imageDataAsBase64}" 
                            class="card-img-top" alt="..." style="height: 210px; width: 100%;">
                        <div class="card-body">
                            <h5 class="card-title" style="font-weight: bold;">${blog.title}</h5>
                            <p>จำนวนการกดไลค์: ${blog.likeCount}</p>
                            <p>จำนวนการเข้าชม: ${blog.viewCount}</p>
                            <a class="Like" href="/likeHomeUser/${blog.id}">
                                <span class="material-symbols-outlined">thumb_up</span>
                            </a>
                            <a class="Bookmark" href="/bookmark3/${blog.id}">
                                <span class="material-symbols-outlined">bookmark</span>
                            </a>
                        </div>
                    </a>
                </div>
            </a>
         </div>    
    </c:forEach>
</div>




	<%@ include file="footer.jsp"%>








</body>
</html>