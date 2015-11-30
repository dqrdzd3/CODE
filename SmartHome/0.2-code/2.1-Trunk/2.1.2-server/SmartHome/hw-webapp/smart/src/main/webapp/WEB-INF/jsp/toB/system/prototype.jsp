<%@ page contentType="text/html;charset=UTF-8" language="java"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta charset="UTF-8">
<title>空气电台数据系统</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

<!-- jQuery 1.9.1 --> 
<script src="<%=basePath%>public/toB/plugins/jQuery/jquery-1.9.1.min.js"></script>
   

<!-- Bootstrap 3.3.4 -->
<link href="<%=basePath%>public/toB/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- FontAwesome 4.3.0 -->
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons 2.0.0 -->
<link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link href="<%=basePath%>public/toB/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
<!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
<link href="<%=basePath%>public/toB/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
<!-- iCheck -->
<link href="<%=basePath%>public/toB/plugins/iCheck/flat/blue.css" rel="stylesheet" type="text/css" />
<!-- Morris chart -->
<link href="<%=basePath%>public/toB/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
<!-- jvectormap
    <link href="plugins/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" /> -->
<!-- Date Picker 
    <link href="plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />-->
<!-- Daterange picker 
    <link href="plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />-->
<!-- bootstrap wysihtml5 - text editor -->
<link href="<%=basePath%>public/toB/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />

<!-- DATA TABLES 曾凡 20150626 污染排名 -->
<link href="<%=basePath%>public/toB/plugins/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">  

table.tableList
        {
            border-collapse: collapse;
            
            width: 100%;
            text-align: left;
        }
        table.tableList th
        {
            padding: 1px 6px;
            line-height: 21px;
            font-weight: bolder;
        }
        table.tableList tr:hover{
        	background-color:#BADDE9;
        }
        table.tableList td
        {
        	color:#000;
            padding: 1px 6px;
            line-height: 21px;
        }
        table.tableList tr:even{
        background-color:#fff;
        }
        table.tableList .hot
        {
            background-color: #FFFACD;
        }
        table.tableList .toleft
        {
            text-align: left;
        }
        table.tableList .toright
        {
            text-align: right;
        }
        table.tableList .tomiddle
        {
            text-align: center;
        }
        
        
