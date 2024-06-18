<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../head.jsp" %>
<%@ include file="../../header.jsp" %>

<main>
    <div class="kamokucontainer-create">
        <%@ include file="./base.jsp" %>

        <form id="subjectForm" style="width:100%; margin-left:20px;" action="StudentCreateExecute.action" method="post">
            <div class="form-group-create">
                <h2 style="width: 100%; text-align: left; background-color: gainsboro; padding: 10px 20px;">学生情報登録</h2>
                <label for="subjectCode" style="margin: 20px 0 0 5px;">入学年度</label>
                <select id="ent_year" class="form-control" name="ent_year" style="margin-left: 20px; width: 95%;" required>
					<option value="">--------</option>

            		<!-- 動的に選択肢を生成する場合は以下にJavaコードを記述 -->
           			 <c:forEach var="i" items="${year}">
              		  <option value="${i}">${i}</option>
              		  </c:forEach>

            	</select><br>
                <div id="lengthError" style="color: gold; display: none;"></div>
                    <c:if test="${not empty message}">
                   		 <div id="serverError" style="color: gold;">${message}</div>
               		</c:if>
            </div>

                <label for="subjectName" style="margin: 20px 0 0 5px;">学生番号</label>
                <input type="text" class="form-control" name="no" value="${no}" placeholder="学生番号を入力してください" style="margin-left: 20px; width: 95%;" required>

                <label for="subjectName" style="margin: 20px 0 0 5px;">氏名</label>
                <input type="text" class="form-control" name="name" value="${name}" placeholder="氏名を入力してください" style="margin-left: 20px; width: 95%;" required>

        	<label for="class_num"style="margin: 20px 0 0 5px;">クラス</label>
        	<select id="class_num" name="class_num">
        	<option value="">--------</option>
        	<c:forEach var="i" items="${classList}">
        		<option value="${i}">${i}</option>
        	</c:forEach>
        	</select>


            <input type="hidden" name="schoolcd" value="${user.school}">
            <div style="width: 100%;"></div>
                <button type="submit" class="btn btn-primary" style="margin-left: 20px; width: 15%;">登録</button>
            <div style="margin: 10px 0 0 20px;">
                <a href="StudentList.action">戻る</a>
            </div>
        </form>
    </div>
</main>

<%@ include file="../../footer.jsp" %>

<script src="../javascript/subject.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>


</body>
</html>
