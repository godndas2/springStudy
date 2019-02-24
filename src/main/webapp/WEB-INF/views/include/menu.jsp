<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="path" value="${pageContext.request.contextPath}" />
<div style="text-align: center;">
<a href="${path}/">Main</a> ||
<a href="${path}/member/list">회원관리</a> ||
<a href="${path}/upload/upload">파일업로드</a> ||
<a href="${path}/mall/product/list">상품목록</a> ||
<a href="${path}/chart/googleChart">차트 보기</a> ||
<c:choose>
	<c:when test="${sessionScope.userid == null}">
		<a href="${path}/member/login">로그인</a> ||
		<a href="${path}/admin/login">관리자 로그인</a>
	</c:when>
	<c:otherwise>
		<span style="color: blue;">${sessionScope.userid}님 환영합니다</span>
		<a href="${path}/member/logout">로그아웃</a>
	</c:otherwise>
</c:choose>
</div>
<hr/>