.pageDiv{padding: 15px 20px;text-align: left;color: #ccc;text-align:center;}
.pageDiv a{display: inline-block;color: #428bca;display: inline-block;height: 25px;	line-height: 25px;	padding: 0 10px;border: 1px solid #ddd;	margin: 0 2px;border-radius: 4px;vertical-align: middle;}
.pageDiv a:hover{text-decoration: none;border: 1px solid #428bca;}
.pageDiv span.current{display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;color: #fff;background-color: #428bca;	border: 1px solid #428bca;border-radius: 4px;vertical-align: middle;}
.pageDiv span.disabled{	display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;	color: #bfbfbf;background: #f2f2f2;border: 1px solid #bfbfbf;border-radius: 4px;vertical-align: middle;}
        

.loading2{  
    
      
     
    text-align:center
	width:200px;  
    
    color:#fff;  
     
    font-size:15px;  

    background: #000 url(<%=basePath%>public/toB/dist/img/loading.gif) no-repeat 10px 50%;  
    opacity: 0.7;  
    z-index:999999;  
    
    filter:progid:DXImageTransform.Microsoft.Alpha(opacity=70);   
}
.loading{  
    width:inherit;
    height: inherit;
    position: fixed;  
   
    line-height:56px;  
    color:#fff;  
    padding-left:50px;  
    font-size:15px;  
    background: #000 url(<%=basePath%>public/toB/dist/img/loading.gif) no-repeat 10px 50%;  
    opacity: 0.7;  
    z-index:9999;  
    -moz-border-radius:20px;  
    -webkit-border-radius:20px;  
    border-radius:20px;  
    filter:progid:DXImageTransform.Microsoft.Alpha(opacity=70);  
}
#loading1{  
    
    width:100%;  
    height:100%;  
    position: fixed;  
    text-align:center
    top:0%;  
    left:0%;  
    line-height:56px;  
    color:#fff;  
    padding-top:380px;  
    font-size:15px;  
   
    background: #000 url(<%=basePath%>public/toB/dist/img/loading.gif) no-repeat 10px 50%;  
    opacity: 0.7;   
    z-index:999999;  
    
    filter:progid:DXImageTransform.Microsoft.Alpha(opacity=70); 
} 
</style>      
 
<!-- 模态窗口 --> 
<script src="<%=basePath%>public/toB/dist/css/sweetalert.min.js"></script> 

<link href="<%=basePath%>public/toB/dist/css/sweetalert.css" rel="stylesheet" type="text/css">

<style>
text{
    font: bold 120px Verdana, Helvetica, Arial, sans-serif;
    color:red;
}
</style>
    
 
 <script>
 $(function(){
 $(window).scroll( function() {
var x=0;
var length_ = $("body").scrollTop();
				

			
				if(length_ >= 225)
							{	
								//$("#header").slideUp("slow");
								$("#headerTitle").css({position:"fixed",width:"100%"});
								$("#headerTitle").slideDown("100000");

								//$("#header").slideToggle("slow");
								
								//$("#header").transition({ x:10,y:10}, 1000); 
							
							}
						
				if($("body").scrollTop() <225)
				{		
								$("#headerTitle").css({position:"relative"});
							
							$("#headerTitle").stop();
							$("#headerTitle").show();
				}
 });
 });
 </script>
 
 
    
 <script>
 //日期格式转换
 function dateFormat(date, format) {
	 
	    date = new Date(date);

	    var o = {
	        'M+' : date.getMonth() + 1, //month
	        'd+' : date.getDate(), //day
	        'H+' : date.getHours(), //hour
	        'm+' : date.getMinutes(), //minute
	        's+' : date.getSeconds(), //second
	        'q+' : Math.floor((date.getMonth() + 3) / 3), //quarter
	        'S' : date.getMilliseconds() //millisecond
	    };

	    if (/(y+)/.test(format))
	        format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));

	    for (var k in o)
	        if (new RegExp('(' + k + ')').test(format))
	            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length));
		document.write(format);
	    return format;
	}
 
 
 
 //耗材更换
 function doUseModify(ma001){
		
	 swal({
			title: "确定更换耗材吗？", 
			text: "您确定要更换耗材？", 
			type: "warning",
			showCancelButton: true,
			closeOnConfirm: false,
			animation: "slide-from-top",  
			confirmButtonText: "是的，我要更换",
			confirmButtonColor: "#8ED84A"
			}, function() {
				
				//alert("dd");
				//$(".confirm").attr("disabled", true);
				$(".confirm").addClass("loading2");
	$(".confirm").html("加载中...请稍候");
	$(".confirm").attr("disabled", true);
				
				
				
					
			 	$.post('<%=basePath%>hwmobile/smart/corpmaterial!doStatus',
					      {
							ACCOUNT : localStorage.getItem('PHONE',''),
							SESSIONID : localStorage.getItem('PASSWORD',''),
							MA001 : ma001
					      },
					      function (data,status) //回传函数
					      {
					    	  
					    	 	
					    	 	if(data.code == 1){
					    	 		$("#loading1").hide();
					    	 		$(".confirm").removeClass("loading2");
									//$(".confirm").html("加载中...请稍候");
									$(".confirm").attr("disabled", false);
					    	 		//swal("Good!", "更换成功！", "success");
					    	 		
					    	 		swal({
					    				title: "GOOD", 
					    				text: "更换成功", 
					    				type: "success",
					    				showCancelButton: false,
					    				closeOnConfirm: false,
					    				animation: "slide-from-top",  
					    				confirmButtonText: "确定",
					    				confirmButtonColor: "#AEDEF4"
					    				}, function() {
					    	 				//var url = "<%=basePath%>hwmobile/smart/corpbusiness!doIndex?ACCOUNT="+localStorage.getItem("PHONE","")+"&SESSIONID="+localStorage.getItem("PASSWORD","");
					    	 					    		    //alert(url);
					    	 					    			//window.location.href=url;
					    	 					    			back2home();
					    	 			//	$("tr:contains("+ma001+")").children("td").eq(4).html("<span style = 'font-weight:bold;color:#F39C12;'>已更换</span>");
					    	 			//	$("tr:contains("+ma001+")").children("td").eq(5).html("");
					    	 						    		
					    				});
					    	 		
					    	 	
					    	 		
					    	 	}
					    	 	if(data.code == 0){
					    	 		$("#loading1").hide();
					    	 		$(".confirm").removeClass("loading2");
									//$(".confirm").html("加载中...请稍候");
									$(".confirm").attr("disabled", false);
					    	 		swal("OMG", "操作失败了!", "error");
					    	 	}
					    	  
					    	 	
					    	 
					    	 if(status != "success"){
					    		 $("#loading1").hide();
					    		 $(".confirm").removeClass("loading2");
									//$(".confirm").html("加载中...请稍候");
									$(".confirm").attr("disabled", false);
					    		 swal("OMG", "操作失败了!", "error");
					    	 }
					      }
					    );
				
			});
	
	 
	
	

	
	
}
 //添加关注
 function onSureStatus(ma001){
		swal({
			title: "确定关注吗？", 
			text: "您确定要添加关注吗？", 
			type: "warning",
			showCancelButton: true,
			closeOnConfirm: false,
			animation: "slide-from-top",  
			confirmButtonText: "是的，我要关注",
			confirmButtonColor: "#8ED84A"
			}, function() {
				$(".confirm").addClass("loading2");
				$(".confirm").html("加载中...请稍候");
				$(".confirm").attr("disabled", true);	
				
				$.post('<%=basePath%>hwmobile/smart/corpcrm!doFocus',
					      {
							ACCOUNT : localStorage.getItem('PHONE',''),
							SESSIONID : localStorage.getItem('PASSWORD',''),
							ids : ma001,
							MA011:"1"
							
					      },
					      function (data,status) //回传函数
					      {
					    	  
					    	 	//alert(data.code);
					    	 	if(data.code == 1){
					    	 		$("#loading1").hide();
					    	 		$(".confirm").removeClass("loading2");
									//$(".confirm").html("加载中...请稍候");
									$(".confirm").attr("disabled", false);
									swal("Good", "关注成功！", "success");
					    	 		
									
									pageChange_pm25Mon();
									pageChange_pm25Day();
									
									pageChange_co2Mon();
									pageChange_co2Day();
									
									pageChange_vocMon();
									pageChange_vocDay();
									
									reNew_rank_pm25_day();
									reNew_rank_pm25_mon();
									
									reNew_rank_co2_day();
									reNew_rank_co2_mon();
									
									reNew_rank_voc_day();
									reNew_rank_voc_mon();
					    	 		
									
									
									pageChange();
					    	 		reNew();
					    	 	
					    	 		
					    	 	}
					    	 	if(data.code == 0){
					    	 		$("#loading1").hide();
					    	 		$(".confirm").removeClass("loading2");
									//$(".confirm").html("加载中...请稍候");
									$(".confirm").attr("disabled", false);
					    	 		swal("OMG", "操作失败了!", "error");
					    	 	}
					    	 	if(status != "success"){
					    	  		$("#loading1").hide();
					    	  		$(".confirm").removeClass("loading2");
									//$(".confirm").html("加载中...请稍候");
									$(".confirm").attr("disabled", false);
					    	  		swal("OMG", "操作失败了!", "error");
					    	  	}
					    	  	
					      }
					    );
				
			});
	}
 //取消关注
 
 function onCancelStatus(ma001){
	swal({
		title: "取消关注吗？", 
		text: "您确定要取消关注吗？", 
		type: "warning",
		showCancelButton: true,
		closeOnConfirm: false,
		animation: "slide-from-top",  
		confirmButtonText: "是的，我要取消关注",
		confirmButtonColor: "#ec6c62"
		}, function() {
			$(".confirm").addClass("loading2");
			$(".confirm").html("加载中...请稍候");
			$(".confirm").attr("disabled", true);
			$.post('<%=basePath%>hwmobile/smart/corpcrm!doFocus',
				      {
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						ids : ma001,
						MA011:"0"
						
				      },
				      function (data,status) //回传函数
				      {
				    	  
				    	 	//alert(data.code);
				    	 	if(data.code == 1){
				    	 		//swal("Good!", "取消关注成功！", "success");
		    	 				$("#loading1").hide();
		    	 				$(".confirm").removeClass("loading2");
								//$(".confirm").html("加载中...请稍候");
								$(".confirm").attr("disabled", false);
					    	 		//swal("Good!", "更换成功！", "success");
					    	 		
					    	 		swal("Good", "取消关注成功!", "success");
					    	 		
					    	 		pageChange();
					    	 		reNew();
					    	 			
		    	 			//	$("tr:contains("+ma001+")").children("td").eq(5).html("<span style = 'font-weight:bold;'>未关注</span>");
		    	 			//$("tr:contains("+ma001+")").remove();
		    	 			    	 						    		
				    	 		
				    	 		
				    	 	}
				    	 	if(data.code == 0){
				    	 		$("#loading1").hide();
				    	 		$(".confirm").removeClass("loading2");
								//$(".confirm").html("加载中...请稍候");
								$(".confirm").attr("disabled", false);
				    	 		swal("OMG", "操作失败了!", "error");
				    	 	}
				    	  	if(status != "success"){
				    	  		$("#loading1").hide();
				    	  		$(".confirm").removeClass("loading2");
								//$(".confirm").html("加载中...请稍候");
								$(".confirm").attr("disabled", false);
				    	  		swal("OMG", "操作失败了!", "error");
				    	  	}
				      }
				    );
			
		});
}
 
 
 
 function Map(){
		this.container = new Object();
		}


		Map.prototype.put = function(key, value){
			this.container[key] = value;
		}

		Map.prototype.collect = function(key, value){
			if (typeof(this.container[key])=="undefined")
				this.container[key] = value;
			else	
				this.container[key] = this.container[key]+'-'+value;
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
 var deviceMap = new Map();
 
 	$(function(){
 		$('#navi button:first').click(function(){
 			back2home();
 		});
 		$("#device").click(function(){
 			back2crm();
 		});
 		
 		$("#material").click(function(){
 			back2material();
 		});
 		
 		$("#potential").click(function(){
			 back2potential();
		});
 		
 		
 		
 		
 		$("#updateDevice").click(function(){

 		back2client();
 			
 			
 		});
 		
 		
 		$("#user_online a:last").click(function(){
 			
 			 back2online();
 		});
 		$("#user_all a:last").click(function(){
 			
			 back2crm();
		});
 		$("#user_out a:last").click(function(){
 			
			 back2material();
		});
 		$("#user_focus a:last").click(function(){
 			
			 back2potential();
		});
 		
 		
 	});
 	
 	
 	$(document).ready(function() {
 		$("#navigation").html($("#loading1").html());
 		//左边菜单
 		$.post(
 				'<%=basePath%>hwmobile/smart/corpcrm!doListMenu',
 				{
 					ACCOUNT : localStorage.getItem('PHONE',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
 					SESSIONID : localStorage.getItem('PASSWORD','')//'f3d46750-e022-4bf6-88d0-2893caed8b24',
 				

 				}, function(data) {
		 				var crm = data.dataObject;
		 			
		 				for(var i=0 ; i<data.dataObject.length ;i++){     //data.dataObject.length
		 		
		 					deviceMap.collect(crm[i].MA007,crm[i].MA008);
		 				}
		 	
 						doLeftTree();
 				}); 
 		//计数
		$.post(
 				'<%=basePath%>hwmobile/smart/corpmain!doGetCount',
 				{
 					ACCOUNT : localStorage.getItem('PHONE',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
 					SESSIONID : localStorage.getItem('PASSWORD','')//'f3d46750-e022-4bf6-88d0-2893caed8b24',
 				

 				}, function(data) {
 				var co = data.dataObject;
 		
 					$('#user_all div H3').html(co.crm);
 					$('#user_focus div H3').html(co.focus);
 					$('#user_out div H3').html(co.material);
 					$('#user_online div H3').html(co.online);
 				}); 
 				
 	});
 	var proAndCity,province,city ;
 	/* <ul class="sidebar-menu" id="navigation">
    <li class="header">设备列表</li>
    <li class="active treeview"> <a href="#"> <i class="fa fa-dashboard"></i> <span>河南省</span> <i class="fa fa-angle-left pull-right"></i> </a>
      <ul class="treeview-menu menu-open">
        <li class="active treeview"> <a href="#"> <i class="fa fa-circle-o"></i> <span>郑州市</span> <i class="fa fa-angle-left pull-right"></i> </a>
          <ul class="treeview-menu">
            <li class="active"> <a href="#"><i class="fa fa-circle-o"></i>R1[100000BEB594]</a> </li>
            <li> 				  <a href="#"><i class="fa fa-circle-o"></i>A1[5000013698D9]</a> </li>
          </ul>
        </li>
      </ul>
    </li>
  </ul> */
 	function doLeftTree(){
 		var keys = deviceMap.keySet();
 		var content =  '<li class="header">设备列表</li>';
 		 var ss = new Array();
 		for (var i=0;i<keys.length;i++){
 			var temp = deviceMap.get(keys[i]);
 			if (typeof(temp)=="undefined")
 				ss[0]=temp;
 			else				
     	 		ss = temp.split('-');
	
 		
 			
 				content += '<li class="treeview"> <a > <i class="fa fa-dashboard"></i> <span>'+keys[i]+'</span> <i class="fa fa-angle-left pull-right"></i> </a>';
 				content += '<ul class="treeview-menu menu-open" >';
 				for (var j=0;j<ss.length;j++){
		 		        content += ' <li class=" treeview"> <a > <i class="fa fa-circle-o"></i> <span>'+ss[j]+'</span> <i class="fa fa-angle-left pull-right"></i> </a>';
		 		       content +=   ' <ul class="treeview-menu">';
		 		      //content +=  '    <li > <a onclick=setCurrentSensor("'+keys[i]+'")><i class="fa fa-circle-o"></i>A1['+keys[i]+']</a> </li>';
		           		content += '</ul></li>';
 				}
 		        content +='</ul><li>'
 		        
 					
 			
 	
 		}
 		//content += ' </li></ul>';
 	
 		$("#navigation").html(content);
 	
 		var ddd='b';
 		$(".sidebar-menu li").children('a').click(function(){
 			
 			//$(this).next("ul").slideToggle();
 			if($(this).next("ul").is(":hidden")){
 				
 			
					$(this).next("ul").slideDown();
					ddd = 'a';
				}
			else{
				
			
				$(this).next("ul").slideUp();
				ddd = 'b';
			}
 		});
 		$(".sidebar-menu li").children("ul").children("li").children("a").click(function(){
 			var obj = $(this);
 			
 			obj.next("ul").html($("#loading1").html());
 			
 			if(ddd=='a'){
 				var city =this.getElementsByTagName("span")[0].innerHTML
 				var prov = this.parentNode.parentNode.parentNode.getElementsByTagName("span")[0].innerHTML;
 				$.post(
 		 				'<%=basePath%>hwmobile/smart/corpcrm!doListMenuSensor',
 		 				{
 		 					ACCOUNT : localStorage.getItem('PHONE',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
 		 					SESSIONID : localStorage.getItem('PASSWORD',''),//'f3d46750-e022-4bf6-88d0-2893caed8b24',
 		 					PROVINCE : prov,
							CITY : city
 		 				}, function(data) {
 		 					var sensorlist = data.dataObject;
 		 					var content="";
 		 			
 			 				for(var i=0 ; i<data.dataObject.length ;i++){     //data.dataObject.length
 			 					
 			 					content +=  '<li > <a onclick=setCurrentSensor("'+sensorlist[i].MA002+'")><i class="fa fa-circle-o"></i>A1['+sensorlist[i].MA002+']</a> </li>';
 			 					
 			 				}
 			 			
 			 				obj.next("ul").html(content);
 			 	
 			 				
 			 				if(obj.next("ul").is(":hidden") && ddd=='a'){
 			 	 				
 			 	 				
 			 	 				obj.next("ul").slideDown();
 			 	 				}
 			 	 			if(obj.next("ul").is(":visible") && ddd=='b'){
 			 	 				
 			 	 	 			
 			 	 				obj.next("ul").slideUp();
 			 					}
 			 				
 		 				}); 
 			}
 		
 			
 			
 
 		});

 	}

 	
 function setCurrentSensor(obj){
	 var url = "<%=basePath%>home/index-1.jsp?DEVID="+obj;
		window.location.target="_blank";
		window.location.href=url;
 }	
 
 function getTreeContent(e){
	
	 //alert(e.getElementsByTagName("span")[0].innerHTML);
	 var d = e.parentNode.parentNode.parentNode.getElementsByTagName("span")[0].innerHTML;
	 //alert(d);
 }

 </script>

</head>

<body class="skin-blue sidebar-mini">

<div id="loading1"  style="display:none"><center><img src="<%=basePath%>public/loading.gif" />Loading pages...</center></div>


<div class="wrapper">
<%-- <header id = "headerTitle" class="main-header"> 
  <!-- logo --> 
  <a href="" class="logo"> 
  
  <!-- mini logo for sidebar mini 50x50 pixels --> 
  
  <span class="logo-mini"><b>空气电台</b></span> 
  
  <!-- logo for regular state and mobile devices --> 
  
  <span class="logo-lg">空气电台数据系统</span> </a> 
  <!-- Header Navbar: style can be found in header.less -->
  <nav class="navbar navbar-static-top" role="navigation" id="navi"> 
    <!-- Sidebar toggle button--> 
    <a href="" class="sidebar-toggle" data-toggle="offcanvas" role="button"> <span class="sr-only">Toggle navigation</span> </a>
    <!-- 头部的按钮  -->
   
    <button type="button" class="btn btn-info btn-lg" style = "margin-top:2px">主页</button>
    <button type="button" class="btn btn-info btn-lg" style = "margin-top:2px" id = "device">设备管理</button>
    <button type="button" class="btn btn-info btn-lg" style = "margin-top:2px" id = "material">耗材维护</button>
    <button type="button" class="btn btn-info btn-lg" style = "margin-top:2px" id = "potential">潜在用户</button>
    <button type="button" class="btn btn-info btn-lg" style = "margin-top:2px">威果论坛</button>
    <button type="button" class="btn btn-info btn-lg" style = "margin-top:2px">威果商城</button>
    
    <!-- /.头部按钮 -->
    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">
        <!-- User Account: style can be found in dropdown.less -->
        <li class="dropdown user user-menu"> 
        		<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
        			<img src="<%=basePath%>public/toB/dist/img/user_admin.jpg" class="user-image" alt="User Image"> 
        			<span class="hidden-xs">空净厂商A</span> 
        		</a>
          <ul class="dropdown-menu">
            <!-- User image -->
            <li class="user-header"> <img src="<%=basePath%>public/toB/dist/img/user_admin.jpg" class="img-circle" alt="User Image">
              <p><span id = "companyName">空净厂商A </span><small>联系人：<span id = "contactName">猫咪</span></small> <small>联系人手机号：<span id = "TelContent">110</span></small> </p>
            </li>
            <!-- Menu Footer-->
            <li class="user-footer">
              <div class="pull-left"> <a id = "updateDevice" class="btn btn-default btn-flat">账号管理</a> </div>
              <div class="pull-left"> <a href="<%=basePath%>hwmobile/smart/corpbusiness!doChange" class="btn btn-default btn-flat">修改密码</a> </div>
              
              <div class="pull-right"> <a href="<%=basePath%>hwmobile/smart/corpbusiness!doLogin" class="btn btn-default btn-flat">退出登录</a> </div>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>
</header> --%>
<jsp:include page="top.jsp"></jsp:include>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar"> 
  <!-- sidebar: style can be found in sidebar.less -->
  <section class="sidebar"> 
    <!-- search form -->
    <form action="#" method="get" class="sidebar-form">
      <div class="input-group">
        <input type="text" name="q" class="form-control" placeholder="查询[设备标识]">
        <span class="input-group-btn">
        <button type="submit" name="search" id="search-btn" class="btn btn-flat"> <i class="fa fa-search"></i> </button>
        </span> </div>
    </form>
    <!-- /.search form --> 
    <!-- sidebar menu: : style can be found in sidebar.less -->
    <ul class="sidebar-menu" id="navigation">
      <li class="header">设备列表</li>
     
    </ul>
  </section>
  <!-- /.sidebar --> 
</aside>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>首页</h1>
  <ol class="breadcrumb">
    <li> <a href="#"><i class="fa fa-dashboard"></i> 首页</a> </li>
  </ol>
</section>
<!-- Main content -->
<section class="content">
<!-- Small boxes (Stat box) -->
<div class="row">
  <div class="col-lg-3 col-xs-6"> 
    <!-- small box -->
    <div class="small-box bg-aqua" id="user_all">
      <div class="inner">
        <h3>0</h3>
        <p>已发货数</p>
      </div>
      <div class="icon"> <i class="ion ion-bag"></i> </div>
      <a href="#" class="small-box-footer">更多<i class="fa fa-arrow-circle-right"></i></a> </div>
  </div>
  <!-- ./col -->
  <div class="col-lg-3 col-xs-6"> 
    <!-- small box -->
    <div class="small-box bg-green" id="user_out">
      <div class="inner">
        <h3>0 <sup style="font-size: 20px"></sup> </h3>
        <p>需要更换耗材的用户</p>
      </div>
      <div class="icon"> <i class="ion ion-stats-bars"></i> </div>
      <a href="#" class="small-box-footer">更多<i class="fa fa-arrow-circle-right"></i></a> </div>
  </div>
  <!-- ./col -->
  <div class="col-lg-3 col-xs-6"> 
    <!-- small box -->
    <div class="small-box bg-yellow" id="user_focus">
      <div class="inner">
        <h3>0</h3>
        <p>潜在用户</p>
      </div>
      <div class="icon"> <i class="ion ion-person-add"></i> </div>
      <a href="#" class="small-box-footer">更多<i class="fa fa-arrow-circle-right"></i></a> </div>
  </div>
  <!-- ./col -->
  <div class="col-lg-3 col-xs-6"> 
    <!-- small box -->
    <div class="small-box bg-purple" id="user_online">
      <div class="inner">
        <h3>0</h3>
        <p>设备在线的用户</p>
      </div>
      <div class="icon"> <i class="ion ion-pie-graph"></i> </div>
      <a href="#" class="small-box-footer">更多<i class="fa fa-arrow-circle-right"></i></a> </div>
  </div>
  <!-- ./col --> 
</div>
<!-- /.row --> 
<!-- Main row -->
</section>
<div class="row"> 
  <!-- Left col -->
  <section class="col-lg-6 connectedSortable"> 
    <!-- 发货统计 -->
    <div class="nav-tabs-custom "> 
      <!-- Tabs within a box -->
      <ul class="nav nav-tabs pull-right">
        <li class="active"> 
        	 <a href="#year-prov-chart" data-toggle="tab" id = "yearBtn">年</a></li>
        <li> <a href="#mon-prov-chart" data-toggle="tab" id = "monthBtn">月</a> </li>
        <li> <a href="#week-prov-chart" data-toggle="tab" id = "weekBtn">周</a> </li>
        <li class="pull-left header"> <i class="fa fa-inbox"></i>发货统计</li>
      </ul>
      <div class="tab-content no-padding"> 
        <!-- Morris chart - Sales -->
        <font style="margin:50px;">省：</font>
        <div class="chart tab-pane active" id="year-prov-chart" style="position: relative; height: 310px;"></div>
        <div class="chart tab-pane" id="mon-prov-chart" style="position: relative; height: 310px;"></div>
        <div class="chart tab-pane" id="week-prov-chart" style="position: relative; height: 310px;"></div>
      </div>
      <div class="tab-content no-padding"> 
        <!-- Morris chart - Sales -->
        <font style="margin:50px;">市：</font>
        <div class="chart tab-pane active" id="year-city-chart" style="position: relative; height: 310px;"></div>
        <div class="chart tab-pane" id="mon-city-chart" style="position: relative; height: 310px;"></div>
        <div class="chart tab-pane" id="week-city-chart" style="position: relative; height: 310px;"></div>
      </div>
    </div>
    <!-- /.nav-tabs-custom --> 
  </section>
  <!-- left col --> 
  <!-- right col (We are only adding the ID to make the widgets sortable)-->
  <section class="col-lg-6 connectedSortable"> 
    <!-- 耗材更换提醒 -->
    <div class="box box-success" style="position: relative; height: inherit;">
      <div class="box-header with-border">
        <h3 class="box-title">耗材更换提醒</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <table id = "changeDevice" class="table table-hover table-bordered table-striped">
          <thead>
            <tr>
				<th>名称</th>
                <th>设备</th>
                <th>开始</th>
                <th>到期</th>
                <th>状态</th>
                <th></th>
              </tr>
          </thead>
          <tbody>
           
           <c:forEach items="${materialDetailPOList }" var="detail"> 
		        <tr>  
		        	<script></script>
		            <td>${detail.ma008}</td>  
		            <td onMouseOver="javascript:show(this,'phone_show');" onMouseOut="hide(this,'phone_show')">${detail.ma003}</td> 
		            <td><script>dateFormat('${detail.ma004}','yyyy-MM-dd')</script></td>
		            <td><script>dateFormat('${detail.ma005}','yyyy-MM-dd')</script></td> 
		            <td><script>if(${detail.ma006} == 0) document.write("<span style = 'font-weight:bold;color:#F39C12;'>已更换</span>");if(${detail.ma006} == 1) document.write("<span style = 'font-weight:bold;color:green;'>使用中</span>");if(${detail.ma006} == 2) document.write("<span style = 'font-weight:bold;color:red;'>超期</span>");</script></td> 
		            <td><input type="button" class="btn btn-success btn-flat" value="更换" onClick="doUseModify('${detail.ma001}');" /> 
		            <span style = "display: none;" >${detail.ma001} </span></td>
		        </tr> 
		        
		    </c:forEach> 
           
          
           
           
          </tbody>
        </table>
      </div>
      <!-- /.box-body -->
      
    </div>
    <!-- /.box --> 
  </section>
  <!-- right col --> 
</div>
<!-- /.row (main row) --> 
<!-- Main row -->
<div class="row"> 
  <!-- Left col -->
  <section class="col-lg-6 connectedSortable"> 
    <!-- 潜在用户 -->
    <div class="box box-warning" style="position: relative; height: inherit;">
      <div class="box-header with-border">
        <h3 class="box-title">潜在用户</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <table  id = "potentialTable"  class="table table-hover table-bordered table-striped tableList">
          <thead>
            <tr>
                <th>设备标识</th>
                <th>手机号</th>
                <th>收货人</th>
                <th>省</th>
                <th>市</th>
                <th></th>
              </tr>
          </thead>
          <tbody id = "potential_tbody">
           
           <c:forEach items="${corpCrmPOList}" var="crm" begin="1" end="10">  
		        <tr>  
		            <td>${crm.ma002}</td>  
		            <td>${crm.ma005}</td> 
		            <td>${crm.ma004}</td>
		            <td>${crm.ma007}</td>
		            <td>${crm.ma008}</td>
		            
		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-warning btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		        </tr>  
		    </c:forEach> 
         
           
           
          </tbody>
        </table>
         <div class = "pageDiv" id = "pagePotential"></div>
      </div>
      
  </section>
  <!-- left col --> 
  <!-- right col (We are only adding the ID to make the widgets sortable)-->
  <section class="col-lg-6 connectedSortable"> 
    <!-- 已上线用户 -->
    <div class="box" style="position: relative; height: inherit;border-top-color:#605ca8">
      <div class="box-header with-border">
        <h3 class="box-title">已上线用户</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <table id = "onlineTable" class="table table-hover table-bordered table-striped tableList">
          <thead>
            <tr>
                <th>设备标识</th>
                <th>手机号</th>
                <th>注册地址</th>
                <th>注册日期</th>
              </tr>
          </thead>
          <tbody id = "online_tbody">
           
           <c:forEach items="${corpOnlinePOList }" var="u001" begin="1" end="14">  
		        <tr>  
		            <td>${u001.ma018}</td>  
		            <td>${u001.ma006}</td> 
		            <td>${u001.ma016}</td>
		            <td>
		            	<script>dateFormat('${u001.ma007}','yyyy-MM-dd')</script>
		            	<span style = "display: none;" >${u001.ma001} </span>
		            </td>
		            
		        </tr>  
		    </c:forEach> 
           
          </tbody>
        </table>
        <div class = "pageDiv" id = "pageOnline"></div>
      </div>
     
  </section>
  <!-- right col --> 
</div>
<!-- /.row (main row) --> 

<!-- row PM2.5统计 -->
<div class="row"> 
  <!-- col left PM2.5 left-->
  <section class="col-lg-5 connectedSortable">
    <div class="box box-danger" style="position: relative">
     
      <!-- /.box-header -->
      <div class="box-body">
        <div class="row">
          <div class="col-sm-12" > 
            <!-- Progress bars -->
            
                   <div class="nav-tabs-custom "> 
      <!-- Tabs within a box -->
      <ul class="nav nav-tabs pull-right">
        
        <li class="pull-left header"> <i class="fa fa-signal"></i>按省统计室内PM2.5</li>
        <li class="active"> <a href="#pm25_rank_mon" data-toggle="tab" id = "rankMon">月</a> </li>
        <li > <a href="#pm25_rank_week" data-toggle="tab" id = "rankWeek">周</a> </li>
       
      </ul>
      <div class="tab-content no-padding"> 
        <!-- Morris chart - Sales -->
       
        <div class="chart tab-pane active" id="pm25_rank_mon" style="position: relative; height: inherit;margin-top:10px;"></div>
        <div class="chart tab-pane" id="pm25_rank_week" style="position: relative; height: inherit;margin-top:10px;"></div>
       
      </div>
      
    </div>
            
            
          </div>
          <!-- /.col --> 
        </div>
        <!-- /.row --> 
      </div>
      <!-- /.box-body --> 
    </div>
    <!-- /.box --> 
    <!-- box 市统计室内PM2.5 -->
    <div class="box box-danger" style="position: relative">
      
      <!-- /.box-header -->
      <div class="box-body">
        <div class="row">
          <div class="col-sm-12" > 
            <!-- Progress bars -->
            
            
            
       <div class="nav-tabs-custom "> 
      <!-- Tabs within a box -->
      <ul class="nav nav-tabs pull-right">
        
        <li class="pull-left header"> <i class="fa fa-signal"></i>按市统计室内PM2.5</li>
        <li class="active"> <a href="#pm25_rank_mon2" data-toggle="tab" id = "rankMon">月</a> </li>
        <li > <a href="#pm25_rank_week2" data-toggle="tab" id = "rankWeek">周</a> </li>
       
      </ul>
      <div class="tab-content no-padding"> 
        <!-- Morris chart - Sales -->
       
        <div class="chart tab-pane active" id="pm25_rank_mon2" style="position: relative; height: inherit;margin-top:10px;"></div>
        <div class="chart tab-pane" id="pm25_rank_week2" style="position: relative; height: inherit;margin-top:10px;"></div>
       
      </div>
      
    </div>
            
            
          </div>
          <!-- /.col --> 
        </div>
        <!-- /.row --> 
      </div>
      <!-- /.box-body --> 
    </div>
    <!-- /.box 市统计室内PM2.5 --> 
  </section>
  <!-- /.col left PM2.5 left--> 
  
  <!-- col right PM2.5 right-->
  <section class="col-lg-7 connectedSortable">
    <div class="box box-danger" style="position: relative">
     
      <!-- /.box-header -->
      <div class="box-body">
      
      
      
        <div class="nav-tabs-custom "> 
      <!-- Tabs within a box -->
      <ul class="nav nav-tabs pull-right">
        
        <li class="pull-left header"> <i class="fa fa-th-list"></i>用户室内统计PM2.5排名</li>
        <li class="active"> <a href="#pm25_ranklist_day" data-toggle="tab" id = "rankMon">日</a> </li>
        <li > <a href="#pm25_ranklist_week" data-toggle="tab" id = "rankWeek">月</a> </li>
       
      </ul>
      <div class="tab-content no-padding"> 
        <!-- Morris chart - Sales -->
       
        <div class="chart tab-pane active" id="pm25_ranklist_day" style="position: relative; height: inherit;margin-top:10px;">
        
                <table id="userPm25Rank" class="table table-bordered table-hover table-striped tableList">
          <thead>
            <tr>
              <th>发货日期</th>
              <th>联系手机</th>
              <th>联系人</th>
              <th>设备标识</th>
              <th>数据</th>
              <th>添加潜在客户</th>
            </tr>
          </thead>
          <tbody id = "pm25day_tbody">
          
          	<c:forEach items="${rankUserPM }" var="rankPm" begin = "1" end = "10">  
		        <tr>  
		            <td><script>dateFormat('${rankPm.REG_DATE}','yyyy-MM-dd')</script></td>  
		            <td>${rankPm.TEL}</td> 
		            <td>${rankPm.LXR}</td>
		            <td>${rankPm.DEV_ID}</td>
		            <td>${rankPm.DATAVALUE}</td>
		            
		            
		            <td><script>if(${rankPm.FOCUSOR} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${rankPm.ID}\");'>关注</button>");if(${rankPm.FOCUSOR} == 1) document.write("<font>已关注</font>");</script><span style = "display: none;" >${rankPm.ID} </span></td>
		            
		        </tr>  
		    </c:forEach> 
          	
		  </tbody>
        </table>
        <div class = "pageDiv" id = "pagePm25Day"></div>
        
        </div>
        <div class="chart tab-pane" id="pm25_ranklist_week" style="position: relative; height: inherit;margin-top:10px;">
        
        		<table id="userPm25RankMon" class="table table-bordered table-hover table-striped tableList">
          <thead>
            <tr>
              <th>发货日期</th>
              <th>联系手机</th>
              <th>联系人</th>
              <th>设备标识</th>
              <th>数据</th>
              <th>添加潜在客户</th>
            </tr>
          </thead>
          <tbody id = "pm25mon_tbody">
          
          	<c:forEach items="${rankUserPMM}" var="rankPmm" begin="1" end = "10">  
		        <tr>  
		            <td><script>dateFormat('${rankPmm.REG_DATE}','yyyy-MM-dd')</script></td>  
		            <td>${rankPmm.TEL}</td> 
		            <td>${rankPmm.LXR}</td>
		            <td>${rankPmm.DEV_ID}</td>
		            <td>${rankPmm.DATAVALUE}</td>
		            
		            
		            <td><script>if(${rankPmm.FOCUSOR} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${rankPmm.ID}\");'>关注</button>");if(${rankPmm.FOCUSOR} == 1) document.write("<font>已关注</font>");</script><span style = "display: none;" >${rankPmm.ID} </span></td>
		            
		        </tr>  
		    </c:forEach> 
          	
		  </tbody>
        </table>
        <div class = "pageDiv" id = "pagePm25Mon"></div>
        </div>
       
      </div>
      
    </div>
      
      
      
      
      
      
      
      
      
      
      
      
      
      

      </div>
      <!-- /.box-body --> 
    </div>
    <!-- /.box --> 
  </section>
  <!-- /.col left PM2.5 right--> 
</div>
<!-- /.row PM2.5统计 --> 

<!-- row CO2统计 -->
<div class="row"> 
  <!-- col left CO2 left-->
  <section class="col-lg-5 connectedSortable">
    <div class="box box-danger" style="position: relative">
      
      <!-- /.box-header -->
      <div class="box-body">
        <div class="row">
          <div class="col-sm-12" > 
            <!-- Progress bars -->
            
            
            <div class="nav-tabs-custom "> 
      <!-- Tabs within a box -->
      <ul class="nav nav-tabs pull-right">
        
        <li class="pull-left header"> <i class="fa fa-signal"></i>按省统计室内CO2</li>
        <li class="active"> <a href="#co2_rank_mon" data-toggle="tab" id = "rankMon">月</a> </li>
        <li > <a href="#co2_rank_week" data-toggle="tab" id = "rankWeek">周</a> </li>
       
      </ul>
      <div class="tab-content no-padding"> 
        <!-- Morris chart - Sales -->
       
        <div class="chart tab-pane active" id="co2_rank_mon" style="position: relative; height: inherit;margin-top:10px;"></div>
        <div class="chart tab-pane" id="co2_rank_week" style="position: relative; height: inherit;margin-top:10px;"></div>
       
      </div>
      
    </div>
            
            
            
          </div>
          <!-- /.col --> 
        </div>
        <!-- /.row --> 
      </div>
      <!-- /.box-body --> 
    </div>
    <!-- /.box --> 
    <!-- box 市统计室内CO2 -->
    <div class="box box-danger" style="position: relative">
      
      <!-- /.box-header -->
      <div class="box-body">
        <div class="row">
          <div class="col-sm-12" > 
            <!-- Progress bars -->
            
            
             <div class="nav-tabs-custom "> 
      <!-- Tabs within a box -->
      <ul class="nav nav-tabs pull-right">
        
        <li class="pull-left header"> <i class="fa fa-signal"></i>按市统计室内CO2</li>
        <li class="active"> <a href="#co2_rank_mon2" data-toggle="tab" id = "rankMon">月</a> </li>
        <li > <a href="#co2_rank_week2" data-toggle="tab" id = "rankWeek">周</a> </li>
       
      </ul>
      <div class="tab-content no-padding"> 
        <!-- Morris chart - Sales -->
       
        <div class="chart tab-pane active" id="co2_rank_mon2" style="position: relative; height: inherit;margin-top:10px;"></div>
        <div class="chart tab-pane" id="co2_rank_week2" style="position: relative; height: inherit;margin-top:10px;"></div>
       
      </div>
      
    </div>
            
            
            
            
          </div>
          <!-- /.col --> 
        </div>
        <!-- /.row --> 
      </div>
      <!-- /.box-body --> 
    </div>
    <!-- /.box 市统计室内co2 --> 
  </section>
  <!-- /.col left co2 left--> 
  
  <!-- col right co2 right-->
  <section class="col-lg-7 connectedSortable">
    <div class="box box-danger" style="position: relative">
     
      <!-- /.box-header -->
      <div class="box-body">
      
      
      
       <div class="nav-tabs-custom "> 
      <!-- Tabs within a box -->
      <ul class="nav nav-tabs pull-right">
        
        <li class="pull-left header"> <i class="fa fa-th-list"></i>用户室内统计CO2排名</li>
        <li class="active"> <a href="#co2_ranklist_day" data-toggle="tab" id = "rankMon">日</a> </li>
        <li > <a href="#co2_ranklist_week" data-toggle="tab" id = "rankWeek">月</a> </li>
       
      </ul>
      <div class="tab-content no-padding"> 
        <!-- Morris chart - Sales -->
       
        <div class="chart tab-pane active" id="co2_ranklist_day" style="position: relative; height: inherit;margin-top:10px;">
        
                <table id="userCO2Rank" class="table table-bordered table-hover table-striped tableList">
          <thead>
            <tr>
              <th>发货日期</th>
              <th>联系手机</th>
              <th>联系人</th>
              <th>设备标识</th>
              <th>数据</th>
              <th>添加潜在客户</th>
            </tr>
          </thead>
          <tbody id = "co2day_tbody">
          
          	<c:forEach items="${rankUserCo2 }" var="rankCO" begin = "1" end = "10">  
		        <tr>  
		            <td><script>dateFormat('${rankCO.REG_DATE}','yyyy-MM-dd')</script></td>  
		            <td>${rankCO.TEL}</td> 
		            <td>${rankCO.LXR}</td>
		            <td>${rankCO.DEV_ID}</td>
		            <td>${rankCO.DATAVALUE}</td>
		            
		            
		            <td><script>if(${rankCO.FOCUSOR} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${rankPm.ID}\");'>关注</button>");if(${rankCO.FOCUSOR} == 1) document.write("<font>已关注</font>");</script><span style = "display: none;" >${rankCO.ID} </span></td>
		            
		        </tr>  
		    </c:forEach> 
          	
		  </tbody>
        </table>
        <div class = "pageDiv" id = "pageCo2Day"></div>
        
        
        </div>
        <div class="chart tab-pane" id="co2_ranklist_week" style="position: relative; height: inherit;margin-top:10px;">
        
        		 <table id="userCO2RankMon" class="table table-bordered table-hover table-striped tableList">
          <thead>
            <tr>
              <th>发货日期</th>
              <th>联系手机</th>
              <th>联系人</th>
              <th>设备标识</th>
              <th>数据</th>
              <th>添加潜在客户</th>
            </tr>
          </thead>
          <tbody id = "co2mon_tbody">
          
          	<c:forEach items="${rankUserCo2M }" var="rankCO" begin = "1" end = "10">  
		        <tr>  
		            <td><script>dateFormat('${rankCO.REG_DATE}','yyyy-MM-dd')</script></td>  
		            <td>${rankCO.TEL}</td> 
		            <td>${rankCO.LXR}</td>
		            <td>${rankCO.DEV_ID}</td>
		            <td>${rankCO.DATAVALUE}</td>
		            
		            
		            <td><script>if(${rankCO.FOCUSOR} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${rankPm.ID}\");'>关注</button>");if(${rankCO.FOCUSOR} == 1) document.write("<font>已关注</font>");</script><span style = "display: none;" >${rankCO.ID} </span></td>
		            
		        </tr>  
		    </c:forEach> 
          	
		  </tbody>
        </table>
        <div class = "pageDiv" id = "pageCo2Mon"></div>
        </div>
       
      </div>
      
    </div>
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
       
      </div>
      <!-- /.box-body --> 
    </div>
    <!-- /.box --> 
  </section>
  <!-- /.col left co2 right--> 
</div>
<!-- /.row CO2统计 --> 

<!-- row VOC统计 -->
<div class="row"> 
  <!-- col left VOC left-->
  <section class="col-lg-5 connectedSortable">
    <div class="box box-danger" style="position: relative">
      
      <!-- /.box-header -->
      <div class="box-body">
        <div class="row">
          <div class="col-sm-12" > 
            <!-- Progress bars -->
            
            
            
      <div class="nav-tabs-custom "> 
      <!-- Tabs within a box -->
      <ul class="nav nav-tabs pull-right">
        
        <li class="pull-left header"> <i class="fa fa-signal"></i>按省统计室内VOC</li>
        <li class="active"> <a href="#voc_rank_mon" data-toggle="tab" id = "rankMon">月</a> </li>
        <li > <a href="#voc_rank_week" data-toggle="tab" id = "rankWeek">周</a> </li>
       
      </ul>
      <div class="tab-content no-padding"> 
        <!-- Morris chart - Sales -->
       
        <div class="chart tab-pane active" id="voc_rank_mon" style="position: relative; height: inherit;margin-top:10px;"></div>
        <div class="chart tab-pane" id="voc_rank_week" style="position: relative; height: inherit;margin-top:10px;"></div>
       
      </div>
      
    </div>
            
            
            
            
          </div>
          <!-- /.col --> 
        </div>
        <!-- /.row --> 
      </div>
      <!-- /.box-body --> 
    </div>
    <!-- /.box --> 
    <!-- row 市统计室内VOC -->
    <div class="box box-danger" style="position: relative">
     
      <!-- /.box-header -->
      <div class="box-body">
        <div class="row">
          <div class="col-sm-12" > 
           
            
            
              <div class="nav-tabs-custom "> 
      <!-- Tabs within a box -->
      <ul class="nav nav-tabs pull-right">
        
        <li class="pull-left header"> <i class="fa fa-signal"></i>按市统计室内VOC</li>
        <li class="active"> <a href="#voc_rank_mon2" data-toggle="tab" id = "rankMon">月</a> </li>
        <li > <a href="#voc_rank_week2" data-toggle="tab" id = "rankWeek">周</a> </li>
       
      </ul>
      <div class="tab-content no-padding"> 
        <!-- Morris chart - Sales -->
       
        <div class="chart tab-pane active" id="voc_rank_mon2" style="position: relative; height: inherit;margin-top:10px;"></div>
        <div class="chart tab-pane" id="voc_rank_week2" style="position: relative; height: inherit;margin-top:10px;"></div>
       
      </div>
      
    </div>
            
            
            
          </div>
          <!-- /.col --> 
        </div>
        <!-- /.row --> 
      </div>
      <!-- /.box-body --> 
    </div>
    <!-- /.box 市统计室内VOC --> 
  </section>
  <!-- /.col left VOC left--> 
  
  <!-- col right VOC right-->
  <section class="col-lg-7 connectedSortable">
    <div class="box box-danger" style="position: relative">
      
      <!-- /.box-header -->
      <div class="box-body" >
      
      
      
       <div class="nav-tabs-custom "> 
      <!-- Tabs within a box -->
      <ul class="nav nav-tabs pull-right">
        
        <li class="pull-left header"> <i class="fa fa-th-list"></i>用户室内统计VOC排名</li>
        <li class="active"> <a href="#voc_ranklist_day" data-toggle="tab" id = "rankMon">日</a> </li>
        <li > <a href="#voc_ranklist_week" data-toggle="tab" id = "rankWeek">月</a> </li>
       
      </ul>
      <div class="tab-content no-padding"> 
        <!-- Morris chart - Sales -->
       
        <div class="chart tab-pane active" id="voc_ranklist_day" style="position: relative; height: inherit;margin-top:10px;">
        
        <table id="userVOCRank" class="table table-bordered table-hover table-striped tableList">
          <thead>
            <tr>
              <th>发货日期</th>
              <th>联系手机</th>
              <th>联系人</th>
              <th>设备标识</th>
              <th>数据</th>
              <th>添加潜在客户</th>
            </tr>
          </thead>
          <tbody id = "vocday_tbody">
          
          <c:forEach items="${rankUserVoc }" var="rankVoc" begin = "1" end = "10">  
		        <tr>  
		            <td><script>dateFormat('${rankVoc.REG_DATE}','yyyy-MM-dd')</script></td>  
		            <td>${rankVoc.TEL}</td> 
		            <td>${rankVoc.LXR}</td>
		            <td>${rankVoc.DEV_ID}</td>
		            <td>${rankVoc.DATAVALUE}</td>
		            
		            
		            <td><script>if(${rankVoc.FOCUSOR} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${rankVoc.ID}\");'>关注</button>");if(${rankVoc.FOCUSOR} == 1) document.write("<font>已关注</font>");</script><span style = "display: none;" >${rankVoc.ID} </span></td>
		            
		        </tr>  
		    </c:forEach> 
          
          
          </tbody>
        </table>
       <div class = "pageDiv" id = "pageVocDay"></div>
        
        
        </div>
        <div class="chart tab-pane" id="voc_ranklist_week" style="position: relative; height: inherit;margin-top:10px;">
        
        		 <table id="userVOCRankMon" class="table table-bordered table-hover table-striped tableList">
          <thead>
            <tr>
              <th>发货日期</th>
              <th>联系手机</th>
              <th>联系人</th>
              <th>设备标识</th>
              <th>数据</th>
              <th>添加潜在客户</th>
            </tr>
          </thead>
          <tbody id = "vocmon_tbody">
          
          <c:forEach items="${rankUserVocM }" var="rankVoc" begin = "1" end = "10">  
		        <tr>  
		            <td><script>dateFormat('${rankVoc.REG_DATE}','yyyy-MM-dd')</script></td>  
		            <td>${rankVoc.TEL}</td> 
		            <td>${rankVoc.LXR}</td>
		            <td>${rankVoc.DEV_ID}</td>
		            <td>${rankVoc.DATAVALUE}</td>
		            
		            
		            <td><script>if(${rankVoc.FOCUSOR} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${rankVoc.ID}\");'>关注</button>");if(${rankVoc.FOCUSOR} == 1) document.write("<font>已关注</font>");</script><span style = "display: none;" >${rankVoc.ID} </span></td>
		            
		        </tr>  
		    </c:forEach> 
          
          
          </tbody>
        </table>
        <div class = "pageDiv" id = "pageVocMon"></div>
        </div>
       
      </div>
      
    </div>
      
      
      
      
      
      
      
      
      
      
      
      
      
        
      </div>
      <!-- /.box-body --> 
    </div>
    <!-- /.box --> 
  </section>
  <!-- /.col left VOC right--> 
</div>
<!-- /.row VOC统计 --> 

<!-- 空气电台所在的城市天气和污染地图 -->
<div class="nav-tabs-custom" style="height:800;"> 
  <!-- Tabs within a box -->
  <ul class="nav nav-tabs pull-right" id = "table_city">
  <li class="pull-left header"> <i class="fa fa-inbox"></i>污染地图</li>
    
    
  </ul>
  <div class="tab-content no-padding" style="position: relative; height: 720;" id = "tableContent_city">
    
  </div>
  <!-- /.tab-content>

          </div> <!-- /.nav-tabs-custom 空气电台所在的城市天气和污染地图 --> 
  
  <!-- /.content --> 
</div>
<!-- /.content-wrapper -->
</div>
<footer class="main-footer ">
  <div class="pull-right hidden-xs "> <b>Version</b> 2.0 </div>
  <strong>Copyright © 2014-2015 <a href="http://www.airradio.cn/ ">北京威果智能科技有限公司</a>.</strong> All rights reserved. </footer>
</div>
<!-- ./wrapper --> 

<!-- jquery-ui-1.11.2.min.js --> 
<script src="<%=basePath%>public/toB/plugins/jQueryUI/jquery-ui-1.11.2.min.js" type="text/javascript"></script> 
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip --> 
<script>
$.widget.bridge('uibutton', $.ui.button);    
</script> 

<!-- 汉字转换拼音 -->
<script src="<%=basePath%>public/toB/dist/js/transformPY.js" type="text/javascript"></script> 


<!-- Bootstrap 3.3.2 JS --> 
<script src="<%=basePath%>public/toB/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
<!-- Morris.js charts-->
<script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>   
<script src="<%=basePath%>public/toB/plugins/morris/morris.min.js" type="text/javascript"></script> 
<!-- Sparkline
<script src="plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>  --> 
<!-- jvectormap 
<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script> 
<script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script> --> 
<!-- jQuery Knob Chart --> 
<script src="<%=basePath%>public/toB/plugins/knob/jquery.knob.js" type="text/javascript"></script> 
<!-- daterangepicker 
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js" type="text/javascript"></script>
    <script src="plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>--> 
<!-- datepicker 
    <script src="plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>--> 
<!-- Bootstrap WYSIHTML5 
<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script> --> 
<!-- Slimscroll --> 
<script src="<%=basePath%>public/toB/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script> 
<!-- FastClick --> 
<script src='<%=basePath%>public/toB/plugins/fastclick/fastclick.min.js'></script> 
<!-- AdminLTE App --> 
<script src="<%=basePath%>public/toB/dist/js/app.js" type="text/javascript"></script> 

<!-- AdminLTE dashboard demo (This is only for demo purposes)
    <script src="dist/js/pages/dashboard.js" type="text/javascript"></script>    --> 

<!-- AdminLTE for demo purposes 
    <script src="dist/js/demo.js" type="text/javascript"></script>--> 

<!-- DATA TABES SCRIPT 曾凡 20150626 污染排名--> 

<script src="<%=basePath%>public/toB/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script> 

<!-- DATA TABES SCRIPT 曾凡 20150626 污染排名--> 

<script src="<%=basePath%>public/toB/plugins/datatables/dataTables.bootstrap.min.js" type="text/javascript"></script> 



<!-- 分页js -->
<script src="<%=basePath%>public/toB/dist/js/jquery.page.js" type="text/javascript"></script>



<script> 
function show(obj,id) { 
var objDiv = $("#"+id+""); 
$(objDiv).css("display","block"); 
$(objDiv).css("left", event.clientX); 
$(objDiv).css("top", event.clientY + 10); 
//alert(obj.innerText);

$.post('<%=basePath%>hwmobile/smart/corpcrm!getCrmInfoByCorpId',
		{
			ACCOUNT : localStorage.getItem('PHONE',''),
			SESSIONID : localStorage.getItem('PASSWORD',''),
			DEVICE :  obj.innerText


		},
			function (data,status) //回传函数
				{
					    	  
					//alert(data.code);
					if(data.code == 1){
					    	 		
						
						$("#crm_name_modal").html(data.dataObject[0].ma004);
						$("#crm_tel_modal").html(data.dataObject[0].ma005);
						$("#crm_addr_modal").html(data.dataObject[0].ma006);
						$("#loading").hide();
						$("#show_content").show(); 		
					 	
						}
				}
			);


} 
function hide(obj,id) { 
var objDiv = $("#"+id+""); 
$(objDiv).css("display", "none"); 
$("#loading").show();
} 
</script> 

<script>
<%-- $(function(){
	$(".modal_phone").hover(
			  function () {
				  var p = $(this);
				  var position = p.position();

	
						//$(document).mousemove(function (e) {
							
							
							$("#phone_show").css({ left: position.left, top: position.top });
							
							
						//});
						
							$(".modal_phone").mouseover(function(){
								  
								
								
								});
						
						
						
						
						$.post('<%=basePath%>hwmobile/smart/corpcrm!getCrmInfoByCorpId',
								{
									ACCOUNT : localStorage.getItem('PHONE',''),
									SESSIONID : localStorage.getItem('PASSWORD',''),
									DEVICE :  $(this).html()


								},
									function (data,status) //回传函数
										{
											    	  
											//alert(data.code);
											if(data.code == 1){
											    	 		
												
												$("#crm_name_modal").html(data.dataObject[0].ma004);
												$("#crm_tel_modal").html(data.dataObject[0].ma005);
												$("#crm_addr_modal").html(data.dataObject[0].ma006);
												$("#phone_show").show(); 		
											 	
												}
										}
									);
						
						
						
				  },
			function () {
					  $("#phone_show").hide();
				  }
				);
}); --%>
</script>
<script>
//计算两个整数的百分比值 
function GetPercent(num, total) { 
num = parseFloat(num); 
total = parseFloat(total); 
if (isNaN(num) || isNaN(total)) { 
return "-"; 
} 
return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00 + "%"); 
} 
var _page = 1;
var _page2 = 1;
var _pageRankPm25Day = 1;
var _pageRankPm25Mon = 1;
var _pageRankCo2Day = 1;
var _pageRankCo2Mon = 1;
var _pageRankVocDay = 1;
var _pageRankVocMon = 1;
var _count_crm = 0;
var _count_online = 0;
var _count_rank_pm25_day = 0;
var _count_rank_pm25_mon = 0;
var _count_rank_co2_day = 0;
var _count_rank_co2_mon = 0;
var _count_rank_voc_day = 0;
var _count_rank_voc_mon = 0;
$(function () {
			
	pageChange();
	pageChange_online();
	pageChange_pm25Day();
	pageChange_pm25Mon();
	
	
	pageChange_co2Day();
	pageChange_co2Mon();
	
	pageChange_vocDay();
	pageChange_vocMon();
	
		var _year_prov_pie = new Array();
		var	_year_city_pie = new Array();
		var _mon_prov_pie = new Array();
		var _mon_city_pie = new Array();
		var _week_prov_pie = new Array();
		var _week_city_pie = new Array();
		
		
		$("#yearBtn").click(function(){
			
			
			
			$("#mon-city-chart").removeClass("active");
			$("#week-city-chart").removeClass("active");
			$("#year-city-chart").removeClass("active");
			$("#year-city-chart").addClass("active");
			
		});
		$("#monthBtn").click(function(){
			
			
			
			$("#mon-city-chart").removeClass("active");
			$("#week-city-chart").removeClass("active");
			$("#year-city-chart").removeClass("active");
			$("#mon-city-chart").addClass("active");
			
		});
		$("#weekBtn").click(function(){
	
	
	
			$("#mon-city-chart").removeClass("active");
			$("#week-city-chart").removeClass("active");
			$("#year-city-chart").removeClass("active");
			$("#week-city-chart").addClass("active");
			
		});
			
							 
			//获得发货统计的年--省	
			$.post('<%=basePath%>hwmobile/smart/corpcrm!doDeviceCountByGeo',
				{
					ACCOUNT : localStorage.getItem('PHONE',''),
					SESSIONID : localStorage.getItem('PASSWORD',''),
					GEOTYPE : 0,//0 province 1 city
					DATETYPE : 0 //0 year  1 month 2 week


				},
					function (data,status) //回传函数
						{
							    	  
							//alert(data.code);
							if(data.count > 0){
							    	 		
							for(var i = 0; i < data.dataObject.length; i++){
							   var prov_pie = new Object();
							   prov_pie.label = data.dataObject[i].GEO;
							   prov_pie.value = data.dataObject[i].TOTAL;
							   _year_prov_pie.push(prov_pie);
							  }
							    	 		
							    	 		
							 	var donut = new Morris.Donut({
								 element: 'year-prov-chart',
								resize: true,
								colors: ["#320033", "#FE0094", "#CC3402","#CA6701","#993501","#FBFE01","#316500", "#97CC00", "#FD9A32","#000032","#D02F97","#D12F2D","#629C2E", "#9FA05E", "#9F5CA1","#350099","#D26261","#8D2001","#6700FF", "#D066FE", "#312F9D","#95CF61","#019599","#9A31FF","#00CACA", "#2FCE67", "#CAFE99","#0030CB","#9493CF","#FECCCD","#01FAFE", "#67FFCE", "#99FF9B","#3532FF","#0096FF","#CAFCFD"],
								data: _year_prov_pie,
								hideHover: 'auto'
								});
						}
							else{
								$("#year-prov-chart").html('<div style = "position:relative;left:200px;width:300px;height:300px;border-radius:300px;border:40px solid #DFDFDF;"><font style = "position:relative;left:85px;top:70px;" size = "11px;">0</font></div>');
								
							}
					});
			
			//获得发货统计的年--市	
			$.post('<%=basePath%>hwmobile/smart/corpcrm!doDeviceCountByGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						GEOTYPE : 1,//0 province 1 city
						DATETYPE : 0 //0 year  1 month 2 week


					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.count > 0){
								    	 		
								for(var i = 0; i < data.dataObject.length; i++){
								   var prov_pie = new Object();
								   prov_pie.label = data.dataObject[i].GEO;
								   prov_pie.value = data.dataObject[i].TOTAL;
								   _year_city_pie.push(prov_pie);
								  }
								    	 		
								    	 		
								 	var donut2 = new Morris.Donut({
									 element: 'year-city-chart',
									resize: true,
									colors: ["#320033", "#FE0094", "#CC3402","#CA6701","#993501","#FBFE01","#316500", "#97CC00", "#FD9A32","#000032","#D02F97","#D12F2D","#629C2E", "#9FA05E", "#9F5CA1","#350099","#D26261","#8D2001","#6700FF", "#D066FE", "#312F9D","#95CF61","#019599","#9A31FF","#00CACA", "#2FCE67", "#CAFE99","#0030CB","#9493CF","#FECCCD","#01FAFE", "#67FFCE", "#99FF9B","#3532FF","#0096FF","#CAFCFD"],
									data: _year_city_pie,
									hideHover: 'auto'
									});
							}
								else{
									$("#year-city-chart").html('<div style = "position:relative;left:200px;width:300px;height:300px;border-radius:300px;border:40px solid #DFDFDF;"><font style = "position:relative;left:85px;top:70px;" size = "11px;">0</font></div>');
									
								}
						}); 
			
			//获得发货统计的月--省	
			$.post('<%=basePath%>hwmobile/smart/corpcrm!doDeviceCountByGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						GEOTYPE : 0,//0 province 1 city
						DATETYPE : 1 //0 year  1 month 2 week


					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.count > 0){
								    	 		
								for(var i = 0; i < data.dataObject.length; i++){
								   var prov_pie = new Object();
								   prov_pie.label = data.dataObject[i].GEO;
								   prov_pie.value = data.dataObject[i].TOTAL;
								   _mon_prov_pie.push(prov_pie);
								  }
								    	 		
								    	 		
								 	var donut3 = new Morris.Donut({
									 element: 'mon-prov-chart',
									resize: false,
									colors: ["#320033", "#FE0094", "#CC3402","#CA6701","#993501","#FBFE01","#316500", "#97CC00", "#FD9A32","#000032","#D02F97","#D12F2D","#629C2E", "#9FA05E", "#9F5CA1","#350099","#D26261","#8D2001","#6700FF", "#D066FE", "#312F9D","#95CF61","#019599","#9A31FF","#00CACA", "#2FCE67", "#CAFE99","#0030CB","#9493CF","#FECCCD","#01FAFE", "#67FFCE", "#99FF9B","#3532FF","#0096FF","#CAFCFD"],
									data: _mon_prov_pie,
									hideHover: 'auto'
									});
							}
								else{
									$("#mon-prov-chart").html('<div style = "position:relative;left:200px;width:300px;height:300px;border-radius:300px;border:40px solid #DFDFDF;"><font style = "position:relative;left:85px;top:70px;" size = "11px;">0</font></div>');
									
								}
						});
			
			//获得发货统计的月--市	
			$.post('<%=basePath%>hwmobile/smart/corpcrm!doDeviceCountByGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						GEOTYPE : 1,//0 province 1 city
						DATETYPE : 1 //0 year  1 month 2 week


					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.count > 0){
								    	 		
								for(var i = 0; i < data.dataObject.length; i++){
								   var prov_pie = new Object();
								   prov_pie.label = data.dataObject[i].GEO;
								   prov_pie.value = data.dataObject[i].TOTAL;
								   _mon_city_pie.push(prov_pie);
								  }
								    	 		
								    	 		
								 	var donut4 = new Morris.Donut({
									 element: 'mon-city-chart',
									resize: false,
									colors: ["#320033", "#FE0094", "#CC3402","#CA6701","#993501","#FBFE01","#316500", "#97CC00", "#FD9A32","#000032","#D02F97","#D12F2D","#629C2E", "#9FA05E", "#9F5CA1","#350099","#D26261","#8D2001","#6700FF", "#D066FE", "#312F9D","#95CF61","#019599","#9A31FF","#00CACA", "#2FCE67", "#CAFE99","#0030CB","#9493CF","#FECCCD","#01FAFE", "#67FFCE", "#99FF9B","#3532FF","#0096FF","#CAFCFD"],
									data: _mon_city_pie,
									hideHover: 'auto'
									});
							}
								else{
									$("#mon-city-chart").html('<div style = "position:relative;left:200px;width:300px;height:300px;border-radius:300px;border:40px solid #DFDFDF;"><font style = "position:relative;left:85px;top:70px;" size = "11px;">0</font></div>');
									
								}
						}); 
					 
					 
			//获得发货统计的周--省	
			$.post('<%=basePath%>hwmobile/smart/corpcrm!doDeviceCountByGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						GEOTYPE : 0,//0 province 1 city
						DATETYPE : 2 //0 year  1 month 2 week


					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.count > 0){
								    	 		
								for(var i = 0; i < data.dataObject.length; i++){
								   var prov_pie = new Object();
								   prov_pie.label = data.dataObject[i].GEO;
								   prov_pie.value = data.dataObject[i].TOTAL;
								   _week_prov_pie.push(prov_pie);
								  }
								    	 		
								    	 		
								 	var donut5 = new Morris.Donut({
									 element: 'week-prov-chart',
									resize: true,
									colors: ["#320033", "#FE0094", "#CC3402","#CA6701","#993501","#FBFE01","#316500", "#97CC00", "#FD9A32","#000032","#D02F97","#D12F2D","#629C2E", "#9FA05E", "#9F5CA1","#350099","#D26261","#8D2001","#6700FF", "#D066FE", "#312F9D","#95CF61","#019599","#9A31FF","#00CACA", "#2FCE67", "#CAFE99","#0030CB","#9493CF","#FECCCD","#01FAFE", "#67FFCE", "#99FF9B","#3532FF","#0096FF","#CAFCFD"],
									data: _week_prov_pie,
									hideHover: 'auto'
									});
							}
								else{
									$("#week-prov-chart").html('<div style = "position:relative;left:200px;width:300px;height:300px;border-radius:300px;border:40px solid #DFDFDF;"><font style = "position:relative;left:85px;top:70px;" size = "11px;">0</font></div>');
									
								}
						});
			
		//获得发货统计的周--市	
			$.post('<%=basePath%>hwmobile/smart/corpcrm!doDeviceCountByGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						GEOTYPE : 1,//0 province 1 city
						DATETYPE : 2 //0 year  1 month 2 week


					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.count > 0){
								    	 		
								for(var i = 0; i < data.dataObject.length; i++){
								   var prov_pie = new Object();
								   prov_pie.label = data.dataObject[i].GEO;
								   prov_pie.value = data.dataObject[i].TOTAL;
								   _week_city_pie.push(prov_pie);
								  }
								    	 		
								    	 		
								 	var donut6 = new Morris.Donut({
									 element: 'week-city-chart',
									resize: true,
									colors: ["#320033", "#FE0094", "#CC3402","#CA6701","#993501","#FBFE01","#316500", "#97CC00", "#FD9A32","#000032","#D02F97","#D12F2D","#629C2E", "#9FA05E", "#9F5CA1","#350099","#D26261","#8D2001","#6700FF", "#D066FE", "#312F9D","#95CF61","#019599","#9A31FF","#00CACA", "#2FCE67", "#CAFE99","#0030CB","#9493CF","#FECCCD","#01FAFE", "#67FFCE", "#99FF9B","#3532FF","#0096FF","#CAFCFD"],
									data: _week_city_pie,
									hideHover: 'auto'
									});
							}
								else{
									$("#week-city-chart").html('<div style = "position:relative;left:200px;width:300px;height:300px;border-radius:300px;border:40px solid #DFDFDF;"><font style = "position:relative;left:85px;top:70px;" size = "11px;">0</font></div>');
									
								}
						}); 
					
			
		//获得VOC 省 周排名
		
		$.post('<%=basePath%>hwmobile/smart/corpmain!rankWithGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						TYPE1 : 1,//1 province 2 city
						TYPE2 : 1,//  1 week   2 month
						SENSOR : "D9"  //voc

					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.code == 1){
								    	 	
									$("#voc_rank_week").html("");
									for(var i = 0; i < data.dataObject.length; i++){
										
										var x = GetPercent(data.dataObject[i].MA003,100);
										if(data.dataObject[i].MA003 <= 20 )
											$("#voc_rank_week").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-success" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 > 20 && data.dataObject[i].MA003 < 40)
											$("#voc_rank_week").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-warning" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 >= 40)
											$("#voc_rank_week").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-danger" style="width: '+x+';"></div></div>');
											
										
										
										
										  }
								
								}
						}); 
		
		
