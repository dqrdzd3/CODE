<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML>

<html>
<head>

<meta name="viewport" content="target-densitydpi=device-dpi,initial-scale=1"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<meta content="yes" name="apple-mobile-web-app-capable">
<meta name="viewport" content="width=device-width,user-scalable=no"/> 
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta content="telephone=no" name="format-detection">


<title>空气电台改善建议</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<script src="<%=basePath%>public/validate/jquery.js"></script>
<style type="text/css">
/* h2 { background-color: #1c87cf; padding:10px; color:#ffffff} */


#value_table{
width:100%;
font-family: verdana,arial,sans-serif;
	font-size:18px;
	color:#333333;
	border-width: 2px;
	border-color: #666666;
	border-collapse: collapse;
}
#value_table th{
border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
#value_table  td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

#prev{
height:48px;
border:0px;
width: 100px;
cursor:pointer;
background:#5997D0;
text-align: center;
list-style-type:none;
color: #fff;
font-size:14pt;
padding-top:5px;
margin:0 0 0 0;
position: relative;
overflow: hidden;


  
  
  -webkit-appearance: none ;  /*解决iphone safari上的圆角问题*/

}

#next{
height:48px;
background:#5997D0;
border:0px;
width: 100px;
font-size:20px;
text-align: center;
line-height:40px;
color: #fff;
cursor:pointer;
padding-top:5px;
padding-right: 1px;
margin:0 0 0 0;
position: relative;
overflow: hidden;

 -webkit-border-radius:0px;
  -moz-border-radius:0px;
  -ms-border-radius:0px;
  -o-border-radius:0px;
  border-radius:0px;
  -webkit-appearance : none ;  /*解决iphone safari上的圆角问题*/

}


body { font: normal 100% Helvetica, Arial, sans-serif; 
		margin:0px;
		ackground-attachment: fixed;
		bgproperties ="fixed";
		scroll ="no";
		
		}



</style>


<script>
function GetQueryString(name) 
{ 
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
     var r = window.location.search.substr(1).match(reg); 
     if(r!=null)return  unescape(r[2]); return null; 
} 



var _flag = GetQueryString("FILETYPE");
var _CO2 = GetQueryString("CO2");
var _PM25 = GetQueryString("PM25");
var _VOC = GetQueryString("VOC");
var _SCENE = GetQueryString("SCENE");
//var _CO2 = ${solutionPO.ma004};
//var _PM25 = ${solutionPO.ma006};
//var _VOC = ${solutionPO.ma005};
//var _SCENE = ${solutionPO.ma008};

function setTable(){
	//pm25 table 赋值
	if(_PM25 == 10){
		$("#pm25_rule").html("PM2.5≤200");
		$("#pm25_value").html("优");
		
	}
	if(_PM25 == 20){
		$("#pm25_rule").html("201-399");
		$("#pm25_value").html("良");
		
	}
	if(_PM25 == 30){
		$("#pm25_rule").html("PM2.5≥400");
		$("#pm25_value").html("严重污染");
		
	}
	
	/* VOC table 赋值 */
	if(_VOC == 10){
		$("#VOC_rule").html("VOC≤20");
		$("#VOC_value").html("优");
		
	}
	if(_VOC == 20){
		$("#VOC_rule").html("21-39");
		$("#VOC_value").html("良");
		
	}
	if(_VOC == 30){
		$("#VOC_rule").html("VOC≥40");
		$("#VOC_value").html("严重污染");
		
	}
	
	/* co2 table 赋值 */
	
	if(_CO2 == 10){
		$("#CO2_rule").html("CO2≤800");
		$("#CO2_value").html("优");
		
	}
	if(_CO2 == 20){
		$("#CO2_rule").html("801-1119");
		$("#CO2_value").html("良");
		
	}
	if(_CO2 == 30){
		$("#CO2_rule").html("CO2≥1200");
		$("#CO2_value").html("严重污染");
		
	}
	
}

