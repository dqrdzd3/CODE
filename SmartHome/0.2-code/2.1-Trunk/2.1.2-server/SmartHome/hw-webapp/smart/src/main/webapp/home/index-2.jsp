<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<head>        
   <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
     <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />    

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />        
    <![endif]-->                
    <title>Air Radio - 空气电台</title>
    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="css/stylesheets.css" rel="stylesheet" type="text/css" />
    
    <!-- tab页面的css -->
    <link href="css/lanrenzhijia.css" type="text/css" rel="stylesheet" />
    <!-- 模态 -->
    <link href="css/style_modal.css" type="text/css" rel="stylesheet" />
    
    <!--[if lte IE 7]>
        <link href="css/ie.css" rel="stylesheet" type="text/css" />
        <script type='text/javascript' src='js/plugins/other/lte-ie7.js'></script>
    <![endif]-->    
    <script type='text/javascript' src='js/plugins/jquery/jquery-1.9.1.min.js'></script>
    <script type='text/javascript' src='js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='js/plugins/jquery/jquery-migrate-1.1.1.min.js'></script>
    <script type='text/javascript' src='js/plugins/jquery/globalize.js'></script>
    <script type='text/javascript' src='js/plugins/other/excanvas.js'></script>
    
    <script type='text/javascript' src='js/plugins/other/jquery.mousewheel.min.js'></script>
        
    <script type='text/javascript' src='js/plugins/bootstrap/bootstrap.min.js'></script>
    
    <script type='text/javascript' src='js/plugins/cookies/jquery.cookies.2.2.0.min.js'></script>    
    
    <script type='text/javascript' src='js/plugins/jflot/jquery.flot.js'></script>    
    <script type='text/javascript' src='js/plugins/jflot/jquery.flot.stack.js'></script>    
    <script type='text/javascript' src='js/plugins/jflot/jquery.flot.pie.js'></script>
    <script type='text/javascript' src='js/plugins/jflot/jquery.flot.resize.js'></script>
    
    <script type='text/javascript' src='js/plugins/sparklines/jquery.sparkline.min.js'></script>        
    
    <script type='text/javascript' src='js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    
    <script type='text/javascript' src="js/plugins/uniform/jquery.uniform.min.js"></script>

<script type='text/javascript' src='js/plugins/shbrush/XRegExp.js'></script>
<script type='text/javascript' src='js/plugins/shbrush/shCore.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushXml.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushJScript.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushCss.js'></script>    
    
    <script type='text/javascript' src='js/plugins.js'></script>
    <script type='text/javascript' src='js/charts.js'></script>
    
    <script type='text/javascript' src='js/actions.js'></script>



<link href="jqplot/jquery.jqplot.min.css" rel="stylesheet" />



<script type="text/javascript" src="js/jqplot.js"></script>
<script src="js/jquery.jqplot.min.js"></script>

<script src="js/jqplot.highlighter.min.js"></script>
<script src="js/jqplot.cursor.min.js"></script>
<script src="js/jqplot.dateAxisRenderer.min.js"></script>
<script src="js/jqplot.highlighter.js"></script>
<script src="js/jqplot.cursor.js"></script>
<script src="js/jqplot.dateAxisRenderer.js"></script>


<script type="text/javascript" src="js/lazyload.js"></script>
<script type="text/javascript" src="js/lanrenzhijia.js"></script>
<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>

<script type="text/javascript" src="js/index_main.js"></script>


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
    margin-top:0px;
    margin-right:0px;
    font-size:15px;  
   	
    background: #000;
    opacity: 0.5;  
    z-index:9999;  
    -moz-border-radius:20px;  
    -webkit-border-radius:20px;  
    border-radius:20px;  
    filter:progid:DXImageTransform.Microsoft.Alpha(opacity=50);  
} 
#loading_nav {
	width:219px;  
    height:100%;  
    position: absolute;  
    text-align:center
    top:0%;  
    left:0%;  
    line-height:56px;  
    color:#fff;  
    margin-top:0px;
    margin-right:0px;
    font-size:15px;  
   	
    background: #000;
    opacity: 0.8;  
    z-index:9999;  
    -moz-border-radius:20px;  
    -webkit-border-radius:20px;  
    border-radius:20px;  
    filter:progid:DXImageTransform.Microsoft.Alpha(opacity=80);  
}
</style>  


