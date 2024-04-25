<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>t02_2.jsp</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <script>
    'use strict';
    
    function fCheck() {
	    let name = document.getElementById("name");
	    let age = document.getElementById("age");
	    
	    if(name.value.trim() == "") {
	    	alert("이름을 입력하세요");
	    	document.getElementById("name").focus();
	    	return false;  // false 안써도 됨
	    }
	    else if(age.value.trim() == "" || age.value < 20) {
	    	alert("나이를 체크해주세요");
	    	document.getElementById("age").focus();
	    	return;
	    }
	    else {
	    	// myform.submit();
	    	location.href="t02Ok.jsp?name="+name.value+"&age="+age.value;  // get 방식 // 같은 위치에 있어서 경로 안줘도 됨(서블릿에 있으면 당연히 줘야함)
	    	// location.href="t02_2Ok.jsp?name=${name.value}&age=${age.value};  // 서버에서는 백팃 사용 불가...
	    }
    }
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>값 전송 연습(Get/Post)</h2>
  <!-- <form name="myform" method="post" action="t02Ok.jsp"> -->
  <!-- <form name="myform" method="get" action="t02Ok.jsp"> -->
    <div>성명
      <input type="text" name="name" id="name" value="홍길동" class="form-control mb-3" autofocus required />  <!-- 프론트의 체크는 정규식으로 // required하면 공백체크 안해도 됨 -->
    </div>
    <div>나이
      <input type="number" name="age" id="age" value="20" class="form-control mb-3" />
    </div>
    <div>
      <input type="submit" value="바로전송(submit)" class="btn btn-success mr-3" />
      <input type="button" value="체크후전송(submit)" onclick="fCheck()" class="btn btn-primary" />
    </div>
  <!-- </form> -->
</div>
<p><br/></p>
</body>
</html>