//获得VOC 省 月排名
		
		$.post('<%=basePath%>hwmobile/smart/corpmain!rankWithGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						TYPE1 : 1,//1 province 2 city
						TYPE2 : 2,//  1 week   2 month
						SENSOR : "D9"  //voc

					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.code == 1){
								    	 	
									$("#voc_rank_mon").html("");
									for(var i = 0; i < data.dataObject.length; i++){
										
										var x = GetPercent(data.dataObject[i].MA003,100);
										if(data.dataObject[i].MA003 <= 20 )
											$("#voc_rank_mon").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-success" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 > 20 && data.dataObject[i].MA003 < 40)
											$("#voc_rank_mon").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-warning" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 >= 40)
											$("#voc_rank_mon").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-danger" style="width: '+x+';"></div></div>');
											
										
										
										  }
								
								}
						}); 
					 
					
//获得VOC 市 周排名
		
		$.post('<%=basePath%>hwmobile/smart/corpmain!rankWithGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						TYPE1 : 2,//1 province 2 city
						TYPE2 : 1,//  1 week   2 month
						SENSOR : "D9"  //voc

					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.code == 1){
								    	 	
									$("#voc_rank_week2").html("");
									for(var i = 0; i < data.dataObject.length; i++){
										
										var x = GetPercent(data.dataObject[i].MA003,100);
										if(data.dataObject[i].MA003 <= 20 )
											$("#voc_rank_week2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-success" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 > 20 && data.dataObject[i].MA003 < 40)
											$("#voc_rank_week2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-warning" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 >= 40)
											$("#voc_rank_week2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-danger" style="width: '+x+';"></div></div>');
											
										
										
										  }
								
								}
						}); 	 
					 
					 
					 
