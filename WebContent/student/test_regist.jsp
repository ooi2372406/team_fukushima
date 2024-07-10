<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 共通のヘッダーとヘッドファイルをインクルード -->
<%@ include file="../../head.jsp" %>
<%@ include file="../../header.jsp" %>
<main style="height:100vh;">
    <div class="kamokucontainer-create">
        <!-- ベースファイルをインクルード -->
        <%@ include file="base.jsp" %>
        <div id="subjectForm" class="form-container">
            <!-- 成績管理用のフォーム -->
            <form action="TestRegist.action" method="post">
                <div class="form-group-create">
                    <!-- 見出し -->
                    <h2>成績管理</h2>
                    <!-- 条件選択用のテーブル -->
                    <table class="responsive-table-seiseki">
                        <tr>
                            <th><label>入学年度</label></th>
                            <th><label>クラス</label></th>
                            <th><label>科目</label></th>
                            <th><label>回数</label></th>
                            <td rowspan="2"><button class="btn btn-primary" type="submit" value="検索" style="padding:10px 25px; color:white; background-color:#666666;">検索</button></td>
                        </tr>
                        <tr>
                            <!-- 入学年度の選択 -->
                            <td>
                                <select name="f1" style="width:80%; border-radius:5%; padding-top:5px; padding-bottom:5px;">
                                    <c:choose>
                                        <c:when test="${empty testList && not empty yearerrormessage && not empty setYear}">
                                            <h1>1</h1>
                                            <c:forEach var="seireki" items="${studentYear}">
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
                                        <c:when test="${empty testList && not empty emptymessage}">
                                            <c:forEach var="seireki" items="${studentYear}">
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
                                        <c:when test="${empty testList && not empty yearerrormessage && empty setYear}">
                                            <h1>2</h1>
                                            <option value="">--------</option>
                                            <c:forEach var="seireki" items="${studentYear}">
                                                <option>${seireki}</option>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${empty testList}">
                                            <h1>3</h1>
                                            <option value="">--------</option>
                                            <c:forEach var="seireki" items="${studentYear}">
                                                <option>${seireki}</option>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${not empty testList}">
                                            <c:forEach var="seireki" items="${studentYear}">
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
                            </td>
                            <!-- クラスの選択 -->
                            <td>
                                <select name="f2" style="width:80%; border-radius:5%; padding-top:5px; padding-bottom:5px;">
                                    <c:choose>
                                        <c:when test="${empty testList && not empty yearerrormessage && not empty setClassNum}">
                                            <c:forEach var="seireki" items="${studentclassnum}">
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
                                        <c:when test="${empty testList && not empty emptymessage}">
                                            <c:forEach var="seireki" items="${studentclassnum}">
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
                                        <c:when test="${empty testList && not empty yearerrormessage && empty setClassNum}">
                                            <h1>2</h1>
                                            <option value="">--------</option>
                                            <c:forEach var="seireki" items="${studentclassnum}">
                                                <option>${seireki}</option>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${empty testList}">
                                            <option value="">--------</option>
                                            <c:forEach var="seireki" items="${studentclassnum}">
                                                <option>${seireki}</option>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${not empty testList}">
                                            <c:forEach var="seireki" items="${studentclassnum}">
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
                            <!-- 科目の選択 -->
                            <td>
                                <select name="f3" style="width:80%; border-radius:5%; padding-top:5px; padding-bottom:5px;">
                                    <option value="">--------</option>
                                    <c:forEach var="i" items="${subject}">
                                        <option value="${i.name}" <c:if test="${param.f3 == i.name}">selected</c:if>>${i.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <!-- 回数の選択 -->
                            <td>
                                <select name="f4" style="width:80%; border-radius:5%; padding-top:5px; padding-bottom:5px;">
                                    <option value="">--------</option>
                                    <c:forEach var="i" begin="1" end="2">
                                        <option value="${i}" <c:if test="${param.f4 == i}">selected</c:if>>${i}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
            <!-- 検索結果の表示 -->
            <c:choose>
                <c:when test="${not empty yearerrormessage}">
                    <p style="color:gold">${yearerrormessage}</p>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${empty testList && not empty emptymessage}">
                    <p>${emptymessage}</p>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${not empty testList}">
                    <form action="TestRegistExecute.action" method="post">
                        <div style="width:100%;">
                            <table class="right-half">
                                <tr>
                                    <td colspan="5"><div style="display: block;"><p class="mt-3">科目: ${testList.get(0).subject.name} (${testList.get(0).no}回目)</p></div></td>
                                </tr>
                                <tr>
                                    <th>入学年度</th>
                                    <th>クラス</th>
                                    <th>学生番号</th>
                                    <th>氏名</th>
                                    <th>点数</th>
                                </tr>
                                <!-- テストの結果をループで表示 -->
                                <c:forEach var="test" items="${testList}">
                                    <tr style="border-bottom: 1px solid #cecfca">
                                        <td>${test.student.entYear}</td>
                                        <td>${test.classNum}</td>
                                        <td>${test.student.no}</td>
                                        <td>${test.student.name}</td>
                                        <!-- 点数入力欄に class="pointInput" を追加 -->
                                        <td><input type="text" class="pointInput" name="point_${test.student.no}" value="${test.point}"></td>
                                        <c:if test="${not empty message}">
                                            <div id="pointError_${test.student.no}" style="color: gold;">${message}</div>
                                        </c:if>
                                    </tr>
                                    <!-- 隠しフィールド -->
                                    <input type="hidden" name="gakuban" value="${test.student.no}">
                                    <input type="hidden" name="kamokucd" value="${test.subject.cd}">
                                    <input type="hidden" name="num" value="${test.no}">
                                </c:forEach>
                                <!-- 登録ボタン -->
                                <tr>
                                    <td colspan="5"><button type="submit" value="登録して終了" style="padding: 10px 35px; border-radius: 5px; margin-top: 20px; color:white; background-color:#666666;">登録して終了</button></td>
                                </tr>
                            </table>
                        </div>
                    </form>
                </c:when>
            </c:choose>
        </div>
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
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft"></script>
