<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML>

<html>
<head>

<!--  <meta name="viewport" content="target-densitydpi=device-dpi,initial-scale=1"/>
-->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>

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



.imgWrp{
float:none !important;
margin:0 auto !important;

}
.nameWrp{
display:none;

}
.attachWrp{
text-align:center !important;
}

.button{
background:#656565;
width: 140px;
line-height: 38px;
text-align: center;
font-weight: bold;
color: #fff;
text-shadow:1px 1px 1px #333;
border-radius: 5px;
margin:0 20px 20px 0;
position: relative;
overflow: hidden;

}

#pic img{
max-width:300px;
width:expression(document.body.clientWidth>document.getElementById("pic").scrollWidth*9/10? "300px": "auto" );
border:1px dashed #000;
}
body {      font: normal 100% Helvetica, Arial, sans-serif; }



</style>


<script>

window.onload=function(){
	
	$("#next").click(function(){
		window.location.href="http://192.168.111.46:8080/smart/hwmobile/smart/s007!getSum";
	});
	
	$("#prev").click(function(){
		history.back();
	});
	
	
	
	$.post(
			'http://192.168.111.46:8080/smart/hwmobile/smart/s007!getSolutionsByCondition?',
			{
				FILETYPE:"20",
				CO2:"20",
				PM25:"30",
				VOC:"10",
				SCENE:"20"
				
				
			}, function(data) {

				
				
				
				/* var b = JSON.stringify(data);
				alert("b"+b);
				
				*/
				var dataObj = data.split(",");
				
				dataObj = dataObj[3].split('"');
				//dataObj = dataObj[3].split('r');
				dataObj = dataObj[3];
				
				dataObj = dataObj.split("\\r\\n");
				//dataObj.replace(/[^\u4e00-\u9fa5]/gi,"");
				
				
				for(var i = 0;i<dataObj.length;i++)
				$("#chk").append(dataObj[i]+"<br/>");
				//var dataObj=eval(data);
				//alert(dataObj);
				//$("#chk").html(data.dataObject);
				//alert("yes");
				}
			);
	
	
	}
</script>
</head>
<body>
<div >
<ul >

 <li style="list-style:none; padding-left:0; font-size:40px; width:100%; border-bottom: #3c6fb1; padding-bottom:15px;color:#84ae32; font-weight:bold; ">

					空气电台提示：</li>


<li style="list-style:none;margin-bottom:40px;margin-top:30px;display:-webkit-box;
    display:-webkit-flex;
    display:-ms-flexbox;
    display:flex;
    -webkit-box-pack:justify;
    -webkit-justify-content:space-between;
    -ms-flex-pack:justify;
    justify-content:space-between;"><input type = "button" id = "prev" value = "上一步" class = "button" />
<input type = "button" id = "next" class = "button" value = "下一步" style = "margin-right:40px;"/></li>
 
<li style="list-style:none;background:#00bff3;color:#ffffff;padding-left:20px;padding-top:2px;margin-left:-15px;margin-right:15px" >
 <h2>综合结论</h2>
    <div class="clear"></div>
    <div class="line"></div>
    <div class="text">  			
   <p style="font-size:1.5em;padding-bottom:14px" id = "chk"><br></p>           
    </div>
</li>

</ul>
</div>


</body>
</html>
