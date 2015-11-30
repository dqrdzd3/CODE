<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main-right</title>
<style type="text/css">
body,html {overflow:hidden; background-color:#FFF; overflow-x:hidden;}
#welcome {width:783px; height:548px; margin:0 auto; position:relative;}
</style>
<s:action name="include(mainCSS,jqueryJS,jqueryUI,utilJS)" namespace="" executeResult="true"/>
<s:if test="#application.config.tabs">
<script type="text/javascript" src="js/tabs.js"></script>
<link  type="text/css" rel="stylesheet" href="<s:url value="/res/jquery-easyui-1.2.6/themes/default/easyui.css"/>"/>
<link  type="text/css" rel="stylesheet" href="<s:url value="/res/jquery-easyui-1.2.6/themes/icon.css"/>"/>
<script type="text/javascript" src="<s:url value="/res/jquery-easyui-1.2.6/plugins/jquery.tabs.js"/>"></script>
<script type="text/javascript" src="<s:url value="/res/jquery-easyui-1.2.6/plugins/jquery.panel.js"/>"></script>
</s:if>
<script type="text/javascript">
/**
 * 退出提示
 */
function onLogout(){
	var divId= "logoutDiv";

	var options = {
			title : '退出提示',
			buttons : {
				'取消' : function() {
					$(this).dialog('close');
				},
				'确定' : function() {
					$(this).dialog('close');
					top.location = location.pathname.match("/[^/]*/?")[0]
							+ "login!logout.action";
				}
			}
		};
	openDialog(divId, options);

}

/**
 * 权限判断提示
 */
function permiNotice(data){
	
	var divId= "permiNoticeDiv";

	var options = {
			title : '提示',
			buttons : {
				'确定' : function() {
					$(this).dialog('close');
				}
			}
		};
	
	$("#noticeDiv","#"+divId).html(data);
	openDialog(divId, options);
}
 
function openDialog(divId, options) {
	var option = $.extend({
		title : '信息提示',
		zIndex : 99999,
		autoOpen : false,
		height : '180',
		width : '280',
		resizable : false,
		modal : true,
		show : {
			effect : 'drop',
			direction : 'up'
		},
		hide : {
			effect : 'drop',
			direction : 'down'
		},
		dialogClass : 'jumpdiv-dialog'
	}, options);
	var idstr = "#" + divId;
	var divDialog = $(idstr).dialog(option);
	divDialog.dialog('open');
}
</script>
</head>

<body>
<s:include value="/WEB-INF/jsp/pagination/pageloading.jsp"></s:include>
	<div id="bodyMainContainer">
	
		<div id="homePage" title="首页">
			
			<s:action namespace="/dispatch" name="p002!filterOrgIndex" executeResult="true"/>
			
		</div>
	</div>
	<!-- 登录退出Div start -->
	<div id="logoutDiv" style="display: none; padding:10px;">
		<div id="radiv" style="height: 100%;">
			<table  border="0" cellspacing="6" cellpadding="0" style="width:100%;height: 100%;">
		 		 <tbody>
			 		<tr>
			    		<td style="font-size:14px;  color:#F00;height:30px;text-align:center;">您确定要退出系统吗？</td>
			    	</tr>
			    </tbody>
		    </table>
		 </div>
	</div>
	<!-- 登录退出Div end -->
	<!-- 业务权限提示Div start -->
	<div id="permiNoticeDiv" style="display: none; padding:10px;">
		<div id="radiv" style="height: 100%;">
			<table  border="0" cellspacing="6" cellpadding="0" style="width:100%;height: 100%;">
		 		 <tbody>
			 		<tr>
			    		<td style="font-size:14px;  color:#F00;height:30px;text-align:center;" id="noticeDiv">您暂无权使用此业务，</br>请联系管理员！</td>
			    	</tr>
			    </tbody>
		    </table>
		 </div>
	</div>
	<!-- 业务权限提示Div end -->
</body>
</html>
