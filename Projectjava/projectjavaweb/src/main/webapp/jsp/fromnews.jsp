<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>News Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        form {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"],
        input[type="file"],
        textarea {
            width: calc(100% - 12px);
            padding: 6px;
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
            margin-left: 45%;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>News Form</h2>
    <form action="/news" method="post" enctype="multipart/form-data">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required><br>

        <label for="image">Image:</label>
        <input type="file" id="image" name="imageData" accept="image/*" required><br>

        <label for="content">Content:</label>
        <textarea id="content" name="detail" rows="6" required></textarea><br>

        <label for="author">Author:</label>
        <input type="text" id="author" name="writer" required><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>