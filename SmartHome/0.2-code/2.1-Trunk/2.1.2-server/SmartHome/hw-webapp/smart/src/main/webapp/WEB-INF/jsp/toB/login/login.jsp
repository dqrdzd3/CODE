<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>空气电台</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<!-- Bootstrap 3.3.4 
<link href="<%=basePath%>public/toB/bootstrap/css/bootstrap-combined.min.css" rel="stylesheet" type="text/css" />
-->
    <!-- Bootstrap 3.3.4 -->
<link href="<%=basePath%>public/toB/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

<!-- Font Awesome Icons -->
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link href="<%=basePath%>public/toB/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />

<style>
#loading1{  
    
    width:100%;  
    height:100%;  
    position: fixed;  
    text-align:center
    top:0%;  
    left:0%;  
    line-height:56px;  
    color:#fff;  
    padding-top:380px;  
    font-size:15px;  
   
    background: #000;
    opacity: 0.7;  
    z-index:999999;  
    
    filter:progid:DXImageTransform.Microsoft.Alpha(opacity=70); 
} 
</style>

<!-- 导入验证类库  -->
<script src="<%=basePath%>public/validate/jquery.js"></script>
<script src="<%=basePath%>public/validate/jquery.validate.js"></script>
<script src="<%=basePath%>public/validate/messages_cn.js"></script>
<script src="<%=basePath%>public/validate/additional.js"></script>

