<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script src="${path}/ckeditor/ckeditor.js"></script>
<c:if test="${sessionScope.adminUserId == null }">
	<script type="text/javascript">
		alert("로그인 후 사용할 수 있습니다");
		location.href = "${path}/admin/login";
	</script>
</c:if>
<script type="text/javascript">
	function productInsert(){
		var productName = document.form1.productName.value;
		var price = document.form1.price.value;
		var description = document.form1.description.value;
		if (productName == "") {
			alert("상품명을 입력해주세요")
			document.form1.productName.focus();
			return;
		}
		if (price == "") {
			alert("가격을 입력해주세요")
			document.form1.price.focus();
			return;
		}
		// CKEDITOR 실습을 할 땐 아래 IF문을 주석으로 막아주세요
// 		if (description == "") {
// 			alert("상품설명을 입력해주세요")
// 			document.form1.description.focus();
// 			return;
// 		}
		document.form1.action = "/mall/product/insert";
		document.form1.submit();
	}
</script>
</head>
<body>
<%@ include file="../include/adminMenu.jsp" %>
<h2>상품 등록</h2>
<form id="form1" name="form1" method="post" enctype="multipart/form-data">
<table>
	<tr>
		<td>상품명</td>
		<td><input name="productName"></td>
	</tr>
	<tr>
		<td>가격</td>
		<td><input name="price"></td>
	</tr>
	<tr>
		<td>설명</td>
		<td><textArea rows="5" cols="60" name="description" id="description"></textArea></td>
	</tr>
	<!-- id : description인 태그에 적용-->
	<script>
		CKEDITOR.replace("description", {
			filebrowserUploadUrl : "${path}/imageUpload"
		});
	</script>
	<tr>
		<td>이미지</td>
		<td><input type="file" name="file1"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" value="등록" onclick="productInsert()">
			<input type="button" value="목록" onclick="location.href='${path}/admin/product/list'">
		</td>
	</tr>
</table>


</form>
</body>
</html>