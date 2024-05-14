<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>adminLeft.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
</head>
<body>
<p><br/></p>
<!-- <div class="container"> --> <!-- container 하면 80%사용 너무 좁아짐 -->
<div class="text-center">
  <h5><a href="AdminMain.ad" target="_top">관리자메뉴</a></h5>  <!-- target 지정 안하면 // top 합쳐서 다시 뿌림(parent도 ㄱㅊ) -->
  <hr>
  <p><a href="${ctp}/Main" target="_top">홈으로</a></p>  <!-- _top 모두 묶어서 간다 -->
  <hr>
  <div>  <!-- 아코디언 메뉴로 바꿔서 -->
  	<h5>방명록</h5>
  	<div><a href="${ctp}/GuestList" target="adminContent">방명록리스트</a></div>  <!-- adminContent 오른쪽에 -->
  </div>
  <hr>
  <div>
  	<h5>게시판</h5>
  	<div><a href="BoardList.ad" target="adminContent">게시판리스트</a></div>
  </div>
  <hr>  
  <div>
  	<h5>회원관리</h5>
  	<div><a href="MemberList.ad" target="adminContent">회원리스트</a></div>  <!-- mem으로 함부로 하면 안됨(관리자가 보는 회원리스트 => 수정/삭제) // 새로 하는게 나음 // 관리자든 멤버든 하나를 깔끔하게 만들고 고쳐서 사용(왔다갔다 하면서 만들면 힘듬) -->
  	<div><a href="#" target="adminContent">신고리스트</a></div>
  </div>
  <hr>  
</div>
<p><br/></p>
</body>
</html>