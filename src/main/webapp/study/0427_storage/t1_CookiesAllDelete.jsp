<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t1_CookiesAllDelete.jsp -->
<%
	Cookie[] cookies = request.getCookies();

	for(int i=0; i<cookies.length; i++) {
		cookies[i].setMaxAge(0);  // 쿠키의 만료시간 0되면서 소멸
		response.addCookie(cookies[i]);
	}
%>
<script>
	alert("모든 쿠키가 삭제 되었습니다.");  // 세션은 안지워짐
	location.href = "t1_Cookies.jsp";
</script>