<script>
$().ready(function(){
	var validator = $("#form_login").validate({
	    rules: {
	    		username: {required: true,bytemaxlength:16},
	    		password: {required: true,bytemaxlength:16}
	    },
	    		
	    messages: {
	    		username:{required: "请输入用户名！",
	    			bytemaxlength:"用户名最大长度:16"
	    			},
	    		password: {required: "请输入密码！",
	    				   bytemaxlength:"密码最大长度:16"
	    					}
	    		},
	    errorPlacement: function(error, element) {
	    	$("#message").html("");
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
	
	$("#forget").click(function(){
		window.location.href="<%=basePath%>hwmobile/smart/corpbusiness!doForget";
		
	});
	
	$(window).keydown(function(event){
		  if(event.keyCode == 13) {
			  
			  if(!validator.form()){
					return;
				} 
				 $("#message").html("");
				 $("#loading1").show();
				$.post(
			      '<%=basePath%>hwmobile/smart/corpbusiness!doCorpLogin',
			      {
					ACCOUNT : $("#username").val(),
					PASSWORD : $("#password").val()
			      },
			      function (data,status) //回传函数
			      {
			    	  
			    	 	//alert(data);
			    	 	
			    	  	if(status=="success")
			              {
			    	  		$("#loading1").hide();
			    	  		var dataObj=eval("("+data+")");
			    	  		
			    	  		//alert(dataObj.ma009);
			    	  		
						    	 if(dataObj.code == 1)
						    	  {
						    		 
						    		 	  dataObj=eval("("+dataObj.data+")");
								    	  localStorage.setItem("PHONE",dataObj.ma009);
								    	  localStorage.setItem("PASSWORD",dataObj.ma011);
								    	  
								    	  
								    	  form = $("<form></form>");
								          form.attr('action',"<%=basePath%>hwmobile/smart/corpbusiness!doIndex");
								          form.attr('method','post');
								          input1 = $("<input type='hidden' name='ACCOUNT' />");
								          input1.attr('value',localStorage.getItem('PHONE',''));
								          input2 = $("<input type='hidden' name='SESSIONID' />");
								          input2.attr('value',localStorage.getItem('PASSWORD',''));
								          form.append(input1);
								          form.append(input2);
								         form.appendTo("body");
								          form.css('display','none');
								          form.submit();
						     		}else{
						     			$("#loading1").hide();
						     			$("#message").html("");
						     			$("#message").html("输入的用户名密码错误！");
						 				
			    	  				}
			              }
			    	  	else{
			    	  		$("#loading1").hide();
			    	  		$("#message").html("");
			     			$("#message").html("网络连接失败！");
			    	  	}
			      });
		    
		  }
		});
	
	
	$("#button").click(function(){
		
		
		 if(!validator.form()){
			return;
		} 
		 $("#message").html("");
		 $("#loading1").show();
		$.post(
	      '<%=basePath%>hwmobile/smart/corpbusiness!doCorpLogin',
	      {
			ACCOUNT : $("#username").val(),
			PASSWORD : $("#password").val()
	      },
	      function (data,status) //回传函数
	      {
	    	  
	    	 	//alert(data);
	    	 	
	    	  	if(status=="success")
	              {
	    	  		$("#loading1").hide();
	    	  		var dataObj=eval("("+data+")");
	    	  		
	    	  		//alert(dataObj.ma009);
	    	  		
				    	 if(dataObj.code == 1)
				    	  {
				    		 
				    		 
				    		 
				    		 	  dataObj=eval("("+dataObj.data+")");
						    	  localStorage.setItem("PHONE",dataObj.ma009);
						    	  localStorage.setItem("PASSWORD",dataObj.ma011);
						    	  localStorage.setItem("logo",dataObj.ma019);    //logo
						    	  
						    	  //$("#loading1").hide();
						    	  
						    	  //window.location="<%=basePath%>hwmobile/smart/corpbusiness!doIndex?ACCOUNT="+localStorage.getItem("PHONE","")+"&SESSIONID="+localStorage.getItem("PASSWORD","");
								
						    
						    		  //  	 ACCOUNT : localStorage.getItem('PHONE',''),
						    		     
										//	SESSIONID : localStorage.getItem('PASSWORD','') ,
						    	  form = $("<form></form>");
						          form.attr('action',"<%=basePath%>hwmobile/smart/corpbusiness!doIndex");
						          form.attr('method','post');
						          input1 = $("<input type='hidden' name='ACCOUNT' />");
						          input1.attr('value',localStorage.getItem('PHONE',''));
						          input2 = $("<input type='hidden' name='SESSIONID' />");
						          input2.attr('value',localStorage.getItem('PASSWORD',''));
						          form.append(input1);
						          form.append(input2);
						         form.appendTo("body");
						          form.css('display','none');
						          form.submit();	
						    		     
						    	  
						    	  
				     		}else{
				     			$("#loading1").hide();
				     			$("#message").html("");
				     			$("#message").html("输入的用户名密码错误！");
				 				
	    	  				}
	              }
	    	  	else{
	    	  		$("#loading1").hide();
	    	  		$("#message").html("");
	     			$("#message").html("网络连接失败！");
	    	  	}
	      });

	   });
});

</script>
</head>
<body>



<div id="loading1"  style="display:none"><center><img src="<%=basePath%>public/loading.gif" />Loading pages...</center></div>

<div class="container-fluid" style = "width:100%;padding:0px;">
	<div class="row-fluid">
		
		<div class="span1" style="padding:10px 5px 0 5px;text-align: left;margin-left:50px;">
			<img alt="140x140" src="<%=basePath%>public/toB/dist/img/logo.png" />
		
		
			
				<strong style=" margin:20px; line-height:40px;font-size:40px;">空气电台数据系统</strong>
			
		</div>

	</div>

	<div   style = "background: #89BAD6;margin-top:20px;width:100%; padding:100px 0;height:500px;"> 
	<div class="row-fluid" style = "width:100%">
		
		<div class="span6" style="position:relative; padding-left:200px;padding-top:30px; padding-bottom:50px">
			<!-- <h2>
				<strong>北京威果智能科技有限公司</strong>
			</h2>
			
			<h3 style  = "margin-left:50px;">
				空气电台  不只是空气质量检测
			</h3>
			
			<p style  = "margin-left:50px;">
				把海边的空气带回家

			</p>
			<p style  = "margin-left:50px;">
				室内空气的维修师，海边空气的搬运工
			</p>
			<p >
			</p> -->
		</div>
		
		
		<div class="login-box" style = "position:fixed;left:55%;top:20%;height：500px;background:#fff">
      
      <div class="login-box-body" >
        <p class="login-box-msg">登录 </p>
        <form id="form_login"  styleId="form_login"  >
          <div class="form-group has-feedback">
            <input type="text" class="form-control" placeholder="用户名" name="username" id="username" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" name="password" id="password" class="form-control" placeholder="密码">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">    
              <a id = "forget">忘记密码？</a>                       
            </div><!-- /.col -->
            </div>
            </form>
            <div class="col-xs-4">
              <button type="button" style = "margin-left:200px;" id = "button" class="btn btn-primary btn-block btn-flat">Sign In</button>
            </div><!-- /.col -->
            <div class="row">
            <div class="col-xs-12">
              <div id="message" style = "left:0px;color:#f00;height:10px;">
            </div><!-- /.col -->
            </div>
          </div>
       

        

        <br>
   		
						

      </div><!-- /.login-box-body -->
    </div>
		
		
		
	</div>


	</div>
	<!-- -->
	<div class="row-fluid">
		<div class="span2">
		</div>
		<div class="footer" style="position:relative; padding-left:450px;margin-top:48px">
			<p class="text-center">
				<span>Copyright © 2014-2015 <a href="http://www.airradio.cn/">北京威果智能科技有限公司</a>.</span> <span>All rights reserved.</span>
			</p>
		</div>
	</div>
</div>

</body>
</html>


