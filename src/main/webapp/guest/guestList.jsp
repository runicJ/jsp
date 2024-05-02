<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>  <!-- 자바코드임 newLine은 변수(\n밑에서인식 못하니까) -->
<%-- <%@ include file="/include/certification.jsp" %> --%>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>guestList.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <style>
  
  </style>
  <script>
  	'use strict';
  	
  	function delCheck(idx) {
  		let ans = confirm("현재 방문글을 삭제하시겠습니까?");
  		if(ans) {
  			location.href='${ctp}/GuestDelete?idx=' + idx;  // javascript 변수이기 때문에 그냥 +변수 쓰면됨, jsp 변수일 경우 EL로 적어야함
  			return false
  		}
  	}
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2 class="text-center">방 명 록 리 스 트</h2>
	<table class="table table-borderless m-0 p-0">
		<tr>
			<td><a href="#" class="btn btn-primary">관리자</a></td>  <!-- 마우스 올려서 주소보려면 a태그쓰기(좌측 하단) -->
			<td class="text-right"><a href="${ctp}/guest/guestInput.jsp" class="btn btn-success">글쓰기</a></td>
		</tr>
	</table>
  <c:forEach var="vo" items="${vos}" varStatus="st">
	  <table class="table table-borderless m-0 p-0">
	  	<tr>
	  		<td>
	  			번호 : ${vo.idx}
	  			<c:if test="${sMid == 'hkd1234'}"><a href="javascript:delCheck(${vo.idx})" class="btn btn-danger btn-sm">삭제</a></c:if>  <!-- onclick이 아니고 a태그일때 함수 부를떄 --> <!-- $ {!empty sMid} -->
	  		</td>  <!-- vo가 아니라 vos로 넘어옴(반복문으로 전체를 돌려야 한다) -->
	  		<td class="text-right">방문IP : ${vo.hostIp}</td>
	  	</tr>
	  </table>
	  <table class="table table-bordered">
	  	<tr>
	  		<th>성명</th>
	  		<td>${vo.name}</td>
	  		<th>방문일자</th>
	  		<td>${vo.visitDate}</td>
	  	</tr>
	  	<tr>
	  		<th>메일주소</th>
	  		<td colspan="3">${vo.email}</td>
	  	</tr>
	  	<tr>
	  		<th>홈페이지</th>
	  		<td colspan="3">${vo.homePage}</td>
	  	</tr>
	  	<tr>
	  		<th>방문소감</th>
	  		<td colspan="3">${fn:replace(vo.content,newLine,"<br/>")}</td>
	  	</tr>
	  </table>
  	<br/>
  </c:forEach>
	<br/>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>