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

  		<div class="container w-75 headerstyle" style="background-color:lightskyblue">
  			<ul style="display:flex">
  				<li style="list-style:none; flex-grow:2"><h2>得点管理システム</h2></li>
       			<% if (session.getAttribute("user") != null){%>
       			<li style="list-style:none"><span>${user.name } 様</span></li>
       			<li style="list-style:none"><a href="Logout.action">ログアウト</a></li>
       			<% } %>
			</ul>
  		</div>
  	</header>