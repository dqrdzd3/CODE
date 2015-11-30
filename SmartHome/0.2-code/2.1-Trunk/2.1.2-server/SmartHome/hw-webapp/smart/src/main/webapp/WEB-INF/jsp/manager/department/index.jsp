<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>部门管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:action name="include(mainCSS,jquery,ztree)" executeResult="true"/>

</head>
<frameset cols="173,*" border="0" framespacing="0"  frameborder="no">
	<frame src="department!doLoadTree" id="depttree" name="depttree" noresize="noresize"/>
	<frame src="department!doList?code=${code}" id="deptlist" name="deptlist" noresize="noresize"/>
</frameset>
<noframes>
	<body>
		浏览器暂不支持!
	</body>
</noframes>
</html>