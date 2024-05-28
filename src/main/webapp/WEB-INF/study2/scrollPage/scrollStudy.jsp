<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>scrollStudy.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <style>
  	.sample .box {
  		margin: 20px auto;  /* 가운데 정렬 */
  		width: 400px;
  		height: 200px;
  		background-color: #def;
  		border: 2px solid #492;
  		padding: 20px;
  		overflow: auto;  /* 넘칠때 자동으로 스크롤바 */
  	}
  	.sample .small-box {
  		width: 500px;
  		height: 300px;
  		background-color: #eee;
  		border: 1px dashed #492;
  		display: flex;
  		align-items: center;  /* flex의 수직 가운데 정렬 */
  		justify-content: center;  /* flex의 수평 가운데 정렬 */
  	}
  </style>
  <script>
  	'use strict';
  	
  	$(function() {
  		const box = document.querySelector(".sample .box");  // box의 정보 가져오기 // class 가져올때(아이디는 # 클리스는 . selector안은 큰따움표)
  		
  		// box에서 스크롤 시킬때 정보 수집..
  		//box.addEventListener("", function() {});  // 콜백함수는 arrow로 만들수 있음
  		box.addEventListener("scroll", () => {  // "명령어" (이벤트)
  			let x = box.scrollLeft;  // 스크롤바의 x축(왼쪽) 시작좌표
  			let y = box.scrollTop;  // 스크롤바의 y축(위쪽) 시작좌표
  			
  			document.querySelector("#disp .h").innerHTML = x;  // 움직여야 값이 나옴
  			document.querySelector("#disp .v").innerHTML = y;
  		});
  		
  		/* 스크롤바 제어하기 */
  		// 초기화 버튼
  		document.querySelector(".sampleBtn .btn1").addEventListener("click", (e) => {  // click, hover, drag  // 이벤트 발생시 변화가 있으면 e 값에 따라서
  			e.preventDefault();
  			document.querySelector(".sample .box").scrollLeft = 0;  // 박스의 위치 초기화
  			document.querySelector(".sample .box").scrollTop = 0;
  		});
  		// 오른쪽으로 이동하는 버튼
  		document.querySelector(".sampleBtn .btn2").addEventListener("click", (e) => {
  			e.preventDefault();
  			document.querySelector(".sample .box").scrollLeft = 150;
  		});
  		// 위에서 아래로 이동하는 버튼
  		document.querySelector(".sampleBtn .btn3").addEventListener("click", (e) => {
  			e.preventDefault();
  			document.querySelector(".sample .box").scrollTop = 150;
  		});
  		
  		document.querySelector(".sampleBtn .btn4").addEventListener("click", (e) => {
  			e.preventDefault();
  			document.querySelector(".sample .box").scrollLeft += 10;
  		});
  		document.querySelector(".sampleBtn .btn5").addEventListener("click", (e) => {
  			e.preventDefault();
  			document.querySelector(".sample .box").scrollLeft -= 10;
  		});
  		
  		document.querySelector(".sampleBtn .btn6").addEventListener("click", (e) => {
  			e.preventDefault();
  			document.querySelector(".sample .box").scrollTop += 10;
  		});
  		document.querySelector(".sampleBtn .btn7").addEventListener("click", (e) => {
  			e.preventDefault();
  			document.querySelector(".sample .box").scrollTop -= 10;
  		});
  	})
  	
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h3>스크롤 학습하기</h3>
  <div class="sample text-center">
  	<div class="box">
	  	<div class="small-box">
	  		<span class="text-success"><b>박스 안에서 스크롤바를 '상/하/좌/우'로 이동해 보세요</b></span>
	  	</div>
	  </div>
  </div>
  <div class="text-danger text-center" id="disp">
  	<strong>
	  	<span class="h">0</span> px /  <!-- 수직/수평 0px -->
	  	<span class="v">0</span> px
  	</strong>
  </div>
  <hr>
  <div class="sampleBtn">
  	<div>
  		<input type="button" value="초기화" class="btn btn-success mb-2 btn1"/>  <!-- btn1은 초기화 버튼 -->
  	</div>
  	<div>
  		<input type="button" value="x축으로 +150px" class="btn btn-primary mb-2 btn2"/>  <!-- onclick해도 되고 쿼리셀렉터도 가능 -->
  		<input type="button" value="y축으로 +150px" class="btn btn-outline-primary mb-2 btn3"/>
  	</div>
  	<div>
  		<input type="button" value="x축으로 10증가" class="btn btn-info mb-2 btn4"/>
  		<input type="button" value="x축으로 10감소" class="btn btn-outline-info mb-2 btn5"/>
  	</div>
  	<div>
  		<input type="button" value="y축으로 10증가" class="btn btn-secondary mb-2 btn6"/>
  		<input type="button" value="y축으로 10감소" class="btn btn-outline-secondary mb-2 btn7"/>
  	</div>
  </div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>