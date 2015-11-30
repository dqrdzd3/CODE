<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<s:action name="include(mainCSS,jquery,ztree)" executeResult="true"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function onCheckFun(e, treeId, treeNode){
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var nodes = treeObj.getCheckedNodes(true);

	var id = "";
	var userType = "";
	if(nodes.length>0){
		id = nodes[0].id;
		userType = nodes[0].userType;
		
		window.parent.frames['modulelist'].document.searchForm["pId"].value=id;
		
		var obj = window.parent.frames['modulelist'].document.searchForm["userType"];
        $(obj).find("option[value='"+userType+"']").attr('selected',true);
		
	}else{
		window.parent.frames['modulelist'].document.searchForm["pId"].value="";
		
		var obj = window.parent.frames['modulelist'].document.searchForm["userType"];
        $(obj).find("option[value='"+userType+"']").attr('selected',true);
	}
		
		window.parent.frames['modulelist'].reloadGrid("gridTable");
}

function onClickFun(e, treeId, treeNode){
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	treeObj.checkNode(treeNode, null, true,true);
}

/**
 * 添加节点
 */
function addNodeFun(node){
	var zTree = $.fn.zTree.getZTreeObj("ztree");
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
	var zTree = $.fn.zTree.getZTreeObj("ztree");
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
	var zTree = $.fn.zTree.getZTreeObj("ztree");
	var parentNodes = zTree.getCheckedNodes(true);
	var parentNode=null;
	if(parentNodes.length>0){
		parentNode = parentNodes[0];
	}
	var ids = nodes.ids.split(",");
	
	for(var i=0;i<ids.length;i++){
		var oldNode = zTree.getNodeByParam("id", ids[i], parentNode);
			zTree.removeNode(oldNode);
	}
}

function checkNodeType(){
	var zTree = $.fn.zTree.getZTreeObj("ztree");
	var nodes = zTree.getCheckedNodes(true);
	if(nodes.length>0){
		var userType = nodes[0].userType;
		return userType;
	}
	return;
}

$(function(){
	var nodes = <s:property value="moduleTree" escape="false"/>;
	var setting = {
			data: {
				simpleData: {
					enable: true
				},
				keep: {
					parent: true,
					leaf: true
				}
			},
			check: {
				enable: true,
				chkStyle: "radio",
				radioType:"all"
			},
			callback: {
				onCheck: onCheckFun,
				onClick: onClickFun
			}

	};

	var ztreeObj = $.fn.zTree.init($("#ztree"), setting, nodes);

	

});

</script>
<body>
	<div>
		<div id="moduletree">
			<ul id="ztree" class="ztree"></ul>
		</div>
	</div>
</body>
</html>