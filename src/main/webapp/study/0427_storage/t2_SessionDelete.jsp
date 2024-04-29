<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%@ include file = "/include/bs4.jsp" %>
  <title>t2_SessionDelete.jsp</title>
  <script>
	  'use strict';
	  
	  function sessionDelete() {
	  	let sessionSW = document.getElementById("sessionSW").value;
	  	
	  	if(sessionSW == "") {
	  		alert('삭제할 값을 선택하세요');
	  		return false;
	  	}
	  	
			location.href = "t2_SessionDeleteOk.jsp?sessionSW="+sessionSW;
	  }
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2>세션 삭제</h2>
  <select name="sessionSW" id="sessionSW" onclick="sessionDelete()">  <!-- js를 사용했기 때문에 위에 script에서 처리 // view page는 서버프로그램이 먼저 실행 꺽쇠퍼센트부터 사용함(중간에 서버프로그램 부를 수 없음) => 비동기식 사용 -->
  	<option value="">선택</option>  <!-- null 체크 위해서 -->
  	<option value="sMid">아이디</option>
    <option value="sNickName">닉네임</option>
    <option value="sName">성명</option>
  </select>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>