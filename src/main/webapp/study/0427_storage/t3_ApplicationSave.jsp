<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t3_ApplicationSave.jsp --> <!-- 내장객체이므로 간단히 사용 -->
<%
	request.setCharacterEncoding("utf-8");
	
	String mid = request.getParameter("mid")==null ? "guest" : request.getParameter("mid");
	String nickName = request.getParameter("nickName")==null ? "손님" : request.getParameter("nickName");
	String name = request.getParameter("name")==null ? "방문자" : request.getParameter("name");
	
	System.out.println("mid : " + mid);
	System.out.println("nickName : " + nickName);
	System.out.println("name : " + name);
	
	application.setAttribute("aMid", mid);
	application.setAttribute("aNickName", nickName);
	application.setAttribute("aName", name);
%>
<script>
	alert("어플리케이션이 생성/저장 되었습니다.");  // 서버가 시작될 때부터 끝날 때까지 유지(서버에 저장되므로 브라우저가 바뀌어도 값이 유지됨)
	location.href = "t3_Application.jsp";
</script>