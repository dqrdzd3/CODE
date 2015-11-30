<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="-1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title><s:text name="label.role" />-<s:text name="application.title" /></title>

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/plugin/gt-grid/gt_grid.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/plugin/gt-grid/skin/china/skinstyle.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/plugin/gt-grid/skin/vista/skinstyle.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/plugin/gt-grid/skin/mac/skinstyle.css" />

<script type="text/javascript" src="<%=contextPath%>/plugin/gt-grid/gt_msg_cn.js"></script>
<script type="text/javascript" src="<%=contextPath%>/plugin/gt-grid/gt_grid_all.js"></script>

<script type="text/javascript">
//教程中的代码就写在这个区域里.
var dsConfig= {
	fields :[
		{name : 'id'         , type: 'string', index : 'id'         },
		{name : 'name'       , type: 'string', index : 'name'       },
		{name : 'description', type: 'string', index : 'description'}
	]
};

var colsConfig = [
	{ id : 'id'         ,header : "编号" , fieldName : 'id', align : 'center'},
    { id : 'name'       ,header : "名称" , fieldName : 'name'},
    { id : 'description',header : "描述" , fieldName : 'description'}
];

var gridConfig={
		id : "grid1",
		dataset : dsConfig ,
		columns : colsConfig ,
		toolbarContent : 'nav | state',
		container : 'grid1_container', 
		toolbarPosition : 'bottom',
		loadURL : 'roleTestAction!doList',
};

var mygrid=new GT.Grid(gridConfig);
GT.Utils.onLoad( GT.Grid.render(mygrid) );   //mygrid为之前创建的对象,此处也可以传入grid的id.



</script>
</head>

<body>
	<!-- grid的容器. -->
	<div id="grid1_container" style="width:100%; height: 300px"></div>

</body>
</html>

