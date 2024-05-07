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
  <script>
  	'use strict';
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <select name="opt" id="opt" onchange="">
	  <option value="">선택</option>
	  <option value="input">회원가입</option>
	  <option value="update">수정하기</option>
	  <option value="delete">삭제하기</option>
	  <option value="search">검색하기</option>
	  <option value="list">전체리스트</option>
	</select>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>