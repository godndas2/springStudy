<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript">
function productUpdate() {
	document.form1.action = "${path}/mall/product/update";
	document.form1.submit();
}
function productDelete() {
	document.form1.action = "${path}/mall/product/delete";
	document.form1.submit();
}

</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h1>상품 정보 편집</h1>
<form name="form1" method="post" enctype="multipart/form-data">
<table border="1" width="500px">
	<tr>
		<td>상품명</td>
		<td><input name="productName" value="${dto.productName}"></td>
	</tr>
	<tr>
		<td>가격</td>
		<td><input name="price" value="${dto.price}"></td>
	</tr>
	<tr>
		<td>상품설명</td>
		<td><textArea rows="5" cols="60" name="description" id="description">${description}</textArea></td>
	</tr>
	<tr>
		<td>상품이미지</td>
		<td>
			<img src="${path}/image/${dto.imgURL}" width="300px" height="300px"><br/>
			<input type="file" name="fileA">
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="hidden" name="productID" value="${dto.productID}">
			<input type="button" value="수정" onclick="productUpdate()">
			<input type="button" value="삭제" onclick="productDelete()">
			<input type="button" value="목록" onclick="location.href='${path}/mall/product/list'">
		</td>
	</tr>
</table>

</form>
</body>
</html>