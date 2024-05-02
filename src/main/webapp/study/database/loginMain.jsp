<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/certification.jsp" %>  <!-- LoginOk에서 session을 담아야 인증이 됨 -->
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>loginMain.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
  	'use strict';
  	
  	function nameSearch() {
  		let name = document.getElementById("name").value;  // 입력된 내용이 있는가(form태그 없으면 아이디로 조회 => form태그 없으면 post방식 못씀)
  		if(name.trim() == "") {
  			alert("검색할 성명을 입력하세요!");
  			document.getElementById("name").focus();
  			// return false;  // 이거 쓰기 싫으면 else로 뺌
  		}
  		else {
  			location.href = "${ctp}/database/LoginSearch?name="+name;  // 쿼리스트링 이용해서 get방식으로 보냄(동기식)
  		}
  	}
  	
  	function selectSort() {
  		let sort = document.getElementById("sort").value;
  		if(sort.trim() == "") {
  			alert("정렬 항목을 선택하세요.");
  			document.getElementById("sort").focus();
  		}
  		else {
  			location.href = "${ctp}/database/SortList?sort="+sort;
  		}
  	}
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2 class="text-center">회 원 메 인 방</h2>
  <hr/>
  <div>
		<input type="button" value="전체조회" onclick="location.href='${ctp}/study/database/LoginList';" class="btn btn-success btn-sm mb-2 float-left" />  <!-- location.href(이름) -->
  	<select id="sort" name="sort" onchange="selectSort(this.value)" class="float-right">
		  <option value="">선택</option>
		  <option value="name">이름순</option>
		  <option value="age">나이순</option>
		  <option value="idx">번호순</option>
		</select>
  </div>
  <table class="table table-hover text-center">
  	<tr class="table-dark text-dark">
  		<th>번호</th>
  		<th>아이디</th>
  		<th>성명</th>
  		<th>나이</th>
  		<th>성별</th>
  		<th>주소</th>
  	</tr>
  	<c:forEach var="vo" items="${vos}" varStatus="st">
  		<tr>
  			<td>${vo.idx}</td>
  			<td>${vo.mid}</td>
  			<%-- <td><a href="view.jsp?mid=${vo.mid}&name=${vo.name}">${vo.name}</a></td> --%>  <!-- 무조건 동기식으로(비동기식x) --> <!-- 서블릿을 부르는게 아니면 jsp 사용시(쿼리스트링 사용) => mvc방식 -->
  			<td><a href="${ctp}/study/database/LoginView?idx=${vo.idx}">${vo.name}</a></td>
  			<td>${vo.age}</td>
  			<td>${vo.gender}</td>
  			<td>${vo.address}</td>
  		</tr>
  	</c:forEach>
  	<tr><td colspan="6" class="m-0 p-0"></td></tr>
  </table>
  <hr/><br/>
  <div class="input-group">
  	<div class="input-group-prepend"><span class="input-group-text">성 명</span></div>
  	<input type="text" name="name" id="name" class="form-control"/>
  	<button type="button" onclick="nameSearch()" class="btn btn-danger">개별조회</button>  <!-- a -> a-s -> a 지금은 동기식(서블릿 오감(주소창)) // 원래는 비동기식으로 해야함(여기 창에 머무르고 검색해서 위에 자료를 뿌림)=>ajax배워야함 -->
  </div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>
<!-- getIdx() => get(x) ()(x) -->

<!-- 이름순, 나이순, 번호순 콤보상자 // onchange -->
<!-- css파일을 import 걸면 잘 적용이 되지 않으므로 jsp파일로 만들고 include 하면 그나마 잘 적용할 수 있음 -->