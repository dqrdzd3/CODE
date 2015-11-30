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
<s:action name="include(mainCSS,commonJS,jqueryJS,bootstrap)"
	executeResult="true" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, minimum-scale=1, maximum-scale=1">
	
	<meta name="keywords" content="">
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<link href="<%=basePath%>public/css/base.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>public/css/login.css" rel="stylesheet" type="text/css">
	
 <script src = "<%=basePath%>public/validate/additional.js"></script>
<script src = "<%=basePath%>public/validate/jquery.metadata.js"></script>
<script src = "<%=basePath%>public/validate/jquery.validata.js"></script>
<script src = "<%=basePath%>public/validate/messages_cn.js"></script>
<title>AIR RADIO空气电台</title>



</head>

<script type="text/javascript">

var validator = $("#form_login").validate({
    rules: {
    		username: {required: true},
    		password: {required: true,bytemaxlength:32}
    },
    		
    messages: {
    		username:{required: "请输入用户名！"},
    		password: {required: "请输入密码"}
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


function TestUser(obj){

  	if(obj.value.length>=16){

  		alert('请输入16以内的字符');

  		return false;

  	}

  }

  function TestPwd(obj){

  	if(obj.value.length>=16){

  		alert('请输入16位以内的密码');

  		return false;

  	}

  }

	$(function() {

		getLocation();
	});

	$().ready(function() {
		 $("#signupForm").validate({
		        rules: {
		   username: "required",
		   password: {
		    required: true,
		    minlength: 5
		   }
		 },
		        messages: {
		   username: "请输入姓名",
		  
		   password: {
		    required: "请输入密码",
		    minlength: jQuery.format("密码不能小于{0}个字 符")
		   },
		  
		  }
		    });
		});
	
	
var userid;


$(function(){  
	
	  $("#form_login").validate({
		    rules:
		    {
		      username: "required",
		      password:
		          {
		            required: true,
		            minlength: 5
		          }
		    },
		    messages:
		    {
		      username: "请输入姓名",
		      password:
		      {
		          required: "请输入密码",
		          minlength: jQuery.format("密码不能小于{0}个字符")
		        },
		    }
		  
		});
	
	

   $("#button").click(function(){
	   
	   
	$.post(
      'http://192.168.111.186:8080/smart/hwmobile/smart/u001!doLogin',
      {
		PHONE : $("#username").val(),
		PASSWORD : $("#password").val()
        
      },
      function (data,status) //回传函数
      {
    	  	if(status=="success")
              {
          
    	var dataObj=eval("("+data.data+")");
		//alert("我的json："+dataObj);
		
    	 //alert(dataObj.ma017);
			    	 if(data.code == 1)
			    	  {
			    	  $("#pic").attr("src",dataObj.ma017);  
			    	  localStorage.setItem("USERID",dataObj.ma001);
			    	  localStorage.setItem("SESSIONID",dataObj.ma010);
			    	  localStorage.setItem("username",dataObj.ma008);
			    	  localStorage.setItem("tel",dataObj.ma006);
			    	  window.location="http://192.168.111.186:8080/smart/home/index-2.html";
			
			     		}else{
			 				alert("输入的用户名密码错误！"); 
    	  				}
              }
    	  	else{
    	  		alert("网络连接失败！");
    	  	}
      }
     
    	  
      
    );

	   

   });
   
});  



/** 
* 以下为html5代码,获取地理位置 
*/ 
function getLocation() { 
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
/** 
* 获取地址位置成功 
*/ 
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
} 
/** 
* 获取地址位置失败[暂不处理] 
*/ 
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
} 
/** 
* 设置地址 
*/ 
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
} 
</script>

<body>


<div class="login">
<form action="" method="post" id="form_login">
	<div class="logo"></div>
    <div class="login_form">
    	<div class="user">
        	<input class="text_value" value="" id = "username" name="username" validate="{required:true}" ptype="text" id="username" placeholder="用户名"   />
            <input class="text_value" value="" id = "password" name="password" class="{required:true,minlength:5}" type="password" id="password" placeholder="密码"   />
        </div>
        <button class="button" id="button" type="button">登录</button>
    </div>
    
    <div id="tip"></div>

    </form>
    <div id="message"><html:messages></html:messages></div>
</div>

<script>var basedir='public/ui/';</script>
<script src="public/ui/do.js"></script>
<script src="public/ui/config.js"></script>

<div style="display:none"></div>
</body>

</html>