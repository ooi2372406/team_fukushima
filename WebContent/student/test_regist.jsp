<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<%@ page import="java.io.*" %>

<%@ page import="java.io.*, java.sql.*" %>

<%
    // SQLファイルからデータを読み込む
    StringBuilder sqlQuery = new StringBuilder();
    try {
        String filePath = "./database/.sql";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            // 各行のSQLをStringBuilderに追加
            sqlQuery.append(line).append("\n");
        }
        reader.close();
    } catch (IOException e) {
        // ファイル読み込みエラーの処理
        e.printStackTrace();
    }

    // 読み込んだSQLを実行し、データを取得する
    try {
        String url = "jdbc:h2:tcp://localhost/~/db_TEAM_FUKUSHIMA";
        String username = "sa";
        // パスワードを含まないバージョンでは、パスワードを明示的に指定する代わりに、ユーザーに入力させるなどの方法を採用する必要があります

        Connection connection = DriverManager.getConnection(url, username, "");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery.toString());

        // データをテーブルに反映するコードをここに記述

        resultSet.close();
        statement.close();
        connection.close();
    } catch (SQLException e) {
        // SQL実行エラーの処理
        e.printStackTrace();
    }
%>




<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .section {
            margin-bottom: 20px;
        }
        .section div {
            font-weight: bold;
            margin-bottom: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px 12px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        select, input[type="text"] {
            width: 100%;
            padding: 8px;
            margin: 8px 0;
            box-sizing: border-box;
        }
        input[type="submit"], button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover, button:hover {
            background-color: #45a049;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #4CAF50;
            font-weight: bold;
        }
        a:hover {
            color: #45a049;
        }
        p {
            text-align: center;
            font-style: italic;
            color: #666;
        }
    </style>
    <title>成績管理一覧</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body>
    <div class="container">
        <!-- 見出し -->
        <h2>成績管理</h2>

        <!-- 検索フォーム -->
     <!-- 検索フォームと検索結果のセクションをコースごとに表示する -->
    <div class="container">


        <!-- 検索フォーム -->
        <div id="subjectInfo">
            <c:forEach var="result" items="${searchResults}">
                <table>
                    <thead>
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>科目</th>
                            <th>回数</th>
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
            <p>科目：Python1（1回）</p>
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
                    <!-- 検索結果を表示する部分 -->
                </tbody>
            </table>
        </div>

        <!-- 登録ボタン -->
        <div style="text-align: center; margin-top: 20px;">
            <button type="button" onclick="registerScores()">登録して終了</button>
        </div>
    </div>
</script>
</body>
</c:forEach>
</html>

<%@include file="../footer.jsp" %>
