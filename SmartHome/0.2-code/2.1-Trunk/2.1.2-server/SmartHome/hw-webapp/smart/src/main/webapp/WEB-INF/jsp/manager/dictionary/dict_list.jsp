<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath %>" />
<title><s:text name="系统菜单列表" />-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,datepicker,grid,commonJS)" executeResult="true"/>

<style type="text/css">
<!--
body,html {background:url(images/contentleftbg.jpg) left top repeat-y;
 	overflow-x:hidden;
}

-->
</style>

<script type="text/javascript">
	var gridOption = {
			caption:'系统字典',
			colModel:[
				{label:'编码', name:'key', index:'key', formatter:viewLinkFormatter},
				{label:'值', name:'value', index:'value'},
				{label:'显示内容', name:'display', index:'display'},
				{label:'排序', name:'ordernum', index:'ordernum'}
			],
			pk:'id',
			formId:'searchForm',
			winTitle:'字典信息'
	};
	
	var actionOption = {
			poName:'dictPO',
			table:'sys_dictionary',
			beforeAdd : function(form) {
				var parentKey=window.parent.frames['dictLeftFrame'].document.getElementById("parentKey").value;
				var parentid=window.parent.frames['dictLeftFrame'].document.getElementById("parentid").value;
				if(parentKey!=null && parentid!=null){
					form["dictPO.parentid"].value=parentid;
					form["dictPO.parentKey"].value=parentKey;
				}
				
				//qc();
			},
			success : function(form) {
				var parentKey=form["dictPO.parentKey"].value;
				var key=form["dictPO.key"].value;
				if(form["dictPO.id"].value==null || form["dictPO.id"].value==""){
					window.parent.frames['dictLeftFrame'].addTreeNodes(parentKey+'_'+key);
				}else{
					window.parent.frames['dictLeftFrame'].updateTreeNode(form["dictPO.id"].value,form["dictPO.display"].value);
				}
			},
			buttonInited : function() {
				$$.bindButton('delete', function(){
					if ($$.checkSelRow(0, "gridTable"))
						return;
					var url = "manager/sysDict!delDictByIds";
					var ids = getSelectedRowID("#gridTable");
					showMsg("确定要删除所选的记录吗？",MSG.WARNING, {buttons:{'取消':function(){$(this).dialog('close');},'确定':function(){
						$.post(url,{ids:ids.join()}, function(msg) {
							showMsg(msg.content, msg.type);
							reloadGrid("gridTable");
							if(msg.type==MSG.OK){
								window.parent.frames['dictLeftFrame'].removeTreeNodes(ids);
							}
						});
					}}});
				});
			}
		};
	
	function qc() {
		$$.getPO('manager/sysDict!queryData?qc=true',$('#searchForm').serialize(),function(po){
			if(po!=null && po.records!=null){
				document.addfrom["dictPO.ordernum"].value=po.records+1;
			}
		});
	}
	
	function changeFullKey(){
		var parentKey=document.addfrom["dictPO.parentKey"].value;
		if(parentKey!=null && parentKey!=''){
			document.addfrom["fullKey"].value=document.addfrom["dictPO.parentKey"].value+'_'+document.addfrom["dictPO.key"].value;
		}else{
			document.addfrom["fullKey"].value=document.addfrom["dictPO.key"].value;
		}
	}
</script>
</head>
<body>
	<div id="searcharea" style="margin-left:10px;">
		<div id="searcharealeft">
			<div id="searcharearight">
				<s:form method="post" cssClass="formmargin" id="searchForm">
					<s:hidden name="dictPO.parentid"></s:hidden>
					<table border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td align="right" style="width: 50px;" nowrap="nowrap">编码</td>
							<td style="width: 250px;" nowrap="nowrap">
								<s:textfield name="dictPO.key" cssClass="input2" maxLength="30"/>
							</td>
							<td align="right" style="width: 50px;" nowrap="nowrap">显示内容</td>
							<td style="width: 250px;" nowrap="nowrap">
								<s:textfield name="dictPO.display" cssClass="input2" maxLength="30"/>
							</td>
							<td colspan="2" align="left">
								<s:submit value="" cssClass="search" />
							</td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>
	</div>
	<div id="hiddlebutton">
		<a href="#"><img src="images/searchhiddleicon.jpg" width="71" height="13" /></a>
	</div>
	<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="add,edit,delete"/>
	</div>
	<div id="moduleDiv" style="display: none;"></div>
</body>
</html>