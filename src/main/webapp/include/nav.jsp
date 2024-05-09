<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<%
	int level = session.getAttribute("sLevel")==null ? 999 : (int) session.getAttribute("sLevel");
	pageContext.setAttribute("level", level);
%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <%-- <a class="navbar-brand" href="<%=request.getContextPath()%>/">Home</a> --%>
  <a class="navbar-brand" href="http://192.168.50.57:9090/javaclass/Main">Home</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">  <!-- 햄버거 메뉴 포함 -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="${ctp}/guest/GuestList">Guest</a>  <!-- 사실 겹칠일 없으니까 그냥 경로 GuestList 해도됨 -->
      </li>
<%-- <%		if(!mid_.equals("")) { %> --%>
			<c:if test="${level <= 4}">  <!-- jstl 여기서 등급에 따라 보여줄 부분 설정 -->
	      <li class="nav-item">
	        <a class="nav-link" href="#">Board</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">PDS</a>  <!-- posting data system 자료실 -->
	      </li>    
	      <li class="nav-item mr-2">
	        <!-- <a class="nav-link" href="study.jsp">Study</a> -->
	        <div class="dropdown">
				    <button type="button" class="btn btn-dark dropdown-toggle" data-toggle="dropdown">
				      Study1
				    </button>
				    <div class="dropdown-menu">
				      <a class="dropdown-item" href="<%=request.getContextPath() %>/study/0426/t01.jsp">서버환경</a>
				      <a class="dropdown-item" href="<%=request.getContextPath() %>/study/0426/t02.jsp">성적계산</a>
				      <a class="dropdown-item" href="<%=request.getContextPath() %>/study/homework/ex2_GuGuDan.jsp">구구단계산</a>
				      <a class="dropdown-item" href="<%=request.getContextPath() %>/study/0427_storage/t1_Cookies.jsp">쿠키연습</a>
				      <a class="dropdown-item" href="<%=request.getContextPath() %>/study/0427_storage/t2_Session.jsp">세션연습</a>
				      <a class="dropdown-item" href="<%=request.getContextPath() %>/study/0427_storage/t3_Application.jsp">어플리케이션연습</a>
				      <a class="dropdown-item" href="<%=request.getContextPath() %>/study/0427_storage/t4_StorageTest.jsp">저장소연습</a>
				      <a class="dropdown-item" href="<%=request.getContextPath() %>/study/homework/ex1_Login.jsp">아이디저장연습</a>
				      <a class="dropdown-item" href="<%=request.getContextPath() %>/study/0429_JSTL/el1.jsp">EL연습</a>
				      <a class="dropdown-item" href="<%=request.getContextPath() %>/study/0429_JSTL/jstl1.jsp?jumsu=85&code=K">JSTL연습1</a>
				      <a class="dropdown-item" href="<%=request.getContextPath() %>/study/0430_web_xml/filter/t1_Filter.jsp">Filter한글연습</a>
				      <%-- <a class="dropdown-item" href="<%=request.getContextPath() %>/study/0430_web_xml/filter/t2_Certification.jsp">인증코드발행(관리자)</a> --%>  <!-- 원래 관리자창에 있어야 하는 것 -->
				      <a class="dropdown-item" href="<%=request.getContextPath() %>/study/0430_web_xml/init/t03_Init.jsp">초기값확인</a>
				      <a class="dropdown-item" href="<%=request.getContextPath() %>/study/0430_web_xml/lifeCycle/lifeCycle2.jsp">서블릿 생명주기</a>
				      <a class="dropdown-item" href="${pageContext.request.contextPath}/study/database/LoginList">데이터베이스연습</a>
				    </div>
	  			</div>
	      </li>
	      <li class="nav-item mr-2">
	        <!-- <a class="nav-link" href="study.jsp">Study</a> -->
	        <div class="dropdown">
				    <button type="button" class="btn btn-dark dropdown-toggle" data-toggle="dropdown">
				      Study2
				    </button>
				    <div class="dropdown-menu">
				      <a class="dropdown-item" href="${ctp}/study/password/passCheck.jsp">비밀번호암호화</a>
				      <a class="dropdown-item" href="${ctp}/mapping/test1">디렉토리매핑연습</a>  <!-- url pattern -->
				      <a class="dropdown-item" href="${ctp}/mapping/test2.do">확장자매핑연습</a>  <!-- 무조건 뒤에 확장자 써야 -->
				      <a class="dropdown-item" href="${ctp}/mapping/test3.do3">확장자매핑연습3</a>
				      <a class="dropdown-item" href="${ctp}/mapping/test4.do4">확장자매핑연습4</a>
				      <a class="dropdown-item" href="${ctp}/mapping/test5.do5">확장자매핑연습5</a>
				      <a class="dropdown-item" href="${ctp}/ajaxTest1.st">AJAX연습(일반)</a>  <!-- lastIndex에서 /기준으로 자르면 // 확장자 뭘하건 관계없음 -->
				      <a class="dropdown-item" href="${ctp}/ajaxTest2.st">AJAX연습(응용)</a>
				      <a class="dropdown-item" href="${ctp}/ajaxTest3.st">AJAX연습(회원관리)</a>
				      <a class="dropdown-item" href="${ctp}/uuidForm.st">UUID연습</a>  <!-- study 확장자 패턴 studyController(.st) -->
				      <a class="dropdown-item" href="${ctp}/study/database/login.jsp">로그인연습</a>
				    </div>
	  			</div>
	      </li>
<%-- <%		} %> --%>
		</c:if>
	  </ul>
		<ul class="navbar-nav ml-auto">
			<c:if test="${level <= 4}">
      <li class="nav-item">
<%-- <%		if(!mid_.equals("")) { %> --%>
        <li><a class="nav-link ml-3" href="#">${sMid} 님 환영합니다!</a></li>
        <a class="nav-link" href="${ctp}/MemberLogout.mem">Logout</a>
      </li>
      </c:if>
<%-- <%		} else { %> --%>
			<c:if test="${level > 4}">
			<li class="nav-item">
				<a class="nav-link" href="${ctp}/MemberLogin.mem">Login</a>			
      </li>
			</c:if>  
<%-- <%		} %> --%>
      <c:if test="${level > 4}">
    	<li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath() %>/MemberJoin.mem">Join</a>
      </li>
      </c:if>
    </ul>
  </div>  
</nav>
