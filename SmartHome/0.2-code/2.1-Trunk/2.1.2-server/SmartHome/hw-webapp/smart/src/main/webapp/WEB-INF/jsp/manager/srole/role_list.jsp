<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.hw.hwsafe.platform.userinfo.UserSession"%>
<%@page import="com.hw.hwsafe.platform.constants.Constants"%>
<%@page import="com.hw.hwsafe.platform.pojo.UserPO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<% 
	UserPO userPO = ((UserSession)session.getAttribute(Constants.USER_SESSION_KEY)).getUserPO();
	String userType = userPO.getUSER_TYPE();
		   userType = userType == null ? "" : userType;
	String orgId = userPO.getORGAN_UUID();
	String creator = userPO.getUUID();
%>
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<s:action name="include(mainCSS,jquery,ztree,validatorJS,grid,commonJS)" executeResult="true" />
<title><s:text name="label.role" />-<s:text name="application.title" /></title>
<script type="text/javascript" src="<s:url value="/js/util/links.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/manager/role.js"/>"></script>
<script type="text/javascript">
var userType = "<%= userType%>";
var gridOption = {
		caption:'角色管理',
		colModel:[
			{label:'id', name:'UUID', index:'UUID',hidden:true,hidedlg:true},
			{label:'授权人类别', name:'USER_TYPE', index:'USER_TYPE',hidden:true,hidedlg:true},
			{label:'企业/政府ID', name:'ORGAN_UUID', index:'ORGAN_UUID',hidden:true,hidedlg:true},
			{label:'角色名称', name:'ROLE_NAME', index:'ROLE_NAME',formatter:queryDetails,formatoptions:{divId:'roleDiv',needURL:true,action:'manager/sysrole!doView',param:'',idName:'sysRolePO.UUID',postion:'0',title:'角色信息'}},
			{label:'授权类别', name:'USER_TYPE_NAME', index:'USER_TYPE_NAME'},
			{label:'备注', name:'REMARK', index:'REMARK'}
		],
		pk:'UUID',
		formId:'searchForm',
		winTitle:'角色信息'
};
var actionOption = {
		init:false
};

$(function(){
			
			$$.bindButton('add',function(){
				var divId = 'roleDiv',
					title = '添加角色',
					url = 'manager/sysrole!doAdd',
					param='',
					callback=function(){};
				$$.openDiv(divId, title, url, param, callback);
			});
			$$.bindButton('edit',function(){
				 var gridId="#gridTable";
				 if(!$$.checkSelRow(1)) return;
				 var ids = getSelectedRowID(gridId);
				 var id = ids[0];
				 
				 var divId = 'roleDiv',
					title = '修改角色',
					url = 'manager/sysrole!doEdit',
					param='sysRolePO.UUID='+id,
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
				var divId = 'roleDiv',
				title = '浏览角色',
				url = 'manager/sysrole!doView',
				param='',
				callback=function(){};
				
				param += 'sysRolePO.UUID='+id;
			
				$$.openDiv(divId, title, url, param, callback);
			});
			
});




</script>
</head>

<body>

	<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">

				<s:form action="role" method="post" id="searchForm" cssClass="formmargin">

					<table border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td style="width: 50px;" align="right">
								角色名称
							</td>
							<td style="width: 300px;">
								<input name="sysRolePO.ORGAN_UUID" value="<%=orgId%>" type="hidden"/>
								<%
									if(!userType.equals("SYS")){
							  	%>
										<input name="sysRolePO.USER_TYPE" value="<%=userType%>" type="hidden"/>
							  	<%
									}
								%>
								<s:textfield name="sysRolePO.ROLE_NAME" cssClass="input2"/>
							</td>
							<td width="98" rowspan="2">
								<s:submit value="" cssClass="search" />
							</td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>
	</div>
	<div id="hiddlebutton">
		<a href="#">
			<img src="images/searchhiddleicon.jpg" width="71" height="13" />
		</a>
	</div>
	<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="view,add,edit,delete,role,permi"/>
	</div>
	<div id="roleDiv" style="display: none;"></div>
</body>
</html>