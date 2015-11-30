<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>

<%@page import="com.hw.hwsafe.platform.userinfo.UserSession"%>
<%@page import="com.hw.hwsafe.platform.constants.Constants"%>
<%@page import="com.hw.hwsafe.platform.pojo.UserPO"%>
<% 
	UserPO userPO = ((UserSession)session.getAttribute(Constants.USER_SESSION_KEY)).getUserPO();
	String userType = userPO.getUSER_TYPE();
	String orgId = userPO.getORGAN_UUID();
	String userId = userPO.getUUID();
%>
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<s:action name="include(mainCSS,jquery,ztree,validatorJS,grid,commonJS)" executeResult="true" />
<title><s:text name="用户管理" />-<s:text name="application.title" /></title>
<style type="text/css">
<!--
body,html {background:url(images/contentleftbg.jpg) left top repeat-y;
 	overflow-x:hidden;
}

-->
</style>
<script type="text/javascript" src="<s:url value="/js/util/links.js"/>"></script>
<script type="text/javascript" src='<s:url value="/js/manager/orguser.js"></s:url>'></script>
<script type="text/javascript">
var s_userType = "<%= userType%>";
var s_orgId = "<%= orgId%>";
	var gridOption = {
		caption : '用户管理',
		colModel : [ 
			 {label : '用户ID',name : 'UUID',index : 'UUID',hidden:true,hidedlg:true},
			 {label : '状态',name : 'USER_STATUS',index : 'USER_STATUS',hidden:true,hidedlg:true},
			 {label : '是否支持手持设备',name : 'MOBILE_DEVICE_ACCESS',index : 'MOBILE_DEVICE_ACCESS',hidden:true,hidedlg:true},
			 {label : '机构Id',name : 'ORGAN_UUID',index : 'ORGAN_UUID',hidden:true,hidedlg:true},
             {label : '账户名称',name : 'LOGIN_NAME',index : 'LOGIN_NAME',formatter:queryDetails,formatoptions:{divId:'userDiv',needURL:true,action:'manager/orguser!doViewOrg',idName:'sysUserPO.UUID',postion:'0',title:'用户信息',fun:getDeptId}},
             {label : '真实姓名',name : 'REAL_NAME',index : 'REAL_NAME'},
             {label : '手机',name : 'MOBILE_NUMBER',index : 'MOBILE_NUMBER'},
             {label : '邮箱',name : 'EMAIL',index : 'EMAIL'},
             {label : '员工编号',name : 'EMPLOYEE_NUM',index : 'EMPLOYEE_NUM'},
			 {label : '是否支持手持设备',name : 'MEAccess',index : 'MEAccess'},
             {label : '状态',name : 'USER_STATUS_NAME',index : 'USER_STATUS_NAME'}
		],
		pk : "UUID",
		formId : "searchForm"
	};
	
	var actionOption = {
			init:false
	};
	$(function(){
				
				$$.bindButton('add',function(){
					var divId = 'userDiv',
						title = '添加用户',
						url = 'manager/orguser!doAddOrg',
						param='deptID='+$("#searchForm #deptId").val(),
						callback=function(){
					};
					$$.openDiv(divId, title, url, param, callback);
				});
				$$.bindButton('edit',function(){
					 var gridId="#gridTable";
					 if(!$$.checkSelRow(1)) return;
					 var ids = getSelectedRowID(gridId);
					 var id = ids[0];
					 
					var divId = 'userDiv',
						title = '修改用户',
						url = 'manager/orguser!doEditOrg',
						param='sysUserPO.UUID='+id,
						callback=function(){
						$("#userDiv #userType").change();
					};
						
						param +='&deptID='+$("#searchForm #deptId").val();
					
					$$.openDiv(divId, title, url, param, callback);
				});
				$$.bindButton('delete',function(){
					doOrgDelete();
				});
				$$.bindButton('view',function(){
					 var gridId="#gridTable";
					 if(!$$.checkSelRow(1)) return;
					 var ids = getSelectedRowID(gridId);
					 var id = ids[0];
					var divId = 'userDiv',
					title = '浏览用户',
					url = 'manager/orguser!doViewOrg',
					param='',
					callback=function(){
						$("#userDiv #userType").change();
					};
					
					param += 'sysUserPO.UUID='+id;
					param +='&deptID='+$("#searchForm #deptId").val();
				
					$$.openDiv(divId, title, url, param, callback);
				});
				
	});
	function getDeptId(){
		return 'deptID='+$("#searchForm #deptId").val();
	}
	
</script>
</head>

<body>
	<div id="searcharea" style="margin-left: 10px;">
		<div id="searcharealeft">
			<div id="searcharearight">
				<s:form  method="post" cssClass="formmargin"
					id="searchForm">
					<table border="0" cellspacing="8" cellpadding="0">
						<tr>
							<input name="sysUserPO.USER_TYPE" value="<%= userType%>" type="hidden"/>
							<input name="sysUserPO.ORGAN_UUID" value="<%= orgId%>" type="hidden"/>
							<input name="sysUserPO.UUID" value="<%= userId%>" type="hidden"/>
							<s:hidden name="sysUserPO.IS_ADMIN" value="0"/>
							<s:hidden id="deptId" name="sysUserPO.DEPART_UUID"/>
							<td style="width: 80px;" align="right">账户名称：</td>
							<td style="width: 280px;">
								<s:textfield name="sysUserPO.LOGIN_NAME" cssClass="input2" />
							</td>
							<td style="width: 80px;" align="right">真实姓名：</td>
							<td style="width: 280px;">
								<s:textfield name="sysUserPO.REAL_NAME" cssClass="input2" />
							</td>
							<td style="width: 80px;" align="right">状态：</td>
							<td style="width: 280px;">
								<s:select list="userStatus" listKey="value" listValue="display" name="sysUserPO.USER_STATUS" headerKey="" headerValue="请选择" cssClass="input2" />
							</td>
							<td width="98" rowspan="2">
								<s:submit value="" cssClass="search"></s:submit>
							</td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>
	</div>
	<div id="hiddlebutton">
		<a href="#"><img src="images/searchhiddleicon.jpg" width="71"
			height="13" /></a>
	</div>
	<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="view,add,edit,delete,permi,password,bound"/>
	</div>
	<div id="userDiv" style="display: none"></div>
	<!-- 移动设备列表div -->
	<div id="MEDiv" style="display: none;"></div>
</body>
</html>