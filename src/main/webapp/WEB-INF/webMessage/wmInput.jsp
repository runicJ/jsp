<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>wmInput.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
  	'use strict';
  	
  	function fCheck() {
  		let receiveId = myform.receiveId.value;
  		let title = myform.title.value;
  		let content = myform.content.value;
  		
    	if(receiveId.trim() == "") {
    		alert("받는사람 아이디를 입력하세요");
    		myform.receiveId.focus();
    		return false;
    	}
    	else if(title.trim() == "") {
    		alert("메시지 제목을 입력하세요");
    		myform.title.focus();
    		return false;
    	}
    	else if(content.trim() == "") {
    		alert("메시지 내용을 입력하세요");
    		myform.content.focus();
    		return false;
    	}
    	else {
  			myform.submit();
    	}
  	}
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <form name="myform" method="post" action="WmInputOk.wm">
  	<table class="table table-bordered">
  		<tr>
  			<th>보내는 사람</th>
  			<td><input type="text" name="sendId" value="${sMid}" readOnly class="form-control" /></td>
  		</tr>
  		<tr>
  			<th>받는 사람</th>
  			<td>  <!-- 주소록버튼을 클릭해서 아이디를 선택하면 받는사람 입력란에 표시되도록 작성(아이디가 없는 사람은 보낼 수 없게) -->
					<c:if test="${empty param.receiveId}">
            <div class="input-group">
	            <input type="text" name="receiveId" placeholder="받는사람 아이디" class="form-control"/>
              <div class="input-group-append"><input type="button" value="주소록" onclick="#" class="btn btn-info"/></div>
            </div>
          </c:if>
  				<c:if test="${!empty param.receiveId}"><input type="text" name="receiveId" id="receiveId" value="${param.receiveId}" readonly class="form-control" /></c:if>
  			</td>
  		</tr>
  		<tr>
  			<th>메시지 제목</th>
  			<td><input type="text" name="title" id="title" placeholder="메시지 제목을 입력하세요" class="form-control" /></td>
  		</tr>
  		<tr>
  			<th>메시지 내용</th>
  			<td><textarea rows="5" name="content" id="content" placeholder="메시지 내용을 입력하세요" class="form-control"></textarea></td>
  		</tr>
  		<tr>
  			<td colspan="2" class="text-center">
  				<input type="button" value="메시지 전송" onclick="fCheck()" class="btn btn-success mr-2"/>
  				<input type="reset" value="다시쓰기" class="btn btn-warning mr-2"/>
  				<input type="button" value="돌아가기" onclick="history.back()" class="btn btn-danger"/>
  			</td>
  		</tr>
  	</table>
  </form>
</div>
<p><br/></p>
</body>
</html>