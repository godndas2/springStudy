<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<style type="text/css">
.fileDrop {
	width : 100%;
	height: 200px;
	border: 1px dotted black;
}
small {
	margin-left: 3px;
	font-weight: bold;
	color: gray;
}
</style>
<script type="text/javascript">
$(function(){
	// # : id
	// . : class
	// 태그.on("이벤트", "자손태그", 이벤트핸들러)
	// data : "fileName="+$(this).attr("data-src")
	//태그.attr("속성")
	// $("#userid").attr("type")
	$(".uploadedList").on("click","span",function(event){
		var tag = $(this); // click 한 span 태그
		
		$.ajax({
			url : "${path}/upload/deleteFile",
			type : "post",
			data : {
					fileName : $(this).attr("data-src")
					},
			dataType : "text",
			success : function(result){
				if (result == "deleted") {
					tag.parent("div").remove();
				}
			}
		});
	});
});


$(".fileDrop").on("dragenter dragover", function(event) {
	alert("mm");
	event.preventDefault(); // 기본효과 막기, 안막으면 드래그해서 사진을 옮길 때 사진이 전체화면으로 나타남(썸네일x)
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
		success : function(data, status, req){
			console.log("data : " + data); // 업로드 된 파일 이름
			console.log("status : " + status); 
			console.log("req : " + req); // 요청 코드 값
			
			var str = "";
			if (checkImgType(data)) { // 이미지 파일일 떄 
				str = "<div><a href='${path}/upload/displayFile?fileName="
						+ getImgLink(data)+"'/>";
				str += "<img src='{path}/upload/displayFile?fileName="
						+ data+"'></a>";
			} else {
				str = "<div>"
				str += "<a href='${path}/upload/displayFile?fileName="
						+data+"'>"+getOriginalName(data)+"</a>";
			}
			str += "<span data-src="+data+">[삭제]</span></div>";
			$(".uploadedList").append(str);
		}
	});
});

function checkImgType(fileName) {
	var pattern = /jpg|gif|png|jpeg/i; // 정규표현식 ( i는 대소문자 무시(ignore)
	return fileName.match(pattern); // 규칙에 맞으면 true;
}
function getImgLink(fileName) {
	if (!checkImgType(fileName)) { // 이미지가 아니면
		return; // 해당 함수 종료
	}
	
	var front = fileName.substr(0,12) // 연도/월/일 경로를 추출 ( upload된 이미지 파일명을 보면 앞에 날짜가 붙는다 )
	var end = fileName.substr(14); // thumb_ 제거
	console.log(front);
	console.log(end);
	return front + end;
}
function getOriginalName(fileName) {
	if (checkImgType(fileName)) {
		return;
	}
	// UUID가 아닌 원래 file name 리턴
	var idx = fileName.indexOf("_") + 1;
	return fileName.substr(idx);
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>

<h2>Ajax File Upload</h2>
<!-- file upload 할 영역 -->
<div class="fileDrop"></div>
<!-- upload 된 파일 목록 -->
<div class="uploadedList"></div>
</body>
</html>