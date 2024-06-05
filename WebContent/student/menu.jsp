<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<%@ include file="../head.html" %>

<body>
<%= session.getAttribute("customer") %>
<%@ include file="../header.html" %>

<h1>ログイン成功しました</h1>
<div>${custmer}</div>

<%@ include file="../footer.html" %>

</body>
</html>