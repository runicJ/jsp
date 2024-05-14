<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>  <!-- 엔터키에 대한 처리 newLine 변수로 사용 -->
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>boardContent.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <style>
  	th {
  		text-align: center;
  		background-color: #eee;
  	}
  </style>
  <script>
  	'use strict';
  	
  	function boardDelete() {
  		let ans = confirm("현재 게시글을 삭제 하시겠습니까?");
  		if(ans) location.href = "BoardDelete.bo?idx=${vo.idx}";  // 여기서는 idx를 넘겨야함 // 아래에서 안가져오면 +idx
  	}
  	
  	// 좋아요 처리(중복허용)
  	function goodCheck() {
  		$.ajax({
  			url : "BoardGoodCheck.bo",
  			type : "post",
  			data : {idx : ${vo.idx}},  // 현재 게시글의 idx  // 숫자는 안써도 되지만 문자는 "" 무조건 써야함  // 여기까지가 전송할 것
  			success:function(res) {
  				if(res != "0") location.reload();  // 전부다 새로 읽는 것 (부분리로드 처리)
  			},
  			error:function() {
  				alert("전송오류");
  			}
  		});
  	}
  	
    // 좋아요 처리(중복불허)
    function goodCheck2() {
    	$.ajax({
    		url  : "BoardGoodCheck2.bo",
    		type : "post",
    		data : {idx : ${vo.idx}},
    		success:function(res) {
    			if(res != "0") location.reload();
    			else alert("이미 좋아요 버튼을 클릭하셨습니다.");
    		},
    		error : function() {
    			alert("전송오류");
    		}
    	});
    }

  	// 좋아요(따봉)수 증가 처리(중복허용)
  	function goodCheckPlus() {
  		$.ajax({
  			url : "BoardGoodCheckPlusMinus.bo",
  			type : "post",
  			data : {
  				idx : ${vo.idx},
  				goodCnt : +1
  			},
  			success:function(res) {
  				if(res != "0") location.reload();
  				else alert("이미 좋아요 버튼을 클릭하셨습니다.");
  			},
  			error:function() {
  				alert("전송오류");
  			}
  		});
  	}
  	
  	// 좋아요(따봉)수 감소 처리(중복허용)
  	function goodCheckMinus() {
  		$.ajax({
  			url : "BoardGoodCheckPlusMinus.bo",  // 한번에 처리
  			type : "post",
  			data : {
  				idx : ${vo.idx},
  				goodCnt : -1
  			},
  			success:function(res) {
  				if(res != "0") location.reload();
  			},
  			error:function() {
  				alert("전송오류");
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
  <h2 class="text-center mb-4">글 내 용 보 기</h2>
  <hr>
  <table class="table table-bordered">
  	<tr>
  		<th>글쓴이</th>
  		<td>${vo.nickName}</td>
  		<th>작성일</th>
  		<td>${fn:substring(vo.wDate, 0, 19)}</td>  <!-- 초 안나오게 하려면 16개 -->
  	</tr>
  	<tr>
  		<th>글조회수</th>
  		<td>${vo.readNum}</td>
  		<th>접속IP</th>
  		<td>${vo.hostIp}</td>
  	</tr>
  	<tr>
  		<th>글제목</th>
  		<td colspan="3">${vo.title}</td>
  	</tr>
  	<tr>
  		<th>글내용</th>
  		<td colspan="3" style="height:220px">${fn:replace(vo.content, newLine, "<br/>")}</td>
  	</tr>
  	<tr>
  		<td colspan="4">
  			<div class="row">
  				<div class="col">
	  				<input type="button" value="돌아가기" onclick="location.href='BoardList.bo?pag=${pag}&pageSize=${pageSize}';" class="btn btn-warning" />  <!-- controller로 넘겼으니까 command에서 값을 받아야함 --> <!-- 왔던 페이지랑 페이지사이즈를 가져가?검색 기능 있으면 검색분류와 검색어까지 같이 보냄 // 단순히 보기만 하면 history.back(), 수정삭제 하면 이걸로 안도미 -->
		  		</div>
		  		<div class="col text-center">
	  				<a href="javascript:goodCheck()"> ❤ </a> ${vo.good} /
	  				<a href="javascript:goodCheckPlus()"> 💖 </a> &nbsp;
	  				<a href="javascript:goodCheckMinus()"> 💔 </a> /
	  				<a href="javascript:goodCheck2()"><font color="blue" size="5">♥</font></a> ${vo.good}
	  			</div>
	  			<div class="col text-right">
			  		<c:if test="${sNickName == vo.nickName || sLevel == 0}">
				  		<input type="button" value="수정" onclick="location.href='BoardUpdate.bo?idx=${vo.idx}&pag=${pag}&pageSize=${pageSize}'" class="btn btn-primary" />
				  		<input type="button" value="삭제" onclick="boardDelete()" class="btn btn-danger" />  <!-- 현재 글에 있는 idx는 하나이기 때문에 idx 전달할 필요 없음 // 현재 페이지 -->
			  		</c:if>
			  	</div>
	  		</div>
  		</td>
  	</tr>
  </table>
  <hr>
  <!-- 이전글/ 다음글 출력하기 -->
  <table class="table table-borderless">  <!-- 얘도 테이블로 써야 위와 시작점이 같음 -->
  	<tr>
  		<td>
  			<c:if test="${!empty nextVo.title}">
  				💚 <a href="BoardContent.bo?idx=${nextVo.idx}">다음글 : ${nextVo.title}</a><br>  <!-- 페이지번호, 페이지크기 같이 전달 -->
  			</c:if>
  			<c:if test="${!empty preVo.title}">  			
  				🧡 <a href="BoardContent.bo?idx=${preVo.idx}">이전글 : ${preVo.title}</a><br>
  			</c:if>
  		</td>
  	</tr>
  </table>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>