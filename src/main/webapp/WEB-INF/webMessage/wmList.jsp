<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>wmList.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
  	'use strict';
  	
  	setTimeout("location.reload()",1000*10);  // (익명함수(내용이 많으면 function(){}), 10초에 한번씩 읽어라)
  	
  	function msgDel(idx){
  		let ans = confirm("선택하신 메시지를 삭제하시겠습니까?");
  		if(!ans) return false;
  		
  		let query = {
  				idx : idx,
  				mFlag : 13  // 보낸 메시지  // 갔다 돌아오는 것 처리
  		}
  		
  		$.ajax({
  			url : "WmDeleteOne.wm",
  			type : "post",
  			data : query,
  			success:function(res) {
  				if(res != "0") {
  					alert("메시지가 삭제되었습니다.");
  					location.reload();
  				}
  				else alert("메시지 삭제 실패~~");
  			},
  			error:function(){
  				alert("전송 오류!");
  			}
  		});
  	}
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <table class="table table-hover">  <!-- list 형식으로 꺼내므로 hover -->
  	<tr class="table-dark text-dark">
  		<th>번호</th>
  		<th>제목</th>
  		<th>
  			<c:if test="${mSw==1 || mSw==2 || mSw==5}">보낸사람</c:if>  <!-- 받은 메시지이므로 보낸 사람, 보낸 날짜 -->
  			<c:if test="${mSw==3 || mSw==4}">받은사람</c:if>
  		</th>
  		<th>
  			<c:if test="${mSw==1 || mSw==2 || mSw==5}">보낸날짜</c:if>  <!-- 설계를 잘 해놔야 함 보낸, 받은 확인 -->
  			<c:if test="${mSw==3 || mSw==4}">받은날짜</c:if>
  		</th>
  		<c:forEach var="vo" items="${vos}" varStatus="st">
  			<tr>
  				<td>${vo.idx}</td>
  				<td class="text-left">
  					<c:if test="${mSw!=4}"><a href="WebMessage.wm?mSw=6&idx=${vo.idx}&mFlag=${mFlag}&pag=${pag}&pageSize=${pageSize}">${vo.title}</a></c:if>  <!-- mFlag 부분 없어도 됨 -->
  					<c:if test="${mSw==4}">${vo.title}</c:if>  <!-- mSw 4번 수신확인인 경우 내용 확인 안할 것 -->
  					<c:if test="${vo.receiveSw=='n'}"><img src="${ctp}/images/new.gif" /></c:if>
  					<c:if test="${mSw==3}">  <!-- 내용보기에서 지우는게 아니라 보낸 메시지 확인에서 바로지우기 -->
  						<a href="javascript:msgDel(${vo.idx})" class="badge badge-danger">삭제</a>  <!-- 휴지통 보내지 않고 바로 지우기 -->
  					</c:if>
  				</td>
  				<td>
  					<c:if test="${mSw==1 || mSw==2 || mSw==5 || mSw==6}">${vo.sendId}</c:if>
  					<c:if test="${mSw==3 || mSw==4}">${vo.receiveId}</c:if>
  				</td>
  				<td>
  					<c:if test="${vo.hour_diff < 24}">${fn:substring(vo.receiveDate,11,19)}</c:if>  <!-- 일(day)로 안하고 시간으로 케크 -->
  					<c:if test="${vo.hour_diff >= 24}">${fn:substring(vo.receiveDate,0,16)}</c:if>
  				</td>
  			</tr>
  		</c:forEach>
  		<tr><td colspan="4" class="m-0 p-0"></td></tr>
      <th></th>
    </tr>
  </table>
</div>
<p><br/></p>
	<div class="text-center">
		<ul class="pagination justify-content-center" style="margin:20px 0">
			<c:if test="${pag > 1}"><li class="page-item"><a class="page-link text-secondary" href="WebMessage.wm?mSw=${mSw}&mFlag=${mFlag}&pag=${pag}&pageSize=${pageSize}">첫페이지</a></li></c:if>
			<c:if test="${curBlock > 0}"><li class="page-item"><a class="page-link text-secondary" href="WebMessage.wm?mSw=${mSw}&mFlag=${mFlag}&pag=${(curBlock*blockSize+1)-blockSize}&pageSize=${pageSize}">이전블록</a></li></c:if>  <!-- (curBlock-1)*blockSize +1 -->
			<c:forEach var="i" begin="${(curBlock*blockSize)+1}" end="${(curBlock*blockSize)+blockSize}" varStatus="st">  <!-- 처음이니까 curBlock => 0블록 -->
				<c:if test="${i <= totPage && i == pag}"><li class="page-item active"><a class="page-link bg-secondary border-secondary" href="WebMessage.wm?mSw=${mSw}&mFlag=${mFlag}&pag=${i}&pageSize=${pageSize}">${i}</a></li></c:if>
				<c:if test="${i <= totPage && i != pag}"><li class="page-item"><a class="page-link text-secondary" href="WebMessage.wm?mSw=${mSw}&mFlag=${mFlag}&pag=${i}&pageSize=${pageSize}">${i}</a></li></c:if>
			</c:forEach>
			<c:if test="${curBlock < lastBlock}"><li class="page-item"><a class="page-link text-secondary" href="WebMessage.wm?mSw=${mSw}&mFlag=${mFlag}&pag=${(curBlock+1)*blockSize+1}&pageSize=${pageSize}">다음블록</a></li></c:if>
			<c:if test="${pag < totPage}"><li class="page-item"><a class="page-link text-secondary" href="WebMessage.wm?mSw=${mSw}&mFlag=${mFlag}&pag=${totPage}&pageSize=${pageSize}">마지막페이지</a></li></c:if>
		</ul>
	</div>
</body>
</html>