<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../head.jsp" %>
<%@ include file="../../header.jsp" %>
<main>
    <div class="kamokucontainer-create">
        <%@ include file="base.jsp" %>
		<% String errorMessage =(String)request.getAttribute("errorMessage"); %>
        <form id="subjectForm" style="width:100%; margin-left:20px;" action="TestList.action" method="post">
            <div class="form-group-create">
			<c:choose>
				<c:when test="${empty testList && empty studentList}">
				 	<h2 style="width: 100%; text-align: left; background-color: gainsboro; padding: 10px 20px;  margin-bottom:20px;">成績参照</h2>
				</c:when>
				<c:when test="${not empty testList }">
				 	<h2 style="width: 100%; text-align: left; background-color: gainsboro; padding: 10px 20px;  margin-bottom:20px;">成績一覧（科目）</h2>
				</c:when>
				<c:when test="${not empty studentList }">
				 	<h2 style="width: 100%; text-align: left; background-color: gainsboro; padding: 10px 20px;  margin-bottom:20px;">成績一覧（学生）</h2>
				</c:when>
			</c:choose>


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
                	<!-- 入学年度 -->
                	<select style="width:80%; border-radius:5%;  padding-top:5px; padding-bottom:5px;"class="form-control" name="f1">
                	<c:choose>
					     <c:when test="${empty testList }">
					    <option>--------</option>
					<c:forEach var="seireki" items="${student}">
					 <c:choose>
					           <c:when test="${seireki eq selectstudent}">
					                <option selected>${seireki}</option>
					            </c:when>
					            <c:otherwise>
					                <option>${seireki}</option>
					            </c:otherwise>
					        </c:choose>
					    </c:forEach>
					    </c:when>
					    <c:when test="${not empty testList }">
					    <c:forEach var="seireki" items="${student}">
					        <c:choose>
					            <c:when test="${seireki eq selectstudent}">
					                <option selected>${seireki}</option>
					            </c:when>
					            <c:otherwise>
					                <option>${seireki}</option>
					            </c:otherwise>
					        </c:choose>
					    </c:forEach>
					    </c:when>
					    </c:choose>
					</select>
                	</td>
                	<td>
                	<!-- クラス -->
					<select style="width: 80%; border-radius: 5%; padding-top: 5px; padding-bottom: 5px;"class="form-control"  name="f2">
					    <c:choose>
					     <c:when test="${empty testList }">
					    <option>--------</option>
					    <c:forEach var="classnum" items="${classnum}">
					        <c:choose>
					           <c:when test="${classnum eq selectclass}">
					                <option selected>${classnum}</option>
					            </c:when>
					            <c:otherwise>
					                <option>${classnum}</option>
					            </c:otherwise>
					        </c:choose>
					    </c:forEach>
					    </c:when>
					    <c:when test="${not empty testList }">
					    <c:forEach var="classnum" items="${classnum}">
					        <c:choose>
					            <c:when test="${classnum eq selectclass}">
					                <option selected>${classnum}</option>
					            </c:when>
					            <c:otherwise>
					                <option>${classnum}</option>
					            </c:otherwise>
					        </c:choose>
					    </c:forEach>
					    </c:when>
					    </c:choose>
					</select>
                	</td>
                	<td>
                	<!-- 科目 -->
					<select style="width: 70%; border-radius: 5%; padding-top: 5px; padding-bottom: 5px;"class="form-control"  name="f3">
					   <c:choose>
					   	<c:when test="${empty testList }">
					   	<option>--------</option>
					    <c:forEach var="i" items="${subject}">
					        <c:choose>
					            <c:when test="${i.name eq selectsubject}">
					                <option selected>${i.name}</option>
					            </c:when>
					            <c:otherwise>
					                <option>${i.name}</option>
					            </c:otherwise>
					        </c:choose>
					    </c:forEach>
					    </c:when>
					    <c:when test="${not empty testList }">
					    <c:forEach var="i" items="${subject}">
					        <c:choose>
					            <c:when test="${i.name eq selectsubject}">
					                <option selected>${i.name}</option>
					            </c:when>
					            <c:otherwise>
					                <option>${i.name}</option>
					            </c:otherwise>
					        </c:choose>
					    </c:forEach>
					    </c:when>
					    </c:choose>
					</select>

                	<input type="hidden" name="f" value="sj">
                	</td>
                	</tr>
                	</table>
                	<div><button style="border: none; background-color: #666666;"class="btn btn-primary" type="submit" value="検索">検索</button></div>
					</div>
					<% if (errorMessage != null){%>
					<div style="color:#FFD700"> ${ errorMessage } </div>
					<% } %>
                </div>
                </form>

                <form action="TestList.action" method="post">
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
                	<input style="width:100%;  border-radius:5%;  padding-top:5px; padding-bottom:5px;" type="text" name="f4" class="form-control"placeholder="学生番号を入力してください" required>
                	</c:when>
                	</c:choose>
                	<input type="hidden" name="f" value="st">
                	</td>
					<td></td>
					<td></td>
                	</tr>
                	</table>
                	<div><button style="border: none; background-color: #666666;margin-left:50px;" class="btn btn-primary" type="submit" value="検索">検索</button></div>
				</div>
                </div>

        </form>
        <c:choose>
        	<c:when test="${not empty testList}">
        	<table style="width:100%; margin-top:auto;">
        			<tr>
               					 <td><div style="display:block"><p class="mt-3">科目 : ${subjectname}</p></div></td>
                			</tr>

                			<tr style="border-bottom: 1px solid  #cecfca;">
                    			<th>入学年度</th>
                    			<th>クラス</th>
                    			<th>学生番号</th>
                    			<th>氏名</th>
                    			<th>1回目</th>
                    			<th>2回目</th>
                			</tr>


                     			<c:forEach var="test" items="${testList }">
                     			<tr style="border-bottom: 1px solid  #cecfca;">
                     				<td>${test.entYear }</td>
                        			<td>${test.classNum}</td>
                        			<td>${test.studentNo}</td>
                        			<td>${test.studentName}</td>
									<c:forEach var="i" items="${test.points}">
        							<td>${i.value != -1 ? i.value : '-'}</td>

        							</c:forEach>



                					<c:if test="${not empty message}">
                    				<div id="pointError" style="color: gold;">${message}</div>
                					</c:if>

                        			</tr>

                        		</c:forEach>
							</table>
        					</c:when>
        					<c:when test="${not empty studentList }">
        					<table style="width:100%; margin-top:auto;">
        						<tr>
               					 <td><div style="display:block"><p class="mt-3">氏名 : ${studentname.name}(${ studentname.no })</p></div></td>
                				</tr>

                				<tr style="border-bottom: 1px solid  #cecfca;">
                    				<th>科目名</th>
                    				<th>科目コード</th>
                    				<th>回数</th>
                    				<th>点数</th>
                				</tr>


                     			<c:forEach var="student" items="${studentList}">
                     			<tr style="border-bottom: 1px solid  #cecfca;">
                     				<td>${student.subjectName}</td>
                        			<td>${student.subjectCd}</td>
                        			<td>${student.num}</td>
                        			<td>${student.point}</td>

                					<c:if test="${not empty message}">
                    				<div id="pointError" style="color: gold;">${message}</div>
                					</c:if>

                        			</tr>

                        		</c:forEach>
							</table>
        					</c:when>
        					<c:when test="${empty testList && not empty errorMessege2}">
        					 	<div style="margin: 10px 0 0 20px;">
               						<label>${errorMessege2}</label>
            					</div>
        					</c:when>

        					<c:when test="${empty testList}">
        					 	<div style="margin: 10px 0 0 20px;">
               						<label><p style="color:#03fcf8">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</label>
            					</div>
        					</c:when>
        				</c:choose>

    </div>
</main>

<%@ include file="../../footer.jsp" %>

<script src="../javascript/subject.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>


</body>
</html>