<script>
	window.onload = function (){ 
		
		$("#loading").hide();

} 
</script>


</head>
<body>    

<!-- <a id="keleyivisitorip" class="txt" id="txt" style="display:none"></a>
	<script type="text/javascript"
		src="http://tool.keleyi.com/ip/visitoriphost/"></script> -->
		<script src = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js"></script>

    <div id="loader" ><img src="img/loader.gif"/></div>
    <div class="wrapper">
        
        <div class="sidebar">
            
            <div class="top">
                <a  class="logo"></a>
                <div class="search">
                	<img src = "img/about_logo_text.png"/>
                	
                    <!--<div class="input-prepend">
                        <span class="add-on orange"><span class="icon-search icon-white"></span></span>
                        <input type="text" placeholder="search..."/>                                                      
                    </div>   -->         
                </div>   
            </div>
            <div class="nContainer">     
            <div id="loading_nav" class="loading" style="overflow:hidden"><center><img src="<%=basePath%>public/loading.gif" />Loading...</center></div>           
                <ul class="navigation" id="navigation11">         
              <!--       <li class="active"><a href="index-2.html" class="blblue"></a></li> -->
                    <li class = 'active'>
                        <a href="#" class="bldblue">未分配</a>
                        <div class="open"></div>
                        <ul id="devices_nop">
                            <li><a href="ui.html"></a></li>
                            <li><a href="widgets.html"></a></li>
                        </ul>
                    </li>
      <!--               <li>
                        <a href="#" class="blgreen">R1设备</a>
                        <div class="open"></div>
                        <ul id="r1_devices">
                            <li><a href="forms.html">Form Elements</a></li>
                            <li><a href="validation.html">Validation</a></li>
                            <li><a href="grid.html">Grid</a></li>
                            <li><a href="editor.html">Editors</a></li>  
                            <li><a href="wizard.html">Wizard</a></li>
                        </ul>
                    </li>
               
                    <li>
                        <a href="#" class="bldyellow">控制设备</a>
                        <div class="open"></div>
                        <ul id="ctr_devices">
                            <li><a href="tables.html">Simple</a></li>
                            <li><a href="tables_dynamic.html">Dynamic</a></li>                    
                        </ul>
                    </li> -->

                  
                  
                   <!--  <li>
                        <a href="#" class="blpurple"></a>
                        <div class="open"></div>
                        <ul>
                            <li><a href="faq.html">FAQ</a></li>
                            <li><a href="login.html">Login</a></li>
                        </ul>                    
                    </li>
                    <li>
                        <a href="#" class="blorange"></a>
                        <div class="open"></div>
                        <ul>
                            <li><a href="files.html">File handling</a></li>
                            <li><a href="images.html">Images</a></li>
                            <li><a href="typography.html">Typography</a></li>
                            <li><a href="404.html">Error 404</a></li>
                        </ul>
                    </li> -->
                </ul>
                <a class="close">
                    <span class="ico-remove"></span>
                </a>
            </div>
            <div class="widget">
                <div class="datepicker"></div>
            </div>
            
        </div>
        
        <div class="body">
            
            <ul class="navigation">
                <li>
                    <a href="#" class="button">
                        <div class="icon">
                            <span class="ico-monitor"></span>
                        </div>                    
                        <div class="name">主页</div>
                    </a>                
                </li>
                <!-- <li>
                    <a href="#" class="button yellow">
                        <div class="arrow"></div>
                        <div class="icon">
                            <span class="ico-cog-2"></span>
                        </div>                    
                        <div class="name">咨询问题</div>
                    </a>          
                    <ul class="sub">
                        <li><a href="ui.html">UI Elements</a></li>
                        <li><a href="widgets.html">Widgets</a></li>
                        <li><a href="buttons.html">Buttons</a></li>
                        <li><a href="icons.html">Icons</a></li>
                        <li><a href="grid_system.html">Grid System</a></li>
                    </ul>
                </li>                
                <li>
                    <a href="#" class="button green">
                        <div class="arrow"></div>
                        <div class="icon">
                            <span class="ico-pen-2"></span>
                        </div>                    
                        <div class="name">DIY课堂</div>
                    </a>                
                    <ul class="sub">
                        <li><a href="forms.html">Elements</a></li>
                        <li><a href="validation.html">Validation</a></li>
                        <li><a href="grid.html">Grid</a></li>
                        <li><a href="editor.html">Editors</a></li>
                        <li><a href="wizard.html">Wizard</a></li>
                    </ul>                    
                </li>                        
                <li>
                    <a href="statistic.html" class="button red">
                        <div class="icon">
                            <span class="ico-chart-4"></span>
                        </div>                    
                        <div class="name">免费检测</div>
                    </a>                
                </li>  -->               
                <li>
                    <a href="#" class="button dblue">
                        <div class="arrow"></div>
                        <div class="icon">
                            <span class="ico-layout-7"></span>
                        </div>                    
                        <div class="name">在线商城</div>
                    </a> 
                    <ul class="sub">
                        <li><a href="#loginmodal1"  id="modaltrigger1">微店</a></li>
                        <li><a href="http://shop124651414.taobao.com/" target="_blank">淘宝店</a></li>
                         <li><a href="#loginmodal2"  id="modaltrigger2">服务号</a></li>
                    </ul>                                        
                </li>
                <li>
                    <a href="index-bigScreen.jsp" class="button purple">
                        <div class="arrow"></div>
                        <div class="icon">
                            <span class="ico-box"></span>
                        </div>                    
                        <div class="name">大屏</div>
                    </a>                
                   <!--  <ul class="sub">
                        <li><a href="faq.html">FAQ</a></li>
                        <li><a href="login.html">Login</a></li>
                    </ul>      -->                                   
                </li> 
                <li>
                    <a href="http://www.airradio.cn/bbs/forum.php" class="button orange" target="_blank">
                        <div class="arrow"></div>
                        <div class="icon">
                            <span class="ico-cloud"></span>
                        </div>                    
                        <div class="name">讨论区</div>
                    </a>                
                    <!-- <ul class="sub">
                        <li><a href="files.html">File handling</a></li>
                        <li><a href="images.html">Images</a></li>
                        <li><a href="typography.html">Typography</a></li>
                        <li><a href="404.html">Error 404</a></li>
                    </ul>     -->                                    
                </li> 
				 <li>
                    <a href="<%=basePath%>hwmobile/smart/help" class="button purple" target="_blank">
                        <div class="arrow"></div>
                        <div class="icon">
                            <span class="ico-cloud"></span>
                        </div>                    
                        <div class="name">帮助区</div>
                    </a>                
                    <ul class="sub">
                        <li><a href="files.html">File handling</a></li>
                        <li><a href="images.html">Images</a></li>
                        <li><a href="typography.html">Typography</a></li>
                        <li><a href="404.html">Error 404</a></li>
                    </ul>                                        
                </li>           
                <li>
                    <div class="user">
                        <img src="img/examples/users/dmitry_m.jpg" align="left" id="userpic" width="50"/>
                        <a href="#" class="name">
                            <span id="username">Dmitry Ivaniuk</span>
                          
                           
                        </a>
                    </div>
                    <div class="buttons">
                        <div class="sbutton green navButton">
                            <a href="#"><span class="ico-align-justify"></span></a>
                        </div>
                        <div class="sbutton blue">
                            <a href="#"><span class="ico-cogs"></span></a>
                            <div class="popup">
                                <div class="arrow"></div>
                                <div class="row-fluid">
                                    <div class="row-form">
                                        <div class="span12"><strong>个人信息</strong></div>
                                    </div>                                    
                                    <div class="row-form">
                                        <div class="span4">  <span class="sm" id = "tel">Administrator</span></div>
         
                                    </div>                                    
                                    <div class="row-form">
                                        <div class="span4"><input type="button" id="quit" value="退出" /></div>
                                       
                                    </div>                                    
                                </div>
                            </div>
                        </div>                        
                    </div>
                </li>                
            </ul>
            
            
            <div class="content">
                
                <div class="page-header">
                   <!--  <div class="icon">
                        <span class="ico-arrow-right"></span>
                    </div> -->
                    <h2>数据平台 <small></small></h2>
                </div>
                
                <div class="row-fluid">
                    <div class="span12">
                        <div class="widgets">
                            <div class="widget blue value">
                                <div class="left" id = "air_quilty"></div>
                                <div class="right">
                                
                                <table cellpadding="0" cellspacing="0" width="100%">
									
                                        <tr>
                                            <td><font size="4">温度：</font></td><td><font size="4"><span id="airradio_temp"></span></font></td>
                                        </tr>
                                        <tr>
                                            <td><font size="4">湿度：</font></td><td><font size="4"><span id="airradio_hum"></span></font></td>
                                        </tr>
                                        <tr>
                                            <td><font size="4">PM2.5：</font></td><td><font size="4"><span id="airradio_pm25"></span></font></td>
                                        </tr>
                                        <tr>
                                            <td><font size="4">CO2：</font></td><td><font size="4"><span id="airradio_co2"></span></font></td>
                                        </tr>
                                        <tr>
                                            <td><font size="4">VOC：</font></td><td><font size="4"><span id="airradio_voc"></span></font></td>
                                        </tr>
                                       
                                    </table>
  
                                </div>
                                <div class="bottom">
                                    <a href="#"><span id = "real_air_name"></span></a>
                                </div>
                            </div>
                            <div class="widget green icon">
                                <div class="left">
                                    <div class="icon">
                                        <span class="ico-download"></span>
                                    </div>
                                </div>
                                <div class="right">
                                    <table cellpadding="0" cellspacing="0" width="100%">
									
                                        <tr>
                                            <td><font size="5">  天然气：</font></td><td><font size="5"><span id="airradio_ch4"></span></font></td>
                                        </tr>
                                        <tr>
                                            <td><font size="5">一氧化碳：</font></td><td><font size="5"><span id="airradio_co"></span></font></td>
                                        </tr>
                                       
                                    </table>
                                </div>
                                <div class="bottom">
                                    <a href="#"><span id = "real_gas_name"></span></a>
                                </div>                            
                            </div>
                            <div class="widget orange chart nmr">
                                <div class="left">                                    
                                    <span class="mChartBar" sparkWidth="90" sparkHeight="90" sparkType="bar" sparkBarColor="#FFFFFF" sparkBarWidth="10">
                                    <font size="20"> <span id="city"></span></font></span>
                                </div>
                                <div class="right">
                                <table cellpadding="0" cellspacing="0" width="100%">
									
                                        <tr>
                                            <td><font size="3">温度：</font></td><td><font size="3"><span id="outside_temp"></span></font></td>
                                        </tr>
                                        <!-- <tr>
                                            <td><font size="3">湿度：</font></td><td><font size="3"><span id="outside_hum"></span></font></td>
                                        </tr> -->
                                        <tr>
                                            <td><font size="3">PM2.5：</font></td><td><font size="3"><span id="outside_pm25"></span></font></td>
                                        </tr>
                                        <tr>
                                            <td><font size="3">空气状况：</font></td><td><font size="3"><span id="air_quality"></span></font></td>
                                        </tr>
                                       
                                    </table>
								
                                </div>
                                <div class="bottom">
                                    <a href="#">中央气象</a>
                                </div>                            
                            </div>                        
                        </div>
                    </div>
                </div>
                
                <div class="row-fluid">
                    
                    <div class="span7">
                        
                        <div class="block">
                        <div id="loading_history" class="loading" style="overflow:hidden"><center><img src="<%=basePath%>public/loading.gif" />Loading...</center></div>
                            <div class="head">
                                <!-- <div class="icon">
                                <span class="ico-chart-4"></span>
                                </div> -->
                                <h2>历史数据  <small><span class = "deviceName"></span><small></h2>
                                <ul class="buttons">
                                    <li><a href="#" class="ublock"><div class="icon"><span class="ico-undo"></span></div></a></li>
                                    <!-- <li><a href="#" class="cblock"><div class="icon"><span class="ico-sort"></span></div></a></li> -->
                                </ul>
                            </div>
                            
                            <div id  = "Tab_Gas" style="display:block;">
                            
                             <div class="tabshow5">
	
	<div class="tabTagBox5">
		<ul class="tabTagList5">
			<li id="tag015" >过去24小时</li>
			<li id="tag025">过去七天</li>
			<li id="tag035" class="current5" >过去30天</li>
		
		</ul>
	</div>
	
	<div class="tabcon5">
		<h2 >天然气</h2>
		<div id="h_ch4_chart" style = "width:100%;"></div>           
		
	</div>
	
	<div class="tabcon5">
		<h2 >天然气</h2>
		<div id="w_ch4_chart" style = "width:100%;"></div>    
	
	</div>
	
	<div class="tabcon5">
		<h2 >天然气</h2>
		<div id="m_ch4_chart" style = "width:100%;"></div>    
		
	</div>
	


