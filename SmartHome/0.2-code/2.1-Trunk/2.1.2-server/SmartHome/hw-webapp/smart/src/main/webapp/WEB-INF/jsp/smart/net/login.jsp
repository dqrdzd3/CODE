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


    <link rel="icon" type="image/ico" href="<%=basePath%>home/favicon.ico"/>
 <link rel="stylesheet" href="<%=basePath%>public/css/style.css" media="screen" type="text/css" />
 <link rel="stylesheet" type="text/css" href="<%=basePath%>public/css/component.css" /> 
 <%-- <script type="text/javascript" src="<%=basePath%>public/js/jquery-1.9.1.min.js"></script>  --%>
<!-- 导入验证类库  -->
<script src="<%=basePath%>public/validate/jquery.js"></script>
<script src="<%=basePath%>public/validate/jquery.validate.js"></script>
<script src="<%=basePath%>public/validate/messages_cn.js"></script>
<script src="<%=basePath%>public/validate/additional.js"></script>
 
<style type="text/css">  

.loading{  
	
    width:100%;  
    height:100%;  
    position: absolute;  
    text-align:center
    top:0%;  
    left:0%;  
    line-height:56px;  
    color:#fff;  
    padding-top:380px;  
    font-size:15px;  
   
    background: #000;
    opacity: 0.7;  
    z-index:9999;  
    -moz-border-radius:20px;  
    -webkit-border-radius:20px;  
    border-radius:20px;  
    filter:progid:DXImageTransform.Microsoft.Alpha(opacity=70);  
}  
</style>  
<style>

#message{
color:white; 
font-size:30px;

}
#warp {
	position: absolute;
	width: 500px;
	height: 200px;
	left: 50%;
	top: 40%;
	margin-left: -250px;
	margin-top: -100px;
	border: 0px;
}
</style>
<script>
$(function(){ 
	$("#loading").hide();
	var basePath = "<%=basePath%>";
	localStorage.setItem("basePath",basePath);
	}); 
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
	    		password: {required: "请输入密码",
	    				   bytemaxlength:"密码最大长度:16"
	    					}
	    		},
	    errorPlacement: function(error, element) {
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
	$("#button").click(function(){
		
		if(!validator.form()){
			return;
		} 
	
		$.post(
	      '<%=basePath%>hwmobile/smart/u001!doLogin',
	      {
			PHONE : $("#username").val(),
			PASSWORD : $("#password").val()
	        
	      },
	      function (datat,status) //回传函数
	      {
	    	  $("#loading").show();
	    	 
	    	  	if(status=="success")
	              {
	                  
				    	var dataObj1=eval("("+datat+")");
					    var dataObj = eval("("+dataObj1.data+")");
						
				    	// alert(dataObj.ma017);
				    	 if(dataObj1.code == 1)
				    	  {
						    	  $("#pic").attr("src",dataObj.ma017);  
						    	  localStorage.setItem("USERID",dataObj.ma001);
						    	  localStorage.setItem("SESSIONID",dataObj.ma010);
						    	  localStorage.setItem("username",dataObj.ma008);
						    	  localStorage.setItem("tel",dataObj.ma006);
			    	  
						    	  window.location="<%=basePath%>home/index-2.jsp";
				
				     		}else{

				     			$("#loading").hide();

				     			$("#message").html("输入的用户名密码错误！");
				 				
	    	  				}
	              }
	    	  	else{

	    	  		$("#loading").hide();

	     			$("#message").html("网络连接失败！");
	    	  		
	    	  	}
	      }
	     
	    	  
	      
	    );

		   

	   });
});