//获得VOC 市 月排名
		
		$.post('<%=basePath%>hwmobile/smart/corpmain!rankWithGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						TYPE1 : 2,//1 province 2 city
						TYPE2 : 2,//  1 week   2 month
						SENSOR : "D9"  //voc

					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.code == 1){
								    	 	
									$("#voc_rank_mon2").html("");
									for(var i = 0; i < data.dataObject.length; i++){
										
										var x = GetPercent(data.dataObject[i].MA003,100);
										if(data.dataObject[i].MA003 <= 20 )
											$("#voc_rank_mon2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-success" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 > 20 && data.dataObject[i].MA003 < 40)
											$("#voc_rank_mon2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-warning" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 >= 40)
											$("#voc_rank_mon2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-danger" style="width: '+x+';"></div></div>');
											
										
										
										  }
								
								}
						}); 	 
					 
					 
					 
//获得CO2 省 周排名
		
		$.post('<%=basePath%>hwmobile/smart/corpmain!rankWithGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						TYPE1 : 1,//1 province 2 city
						TYPE2 : 1,//  1 week   2 month
						SENSOR : "30"  //voc

					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.code == 1){
								    	 	
									$("#co2_rank_week").html("");
									for(var i = 0; i < data.dataObject.length; i++){
										
										var x = GetPercent(data.dataObject[i].MA003,2000);
										if(data.dataObject[i].MA003 <= 800 )
											$("#co2_rank_week").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-success" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 > 800 && data.dataObject[i].MA003 < 1200)
											$("#co2_rank_week").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-warning" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 >= 1200)
											$("#co2_rank_week").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-danger" style="width: '+x+';"></div></div>');
											
										
										
										  }
								
								}
						}); 
						
