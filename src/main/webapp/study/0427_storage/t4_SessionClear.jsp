<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t4_ApplicationClear.jsp -->
<%
	session.setAttribute("sCount", 0);
%>
<script>
	alert("세션 값이 초기화 되었습니다.");
	location.href = "t4_StorageTest.jsp";
</script>