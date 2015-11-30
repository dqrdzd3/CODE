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
<title>登录日志统计</title>
<s:action name="include(mainCSS,jquery,plotCharts,commonJS,validatorJS,grid)" executeResult="true" />
<script type="text/javascript">
	jQuery(function(){
		initPlot("pie1", $$.getContextPath()+'smart/d005!doGroupByMA002?ids=10', null, 'pie','3d', {
			width : '500',
			height : '350'
		});
	});
	jQuery(function(){
		initPlot("pie2", $$.getContextPath()+'smart/d005!doGroupByMA003?ids=10', null, 'pie','3d', {
			width : '500',
			height : '350'
		});
	});
	jQuery(function(){
		initPlot("pie3", $$.getContextPath()+'smart/d005!doGroupByMA007?ids=10', null, 'pie','3d', {
			width : '500',
			height : '350'
		});
	});

</script>
</head>
<body>
<div class="countcontent">
<div class="counttitle">按手机品牌统计</div>
<div id="pie1"></div>
<div class="counttitle">按系统版本号统计</div>
<div id="pie2"></div>
<div class="counttitle">按手机型号统计</div>
<div id="pie3"></div>
</div>
</body>
</html>