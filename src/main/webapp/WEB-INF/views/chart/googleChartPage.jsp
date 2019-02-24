<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<!-- Google Chart CDN -->
<script type="text/javascript" src="https://www.google.com/jsapi" />
<script type="text/javascript">
// Google Library Load..
google.load('visualization', '1', {
	'packages' : ['corechart']
});
// drawChart() 호출
google.setOnLoadCallback(drawChart);

function drawChart() {
	var jsonData = $.ajax({
		url : "${path}/json/jsonTest.json",
		dataType : "json",
		async : false
	}).responseText;
	// Chrome F12 click -> console tab Check!
	console.log(jsonData);
	
	var data = new google.visualization.DataTable(jsonData);
	
	// 차트 디자인 (Pie, Line, Column)
	var chart = new google.visualization.PieChart(document.getElementById('chart_div')); 
// 	var chart = new google.visualization.LineChart(document.getElementById('chart_div')); 
// 	var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
	chart.draw(data, {
		title : "chart",
		width : 600,
		height : 440
	});
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<!-- chart 출력 -->
<div id="chart_div"></div>
<!-- chart 새로고침 버튼 -->
<button id="btn" type="button" onclick='drawChart()'>새로고침</button>
</body>
</html>