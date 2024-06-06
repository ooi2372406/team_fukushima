<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../header.jsp" %>

<!-- display:flexでmenu.jspとcontentを横並びにする -->
<div class="container" style="display:flex; height:100vh;">
	<!-- flexアイテム左側としてbase.jspを読み込む -->
	<%@include file="base.jsp" %>

	<!-- flexアイテム右側としてdiv class="content" -->
		<div class="content d-flex justify-content-between" style="flex:0 0 85%; padding-left:20px ">

    <div class="cl d-flex align-items-center justify-content-center mx-2 rounded shadow"
         style="height:10rem; background-color:#dbb;">
        <a href="StudentList.action">学生管理1</a>
    </div>
    <div class="cl d-flex align-items-center justify-content-center mx-2 rounded shadow"
         style="height:10rem; background-color:#dbb;">
        <a href="StudentList.action">学生管理2</a>
    </div>
    <div class="cl d-flex align-items-center justify-content-center mx-2 rounded shadow"
         style="height:10rem; background-color:#dbb;">
        <a href="StudentList.action">学生管理3</a>
    </div>
</div>

</div>

<%@ include file="../footer.jsp" %>

</body>
</html>