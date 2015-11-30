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
<title><s:text name="企业登记" />-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,grid,commonJS)" executeResult="true"/>



<script type="text/javascript">
$(function() {
	var trades;
	$.getJSON('manager/sysDict!getDictFmtJsonByKey?dictKey=isValid', function(data){
		var items = new Array();
		$.each(data, function(i, v) {
		items.push(v.value + ':' + v.display);
		});
		trades = items.join(';');
		
		initGrid({
					caption:'企业登记',
					colModel:[
						{label:'企业名称', name:'ma002', index:'ma002', formatter:viewLinkFormatter},
						{label:'组织机构代码', name:'ma003', index:'ma003'},
						{label:'联系电话', name:'ma006', index:'ma006'},
						{label:'邮箱', name:'ma007', index:'ma007'},
						{label:'是否有效', name:'ma008', index:'ma008', formatter:'select',
							editoptions:{value:trades}}

					],
					pk:'MA001',
					formId:'searchForm',
					winTitle:'企业登记信息'
			}
		);
		
		initActionButtons();
		//绑定删除按钮
		$$.bindButton('delete', function() {
			if ($$.checkSelRow(0, "gridTable"))
				return;
			var url = "register/c001!doDeleteC002ByMa001s";
			var ids = getSelectedRowID("#gridTable");
			showMsg("确定要删除所选的记录吗？",MSG.WARNING, {buttons:{'取消':function(){$(this).dialog('close');},'确定':function(){
				$.post(url,{ids:ids.join()}, function(msg) {
					showMsg(msg.content, msg.type);
					reloadGrid("gridTable");
				});
			}}});
		});
		
	});
});

</script>
</head>
<body> 

<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">
			<s:form  method="post" cssClass="formmargin" id="searchForm"> 
			<table  border="0" cellspacing="8" cellpadding="0">
								<tr>
									<td style="width:50px;"align="right" nowrap="nowrap">企业名称</td>
									<td  style="width:300px;"><s:textfield name="c001PO.MA002" cssClass="input2" /></td>
									
									<td style="width:80px;" align="right" nowrap="nowrap">组织机构代码</td>
									<td style="width:300px;"><s:textfield name="c001PO.MA003" cssClass="input2"/></td>
									
									<td width="98" rowspan="2"><s:submit value="" cssClass="search" /></td>
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