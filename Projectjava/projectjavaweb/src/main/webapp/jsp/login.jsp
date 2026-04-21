<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/css/login.css">
</head>
<body>
     <div class="container">
        <div class="background-image-container">
            <img src="/images/imageLogo.png" alt="" class="background-image">
        </div>
        <form action="/login" method="post">
           <img class="logoLogin" src="./images/login.png" alt="">
            <h1 class="loginTitle">Log in</h1>
            
           <label for="email" class="label1">Email:</label><br>
           <input type="email" id="email" name="email" required><br>
           
           <label for="password" class="labelPassword">Password:</label><br>
           <div class="password-container">
               <input type="password" id="password" name="password" required><br>
           </div>
           
           <button type="submit">Sign In</button>
       
           <div class="resetpassword">
               <a href="/resetpassword">Change Password?</a>
           </div>
           
          <p class="Signup">Don't you have an account? <a href="/register">register</a></p>
          
          <c:if test="${not empty errorMessage}">
             <p style="color: red;">${errorMessage}</p>
          </c:if>
     </form>
    
    </div>
</body>
</html>