<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../header.jsp" %>
<% session.getAttribute("customer"); %>

<h1>ログイン成功しました</h1>
<div>${custmer}</div>

<%@ include file="../footer.jsp" %>

</body>
</html>