</div><!--tabshow end-->

<div class="tabshow6">
	
	<div class="tabTagBox6">
		<ul class="tabTagList6 ">
			<li id="tag016" >过去24小时</li>
			<li id="tag026">过去七天</li>
			<li id="tag036" class="current6">过去30天</li>
		
		</ul>
	</div>
	
	<div class="tabcon6">
	<h2 >一氧化碳</h2>
		<div id="h_co_chart" style = "width:100%;"></div>           
	</div>
	
	<div class="tabcon6">
	<h2 >一氧化碳</h2>
		<div id="w_co_chart" style = "width:100%;"></div>    
	</div>
	
	<div class="tabcon6">
	<h2 >一氧化碳</h2>
		<div id="m_co_chart" style = "width:100%;"></div>    
		
	</div>
</div><!--tabshow1 end-->

                            
                            
                            </div>
                           
                          <div id = "Tab_Air" style="display:none;">
                          
                          
                          
    <div class="tabshow">
	
	<div class="tabTagBox">
		<ul class="tabTagList">
			<li id="tag01" >过去24小时</li>
			<li id="tag02">过去七天</li>
			<li id="tag03" class="current" >过去30天</li>
		
		</ul>
	</div>
	
	<div class="tabcon">
		<h2 >温度</h2>
		<div id="h_temp_chart" style = "width:100%;"></div>           
		
	</div>
	
	<div class="tabcon">
		<h2 >温度</h2>
		<div id="w_temp_chart" style = "width:100%;"></div>    
	
	</div>
	
	<div class="tabcon">
		<h2 >温度</h2>
		<div id="m_temp_chart" style = "width:100%;"></div>    
		
	</div>
	
	
	

	
	

	
	

