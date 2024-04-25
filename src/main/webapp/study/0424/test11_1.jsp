<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>test11_1.jsp</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>test11_1.jsp</h2>
  <form name="myform" method="post" action="/javaclass/T11_1">
    <div>수
      <input type="number" name="su" value="100" class="form-control mb-3" />  <!-- name에 있는 변수가 서블릿에서 요청한 변수명이어야 함 -->
    </div>
    <p><input type="submit" value="계산" class="btn btn-success" /></p>  <!-- js로 점검 안하고 바로 보낼려면 submit -->
  </form>
</div>
<p><br/></p>
</body>
</html>