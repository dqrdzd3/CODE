<%@page import="com.hw.hwsafe.platform.constants.Constants"%>
<%@page import="com.hw.hwsafe.platform.pojo.UserPO"%>
<%@page import="com.hw.hwsafe.platform.userinfo.UserSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
UserSession userSession = (UserSession)session.getAttribute(Constants.USER_SESSION_KEY);
UserPO userPO = userSession.getUserPO();
%>
<div class="titleDiv">
	<div class="userDiv">
		用户名: <%=userPO.getREAL_NAME() %> &nbsp;&nbsp; <%=userPO.getUSER_TYPE() %>  &nbsp;&nbsp; <a href="login!logout"> 退出</a><br>
	</div>
</div>