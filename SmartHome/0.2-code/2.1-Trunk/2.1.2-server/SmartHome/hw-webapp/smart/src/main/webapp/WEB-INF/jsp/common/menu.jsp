<%@page import="java.util.List"%>
<%@page import="com.hwsensor.permission.pojo.SysModulePO"%>
<%@page import="com.hwsensor.permission.service.ISysModuleService"%>
<%@page import="com.hwsensor.permission.utils.BeanUtils"%>
<%@page import="com.hw.hwsafe.platform.pojo.UserPO"%>
<%@page import="com.hw.hwsafe.platform.constants.Constants"%>
<%@page import="com.hw.hwsafe.platform.userinfo.UserSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
UserSession userSession = (UserSession)session.getAttribute(Constants.USER_SESSION_KEY);
UserPO userPO = userSession.getUserPO();

String contextPath = request.getContextPath();

ISysModuleService iPermissionService = BeanUtils.getSysModuleService();
List<SysModulePO> permList = iPermissionService.retrieveAllUserPermByUserId(userPO.getId());

for(SysModulePO permPO : permList){
%>
<a href="<%=contextPath + permPO.getURL() %>"><%=permPO.getMENU_NAME() %></a><br>
<%
}
%>
<%-- <a href="<s:url action="Permission"/>" >功能管理</a><br> --%>
<%-- <a href="<s:url action="department"/>" >部门管理</a><br> --%>
<%-- <a href="<s:url action="user"/>" >用户管理</a><br> --%>
<%-- <a href="<s:url action="role"/>" >角色管理</a><br> --%>
<%-- <a href="<s:url action="log"/>" >日志管理</a><br> --%>
