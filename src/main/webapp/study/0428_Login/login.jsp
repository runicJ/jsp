<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 로그인창에 아이디 체크 유무에 대한 처리
	// 쿠키를 검색해서 cMid가 있을때 가져와서 아이디입력창에 뿌릴수 있게 한다.
	Cookie[] cookies = request.getCookies();  // 쿠키에 있는게 비어있을 수도 여러개 있을 수도 => 배열로 받음

	if(cookies != null) {
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().equals("cMid")) {  // cookies는 우리는 c로  // cMid는 쿠키 이름
				pageContext.setAttribute("mid", cookies[i].getValue());  // pageContext로 담아야(mid 변수에 / 저장소에 담은 것) EL로 출력 가능
				break;
			}
/* 			if(cookies[i].getName().equals("cLastVisit")) {
				pageContext.setAttribute("last", cookies[i].getValue());
			} */
		}
	}
	Calendar today = Calendar.getInstance();
	int year = today.get(Calendar.YEAR);
	int month = today.get(Calendar.MONTH);
	int date = today.get(Calendar.DAY_OF_MONTH);
	int hour = today.get(Calendar.HOUR);
	int minute = today.get(Calendar.MINUTE);
	int second = today.get(Calendar.SECOND);
	String last = year+"년"+(month+1)+"월"+date+"일"+hour+"시"+minute+"분"+second+"초";
	Cookie cLast = new Cookie("last", last);
	response.addCookie(cLast);
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>login.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
</head>
<body style="background-image:linear-gradient(to bottom right, #052430, #e2acd5);">
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
	<img src="../../images/logo_login.png" style="vertical-align:top;background-position:center;" />
  <form name="myform" method="post" action="${pageContext.request.contextPath}/j0427/LoginOk">
    <table class="table table-bordered text-center">
      <tr>
        <td colspan="2"><font size="5">로 그 인</font></td>
      </tr>
      <tr>
        <th>아이디</th>
        <td><input type="text" name="mid" value="${mid}" autofocus required class="form-control"/></td>
      </tr>
      <tr>
        <th>비밀번호</th>
        <td><input type="password" name="pwd" value="1234" required class="form-control"/></td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="submit" value="로그인" class="btn btn-success mr-2"/>
          <input type="reset" value="다시입력" class="btn btn-warning mr-2"/>
          <input type="button" value="회원가입" onclick="alert('준비중입니다.');" class="btn btn-primary mr-4"/>
	    		<input type="checkbox" name="idSave" checked /> 아이디 저장
        </td>
      </tr>
    </table>
  </form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>