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
                <a href="index-2.html" class="logo"></a>
                <div class="search">
                    <!--<div class="input-prepend">
                        <span class="add-on orange"><span class="icon-search icon-white"></span></span>
                        <input type="text" placeholder="search..."/>                                                      
                    </div>   -->         
                </div>   
            </div>
            <div class="nContainer">                
                <ul class="navigation" id="navigation11">         
              <!--       <li class="active"><a href="index-2.html" class="blblue"></a></li> -->
                    <li class = 'active'>
                        <a href="#" class="bldblue">未分配</a>
                        <div class="open"></div>
                        <ul id="devices_nop">
                            <li><a href="ui.html">A1</a></li>
                            <li><a href="widgets.html">E1</a></li>
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
                    <a href="index.html" class="button">
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
                        <li><a href="">微店</a></li>
                        <li><a href="http://shop116386771.taobao.com/" target="_blank">淘宝店</a></li>
                         <li><a href="">服务号</a></li>
                    </ul>                                        
                </li>
               <!--  <li>
                    <a href="#" class="button purple">
                        <div class="arrow"></div>
                        <div class="icon">
                            <span class="ico-box"></span>
                        </div>                    
                        <div class="name">我要创业</div>
                    </a>                
                    <ul class="sub">
                        <li><a href="faq.html">FAQ</a></li>
                        <li><a href="login.html">Login</a></li>
                    </ul>                                        
                </li> -->
                <li>
                    <a href="http://www.airradio.cn/bbs/forum.php" class="button orange">
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
                                <div class="left">60%</div>
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
                                    <li><a href="#" onClick="source('table_main'); return false;"><div class="icon"><span class="ico-info"></span></div></a></li>
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
                            <div class="head">
                                <!-- <div class="icon"><span class="ico-tag"></span></div> -->
                                <h2>数据在线</h2>
                                <ul class="buttons">             
                                    <li><a href="#" onClick="source('tickets'); return false;"><div class="icon"><span class="ico-info"></span></div></a></li>
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
                            <div class="head dblue">                                
                                <h2>留言板</h2>
                                <ul class="buttons">             
                                    <li><a href="#" onClick="source('messages'); return false;"><div class="icon"><span class="ico-info"></span></div></a></li>
                                    <li><a href="#" class="ublock"><div class="icon"><span class="ico-undo"></span></div></a></li>
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
    
</body>
</html>


<script type="text/javascript">
var _timerWeather;
var _timerRealData;
var _timerHistoryData;

var _currentSensorId;
var _currentGasId;
var deviceMap = new Map();
var cityISO;
var cityname;

var dataArray=new Array();    //存放历史数据
for(var i=0;i<7;i++){
	dataArray[i]=new Array(); 
}

var _currentHistoryType = 2;     //0:24小时    1:过去7天   2:过去30天    
var _historyTimeType = [1,2,2];
var _historyInterval = [24,7,30];

var dataT_24 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[]];

var dataT_7 = [[],[],[],[],[],[],[]];
var dataT_30  = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[],[],[],[],[],[],[]];
var dataH_24 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[]];
var dataH_7 = [[],[],[],[],[],[],[]];
var dataH_30 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[],[],[],[],[],[],[]];
var dataC_24 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[]];
var dataC_7 = [[],[],[],[],[],[],[]];
var dataC_30 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[],[],[],[],[],[],[]];
var dataP_7 = [[],[],[],[],[],[],[]];
var dataP_24 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[]];
var dataP_30 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[],[],[],[],[],[],[]];
var dataV_24 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[]];
var dataV_7 = [[],[],[],[],[],[],[]];
var dataV_30 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[],[],[],[],[],[],[]];

var dataCH_7 = [[],[],[],[],[],[],[]];
var dataCH_24 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[]];
var dataCH_30 =[[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[],[],[],[],[],[],[]];
var dataCO_24 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[]];
var dataCO_7 = [[],[],[],[],[],[],[]];
var dataCO_30 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[],[],[],[],[],[],[]];

//温度
var plot_temp_1;
var plot_temp_2;
var plot_temp_3;
//湿度
var plot_humidty_1; 
var plot_humidty_2; 
var plot_humidty_3; 
//pm2.5
var plot_pm25_1; 
var plot_pm25_2;
var plot_pm25_3; 
//co2
var plot_co2_1;
var plot_co2_2;
var plot_co2_3;
//voc
var plot_voc_1;
var plot_voc_2;
var plot_voc_3;

//ch4
var plot_ch4_1;
var plot_ch4_2;
var plot_ch4_3;

//co
var plot_co_1;
var plot_co_2;
var plot_co_3;

