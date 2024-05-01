<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ include file="/include/certification.jsp" %> --%>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>t2_Certification.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
  	'use strict';
  	
  	function certificationCheck() {
  		let certification = myform.certification.value;  // 변수명과 함수명이 같으면 에러남!! (익명함수)
  		if(certification == "" || certification.length != 4) {
  			alert("인증코드를 확인하세요(4자리)");
  			return false;
  		}
  		else {
  			location.href="${ctp}/j0430/T02_Certification?admin=admin&u="+certification;  // filter를 통하기 때문에 이렇게 밖에 사용x
  		}
  	}
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2>이곳은 인증코드 발급창입니다.</h2>  <!-- filter에서 주소창(쿼리스트링)으로 인증 => ?admin=admin 쓰면됨 // 관리자 창 따로 해둠 // 핸드폰 인증 하는 것처럼 -->
  <form name="myform">
  	<div><input type="text" name="certification" value="1234" class="form-control" /></div>
  	<div><input type="button" value="인증코드발급" onclick="certificationCheck()" class="btn btn-success form-control mt-2"/></div>
  </form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>