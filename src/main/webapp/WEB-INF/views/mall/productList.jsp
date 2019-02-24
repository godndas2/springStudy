<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript">
$(function() {
	$("#btnAdd").click(function() {
		location.href = "${path}/mall/product/insert";
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h1>상품 목록</h1>
<!-- 관리자가 아니면 상품등록 버튼이 안보임 -->
<c:if test="${sessionScope.admin.userid != null}">
	<button type="button" id="btnAdd">상품 등록</button>
</c:if>

<table border="1" width="500px">
	<tr>
		<th>상품 코드</th>
		<th>상품 이미지</th>
		<th>상품명</th>
		<th>가격</th>
	</tr>
<c:forEach var="row" items="${list}">
	<tr align="center">
		<td>${row.productID}</td>
		<td><img src="${path}/image/${row.imgURL}" width="100" height="100"></td>
		<td><a href="${path}/mall/product/detail/${row.productID}">${row.productName}</a>
		<c:if test="${sessionScope.adminUserId != null }"><br/>
		<a href="${path}/mall/product/edit/${row.productID}">[편집]</a>
		</c:if>
		</td>
		<td><fmt:formatNumber value="${row.price}" pattern="#,###" /></td>
	</tr>
</c:forEach>
</table>
</body>
</html>