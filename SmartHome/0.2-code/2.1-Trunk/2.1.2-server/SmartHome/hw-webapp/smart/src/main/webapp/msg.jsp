<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<title>消息提醒</title>
</head>
<style>
/* CSS Document */
body,html{ 
	margin:0px; 
	padding:0px; 
	font-size:12px; 
	font-family:Verdana, Geneva, sans-serif; 
	color:#565656;
	margin: 0px;
	padding:0px;
	height:100%;
	background-color:#FFF;
}

ul{ 
	margin:0px; 
	padding:0px; 
	list-style:none;
}

/*表格样式*/
.zzsajj_table{border-top:solid 1px #93c1db; border-right:solid 1px #93c1db;border-collapse: collapse;
font-size: 12px;
color: #565656;
padding: 0px;
margin: 5px;
width: 98%;  }
/*表格th样式*/
.zzsajj_table th{background: url(<%=path %>/images/th_bj.jpg) repeat-x bottom #e7fbff; height:28px; line-height:28px; font-size:12px; color: #003399; border-left:solid 1px #93c1db; border-bottom:solid 1px #93c1db;font-weight:normal; width:100px; text-align:right; padding-right:10px;}
.zzsajj_table th span{ color:#FF0000;}
/*表格td样式*/
.zzsajj_table td{height:28px; line-height:28px; font-size:12px; background:#f0faff; border-left:solid 1px #93c1db; border-bottom:solid 1px #93c1db;font-weight:normal; text-align:left;  line-height:18px; word-wrap:break-word; padding:3px 15px 3px 3px; }
.zzsajj_table td span{ color:#FF0000;}
/*表格td修改样式*/
.zzsajj_table .td_mod{height:28px; line-height:28px; font-size:12px; background:#d3eaef; border-left:solid 1px #93c1db; border-bottom:solid 1px #93c1db;font-weight:normal; text-align:left;  line-height:18px; word-wrap:break-word; padding:3px 15px 3px 3px; }

/*表格表单样式*/
.zzsajj_table input,textarea,select{
	border:#91c8e8 solid 1px;
	height:19px;font-size:12px; font-family:Verdana, Geneva, sans-serif;  
	line-height:19px; color:#666;}
.zzsajj_table input[type="button"]{border:1px solid #91c8e8; width:auto; height:auto; background:url(../images/button_bj.jpg) repeat-x;}
.zzsajj_table input[type="radio"]{border:none; width:auto; height:auto;}
.zzsajj_table input[type="text"]{border:1px solid #91c8e8; width:100%; }
.zzsajj_table textarea,select{ border:1px solid #91c8e8; width:100%;}
.zzsajj_table .date_inputdate{ width:50px;}
.zzsajj_table .td_danwei{ border-left:5px;}
/*表格title样式*/
.zzsajj_table .title{ text-align:center; font-weight:bold;}
/*表格超链接样式*/
.zzsajj_table  a{
	color:#1AAF08;
	text-decoration:underline;
}
.zzsajj_table  a:hover{ color:#FF6D25; }
/*文字上下标*/
.zzsajj_table .danweifontsb{vertical-align:super; font-size:9px; color:#565656;}
.zzsajj_table .danweifontxb{vertical-align:sub; font-size:9px; color: #003399;}

.tjwz_table{ background:#e7fbff; border:dotted 1px #6bb0c8; margin:5px; font-size:12px; color: #003399; width:auto;}
.tjwz_table td{ padding:7px;}
/*修改表格样式*/
.zzsajj_table_mod{border-top:solid 1px #93c1db; border-right:solid 1px #93c1db;border-collapse: collapse;
font-size: 12px;
color: #565656;
padding: 0px;
margin:5px; 
width: 98%; }
/*表格th样式*/
.zzsajj_table_mod .title{background: #d3eaef; height:28px; line-height:28px; font-size:12px; color: #000; border-left:solid 1px #93c1db; border-bottom:solid 1px #93c1db;font-weight:normal;  text-align:center; }
.zzsajj_table_mod th { }
/*表格td样式*/
.zzsajj_table_mod td{height:28px; line-height:28px; font-size:12px; background:#fff; border-left:solid 1px #a8c7ce; border-bottom:solid 1px #a8c7ce;font-weight:normal; text-align:left;  line-height:18px; word-wrap:break-word; padding:3px 15px 3px 3px; color:565656; }
.zzsajj_table_mod td span{ color:#12479f; font-weight:bold;}
.zzsajj_table_mod td a{color:#1AAF08;
	text-decoration:underline;}
.zzsajj_table_mod td a:hover{color:#FF6D25;}
.shanchu{ text-decoration:line-through;}

/*隐藏显示title*/
.zzsajj_table_title{border: dashed 1px #b5d6e6;border-collapse: collapse;
font-size: 14px;
color: #65abd2;
padding: 0px;
margin: 5px;
width: 98%; font-weight:bold;}

.zzsajj_table_title th{background: url(../images/mod_th_bj.jpg) repeat-x bottom ; height:24px; line-height:24px; font-size:14px; color: #387da3;  text-align:left; padding-right:10px;font-weight:bold;}

/*隐藏显示层样式*/
 .link{
	position: relative;
}
#box{
	display: none;
	
	width:300px;
	padding: 0px;
	text-align: left;
	line-height: 175%;
	text-indent: 2em;
	position: absolute;
	background:#FFFFFF;
}
.box_table{border-top:solid 1px #a8c7ce; border-right:solid 1px #a8c7ce;border-collapse: collapse;
font-size: 12px;
color: #565656;
padding: 0px;
margin: 0px;
width: 100%; }
.box_table td{border-bottom:solid 1px #a8c7ce; border-left:solid 1px #a8c7ce;height:22px; line-height:22px; padding-left:5px; background:#fff; }
.box_table th{border-bottom:solid 1px #a8c7ce; border-left:solid 1px #a8c7ce; background: url(../images/mod_th_bj.jpg)  #fff;height:25px; line-height:25px; font-size:12px; color: #387da3;  text-align:left; padding-right:10px;font-weight:bold; padding-left:5px;}
.box_table .tdtitletd{  text-align:right; padding-right:5px; background:#fff; width:100px;}
.box_table .tdtitle{  text-align:left; padding-left:5px; background:#fff; font-weight:bold; }

/*隐藏显示层menu*/
.div_mod_ml{position:absolute; top:35px; right:0;   }
.div_mod_ml image{ float:left; margin:0; padding:0;}
.div_mod_top{ width:31px; height:34px;}
.div_mod_menu{width: 80px;
border: 1px solid rgb(73, 161, 187);
background: none repeat scroll 0% 0% rgb(214, 246, 255);
display: block;
margin-left: 31px;}
.div_mod_menu ul{margin:0; padding:0;}
.menutop{height:31px; background:url(../images/menu_top.jpg) repeat-x top; color:#fff; font-size:14px; font-weight:bold; text-align:center;}
.div_mod_menu ul li{ height:26px; border-bottom:dashed 1px #588a11; line-height:26px;  display:block; text-align:center;}
.div_mod_menu ul li a{ color:#207d98;text-decoration:none;display:block;}
.div_mod_menu ul li a:hover{ background:#fff;text-decoration:none;display:block;}



/*弹出*/
.notice{border:1px solid #8dc9ef; width:380px; float:right; bottom:0; position:absolute; right:0;}
.notice .menu{background:url(<%=path %>/images/th_bj.jpg) repeat-x; height:30px; line-height:30px; border:#8dc9ef 1px solid;}
.notice .menu_title{background:url(<%=path %>/images/sc.png) no-repeat left ; padding-left:20px; margin-left:5px; font-weight:bold; font-size:14px; color:#0c7dc5;}
.notice .content{ margin:15px; border-bottom:dashed 1px #8dc9ef; float:left; width:95%;}
.name{ background:url(<%=path %>/images/use.png) no-repeat left; padding-left:20px; color:#0066CC; line-height:25px;}
.date{ color:#999999;line-height:25px; float:right}
.main{line-height:25px;}
.chakan{   clear:both; float:right; height:30px;}
.chakan ul{ padding:0; margin:0;}
.chakan ul li{ padding:0 10px; list-style:none; float:right; display:block; height:30px; line-height:30px;  }
.chakan ul li a{ color:#0066CC; text-decoration:none;}
.chakan ul li a:hover{ color: #FF9900; text-decoration:underline;}
</style>
<body>
<div  class="notice" id="notice">
	<div  class="menu">
		<div class="menu_title">河南汉威电子		</div>
	</div>

	<div id="hanwei" style="height:290px;overflow:auto;"> 

		<div class="content">
			<div class="name">刘俊
				<span class="date">2013-09-24 10:22</span>
			</div>
			<div class="main" >河南汉威电子股份有限公司欢迎你</div>
			<div  class="chakan">
				<ul>
					<li><a href="#">查看</a></li>
					<!-- <li ><a href="#">已经阅读</a></li> -->
				</ul>
			</div>
		</div>
		
		


	</div>
</div>
</body>
</html>