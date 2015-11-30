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
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />        
    <![endif]-->
<title>Air Radio - 空气电台</title>

<script type='text/javascript'
	src='js/plugins/jquery/jquery-1.9.1.min.js'></script>

<link rel="icon" type="image/ico" href="favicon.ico" />

<!-- 日历 -->
<link href="<%=basePath%>public/toB/dist/css/skins/_all-skins.min.css"
	rel="stylesheet" type="text/css" />
<!-- Date Picker -->
<link href="<%=basePath%>public/toB/plugins/datepicker/datepicker3.css"
	rel="stylesheet" type="text/css" />
<!-- DATApickerS  -->
<link href="css/daterangepicker-bs3.css" rel="stylesheet"
	type="text/css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js"
	type="text/javascript"></script>
<!-- datepicker -->
<script
	src="<%=basePath%>public/toB/plugins/datepicker/bootstrap-datepicker.js"
	type="text/javascript"></script>
<!-- datepicker -->
<script
	src="<%=basePath%>public/toB/plugins/daterangepicker/daterangepicker.js"
	type="text/javascript"></script>

<!-- ./日历 -->

<link href="css/stylesheets.css" rel="stylesheet" type="text/css" />

<!-- tab页面的css -->
<link href="css/lanrenzhijia.css" type="text/css" rel="stylesheet" />
<!-- 模态 -->
<link href="css/style_modal.css" type="text/css" rel="stylesheet" />

<!--[if lte IE 7]>
        <link href="css/ie.css" rel="stylesheet" type="text/css" />
        <script type='text/javascript' src='js/plugins/other/lte-ie7.js'></script>
    <![endif]-->

<script type='text/javascript'
	src='js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
<script type='text/javascript'
	src='js/plugins/jquery/jquery-migrate-1.1.1.min.js'></script>
<script type='text/javascript' src='js/plugins/jquery/globalize.js'></script>
<script type='text/javascript' src='js/plugins/other/excanvas.js'></script>

<script type='text/javascript'
	src='js/plugins/other/jquery.mousewheel.min.js'></script>

<script type='text/javascript'
	src='js/plugins/bootstrap/bootstrap.min.js'></script>

<script type='text/javascript'
	src='js/plugins/cookies/jquery.cookies.2.2.0.min.js'></script>

<script type='text/javascript' src='js/plugins/jflot/jquery.flot.js'></script>
<script type='text/javascript'
	src='js/plugins/jflot/jquery.flot.stack.js'></script>
<script type='text/javascript' src='js/plugins/jflot/jquery.flot.pie.js'></script>
<script type='text/javascript'
	src='js/plugins/jflot/jquery.flot.resize.js'></script>

<script type='text/javascript'
	src='js/plugins/sparklines/jquery.sparkline.min.js'></script>

<script type='text/javascript'
	src='js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>

<script type='text/javascript'
	src="js/plugins/uniform/jquery.uniform.min.js"></script>

<script type='text/javascript' src='js/plugins/shbrush/XRegExp.js'></script>
<script type='text/javascript' src='js/plugins/shbrush/shCore.js'></script>
<script type='text/javascript' src='js/plugins/shbrush/shBrushXml.js'></script>
<script type='text/javascript'
	src='js/plugins/shbrush/shBrushJScript.js'></script>
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

<!-- 模态窗口 -->
<script src="<%=basePath%>public/toB/dist/css/sweetalert.min.js"></script>

<link href="<%=basePath%>public/toB/dist/css/sweetalert.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript"
	src="<%=basePath %>home/js/index_main3.js"></script>
<script type="text/javascript" src="<%=basePath %>js/ajaxfileupload.js"></script>

<style type="text/css">
.loading {
	width: 100%;
	height: 100%;
	position: absolute;
	text-align: center top:0%;
	left: 0%;
	line-height: 56px;
	color: #fff;
	margin-top: 0px;
	margin-right: 0px;
	font-size: 15px;
	background: #000;
	opacity: 0.5;
	z-index: 9999;
	-moz-border-radius: 20px;
	-webkit-border-radius: 20px;
	border-radius: 20px;
	filter: progid:DXImageTransform.Microsoft.Alpha(opacity=50);
}

#loading_nav {
	width: 219px;
	height: 100%;
	position: absolute;
	text-align: center top:0%;
	left: 0%;
	line-height: 56px;
	color: #fff;
	margin-top: 0px;
	margin-right: 0px;
	font-size: 15px;
	background: #000;
	opacity: 0.8;
	z-index: 9999;
	-moz-border-radius: 20px;
	-webkit-border-radius: 20px;
	border-radius: 20px;
	filter: progid:DXImageTransform.Microsoft.Alpha(opacity=80);
}

table {
	*border-collapse: collapse; /* IE7 and lower */
	border-spacing: 0;
	width: 100%;
}

.bordered {
	border: solid #ccc 1px;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	-webkit-box-shadow: 0 1px 1px #ccc;
	-moz-box-shadow: 0 1px 1px #ccc;
	box-shadow: 0 1px 1px #ccc;
}

.bordered tr:hover {
	background: #fbf8e9;
	-o-transition: all 0.1s ease-in-out;
	-webkit-transition: all 0.1s ease-in-out;
	-moz-transition: all 0.1s ease-in-out;
	-ms-transition: all 0.1s ease-in-out;
	transition: all 0.1s ease-in-out;
}

.bordered td,.bordered th {
	border-left: 1px solid #ccc;
	border-top: 1px solid #ccc;
	padding: 10px;
	text-align: left;
}

