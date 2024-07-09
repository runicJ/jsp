<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>photoGalleryList.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
    'use strict';
    
    function photoSearch() {
    	let part = $("#part").val();
    	let choice = $("#choice").val();
    	
    	location.href = "PhotoGallery.ptg?part="+part+"&choice="+choice;
    }
    
    
    // 무한 스크롤 구현(aJax처리)
    let lastScroll = 0;
    let curPage = 1;
    
    $(document).scroll(function(){
    	let currentScroll = $(this).scrollTop();			// 스크롤바 위쪽시작 위치, 처음은 0이다.
    	let documentHeight = $(document).height();		// 화면에 표시되는 전체 문서의 높이
    	let nowHeight = $(this).scrollTop() + $(window).height();	// 현재 화면상단 + 현재 화면높이
    	
    	// 스크롤이 아래로 내려갔을때 이벤트 처리..
    	if(currentScroll > lastScroll) {
    		if(documentHeight < (nowHeight + (documentHeight*0.1))) {
    			console.log("다음페이지 가져오기");
    			curPage++;
    			//getList(curPage);
    			$.ajax({
  	    		url  : "PhotoGalleryPaging.ptg",
  	    		type : "post",
  	    		data : {pag : curPage},
  	    		success:function(res) {
  	    			$("#list-wrap").append(res);
  	    		}
  	    	});
    		}
    	}
    	lastScroll = currentScroll;
    });
    
    // 리스트 불러오기 함수(ajax처리)
    function getList(curPage) {
    	$.ajax({
    		url  : "PhotoGallery.ptg",
    		type : "post",
    		data : {pag : curPage},
    		success:function(res) {
    			$("#list-wrap").append(res);
    		}
    	});
    }
  </script>
  <style>
    .container {
      width: 1000px;
      margin: 0 auto;
    }
    .card {
      float:left;
      margin: 10px;
    }
  </style>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <table class="table table-borderless">
    <tr>
      <td colspan="2"><h2 class="text-center">포토 갤러리</h2></td>
    </tr>
    <tr>
      <td>
        <div class="input-group">
          <div>
	          <select name="part" id="part" class="form-control mr-1" style="width:100px">
		          <option value="전체" ${part == '전체' ? 'selected' : ''}>전체</option>
		          <option value="풍경" ${part == '풍경' ? 'selected' : ''}>풍경</option>
		          <option value="인물" ${part == '인물' ? 'selected' : ''}>인물</option>
		          <option value="음식" ${part == '음식' ? 'selected' : ''}>음식</option>
        			<option value="여행" ${part == '여행' ? 'selected' : ''}>여행</option>
		          <option value="학습" ${part == '학습' ? 'selected' : ''}>학습</option>
		          <option value="사물" ${part == '사물' ? 'selected' : ''}>사물</option>
		          <option value="기타" ${part == '기타' ? 'selected' : ''}>기타</option>
		        </select>
	        </div>
	        <div class="input-group-append">
		        <select name="choice" id="choice" class="form-control mr-1" style="width:100px">
		          <option value="최신순" ${choice == '최신순' ? 'selected' : ''}>최신순</option>
		          <option value="추천순" ${choice == '추천순' ? 'selected' : ''}>추천순</option>
		          <option value="조회순" ${choice == '조회순' ? 'selected' : ''}>조회순</option>
		          <option value="댓글순" ${choice == '댓글순' ? 'selected' : ''}>댓글순</option>
		        </select>
          </div>
          <div class="input-group-append">
            <input type="button" value="조건검색" onclick="photoSearch()" class="btn btn-primary"/>
          </div>
        </div>
      </td>
      <td class="text-right">
        <input type="button" value="사진올리기" onclick="location.href='PhotoGalleryInput.ptg';" class="btn btn-success"/>
        <input type="button" value="한장씩보기" onclick="location.href='PhotoGallerySingle.ptg';" class="btn btn-info mr-2"/>
      </td>
    </tr>
  </table>
  <section id="list-wrap">
	  <c:forEach var="vo" items="${vos}" varStatus="st">
	    <!-- <div class="card" style="width:25%;"> -->
	    <div class="card mb-5" style="width:220px;">
		    <%-- <div class="card-body m-0 p-2"><img src="${ctp}/images/photoGallery/${vo.fSName}" width="100%" height="150px" title="${vo.title}" class="m-0" /></div> --%> 
		    <div class="card-body m-0 p-2 text-center">
		      <a href="PhotoGallerContent.ptg?idx=${vo.idx}">
		        <img src="${ctp}/images/photoGallery/${vo.fSName}" width="200px" height="150px" title="${vo.title}" class="m-0" />
		      </a>
		    </div> 
		    <div class="card-footer">
		      <div class="row text-center" style="font-size:11px">
		        <div class="col p-0"><i class="fa-regular fa-pen-to-square" title="댓글수"></i> ${vo.replyCnt}</div>
		        <div class="col p-0"><i class="fa-regular fa-face-grin-hearts" title="좋아요"></i> ${vo.goodCount}</div>
		        <div class="col p-0"><i class="fa-regular fa-eye" title="조회수"></i> ${vo.readNum}</div>
		        <div class="col p-0"><i class="fa-solid fa-layer-group" title="사진수"></i> ${vo.photoCount}</div>
		      </div>
		    </div>
		  </div>
	  </c:forEach>
  </section>
</div>
<p style="clear:both;"><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>