<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>

<%@page import="com.hw.hwsafe.platform.userinfo.UserSession"%>
<%@page import="com.hw.hwsafe.platform.constants.Constants"%>
<%@page import="com.hw.hwsafe.platform.pojo.UserPO"%>
<% 
	UserPO userPO = ((UserSession)session.getAttribute(Constants.USER_SESSION_KEY)).getUserPO();
	String userId = userPO.getUUID();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<s:action name="include(mainCSS,jquery,ztree,validatorJS,grid,commonJS)" executeResult="true" />
<title><s:text name="用户管理" />-<s:text name="application.title" /></title>
<script type="text/javascript" src="<s:url value="/js/util/links.js"/>"></script>
<script type="text/javascript" src='<s:url value="/js/manager/user.js"></s:url>'></script>
<script type="text/javascript">
	var gridOption = {
		caption : '用户管理',
		colModel : [ 
			 {label : '用户ID',name : 'UUID',index : 'UUID',hidden:true,hidedlg:true},
             {label : '状态',name : 'USER_STATUS',index : 'USER_STATUS',hidden:true,hidedlg:true},
             {label : '账户名称',name : 'LOGIN_NAME',index : 'LOGIN_NAME',formatter:queryDetails,formatoptions:{divId:'userDiv',needURL:true,action:'manager/sysuser!doView',idName:'sysUserPO.UUID',postion:'0',title:'用户信息'}},
             {label : '真实姓名',name : 'REAL_NAME',index : 'REAL_NAME'},
             {label : '手机',name : 'MOBILE_NUMBER',index : 'MOBILE_NUMBER'},
             {label : '邮箱',name : 'EMAIL',index : 'EMAIL'},
             {label : '员工编号',name : 'EMPLOYEE_NUM',index : 'EMPLOYEE_NUM'},
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
						url = 'manager/sysuser!doAdd',
						param='',
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
						url = 'manager/sysuser!doEdit',
						param='sysUserPO.UUID='+id,
						callback=function(){
						$("#userDiv #userType").change();
					};
						
					
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
					var divId = 'userDiv',
					title = '浏览用户',
					url = 'manager/sysuser!doView',
					param='',
					callback=function(){
						$("#userDiv #userType").change();
					};
					
					param += 'sysUserPO.UUID='+id;
				
					$$.openDiv(divId, title, url, param, callback);
				});
				
// 				$$.addButton( '权限分配', assignPermission,'lipermi', 'operating');
// 				$$.addButton( '密码重置', reSetPassWord,'lipermi', 'operating');

	});

	
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
							<!-- 用于作判断，若为1，查询系统非管理员用户和企业政府管理员用户 -->
							<s:hidden name="sysUserPO.IS_ADMIN" value="1"/>
							<input name="sysUserPO.UUID" value="<%= userId%>" type="hidden"/>
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
		<hwsoft:operation code="${code}" param="view,add,edit,delete,permi,password"/>
	</div>
	<div id="userDiv" style="display: none"></div>
	<div id="xzDiv" style="display: none"></div>
</body>
</html>