<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../../head.jsp" %>
<%@ include file="../../header.jsp" %>
<main>
    <div class="kamokucontainer-create">
        <%@ include file="base.jsp" %>

        <form id="subjectForm" style="width:100%; margin-left:20px;" action="StudentList.action" method="post">
            <div class="form-group-create">
                <h2 style="width: 100%; text-align: left; background-color: gainsboro; padding: 10px 20px;  margin-bottom:20px;">学生管理</h2>
                <div style="text-align:right; padding-right:20px;"><a  href="StudentCreate.action">新規登録</a></div>
                <div style="border:1px solid whitesmoke; height:120px;">
                <div  style="display:flex;  display: -webkit-flex; align-items: center; height:90px; margin-top:10px;">


                	<table class="responsive-table-kamoku" style="width:60%;">

                	<tr>
                	<th><label>入学年度</label></th>
                	<th><label>クラス</label></th>

                	</tr>
                	<tr>
                	<td>
                	<select class="select-dropdown" style="width:80%; border-radius:5%;  padding-top:5px; padding-bottom:5px;" name="f1">

                		<c:choose>
                             <c:when test="${not empty setYear && not empty message}">

                                   <c:forEach var="seireki" items="${ent_year_set}">
                                        <c:choose>
                                             <c:when test="${seireki eq setYear}">
                                                  <option selected>${setYear}</option>
                                             </c:when>
                                             <c:otherwise>
                                                  <option>${seireki}</option>
                                             </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                             </c:when>

                             <c:when test="${ empty setYear}">

                                   <option value="">--------</option>
                                    <c:forEach var="seireki" items="${ent_year_set}">
                                          <option>${seireki}</option>
                                    </c:forEach>
                             </c:when>

                             <c:when test="${not empty setYear}">

                                    <c:forEach var="seireki" items="${ent_year_set}">
                                         <c:choose>
                                               <c:when test="${seireki eq setYear}">
                                                    <option selected>${setYear}</option>
                                               </c:when>
                                              <c:otherwise>
                                                     <option>${seireki}</option>
                                              </c:otherwise>
                                         </c:choose>
                                    </c:forEach>
                              </c:when>
                          </c:choose>
					  </select>


                	<td>
                	<select style="width:80%; border-radius:5%;  padding-top:5px; padding-bottom:5px;" name="f2" >
                	<c:choose>
                             <c:when test="${not empty setClassNum && not empty message}">

                                   <c:forEach var="seireki" items="${class_num_set}">
                                        <c:choose>
                                             <c:when test="${seireki eq setClassNum}">
                                                  <option selected>${setClassNum}</option>
                                             </c:when>
                                             <c:otherwise>
                                                  <option>${seireki}</option>
                                             </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                             </c:when>

                             <c:when test="${ empty setClassNum}">

                                   <option value="">--------</option>
                                    <c:forEach var="seireki" items="${class_num_set}">
                                          <option>${seireki}</option>
                                    </c:forEach>
                             </c:when>

                             <c:when test="${not empty setClassNum}">

                                    <c:forEach var="seireki" items="${class_num_set}">
                                         <c:choose>
                                               <c:when test="${seireki eq setClassNum}">
                                                    <option selected>${setClassNum}</option>
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
                	</tr>
                	</table>
                	<c:choose>
                		<c:when test="${not empty setYear and not attend}">

                			<div><input id="check" type="checkbox"  name="f3"  style="margin-right:10px;" value="true"><label for="check">在学中</label></div>
                			<div style="margin-left:15%;"><button class="btn btn-primary" type="submit" style="border : none; background-color : #666666;" value="検索">絞込み</button></div>
                		</c:when>
                		<c:when test="${empty setYear && not empty message and not attend}">

                			<div><input id="check" type="checkbox"  name="f3"  style="margin-right:10px;" value="true"><label for="check">在学中</label></div>
                			<div style="margin-left:15%;"><button class="btn btn-primary" type="submit" style="border : none; background-color : #666666;" value="検索">絞込み</button></div>
                		</c:when>
                		<c:otherwise>

                			<div><input id="check" type="checkbox"  name="f3"  style="margin-right:10px;" value="true" checked><label for="check">在学中</label></div>
                			<div style="margin-left:15%;"><button class="btn btn-primary" type="submit" style="border : none; background-color : #666666;" value="検索">絞込み</button></div>
                		</c:otherwise>
                	</c:choose>




					</div>
                </div>
                </form>

				<c:choose>
					<c:when test="${not empty message }">
						<div>${message}</div>
					</c:when>
					<c:when test="${empty message }">




				<div style="width:100%;">
				<c:choose>
						<c:when test="${not empty yearerrormessage && not empty setClassNum}">
							<table>
								<tr>
									<td style="color:gold;">${yearerrormessage}</td>
								</tr>
							</table>
						</c:when>
					</c:choose>
                <table style="width:100%;">
                	<tr>
                		<td><p>検索結果 : ${fn:length(studentList)}件</p></td>
                	</tr>
                	<tr style="border-bottom:1px solid #cecfca;">
                		<th>入学年度</th>
                		<th>学生番号</th>
                		<th>氏名</th>
                		<th>クラス</th>
                		<th>在学中</th>
                		<th></th>
                	</tr>

                	<c:forEach var="student" items="${studentList}">
                	<tr style="border-bottom:1px solid #cecfca;">
                		<td>${student.entYear }</td>
						<td>${student.no}</td>

                		<td>${student.name }</td>
                		<td>${student.classNum }</td>
                		<c:choose>
                		<c:when test="${student.isAttend == true}"><td>〇</td></c:when>
                		<c:when test="${student.isAttend == false}"><td>×</td></c:when>
                		</c:choose>

                		<td>
						<form action="StudentUpdate.action" method="post">
        				<button type="submit" class="btn btn-link" >変更</button>
        				<input type="hidden" name="f1" value="${student.no}">
						</form>
						</td>

						<td>
						<form action="StudentAnalysis.action" method="post">
						<input type="hidden" name="f2" value="${student.no}">
        				<button type="submit" class="btn btn-link" >詳細</button>

						</form>
						</td>

                		</tr>
                	</c:forEach>

                </table>
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
  <script>
    document.getElementById('subjectForm').addEventListener('submit', function(event) {
      const form = event.target;
      const checkboxes = form.querySelectorAll('input[type="checkbox"]');

      checkboxes.forEach(checkbox => {
        if (!checkbox.checked) {
          // Hidden input element to send `false` for unchecked checkboxes
          const hiddenInput = document.createElement('input');
          hiddenInput.type = 'hidden';
          hiddenInput.name = checkbox.name;
          hiddenInput.value = 'false';
          form.appendChild(hiddenInput);
        }
      });
    });
  </script>
</div>
</body>
</html>
