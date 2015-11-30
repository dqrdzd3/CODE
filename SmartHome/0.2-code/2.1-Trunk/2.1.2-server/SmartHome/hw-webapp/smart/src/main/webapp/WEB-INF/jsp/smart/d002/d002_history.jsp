<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>设备历史明细</title>
<script type="text/javascript" src='<s:url value="/res/plotCharts/FusionCharts.js"></s:url>'></script>
<script type="text/javascript" src='<s:url value="/res/plotCharts/FusionCharts.jqueryplugin.js"></s:url>'></script>
<script type="text/javascript">
function showAir(url, mainTitle, xTitle, yTitle,width, height, divId) {
    $.post(url, function (data) {
        data = data.dataObject;
        var str = '{"chart": {"palette": "1", "xaxisname": "' + xTitle + '","yaxisname": "' + yTitle + '", "numdivlines": "3","caption": "' + mainTitle + '"},"categories": [{"font": "Arial","category": [';
        if (data != null && data.length > 1) {
            for (var i = 0; i < data.length; i++) {
                str += '{"label": "' + data[i].air.createTime + '"},';
            }
            str = str.substring(0, str.length - 1);
            str += ']}],"dataset": [{ "seriesname": "苯","color": "8BBA00","data": [';
            for (var i = 0; i < data.length; i++) {
                str += '{"value": "' + data[i].air.c6h6 + '"},';
            }
            str = str.substring(0, str.length - 1);
            str += ']},{"seriesname": "甲醛","color": "A68EDD","data": [';
            for (var i = 0; i < data.length; i++) {
                str += '{"value": "' + data[i].air.ch2o + '"},';
            }
            str = str.substring(0, str.length - 1);
            str += ']},{"seriesname": "二氧化碳","color": "F86EED","data": [';
            for (var i = 0; i < data.length; i++) {
                str += '{"value": "' + data[i].air.co2 + '"},';
            }
            str = str.substring(0, str.length - 1);
            str += ']},{"seriesname": "pm2.5","color": "808080","data": [';
            for (var i = 0; i < data.length; i++) {
                str += '{"value": "' + data[i].air.pm25 + '"},';
            }
            str = str.substring(0, str.length - 1);
            str += ']},{"seriesname": "温度","color": "C8263D","data": [';
            for (var i = 0; i < data.length; i++) {
                str += '{"value": "' + data[i].air.temperature + '"},';
            }
            str = str.substring(0, str.length - 1);
            str += ']},{"seriesname": "湿度","color": "F6BD0F","data": [';
            for (var i = 0; i < data.length; i++) {
                str += '{"value": "' + data[i].air.humidity + '"},';
            }
            str = str.substring(0, str.length - 1);
            str += ']}]}';
        }
        var chart = new FusionCharts($$.getContextPath()+'res/plotCharts/MSColumn3D.swf', 'ChartId', width, height, '0', '0');
        chart.setJSONData(str);
        chart.render(divId);
    }, "json");
}

function showGas(url, mainTitle, xTitle, yTitle,width, height, divId) {
    $.post(url, function (data) {
        data = data.dataObject;
        var str = '{"chart": {"palette": "1", "xaxisname": "' + xTitle + '","yaxisname": "' + yTitle + '", "numdivlines": "3","caption": "' + mainTitle + '"},"categories": [{"font": "Arial","category": [';
        if (data != null && data.length > 1) {
            for (var i = 0; i < data.length; i++) {
                str += '{"label": "' + data[i].gas.createTime + '"},';
            }
            str = str.substring(0, str.length - 1);
            str += ']}],"dataset": [{ "seriesname": "co","color": "8BBA00","data": [';
            for (var i = 0; i < data.length; i++) {
                str += '{"value": "' + data[i].gas.co + '"},';
            }
            str = str.substring(0, str.length - 1);
            str += ']},{"seriesname": "ch4","color": "F6BD0F","data": [';
            for (var i = 0; i < data.length; i++) {
                str += '{"value": "' + data[i].gas.ch4 + '"},';
            }
            str = str.substring(0, str.length - 1);
            str += ']}]}';
        }
        var chart = new FusionCharts($$.getContextPath()+'res/plotCharts/MSColumn3D.swf', 'ChartId', width, height, '0', '0');
        chart.setJSONData(str);
        chart.render(divId);
    }, "json");
}

</script>
<script type="text/javascript">
	jQuery(function(){
		var driveType = $("#driverType").val();
		if(driveType != null && driveType == 1){
			$("#pie").html("");
			var url = $$.getContextPath()+'smart/d002!showHistory?d002PO.ma001='+$("#d002POId").val();
			showGas(url,"当日最高值", "日期", "数值", "560", "400", "pie");
		}else if(driveType != null && driveType == 2){
			$("#pie1").html("");
			var url = $$.getContextPath()+'smart/d002!showHistory?d002PO.ma001='+$("#d002POId").val();
			showAir(url,"当日最高值", "日期", "数值", "560", "400", "pie1");
		}else{
			
		}

	});
</script>
</head>
<body>
<s:hidden id="d002POId" name="d002PO.ma001" />
<s:hidden id="driverType" name="d002PO.ma003" />
<div class="countcontent">

<div id="pie" align="center" ></div>
<div id="pie1" align="center"></div>
</body>
</html>