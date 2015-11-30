<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS)" executeResult="true"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<s:url value="/js/util/links.js"/>"></script>
<script type="text/javascript" src='<s:url value="/js/manager/resource.js"></s:url>'></script>
<style type="text/css">
<!--
body,html {background:url(images/contentleftbg.jpg) left top repeat-y;
 	overflow-x:hidden;
}

-->
</style>
<script type="text/javascript">

$(function(){
			
			$$.bindButton('add',function(){
				var divId = 'resourceDiv',
					title = '添加资源',
					url = 'manager/sysresource!doAdd',
					param='',
					callback=function(){};
					param += 'sysResourcePO.MODULE_OPERA_UUID='+$('#searchForm #pId').val();
					param += '&sysResourcePO.RESOURCE_TYPE='+$('#searchForm #type').val();
				$$.openDiv(divId, title, url, param, callback);
			});
			$$.bindButton('edit',function(){
				 var gridId="#gridTable";
				 if(!$$.checkSelRow(1)) return;
				 var ids = getSelectedRowID(gridId);
				 var id = ids[0];
				 
				 var isDefault = jQuery(gridId).jqGrid('getCell',id,'IS_DEFAULT');
				 
				 if(isDefault == 0){
			        var buttons = {
			                '确定':function(){
			                    $(this).dialog('close');
			                }
			            };
			            showMsg('默认资源不可修改！',MSG.INFO);
			            return;
				 }
				 
				var divId = 'resourceDiv',
					title = '修改资源',
					url = 'manager/sysresource!doEdit',
					param='sysResourcePO.UUID='+id,
					callback=function(){};
					
				
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
				var divId = 'resourceDiv',
				title = '浏览资源',
				url = 'manager/sysresource!doView',
				param='',
				callback=function(){};
				
				param += 'sysResourcePO.UUID='+id;
			
				$$.openDiv(divId, title, url, param, callback);
			});
			
});

var gridOption = {
		caption:'系统资源表',
		colModel:[
			{label:'id', name:'UUID', index:'UUID',hidden:true,hidedlg:true},
			{label:'资源类型', name:'RESOURCE_TYPE', index:'RESOURCE_TYPE',hidden:true,hidedlg:true},
			{label:'是否默认资源', name:'IS_DEFAULT', index:'IS_DEFAULT',hidden:true,hidedlg:true},
			{label:'资源名称', name:'RESOURCE_NAME', index:'RESOURCE_NAME',formatter:queryDetails,formatoptions:{divId:'resourceDiv',needURL:true,action:'manager/sysresource!doView',param:'',idName:'sysResourcePO.UUID',postion:'0',title:'资源信息'}},
			{label:'资源类型', name:'RESOURCE_TYPE_NAME', index:'RESOURCE_TYPE_NAME'},
			{label:'是否默认资源', name:'IS_DEFAULT_NAME', index:'IS_DEFAULT_NAME'},
			{label:'URL', name:'URL', index:'URL'},
			{label:'绑定函数', name:'BIND_FUNC', index:'BIND_FUNC'}
		],
		formId:'searchForm',
		winTitle:'系统资源信息',
		pk:'UUID'
};

var actionOption={
	init:false
};

</script>
</head>

<body>
	<div id="searcharea" style="margin-left:10px;">
		<div id="searcharealeft">
			<div id="searcharearight">
				<s:form action="" method="post" cssClass="formmargin" name="searchForm" id="searchForm"> 
					<table border="0" cellspacing="8" cellpadding="0">
						<s:hidden id="pId" name="sysResourcePO.MODULE_OPERA_UUID"/>
						<s:hidden id="type" name="sysResourcePO.RESOURCE_TYPE"/>
				      <tr>
				        <td align="right" style="width:50px;"  nowrap="nowrap">资源名称</td>
				          <td style="width:250px;" nowrap="nowrap">
					          	<s:textfield name="sysResourcePO.RESOURCE_NAME" cssClass="input2"/>
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
		<hwsoft:operation code="${code}" param="view,add,edit,delete"/>
	</div>
	<div style="display: none;" id="resourceDiv"></div>
	
</body>
</html>