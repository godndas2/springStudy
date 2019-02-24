<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <a href="${path}/mall/product/list">상품목록</a> ||
    <a href="${path}/mall/product/insert">상품등록</a> ||
<c:choose>
	<c:when test="${sessionScope.userid == null}">
		<a href="${path}/admin/login">관리자 로그인</a>
	</c:when>
	<c:otherwise>
		${sessionScope.adminName}님이 로그인 중입니다
		<a href="${path}/admin/logout">로그아웃</a>
	</c:otherwise>
</c:choose>