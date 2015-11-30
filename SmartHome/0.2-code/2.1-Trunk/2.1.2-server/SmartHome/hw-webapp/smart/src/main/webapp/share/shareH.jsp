<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	String names = request.getParameter("name");
	String d1 = request.getParameter("d1");
	String d2 = request.getParameter("d2");
	String d3 = request.getParameter("d3");
	String d4 = request.getParameter("d4");
	String d5 = request.getParameter("d5");
	String d6 = request.getParameter("d6");
	String d7 = request.getParameter("d7");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1">
<title>空气电台</title>

<script type=text/javascript src="Chart.min.js"></script>
<script type=text/javascript src="DateUtil.js"></script>
<STYLE type=text/css>
BODY {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px;

 

}

DIV {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

DL {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

DT {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

DD {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

UL {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

OL {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

LI {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

H1 {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

H2 {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

H3 {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

H4 {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

H5 {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

H6 {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

PRE {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

CODE {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

FORM {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

FIELDSET {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

LEGEND {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

INPUT {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

TEXTAREA {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

P {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

BLOCKQUOTE {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

TH {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

TD {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "微软雅黑";
	PADDING-TOP: 0px
}

BODY {
	MIN-WIDTH: 400px
}

HTML {
	MIN-WIDTH: 400px
}

.tc {
	TEXT-ALIGN: center
}

P {
	FONT-SIZE: 14px
}

.content {
	MARGIN: 0px auto;
	WIDTH: 400px;
	HEIGHT:711px;
	OVERFLOW: hidden;
		background-image: url('bg1.png'); 
background-repeat: no-repeat; 
}

A {
	BORDER-BOTTOM: medium none;
	BORDER-LEFT: medium none;
	OUTLINE-STYLE: none;
	OUTLINE-COLOR: invert;
	OUTLINE-WIDTH: medium;
	BORDER-TOP: medium none;
	BORDER-RIGHT: medium none;
	TEXT-DECORATION: none
}

.blank10 {
	HEIGHT: 10px
}

.blank20 {
	HEIGHT: 20px
}

.title {
	MARGIN-TOP: 20px;
	COLOR: #717171;
	FONT-SIZE: 22px
}

.value {
	WHITE-SPACE: nowrap;
	COLOR: #71d939;
	FONT-SIZE: 30px
}

.bfState {
	PADDING-BOTTOM: 4px;
	BACKGROUND-COLOR: #71d939;
	PADDING-LEFT: 4px;
	PADDING-RIGHT: 4px;
	COLOR: #fff;
	FONT-SIZE: 14px;
	PADDING-TOP: 4px;
	border-radius: 10px
}

.waterState {
	PADDING-BOTTOM: 4px;
	BACKGROUND-COLOR: #71d939;
	PADDING-LEFT: 4px;
	PADDING-RIGHT: 4px;
	COLOR: #fff;
	FONT-SIZE: 14px;
	PADDING-TOP: 4px;
	border-radius: 10px
}

.muscleState {
	PADDING-BOTTOM: 4px;
	BACKGROUND-COLOR: #71d939;
	PADDING-LEFT: 4px;
	PADDING-RIGHT: 4px;
	COLOR: #fff;
	FONT-SIZE: 14px;
	PADDING-TOP: 4px;
	border-radius: 10px
}

.boneState {
	PADDING-BOTTOM: 4px;
	BACKGROUND-COLOR: #71d939;
	PADDING-LEFT: 4px;
	PADDING-RIGHT: 4px;
	COLOR: #fff;
	FONT-SIZE: 14px;
	PADDING-TOP: 4px;
	border-radius: 10px
}

.sfatState {
	PADDING-BOTTOM: 4px;
	BACKGROUND-COLOR: #71d939;
	PADDING-LEFT: 4px;
	PADDING-RIGHT: 4px;
	COLOR: #fff;
	FONT-SIZE: 14px;
	PADDING-TOP: 4px;
	border-radius: 10px
}

.infatState {
	PADDING-BOTTOM: 4px;
	BACKGROUND-COLOR: #71d939;
	PADDING-LEFT: 4px;
	PADDING-RIGHT: 4px;
	COLOR: #fff;
	FONT-SIZE: 14px;
	PADDING-TOP: 4px;
	border-radius: 10px
}

.bmrState {
	PADDING-BOTTOM: 4px;
	BACKGROUND-COLOR: #71d939;
	PADDING-LEFT: 4px;
	PADDING-RIGHT: 4px;
	COLOR: #fff;
	FONT-SIZE: 14px;
	PADDING-TOP: 4px;
	border-radius: 10px
}

.bodyAgeState {
	PADDING-BOTTOM: 4px;
	BACKGROUND-COLOR: #71d939;
	PADDING-LEFT: 4px;
	PADDING-RIGHT: 4px;
	COLOR: #fff;
	FONT-SIZE: 14px;
	PADDING-TOP: 4px;
	border-radius: 10px
}

.value {
	WHITE-SPACE: nowrap;
	COLOR: #71d939;
	FONT-SIZE: 30px
}

.bf {
	WHITE-SPACE: nowrap;
	COLOR: #71d939;
	FONT-SIZE: 30px
}

.water {
	WHITE-SPACE: nowrap;
	COLOR: #71d939;
	FONT-SIZE: 30px
}

.muscle {
	WHITE-SPACE: nowrap;
	COLOR: #71d939;
	FONT-SIZE: 30px
}

.bone {
	WHITE-SPACE: nowrap;
	COLOR: #71d939;
	FONT-SIZE: 30px
}

.sfat {
	WHITE-SPACE: nowrap;
	COLOR: #71d939;
	FONT-SIZE: 30px
}

.infat {
	WHITE-SPACE: nowrap;
	COLOR: #71d939;
	FONT-SIZE: 30px
}

.bmr {
	WHITE-SPACE: nowrap;
	COLOR: #71d939;
	FONT-SIZE: 30px
}

.bodyAge {
	WHITE-SPACE: nowrap;
	COLOR: #71d939;
	FONT-SIZE: 30px
}

.unit {
	COLOR: #807f7f;
	FONT-SIZE: 16px
}
</STYLE>
</head>
<body>
	<DIV class="blank20"></DIV>
	<DIV style="color: #707070" class="content">
		<!--  -->
		<DIV style="MARGIN: 0px auto; WIDTH: 400px">
			<SPAN style="FONT-SIZE: 14px">数据来自 <FONT style="COLOR: #88c539">空气电台</FONT>
			</SPAN><SPAN style="FLOAT: right; COLOR: #959595"><IMG
				style="WIDTH: 12px" alt="" src="clock.png"> <SPAN
				id=recordDate>7-25</SPAN>&nbsp; <SPAN id=recordTime>13:40</SPAN> </SPAN>
		</DIV>
		<label id="name"></label>
	<div align="center" id = "2ddiv">
	<DIV class="blank20"></DIV>
		<canvas id="canvas"></canvas>
	</div>
	<script>
	   
		document.getElementById('2ddiv').style.webkitTransform = 'translate() translateZ()';
	
		var values = new Array(7);
		var width = "350";
		var height = "160";

		initDatas();
	

		
		
		function initDatas(){
			
	        document.getElementById("name").innerText = "<%=names%>";
			values[0] = initValue("<%=d1%>");
			values[1] = initValue("<%=d2%>");
			values[2] = initValue("<%=d3%>");
			values[3] = initValue("<%=d4%>");
			values[4] = initValue("<%=d5%>");
			values[5] = initValue("<%=d6%>");
			values[6] = initValue("<%=d7%>");
			
			
						
		} 
		

		function getShareContent(){
					var datas = window.action.initDatas();
			// document.write("datas "+datas);
	        var json = eval("("+datas+")");
			     	var weeks = getLastWeek();
	     	var shareContent=json.name+"过去7天当日最高值分别是：\n";
			for (var i=0;i<7;i++)
			{
				shareContent = shareContent +weeks[i]+'['+values[i]+json.unit+']';
				if(i<6){
					shareContent = shareContent+',';
				}
			}
			window.share.historyShare(shareContent);
		}
		function initValue(value){
			return chkIsNull(value)? 0 : new Number(value);
		}
		/* 判断是否为空 */
		function chkIsNull(exp){
			if (typeof(exp) != "undefined") 
			{
				return false;
			}
			return true;
		}
		
	
		var barChartData = {
				labels : getLastWeek(),
				datasets : [
					{
						fillColor : "rgba(151,187,205,0.5)",
						strokeColor : "rgba(151,187,205,1)",
						pointColor : "rgba(151,187,205,1)",
						pointStrokeColor : "#fff",
						data : values
					}
				]
				
			}
		var canvas = document.getElementById("canvas");
		//document.write("width "+width);
		//document.write("<br>height "+height);
		var w = chkIsNull(width)? 0 : new Number(width);
		var h = chkIsNull(height)? 0 : new Number(height);
		canvas.width = w;
		canvas.height = h;
		
		var ctx = canvas.getContext("2d");
		ctx.clearRect(0, 0, w, h); 
		var myLine = new Chart(ctx).Line(barChartData);
	</script>
		
		
		
		
	<div class="blank10"></div>
	<!--  
	<div style="width: 300px;margin: 0 auto;">
		<div style="">
			<a style="display:inline-block; width:100%;" href="http://wd.koudai.com/s/194819429?wfr=qfriend">
				<span style="font-size:14px;float:left;color:#000000;">
				<span style="color:red;text-decoration:underline;">开云健康测量套装及VIP服务特惠抢购中</span> 
				</span>  </a>
		</div>
	</div>
	-->
</body>


</html>