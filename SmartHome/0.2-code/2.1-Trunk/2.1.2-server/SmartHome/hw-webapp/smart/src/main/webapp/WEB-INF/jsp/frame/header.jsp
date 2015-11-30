<%@page import="com.hw.hwsafe.utils.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="com.hw.hwsafe.platform.constants.Constants, com.hw.hwsafe.platform.pojo.UserPO,com.hw.hwsafe.platform.userinfo.UserSession"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	UserSession userSession = (UserSession)session.getAttribute(Constants.USER_SESSION_KEY);
UserPO userPO = userSession.getUserPO();
String userType = userPO.getUSER_TYPE();
	   StringUtil.string2DefVal(userType, "SYS");
String menuCode = "XT_QXGLXT";
if(userType.equals("ENT")){
  menuCode = "QY_QXGLXT";
}
if(userType.equals("GOV")){
  menuCode = "ZF_QXGLXT";
}

String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>header</title>
<link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />

<s:action name="include(mainCSS,jqueryJS,utilJS,easyui)" namespace="" executeResult="true"/>

<script type="text/javascript">
window.onload = function() {
	(updateDateTime = function(){
		var d = new Date;
		document.getElementById("dateSpan").innerHTML = d.toLocaleDateString();
		document.getElementById("timeSpan").innerHTML = d.toLocaleTimeString();
		document.getElementById("daySpan").innerHTML = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"][d.getDay()];
	})();
	setInterval(updateDateTime, 1000);
};

function openMap(){
	$$.getFrame('rightFrame').Tabs.openTab("mapgisid", "地图模式",
			"map/map_right!mapShowDTMS.action", "地图模式 ");
	}
function openPermi(){
	var id="<%=menuCode%>";
	$$.getFrame("leftFrame").checkPermi(id);	
}
function openBusi(){
	var id="BUSI";
	$$.getFrame("leftFrame").checkPermi(id);	
}
function remind(){
	$$.getFrame('rightFrame').Tabs.openTab("remind", "业务消息处理",
			"remind/remind!doList", "业务消息处理 ");
}
</script>
</head>
<body>
  <div id="top">
    <div id="topmain">
      <div id="topmainleft">
	       <div class="user">
		      <ul>
		      <li class="liicon">欢迎您:<span class="smallfont"><%=userPO.getREAL_NAME()%></span></li>
		<%--       <li>角色:<%=userPO.getUSER_TYPE() %></li> --%>
		      
		      <!-- 权限管理系统 start -->
<%-- 		      <li class="lipurview"><a href="main_left.action?id=<s:property value="moduleID['权限管理']"/>" target="leftFrame">权限管理系统</a></li> --%>
		      <li class="lipurview"><a href="javascript:;" onclick="openPermi()" target="leftFrame">权限管理系统</a></li>
		      <li class="libusiview"><a href="javascript:;" onclick="openBusi()" target="leftFrame">业务系统</a></li>
		      <!-- 权限管理系统 end -->
		      </ul>
	      </div>
	      <div id="message">
	      	<%-- // 分辨率1024X768时，宽度过窄使右侧“地图模式”按钮无法显示， 去掉“上次登录时间。。。”， 将“当前时间”移至此处。
	      	您好！上次登录时间：<%=session.getAttribute(Constants.LAST_ACCESS_DATETIME) %>  
	      	--%>
	      	<!-- <a href="javascript:remind()">您有<span class="yellow" id="remind"> ${requestScope.total } </span>项业务消息等待您处理</a>　 -->
	      	<!-- 日期时间显示start -->
			<span id="dateSpan" class="smallfont"></span>&nbsp;&nbsp;<span id="daySpan"></span>
			<span id="timeSpan" class="smallfont"></span>
			<!-- 日期时间显示end -->
	      </div>
      </div>
      
      <!-- 
      <div id="topmainright">
      	<a href="javascript:openMap();" ><img src="<s:url value="/"/>images/mapbutton.jpg" width="127" height="41" /></a>
      </div>
       -->
    </div>
  </div>

</body>
</html>
