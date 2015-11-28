<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">
<title>Insert title here</title>
<script src="performance/plugins/jQuery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript">
		function myfunction(){
				var flag = <%=request.getAttribute("flag")%>;
				alert(flag);			
		}
		window.onload=myfunction;
	</script>
</head>
<body>
<%-- <%String flag =  (String)request.getAttribute("flag"); 
	System.out.println(flag);
%> --%>
aaa
</body>
</html>