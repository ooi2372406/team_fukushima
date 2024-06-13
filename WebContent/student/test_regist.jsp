<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../../head.jsp" %>

<%@ include file="../../header.jsp" %>
<main>
    <div class="kamokucontainer-create">
        <%@ include file="base.jsp" %>

        <form id="subjectForm" style="width:100%; margin-left:20px;" action="TestListSubjectExecute.action" method="post">
            <div class="form-group-create">

                <h2 style="width: 100%; text-align: left; background-color: gainsboro; padding: 10px 20px;  margin-bottom:20px;">学生管理</h2>
                <div style="text-align:right; padding-right:20px;"><a  href="SubjectCreate.action">新規登録</a></div>
                <div style="border:1px solid whitesmoke; height:120px;">
                <div  style="display:flex;  display: -webkit-flex; align-items: center; height:90px; margin-top:10px;">


                	<table class="responsive-table-seiseki" style="width:60%;">
                	<tr>
                	<th><label>入学年度</label></th>
                	<th><label>クラス</label></th>
                	<th><label>科目</label></th>
                	<th><label>回数</label>

                	</tr>
                	<tr>
                	<td>
                	<select style="width:80%; border-radius:5%;  padding-top:5px; padding-bottom:5px;" name="f1">
                	<option selected>--------</option>
					<c:forEach var="i" items="${student}">
						<option>${i.entYear}</option>
					</c:forEach>
                	</select>
                	</td>


                	<td>
                	<select style="width:80%; border-radius:5%;  padding-top:5px; padding-bottom:5px;" name="f2" >
                	<option selected>--------</option>
					<c:forEach var="i" items="${student}">
						<option>${i.classNum}</option>
					</c:forEach>
                	</select>
                	</td>
                	<td>
                	<select style="width:80%; border-radius:5%;  padding-top:5px; padding-bottom:5px;" name="f1">
                	<option selected>--------</option>
					<c:forEach var="i" items="${subject}">
						<option>${i.name}</option>
					</c:forEach>
                	</select>
                	</td>

                	<td>
                	<select style="width:80%; border-radius:5%;  padding-top:5px; padding-bottom:5px;" name="f1">
                	<option selected>--------</option>
					<c:forEach var="i" begin="1" end="3">
						<option>${i}</option>
					</c:forEach>


                	</select>
                	</td>
                	</tr>
                	</table>

                	<div style="margin-left:15%;"><button class="btn btn-primary" type="submit" value="検索">絞込み</button></div>
					</div>
                </div>
                </form>



</main>

<%@ include file="../../footer.jsp" %>

<script src="../javascript/subject.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>


</body>
</html>
