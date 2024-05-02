<%@ page import="study.database.LoginVO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="study.database.LoginDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  <!-- page : 지시자 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<%
	int sCount = session.getAttribute("sCount")==null ? 0 : (int) session.getAttribute("sCount");
	sCount++;
	session.setAttribute("sCount", sCount);
	
  //Main main = new Main()
  LoginDAO dao = new LoginDAO();
	ArrayList<LoginVO> recentVos = dao.getRecentFiveMember();
	pageContext.setAttribute("recentVos", recentVos);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Greenhouse</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <%@ include file = "../../include/bs4.jsp" %>
  <style>
  .fakeimg {
    height: 200px;
    background: #aaa;
  }
  </style>
</head>
<body>

<%@ include file = "../../include/header.jsp" %>

<!-- 메뉴바(Nav) -->
<%@ include file = "../../include/nav.jsp" %>  <!-- 지시자 : page(속성), include라는 지시자(문서를 포함) // file = "nav.jsp" 이렇게 하면 현재 위치에 없어서 에러 // 상대경로 지정 -->

<div class="container" style="margin-top:30px">
  <div class="row">
    <div class="col-sm-4">
      <c:choose>
	      <c:when test="${sMid != null}">
    			<h5>마지막 방문일자 : ${cLast}</h5>
	      	<h3>${sName}(${sMid})</h3>  <!-- session은 EL로 -->
		      <h4>가입한 회원</h4>
		      <table class="table table-hover text-center">
		      	<tr class="table-dark text-dark">
		      		<th>번호</th>
		      		<th>아이디</th>
		      		<th>성명</th>
		      	</tr>
<%-- 		      	<c:forEach var="vo" items="${vos}" varStatus="st">
		      		<tr>
		      			<td>${vo.idx}</td>
		      			<td>${vo.mid}</td>
		      			<td>${vo.name}</td>
		      		</tr>
		      	</c:forEach> --%>
		      	<c:forEach var="vo" items="${recentVos}">
		          <tr>
		            <td>${vo.mid}</td>
		            <td>${vo.name}</td>
		            <td>${vo.address}</td>
		          </tr>
	        	</c:forEach>
		      	<tr><td colspan="3" class="m-0 p-0"></td></tr>
		      </table>
		      </c:when>
	      <c:otherwise>
	      	<h2>Welcome!(guest)</h2>
	      	<div>
	      		<img src="${ctp}/images/whiteCat.jpg" class="rounded" width="360px" />
	      	</div>
	      </c:otherwise>
      </c:choose>
      <p>Some text about me in culpa qui officia deserunt mollit anim..</p>
      <h3>Some Links</h3>
      <p>Lorem ipsum dolor sit ame.</p>
      <ul class="nav nav-pills flex-column">
        <li class="nav-item">
          <a class="nav-link active" href="#">Active</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#">Disabled</a>
        </li>
      </ul>
      <hr class="d-sm-none">
    </div>
    <div class="col-sm-8">
      <h2>TITLE HEADING(view: ${sCount})</h2>
      <h5>Title description, Dec 7, 2017</h5>
      <div class="fakeimg">Fake Image</div>
      <p>Some text..</p>
      <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
      <br>
      <h2>TITLE HEADING</h2>
      <h5>Title description, Sep 2, 2017</h5>
      <div class="fakeimg">Fake Image</div>
      <p>Some text..</p>
      <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
    </div>
  </div>
</div>

<!-- 하단(footer) -->
<%@ include file = "../../include/footer.jsp" %>

</body>
</html>