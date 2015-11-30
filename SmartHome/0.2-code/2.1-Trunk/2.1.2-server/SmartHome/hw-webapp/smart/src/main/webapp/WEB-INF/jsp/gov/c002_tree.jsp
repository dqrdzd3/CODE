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
		<title><s:text name="政府机构左侧菜单树" />-<s:text name="application.title" /></title>
		
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
			nodes = <s:property value="c002Tree" escape="false"/>,
			deptId = "<s:property value="userPO.orgId"/>";   // ????
			$(function(){
				zTree = $.fn.zTree.init($("#ztree"), setting, nodes);
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
					var c002Id=nodes[0].id;
					var c002Name=nodes[0].name;
					window.parent.frames['govRightFrame'].document.searchForm["c002PO.ma004"].value=c002Id;
					window.parent.frames['govRightFrame'].reloadGrid("gridTable");
					//window.parent.frames['govRightFrame'].document.addfrom["c002PO.ma004"].value=c002Id;
					//window.parent.frames['govRightFrame'].document.addfrom["parentDepar"].value=c002Name;
					document.getElementById("ma004").value=c002Id;
					document.getElementById("parentDepar").value=c002Name;
				}else{
					window.parent.frames['govRightFrame'].document.searchForm["c002PO.ma004"].value="";
					window.parent.frames['govRightFrame'].reloadGrid("gridTable");
					//window.parent.frames['govRightFrame'].document.addfrom["c002PO.ma004"].value="";
					//window.parent.frames['govRightFrame'].document.addfrom["parentDepar"].value="";
					document.getElementById("ma004").value="";
					document.getElementById("parentDepar").value="";
				}
			}
			
			function addTreeNodes(ma002){
				var zTree = $.fn.zTree.getZTreeObj("ztree");
				var parentNode = zTree.getCheckedNodes(true);
				if(ma002!=null && ""!=ma002){
					$$.getPO('gov/c002!doQueryC002ByMa002',{'c002PO.ma002':ma002}, function(c002PO) {
						if(c002PO!=null){
							var newNode={id:c002PO.ma001,pId:c002PO.ma004,name:c002PO.ma003,iconSkin:"pIcon"};
							newNode=zTree.addNodes(parentNode[0], newNode);
						}
					});
				}
			}
			
			function updateTreeNode(id,name,ma002){
				var zTree = $.fn.zTree.getZTreeObj("ztree");
				var parentNode = zTree.getCheckedNodes(true);
				var node=zTree.getNodeByParam("id", id, null);
				if(node==null){
					$$.getPO('gov/c002!doQueryC002ByMa002',{'c002PO.ma002':ma002}, function(c002PO) {
						if(c002PO!=null){
							var newNode={id:c002PO.ma001,pId:c002PO.ma004,name:c002PO.ma003,iconSkin:"pIcon"};
							newNode=zTree.addNodes(parentNode[0], newNode);
						}
					});
				}else{
					zTree.removeNode(node);
					$$.getPO('gov/c002!doQueryC002ByMa002',{'c002PO.ma002':ma002}, function(c002PO) {
						if(c002PO!=null){
							var newNode={id:c002PO.ma001,pId:c002PO.ma004,name:c002PO.ma003,iconSkin:"pIcon"};
							newNode=zTree.addNodes(parentNode[0], newNode);
						}
					});
				}
			}
			
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
				<td align="left"><div class="usertitle">政府机构</div></td>
			</tr>
			<tr>
				<td><ul id="ztree" class="ztree"></ul></td>
			</tr>
		</table>
		<s:hidden name="ma004" id="ma004"/>
		<s:hidden name="parentDepar" id="parentDepar"/>
	</body>
</html>
