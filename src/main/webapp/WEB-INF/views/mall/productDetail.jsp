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
<h1>상품 정보</h1>

<table>
	<tr>
		<td>
			<img src="${path}/image/${dto.imgURL}" width="300px" height="300px">
		</td>
		<td align="center">
			<table>
				<tr>
					<td>상품명</td>
					<td>${dto.productName}</td>
				</tr>
				<tr>
					<td>가격</td>
					<td>${dto.price}</td>
				</tr>
				<tr>
					<td>상품 설명</td>
					<td>${dto.description}</td>
				</tr>
				<tr>
					<td colspan="2">
						<form name="form1" method="post" action="${path}/cart/insert">
							<input type="hidden" name="productID" value="${dto.productID}">
							<select name="count">
								<c:forEach begin="1" end="10" var="i">
									<option value="${i}">${i}</option>
								</c:forEach>
							</select>&nbsp;개
							
							<input type="submit" value="장바구니 담기">
						</form>
						<a href="${path}/mall/product/list">상품 목록</a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>