<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>modal2.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
  	'use strict';
  	
  	function fCheck2(name) {
  		//let name = myform.name.value;  // form을 내가 만들어 놨으면 그냥 뿌리면 됨  // 지금 myform 안만듦 한단계 건너뜀
  		$("#myModal2 #modalName1").text(name);  // 값으로 던질래(val(name)), text로 던질래
  		$("#myModal2 #modalName2").text('${mVo.name}');
  	}
  	
  	function ModalCheck() {  // 값을 가져온 상태 '아이디 검색' 누르면 mid 가져온 상태
  		let mid = myform.mid.value;
  		location.href="Modal2.st?mid="+mid;
  	}
  	
  	function fCheck3() {
  		$("#myModal3 #modalName3").text('${mVo.name}');
  	}
  	
  	function fCheck4(name, mid, nickName) {
  		$("#myModal4 #modalName").text(name);
  		$("#myModal4 #modalMid").text(mid);
  		$("#myModal4 #modalNickName").text(nickName);
  	}
  	
  	function nameCheck() {
  		
  	}
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2>Modal 연습2</h2>  <!-- 모달을 만들어놓고 a태그로 값을 가져온다 -->
  <hr>
  <p>
  	<input type="button" value="폼내용출력" class="btn btn-primary" data-toggle="modal" data-target="#myModal1"/>  <!-- button태그로 하면 script 출력이 안될 때가 있음. input은 그나마 되나, a태그로 사용(모달에서는) -->
  	<a href="#" onclick="fCheck2('홍길동')" class="btn btn-success" data-toggle="modal" data-target="#myModal2">폼내용출력2</a>  <!-- 현재창(#)에서 js를 불러야 함 / 버튼 누르면 onclick이벤트 발생 -->
  	<a href="#" onclick="fCheck3()" class="btn btn-secondary" data-toggle="modal" data-target="#myModal3">폼내용출력3</a>  <!-- fCheck3('홍길동') -->
  	<a href="#" onclick="fCheck4('${mVo.name}','${mVo.mid}','${mVo.nickName}')" class="btn btn-warning" data-toggle="modal" data-target="#myModal4">폼내용출력4</a>  <!-- 모달에 이미 가져온 값을 출력 -->
  </p>
	<form name="myform">
		<div class="input-group">아이디 &nbsp;
			<div class="input-group-prepend"><input type="text" name="mid" id="mid" placeholder="아이디를 입력하세요" class="form-control" /></div>
			<div class=""><input type="button" value="아이디찾기" onclick="ModalCheck()" class="btn btn-info" /></div>
		</div>
	</form>
</div>
<p><br/></p>

  <!-- The Modal -->
  <div class="modal fade" id="myModal1">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Modal Heading</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          Modal body..
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
  
  <!-- The Modal -->
  <div class="modal fade" id="myModal2">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Modal Heading</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          성명 : <span id="modalName1"></span>  <!-- 줄 바뀌면 안되니까 span태그로 줌 -->
          성명2 : <span id="modalName2"></span>  <!-- db에서 가져온 것 -->
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
  
  <!-- The Modal -->
  <div class="modal fade" id="myModal3">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Modal Heading</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          성명3 : <span id="modalName3"></span>  <!-- db에서 가져온 것 -->
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
  
  <!-- The Modal -->
  <div class="modal fade" id="myModal4">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Modal Heading</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form name="midModalForm">
        		<!-- 아이디 : 
        		<input type="text" name="mid" id="mid" placeholder="아이디를 입력하세요" class="form-control mb-2" required />
        		<input type="button" value="성명확인" onclick="nameCheck()" class="btn btn-danger form-control" />  -->
        		아이디 : <span id="modalMid"></span><br>
        		성명 : <span id="modalName"></span><br>
        		닉네임 : <span id="modalNickName"></span><br>
        	</form>
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