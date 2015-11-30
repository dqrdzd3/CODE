<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="res/dtree/dtree.css">
<script type="text/javascript" src="res/dtree/dtree.js"></script>
</head>
<body>
	<div class="dtree">
		<p><a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a></p>
		<script type="text/javascript">
			<!--
			d = new dTree('d', 'res/dtree/');
			d.add(0,-1,'系统权限设置');
			d.add(1,0,'功能菜单一','javascript:d.o(1);');
			d.add(2,1,'部门管理','manager/department/dept_view.action','','mainFrame');
			d.add(5,1,'功能管理','manager/permission/Permission.action','','mainFrame');
			d.add(3,0,'功能菜单二','javascript:d.o(3);');
			d.add(4,3,'菜单21','manager/user/b.jsp','','mainFrame');
			document.write(d);
			//-->
		</script>
	</div>
</body>
</html>