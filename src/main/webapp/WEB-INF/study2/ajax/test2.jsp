<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/certification.jsp" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>test2.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
  	'use strict';
  	
  	// 아이디 공백체크하기 함수
  	function idCheck(mid) {
  		if(mid == "") {
  			alert("아이디를 입력하세요");
  			myform.mid.focus();
  			return "0";
  		}  		
  	}
  	
  	// 1개값의 전달처리
  	function idCheck1() {
  		let mid = myform.mid.value;
  		if(idCheck(mid) == "0") return false;
  		
  		$.ajax({
  			url : "${ctp}/AjaxIdCheck2_1",
  			type : "get",
  			data : {mid : mid},  // 전송
  			success:function(res) {  // 여기부터 갔다오는 것
  				$("#demo").html(res);
  			},
  			error: function() {
  				alert("전송오류!!");
  			}
  		});
  	}
  	
  	// vo 전달처리(문자열처리...)
  	function idCheck2() {
  		let mid = myform.mid.value;
  		if(idCheck(mid) == "0") return false;  // "0"이라는건 아이디를 입력하지 않음
  		
  		$.ajax({  // ajax는 $로 ()안에 내용 적을때 {}
  			url : "${ctp}/AjaxIdCheck2_2",
  			type : "get",  // 보통 get방식, post(생성해서 보냄, 써도됨)
  			data : {mid : mid},  // {} 안에 여러개 가능  // 앞에 mid "" 붙여도 되고 안붙여도 됨
  			success:function(res) {
  				$("#demo").html(res);
  				
  				let str = res.split("/");  // 문자로 넘어온 res를 배열형식으로 넣어서 처리
  				$("#imsiiMid").html(str[0]);
  				$("#imsiName").html(str[1]);
  				$("#imsiAge").html(str[2]);
  				$("#imsiGender").html(str[3]);
  				$("#imsiAddress").html(str[4]);
  			},
  			error: function() {
  				alert("전송오류!!");
  			}
  		});
  	}

  	// '키/값'형식으로 전달처리(map으로 바꿔서 많이 사용)
  	function idCheck3() {
  		let mid = myform.mid.value;
  		if(idCheck(mid) == "0") return false;
  		
  		$.ajax({
  			url : "${ctp}/AjaxIdCheck2_3",
  			type : "get",
  			data : {mid : mid},  // 전송
  			success:function(res) {  // 여기부터 갔다오는 것
  				console.log("res : ", res);  // json object
  				$("#demo").html(res);
  				
  				let js = JSON.parse(res);  // json parsing  => js의 객체로 바꿈
  				console.log("js : ", js);
  			
  				$("#imsiMid").html(js.mid);  // 객체 이므로 js.
  				$("#imsiName").html(js.name);
  				$("#imsiAge").html(js.age);
  				$("#imsiGender").html(js.gender);
  				$("#imsiAddress").html(js.address);
  			},
  			error: function() {
  				alert("전송오류!!");
  			}
  		});
  	}
  	
  	// vos형식으로 전달처리
  	function idCheck4() {
  		let mid = myform.mid.value;
  		if(idCheck(mid) == "0") return false;
  		
  		$.ajax({
  			url : "${ctp}/AjaxIdCheck2_4",
  			type : "get",
  			data : {mid : mid},  // 전송
  			success:function(res) {  // 여기부터 갔다오는 것
  				console.log("res : ", res);  // json object
  				$("#demo").html(res);
  				
  				let js = JSON.parse(res);  // json parsing  => js의 객체로 바꿈
  				console.log("js : ", js);
  				
  				// 향상된 for문(키in, 값꺼낼때of), forEach, map
  				let tMid = "", tName = "", tAge = "", tGender="", tAddress="";  // 누적하기 위한 준비
  				for(let j of js) {  // 하나의 j => {"name":"관리자","mid":"admin","address":"서울","gender":"여자","age":"10"}
  					tMid += j.mid + "/";
  					tName += j.name + "/";
  					tAge += j.age + "/";
  					tGender += j.gender + "/";
  					tAddress += j.address + "/";
  				}
  			
  				$("#imsiMid").html(tMid);  // 객체 이므로 js.
  				$("#imsiName").html(tName);
  				$("#imsiAge").html(tAge);
  				$("#imsiGender").html(tGender);
  				$("#imsiAddress").html(tAddress);
  			},
  			error: function() {
  				alert("전송오류!!");
  			}
  		});
  	}
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
	<div>
		<h2>AJAX 값 전달 연습</h2>
		<form name="myform">
			아이디 : 
			<input type="text" name="mid" id="mid" class="form-control" />
			<div>
				<input type="button" value="아이디검색1" onclick="idCheck1()" class="btn btn-success mt-2 mr-2"/>
				<input type="button" value="아이디검색2" onclick="idCheck2()" class="btn btn-primary mt-2 mr-2"/>
				<input type="button" value="아이디검색3" onclick="idCheck3()" class="btn btn-secondary mt-2 mr-2"/>
				<input type="button" value="아이디검색4" onclick="idCheck4()" class="btn btn-info mt-2 mr-2"/>
			</div>
		</form>
		<hr>
			<div id="demo"><b>${param.name}</b></div>
		<hr>
		<div>
			<div>아이디 : <span id="imsiMid"></span></div>
			<div>성명 : <span id="imsiName"></span></div>
			<div>나이 : <span id="imsiAge"></span></div>
			<div>성별 : <span id="imsiGender"></span></div>
			<div>주소 : <span id="imsiAddress"></span></div>
		</div>
	</div>
	<hr>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>