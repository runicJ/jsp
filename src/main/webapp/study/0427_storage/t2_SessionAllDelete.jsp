<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t2_SessionAllDelete.jsp --> <!-- 서블릿으로 생각하고 작성 중 -->
<%
	session.invalidate();
%>
<script>
	alert("모든 세션이 삭제 되었습니다.");
	location.href = "t2_Session.jsp";
</script>