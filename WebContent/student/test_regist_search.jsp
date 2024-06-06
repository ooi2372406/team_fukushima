<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>成績一覧</title>
</head>
<body>
    <!-- 成績一覧テーブル -->
    <table border="1">
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
            <!-- 成績情報 -->
            <tr>
                <!-- 入学年度 -->
                <td>2022</td>
                <!-- クラス -->
                <td>Aクラス</td>
                <!-- 学生番号 -->
                <td>123456</td>
                <!-- 氏名 -->
                <td>田中太郎</td>
                <!-- 点数 -->
                <td><input type="number" min="0" max="100"></td>
            </tr>
            <!-- 他の成績情報も同様に追加 -->
        </tbody>
    </table>

    <!-- 登録して終了ボタン -->
    <button onclick="registerAndFinish()">登録して終了</button>

    <script>
        // 登録して終了処理
        function registerAndFinish() {
            // 登録処理を行う
            // 登録が成功したら、登録完了画面に遷移する
            window.location.href = 'http://localhost:8080/student_management/student/test_regist_done.jsp';
        }
    </script>
</body>
</html>