$(document).ready(function() {
	
	
	
	//setChart();
	$.post(
			'<%=basePath%>hwmobile/smart/s006!doListMessage',
			{
				SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
			USERID : localStorage.getItem('USERID','')//'f3d46750-e022-4bf6-88d0-2893caed8b24',
			

			}, function(data) {
			//data.dataObject 
			for(var i=0 ; i<data.dataObject.length ;i++){
				$("#message_leave").append('<div class="item dblue out"><div class="arrow"></div><div class="text">'+data.dataObject[i].ma002+'</div><div class="date">'+data.dataObject[i].ma003+'</div></div>');		
					}
			}
			}); 
	


	
	$("#discuss_btn").click(function(){
		if($("#discuss_text").val()){
		$("#message_leave").append('<div class="item dblue out"><div class="arrow"></div><div class="text">'+$("#discuss_text").val()+'</div><div class="date">'+getRealTime()+'</div></div>');
		$("#discuss_text").val('');
		$.post(
				'<%=basePath%>hwmobile/smart/s006!doAddMessage',
				{
					SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
					USERID : localStorage.getItem('USERID',''),//'f3d46750-e022-4bf6-88d0-2893caed8b24',
					MSG: $("#discuss_text").val()
	
				}, function(data) {
					//alert(data.code);
			
				}); 
		}
	});


	
	
	 var $items = $('#lanrenzhijia>ul>li');
     $items.mouseover(function() {
         $items.removeClass('selected');
         $(this).addClass('selected');

         var index = $items.index($(this));
         $('#lanrenzhijia>div').hide().eq(index).show();
     }).eq(1).mouseover();
	
	
//初始化设备信息
   $('#username').html(localStorage.getItem('username',''));
   $('#tel').html(localStorage.getItem('tel',''));
   $('#quit').click(function(){
	   localStorage.setItem("USERID","");
 	  localStorage.setItem("SESSIONID","");
 	  localStorage.setItem("username","");
 	  localStorage.setItem("tel","");
 	  window.location="<%=basePath%>hwmobile/smart/weather!other";
   });
   //ip city
   cityISO = encodeURI(remote_ip_info.city);
   cityname = remote_ip_info.city;
 
   //保存设备和别名

	deviceMap.clear;

	$.post(
						'<%=basePath%>hwmobile/smart/d002!doListEquip',
						{
							SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
							USERID : localStorage.getItem('USERID',''),//'f3d46750-e022-4bf6-88d0-2893caed8b24',
					

						}, function(data) {
							
					
							var dataObj=eval("("+data.data+")");
					
							for (var i = 0; i < dataObj.sensorList.length ; i++)
							{
							
								var device = dataObj.sensorList[i];
								if (device.air != null){
									if (!_currentSensorId) _currentSensorId = device.air.sensorId;
									  
								
									   $('#Tab_Air').show();
									   $('#Tab_Gas').hide();
							
									//deviceStr += " <li ><a href='#' onclick=setCurrentSensor('"+device.air.sensorId+"')>"+ device.air.name+" ["+device.air.sensorId+"]" +"</a></li>";
								
									deviceMap.put(device.air.sensorId,device.air.name);
									continue;
									
								}
							 	 if (device.gas != null){
										if (!_currentGasId) _currentGasId = device.gas.sensorId;
									
								
									
								
							 		//r1Str += " <li><a href='#'>"+ device.gas.name+" ["+device.gas.sensorId+"]" +"</a></li>";
							 		deviceMap.put(device.gas.sensorId,device.gas.name);
							 		continue;
							 	}
								if (device.ctrl != null){
									deviceMap.put(device.ctrl.deviceId,device.ctrl.name);
									//ctrStr += " <li><a href='#'>"+ device.ctrl.name+" ["+device.ctrl.deviceId+"]" +"</a></li>";
									continue;
								} 
							}
							if (typeof(_currentSensorId)=="undefined"){
								  $('#Tab_Air').hide();
								  $('#Tab_Gas').show();
							}
							
							getScene();
							setHistoryData();
						}); 
		

function getScene(){
	
	$.post(
			'<%=basePath%>hwmobile/smart/scene!getAllScene',
			{
				SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
				USERID : localStorage.getItem('USERID',''),//'f3d46750-e022-4bf6-88d0-2893caed8b24',
		

			}, function(data) {
				
				var deviceStr = '';
			 
				var dataObj=eval("("+data.data+")");
				
				for (var i = 0; i < dataObj.length ; i++)
				{
					
					deviceStr += '<li class = "active">';
					
		
                    deviceStr += '<a href="#" class="blyellow">'+dataObj[i].ma004+'</a>';
                    deviceStr += ' <div class="open"></div>';
                    deviceStr += '<ul>';
    
                         var arStr = dataObj[i].ma002;
                   
                         if (arStr != undefined){
                        	 var names = new Array();
                             
                             names = arStr.split(";");
                     
                             for (var k=0;k<names.length;k++){
                            	 var ss = new Array();
                            	 	ss = names[k].split(',');
								if(deviceMap.get(ss[1])==undefined) continue;
                            		 deviceStr +=" <li><a href='#' onclick=setCurrentSensor('"+ss[1]+"')>"+ deviceMap.get(ss[1])+" ["+ss[1]+"]" +"</a></li>"; 
                            		deviceMap.remove(ss[1]);
                            
                             }
                        	 
                         }
                        
                        	var ctrStr = dataObj[i].ma003;
                        	if (ctrStr != undefined){
                        		var names = new Array();
                                
                                names = ctrStr.split(";");
                        
                                for (var k=0;k<names.length;k++){
                               	 var ss = new Array();
                               	 	ss = names[k].split(',');
   									if (deviceMap.get(ss[1])==undefined) continue;
                               		 deviceStr +=" <li><a href='#'>"+ deviceMap.get(ss[1])+" ["+ss[1]+"]" +"</a></li>"; 
                              		deviceMap.remove(ss[1]);
                               
                                }
                        	}
                       
                        
                         
                     deviceStr += '</ul></li>';
      				
					
					
				}
				var te = document.getElementById("navigation11").innerHTML;  // $('#navigation11').innerHTML;
				document.getElementById("navigation11").innerHTML = te + deviceStr;
				
				var deviceNo ='';
				var arrays = deviceMap.keySet();
				 for(var ii in arrays) {
				 	deviceNo += " <li class = 'active'><a href='#' onclick=setCurrentSensor('"+arrays[ii]+"')>"+ deviceMap.get(arrays[ii])+" ["+arrays[ii]+"]" +"</a></li>";
				}
	
				
				$('#devices_nop').html(deviceNo);
		
				 $(".sidebar .navigation > li > a, .sidebar .navigation > li > .open").click(function(){
				        if($(this).parent('li').find('ul').length > 0){
				            if($(this).parent('li').hasClass('active')){
				                $(this).parent('li').removeClass('active');            
				            }else{
				                $(this).parent('li').addClass('active');
				            }    
				            return false;
				        }
				    });
			
			});	
	
}
		
		
//初始化图片
setMyPic();

getPM25();
//初始化天气
setWeather();
setRealData();

_timerWeather = window.setInterval(setWeather, 10000);
_timerRealData = window.setInterval(setRealData, 10000);





//温度
plot_temp_1 = $.jqplot('h_temp_chart', [dataT_24], setChartOption("温度","#FF327A"));
plot_temp_2 = $.jqplot('w_temp_chart', [dataT_7], setChartOption("温度","#FF427F"));
plot_temp_3 = $.jqplot('m_temp_chart', [dataT_30], setChartOption("温度","#FF367F"));
//湿度
plot_humidty_1 = $.jqplot('h_humidty_chart', [dataH_24],setChartOption("湿度","#FFAB22"));
plot_humidty_2 = $.jqplot('w_humidty_chart', [dataH_7], setChartOption("湿度","#FFAB22"));
plot_humidty_3 = $.jqplot('m_humidty_chart', [dataH_30], setChartOption("湿度","#FFAC22"));
//pm2.5
plot_pm25_1 = $.jqplot('h_pm25_chart', [dataP_24], setChartOption("pm2.5","#00327A"));
plot_pm25_2 = $.jqplot('w_pm25_chart', [dataP_7], setChartOption("pm2.5","#00327A"));
plot_pm25_3 = $.jqplot('m_pm25_chart', [dataP_30], setChartOption("pm2.5","#00327A"));
//co2
plot_co2_1 = $.jqplot('h_co2_chart', [dataC_24], setChartOption("二氧化碳","#0F327A"));
plot_co2_2 = $.jqplot('w_co2_chart', [dataC_7], setChartOption("二氧化碳","#2F327A"));
plot_co2_3 = $.jqplot('m_co2_chart', [dataC_30], setChartOption("二氧化碳","#4F327A"));
//voc

plot_voc_1 = $.jqplot('h_voc_chart', [dataV_24],  setChartOption("VOC","#0F327A"));
plot_voc_2 = $.jqplot('w_voc_chart', [dataV_7],  setChartOption("VOC","#0F327A"));
plot_voc_3 = $.jqplot('m_voc_chart', [dataV_30], setChartOption("VOC","#0F327A"));

//ch4

plot_ch4_1 = $.jqplot('h_ch4_chart', [dataCH_24],  setChartOption("天然气","#0F327A"));
plot_ch4_2 = $.jqplot('w_ch4_chart', [dataCH_7],  setChartOption("天然气","#0F327A"));
plot_ch4_3 = $.jqplot('m_ch4_chart', [dataCH_30], setChartOption("天然气","#0F327A"));

//co

plot_co_1 = $.jqplot('h_co_chart', [dataCO_24],  setChartOption("一氧化碳","#0F327A"));
plot_co_2 = $.jqplot('w_co_chart', [dataCO_7],  setChartOption("一氧化碳","#0F327A"));
plot_co_3 = $.jqplot('m_co_chart', [dataCO_30], setChartOption("一氧化碳","#0F327A"));

//var data_max = 60; //Y轴最大刻度
//var line_title = ["温度","湿度","pm2.5","CO2","VOC"]; //曲线名称
/* var y_label = "值"; //Y轴标题
var x_label = "小时/h"; //X轴标题
var x = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24]; //定义X轴刻度值
var title = ""; //统计图标标题 */
//j.jqplot.diagram.base("chart1", data, line_title, "24小时监控数据", x, x_label, y_label, data_max, 1);
//j.jqplot.diagram.base("chart2", data, line_title, "这是统计标题", x, x_label, y_label, data_max, 2);
//jQuery.jqplot('chart2', data, options);


	//plot2.replot();
	

});

