<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>memberList.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
  	'use strict';
  	
  	$(function(){  // jsp
  		$("#userDisplay").hide();
  	
  		$("#userInfor").on("click", function(){
  			//if($("#userInfor").is(':checked')) {
  			if($("input:checkbox[id='userInfor']").is(":checked")) {
  				$("#totalList").hide();
  				$("#userDisplay").show();
  			}
  			else {
  				$("#totalList").show();
  				$("#userDisplay").hide();
  			}
  		});
  	});
  	
  	// 각 레벨(등급)별 회원 보기...
  	function levelItemCheck() {
  		let level = $("#levelItem").val();
  		location.href = "MemberList.ad?level="+level;  // ajax 안써도 됨 jquery로 했음 
  	}
  	
  	// 회원별 각각의 등급 변경처리(ajax처리)
  	function levelChange(e) {  // 이벤트이므로 e로 받음
  		let ans = confirm("선택한 회원의 등급을 변경하시겠습니까?");
  		if(!ans) {
  			location.reload(); // 원위치함(리로드 해야 원래 등급으로 위치가 되어있음)
  			return false;
  		}
		
  		let items = e.value.split("/");  // 이벤트에서 넘어온 값
  		let query = {
  				level : items[0],
  				idx : items[1]
  		}  // 변수를 끄집어냄(백엔드 쪽에서 해도 됨)
  		
  		$.ajax({
  			url : "MemberLevelChange.ad",
  			type : "get",
  			data : query,  // 앞에서 query 변수로 넘김  // 여기까지가 전송
  			success:function(res) {
  				if(res != "0") {
  					alert("등급 수정 완료!");
  					location.reload();
  				}
  				else alert("등급 수정 실패~~");
  			},
  			error : function() {
  				alert("전송오류!");
  			}
  		});
  	}
  	
  	// 30일 경과회원 삭제처리
  	function memberDeleteOk(idx) {  // 가입할때 입력한 사진도 지우기(noImage말고)
  		let ans = confirm("선택하신 회원을 영구삭제 하시겠습니까?");
  		if(ans) {
  			$.ajax ({
  				url: "MemberDeleteOk.ad",
  				type : "post",
  				data : {idx : idx},
  				success:function(res) {
  					if(res != "0") {
  						alert("영구 삭제 처리 되었습니다.");
  						location.reload();
  					}
  					else alert("삭제 실패~~");
  				},
  				error:function() {
						alert("전송실패~~");
  				}
  			});
  		}
  	}
  	
