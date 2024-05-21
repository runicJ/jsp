<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>fileDownLoad.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
    'use strcit';
    
    function fileDelete(fileName) {
    	$.ajax({
    		url  : "FileDelete.st",
    		type : "post",
    		data : {fileName:fileName},
    		success:function(res) {
    			if(res != "0") {
    				alert("삭제완료");
    				location.reload();
    			}
    			else alert("삭제 실패!!");
    		},
    		error : function() {
    			alert("전송오류!");
    		}
    	});
    }
    
    // 전체선택
    function allCheck() {
      for(let i=0; i<myform.fileFlag.length; i++) {
        myform.fileFlag[i].checked = true;
      }
    }

    // 전체취소
    function allReset() {
      for(let i=0; i<myform.fileFlag.length; i++) {
        myform.fileFlag[i].checked = false;
      }
    }

    // 선택반전
    function reverseCheck() {
      for(let i=0; i<myform.fileFlag.length; i++) {
        myform.fileFlag[i].checked = !myform.fileFlag[i].checked;
      }
    }
    
    // 선택항목 삭제처리
    function selectFileDelete() {
    	let selectFileArray = '';
    	
      for(let i=0; i<myform.fileFlag.length; i++) {  // fileFlag checkbox 이름
        if(myform.fileFlag[i].checked) selectFileArray += myform.fileFlag[i].value + "/";  // value 파일명 중복이 안됨 누적
      }
    	if(selectFileArray == '') {
    		alert("삭제할 파일 항목을 1개 이상 선택하세요");
    		return false;
    	}
    	
    	let ans = confirm("선택된 파일을 삭제하시겠습니까?");
    	if(!ans) return false;
    	
    	selectFileArray = selectFileArray.substring(0,selectFileArray.lastIndexOf("/"));
      let query = {
    		  selectFileArray : selectFileArray
      }
      
      $.ajax({
    	  url  : "FileDeleteCheck.st",
    	  type : "post",
    	  data : query,
    	  success:function(res) {
    		  if(res != "0") { 
    			  alert("선택한 항목들을 삭제하셨습니다.");
    			  location.reload();
    		  }
    		  else alert("삭제 실패~");
    	  },
    	  error : function() {
    		  alert("전송 실패~~");
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
  <h2>저장된 파일 정보</h2>
  <hr/>
  <div class="row">
  <div class="col input-group">
    <input type="button" value="전체선택" onclick="allCheck()" class="btn btn-success btn-sm mr-1"/>
    <input type="button" value="전체취소" onclick="allReset()" class="btn btn-primary btn-sm mr-1"/>
    <input type="button" value="선택반전" onclick="reverseCheck()" class="btn btn-info btn-sm mr-1"/>
    <input type="button" value="선택항목삭제" onclick="selectFileDelete()" class="btn btn-danger btn-sm" />
  </div>
  <div class="col text-right">
  	<a href="FileUpload.st" class="btn btn-warning">돌아가기</a>
  </div>
  </div>
  <hr/>
  <form name="myform">
	  <c:forEach var="file" items="${files}" varStatus="st">  <!-- 향상된 for문과 같은 forEach -->
	    <input type="checkbox" name="fileFlag" id="fileFlag${st.index}" value="${file}"/>
	    ${st.count} : <a href="${ctp}/images/pdstest/${file}" download="${file}">${file}</a>  <!-- 절대경로 // download html 프론트 명령-->
	    <input type="button" value="삭제" onclick="fileDelete('${file}')" class="btn btn-danger btn-sm"/>  <!-- file명 같이 넘김 el 문자니까 ''로 -->
	    <input type="button" value="자바다운로드" onclick="location.href='JavaFileDownload.st?file=${file}';" class="btn btn-secondary" />  <!-- 바로 보냄 처리하고 ajax처럼 끝내버러(메시지처리 해도됨) --><!-- 쿼리스트링 방식으로 위에 넘어온 file명 같이 넘김 -->
	    <br/>
	    <c:set var="fNameArr" value="${fn:split(file,'.')}"/>
	    <c:set var="extName" value="${fn:toLowerCase(fNameArr[fn:length(fNameArr)-1])}"/>
	    <font color="blue">
		    <c:if test="${extName == 'zip'}">압축파일</c:if>
		    <c:if test="${extName == 'ppt' || extName == 'pptx'}">파워포인트파일</c:if>
		    <c:if test="${extName == 'xls' || extName == 'xlsx'}">엑셀파일</c:if>
	    </font>
	    <c:if test="${extName == 'jpg' || extName == 'png' || extName == 'gif'}">
	      <img src="${ctp}/images/pdstest/${file}" width="150px"/>
	    </c:if>
	    <br/><br/>
	  </c:forEach>
  </form>
  <hr/>
  <p>
    <input type="button" value="돌아가기" onclick="location.href='FileUpload4.st';" class="btn btn-success"/>
  </p>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>