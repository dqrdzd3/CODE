<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta charset="UTF-8">
<title>空气电台数据系统</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<!-- Bootstrap 3.3.4 -->
<link href="<%=basePath%>public/toB/bootstrap/css/bootstrap-combined.min.css" rel="stylesheet" type="text/css" />


<!-- jQuery 1.9.1 --> 
<script src="<%=basePath%>public/toB/plugins/jQuery/jquery-1.9.1.min.js"></script>


<!-- 导入验证类库  -->
<script src="<%=basePath%>public/validate/jquery.js"></script> 
<script src="<%=basePath%>public/validate/jquery.validate.js"></script>
<script src="<%=basePath%>public/validate/messages_cn.js"></script>
<script src="<%=basePath%>public/validate/additional.js"></script>

<script>
$().ready(function(){
	var validator = $("#form_login").validate({
	    rules: {
	    		inputOldPwd: {required: true,bytemaxlength:16},
	    		inputNewPwd: {required: true,bytemaxlength:16},
	    		inputSure:{equalTo:"#inputNewPwd"},
	    },
	    		
	    messages: {
	    	inputOldPwd: {required: "请输入原始密码",
				   bytemaxlength:"密码最大长度:16"
					},
	    	inputNewPwd: {required: "请输入新密码",
	    				   bytemaxlength:"密码最大长度:16"
	    					},
	    	inputSure:{
	    		equalTo:"两次密码输入不一致"
	 	        }
	    		
	    		},
	    errorPlacement: function(error, element) {
	    	$("#msg").html("");
	       	error.appendTo("#message");
	    },
	    showErrors: function(errorMap, errorList) {
	       	while(errorList.length > 1) {
	        	errorList.pop();
	        }
	        if(errorList.length > 0) {
	            if($("#message").find("span.msgCss:visible:first").length != 0) {
	            	 
	            	$("#message").html("");
	            }
	        }
	        this.defaultShowErrors();
	    },
	    errorClass: "msgCss",
	    errorElement: "span",
	    highlight:""
	}); 

	
	$("#btn2").click(function(){
		var url = "<%=basePath%>hwmobile/smart/corpbusiness!doIndex?ACCOUNT="+localStorage.getItem("PHONE","")+"&SESSIONID="+localStorage.getItem("PASSWORD","");
	    //alert(url);
		window.location.href=url;
	});


 		$("#btn").click(function(){
 			
 			
 			
 			if(!validator.form()){
 				return;
 			} 
 			
 			
 			
 			
 			
 			$.post(
 					'<%=basePath%>hwmobile/smart/corpbusiness!changePwd',
 					{
 						account : localStorage.getItem('PHONE',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
 						oldpass : $("#inputOldPwd").val(),//'f3d46750-e022-4bf6-88d0-2893caed8b24',
 						newpass:$("#inputNewPwd").val()

 					}, function(data) {
 						
 						
 					//alert(data);
 					var dataObj=eval("("+data+")");
 					//alert(dataObj.code);
 					//$("#loading_msg").hide();
 					//data.dataObject
 					if(dataObj.code == 1){
 						//alert(dataObj.code);
 						//window.location="http://192.168.111.46:8080/smart/hwmobile/smart/corpbusiness!doIndex";
 						$("#message").html("");
 						$("#msg").html("秒后自动跳转到登录页面");
 						countDown(5,'http://192.168.111.46:8080/smart/hwmobile/smart/corpbusiness!doLogin');
 					}
 					if(dataObj.code == 0){
 						//alert("ddd");
 						$("#message").html("");
 						//window.location="http://192.168.111.46:8080/smart/hwmobile/smart/corpbusiness!doIndex";
 						$("#msg").html("原始密码输入错误，请重新输入");
 						//$("#msg").html("原始密码输入错误，请重新输入");
 					}
 						
 					
 					}); 
 		});
 		
 		
 		
 		
 	});
 	
function countDown(secs,surl){     
	 var jumpTo = document.getElementById('jumpTo');
	 jumpTo.innerHTML=secs;  
	 if(--secs>0){     
	     setTimeout("countDown("+secs+",'"+surl+"')",1000);     
	     }     
	 else{       
	     location.href=surl;     
	     }     
	 }     
</script>


</head>
<body>

<div class="container-fluid" style = "width:100%">
	<div class="row-fluid">
		<div class="span1">
		</div>
		<div class="span1" style="padding-top:50px;">
			<img alt="140x140" src="<%=basePath%>public/toB/dist/img/100.png" />
		</div>
		<div class="span5" style="padding-top:50px;">
			<h1 >
				<strong>空气电台数据系统</strong>
			</h1>
		</div>

	</div>

	<div> 
	<div class="row-fluid" style = "width:100%">
		
	
		<div class="span12">
			<form class="form-horizontal" id="form_login"  styleId="form_login" style="position:relative;margin-top:60px;padding-left:25%;">
			<div class="control-group">
					<label class="control-label" >原密码</label>
					<div class="controls">
						<input name="inputOldPwd" id="inputOldPwd" type="password" placeholder = "原密码"/>
						
					</div>
						
						
				</div>
				<div class="control-group">
					<label class="control-label" >新密码</label>
					<div class="controls">
						<input name="inputNewPwd" id="inputNewPwd" type="password" placeholder = "新密码"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" >密码确认</label>
					<div class="controls">
						<input name="inputSure" id = "inputSure" type="password" placeholder = "密码确认"/>
					</div>
					<div class="controls">
					</div>
				</div>
				
			</form>
			<div class="control-group">
			
					<div class="controls" style="margin-left:35%;">
						<div id="message" style = "color:red"></div>
						<span id="jumpTo"></span><span id = "msg" style = "color:red"></span>
						
					</div>
					<div class="controls" style="margin-left:40%">
					
						<button id = "btn" >提交</button>
						
						<button id = "btn2" >取消</button>
					</div>
				</div>
		</div>
	</div>


	</div>
	<!-- -->
	<div class="row-fluid">
		<div class="span2">
		</div>
		<div class="footer" style="position:relative; padding-left:450px;margin-top:150px">
			<p class="text-center">
				<span>Copyright © 2014-2015 <a href="http://www.airradio.cn/">北京威果智能科技有限公司</a>.</span> <span>All rights reserved.</span>
			</p>
		</div>
	</div>
</div>

</body>
</html>


