<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>scheduleMenu.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
  	'use strict';
  	
  	$(function(){
  		$("#scheduleInputClose").hide();  // 처음 상태
  	});
  	
  	function scheduleInputView() {  // 동적폼
  		let str = '<div id="scheduleInputForm">';
  		str += '<form name="scheduleForm">';
  		str += '<table class="table table-bordered">';
  		str += '<tr>';
  		str += '<th>일정분류</th><td>';  //db보면 분류랑 내용 넣어줘야함(combo상자, textarea)
    	str += '<select name="part" class="form-control">';
    	str += '<option>모임</option>';
    	str += '<option>학습</option>';
    	str += '<option>업무</option>';
    	str += '<option>여행</option>';
    	str += '<option>기타</option>';
    	str += '</select>';
  		str += '</td></tr>';
  		str += '<tr><th>내역</th><td>';
  		str += '<textarea name="content" rows="4" class="form-control"></textarea>';
  		str += '</td></tr>';
  		str += '<tr><td colspan="2" class="text-center">';
  		str += '<input type="button" value="일정등록" onclick="scheduleInputOk()" class="btn btn-success form-control" />';
  		str += '</td></tr>';
  		str += '</table>';
  		str += '</form>';
  		str += '</div>';
  		
  		$("#scheduleInputView").hide();  // 등록버튼 누르면
  		$("#scheduleInputClose").show();
  		
  		$("#demo").html(str);  // 다 누적해서 html로 뿌려줌
  	}
  	
  	function scheduleInputClose(){  // 닫기를 클릭하면
  		$("#scheduleInputView").show();
  		$("#scheduleInputClose").hide();
  		$("#scheduleInputForm").hide();  // 등록했으니까 입력폼도 안보임
  	}
  	
  	// 일정 등록하기
  	function scheduleInputOk() {
  		let part = scheduleForm.part.value;
  		let content = scheduleForm.content.value;
  		if(content.trim() == "") {
  			alert("일정을 입력하세요");
  			scheduleFrom.content.focus();
  			return false
  		}
  		let query = {
  			mid : '${sMid}',
  			sDate : '${ymd}',
  			part : part,
  			content : content
  		}
  		
  		$.ajax({
  			url : "ScheduleInputOk.sc",
  			type : "post",
  			data : query,
  			success:function(res){
  				if(res != 0) {
  					alert("일정이 등록되었습니다.");
  					location.reload();
  				}
  				else alert("일정 등록 실패~");
  			},
  			error:function() {
  				alert("전송오류");
  			}
  		});
  	}
  	
  	// 일정 삭제하기
	  function scheduleDeleteCheck(idx) {
  		let ans = confirm("선택된 일정을 삭제하시겠습니까?");
  		if(!ans) return false;
  		
  		$.ajax({
  			url : "ScheduleDeleteOk.sc",
  			type : "post",
  			data : {idx : idx},
  			success:function(res) {
  				if(res != "0") {
  					alert("일정이 삭제되었습니다.");
  					location.reload();
  				}
  				else alert("일정 삭제 실패~");
  			},
  			error:function() {
  				alert("전송오류!");
  			}
  		});
  	}
  	

    // 일정관리 수정창 보기
    function scheduleUpdateView(idx, part, content) {
    	let str = '<div id="scheduleUpdateForm'+idx+'">';
  		str += '<form name="scheduleUpdateForm">';
    	str += '<table class="table table-bordered">';
    	str += '<tr>';
    	str += '<th>일정분류</th><td>';
    	str += '<select name="part" id="part'+idx+'" class="form-control">';
    	str += '<option>모임</option>';
    	str += '<option>학습</option>';
    	str += '<option>업무</option>';
    	str += '<option>여행</option>';
    	str += '<option>기타</option>';
    	str += '<option value="'+part+'" selected>'+part+'</option>';
    	str += '</select>';
    	str += '</td></tr>';
    	str += '<tr><th>내역</th><td>';
    	str += '<textarea name="content" id="content'+idx+'" rows="4" class="form-control">'+content.replaceAll("<br/>","\n")+'</textarea>';
    	str += '</td></tr>';
    	str += '<tr><td colspan="2" class="text-center">';
    	str += '<input type="button" value="일정수정" onclick="scheduleUpdateOk('+idx+')" class="btn btn-success mr-3"/>';
    	str += '<input type="button" value="수정창닫기" onclick="scheduleUpdateClose('+idx+')" class="btn btn-warning"/>';
    	str += '</td></tr>';
    	str += '</table>';
    	str += '</form>';
    	str += '</div>';
    	
    	$("#scheduleUpdateViewBtn"+idx).hide();
    	$("#updateDemo"+idx).html(str);
    }
    
    function scheduleUpdateClose(idx) {
    	$("#scheduleUpdateForm"+idx).hide();
    	$("#scheduleUpdateViewBtn"+idx).show();
    }
    
    // 일정관리 수정하기
    function scheduleUpdateOk(idx) {
    	let part = $("#part"+idx).val();
    	let content = $("#content"+idx).val();
    	let query = {
    			idx  : idx,
    			part : part,
    			content:content
    	}
    	
    	$.ajax({
    		url  : "ScheduleUpdateOk.sc",
    		type : "post",
    		data : query,
    		success:function(res) {
    			if(res != "0") {
    				alert("일정이 수정되었습니다.");
    				location.reload();
    			}
    			else alert("일정 수정 실패~");
    		},
    		error : function() {
    			alert("전송오류");
    		}
    	});
    }
  	
  	// 모달창에 상세내역 뿌리기
  	function modalView(part, content) {
  		$("#myModal #part").html(part);
  		$("#myModal #content").html(content);
  	}
  </script>
  <style>
  	th {
  		text-align: center;
  		background-color: #ccc;
  	}
  </style>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h4>${ymd}의 일정입니다.</h4>
  <p>오늘의 일정은 총 ${scheduleCnt}건이 있습니다.</p>
  <hr>
  <div>
  	<input type="button" value="일정등록" onclick="scheduleInputView()" id="scheduleInputView" class="btn btn-success mb-2" />  <!-- 내용도 보고 등록도 하니까 동적폼 -->
  	<input type="button" value="일정닫기" onclick="scheduleInputClose()" id="scheduleInputClose" class="btn btn-success mb-2" />
  	<c:set var="ymds" value="${fn:split(ymd, '-')}" />
  	<input type="button" value="돌아가기" onclick="location.href='Schedule.sc?yy=${ymds[0]}&mm=${ymds[1]-1}';" class="btn btn-warning mb-2"/>  <!-- 아까 하나 증가 해놨으니(원래 달은 하나 뺀 상태였음) -->
  </div>
  <div id="demo"></div>
  <hr>
  <c:if test="${scheduleCnt != 0}">  <!-- 0이 아니면 1건이라도 있다 -->
  	<table class="table table-hover text-center">
  		<tr class="table-dark text-dark">
  			<th>번호</th>
  			<th>분류</th>
  			<th>내용</th>
  			<th>삭제</th>
  		</tr>
  		<c:forEach var="vo" items="${vos}" varStatus="st">
  			<tr>
	  			<td>${st.count}</td>
	  			<td>${vo.part}</td>
	  			<td class="text-left">
	  				<a href="#" onclick="modalView('${vo.part}','${fn:replace(vo.content,newLine,'<br>')}')" data-toggle="modal" data-target="#myModal">
		  					<c:if test="${fn:indexOf(vo.content, newLine) != -1}">${fn:replace(vo.content,newLine,'<br>')}</c:if>  <!-- -1가 아니면 존재한다는 얘기 -->
		  					<c:if test="${fn:indexOf(vo.content, newLine) == -1}">
			  					<c:if test="${fn:length(vo.content) >= 15}">${fn:substring(vo.content,0,16)}...</c:if>
			  					<%-- <c:if test="${fn:length(vo.content) < 15}">${vo.content} --%>  <!-- 배타적 15자까지만 보여줌 -->
			  					<c:if test="${fn:length(vo.content) < 15}">${vo.content}</c:if>
		  					</c:if>
	  				</a>
	  			</td>  <!-- content textarea로 받아 enter키 사용 했을 것 그냥 쓰면 문제가 됨 -->
	  			<td>
	  				<input type="button" value="수정" id="scheduleUpdateViewBtn${vo.idx}" onclick="scheduleUpdateView('${vo.idx}','${vo.part}','${fn:replace(vo.content,newLine,'<br/>')}')" class="btn btn-primary btn-sm"/>  <!-- 수정은 만들기(모달?) -->
	  				<input type="button" value="삭제" onclick="scheduleDeleteCheck(${vo.idx})" class="btn btn-danger btn-sm"/>
	  			</td>
	  		</tr>
	  		<tr><td colspan="4" class="m-0 p-0"><div id="updateDemo${vo.idx}"></div></td></tr>
  		</c:forEach>
  		<tr><td colspan="4" class="m-0 p-0"></td></tr>  <!-- 반복문 돌렸을때 넣어줌 -->
  	</table>
  </c:if>
</div>
<p><br/></p>
<!-- 일정 상세내역 출력을 위한 모달처리 -->
  <div class="modal fade" id="myModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">>> <b>${ymd}</b></h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <!-- Modal body -->
        <div class="modal-body">
        	<table class="table table-bordered">
        		<tr><th>분류</th><td><span id="part"></span></td></tr>
        		<tr><th>내용</th><td><span id="content"></span></td></tr>
        	</table>
        </div>        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>    
      </div>
    </div>
  </div>
<jsp:include page="/include/footer.jsp" />
</body>
</html>