<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登録完了</title>
</head>
<body>
    <h2>成績登録完了</h2>
    <p>以下の成績が登録されました:</p>
    <p>学生ID: ${studentId}</p>
    <p>科目: ${subject}</p>
    <p>成績: ${score}</p>
    <a href="test_regist.jsp">戻る</a>
</body>
</html>
