<%@page import="com.hw.hwsafe.utils.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.hw.hwsafe.platform.constants.Constants, com.hw.hwsafe.platform.pojo.UserPO,com.hw.hwsafe.platform.userinfo.UserSession"%>
<%
	UserSession userSession = (UserSession)session.getAttribute(Constants.USER_SESSION_KEY);
UserPO userPO = userSession.getUserPO();
String userType = userPO.getUSER_TYPE();
String isAdmin = userPO.getIS_ADMIN();

userType = StringUtil.string2DefVal(userType);
isAdmin = StringUtil.string2DefVal(isAdmin);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true" />"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main-left</title>
<s:action name="include(mainCSS,jquery,ztree,utilJS)" namespace="" executeResult="true"/>
<style type="text/css">
body,html {margin:0px; padding:0px; background-color:#EDF8FF; height:100%;}
</style>
<script type="text/javascript">
	function backHome() {
		if (location.href.indexOf('?') != -1)
			location.href = location.href.split('?')[0];
	}
	
	var treesStr = {};
	<s:iterator value="systemTreeStrList">
		$.extend(treesStr,{'<s:property value="id" escape="false"/>':'<s:property value="systemTree" escape="false"/>'});
	</s:iterator>
	
	
	
	$(function(){
		// accordion
		function resizeAcc() {
			$("#accordionContainer").height($("#mainleft").height() - $("#topimg1").height() - $("#topimg2").height() - $("#iconmeau").height());
			$("#leftmeau_permi").accordion("resize");
			$("#leftmeau_busi").accordion("resize");
		}
		$("#leftmeau_permi").accordion({
			collapsible: true,
			fillSpace:true,
			active:0
		});	
		$("#leftmeau_busi").accordion({
			collapsible: true,
			fillSpace:true,
			active:0
		});	
		resizeAcc();
		$(window).resize(function(){
			resizeAcc();
		});
		
		// ztree
		var settings = {
			view: {
				dblClickExpand: false
			},
			data:{
				simpleData:{enable:true}
			},
			
			callback:{
				onClick : function(event, treeId, node){
					// 单击展开节点
					if ($.fn.zTree.getZTreeObj(treeId).expandNode(node) !== null) {
						return;
					}

					var href = node.href,
						name = node.name,
						position = name,
						target = node.target,
						id = node.id;
					if (href) {
						<s:if test="#application.config.tabs">
							$$.getFrame('rightFrame').Tabs.openTab(id,name,href,position);
						</s:if>	
						<s:else>
							window.open('<s:url value="/"/>' + href,target);
						</s:else>
						$$.getFrame('righttopFrame').changePosition(position);
					}
				}
			}
		};
		$.each($("#accordionContainer .ztree"), function(i, n){
			var val = $(this).attr("value");
			$.fn.zTree.init($(this), settings, $.parseJSON(treesStr[val]));
		});
		
	});
	
	function accorActive(id) {
		checkPermi(id);
	}
	
		// 20130515 update by mfb: 兼容IE6修改
	function barClick($lfm, $h3) {
		if (navigator.userAgent.toLowerCase().indexOf('msie 6') != -1) {
			$h3.click();
		} else {
			$lfm.accordion("activate", $h3);
		}
	}
	
	function checkPermi(id){
		var url = 'manager/switchmodule',
			param = 'id='+id,
			callback = function(data){
				if(data == 'true'){
					if(id == "XT_QXGLXT" || id == "QY_QXGLXT" || id=="ZF_QXGLXT"){
						// IE6 not support
						// $("#leftmeau[name='permi']").show().siblings("div[id='leftmeau']").hide();
						// $("#leftmeau[name='permi']").accordion("activate", $("#leftmeau h3[id='" + id + "']"));
						$("#leftmeau_permi").show();
						$("#leftmeau_busi").hide();
						barClick($("#leftmeau_permi"), $("#leftmeau_permi h3[id='" + id + "']"));
					}else{
						// $("#leftmeau[name='busi']").show().siblings("div[id='leftmeau']").hide();
						// $("#leftmeau[name='busi']").accordion("activate", $("#leftmeau h3[id='" + id + "']"));
						$("#leftmeau_permi").hide();
						$("#leftmeau_busi").show();
						barClick($("#leftmeau_busi"), $("#leftmeau_busi h3[id='" + id + "']"));
					}

				}else{
					$$.getFrame("rightFrame").permiNotice(data);
				}
		};
		
		if(id=="BUSI"){
			// $("#leftmeau[name='busi']").show().siblings("div[id='leftmeau']").hide();
			// $("#leftmeau[name='busi']").accordion("activate", $("#leftmeau h3").eq(0));
			$("#leftmeau_permi").hide();
			$("#leftmeau_busi").show();
			barClick($("#leftmeau_permi"), $("#leftmeau_permi h3").eq(0));
		}else{
			$.post(url,param,callback);
		}
	}
	
// 	function accorActive(name) {
// 		backHome();
// 		$("#leftmeau").accordion("activate", $("#leftmeau h3:contains('" + name + "')"));
// 	}
	
</script>
</head>

<body>
<div id="mainleft" style="height:100%">
  <div id="topimg1"><img src="images/lefttop.jpg" width="198" height="31" /></div>
  <div id="topimg2"><img src="images/meau.jpg" width="198" height="28" /></div>
 
	<s:if test="#application.config.fourIcon">
		<script type="text/javascript">
			var classNames = {
				 "<s:property value="#application.config.Module_Daily" escape="false"/>": ["daily_disabled", "daily", "daily_sel"],
				 "<s:property value="#application.config.Module_Safe" escape="false"/>": ["administrative_disabled", "administrative", "administrative_sel"],
				 "<s:property value="#application.config.Module_Monitor" escape="false"/>": ["monitor_disabled", "monitor", "monitor_sel"],
				 "<s:property value="#application.config.Module_Rescue" escape="false"/>": ["rescue_disabled", "rescue", "rescue_sel"]
			};
		</script>
		<div id="iconmeau">
			<ul id="modulesIcon">
				<!-- <li><a title="日常普查" href="frame/main_left.action?id=1" target="leftFrame" class="daily"></a></li> -->
				<script type="text/javascript">
					<s:iterator value="userRootPerms">
							document.write('<li><a title="<s:property value="MENU_NAME" escape="false"/>" href="<s:if test="IS_VALID != 0">frame/main_left.action?id=<s:property value="UUID"/></s:if><s:else>javascript:void(0)</s:else>" target="leftFrame" class="' + classNames["<s:property value="MENU_NAME" escape="false"/>"][<s:property value="IS_VALID"/>] + '"></a></li>');
					</s:iterator>
				</script>
			</ul>
		</div>
	</s:if>
	  	 <div id="accordionContainer">
			  <div  id="leftmeau_busi" name="busi">
	 			<s:iterator value="showModules">
		 				<h3 id="<s:property value="MENU_CODE" escape="false"/>" ><s:property value="MENU_NAME" escape="false"/></h3>
		 				<ul id="<s:property value="UUID"/>_busi" value="<s:property value="UUID"/>" class="ztree"></ul>
	 			</s:iterator>
	  	 	 </div>
			  <div id="leftmeau_permi" name="permi" style="display: none;" >
	 			<s:iterator value="permiModules">
	 				<h3 id="<s:property value="MENU_CODE" escape="false"/>" ><s:property value="MENU_NAME" escape="false"/></h3>
	 				<ul id="<s:property value="UUID"/>_permi" value="<s:property value="UUID"/>" class="ztree"></ul>
	 			</s:iterator>
	  	 	 </div>
	  	</div>
	  			<!-- ie6 兼容性 -->
	  			<!--[if IE 6]> 
	 				<script type="text/javascript">  
		 				$(function(){
			 				$("#leftmeau_busi h3, #leftmeau_permi h3").unbind('click').click(function(){
			 					$(this).addClass("ui-state-active");
			 					$(this).siblings("h3").removeClass("ui-state-active");
			 					$(this).siblings("ul").hide();
			 					var ph = $("#accordionContainer").height(),
			 						hh = $(this).height(),
			 						num = $(this).siblings("ul").length;
			 						
			 					var h = ph - (hh + 2) * num;	
			 					var cul = $(this).next();
			 					
			 					cul.height(h).show();
			 				});
		 				});
	 				</script>
	 			<![endif]-->
</div>
</body>
</html>
