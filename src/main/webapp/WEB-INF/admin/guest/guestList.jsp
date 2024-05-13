<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  <!-- jsp에서 page가 예약어 -->
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
  	th {
  		background-color: #eee;
  		text-align: center;
  	}
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
  	
  	function pageSizeCheck() {
  		let pageSize = document.getElementById("pageSize").value;
  		location.href="${ctp}/guest/GuestList?pag=${pag}&pageSize="+pageSize;
  	}
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2 class="text-center">방 명 록 리 스 트(기본 페이징 처리)</h2>
	<table class="table table-borderless m-0 p-0">
		<tr>
			<!-- <td><a href="#" class="btn btn-primary">관리자</a></td> -->  <!-- 마우스 올려서 주소보려면 a태그쓰기(좌측 하단) -->
			<td><a href="${ctp}/guest/guestInput.jsp" class="btn btn-success">글쓰기</a></td>
			<td class="text-right">
				<c:if test="${pag > 1}">
					<a href="${ctp}/guest/GuestList?pag=1&pageSize=${pageSize}" title="첫페이지">◁◁</a>  <!-- 같은 위치 이므로 ${ctp}를 안적어도 되긴함 -->
					<a href="${ctp}/guest/GuestList?pag=${pag-1}&pageSize=${pageSize}" title="이전페이지">◀</a>  <!-- 나중에 폰트어썸 버튼처리 -->
				</c:if>
				${pag}/${totPage}
				<c:if test="${pag < totPage}">
					<a href="${ctp}/guest/GuestList?pag=${pag+1}&pageSize=${pageSize}" title="다음페이지">▶</a>
					<a href="${ctp}/guest/GuestList?pag=${totPage}&pageSize=${pageSize}" title="마지막페이지">▷▷</a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="text-right">
				<select id="pageSize" onchange="pageSizeCheck()">
					<option <c:if test="${pageSize == 2}">selected</c:if>>2</option>
					<option <c:if test="${pageSize == 3}">selected</c:if>>3</option>
					<option <c:if test="${pageSize == 5}">selected</c:if>>5</option>
					<option <c:if test="${pageSize == 10}">selected</c:if>>10</option>
				</select>
			</td>
		</tr>
	</table>
	<%-- <c:set var="no" value="${fn:length(vos)}" /> --%>
	<c:set var="curScrStartNo" value="${curScrStartNo}" />
  <c:forEach var="vo" items="${vos}" varStatus="st">
	  <table class="table table-borderless m-0 p-0">
	  	<tr>
	  		<td>
	  			번호 : ${curScrStartNo}
	  			<%-- 번호 : ${vo.idx} --%>  <!-- st.count로 해도됨, 나중에 올라온게 앞에서 보고 싶음 // vos.size로 => 이건 서블릿 명령(fn 써야함) -->
	  			<%-- <c:if test="${sMid == 'admin' || sName == vo.name}"><a href="javascript:delCheck(${vo.idx})" class="btn btn-danger btn-sm">삭제</a></c:if>  <!-- onclick이 아니고 a태그일때 함수 부를떄 --> --%> <!-- $ {!empty sMid} -->
	  			<c:if test="${sAdmin == 'OK' || sName == vo.name}"><a href="javascript:delCheck(${vo.idx})" class="btn btn-danger btn-sm">삭제</a></c:if>  <!-- LoginOk에서 넘김 / 이렇게 하면 관리자 id도 공개가 안됨 -->
	  		</td>  <!-- vo가 아니라 vos로 넘어옴(반복문으로 전체를 돌려야 한다) -->
	  		<td class="text-right">방문IP : ${vo.hostIp}</td>
	  	</tr>
	  </table>
	  <table class="table table-bordered">
	  	<tr>
	  		<th>성명</th>
	  		<td>${vo.name}</td>
	  		<th>방문일자</th>
	  		<td>${fn:substring(vo.visitDate,0,19)}</td>
	  	</tr>
	  	<tr>
	  		<th>메일주소</th>
	  		<td colspan="3">
	  			<%-- <c:if test="${vo.email == '' || vo.email == null}">- 없음 -</c:if>
	  			<c:if test="${vo.email != '' && vo.email != null}">${vo.email}</c:if>	--%>  <!-- 배타적 -->
	  			<c:if test="${empty vo.email || fn:length(vo.email)<6 || fn:indexOf(vo.email, '@')==-1 || fn:indexOf(vo.email, '.')==-1}">- 없음 -</c:if>  <!-- 위와 같음 empty 명령어 -->
	  			<c:if test="${!empty vo.email && fn:length(vo.email)>=6 && fn:indexOf(vo.email, '@')!=-1 && fn:indexOf(vo.email, '.')!=-1}">${vo.email}</c:if>
	  			<%-- ${vo.email} --%>
	  		</td>
	  	</tr>
	  	<tr>
	  		<th>홈페이지</th>
	  		<td colspan="3">
	  			<c:if test="${empty vo.homePage || fn:length(vo.homePage)<10 || fn:indexOf(vo.homePage, '.')==-1}">- 없음 -</c:if>  <!-- http://가 있다는 가정 하에 -->
	  			<c:if test="${!empty vo.homePage && fn:length(vo.homePage)>=10 && fn:indexOf(vo.homePage, '.')!=-1}"><a href='${vo.homePage}' target='_blank'>${vo.homePage}</a></c:if>
	  			<%-- ${vo.homePage} --%>
	  		</td>
	  	</tr>
	  	<tr>
	  		<th>방문소감</th>
	  		<td colspan="3">${fn:replace(vo.content,newLine,"<br/>")}</td>
	  	</tr>
	  </table>
  	<br/>
  	<c:set var="curScrStartNo" value="${curScrStartNo - 1}" />
  </c:forEach>
	<br/>
	
<!-- 블록페이지 시작 -->  <!-- 0블록: 1/2/3 -->
<div class="text-center">

	<c:if test="${pag > 1}"><a href="${ctp}/guest/GuestList?pag=1&pageSize=${pageSize}">첫페이지</a></c:if>
	<c:if test="${curBlock > 0}"><a href="${ctp}/guest/GuestList?pag=${(curBlock*blockSize+1)-blockSize}&pageSize=${pageSize}">이전블록</a></c:if>  <!-- (curBlock-1)*blockSize +1 -->
	<c:forEach var="i" begin="${(curBlock*blockSize)+1}" end="${(curBlock*blockSize)+blockSize}" varStatus="st">  <!-- 처음이니까 curBlock => 0블록 -->
		<c:if test="${i <= totPage && i == pag}"><a href="${ctp}/guest/GuestList?pag=${i}&pageSize=${pageSize}"><font color="red"><b>${i}</b></font></a></c:if>
		<c:if test="${i <= totPage && i != pag}"><a href="${ctp}/guest/GuestList?pag=${i}&pageSize=${pageSize}">${i}</a></c:if>
	</c:forEach>
	<c:if test="${curBlock < lastBlock}"><a href="${ctp}/guest/GuestList?pag=${(curBlock+1)*blockSize+1}&pageSize=${pageSize}">다음블록</a></c:if>
	<c:if test="${pag < totPage}"><a href="${ctp}/guest/GuestList?pag=${totPage}&pageSize=${pageSize}">마지막페이지</a></c:if>
	
</div>
<!-- 블록페이지 끝 -->
</div>
<p><br/></p>
</body>
</html>