function setChartOption(name,y){
	
	var x = [{label: name}];

	var color = new Array();
	color.push(y);
	var options_temp =      
	{     
			 seriesColors: color,
		      
			  series:x,    
			         
			    legend: {     
			        show: true,//设置是否出现分类名称框（即所有分类的名称出现在图的某个位置）     
			        location: 'ne',     // 分类名称框出现位置, nw, n, ne, e, se, s, sw, w.     
			        xoffset: 12,        // 分类名称框距图表区域上边框的距离（单位px）     
			        yoffset: 12,        // 分类名称框距图表区域左边框的距离(单位px)     
			        background:'' ,     //分类名称框距图表区域背景色     
			        textColor:'' ,  //分类名称框距图表区域内字体颜色  
					
					
			       // ..其他关于样式设计参考官方文档     
			    }, 
			    axesDefaults: {
                    tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                    tickOptions: {
                        angle: 10,  //倾斜角度 
                        fontSize: '10pt'
                    }
                },
		      axes:{
		        xaxis:{
		          renderer:$.jqplot.DateAxisRenderer,
		          tickOptions:{
		            formatString:'%#m - %#d'
		          } 
		        },
		        yaxis:{
		          tickOptions:{
		            formatString:'%.2f',
		            pad:5
		            }
		        }
		      },
		      highlighter: {
		        show: true,
		        sizeAdjust: 7.5
		      },
		      cursor: {
		        show: false
		      }

	} 
	
	return options_temp;
	
}

function set24_Option(name,y,last_hour){
	
	var x = [{label: name}];
	//var tick2 = ['-6', '-5', '-4', '-3' ,'-2', '-1' ,'0','1', '2'];
	var tick2 = new Array();
	for(var i=0; i< 24; i++){
		var index = i+last_hour; 
		tick2.push(index.toString());
	}
	var color = new Array();
	color.push(y);
	var options_temp =      
	{     
			 seriesColors: color,
		      
			  series:x,    
			         
			    legend: {     
			        show: true,//设置是否出现分类名称框（即所有分类的名称出现在图的某个位置）     
			        location: 'ne',     // 分类名称框出现位置, nw, n, ne, e, se, s, sw, w.     
			        xoffset: 12,        // 分类名称框距图表区域上边框的距离（单位px）     
			        yoffset: 12,        // 分类名称框距图表区域左边框的距离(单位px)     
			        background:'' ,     //分类名称框距图表区域背景色     
			        textColor:'' ,  //分类名称框距图表区域内字体颜色  
					
					
			       // ..其他关于样式设计参考官方文档     
			    }, 
			    axesDefaults: {
                    tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                    tickOptions: {
                        angle: 10,  //倾斜角度 
                        fontSize: '10pt'
                    }
                },
		      axes:{
        xaxis:{
          //renderer:$.jqplot.DateAxisRenderer,
		  renderer: $.jqplot.CategoryAxisRenderer,
		  tickInterval: '1hour',
          ticks: tick2,
                        tickOptions: {

                            fontSize: '10pt',
							formatString:'%b&nbsp;%#d'
         },
		  mark: 'cross'
        },
        yaxis:{
          tickOptions:{
            formatString:'%.2f'
            }
        }
      },
		      highlighter: {
		        show: true,
		        sizeAdjust: 7.5
		      },
		      cursor: {
		        show: false
		      }

	} 
	
	return options_temp;
	
}



