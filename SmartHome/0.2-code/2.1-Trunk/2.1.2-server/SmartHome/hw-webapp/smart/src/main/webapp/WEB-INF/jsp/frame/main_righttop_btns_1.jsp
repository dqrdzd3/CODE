<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
		
		
<li class="liquit"><a href="javascript:;" onclick="top.location = '../login!logout.action'">退出</a></li>
<li class="lirefresh"><a href="javascript:;" onclick="$$.getFrame('rightFrame').location.reload()">刷新</a></li>
<li class="liback1"><a href="javascript:history.go(1)">前进</a></li>
<li class="liforward"><a href="javascript:history.go(-1)">后退</a></li>
<li class="lihome"><a href="main_right.action" target="rightFrame">首页</a></li>