window.onload=function(){
	
	systemTime();
	
	setTable();
	
	$("#explain_N").css({cursor:"pointer",margin:"20px"});
	$("#explain_N").click(function(){
		window.open("<%=basePath%>public/html/explain.html");
	});
	
	
	
	if(_flag == 10){
		$("#prev").val("返回");
		$("#next").val("下一步");
	}
	if(_flag == 20){
		$("#prev").val("上一步");
		$("#next").val("下一步");
	}
	if(_flag == 30){
		$("#prev").val("上一步");
		$("#next").val("在线商城");
	}
	
	
	_flag = 10;
	getContent();
	
	$("#next").click(function(){
		
		var index = $("#next").val();
		if(index == "在线商城")
			window.open("http://m.airradio.cn/Mall.asp");
		if(_flag >= 30)
			_flag = 30;
		else
		_flag += 10;
		
		getContent();
	});
	
	$("#prev").click(function(){
		
		if(_flag <= 10)
			_flag = 10;
		else
		_flag -= 10;
		getContent();
	});//click 事件
	}
	
	
	function getContent(){
		if ( _flag == 10){
			$("#prev").val("返回");
			$("#next").val("下一步");
			$("#title_sol").html("检测评估");
			
			//$("#prev").html("返回");
			$("#prev").css({visibility:"hidden"});
			
			
			$.post(
					'<%=basePath%>hwmobile/smart/s007!getSolutionsByCondition?',
					{
						FILETYPE:_flag,
						CO2:_CO2,
						PM25:_PM25,
						VOC:_VOC,
						SCENE:_SCENE
						
						
					}, function(data) {

						
						//alert(data);
						
						/* var b = JSON.stringify(data);
						alert("b"+b);
						
						*/
						//var dataObj = data.split(",");
						//alert(dataObj);
						dataObj = data.split(':');
						//alert(dataObj);
						//dataObj = dataObj[3].split('r');
						dataObj = dataObj[4];
						//alert(dataObj);
						//d = daataObj.replace(/\"/g, "");
						dataObj = dataObj.replace(/\"/g,"");
						//alert(dataObj);
						dataObj=dataObj.substring(0,dataObj.length-8);
						//alert(dataObj);
						
						dataObj = dataObj.split("\\r\\n");
						
						//alert(dataObj);
						$("#chk").html("");
						$("#pro").html("");
						for(var i = 0;i<dataObj.length;i++){
						var fdStart = dataObj[i].indexOf("申明");
						if(fdStart == 0){
							$("#pro").append(dataObj[i]);
							$("#pro").append(dataObj[i+1]);
							break;
							}else if(fdStart == -1){
								if(dataObj[i])
									$("#chk").append(dataObj[i]+"<br/>");
									}
							}
						//var dataObj=eval(data);
						//alert(dataObj);
						//$("#chk").html(data.dataObject);
						//alert("yes");
						}
					);
			
				}//完成 _flag = 10
		
		if ( _flag == 20){
			$("#prev").val("上一步");
			$("#next").val("下一步");
			$("#title_sol").html("综合结论");
			$("#prev").css({visibility:"visible"});
			
			$.post(
					'<%=basePath%>hwmobile/smart/s007!getSolutionsByCondition?',
					{
						FILETYPE:_flag,
						CO2:_CO2,
						PM25:_PM25,
						VOC:_VOC,
						SCENE:_SCENE
						
						
					}, function(data) {

						
						//alert(data);
						
						/* var b = JSON.stringify(data);
						alert("b"+b);
						
						*/
						//var dataObj = data.split(",");
						//alert(dataObj);
						dataObj = data.split(':');
						//alert(dataObj);
						//dataObj = dataObj[3].split('r');
						dataObj = dataObj[4];
						//alert(dataObj);
						//d = daataObj.replace(/\"/g, "");
						dataObj = dataObj.replace(/\"/g,"");
						//alert(dataObj);
						dataObj=dataObj.substring(0,dataObj.length-8);
						//alert(dataObj);
						
						dataObj = dataObj.split("\\r\\n");
						
						//alert(dataObj);
						$("#chk").html("");
						$("#pro").html("");
						for(var i = 0;i<dataObj.length;i++){
						var fdStart = dataObj[i].indexOf("申明");
						if(fdStart == 0){
							$("#pro").append(dataObj[i]);
							$("#pro").append(dataObj[i+1]);
							break;
							}else if(fdStart == -1){
								if(dataObj[i])
									$("#chk").append(dataObj[i]+"<br/>");
									}
							}
						//var dataObj=eval(data);
						//alert(dataObj);
						//$("#chk").html(data.dataObject);
						//alert("yes");
						}
					);
			}//完成 _flag = 20
			
		if ( _flag == 30){
			
			$("#title_sol").html("解决方案");
			$("#prev").val("上一步");
			$("#next").val("在线商城");
			$("#prev").css({visibility:"visible"});
			
			$.post(
					'<%=basePath%>hwmobile/smart/s007!getSolutionsByCondition?',
					{
						FILETYPE:_flag,
						CO2:_CO2,
						PM25:_PM25,
						VOC:_VOC,
						SCENE:_SCENE
						
						
					}, function(data) {

						
						//alert(data);
						
						/* var b = JSON.stringify(data);
						alert("b"+b);
						
						*/
						//var dataObj = data.split(",");
						//alert(dataObj);
						dataObj = data.split(':');
						//alert(dataObj);
						//dataObj = dataObj[3].split('r');
						dataObj = dataObj[4];
						//alert(dataObj);
						//d = daataObj.replace(/\"/g, "");
						dataObj = dataObj.replace(/t/g, "");
						//dataObj = dataObj.replace(/\/g, "");
						//alert(dataObj);
						dataObj = dataObj.replace(/\"/g,"");
						//alert(dataObj);
						dataObj=dataObj.substring(0,dataObj.length-8);
						//alert(dataObj);
						
						dataObj = dataObj.split("\\r\\n");
						
						//alert(dataObj);
						$("#chk").html("");
						$("#pro").html("");
						for(var i = 0;i<dataObj.length;i++){
						var fdStart = dataObj[i].indexOf("申明");
						if(fdStart == 0){
							$("#pro").append(dataObj[i]+"<br/>");
							$("#pro").append(dataObj[i+1]);
							break;
							}else if(fdStart == -1){
								if(dataObj[i])
									dataObj[i] = dataObj[i].replace(/\\/g, "");
									$("#chk").append(dataObj[i]+"<br/>");
									}
							}
						
						//var dataObj=eval(data);
						//alert(dataObj);
						//$("#chk").html(data.dataObject);
						//alert("yes");
						}
					);
			
				}//完成 _flag = 30
	}
</script>
<script>
//获取系统时间，将时间以指定格式显示到页面。  
function systemTime()  
{  
    //获取系统时间。  
    var dateTime=new Date();
          var hh=dateTime.getHours();
          var mm=dateTime.getMinutes();
		  var ss=dateTime.getSeconds();
          
		  var yy=dateTime.getFullYear();
		  var mon=dateTime.getMonth()+1;  //因为1月这个方法返回为0，所以加1
		  var dd=dateTime.getDate();  
      
    //分秒时间是一位数字，在数字前补0。  
    mm = extra(mm);  
    ss = extra(ss);  
      
    //将时间显示到ID为time的位置，时间格式形如：19:18:02  
    document.getElementById("time").innerHTML=yy+"年"+mon+"月"+dd+"日 "+hh+":"+mm+":"+ss;  
      
    //每隔1000ms执行方法systemTime()。  
    //setTimeout("systemTime()",1000);  
}  
  
//补位函数。  
function extra(x)  
{  
    //如果传入数字小于10，数字前补一位0。  
    if(x < 10)  
    {  
        return "0" + x;  
    }  
    else  
    {  
        return x;  
    }  
}  
</script>
</head>
<body >

<div style = "margin:0px;padding:0px;height: auto">
<ul style = "margin:0px;padding:0px;height: auto">

<li style="list-style:none;background:#5997D0;margin-bottom:0px;margin-top:0px;height: 100%;
font-size: 20px;
line-height: 56px;margin-right:0px;padding-right:0px">
<center>
<input readonly="readonly" type = "button" id = "prev" style = "float:left;visibility:hidden" value = "返回"  />
   
<lable id = "title_sol" style = "font-size:20pt;text-align: center;font-weight: bold;float:inherit;color:#fff;">检测评估</lable>
    
<input readonly="readonly"  type = "button" id = "next"  value = "下一步" style = "float:right"/>
</center>
</li>
 <div id="time" style = "margin-top:10px;margin-left:10px">  
    </div>   
<hr/>
<div style = "margin:20px;">
<img src="<%=basePath%>public/solutionLogo.jpg" /><br/>

</div>

<li style="list-style:none;width:100%;">

<table id = "value_table">
	<thead>
		<tr>
			<th>检测项目</th>
			<th>数值</th>
			<th>质量度</th>
			
		</tr>
	</thead>
	<tbody>
			<tr>
				<td>PM2.5</td>
				<td id = "pm25_rule"></td>
				<td id = "pm25_value"></td>
			</tr>
			<tr>
				<td>VOC</td>
				<td id = "VOC_rule"></td>
				<td id = "VOC_value"></td>
			</tr>
			<tr>
				<td>CO2</td>
				<td id = "CO2_rule"></td>
				<td id = "CO2_value"></td>
			</tr>
	</tbody>
</table>

</li>


<li style="list-style:none;padding-left:20px;padding-top:2px;margin-left:0px;margin-right:15px" >
 
    <div class="clear"></div>
    <div class="line"></div>
    <div class="text">  			
   <p style="font-size:1.5em;padding-bottom:14px" id = "chk"><br></p>
   <p style="font-size:1.0em;padding-bottom:14px" id = "pro"><br></p>            
    </div>
</li>
<li style="list-style:none;">
		<div style = "float:right;">
				<a id = "explain_N" ><font style = "color:#281BFE">附：名词解释</font></a>
		</div>
</li>
<%-- <div style = "margin:20px;">
<img src="<%=basePath%>public/100.png" style = "width:100%"/><br/>
<img src="<%=basePath%>public/120-120.png" style = "width:100%"/><br/>
<img src="<%=basePath%>public/200x200_3.jpg" style = "width:100%"/><br/>
<img src="<%=basePath%>public/th.jpg" style = "width:100%"/><br/>
<img src="<%=basePath%>public/weidian.png" style = "width:100%"/><br/>
<img src="<%=basePath%>public/36.jpg" style = "width:100%;"/><br/> 
</div> --%>
</ul>
</div>


</body>
</html>
