<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>calendar1.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <style>
  	#td1,#td8,#td15,#td22,#td29,#td36 {color:red} /* 36까지 해줘야함 공백포함 */
  	#td7,#td14,#td21,#td28,#td35 {color:blue}
	  
  	.today {
  		background-color: pink;
  		color: #fff;
  		font-weight: bolder;
  	}
  </style>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <div class="text-center">
  	<button type="button" onclick="location.href='Calendar1.st?yy=${yy-1}&mm=${mm}';" class="btn btn-secondary btn-sm" title="이전년도">◁</button>  <!-- 값을 넘겨주고 자신의 위치로 -->
  	<button type="button" onclick="location.href='Calendar1.st?yy=${yy}&mm=${mm-1}';" class="btn btn-info btn-sm" title="이전월">◀</button>
  	<font size="5">${yy}년 ${mm+1}월</font>
  	<button type="button" onclick="location.href='Calendar1.st?yy=${yy}&mm=${mm+1}';" class="btn btn-info btn-sm" title="다음월">▶</button>
  	<button type="button" onclick="location.href='Calendar1.st?yy=${yy+1}&mm=${mm}';" class="btn btn-secondary btn-sm" title="다음년도">▷</button>
  	<button type="button" onclick="location.href='Calendar1.st';" class="btn btn-success btn-sm" title="오늘날짜"><i class="fa-solid fa-house"></i></button>
  </div>
  <br>
  <div class="text-center">
  	<table class="table table-bordered" style="height:450px">  <!-- 크기 따로 안주면 전체의 90퍼 -->
  		<tr class="table-dark text-dark">
  			<th style="width:14%;color:red;vertical-align:middle;">일</th>
  			<th style="width:14%;vertical-align:middle;">월</th>
  			<th style="width:14%;vertical-align:middle;">화</th>
  			<th style="width:14%;vertical-align:middle;">수</th>
  			<th style="width:14%;vertical-align:middle;">목</th>
  			<th style="width:14%;vertical-align:middle;">금</th>
  			<th style="width:14%;color:blue;vertical-align:middle;">토</th>
  		</tr>
  		<tr>
  			<!-- 시작일 이전을 공백으로 처리한다.(해당 '년/월'의 1일이 수요일이면 startWeek가 4가 오기에 3칸을 공백처리한다.(24.5 기준) -->
  			<c:forEach begin="1" end="${startWeek - 1}">  <!-- 4가나오면 하나를 빼줘야 공백크기가 나옴 -->
  				<td>&nbsp;</td>
  			</c:forEach>
  			<!-- 해당월의 1일을 startWeek위치부터 출력한다.(날짜는 1씩 증가시켜주고, 7칸이 될때 줄바꿈 처리한다.) -->
  			<c:set var="cell" value="${startWeek}" />  <!-- 4부터 출발해야함 -->
  			<c:forEach begin="1" end="${lastDay}" varStatus="st">
  				<c:set var="todaySw" value="${toYear==yy && toMonth==mm && toDay==st.count ? 1 : 0}" />  <!-- 셀의 번호와 비교해서 오늘 날짜와 일치하면 오늘 날짜  // 위에 class로 사용하려고 변수로 뺌 -->  				
  				<td id="td${cell}" ${todaySw==1 ? 'class=today' : ''}>${st.count}</td>  <!-- 7개 되면 줄바꿈 // 오늘 날짜가 맞으면 class에 today로 줌 -->
  				<c:if test="${cell % 7 == 0}"><tr></tr></c:if>  <!-- st.count 쓰면 좋은데 이미 위에서 써버림(7개가 되면 넘김) -->
  				<c:set var="cell" value="${cell + 1}" />
  			</c:forEach>
  		</tr>
  	</table>
  </div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>