//获得CO2 省 月排名
		
		$.post('<%=basePath%>hwmobile/smart/corpmain!rankWithGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						TYPE1 : 1,//1 province 2 city
						TYPE2 : 2,//  1 week   2 month
						SENSOR : "30"  //voc

					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.code == 1){
								    	 	
									$("#co2_rank_mon").html("");
									for(var i = 0; i < data.dataObject.length; i++){
										
										var x = GetPercent(data.dataObject[i].MA003,2000);
										if(data.dataObject[i].MA003 <= 800 )
											$("#co2_rank_mon").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-success" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 > 800 && data.dataObject[i].MA003 < 1200)
											$("#co2_rank_mon").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-warning" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 >= 1200)
											$("#co2_rank_mon").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-danger" style="width: '+x+';"></div></div>');
											
										
										
										  }
								
								}
						}); 
					 
					
//获得CO2 市 周排名
		
		$.post('<%=basePath%>hwmobile/smart/corpmain!rankWithGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						TYPE1 : 2,//1 province 2 city
						TYPE2 : 1,//  1 week   2 month
						SENSOR : "30"  //co2

					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.code == 1){
								    	 	
									$("#co2_rank_week2").html("");
									for(var i = 0; i < data.dataObject.length; i++){
										
										var x = GetPercent(data.dataObject[i].MA003,2000);
										if(data.dataObject[i].MA003 <= 800 )
											$("#co2_rank_week2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-success" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 > 800 && data.dataObject[i].MA003 < 1200)
											$("#co2_rank_week2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-warning" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 >= 1200)
											$("#co2_rank_week2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-danger" style="width: '+x+';"></div></div>');
											
										
										
										  }
								
								}
						}); 	 
					 
//获得CO2 市 月排名
		
		$.post('<%=basePath%>hwmobile/smart/corpmain!rankWithGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						TYPE1 : 2,//1 province 2 city
						TYPE2 : 2,//  1 week   2 month
						SENSOR : "30"  //co2

					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.code == 1){
								    	 	
									$("#co2_rank_mon2").html("");
									for(var i = 0; i < data.dataObject.length; i++){
										
										var x = GetPercent(data.dataObject[i].MA003,2000);
										if(data.dataObject[i].MA003 <= 800 )
											$("#co2_rank_mon2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-success" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 > 800 && data.dataObject[i].MA003 < 1200)
											$("#co2_rank_mon2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-warning" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 >= 1200)
											$("#co2_rank_mon2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-danger" style="width: '+x+';"></div></div>');
											
										
										
										  }
								
								}
						}); 
					 
					 
					 
//获得PM25 省 月排名
		
		$.post('<%=basePath%>hwmobile/smart/corpmain!rankWithGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						TYPE1 : 1,//1 province 2 city
						TYPE2 : 2,//  1 week   2 month
						SENSOR : "D8"  //pm25

					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.code == 1){
								    	 	
									$("#pm25_rank_mon").html("");
									for(var i = 0; i < data.dataObject.length; i++){
										
										var x = GetPercent(data.dataObject[i].MA003,1000);
										if(data.dataObject[i].MA003 <= 100)
										$("#pm25_rank_mon").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-success" style="width: '+x+';"></div></div>');
										if(data.dataObject[i].MA003 > 100 && data.dataObject[i].MA003 <200)
										$("#pm25_rank_mon").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-warning" style="width: '+x+';"></div></div>');
										if(data.dataObject[i].MA003 >= 200)
										$("#pm25_rank_mon").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-danger" style="width: '+x+';"></div></div>');
										
										  }
								
								}
						}); 
						
//获得PM25 省 周排名
		
		$.post('<%=basePath%>hwmobile/smart/corpmain!rankWithGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						TYPE1 : 1,//1 province 2 city
						TYPE2 : 1,//  1 week   2 month
						SENSOR : "D8"  //pm25

					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.code == 1){
								    	 	
									$("#pm25_rank_week").html("");
									for(var i = 0; i < data.dataObject.length; i++){
										
										var x = GetPercent(data.dataObject[i].MA003,1000);
										if(data.dataObject[i].MA003 <= 100)
											$("#pm25_rank_week").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-success" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 > 100 && data.dataObject[i].MA003 <200)
											$("#pm25_rank_week").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-warning" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 >= 200)
											$("#pm25_rank_week").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-danger" style="width: '+x+';"></div></div>');
											
										
										
										  }
								
								}
						}); 
					 
					
//获得PM25 市 周排名
		
		$.post('<%=basePath%>hwmobile/smart/corpmain!rankWithGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						TYPE1 : 2,//1 province 2 city
						TYPE2 : 1,//  1 week   2 month
						SENSOR : "D8"  //voc

					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.code == 1){
								    	 	
									$("#pm25_rank_week2").html("");
									for(var i = 0; i < data.dataObject.length; i++){
										
										var x = GetPercent(data.dataObject[i].MA003,1000);
										if(data.dataObject[i].MA003 <= 100)
											$("#pm25_rank_week2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-success" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 > 100 && data.dataObject[i].MA003 <200)
											$("#pm25_rank_week2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-warning" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 >= 200)
											$("#pm25_rank_week2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-danger" style="width: '+x+';"></div></div>');
											
										
										
										  }
								
								}
						}); 	
//获得PM25 市 月排名
		
		$.post('<%=basePath%>hwmobile/smart/corpmain!rankWithGeo',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD',''),
						TYPE1 : 2,//1 province 2 city
						TYPE2 : 2,//  1 week   2 month
						SENSOR : "D8"  //pm25

					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.code == 1){
								    	 	
									$("#pm25_rank_mon2").html("");
									for(var i = 0; i < data.dataObject.length; i++){
										
										var x = GetPercent(data.dataObject[i].MA003,1000);
										if(data.dataObject[i].MA003 <= 100)
											$("#pm25_rank_mon2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-success" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 > 100 && data.dataObject[i].MA003 <200)
											$("#pm25_rank_mon2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-warning" style="width: '+x+';"></div></div>');
											if(data.dataObject[i].MA003 >= 200)
											$("#pm25_rank_mon2").append(' <div class="clearfix"> <span class="pull-left">'+data.dataObject[i].MA005+'</span> <small class="pull-right">'+data.dataObject[i].MA003+'</small> </div><div class="progress xs"><div class="progress-bar btn-danger" style="width: '+x+';"></div></div>');
											
										
										
										  }
								
								}
						}); 	
					 			 
		

//更新污染地图的表头

$.post('<%=basePath%>hwmobile/smart/corpmain!cityInRank',
					{
						ACCOUNT : localStorage.getItem('PHONE',''),
						SESSIONID : localStorage.getItem('PASSWORD','')
						

					},
						function (data,status) //回传函数
							{
								    	  
								//alert(data.code);
								if(data.code == 1){
								    	 	
									
									for(var i = 0; i < data.dataObject.length; i++){
										
										var pinyin = codefans_net_CC2PY(data.dataObject[i].CITY);
										
										if(i==0){
										$("#table_city").append('<li class = "active"> <a href="#'+pinyin+'" data-toggle="tab">'+data.dataObject[i].CITY+'</a> </li>');
										$("#tableContent_city").append('<div id="'+pinyin+'" class="tab-pane active"><iframe id="rightmain=" src="http://aqicn.org/city/'+pinyin+'" frameborder="false" scrolling="no" style="border:none; " width="100%" height="720" allowtransparency="true "></iframe></div>');
										
										}else{
											$("#table_city").append('<li> <a href="#'+pinyin+'" data-toggle="tab">'+data.dataObject[i].CITY+'</a> </li>');
											$("#tableContent_city").append('<div id="'+pinyin+'" class="tab-pane"><iframe id="rightmain=" src="http://aqicn.org/city/'+pinyin+'" frameborder="false" scrolling="no" style="border:none; " width="100%" height="720" allowtransparency="true "></iframe></div>');
											
										}
										
										
									}
								
								}
						}); 	 


//空气净化厂商信息加载
<%-- $.post(
	      '<%=basePath%>hwmobile/smart/corpbusiness!doCorpLogin',
	      {
	    	  ACCOUNT : localStorage.getItem('PHONE',''),
			SESSIONID : localStorage.getItem('PASSWORD','')
	      },
	      function (data,status) //回传函数
	      {
	    	  
	    	 	//alert(data);
	    	 	
	    	  	if(data.code == 1)
	              {
				    	
	    	  		$("#companyName").html(data.dataObject.);
	    	  		$("#contactName").html();
	    	  		$("#TelContent").html();
	    	  		
	    	  		
	    	  		
				    	 
	              }
	    	  	
	      }); --%>
	      
	      
	  /*    $('#userCO2RankMon').dataTable({
			  "bPaginate": true,
			  "bLengthChange": false,
			  "bFilter": false,
			  "bSort": true,
			  "bInfo": true,
			  "bAutoWidth": false
			});
	      
	      $('#userCO2Rank').dataTable({
			  "bPaginate": true,
			  "bLengthChange": false,
			  "bFilter": false,
			  "bSort": true,
			  "bInfo": true,
			  "bAutoWidth": false
			});
	      
	      */
	      
	   /*   $('#userPm25RankMon').dataTable({
			  "bPaginate": true,
			  "bLengthChange": false,
			  "bFilter": false,
			  "bSort": true,
			  "bInfo": true,
			  "bAutoWidth": false
			});     */

					/* $('#userPm25Rank').dataTable({
					  "bPaginate": true,
					  "bLengthChange": false,
					  "bFilter": false,
					  "bSort": true,
					  "bInfo": true,
					  "bAutoWidth": false
					}); */
					
					$("#changeDevice").dataTable({
						  "bPaginate": true,
						  "bLengthChange": true,
						  "bFilter": true,
						  "bSort": false,
						  "bInfo": true,
						  "bAutoWidth": true
						});
					
					//$("#onlineTable").dataTable();
					//$("#crm").dataTable();
				/*	$('#userVOCRank').dataTable({
					  "bPaginate": true,
					  "bLengthChange": false,
					  "bFilter": false,
					  "bSort": true,
					  "bInfo": true,
					  "bAutoWidth": false
					});
				*/
					
				/*	$('#userVOCRankMon').dataTable({
						  "bPaginate": true,
						  "bLengthChange": false,
						  "bFilter": false,
						  "bSort": true,
						  "bInfo": true,
						  "bAutoWidth": false
						});
					
					*/
					
					

				  });
				 
				 
