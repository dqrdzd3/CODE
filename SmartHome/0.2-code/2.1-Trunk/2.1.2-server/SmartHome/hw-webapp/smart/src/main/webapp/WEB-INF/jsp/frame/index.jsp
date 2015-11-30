<%@page import="com.hw.hwsafe.platform.userinfo.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String userId = SessionUtil.getUserId();
%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>汉威智能家居业务处理平台</title>

<!-- 马宁于2013-09-04添加pushlet的代码 -->
 <s:action name="include(jqueryJS)" namespace="" executeResult="true"/> 
 <script type="text/javascript" src="res/pushlet/ajax-pushlet-client.js"></script>
<script type="text/javascript">
	<%-- PL.hwSessionId="<%=userId%>";   //指定连接的sessionid为客户登录id
	
	PL._init();
	PL.joinListen("hwUnicastEvent");
	
	// 登录时请求推送
	$.get("pushlet/pushletMsg!unicastCurrentUserMsg");
	
	//top.open('http://www.baidu.com','dsaf');
	var hh = window.screen.height - 320;
	var ww = window.screen.width - 380;
    var obj = top.open ('msg.jsp','msg','height=320,width=380,top=0,left='+ww+',top='+hh+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no,z-look=yes')
	
	/*
	function aaa(){
		alert('sdaf');
		var divObj = obj.document.getElementById('hanwei');
		divObj.innerHTML = '';
	}
	*/
	function handleEvent(event){

		//alert(event.get("msgUrl"));
		
		// main_right_home_sys.jsp中定义的id为wrap_msg_msg的div
		//获取消息的div
  		var divObj = obj.document.getElementById('hanwei');
		//var str = divObj.innerHTML; 
		
		//添加一条
		var msg = '<div class="content"><div class="name"><span class="date">2013-09-24 10:22</span></div>';
		msg += '<div class="main" >'+event.get("msg")+'</div><div  class="chakan"><ul><li><a href="#">查看</a></li></ul></div></div>';
		
		divObj.innerHTML += msg;
		//alert($wrapMsgDiv.attr("class"));
	}
	
	// event中的key: msg, msgId, msgUrl
	function onData(event){
		
		// 处理event
		handleEvent(event);
		
		// 删除已处理过的消息
	//	$.get("pushlet/pushletMsg!delMsg?msgId=" + event.get("msgId"));
	} --%>
</script> 

</head>
<frameset rows="57,*,4" frameborder="no" border="0" framespacing="0" style="background-color:#0a6392">
	<frame src="<s:url value="/frame/"/>header.action" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
	<frame src="<s:url value="/frame/"/>main.action" name="mainFrame" id="mainFrame" title="mainFrame" />
	<frame src="<s:url value="/frame/"/>footer.action" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" title="bottomFrame" />
	<noframes>
		<body>
			 
			
		</body>
	</noframes>
</frameset>
 
</html>

