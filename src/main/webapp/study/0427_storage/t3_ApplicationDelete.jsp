<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%@ include file = "/include/bs4.jsp" %>
  <title>t3_ApplicationDelete.jsp</title>
  <script>
  	'use strict';
  	
  	function applicationDelete() {
  		let applicationSW = document.getElementById("applicationSW").value;
  		
  		if(applicationSW == "") {
  			alert("삭제할 값을 선택하세요");
  			return false;
  		}
  		
  		location.href = "t3_ApplicationDeleteOk.jsp?applicationSW="+applicationSW;  // 여기서 값을 넘겨줘야함 => 쿼리스트링
  	}
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2>어플리케이션 삭제</h2>
  <select name="applicationSW" id="applicationSW" onclick="applicationDelete()">  <!-- js를 사용했기 때문에 위에 script에서 처리 // view page는 서버프로그램이 먼저 실행 꺽쇠퍼센트부터 사용함(중간에 서버프로그램 부를 수 없음) => 비동기식 사용 -->
  	<option value="">선택</option>  <!-- null 체크 위해서 -->
  	<option value="aMid">아이디</option>
  	<option value="aNickName">닉네임</option>
  	<option value="aName">성명</option>
  </select>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>