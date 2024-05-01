<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <!-- jsp/jstl/로 사용해야함 // prefix="c" 라이브러리를 나타냄(태그에서 예약어로 사용) // tag라이브러리는 지시자로 사용(WEB-INF > lib > 에 파일이 있어야 사용가능) CDN 방식으로 연결 가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<!-- < jsp : jsp action 태그 -->
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2>JSTL(Java Standard Tag Library)</h2>
  <div class="text-right">
  	<button type="button" onclick="location.href='jstl2.jsp';" class="btn btn-success btn-sm">JSTL반복문</button>  <!-- 기본이 button이고 javascript: 안써도 됨 -->
  	<button type="button" onclick="location.href='jstl3_vo.jsp';" class="btn btn-primary btn-sm">JSTL 반복문응용</button>
  	<button type="button" onclick="location.href='jstl4_function.jsp';" class="btn btn-info btn-sm">JSTL 함수</button>
  	<button type="button" onclick="location.href='jstl5_format.jsp';" class="btn btn-info btn-sm">JSTL Formatting</button>
  </div>
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
  <hr/>
  <h2>Core 라이브러리 사용예</h2>
  <pre>
  	<h4>용도 : 변수제어(선언/값(일반적인 내용,객체도 가능)할당/출력/제거), 제어문(조건문, 반복문)</h4>
  	변수선언 : < c : set var="변수명" value="값" />  <!-- JSTL 태그 라이브러리 < c : set(사용하겠다) // /> 사용 안할 경우 < / c : set > -->
  	변수출력 : < c : out value="값/변수/수식" />
  	변수제거 : < c : remove var="변수명" />  <!-- 자주 안씀 그러나 여러개 사용할 때 지우는 경우가 있음 -->
  </pre>
  <hr/>
  su1변수를 선언 후 초기값으로 100을 할당? <c:set var="su1" value="100"></c:set><br/>
  값을 출력1? <c:out value="200"></c:out><br/>
  su1변수값을 출력1? <c:out value="${su1}"/><br/>
  su1변수값을 출력2? ${su1}<br/>
  수식을 출력1? <c:out value="${100 + 200}" /><br/>  <!-- 그냥 수식은 못적음 EL로 써야함 -->
  수식을 출력2? <c:out value="${'100' + 200}" /><br/>
  수식을 출력3? <c:out value="${'100' + '200'}" /><br/>
  <% String name = "홍길동"; %>
  값을 출력2? <%=name %><br/>
  값을 출력3? <c:out value='<%=name %>' /><br/>
  값을 출력4? <c:out value='${name}' /> : 저장소에 저장하지 않은 변수는 값이 출력되지 않는다.<br/>
  <% pageContext.setAttribute("name", name); %>
  값을 출력5? <c:out value='${name}' /> == ${name}<br/>  <!-- 무조건 저장소에 값을 담아야 담아온 값을 쓸 수 있다. // 저장소에 담으면 굳이 out 사용 안해도 되지 -->
  su1변수값을 출력1? ${su1}<br/>
  su1변수를 제거? <c:remove var="su1"/><br/>
  su1변수값을 출력2? ${su1}<br/>
  su1변수에 name변수값을 저장? <c:set var="su1" value="${name}"></c:set><br/>  <!-- 이렇게 많이 사용 // 변수 재선언 해도 관계없음 -->
  su1변수값을 출력3? ${su1}<br/>
  <hr/>
  <h4>JSTL 제어문(조건문(if), 반복문(forEach)) => 주의점 : 조건식의 내용은 EL표기법으로 처리한다.</h4>
  <p>사용법 : < c : if test="$ {조건식}" > 조건식에 따른 처리할 내용 < / c : if ></p>  <!-- java에서 정규식 사용할 때 test 사용했었음 // 나중에 마이바치?에서도 사용 -->
  <p>단점1 : EL조건문에는 Else가 없다.</p>
  <p>단점2 : jstl의 숫자비교는 문자형식으로 비교한다.</p> 
  <p>사용예1 : (su3=300, su4=400)</p>
  <c:set var="su3" value="300" /> 
  <c:set var="su4" value="400" />
  <div>su3 : ${su3} / su4 : ${su4}</div> 
  <div>1. su3 == su4 : <c:if test="${su3 == su4}"> 1.su3와 su4는 같다.</c:if></div>  <!-- 조건식이 참인 경우에 출력 -->
  <div>2. su3 != su4 : <c:if test="${su3 != su4}"> 2.su3와 su4는 같지않다.</c:if></div>
  <div>3. su3 > su4 : <c:if test="${su3 > su4}"> 3.su3가 su4보다 크다.</c:if></div>
  <div>4. su3 < su4 : <c:if test="${su3 < su4}"> 4.su3가 su4보다 작다.</c:if></div>
  <div>5. su3 >= su4 : <c:if test="${su3 >= su4}"> 5.su3가 su4보다 크거나 같다.</c:if></div>
  <div>6. su3 <= su4 : <c:if test="${su3 <= su4}"> 6.su3가 su4보다 작거나 같다.</c:if></div>
  <br/>
  
  <p>사용예2 : (su3=100, su4=40)</p>
  <c:set var="su3" value="100" /> 
  <c:set var="su4" value="40" />
  <div>su3 : ${su3} / su4 : ${su4}</div>
  <div>11. su3 == su4 : <c:if test="${su3 == su4}"> 11.su3와 su4는 같다.</c:if></div>
  <div>12. su3 != su4 : <c:if test="${su3 != su4}"> 12.su3와 su4는 같지않다.</c:if></div>
  <div>13. su3 > su4 : <c:if test="${su3 > su4}"> 13.su3가 su4보다 크다.</c:if></div>
  <div>14. su3 < su4 : <c:if test="${su3 < su4}"> 14.su3가 su4보다 작다.</c:if></div>  <!-- 문자 비교 앞에서부터 비교함 // 1<4 -->
  <div>113. su3 > su4 : <c:if test="${su3+0 > su4+0}"> 113.su3가 su4보다 크다.</c:if></div>
  <div>114. su3 < su4 : <c:if test="${su3+0 < su4+0}"> 114.su3가 su4보다 작다.</c:if></div>  <!-- EL(얘는 무조건 문자비교)에서 숫자 비교하고 싶을때는 +0를 붙여준다. -->
  <div>1113. su3 gt su4 : <c:if test="${su3+0 gt su4+0}"> 1113.su3가 su4보다 크다.</c:if></div>
  <div>1114. su3 lt su4 : <c:if test="${su3+0 lt su4+0}"> 1114.su3가 su4보다 작다.</c:if></div>  <!-- less than  // CDATA // <(xml에서 안먹음 / 여기서도 사용을 지양하는 것으로 뜸) -->
  <div>1115. su3 ge su4 : <c:if test="${su3+0 ge su4+0}"> 1115.su3가 su4보다 크거나 같다.</c:if></div>
  <div>1116. su3 le su4 : <c:if test="${su3+0 le su4+0}"> 1116.su3가 su4보다 작거나 같다.</c:if></div>  <!-- less than equal -->
	<br/>
	
	<div>예제1 : URL에 jumsu를 QueryString방식으로 입력후 학점을 구하시오</div>
	<div>변수명 : 입력 점수(jum), 학점(grade)</div>
	<div>
		<c:set var="jum" value="${param.jumsu}" />
		<c:if test="${jum+0 < 60}"><c:set var="grade" value="F"/></c:if>
		<c:if test="${jum+0 >= 60}"><c:set var="grade" value="D"/></c:if>
		<c:if test="${jum+0 >= 70}"><c:set var="grade" value="C"/></c:if>
		<c:if test="${jum+0 >= 80}"><c:set var="grade" value="B"/></c:if>
		<c:if test="${jum+0 >= 90}"><c:set var="grade" value="A"/></c:if>  <!-- 위치를 바꾸거나 중첩if문 사용(else가 없으므로) // ctrl+space 쓰기 -->
		
		입력받은 점수 : ${jum} 의 학점은 ${grade} 입니다.
	</div>
	<br/>
	
	<h4>다중조건비교 : choose ~ when</h4>
	<pre>
		사용법 : 
		< c : choose >
			< c : when test="조건식1">수행할 내용1< / c : when >
			< c : when test="조건식2">수행할 내용2< / c : when >
			< c : when test="조건식3">수행할 내용3< / c : when >
			< c : when test="조건식4">수행할 내용4< / c : when >
			~~ ~~ ~~
			< c : otherwise > 기타 수행할 내용 < / c : otherwise >
		< / c : choose >
	</pre>
	<div>학점은?
		<c:choose>
			<c:when test="${jum >= 90}">A</c:when>
			<c:when test="${jum >= 80}">B</c:when>
			<c:when test="${jum >= 70}">C</c:when>
			<c:when test="${jum >= 60}">D</c:when>
			<c:otherwise>F</c:otherwise>
		</c:choose>
	</div>
	<br/>
	
	<div>예제2 : URL에 직급코드(code)를 QueryString방식으로 입력후 직급명을 구하시오</div>
	<div>(B:부장, K:과장, D:대리, S:사원) 변수명: 직급코드(kCode), 직급명(kName)</div>
	<div>직급명은?
		<c:set var="kCode" value="${param.code}" />
		<c:choose>
			<c:when test="${kCode=='B'}"><c:set var="kName" value="부장" /></c:when>
			<c:when test="${kCode=='K'}"><c:set var="kName" value="과장" /></c:when>
			<c:when test="${kCode=='D'}"><c:set var="kName" value="대리" /></c:when>
			<c:when test="${kCode=='S'}"><c:set var="kName" value="사원" /></c:when>
			<c:otherwise><c:set var="kName" value="인턴" /></c:otherwise>
		</c:choose>
		직급코드 : ${kCode} / 직급명 : ${kName}
	</div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>