<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%@ include file = "/include/bs4.jsp" %>
  <title>t02Res.jsp</title>
</head>
<body>
<%@ include file = "/include/header.jsp" %>
<%@ include file = "/include/nav.jsp" %>
<p><br/></p>
<div class="container">
  <h2>성적 처리 결과</h2>
  <hr/>
  <p>성명 : ${vo.name} / ${param.name}</p>  <!-- 따로따로 안넣으면 그냥 ${name} -->
  <p>학번 : ${vo.hakbun} / ${param.hakbun}</p> <!-- vo객체 header에 있는 것을 읽어온 것 / get방식(EL에서 사용하는 param이란 명령어) -->
  <p>국어 : ${vo.kor} / ${param.kor}</p>
  <p>영어 : ${vo.eng} / ${param.eng}</p>
  <p>수학 : ${vo.mat} / ${param.mat}</p>
  <p>사회 : ${vo.soc} / ${param.soc}</p>
  <p>과학 : ${vo.sci} / ${param.sci}</p>
  <p>총점 : ${vo.tot} / ${param.tot}</p>
  <p>평균 : ${vo.avg} / ${param.avg}</p>
  <p>학점 : ${vo.grade} / ${param.grade}</p>
  <hr/>
  <p><a href="<%=request.getContextPath() %>/study/0426/t02.jsp" class="btn btn-warning">돌아가기</a></p>
</div>
<p><br/></p>
<%@ include file = "/include/footer.jsp" %>
</body>
</html>