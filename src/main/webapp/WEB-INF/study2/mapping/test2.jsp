<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ include file="/include/certification.jsp" %> --%>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>test2.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
  	'use strict';
  	/* 
  	function fCheck(flag) {
  		if(flag == 1) {
  			myform.submit();
  		}
  		else if(flag == 2) {
  			location.href="${ctp}/mapping/test1";
  		}
  		else if(flag == 3) {
  			//location.href="${ctp}/mapping/test1Res"  이렇게 하면 값을 안가져감
  			myform.action = "${ctp}/mapping/test1Res";
  			myform.submit();
  		}
  		else if(flag == 4) {
  			myform.action = "${ctp}/mapping/test1Res2";
  			myform.submit();
  		}
  	} */
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2>이곳은 test2.jsp(/WEB-INF/study2/mapping)</h2>
  <hr/>
  <div>전송 메시지 : ${msg}</div>
  <hr>
  <div>
  	<a href="list.do" class="btn btn-success mr-3" >연습List</a>  <!-- controller가 전부 1개로 감(앞에 뭘 쓰던(의미없음) 확장자만 봄) -->
  	<a href="input.do" class="btn btn-warning mr-3">연습Input</a>  <!-- 앞의 경로 신경쓰지 않고 ctp 신경x -->
  	<a href="update.do" class="btn btn-info mr-3">연습Update</a>
  	<a href="delete.do" class="btn btn-primary mr-3">연습Delete</a>
  	<a href="search.do" class="btn btn-secondary mr-3">연습Search</a>
  </div>
  <hr>
  <div>
  	<form name="myform" method="post" action="${ctp}/mapping/test2.do">  <!-- 디렉토리 패턴 -->
  		<div>
	  		<input type="number" name="su1" value="${su1}" class="form-control mb-2" />  <!-- 이제 controller를 태우니까 EL을 씀 -->
	  		<input type="number" name="su2" value="${su2}" class="form-control mb-2" />
  		</div>
  		<div><input type="submit" value="전송하기" class="btn btn-success"/></div>
  	</form>
  </div>
  <div>
  	<div>두수의 합 : ${hap}</div>
  	<div>두수의 차 : ${cha}</div>
  </div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>

<!-- jsp 파일을 바깥으로 노출시키지 않음 / ctrl + f11로 화면이 보이지 않음(무조건 controller(서블릿) 거쳐야) -->