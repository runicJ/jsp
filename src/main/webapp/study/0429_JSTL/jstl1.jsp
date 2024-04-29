<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/certification.jsp" %>  <!-- 보안 기억 // 주소창에 복붙해도 로그인 하지 않은 상태에서는 알림창 -->
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%@ include file = "/include/bs4.jsp" %>
  <title>jstl1.jsp</title>
  <style>
  	th {
  		background-color: #ccc;
  		text-align: center;
  	}
  </style>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2>JSTL(Java Standard Tag Library)</h2>
  <table class="table table-bordered">
  	<tr>
  		<th>라이브러리</th>
  		<th>주소(URI)</th>
  		<th>접두어</th>
  		<th>기본문법</th>
  	</tr>
  	<tr>
  		<td>Core</td>
  		<td>http://java.sun.com/jstl/core</td>
  		<td>c</td>  <!-- core 라이브러리를 나타내는 접두어 -->
  		<td>< c : 태그명.... ></td>  <!-- < % % > 없애려고 나옴, < %= % > => $ {} 변수 꺼내는 것 -->
  	</tr>
  	<tr>
  		<td>Formatting</td>
  		<td>http://java.sun.com/jstl/fmt</td>
  		<td>fmt</td>
  		<td>< fmt : 태그명.... ></td>
  	</tr>
  	<tr>
  		<td>Function</td>
  		<td>http://java.sun.com/jstl/function</td>
  		<td>fn</td>  <!-- 예약어 -->
  		<td>$ { fn : 태그명.... }</td>  <!-- function은 EL과 같이 쓰임 -->
  	</tr>
  	<tr>
  		<td>SQL</td>
  		<td>http://java.sun.com/jstl/sql</td>
  		<td>sql</td>  <!-- 예약어 -->
  		<td>< sql : 태그명.... ></td>  <!-- function은 EL과 같이 쓰임 -->
  	</tr>
  </table>
  <hr/>
  <div>
  	앞의 라이브러리를 사용할 경우에는 반드시 상단에 jsp 지시자(1번라인) 중 'taglib'를 이용하여 먼저 선언 후 사용해야 한다.(안쓰는 경우 바로 에러남)
  	태그 라이브러리 사용(jspl)
  </div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>