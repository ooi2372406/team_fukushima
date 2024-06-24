<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../../head.jsp" %>

<%@ include file="../../header.jsp" %>
<main>
    <div class="kamokucontainer-create">
        <%@ include file="base.jsp" %>

        <form id="subjectForm" style="width:100%; margin-left:20px;" action="${not empty testList ? 'TestRegistExecute.action' : 'TestRegist.action'}" method="post">
            <div class="form-group-create">

                <h2 style="width: 100%; text-align: left; background-color: gainsboro; padding: 10px 20px;  margin-bottom:20px;">学生管理</h2>

                <div style="border:1px solid #cecfca; height:120px;">
                <div  style="display:flex;  display: -webkit-flex; align-items: center; height:90px; margin-top:10px;">


                	<table class="responsive-table-seiseki" style="width:100%; margin-top:auto;">
                	<tr>
                	<th><label>入学年度</label></th>
                	<th><label>クラス</label></th>
                	<th><label>科目</label></th>
                	<th><label>回数</label>
                	<td rowspan="2" style="text-align:center;"><button class="btn btn-primary" type="submit" value="検索" style="padding:10px 25px;background-color: #666666">検索</button></td>


                	</tr>
                	<tr>
                	<td>
                	<select style="width:80%; border-radius:5%;  padding-top:5px; padding-bottom:5px; padding-left:5px;" name="f1">
                	<option selected>--------</option>
					<c:forEach var="i" items="${student}">
						<option>${i.entYear}</option>
					</c:forEach>
                	</select>
                	</td>


                	<td>
                	<select style="width:80%; border-radius:5%;  padding-top:5px; padding-bottom:5px; padding-left:5px;" name="f2" >
                	<option selected>--------</option>
					<c:forEach var="i" items="${student}">
						<option>${i.classNum}</option>
					</c:forEach>
                	</select>
                	</td>
                	<td>
                	<select style="width:80%; border-radius:5%;  padding-top:5px; padding-bottom:5px; padding-left:5px;" name="f3">
                	<option selected>--------</option>
					<c:forEach var="i" items="${subject}">
						<option>${i.name}</option>
					</c:forEach>
                	</select>
                	</td>

                	<td>
                	<select style="width:80%; border-radius:5%;  padding-top:5px; padding-bottom:5px; padding-left:5px;" name="f4">
                	<option selected>--------</option>
					<c:forEach var="i" begin="1" end="3">
						<option>${i}</option>
					</c:forEach>


                	</select>
                	</td>


                	</tr>
                	<c:choose>
                		<c:when test="${not empty testList}">

                			<tr>
               					 <td><div style="display:block"><p class="mt-3">科目:　${testList.get(0).subject.name } (${testList.get(0).no}回目)</p></div></td>
                			</tr>

                			<tr style="border-bottom: 1px solid  #cecfca;">
                    			<th>入学年度</th>
                    			<th>クラス</th>
                    			<th>学生番号</th>
                    			<th>氏名</th>
                    			<th>点数</th>
                			</tr>


                     			<c:forEach var="test" items="${testList }">
                     			<tr style="border-bottom: 1px solid  #cecfca;">
                     				<td>${test.student.entYear }</td>
                        			<td>${test.classNum}</td>
                        			<td>${test.student.no}</td>
                        			<td>${test.student.name}</td>


                        			<td><input type="text" name="point_${test.student.no}" value="${test.point}"></td>
                					<c:if test="${not empty message}">
                    				<div id="pointError" style="color: gold;">${message}</div>
                					</c:if>

                        			</tr>
                        			<input type="hidden" name="gakuban" value="${test.student.no }">
                        			<input type="hidden" name="kamokucd" value="${test.subject.cd }">
                        			<input type="hidden" name="num" value="${test.no }">
                        		</c:forEach>

							<tr>
							<td><button type="submit" value="登録して終了" style="padding:10px 35px; margin-top:20px;">登録して終了</button>

		                </c:when>
        	        </c:choose>

				</table>
				 </div>
                </div>
                </div>
                </form>




    </div>






</main>

<%@ include file="../../footer.jsp" %>
<script>
document.getElementById('subjectForm').addEventListener('submit', function(event) {
    let pointInputs = document.querySelectorAll('.pointInput');
    let isValid = true;

    pointInputs.forEach(function(input) {
        let pointValue = input.value;
        let studentNo = input.getAttribute('name').split('_')[1];
        let pointError = document.getElementById(`pointError_${studentNo}`);

        // エラーメッセージの初期化
        if (pointError) {
            pointError.style.display = 'none';
            pointError.textContent = '';
        }

        let isPointNumeric = /^[0-9]+$/.test(pointValue);
        let isPointValid = isPointNumeric && pointValue >= 1 && pointValue <= 100;

        if (!isPointValid) {
            if (!pointError) {
                pointError = document.createElement('div');
                pointError.id = `pointError_${studentNo}`;
                pointError.style.color = 'gold';
                input.parentNode.appendChild(pointError);
            }
            pointError.textContent = 'ポイントは1から100の範囲で入力してください。';
            pointError.style.display = 'block';
            isValid = false;
        }
    });

    if (!isValid) {
        event.preventDefault();
    }
});
</script>

<script src="../javascript/testregist.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>


</body>
</html>
