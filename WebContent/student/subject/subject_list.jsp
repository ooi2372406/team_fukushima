<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../head.jsp" %>
<%@ include file="../../header.jsp" %>
<%  %>
<main>
	<div class="kamokucontainer-create" >

	<%@ include file="../base.jsp" %>



 <table style="flex:0 0 65%; padding-left:20px; margin-left:20px; height:150px;" >
<tr>
<td colspan="4"><h2 style="width: 100%;text-align: left; background-color:gainsboro; padding:10px 20px 10px 20px;">科目管理</h2></td>
</tr>
<tr>
<td colspan="4"  style="text-align:right; padding-right:20px;"><a href="SubjectCreate.action">新規登録</a></td>
</tr>
<c:choose>
	<c:when test="${empty subject}">
<tr style="border-bottom: solid 1px gainsboro;">
<th style="width:49%;">科目コード</th>
</c:when>
<c:when test="${not empty subject }">
<tr style="border-bottom: solid 1px gainsboro;">
<th style="width:20%;">科目コード</th>
</c:when>
</c:choose>
<th style="width:60%;">科目名</th>
<th></th>
<th></th>
</tr>

<c:forEach var="subject" items="${subject}">

<tr style="border-bottom: solid 1px gainsboro;">

<td>${subject.cd}</td>
<td>${subject.name}</td>

<td>
     <form action="SubjectUpdate.action" method="post" style="display:inline;">
            <input type="hidden" name="cd" value="${subject.cd}">
            <button type="submit" class="btn btn-link" style="padding:0; border:none; background:none;">変更</button>
     </form>
</td>
<td>
     <form action="SubjectDelete.action" method="post" style="display:inline;">
            <input type="hidden" name="cd" value="${subject.cd}">
            <button type="submit" class="btn btn-link" style="padding:0; border:none; background:none;">削除</button>
      </form>
</td>

</tr>

</c:forEach>

</table>


</div>
</main>


<%@ include file="../../footer.jsp" %>

<script src="../javascript/login.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>


</body>
</html>