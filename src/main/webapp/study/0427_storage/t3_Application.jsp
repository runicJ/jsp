<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/certification.jsp" %>
<%
	String sId = session.getId();
	pageContext.setAttribute("sId", sId);
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%@ include file = "/include/bs4.jsp" %>
  <title>t3_Application.jsp</title>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2>Application 연습 메인 메뉴</h2>
  <hr/>
  <form name="myform" method="post" action="t3_ApplicationSave.jsp">  <!-- 앞으론 서블릿으로(지금은 jsp로만 했음) -->
    <table class="table table-bordered text-center">
      <tr>
        <td colspan="2"><font size="5">로 그 인</font></td>
      </tr>
      <tr>
        <th>아이디</th>
        <td><input type="text" name="mid" value="${aMid}" autofocus required class="form-control"/></td>
      </tr>
      <tr>
        <th>닉네임</th>
        <td><input type="text" name="nickName" value="${aNickName}" required class="form-control"/></td>
      </tr>
      <tr>
        <th>성명</th>
        <td><input type="text" name="name" value="${aName}" required class="form-control"/></td>
      </tr>
      <tr>
        <td colspan="2">
			  	<button type="submit" class="btn btn-success mr-3 btn-sm">어플리케이션저장</button>  <!-- 더 작은 버튼 뱃지 -->
			  	<a href="t3_ApplicationCheck.jsp" class="btn btn-primary mr-3 btn-sm">어플리케이션확인</a>
			  	<a href="t3_ApplicationDelete.jsp" class="btn btn-danger mr-3 btn-sm">부분어플리케이션삭제</a>  <!-- 전체 어플리케이션 삭제 불가(Server단) -->
        </td>
      </tr>
    </table>
  </form>
  <hr/>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>

<!--
	ctrl + f11 그냥 실행되면 경로가 노출됨(MVC의 Controller 이용해서 보안) 
	session의 라이프 사이클 => 브라우저의 시작과 끝이 세션의 생명주기(강제로 종료하기 전까지는)
	167807CEE02DBDDBEEF589507E05244E
	167807CEE02DBDDBEEF589507E05244E  // 같은 브라우저에서는 같은 세션(기존 세션 종료하지 않고)
	9ABAC74E4E93297DC435D0879AA40D24  // edge(세션은 브라우저에 따라서)
	session은 작업하는 동안에만 살아있다(cookies랑 같이 써야함) // 브라우저 종료하면 값이 저장 안됨
-->