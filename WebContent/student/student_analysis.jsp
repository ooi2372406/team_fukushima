<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../../head.jsp" %>
<%@ include file="../../header.jsp" %>
<main>
    <div class="kamokucontainer-create">
        <%@ include file="base.jsp" %>

		<div style="width:50%; position:relative; margin-left:30px;">

        <table border="1" style="width:80%; text-align:center;">
        	<tr>
        		<th colspan="6"><c:forEach var="i" items="${students}"><p>${i.name}さん</p></c:forEach></th>
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
            			<c:when test="${i.value > 89 }">
            			<th>優</th>
            			</c:when>
            			<c:when test="${i.value > 79 }">
            			<th>秀</th>
            			</c:when>
            			<c:when test="${i.value > 69 }">
            			<th>良</th>
            			</c:when>
            			<c:when test="${i.value > 59 }">
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
            	<th>${i.value }</th>
            </c:forEach>
            </tr>
            <tr>
            <th>二回目順位</th>
          	<c:forEach var="i" items="${subjectRanks2}">
            	<th>${i.value }</th>
            </c:forEach>
            </tr>
            <tr>
            <th colspan="6" style="height:150px;"><p>教師コメント（仮）</p></th>
            </tr>
        </table>


        </div>
        <div style="position:absolute;right:-250px; width:60%;height:750px;">
		<canvas id="myRadarChart" style="width:100%;"></canvas>
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
</script>
</body>
</html>
