<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>


<!-- display:flexでmenu.jspとcontentを横並びにする -->
<div class="container" style="display:flex; height:100vh;">

	<!-- flexアイテム左側としてbase.jspを読み込む -->
	<%@include file="base.jsp" %>

	<!-- flexアイテム右側としてdiv class="content" -->
	<div class="content " style="flex:0 0 65%; padding-left:20px ">

	        <div class="title" style="padding: 10px; background-color: whitesmoke;">
	            メニュー
	        </div>
<br>

		<div class="container" style="display: flex; flex-direction: columu ; justify-content: center; padding: 0 10px 10px 10px; text-align: center;">

			<div class="card" style="display: flex; flex-direction: columu ;
			align-items: center; justify-content: center; height: 8rem; background-color: #dbb; margin: 0 10px; border-radius: 0.25rem; box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15); width: 200px;  height: 90px;">
				<a href="/student_management/student/StudentList.action" style= text-decoration:none;>学生管理</a>
			</div>
			<div class="card" style="display: flex; flex-direction: columu;
			align-items: center; justify-content: center; height: 8rem; background-color: #C2D8B1; margin: 0 10px; border-radius: 0.25rem; box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15); width: 200px;      height: 90px;">

				<div class="text">成績管理<br>
				<a href="TestRegist.action" style= text-decoration:none;>成績登録</a><br>
				<a href="TestList.action" style= text-decoration:none;>成績参照</a>
				</div>
			</div>
			<div class="card" style="display: flex; align-items: center; justify-content: center; height: 8rem; background-color: #B1B2D8; margin: 0 10px; border-radius: 0.25rem; box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15); width: 200px;  height: 90px;">
				<a href="/student_management/subject/SubjectList." style= text-decoration:none;>科目管理</a>
			</div>
		</div>
	</div>
</div>
<%@ include file="../footer.jsp" %>

</body>
</html>