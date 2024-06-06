<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>成績登録</title>
</head>
<body>
    <h2>成績登録フォーム</h2>
    <form action="TestRegistAction" method="post">
        <label for="studentId">学生ID:</label>
        <input type="text" id="studentId" name="studentId" required><br>
        <label for="subject">科目:</label>
        <input type="text" id="subject" name="subject" required><br>
        <label for="score">成績:</label>
        <input type="text" id="score" name="score" required><br>
        <input type="submit" value="登録">
    </form>
</body>
</html>
