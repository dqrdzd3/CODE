<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>" />
<title><s:text name="企业注册页面" />-<s:text
		name="application.title" /></title>

<script type="text/javascript" src="res/jquery-1.7.2.min.js"></script>

<style type="text/css">
<!--
body,html{
	margin: 0px; padding:0px; background:#E9E9E9; font-size:12px;
}
img{ border:none;}
#top{ height:69px; background:#027B62;}

#topmain{ margin:0 auto; padding:0px; position:relative;height:69px; background:url(images/topbannner.jpg) center top no-repeat;}
#topcontent{
	width:450px;
	margin:0 auto;
	text-align:right;
	color:#d0ebff;
	line-height:22px;
	padding-left: 553px;
}
#maincontent{ background:#E9E9E9;}
#main{ width:1003px; margin:0 auto; padding:0px; position:relative; background:url(images/register/mainbg1.jpg) center top no-repeat; height:471px;}
#mainbutton1{
	position:absolute;
	width:300px;
	height:42px;
	top:260px;
	right:56px;
}
#mainbutton1 a{ float:left; display:block; margin-right:17px;}
#phone{
	position:absolute;
	width:400px;
	height:120px;
	top:330px;
	right:20px; font-size:14px; font-weight:bold; color:#006699;
}
#phone a{ float:left; display:block; margin-right:17px;}
-->
</style>


<script type="text/javascript">

</script>

</head>

<body>
<div id="top">
 
    <div id="topmain">
      <div id="topcontent"><marquee direction="left" align="bottom" height="22" width="250" onmouseout="this.start()" onmouseover="this.stop()" scrollamount="2" scrolldelay="1"></marquee></div>
    </div>
  
</div>
<div id="maincontent">
  <div id="main">

    <div id="mainbutton1"><a href="register/c006!doRegister"><img src="images/button1.png" width="124" height="42" /></a></div>
	<div id="phone"></div>



     <div id="mainbutton1">
     <a href="register/c006!doRegister""><img src="images/register/button1.png" width="124" height="42" /></a></div>

  </div>
</div>

</body>
</html>