</div><!--tabshow end-->

<div class="tabshow1">
	
	<div class="tabTagBox1">
		<ul class="tabTagList1 ">
			<li id="tag011" >过去24小时</li>
			<li id="tag021">过去七天</li>
			<li id="tag031" class="current1">过去30天</li>
		
		</ul>
	</div>
	
	<div class="tabcon1">
	<h2>湿度</h2>
		<div id="h_humidty_chart" style = "width:100%;"></div>           
	</div>
	
	<div class="tabcon1">
	<h2>湿度</h2>
		<div id="w_humidty_chart" style = "width:100%;"></div>    
	</div>
	
	<div class="tabcon1">
	<h2>湿度</h2>
		<div id="m_humidty_chart" style = "width:100%;"></div>    
		
	</div>
</div><!--tabshow1 end-->


<div class="tabshow2">
	
	<div class="tabTagBox2">
		<ul class="tabTagList2">
			<li id="tag012" >过去24小时</li>
			<li id="tag022">过去七天</li>
			<li id="tag032" class="current2">过去30天</li>
		
		</ul>
	</div>
	
	<div class="tabcon2">
	<h2>PM2.5</h2>
		<div id="h_pm25_chart" style = "width:100%;"></div>           
	</div>
	
	<div class="tabcon2">
	<h2>PM2.5</h2>
		<div id="w_pm25_chart" style = "width:100%;"></div>    
	</div>
	
	<div class="tabcon2">
	<h2>PM2.5</h2>
		<div id="m_pm25_chart" style = "width:100%;"></div>    
	</div>
