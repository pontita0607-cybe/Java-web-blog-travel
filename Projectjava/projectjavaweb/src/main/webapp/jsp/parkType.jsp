<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
<title>Insert title here</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Park Type</title>
</head>
<body>
    <%@ include file="nav.jsp"%>
    <h1>สถานที่ท่องเที่ยวประเภทสวนสาธารณะ</h1>
    
    <c:forEach var="blog" items="${parkBlogs}">
        <div>
            <h2>${blog.title}</h2>
            <img src="data:image/jpg;base64,${blog.imageDataAsBase64}" width="100" height="100" />
            <p>รายละเอียด: ${blog.detail}</p>
            <p>ผู้เขียน: ${blog.writer}</p>
            <p>เวลา : ${blog.post_date}</p>
            <p>ประเภท: ${blog.type}</p>
            <a href="/blog/${blog.id}">Read More</a>
            
            
            <p>จำนวนการกดไลค์: ${blog.likeCount}</p>
				  <p>จำนวนการเข้าชม: ${blog.viewCount}</p>
				<a class="Like" href="/like/${blog.id}">Like</a>
                <a class="Bookmark" href="/bookmark/${blog.id}">Bookmark</a>View history   
        </div>
        <hr>
    </c:forEach>
    
</body>
</html>