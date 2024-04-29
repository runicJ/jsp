<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String mid = request.getParameter("mid");  // 쿼리스트링 방식으로 넘겼기 때문에 mid로 들어있는걸 가져옴  // request 객체에 담은 것이 아님(dipatcher.forward로 보낸것이 아님)
	pageContext.setAttribute("mid", mid);  // 이렇게 해야지 EL 방식으로 사용할 수 있음
%>  <!-- 이게 빠지면 MVC2 -->
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>ex1_Main.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    function searchCehck() {
    	let str = '';
    	str += '접속아이피 : <%=request.getRemoteAddr()%><br/>';
    	str += '접속 URL : <%=request.getRequestURL()%><br/>';
    	str += 'ContextPath명 : <%=request.getContextPath()%><br/>';
    	str += '접속프로토콜 : <%=request.getProtocol()%><br/>';
    	str += '접속방식 : <%=request.getMethod()%><br/>';
    	str += '<hr/>';
    	str += '<a href="javascript:location.reload();" class="btn btn-success">새로고침</a>';
    	
    	demo.innerHTML = str;
    }
  </script>
</head>
<body>
<p><br/></p>
<div class="container text-center">
  <h2>회원 전용방</h2>
  <br/>
  <p>현재 <font color='blue'><b>${mid}</b></font>님 로그인 중이십니다.</p>
  <hr/>
  <p><img src="<%=request.getContextPath()%>/images/112.jpg" width="300px"/></p>
  <hr/>
  <div class="row">
    <div class="col"></div>
    <div class="col"><button type="button" onclick="searchCehck()" class="btn btn-primary">접속조회</button></div>
    <div class="col"><a href="ex1_Logout.jsp?mid=${mid}" class="btn btn-danger">로그아웃</a></div>  <!-- 쿼리스트링 방식으로 넘김 request로 받아야 함 -->
    <div class="col"></div>
  </div>
  <hr/>
  <div id="demo"></div>
</div>
<p><br/></p>
</body>
</html>