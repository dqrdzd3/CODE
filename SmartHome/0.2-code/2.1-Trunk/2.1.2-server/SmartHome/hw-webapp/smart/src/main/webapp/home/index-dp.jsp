<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
    
 <!-- DATApickerS  -->
	<link href="<%=basePath%>public/toB/plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
    
    <!-- Date Picker -->
<link href="<%=basePath%>public/toB/plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
    
 
    
    <!--[if lte IE 7]>
        <link href="css/ie.css" rel="stylesheet" type="text/css" />
        <script type='text/javascript' src='js/plugins/other/lte-ie7.js'></script>
    <![endif]-->    

<!-- jQuery 1.9.1 --> 
	<script src="<%=basePath%>public/toB/plugins/jQuery/jquery-1.9.1.min.js"></script>    


    <script type='text/javascript' src='js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    
    
    <script type='text/javascript' src='js/plugins/jquery/jquery-migrate-1.1.1.min.js'></script>
    <script type='text/javascript' src='js/plugins/jquery/globalize.js'></script>
    <script type='text/javascript' src='js/plugins/other/excanvas.js'></script>
    
    <script type='text/javascript' src='js/plugins/other/jquery.mousewheel.min.js'></script>
        
    <script type='text/javascript' src='js/plugins/bootstrap/bootstrap.min.js'></script>
 
 <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js" type="text/javascript"></script> 
    
 <!-- datepicker --> 
<script src="<%=basePath%>public/toB/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script> 
 
 <script src="<%=basePath%>public/toB/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script> 
<!-- datepicker --> 
    
<%--     <script type='text/javascript' src='js/plugins/cookies/jquery.cookies.2.2.0.min.js'></script>    
    
    <script type='text/javascript' src='js/plugins/jflot/jquery.flot.js'></script>    
    <script type='text/javascript' src='js/plugins/jflot/jquery.flot.stack.js'></script>    
    <script type='text/javascript' src='js/plugins/jflot/jquery.flot.pie.js'></script>
    <script type='text/javascript' src='js/plugins/jflot/jquery.flot.resize.js'></script>
    
    <script type='text/javascript' src='js/plugins/sparklines/jquery.sparkline.min.js'></script>        
    
    <script type='text/javascript' src='js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    
    <script type='text/javascript' src="js/plugins/uniform/jquery.uniform.min.js"></script> --%>

<%-- <script type='text/javascript' src='js/plugins/shbrush/XRegExp.js'></script> --%>
<%-- <script type='text/javascript' src='js/plugins/shbrush/shCore.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushXml.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushJScript.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushCss.js'></script>    
     --%>
<%--     <script type='text/javascript' src='js/plugins.js'></script>
    <script type='text/javascript' src='js/charts.js'></script>
    
    <script type='text/javascript' src='js/actions.js'></script> --%>



<link href="jqplot/jquery.jqplot.min.css" rel="stylesheet" />



<%-- <script type="text/javascript" src="js/jqplot.js"></script> --%>
<%-- <script src="js/jquery.jqplot.min.js"></script>

<script src="js/jqplot.highlighter.min.js"></script>
<script src="js/jqplot.cursor.min.js"></script>
<script src="js/jqplot.dateAxisRenderer.min.js"></script>
<script src="js/jqplot.highlighter.js"></script>
<script src="js/jqplot.cursor.js"></script>
<script src="js/jqplot.dateAxisRenderer.js"></script>
 --%>

<script type="text/javascript" src="js/lazyload.js"></script>
<script type="text/javascript" src="js/lanrenzhijia.js"></script>
<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>

