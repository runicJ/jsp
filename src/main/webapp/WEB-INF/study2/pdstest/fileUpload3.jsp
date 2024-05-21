<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>fileUpload3.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
    'use strict';
    
    function fCheck() {
    	let fName1 = document.getElementById("file1").value;  // 여기 프론트에서 체크는 하나만 가능
    	let ext1 = fName1.substring(fName1.lastIndexOf(".")+1).toLowerCase();  // 확장자 체크
    	let maxSize = 1024 * 1024 * 10;	// 기본 단위 : Byte,   1024 * 1024 * 10 = 10MByte 허용  // 1의 10승 * 1의 10승 = 1Mb(1024Byte)
    	
    	if(fName1.trim() == "") {
    		alert("업로드할 파일을 선택하세요");
    		return false;
    	}
    	//demo.innerHTML = fName;
    	
    	let fileSize1 = document.getElementById("file1").files[0].size;
    	if(fileSize1 > maxSize) {
    		alert("업로드할 1개 파일의 최대용량은 10MByte입니다.");
    	}
    	else if(ext1 != 'jpg' && ext1 != 'gif' && ext1 != 'png' && ext1 != 'zip' && ext1 != 'hwp' && ext1 != 'ppt' && ext1 != 'pptx' && ext1 != 'doc' && ext1 != 'pdf' && ext1 != 'xlsx' && ext1 != 'txt') {
    		alert("업로드 가능한 파일은 'jpg/gif/png/zip/hwp/ppt/pptx/doc/pdf/xlsx/txt'만 가능합니다.");
    	}
    	else {
    		myform.submit();
    	}
    }
    
    // 파일 박스 추가하기 => 동적폼 만들기
    let cnt = 1;
    function fileBoxAppend() {
    	cnt++;
    	let fileBox = '';
    	fileBox += '<div id="fBox'+cnt+'">';
    	fileBox += '<input type="file" name="fName'+cnt+'" id="file'+cnt+'" class="form-control-file border mb-2" style="float:left; width:85%;" />';  /* 메뉴가 나오는 걸 지울 수 있어야 함 */
    	fileBox += '<input type="button" value="삭제" onclick="deleteBox('+cnt+')" class="btn btn-danger mb-2 ml-2" style="width:10%;" />';  // float 안해도 위에 것 따라갈 것
    	fileBox += '</div>';
    	$("#fileBox").append(fileBox);		// html(), text(), append() 기존 것에 추가하는 것<=들어갈 수 있는 것
    }
    
    // 파일 박스 삭제
    function deleteBox(cnt) {  // 위의 변수와 다름 매개변수cnt
    	$("#fBox"+cnt).remove();
    	cnt--;
    }
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2>파일 업로드 연습(멀티파일처리)</h2>
  <p>COS라이브러리를 이용한 파일 업로드</p>
  <div>(http://www.servlets.com/cos/)</div>
  <hr/>
  <form name="myform" method="post" action="FileUpload3Ok.st" enctype="multipart/form-data">  <!-- 확장자 패턴 // 웹에서 데이터 무조건 multipart/form-data  -->
    파일명 : 
    <div>
      <input type="button" value="파일박스추가" onclick="fileBoxAppend()" class="btn btn-primary mb-2" />  <!-- 파일태그 value가 있지만 file속성 안의 value는 읽지 않음(js로 처리) (value = "atom.jpg" 넣었었음) -->
    	<input type="file" name="fName1" id="file1" class="form-control-file border mb-2" />
    </div>
	  <div id="fileBox"></div>
    <input type="button" value="파일전송" onclick="fCheck()" class="btn btn-success form-control"/>  <!-- 프론트에서 체크 // 백에서도 체크 둘다 해야함 -->
    <input type="hidden" name="nickName" value="${sNickName}"/>
  </form>
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