<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS)" executeResult="true"/>
<script type="text/javascript" src="<s:url value="/js/util/links.js"/>"></script>
<script type="text/javascript" src='<s:url value="/js/manager/module.js"></s:url>'></script>
<style type="text/css">
<!--
body,html {background:url(images/contentleftbg.jpg) left top repeat-y;
 	overflow-x:hidden;
}

-->
</style>


<script type="text/javascript">

var gridOption = {
		caption:'系统模块表',
		colModel:[
			{label:'id', name:'UUID', index:'UUID',hidden:true,hidedlg:true},
			{label:'类别', name:'MENU_TYPE', index:'MENU_TYPE',hidden:true,hidedlg:true},
			{label:'用户类型', name:'USER_TYPE', index:'USER_TYPE',hidden:true,hidedlg:true},
			{label:'是否有效', name:'IS_VALID', index:'IS_VALID',hidden:true,hidedlg:true},
			{label:'模块名称', name:'MENU_NAME', index:'MENU_NAME',formatter:queryDetails,formatoptions:{divId:'moduleDiv',needURL:true,action:'manager/sysmodule!view',param:'',idName:'sysModulePO.UUID',postion:'0',title:'模块信息'}},
			{label:'授权类别', name:'USER_TYPE_NAME', index:'USER_TYPE_NAME'},
			{label:'是否有效', name:'IS_VALID_NAME', index:'IS_VALID_NAME'},
			{label:'类别', name:'MENU_TYPE_NAME', index:'MENU_TYPE_NAME'},
			{label:'备注', name:'REMARK', index:'REMARK'},
			{label:'排序', name:'ORDERNUM', index:'ORDERNUM'}
		],
		formId:'searchForm',
		winTitle:'系统模块信息',
		pk:'UUID'
};

var actionOption={
		init:false
};


$(function(){
	
	
	$$.bindButton('add',function(){
		var id = 'moduleDiv',
			title = '添加模块',
			url = 'manager/sysmodule!doAdd',
			param='sysModulePO.PARENT_UUID='+$('#searchForm #pId').val()
			+'&sysModulePO.USER_TYPE='+$('#searchForm #userType').val(),
			callback=function(){
				var pId = $("#"+id+" #addModule #parentId").val();
				if(!pId || pId == ""){
					$("#"+id+" #addModule input[type='radio'][id^='menuType'][value='20']").attr('disabled','disabled');
					$("#"+id+" #addModule input[type='radio'][id^='menuType'][value='10']").click();
				}
			
		};
		
		$$.openDiv(id, title, url, param, callback);
	});
	$$.bindButton('edit',function(){
		 var gridId="#gridTable";
		 if(!$$.checkSelRow(1)) return;
		 var ids = getSelectedRowID(gridId);
		 var id = ids[0];
		
		var divId = 'moduleDiv',
			title = '修改模块',
			url = 'manager/sysmodule!doEdit',
			param='sysModulePO.PARENT_UUID='+$('#searchForm #pId').val(),
			callback=function(){};
			
			param += '&sysModulePO.UUID='+id;
		
		$$.openDiv(divId, title, url, param, callback);
	});
	$$.bindButton('delete',function(){
		 doDelete();
	});
	$$.bindButton('view',function(){
		 var gridId="#gridTable";
		 if(!$$.checkSelRow(1)) return;
		 var ids = getSelectedRowID(gridId);
		 var id = ids[0];
		var divId = 'moduleDiv',
		title = '浏览模块',
		url = 'manager/sysmodule!doView',
		param='',
		callback=function(){};
		
		param += '&sysModulePO.UUID='+id;
	
		$$.openDiv(divId, title, url, param, callback);
	});
	
});


</script>
</head>
<body>
	<div id="searcharea" style="margin-left:10px;">
		<div id="searcharealeft">
			<div id="searcharearight">
				<s:form action="" method="post" cssClass="formmargin" name="searchForm" id="searchForm"> 
					<table border="0" cellspacing="8" cellpadding="0">
				      <tr>
				        <td align="right" style="width:50px;"  nowrap="nowrap">模块名称</td>
				          <td style="width:250px;" nowrap="nowrap">
					          	<s:hidden id="pId" name="sysModulePO.PARENT_UUID" cssClass="input2"/>
					          	<s:textfield name="sysModulePO.MENU_NAME" cssClass="input2"/>
					       </td>
				        <td align="right" style="width:50px;"  nowrap="nowrap">类别</td>
				          <td style="width:250px;" nowrap="nowrap">
					          	<s:select list="menuType" listKey="value" listValue="display" name="sysModulePO.MENU_TYPE" headerKey="" headerValue="请选择"></s:select>
					       </td>
				        <td align="right" style="width:50px;"  nowrap="nowrap">授权类别</td>
				          <td style="width:250px;" nowrap="nowrap">
					          	<s:select id="userType" list="userType" listKey="value" listValue="display" name="sysModulePO.USER_TYPE" headerKey="" headerValue="请选择"></s:select>
					       </td>
				          <td align="right" style="width:50px;"  nowrap="nowrap">是否有效</td>
				          <td style="width:70px;" nowrap="nowrap">
				          		<s:radio list="isValid" listKey="value" listValue="display" value="'10'" name="sysModulePO.IS_VALID"></s:radio>
			          	  </td>
				          <td  colspan="2" align="left" ><s:submit value="" cssClass="search"/></td>
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
		<hwsoft:operation code="${code}" param="view,add,edit,delete,oper"/>
	</div>
	<div id="moduleDiv" style="display: none;"></div>
	
</body>
</html>