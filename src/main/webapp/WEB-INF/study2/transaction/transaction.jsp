<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>transaction.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
  <script>
    'use strict';
    
    function bankBookSearch() {
    	let mid = myform.mid.value;
    	$.ajax({
    		url  : "TransactionBankBook.st",
    		type : "post",
    		data : {mid : mid},
    		success:function(res) {
    			demo.innerHTML = res;
    		},
    		error:function() {
    			alert("전송오류");
    		}
    	});
    }
    
    function fCheck(flag) {
    	if(flag == 1) {
    		myform.action = "TransactionTest1.st";
    	}
    	else {
    		myform.action = "TransactionTest2.st";
    	}
    	myform.error.value = "error";  // 일부러 에러 발생  // 정상도 에러로 뜸
    	myform.submit();
    }
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
  <h2>트랜잭션 연습</h2>
  <hr>
  <pre>
  	트랜잭션(Transaction)이란, 데이터베이스의 상태를 변화시키기 위해 수행하는 작업의 단위를 뜻한다.
  	여기서 작업의 단위는 질의어(SQL) 한 문장이 아닌, 작업 시간에서 종료(확인)될때까지의 흐름을 모두 포함한다.
  	즉, 게시판에 글을 등록한다면 글을 등록하는 'insert'와 등록된 글을 보여주는 'select'질의어가 정상적으로 종료될때까지의 시점을 말한다.
  	트랜잭션은 'ACID'라는 '원자성/일관성/독립성(격리성)/지속성'을 보장해야 한다.
  	
  	1. 원자성(Atomicity) :
		트랜잭션이 데이터베이스에 모두 반영되던가, 아니면 전혀 반영되지 않아야 한다는 것이다.
		트랜잭션은 사람이 설계한논리적인 작업 단위로서, 일처리는 작업단위 별로 이루어 져야 사람이 다루는데 무리가 없다.
		만약 트랜잭션 단위로 데이터가 처리되지 않는다면, 설계한 사람은 데이터 처리 시스템을 이해하기 힘들 뿐만 아니라, 오작동 했을시 원인을 찾기가 매우 힘들어질것이다.
		​
		2. 일관성(Consistency)
		트랜잭션의 작업 처리 결과가 항상 일관성이 있어야 한다는 것이다.
		트랜잭션이 진행되는 동안에 데이터베이스가 변경 되더라도 업데이트된 데이터베이스로 트랜잭션이 진행되는것이 아니라, 처음에 트랜잭션을 진행 하기 위해 참조한 데이터베이스로 진행된다. 
		이렇게 함으로써 각 사용자는 일관성 있는 데이터를 볼 수 있는 것이다.
		​
		3. 독립성(Isolation)
		독립성은 둘 이상의 트랜잭션이 동시에 실행되고 있을 경우 어떤 하나의 트랜잭션이라도, 다른 트랜잭션의 연산에 끼어들 수 없다는 점을 가리킨다.하나의 특정 트랜잭션이 완료될때까지, 다른 트랜잭션이 특정 트랜잭션의 결과를 참조할 수 없다.
		​
		4. 지속성(Durability)
		지속성은 트랜잭션이 성공적으로 완료됬을 경우, 결과는 영구적으로 반영되어야 한다는 점이다. 즉, 그 결과가 항상 기록되어야 한다. 그래야 중간에 시스템에 문제가 발생해도 데이터베이스 로그등을 사용해서 성공한 프랜잭션 내용을 복구할수 있게한다.
		
		■ 트랜잭션의 연산에는 Commit, Rollback 연산이 있다.
		
		- Commit : 하나의 트랜잭션이 성공적으로 끝났고, 데이터베이스가 일관성있는 상태에 있을때, 하나의 트랜잭션이 끝났다라는것을 알려주기위해 사용하는 연산이다. 
							즉, 모든 작업이 정상적으로 성공하는 경우 데이터베이스에 정상 반영하는 것을 커밋(commit)이라한다.
							이 연산을 사용하면 수행했던 트랜잭션이 로그에 저장되며, 후에 Rollback연산을 수행했었던 트랜잭션단위로 처리되는것을 도와준다.
		- Rollback : 하나의 트랜잭션 처리가 비정상적으로 종료되어 트랜잭션의 원자성이 깨진경우, 트랜잭션을 처음부터 다시 시작하거나, 트랜잭션의 부분적으로만 연산된결과를 다시 취소시킨다. 
								즉, 작업 중 하나라도 실패를 해서 거래 이전으로 되돌리는 것을 롤백(rollback)이라한다.
								
		□ 수행처리는 기본적으로 자동 커밋이 설정되어 있으며, 트랜잭션을 처리하기 위해서는 '수동커밋'으로 시작해야 한다.
  </pre>
  <hr>
  <form name="myform" method="post">
    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text">거래자아이디</span>
      </div>
      <input type="text" name="mid" value="hkd1234" class="form-control"/>
    </div>
    <div class="custom-control custom-radio custom-control-inline mb-3">
	    <input type="radio" class="custom-control-input" name="bankFlag" id="customRadio1" value="입금" checked>
	    <label class="custom-control-label" for="customRadio1">입금</label>
	  </div>
	  <div class="custom-control custom-radio custom-control-inline">
	    <input type="radio" class="custom-control-input" name="bankFlag" id="customRadio2" value="출금">
	    <label class="custom-control-label" for="customRadio2">출금</label>
	  </div>
    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text">입출금액</span>
      </div>
      <input type="number" name="money" value="1000" class="form-control"/>
    </div>
    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text">적요</span>
      </div>
      <input type="text" name="content" value="군것질" class="form-control"/>
    </div>
	  <div>
	    <input type="button" value="현재잔고확인" onclick="bankBookSearch()" class="btn btn-info"/>
	    <input type="button" value="트랜잭션미적용(입출금)" onclick="fCheck(1)" class="btn btn-success"/>
	    <input type="button" value="트랜잭션적용(입출금)" onclick="fCheck(2)" class="btn btn-primary"/>
	  </div>
	  <input type="hidden" name="error"/>
  </form>
  <hr/>
  <div id="demo"></div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>