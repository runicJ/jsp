<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/certification.jsp" %>
<%
	String hostIp = request.getRemoteAddr();
	//request.setAttribute("hostIp", hostIp);  // MVC 위에단 아랫단 따로따로 라고 생각
	pageContext.setAttribute("hostIp", hostIp);  // 현 페이지에서 저장을 유지하고 싶은 것
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%@ include file = "/include/bs4.jsp" %>
  <title>t01.jsp</title>
</head>
<body>
<%@ include file = "/include/header.jsp" %>
<%@ include file = "/include/nav.jsp" %>
<p><br/></p>
<div class="container">
  <h2>서버의 환경변수 값 확인하기</h2>
  <hr/>
  <div>호스트IP1 : <%=request.getRemoteAddr() %></div> <!-- 자바코드를 쓰려면 EL은 안되고 script나 표현식써야함 / IpV6 <= 128bit IPV4로 바꿔야(192.168.50.57/127.0.0.1) -->
  <div>호스트IP2 : ${hostIp}</div>
  <div>전송방식 : <%=request.getMethod() %></div>
  <div>접속프로토콜 : <%=request.getProtocol() %></div>
  <div>접속(서버)포트 : <%=request.getServerPort() %></div>
  <div>접속(서버)이름 : <%=request.getServerName() %></div>
  <div>접속 Context이름 : <%=request.getContextPath() %></div>
  <div>접속 URL : <%=request.getRequestURL() %></div>
  <div>접속 URI : <%=request.getRequestURI() %></div>  <!-- 식별자 Domain이름부터 나옴 -->
</div>
<p><br/></p>
<%@ include file = "/include/footer.jsp" %>
</body>
</html>