<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
  	
  	// 다운로드 수 증가시키기
  	function downNumCheck(idx) {
  		$.ajax({
  			url : "PdsDownNumCheck.pds",
  			type : "post",
  			data : {idx : idx},
  			success:function() {
  				location.reload();
  			},
  			error : function() {
  				alert("전송오류!");
  			}
  		});
  	}
  	
  	// 자료 내용 삭제(자료 + Data)  // 자료 삭제하고 DB도 삭제
  	function pdsDeleteCheck(idx,fSName) {
  		let ans = confirm("선택하신 자료를 삭제하시겠습니까?");
  		if(!ans) return false;
  		else {
	  		$.ajax({
	  			url : "PdsDeleteCheck.pds",
	  			type : "post",
	  			data : {
	  				idx : idx,
	  				fSName : fSName
	  			},
	  			success:function(res) {
	  				if(res != 0) {
		  				alert("자료가 삭제되었습니다.");
		  				location.reload();
	  				}
	  				else alert("삭제 실패~");
	  			},
	  			error : function() {
	  				alert("전송오류!");
	  			}
	  		});
  		}
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
  			<%-- <td><a href="PdsContent.pds?idx=${vo.idx}&pag=${pag}&pageSize=${pageSize}&part=${part}">${vo.title}</a></td> --%>
  			<td class="text-left">
	        <a href="PdsContent.pds?idx=${vo.idx}&pag=${pag}&pageSize=${pageSize}&part=${part}">${vo.title}</a>  <!-- 지금까지 idx만 넘겼지만, 페이지 수, 페이지 사이즈, 검색필드, 검색어 같이 넘겨야함 --> <!-- 확장자 쓰면 좋은점 ctp 안써도 됨 -->
	        <c:if test="${vo.hour_diff <= 24}"><img src="${ctp}/images/new.gif" /></c:if>  <!-- 이미지 무조건 절대경로 // 시작은 webapp -->
		    </td>
  			<td>${vo.nickName}</td>
  			<%-- <td>${vo.fDate}</td> --%>
  			<td>
		    	${vo.date_diff == 0 ? fn:substring(vo.fDate,11,19) : fn:substring(vo.fDate,0,10)}  <!-- < c : if test = " $ { vo.date_diff == 0 } "> 쓰면 또 안에서 함수를 써야해서 삼항연산자로 -->
		    </td>
  			<td>
  				<c:set var="fNames" value="${fn:split(vo.fName,'/')}"/>  <!-- 스크립트 사용하지 않고 jstl로 사용 // 변수 설정 c set-->
  				<c:set var="fSNames" value="${fn:split(vo.fSName,'/')}"/>
  				<c:forEach var="fName" items="${fNames}" varStatus="st">  <!-- 개별 다운로드 -->
  					<a href="${ctp}/images/pds/${fSNames[st.index]}" download="${fName}" onclick="downNumCheck(${vo.idx})">${fName}<br></a>  <!-- download= $ {fName} 저장한 이름으로 다운 받도록 이름 설정 -->
  				</c:forEach>
  				<%-- (${vo.fSize}Byte) --%>
  				(<fmt:formatNumber value="${vo.fSize/1024}" pattern="#,##0" />kByte)
  				<%-- (<fmt:formatNumber value="${vo.fSize/1024/1024}" pattern="#,##0" />mByte) --%>
  			</td>
  			<td>${vo.downNum}</td>
  			<td>
  				<c:if test="${vo.mid == sMid || sLevel == 0}">
  					<a href="javascript:pdsDeleteCheck('${vo.idx}','${vo.fSName}')" class="badge badge-danger">삭제</a><br>  <!-- 숫자, 문자인데 '' 안붙여도 이 버전에선 에러 안남 -->
  				</c:if>
  				<a href="PdsTotalDownload.pds?idx=${vo.idx}" class="badge badge-primary">전체파일다운로드</a>  <!-- fName이나 fSName 하면 편한데 이걸로 안하고 처음부터 작성 -->  <!-- 여기서는 다운로드 라는 속성을 쓸 수 없음(여러개의 파일을 zip으로 만들어서 처리) -->
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

<!-- ?가 웹에서는 없는 값으로 봄 경계로 보고 // 업로드시에 ?를 _로 replace 해줌 -->