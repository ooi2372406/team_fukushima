<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生管理システム</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        .filter-container {
            display: flex;
            align-items: center;
            gap: 10px;
            margin-bottom: 10px;
        }
        .filter-container label {
            margin-right: 5px;
        }
        .filter-container select, .filter-container input[type="checkbox"], .filter-container button {
            margin-right: 10px;
        }
        .error-message {
            color: red;
        }
    </style>
</head>
<body>
    <h1>学生管理システム</h1>
    <nav>
        <ul>
            <li><a href="index.jsp">ホーム</a></li>
            <li><a href="students.jsp">学生管理</a></li>
            <!-- 必要に応じて他のナビゲーション項目を追加 -->
        </ul>
    </nav>

    <div class="filter-container">
        <form method="get" action="students.jsp">
            <label for="year">入学年度:</label>
            <select id="year" name="year">
                <option value="2021">2021</option>
                <option value="2022">2022</option>
                <option value="2023">2023</option>
                <!-- 必要に応じて他の年度を追加 -->
            </select>

            <label for="studentClass">クラス:</label>
            <select id="studentClass" name="studentClass">
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
                <!-- 必要に応じて他のクラスを追加 -->
            </select>

            <label for="enrolled">在学中:</label>
            <input type="checkbox" id="enrolled" name="enrolled">

            <button type="submit">絞込み</button>
        </form>
    </div>

    <div>
        <c:choose>
            <c:when test="${not empty param.year and not empty param.studentClass}">
                <sql:query var="students" dataSource="${dataSource}">
                    SELECT * FROM students
                    WHERE year = ?
                    AND class = ?
                    <c:if test="${param.enrolled == 'on'}">
                        AND enrolled = true
                    </c:if>
                </sql:query>

                <c:if test="${empty students.rows}">
                    <p class="error-message">学生情報が存在しませんでした</p>
                </c:if>

                <table border="1">
                    <thead>
                        <tr>
                            <th>学籍番号</th>
                            <th>学生名</th>
                            <th>性別</th>
                            <th>生年月日</th>
                            <th>学部</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${students.rows}">
                            <tr>
                                <td><c:out value="${student.id}"/></td>
                                <td><c:out value="${student.name}"/></td>
                                <td><c:out value="${student.gender}"/></td>
                                <td><c:out value="${student.birth_date}"/></td>
                                <td><c:out value="${student.department}"/></td>
                                <td>
                                    <button onclick="editStudent(${student.id})">編集</button>
                                    <button onclick="deleteStudent(${student.id})">削除</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>学生情報を検索してください。</p>
            </c:otherwise>
        </c:choose>
    </div>

    <script type="text/javascript">
        function editStudent(studentId) {
            // 編集処理を行うJavaScriptコード
            alert("編集: " + studentId);
        }

        function deleteStudent(studentId) {
            // 削除処理を行うJavaScriptコード
            alert("削除: " + studentId);
        }
    </script>
</body>
</html>
