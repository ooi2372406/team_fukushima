<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../../head.jsp" %>
<%@ include file="../../header.jsp" %>
<main>
    <div class="kamokucontainer-create">
        <%@ include file="base.jsp" %>

        <div style="width:50%; position:relative; margin-left:30px;">
            <button onclick="window.print(); return false;">このページを印刷する</button>
            <button onclick="addCommentRow()">コメントを追加</button>
			<form id="commentForm" action="StudentAnalysisExecute.action"  method="post">
			<input type="hidden" name="f2" value="${ studentno }">
            <table id="commentTable" class="printableArea" border="1" style="width:80%; text-align:center;">
                <tr>
                    <th colspan="6">
                        <c:forEach var="i" items="${students}">
                            <p>${i.name}さん</p>
                        </c:forEach>
                    </th>
                </tr>
                <tr>
                    <th>科目</th>
                    <c:forEach var="student" items="${students}">
                        <c:forEach var="point" items="${student.points}">
                            <th>${point.key}</th>
                        </c:forEach>
                    </c:forEach>
                </tr>
                <tr>
                    <th>平均点数</th>
                    <c:forEach var="student" items="${students}">
                        <c:forEach var="point" items="${student.points}">
                            <th>${point.value}</th>
                        </c:forEach>
                    </c:forEach>
                </tr>
                <tr>
                    <th>学年平均</th>
                    <c:forEach var="subject" items="${subject}">
                        <c:forEach var="point" items="${subject.points}">
                            <th>${point.value}</th>
                        </c:forEach>
                    </c:forEach>
                </tr>
                <tr>
                    <th>判定</th>
                    <c:forEach var="student" items="${students}">
                        <c:forEach var="i" items="${student.points}">
                            <c:choose>
                                <c:when test="${i.value > 89}">
                                    <th>優</th>
                                </c:when>
                                <c:when test="${i.value > 79}">
                                    <th>秀</th>
                                </c:when>
                                <c:when test="${i.value > 69}">
                                    <th>良</th>
                                </c:when>
                                <c:when test="${i.value > 59}">
                                    <th>可</th>
                                </c:when>
                                <c:when test="${i.value <= 59}">
                                    <th style="color:red;">不可</th>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </c:forEach>
                </tr>
                <tr>
                    <th>一回目順位</th>
                    <c:forEach var="i" items="${subjectRanks1}">
                        <th>${i.value}</th>
                    </c:forEach>
                </tr>
                <tr>
                    <th>二回目順位</th>
                    <c:forEach var="i" items="${subjectRanks2}">
                        <th>${i.value}</th>
                    </c:forEach>
                </tr>
                <!-- コメントの部分を追加 -->
				<c:forEach var="i" items="${comment}">
    				<tr id="commentRow-${i.index}">
        				<td class="commenttd" colspan="6">
            				<p style="text-align:left;">${i.date}</p>
            				<p>${i.comment}</p>
            				<form action="StudentAnalysisDelete.action" method="post">
            				<button class="delete-btn" onclick="deleteCommentRow(${i.index})">削除</button>
            				</form>
        				</td>
    				</tr>
				</c:forEach>

            </table>
            </form>
        </div>
        <div style="position:absolute; right:-250px; width:60%;">
            <canvas id="myRadarChart" class="printableArea2" style="width:100%;"></canvas>
        </div>
    </div>
</main>

<%@ include file="../../footer.jsp" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
<script>
var studentNames = [];
<c:forEach var="student" items="${students}">
    studentNames.push('${student.name}');
</c:forEach>;

var studentKeys = [];
<c:forEach var="student" items="${students}">
    var keys = [];
    <c:forEach var="point" items="${student.points}">
        keys.push('${point.key}');
    </c:forEach>;
    studentKeys.push(keys);
</c:forEach>;

var studentPoints = [];
<c:forEach var="student" items="${students}">
    var points = [];
    <c:forEach var="point" items="${student.points}">
        points.push(${point.value});
    </c:forEach>;
    studentPoints.push(points);
</c:forEach>;

var subjectKeys = [];
<c:forEach var="subject" items="${subject}">
    var keys = [];
    <c:forEach var="point" items="${subject.points}">
        keys.push('${point.key}');
    </c:forEach>;
    subjectKeys.push(keys);
</c:forEach>;

var subjectPoints = [];
<c:forEach var="subject" items="${subject}">
    var points = [];
    <c:forEach var="point" items="${subject.points}">
        points.push(${point.value});
    </c:forEach>;
    subjectPoints.push(points);
</c:forEach>;

