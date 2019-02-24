<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h1>장바구니</h1>
<c:choose>
	<c:when test="${map.count == 0}">
		장바구니가 비어있습니다
	</c:when>
	<c:otherwise>
		<form id="form1" name="form1" method="post" action="${path}/mall/cart/update">
			<table border="1" width="500px">
				<tr>
					<th>상품명</th>
					<th>원가</th>
					<th>수량</th>
					<th>금액</th>
					<th>&nbsp;</th>
				</tr>
				<c:forEach var="row" items="${map.list}"> 
					<tr align="center">
						<td><fmt:formatNumber value="${row.price}" pattern="#,###,###" /></td>
						<td><input type="number" name="count" style="width: 30px;" value="<fmt:formatNumber value="${row.count}" pattern="#,###,###" />">
						<input type="hidden" name="cartID" value="${row.cartID}">
						</td>
						<td><fmt:formatNumber value="${row.money}" pattern="#,###,###" /></td>
						<td><a href="${path}/mall/cart/delete?cartID=${row.cartID}">[삭제]</a></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5" align="right">
						합계 :
						<fmt:formatNumber value="${map.totalMoney}" pattern="#,###,###" /><br/>
						배송비 : ${map.free}<br/>
						합계 : <fmt:formatNumber value="${map.sum}" pattern="#,###,###" />
					</td>
				</tr>
			</table>
			<button id="btnUpdate">수정</button>
			<button type="button" id="btnDelete">장바구니 초기화</button>
		</form>
	</c:otherwise>
</c:choose>
</body>
</html>