<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>成績管理一覧</title>
</head>
<body>
    <h2>成績管理一覧</h2>
    <div id="subjectInfo">
        <table>
            <thead>
                <tr>
                    <th>入学年度</th>
                    <th>クラス</th>
                    <th>科目</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <select name="f1">
                            <!-- Options for 入学年度 -->
                        </select>
                    </td>
                    <td>
                        <select name="f2">
                            <!-- Options for クラス -->
                        </select>
                    </td>
                    <td>
                        <select name="f3">
                            <!-- Options for 科目 -->
                        </select>
                    </td>
                    <td>
                        <button type="button" onclick="searchSubject()">検索</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <div id="studentInfo">
        <table>
            <thead>
                <tr>
                    <th>学生番号</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <input type="text" name="f4" value="${f4}" placeholder="学生番号を入力してください" maxlength="10" required>
                    </td>
                    <td>
                        <button type="button" onclick="searchStudent()">検索</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <p id="usageInfo">
        <!-- 科目情報の科目名と科目番号が表示される -->
    </p>

    <input type="hidden" name="sj" value="科目情報から成績を表示するための識別コード">
    <input type="hidden" name="st" value="学生情報から成績を表示するための識別コード">

    <script>
        function searchSubject() {
            // 科目情報の検索処理
        }

        function searchStudent() {
            // 学生情報の検索処理
        }
    </script>
</body>
</html>
