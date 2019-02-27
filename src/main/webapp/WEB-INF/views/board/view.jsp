<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script src="${path}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
$(function(){
	listReply("1"); // 댓글의 목록을 뿌려줌
	$("#btnReply").click(function() {
		reply();
	});
	$("#btnList").click(function() {
		location.href = "${path}/board/list";
	});
});
function reply() {
	var replytext = $("#replytext").val();
	var bno = "$(dto.bno)";
	var param = {"replytext" : replytext, 
				 "bno" : bno};
	$.ajax({
		type : "post",
		url : "${path}/reply/insert",
		data : param,
		success : function() {
			alert("댓글이 등록되었습니다");
			listReply("1"); // 댓글의 목록을 뿌려줌
		}
	});
}
function listReply(num) {
	$.ajax({
		type : "post",
		url : "${path}/reply/list?bno=${dto.bno}&curPage="+num,
		success : function(result) {
			$("#listReply").html(result);
		}
	});
}
</script>
<style type="text/css">
.fileDrop {
	width: 500px;
	height: 100px;
	border: 1px dotted gray;
	background-color: gray;
}
</style>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>게시물 상세</h2>
<form id="form1" name="form1" method="post">
<div>작성일 : <fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd a HH:mm:ss" /></div>
<div>조회수 : ${dto.viewcnt}</div>
<div>이름 : ${dto.name}</div>
<div>제목 : <input name="title" value="${dto.title}"></div>
<div style="width: 80%;"> 내용 :
<textarea rows="3" cols="80" name="content" id="content">${dto.content}</textarea>
</div>
<script type="text/javascript">
CKEDITOR.replace("content", {
	filebrowserUploadUrl : "${path}/imageUpload",
	height : "500px"
});
</script>
<div id="uploadedList"></div>
<div class="fileDrop"></div>
<div>
	<input type="hidden" name="bno" value="${dto.bno}">
	<c:if test="${sessionScope.userid == dto.writer}">
		<button type="button" id="btnUpdate">수정</button>
		<button type="button" id="btnDelete">삭제</button>
	</c:if>
		<button type="button" id="btnList">목록</button>
</div>
</form>
<!-- 댓글 -->
<div style="width: 700px; text-align: center;">
<c:if test="${sessionScope.userid != null }">
	<textarea rows="5" cols="80" id="replytext" placeholder="댓글"></textarea><br/>
	<button type="button" id="btnReply">댓글 작성</button>
</c:if>
</div>
<div id="listReply"></div>
</body>
</html>