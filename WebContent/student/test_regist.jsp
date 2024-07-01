<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 共通のヘッダーとヘッドファイルをインクルード -->
<%@ include file="../../head.jsp" %>
<%@ include file="../../header.jsp" %>
<main>
    <div class="kamokucontainer-create">
        <!-- ベースファイルをインクルード -->
        <%@ include file="base.jsp" %>

        <!-- 成績管理用のフォーム -->
        <form id="subjectForm" style="width:100%; margin-left:20px;" action="${not empty testList ? 'TestRegistExecute.action' : 'TestRegist.action'}" method="post">
            <div class="form-group-create">

                <!-- 見出し -->
                <h2 style="width: 100%; text-align: left; background-color: gainsboro; padding: 10px 20px;  margin-bottom:20px;">成績管理</h2>

                <div style="border:1px solid #cecfca; height:120px;">
                    <div style="display:flex; display: -webkit-flex; align-items: center; height:90px; margin-top:10px;">

                        <!-- 条件選択用のテーブル -->
                        <table class="responsive-table-seiseki" style="width:100%; margin-top:auto;">
                            <tr>
                                <th><label>入学年度</label></th>
                                <th><label>クラス</label></th>
                                <th><label>科目</label></th>
                                <th><label>回数</label></th>
                                <td rowspan="2" style="text-align:center;"><button class="btn btn-primary" type="submit" value="検索" style="padding:10px 25px;">検索</button></td>
                            </tr>
                            <tr>
                                <!-- 入学年度の選択 -->
                                <td>
                                    <select style="width:80%; border-radius:5%; padding-top:5px; padding-bottom:5px;" name="f1">
                                        <c:choose>
					     					<c:when test="${empty testList }">
					    						<option>--------</option>
												<c:forEach var="seireki" items="${studentYear}">
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
					    					<c:forEach var="seireki" items="${studentYear}">
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

                                <!-- クラスの選択 -->
                                <td>
                                    <select style="width:80%; border-radius:5%; padding-top:5px; padding-bottom:5px;" name="f2">
                                       <c:choose>
					     					<c:when test="${empty testList }">
					    						<option>--------</option>
												<c:forEach var="seireki" items="${studentclassnum}">
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
					    					<c:forEach var="seireki" items="${studentclassnum}">
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

                                <!-- 科目の選択 -->
                                <td>
                                    <select style="width:80%; border-radius:5%; padding-top:5px; padding-bottom:5px;" name="f3">
                                        <option value="--------">--------</option>
                                        <c:forEach var="i" items="${subject}">
                                            <option value="${i.name}" <c:if test="${param.f3 == i.name}">selected</c:if>>${i.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>

                                <!-- 回数の選択 -->
                                <td>
                                    <select style="width:80%; border-radius:5%; padding-top:5px; padding-bottom:5px;" name="f4">
                                        <option value="--------">--------</option>
                                        <c:forEach var="i" begin="1" end="2">
                                            <option value="${i}" <c:if test="${param.f4 == i}">selected</c:if>>${i}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>

                            <!-- 検索結果の表示 -->
                            <c:choose>
                                <c:when test="${not empty testList}">
                                    <tr>
                                        <td colspan="5"><div style="display:block"><p class="mt-3">科目:　${testList.get(0).subject.name } (${testList.get(0).no}回目)</p></div></td>
                                    </tr>
                                    <tr style="border-bottom: 1px solid #cecfca;">
                                        <th>入学年度</th>
                                        <th>クラス</th>
                                        <th>学生番号</th>
                                        <th>氏名</th>
                                        <th>点数</th>
                                    </tr>

                                    <!-- テストの結果をループで表示 -->
                                    <c:forEach var="test" items="${testList}">
                                        <tr style="border-bottom: 1px solid #cecfca;">
                                            <td>${test.student.entYear}</td>
                                            <td>${test.classNum}</td>
                                            <td>${test.student.no}</td>
                                            <td>${test.student.name}</td>
                                            <!-- 点数入力欄に class="pointInput" を追加 -->
                                            <td><input type="text" class="pointInput" name="point_${test.student.no}" value="${test.point}"></td>
                                            <c:if test="${not empty message}">
                                                <div id="pointError" style="color: gold;">${message}</div>
                                            </c:if>
                                        </tr>
                                        <!-- 隠しフィールド -->
                                        <input type="hidden" name="gakuban" value="${test.student.no}">
                                        <input type="hidden" name="kamokucd" value="${test.subject.cd}">
                                        <input type="hidden" name="num" value="${test.no}">
                                    </c:forEach>

                                    <!-- 登録ボタン -->
                                    <tr>
                                        <td colspan="5" style="text-align:center;"><button type="submit" value="登録して終了" style="padding:10px 35px; border-radius:15px; margin-top:20px;">登録して終了</button></td>
                                    </tr>
                                </c:when>
                            </c:choose>
                        </table>
                    </div>
                </div>
            </div>
        </form>
    </div>
</main>

<!-- フッターファイルをインクルード -->
<%@ include file="../../footer.jsp" %>

<!-- 点数のバリデーション用JavaScript -->
<script>
document.getElementById('subjectForm').addEventListener('submit', function(event) {
    // 点数入力欄を取得
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

        // 数値と範囲のチェック
        let isPointNumeric = /^[0-9]+$/.test(pointValue);
        let isPointValid = isPointNumeric && pointValue >= 0 && pointValue <= 100;

        // 条件を満たさない場合、エラーメッセージを表示
        if (!isPointValid) {
            if (!pointError) {
                pointError = document.createElement('div');
                pointError.id = `pointError_${studentNo}`;
                pointError.style.color = 'gold';
                input.parentNode.appendChild(pointError);
            }
            pointError.textContent = '0～100の範囲で入力してください';
            pointError.style.display = 'block';
            isValid = false;
        }
    });

    // フォームの送信を停止
    if (!isValid) {
        event.preventDefault();
    }
});
</script>

<!-- 他のJavaScriptファイルの読み込み -->
<script src="../javascript/testregist.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft
