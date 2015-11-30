<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main-left</title>
<style type="text/css">
<!--
body,html{
background-color:#E5F9F8;
}

-->
</style>
<link href="css/main.css" rel="stylesheet" type="text/css" />

</head>

<body onload="MM_preloadImages('images/dailyover.jpg','images/administrativeover.jpg','images/monitorover.jpg','images/rescueover.jpg')" bgcolor="#E5F9F8">
<div id="mainleft">
  <div><img src="images/lefttop.jpg" width="198" height="31" /></div>
  <div><img src="images/meau.jpg" width="198" height="28" /></div>
  <div id="iconmeau">
  <ul>
  <li><a href="#" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image3','','images/dailyover.jpg',1)"><img src="images/daily.jpg" name="Image3" width="49" height="81" border="0" id="Image3" /></a></li>
  <li><a href="#" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image4','','images/administrativeover.jpg',1)"><img src="images/administrative.jpg" name="Image4" width="49" height="81" border="0" id="Image4" /></a></li>
  <li><a href="#" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image5','','images/monitorover.jpg',1)"><img src="images/monitor.jpg" name="Image5" width="49" height="81" border="0" id="Image5" /></a></li>
  <li><a href="#" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image6','','images/rescueover.jpg',1)"><img src="images/rescue.jpg" name="Image6" width="51" height="81" border="0" id="Image6" /></a></li>
  </ul>
  </div>
  <div id="leftmeau">
  <ul>

				<li><a href="knowledge/whp" target="rightFrame">危化品知识库</a></li>
				<li><a href="knowledge/accident" target="rightFrame">事故案例知识库</a></li>
				<li><a href="knowledge/tsafelaw" target="rightFrame">法律法规知识库</a></li>
				<li><a href="knowledge/tsafestandard" target="rightFrame">安全生产标准库</a></li>
				<li><a href="knowledge/tcorpamb" target="rightFrame">预案库</a></li>

				<li><a href="knowledge/tregulation" target="rightFrame">规章制度库</a></li>

				<li><a href="knowledge/k007" target="rightFrame">企业预案库</a></li>
				
				<li><a href="knowledge/k006" target="rightFrame">职业病知识库</a></li>

  			
  		
  
		  
		  
  </ul>
  </div>
</div>





<%-- <a href="<s:url action="department"/>" >部门管理</a><br> --%>


</body>
</html>
