<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main-righttop</title>
<s:action name="include(mainCSS,jqueryJS,utilJS)" namespace="" executeResult="true"/>
<style type="text/css">
body,html{margin:0px; padding:0px; width:100%; height:100%; overflow:hidden; background:url(<s:url value="/"/>images/centerbg.jpg) left top repeat-x;}
</style>
<script type="text/javascript">
function changePosition(position) {
	var str = "", pp = position? position.split(",") : ['首页'];
	for (var i = 0, l = pp.length; i < l; i++)
		str += " > " + pp[i];
	if (str.length > 1) str = str.substring(3);
	var pSpan = document.getElementById("positionSpan");
	if (pSpan) pSpan.innerHTML = str;
}
function onLogout(){
		$$.getFrame("rightFrame").onLogout();
}
</script>
</head>

<body>
<div id="righttop">
	<div id="righttopleft">您目前的位置：<span id="positionSpan">首页</span></div> 
	<div id="righttopright">
	
		<s:action name="main_righttop_btns" executeResult="true"/>

	</div>
</div>
</body>
</html>
