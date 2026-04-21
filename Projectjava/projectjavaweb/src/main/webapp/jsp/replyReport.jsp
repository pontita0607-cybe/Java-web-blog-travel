<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Read More</title>
    <link rel="stylesheet" type="text/css" href="/css/replyReport.css">
</head>
<body>

    <h1 style="text-align: center; text-shadow: 2px 2px 4px #888888;">รายงานปัญหาของผู้ใช้งาน</h1>
<div class="replyReport-container">
    <div class="report-item">
        <div class="forum-item">
            <span class="forum-label">ID:</span> <span class="forum-value">${report.id}</span>
        </div>
        <div class="forum-item">
            <span class="forum-label">Title:</span> <span class="forum-value">${report.title}</span>
        </div>
        <div class="forum-item">
            <span class="forum-label">Detail:</span> <span class="forum-value">${report.detail}</span>
        </div>
        <div class="forum-item">
            <span class="forum-label">Port Date:</span> <span class="forum-value">${report.port_date}</span>
        </div>
    </div>
</div>
<form action="/replyReport" method="post">
    <!-- Hidden input to send report id -->
    <input type="hidden" name="reportId" value="${report.id}">
    <!-- Textarea for reply content -->
    <textarea name="replyContent" placeholder="กรุณาใส่เนื้อหาการตอบกลับ" rows="4" cols="50"></textarea><br>
    <!-- Submit button -->
    <input type="submit" value="ตอบกลับ">
</form>


</body>
</html>