<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../header.jsp" %>

	<% String errorMessage =(String)request.getAttribute("errorMessage"); %>
	 <main class="mt-xl-5">
  	<div class="container original-border loginstyle" style="width:40%;padding:0; margin-bottom:30px;">
  	<% if (errorMessage != null){%>
  		<div style="color:red"> ${ errorMessage } </div>
  	<% } %>
  		<div class="mx-auto my-auto" >
    		<h3 class="mb-auto text-center" style="background-color:whitesmoke;">ログイン</h3>
    		<form action="LoginExecute.action" class="login-form">

  				<div class="form-group formGroup1">
    				<label for="exampleInputEmail1" style="margin-bottom:0;">　ID</label>
    				<input type="text" class="exampleInputEmail1" name="id"  placeholder="admin" style="border:none;"required/>

  				</div>

  				<div class="form-group formGroup1">
    				<label for="exampleInputEmail1" style="margin-bottom:0;">　パスワード</label>
    				<input type="password" class="exampleInputEmail1" name="password" id="password" placeholder="	password" style="border:none"required>

  				</div>
  				<div class="form-check text-center" style="margin:10px 0;">
    				<input type="checkbox" class="form-check-input" id="show-password">
    				<label for="show-password">パスワードを表示</label>
  				</div>
  				<div class=" text-center">
  					<button type="submit" class="btn btn-primary" style="padding:10px 45px; margin-bottom:15px;">ログイン</button>
  				</div>
			</form>
		</div>
	</div>
	</main>

	<%@ include file="../footer.jsp" %>

 	<script src="../javascript/login.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>


</body>
</html>