<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<s:action name="include(mainCSS,jquery,ztree,validatorJS,utilJS,grid,commonJS)" executeResult="true" />
<title><s:text name="消息推送 按地区推送" />-<s:text name="application.title" /></title>
<style type="text/css">
  .textareaSMSPhone{
	border:#8BDED2 solid 1px;
	height:74px;
	width: 100%;
	resize:none;
}
.textareaSMSContent{
	border:#8BDED2 solid 1px;
	height:258px;
	width: 100%;
	resize:none;
}
.inputSearchBackground{
	padding-left: 20px;
    background-image:url(<%=basePath %>/res/jquery-easyui-1.2.6/themes/icons/search.png);
    background-repeat: no-repeat;
}
.ztree li span.button.firstPage {float:right; margin-left:2px; margin-right: 0; background-position:-144px -16px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.prevPage {float:right; margin-left:2px; margin-right: 0; background-position:-144px -48px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.nextPage {float:right; margin-left:2px; margin-right: 0; background-position:-144px -64px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.lastPage {float:right; margin-left:2px; margin-right: 0; background-position:-144px -32px; vertical-align:top; *vertical-align:middle}
</style>
<script type="text/javascript">
    //初始化树
	var nodes = <s:property value="userTree" escape="false"/>;
	//var nodes = {name:"人员列表", t:"请点击分页按钮", id:"1", count:2000, page:0, pageSize:100, isParent:true};
	var ztreeObj;
	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				//onClick: onClickFun,
				onCheck: zTreeOnCheck

			},
			check: {//复选框
				enable: true
			} 
	};
	
	function zTreeOnCheck(event, treeId, treeNode) {
	  // alert(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
	    var phoneNum = $('#nameTextArea').val();
	    var isParent = treeNode.isParent;
	   
	  //  alert(nodes);
	    //return ;
	    if(isParent)//根节点
	    {
            if(treeNode.checked)//根节点选中时，添加到文本框
		    {
            	var s_child="";
            	var treeObj = $.fn.zTree.getZTreeObj("ztree");
         	    var nodes = treeObj.getCheckedNodes();
         	    for(var i=0;i<nodes.length;i++)
         	    {
         	    	if(nodes[i].isParent){
         	           //s_parent+=nodes[i].name+",";//父节点
         	         }else{
         	           s_child+=nodes[i].name+",";//子节点
	         	         var str=nodes[i].name;
	         	         var arr= str.split("---");
	         	 	     var name=arr[0];
	         	 	      var phone=arr[1];
	         	 	    phone = phone.substr(phone.indexOf('('));
	    		 
	         	 	    if(phoneNum.indexOf( name+ '<' +phone+ '>')<0){
	 					   phoneNum += name+ '<' +phone+ '>,';
	 					   $('#nameTextArea').val(phoneNum);
	 				      }
         	       }
         	    }
		    }
            else//根节点反选,清空
            {
            	phoneNum = "";
            	$('#nameTextArea').val("");
            	var s_child="";
            	var treeObj = $.fn.zTree.getZTreeObj("ztree");
         	    var nodes = treeObj.getCheckedNodes();
         	    for(var i=0;i<nodes.length;i++)
         	    {
         	    	if(nodes[i].isParent){
         	           //s_parent+=nodes[i].name+",";//父节点
         	         }else{
         	           s_child+=nodes[i].name+",";//子节点
	         	         var str=nodes[i].name;
	         	         var arr= str.split("---");
	         	 	     var name=arr[0];
	         	 	      var phone=arr[1];
	         	 	    phone = phone.substr(phone.indexOf('('));
	    		
	         	 	    if(phoneNum.indexOf( name+ '<' +phone+ '>')<0){
	 					   phoneNum += name+ '<' +phone+ '>,';
	 					   $('#nameTextArea').val(phoneNum);
	 				      }
         	       }
         	    }
            }
	    }
	    else
	    {
	    	var str = treeNode.name;
	 	   var arr= str.split("---");
	 	   var name=arr[0];
	 	   var phone=arr[1];
	 	  phone = phone.substr(phone.indexOf('('));

		    if(treeNode.checked)//选中时，添加到文本框（如果文本框中没有该人）
		    {
		    	
				if(phoneNum.indexOf( name+ '<' +phone+ '>')<0){
					phoneNum += name+ '<' +phone+ '>,';
					$('#nameTextArea').val(phoneNum);
				}
		    }
		    else//取消时，从文本框中删除(如果文本框中有该人)
		    {
		    	var del=name+ '<' +phone+ '>,';
		    	if(phoneNum.indexOf( name+ '<' +phone+ '>')>=0)
		    	{
		    		$('#nameTextArea').val(remove(phoneNum,del));
		    	}
		    }
	    }
	};
	//字符串中删除指定字符，返回删除后的字符串
	function remove(str,del) {
		 return str.replace(del,"");
	} 
	//选中推送人，并赋值到文本域
	/* function onClickFun(event, treeId, treeNode){
		var phoneNum = $('#nameTextArea').val();
		var isParent = treeNode.isParent;
		if(!isParent){
			var name = treeNode.name;
			var phone = treeNode.phone;
			if(phoneNum.indexOf( name+ '<' +phone+ '>')>=0){
				showMsg('您选择的收件人已经存在！',2,'');
				return false;
			}
			if(!isEndStr(phoneNum)){
				phoneNum += ',';
			}
			phoneNum += name+ '<' +phone+ '>,';
		}
		$('#nameTextArea').val(phoneNum);
	} */
	
	// 判断已有的字符串最后一位是否是','
	function isEndStr(phoneNumStr){
		if(''==phoneNumStr){
			return true;
		}
		var strLen = phoneNumStr.length;
		var endStr = phoneNumStr.substr(strLen-1,1);
		if(','==endStr){
			return true;
		}else{
			return false;
		}
	}
	
	$(function(){
		ztreeObj = $.fn.zTree.init($("#ztree"), setting, nodes);
		// 给提交按钮绑定 click事件
		$("#SMSSendSubmit").click(function(){			
			if(!validateNameAndContent()){//验证表单信息
				return false;				
			}
			//获取姓名和发送内容
			getNameAndContent();
			// 提交表单
			subdiv();
		});
		 // 定义收件人文本域失去焦点事件
         $("#nameTextArea").focusout(function(){
        	// 格式化文本域
        	formatPhoneNum();
       	}); 
	});

	// 格式化收件人文本域中的内容
	function formatPhoneNum(){
		// 格式化之后的值
		var formatAfter = '';
		// 格式化之前的值
		var phoneNum = $.trim($('#nameTextArea').val());
		if(''==phoneNum){
			return true;
		}
		if(isEndStr(phoneNum)){
			phoneNum = phoneNum.substring(0,(phoneNum.length-1));
		}
		var arrPhoneNum = phoneNum.split(',');
		for(var i=0;i<arrPhoneNum.length;i++){
			if ((arrPhoneNum[i].indexOf('<'))>0 && (arrPhoneNum[i].indexOf('>'))>0){
				formatAfter += arrPhoneNum[i] + ',';
				continue;
			}
			formatAfter += arrPhoneNum[i] + '<' + arrPhoneNum[i] + '>,';
		}
		$('#nameTextArea').val(formatAfter);
	}	
	// 校验是否有推送人和内容,推送人是否合法
	function validateNameAndContent(){		
		// 验证推送人
		var nameText = $('#nameTextArea').val();
		if($.trim(nameText) == ''){
			showMsg('请选择推送人',2,'');
			return false;
		}
		//验证内容
		var smsContent = $("#contentTextArea").val();
		if($.trim(smsContent) == ''){
			showMsg('请输入推送内容！',2,'');
			return false;
		}
		if($.trim(smsContent).length>256||$.trim(smsContent).length<0)
		{
			showMsg('内容字数在0到256之间！',2,'');
			return false;
		}
		return true;
	}
	
	// 获取推送人和推送内容
	function getNameAndContent(){
		var tempName = $('#nameTextArea').val();
		var tempPhone = $('#contentTextArea').val();
		jQuery("#hidden_name").val(tempName);
		jQuery("#hidden_content").val(tempPhone);
	}

	/*
	 提交方法
	*/
	function subdiv(){
		if(!jQuery('#s002Form').valid()){
	        return;
	    }
		jQuery.post(
			$$.getContextPath()+'smart/s002!doPush',
			jQuery('#s002Form').serialize(),
			function(msg){
				showMsg(msg.message,2,'');
				clearAllContent();
			}
		);
	}
	
	// 清空所有内容,为下次发送做准备
	function clearAllContent(){
		$("#contentTextArea").val("");
        $("#hidden_name").val("");
        $("#hidden_content").val("");
        $('#nameTextArea').val("");
        var treeObj = $.fn.zTree.getZTreeObj("ztree");
 	    var nodes = treeObj.getCheckedNodes();
 	    //取消所有节点选中状态
 	    for(var i=0;i<nodes.length;i++)
 	    {
 	    	
 	    	treeObj.checkNode(nodes[i],false,false);
 	
 	    }
	}
	
	$(function(){
		//模糊搜索
        $('#searchInput').bind('keypress',function(event){
            if(event.keyCode == "13"){
                jQuery.post(
           			$$.getContextPath()+'smart/s002!initUserTreeByUserName',
           			jQuery('#suserActionForm').serialize(),
           			function(msg){
           				ztreeObj = $.fn.zTree.init($("#ztree"), setting, msg.dataObject);
           				ztreeObj.expandAll(true);
           			});
                return false;
            }
        });
    });
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<!-- Save for Web Slices (短信发送页面.psd) -->
<table id="__01" width="1041" height="579" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2">
			<img src="images/push/tongyong_01.png" width="702" height="48" alt=""></td>
		<td rowspan="11">
			<img src="images/push/tongyong_02.png" width="23" height="579" alt=""></td>
		<td>
			<img src="images/push/tongyong_03.png" width="298" height="48" alt=""></td>
		<td rowspan="11">
			<img src="images/push/tongyong_04.png" width="17" height="579" alt=""></td>
		 <td>
			<img src="images/push/tongyong_18.gif" width="1" height="48" alt=""></td> 
	</tr>
	<tr>
		<td rowspan="5">
			<img src="images/push/tongyong_05.png" width="14" height="165" alt=""></td>
		<!-- 推送人 -->
		<td rowspan="2">
		
		    <s:textarea id="nameTextArea" name="nameTextArea" maxlength="100" cssClass="textareaSMSPhone"/>
			<span style="color:#BEBEBE; display:block;height:15px; ">您可以选择右侧的人员作为"推送人",多个推送人用<span style="color:#FF9797">半角逗号</span>分割,例如 :小张<span style="color:#F00;">,</span>小李</span>
		</td>
			<!-- 搜索框 -->	
		<td>
		     <s:form method="post" action="u001PO.s002" id="suserActionForm" enctype="multipart/form-data" style="width:28px">
		     	<s:hidden name="u001PO.ma020" value="byArea"/>
				<s:textfield id="searchInput" name="u001PO.ma008" cssClass="inputSearchBackground" value="输入姓名,按回车搜索"  maxlength="20"
			    onfocus="this.value=''"
			    onblur="if(this.value==''){this.value='输入姓名,按回车搜索'}"/>
			</s:form>
		<td>
			<img src="images/push/tongyong_18.gif" width="1" height="28" alt=""></td>
	</tr>
	<tr>
	    <!-- ztree区域 -->
		<td rowspan="8">  
		
		 <!-- <div id="opertree" style="height: 485px; width: 298px;background-color: red;OVERFLOW-Y: auto;" >
				<ul id="ztree" class="ztree"></ul>
		</div> -->
		<div id="opertree" style="height: 485px; width: 298px;overflow-y: auto;" >
				<ul id="ztree" class="ztree"></ul>
		</div>
		
		</td>
		<td>
			<img src="images/push/tongyong_18.gif" width="1" height="61" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="images/push/tongyong_09.png" width="688" height="30" alt=""></td>
		<td>
			<img src="images/push/tongyong_18.gif" width="1" height="30" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="images/push/tongyong_10.png" width="688" height="15" alt=""></td>
		<td>
			<img src="images/push/tongyong_18.gif" width="1" height="15" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="images/push/tongyong_11.png" width="688" height="31" alt=""></td>
		<td>
			<img src="images/push/tongyong_18.gif" width="1" height="31" alt=""></td>
	</tr>
	<tr>
		<td rowspan="5">
			<img src="images/push/tongyong_12.png" width="14" height="366" alt=""></td>
		<!-- 推送内容 -->
		<td>
		    <s:textarea id="contentTextArea" name="contentTextArea"  style="border:#8BDED2 solid 1px;height:258px;width: 100%;resize:none;"
		     cssClass="textareamodify required {maxlength:256,minlength:0}" />
		</td>
		<td>
			<img src="images/push/tongyong_18.gif" width="1" height="261" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="images/push/tongyong_14.png" width="688" height="22" alt=""></td>
		<td>
			<img src="images/push/tongyong_18.gif" width="1" height="22" alt=""></td>
	</tr>
	<!-- 推送按钮 -->
	<tr>
		<td align="center" >
		     <form method="post" action="s002" id="s002Form" enctype="multipart/form-data">	
		         <input type="hidden" id="hidden_name" name="s002PO.ma006">  
		         <input type="hidden" id="hidden_content" name="s002PO.ma005"> 
			     <img src="<%=basePath%>images/push/dxqf.png" id="SMSSendSubmit">	 
			</form>	
			</td>
		<td>
			<img src="images/push/tongyong_18.gif" width="1" height="50" alt=""></td>
	</tr>
	<tr>
		<td rowspan="2">
			<img src="images/push/tongyong_16.png" width="688" height="33" alt=""></td>
		<td>
			<img src="images/push/tongyong_18.gif" width="1" height="15" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="images/push/tongyong_17.png" width="298" height="18" alt=""></td>
		<td>
			<img src="images/push/tongyong_18.gif" width="1" height="18" alt=""></td>
	</tr>
</table>
<!-- End Save for Web Slices -->
</body>
</html>