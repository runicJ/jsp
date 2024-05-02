<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/certification.jsp" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>lifeCycle1.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <form name="myform" method="post" action="${ctp}/j0430/LifeCycle1Ok">
    <table class="table table-bordered text-center">
      <tr>
        <th>제목</th>
        <td><input type="text" name="mid" value="Servlet 메소드 생명주기" required class="form-control"/></td>
      </tr>
      <tr>
        <th>내용</th>
        <td><textarea name="content" rows="6" class="form-control">서블릿 메소드 생명주기 입니다.</textarea></td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="submit" value="전송" class="btn btn-success mr-2"/>
        </td>
      </tr>
    </table>
  </form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>