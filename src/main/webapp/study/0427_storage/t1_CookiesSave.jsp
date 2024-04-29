<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t1_CookiesSave.jsp -->
<%  /* JSP 안에 서블릿 */
	String mid = "hkd1234";
	Cookie cookieMid = new Cookie("cMid", mid);  // 쿠키는 내장함수, 쿠키를 생성하는 것이 아니라 cookieMid 생성 (내장변수, 값)
	cookieMid.setMaxAge(60*60*24);  // 쿠키의 만료시간(초) : 1일 = 60(초) * 60(분) * 24(시간) = ?
	response.addCookie(cookieMid);
	
	String pwd = "1234";
	Cookie cookiePwd = new Cookie("cPwd", pwd);
	cookiePwd.setMaxAge(60*60*24);
	response.addCookie(cookiePwd);
	
	String tel = "010-1234-5678";
	Cookie cookieTel = new Cookie("cTel", pwd);
	cookieTel.setMaxAge(60*60*24);
	response.addCookie(cookieTel);
%>
<script>
	alert("쿠키가 생성/저장 되었습니다.");
	location.href = "t1_Cookies.jsp";  // 원래로 보냄
</script>