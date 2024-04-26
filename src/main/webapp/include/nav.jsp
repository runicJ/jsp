<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <%-- <a class="navbar-brand" href="<%=request.getContextPath()%>/">Home</a> --%>
  <a class="navbar-brand" href="http://192.168.50.57:9090/javaclass">Home</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">  <!-- 햄버거 메뉴 포함 -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="#">Guest</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Board</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">PDS</a>  <!-- posting data system 자료실 -->
      </li>    
      <li class="nav-item">
        <!-- <a class="nav-link" href="study.jsp">Study</a> -->
        <div class="dropdown">
			    <button type="button" class="btn btn-dark dropdown-toggle" data-toggle="dropdown">
			      Study1
			    </button>
			    <div class="dropdown-menu">
			      <a class="dropdown-item" href="<%=request.getContextPath() %>/study/0426/t01.jsp">서버환경</a>
			      <a class="dropdown-item" href="<%=request.getContextPath() %>/study/0426/t02.jsp">성적계산</a>
			      <a class="dropdown-item" href="<%=request.getContextPath() %>/study/0427_storage/t1_Cookies.jsp">쿠키연습</a>
			    </div>
  		</div>
      </li>
      <li style="float:right; padding:5px; border:none; font-size: 14px; margin-right: 16px;">
      	<div class="search-container" style="float:right;">
    			<form action="/action_page.php">
			      <input type="text" placeholder="Search.." name="search">
			      <button type="submit"><i class="fa fa-search"></i></button>
			    </form>
			  </div>
      </li>
    </ul>
  </div>  
</nav>

<!-- .topnav .search-container {
  float: right;
}

.topnav input[type=text] {
  padding: 6px;
  margin-top: 8px;
  font-size: 17px;
  border: none;
}

.topnav .search-container button {
  float: right;
  padding: 6px 10px;
  margin-top: 8px;
  margin-right: 16px;
  background: #ddd;
  font-size: 17px;
  border: none;
  cursor: pointer;
}

.topnav .search-container button:hover {
  background: #ccc;
} -->
