<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<title>注册成功</title>
<style type="text/css">
<!--
body,html{
	margin: 0px; padding:0px; background:#E9E9E9; font-size:12px;
}
img{ border:none;}
#top{ height:69px; background:#027B62;}

#topmain{ margin:0 auto; padding:0px; position:relative;height:69px; background:url(<%=basePath%>images/topbannner.jpg) center top no-repeat;}
#topcontent{
	width:450px;
	margin:0 auto;
	text-align:right;
	color:#d0ebff;
	line-height:22px;
	padding-left: 553px;
}
#maincontent3{background:url(<%=basePath%>images/maincontentbg.jpg) left top repeat-x #EAEAEA;}
#main3{
	width:1003px;
	margin:0 auto;
	padding:0px;
	position:relative;
	height:567px;
	background:url(<%=basePath%>images/mainbg3.jpg) center top no-repeat;
	padding-top: 30px;font-size:14px;color:#565656;
}
#main3content{
position:absolute;
	width:300px;
	height:42px;
	top:190px;
	left:200px;}
#main3content span {
	color: #0000FF;
	font-style: italic;
	font-weight: bold;
}

-->
</style>
</head>

<body>
<div id="top">
 
    <div id="topmain">
      <div id="topcontent"><marquee direction="left" align="bottom" height="22" width="250" onmouseout="this.start()" onmouseover="this.stop()" scrollamount="2" scrolldelay="1"></marquee></div>
    </div>
  
</div>
<div id="maincontent3">
  <div id="main3">
  <div id="main3content">注册成功！<br>你的注册账号是<span >${requestScope.zhanghao }</span>，请登录完善企业信息<br />
  </div>
  </div>
</div>
</body>
</html>
