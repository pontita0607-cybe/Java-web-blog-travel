<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Form</title>
    <link rel="stylesheet" type="text/css" href="/css/register.css">
    
</head>
<body>

 <div class="container">
      <div class="background-image-container">
           <img src="/images/imageLogo.png" alt="" class="background-image">
      </div>
    
    <form action="/register" method="post">
        <img class="logoLogin" src="/images/login.png" alt="">
        <h1 class="registerTitle">Register</h1>
        
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>
        
        <label for="password">Password:</label>
        <div class="password-container">
             <input type="password" id="password" name="password" required><br>
        </div>
        
        <div class="password-container">
             <label for="confirmPassword">Confirm Password:</label>
             <input type="password" id="confirmPassword" name="confirmPassword" required><br>
        </div>
        
        <!-- เพิ่มฟิลด์สำหรับประเภทผู้ใช้ -->
       <!-- <label for="type">User Type:</label>
        <select id="type" name="type">
            <option value="user">User</option>
            <option value="admin">Admin</option>
        </select><br> -->
        
       
          <div class="message">
             <!-- แสดงข้อความหากลงทะเบียนสำเร็จ -->
             <c:if test="${not empty messageSuccess}">
                <p style="color: red;">${messageSuccess}</p>
             </c:if>
             
                  <!-- แสดงข้อความหากลงทะเบียนไม่สำเร็จ -->
             <c:if test="${not empty messageFailed}">
                <p style="color: red;">${messageFailed}</p>
             </c:if>
        </div>
        
        <div class="submit">
            <button type="submit">Register</button>
        </div>
        
        <p class="accountSingin">Don't you have an account?
           <a href="/login">Go to Login Page</a>
        </p>
   
    </form> 
    
  </div>  
 
</body>
</html>