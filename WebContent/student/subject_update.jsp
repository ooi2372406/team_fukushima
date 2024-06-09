<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../head.jsp" %>
<%@ include file="../header.jsp" %>
<%  %>
<main>
    <div class="kamokucontainer-create">
        <%@ include file="base.jsp" %>

        <form id="subjectForm" style="width:100%; margin-left:20px;" action="SubjectUpdateExecute.action" method="post">
            <div class="form-group-create">
                <h2 style="width: 100%;text-align: left; background-color:gainsboro; padding:10px 20px 10px 20px;">科目情報登録</h2>
                <label for="subjectCode" style="margin:20px 0 0 5px;">科目コード</label>
                <div style="margin:20px 0 0 20px;">${subject.cd}</div>
                <c:if test="${ not empty message }">
                	<div id="serverError" style="color: gold;　margin-left:20px;">${message}</div>
                </c:if>
                <input type="hidden" name="cd" value="${subject.cd }">


            </div>
            <div class="form-group-create">
                <label for="subjectName" style="margin:20px 0 0 5px;">科目名</label>
                <input type="text" class="form-control" name="name" placeholder="${subject.name }" style="margin-left:20px; width:95%;" required>
            </div>

            <div style="width:100%;">
                <button type="submit" class="btn btn-primary" style="margin-left:20px; width:15%;">変更</button>
            </div>
            <div style="margin:10px 0 0 20px;">
                <a href="SubjectList.action">戻る</a>
            </div>
        </form>
    </div>
</main>

<%@ include file="../footer.jsp" %>

<script src="../javascript/login.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
