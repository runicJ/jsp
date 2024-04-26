<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String msgFlag = request.getParameter("msgFlag");  // null값 처리는 서블릿에서나
	System.out.println("msgFlag : " + msgFlag); 
%> <!-- 서버에서 넘긴 것은 바깥에서 -->
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>t13_서버값전달이동.jsp</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		'use strict';
		
		function javascriptLogin() {
			myform.loginFlag.value = "javascript";  // 받아주는 서블릿(T13Ok)은 하나니까 script에서 교통정리
			myform.submit();
		}
		
		function responseLogin() {
			myform.loginFlag.value = "response";
			myform.submit();
		}
		
		function requestLogin() {
			myform.loginFlag.value = "request";
			myform.submit();
		}
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>서버에서의 값 전송 연습</h2>
  <form name="myform" method="post" action="<%=request.getContextPath() %>/j0425/T13Ok">
    <div>아이디
      <input type="text" name="mid" value="admin" class="form-control mb-3" autofocus required />
    </div>
    <div>비밀번호
      <input type="password" name="pwd" value="1234" class="form-control mb-3" required />
    </div>
    <div>
      <input type="button" value="Javascript로그인" onclick="javascriptLogin()" class="btn btn-success" />
      <input type="button" value="Response로그인" onclick="responseLogin()" class="btn btn-primary" />
      <input type="button" value="Request로그인" onclick="requestLogin()" class="btn btn-info" />
    </div>
    <input type="hidden" name="loginFlag" />  <!-- 사용자에게 안보임 -->
  </form>
</div>
<p><br/></p>
</body>
</html>