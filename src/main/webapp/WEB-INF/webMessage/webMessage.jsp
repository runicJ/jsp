<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>webMessage.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <style>
  	#leftWindow {
  		float: left;  /* table로 안해서 float 줌 */
  		width: 25%;
  		height: 520px;
  		text-align: center;
  		background-color: #ddd;
  	}
  	#rightWindow {
  		float: left;
  		width: 75%;
  		height: 520px;
  		text-align: center;
  		background-color: #eee;
  		overflow: auto;  /* 내용이 기준틀을 넘으면 scrollbar가 나오도록 설정 */
  	}
  	#footerTopMargin {
  		clear: both;
  		margin: 10px;
  	}
  	h3 { text-align: center; }
  </style>
    <script>
  	'use strict';
  	
  	function wmDeleteAll() {
  		let ans = confirm("휴지통을 모두 비우시겠습니까?");
  		if(!ans) return false;
  		
  		$.ajax({
  			url : "WmDeleteAll.wm",
  			type : "post",
  			success:function(res) {
  				if(res != "0") {
  					alert("휴지통의 모든 메시지가 삭제 되었습니다.");
  					location.reload();
  				}
  				else alert("휴지통 비우기 실패!");
  			},
  			error:function() {
  				alert("전송실패~!");
  			}
  		});
  	}
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h3>메시지 관리</h3>
  <div>(현재접속: <font color='red'>${sMid}</font>)</div>
</div>
<div id="leftWindow">  <!-- frame 쓰면 반응형 고정됨(left right 나눌 필요가 없어서 설계는 쉬워짐) -->
	<p><br></p>
	<p><a href="WebMessage.wm?mSw=0">메시지작성</a></p>
	<p><a href="WebMessage.wm?mSw=1&mFlag=11&pag=${pag}&pageSize=${pageSize}">받은메시지</a></p>  <!-- 보고 돌아오고 이동할때 mFlag 안넣으면 길을 잃는 경우가 있음 -->
	<p><a href="WebMessage.wm?mSw=2">새메시지</a></p>
	<p><a href="WebMessage.wm?mSw=3&mFlag=13">보낸메시지</a></p>
	<p><a href="WebMessage.wm?mSw=4">수신확인</a></p>
	<p><a href="WebMessage.wm?mSw=5&mFlag=15">휴지통</a></p>
	<p><a href="javascript:wmDeleteAll()">휴지통비우기</a></p>  <!-- 굳이 확인하고 띄울 필요 없으니 js로 바로 처리 / 버튼으로 해도됨 -->
</div>
<div id="rightWindow">  <!-- 왼쪽 메뉴 누르면 메뉴에 해당하는 걸 include(같은 내용)로 삽입함(frame과 비슷해짐) -->
	<p>  <!-- 신호를 보내서 무슨 메뉴인지 -->
		<c:if test="${mSw == 0}">
			<h3>메시지 작성</h3>
			<jsp:include page="wmInput.jsp" />  <!-- WEB-INF에 존재 jsp를 부르는 것 (include의 특징은 같은 위치에 있으면 경로 안써도o) // url로 부르지 못함(web-inf는 controller에서 불러야 함) / 자신의 위치에서 부트는것(이 경우 controller로 부르지 않아도 됨) -->
		</c:if>
		<c:if test="${mSw == 1}">
			<h3>받은 메시지</h3>
			<jsp:include page="wmList.jsp" />  <!-- 각각 부르는게 아니라 통으로 불러서 List.jps, Input.jsp를 부름(controller를 사용하면 당연히 wmInput.jsp를 부르는 거지만 이 경우와 다름) -->
		</c:if>
		<c:if test="${mSw == 2}">
			<h3>신규 메시지</h3>
			<jsp:include page="wmList.jsp" />  <!-- List(vos) -->
		</c:if>
		<c:if test="${mSw == 3}">
			<h3>보낸 메시지</h3>
			<jsp:include page="wmList.jsp" />
		</c:if>
		<c:if test="${mSw == 4}">
			<h3>수신 확인</h3>
			<jsp:include page="wmList.jsp" />
		</c:if>
		<c:if test="${mSw == 5}">
			<h3>휴지통 목록</h3>
			<jsp:include page="wmList.jsp" />  <!-- 게시판 형식 하나로 끝냄 하나만 input -->
		</c:if>
		<c:if test="${mSw == 6}">
			<h3>메시지 내용보기</h3>
			<jsp:include page="wmContent.jsp" />  <!-- 얘만 idx 가져감 // 한건만 가져옴(vo) -->
		</c:if>
	</p>
</div>
<br>
<div id="footerTopMargin">&nbsp;</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>