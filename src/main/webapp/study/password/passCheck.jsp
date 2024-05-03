<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String msg = request.getParameter("msg")==null ? "" : request.getParameter("msg");
	pageContext.setAttribute("msg", msg);
%>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>passCheck.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
  	'use strict';
  	
  	if('${msg}' == 'OK') alert("전송완료! 콘솔창을 확인하세요.");
  	
  	function fCheck(idx) {
  		let pwd = myform.pwd.value;
  		if(pwd.trim() == "") {
  			alert("비밀번호를 입력하세요");
  			myform.pwd.focus();
  		}
  		else {
 				myform.idx.value = idx;  // 뒤에 idx는 fCheck에 담아져 온 idx  // 1번이 넘어오면 1을 가지고 넘어감
  			myform.action = "${ctp}/study/password/Passcheck";  // ?idx=${idx} 하면 안감(get방식이라서 밑에 form은 post // hidden으로 보내자)
  			myform.submit();
  		}
  	}
  </script>
</head>
<body style="background-image:linear-gradient(to bottom right, #052430, #e2acd5);">
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
	<h2>비밀번호 암호화 연습</h2>
	<pre>
		암호화하여 솔트(salt)는 데이터, 비밀번호, 통과 암호를 해서 처리하는 단방향 함수의 추가 입력으로 사용되는 랜덤 데이터이다.
		솔트틑 스토리지에서 비밀번호를 보호하기 위해 사용된다.
		솔트는 레인보우 테이블(rainbow table : 해시테이블)과 같은 미리 계산된 테이블을 사용하는 공격을 방어한다.
	</pre>
	<p>(비밀번호를 10자 이내로 입력하세요)</p>
  <form name="myform" method="post">
    <table class="table table-bordered text-center">
      <tr>
        <td colspan="2"><font size="5">로 그 인</font></td>
      </tr>
      <tr>
        <th>아이디</th>
        <td><input type="text" name="mid" value="${sMid}" autofocus required class="form-control"/></td>
      </tr>
      <tr>
        <th>비밀번호</th>
        <td><input type="password" name="pwd" value="1234" required class="form-control"/></td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="button" value="숫자비밀번호" onclick="fCheck(1)" class="btn btn-success mr-2"/>
          <input type="button" value="문자비밀번호" onclick="fCheck(2)" class="btn btn-success mr-2"/>
        </td>
      </tr>
    </table>
    <input type="hidden" name="idx" />
  </form>
  <br/>
  <div>비밀번호를 전송 후 콜솔창에서 암호화된 비밀번호를 확인하세요.</div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>