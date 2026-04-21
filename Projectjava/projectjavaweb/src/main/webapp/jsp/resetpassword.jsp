<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
    <link rel="stylesheet" type="text/css" href="/css/resetoassword.css">
</head>
<body>
    
     <div class="container">
        
        <div class="background-image-container">
            <img src="/images/imageLogo.png" alt="" class="background-image">
        </div>
        
        <c:if test="${not empty successMessage}">
            <p style="color: green;">${successMessage}</p>
         </c:if>
         
        <c:if test="${not empty errorMessage}">
            <p style="color: red;">${errorMessage}</p>
        </c:if>
        
        <form action="resetpassword" method="post">
            <img class="logoLogin" src="./images/login.png" alt="">
            <h1 class="ChangeTitle">Forget Password</h1>
            
            <label for="email">Email:</label><br>
            <input type="email" id="email" name="email" required><br><br>
        
            <label for="newPassword" class="newPassword">New Password:</label><br>
            <input type="password" id="newPassword" name="newPassword" required><br><br>

            <label for="confirmNewPassword" class="confirmNewPassword">Confirm New Password:</label><br>
            <input type="password" id="confirmNewPassword" name="confirmNewPassword" required><br><br>

        
            <button type="submit"  value="Reset Password">Reset Password</button>
            
            <p class="Signup">Already have an account?<a href="/login"> Log in</a></p>
        </form>
    
    </div>
</body>
</html>