<%-- <script type="text/javascript" src="js/index_main.js"></script> --%>


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
                    <a href="#" class="button purple">
                        <div class="arrow"></div>
                        <div class="icon">
                            <span class="ico-box"></span>
                        </div>                    
                        <div class="name">大屏</div>
                    </a>                
                    <ul class="sub">
                        <li><a href="faq.html">FAQ</a></li>
                        <li><a href="login.html">Login</a></li>
                    </ul>                                        
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
                
               
                
               
                
                <div class="row-fluid">
                    
                    <div class="span12">
                        
                        <div class="block">
                        <div id="loading_history" class="" style="overflow:hidden"></div>
                            <div class="head">
                                <!-- <div class="icon">
                                <span class="ico-chart-4"></span>
                                </div> -->
                                <h2>广告位  </h2>
                                <ul class="buttons">
                                    <li><a href="#" class="ublock"><div class="icon"><span class="ico-undo"></span></div></a></li>
                                    <!-- <li><a href="#" class="cblock"><div class="icon"><span class="ico-sort"></span></div></a></li> -->
                                </ul>
                            </div>
                            
                            <div id  = "Advertise" style="display:block;">
										
                            
                            </div>
     
                            
                        </div>

                                            
                        
                    </div>
                    
                    
                </div>
                
                 <div class="row-fluid">
                    
                    <div class="span12">
                        
                        <div class="block">
                        <div id="loading_history" class="" style="overflow:hidden"></div>
                            <div class="head">
                                <!-- <div class="icon">
                                <span class="ico-chart-4"></span>
                                </div> -->
                                <h2>通知栏  </h2>
                                <ul class="buttons">
                                    <li><a href="#" class="ublock"><div class="icon"><span class="ico-undo"></span></div></a></li>
                                    <!-- <li><a href="#" class="cblock"><div class="icon"><span class="ico-sort"></span></div></a></li> -->
                                </ul>
                            </div>
                            <s:form  method="post" id="s001Add" enctype="multipart/form-data">
                            <div id  = "Message" style="display:block;">
									<ul>
									<li></li>
									<li><div class="col-sm-4">
						                    <label>设备注册日期:</label>
						                    <div class="input-group">
						                      <div class="input-group-addon">
						                        <i class="fa fa-calendar"></i>
						                      </div>
						                      <input id="range_4" type="text" class="form-control pull-right" />
						                    </div><!-- /.input group -->
						                  </div></li>
				
									<li><s:textarea name="businessPO.ma016" /></li>
									<li></li>
									</ul>
                            
                            </div>
                            </s:form>
     
                            
                        </div>

                                            
                        
                    </div>
                    
                    
                </div>
                
                 <div class="row-fluid">
                    
                    <div class="span12">
                        
                        <div class="block">
                        <div id="loading_history" class="" style="overflow:hidden"></div>
                            <div class="head">
                                <!-- <div class="icon">
                                <span class="ico-chart-4"></span>
                                </div> -->
                                <h2>展示 </h2>
                                <ul class="buttons">
                                    <li><a href="#" class="ublock"><div class="icon"><span class="ico-undo"></span></div></a></li>
                                    <!-- <li><a href="#" class="cblock"><div class="icon"><span class="ico-sort"></span></div></a></li> -->
                                </ul>
                            </div>
                            
                            <div id  = "Display" style="display:block;">

                            
                            </div>
     
                            
                        </div>

                                            
                        
                    </div>
                    
                    
                </div>
                
                 <div class="row-fluid">
                    
                    <div class="span12">
                        
                        <div class="block">
                        <div id="loading_history" class="" style="overflow:hidden"></div>
                            <div class="head">
                                <!-- <div class="icon">
                                <span class="ico-chart-4"></span>
                                </div> -->
                                <h2>综合  </h2>
                                <ul class="buttons">
                                    <li><a href="#" class="ublock"><div class="icon"><span class="ico-undo"></span></div></a></li>
                                    <!-- <li><a href="#" class="cblock"><div class="icon"><span class="ico-sort"></span></div></a></li> -->
                                </ul>
                            </div>
                            
                            <div id  = "All" style="display:block;">

                            
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

	$('#range_4').daterangepicker();
  
  $('#modaltrigger1').leanModal({ top: 110, overlay: 0.45, closeButton: ".hidemodal" });
  $('#modaltrigger2').leanModal({ top: 110, overlay: 0.45, closeButton: ".hidemodal" });
});
</script>
    
</body>
</html>




