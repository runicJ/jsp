<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String mid = request.getParameter("mid");
	String pwd = request.getParameter("pwd");
	
	pageContext.setAttribute("mid", mid);  // 값을 현 페이지에서 저장 => EL 사용 가능
	pageContext.setAttribute("pwd", pwd);  // pageContext라는 서버 저장소에 값을 저장해서 아래 출력한 것
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%-- <%@ include file = "/include/bs4.jsp" %> --%>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  <title>t03Ok.jsp</title>
</head>
<body>
<%-- <%@ include file = "/include/header.jsp" %> --%>
<jsp:include page="/include/header.jsp" />  <!-- 뭔가 처리할 것이 있으면 마감x // 뒤에 더 적을 내용이 없다면 그냥 /쓰고 태그 마감(xml) -->
<%-- <%@ include file = "/include/nav.jsp" %> --%>
<jsp:include page="/include/nav.jsp" />  <!-- 이렇게 action태그로 써도 된다. -->
<p><br/></p>
<div class="container">
  <h2>회원 정보(t03Ok.jsp)</h2>
  <hr/>
  <p>아이디 : ${mid}</p>  <!-- 값을 넘겨야지 여기서 가져올 수 있음 -->
  <p>비밀번호 : ${pwd}</p>  <!-- 파라메타는 ?mid= & pwd= 로 넘긴 것 // getParameter로 받아야함 -->
  <hr/>
  <p><a href="t03.jsp" class="btn btn-warning">돌아가기</a></p>
<!-- dispacher.forward()와 같음 // 주소와 화면이 일치하지 않음 => jsp에서는 이렇게 사용 // 첫번째 화면 주소만 나옴 -->
<!-- 값 // 넘겨서 처리할 변수명 // 여기서 처리 안할거니까 -->
<%if(mid.substring(mid.length()-1).equals("I")) { %>
	<jsp:forward page="t03ResI.jsp">
	  <jsp:param value="${mid}" name="mid"/>
	  <jsp:param value="${pwd}" name="pwd"/>
	</jsp:forward>
<%} else if(mid.substring(mid.length()-1).equals("J")) { %>
	<jsp:forward page="t03ResJ.jsp">
		<jsp:param value="${mid}" name="mid"/>
		<jsp:param value="${pwd}" name="pwd"/>
	</jsp:forward>
<%} else if(mid.substring(mid.length()-1).equals("C")) { %>
	<jsp:forward page="t03ResC.jsp">
		<jsp:param value="${mid}" name="mid"/>
		<jsp:param value="${pwd}" name="pwd"/>
	</jsp:forward>
<%} else if(mid.substring(mid.length()-1).equals("S")) { %>
	<jsp:forward page="t03ResS.jsp">
	  <jsp:param value="${mid}" name="mid"/>
	  <jsp:param value="${pwd}" name="pwd"/>
	</jsp:forward>
<%} %>

</div>
<p><br/></p>
<%-- <%@ include file = "/include/footer.jsp" %> --%>
<jsp:include page="/include/footer.jsp" />
</body>
</html>

<!-- 부서 처리를 보안상의 이유로 t03Ok.jsp(주소에 떠 있으니까)에서 하지 않고 값을 그대로 가져감 // 이 화면에 머무르지 않고 지나감(지금은 머물러 있음) -->