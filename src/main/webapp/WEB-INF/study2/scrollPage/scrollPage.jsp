<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />

<c:forEach var="vo" items="${vos}" varStatus="st">
	<div class="card">
		<div class="card-bocy">
			<h3>${curScrStartNo}.</h3><img src="${ctp}/images/112.jpg" width="100px" />
		</div>
		<div class="card-footer">
			<h3>${vo.title}</h3>
			<div><p>${vo.wDate}</p></div>
		</div>
	</div>
	<br>
	<c:set var="curScrStartNo" value="${curScrStartNo - 1}" />
	<c:if test="${curScrStartNo < 1}"><h4 class="text-center text-danger"><b>더이상 표시할 게시물이 없습니다.</b></h4></c:if>  <!-- 마지막을 벗어난것 -->
</c:forEach>