/*   	$('#all_select').click(function() {
  	    if ($("input:checkbox[id='all_select']").prop("checked")) {
  	        $("input[type=checkbox]").prop("checked", true);
  	    } else {
  	        $("input[type=checkbox]").prop("checked", false);
  	    }
  	});
  	
  	$('#reverse_select').click(function() {
  	    $("input[type=checkbox]").each(function(){
  	        $(this).prop("checked", !$(this).prop("checked"));
  	    });
  	}); */
  	
  	function allCheck() {
        let isChecked = $("#all_select").prop("checked");
        $("input[name=itemCheck]").prop("checked", isChecked);
    }
    
    function updateAllMemberLevel(e) {
 
        let levelB = e.value;
        let checkItems = document.querySelectorAll('input[name=itemCheck]:checked');
 
        if (checkItems.length === 0) {
            alert("선택하신 회원이 없습니다.");
            return false;
        }
        
        let ans = confirm("선택한 회원의 등급을 변경하시겠습니까?");
        if (!ans) {
            location.reload();
            return false;
        }
        
        let strIdx =""
            checkItems.forEach(function(checkItem) {
                strIdx += checkItem.value+"/";
            });
 
        $.ajax({
            url: "MemberLevelChange.ad",
            type: "GET",
            data: {
                level: levelB,
                strIdx : strIdx
            },
            success: function(res) {
                if (res !== "0") {
                    alert("등급 수정 완료!");
                    location.reload();
                } else {
                    alert("등급 수정 실패");
                }
            },
            error: function() {
                alert("전송오류!");
            }
        });
 
    }
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <div class="row">
  	<div class="col"><input type="checkbox" name="userInfor" id="userInfor" onclick="userCheck()" />&nbsp;비공개회원보기</div>
  	<div class="col">
  		<select name="levelItem" id="levelItem" onchange="levelItemCheck()">
  			<option value="999" ${level > 4 ? "selected" : ""}>전체보기</option>
  			<option value="1" <c:if test="${level==1}">selected</c:if>>준회원</option>
  			<option value="2" <c:if test="${level==2}">selected</c:if>>정회원</option>
  			<option value="3" <c:if test="${level==3}">selected</c:if>>우수회원</option>
  			<option value="99" <c:if test="${level==99}">selected</c:if>>탈퇴신청회원</option>
  			<option value="0" ${level==0 ? "selected" : ""}>관리자</option>
  		</select>
  	</div>
  </div>
  <hr>
	<div id="totalList">
	  <h3 class="text-center">전체 회원 리스트</h3>
	  	<div class="d-flex justify-content-between align-items-center mb-2">
  			<div><input type="button" value="선택반전" name="reverse_select" id="reverse_select" /></div>
	  		<div>
	            <select name="levelSelected" id="levelSelected" class="mr-2">
	                <option disabled selected>등급선택</option>
	                <option value="1">준회원</option>
	                <option value="2">정회원</option>
	                <option value="3">우수회원</option>
	                <option value="0">탈퇴신청회원</option>
	                <option value="99">관리자</option>
	            </select>
	            <input type="submit" class="btn btn-outline-info btn-sm" value="등급일괄변경" onclick="updateAllMemberLevel()">
	        </div>
	    </div>
	  <table class="table table-hover text-center">  <!-- 페이징 처리하는 부분 // 정렬하는 부분 넣어주기 -->
	  	<tr class="table-dark text-dark">
	  		<th><div class="checkbox"><input type="checkbox" id="all_select"><span></span></div></th>
	  		<th>번호</th>
	  		<th>아이디</th>
	  		<th>닉네임</th>
	  		<th>성명</th>
	  		<th>생일</th>
	  		<th>성별</th>
	  		<th>최종방문일</th>
	  		<th>오늘방문횟수</th>
	  		<th>활동여부</th>
	  		<th>현재레벨</th>
	  	</tr>
	  	<c:forEach var="vo" items="${vos}" varStatus="st">
	  		<c:if test="${vo.userInfor == '공개' || (vo.userInfor != '공개' && sLevel == 0)}">
	  		<c:if test="${vo.userDel == 'OK'}"><c:set var="active" value="탈퇴신청"/></c:if>  <!-- 부트4에선 active 예약어 -->
	  		<c:if test="${vo.userDel != 'OK'}"><c:set var="active" value="활동중"/></c:if>  <!-- 배타적 -->
	  		<tr>
	  			<td><input type="checkbox" name="itemCheck" value="${vo.idx}"/></td>
	  			<td>${vo.idx}</td>
	  			<td><a href="MemberSearch.mem?mid=${vo.mid}">${vo.mid}</a></td>  <!-- 개인 회원 정보보기 링크준 것 // 해당 클릭한 아이디의 정보만 보는 것이기 때문에 쿼리스트링으로 어떤건지 보내줘야 함 -->
	  			<td>${vo.nickName}</td>
	  			<td>${vo.name}</td>
	  			<td>${fn: substring(vo.birthday,0,10)}</td>  <!-- 날짜 형식으로 엄청 길게 나오니까 잘라서 보여주기 // 위에 fn 쓰려면 정의되어 있는지 확인 -->
	  			<td>${vo.gender}</td>
	  			<td>${fn: substring(vo.lastDate,0,10)}</td>
	  			<td>${vo.todayCnt}</td>
	  			<td>
	  				<c:if test="${vo.userDel == 'OK'}"><font color="red"><b>${active}</b></font></c:if>
	  				<c:if test="${vo.userDel != 'OK'}">${active}</c:if>
	  				<c:if test="${vo.deleteDiff >= 30}"><br>
	  					(<a href="javascript:memberDeleteOk(${vo.idx})">30일 경과</a>)  <!-- idx 넘김 -->
	  				</c:if>
	  			</td>
	  			<td>
		  			<form name="levelForm">
		  				<select name="level" onchange="levelChange(this)">  <!-- this => 현재 발생한 이벤트에 대해서 처리하겠다 -->
		  					<option value="1/${vo.idx}" ${vo.level == 1 ? "selected" : ""}>준회원</option>
		  					<option value="2/${vo.idx}" ${vo.level == 2 ? "selected" : ""}>정회원</option>
		  					<option value="3/${vo.idx}" ${vo.level == 3 ? "selected" : ""}>우수회원</option>
		  					<option value="0/${vo.idx}" ${vo.level == 0 ? "selected" : ""}>관리자</option>
		  					<option value="99/${vo.idx}" ${vo.level == 99 ? "selected" : ""}>탈퇴신청회원</option>  <!-- 구분자 넣고 해당 회원의 고유번호 같이 보내야함 -->
		  				</select>
		  			</form>
		  		</td>
	  		</tr>
	  		</c:if>
	  	</c:forEach>
	  	<tr><td colspan="10" class="m-0 p-0"></td></tr>
  	</table>
  </div>
  <div id="userDisplay">
  	<c:if test="${sLevel == 0}">
	  <hr>
	  <h3 class="text-center">비공개 회원 리스트</h3>  <!-- 관리자만 보는건데 세션처리 안하면 소스보기 했을때 아무나 보임 -->
	  <table class="table table-hover text-center">  <!-- 페이징 처리하는 부분 // 정렬하는 부분 넣어주기 -->
	  	<tr class="table-dark text-dark">
	  		<th>번호</th>
	  		<th>아이디</th>
	  		<th>닉네임</th>
	  		<th>성명</th>
	  		<th>생일</th>
	  		<th>성별</th>
	  		<th>최종방문일</th>
	  		<th>오늘방문횟수</th>
	  	</tr>
	  	<c:forEach var="vo" items="${vos}" varStatus="st">
	  		<c:if test="${vo.userInfor == '비공개'}">
	  		<tr>
	  			<td>${vo.idx}</td>
	  			<td>${vo.mid}</td>
	  			<td>${vo.nickName}</td>
	  			<td>${vo.name}</td>
	  			<td>${fn:substring(vo.birthday,0,10)}</td>  <!-- 날짜 형식으로 엄청 길게 나오니까 잘라서 보여주기 // 위에 fn 쓰려면 정의되어 있는지 확인 -->
	  			<td>${vo.gender}</td>
	  			<td>${fn:substring(vo.lastDate,0,10)}</td>
	  			<td>${vo.todayCnt}</td>
	  		</tr>
	  		</c:if>
	  	</c:forEach>
	  	<tr><td colspan="8" class="m-0 p-0"></td></tr>
	  </table>
	  </c:if>
  </div>
</div>
<p><br/></p>
</body>
</html>