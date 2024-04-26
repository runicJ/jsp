<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>T14_Res.jsp</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	<script>
    'use strict';
    
    if('${loginFlag}' == 'OK') {
    	alert("${mid}님 로그인 되셨습니다.");
    	location.href = '<%=request.getContextPath()%>/study/0425/t14_Main.jsp?mid=${mid}&loginFlag=${loginFlag}';
    }
    /* 
    	http://localhost:9090/javaclass/study/0425/t14_Main.jsp?mid=admin 그냥 전달됨 <= SQLinjection공격 
			request에 지금까지 저장됐던 정보는 response 화면에 사용자에게 응답했으므로 정보가 전부 끊김(사라짐)  // session을 아직 안배웠기 때문에 계속 저장이 안됨
 		*/

  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  
</div>
<p><br/></p>
</body>
</html>