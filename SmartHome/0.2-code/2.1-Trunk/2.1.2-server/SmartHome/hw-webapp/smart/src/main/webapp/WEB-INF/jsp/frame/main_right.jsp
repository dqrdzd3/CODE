<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.hw.hwsafe.platform.userinfo.SessionUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String userId = SessionUtil.getUserId();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main-right</title>
<style type="text/css">
body,html {overflow:hidden; background-color:#FFF; overflow-x:hidden;}
#welcome {width:783px; height:548px; margin:0 auto; position:relative;}
/* pop */
#pop{background:#fff;width:260px;border:1px solid #e0e0e0;font-size:12px;position:fixed;right:10px;bottom:10px;}
#popHead{line-height:32px;background:#f78e57;border-bottom:1px solid #e0e0e0;position:relative;font-size:12px;padding:0 0 0 10px;}
#popHead h2{font-size:14px;color:#fff;line-height:32px;height:32px;}
#popHead #popClose{position:absolute;right:10px;top:1px;}
#popHead a#popClose:hover{color:#f00;cursor:pointer;}
#popContent{padding:5px 10px;}
#popTitle a{line-height:24px;font-size:14px;font-family:'微软雅黑';color:#333;font-weight:bold;text-decoration:none;}
#popTitle a:hover{color:#f60;}
#popIntro{text-indent:24px;line-height:160%;margin:5px 0;color:#666;}
#popMore{text-align:right;border-top:1px dotted #ccc;line-height:24px;margin:8px 0 0 0;}
#popMore a{color:#f60;}
#popMore a:hover{color:#f00;}
</style>
<s:action name="include(mainCSS,jqueryJS,jqueryUI,utilJS)" namespace="" executeResult="true"/>
<s:if test="#application.config.tabs">
<script type="text/javascript" src="js/tabs.js"></script>
<link  type="text/css" rel="stylesheet" href="<s:url value="/res/jquery-easyui-1.2.6/themes/default/easyui.css"/>"/>
<link  type="text/css" rel="stylesheet" href="<s:url value="/res/jquery-easyui-1.2.6/themes/icon.css"/>"/>
<script type="text/javascript" src="<s:url value="/res/jquery-easyui-1.2.6/plugins/jquery.tabs.js"/>"></script>
<script type="text/javascript" src="<s:url value="/res/jquery-easyui-1.2.6/plugins/jquery.panel.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/yanue.pop.js"/>"></script>
<script type="text/javascript" src="<s:url value="/res/pushlet/ajax-pushlet-client.js"/>"></script>

</s:if>

<script type="text/javascript">
PL.hwSessionId="<%=userId%>";   //指定连接的sessionid为客户登录id

PL._init();
PL.joinListen("hwUnicastEvent");

// 登录时请求推送
$.get("pushlet/pushletMsg!unicastCurrentUserMsg");



function handleEvent(event){


	//获取消息的div

		var pop=new Pop(event.get('msg'),event.get('msgUrl'),"Discuz风格jQuery右下角弹出层提示 - 代码家园昨天上午9点半，河南商报记者在事发现场看到，一名女子头朝东躺在SOHO广场3楼平台的花坛里，上身穿紫色套头棉袄，下身穿褐色棉裤、白色袜子，一双棉拖鞋散落在旁，几名民警正在勘查、记录");
	
	
	
}

// event中的key: msg, msgId, msgUrl
function onData(event){
	
	// 处理event
	handleEvent(event);
	
	// 删除已处理过的消息
//	$.get("pushlet/pushletMsg!delMsg?msgId=" + event.get("msgId"));
}
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
					top.location = "<%=basePath%>" + "login!logout.action";
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

$(function(){
	$.ajaxSetup({
		complete:function(xhr, status){
			// 处理session失效
			if(xhr.getResponseHeader("sessionStatus") == "timeout"){ 
				top.location=location.pathname.match("/[^/]*/?")[0] + "login!logout.action";
			} 
		}
	});
});
</script>
</head>

<body>
<s:include value="/WEB-INF/jsp/pagination/pageloading.jsp"></s:include>
	<div id="bodyMainContainer">
	
		<div id="homePage" title="首页">
			
			<s:action name="main_right_home" executeResult="true"/>
			
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
<div id="pop" style="display:none;" >
	<div id="popHead"> <a id="popClose" title="关闭">关闭</a>
		<h2>温馨提示</h2>
	</div>
	<div id="popContent">
		<dl>
			<dt id="popTitle"><a href="http://www.daimajiayuan.com/" target="_blank">这里是标题</a></dt>
			<dd id="popIntro">这里是内容简介Discuz风格jQuery右下角弹出层提示 - 代码家园昨天上午9点半，河南商报记者在事发现场看到，一名女子头朝东躺在SOHO广场3楼平台的花坛里，上身穿紫色套头棉袄，下身穿褐色棉裤、白色袜子，一双棉拖鞋散落在旁，几名民警正在勘查、记录。</dd>
		</dl>
		<p id="popMore"><a href="http://www.daimajiayuan.com/" target="_blank">查看 »</a></p>
	</div>	
</div>
</body>
</html>
