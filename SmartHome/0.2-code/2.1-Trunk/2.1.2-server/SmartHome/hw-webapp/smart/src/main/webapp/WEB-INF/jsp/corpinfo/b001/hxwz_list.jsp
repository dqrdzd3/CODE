<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>物质名称列表</title>
<script type="text/javascript">
jQuery(function(){
	initGrid({
		id:'wztable',
		caption:'非药品类易制毒化学品分类和品种目录',
		colModel:[
					{label:'pk', name:'id', index:'id',hidden:true},
					{label:'化学物品标识', name:'key', index:'key'},
					{label:'化学物品编号', name:'value', index:'value'},
					{label:'物质名称', name:'display', index:'display'}
				],
				pk:'id',
				url:$$.getContextPath()+'corpinfo/b001!queryDataHxwz?ran='+Math.random(),
				formId:'wzsearchForm'
		
});
});

</script>
</head>
<style type="text/css">
<!--
#radiv {
	
}
#radiv table{background-color:#E5F8F7;
	border:#45D7C3 dashed 1px;
	height:26px;
	line-height:25px;
	color:#089381;
	padding-left: 11px;
	margin-bottom:6px;
	width:98%;}
#radiv span{ color:#FF8400;}
-->
</style>
<body>
	
	<!-- 查询区 -->
	<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">

	<s:form  method="post"  cssClass="formmargin"  id="wzsearchForm"> 
		<table border="0" cellspacing="8" cellpadding="0"  >
	        <tr>
	          <td style="width:80px;" align="right" nowrap="nowrap">化学物品编号</td>
	          	<td align="left" style="width:300px;"><input type="text" name="hxwzvalue"  Class="input2"/> 
	          </td>
	          <td style="width:50px;" align="right" nowrap="nowrap">物质名称</td>
	          	<td align="left" style="width:300px;"><input type="text" name="hxwzdisplay"  Class="input2"/> 
	          </td>
	          <td ><s:submit value="" cssClass="search"/></td>
	          <td><img src="<%=basePath%>/images/choose.jpg" id="chooseopt"></td>
			</td>
	        </tr>
      </table></s:form></div>
	</div></div>
	<!-- 操作按钮  -->
	<div id="operating_id" class="operating">
		<ul>
		</ul>
	</div>
	<!-- 主表  -->
	<div id="tablestyle_id" class="tablestyle">
	<form id="faform">
		<div>
			<table id="wztable"></table>
		</div>
	</form>
	</div>
</body>
</html>