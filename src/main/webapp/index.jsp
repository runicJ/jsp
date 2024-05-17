<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  <!-- page : 지시자 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<script>
	location.href = "${ctp}/Main";
</script>

<!-- https://ddururiiiiiii.tistory.com/394 -->