<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<style type="text/css">
iframe {
	width : 400px;
	height: 300px;
	border: 1px;
	border-style: groove; <!-- solid -->
}
</style>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
	<!-- target : iframe1로 지정해주고 iframe으로 전송해준다 -->
	<form id="form1" action="${path}/upload/upload" method="post" enctype="multipart/form-data" target="iframe1">
	<input type="file" name="file">
	<input type="submit" name="파일 업로드">
	</form>
	
	<iframe name="iframe1"></iframe>
</body>
</html>