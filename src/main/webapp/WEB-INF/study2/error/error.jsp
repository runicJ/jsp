<%@page import="memeber.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	MemberVO vo = new MemberVO();
	vo.setMid("hkd1234");
	pageContext.setAttribute("vo", vo);  /* mvc2로는 보내려고 하는 것 */
%>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>error.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2>Error 페이지 연습</h2>
  <hr>
  <div><a href="ErrorJSP.st" class="btn btn-success mb-2">JSP에러 발생</a></div>
  <div><a href="Error400.st?vo=${vo}" class="btn btn-warning mb-2">400에러 발생(X)</a></div>  <!-- 문법상으론 문제없음 -->
  <div><a href="Error404.st" class="btn btn-primary mb-2">404에러 발생</a></div>  <!-- jsp에서 못고침 서블릿에서 해야함 -->
  <div><a href="Error500.st" class="btn btn-info mb-2">500에러 발생</a></div>  <!-- 코드 잘못쓴 것 컴파일에러 -->
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>