//跳转 主页
function back2home(){
 		form = $("<form></form>");
        form.attr('action',"<%=basePath%>hwmobile/smart/corpbusiness!doIndex");
        form.attr('method','post');
        input1 = $("<input type='hidden' name='ACCOUNT' />");
        input1.attr('value',localStorage.getItem('PHONE',''));
        input2 = $("<input type='hidden' name='SESSIONID' />");
        input2.attr('value',localStorage.getItem('PASSWORD',''));
        form.append(input1);
        form.append(input2);
       form.appendTo("body");
        form.css('display','none');
        form.submit();
}			
//跳转 crm

function back2crm(){
 		form = $("<form></form>");
        form.attr('action',"<%=basePath%>hwmobile/smart/corpcrm!doList");
        form.attr('method','post');
        input1 = $("<input type='hidden' name='ACCOUNT' />");
        input1.attr('value',localStorage.getItem('PHONE',''));
        input2 = $("<input type='hidden' name='SESSIONID' />");
        input2.attr('value',localStorage.getItem('PASSWORD',''));
        form.append(input1);
        form.append(input2);
       form.appendTo("body");
        form.css('display','none');
        form.submit();
}			
//跳转 material
function back2material(){
 		form = $("<form></form>");
        form.attr('action',"<%=basePath%>hwmobile/smart/corpmaterial!doList");
        form.attr('method','post');
        input1 = $("<input type='hidden' name='ACCOUNT' />");
        input1.attr('value',localStorage.getItem('PHONE',''));
        input2 = $("<input type='hidden' name='SESSIONID' />");
        input2.attr('value',localStorage.getItem('PASSWORD',''));
        form.append(input1);
        form.append(input2);
       form.appendTo("body");
        form.css('display','none');
        form.submit();
}			
//跳转 potential
function back2potential(){
 		form = $("<form></form>");
        form.attr('action',"<%=basePath%>hwmobile/smart/corpcrm!doListPotential");
        form.attr('method','post');
        input1 = $("<input type='hidden' name='ACCOUNT' />");
        input1.attr('value',localStorage.getItem('PHONE',''));
        input2 = $("<input type='hidden' name='SESSIONID' />");
        input2.attr('value',localStorage.getItem('PASSWORD',''));
        form.append(input1);
        form.append(input2);
       form.appendTo("body");
        form.css('display','none');
        form.submit();
}			
//跳转 online
function back2online(){
 		form = $("<form></form>");
        form.attr('action',"<%=basePath%>hwmobile/smart/corponline!doList");
        form.attr('method','post');
        input1 = $("<input type='hidden' name='ACCOUNT' />");
        input1.attr('value',localStorage.getItem('PHONE',''));
        input2 = $("<input type='hidden' name='SESSIONID' />");
        input2.attr('value',localStorage.getItem('PASSWORD',''));
        form.append(input1);
        form.append(input2);
       form.appendTo("body");
        form.css('display','none');
        form.submit();
}			
//跳转 B用户修改
function back2client(){
 		form = $("<form></form>");
        form.attr('action',"<%=basePath%>hwmobile/smart/corpbusiness!doEdit");
        form.attr('method','post');
        input1 = $("<input type='hidden' name='ACCOUNT' />");
        input1.attr('value',localStorage.getItem('PHONE',''));
        input2 = $("<input type='hidden' name='SESSIONID' />");
        input2.attr('value',localStorage.getItem('PASSWORD',''));
        form.append(input1);
        form.append(input2);
       form.appendTo("body");
        form.css('display','none');
        form.submit();
}	



//分页
function pageChange(){
	
	
	$("#pagePotential").html("");
	
	
	$.post('<%=basePath%>hwmobile/smart/corpcrm!getDataInPage',
		      {
		ACCOUNT : localStorage.getItem('PHONE',''),
		SESSIONID : localStorage.getItem('PASSWORD',''),
				page : _page,
				rows : 10,
				pk : "ma001",
				cellIndex : "ma001,ma002,ma005,ma004,ma007,ma008,ma012,ma011",
				focus:1
		      },
		      function (data,status) //回传函数
		      {
		    	  
		    	  
		    	 _count_crm = data.total;
		    	 
		    	 if(_page > _count_crm){
	 	        		_page = _count_crm;
	 	        		reNew();
	 	        	}
		    	 
		    	 $("#pagePotential").createPage({
		    	        pageCount:_count_crm,
		    	        current:_page,
		    	        backFn:function(p){
		    				
		    	        	
		    	        	
							if(p > _count_crm){
		    	        		
		    	        	}else{
		    	        		_page = p;
		    	        	}
		    	        	 reNew();
		    	        	<%-- $.post('<%=basePath%>hwmobile/smart/corpcrm!getDataInPage',
		    	      		      {
		    	        		ACCOUNT : localStorage.getItem('PHONE',''),
		    	        		SESSIONID : localStorage.getItem('PASSWORD',''),
		    	      				page : p,
		    	      				rows : 10,
		    	      				pk : "ma001",
		    	      				cellIndex : "ma001,ma002,ma005,ma004,ma007,ma008,ma011",
		    	      				focus: 1
		    	      		      },
		    	      		      function (data,status) //回传函数
		    	      		      {
		    	      		    	  
		    	      		    	var content;
		    	    		    	for(var i = 0; i < data.rows.length; i++){
		    	    		    		
		    	    		    		//alert(data.rows[i].cell[2]);
		    	    		    		/* $("#crmTbody").append('<tr>');
		    	    		    		$("#crmTbody").append('<td><INPUT name="items" type="checkbox" value ="'+checknull(data.rows[i].cell[0])+'" /></td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[1])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[2])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[3])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[4])+'</td>');
		    	    		    		
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[5])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+dateFormat2(checknull(data.rows[i].cell[6]),"yyyy-MM-dd")+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checkState(data.rows[i].cell[7])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checkButton(data.rows[i].cell[7],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>');
		    	    		    		
		    	    		    		
		    	    		    		$("#crmTbody").append('</tr>'); */
		    	    		            
		    	    		    		content += '<tr>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[1])+'</td>'+'<td>'+checknull(data.rows[i].cell[2])+'</td>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[3])+'</td>'+'<td>'+checknull(data.rows[i].cell[4])+'</td>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[5])+'</td>'+
		    	    		    		'<td>'+checkButton(data.rows[i].cell[6],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>';
		    	    		    		
		    	    		    		//alert(checkButton(data.rows[i].cell[7],data.rows[i].cell[0]));
		    	    		            <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		    	    		            
		    	    		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		    	    		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		    	    		    	
		    	    		    	}/* for */
		    	    		    	$("#potential_tbody").html(content);
		    	    		    	
		    	      		    	 	
		    	      		    	 
		    	      		    	reNew(); 	
		    	      		      }
		    	      		    );   /* post */ --%>
		    	      		 
		    	        	
		    	        }
		    	    }); 
		    	 	
		    	 
		    	
		    	  	
		      }
		    );   /* post */
	
	
}


//分页    在线用户
//分页
function pageChange_online(){
	
	
	$("#pageOnline").html("");
	var count;
	
	$.post('<%=basePath%>hwmobile/smart/corponline!getDataInPage',
		      {
		ACCOUNT : localStorage.getItem('PHONE',''),
		SESSIONID : localStorage.getItem('PASSWORD',''),
				page : _page2,
				rows : 14,
				pk : "ma001",
				cellIndex : "ma001,ma002,ma005,ma004,ma007,ma008,ma012,ma011",
				focus:1
		      },
		      function (data,status) //回传函数
		      {
		    	  
		    	  
		    	 count = data.total;
		    	 
		    	 $("#pageOnline").createPage({
		    	        pageCount:count,
		    	        current:_page,
		    	        backFn:function(p){
		    				
		    	        	
		    	        	_page2 = p;
		    	        	
		    	        	reNew_online();
		    	        	<%-- $.post('<%=basePath%>hwmobile/smart/corponline!getDataInPage',
		    	      		      {
		    	        		ACCOUNT : localStorage.getItem('PHONE',''),
		    	        		SESSIONID : localStorage.getItem('PASSWORD',''),
		    	      				page : p,
		    	      				rows : 10,
		    	      				pk : "ma001",
		    	      				cellIndex : "ma001,ma002,ma005,ma004,ma007,ma008,ma011",
		    	      				focus: 1
		    	      		      },
		    	      		      function (data,status) //回传函数
		    	      		      {
		    	      		    	  
		    	      		    	var content;
		    	    		    	for(var i = 0; i < data.rows.length; i++){
		    	    		    		
		    	    		    		//alert(data.rows[i].cell[2]);
		    	    		    		/* $("#crmTbody").append('<tr>');
		    	    		    		$("#crmTbody").append('<td><INPUT name="items" type="checkbox" value ="'+checknull(data.rows[i].cell[0])+'" /></td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[1])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[2])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[3])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[4])+'</td>');
		    	    		    		
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[5])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+dateFormat2(checknull(data.rows[i].cell[6]),"yyyy-MM-dd")+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checkState(data.rows[i].cell[7])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checkButton(data.rows[i].cell[7],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>');
		    	    		    		
		    	    		    		
		    	    		    		$("#crmTbody").append('</tr>'); */
		    	    		            
		    	    		    		content += '<tr>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[1])+'</td>'+'<td>'+checknull(data.rows[i].cell[2])+'</td>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[3])+'</td>'+'<td>'+checknull(data.rows[i].cell[4])+'</td>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[5])+'</td>'+
		    	    		    		'<td>'+checkButton(data.rows[i].cell[6],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>';
		    	    		    		
		    	    		    		//alert(checkButton(data.rows[i].cell[7],data.rows[i].cell[0]));
		    	    		            <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		    	    		            
		    	    		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		    	    		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		    	    		    	
		    	    		    	}/* for */
		    	    		    	$("#online_tbody").html(content);
		    	    		    	
		    	      		    	 	
		    	      		    	 
		    	    		    	reNew_online(); 	
		    	      		      }
		    	      		    );   /* post */ --%>
		    	        	
		    	        }
		    	    }); 
		    	 	
		    	 
		    	
		    	  	
		      }
		    );   /* post */
	
	
}


//PM25 日排名
//分页
function pageChange_pm25Day(){
	
	
	$("#pagePm25Day").html("");
	
	
	$.post('<%=basePath%>hwmobile/smart/corpmain!getDataInPage',
		      {
		ACCOUNT : localStorage.getItem('PHONE',''),
		SESSIONID : localStorage.getItem('PASSWORD',''),
				page : _pageRankPm25Day,
				rows : 10,
				pk : "ID",
				cellIndex : "ID,REG_DATE,TEL,LXR,DEV_ID,DATAVALUE,FOCUSOR",
				SENSOR: "D8"
		      },
		      function (data,status) //回传函数
		      {
		    	  
		    	  
		    	 count = data.total;
		    	 
		    	 $("#pagePm25Day").createPage({
		    	        pageCount:count,
		    	        current:_pageRankPm25Day,
		    	        backFn:function(p){
		    				
		    	        	
		    	        	_pageRankPm25Day = p;
		    	        	
		    	        	reNew_rank_pm25_day();
		    	        	<%-- $.post('<%=basePath%>hwmobile/smart/corponline!getDataInPage',
		    	      		      {
		    	        		ACCOUNT : localStorage.getItem('PHONE',''),
		    	        		SESSIONID : localStorage.getItem('PASSWORD',''),
		    	      				page : p,
		    	      				rows : 10,
		    	      				pk : "ma001",
		    	      				cellIndex : "ma001,ma002,ma005,ma004,ma007,ma008,ma011",
		    	      				focus: 1
		    	      		      },
		    	      		      function (data,status) //回传函数
		    	      		      {
		    	      		    	  
		    	      		    	var content;
		    	    		    	for(var i = 0; i < data.rows.length; i++){
		    	    		    		
		    	    		    		//alert(data.rows[i].cell[2]);
		    	    		    		/* $("#crmTbody").append('<tr>');
		    	    		    		$("#crmTbody").append('<td><INPUT name="items" type="checkbox" value ="'+checknull(data.rows[i].cell[0])+'" /></td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[1])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[2])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[3])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[4])+'</td>');
		    	    		    		
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[5])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+dateFormat2(checknull(data.rows[i].cell[6]),"yyyy-MM-dd")+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checkState(data.rows[i].cell[7])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checkButton(data.rows[i].cell[7],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>');
		    	    		    		
		    	    		    		
		    	    		    		$("#crmTbody").append('</tr>'); */
		    	    		            
		    	    		    		content += '<tr>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[1])+'</td>'+'<td>'+checknull(data.rows[i].cell[2])+'</td>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[3])+'</td>'+'<td>'+checknull(data.rows[i].cell[4])+'</td>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[5])+'</td>'+
		    	    		    		'<td>'+checkButton(data.rows[i].cell[6],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>';
		    	    		    		
		    	    		    		//alert(checkButton(data.rows[i].cell[7],data.rows[i].cell[0]));
		    	    		            <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		    	    		            
		    	    		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		    	    		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		    	    		    	
		    	    		    	}/* for */
		    	    		    	$("#online_tbody").html(content);
		    	    		    	
		    	      		    	 	
		    	      		    	 
		    	    		    	reNew_online(); 	
		    	      		      }
		    	      		    );   /* post */ --%>
		    	        	
		    	        }
		    	    }); 
		    	 	
		    	 
		    	
		    	  	
		      }
		    );   /* post */
	
	
}

//PM25 月排名
//分页
function pageChange_pm25Mon(){
	
	
	$("#pagePm25Mon").html("");
	
	var count;
	$.post('<%=basePath%>hwmobile/smart/corpmain!getDataInPage',
		      {
		ACCOUNT : localStorage.getItem('PHONE',''),
		SESSIONID : localStorage.getItem('PASSWORD',''),
				page : _pageRankPm25Mon,
				rows : 10,
				pk : "ID",
				cellIndex : "ID,REG_DATE,TEL,LXR,DEV_ID,DATAVALUE,FOCUSOR",
				SENSOR: "D8",
				TYPE:"M "
		      },
		      function (data,status) //回传函数
		      {
		    	  
		    	  
		    	 count = data.total;
		    	 
		    	 $("#pagePm25Mon").createPage({
		    	        pageCount:count,
		    	        current:_pageRankPm25Mon,
		    	        backFn:function(p){
		    				
		    	        	
		    	        	_pageRankPm25Mon = p;
		    	        	
		    	        	reNew_rank_pm25_mon();
		    	        	<%-- $.post('<%=basePath%>hwmobile/smart/corponline!getDataInPage',
		    	      		      {
		    	        		ACCOUNT : localStorage.getItem('PHONE',''),
		    	        		SESSIONID : localStorage.getItem('PASSWORD',''),
		    	      				page : p,
		    	      				rows : 10,
		    	      				pk : "ma001",
		    	      				cellIndex : "ma001,ma002,ma005,ma004,ma007,ma008,ma011",
		    	      				focus: 1
		    	      		      },
		    	      		      function (data,status) //回传函数
		    	      		      {
		    	      		    	  
		    	      		    	var content;
		    	    		    	for(var i = 0; i < data.rows.length; i++){
		    	    		    		
		    	    		    		//alert(data.rows[i].cell[2]);
		    	    		    		/* $("#crmTbody").append('<tr>');
		    	    		    		$("#crmTbody").append('<td><INPUT name="items" type="checkbox" value ="'+checknull(data.rows[i].cell[0])+'" /></td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[1])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[2])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[3])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[4])+'</td>');
		    	    		    		
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[5])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+dateFormat2(checknull(data.rows[i].cell[6]),"yyyy-MM-dd")+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checkState(data.rows[i].cell[7])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checkButton(data.rows[i].cell[7],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>');
		    	    		    		
		    	    		    		
		    	    		    		$("#crmTbody").append('</tr>'); */
		    	    		            
		    	    		    		content += '<tr>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[1])+'</td>'+'<td>'+checknull(data.rows[i].cell[2])+'</td>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[3])+'</td>'+'<td>'+checknull(data.rows[i].cell[4])+'</td>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[5])+'</td>'+
		    	    		    		'<td>'+checkButton(data.rows[i].cell[6],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>';
		    	    		    		
		    	    		    		//alert(checkButton(data.rows[i].cell[7],data.rows[i].cell[0]));
		    	    		            <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		    	    		            
		    	    		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		    	    		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		    	    		    	
		    	    		    	}/* for */
		    	    		    	$("#online_tbody").html(content);
		    	    		    	
		    	      		    	 	
		    	      		    	 
		    	    		    	reNew_online(); 	
		    	      		      }
		    	      		    );   /* post */ --%>
		    	        	
		    	        }
		    	    }); 
		    	 	
		    	 
		    	
		    	  	
		      }
		    );   /* post */
	
	
}










