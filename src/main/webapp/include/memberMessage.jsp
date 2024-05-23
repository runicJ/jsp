<%@ page import="memeber.MemberChatVO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="memeber.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	request.setCharacterEncoding("utf-8");  // controller 안태웠으니까 강제 인코딩 처리 해야함  // controller 거쳐서 command 객체에 쓸 내용을 여기에 쓰는 것
	MemberDAO dao = new MemberDAO();
	ArrayList<MemberChatVO> vos = dao.getMemberMessage();
	pageContext.setAttribute("vos", vos);  // 여기서 담아서 밑에서 jstl쓰려고 올림 MVC2는 못가도 MVC는 가야함
%>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>memberMessage.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
  	'use strict';
  	
  	setTimeout("location.reload()", 1000*5);  // 무조건 리로드(5초에 한번 / 나중에 10초로 바꾸기)
  	
  	//$(function(){
  	$(document).ready(function(){
  		document.body.scrollIntoView(false);  // 스크롤바를 강제로 Body태그의 마지막 위치로 이동시킨다.
  	});
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <c:forEach var="vo" items="${vos}" varStatus="st">
  	<c:if test="${sMid == vo.mid}"><font color="blue">${vo.mid} : ${vo.chat}</font></c:if>
  	<c:if test="${sMid != vo.mid}">${vo.mid} : ${vo.chat}</c:if>
  	<br><br>
  </c:forEach>
</div>
<p><br/></p>
</body>
</html>