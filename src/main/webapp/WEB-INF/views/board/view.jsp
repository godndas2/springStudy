<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script src="${path}/include/common.js"></script>
<script src="${path}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
$(function(){
	$("#btnList").click(function() {
		location.href = "${path}/board/list";
	});
	
	listReply("1"); // 댓글의 목록을 뿌려줌
	$("#btnReply").click(function() {
		reply();
	});
	
	$(".fileDrop").on("dragenter dragover", function(event) {
		event.preventDefault(); // 기본효과 막기, 안막으면 드래그해서 사진을 옮길 때 사진이 전체화면으로 나타남(썸네일x)
	});
	
	$(".fileDrop").on("drop", function(event){
		event.preventDefault();
		
		var files = event.originalEvent.dataTransfer.files;
		
		var file = files[0]; // file의 첫 번째 파일
		console.log(file);
		
		// ajax로 전달할 form 객체
		var formData = new FormData(); // <form>을 따로 만들지 않았기 때문에 따로 form객체를 생성해주자
		formData.append("file", file); // .append : "변수명", 값
		
		$.ajax({
			type : "post",
			url : "${path}/upload/uploadAjax",
			data : formData,
			dataType : "text",
			processData : false,
			contentType : false, // false로 하면 멀티파트 방식
			success : function(data){
				var fileInfo = getFileInfo(data); // 첨부파일 정보
				var html = "<a href='"+fileInfo.getLink+"'>"+fileInfo.fileName+"</a><br/>"; // 첨부파일 링크
				html += "<input type='hidden' class='file' value='"+fileInfo.fullName+"'>";
				$("#uploadedList").append(html);
			}
		});
	});
});

$("#btnUpdate").click(function(){
	var str = "";
	$("#uploadedList .file").each(function(i){
		str += "<input type='hidden' name='files["+i+"]' value='"+$(this).val()+"'>";
	});
	$("#form1").append(str);
	
	document.form1.action = "$(path)/board/update";
	document.form1.submit();
});

	listAttach();
// });

$("#uploadedList").on("click", ".file_del", function(e){
	var t = $(this);
	$.ajax({
		type : "post",
		url : "${path}/upload/deleteFile",
		data : {fileName : $(this).attr("data-src")},
		dataType : "text",
		success : function(result){
			if (result == "deleted") {
				t.parent("div").remove();
			}
		}
	});
});

$("#btnDelete").click(function(){
	if (confirm("삭제하시겠습니까?")) {
		document.form1.action = "$(path)/board/delete";
		document.form1.submit();
	}
  });
});

function listAttach() {
	$.ajax({
		type : "post",
		url : "${path}/board/getAttach/${dto.bno}", // controller -> getAttach 메소드 확인
		success : function(list){
			$(list).each(function(){
					var fileInfo = getFileInfo(this);
					var html = "<div><a href='"+fileInfo.getLink+"'>"+fileInfo.fileName+"</a>&nbsp;&nbsp;";
					html += "<a href='#' class='file_del' data-src='"+this+"'>[삭제]</a></div>";
					$("#uploadedList").append(html);
			});
		}
	});
}



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