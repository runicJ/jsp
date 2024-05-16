<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>modal1.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2>Modal 연습1</h2>  <!-- controller를 통해 command에서 값을 가져와서 뿌리는 것이 아니라 미리 가져와서 현 화면에서 모달을 사용하기 때문에 뿌려주기만 하는 것임(view에다가 삽입하기 위해 미리 db에서 값을 가져왔어야 함) -->
  <hr>
  <p>
  	<input type="button" value="표준모달창" class="btn btn-primary mr-2" data-toggle="modal" data-target="#myModal1" />
  	<input type="button" value="부드러운모달창" class="btn btn-secondary mr-2" data-toggle="modal" data-target="#myModal2" />
  	<input type="button" value="큰모달창" class="btn btn-warning mr-2" data-toggle="modal" data-target="#myModal3" />
  	<input type="button" value="모달중앙출력" class="btn btn-success" data-toggle="modal" data-target="#myModal4" />
  	<input type="button" value="모달스크롤1" class="btn btn-secondary mr-2" data-toggle="modal" data-target="#myModal5" />
  	<input type="button" value="모달스크롤2" class="btn btn-secondary" data-toggle="modal" data-target="#myModal6" />
  </p>
</div>
<p><br/></p>

  <!-- The Modal1 -->
  <div class="modal" id="myModal1">
    <div class="modal-dialog">
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
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
  
  <!-- The Modal2 -->
  <div class="modal fade" id="myModal2">
    <div class="modal-dialog">
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
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
  
  <!-- The Modal3 -->
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
          Modal body..
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