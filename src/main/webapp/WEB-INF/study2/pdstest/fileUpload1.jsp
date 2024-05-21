<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>fileUpload1.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
    'use strict';
    
    function fCheck() {
    	let fName = document.getElementById("file").value;
    	let maxSize = 1024 * 1024 * 10;	// 기본 단위 : Byte,   1024 * 1024 * 10 = 10MByte 허용  // 1의 10승 * 1의 10승 = 1Mb(1024Byte)
    	let ext = fName.substring(fName.lastIndexOf(".")+1).toLowerCase();  // 확장자 체크
    	
    	if(fName.trim() == "") {
    		alert("업로드할 파일을 선택하세요");
    		return false;
    	}
    	//demo.innerHTML = fName;
    	
    	let fileSize = document.getElementById("file").files[0].size;
    	if(fileSize > maxSize) {
    		alert("업로드할 파일의 최대용량은 10MByte입니다.");
    	}
    	else if(ext != 'jpg' && ext != 'gif' && ext != 'png' && ext != 'zip' && ext != 'hwp' && ext != 'ppt' && ext != 'pptx' && ext != 'doc' && ext != 'pdf' && ext != 'xlsx' && ext != 'txt') {
    		alert("업로드 가능한 파일은 'jpg/gif/png/zip/hwp/ppt/pptx/doc/pdf/xlsx/txt'만 가능합니다.");
    	}
    	else {
    		myform.submit();
    		// alert("전송합니다.");
    	}
    	
    }
    
    // 선택된 그림 미리보기(여러개 넘김=>배열)
    function imgCheck(e) {  // 뭔가 들어와 있으면 참
    	if(e.files && e.files[0]) {
    		let reader = new FileReader();
    		reader.onload = function(e) {  // 익명함수(reader에 의해서 작업이 뭔가 바뀌면 익명함수를 실행해 주세요)
    			document.getElementById("demoImg").src = e.target.result;
    		}
    		reader.readAsDataURL(e.files[0]);  // 그림 미리보기(그림파일만 가능)
    	}
    }
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2>파일 업로드 연습(싱글파일처리)</h2>
  <p>COS라이브러리를 이용한 파일 업로드</p>
  <div>(http://www.servlets.com/cos/)</div>
  <hr/>
  <form name="myform" method="post" action="FileUpload1Ok.st" enctype="multipart/form-data">  <!-- 확장자 패턴 // 웹에서 데이터 무조건 multipart/form-data  -->
    파일명 : 
    <input type="file" name="fName" id="file" onchange="imgCheck(this)" class="form-control-file border mb-2" />  <!-- 파일태그 value가 있지만 file속성 안의 value는 읽지 않음(js로 처리) (value = "atom.jpg" 넣었었음) -->
    <input type="button" value="파일전송" onclick="fCheck()" class="btn btn-success form-control"/>  <!-- 프론트에서 체크 // 백에서도 체크 둘다 해야함 -->
    <!-- <input type="submit" value="파일전송" class="btn btn-success form-control"/> -->
    <input type="hidden" name="nickName" value="${sNickName}"/>
  </form>
  <hr/>
  <div id="demo"></div><br/>
  <img id="demoImg" width="200px"/>
  <hr/>
  <div class="row">
	  <div class="col text-center"><a href="FileDownload.st" class="btn btn-primary form-control">다운로드폴더로 이동하기</a></div>
	  <div class="col"><a href="FileUpload.st" class="btn btn-warning form-control">돌아가기</a></div>
	</div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>