function setWeather() {
	//var _ip = $('#keleyivisitorip').text();
	$("#city").html(cityname);
	if (cityname.length == 0) return;
	$
			.post(
					'<%=basePath%>hwmobile/smart/weather!getWeatherContent',
					{
						city:cityname,
					}, function(data) {

						
						
						$("#outside_temp").html(data.dataObject.weatherinfo.temp1);
						$("#outside_hum").html(data.dataObject.weatherinfo.temp2);
						
						/* $("#date_y")
								.val(data.dataObject.weatherinfo.date_y);
						$("#week").val(data.dataObject.weatherinfo.week);
						$("#temp1").val(data.dataObject.weatherinfo.temp1);
						$("#temp2").val(data.dataObject.weatherinfo.temp2);
						$("#weather1").val(
								data.dataObject.weatherinfo.weather1);
						$("#weather2").val(
								data.dataObject.weatherinfo.weather2);
						$("#index_d").val(
								data.dataObject.weatherinfo.index_d); */
						//var myjson='';
						// eval_r('myjson=' + data + ';');
						
						if(data.dataObject.weatherinfo.city != null){
						//_timerWeather.clearInterval;
						clearInterval(_timerWeather);
						//alert("yes");
						}
					});
	
	
	
}
//获得实时数据
function setRealData() {

	
	
	$.post(
					'<%=basePath%>hwmobile/smart/weather!doContentDetail',
					{
						SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
						USERID : localStorage.getItem('USERID',''),//'f3d46750-e022-4bf6-88d0-2893caed8b24',
					},
					function(data) {
						//alert(data.dataObject[0].sensorList[2].air.temperature);
					 
							  for (var i = 0; i < data.dataObject[0].sensorList.length - 1; i++) {
									if (_currentSensorId != null && data.dataObject[0].sensorList[i].sensorId==_currentSensorId){
										if (data.dataObject[0].sensorList[i].air != null){
											$('#airradio_temp').html(data.dataObject[0].sensorList[i].air.temperature);
											$('#airradio_hum').html(data.dataObject[0].sensorList[i].air.humidity);
			
											
											  $('#airradio_co2').html(data.dataObject[0].sensorList[i].air.co2);
			
											  $('#airradio_pm25').html(data.dataObject[0].sensorList[i].air.pm25);
			
											$('#airradio_voc').html(data.dataObject[0].sensorList[i].air.voc);
										}
										
									}
									if (_currentGasId != null && data.dataObject[0].sensorList[i].sensorId==_currentGasId){
									
										if (data.dataObject[0].sensorList[i].gas != null){
											$('#airradio_ch4').html(data.dataObject[0].sensorList[i].gas.ch4);
											$('#airradio_co').html(data.dataObject[0].sensorList[i].gas.co);
										}
											
									}
							  }
							
						
						
						//$("#temp").val(data.dataObject[0].sensorList[0].air.createTime);
/* 	<tr>
                                        <td width="55" class="bl_blue"><span class="label label-info">燃气</span></td>
                                        <td width="50">#AA-325 <span class="mark">23/02/2013</span></td>
                                        <td><span class="cblue">...</span> <span class="mark">Added by Dmitry Ivaniuk</span></td>                                        
                                    </tr>
                                               
                                    <tr>
                                        <td class="bl_green"><span class="label label-success">空气电台</span></td>
                                        <td>#AC-857 <span class="mark">21/02/2013</span></td>
                                        <td><a href="#" class="cgreen">Buy on themeforest this great template...</a> <span class="mark">Added by Dmitry Ivaniuk</span><span >Added by Dmitry Ivaniuk</span></td>                                        
                                    </tr> */
						var deviceDataAll = "" ;
					    for (var i = 0; i < data.dataObject[0].sensorList.length ; i++) {

					    	if (data.dataObject[0].sensorList[i].air.temperature != undefined){
					    		deviceDataAll += "<tr>"; 
					    		deviceDataAll += '<td class="bl_green"><span class="label label-success">空气电台['+data.dataObject[0].sensorList[i].sensorId+']</span></td>';
					    		deviceDataAll += '<td width="50">'+data.dataObject[0].sensorList[i].air.temperature+'<br><span>'+data.dataObject[0].sensorList[i].air.humidity+'</span><br><span>'+data.dataObject[0].sensorList[i].air.co2+'</span><br><span>'+data.dataObject[0].sensorList[i].air.pm25+'</span><br><span>'+data.dataObject[0].sensorList[i].air.voc+'</span></td>';
					    		deviceDataAll += '<td><span class="cblue">温度</span> <br><span class="cblue">湿度</span><br><span class="cblue">二氧化碳</span><br><span class="cblue">PM2.5</span><br><span class="cblue">VOC</span></td> ';
					    		deviceDataAll += "</tr>"; 
								
							}
							if (data.dataObject[0].sensorList[i].gas.ch4 != undefined){
		
								deviceDataAll += "<tr>"; 
					    		deviceDataAll += '<td class="bl_blue"><span class="label label-info">燃气['+data.dataObject[0].sensorList[i].sensorId+']</span></td>';
					    		deviceDataAll += '<td width="50">'+data.dataObject[0].sensorList[i].gas.ch4+'<br><span>'+data.dataObject[0].sensorList[i].gas.co+'</span></td>';
					    		deviceDataAll += '<td><span class="cblue">天然气</span> <br><span class="cblue">一氧化碳</span></td> ';
					    		deviceDataAll += "</tr>"; 
							}
							if (data.dataObject[0].sensorList[i].ctrl.deviceType != undefined){
								
								for(var key in data.dataObject[0].sensorList[i].ctrl.ctrlContent){
									
									/* 	alert(data.dataObject[0].sensorList[i].ctrl.ctrlContent[key].switchState); */
									deviceDataAll += "<tr>"; 
						    		deviceDataAll += '<td class="bl_red"><span class="label label-important">控制设备['+data.dataObject[0].sensorList[i].sensorId+']</span></td>';
						    		deviceDataAll += '<td width="50">'+getCtrlType(data.dataObject[0].sensorList[i].ctrl.ctrlContent[key].switchType)+'<br><span>'+getCtrlState(data.dataObject[0].sensorList[i].ctrl.ctrlContent[key].switchState)+'</span></td>';
						    		deviceDataAll += '<td><span class="cblue">类型</span> <br><span class="cblue">状态</span></td> ';
						    		deviceDataAll += "</tr>"; 
								}
								
							
							}
						}

						$("#device_all").html(deviceDataAll);

					});

}

