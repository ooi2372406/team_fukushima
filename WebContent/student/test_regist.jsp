<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ include file="../header.jsp" %>

<main>
    <div class="seisekicontainer">

        <%@ include file="base.jsp" %>

        <div class="container">
            <!-- 見出し -->
            <h2 style="width: 100%; text-align: left; background-color: gainsboro; padding: 10px 20px;">成績管理</h2>

            <!-- 検索フォーム -->
            <div id="subjectInfo">
                <form method="post" action="">
                    <table>
                        <thead>
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>科目</th>
                                <th>回数</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <select name="year">
                                        <!-- Options for 入学年度 -->
                                    </select>
                                </td>
                                <td>
                                    <select name="class">
                                        <!-- Options for クラス -->
                                    </select>
                                </td>
                                <td>
                                    <select name="subject">
                                        <!-- Options for 科目 -->
                                    </select>
                                </td>
                                <td>
                                    <select name="times">
                                        <!-- Options for 回数 -->
                                    </select>
                                </td>
                                <td>
                                    <input type="submit" value="検索">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>

            <!-- 検索結果の表示 -->
            <div>
                <%
                    String year = request.getParameter("year");
                    String className = request.getParameter("class");
                    String subject = request.getParameter("subject");
                    String times = request.getParameter("times");

                    List<Map<String, String>> searchResults = new ArrayList<>();

                    if (year != null && className != null && subject != null && times != null) {
                        Connection conn = null;
                        PreparedStatement stmt = null;
                        ResultSet rs = null;

                        try {
                            //conn = database.TABLE_ALL();
                            String sql = "SELECT year, class, studentNumber, name, score FROM grades WHERE year = ? AND class = ? AND subject = ? AND times = ?";
                            stmt = conn.prepareStatement(sql);
                            stmt.setString(1, year);
                            stmt.setString(2, className);
                            stmt.setString(3, subject);
                            stmt.setString(4, times);

                            rs = stmt.executeQuery();

                            while (rs.next()) {
                                Map<String, String> result = new HashMap<>();
                                result.put("year", rs.getString("year"));
                                result.put("class", rs.getString("class"));
                                result.put("studentNumber", rs.getString("studentNumber"));
                                result.put("name", rs.getString("name"));
                                result.put("score", rs.getString("score"));
                                searchResults.add(result);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
                            if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
                        }
                    }

                    request.setAttribute("searchResults", searchResults);
                %>

                <c:if test="${not empty searchResults}">
                    <p>検索結果</p>
                    <table>
                        <thead>
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>点数</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="result" items="${searchResults}">
                                <tr>
                                    <td>${result.year}</td>
                                    <td>${result.class}</td>
                                    <td>${result.studentNumber}</td>
                                    <td>${result.name}</td>
                                    <td>${result.score}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>

            <!-- 登録ボタン -->
            <div style="text-align: center; margin-top: 20px;">
                <button type="button" onclick="registerScores()">登録して終了</button>
            </div>
        </div>

        <script>
            function searchSubject() {
                // 科目情報の検索処理
            }

            function registerScores() {
                // 成績登録処理
            }
        </script>

    </div>
</main>

<%@ include file="../footer.jsp" %>

<script src="../javascript/login.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</body>
</html>
