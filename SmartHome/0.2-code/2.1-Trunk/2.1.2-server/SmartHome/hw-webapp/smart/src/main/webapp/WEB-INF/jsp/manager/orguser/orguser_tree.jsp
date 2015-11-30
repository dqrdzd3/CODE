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
<style type="text/css">
	.ztree li span.button.pIcon_ico_open {margin-right:2px; background:url(<s:url value="/res/ztree/img/diy/1_open.png"/>) no-repeat 0 0; vertical-align:top; *vertical-align:middle;}
	.ztree li span.button.pIcon_ico_close {margin-right:2px; background:url(<s:url value="/res/ztree/img/diy/1_close.png"/>) no-repeat 0 0; vertical-align:top; *vertical-align:middle;}
	.ztree li span.button.pIcon_ico_docu {margin-right:2px; background:url(<s:url value="/res/ztree/img/diy/1_close.png"/>) no-repeat 0 0; vertical-align:top; *vertical-align:middle;}
</style>

<script type="text/javascript">
function onCheckFun(e, treeId, treeNode){
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var nodes = treeObj.getCheckedNodes(true);

	var id = "";
	if(nodes.length>0){
		id = nodes[0].id;
		
		window.parent.frames['orguserlist'].document.searchForm["deptId"].value=id;
	}else{
		window.parent.frames['orguserlist'].document.searchForm["deptId"].value="";
	}
		
		window.parent.frames['orguserlist'].reloadGrid("gridTable");
}

function onClickFun(e, treeId, treeNode){
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	treeObj.checkNode(treeNode, null, true,true);
}

$(function(){
	var nodes = <s:property value="deptTree" escape="false"/>;
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
		<div id="depttree">
			<ul id="ztree" class="ztree"></ul>
		</div>
	</div>
</body>
</html>