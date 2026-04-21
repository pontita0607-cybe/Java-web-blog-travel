<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User's Like</title>
    <link rel="stylesheet" href="/css/bookmark.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <style>
        .alink:nth-of-type(5) {
            color: #0783FF;
            font-weight: bold;
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <%@ include file="nav.jsp"%>
    <p class="Bookmark">Like</p>

    <div class="card-group">
        <c:forEach items="${likeCountList}" var="like">
            <div class="classa">
                <a href="/like/${like.blog.id}" class="card-col">
                    <div class="card mb-4">
                        <img src="data:image/jpg;base64,${like.blog.imageDataAsBase64}" width="327px" height="180px" />
                        <div class="card-body">
                            <h4 class="card-title">${like.blog.title}</h4>
                            <p>ประเภท : ${like.blog.type}</p>
                            <p>ผู้แต่ง : ${like.blog.writer}</p>
                            <a id="delete-${like.id}" href="/deleteLike/${like.id}" class="delete" onclick="toggleDeleteColor(${bookmark.id})">
                                <span class="material-symbols-outlined">heart_minus</span>
                            </a>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">${like.blog.post_date}</small>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
    <%@ include file="footer.jsp"%>
</body>
</html>
