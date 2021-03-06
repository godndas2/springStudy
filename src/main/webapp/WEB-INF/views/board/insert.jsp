<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript">
$(function(){
	$("#btnSave").click(function() {
		document.form1.submit();
	});
});

$(".fileDrop").on("dragenter dragover", function(event) {
	event.preventDefault();
});
// event : jQuery의 이벤트
// originalEvent : Js의 이벤트
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
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<form id="form1" name="form1" method="post" action="${post}/board/insert">
<div>
	제목 <input name="title" id="title" size="80" placeholder="제목을 입력하세요">
</div>
<div style="width: 800px;">
	내용 <textArea id="content" name="content" rows="3" cols="80" placeholder="내용을 입력하세요"></textArea>
</div>
<div>
	첨부파일을 등록하세요
	<div class="fileDrop"></div>
	<div id="uploadedList"></div>
</div>
<div style="width: 700px; text-align: center;">
	<button type="button" id="btnSave">확인</button>
</div>
</form>
</body>
</html>