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
<style type="text/css">
/* .ztree li span.button.pIcon_ico_open ,.ztree li span.button.pIcon_ico_close{margin-right:2px; background: url(<s:url value="/res/ztree/img/diy/1_open.png"/>) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle} */
/* .ztree li span.button.cIcon_ico_docu{margin-right:2px; background: url(<s:url value="/images/ztree/add.png"/>) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle} */
/* .ztree li span.button.addIcon_ico_docu{margin-right:2px; background: url(<s:url value="/images/ztree/add.png"/>) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle} */
/* .ztree li span.button.editIcon_ico_docu{margin-right:2px; background: url(<s:url value="/images/ztree/edit.png"/>) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle} */
/* .ztree li span.button.deleteIcon_ico_docu{margin-right:2px; background: url(<s:url value="/images/ztree/delete.png"/>) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle} */
/* .ztree li span.button.viewIcon_ico_docu{margin-right:2px; background: url(<s:url value="/images/ztree/view.png"/>) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle} */
/* .ztree li span.button.auditIcon_ico_docu{margin-right:2px; background: url(<s:url value="/images/ztree/audit.png"/>) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle} */

</style>
<script type="text/javascript">
function onCheckFun(e, treeId, treeNode){
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var nodes = treeObj.getCheckedNodes(true);

	var id = "";
	var type = "";
	if(nodes.length>0){
		id = nodes[0].id;
		type = nodes[0].type;
		if(type == 'module' || type == 'page'){
			type = 10;
		}
		if(type == 'operation'){
			type = 20;
		}
		
		window.parent.frames['resourcelist'].document.searchForm["pId"].value=id;
		window.parent.frames['resourcelist'].document.searchForm["type"].value=type;
	}else{
		window.parent.frames['resourcelist'].document.searchForm["pId"].value="";
		window.parent.frames['resourcelist'].document.searchForm["type"].value="";
	}
		
		window.parent.frames['resourcelist'].reloadGrid("gridTable");
}

function onClickFun(e, treeId, treeNode){
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	treeObj.checkNode(treeNode, null, true,true);
}

$(function(){
	var nodes = <s:property value="operTree" escape="false"/>;
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
</head>

<body>
	<div>
		<div id="opertree">
			<ul id="ztree" class="ztree"></ul>
		</div>
	</div>
</body>
</html>