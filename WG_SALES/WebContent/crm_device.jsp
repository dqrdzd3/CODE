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
<base href="<%=basePath%>">
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<!-- Bootstrap 3.3.4 -->
<link href="performance/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- FontAwesome 4.3.0 -->
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link href="performance/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
<!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
<link href="performance/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
<!-- iCheck -->
<link href="performance/plugins/iCheck/flat/blue.css" rel="stylesheet" type="text/css" />

<!-- Date Picker -->
<link href="performance/plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />

<!-- DATA TABLES 曾凡 20150626 污染排名 -->
<link href="performance/plugins/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css">

<!-- DATApickerS  -->
<link href="performance/plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css">


<!-- jQuery 1.9.1 --> 
<script src="performance/plugins/jQuery/jquery-1.9.1.min.js"></script> 

<!-- 模态窗口 --> 
<script src="performance/dist/css/sweetalert.min.js"></script> 

<link href="performance/dist/css/sweetalert.css" rel="stylesheet" type="text/css">



<script>
function onSureStatus(ma001){
	swal({
		title: "确定关注吗？", 
		text: "您确定要添加关注吗？", 
		type: "warning",
		showCancelButton: true,
		closeOnConfirm: false,
		animation: "slide-from-top",  
		confirmButtonText: "是的，我要关注",
		confirmButtonColor: "#ec6c62"
		}, function() {
				
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
				    	 		swal("Good!", "关注成功！", "success");	
				    	 	$("tr:contains("+ma001+")").children("td").eq(7).html("<span style = 'font-weight:bold;color:green;'>已关注</span>");
				    		
				    	 	
				    	 	
				    	 	$("tr:contains("+ma001+")").children("td").eq(8).html("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\""+ma001+"\");' >取消关注</button><span style = 'display: none;' >"+ma001+"</span>");
				    		
				    	 	
				    	 		
				    	 	}
				    	 	if(data.code == 0){
				    	 		swal("OMG", "操作失败了!", "error");
				    	 	}
				    	  	
				      }
				    );
			
		});
}
function onSureStatus2(ma001){
	swal({
		title: "确定关注吗？", 
		text: "您确定要添加关注吗？", 
		type: "warning",
		showCancelButton: true,
		closeOnConfirm: false,
		animation: "slide-from-top",  
		confirmButtonText: "是的，我要关注",
		confirmButtonColor: "#ec6c62"
		}, function() {
				
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
				    	 		swal("Good!", "关注成功！", "success");	
				    		
				    	 	$("#doSearch").click();
				    	 	
				    		
				    	 	
				    	 		
				    	 	}
				    	 	if(data.code == 0){
				    	 		swal("OMG", "操作失败了!", "error");
				    	 	}
				    	  	
				      }
				    );
			
		});
}
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
				    	 		swal("Good!", "取消关注成功！", "success");	
				    	 	$("tr:contains("+ma001+")").children("td").eq(7).html("<span style = 'font-weight:bold;color:red;'>未关注</span>");
				    			
				    	 	$("tr:contains("+ma001+")").children("td").eq(8).html("<button class='btn btn-danger btn-flat' style = 'font-weight:bold;' onclick = 'onSureStatus(\""+ma001+"\");' >关注</button><span style = 'display: none;' >"+ma001+"</span>");
				    		
				    	 		
				    	 	}
				    	 	if(data.code == 0){
				    	 		swal("OMG", "操作失败了!", "error");
				    	 	}
				    	  	
				      }
				    );
			
		});
}
function onCancelStatus2(ma001){
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
				    	 		swal("Good!", "取消关注成功！", "success");	
				    			
								$("#doSearch").click();				    		
				    	 		
				    	 	}
				    	 	if(data.code == 0){
				    	 		swal("OMG", "操作失败了!", "error");
				    	 	}
				    	  	
				      }
				    );
			
		});
}
function checkbox_modify(){
	
	
	if($("input[name='items']:checked").length == 1){
	
	$("input[name='items']:checkbox").each(function () { 
		 if(this.checked) {
			
			 var str = $(this).attr('value'); 
			 
			 var url = "<%=basePath%>hwmobile/smart/corpcrm!doEdit?ACCOUNT="+localStorage.getItem("PHONE","")+"&SESSIONID="+localStorage.getItem("PASSWORD","")+"&MA001="+str;

			 window.location.href=url;
			 
			 
		}
	});
	}
	else{
		swal("OMG", "请只选中一项!", "error");
	}
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
$().ready(function(){
	
	$("#toggleSearch").click(function(){
		$("#boxSearch").toggle("slow");

	});
});

</script>
</head>

<body class="skin-blue sidebar-mini" style = "width:100%">

<header class="main-header"> 
  <!-- Logo --> 

  

  <!-- Header Navbar: style can be found in header.less -->
  <nav class="navbar navbar-static-top" role="navigation" style = "margin-left:0px;"> 
    <!-- Sidebar toggle button--> 
    
    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">
        <!-- User Account: style can be found in dropdown.less -->
        <li class="dropdown user user-menu"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <img src="<%=basePath%>public/toB/dist/img/user_admin.jpg" class="user-image" alt="User Image"> <span class="hidden-xs">空净厂商A</span> </a>
          <ul class="dropdown-menu">
            <!-- User image -->
            <li class="user-header"> <img src="<%=basePath%>public/toB/dist/img/user_admin.jpg" class="img-circle" alt="User Image">
              <p>空净厂商A <small>联系人：猫咪</small> <small>联系人手机号：110</small> </p>
            </li>
            <!-- Menu Footer-->
            <li class="user-footer">
              <div class="pull-left"> <a href="#" class="btn btn-default btn-flat">账号管理</a> </div>
              <div class="pull-right"> <a href="#" class="btn btn-default btn-flat">退出登录</a> </div>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>
</header>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" style = "margin-left:0px;"> 
  <!-- Content Header (Page header) -->
  <section class="content-header">
    <h1>设备管理</h1>
    <ol class="breadcrumb">
       <li> <a href="<%=basePath%>hwmobile/smart/corpbusiness!doIndex"><i class="fa fa-dashboard"></i> 首页</a></li>
	   <li class="active"><i class="fa fa-circle-o"></i>设备管理</li>
    </ol>
  </section>
  
  <!-- Main content -->
  <section class="content" >
  
  <!-- Main row -->
  <div class="row"> 
    
    <!-- col -->
    <section class="col-lg-12 connectedSortable"> 
      <!-- 设备管理 -->
      <div class="box box-success" style="position: relative; ">
        <div class="box-header with-border">
        
        <button class="btn btn-block btn-default" id = "toggleSearch" style = "width:120px">详细搜索</button>
          <div class="box-body" id = "boxSearch" style = "display:none;">
			<!-- search form -->
           
			  <!-- row -->
            <div class="row">
              <div class="col-sm-3">
			    <label>设备标识</label>
				<div class="input-group">
                      <div class="input-group-addon">
                        <i class="fa fa-bank"></i>
                      </div>
                      <input id="range_1" type="text"  value="" placeholder = "设备背后二维码" class = "form-control">
                </div><!-- /.input group -->
                
              </div>

              <div class="col-sm-3">
			  <label>联系人手机</label>
				<div class="input-group">
                      <div class="input-group-addon">
                        <i class="fa fa-phone"></i>
                      </div>
                      <input type="text" id = "range_2" class="form-control" placeholder = "用户的手机号" data-inputmask='"mask": "999-9999-8189"' data-mask/>
                </div><!-- /.input group -->
			  </div>

				<div class="col-sm-6">
				
						<div class="col-sm-4">
						 <label>位置</label>
							 <div class="input-group">
		                      <div class="input-group-addon">
		                        <i class="fa fa-map-marker"></i>
		                      </div>
						    <select id="seachprov" name="seachprov"  class="form-control" onChange="changeComplexProvince(this.value, sub_array, 'seachcity', 'seachdistrict');"></select>
						    	</div>
			            </div>
			            <div class="col-sm-4">
			             <label>&nbsp</label>
			            <div class="input-group">
		                      <div class="input-group-addon">
		                        <i class="fa fa-map-marker"></i>
		                      </div>
						    <select id="seachcity" name="homecity" class="form-control " onChange="changeCity(this.value,'seachdistrict','seachdistrict');"></select>
						    </div>
			            </div>
			            <div class="col-sm-4">
			            <label>&nbsp</label>
			            <div class="input-group">
			             
		                      <div class="input-group-addon">
		                        <i class="fa fa-map-marker"></i>
		                      </div>
						    <span id="seachdistrict_div"><select id="seachdistrict" class="form-control pull-left" name="seachdistrict"></select></span>
						    </div>
			            </div>
				
				
				
			  
              </div><!-- /. col-sm-6 -->


            </div><!-- /. row -->
			  <!-- row -->
            <div class="row">
              <hr>
			  <!-- 设备注册日期 ol-sm-3 -->
                  <div class="col-sm-6">
                    <label>设备注册日期:</label>
                    <div class="input-group">
                      <div class="input-group-addon">
                        <i class="fa fa-calendar"></i>
                      </div>
                      <input type="text" id = "range_4" class="form-control pull-right" />
                    </div><!-- /.input group -->
                  </div><!-- /.col-sm-3 设备注册日期-->


			  

              <div class="col-sm-3">
			  <label>收货人:</label>
                    <div class="input-group">
                      <div class="input-group-addon">
                        <i class="fa fa-user"></i>
                      </div>
                      <input type="text" id = "range_5" class="form-control pull-right"/>
                    </div><!-- /.input group -->
              </div><!-- /.col-sm-3 -->


			   
			   <div class="col-sm-3">
			    <div class="">&nbsp</div>
               <button id = "doSearch" class="btn btn-block btn-primary btn-sm">搜索</button>
              </div>
              
            </div>
          </div>
		  <!-- /. row -->	
		  <!--search form -->
		
		  <!-- row -->
          <div class="row margin">
            <div class="col-sm-5"> </div>
            <div class="col-sm-7">
              <div class="col-sm-2">
                <button class="btn btn-block btn-primary btn-sm">批量录入</button>
              </div>
              <div class="col-sm-2">
                <button class="btn btn-block btn-primary btn-sm"  id = "input_withhand">手动录入</button>
              </div>
              <div class="col-sm-2">
                <button class="btn btn-block btn-primary btn-sm"  id = "crm_update" onclick = "checkbox_modify();">修改</button>
              </div>
              <div class="col-sm-2">
                <button class="btn btn-block btn-primary btn-sm"  id = "crm_delete" >删除</button>
              </div>
              <div class="col-sm-2">
                <button class="btn btn-block btn-primary btn-sm">明细</button>
              </div>
              <div class="col-sm-2">
                <button class="btn btn-block btn-primary btn-sm">数据导出</button>
				
              </div>
            </div>
			<!-- /. row -->	
          </div>
        </div>
        <!-- /.box-header -->
        <div class="box-body" id = "table_crm">
        
       
        
        
        	
        	<table id="crm" class="table table-hover table-bordered table-striped">
            <thead>
              <tr>
				<th id = "table_head"></th>
                <th>设备标识</th>
                <th>手机号</th>
                <th>收货人</th>
                <th>省</th>
                <th>市</th>
                <th>设备注册日期</th>
                <th>关注状态</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
            <%-- document.write(Date.parse("${detail.ma004}")) --%>
            <c:forEach items="${corpCrmPOList}" var="crm">  
		        <tr>  
		            <td><INPUT name="items" type="checkbox" value ="${crm.ma001}" /></td>  
		            <td>${crm.ma002}</td>  
		            <td>${crm.ma005}</td> 
		            <td>${crm.ma004}</td>
		            <td>${crm.ma007}</td>
		            <td>${crm.ma008}</td>
		            <td><script>dateFormat('${crm.ma012}','yyyy-MM-dd')</script></td>
		            
		            <td><script>if(${crm.ma011} == 0) document.write("<span style = 'font-weight:bold;color:red;'>未关注</span>");if(${crm.ma011} == 1) document.write("<span style = 'font-weight:bold;color:green;'>已关注</span>");</script></td>
		            <td><script>if(${crm.ma011} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${crm.ma001}\");'>关注</button>");if(${crm.ma011} == 1) document.write("<button class='btn btn-default btn-flat' style = 'font-weight:bold;' onclick = 'onCancelStatus(\"${crm.ma001}\");' >取消关注</button>");</script><span style = "display: none;" >${crm.ma001} </span></td>
		        </tr>  
		    </c:forEach> 
              
              
            </tbody>
          </table>
        	
        	
        	
        	
        	
        	
        
        
        
        
		
		 <div style = "margin:10px;"><input id="chkAll" type="checkbox" onclick="ChkAllClick('items','chkAll')">全选</div>
        </div><!-- /.box-body -->
		
      </div>
      <!-- /.box --> 
    </section>
    <!-- right col --> 
  </div>
  <!-- /. row --> 
</div>
<!-- /.content-wrapper-->
<footer class="main-footer" style = "margin-left:0px;">
  <div class="pull-right hidden-xs"> <b>Version</b> 2.0 </div>
  <strong>Copyright &copy; 2014-2015 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All rights reserved. </footer>




<!-- 获取所有省份的城市 -->
<script src="performance/dist/js/Area.js" type="text/javascript"></script>
<script src="performance/dist/js/AreaData_min.js" type="text/javascript"></script>


<!-- jquery-ui-1.11.2.min.js --> 
<script src="performance/plugins/jQueryUI/jquery-ui-1.11.2.min.js" type="text/javascript"></script> 

<!-- Bootstrap 3.3.2 JS --> 
<script src="performance/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js" type="text/javascript"></script> 
<!-- datepicker --> 
<script src="performance/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script> 
<!-- datepicker --> 
<script src="performance/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script> 

<!-- Bootstrap WYSIHTML5
<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>   
<!-- Slimscroll --> 
<script src="performance/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script> 
<!-- FastClick --> 
<script src='performance/plugins/fastclick/fastclick.min.js'></script> 
<!-- AdminLTE App --> 
<script src="performance/dist/js/app.min.js" type="text/javascript"></script> 
<!-- AdminLTE for demo purposes -->
<script src="performance/dist/js/demo.js" type="text/javascript"></script>

<!-- DATA TABES SCRIPT 曾凡 20150626 污染排名--> 

<script src="performance/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script> 

<!-- DATA TABES SCRIPT 曾凡 20150626 污染排名--> 

<script src="performance/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script> 

<!-- page script 曾凡 20150626 污染排名 --> 


<script>
$(function (){
	initComplexArea('seachprov', 'seachcity', 'seachdistrict', area_array, sub_array, '0', '0', '0');
	
	
$("#doSearch").click(function(){
		
		var time = $("#range_4").val();
		time = time.replace(/\s/g, "");
		var array = time.split("-");
		
		var location = showAreaID();
		location = location.split(" ");
		
		$.post('<%=basePath%>hwmobile/smart/corpcrm!doSearch',
			      {
					ACCOUNT : localStorage.getItem('PHONE',''),
					SESSIONID : localStorage.getItem('PASSWORD',''),
					MA002 : $("#range_1").val(),
					TEL : $("#range_2").val(),
					MA004:$("#range_5").val(),
					timeB:array[0],
					timeE:array[1],
					pro: location[0],
					city:location[1],
					district:location[2]
			      },
			      function (data,status) //回传函数
			      {
			    	  
			    	 	if(data.code == 1){
			    	 		
			    	 		if(data.count != 0){
			    	 			//alert(data.dataObject[0].ma002);
			    	 			$("#table_crm").html("");
			    	 			$("#table_crm").append('<table id="crmNew" class="table table-hover table-bordered table-striped"><thead>  <tr><th id = "table_head"></th><th>设备标识</th><th>手机号</th><th>收货人</th><th>省</th><th>市</th><th>设备注册日期</th><th>关注状态</th><th></th>  </tr></thead><tbody id = "tbodyNew"></tbody></table>');
			    	 			
			    	 			for(var i=0;i<data.dataObject.length;i++){
			    	 				
			    	 				var time1 = dateFormat2(data.dataObject[i].ma012.time,'yyyy-MM-dd');
				    	 			
				    	 			
			    	 				$("#tbodyNew").append("<tr style = 'height:15.5px;padding-bottom:5px'>");
			    	 				$("#tbodyNew").append('<td><INPUT name="items" type="checkbox" value ="'+data.dataObject[i].ma002+'"/></td>');
			    	 				$("#tbodyNew").append('<td>'+data.dataObject[i].ma002+'</td>');
			    	 				$("#tbodyNew").append('<td>'+data.dataObject[i].ma005+'</td>');
			    	 				$("#tbodyNew").append('<td>'+data.dataObject[i].ma004+'</td>');
			    	 				$("#tbodyNew").append('<td>'+data.dataObject[i].ma007+'</td>');
			    	 				$("#tbodyNew").append('<td>'+data.dataObject[i].ma008+'</td>');
			    	 				
			    	 				$("#tbodyNew").append('<td>'+time1+'</td>');
			    	 			
				    	 			if(data.dataObject[i].ma011 == 0)  $("#tbodyNew").append('<td><span style = "color:red">未关注</span></td>'+'<td><input type="button" class="btn btn-danger btn-flat" value="关注" onClick="onSureStatus2(\''+data.dataObject[i].ma001+'\')"><span style = "display: none;" >'+data.dataObject[i].ma001+'</span></td>');
				    	 			if(data.dataObject[i].ma011 == 1) $("#tbodyNew").append('<td><span style = "color:green">已关注</span></td>'+'<td><input type="button" class="btn btn-default btn-flat" value="取消关注" onClick="onCancelStatus2(\''+data.dataObject[i].ma001+'\')"><span style = "display: none;" >'+data.dataObject[i].ma001+'</span></td>');
				    	 			
				    	 			
				    	 			//$("#searchTbody").append('');
			    	 				$("#tbodyNew").append("</tr>");
			    	 				}
			    	 			//$("#table_crm").append("</tbody></table>");
			    	 			
			    	 			//$("#crmNew").dataTable();
			    	 			//alert($("#table_crm").html());
			    	 			
			    	 			
			    	 		}
			    	 		else
			    	 			{
			    	 			swal("OMG", "查询结果  0", "warning");
			    	 			}
			    	 		
			    	 		
			    	 		
			    	 	}
			    	 	if(data.code == 0){
			    	 		swal("OMG", "添加操作失败了!", "error");
			    	 	}
			    	  	
			      }
			    );
		
	});
	
	
});

//得到地区码
function getAreaID(){
	var area = 0;          
	if($("#seachdistrict").val() != "0"){
		area = $("#seachdistrict").val();                
	}else if ($("#seachcity").val() != "0"){
		area = $("#seachcity").val();
	}else{
		area = $("#seachprov").val();
	}
	return area;
}

function showAreaID() {
	//地区码
	var areaID = getAreaID();
	//地区名
	var areaName = getAreaNamebyID(areaID) ;
	return areaName;
	alert("您选择的地区码：" + areaID + "      地区名：" + areaName);            
}

//根据地区码查询地区名
function getAreaNamebyID(areaID){
	var areaName = "";
	if(areaID){
	if(areaID.length == 2){
		areaName = area_array[areaID];
	}else if(areaID.length == 4){
		var index1 = areaID.substring(0, 2);
		areaName = area_array[index1] + " " + sub_array[index1][areaID];
	}else if(areaID.length == 6){
		var index1 = areaID.substring(0, 2);
		var index2 = areaID.substring(0, 4);
		areaName = area_array[index1] + " " + sub_array[index1][index2] + " " + sub_arr[index2][areaID];
	}
	}
	return areaName;
}
</script>

<script>
				 $(function () {
				 //Date range picker
					$('#range_4').daterangepicker();
					//$('#toggleNavigation').();
					$("#crm").dataTable();
			/*		$('#crm').dataTable({
					  "bPaginate": true,
					  "bLengthChange": false,
					  "bFilter": false,
					  "bSort": true,
					  "bInfo": true,
					  "bAutoWidth": false
					}); 
			  */


			
			//全选的位置
			//alert($("#table_head").postion());


		

				
			//手动录入加入点击跳转
				$("#input_withhand").click(function(){
					var url = "<%=basePath%>hwmobile/smart/corpcrm!doAdd?ACCOUNT="+localStorage.getItem("PHONE","")+"&SESSIONID="+localStorage.getItem("PASSWORD","");
				    //alert(url);
					window.location.href=url; 
						});
			//crm  删除
				 $("#crm_delete").click(function(){
					
						
					 	var ids = "";
					 	$("input[name='items']:checkbox").each(function () {
					 		if(this.checked) {
								
								 ids += $(this).attr('value')+","; 
					 		
					 	}
					 	});
					 	swal({
							title: "您确定要删除吗？", 
							text: "您确定要删除数据？", 
							type: "warning",
							showCancelButton: true,
							closeOnConfirm: false,
							animation: "slide-from-top",  
							confirmButtonText: "是的，我要删除",
							confirmButtonColor: "#ec6c62"
							}, function() {
					 
						
						$.post('<%=basePath%>hwmobile/smart/corpcrm!doDelete',
							      {
									ACCOUNT : localStorage.getItem('PHONE',''),
									SESSIONID : localStorage.getItem('PASSWORD',''),
									ids : ids
							      },
							      function (data,status) //回传函数
							      {
							    	  
							    	 	//alert(data.code);
							    	 	if(data.code == 1){
							    	 		swal({
							    				title: "操作成功", 
							    				text: "已成功删除这条数据！", 
							    				type: "success",
							    				showCancelButton: false,
							    				closeOnConfirm: false,
							    				animation: "slide-from-top",  
							    				confirmButtonText: "确定",
							    				confirmButtonColor: "#ec6c62"
							    				}, function() {
							    					var url = "<%=basePath%>hwmobile/smart/corpcrm!doList?ACCOUNT="+localStorage.getItem("PHONE","")+"&SESSIONID="+localStorage.getItem("PASSWORD","");
							    				    //alert(url);
							    					window.location.href=url;
							    				});
							    	 		
							    	 	
							    	 		
							    	 	}
							    	 	if(data.code == 0){
							    	 		swal("OMG", "删除操作失败了!", "error");
							    	 	}
							    	  	
							      }
							    );
							});
				}); 
			
			
			
			
				});
			

			
// --列头全选框被单击--- 
function ChkAllClick(sonName, cbAllId){ 
		var arrSon = document.getElementsByName(sonName); 
		var cbAll = document.getElementById(cbAllId); 
		var tempState=cbAll.checked; 
		for(i=0;i<arrSon.length;i++) { 
				if(arrSon[i].checked!=tempState) 
				arrSon[i].click(); 
				} 
	} 

</script>
</body>
</html>