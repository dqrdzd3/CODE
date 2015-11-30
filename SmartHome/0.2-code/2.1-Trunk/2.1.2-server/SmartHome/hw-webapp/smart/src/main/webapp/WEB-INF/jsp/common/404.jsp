<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(getClass());
	String uri = (String) request
			.getAttribute("javax.servlet.forward.request_uri");
	String msg = "page not found:" + uri;
	logger.warn(msg);
%>
<html>
<head>
<title>page not found</title>
</head>
<body>
	<h1>404 page not found</h1>
	<h1><%=uri%></h1>
</body>
</html>
