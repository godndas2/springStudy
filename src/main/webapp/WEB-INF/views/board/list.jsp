<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript">
$(function(){
	$("#btnWrite").click(function() {
		location.href = '${path}/board/insertForm';
	});
});
function list(page) {
	location.href = "${path}/board/list?curPage="+page+"&srchOption=${map.srchOption}"+"&keyword=${map.keyword};"
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>게시판</h2>

<form name="form1" method="post" action="${path}/board/list">

<select name="srchOption">
<c:choose>
	<c:when test="${map.srchOption == 'all' }">
		<option value="all" selected>전체</option>
		<option value="writer">이름</option>
		<option value="content">내용</option>
		<option value="title">제목</option>
	</c:when>
	<c:when test="${map.srchOption == 'writer' }">
		<option value="all">전체</option>
		<option value="writer" selected>이름</option>
		<option value="content">내용</option>
		<option value="title">제목</option>
	</c:when>
	<c:when test="${map.srchOption == 'content' }">
		<option value="all">전체</option>
		<option value="writer">이름</option>
		<option value="content" selected>내용</option>
		<option value="title">제목</option>
	</c:when>
	<c:when test="${map.srchOption == 'title' }">
		<option value="all">전체</option>
		<option value="writer">이름</option>
		<option value="content">내용</option>
		<option value="title" selected>제목</option>
	</c:when>
</select>

<input name="keyword" value="${map.keyword}">
<input type="submit" value="조회">
</c:choose>
</form>

${map.count}개의 게시물이 존재합니다.
<table border="1" width="600px">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>이름</th>
		<th>날짜</th>
		<th>조회수</th>
	</tr>
	<c:forEach var="row" items="${map.list}">
		<tr>
			<td>${row.bno}</td>
			<td>
			<a href="${path}/board/view?bno=${row.bno}&curPage=${map.paging.curPage}&srchOption=${map.srchOption}&keyword=${map.keyword}">${row.title}</a>
			<!-- 게시글 옆 댓글의 개수 -->
			<c:if test="${row.cnt > 0 }">
				<span style="color: red;">(${row.cnt})</span>
			</c:if>
			</td>
			<td>${row.name}</td> <!-- Member Table의 name을 사용 -->
			<td><fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${row.viewcnt}</td>
		</tr>
	</c:forEach>
	<!-- 페이지 navigation -->
	<tr>
		<td colspan="5" align="center">
		<c:if test="${map.paging.curBlock > 1}">
			<a href="javasript:list('1')" > [처음]</a>
		</c:if>
		<c:if test="${map.paging.curBlock > 1}">
			<a href="javascript:list('${map.paging.blockBegin')">[이전]</a>
		</c:if>
			<c:forEach var="num" begin="${map.paging.blockBegin}" end="${map.paging.blockEnd}">
				<c:choose>
					<c:when test="${num == map.paging.curPage}">
						<span style="color: red;">${num}</span>&nbsp;
					</c:when>
					<c:otherwise>
						<a href="javascript:list('${num}')">${num}</a>&nbsp;
					</c:otherwise>
				</c:choose>
				<a href="javascript:list('${num}')">${num}</a>
			</c:forEach>
			<c:if test="${map.paging.curBlock <= map.paging.totBlock}">
				<a href="javascript:list('${map.paging.nextPage}')">[다음]</a>
			</c:if>
			<c:if test="${map.paging.curPage <= map.paging.totPage}">
				<a href="javascript:list('${map.paging.totPage}')">[끝]</a>
			</c:if>
		</td>
	</tr>
</table>
<button type="button" id="btnWrite">글 쓰기</button>
</body>
</html>