</div><!--tabshow2 end-->



<div class="tabshow3">
	
	<div class="tabTagBox3">
		<ul class="tabTagList3">
			<li id="tag013" >过去24小时</li>
			<li id="tag023">过去七天</li>
			<li id="tag033" class="current3">过去30天</li>
		
		</ul>
	</div>
	
	<div class="tabcon3">
	<h2>二氧化碳</h2>
		<div id="h_co2_chart" style = "width:100%;"></div>           
	</div>
	
	<div class="tabcon3">
	<h2>二氧化碳</h2>
		<div id="w_co2_chart" style = "width:100%;"></div>    
	</div>
	
	<div class="tabcon3">
	<h2>二氧化碳</h2>
		<div id="m_co2_chart" style = "width:100%;"></div>    
	</div>
</div><!--tabshow3 end-->



<div class="tabshow4">
	
	<div class="tabTagBox4">
		<ul class="tabTagList4">
			<li id="tag014" >过去24小时</li>
			<li id="tag024">过去七天</li>
			<li id="tag034" class="current4">过去30天</li>
		
		</ul>
	</div>
	
	<div class="tabcon4">
	<h2>VOC</h2>
		<div id="h_voc_chart" style = "width:100%;"></div>           
	</div>
	
	<div class="tabcon4">
		<h2>VOC</h2>
		<div id="w_voc_chart" style = "width:100%;"></div>    
	</div>
	
	<div class="tabcon4">
	<h2>VOC</h2>
		<div id="m_voc_chart" style = "width:100%;"></div>    
	</div>
