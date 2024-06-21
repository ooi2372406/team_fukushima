<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../head.jsp" %>
<%@ include file="../../header.jsp" %>
<%  %>
<main>
    <div class="kamokucontainer-create">
        <%@ include file="./base.jsp" %>

        <form id="subjectForm" style="width:100%; margin-left:20px;" action="StudentUpdateExecute.action" method="post">
            <div class="form-group-create">
                <h2 style="width: 100%;text-align: left; background-color:gainsboro; padding:10px 20px 10px 20px;">学生情報変更</h2>
                <label for="subjectCode" style="margin:20px 0 0 5px;">入学年度</label>
                <input type="text"   style="margin-left:20px; width:95%; border:none;" readonly value="${student.entYear }">
                <div style="margin:20px 0 0 20px;"></div>
                <c:if test="${ not empty message }">
                	<div id="serverError" style="color: gold;　margin-left:20px;"></div>
                </c:if>



            </div>
            <div class="form-group-create">
                <label for="subjectName" style="margin:20px 0 0 5px;">学生番号</label>
                <input type="text" style="margin-left:20px; width:95%; border:none;" readonly value="${student.no }">
            </div>

            <div class="form-group-create">
                <label for="subjectName" style="margin:20px 0 0 5px;">氏名</label>
                <input type="text" class="form-control" name="name" style="width:95%;"required maxlength="30" placeholder="氏名を入力してください">
            </div>

            <div class="form-group-create">
                <label for="subjectName" style="margin:20px 0 0 5px;">クラス</label>
                <select name="class_num" class="form-control"  style=" width:95%;" >
                <c:forEach var="num" items="${ classnum }">
                <option value="${ num }">${ num }</option>
                </c:forEach>
                </select>
            </div>
			<div><label>在学中</label><input type="checkbox"  name="si_attend"  style="margin-right:10px;" value="true"></div>
            <div style="width:100%;">
                <button type="submit" class="btn btn-primary" style="width:15% padding: 0; margin: 0; text-align: center; display: inline-block; line-height: normal;">変更</button>
            </div>
            <div style="margin:10px 0 0 0px;">
                <a href="StudentList.action">戻る</a>
            </div>
            <input type="hidden" name="no" value="${ student.no }">

        </form>
    </div>
</main>

<%@ include file="../../footer.jsp" %>

<script src="../javascript/login.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
