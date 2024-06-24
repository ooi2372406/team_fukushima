<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
<!-- Required meta tags -->

    <body>
    <div class="wrapper">
    <header>

  		<div class="headerstyle" style="background-color:lightskyblue;">

  				<div><h2>得点管理システム</h2></div>
       			<% if (session.getAttribute("user") != null){%>
       			<div style="padding:15px 10px 0 0;"><span>${user.name } 様</span></div>
       			<div style="padding:15px 10px 0 0 ;"><a href="/student_management/login/Logout.action">ログアウト</a></div>
       			<% } %>

  		</div>
  	</header>