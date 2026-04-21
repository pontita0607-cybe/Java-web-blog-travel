<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit News</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            font-weight: bold;
            margin-bottom: 5px;
        }
        input[type="text"],
        textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        textarea {
            height: 100px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: right;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .image-preview {
            margin-bottom: 20px;
        }
        .image-preview img {
            max-width: 100%;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .delete-button {
            background-color: #ff5c5c;
        }
        .delete-button:hover {
            background-color: #ff3b3b;
        }
    </style>
</head>
<body>
    
    
    <div class="container">
        <h2>Edit News</h2>
        <form action="/edit2/${news.id}" method="post" enctype="multipart/form-data">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" value="${news.title}" required><br>

            <label for="content">Content:</label>
            <textarea id="content" name="detail" rows="6" required>${news.detail}</textarea><br>

            <label for="author">Author:</label>
            <input type="text" id="author" name="writer" value="${news.writer}" required><br>

            <label for="image">Image:</label>
            <div class="image-preview">
                <%-- Check if image data is not null before rendering --%>
                <c:if test="${news.imageData != null}">
                    <img src="data:image/jpg;base64,${news.imageDataAsBase64}" alt="Submitted Image">
                    <br>
                    <a class="delete-button" href="/deleteimage1/${news.id}">Delete Image</a>
                </c:if>
            </div>
            <input type="file" id="image" name="imageData" accept="image/*"><br>

            <input type="submit" value="Update">
        </form>
    
    </div>
</body>
</html>