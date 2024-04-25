<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>t12_form.jsp</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <style>
  	* {
  		margin: 0;
  	}
  </style>
</head>
<body>
<p><br/></p>
<div class="container">  <!-- 프론트에서 체크할 수 있는 만큼 해야함 지금은 안한 것 -->
  <h2>회 원 가 입</h2>
  <form class="was-validated" name="myform" method="post" action="<%=request.getContextPath() %>/j0425/T12Ok">  <!-- 단지 url, 파일명이 아님 -->
    <div class="mb-3">성명
      <input type="text" name="name" value="홍길동" class="form-control mb-3" autofocus required />
    </div>
    <div class="mb-3">나이
      <input type="number" name="age" value="20" class="form-control mb-3" required />
    </div>
    <div class="mb-3">성별 &nbsp;&nbsp;
    	<input type="radio" name="gender" value="남자" /> 남자 &nbsp;  <!-- 같은 값이면 배열로 처리해서 넘김 -->
    	<input type="radio" name="gender" value="여자" checked /> 여자
    </div>
    <div class="mb-3">취미 &nbsp;&nbsp;
    	<input type="checkbox" name="hobby" value="등산" /> 등산 &nbsp;
    	<input type="checkbox" name="hobby" value="낚시" /> 낚시 &nbsp;
    	<input type="checkbox" name="hobby" value="바둑" /> 바둑 &nbsp;
    	<input type="checkbox" name="hobby" value="수영" /> 수영 &nbsp;
    	<input type="checkbox" name="hobby" value="배드민턴" /> 배드민턴 &nbsp;
    	<input type="checkbox" name="hobby" value="바이크" /> 바이크
    </div>
    <div class="mb-3">직업 &nbsp;&nbsp;
    	<select name="job" class="form-control">
    		<option value="">선택</option>  <!-- 서버 위해서 하나 넣어주자 // value 넣어주면 null에러는 안나옴 -->
    		<option>회사원</option>
    		<option>공무원</option>
    		<option>군인</option>
    		<option>의사</option>
    		<option>자영업</option>
    		<option>가사</option>
    	</select>
    </div>
    <div class="mb-3">가본산 &nbsp;&nbsp;
    	<select name="mountain" size="5" class="form-control" multiple>  <!-- 콤보상자가 아니라 리스트박스가 되는 것(multiple) // 그룹으로 주면 예쁘게나옴(부트스트랩) -->
    		<option>백두산</option>
    		<option>한라산</option>
    		<option>태백산</option>
    		<option>소백산</option>
    		<option>금강산</option>
    		<option>우암산</option>
    		<option>속리산</option>
    		<option>대둔산</option>
    	</select>
    </div>
    <div class="mb-3">자기소개
    	<textarea rows="6" name="content" class="form-control"></textarea>
    </div>
    <div class="mb-3">첨부파일
    	<input type="file" name="fileName" class="form-control-file border"/>
    </div>
    <div>
      <input type="submit" value="전송(submit)" class="btn btn-success" />
    </div>
  </form>
</div>
<p><br/></p>
</body>
</html>