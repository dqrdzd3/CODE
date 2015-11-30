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
			caption:'国民经济行业分类',
			colModel:[
				{label:'编码', name:'key', index:'key', formatter:viewLinkFormatter},
				{label:'值', name:'value', index:'value'},
				{label:'显示内容', name:'display', index:'display'},
				{label:'排序', name:'ordernum', index:'ordernum'}
			],
			pk:'id',
			formId:'searchForm',
			winTitle:'行业分类信息'
	};
	
	var actionOption = {
        	id:'operating',
        	gridId:'gridTable',
			beforeAdd : function(form) {
				var parentKey=window.parent.frames['dictLeftFrame'].document.getElementById("parentKey").value;
				var parentid=window.parent.frames['dictLeftFrame'].document.getElementById("parentid").value;
				var levelnum=window.parent.frames['dictLeftFrame'].document.getElementById("levelnum").value;
				if(parentKey!=null && parentid!=null){
					form["c007PO.parentid"].value=parentid;
					form["c007PO.parentKey"].value=parentKey;
					form["c007PO.levelnum"].value=levelnum;
				}
				
				//qc();
			},
			success : function(form) {
				var parentKey=form["c007PO.parentKey"].value;
				var key=form["c007PO.key"].value;
				if(form["c007PO.id"].value==null || form["c007PO.id"].value==""){
					window.parent.frames['dictLeftFrame'].addTreeNodes(parentKey+'_'+key);
				}else{
					window.parent.frames['dictLeftFrame'].updateTreeNode(form["c007PO.id"].value,form["c007PO.display"].value);
				}
			},
			buttonInited : function() {
				$$.bindButton('delete', function(){
					if ($$.checkSelRow(0, "gridTable"))
						return;
					var url = "corpinfo/c007!delC007ByIds";
					var ids = getSelectedRowID("#gridTable");
					showMsg("确定要删除所选的记录吗？",MSG.WARNING, {buttons:{'取消':function(){$(this).dialog('close');},'确定':function(){
						$.post(url,{ids:ids.join()}, function(msg) {
							showMsg(msg.content, msg.type);
							reloadGrid("gridTable");
						});
					}}});
				});
			}
			
		};
	
	function qc() {
		$$.getPO('corpinfo/c007!queryData?qc=true',$('#searchForm').serialize(),function(po){
			if(po!=null && po.records!=null){
				document.addfrom["c007PO.ordernum"].value=po.records+1;
			}
		});
	}
	
	function changeFullKey(){
		var parentKey=document.addfrom["c007PO.parentKey"].value;
		if(parentKey!=null && parentKey!=''){
			document.addfrom["fullKey"].value=document.addfrom["c007PO.parentKey"].value+'_'+document.addfrom["c007PO.key"].value;
		}else{
			document.addfrom["fullKey"].value=document.addfrom["c007PO.key"].value;
		}
	}
</script>
</head>
<body>
	<div id="searcharea" style="margin-left:10px;">
		<div id="searcharealeft">
			<div id="searcharearight">
				<s:form method="post" cssClass="formmargin" id="searchForm">
					<s:hidden name="c007PO.parentid"></s:hidden>
					<table border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td align="right" style="width: 50px;" nowrap="nowrap">编码</td>
							<td style="width: 250px;" nowrap="nowrap">
								<s:textfield name="c007PO.key" cssClass="input2" maxLength="30"/>
							</td>
							<td align="right" style="width: 50px;" nowrap="nowrap">显示内容</td>
							<td style="width: 250px;" nowrap="nowrap">
								<s:textfield name="c007PO.display" cssClass="input2" maxLength="30"/>
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