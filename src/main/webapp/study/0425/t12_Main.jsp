<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% /* MVC 형식 // alt + shift + a 단어 한번에 선택 */
/* 	String vo = request.getParameter("vo");
	System.out.println("vo : " + vo);

	String name = request.getParameter("name");
	String age = request.getParameter("age");
	String gender = request.getParameter("gender");
	String hobby = request.getParameter("hobby");
	String job = request.getParameter("job");
	String mountain = request.getParameter("mountain");
	String content = request.getParameter("content");
	String fileName = request.getParameter("fileName"); */
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>t12_Main.jsp</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		'use strict';
		
		alert('${vo.name}님 회원가입 되었습니다.');  // EL 사용  // 직렬화  // org.apache.jasper.JasperException: javax.el.ELException: Failed to parse the expression <= EL 때문에 생기는 오류
		
		function logout() {
			alert("${vo.name}님 로그아웃 되었습니다.");
			/* location.href = "t12_form.jsp"; */
			/* location.href = "javaclass/study/0425/t12_form.jsp"; */
			<%-- location.href = "<%=request.getContextPath()%>/study/0425/t12_form.jsp"; --%>
			/* location.href = '${request.getContextPath()}/study/0425/t12_form.jsp'; */
			/* location.href = request.getContextPath() + '/study/0425/t12_form.jsp'; */
		}
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>회 원 정 보</h2>
  <hr/>
  <p>${vo.name}</p>
  <p>${vo.age}</p>
  <p>${vo.gender}</p>
  <p>${vo.hobby}</p>
  <p>${vo.job}</p>
  <p>${vo.mountain}</p>
  <p>${vo.content}</p>
  <p>${vo.fileName}</p>
  <hr/>
  <p>
  	<input type="button" value="로그아웃" onclick="logout()" class="btn btn-success"/>
  </p>
</div>
<p><br/></p>
</body>
</html>