//获得历史数据
function setHistoryData() {
	//var _historyTimeType = [1,2,2];
//var _historyInterval = [24,7,30];
	//alert(dataT_30);	

 $.post(
					'<%=basePath%>hwmobile/smart/weather!doListDetailHistory',
					{
						SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
						USERID : localStorage.getItem('USERID',''),//'f3d46750-e022-4bf6-88d0-2893caed8b24',
						SENSORID : _currentSensorId,
						TYPE: _historyTimeType[_currentHistoryType],
						LASTPAR: _historyInterval[_currentHistoryType],

					}, function(data) {
						
						if(_currentSensorId.indexOf("5") == 0){
							$(".deviceName").html("[" + _currentSensorId + "]");
							$("#real_air_name").html(_currentSensorId);
							$("#real_gas_name").html();
							
						}else if(_currentSensorId.indexOf("1") == 0){
							$(".deviceName").html("[" + _currentSensorId + "]");
							$("#real_gas_name").html(_currentSensorId);
							$("#real_air_name").html();
							}
							
						
					  // alert('当前：'+_currentHistoryType);
								if (_currentHistoryType==0){
									//alert("执行了0");
									
									if (data.dataObject[0].sensorList.length>0){
										if(data.dataObject[0].sensorList[0].air.temperature != undefined){
											for (var i = 0; i < data.dataObject[0].sensorList.length; i++)
												{
												
													dataT_24[i][0]=getTimeDif(24-i);
													dataH_24[i][0]=getTimeDif(24-i);
													dataP_24[i][0]=getTimeDif(24-i);
													dataC_24[i][0]=getTimeDif(24-i);
													dataV_24[i][0]=getTimeDif(24-i);
										
													
													dataT_24[i][1]=(parseInt(data.dataObject[0].sensorList[i].air.temperature));
													dataH_24[i][1]=(parseInt(data.dataObject[0].sensorList[i].air.humidity));
													dataP_24[i][1]=(parseInt(data.dataObject[0].sensorList[i].air.pm25));
													dataC_24[i][1]=(parseInt(data.dataObject[0].sensorList[i].air.co2));
													dataV_24[i][1]=(parseInt(data.dataObject[0].sensorList[i].air.voc));
												
												}
								/* 			dataT_24[0] = dataArray[0];
											dataH_24[0] = dataArray[1];
											dataP_24[0] = dataArray[2];
											dataC_24[0] = dataArray[3];
											dataV_24[0] = dataArray[4]; */
											
											 dataT_24[0].length = 24;
												dataH_24[0].length = 24;
												dataP_24[0].length = 24;
												dataC_24[0].length = 24;
												dataV_24[0].length = 24;
											
											
										}
										if(data.dataObject[0].sensorList[0].gas.ch4 != undefined){
											
											for (var i = 0; i < data.dataObject[0].sensorList.length ; i++)
											{
												dataCH_24[i][1]=(getTimeDif(24-i));
												dataCO_24[i][1]=(getTimeDif(24-i));
												dataCH_24[i][1]=(parseInt(data.dataObject[0].sensorList[i].gas.ch4));
												dataCO_24[i][1]=(parseInt(data.dataObject[0].sensorList[i].gas.co));
											
											}
											
									
											dataCH_24[0].length = 24;
											dataCO_24[0].length = 24;
											
										}
										setChart('24');
								}
									
									
									
								
									
		 						}
								if (_currentHistoryType==1){
									//alert("执行了1");
									
									if (data.dataObject[0].sensorList.length>0){
										if(data.dataObject[0].sensorList[0].air.temperature != undefined){
											for (var i = 0; i < data.dataObject[0].sensorList.length; i++)
												{
												
												/* dataArray[0][i]=parseInt(data.dataObject[0].sensorList[i].air.temperature);
												dataArray[1][i]=parseInt(data.dataObject[0].sensorList[i].air.humidity);
												dataArray[2][i]=parseInt(data.dataObject[0].sensorList[i].air.pm25);
												dataArray[3][i]=parseInt(data.dataObject[0].sensorList[i].air.co2);
												dataArray[4][i]=parseInt(data.dataObject[0].sensorList[i].air.voc); */
												
												dataT_7[i][0] = getDateDif(7-i);
												dataH_7[i][0] = getDateDif(7-i);
												dataP_7[i][0] = getDateDif(7-i);
												dataC_7[i][0] = getDateDif(7-i);
												dataV_7[i][0] = getDateDif(7-i);
									
												
												dataT_7[i][1] = parseInt(data.dataObject[0].sensorList[i].air.temperature);
												dataH_7[i][1] = parseInt(data.dataObject[0].sensorList[i].air.humidity);
												dataP_7[i][1] = parseInt(data.dataObject[0].sensorList[i].air.pm25);
												dataC_7[i][1] = parseInt(data.dataObject[0].sensorList[i].air.co2);
												dataV_7[i][1] = parseInt(data.dataObject[0].sensorList[i].air.voc);
												
												
												
												
												}
											//alert(dataT_7); 
											//dataT_7[0] = dataArray[0];
											/* dataH_7[0] = dataArray[1];
											dataP_7[0] = dataArray[2];
											dataC_7[0] = dataArray[3];
											dataV_7[0] = dataArray[4]; */
											
											dataT_7[0].length = 7;
											dataH_7[0].length = 7;
											dataP_7[0].length = 7;
											dataC_7[0].length = 7;
											dataV_7[0].length = 7;
											
										}
										if(data.dataObject[0].sensorList[0].gas.ch4 != undefined){
											
											for (var i = 0; i < data.dataObject[0].sensorList.length ; i++)
											{
												/* dataArray[5][i]=parseInt(data.dataObject[0].sensorList[i].gas.ch4);
												dataArray[6][i]=parseInt(data.dataObject[0].sensorList[i].gas.co); */
												dataCH_7[i][0] = getDateDif(7-i);
												dataCO_7[i][0] = getDateDif(7-i);
												dataCH_7[i][1] = parseInt(data.dataObject[0].sensorList[i].gas.ch4);
												dataCO_7[i][1] = parseInt(data.dataObject[0].sensorList[i].gas.co);
												
											}
					/* 						dataCH_7[0] = dataArray[5];
											dataCO_7[0] = dataArray[6]; */
											
											dataCH_7[0].length = 7;
											dataCO_7[0].length = 7;
											
										}
										setChart('7');
								}
									
							
								
									
		 						}
								
								if (_currentHistoryType==2){
				
									
									if (data.dataObject[0].sensorList.length>0){
										if(data.dataObject[0].sensorList[0].air.temperature != undefined){
											for (var i = 0; i < data.dataObject[0].sensorList.length; i++)
												{
												
								/* 				dataArray[0][i]=parseInt(data.dataObject[0].sensorList[i].air.temperature);
												dataArray[1][i]=parseInt(data.dataObject[0].sensorList[i].air.humidity);
												dataArray[2][i]=parseInt(data.dataObject[0].sensorList[i].air.pm25);
												dataArray[3][i]=parseInt(data.dataObject[0].sensorList[i].air.co2);
												dataArray[4][i]=parseInt(data.dataObject[0].sensorList[i].air.voc);
												*/
												dataT_30[i][0] = getDateDif(30-i);
												dataH_30[i][0] = getDateDif(30-i);
												dataP_30[i][0] = getDateDif(30-i);
												dataC_30[i][0] = getDateDif(30-i);
												dataV_30[i][0] = getDateDif(30-i);
									
												
												dataT_30[i][1] =  parseInt(data.dataObject[0].sensorList[i].air.temperature);
												dataH_30[i][1] = parseInt(data.dataObject[0].sensorList[i].air.humidity);
												dataP_30[i][1] = parseInt(data.dataObject[0].sensorList[i].air.pm25);
												dataC_30[i][1] = parseInt(data.dataObject[0].sensorList[i].air.co2);
												dataV_30[i][1] = parseInt(data.dataObject[0].sensorList[i].air.voc);
												
												}
								/* 			dataT_30[0] = dataArray[0];
											dataH_30[0] = dataArray[1];
											dataP_30[0] = dataArray[2];
											dataC_30[0] = dataArray[3];
											dataV_30[0] = dataArray[4]; */
											
											dataT_30[0].length = 30;
											dataH_30[0].length = 30;
											dataP_30[0].length = 30;
											dataC_30[0].length = 30;
											dataV_30[0].length = 30;
											
											
										}
										if(data.dataObject[0].sensorList[0].gas.ch4 != undefined){
											
											for (var i = 0; i < data.dataObject[0].sensorList.length ; i++)
											{
												dataCH_30[i][0] = getDateDif(30-i);
												dataCO_30[i][0] = getDateDif(30-i);
												dataCH_30[i][1] = parseInt(data.dataObject[0].sensorList[i].gas.ch4);
												dataCO_30[i][1] = parseInt(data.dataObject[0].sensorList[i].gas.co);
											
											}
									
											
											dataCH_30[0].length = 30;
											dataCO_30[0].length = 30;
											
										}
										setChart('30');
								}
								
									
									
									
		 						}
						
						
				
						//setChart();
						//$("#bton").click();
					}); 
	
	
}
function setChart(historyType){
/* 	var dataArray = new Array();
	for(var i = 0;i<7;i++)
	dataArray[i] = new Array();

	dataArray[0].push("23-May-08");
	dataArray[0].push(dataT_7[0][i]);
	dataArray[1].push("24-May-08");
	dataArray[1].push(566.5);
	dataArray[2].push("25-May-08");
	dataArray[2].push(578.55);
	dataArray[3].push("26-May-08");
	dataArray[3].push(566.5);
	dataArray[4].push("27-May-08");
	dataArray[4].push(234.55);
	dataArray[5].push("28-May-08");
	dataArray[5].push(56.5);
	dataArray[6].push("29-May-08");
	dataArray[6].push(878.55); */

	if (historyType == '24'){
			 plot_temp_1 = $.jqplot('h_temp_chart', [dataT_24], set24_Option("温度","#FF327A",parseInt(dataT_24[0][0])));
			  plot_humidty_1 = $.jqplot('h_humidty_chart', [dataH_24],set24_Option("湿度","#30CC00",parseInt(dataH_24[0][0])));
			   plot_pm25_1 = $.jqplot('h_pm25_chart', [dataP_24], set24_Option("PM2.5","#00327A",parseInt(dataP_24[0][0])));
		    plot_co2_1 = $.jqplot('h_co2_chart', [dataC_24], set24_Option("二氧化碳","#0F327A",parseInt(dataC_24[0][0])));
		     plot_voc_1 = $.jqplot('h_voc_chart', [dataV_24],  set24_Option("VOC","#0F327A",parseInt(dataV_24[0][0])));
		      plot_ch4_1 = $.jqplot('h_ch4_chart', [dataCH_24],  set24_Option("天然气","#0F327A",parseInt(dataCH_24[0][0])));
		       plot_co_1 = $.jqplot('h_co_chart', [dataCO_24],  set24_Option("一氧化碳","#0F327A",parseInt(dataCO_24[0][0])));
		        plot_temp_1.replot();
			plot_humidty_1.replot();
			plot_pm25_1.replot();
			plot_co2_1.replot();
			plot_voc_1.replot();
			plot_ch4_1.replot();

	
			plot_co_1.replot();


	}
   
	
if (historyType == '7'){

		 plot_temp_2 = $.jqplot('w_temp_chart', [dataT_7], setChartOption("温度","#FF427F"));
		  plot_humidty_2 = $.jqplot('w_humidty_chart', [dataH_7], setChartOption("湿度","#FFAB22"));
		   plot_pm25_2 = $.jqplot('w_pm25_chart', [dataP_7], setChartOption("PM2.5","#00327A"));
		    plot_co2_2 = $.jqplot('w_co2_chart', [dataC_7], setChartOption("二氧化碳","#2F327A"));
		     plot_voc_2 = $.jqplot('w_voc_chart', [dataV_7],  setChartOption("VOC","#0F327A"));
		     	 plot_ch4_2 = $.jqplot('w_ch4_chart', [dataCH_7],  setChartOption("天然气","#0F327A"));
			  plot_co_2 = $.jqplot('w_co_chart', [dataCO_7],  setChartOption("一氧化碳","#0F327A"));
			  plot_temp_2.replot();
			  plot_humidty_2.replot();
			  plot_pm25_2.replot();
			  plot_co2_2.replot();
			  plot_voc_2.replot();
	
			plot_ch4_2.replot();
		
			plot_co_2.replot();

	}
	
if (historyType == '30'){
	//alert(dataT_30);
	 plot_temp_3 = $.jqplot('m_temp_chart', [dataT_30], setChartOption("温度","#FF367F"));
	  plot_humidty_3 = $.jqplot('m_humidty_chart', [dataH_30], setChartOption("湿度","#FFAC22"));
	   plot_pm25_3 = $.jqplot('m_pm25_chart', [dataP_30], setChartOption("PM2.5","#00327A"));
	    plot_co2_3 = $.jqplot('m_co2_chart', [dataC_30], setChartOption("二氧化碳","#4F327A"));
	     plot_voc_3 = $.jqplot('m_voc_chart', [dataV_30], setChartOption("VOC","#0F327A"));
	      plot_ch4_3 = $.jqplot('m_ch4_chart', [dataCH_30], setChartOption("天然气","#0F327A"));
	       plot_co_3 = $.jqplot('m_co_chart', [dataCO_30], setChartOption("一氧化碳","#0F327A"));
	       plot_temp_3.replot();
	       plot_humidty_3.replot();
	       plot_pm25_3.replot();
	       plot_co2_3.replot();
	       	plot_voc_3.replot(); 

	plot_ch4_3.replot();
	

	plot_co_3.replot();
}

	

 

	/* //温度
		
	
	 plot_temp_1 = $.jqplot('h_temp_chart', dataT_24, setChartOption("温度","#FF327A"));
	 plot_temp_2 = $.jqplot('w_temp_chart', dataT_7, setChartOption("温度","#FF427F"));
	 plot_temp_3 = $.jqplot('m_temp_chart', dataT_30, setChartOption("温度","#FF367F"));
//湿度
	 plot_humidty_1 = $.jqplot('h_humidty_chart', dataH_24,setChartOption("湿度","#30CC00"));
	 plot_humidty_2 = $.jqplot('w_humidty_chart', dataH_7, setChartOption("湿度","#FFAB22"));
	 plot_humidty_3 = $.jqplot('m_humidty_chart', dataH_30, setChartOption("湿度","#FFAC22"));
//pm2.5
	 plot_pm25_1 = $.jqplot('h_pm25_chart', dataP_24, setChartOption("pm2.5","#00327A"));
	 plot_pm25_2 = $.jqplot('w_pm25_chart', dataP_7, setChartOption("pm2.5","#00327A"));
	 plot_pm25_3 = $.jqplot('m_pm25_chart', dataP_30, setChartOption("pm2.5","#00327A"));
//co2
	 plot_co2_1 = $.jqplot('h_co2_chart', dataC_24, setChartOption("Co2","#0F327A"));
	 plot_co2_2 = $.jqplot('w_co2_chart', dataC_7, setChartOption("Co2","#2F327A"));
	 plot_co2_3 = $.jqplot('m_co2_chart', dataC_30, setChartOption("Co2","#4F327A"));
	
//voc

	 plot_voc_1 = $.jqplot('h_voc_chart', dataV_24,  setChartOption("voc","#0F327A"));
	 plot_voc_2 = $.jqplot('w_voc_chart', dataV_7,  setChartOption("voc","#0F327A"));
	 plot_voc_3 = $.jqplot('m_voc_chart', dataV_30, setChartOption("voc","#0F327A"));
	
	//ch4

	 plot_ch4_1 = $.jqplot('h_ch4_chart', dataCH_24,  setChartOption("天然气","#0F327A"));
	 plot_ch4_2 = $.jqplot('w_ch4_chart', dataCH_7,  setChartOption("天然气","#0F327A"));
	 plot_ch4_3 = $.jqplot('m_ch4_chart', dataCH_30, setChartOption("天然气","#0F327A"));

	 //co

	 plot_co_1 = $.jqplot('h_co_chart', dataCO_24,  setChartOption("Co","#0F327A"));
	 plot_co_2 = $.jqplot('w_co_chart', dataCO_7,  setChartOption("Co","#0F327A"));
	 plot_co_3 = $.jqplot('m_co_chart', dataCO_30, setChartOption("Co","#0F327A"));
	
	 plot_temp_1.replot();
	plot_temp_2.replot();
	
	plot_temp_3.replot();
	//plot_temp_3.draw();
	
	plot_humidty_1.replot();
	plot_humidty_2.replot();
	plot_humidty_3.replot();
	
	plot_pm25_1.replot();
	plot_pm25_2.replot();
	plot_pm25_3.replot();
	
	plot_co2_1.replot();
	plot_co2_2.replot();
	plot_co2_3.replot();
	
	plot_voc_1.replot();
	plot_voc_2.replot();
	plot_voc_3.replot(); 
	
	plot_ch4_1.replot();
	plot_ch4_2.replot();
	plot_ch4_3.replot();
	
	plot_co_1.replot();
	plot_co_2.replot();
	plot_co_3.replot(); */
	
}
//获得控制设备类型和状态
function getCtrlType(_type){
	if (_type == '00') return '无连接';
	if (_type == '01') return '灯';
	if (_type == '02') return '风扇';
	if (_type == '03') return '加湿器';
	if (_type == '04') return '空气净化器';
	if (_type == '05') return '空调';
}
function getCtrlState(_state){
	if (_state == '00') return '关';
	if (_state == '01') return '开';
}

