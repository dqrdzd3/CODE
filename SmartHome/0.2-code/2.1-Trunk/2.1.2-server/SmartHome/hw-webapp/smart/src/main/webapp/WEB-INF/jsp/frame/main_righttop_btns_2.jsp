<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!-- <li class="liquit"><a href="javascript:;" onclick="top.location = '../login!logout.action'">退出</a></li> -->
<li class="liquit"><a href="javascript:;" onclick="onLogout()">退出</a></li>
<li class="licloseAll"><a href="javascript:;" onclick="$$.getFrame('rightFrame').Tabs.closeAll()">关闭全部</a></li>
<li class="lirefresh"><a href="javascript:;" onclick="$$.getFrame('rightFrame').Tabs.refreshSel()">刷新</a></li>
<li class="liback1"><a href="javascript:;" onclick="$$.getFrame('rightFrame').Tabs.nextTab()">下一个</a></li>
<li class="liforward"><a href="javascript:;" onclick="$$.getFrame('rightFrame').Tabs.preTab()">上一个</a></li>
<li class="lihome"><a href="javascript:;" onclick="$$.getFrame('rightFrame').Tabs.selectTab(0)">首页</a></li>