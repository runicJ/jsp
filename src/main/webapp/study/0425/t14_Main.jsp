<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String mid = request.getParameter("mid");
	String loginFlag = request.getParameter("loginFlag");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>t14_Main.jsp</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		'use strict';
		/* 		
		if('${loginFlag}' == 'OK') {  // 문제점 새로고침 하면 계속 리로딩 됨 은행에서 돈을 찾는다고 했을때 계속 찾을 수 있음 // 끊어줘야함(T14_Res로 함)
			alert("${mid}님 로그인 되셨습니다.");  // T14Ok1에 담았으므로 신경ㄴㄴ
		}
		*/
		
    if('<%=loginFlag%>' != 'OK') {  // request에 저장됐던 정보가 사라졌다.
    	alert("로그인 후 접속하세요.");  // http://localhost:9090/javaclass/study/0425/t14_Main.jsp?mid=admin 를 주소창에 쳐도 접속x  // &loginFlag=OK는 안보인다고 생각해야함(세션 배우면 안보임)
    	location.href = '<%=request.getContextPath()%>/study/0425/t14_forward.jsp?mid=${mid}';
    }
		
		function logout() {
			let ans = confirm("로그아웃 하시겠습니까?");
			if(ans) {
				/* alert("${mid}님 로그아웃되셨습니다."); */
				alert("<%=mid%>님 로그아웃되셨습니다.");
				location.href = '<%=request.getContextPath()%>/study/0425/t14_forward.jsp';
			}
		}
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>회원 전용방입니다.</h2>
  <hr/>
  <%-- <p>${mid} 회원님 로그인 중이십니다.</p> --%>
  <p><%=mid%> 회원님 로그인 중이십니다.</p>
  <hr/>
  <p><a href="javascript:logout()" class="btn btn-warning">로그아웃</a></p>
</div>
<p><br/></p>
</body>
</html>