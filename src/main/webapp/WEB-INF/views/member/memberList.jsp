<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%--     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<%--     <c:set var="path" value="${pageContext.request.contextPath}" /> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>회원 목록</h2>
<input type="button" value="회원등록" onclick="location.href='${path}/member/insert'">
<table border="1" width="800px">
	<tr>
		<td>아이디</td>
		<td>이름</td>
		<td>이메일</td>
		<td>가입일</td>
	</tr>
	<c:forEach var="row" items="${list}">
		<tr>
			<td>${row.userid}</td>
			<td>
			<a href="${path}/member/read?userid=${row.userid}">${row.name}</a>
			</td>
			<td>${row.email}</td>
			<td><fmt:formatDate value="${row.joinDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>