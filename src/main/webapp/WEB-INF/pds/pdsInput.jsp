<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>pdsInput.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
    'use strict';
    let cnt = 1;
    
    function fCheck() {
    	let fName1 = document.getElementById("fName1").value;
    	let maxSize = 1024 * 1024 * 30;
    	let title = $("#title").val();
    	
    	if(fName1.trim() == "") {
    		alert("업로드할 파일을 선택하세요");
    		return false;
    	}
    	else if(title.trim() == "") {
    		alert("파일의 제목을 입력하세요");
    		return false;
    	}
    	
    	// 파일 사이즈와 확장자 체크하기
    	let fileSize = 0;
    	for(let i=1; i<=cnt; i++) {
    		let imsiName = 'fName' + i;  // id 첫번째 것
    		if(isNaN(document.getElementById(imsiName))) {  // isNaN 숫자냐 값이 있는가
    			let fName = document.getElementById(imsiName).value;
    			if(fName != "") {
    				fileSize += document.getElementById(imsiName).files[0].size;
			    	let ext = fName.substring(fName.lastIndexOf(".")+1).toLowerCase();
			    	if(ext != 'jpg' && ext != 'gif' && ext != 'png' && ext != 'zip' && ext != 'hwp' && ext != 'ppt' && ext != 'pptx' && ext != 'doc' && ext != 'pdf' && ext != 'xlsx' && ext != 'txt') {
			    		alert("업로드 가능한 파일은 'jpg/gif/png/zip/hwp/ppt/pptx/doc/pdf/xlsx/txt'만 가능합니다.");
			    		return false;
			    	}
    			}
    		}
    	}
    		
    	if(fileSize > maxSize) {
    		alert("업로드할 1개 파일의 최대용량은 30MByte입니다.");
    		return false;
    	}
    	else {
    		myform.fSize.value = fileSize;
    		//alert("파일 총 사이즈 : " + fileSize);
    		myform.submit();
    	}	
    }
    
    // 파일 박스 추가하기
    function fileBoxAppend() {
    	cnt++;
    	let fileBox = '';
    	fileBox += '<div id="fBox'+cnt+'">';
    	fileBox += '<input type="file" name="fName'+cnt+'" id="fName'+cnt+'" class="form-control-file border mb-2" style="float:left; width:85%;" />';
    	fileBox += '<input type="button" value="삭제" onclick="deleteBox('+cnt+')" class="btn btn-danger mb-2 ml-2" style="width:10%;" />';
    	fileBox += '</div>';
    	$("#fileBox").append(fileBox);		// html(), text(), append()
    }
    
    // 파일 박스 삭제
    function deleteBox(cnt) {
    	$("#fBox"+cnt).remove();
    	cnt--;
    }
    
    function pwdCheck1() {
    	$("#pwdDemo").hide();
    	$("#pwd").val("");  // 값을 공백으로
    }
    
    function pwdCheck2() {
    	$("#pwdDemo").show();
    }
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <form name="myform" method="post" action="PdsInputOk.pds" enctype="multipart/form-data">
  	<h2 class="text-center">자 료 올 리 기</h2>  <!-- 하나로 해서 배열처리 말고 동적폼 사용 -->
  	<br>
  	<div>
      <input type="button" value="파일박스추가" onclick="fileBoxAppend()" class="btn btn-primary mb-2" />
    	<input type="file" name="fName1" id="fName1" class="form-control-file border mb-2" />
    </div>
    <div id="fileBox"></div>  <!-- 여기에 출력 -->
    <div class="mb-2">
    	올린이 : ${sNickName}
    </div>
    <div class="mb-2">
    	제목 : <input type="text" name="title" id="title" placeholder="자료의 제목을 입력하세요" class="form-control" required />  <!-- placeholder나 value 둘 중에 하난 써야 -->
    </div>
    <div class="mb-2">
    	내용 : <textarea rows="4" name="content" id="content" placeholder="자료의 상세내역을 입력하세요" class="form-control"></textarea>
    </div>
    <div class="mb-2">
    	분류 : 
			<select name="part" id="part" class="form-control">
				<option ${part=="학습" ? "selected" : ""}>학습</option>
				<option ${part=="여행" ? "selected" : ""}>여행</option>
				<option ${part=="음식" ? "selected" : ""}>음식</option>
				<option ${part=="기타" ? "selected" : ""}>기타</option>
			</select>
    </div>
    <div class="mb-2">
    	공개여부 : 
    	<input type="radio" name="openSw" value="공개" onclick="pwdCheck1()" checked class="mr-3" />공개
    	<input type="radio" name="openSw" value="비공개" onclick="pwdCheck2()" class="mr-3" />비공개
    	<div id="pwdDemo" style="display:none"><input type="password" name="pwd" id="pwd" value="1234" /></div>
    </div>
    <div>
    	<input type="button" value="자료올리기" onclick="fCheck()" class="btn btn-success" />
    	<input type="reset" value="다시쓰기" class="btn btn-success" />
    	<input type="button" value="돌아가기" onclick="location.href='PdsList.pds?part=${part}';" class="btn btn-info" />
    </div>
    <input type="hidden" name="hostIp" value="${pageContext.request.remoteAddr}" />
    <input type="hidden" name="mid" value="${sMid}" />
    <input type="hidden" name="nickName" value="${sNickName}" />
    <input type="hidden" name="fSize" />
  </form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>