console.log(studentNames);
console.log(studentKeys);
console.log(studentPoints);
console.log(subjectKeys);
console.log(subjectPoints);

var ctx = document.getElementById("myRadarChart");
var myRadarChart = new Chart(ctx, {
    type: 'radar',
    data: {
        labels: studentKeys[0],  // すべての生徒が同じキーを持っていると仮定
        datasets: [{
            label: studentNames[0] + 'さん',
            data: studentPoints[0],  // 最初の生徒の点数を表示すると仮定
            backgroundColor: 'RGBA(225,95,150, 0.5)',
            borderColor: 'RGBA(225,95,150, 1)',
            borderWidth: 1,
            pointBackgroundColor: 'RGB(46,106,177)'
        }, {
            label: '学年平均',
            data: subjectPoints[0], // 学年平均の点数が一つあると仮定
            backgroundColor: 'RGBA(115,255,25, 0.5)',
            borderColor: 'RGBA(115,255,25, 1)',
            borderWidth: 1,
            pointBackgroundColor: 'RGB(46,106,177)'
        }]
    },
    options: {
        title: {
            display: true,
            text: '試験成績'
        },
        scale: {
            ticks: {
                suggestedMin: 0,
                suggestedMax: 100,
                stepSize: 10,
                callback: function(value, index, values) {
                    return value + '点';
                }
            }
        }
    }
});

//印刷時の設定
function beforePrint() {
    // グラフの再描画など、印刷に関連する事前の調整を行う
    var canvas = document.getElementById('myRadarChart');
    if (canvas) {
        var ctx = canvas.getContext('2d');
        // ここにグラフの再描画やサイズ調整のコードを追加
        // 例えば、以下のようにグラフのサイズを調整
        canvas.style.width = '100%';
        canvas.style.height = 'auto';
    }
}

// 印刷後の設定
function afterPrint() {
    // 必要に応じて、元の設定に戻す処理など
    var canvas = document.getElementById('myRadarChart');
    if (canvas) {
        // ここに元のサイズに戻すコードを追加
        canvas.style.width = '';
        canvas.style.height = '';
    }
}

// イベントリスナーの追加
if (window.matchMedia) {
    var mediaQueryList = window.matchMedia('print');
    mediaQueryList.addListener(function(mql) {
        if (mql.matches) {
            beforePrint();
        } else {
            afterPrint();
        }
    });
}

// windowのbeforeprintとafterprintイベントにも対応
window.onbeforeprint = beforePrint;
window.onafterprint = afterPrint;

var rowCounter = ${comment.size()};

function addCommentRow() {
    rowCounter++;

    // 新しい<tr>要素を作成
    var newRow = document.createElement("tr");
    newRow.id = "commentRow-" + rowCounter;

    // 新しい<td>要素を作成し、その中に<input>要素を追加
    var newCell = document.createElement("td");
    newCell.setAttribute("colspan", "6"); // colspan="6" を設定
    newCell.classList.add("commenttd"); // クラスを追加

    var input = document.createElement("textarea");
    input.rows = 4; // テキストエリアの高さを設定（適宜調整）
    input.style.width = "100%"; // <textarea>要素の幅を100%に設定
    input.name = "f3";

    var button = document.createElement("button");
    button.textContent = "保存"; // ボタンのテキストを設定
    button.type = "submit"; // ボタンのタイプを設定

    var deleteButton = document.createElement("button");
    deleteButton.textContent = "削除";
    deleteButton.classList.add("delete-btn");
    deleteButton.onclick = function() {
        deleteCommentRow(rowCounter);
    };

    newCell.appendChild(input);
    newCell.appendChild(button);
    newCell.appendChild(deleteButton);

    // 新しいセルを行に追加
    newRow.appendChild(newCell);

    // テーブルに新しい行を追加
    var table = document.getElementById("commentTable");
    table.appendChild(newRow);
}

function deleteCommentRow(rowId) {
    var row = document.getElementById("commentRow-" + rowId);
    row.parentNode.removeChild(row);
}

function saveComment(comment) {
    // フォームにコメントを設定
    document.getElementById("commentForm").setAttribute("action", "saveCommentAction?comment=" + encodeURIComponent(comment));
    // フォームを送信
    document.getElementById("commentForm").submit();
}


function saveComment(comment) {
    // フォームにコメントを設定
    document.getElementById("commentForm").setAttribute("action", "saveCommentAction?comment=" + encodeURIComponent(comment));
    // フォームを送信
    document.getElementById("commentForm").submit();
}

</script>
</body>
</html>
