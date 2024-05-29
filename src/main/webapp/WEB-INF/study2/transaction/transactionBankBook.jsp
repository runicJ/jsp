<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  <!-- 화폐단위 때문에 사용 -->
<c:set var="ctp" value="${pageContext.request.contextPath}" />  <!-- 조각난 jsp 밑이 필요없음 -->

<table class="table table-hover text-center">
  <tr class="table-dark text-dark">
    <th>번호</th>
    <th>성명</th>
    <th>거래내역</th>
  </tr>
  <c:set var="sumBalance" value="0"/>
	<c:forEach var="vo" items="${vos}" varStatus="st">
	  <tr>
		  <td>${st.count}</td>
		  <td>${vo.mid}</td>
		  <td><fmt:formatNumber value="${vo.balance}" /></td>
	  </tr>
	  <c:set var="sumBalance" value="${sumBalance + vo.balance}"/>
	</c:forEach>
  <tr style="background-color:orange">
    <th colspan="2">현재 잔고</th>
    <td><fmt:formatNumber value="${sumBalance}"/></td>
  </tr>
  <tr><td colspan="3" class="m-0 p-0"></td></tr>
</table>