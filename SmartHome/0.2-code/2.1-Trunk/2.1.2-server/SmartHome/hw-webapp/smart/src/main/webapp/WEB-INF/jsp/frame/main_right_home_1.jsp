<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<table id="welPicTable" width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="15%" align="left" valign="bottom"><img src="images/welcomepictitle_1.jpg" width="580" height="54" /></td>
	</tr>
	<tr>
		<td height="85%" align="center" valign="middle"><img src="images/welcomepic_1.jpg" width="557" height="418" usemap="#Map" border="0" /></td>
	</tr>
</table>

<map name="Map" id="Map">
	<!-- 权限管理 -->
	<area onfocus="this.blur()" shape="circle" coords="41,277,40" href="frame/main_left.action?id=<s:property value="moduleID[#application.config.Module_Perm]"/>" target="leftFrame"/>
	<!-- 日常普查 -->
	<area onfocus="this.blur()" shape="circle" coords="72,129,40" href="frame/main_left.action?id=<s:property value="moduleID[#application.config.Module_Daily]"/>" target="leftFrame"/>	
	<!-- 行政执法 -->
	<area onfocus="this.blur()" shape="circle" coords="362,40,40" href="frame/main_left.action?id=<s:property value="moduleID[#application.config.Module_Safe]"/>" target="leftFrame"/>
	<!-- 实时监测预警 -->
	<area onfocus="this.blur()" shape="circle" coords="502,150,40" href="frame/main_left.action?id=<s:property value="moduleID[#application.config.Module_Monitor]"/>" target="leftFrame"/>
	<!-- 应急救援 -->
	<area onfocus="this.blur()" shape="circle" coords="499,299,40" href="frame/main_left.action?id=<s:property value="moduleID[#application.config.Module_Rescue]"/>" target="leftFrame"/>
</map>