//获得系统前n天的日期
function getDateDif(d){


	var   today=new   Date();     
       var   yesterday_milliseconds=today.getTime()-1000*60*60*24*d;     
       var   yesterday=new   Date();     
       yesterday.setTime(yesterday_milliseconds);     
         
       var strYear=yesterday.getFullYear();  
       var strDay=yesterday.getDate();  
       var strMonth=yesterday.getMonth()+1;  
      
       var strYesterday=strMonth+"-"+strDay +"-" + strYear; 
       return strYesterday;
	
}
//获得前24小时
function getTimeDif(t){
    var date = new Date ();
    var tim = date.getHours () - t;
    
    return tim;
}
//获取当前时间
function getRealTime(){
	var date = new Date();
	return date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes(); 

}

function setMyPic(){
	$.post(
		      '<%=basePath%>hwmobile/smart/u001!getPic',
		      {
		    	  SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
					USERID : localStorage.getItem('USERID',''),//'f3d46750-e022-4bf6-88d0-2893caed8b24',
		        
		      },
		      function (data) //回传函数
		      {
		  
		    	  
		    	  $("#userpic").attr("src","data:image/png;base64,"+data.data);  


		      }
		    );
}
function getPM25(){

	
	$.ajax({
		type : 'GET',
		url : "<%=basePath%>hwmobile/smart/weather!getPM25",
		data : {
			
			city : cityISO,
		},
		dataType : 'json',
		success:function(data){
		
			$('#air_quality').html(data.message);
			$('#outside_pm25').html(data.count);
		}
	});

}
//设置CurrentSensor
function setCurrentSensor(obj){
	_currentSensorId = obj;
	_currentHistoryType = 2;
	var navList = $('.tabTagList'), navs = navList.children('li'),contentBoxs = $('.tabcon');
	navs.removeClass('current');
	navs.eq(_currentHistoryType).addClass('current');
	contentBoxs.hide().eq(_currentHistoryType).show();
	
	var navList1 = $('.tabTagList1'), navs1 = navList1.children('li'),contentBoxs1 = $('.tabcon1');
	navs1.removeClass('current1');
	navs1.eq(_currentHistoryType).addClass('current1');
	contentBoxs1.hide().eq(_currentHistoryType).show();
	
	var navList2 = $('.tabTagList2'), navs2 = navList2.children('li'),contentBoxs2 = $('.tabcon2');
	navs2.removeClass('current2');
	navs2.eq(_currentHistoryType).addClass('current2');
	contentBoxs2.hide().eq(_currentHistoryType).show();
	
	var navList3 = $('.tabTagList3'), navs3 = navList3.children('li'),contentBoxs3 = $('.tabcon3');
	navs3.removeClass('current3');
	navs3.eq(_currentHistoryType).addClass('current3');
	contentBoxs3.hide().eq(_currentHistoryType).show();
	
	var navList4 = $('.tabTagList4'), navs4 = navList4.children('li'),contentBoxs4 = $('.tabcon4');
	navs4.removeClass('current4');
	navs4.eq(_currentHistoryType).addClass('current4');
	contentBoxs4.hide().eq(_currentHistoryType).show();
	
	var navList5 = $('.tabTagList5'), navs5 = navList5.children('li'),contentBoxs5 = $('.tabcon5');
	navs5.removeClass('current5');
	navs5.eq(_currentHistoryType).addClass('current5');
	contentBoxs5.hide().eq(_currentHistoryType).show();
	
	var navList6 = $('.tabTagList6'), navs6 = navList6.children('li'),contentBoxs6 = $('.tabcon6');
	navs6.removeClass('current6');
	navs6.eq(_currentHistoryType).addClass('current6');
	contentBoxs6.hide().eq(_currentHistoryType).show();
	
	if(_currentSensorId.indexOf("5") == 0){
		   $('#Tab_Air').show();
		   $('#Tab_Gas').hide();
		}else if(_currentSensorId.indexOf("1") == 0){
		  $('#Tab_Air').hide();
		  $('#Tab_Gas').show();
		}
	setRealData();
	setHistoryData();
}


function Map(){
	this.container = new Object();
	}


	Map.prototype.put = function(key, value){
	this.container[key] = value;
	}


	Map.prototype.get = function(key){
	return this.container[key];
	}


	Map.prototype.keySet = function() {
	var keyset = new Array();
	var count = 0;
	for (var key in this.container) {
	// 跳过object的extend函数
	if (key == 'extend') {
	continue;
	}
	keyset[count] = key;
	count++;
	}
	return keyset;
	}


	Map.prototype.size = function() {
	var count = 0;
	for (var key in this.container) {
	// 跳过object的extend函数
	if (key == 'extend'){
	continue;
	}
	count++;
	}
	return count;
	}


	Map.prototype.remove = function(key) {
	delete this.container[key];
	}


	Map.prototype.toString = function(){
	var str = "";
	for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
	str = str + keys[i] + "=" + this.container[keys[i]] + ";\n";
	}
	return str;
	}
	
	Map.prototype.clear = function(){
		for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
			delete this.container[keys[i]];
		}
	}
</script>


