<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<%-- <%@ include file="/include/certification.jsp" %> --%>
<%
	/* MVC 패턴 지양
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String mid = request.getParameter("mid");
	
	pageContext.setAttribute("mid", mid);
	pageContext.setAttribute("name", name);
	*/
%>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>view.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
  	'use strict';
  	
  	function deleteCheck() {
  		let ans = confirm("회원 탈퇴 하시겠습니까?");
  		if(ans) {
  			location.href = "${ctp}/database/LoginDelete?mid=${vo.mid}";  // 아이디 중복체크 했다는 가정 하에
  		}
  	}
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <form name="myform" method="post" action="${ctp}/database/UpdateOk">  <!-- 정규식 등 확인 후에 넘김 -->
    <table class="table table-bordered text-center">
      <tr>
        <td colspan="2"><font size="5">회 원 상 세 정 보</font></td>
      </tr>
      <tr>
        <th>아이디</th>
        <%-- <td><input type="text" name="mid" value="${vo.mid}" readonly class="form-control"/></td> --%>  <!-- mvc로 할때 value="${mid}" -->
        <td class="text-left">${vo.mid}</td> <!-- 어차피 수정 안할꺼니까 그냥 아이디를 뿌려줌 이렇게 써도 된다는 것 -->
      </tr>
      <tr>
        <th>비밀번호</th>
        <td><input type="password" name="pwd" value="${vo.pwd}" required class="form-control"/></td>
      </tr>
      <tr>
        <th>성명</th>
        <td><input type="text" name="name" value="${vo.name}" required class="form-control"/></td>
      </tr>
      <tr>
        <th>나이</th>
        <td><input type="number" name="age" value="${vo.age}" class="form-control"/></td>
      </tr>
      <tr>
        <th>성별</th>
        <td>
        	<input type="radio" name="gender" value="남자" <c:if test="${vo.gender=='남자'}">checked</c:if> /> 남자 &nbsp;&nbsp;
        	<input type="radio" name="gender" value="여자" <c:if test="${vo.gender=='여자'}">checked</c:if> /> 여자
        </td>
      </tr>
      <tr>
        <th>주소</th>
        <td><input type="text" name="address" value="${vo.address}" class="form-control"/></td>
      </tr>
      <tr>
        <td colspan="2">
        	<div class="row">
        		<div class="col text-left">
	          	<input type="submit" value="수정" class="btn btn-success mr-2"/>
	          	<c:choose>
		          	<c:when test="${sMid!=vo.mid}">
		          		<input type="button" value="삭제" onclick="deleteCheck()" class="btn btn-danger mr-2"/>
		          	</c:when>
		          	<c:otherwise>
		          		<input type="button" value="탈퇴" onclick="deleteCheck()" class="btn btn-danger mr-2"/>
		          	</c:otherwise>
	          	</c:choose>
	          </div>
	          <div class="col text-right">
		        	<input type="button" value="돌아가기" onclick="location.href='${ctp}/study/database/LoginList';" class="btn btn-primary mr-4"/>  <!-- 그냥 loginMain.jsp 부르면 껍데기만 옴, 무조건 서블릿 다녀와야 함 -->
		        	<!-- <input type="button" value="돌아가기" onclick="history.back()" class="btn btn-primary mr-4"/> -->  <!-- 무한루프에 빠질 수 있음(중간에 무언가 끼어 있으면(서블릿 같은게 있으면)) // 삭제는 관계없지만, 수정하고 수정한 내용을 볼때 다시 수정하러 -->  <!-- 중간에 메시지 같은걸 보내는 response.redirect 하면 무한루프 -->
	        	</div>
        	</div>
        </td>
      </tr>
    </table>
    <input type="hidden" name="idx" value="${vo.idx}" />  <!-- 통일시키자 form태그 위에 쓰는걸로 -->
  </form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>