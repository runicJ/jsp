<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>scrollBasic.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
  	'use strict';
  	
  	let lastScroll = 0;  // 마지막 위치
  	let curPage = 1;
  	
  	$(document).scroll(function(){
  		let currentScroll = $(this).scrollTop();  // 스크롤바 위쪽 시작 위치, 처음은 0이다. // currentScroll이라는 이름에 저장  // 현재 높이 0
  		let documentHeight = $(document).height();  // 화면에 표시되는 전체 문서의 높이 // 본문의 크기
  		let nowHeight = $(this).scrollTop() + $(window).height();  // 현재 화면상단 + 현재 화면높이 // 현재 높이 + 현재 화면의 높이
  		
  		// 스크롤이 아래로 내려갔을떄 이벤트 처리..
  		if(currentScroll > lastScroll) {  // 화면 끝까지 갔는지 체크
  			if(documentHeight < (nowHeight + (documentHeight*0.1))) {
  				// 다음페이지 가져오기...
  				console.log("다음페이지 가져오기");
  				curPage++;
  				getList(curPage);
  			}
  		}
  		lastScroll = currentScroll;  // 이렇게 하고 다시 계산
  	});
  	
  	// 리스트 불러오기 함수(ajax처리)
  	function getList(curPage) {
  		$.ajax({
  			url : "ScrollPage.st",
  			type : "post",
  			data : {pag : curPage},
  			success:function(res) {
  				// $("#list-wrap").html(res);  // 이렇게 하면 덮어쓰는 개념
  				$("#list-wrap").append(res);
  			},
  			error:function() {
  				alert("전송 오류!");
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
  <h2>무한 스크롤 연습하기</h2>
  <hr>
  <section id="list-wrap">  <!-- 시멘틱스 태그 -->
  	<c:forEach var="vo" items="${vos}" varStatus="st">
  		<div class="card">
  			<div class="card-bocy">
  				<h3>${curScrStartNo}.</h3><img src="${ctp}/images/112.jpg" width="100px" />
  			</div>
  			<div class="card-footer">
  				<h3>${vo.title}</h3>
  				<div><p>${vo.wDate}</p></div>
  			</div>
  		</div>
  		<br>
  		<c:set var="curScrStartNo" value="${curScrStartNo - 1}" />
  	</c:forEach>
  </section>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>