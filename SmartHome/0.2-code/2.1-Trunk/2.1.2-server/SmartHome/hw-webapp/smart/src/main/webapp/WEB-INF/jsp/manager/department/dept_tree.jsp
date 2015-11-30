<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<s:action name="include(mainCSS,jquery,ztree,grid,commonJS)" executeResult="true"/>
<title>部门管理</title>
<style type="text/css">
<!--
body,html{background-color:#F8FFFF;padding-right:2px;overflow-y:hidden;
}
.usertitle{background:url(images/usertitlebg.jpg) left top no-repeat;height:24px; display:block; margin-top:0px; color:#10917A; font-weight:bold; line-height:24px; padding-left:29px;width:141px;}
-->
</style>
<style>
	.ztree li span.button.pIcon_ico_open {margin-right:2px; background:url(<s:url value="/res/ztree/img/diy/1_open.png"/>) no-repeat 0 0; vertical-align:top; *vertical-align:middle;}
	.ztree li span.button.pIcon_ico_close {margin-right:2px; background:url(<s:url value="/res/ztree/img/diy/1_close.png"/>) no-repeat 0 0; vertical-align:top; *vertical-align:middle;}
	.ztree li span.button.pIcon_ico_docu {margin-right:2px; background:url(<s:url value="/res/ztree/img/diy/1_close.png"/>) no-repeat 0 0; vertical-align:top; *vertical-align:middle;}
</style>

<script type="text/javascript">
	
	var zTree,
	setting = {
		data: {
			simpleData: {enable:true}
		},
		view: {
			selectedMulti:false
		},
		check: {
			enable: true,
			chkStyle:"radio",
			radioType:"all"
		},
		callback:{
			onClick:nodeClick,
			onCheck:nodeCheck
		}
	},
	nodes = <s:property value="zTreeNodesStr" escape="false"/>,
	
	code = "<s:property value="code" escape="false"/>",
	
	pId = "<s:property value="pId"/>";
	$(function(){
		zTree = $.fn.zTree.init($("#deptZTree"), setting, nodes);
		var node = zTree.getNodeByParam("id",pId);
		zTree.expandNode(node, true, false, true);
		try{
			zTree.checkNode(node, true, false);
		}catch(e){
		}
	});
	
	function nodeClick(event, treeId, treeNode) {
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
 	}
	
	function nodeCheck(event,treeId,treeNode){
		var nodes = zTree.getCheckedNodes(true);

		if(nodes.length!=0){
			var pId=nodes[0].id;
			var code = nodes[0].code;
			//页面跳转 
			window.parent.frames['deptlist'].document.getElementById('pId').value=pId;
			window.parent.frames['deptlist'].document.getElementById('preKey').value=code+"_";
			document.getElementById("parentid").value=pId;
			document.getElementById("preKey").value=code+"_";
			window.parent.frames['deptlist'].reloadGrid("gridTable");
		}else{
			window.parent.frames['deptlist'].document.getElementById('pId').value="";
			window.parent.frames['deptlist'].document.getElementById('preKey').value="";
			document.getElementById("parentid").value="";
			document.getElementById("preKey").value="";
			window.parent.frames['deptlist'].reloadGrid("gridTable");
		}

	}

	/**
	 * 添加节点
	 */
	function addNodeFun(node){
		var zTree = getTree();
		var parentNodes = zTree.getCheckedNodes(true);
		var parentNode=null;
		if(parentNodes.length>0){
			parentNode = parentNodes[0];
		}
		zTree.addNodes(parentNode,node);
	}
	/**
	 * 修改节点
	 */
	function updateNodeFun(node){
		var zTree = getTree();
		var parentNodes = zTree.getCheckedNodes(true);
		var parentNode=null;
		if(parentNodes.length>0){
			parentNode = parentNodes[0];
		}
		var oldNode = zTree.getNodeByParam("id", node.id, parentNode);
			oldNode.name=node.name;
			zTree.updateNode(oldNode);
	}
	/**
	 * 移除节点
	 */
	function removeNodeFun(nodes){
		var zTree = getTree();
		var parentNodes = zTree.getCheckedNodes(true);
		var parentNode=null;
		if(parentNodes.length>0){
			parentNode = parentNodes[0];
		}
		var ids = nodes.ids.toString().split(",");
		
		for(var i=0;i<ids.length;i++){
			var oldNode = zTree.getNodeByParam("id", ids[i], parentNode);
				zTree.removeNode(oldNode);
		}
	}
	
	function getTree(){
		return $.fn.zTree.getZTreeObj("deptZTree");
	}
</script>
</head>
<body>
	<table>
		<tr><td align="left"><div class="usertitle"><s:text name="label.department.orglist"/></div></td></tr>
		<tr><td><ul id="deptZTree" class="ztree"></ul></td></tr>
	</table>
	<s:hidden name="preKey" id="preKey"/>
	<s:hidden name="parentid" id="parentid"/>
</body>
</html>