.bordered th {
	background-color: #dce9f9;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#ebf3fc),
		to(#dce9f9));
	background-image: -webkit-linear-gradient(top, #ebf3fc, #dce9f9);
	background-image: -moz-linear-gradient(top, #ebf3fc, #dce9f9);
	background-image: -ms-linear-gradient(top, #ebf3fc, #dce9f9);
	background-image: -o-linear-gradient(top, #ebf3fc, #dce9f9);
	background-image: linear-gradient(top, #ebf3fc, #dce9f9);
	-webkit-box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
	-moz-box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
	box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
	border-top: none;
	text-shadow: 0 1px 0 rgba(255, 255, 255, .5);
}

.bordered td:first-child,.bordered th:first-child {
	border-left: none;
}

.bordered th:first-child {
	-moz-border-radius: 6px 0 0 0;
	-webkit-border-radius: 6px 0 0 0;
	border-radius: 6px 0 0 0;
}

.bordered th:last-child {
	-moz-border-radius: 0 6px 0 0;
	-webkit-border-radius: 0 6px 0 0;
	border-radius: 0 6px 0 0;
}

.bordered th:only-child {
	-moz-border-radius: 6px 6px 0 0;
	-webkit-border-radius: 6px 6px 0 0;
	border-radius: 6px 6px 0 0;
}

.bordered tr:last-child td:first-child {
	-moz-border-radius: 0 0 0 6px;
	-webkit-border-radius: 0 0 0 6px;
	border-radius: 0 0 0 6px;
}

.bordered tr:last-child td:last-child {
	-moz-border-radius: 0 0 6px 0;
	-webkit-border-radius: 0 0 6px 0;
	border-radius: 0 0 6px 0;
}

/*----------------------*/
.zebra td,.zebra th {
	padding: 10px;
	border-bottom: 1px solid #f2f2f2;
}

.zebra tbody tr:nth-child(even) {
	background: #f5f5f5;
	-webkit-box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
	-moz-box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
	box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
}

.zebra th {
	text-align: left;
	text-shadow: 0 1px 0 rgba(255, 255, 255, .5);
	border-bottom: 1px solid #ccc;
	background-color: #eee;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#f5f5f5),
		to(#eee));
	background-image: -webkit-linear-gradient(top, #f5f5f5, #eee);
	background-image: -moz-linear-gradient(top, #f5f5f5, #eee);
	background-image: -ms-linear-gradient(top, #f5f5f5, #eee);
	background-image: -o-linear-gradient(top, #f5f5f5, #eee);
	background-image: linear-gradient(top, #f5f5f5, #eee);
}

.zebra th:first-child {
	-moz-border-radius: 6px 0 0 0;
	-webkit-border-radius: 6px 0 0 0;
	border-radius: 6px 0 0 0;
}

.zebra th:last-child {
	-moz-border-radius: 0 6px 0 0;
	-webkit-border-radius: 0 6px 0 0;
	border-radius: 0 6px 0 0;
}

.zebra th:only-child {
	-moz-border-radius: 6px 6px 0 0;
	-webkit-border-radius: 6px 6px 0 0;
	border-radius: 6px 6px 0 0;
}

.zebra tfoot td {
	border-bottom: 0;
	border-top: 1px solid #fff;
	background-color: #f1f1f1;
}

.zebra tfoot td:first-child {
	-moz-border-radius: 0 0 0 6px;
	-webkit-border-radius: 0 0 0 6px;
	border-radius: 0 0 0 6px;
}

.zebra tfoot td:last-child {
	-moz-border-radius: 0 0 6px 0;
	-webkit-border-radius: 0 0 6px 0;
	border-radius: 0 0 6px 0;
}

.zebra tfoot td:only-child {
	-moz-border-radius: 0 0 6px 6px;
	-webkit-border-radius: 0 0 6px 6px border-radius: 0 0 6px 6px
}
</style>


<script>
var adverNum = 1;
	window.onload = function (){ 
		

		$("#loading").hide();
		//加载展示
		$.post('<%=basePath%>hwmobile/smart/weather!retriveSetting',
			      {
					USERID : localStorage.getItem('USERID',''),
					SESSIONID : localStorage.getItem('SESSIONID',''),
				
			      },
			      function (data,status) //回传函数
			      {
			    	  
			    	
			    	 	if(data.code == 1){
			    	 		
			    	
			    			var obj = data.dataObject;
			    			var content = "";
				    		for (var i=0;i<obj.length;i++){	
				    			var _sc = obj[i].ma002;
				    			var _dev = "内："+obj[i].ma008 +"  外："+  obj[i].ma009;
				    			var _scene = "";
				    			//if(_sc == "" ) _sc = "未分配";
				    			
				    			if (obj[i].ma003 > 0) _dev = "场景："+obj[i].ma005;
				    			if (obj[i].ma003 == 1 || obj[i].ma003  == 5) _dev = "设备："+obj[i].ma011;
				    			if (obj[i].ma003 == 0 || obj[i].ma003 == 4 || obj[i].ma003 == 2 || obj[i].ma003 == 6 || obj[i].ma003 == 7) _dev = "内："+obj[i].ma008 +"  外："+  obj[i].ma009;
				    			content+="<tr><td>"+_sc+"</td><td>"+getSettingFreq(obj[i].ma003)+"</td><td>"+_dev+"</td><td>"+obj[i].ma010+"</td><td><a  onclick=clearDispSetting('"+obj[i].ma001+"')>撤销</a></td><td><a  onclick=redirectBS('"+obj[i].ma003+"','"+obj[i].ma008+"','"+obj[i].ma009+"','" +obj[i].ma011+"','" +obj[i].ma007+"')>打开</a></td></tr>";
				    		}
				    		
				    		$("#disp_body").html(content);
			    		
			    	 	}
			    
			    	  	
			      }
			    );
		//加载通知
		$.post('<%=basePath%>hwmobile/smart/weather!getBSMessage',
			      {
					USERID : localStorage.getItem('USERID',''),
					SESSIONID : localStorage.getItem('SESSIONID',''),
				
			      },
			      function (data,status) //回传函数
			      {
			    	  
			    	
			    	 	if(data.code == 1){
			    	 		
			    	
			    			var obj = data.dataObject;
			   
			    			$("#message_ma002").val(obj.ma002);
			    			$("#message_ma001").val(obj.ma001);
			    			$("#message_ma006").val(obj.ma006);
			    			
			    			if(typeof(obj.ma004.time)!="undefined" ){
			    				
			    				$("#dateRange").val(dateFormat(obj.ma004.time,"MM/dd/yyyy")+"-"+dateFormat(obj.ma005.time,"MM/dd/yyyy"));
			    			}
						}
			    
			    	  	
			      }
			    );
		//加载广告
		 $.post('<%=basePath%>hwmobile/smart/weather!getAdvertise',
			      {
					USERID : localStorage.getItem('USERID',''),
					SESSIONID : localStorage.getItem('SESSIONID',''),
				
			      },
			      function (data,status) //回传函数
			      {
			    	  
			    		
			    	 	if(data.code == 1){
	
			    			var obj = data.dataObject;
			    			var content ="";
			    		 
			    			if (obj.length > 0){
			    			
			    				$('#disp_adver').empty();
			    				$('#disp_adver').append( "<option value='0'>无广告</option>" );
				    			for (var i=0;i<obj.length;i++){	
				    				if (obj[i].ma006=='3'){
				    					$("#preImg_logo").attr("src","data:image/png;base64,"+obj[i].ma008);
				    					$("#file_logo").hide();
				    					$("#id_logo").val(obj[i].ma001);
				    				}else{
					    				content += "<tr>";
					    				content += '<td><img id="preImg_advertise'+adverNum+'" alt="图片预览" src="data:image/png;base64,'+obj[i].ma008+'" style="height:150px;"/><input style="display:none" type="file" id="file'+adverNum+'" name="file" value="上传" /></td>';
					    				content += '<td><input type="text" id="txt_title'+adverNum+'" value="'+obj[i].ma004+'"></td>';
					    				content += '<td><input type="button" value="上传" onclick="uploadImage(\'advertise'+adverNum+'\',\'file'+adverNum+'\',\'txt_title'+adverNum+'\',\'id_advertise'+adverNum+'\')" /></td>';
					    				content += '<td><input type="button" value="下架" onclick="clearAdvertise(\'id_advertise'+adverNum+'\')" /></td>';
					    				content += '<input type="hidden" id = "id_advertise'+adverNum+'" value="'+obj[i].ma001+'" />';
					    				content += "</tr>";
					    				$('#disp_adver').append( "<option value='"+obj[i].ma001+"'>"+obj[i].ma004+"</option>" );
					    				adverNum = adverNum + 1; 
				    				}
				    			}
				    			$("#advertise_content").append(content);
			    			}else{
			    			
			    				content = "<tr>";
			    				content += '<td><img id="preImg_advertise1" alt="图片预览" src="<%=basePath %>images/pic_add.png" width="150px"/><input type="file" id="file1" name="file" value="上传" /></td>';
			    				content += '<td><input type="text" id="txt_title1"></td>';
			    				content += '<td><input type="button" value="上传" onclick="uploadImage(\'advertise1\',\'file1\',\'txt_title1\',\'id_advertise1\')" /></td>';
			    				content += '<td><input type="button" value="下架" onclick="clearAdvertise(\'id_advertise1\')" /></td>';
			    				content += '<input type="hidden" id = "id_advertise1" value="" />';
			    				content += "</tr>";
			    				
			    				$("#advertise_content").append(content);
			    				adverNum = 2;
			    			}
			    				
			    			
			    			
						}
			    
			    	  	
			      }
			    ); 

} 
	
	//LOGO提交
	function uploadLogo(pic,fileid) {  
	    //判断是否有选择上传文件  
	      // var imgPath = $("#file").val();  
	       var imgPath = $('#'+fileid).val();

	        if (imgPath == "") {  
	            alert("请选择上传图片！");  
	            return;  
	        } 
	        //alert(imgPath);
	        //判断上传文件的后缀名  
	        var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1).toLowerCase();  
	        if (strExtension != 'jpg' && strExtension != 'gif'  
	        && strExtension != 'png' && strExtension != 'bmp') {  
	            alert("请选择图片文件");  
	            return;  
	        }  
	        
	     
		$.ajaxFileUpload( {
			url : '<%=basePath%>hwmobile/smart/weather!uploadPic?USER='+localStorage.getItem('USERID',''),//用于文件上传的服务器端请求地址
			secureuri : false,//一般设置为false
			fileElementId : fileid,//'file',//文件上传控件的id属性
			data : {'MA006':3},
			dataType : 'json',//返回值类型 一般设置为json
			success : function(data, status) //服务器成功响应处理函数
			{
				//alert(data);
				var obj = eval('(' + data + ')');
				//alert(obj.dataObject.ma001);//从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
				 $("#preImg_"+pic).attr("src","data:image/png;base64,"+obj.data);  
				$("#id_"+pic).val(obj.dataObject.ma001);
				$('#'+fileid).hide();
				
			},
			error : function(data, status, e)//服务器响应失败处理函数
			{
				alert(e);
			}
		})
		return false;
	}
	
	//广告删除
	function clearLogo(obj){

		$("#loading_advertise").show();
		var ma001 = $("#"+obj).val();
		alert(ma001);
		if (ma001 !='') {
		$.post('<%=basePath%>hwmobile/smart/weather!clearAdervitise',
			      {
					USERID : localStorage.getItem('USERID',''),
					SESSIONID : localStorage.getItem('SESSIONID',''),
					MA001:ma001
			      },
			      function (data,status) //回传函数
			      {
			    	  
			    	  $("#loading_advertise").hide();
			    	 	if(data.code == 1){
			    	 		var content;
			   
						 content ='<td><img id="preImg_logo" alt="图片预览" src="<%=basePath %>images/pic_add.png" width="100px"/><input type="file" id="file_logo" name="file" value="上传" /></td>';
							
						 content +='<td><input type="button" value="上传" onclick="uploadLogo(\'logo\',\'file_logo\')" /></td>';
						 content +='<td><input type="button" value="下架" onclick="clearLogo(\'id_logo\')" /><input type="hidden" id = "id_logo" value="" /></td>';
			
							$("#logo_img_content").html(content);
			    	 		swal("Good!", "下架！", "success");	
			    			
	
			    	 	}
			    	 	if(data.code == 0){
			    	 		swal("OMG", "添加操作失败了!", "error");
			    	 	}
			    	  	
			      }
			    );
		}

	}
	
	
	//广告提交
	function uploadImage(pic,fileid,txt,id) {  
	    //判断是否有选择上传文件  
	      // var imgPath = $("#file").val();  
	       var imgPath = $('#'+fileid).val();
	       var txtV = $('#'+txt).val();
	       var adId = $('#'+id).val();
	       if (adId!=''){
	    	   //更新
	    	   updateAdvertise(txtV,adId);
	    	   return;
	       }
	       
	
	        if (imgPath == "") {  
	            alert("请选择上传图片！");  
	            return;  
	        } 
	        //alert(imgPath);
	        //判断上传文件的后缀名  
	        var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1).toLowerCase();  
	        if (strExtension != 'jpg' && strExtension != 'gif'  
	        && strExtension != 'png' && strExtension != 'bmp') {  
	            alert("请选择图片文件");  
	            return;  
	        }  
	        
	        /* 	$("#loading").ajaxStart(function() {
			$(this).show();
		}) //开始上传文件时显示一个图片
		.ajaxComplete(function() {
			$(this).hide();
		});//文件上传完成将图片隐藏起来
	*/
		$.ajaxFileUpload( {
			url : '<%=basePath%>hwmobile/smart/weather!uploadPic?USER='+localStorage.getItem('USERID',''),//用于文件上传的服务器端请求地址
			secureuri : false,//一般设置为false
			fileElementId : fileid,//'file',//文件上传控件的id属性
			data : {'MA004':txtV},
			dataType : 'json',//返回值类型 一般设置为json
			success : function(data, status) //服务器成功响应处理函数
			{
				//alert(data);
				var obj = eval('(' + data + ')');
				//alert(obj.dataObject.ma001);//从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
				 $("#preImg_"+pic).attr("src","data:image/png;base64,"+obj.data);  
				$("#id_"+pic).val(obj.dataObject.ma001);
				$('#'+fileid).hide();
				$('#disp_adver').append( "<option value='"+obj.dataObject.ma001+"'>"+obj.dataObject.ma004+"</option>" );
			},
			error : function(data, status, e)//服务器响应失败处理函数
			{
				alert(e);
			}
		})
		return false;
	}

	
	//广告删除
	function clearAdvertise(obj){

		$("#loading_advertise").show();
		var ma001 = $("#"+obj).val();
		if (ma001 !='') {
		$.post('<%=basePath%>hwmobile/smart/weather!clearAdervitise',
			      {
					USERID : localStorage.getItem('USERID',''),
					SESSIONID : localStorage.getItem('SESSIONID',''),
					MA001:ma001
			      },
			      function (data,status) //回传函数
			      {
			    	  
			    	  $("#loading_advertise").hide();
			    	 	if(data.code == 1){
			    	 		/* swal({
			    				title: "操作成功", 
			    				text: "已成功添加这条数据！", 
			    				type: "success",
			    				showCancelButton: false,
			    				closeOnConfirm: false,
			    				animation: "slide-from-top",  
			    				confirmButtonText: "确定",
			    				confirmButtonColor: "#AEDEF4"
			    				}, function() {
			    					
								    //alert(url);
							
			    				}); */
			    			$("#"+obj).parent("tr").remove();	
			    	 		swal("Good!", "下架！", "success");	
			    			
	
			    	 	}
			    	 	if(data.code == 0){
			    	 		swal("OMG", "添加操作失败了!", "error");
			    	 	}
			    	  	
			      }
			    );
		}

	}
	//增加选项
	function addAdvertise(){

		var content;
		content = "<tr>";
		content += '<td><img id="preImg_advertise'+adverNum+'" alt="图片预览" src="<%=basePath %>images/pic_add.png" width="150px"/><input type="file" id="file'+adverNum+'" name="file" value="上传" /></td>';
		content += '<td><input type="text" id="txt_title'+adverNum+'"></td>';
		content += '<td><input type="button" value="上传" onclick="uploadImage(\'advertise'+adverNum+'\',\'file'+adverNum+'\',\'txt_title'+adverNum+'\',\'id_advertise'+adverNum+'\')" /></td>';
		content += '<td><a  onclick="delAdvertise(this)" >删除</a></td>';
		content += '<input type="hidden" id = "id_advertise'+adverNum+'" value="" />';
		content += "</tr>";
		adverNum = adverNum + 1; 
		
		$("#advertise_content").append(content);
	}
	//清除空广告栏
	function delAdvertise(obj){
		var tr=obj.parentNode.parentNode;
		var tbody=tr.parentNode;
		tbody.removeChild(tr);
		adverNum = adverNum - 1;
	}
	//更新广告
	function updateAdvertise(val,obj){
		$("#loading_advertise").show();
		var ma001 = obj;
		if (ma001 !='') {
		
		$.post('<%=basePath%>hwmobile/smart/weather!updateAdvertise',
			      {
					USERID : localStorage.getItem('USERID',''),
					SESSIONID : localStorage.getItem('SESSIONID',''),
					MA004:val,
					MA001:ma001
			      },
			      function (data,status) //回传函数
			      {
			    	  
			    	  $("#loading_advertise").hide();
			    	 	if(data.code == 1){
			    	 		/* swal({
			    				title: "操作成功", 
			    				text: "已成功添加这条数据！", 
			    				type: "success",
			    				showCancelButton: false,
			    				closeOnConfirm: false,
			    				animation: "slide-from-top",  
			    				confirmButtonText: "确定",
			    				confirmButtonColor: "#AEDEF4"
			    				}, function() {
			    					
								    //alert(url);
							
			    				}); */
			    			
			    	 		swal("Good!", "更新成功", "success");	
			    			
	
			    	 	}
			    	 	if(data.code == 0){
			    	 		swal("OMG", "更新操作失败了!", "error");
			    	 	}
			    	  	
			      }
			    );
		}
	}	
//通知栏提交	
function setMessage(){
	$("#loading_message").show();
	var messageDate = $('#dateRange').val();

	messageDate = messageDate.replace(/\s/g, "");
		var array = messageDate.split("-");
	$.post('<%=basePath%>hwmobile/smart/weather!setMessage',
		      {
				USERID : localStorage.getItem('USERID',''),
				SESSIONID : localStorage.getItem('SESSIONID',''),
				MA002 : $("#message_ma002").val(),
				MA006 : $("#message_ma006").val(),
				MA004 : array[0],
				MA005 : array[1],
				MA001 : $("#message_ma001").val()
		      },
		      function (data,status) //回传函数
		      {
		    	  $("#loading_message").hide();
		    	 	if(data.code == 1){
		    	 		swal("Good!", "添加通知成功！", "success");	
		    	 		var obj = data.dataObject;
		    	 		$("#message_ma002").val(obj.ma002);
		    			$("#message_ma001").val(obj.ma001);
		    			$("#message_ma006").val(obj.ma006);
		    			if(typeof(obj.ma004.time)!="undefined" ){
		    				
		    				$("#dateRange").val(dateFormat(obj.ma004.time,"MM/dd/yyyy")+"-"+dateFormat(obj.ma005.time,"MM/dd/yyyy"));
		    			}
		    	 	}
		    	 	if(data.code == 0){
		    	 		swal("OMG", "添加操作失败了!", "error");
		    	 	}
		    	  	
		      }
		    );
}	
//通知栏清除
function clearMessage(){
	$("#loading_message").show();
	var ma001 = $("#message_ma001").val();
	if (ma001 !='') {
	$.post('<%=basePath%>hwmobile/smart/weather!clearMessage',
		      {
				USERID : localStorage.getItem('USERID',''),
				SESSIONID : localStorage.getItem('SESSIONID',''),

				MA001:$("#message_ma001").val()
		      },
		      function (data,status) //回传函数
		      {
		    	  
		    	  $("#loading_message").hide();
		    	 	if(data.code == 1){
		    	 		/* swal({
		    				title: "操作成功", 
		    				text: "已成功添加这条数据！", 
		    				type: "success",
		    				showCancelButton: false,
		    				closeOnConfirm: false,
		    				animation: "slide-from-top",  
		    				confirmButtonText: "确定",
		    				confirmButtonColor: "#AEDEF4"
		    				}, function() {
		    					
							    //alert(url);
						
		    				}); */
		    	 		swal("Good!", "取消成功！", "success");	
		    			$("#message_ma001").val("");
		    			$("#message_ma002").val("");
		    			$("#dateRange").val("");
		    			$("#message_ma006").val("");
		    	 	}
		    	 	if(data.code == 0){
		    	 		swal("OMG", "添加操作失败了!", "error");
		    	 	}
		    	  	
		      }
		    );
	}

}

function submitDisp(){

	$("#loading_disp").show();
	$.post('<%=basePath%>hwmobile/smart/weather!addDispSetting',
		      {
				USERID : localStorage.getItem('USERID',''),
				SESSIONID : localStorage.getItem('SESSIONID',''),
				MA002 :  $('#scene_type').val(),
				MA003 :  $('#freq_type').find("option:selected").val(),
				MA004 : $('#disp_freq').val(),
				MA005 : $('#scene_name').find("option:selected").val(),
				MA011 : $('#dev_type').find("option:selected").val(),
				MA007 : $('#disp_adver').find("option:selected").val(),
				MA008 :  $('#dev_type_in').find("option:selected").val(),
				MA009 :  $('#dev_type_out').find("option:selected").val()	
		      },
		      function (data,status) //回传函数
		      {
		    	  
		    	 	//alert(data.code);
		    	 	if(data.code == 1){
		    	 		$("#loading_disp").hide();
		    	 		swal("Good!", "添加配置成功！", "success");	
		    	 		
		    	 	//$("tr:contains("+ma001+")").children("td").eq(5).html("<span style = 'font-weight:bold;color:#F39C12;'>已更换</span>");
		    			var obj = data.dataObject;
		    			var content = "";
		    		for (var i=0;i<obj.length;i++){	
		    			var _dev = "内："+obj[i].ma008 +"  外："+  obj[i].ma009;
		    			var _scene = "";
		    			//if(_sc == "" ) _sc = "未分配";
		    			
		    			if (obj[i].ma003 > 0) _dev = "场景："+obj[i].ma005;
		    			if (obj[i].ma003 == 1 || obj[i].ma003  == 5) _dev = "设备："+obj[i].ma011;
		    			if (obj[i].ma003 == 0 || obj[i].ma003 == 4 || obj[i].ma003 == 2 || obj[i].ma003 == 6 || obj[i].ma003 == 7) _dev = "内："+obj[i].ma008 +"  外："+  obj[i].ma009;
		    			
		    			content += "<tr><td>"+obj[i].ma002+"</td><td>"+getSettingFreq(obj[i].ma003)+"</td><td>"+_dev+"</td><td>"+obj[i].ma010+"</td><td><a  onclick=clearDispSetting('"+obj[i].ma001+"')>撤销</a></td><td><a  onclick=redirectBS('"+obj[i].ma003+"','"+obj[i].ma008+"','"+obj[i].ma009+"','"+obj[i].ma011+"','"+obj[i].ma007+"')>打开</a></td></tr>";
		    		}
		    		
		    		$("#disp_body").html(content);
		    	 	}
		    	 	if(data.code == 0){
		    	 		$("#loading_disp").hide();
		    	 		swal("OMG", data.message, "error");
		    	 	}
		    	  	
		      }
		    );
	

}	

function clearDispSetting(obj){

	$("#loading_disp").show();
	$.post('<%=basePath%>hwmobile/smart/weather!delDispSetting',
		      {
				USERID : localStorage.getItem('USERID',''),
				SESSIONID : localStorage.getItem('SESSIONID',''),
				MA001 : obj
				
		      },
		      function (data,status) //回传函数
		      {
		    	  
		    	 	//alert(data.code);
		    	 	if(data.code == 1){
		    	 		$("#loading_disp").hide();
		    	 		swal("Good!", "撤销成功！", "success");	
		    	 		
		    	 	//$("tr:contains("+ma001+")").children("td").eq(5).html("<span style = 'font-weight:bold;color:#F39C12;'>已更换</span>");
		    			var obj = data.dataObject;
		    			var content = "";
			    		for (var i=0;i<obj.length;i++){	
			    			var _dev = "内："+obj[i].ma008 +"  外："+  obj[i].ma009;
			    			var _scene = "";
			    			//if(_sc == "" ) _sc = "未分配";
			    			
			    			if (obj[i].ma003 > 0) _dev = "场景："+obj[i].ma005;
			    			if (obj[i].ma003  == 1 || obj[i].ma003  == 5) _dev = "设备："+obj[i].ma011;
			    			if (obj[i].ma003 == 0 || obj[i].ma003 == 4 || obj[i].ma003 == 2 || obj[i].ma003 == 6 || obj[i].ma003 == 7) _dev = "内："+obj[i].ma008 +"  外："+  obj[i].ma009;
			    			content += "<tr><td>"+obj[i].ma002+"</td><td>"+getSettingFreq(obj[i].ma003)+"</td><td>"+_dev+"</td><td>"+obj[i].ma010+"</td><td><a  onclick=clearDispSetting('"+obj[i].ma001+"')>撤销</a></td><td><a  onclick=redirectBS('"+obj[i].ma003+"','"+obj[i].ma008+"','"+obj[i].ma009+"','"+obj[i].ma011+"','"+obj[i].ma007+"')>打开</a></td></tr>";
			    		}
			    		
			    		$("#disp_body").html(content);
		    	 	}
		    	 	if(data.code == 0){
		    	 		$("#loading_disp").hide();
		    	 		swal("OMG", data.message, "error");
		    	 	}
		    	  	
		      }
		    );
}

function getSettingFreq(obj){
	if (obj == "0") return "模板1(内外)";
	if (obj == "1") return "模板2(滚动)";
	if (obj == "2") return "模板3(固定)";
	if (obj == "3") return "模板4(场景滚动)"; 
	if (obj == "4") return "方案-绿地"; 
	if (obj == "5") return "方案-贝昂"; 
	if (obj == "6") return "方案-绿地(噪音)"; 
	if (obj == "7") return "模板5(内外)(噪音)"; 
	if (obj == "100") return "自定义"; 
	
}	
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
	//document.write(format);
    return format;
}	
//打开大屏展示
/* 	<option value="0" selected>模板1(内外)</option>
														<option value="1">模板2(滚动)</option>
														<option value="2">模板3(固定)</option>
														<option value="3">模板4(场景滚动)</option>
														<option value="4">方案-绿地</option>
														<option value="100">自定义</option> */
function redirectBS(model,sensorIn,sensorOut,singleDevice,ad){
	var url;

	if (model == 0){
	  url = "<%=basePath%>home/bigscreen/bigS1/newScreen.html?IN="
					+ sensorIn + "&OUT=" + sensorOut + "&AD=" + ad+"&USERID="+localStorage.getItem('USERID','')+"&SESSIONID="+ localStorage.getItem('SESSIONID','');
		
	}
	if (model == 1){
		  url = "<%=basePath%>home/bigscreen/bigS2/bigScreen2.html?IN="
						+ singleDevice + "&OUT=" + sensorOut + "&AD=" + ad+"&USERID="+localStorage.getItem('USERID','')+"&SESSIONID="+ localStorage.getItem('SESSIONID','');
			
		}
	if (model == 2){
		  url = "<%=basePath%>home/bigscreen/bigS1/bigScreen1.html?IN="
						+ sensorIn + "&OUT=" + sensorOut + "&AD=" + ad+"&USERID="+localStorage.getItem('USERID','')+"&SESSIONID="+ localStorage.getItem('SESSIONID','');
			
		}
	if (model == 3){
		  url = "<%=basePath%>home/bigscreen/bigS1/bigScreen.html?IN="
						+ sensorIn + "&OUT=" + sensorOut + "&AD=" + ad+"&USERID="+localStorage.getItem('USERID','')+"&SESSIONID="+ localStorage.getItem('SESSIONID','');
			
		}
	if (model == 4){
		  url = "<%=basePath%>home/bigscreen/bigS1/newScreen2.html?IN="
						+ sensorIn + "&OUT=" + sensorOut + "&AD=" + ad+"&USERID="+localStorage.getItem('USERID','')+"&SESSIONID="+ localStorage.getItem('SESSIONID','');
			
		}
	if (model == 5){
		  url = "<%=basePath%>home/bigscreen/bigS1/BAScreen.html?IN="
						+ singleDevice + "&OUT=" + sensorOut + "&AD=" + ad+"&USERID="+localStorage.getItem('USERID','')+"&SESSIONID="+ localStorage.getItem('SESSIONID','');
			
		}
	if (model == 6){
		  url = "<%=basePath%>home/bigscreen/bigS1/newScreen2V2.html?IN="
						+ sensorIn + "&OUT=" + sensorOut + "&AD=" + ad+"&USERID="+localStorage.getItem('USERID','')+"&SESSIONID="+ localStorage.getItem('SESSIONID','');
			
		}
	if (model == 7){
		  url = "<%=basePath%>home/bigscreen/bigS1/newScreenV2.html?IN="
						+ sensorIn + "&OUT=" + sensorOut + "&AD=" + ad+"&USERID="+localStorage.getItem('USERID','')+"&SESSIONID="+ localStorage.getItem('SESSIONID','');
			
		}

	//window.location.target = "_blank";
	//window.location.href = url;
	window.open(url);  
}
</script>


</head>
<body>

	<!-- <a id="keleyivisitorip" class="txt" id="txt" style="display:none"></a>
	<script type="text/javascript"
		src="http://tool.keleyi.com/ip/visitoriphost/"></script> -->
	<script
		src="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js"></script>

	<div id="loader">
		<img src="img/loader.gif" />
	</div>
	<div class="wrapper">

		<div class="sidebar">

			<div class="top">
				<a class="logo"></a>
				<div class="search">
					<img src="img/about_logo_text.png" />

					<!--<div class="input-prepend">
                        <span class="add-on orange"><span class="icon-search icon-white"></span></span>
                        <input type="text" placeholder="search..."/>                                                      
                    </div>   -->
				</div>
			</div>
			<div class="nContainer">
				<div id="loading_nav" class="loading" style="overflow: hidden">
					<center>
						<img src="<%=basePath%>public/loading.gif" />Loading...
					</center>
				</div>
				<ul class="navigation" id="navigation11">
					<!--       <li class="active"><a href="index-2.html" class="blblue"></a></li> -->
					<li class='active'><a href="#" class="bldblue">未分配</a>
						<div class="open"></div>
						<ul id="devices_nop">
							<li><a href="ui.html"></a></li>
							<li><a href="widgets.html"></a></li>
						</ul></li>
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
				<a class="close"> <span class="ico-remove"></span>
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


				<div class="row-fluid">

					<div class="span12">

						<div class="block">
							<div id="loading_logo" class="" style="display: none">
								<center>
									<img src="<%=basePath%>public/loading.gif" />Loading pages...
								</center>
							</div>
							<div class="head">
								<!-- <div class="icon">
                                <span class="ico-chart-4"></span>
                                </div> -->
								<h2>LOGO</h2>
								<ul class="buttons">
									<li><a href="#" class="ublock"><div class="icon">
												<span class="ico-undo"></span>
											</div></a></li>
									<!-- <li><a href="#" class="cblock"><div class="icon"><span class="ico-sort"></span></div></a></li> -->
								</ul>
							</div>

							<div id="logo" style="display: block;">
								
								<table class="bordered">
									
									<tbody >
										<tr id="logo_img_content">
								<td><img id="preImg_logo" alt="图片预览" src="<%=basePath %>images/pic_add.png" width="100px"/><input type="file" id="file_logo" name="file" value="上传" />
								</td>
								<td><input type="button" value="上传" onclick="uploadLogo('logo','file_logo')" /></td>
								<td><input type="button" value="下架" onclick="clearLogo('id_logo')" />
								<input type="hidden" id = "id_logo" value="" />
								</td>
								</tr>
								
									</tbody>

								</table>

							</div>


						</div>



					</div>


				</div>


				<div class="row-fluid">

					<div class="span12">

						<div class="block">
							<div id="loading_advertise" class="" style="display: none">
								<center>
									<img src="<%=basePath%>public/loading.gif" />Loading pages...
								</center>
							</div>
							<div class="head">
								<!-- <div class="icon">
                                <span class="ico-chart-4"></span>
                                </div> -->
								<h2>广告位</h2>
								<ul class="buttons">
									<li><a href="#" class="ublock"><div class="icon">
												<span class="ico-undo"></span>
											</div></a></li>
									<!-- <li><a href="#" class="cblock"><div class="icon"><span class="ico-sort"></span></div></a></li> -->
								</ul>
							</div>

							<div id="Advertise" style="display: block;">
								<img alt="图片预览" src="<%=basePath%>images/pic_add.png"
									width="50px" onclick="addAdvertise();" />
								<table class="bordered">
									<thead>
										<tr>
											<th>图片</th>
											<th>广告标题</th>
											<th>操作</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="advertise_content">
										<%-- <tr>
								<td><img id="preImg_advertise1" alt="图片预览" src="<%=basePath %>images/pic_add.png" width="150px"/><input type="file" id="file" name="file" value="上传" />
								</td>
								<td><input type="button" value="上传" onclick="uploadImage('advertise1','file')" /></td>
								<td><input type="button" value="下架" onclick="clearAdvertise('id_advertise1')" />
								<input type="hidden" id = "id_advertise1" value="" />
								</td>
								</tr>
								<tr>
								<td><img id="preImg_advertise2" alt="图片预览" src="<%=basePath %>images/pic_add.png" width="150px"/><input type="file" id="file1" name="file" value="上传" />
								</td>
								<td><input type="button"  value="上传" onclick="uploadImage('advertise2','file1')" /></td>
								<td><input type="button" value="下架" onclick="clearAdvertise('id_advertise2')" />
									<input type="hidden" id = "id_advertise2" value="" />
								</td>
								</tr> --%>
									</tbody>

								</table>

							</div>


						</div>



					</div>


				</div>

				<div class="row-fluid">

					<div class="span12">

						<div class="block">
							<div id="loading_message" class="" style="display: none">
								<center>
									<img src="<%=basePath%>public/loading.gif" />Loading pages...
								</center>
							</div>
							<div class="head">
								<!-- <div class="icon">
                                <span class="ico-chart-4"></span>
                                </div> -->
								<h2>通知栏</h2>
								<ul class="buttons">
									<li><a href="#" class="ublock"><div class="icon">
												<span class="ico-undo"></span>
											</div></a></li>
									<!-- <li><a href="#" class="cblock"><div class="icon"><span class="ico-sort"></span></div></a></li> -->
								</ul>
							</div>
							<s:form method="post" id="bs_message">
								<div id="Message" style="display: block;">
									<table>
										<thead>
											<tr>
												<td></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td><input type="checkbox" id="box" name="frequency"
													value="1" /> 频率</td>
												<td>每隔 <input type="text" name="messagePO.ma006"
													id="message_ma006" value="${messagePO.ma006}" /> 分钟
												</td>
												<td>0 为无限循环播放</td>
											</tr>
											<tr>
												<td><input type="checkbox" id="box1" name="timing"
													value="1" /> 定时</td>
												<td><input id="dateRange" type="text"
													class="form-control pull-right"
													value="${messagePO.ma004}-${messagePO.ma005}" /></td>
												<td>不选择为马上执行</td>
											</tr>
											<tr>
												<td></td>
												<td colspan="2"><s:textarea name="messagePO.ma002"
														id="message_ma002">${messagePO.ma002}</s:textarea></td>

											</tr>
											<tr>
												<td></td>
												<td><input type="button" value="清 除" class="lan_at_bn"
													onclick="clearMessage();" /></td>
												<td><input type="button" value="确定"
													onclick="setMessage();"></td>
											</tr>
										</tbody>

									</table>

								</div>
								<input type="hidden" id="message_ma001"
									value="${messagePO.ma001}" />
							</s:form>


						</div>



					</div>


				</div>

				<div class="row-fluid">

					<div class="span12">

						<div class="block">
							<div id="loading_disp" style="display: none">
								<center>
									<img src="<%=basePath%>public/loading.gif" />Loading pages...
								</center>
							</div>
							<div class="head">
								<!-- <div class="icon">
                                <span class="ico-chart-4"></span>
                                </div> -->
								<h2>展示</h2>
								<ul class="buttons">
									<li><a href="#" class="ublock"><div class="icon">
												<span class="ico-undo"></span>
											</div></a></li>
									<!-- <li><a href="#" class="cblock"><div class="icon"><span class="ico-sort"></span></div></a></li> -->
								</ul>
							</div>
							<s:form method="post" id="scene_disp_setting">
								<div id="Display" style="display: block;">
									<table class="bordered">
										<thead>
											<tr>
												<th>场景名称</th>
												<th>模板选择</th>
												<th>设备选择</th>
												<th>广告展示</th>
											</tr>

										</thead>
										<tbody>
											<tr>
												<td>
													<%-- <select type="select" name="MA002" id="scene_type" onchange="sceneOnChange(this);">

										    <option value="-1" selected>请选择</option>
											<option value="0" >未分配</option>
										   </select> --%> <input type="text" name="MA002"
													id="scene_type" />
												</td>
												<td><select type="select" name="MA003" id="freq_type"
													onchange="freqOnChange()">

														<option value="0" selected>模板1(内外)</option>
														<option value="1">模板2(滚动)</option>
														<option value="2">模板3(固定)</option>
														<option value="3">模板4(场景滚动)</option>
														<option value="4">方案-绿地</option>
														<option value="5">方案-贝昂</option>
														<option value="6">方案-绿地(噪音)</option>
														<option value="7">模板5(内外)(噪音)</option>
														<option value="100">自定义</option>
												</select></td>
												<td><select type="select" name="MA005" id="dev_type"
													style="display: none">

														<option value="0">全部</option>

												</select> <span id="spn_frq" style="display: none"> 间隔 <input
														type="text" name="MA004" id="disp_freq" value="0" /> s
												</span> <span id="spn_noloop" style="display: bolck">内：<select
														type="select" name="MA007" id="dev_type_in">

															<option value="0">请选择</option>

													</select>外：<select type="select" name="MA008" id="dev_type_out">

															<option value="0">请选择</option>

													</select></span> <span id="spn_scene" style="display: none"> <select
														type="select" name="MA002" id="scene_name">

															<option value="-1" selected>请选择</option>
															<option value="0">未分配</option>
													</select>

												</span></td>
												<td><select type="select" name="MA007" id="disp_adver">

														<option value="0">无广告</option>

												</select></td>
											</tr>

											<tr>
												<td></td>
												<td><input type="hidden" value="清 除" class="lan_at_bn" /></td>
												<td><input type="button" value="添加"
													onclick="submitDisp();"></td>
												<td></td>
											</tr>
											<tr>
												<td colspan="4">
													<table class="zebra">
														<thead>
															<tr>
																<th>场景</th>
																<th>模板</th>
																<th>设备</th>
																<th>广告</th>
																<th>操作</th>
																<th>链接</th>
															</tr>

														</thead>
														<tbody id="disp_body">
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>

									</table>

								</div>
							</s:form>

						</div>



					</div>


				</div>

				<div class="row-fluid">

					<div class="span12">

						<div class="block">
							<div id="loading_history" class="" style="display: none">
								<center>
									<img src="<%=basePath%>public/loading.gif" />Loading pages...
								</center>
							</div>
							<div class="head">
								<!-- <div class="icon">
                                <span class="ico-chart-4"></span>
                                </div> -->
								<h2>
									<!-- 综合 -->
								</h2>
								<ul class="buttons">
									<li><a href="#" class="ublock"><div class="icon">
												<span class="ico-undo"></span>
											</div></a></li>
									<!-- <li><a href="#" class="cblock"><div class="icon"><span class="ico-sort"></span></div></a></li> -->
								</ul>
							</div>

							<div id="All" style="display: block;"></div>


						</div>



					</div>


				</div>

			</div>

		</div>

	</div>

	<div class="dialog" id="source" style="display: none;" title="Source"></div>


	<div id="loginmodal1" style="display: none;">
		<center>
			<h2>微店</h2>
		</center>
		<img src="<%=basePath%>public/weidian.png" />
	</div>
	<div id="loginmodal2" style="display: none;">
		<center>
			<h2>服务号</h2>
		</center>
		<img src="<%=basePath%>public/fuwuhao.jpg" />
	</div>

	<script type="text/javascript">
		$(function() {
			//Date range picker
			$('#dateRange').daterangepicker();
		});
	</script>



	<script type="text/javascript">
		$(function() {
			$('#loginform').submit(function(e) {
				return false;
			});

			$('#modaltrigger1').leanModal({
				top : 110,
				overlay : 0.45,
				closeButton : ".hidemodal"
			});
			$('#modaltrigger2').leanModal({
				top : 110,
				overlay : 0.45,
				closeButton : ".hidemodal"
			});
		});
	</script>

</body>
</html>