/**
/** 
* 以下为html5代码,获取地理位置 

function getLocation(){
//检查浏览器是否支持地理位置获取 
if (navigator.geolocation) { 
//若支持地理位置获取,成功调用showPosition(),失败调用showError 
// alert("正在努力获取位置..."); 
var config = { enableHighAccuracy: true, timeout: 5000, maximumAge: 30000 }; 
navigator.geolocation.getCurrentPosition(showPosition, showError, config); 
} else { 
//alert("Geolocation is not supported by this browser."); 
alert("定位失败,用户已禁用位置获取权限"); 
} 
} 
*/ 
// getLocation();
/** 
* 获取地址位置成功 

function showPosition(position) { 
//获得经度纬度 
var x = position.coords.latitude; 
var y = position.coords.longitude; 
//配置Baidu Geocoding API 
var url = "http://api.map.baidu.com/geocoder/v2/?ak=C93b5178d7a8ebdb830b9b557abce78b" + 
"&callback=renderReverse" + 
"&location=" + x + "," + y + 
"&output=json" + 
"&pois=0"; 
$.ajax({ 
type: "GET", 
dataType: "jsonp", 
url: url, 
success: function (json) { 
if (json == null || typeof (json) == "undefined") { 
return; 
} 
if (json.status != "0") { 
return; 
} 
setAddress(json.result.addressComponent); 
}, 
error: function (XMLHttpRequest, textStatus, errorThrown) { 
alert("[x:" + x + ",y:" + y + "]地址位置获取失败,请手动选择地址"); 
} 
}); 
} */ 
/** 
* 获取地址位置失败[暂不处理] 

function showError(error) { 
switch (error.code) { 
case error.PERMISSION_DENIED: 
alert("定位失败,用户拒绝请求地理定位"); 
//x.innerHTML = "User denied the request for Geolocation.[用户拒绝请求地理定位]" 
break; 
case error.POSITION_UNAVAILABLE: 
alert("定位失败,位置信息是不可用"); 
//x.innerHTML = "Location information is unavailable.[位置信息是不可用]" 
break; 
case error.TIMEOUT: 
alert("定位失败,请求获取用户位置超时"); 
//x.innerHTML = "The request to get user location timed out.[请求获取用户位置超时]" 
break; 
case error.UNKNOWN_ERROR: 
alert("定位失败,定位系统失效"); 
//x.innerHTML = "An unknown error occurred.[未知错误]" 
break; 
} 
} */ 
/** 
* 设置地址 

function setAddress(json) { 
var position = document.getElementById("txtPosition"); 
//省 
var province = json.province; 
//市 
var city = json.city; 
//区 
var district = json.district; 
province = province.replace('市', ''); 
position.value = province + "," + city + "," + district; 
position.style.color = 'black'; 
} */ 

</script>
</head>

<body  style="overflow:hidden">
<div id="loading" class="loading" style="overflow:hidden"><center><img src="<%=basePath%>public/loading.gif" />Loading pages...</center></div>

	<div class="container demo-2">
		<div class="content">
			<div id="large-header" class="large-header">
				<canvas id="demo-canvas"></canvas>
				<div id=warp>
					<center>
						<img src="<%=basePath%>public/admin_logo.png" />

						<form id="form_login" styleId="form_login">
							<fieldset>
							
								<!-- Entypo &#128100; = User -->
								<input type="text" name="username" id="username"
									placeholder="用户名"  /> <label
									for="username" data-icon="&#128100;">Username</label>
								<!-- Entypo &#128274; = Locked -->
								<input type="password" name="password" id="password"
									placeholder="密码"  /> <label
									for="password" data-icon="&#128274;">Password</label>
								<!-- Entypo &#58542; = Right Arrow -->
								<button class="button" id="button" name = "button" type="button" data-icon="&#58542;" />
								
							</fieldset>
							
						</form>
						<div id="message">
							
									
									
						</div>

						<center>
				</div>
			</div>
		</div>

	</div>
	<!-- /container -->
	
	

</body>
<%-- <script src='<%=basePath%>public/js/jquery.js'></script>  --%>
  <script src="<%=basePath%>public/js/index.js"></script>
	<script src="<%=basePath%>public/js/demo.js"></script>
     <%--   <script src="<%=basePath%>public/js/jquery-1.10.0.min.js"></script> --%> 
</html>