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





<link rel="stylesheet" href="<%=basePath%>public/css/style.css" media="screen" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>public/css/component.css" />
<script type="text/javascript" src="<%=basePath%>public/js/jquery-1.9.1.min.js"></script>
		
		
		
		
	
<title>AIR RADIO空气电台</title>

<style>  
#warp {   
  position: absolute;   
  width:500px;   
  height:200px;   
  left:50%;   
  top:40%;   
  margin-left:-250px;   
  margin-top:-100px;   
  border: 0px;   
}   
</style> 
</head>

<script type="text/javascript">


function addCookie(name,value,days,path){   /**添加设置cookie**/  
    var name = escape(name);  
    var value = escape(value);  
    var expires = new Date();  
    expires.setTime(expires.getTime() + days * 3600000 * 24);  
    //path=/，表示cookie能在整个网站下使用，path=/temp，表示cookie只能在temp目录下使用  
    path = path == "" ? "" : ";path=" + path;  
    //GMT(Greenwich Mean Time)是格林尼治平时，现在的标准时间，协调世界时是UTC  
    //参数days只能是数字型  
    var _expires = (typeof days) == "string" ? "" : ";expires=" + expires.toUTCString();  
    document.cookie = name + "=" + value + _expires + path;  
}  


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

var userid

$(function(){  
   $("#button").click(function(){
	   alert("dd");
	$.post(
      'http://192.168.111.186:8080/smart/hwmobile/smart/u001!doLogin',
      {
		PHONE : $("#username").val(),
		PASSWORD : $("#password").val()
        
      },
      function (data) //回传函数
      {
  
    	
    	var dataObj=eval("("+data.data+")");
		//alert("我的json："+dataObj);
		
    	 //alert(dataObj.ma017);
    	 
    	  
    	  
    	  
    	  $("#pic").attr("src",dataObj.ma017);  

       //双击查看原图ar myjson='';
       // eval_r('myjson=' + data + ';');
      
       
    	  localStorage.setItem("USERID",dataObj.ma001);
    	  localStorage.setItem("SESSIONID",dataObj.ma010);
    	  localStorage.setItem("username",dataObj.ma008);
    	  localStorage.setItem("tel",dataObj.ma006);
    	  addCookie("userName",$("#username").val(),7,"/");  
          addCookie("userPass",$("#password").val(),7,"/");  
    	  window.location="http://192.168.111.186:8080/smart/home/index-2.html";

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
<div class="container demo-2">
			<div class="content">
                <div id="large-header" class="large-header">
                    <canvas id="demo-canvas"></canvas>
                    	 <div id=warp>
<center><img src="<%=basePath%>public/admin_logo.png" />

 <form>
    <fieldset>
      <!-- Entypo &#128100; = User -->
      <input type="text" name="username" id="username" placeholder="用户名"   onblur="TestUser(this)" />
      <label for="username" data-icon="&#128100;">Username</label>
      <!-- Entypo &#128274; = Locked -->
      <input type="password" name="password" id="password" placeholder="密码"   onblur="TestPwd(this)" />
      <label for="password" data-icon="&#128274;">Password</label>
      <!-- Entypo &#58542; = Right Arrow -->
      <button value="Submit" id = "button" data-icon="&#58542;" />
    </fieldset>
  </form>

<center>

</div>  
				</div>
                </div>
                
            </div>
		</div><!-- /container -->


			





 


  <script src='<%=basePath%>public/js/jquery.js'></script>

 

	<script src="<%=basePath%>public/js/demo-2.js"></script>
       <script src="<%=basePath%>public/js/jquery-1.10.0.min.js"></script>

</body>

</html>