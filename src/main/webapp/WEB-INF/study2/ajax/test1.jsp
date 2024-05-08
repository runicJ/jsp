<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/certification.jsp" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>test1.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
  	'use strict';
  	
  	function idCheck(mid) {
  		if(mid == "") {
  			alert("아이디를 입력하세요");
  			myform.mid.focus();
  			return "0";
  		}  		
  	}
  	
  	// 동기식처리
  	function idCheck0() {
  		let mid = myform.mid.value.trim();
  		let flag = idCheck(mid);
  		if(flag != "0") location.href = "${ctp}/ajaxIdCheck0.st?mid="+mid;  // 깜박이 // 값을 넘김(확장자 패턴 집중화되서 컨트롤러로 감)
  	}
  	
  	// 비동기식처리(브라우저에서 제공하는 객체(XMLHttpRequest) 사용)
  	function idCheck1() {
  		let mid = myform.mid.value.trim();
  		let flag = idCheck(mid);
  		if(flag == "0") return false;
  		
  		let xhr = new XMLHttpRequest();
  		xhr.open("GET", "${ctp}/ajaxIdCheck1.st?mid="+mid);  // 서버를 부름
  		xhr.send();
  		
  		xhr.onreadystatechange = function() {
  			if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {  // 브라우저에 보낸 상태가 정상이고, 정상처리하면 200이 옴  // 500은 코드 잘못썼을때 
  				console.log(xhr.responseText);
  				demo.innerHTML = xhr.responseText;  // response에 넘어온 텍스트(response.getWriter().write(name))
  			}
  		}
  	}
  	
  	// 비동기식처리(fetch() 사용)
  	function idCheck2() {
  		let mid = myform.mid.value.trim();
  		let flag = idCheck(mid);
  		if(flag == "0") return false;
  		
  		fetch("${ctp}/ajaxIdCheck1.st?mid="+mid)
  			.then((res) => res.json())  // json으로 변경하려면 키:value 형식으로 바꿔야함 => java의 hashMap // 현재는 오류
  			.then((res) => console.log("res : ", res))  // 성공해서 돌아오면 값을 찍어봄
  			.catch((error) => console.log("error : ", error));
  	}
  	
 		// 비동기식처리(브라우저에서 제공하는 객체(XMLHttpRequest) 사용)  => 비동기식은 깜박임이 없음(현재 페이지에서 처리 응답)
  	function idCheck3() {
  		let mid = myform.mid.value.trim();
  		let flag = idCheck(mid);
  		if(flag == "0") return false;
  		
  		let xhr = new XMLHttpRequest();
  		xhr.open("GET", "${ctp}/ajaxIdCheck1.st?mid="+mid);  // 404 request가 있는데 response가 없다 => 파일이 없다
  		xhr.send();
  		
  		xhr.addEventListener("load", (e) => {
  			console.log("e값 : ", e);
  			
  			if(e.target.status == 200) {
  				demo.innerHTML = '검색아이디 : ' + mid + " , 성명 : " + e.target.responseText;
  			}
  			else {
  				demo.innerHTML = "검색서버오류~~";
  			}
  		});
  	}
 		
 		// 비동기식처리(ajax 사용1) => js 너무 복잡 // jquery ajax 사용(jquery script 걸어놔야 사용가능)
  	function idCheck4() {
  		let mid = myform.mid.value.trim();
  		let flag = idCheck(mid);
  		if(flag == "0") return false;
  		
  		$.ajax({
  			url : "${ctp}/AjaxIdCheck1",  // 디렉토리 패턴(ajax에선 보통 디렉토리 선호) // 따로 데이터를 담아 보내기에 주소만 적음(위는 get방식으로 ? 담아보냄)
  			type : "get",  //method : "get"
  			data : {"mid" : mid},  // {받는 쪽의 서버변수 : 위에 받아온 mid} mid "" 붙이면 위의 변수가 아니라 값이 mid
  			// 예약어(변수명 바꾸면안됨) : 값 (키 : value)
  			//dataType : "json",
  			contextType : "application/json",  // 위와 중복이 되어서 에러 하나 안써야
  			charset : "utf-8",
  			timeout : 10000,  // 만초동안 대기
  			beforeSend : function() {  // 콜백함수
  				console.log("mid(전송전) : ", mid);  // javascript 함수 끝에 ,이 아니라 ; // 위는 ajax 끝에 , 붙여줌
  			},  // 서버로 전송된 자료가 다시 돌아옴
  			success:function(res) {  //정상적으로 돌아왔니  // name 넘겼으니까 res(name)
  				let str = "<font color='blue'>검색아이디 : " + mid + " , 성명 : " + res + "</font>";
  				$("#demo").html(str);
  			},  // beforeSend나 success 안은 자바스크립트 함수 쓰면됨 끝에 ;  // 가장 많은 오류 : 오타 아니면 전송 오류
  			error : function() {
  				alert("전송오류~");
  			},
  			complete: function() {
  				console.log("mid(후) : ", mid);
  			}
  		});
  	}
 		
 		// 비동기식처리(ajax 사용2)
  	function idCheck5() {  // 기본적으로 해야함
  		let mid = myform.mid.value.trim();
  		let flag = idCheck(mid);
  		if(flag == "0") return false;
  		
  		$.ajax({
  			url : "${ctp}/AjaxIdCheck1",
  			//type : "get",
  			data : {"mid" : mid},  // 서버로 보냄
  			success:function(res) {  // 갔다가 오는 것(정상적으로 왔을때)
  				let str = "<font color='blue'>검색아이디 : " + mid + " , 성명 : " + res + "</font>";
  				$("#demo").html(str);
  			},
  			error : function() {  // 비정상
  				alert("전송오류~");
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
		<form name="myform">
			아이디 : 
			<input type="text" name="mid" id="mid" class="form-control" />
			<div>
				<input type="button" value="아이디검색(동기식)" onclick="idCheck0()" class="btn btn-success mt-2 mr-2"/>
				<input type="button" value="아이디검색(비동기식1)" onclick="idCheck1()" class="btn btn-primary mt-2 mr-2"/>
				<input type="button" value="아이디검색(비동기식2)" onclick="idCheck2()" class="btn btn-secondary mt-2 mr-2"/>
				<input type="button" value="아이디검색(비동기식3)" onclick="idCheck3()" class="btn btn-info mt-2 mr-2"/>
				<input type="button" value="아이디검색(AJAX1)" onclick="idCheck4()" class="btn btn-warning mt-2 mr-2"/>
				<input type="button" value="아이디검색(AJAX2)" onclick="idCheck5()" class="btn btn-dark mt-2 mr-2"/>
			</div>
		</form>
		<hr>
			출력결과 : <div id="demo"><b>${param.name}</b></div>  <!-- 눈에 보이게 사용 -->
			<!-- ${param}은 request의 parameter를 직접 참조하는 것이고.. 모델에 넣어주신 값은 그냥.. ${model명}을 사용하시면 됩니다.
			id로 넣어주셨기 때문에.. ${id}로 참조하시면 되는 것이죠.. -->
		<hr>
	</div>
	<hr>
  <h2>HTTP통신</h2>
  <pre>
  	☞ 동기식(Synchronous) : 먼저 시작된 하나의 작업이 끝날때까지 다른 작업들은 시작하지 않고 기다렸다가, 앞의 작업이 모두 끝나면
  		새로운 작업을 시작하는 방식이다.
  	☞ 비동기식(Asynchronous) : 먼저 시작된 작업의 완료여부와 상관없이 새로운 작업을 시작하는 방식
  	- 바닐라 자바스크립트의 비동기식 : 브라우저의 XMLHttpRequest
  	- ECMA6 자바스크립트의 비동기식 : 콜백함수, Promise, Promise를 활용한 async/await, 그리고 fetch()방식
  	
  	<h4>AJAX</h4>
  	☞ AJAX(Asynchronous Javascript And Xml)
  		자바스크립트 라이브러리 중의 하나이며, 브라우저 객체인 XMLHttpRequest를 이용해서 전체페이지를 고치지 않아도 부분적인 페이지의
  		일부만을 위한 데이터를 로드하는 기법이다.
  		즉, 자바스크립트를 이용하여 서버에 데이터를 요청할때 비동기식으로 통신하는 방식. 과거는 XML방식을 많이 선호하였으나, 
  		지금은 JSON 방식을 많이 사용한다.
  		
  	<h5>AJAX에서 메소드(전송방식) 종류</h5>
  	- GET : 데이터를 읽거나 주로 검색할때 사용
  	- POST : 새로운 리소스를 생성할때 사용
  	- PUT : 리소스를 생성/업데이트할때 사용
  	- DELETE : 지정된 리소스를 삭제할때 사용
  </pre>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>