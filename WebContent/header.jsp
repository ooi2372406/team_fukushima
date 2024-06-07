<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
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
    <header>

  		<div class="headerstyle" style="background-color:lightskyblue;">

  				<div><h2>得点管理システム</h2></div>
       			<% if (session.getAttribute("user") != null){%>
       			<div style="padding:15px 10px 0 0;"><span>${user.name } 様</span></div>
       			<div style="padding:15px 10px 0 0 ;"><a href="Logout.action">ログアウト</a></div>
       			<% } %>

  		</div>
  	</header>