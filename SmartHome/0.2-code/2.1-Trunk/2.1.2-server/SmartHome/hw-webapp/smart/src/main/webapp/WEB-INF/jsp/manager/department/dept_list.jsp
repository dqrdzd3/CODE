<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- include -->
<s:action name="include(mainCSS,jquery,ztree,validatorJS,grid,commonJS)" executeResult="true"/>
<script type="text/javascript" src="<s:url value="/js/util/links.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/manager/dept.js"/>"></script>
<title>部门管理</title>
<style type="text/css">
<!--
body,html {background:url(images/contentleftbg.jpg) left top repeat-y;
 	overflow-x:hidden;
 	
}
-->
</style>
<script type="text/javascript">
var gridOption = {
		id:'gridTable',
		caption:'部门管理',
		colModel:[
		          {label:'部门名称', name:'uuid', index:'uuid',hidden:true,hidedlg:true},
			      {label:'部门名称', name:'DEPART_NAME', index:'DEPART_NAME',formatter:queryDetails,formatoptions:{divId:'deptDiv',needURL:true,action:'manager/department!doView',idName:'deptPO.uuid',postion:'0',title:'部门信息'}},
			      {label:'部门编号', name:'DEPART_CODE', index:'DEPART_CODE'},
			      {label:'描述', name:'REMARK', index:'REMARK'}
		],
		formId:'searchForm',
		pk:'uuid',
		winTitle:'部门信息'
};
var actionOption = {
		init:false
};

// 部门授权
function empower(){
	 var id=jQuery("#uuid").val();
	 var str='';
	 $("input:checkbox").each(function(){
	        if($(this).attr("checked")){
	        	str += $(this).val() + "|";
	        }
	    });
	    var roleids=str.substr(3);
		jQuery.post(
				$$.getContextPath()+'manager/department!saveAssigndeptEmpower',
				{'deptid':id,'roleids':roleids},function(msg){
				showMsg(msg.content,msg.type);
				closeDiv ('deptDiv','gridTable'); 
				}
		);
};

function assignRole(){
	var id = getSelectedRowID("#gridTable");
	if($$.checkSelRow(1, 'gridTable', '请选择选一条记录呗', '只能选一条记录啊')) {
		 $$.openDiv(
				 'deptDiv',
				 '部门角色授权',
				 $$.getContextPath()+'manager/department!assignDeptRole',{id:''+id}
			); 
	}
};


/**
 *关闭并清空层 ，刷新列表
 */
          
function closeDiv (divId,gridId) {
  var divId = divId || 'operating_input_form';
  var gridId = gridId || 'gridTable';
  
  $$.closeDiv(divId);
  $("#"+divId).empty();
  reloadGrid(gridId);
}

$(function(){
	$$.bindButton('add',function(){
		var id = 'deptDiv',
			title = '添加',
			url = 'manager/department!doAdd',
			param='deptPO.parent_uuid='+$('#searchForm #pId').val()
			+'&deptPO.prekey='+$('#searchForm #preKey').val(),
			callback=function(){};
		
		$$.openDiv(id, title, url, param, callback);
	});
	$$.bindButton('edit',function(){
		 var gridId="#gridTable";
		 if(!$$.checkSelRow(1)) return;
		 var ids = getSelectedRowID(gridId);
		 var id = ids[0];
		
		var divId = 'deptDiv',
			title = '修改',
			url = 'manager/department!doEdit',
			param='deptPO.uuid='+id,
			callback=function(){};
		
		$$.openDiv(divId, title, url, param, callback);
	});
	$$.bindButton('view',function(){
		 var gridId="#gridTable";
		 if(!$$.checkSelRow(1)) return;
		 var ids = getSelectedRowID(gridId);
		 var id = ids[0];
		var divId = 'deptDiv',
		title = '浏览',
		url = 'manager/department!doView',
		param='deptPO.uuid='+id,
		callback=function(){};
	
		$$.openDiv(divId, title, url, param, callback);
	});
	
	$$.bindButton('delete', function() {
			var state = true;
			var url = "manager/department!delBatch.action";
			var zTree = window.parent.frames['depttree'].getTree();
			var ids = getSelectedRowID("#gridTable");
			if (ids.length == 0) {
				showMsg("请选择一条记录！", MSG.INFO);
				return;
			}
			jQuery(ids).each(function(i, v) {
				var node = zTree.getNodeByParam("id", v);
				if (node.isParent) {
					showMsg("『" + node.name + "』部门下有子部门,\r\n不允许删除！", MSG.INFO);
					state = false;
					return false;//false,退出循环;true,继续下次循环
				}
			});
			if (!state)
				return;
			showMsg('确定要删除所选的记录吗？', MSG.WARNING, {
				buttons : {
					'取消' : function() {
						jQuery(this).dialog("close");
					},
					'确定' : function() {
						$.post(url, {
							'ids' : '' + ids.join()
						}, function(data) {
							callback(data);
						});
					}
				}
			});

			var callback = function(data) {
				var buttons = {
					'确定' : function() {
						$(this).dialog('close');
						reloadGrid("gridTable");
						if (data.map.ids) {
							var ids = data.map.ids;
							var nodes = {
								'ids' : ids
							};
							window.parent.frames['depttree'].removeNodeFun(nodes);
						}
					}
				};
				showMsg(data.content, data.type, {
					title : data.title,
					buttons : buttons
				});
			};

		});
	});
</script>
</head>
<body>

	<s:form action="department" method="post" cssClass="formmargin" id="searchForm" name="searchForm" cssStyle="display:block;"> 
	
		<table  border="0" cellspacing="8" cellpadding="0" >
			<tr>
				<td align="right" style="width:50px;"  nowrap="nowrap">
					部门名称
						<s:hidden id="pId" name="pId"></s:hidden>
						<s:hidden id="preKey" name="deptPO.prekey"></s:hidden>
				</td>
				 <td style="width:250px;" nowrap="nowrap">
						<s:textfield id="orgname" name="deptPO.name" cssClass="input2"/>
				</td>
				<td width="98" rowspan="2"><s:submit value="" cssClass="search" /></td>
			</tr>
		</table>
	</s:form>
	<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="view,add,edit,delete,role"/>
	</div>
	<div id="deptDiv" style="display: none;"></div>
</body>
</html>