//co2 日排名
//分页
function pageChange_co2Day(){
	
	
	$("#pageCo2Day").html("");
	var count;
	
	$.post('<%=basePath%>hwmobile/smart/corpmain!getDataInPage',
		      {
		ACCOUNT : localStorage.getItem('PHONE',''),
		SESSIONID : localStorage.getItem('PASSWORD',''),
				page : _pageRankCo2Day,
				rows : 10,
				pk : "ID",
				cellIndex : "ID,REG_DATE,TEL,LXR,DEV_ID,DATAVALUE,FOCUSOR",
				SENSOR: "30"
		      },
		      function (data,status) //回传函数
		      {
		    	  
		    	  
		    	 count = data.total;
		    	 
		    	 $("#pageCo2Day").createPage({
		    	        pageCount:count,
		    	        current:_pageRankCo2Day,
		    	        backFn:function(p){
		    				
		    	        	
		    	        	_pageRankCo2Day = p;
		    	        	
		    	        	reNew_rank_co2_day();
		    	        	<%-- $.post('<%=basePath%>hwmobile/smart/corponline!getDataInPage',
		    	      		      {
		    	        		ACCOUNT : localStorage.getItem('PHONE',''),
		    	        		SESSIONID : localStorage.getItem('PASSWORD',''),
		    	      				page : p,
		    	      				rows : 10,
		    	      				pk : "ma001",
		    	      				cellIndex : "ma001,ma002,ma005,ma004,ma007,ma008,ma011",
		    	      				focus: 1
		    	      		      },
		    	      		      function (data,status) //回传函数
		    	      		      {
		    	      		    	  
		    	      		    	var content;
		    	    		    	for(var i = 0; i < data.rows.length; i++){
		    	    		    		
		    	    		    		//alert(data.rows[i].cell[2]);
		    	    		    		/* $("#crmTbody").append('<tr>');
		    	    		    		$("#crmTbody").append('<td><INPUT name="items" type="checkbox" value ="'+checknull(data.rows[i].cell[0])+'" /></td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[1])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[2])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[3])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[4])+'</td>');
		    	    		    		
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[5])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+dateFormat2(checknull(data.rows[i].cell[6]),"yyyy-MM-dd")+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checkState(data.rows[i].cell[7])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checkButton(data.rows[i].cell[7],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>');
		    	    		    		
		    	    		    		
		    	    		    		$("#crmTbody").append('</tr>'); */
		    	    		            
		    	    		    		content += '<tr>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[1])+'</td>'+'<td>'+checknull(data.rows[i].cell[2])+'</td>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[3])+'</td>'+'<td>'+checknull(data.rows[i].cell[4])+'</td>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[5])+'</td>'+
		    	    		    		'<td>'+checkButton(data.rows[i].cell[6],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>';
		    	    		    		
		    	    		    		//alert(checkButton(data.rows[i].cell[7],data.rows[i].cell[0]));
		    	    		            <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		    	    		            
		    	    		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		    	    		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		    	    		    	
		    	    		    	}/* for */
		    	    		    	$("#online_tbody").html(content);
		    	    		    	
		    	      		    	 	
		    	      		    	 
		    	    		    	reNew_online(); 	
		    	      		      }
		    	      		    );   /* post */ --%>
		    	        	
		    	        }
		    	    }); 
		    	 	
		    	 
		    	
		    	  	
		      }
		    );   /* post */
	
	
}

//co2 月排名
//分页
function pageChange_co2Mon(){
	
	
	$("#pageCo2Mon").html("");
	
	var count;
	$.post('<%=basePath%>hwmobile/smart/corpmain!getDataInPage',
		      {
		ACCOUNT : localStorage.getItem('PHONE',''),
		SESSIONID : localStorage.getItem('PASSWORD',''),
				page : _pageRankCo2Mon,
				rows : 10,
				pk : "ID",
				cellIndex : "ID,REG_DATE,TEL,LXR,DEV_ID,DATAVALUE,FOCUSOR",
				SENSOR: "30",
				TYPE:"M "
		      },
		      function (data,status) //回传函数
		      {
		    	  
		    	  
		    	 count = data.total;
		    	 
		    	 $("#pageCo2Mon").createPage({
		    	        pageCount:count,
		    	        current:_pageRankCo2Mon,
		    	        backFn:function(p){
		    				
		    	        	
		    	        	_pageRankCo2Mon = p;
		    	        	
		    	        	reNew_rank_co2_mon();
		    	        	<%-- $.post('<%=basePath%>hwmobile/smart/corponline!getDataInPage',
		    	      		      {
		    	        		ACCOUNT : localStorage.getItem('PHONE',''),
		    	        		SESSIONID : localStorage.getItem('PASSWORD',''),
		    	      				page : p,
		    	      				rows : 10,
		    	      				pk : "ma001",
		    	      				cellIndex : "ma001,ma002,ma005,ma004,ma007,ma008,ma011",
		    	      				focus: 1
		    	      		      },
		    	      		      function (data,status) //回传函数
		    	      		      {
		    	      		    	  
		    	      		    	var content;
		    	    		    	for(var i = 0; i < data.rows.length; i++){
		    	    		    		
		    	    		    		//alert(data.rows[i].cell[2]);
		    	    		    		/* $("#crmTbody").append('<tr>');
		    	    		    		$("#crmTbody").append('<td><INPUT name="items" type="checkbox" value ="'+checknull(data.rows[i].cell[0])+'" /></td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[1])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[2])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[3])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[4])+'</td>');
		    	    		    		
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[5])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+dateFormat2(checknull(data.rows[i].cell[6]),"yyyy-MM-dd")+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checkState(data.rows[i].cell[7])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checkButton(data.rows[i].cell[7],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>');
		    	    		    		
		    	    		    		
		    	    		    		$("#crmTbody").append('</tr>'); */
		    	    		            
		    	    		    		content += '<tr>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[1])+'</td>'+'<td>'+checknull(data.rows[i].cell[2])+'</td>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[3])+'</td>'+'<td>'+checknull(data.rows[i].cell[4])+'</td>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[5])+'</td>'+
		    	    		    		'<td>'+checkButton(data.rows[i].cell[6],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>';
		    	    		    		
		    	    		    		//alert(checkButton(data.rows[i].cell[7],data.rows[i].cell[0]));
		    	    		            <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		    	    		            
		    	    		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		    	    		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		    	    		    	
		    	    		    	}/* for */
		    	    		    	$("#online_tbody").html(content);
		    	    		    	
		    	      		    	 	
		    	      		    	 
		    	    		    	reNew_online(); 	
		    	      		      }
		    	      		    );   /* post */ --%>
		    	        	
		    	        }
		    	    }); 
		    	 	
		    	 
		    	
		    	  	
		      }
		    );   /* post */
	
	
}














//voc 日排名
//分页
function pageChange_vocDay(){
	
	
	$("#pageVocDay").html("");
	var count;
	
	$.post('<%=basePath%>hwmobile/smart/corpmain!getDataInPage',
		      {
		ACCOUNT : localStorage.getItem('PHONE',''),
		SESSIONID : localStorage.getItem('PASSWORD',''),
				page : _pageRankVocDay,
				rows : 10,
				pk : "ID",
				cellIndex : "ID,REG_DATE,TEL,LXR,DEV_ID,DATAVALUE,FOCUSOR",
				SENSOR: "D9"
		      },
		      function (data,status) //回传函数
		      {
		    	  
		    	  
		    	 count = data.total;
		    	 
		    	 $("#pageVocDay").createPage({
		    	        pageCount:count,
		    	        current:_pageRankVocDay,
		    	        backFn:function(p){
		    				
		    	        	
		    	        	_pageRankVocDay = p;
		    	        	
		    	        	reNew_rank_voc_day();
		    	        	<%-- $.post('<%=basePath%>hwmobile/smart/corponline!getDataInPage',
		    	      		      {
		    	        		ACCOUNT : localStorage.getItem('PHONE',''),
		    	        		SESSIONID : localStorage.getItem('PASSWORD',''),
		    	      				page : p,
		    	      				rows : 10,
		    	      				pk : "ma001",
		    	      				cellIndex : "ma001,ma002,ma005,ma004,ma007,ma008,ma011",
		    	      				focus: 1
		    	      		      },
		    	      		      function (data,status) //回传函数
		    	      		      {
		    	      		    	  
		    	      		    	var content;
		    	    		    	for(var i = 0; i < data.rows.length; i++){
		    	    		    		
		    	    		    		//alert(data.rows[i].cell[2]);
		    	    		    		/* $("#crmTbody").append('<tr>');
		    	    		    		$("#crmTbody").append('<td><INPUT name="items" type="checkbox" value ="'+checknull(data.rows[i].cell[0])+'" /></td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[1])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[2])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[3])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[4])+'</td>');
		    	    		    		
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[5])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+dateFormat2(checknull(data.rows[i].cell[6]),"yyyy-MM-dd")+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checkState(data.rows[i].cell[7])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checkButton(data.rows[i].cell[7],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>');
		    	    		    		
		    	    		    		
		    	    		    		$("#crmTbody").append('</tr>'); */
		    	    		            
		    	    		    		content += '<tr>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[1])+'</td>'+'<td>'+checknull(data.rows[i].cell[2])+'</td>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[3])+'</td>'+'<td>'+checknull(data.rows[i].cell[4])+'</td>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[5])+'</td>'+
		    	    		    		'<td>'+checkButton(data.rows[i].cell[6],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>';
		    	    		    		
		    	    		    		//alert(checkButton(data.rows[i].cell[7],data.rows[i].cell[0]));
		    	    		            <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		    	    		            
		    	    		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		    	    		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		    	    		    	
		    	    		    	}/* for */
		    	    		    	$("#online_tbody").html(content);
		    	    		    	
		    	      		    	 	
		    	      		    	 
		    	    		    	reNew_online(); 	
		    	      		      }
		    	      		    );   /* post */ --%>
		    	        	
		    	        }
		    	    }); 
		    	 	
		    	 
		    	
		    	  	
		      }
		    );   /* post */
	
	
}

//voc 月排名
//分页
function pageChange_vocMon(){
	
	
	$("#pageVocMon").html("");
	
	var count;
	$.post('<%=basePath%>hwmobile/smart/corpmain!getDataInPage',
		      {
		ACCOUNT : localStorage.getItem('PHONE',''),
		SESSIONID : localStorage.getItem('PASSWORD',''),
				page : _pageRankVocMon,
				rows : 10,
				pk : "ID",
				cellIndex : "ID,REG_DATE,TEL,LXR,DEV_ID,DATAVALUE,FOCUSOR",
				SENSOR: "D9",
				TYPE:"M "
		      },
		      function (data,status) //回传函数
		      {
		    	  
		    	  
		    	 count = data.total;
		    	 
		    	 $("#pageVocMon").createPage({
		    	        pageCount:count,
		    	        current:_pageRankVocMon,
		    	        backFn:function(p){
		    				
		    	        	
		    	        	_pageRankVocMon = p;
		    	        	
		    	        	reNew_rank_voc_mon();
		    	        	<%-- $.post('<%=basePath%>hwmobile/smart/corponline!getDataInPage',
		    	      		      {
		    	        		ACCOUNT : localStorage.getItem('PHONE',''),
		    	        		SESSIONID : localStorage.getItem('PASSWORD',''),
		    	      				page : p,
		    	      				rows : 10,
		    	      				pk : "ma001",
		    	      				cellIndex : "ma001,ma002,ma005,ma004,ma007,ma008,ma011",
		    	      				focus: 1
		    	      		      },
		    	      		      function (data,status) //回传函数
		    	      		      {
		    	      		    	  
		    	      		    	var content;
		    	    		    	for(var i = 0; i < data.rows.length; i++){
		    	    		    		
		    	    		    		//alert(data.rows[i].cell[2]);
		    	    		    		/* $("#crmTbody").append('<tr>');
		    	    		    		$("#crmTbody").append('<td><INPUT name="items" type="checkbox" value ="'+checknull(data.rows[i].cell[0])+'" /></td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[1])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[2])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[3])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[4])+'</td>');
		    	    		    		
		    	    		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[5])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+dateFormat2(checknull(data.rows[i].cell[6]),"yyyy-MM-dd")+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checkState(data.rows[i].cell[7])+'</td>');
		    	    		    		$("#crmTbody").append('<td>'+checkButton(data.rows[i].cell[7],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>');
		    	    		    		
		    	    		    		
		    	    		    		$("#crmTbody").append('</tr>'); */
		    	    		            
		    	    		    		content += '<tr>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[1])+'</td>'+'<td>'+checknull(data.rows[i].cell[2])+'</td>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[3])+'</td>'+'<td>'+checknull(data.rows[i].cell[4])+'</td>'+
		    	    		    		'<td>'+checknull(data.rows[i].cell[5])+'</td>'+
		    	    		    		'<td>'+checkButton(data.rows[i].cell[6],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>';
		    	    		    		
		    	    		    		//alert(checkButton(data.rows[i].cell[7],data.rows[i].cell[0]));
		    	    		            <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		    	    		            
		    	    		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		    	    		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		    	    		    	
		    	    		    	}/* for */
		    	    		    	$("#online_tbody").html(content);
		    	    		    	
		    	      		    	 	
		    	      		    	 
		    	    		    	reNew_online(); 	
		    	      		      }
		    	      		    );   /* post */ --%>
		    	        	
		    	        }
		    	    }); 
		    	 	
		    	 
		    	
		    	  	
		      }
		    );   /* post */
	
	
}















function checknull(data){
	if(data == null){
		return "";
	}else
		return data;
}

function checkState(data){
	if(data == 0){
		return "<span style = 'font-weight:bold;color:red;'>未关注</span>";
	}
	if(data == 1){
		return "<span style = 'font-weight:bold;color:green;'>关注</span>";
		
	}
}

