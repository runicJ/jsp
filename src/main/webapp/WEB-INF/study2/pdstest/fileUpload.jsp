<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>fileUpload.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2>파일 업로드 연습(싱글/멀티파일)</h2>
  <hr>
  <h4>COS라이브러리를 이용한 파일 업로드</h4>
  <div>(http://www.servlets.com/cos/)</div>
  <hr>
  <div class="row text-center">
  	<div class="col"><a href="FileUpload1.st" class="btn btn-success">업로드연습1</a></div>
  	<div class="col"><a href="FileUpload2.st" class="btn btn-primary">업로드연습2</a></div>
  	<div class="col"><a href="FileUpload3.st" class="btn btn-secondary">업로드연습3</a></div>
  	<div class="col"><a href="FileUpload4.st" class="btn btn-info">업로드연습4</a></div>
  </div>
  <hr>
  <div><a href="FileDownload.st" class="btn btn-warning form-control">다운로드로 이동하기</a></div>
  <br><br>
  <hr>
  <h4>자바 서블릿의 Part객체를 이용한 파일 업로드</h4>  <!-- 서블릿에서 제공하는, 스프링에서 제공하는 객체와 비슷한 객체 -->
  <div>(javax.servlet.http.Part)</div>
  <hr>
  <div class="row text-center">
  	<div class="col"><a href="FileUpload5.st" class="btn btn-success">업로드연습5(싱글)</a></div>
  	<div class="col"><a href="FileUpload6.st" class="btn btn-primary">업로드연습6(멀티)</a></div>
  </div>
  <hr>
  <div><a href="FileDownload.st" class="btn btn-warning form-control">다운로드로 이동하기</a></div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>