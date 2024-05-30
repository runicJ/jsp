<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/errorMSG/errorMessage.jsp" %>  <!-- 에러발생하면 얘좀 불러줘 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>errorJSP.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  name 파라미터 값 : <%=request.getParameter("name").toUpperCase() %>  <!-- name값이 넘어온게 없으므로 null포인트Exception 발생 -->
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>