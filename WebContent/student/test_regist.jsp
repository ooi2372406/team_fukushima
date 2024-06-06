<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>成績管理一覧</title>
<!-- Required meta tags -->
	<head>
    	<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    	<!-- Bootstrap CSS -->
    	<link rel="stylesheet" href="../css/stylesheet.css">
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    	<title>学生管理システム</title>
    </head>
    <body>

<body>
<!--見出し(h2)  -->
    <h2>成績管理</h2>
    <div id="subjectInfo">
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
                     <select name="f3">
                            <!-- Options for 回数 -->
                        </select>
                          </td>
                    		<td>
                        <button type="button" onclick="searchSubject()">検索</button>
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

<footer>
		<div class="container w-75" style="background-color:lightgray; padding:5px 0;">
			<div class="footerstyle"><p>© 2023 TIC</p></div>
			<div class="footerstyle"><p>大原学園</p></div>
		</div>
</footer>
