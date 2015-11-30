<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
 String basePath = request.getScheme() + "://"
   + request.getServerName() + ":" + request.getServerPort()
   + request.getContextPath() + "/";
%>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
  <title>部门管理</title>
  <link rel="stylesheet" type="text/css" href="<%=basePath%>plugin/gt-grid/gt_grid.css" />
  <script type="text/javascript" src="<%=basePath%>plugin/gt-grid/gt_msg_cn_gbk.js"></script>
  <script type="text/javascript" src="<%=basePath%>plugin/gt-grid/gt_grid_all.js"></script>

<script type="text/javascript">
var dsConfig= {
  fields :[
	{name : 'id'                 },
	{name : 'name'               },
	{name : 'description'        }
 ]
};

var colsConfig = [
	{id:'id'         ,header:'ID'  },
	{id:'name'       ,header:'名称'},
	{id:'description',header:'描述'}
];

var gridConfig={
	id              : "grid1",
	dataset         : dsConfig ,
	columns         : colsConfig ,
	container       : 'grid1_container', 
	toolbarPosition : 'bottom',
	saveURL         : 'showDepart/load',
	pageSize        : 2,
	pageSizeList    : [2,3,4],
	//toolbarContent  : ' reload | add | del | save | goto | pagesize | state' ,
	toolbarContent  : 'nav | goto  | pagesize | state',
	loadURL         : 'roleTestAction!list',   
	remotePaging    : true 
};

var mygrid = new GT.Grid(gridConfig);
GT.Utils.onLoad( function(){
	mygrid.render();
});
</script>
</head>
<body>
<!-- grid的容器. -->
<div id="grid1_container" style="width:100%;height:400px">
</div>

 </body>
</html>