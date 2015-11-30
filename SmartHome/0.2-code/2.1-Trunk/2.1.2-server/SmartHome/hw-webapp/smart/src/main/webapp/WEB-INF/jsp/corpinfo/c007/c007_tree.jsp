<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<s:action name="include(mainCSS,jquery,ztree,grid,commonJS)" executeResult="true"/>
<title><s:text name="系统字典左侧菜单树" />-<s:text name="application.title" /></title>
<style type="text/css">
<!--
body,html{background-color:#F8FFFF;padding-right:0px;overflow-x:hidden;overflow-y:auto;
}
.usertitle{background:url(images/usertitlebg.jpg) left top no-repeat;height:24px; display:block; margin-top:0px; color:#10917A; font-weight:bold; line-height:24px; padding-left:29px;width:141px;}
-->
</style>
<style type="text/css">
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
			selectedMulti: false
		},
		check: {
			enable: true,
			chkStyle:"radio",
			radioType:"all"
		}
		,callback: {
			onClick:onClick,
			onCheck: onCheck
		}
	},
	nodes = <s:property value="dictTree" escape="false"/>;
	
	$(function(){
		zTree = $.fn.zTree.init($("#ztree"), setting, nodes);
		//window.parent.frames['dictRightFrame'].qc();
	});

	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("ztree");
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}

	function onCheck(e, treeId, treeNode) {
		
		var zTree = $.fn.zTree.getZTreeObj("ztree");
		var nodes = zTree.getCheckedNodes(true);
		if(nodes.length!=0){
			var dictId=nodes[0].id;
			//节点所在层数，当层数为1是表示的是根层
			var levelNum=nodes[0].level+1;
			//页面跳转 
			window.parent.frames['dictRightFrame'].document.searchForm["c007PO.parentid"].value=dictId;
			window.parent.frames['dictRightFrame'].reloadGrid("gridTable");
			document.getElementById("parentid").value=dictId;
			document.getElementById("levelnum").value=levelNum;
			$$.getPO('corpinfo/c007!edit',{'c007PO.id':dictId}, function(c007PO) {
 				if(c007PO!=null){
 					document.getElementById("parentKey").value=c007PO.fullKey;
 				}
 			});
			//window.parent.frames['dictRightFrame'].qc();
		}else{
			window.parent.frames['dictRightFrame'].document.searchForm["c007PO.parentid"].value="";
			window.parent.frames['dictRightFrame'].reloadGrid("gridTable");
			document.getElementById("parentid").value="";
			document.getElementById("parentKey").value="";
		}
	}
	
	//依照key添加子项
	function addTreeNodes(key){
		var zTree = $.fn.zTree.getZTreeObj("ztree");
		var parentNode = zTree.getCheckedNodes(true);
		if(key!=null && ""!=key){
			$$.getPO('corpinfo/c007!doQueryC007ByKey',{'c007PO.key':key}, function(c007PO) {
				if(c007PO!=null){
					var newNode={id:c007PO.id,pId:c007PO.parentid,name:c007PO.display,iconSkin:"pIcon",levelnum:c007PO.levelnum};
					newNode=zTree.addNodes(parentNode[0], newNode);
				}
			});
		}
	}
	
	//修改节点
	function updateTreeNode(id,name){
		var zTree = $.fn.zTree.getZTreeObj("ztree");
		var parentNode = zTree.getCheckedNodes(true);
		var node=zTree.getNodeByParam("id", id, parentNode[0]);
		node.name=name;
		zTree.updateNode(node);
	}
	
	//依照id数组删除子项
	function removeTreeNodes(ids){
		var zTree = $.fn.zTree.getZTreeObj("ztree");
		var parentNode = zTree.getCheckedNodes(true);
		for (var i=0, l=ids.length; i < l; i++) {
			var node=zTree.getNodeByParam("id", ids[i], parentNode[0]);
			zTree.removeNode(node);
		}
	}

</script>
</head>

<body>
	<table>
		<tr>
			<td align="left"><div class="usertitle">系统字典</div></td>
		</tr>
		<tr>
			<td><ul id="ztree" class="ztree"></ul></td>
		</tr>
	</table>
	<s:hidden name="parentKey" id="parentKey"/>
	<s:hidden name="parentid" id="parentid"/>
	<s:hidden name="levelnum" id="levelnum"/>
</body>
</html>
