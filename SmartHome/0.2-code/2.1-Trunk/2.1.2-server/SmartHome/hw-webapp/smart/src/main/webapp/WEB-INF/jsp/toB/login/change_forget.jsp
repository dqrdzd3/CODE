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

<script>
$(function(){
 		$("#btn").click(function(){
 			$.post(
 					'<%=basePath%>hwmobile/smart/corpbusiness!savePwd',
 					{
 						phone : $("#inputPhone").val(),//'2F37FE55ECB0E3D27607A01E5312EE86',
 						code : $("#inputTest").val(),//'f3d46750-e022-4bf6-88d0-2893caed8b24',
 						newpass:$("#inputPassword").val()

 					}, function(data) {
 					//$("#loading_msg").hide();
 					//data.dataObject
 						
 					
 					}); 
 		});
 		
 		
 		$("#SMS").click(function(){
 			$.post(
 					'<%=basePath%>hwmobile/smart/corpbusiness!sendCode',
 					{
 						PHONE : $("#inputPhone").val()
 						

 					}, function(data) {
 					//$("#loading_msg").hide();
 					//data.dataObject
 					alert(data);
 						
 					
 					}); 
 		});
 		
 	});
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
			<form class="form-horizontal" style="position:relative;margin-top:60px;padding-left:25%;">
			<div class="control-group">
					<label class="control-label" >手机号</label>
					<div class="controls">
						<input id="inputPhone" type="text" placeholder = "手机号"/>
						<button id = "SMS" class="btn btn-default">短信验证</button>
					</div>
						
						
				</div>
				<div class="control-group">
					<label class="control-label" >验证码</label>
					<div class="controls">
						<input id="inputTest" type="text" placeholder = "验证码"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" >新密码</label>
					<div class="controls">
						<input id="inputPassword" type="password" placeholder = "新密码"/>
					</div>
					<div class="controls">
					</div>
				</div>
				<div class="control-group">
					<div class="controls" style="padding-left:100px;">
						<button class="btn" id = "btn" type="submit">提交</button>
					</div>
				</div>
			</form>
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


