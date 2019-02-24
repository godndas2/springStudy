<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h1>로그인</h1>
<form name="form1" method="post">
<table border="1" width="500px">
	<tr>
		<td>아이디</td>
		<td><input id="userid" name="userid"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" id="pwd" name="pwd"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button type="button" id="btnLogin">로그인</button>
			<c:if test="${param.message == 'nologin'}">
				<div style="color: red;">
					로그인이 필요합니다
				</div>
			</c:if>		
			<c:if test="${param.message == 'error'}">
				<div style="color: red;">
					아이디 또는 비밀번호가 일치하지 않습니다
				</div>
			</c:if>		
			<c:if test="${param.message == 'logout'}">
				<div style="color: red;">
					로그아웃되었습니다
				</div>
			</c:if>		
		</td>
	</tr>
</table>
</form>

<script type="text/javascript">
$(document).ready(function(){
	$("#btnLogin").click(function(){
		var id = $("#userid").val();
		var pwd = $("#pwd").val();
		
		if (id == null) {
			alert("아이디를 입력해주세요")
			$("#userid").focus();
			return;
		}
		if (pwd == "") {
			alert("비밀번호를 입력해주세요")
			$("#pwd").focus();
			return;
		}
		document.form1.action = "${path}/member/loginCheck";
		document.form1.submit();
	});
});
</script>
</body>
</html>