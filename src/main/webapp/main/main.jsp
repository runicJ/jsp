<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
  <title>main.jsp</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <%@ include file = "/include/bs4.jsp" %>
  <link rel="stylesheet" href="${ctp}/css/style.css" />
  <style>
  .fakeimg {
    height: 200px;
    background: #aaa;
  }
  </style>
</head>
<body>


<%@ include file = "/include/header.jsp" %>

<!-- 메뉴바(Nav) -->
<%@ include file = "/include/nav.jsp" %>

<div class="container" style="margin-top:30px">
  <div class="row">
    <div class="col-sm-4">
      <h6 class="mb-4">로그인 중인 회원 : ${sNickName}</h6>
      <h6 class="text-right">최근 가입한 회원</h6>
      <div class="fakeimg">
      	<table class="table table-striped table-hover text-center">
      	  <tr class="thead-dark">
      	    <th>아이디</th>
      	    <th>성명</th>
      	    <th>지역</th>
      	  </tr>
	        <c:forEach var="vo" items="${recentVos}">
	          <tr>
	            <td>${vo.mid}</td>
	            <td>${vo.name}</td>
	            <td>${vo.address}</td>
	          </tr>
	        </c:forEach>
        </table>
      </div>
      <p>Some text about me in culpa qui officia deserunt mollit anim..</p>
      <h3>Some Links</h3>
      <p>Lorem ipsum dolor sit ame.</p>
      <ul class="nav nav-pills flex-column">
        <li class="nav-item">
          <a class="nav-link active" href="#">Active</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#">Disabled</a>
        </li>
      </ul>
      <hr class="d-sm-none">
    </div>
    <div class="col-sm-8">
      <h2>TITLE HEADING</h2>
      <!-- <h6>Title description, Dec 7, 2017</h6> -->
      <%-- <div><img src="${ctp}/images/${mainImage1}.jpg" width="100%" height="200px" style="border-radius: 1rem"/></div> --%>
      
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
			  <!-- Indicators -->
			  <ul class="carousel-indicators">
			    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			    <li data-target="#myCarousel" data-slide-to="1"></li>
			    <li data-target="#myCarousel" data-slide-to="2"></li>
			    <li data-target="#myCarousel" data-slide-to="3"></li>
			    <li data-target="#myCarousel" data-slide-to="4"></li>
			  </ul>
			  
			  <!-- The slideshow -->
			  <div class="carousel-inner">
			    <div class="carousel-item active">
			      <img src="${ctp}/images/${mainImage1}.jpg" alt="Los Angeles" width="1100" height="400" style="border-radius: 1rem">
			    </div>
			    <div class="carousel-item">
			      <img src="${ctp}/images/${mainImage2}.jpg" alt="Chicago" width="1100" height="400" style="border-radius: 1rem">
			    </div>
			    <div class="carousel-item">
			      <img src="${ctp}/images/${mainImage3}.jpg" alt="New York" width="1100" height="400" style="border-radius: 1rem">
			    </div>
			    <div class="carousel-item">
			      <img src="${ctp}/images/${mainImage4}.jpg" alt="New York" width="1100" height="400" style="border-radius: 1rem">
			    </div>
			    <div class="carousel-item">
			      <img src="${ctp}/images/${mainImage5}.jpg" alt="New York" width="1100" height="400" style="border-radius: 1rem">
			    </div>
			  </div>
		  
			  <!-- Left and right controls -->
			  <a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
			    <span class="carousel-control-prev-icon"></span>
			  </a>
			  <a class="carousel-control-next" href="#myCarousel" data-slide="next">
			    <span class="carousel-control-next-icon"></span>
			  </a>
			</div>

      <p>Some text..</p>
      <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
      <!-- <h3>TITLE HEADING</h3> -->
      <!-- <h5>Title description, Sep 2, 2017</h5> -->
      <c:set var="today" value="<%=java.time.LocalDate.now() %>"/>
      <h5>Title description, ${today}</h5>
      <!-- <div class="fakeimg">Fake Image</div> -->
        <div class="item">
          <div class="item-img">
            <div class="skeleton_loading">
              <div class="skeleton_img"></div>
            </div>
            <img class="img" src="${ctp}/images/${mainImage2}.jpg" width="100%" height="100%" />
          </div>
          <div class="item-text">
            <div class="description">스켈레톤 애니메이션</div>
          </div>
        </div>
	    <!--스켈레톤 애니메이션 스크립트-->
      <script src="${ctp}/js/skeletonJS.js"></script>
      <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
    </div>
  </div>
</div>
<%@ include file = "../../include/footer.jsp" %>
</body>
</html>
