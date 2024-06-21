<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../head.jsp" %>
<%@ include file="../../header.jsp" %>
<main>
    <div class="kamokucontainer-create">
        <%@ include file="base.jsp" %>
		<% String errorMessage =(String)request.getAttribute("errorMessage"); %>
        <form id="subjectForm" style="width:100%; margin-left:20px;" action="TestListSubjectExecute.action" method="post">
            <div class="form-group-create">
                <h2 style="width: 100%; text-align: left; background-color: gainsboro; padding: 10px 20px;  margin-bottom:20px;">成績参照</h2>
                <div style="border:1px solid whitesmoke; height:120px;">
                <div  style="display:flex;  display: -webkit-flex; align-items: center; height:90px; margin-top:10px;">

                	<p style="width:20%; padding-left:60px;">科目情報</p>
                	<table class="responsive-table-kamoku" style="width:60%;">
                	<tr>
                	<th>入学年度</th>
                	<th>クラス</th>
                	<th>科目</th>
                	</tr>
                	<tr>
                	<td>
                	<select style="width:80%; border-radius:5%;  padding-top:5px; padding-bottom:5px;" name="f1">
                	<option value="----">--------</option>
                			<option value="2023">2024年度</option>
                            <option value="2023">2023年度</option>
                            <option value="2022">2022年度</option>
                            <option value="2021">2021年度</option>
                            <option value="2020">2020年度</option>
                            <option value="2019">2019年度</option>
                            <option value="2018">2018年度</option>
                            <option value="2017">2017年度</option>
                            <option value="2016">2016年度</option>
                            <option value="2015">2015年度</option>
                            <option value="2014">2014年度</option>
                	</select>
                	</td>
                	<td>
                	<select style="width:80%; border-radius:5%;  padding-top:5px; padding-bottom:5px;" name="f2" >

					<option value="---">--------</option>
                            <option value="131">131</option>
                            <option value="132">132</option>
                            <option value="133">133</option>
                	</select>
                	</td>
                	<td>
                	<select style="width:70%; border-radius:5%;  padding-top:5px; padding-bottom:5px;" name="f3">

					<option value="----">--------</option>
                            <option value="国語">国語</option>
                            <option value="数学">数学</option>
                            <option value="英語">英語</option>
                	</select>
                	<input type="hidden" name="f" value="sj">
                	</td>
                	</tr>
                	</table>
                	<div><button class="btn btn-primary" type="submit" value="検索">検索</button></div>
					</div>
                </div>
                </form>
                <form action="TestListStudentExecute.action" method="post">
                <div style="border:1px solid whitesmoke; height:120px;">
                <div style="display:flex;  display: -webkit-flex; align-items: center;">

                	<p style="width:20%; padding-left:60px;">学生情報</p>
                	<table style="width:30%;"  class="responsive-table-gakusei">
                	<tr>
                	<th>学生番号</th>
					<th></th>
					<th></th>
                	</tr>
                	<tr>
                	<td>
                	<c:choose>
                	<c:when test="${not empty f4 }">
                	<input style="width:100%  border-radius:5%;  padding-top:5px; padding-bottom:5px; " type="text" name="f4" value="${ f4 }">
                	</c:when>
                	<c:when test="${empty f4 }">
                	<input style="width:100%;  border-radius:5%;  padding-top:5px; padding-bottom:5px;" type="text" name="f4" placeholder="学生番号を入力してください">
                	</c:when>
                	</c:choose>
                	<input type="hidden" name="f" value="st">
                	</td>
					<td></td>
					<td></td>
                	</tr>
                	</table>
                	<div><button style="margin-left:50px;" class="btn btn-primary" type="submit" value="検索">検索</button></div>
				</div>
                </div>
            <div style="margin: 10px 0 0 20px;">
               <label><p style="color:#03fcf8">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</label>
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