</div><!--tabshow1 end-->
                           
                        </div> 
                            
                        </div>

                      <div class="block" style ="visibility:hidden;">
                            <div class="head orange">                                
                                <h2>Latest Orders</h2>
                                <ul class="buttons">
                                    <li><a href="#" onClick="source('table_main'); return false;"><div class="icon"></div></a></li>
                                    <li><a href="#" class="ublock"><div class="icon"><span class="ico-undo"></span></div></a></li>
                                    <li><a href="#" class="cblock"><div class="icon"><span class="ico-sort"></span></div></a></li>
                                </ul>
                            </div>
                            <div class="data-fluid">
                                <table cellpadding="0" cellspacing="0" width="100%" class="table lcnp">
                                    <thead>
                                        <tr>
                                            <th width="16"><input type="checkbox" class="checkall"/></th>                                        
                                            <th>Name</th>
                                            <th>E-mail</th>
                                            <th>Phone</th>                       
                                            <th width="78">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><input type="checkbox" name="checkbox"/></td>                                        
                                            <td>Dmitry</td>
                                            <td>dmitry@domain.com</td>
                                            <td>+98(765) 432-10-98</td>                      
                                            <td>
                                                <a href="#" class="button green">
                                                    <div class="icon"><span class="ico-pencil"></span></div>
                                                </a>
                                                <a href="#" class="button red">
                                                    <div class="icon"><span class="ico-remove"></span></div>
                                                </a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" name="checkbox"/></td>                                        
                                            <td>Alex</td>
                                            <td>alex@domain.com</td>
                                            <td>+98(765) 432-10-99</td>                      
                                            <td>
                                                <a href="#" class="button green">
                                                    <div class="icon"><span class="ico-pencil"></span></div>
                                                </a>
                                                <a href="#" class="button red">
                                                    <div class="icon"><span class="ico-remove"></span></div>
                                                </a>                                              
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" name="checkbox"/></td>
                                            <td>John</td>
                                            <td>john@domain.com</td>
                                            <td>+98(765) 432-10-97</td>                      
                                            <td>
                                                <a href="#" class="button green">
                                                    <div class="icon"><span class="ico-pencil"></span></div>
                                                </a>
                                                <a href="#" class="button red">
                                                    <div class="icon"><span class="ico-remove"></span></div>
                                                </a>                                              
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" name="checkbox"/></td>                                        
                                            <td>Angelina</td>
                                            <td>angelina@domain.com</td>
                                            <td>+98(765) 432-10-90</td>                      
                                            <td>
                                                <a href="#" class="button green">
                                                    <div class="icon"><span class="ico-pencil"></span></div>
                                                </a>
                                                <a href="#" class="button red">
                                                    <div class="icon"><span class="ico-remove"></span></div>
                                                </a>                                              
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" name="checkbox"/></td>
                                            <td>Tom</td>
                                            <td>tom@domain.com</td>
                                            <td>+98(765) 432-10-92</td>                      
                                            <td>
                                                <a href="#" class="button green">
                                                    <div class="icon"><span class="ico-pencil"></span></div>
                                                </a>
                                                <a href="#" class="button red">
                                                    <div class="icon"><span class="ico-remove"></span></div>
                                                </a>                                                
                                            </td>                                        
                                        </tr>                                
                                    </tbody>
                                </table>
                            </div>                           
                        </div>                        
                        
                    </div>
                    <div class="span5">
                        <div class="block">
                        <div id="loading_real" class="loading" style="overflow:hidden"><center><img src="<%=basePath%>public/loading.gif" />Loading...</center></div>
                            <div class="head">
                                <!-- <div class="icon"><span class="ico-tag"></span></div> -->
                                <h2>数据在线</h2>
                                <ul class="buttons">             
                                    <li><a href="#" onClick="source('tickets'); return false;"></a></li>
                                    <li><a href="#" class="ublock"><div class="icon"><span class="ico-undo"></span></div></a></li>
                                    <li><a href="#" class="cblock"><div class="icon"><span class="ico-sort"></span></div></a></li>
                                </ul>                                
                            </div>
                            <div class="data-fluid">
                                <table width="100%" class="table tickets" id="device_all">
                                    <tr>
                                        <td width="55" class="bl_blue"><span class="label label-info">燃气</span></td>
                                        <td width="50">当前值 <span class="mark">23/02/2013</span></td>
                                        <td><a href="#" class="cblue">气体类型</a> <span class="mark"></span></td>                                        
                                    </tr>
                                               
                                    <tr>
                                        <td class="bl_green"><span class="label label-success">空气电台</span></td>
                                        <td>当前值 <span class="mark">21/02/2013</span></td>
                                        <td><a href="#" class="cgreen">气体类型</a> </td>                                        
                                    </tr>
                                    <tr>
                                        <td class="bl_red"><span class="label label-important">控制设备</span></td>
                                        <td>当前状态 <span class="mark">20/02/2013</span></td>
                                        <td><a href="#" class="cred">设备类型</a> </td>                                        
                                    </tr>   
                                                                 
                                </table>
                            </div>                                   
                        </div>                        
                        
                       <div class="block">
                       <div id="loading_msg" class="loading" style="overflow:hidden"><center><img src="<%=basePath%>public/loading.gif" />Loading...</center></div>
                            <div class="head dblue">                                
                                <h2>留言板</h2>
                                <ul class="buttons">             
                                    <li><a href="#" onClick="source('messages'); return false;"></a></li>
                                    <li><a href="#" class="ublock"><div class="icon" id = "msg_reload"><span class="ico-undo"></span></div></a></li>
                                    <li><a href="#" class="cblock"><div class="icon"><span class="ico-sort"></span></div></a></li>
                                </ul>                                
                            </div>
                            <div class="data dark npr npb">                                
                                <div class="messages scroll" style="height: 200px;overflow:auto" id = "message_leave">
                                  
                                    <div class="item blue">
                                        <div class="arrow"></div>
                                        <div class="text">空气电台反馈</div>
                                        <div class="date"></div>
                                    </div>                               
                                                                  
                                </div>                                
                            </div>    
                            <div class="toolbar dark">
                                <div class="input-prepend input-append">
                                    <span class="add-on dblue"><span class="icon-envelope icon-white"></span></span>
                                    <input type="text" id = "discuss_text"/>                              
                                    <button class="btn dblue" id = "discuss_btn" type="button">Send  <span class="icon-arrow-next icon-white"></span></button>
                                </div>                                 
                            </div>
                        </div>
                        
                    </div>
                    
                </div>
                
            </div>
            
        </div>
        
    </div>
    
    <div class="dialog" id="source" style="display: none;" title="Source"></div>
    
    
   <div id="loginmodal1" style="display:none;">
   <center><h2>微店</h2></center>
   <img src = "<%=basePath%>public/weidian.png"/>
  </div>
  <div id="loginmodal2" style="display:none;">
   <center><h2>服务号</h2></center>
   <img src = "<%=basePath%>public/fuwuhao.jpg"/>
  </div>
    <script type="text/javascript">
$(function(){
  $('#loginform').submit(function(e){
    return false;
  });
  
  $('#modaltrigger1').leanModal({ top: 110, overlay: 0.45, closeButton: ".hidemodal" });
  $('#modaltrigger2').leanModal({ top: 110, overlay: 0.45, closeButton: ".hidemodal" });
});
</script>
    
</body>
</html>




