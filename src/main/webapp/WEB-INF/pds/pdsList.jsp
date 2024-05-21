<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>pdsList.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
  	'use strict';
  	
  	function partCheck() {
  		let part = $("#part").val();
  		location.href = "PdsList.pds?pag=${pag}&pageSize=${pageSize}&part=" + part;  // part는 js에서 받았으니까 끊어줌
  	}
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2 class="text-center">자 료 실 리 스 트(${part})</h2>  <!-- part pagination에서 넘어옴 -->
  <br>
  <table class="table table-borderless m-0 p-0">
  	<tr>
  		<td>
  			<form name="partForm">
  				<select name="part" id="part" onchange="partCheck()">
  					<option ${part=="전체" ? "selected" : ""}>전체</option>
  					<option ${part=="학습" ? "selected" : ""}>학습</option>
  					<option ${part=="여행" ? "selected" : ""}>여행</option>
  					<option ${part=="음식" ? "selected" : ""}>음식</option>
  					<option ${part=="기타" ? "selected" : ""}>기타</option>
  				</select>
  			</form>
  		</td>
  		<td class="text-right">
  			<a href="PdsInput.pds?part=${part}" class="btn btn-success">자료올리기</a>  <!-- 해당 파트를 보고 있다면 그 해당 파트로 가고 싶을때 -->
  		</td>
  	</tr>
  </table>
  <table class="table table-hover text-center">
  	<tr class="table-dark text-dark">
  		<th>번호</th>
  		<th>분류</th>
  		<th>자료제목</th>
  		<th>작성자</th>
  		<th>작성일</th>
  		<th>파일명(크기)</th>
  		<th>다운로드 수</th>
  		<th>비고</th>
  	</tr>
  	<c:set var="curScrStartNo" value="${curScrStartNo}" />
  	<c:forEach var="vo" items="${vos}" varStatus="st">
  		<tr>
  			<td>${curScrStartNo}</td>
  			<td>${vo.part}</td>
  			<td><a href="PdsContent.pds?idx=${vo.idx}&pag=${pag}&pageSize=${pageSize}&part=${part}">${vo.title}</a></td>
  			<td>${vo.nickName}</td>
  			<td>${vo.fDate}</td>
  			<td>
  				개별파일다운로드	
  			</td>
  			<td>${vo.downNum}</td>
  			<td>
  				<c:if test="${vo.mid == sMid || sLevel == 0}">
  					<a href="PdsDelete.pds?idx=${vo.idx}" class="badge badge-danger">삭제</a><br>
  				</c:if>
  				<a href="PdsTotalDownload.pds?idx=${vo.idx}" class="badge badge-primary">전체파일다운로드</a>  <!-- 여기서는 다운로드 라는 속성을 쓸 수 없음(여러개의 파일을 zip으로 만들어서 처리) -->
  			</td>
  		</tr>
  		<c:set var="curScrStartNo" value="${curScrStartNo - 1}" />
  	</c:forEach>
  	<tr><td colspan="8" class="m-0 p-0"></td></tr>
  </table>
</div>
<p><br/></p>
  <!-- 블록페이지 시작 -->  <!-- 0블록: 1/2/3 -->
	<div class="text-center">
		<ul class="pagination justify-content-center" style="margin:20px 0">
			<c:if test="${pag > 1}"><li class="page-item"><a class="page-link text-secondary" href="${ctp}/PdsList.pds?part=${part}&pag=1&pageSize=${pageSize}">첫페이지</a></li></c:if>
			<c:if test="${curBlock > 0}"><li class="page-item"><a class="page-link text-secondary" href="${ctp}/PdsList.pds?part=${part}&pag=${(curBlock*blockSize+1)-blockSize}&pageSize=${pageSize}">이전블록</a></li></c:if>  <!-- (curBlock-1)*blockSize +1 -->
			<c:forEach var="i" begin="${(curBlock*blockSize)+1}" end="${(curBlock*blockSize)+blockSize}" varStatus="st">  <!-- 처음이니까 curBlock => 0블록 -->
				<c:if test="${i <= totPage && i == pag}"><li class="page-item active"><a class="page-link bg-secondary border-secondary" href="${ctp}/PdsList.pds?part=${part}&pag=${i}&pageSize=${pageSize}">${i}</a></li></c:if>
				<c:if test="${i <= totPage && i != pag}"><li class="page-item"><a class="page-link text-secondary" href="${ctp}/PdsList.pds?part=${part}&pag=${i}&pageSize=${pageSize}">${i}</a></li></c:if>
			</c:forEach>
			<c:if test="${curBlock < lastBlock}"><li class="page-item"><a class="page-link text-secondary" href="${ctp}/PdsList.pds?part=${part}&pag=${(curBlock+1)*blockSize+1}&pageSize=${pageSize}">다음블록</a></li></c:if>
			<c:if test="${pag < totPage}"><li class="page-item"><a class="page-link text-secondary" href="${ctp}/PdsList.pds?part=${part}&pag=${totPage}&pageSize=${pageSize}">마지막페이지</a></li></c:if>
		</ul>
	</div>
	<!-- 블록페이지 끝 -->
<jsp:include page="/include/footer.jsp" />
</body>
</html>