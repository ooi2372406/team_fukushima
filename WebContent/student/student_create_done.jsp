<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../head.jsp" %>
<%@ include file="../../header.jsp" %>
<%  %>
<main>
	<div class="kamokucontainer-create" >

	<%@ include file="./base.jsp" %>







  <div class="form-group-create" style="width:100%; margin-left:20px;">
  <h2 style="width: 100%;text-align: left; background-color:gainsboro; padding:10px 20px 10px 20px;">学生情報登録</h2>
  <div style="width: 100%;text-align: center; background-color:seagreen; padding:10px 20px 10px 20px; margin-bottom:100px;">登録が完了しました</div>


		<div class="d-flex flex-row bd-highlight mb-3">
		<div style="margin-right: 20px;"><a href="StudentCreate.action">戻る</a></div>
  		<div><a href="StudentList.action">学生一覧</a></div>

  	</div>
  </div>


</div>
</main>


<%@ include file="../../footer.jsp" %>

<script src="../javascript/login.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>


</body>
</html>