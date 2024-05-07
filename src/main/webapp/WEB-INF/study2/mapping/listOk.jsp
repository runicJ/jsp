<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/certification.jsp" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Insert</title>
  <%@ include file = "/include/bs4.jsp" %>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2>이곳은 ListOk.jsp 입니다.</h2>
  <hr>
  <div>
  	<input type="text" />
  	<a href="test5.do5" class="btn btn-warning">처리하기</a>
  </div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>