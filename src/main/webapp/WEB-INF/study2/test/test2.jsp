<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ include file="/include/certification.jsp" %> --%>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<%-- <%
	String msg = request.getParameter("msg")==null ? "공백" : request.getParameter("msg");
	pageContext.setAttribute("msg", msg);
%> --%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>test2.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2>이곳은 test2.jsp(/WEB-INF/study2/test)</h2>
  <hr/>
  <div>전송 메시지 : ${msg}</div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>
