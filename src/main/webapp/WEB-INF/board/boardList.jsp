<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>boardList.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
  	'use strict';
  	
  	function pageSizeCheck() {  // 글 상세 보기 하고 '돌아가기'버튼 누르면 해당 페이지로 돌아가지 않음
  		let pageSize = $("#pageSize").val();
  		location.href = "BoardList.bo?pageSize="+pageSize;  // javaScript 함수니까 +pageSize
  	}
  	
  	function modalCheck(hostIp, mid, nickName, idx) {
  		$("#myModal #modalHostIp").text(hostIp);  // .html(html방식으로 변환해서 출력) / .val(form태그의 값을 출력)
  		$("#myModal #modalMid").text(mid);
  		$("#myModal #modalNickName").text(nickName);
  		$("#myModal #modalIdx").text(idx);
  	}
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <table class="table table-borderless m-0 p-0">
  	<tr>
  		<td colspan="2"><h2 class="text-center">게 시 판 리 스 트</h2></td>
  	</tr>
  	<tr>
  		<td><c:if test="${sLevel != 1}"><a href="BoardInput.bo" class="btn btn-success btn-sm">글쓰기</a></c:if></td>  <!-- 세션 들어가서 준회원이면 안보이게 준회원은 읽을 수만 있게 -->
  		<td class="text-right">
  			<select name="pageSize" id="pageSize" onchange="pageSizeCheck()">  <!-- ajax로 할 필요 없음 -->
  				<option ${pageSize==5 ? "selected" : ""}>5</option>
  				<option ${pageSize==10 ? "selected" : ""}>10</option>
  				<option ${pageSize==15 ? "selected" : ""}>15</option>
  				<option ${pageSize==20 ? "selected" : ""}>20</option>
  				<option ${pageSize==30 ? "selected" : ""}>30</option>
  			</select>
  		</td>
  	</tr>
  </table>
  <table class="table table-hover m-0 p-0 text-center">
  	<tr class="table-dark text-dark">
  		<th>글번호</th>
  		<th>글제목</th>
  		<th>글쓴이</th>
  		<th>작성일</th>
  		<th>조회수(좋아요)</th>
  	</tr>
  	<%-- <c:set var="curScrStartNo" value="${curScrStartNo}" /> --%>
  	<c:forEach var="vo" items="${vos}" varStatus="st">
  		<c:if test="${vo.openSw == 'OK' && (vo.complaint == 'NO' || sNickName == vo.nickName || sLevel == 0)}">
		    <tr>
		      <td>${fn:length(vos)-st.index}</td>
		      <td class="text-left">
		        <a href="BoardContent.bo?idx=${vo.idx}&pag=${pag}&pageSize=${pageSize}">${vo.title}</a>  <!-- 지금까지 idx만 넘겼지만, 페이지 수, 페이지 사이즈, 검색필드, 검색어 같이 넘겨야함 --> <!-- 확장자 쓰면 좋은점 ctp 안써도 됨 -->
		        <c:if test="${vo.hour_diff <= 24}"><img src="${ctp}/images/new.gif" /></c:if>  <!-- 이미지 무조건 절대경로 // 시작은 webapp -->
		      </td>
		      <td>
		      	${vo.nickName}
		      	<c:if test="${sLevel == 0}">
		      		<a href="#" onclick="modalCheck('${vo.hostIp}','${vo.mid}','${vo.nickName}','${vo.idx}')" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#myModal">모달출력</a>  <!-- idx는 숫자니까 오류가 날 수 있음(타입때문에) 앞에는 문자니까 ''했음, js는 형변환이 자유로움(그냥 무조건 '' 붙여주자) --> <!-- 현재창에서 모달로 띄울 것이기 때문에 # --> <!-- data-toggle은 예약어 target은 id -->
		      	</c:if>
		      </td>
		      <td>
		        <!-- 1일(24시간) 이내는 시간만 표시(10:43), 이후는 날짜와 시간을 표시 : 2024-05-14 10:43 -->
		        <!-- 단, 24시간안에 만족하는 자료에 대해서는, 날짜가 '오늘날짜'만 시간으로 표시하고, 어제날짜는 '날짜시간'으로 표시하시오 // 이거 아님 -->
		        ${vo.date_diff == 0 ? fn:substring(vo.wDate,11,19) : fn:substring(vo.wDate,0,16)}  <!-- < c : if test = " $ { vo.date_diff == 0 } "> 쓰면 또 안에서 함수를 써야해서 삼항연산자로 -->
		      </td>
		      <td>${vo.readNum}(${vo.good})</td>
		    </tr>
	  	</c:if>
	  	<%-- <c:set var="curScrStartNo" value="${curScrStartNo - 1}" /> --%>
	  </c:forEach>
  	<tr><td colspan="5" class="m-0 p-0"></td></tr>
  </table>
  <br>
  <!-- 블록페이지 시작 -->  <!-- 0블록: 1/2/3 -->
	<div class="text-center">
		<ul class="pagination justify-content-center" style="margin:20px 0">
			<c:if test="${pag > 1}"><li class="page-item"><a class="page-link text-secondary" href="${ctp}/BoardList.bo?pag=1&pageSize=${pageSize}">첫페이지</a></li></c:if>
			<c:if test="${curBlock > 0}"><li class="page-item"><a class="page-link text-secondary" href="${ctp}/BoardList.bo?pag=${(curBlock*blockSize+1)-blockSize}&pageSize=${pageSize}">이전블록</a></li></c:if>  <!-- (curBlock-1)*blockSize +1 -->
			<c:forEach var="i" begin="${(curBlock*blockSize)+1}" end="${(curBlock*blockSize)+blockSize}" varStatus="st">  <!-- 처음이니까 curBlock => 0블록 -->
				<c:if test="${i <= totPage && i == pag}"><li class="page-item active"><a class="page-link bg-secondary border-secondary" href="${ctp}/BoardList.bo?pag=${i}&pageSize=${pageSize}">${i}</a></li></c:if>
				<c:if test="${i <= totPage && i != pag}"><li class="page-item"><a class="page-link text-secondary" href="${ctp}/BoardList.bo?pag=${i}&pageSize=${pageSize}">${i}</a></li></c:if>
			</c:forEach>
			<c:if test="${curBlock < lastBlock}"><li class="page-item"><a class="page-link text-secondary" href="${ctp}/BoardList.bo?pag=${(curBlock+1)*blockSize+1}&pageSize=${pageSize}">다음블록</a></li></c:if>
			<c:if test="${pag < totPage}"><li class="page-item"><a class="page-link text-secondary" href="${ctp}/BoardList.bo?pag=${totPage}&pageSize=${pageSize}">마지막페이지</a></li></c:if>
		</ul>
	</div>
	<!-- 블록페이지 끝 -->
</div>
<p><br/></p>
  <!-- The Modal -->
  <div class="modal fade" id="myModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Modal Heading</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
      		고유번호 : <span id="modalIdx"></span><br>
      		ip : <span id="modalHostIp"></span><br>
      		아이디 : <span id="modalMid"></span><br>
      		닉네임 : <span id="modalNickName"></span><br>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
<jsp:include page="/include/footer.jsp" />
</body>
</html>