<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成績参照</title>
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
    input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    input[type="submit"]:hover {
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
</head>
<body>

<div class="container">
    <h2>成績参照</h2>

    <div class="section">
        <div>科目情報</div>
        <form action="TestListSubjectExecute.action" method="get">
            <table>
                <tr>
                    <th>入学年度</th>
                    <th>クラス</th>
                    <th>科目</th>
                </tr>
                <tr>
                    <td>
                        <select size="1" name="f1">
                            <option value="----">--------</option>
                            <option value="2023">2023年度</option>
                            <option value="2022">2022年度</option>
                            <option value="2021">2021年度</option>
                        </select>
                    </td>
                    <td>
                        <select size="1" name="f2">
                            <option value="---">--------</option>
                            <option value="131">131</option>
                            <option value="132">132</option>
                            <option value="133">133</option>
                        </select>
                    </td>
                    <td>
                        <select size="1" name="f3">
                            <option value="----">--------</option>
                            <option value="国語">国語</option>
                            <option value="数学">数学</option>
                            <option value="英語">英語</option>
                        </select>
                    </td>
                </tr>
            </table>
            <input type="submit" value="検索">
            <input type="hidden" name="f" value="sj">
        </form>
    </div>

    <a href="TestListStudentExecute.action">科目別成績一覧へ</a>

    <div class="section">
        <div>学生情報</div>
        <form action="TestListStudentExecute.action" method="get">
            <table>
                <tr>
                    <th>学生番号</th>
                </tr>
                <tr>
                    <td>
                        <input type="text" id="uname" name="f4" placeholder="学生番号を入力してください" maxlength="10" required />
                    </td>
                </tr>
            </table>
            <input type="submit" value="検索">
            <input type="hidden" name="f" value="st">
        </form>
    </div>

    <p>利用方法案内メッセージ: 科目情報を選択してください。学生情報を入力してください。</p>
</div>

</body>
</html>