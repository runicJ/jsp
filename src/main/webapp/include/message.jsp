<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ include file = "/include/certification.jsp" %> --%>  <!-- 이거 있으면 메시지가 당연히 "로그인 후에 사용하세요"로 뜸 -->
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>message.jsp</title>
  <script>
    'use strict';
    
    if('${message}' != "NO") alert("${message}");  // NO가 아니면 메시지고
    location.href = "${url}";  // NO면 url로 바로 보낸다는겨 메시지 없이
  </script>
</head>
<body>

</body>
</html>