function checkButton(data,ma001){
	if(data == 0){
		return "<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\""+ma001+"\");'>关注</button>";
	}
	if(data == 1){
		return "<button class='btn btn-warning btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\""+ma001+"\");' >取消关注</button>";
		
	}
}
function checkButton2(data,ma001){
	if(data == 0){
		return "<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\""+ma001+"\");'>关注</button>";
	}
	if(data == 1){
		return "已关注";
		
	}
}
function reNew(){
	
	$.post('<%=basePath%>hwmobile/smart/corpcrm!getDataInPage',
		      {
		ACCOUNT : localStorage.getItem('PHONE',''),
		SESSIONID : localStorage.getItem('PASSWORD',''),
				page : _page,
				rows : 10,
				pk : "ma001",
				cellIndex : "ma001,ma002,ma005,ma004,ma007,ma008,ma011",
				focus: 1
		      },
		      function (data,status) //回传函数
		      {
		    	  var content = "";
		    	if(data.records){ 
		    	
		    		
		    	for(var i = 0; i < data.rows.length; i++){
		    		
		    		//alert(data.rows[i].cell[2]);
		    		/* $("#crmTbody").append('<tr>');
		    		$("#crmTbody").append('<td><INPUT name="items" type="checkbox" value ="'+checknull(data.rows[i].cell[0])+'" /></td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[1])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[2])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[3])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[4])+'</td>');
		    		
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[5])+'</td>');
		    		$("#crmTbody").append('<td>'+dateFormat2(checknull(data.rows[i].cell[6]),"yyyy-MM-dd")+'</td>');
		    		$("#crmTbody").append('<td>'+checkState(data.rows[i].cell[7])+'</td>');
		    		$("#crmTbody").append('<td>'+checkButton(data.rows[i].cell[7],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>');
		    		
		    		
		    		$("#crmTbody").append('</tr>'); */
		            
		    		
		    		content += '<tr>'+
		    		'<td>'+checknull(data.rows[i].cell[1])+'</td>'+'<td>'+checknull(data.rows[i].cell[2])+'</td>'+
		    		'<td>'+checknull(data.rows[i].cell[3])+'</td>'+'<td>'+checknull(data.rows[i].cell[4])+'</td>'+
		    		'<td>'+checknull(data.rows[i].cell[5])+'</td>'+
		    		'<td>'+checkButton(data.rows[i].cell[6],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>';
		    		
		    		
		            <%-- <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		            
		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		    	 --%>
		    	}/* for */
		    	
		    	}/* if */
		    	$("#potential_tbody").html(content); 	
  		    		
		    	
		    	  	
		      }
		    );   /* post */
}


//online 分页刷新
function reNew_online(){
	$.post('<%=basePath%>hwmobile/smart/corponline!getDataInPage',
		      {
		ACCOUNT : localStorage.getItem('PHONE',''),
		SESSIONID : localStorage.getItem('PASSWORD',''),
				page : _page2,
				rows : 14,
				pk : "ma020",
				cellIndex : "ma018,ma006,ma016,ma007",
				focus: 1
		      },
		      function (data,status) //回传函数
		      {
		    	  var content = "";
		    	if(data.records){ 
		    	
		    		
		    	for(var i = 0; i < data.rows.length; i++){
		    		
		    		//alert(data.rows[i].cell[2]);
		    		/* $("#crmTbody").append('<tr>');
		    		$("#crmTbody").append('<td><INPUT name="items" type="checkbox" value ="'+checknull(data.rows[i].cell[0])+'" /></td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[1])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[2])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[3])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[4])+'</td>');
		    		
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[5])+'</td>');
		    		$("#crmTbody").append('<td>'+dateFormat2(checknull(data.rows[i].cell[6]),"yyyy-MM-dd")+'</td>');
		    		$("#crmTbody").append('<td>'+checkState(data.rows[i].cell[7])+'</td>');
		    		$("#crmTbody").append('<td>'+checkButton(data.rows[i].cell[7],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>');
		    		
		    		
		    		$("#crmTbody").append('</tr>'); */
		            
		    		
		    		content += '<tr>'+
		    		'<td>'+checknull(data.rows[i].cell[0])+'</td>'+'<td>'+checknull(data.rows[i].cell[1])+'</td>'+
		    		'<td>'+checknull(data.rows[i].cell[2])+'</td>'+
		    		'<td>'+dateFormat2(checknull(data.rows[i].cell[3]),"yyyy-MM-dd")+"</td>"+"</tr>";
		    		
		    		
		            <%-- <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		            
		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		    	 --%>
		    	}/* for */
		    	
		    	}/* if */
		    	$("#online_tbody").html(content);
  		    		
		    	
		    	  	
		      }
		    );   /* post */
}


//分页  排名  pm25 day 刷新
function reNew_rank_pm25_day(){
	
	$.post('<%=basePath%>hwmobile/smart/corpmain!getDataInPage',
		      {
		ACCOUNT : localStorage.getItem('PHONE',''),
		SESSIONID : localStorage.getItem('PASSWORD',''),
				page : _pageRankPm25Day,
				rows : 10,
				pk : "ID",
				cellIndex : "ID,REG_DATE,TEL,LXR,DEV_ID,DATAVALUE,FOCUSOR",
				SENSOR: "D8"
		      },
		      function (data,status) //回传函数
		      {
		    	  var content = "";
		    	if(data.records){ 
		    	
		    		
		    	for(var i = 0; i < data.rows.length; i++){
		    		
		    		//alert(data.rows[i].cell[2]);
		    		/* $("#crmTbody").append('<tr>');
		    		$("#crmTbody").append('<td><INPUT name="items" type="checkbox" value ="'+checknull(data.rows[i].cell[0])+'" /></td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[1])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[2])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[3])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[4])+'</td>');
		    		
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[5])+'</td>');
		    		$("#crmTbody").append('<td>'+dateFormat2(checknull(data.rows[i].cell[6]),"yyyy-MM-dd")+'</td>');
		    		$("#crmTbody").append('<td>'+checkState(data.rows[i].cell[7])+'</td>');
		    		$("#crmTbody").append('<td>'+checkButton(data.rows[i].cell[7],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>');
		    		
		    		
		    		$("#crmTbody").append('</tr>'); */
		            
		    		
		    		content += '<tr>'+
		    		'<td>'+dateFormat2(checknull(data.rows[i].cell[1]),"yyyy-MM-dd")+'</td>'+'<td>'+checknull(data.rows[i].cell[2])+'</td>'+
		    		'<td>'+checknull(data.rows[i].cell[3])+'</td>'+'<td>'+checknull(data.rows[i].cell[4])+'</td>'+
		    		'<td>'+checknull(data.rows[i].cell[5])+'</td>'+
		    		'<td>'+checkButton2(data.rows[i].cell[6],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>';
		    		
		    		
		            <%-- <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		            
		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		    	 --%>
		    	}/* for */
		    	
		    	}/* if */
		    	$("#pm25day_tbody").html(content);
  		    		
		    	
		    	  	
		      }
		    );   /* post */
}


//分页  排名  pm25 mon 刷新
function reNew_rank_pm25_mon(){
	
	$.post('<%=basePath%>hwmobile/smart/corpmain!getDataInPage',
		      {
		ACCOUNT : localStorage.getItem('PHONE',''),
		SESSIONID : localStorage.getItem('PASSWORD',''),
				page : _pageRankPm25Mon,
				rows : 10,
				pk : "ID",
				cellIndex : "ID,REG_DATE,TEL,LXR,DEV_ID,DATAVALUE,FOCUSOR",
				SENSOR: "D8",
				TYPE: "M"
		      },
		      function (data,status) //回传函数
		      {
		    	  var content = "";
		    	if(data.records){ 
		    	
		    		
		    	for(var i = 0; i < data.rows.length; i++){
		    		
		    		//alert(data.rows[i].cell[2]);
		    		/* $("#crmTbody").append('<tr>');
		    		$("#crmTbody").append('<td><INPUT name="items" type="checkbox" value ="'+checknull(data.rows[i].cell[0])+'" /></td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[1])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[2])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[3])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[4])+'</td>');
		    		
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[5])+'</td>');
		    		$("#crmTbody").append('<td>'+dateFormat2(checknull(data.rows[i].cell[6]),"yyyy-MM-dd")+'</td>');
		    		$("#crmTbody").append('<td>'+checkState(data.rows[i].cell[7])+'</td>');
		    		$("#crmTbody").append('<td>'+checkButton(data.rows[i].cell[7],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>');
		    		
		    		
		    		$("#crmTbody").append('</tr>'); */
		            
		    		
		    		content += '<tr>'+
		    		'<td>'+dateFormat2(checknull(data.rows[i].cell[1]),"yyyy-MM-dd")+'</td>'+'<td>'+checknull(data.rows[i].cell[2])+'</td>'+
		    		'<td>'+checknull(data.rows[i].cell[3])+'</td>'+'<td>'+checknull(data.rows[i].cell[4])+'</td>'+
		    		'<td>'+checknull(data.rows[i].cell[5])+'</td>'+
		    		'<td>'+checkButton2(data.rows[i].cell[6],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>';
		    		
		    		
		            <%-- <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		            
		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		    	 --%>
		    	}/* for */
		    	
		    	}/* if */
		    	$("#pm25mon_tbody").html(content);
  		    		
		    	
		    	  	
		      }
		    );   /* post */
}






//分页  排名  co2 day 刷新
function reNew_rank_co2_day(){
	
	$.post('<%=basePath%>hwmobile/smart/corpmain!getDataInPage',
		      {
		ACCOUNT : localStorage.getItem('PHONE',''),
		SESSIONID : localStorage.getItem('PASSWORD',''),
				page : _pageRankCo2Day,
				rows : 10,
				pk : "ID",
				cellIndex : "ID,REG_DATE,TEL,LXR,DEV_ID,DATAVALUE,FOCUSOR",
				SENSOR: "30"
		      },
		      function (data,status) //回传函数
		      {
		    	  var content = "";
		    	if(data.records){ 
		    	
		    		
		    	for(var i = 0; i < data.rows.length; i++){
		    		
		    		//alert(data.rows[i].cell[2]);
		    		/* $("#crmTbody").append('<tr>');
		    		$("#crmTbody").append('<td><INPUT name="items" type="checkbox" value ="'+checknull(data.rows[i].cell[0])+'" /></td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[1])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[2])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[3])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[4])+'</td>');
		    		
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[5])+'</td>');
		    		$("#crmTbody").append('<td>'+dateFormat2(checknull(data.rows[i].cell[6]),"yyyy-MM-dd")+'</td>');
		    		$("#crmTbody").append('<td>'+checkState(data.rows[i].cell[7])+'</td>');
		    		$("#crmTbody").append('<td>'+checkButton(data.rows[i].cell[7],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>');
		    		
		    		
		    		$("#crmTbody").append('</tr>'); */
		            
		    		
		    		content += '<tr>'+
		    		'<td>'+dateFormat2(checknull(data.rows[i].cell[1]),"yyyy-MM-dd")+'</td>'+'<td>'+checknull(data.rows[i].cell[2])+'</td>'+
		    		'<td>'+checknull(data.rows[i].cell[3])+'</td>'+'<td>'+checknull(data.rows[i].cell[4])+'</td>'+
		    		'<td>'+checknull(data.rows[i].cell[5])+'</td>'+
		    		'<td>'+checkButton2(data.rows[i].cell[6],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>';
		    		
		    		
		            <%-- <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		            
		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		    	 --%>
		    	}/* for */
		    	
		    	}/* if */
		    	$("#co2day_tbody").html(content);	
  		    		
		    	
		    	  	
		      }
		    );   /* post */
}


//分页  排名  co2 mon 刷新
function reNew_rank_co2_mon(){
	
	$.post('<%=basePath%>hwmobile/smart/corpmain!getDataInPage',
		      {
		ACCOUNT : localStorage.getItem('PHONE',''),
		SESSIONID : localStorage.getItem('PASSWORD',''),
				page : _pageRankCo2Mon,
				rows : 10,
				pk : "ID",
				cellIndex : "ID,REG_DATE,TEL,LXR,DEV_ID,DATAVALUE,FOCUSOR",
				SENSOR: "30",
				TYPE: "M"
		      },
		      function (data,status) //回传函数
		      {
		    	  var content = "";
		    	if(data.records){ 
		    	
		    		
		    	for(var i = 0; i < data.rows.length; i++){
		    		
		    		//alert(data.rows[i].cell[2]);
		    		/* $("#crmTbody").append('<tr>');
		    		$("#crmTbody").append('<td><INPUT name="items" type="checkbox" value ="'+checknull(data.rows[i].cell[0])+'" /></td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[1])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[2])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[3])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[4])+'</td>');
		    		
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[5])+'</td>');
		    		$("#crmTbody").append('<td>'+dateFormat2(checknull(data.rows[i].cell[6]),"yyyy-MM-dd")+'</td>');
		    		$("#crmTbody").append('<td>'+checkState(data.rows[i].cell[7])+'</td>');
		    		$("#crmTbody").append('<td>'+checkButton(data.rows[i].cell[7],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>');
		    		
		    		
		    		$("#crmTbody").append('</tr>'); */
		            
		    		
		    		content += '<tr>'+
		    		'<td>'+dateFormat2(checknull(data.rows[i].cell[1]),"yyyy-MM-dd")+'</td>'+'<td>'+checknull(data.rows[i].cell[2])+'</td>'+
		    		'<td>'+checknull(data.rows[i].cell[3])+'</td>'+'<td>'+checknull(data.rows[i].cell[4])+'</td>'+
		    		'<td>'+checknull(data.rows[i].cell[5])+'</td>'+
		    		'<td>'+checkButton2(data.rows[i].cell[6],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>';
		    		
		    		
		            <%-- <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		            
		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		    	 --%>
		    	}/* for */
		    	
		    	}/* if */
		    	 	
		    	$("#co2mon_tbody").html(content);
		    	
		    	  	
		      }
		    );   /* post */
}












//分页  排名  voc day 刷新
function reNew_rank_voc_day(){
	
	$.post('<%=basePath%>hwmobile/smart/corpmain!getDataInPage',
		      {
		ACCOUNT : localStorage.getItem('PHONE',''),
		SESSIONID : localStorage.getItem('PASSWORD',''),
				page : _pageRankVocDay,
				rows : 10,
				pk : "ID",
				cellIndex : "ID,REG_DATE,TEL,LXR,DEV_ID,DATAVALUE,FOCUSOR",
				SENSOR: "D9"
		      },
		      function (data,status) //回传函数
		      {
		    	  var content = "";
		    	if(data.records){ 
		    	
		    		
		    	for(var i = 0; i < data.rows.length; i++){
		    		
		    		//alert(data.rows[i].cell[2]);
		    		/* $("#crmTbody").append('<tr>');
		    		$("#crmTbody").append('<td><INPUT name="items" type="checkbox" value ="'+checknull(data.rows[i].cell[0])+'" /></td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[1])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[2])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[3])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[4])+'</td>');
		    		
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[5])+'</td>');
		    		$("#crmTbody").append('<td>'+dateFormat2(checknull(data.rows[i].cell[6]),"yyyy-MM-dd")+'</td>');
		    		$("#crmTbody").append('<td>'+checkState(data.rows[i].cell[7])+'</td>');
		    		$("#crmTbody").append('<td>'+checkButton(data.rows[i].cell[7],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>');
		    		
		    		
		    		$("#crmTbody").append('</tr>'); */
		            
		    		
		    		content += '<tr>'+
		    		'<td>'+dateFormat2(checknull(data.rows[i].cell[1]),"yyyy-MM-dd")+'</td>'+'<td>'+checknull(data.rows[i].cell[2])+'</td>'+
		    		'<td>'+checknull(data.rows[i].cell[3])+'</td>'+'<td>'+checknull(data.rows[i].cell[4])+'</td>'+
		    		'<td>'+checknull(data.rows[i].cell[5])+'</td>'+
		    		'<td>'+checkButton2(data.rows[i].cell[6],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>';
		    		
		    		
		            <%-- <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		            
		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		    	 --%>
		    	}/* for */
		    	
		    	}/* if */
		    	$("#vocday_tbody").html(content);	
  		    		
		    	
		    	  	
		      }
		    );   /* post */
}


//分页  排名  voc mon 刷新
function reNew_rank_voc_mon(){
	
	$.post('<%=basePath%>hwmobile/smart/corpmain!getDataInPage',
		      {
		ACCOUNT : localStorage.getItem('PHONE',''),
		SESSIONID : localStorage.getItem('PASSWORD',''),
				page : _pageRankVocMon,
				rows : 10,
				pk : "ID",
				cellIndex : "ID,REG_DATE,TEL,LXR,DEV_ID,DATAVALUE,FOCUSOR",
				SENSOR: "D9",
				TYPE: "M"
		      },
		      function (data,status) //回传函数
		      {
		    	  var content = "";
		    	if(data.records){ 
		    	
		    		
		    	for(var i = 0; i < data.rows.length; i++){
		    		
		    		//alert(data.rows[i].cell[2]);
		    		/* $("#crmTbody").append('<tr>');
		    		$("#crmTbody").append('<td><INPUT name="items" type="checkbox" value ="'+checknull(data.rows[i].cell[0])+'" /></td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[1])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[2])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[3])+'</td>');
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[4])+'</td>');
		    		
		    		$("#crmTbody").append('<td>'+checknull(data.rows[i].cell[5])+'</td>');
		    		$("#crmTbody").append('<td>'+dateFormat2(checknull(data.rows[i].cell[6]),"yyyy-MM-dd")+'</td>');
		    		$("#crmTbody").append('<td>'+checkState(data.rows[i].cell[7])+'</td>');
		    		$("#crmTbody").append('<td>'+checkButton(data.rows[i].cell[7],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>');
		    		
		    		
		    		$("#crmTbody").append('</tr>'); */
		            
		    		
		    		content += '<tr>'+
		    		'<td>'+dateFormat2(checknull(data.rows[i].cell[1]),"yyyy-MM-dd")+'</td>'+'<td>'+checknull(data.rows[i].cell[2])+'</td>'+
		    		'<td>'+checknull(data.rows[i].cell[3])+'</td>'+'<td>'+checknull(data.rows[i].cell[4])+'</td>'+
		    		'<td>'+checknull(data.rows[i].cell[5])+'</td>'+
		    		'<td>'+checkButton2(data.rows[i].cell[6],data.rows[i].cell[0])+'<span style = "display: none;" >'+checknull(data.rows[i].cell[0])+'</span>'+'</td>';
		    		
		    		
		            <%-- <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		            
		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		    	 --%>
		    	}/* for */
		    	
		    	}/* if */
		    	$("#vocmon_tbody").html(content);	
  		    		
		    	
		    	  	
		      }
		    );   /* post */
}









function dateFormat2(date, format) {
	 
    date = new Date(date);

    var o = {
        'M+' : date.getMonth() + 1, //month
        'd+' : date.getDate(), //day
        'H+' : date.getHours(), //hour
        'm+' : date.getMinutes(), //minute
        's+' : date.getSeconds(), //second
        'q+' : Math.floor((date.getMonth() + 3) / 3), //quarter
        'S' : date.getMilliseconds() //millisecond
    };

    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));

    for (var k in o)
        if (new RegExp('(' + k + ')').test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length));
	//document.write(format);
    return format;
}

</script>

<div id = "phone_show" style="position:fixed;border-radius: 5px;font-family: 'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif;display:none;border:1px solid;background-color: #B3FFA7;z-index: 99999;width:auto;height:auto;">
	<div id="loading" class="loading">Loading pages...</div> 
	<div id = "show_content" style = "display: none;">
	联系人：<span id = "crm_name_modal"></span><br>
	手机号：<span id = "crm_tel_modal"></span><br>
	地址：<span id = "crm_addr_modal"></span>
	</div>

</div>
</body>
</html>