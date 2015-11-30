<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<link href="<s:url value="/css/"/>main.css" rel="stylesheet" type="text/css" />
</head>
<frameset cols="3,198,13,*,7" id="mainFrameSet" framespacing="0" frameborder="no" border="0" style="background-color:#0a6392">
	<frame src="left_switch.action" name="leftSwitchFrame" scrolling="No" noresize="noresize" id="leftSwitchFrame" title="leftSwitchFrame"/>
	<frame src="main_left.action" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame"/>
	<frame src="center_switch.action" name="switchFrame" id="switchFrame"  scrolling="No" title="switchFrame"  marginwidth ="13px"/>
	<frameset rows="31,*" frameborder="no" border="0" framespacing="0">
		<frame src="main_righttop.action" name="righttopFrame" scrolling="No" noresize="noresize" id="topFrame" />
		<frame src="main_right.action" name="rightFrame" noresize="noresize" id="rightFrame" title="rightFrame" scrolling="auto" />
	</frameset>
	<frame src="right_switch.action" name="rightSwitchFrame" scrolling="No" noresize="noresize" id="rightSwitchFrame" title="rightSwitchFrame"/>   
	<noframes>
		<body></body>
	</noframes>
</frameset>
</html>
