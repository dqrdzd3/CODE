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
<!-- Bootstrap 3.3.4 -->
<link href="<%=basePath%>public/toB/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- FontAwesome 4.3.0 -->
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons 2.0.0 -->
  <!-- iCheck for checkboxes and radio inputs -->
    <link href="<%=basePath%>public/toB/plugins/iCheck/all.css" rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link href="<%=basePath%>public/toB/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
<!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
<link href="<%=basePath%>public/toB/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
<!-- iCheck -->
<link href="<%=basePath%>public/toB/plugins/iCheck/flat/blue.css" rel="stylesheet" type="text/css" />

<!-- Date Picker -->
<link href="<%=basePath%>public/toB/plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />

<!-- DATA TABLES 曾凡 20150626 污染排名 -->
<link href="<%=basePath%>public/toB/plugins/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css">

<!-- DATApickerS  -->
<link href="<%=basePath%>public/toB/plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css">


<!-- jQuery 1.9.1 --> 
<script src="<%=basePath%>public/toB/plugins/jQuery/jquery-2.1.4.min.js"></script> 

<!-- 模态窗口 --> 
<script src="<%=basePath%>public/toB/dist/css/sweetalert.min.js"></script> 

<link href="<%=basePath%>public/toB/dist/css/sweetalert.css" rel="stylesheet" type="text/css">



<!-- 导入验证类库  -->
<script src="<%=basePath%>public/validate/jquery.js"></script>
<script src="<%=basePath%>public/validate/jquery.validate.js"></script>
<script src="<%=basePath%>public/validate/messages_cn.js"></script>
<script src="<%=basePath%>public/validate/additional.js"></script>




<style>
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


<script>
var index = 1;

$().ready(function(){
	$("#subMaterialType").hide();
	
	$("#crm_back").click(function(){
		back2crm();
	});
	
	$("#goback").click(function(){
		back2crm();
	});
	
	$("#addMaterialType").click(function(){
		index++;
		$("#form_append").append('<div class="row margin"><div class="col-sm-2"></div><div class="col-sm-8"><div class="col-sm-6"><label >耗材类型'+index+'</label></div><div class="col-sm-6"><select id = "ids" name = "ids" class="form-control ids"><option>=请选择=</option><c:forEach items="${materialPOList }" var="material"><option value ="${material.ma001}">${material.ma002}(${material.ma003})</option></c:forEach></select></div></div><div class="col-sm-2"></div> </div>');
		if($("#form_append").children(".margin").length>9){
		$("#subMaterialType").show();
		}
		
	});
	$('.breadcrumb li:first').click(function(){
		back2home();
	});
	$("#subMaterialType").click(function(){
		index--;
		$("#form_append").children(".margin").last().remove();
		if($("#form_append").children(".margin").length<10){
		$("#subMaterialType").hide();
			}
		
	});
});

function checkMobile( s ){   
	var re = /^[1-9]\d{10,10}$/i; 
	if(s){
	if (re.test(s)) { 
	return true; 
	}else{ 
	return false; 
	} 
	}
	} 
function doValidate(){
	var corpCrmPOma002 = $("#corpCrmPOma002").val();
	var corpCrmPOma004 = $("#corpCrmPOma004").val();
	var corpCrmPOma005 = $("#corpCrmPOma005").val();
	var corpCrmPOma006 = $("#corpCrmPOma006").val();
	var corpCrmPOma0013 = $("#corpCrmPOma0013").val();
	
	
	
	if(corpCrmPOma004.length > 16){
		$("#message").html("请输入16位以下的收货人！！");
		return false;
	}
	if(corpCrmPOma005.length > 16){
		$("#message").html("请输入16位以下的手机号！！");
		return false;
	}

	if(checkMobile(corpCrmPOma005) == false){
		$("#message").html("请输入11位的手机号！！");
		return false;
	}
		
	
	if(corpCrmPOma005.length > 16){
		$("#message").html("请输入16位以下的手机号！！");
		return false;
	}
	if(corpCrmPOma006.length > 128){
		$("#message").html("请输入128位以下的通讯地址！！");
		return false;
	}
	if(corpCrmPOma0013){
	if(corpCrmPOma0013.length > 256){
		$("#message").html("请输入256位以下的备注！！");
		return false;
	}
	}
	
	else
		return true;
	
}

function doSaveAdd(){
	
	 if(!doValidate())
		return;
	 
	 
	
	 $(".confirm").addClass("loading2");
		$(".confirm").html("加载中...请稍候");
		$(".confirm").attr("disabled", true);
	
	if($("input:first").val()){
	
	
	
	 var str = showAreaID();
	var array = str.split(" ");
	//alert(array);
	
	if(($("#seachprov").val() == 11) || ($("#seachprov").val() == 12) || ($("#seachprov").val() == 31) || ($("#seachprov").val() == 50) || ($("#seachprov").val() == 81) ||($("#seachprov").val() == 82)){
		$("#corpCrmPOma007").val(array[0]);
		
		$("#corpCrmPOma008").val(array[0]);
		$("#corpCrmPOma009").val(array[1]);
	}else{
		$("#corpCrmPOma007").val(array[0]);
		
		$("#corpCrmPOma008").val(array[1]);
		$("#corpCrmPOma009").val(array[2]);
		
	}
	
	
	
	
	
	
	
	
	
	var ID = getAreaID();
	
	
	$("#corpCrmPOma015").val(ID);
	
	
	//var x = $('.ids').val();
	
	
	var param = $('#form_crm').serialize();
	var url = "<%=basePath%>hwmobile/smart/corpcrm!doSaveAdd?ACCOUNT="+localStorage.getItem("PHONE","")+"&SESSIONID="+localStorage.getItem("PASSWORD","");

	
	$.post(url,param,function(data){
		
		
		if(data.code == 1){
			$("#loading1").hide();
			$(".confirm").removeClass("loading2");
			//$(".confirm").html("加载中...请稍候");
			$(".confirm").attr("disabled", false);
			$("#message").html("");
			swal("操作成功", "已成功添加操作", "success");
			$("input").val("");
	 	}
	 	if(data.code == 0){
	 		$("#loading1").hide();
	 		$(".confirm").removeClass("loading2");
			//$(".confirm").html("加载中...请稍候");
			$(".confirm").attr("disabled", false);
	 		$("#message").html("");
	 		var str = "";
	 		for(var i=0;i<data.dataObject.length;i++){
	 			if(i == data.dataObject.length-1){
	 				str += data.dataObject[i];
	 			}else{
	 				str += data.dataObject[i]+"，";
	 			}
	 			
	 		}
	 		swal("添加异常", str+" 设备添加失败!", "error");
	 	}
		
	});
	
	}else{
		$("#loading1").hide();
		$(".confirm").removeClass("loading2");
		//$(".confirm").html("加载中...请稍候");
		$(".confirm").attr("disabled", false);
		$("#message").html("");
		swal("OMG", "请输入设备标识", "error");
	}

	
//}
}

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

 
   

</script>
</head>

<body class="skin-blue sidebar-mini" style = "width:100%">
<div id="loading1"  style="display:none"><center><img src="<%=basePath%>public/loading.gif" />Loading pages...</center></div>

<%-- <header class="main-header"> 
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
</header> --%>

<jsp:include page="../topSmall.jsp"></jsp:include>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" style = "margin-left:0px;"> 
  <!-- Content Header (Page header) -->
  <section class="content-header">
    <h1>设备管理</h1>
    <ol class="breadcrumb">
       <li> <a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
	   <li ><a id = "crm_back" style = "cursor:pointer;"><i class="fa fa-circle-o" style = "margin-right:5px;"></i>设备管理</a></li>
	   <li class="active"><i class="fa fa-circle-o" style = "margin-right:5px;"></i>设备手动录入</li>
    </ol>
  </section>
  
  <!-- Main content -->
  <section class="content" >
  
  <!-- Main row -->
  <div class="row"> 
    
    <!-- col -->
     <section class="col-lg-12 connectedSortable"> 
    <!-- 手动录入 -->
    
	<div class="box box-primary">
                <div class="box-header">
                  <h3 class="box-title">手动录入</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form role="form" method="post" name = "form1" id = "form_crm" >
                  <div class="box-body" id = "form_append">
                    <div class="row margin">
					<div class="col-sm-2">
					</div>
					<div class="col-sm-8">
							<div class="col-sm-6">
							  <label>设备标识</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" id = "corpCrmPOma002" name = "corpCrmPO.ma002" class="form-control"  placeholder="设备标识">
							  </div>
					</div>
					<div class="col-sm-2">
					</div>
								
                    </div>
                   <div class="row margin">
                      <div class="col-sm-2">
					</div>
					<div class="col-sm-8">
							<div class="col-sm-6">
							  <label >收货人</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" id = "corpCrmPOma004" name = "corpCrmPO.ma004" class="form-control"  placeholder="收货人">
							  </div>
					</div>
					<div class="col-sm-2">
					</div>
                    </div>
                   <div class="row margin">
                      <div class="col-sm-2">
					</div>
					<div class="col-sm-8">
							<div class="col-sm-6">
							  <label>手机</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" id = "corpCrmPOma005" name = "corpCrmPO.ma005" class="form-control"  placeholder="手机">
							  </div>
					</div>
					<div class="col-sm-2">
					</div>
                    </div>
					<div class="row margin">
                      <div class="col-sm-2">
					</div>
					<div class="col-sm-8">
							<div class="col-sm-6">
							  <label>通讯地址</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" id = "corpCrmPOma006" name = "corpCrmPO.ma006" placeholder="通讯地址">
							  </div>
					</div>
					<div class="col-sm-2">
					</div>
                    </div>
					
	
	
					<div class="row margin">
                      <div class="col-sm-2">
					</div>
					<div class="col-sm-8">
							<div class="col-sm-6">
							  <label>省</label>
								</div>
								<div class="col-sm-6">
							<select id="seachprov" name="seachprov"  class="form-control" onChange="changeComplexProvince(this.value, sub_array, 'seachcity', 'seachdistrict');"></select>
							  </div>
					</div>
					<div class="col-sm-2">
					</div>
                    </div>
					<div class="row margin">
                      <div class="col-sm-2">
					</div>
					<div class="col-sm-8">
							<div class="col-sm-6">
							  <label >市</label>
								</div>
								<div class="col-sm-6">
							<select id="seachcity" name="homecity" class="form-control " onChange="changeCity(this.value,'seachdistrict','seachdistrict');">
								<option value="0">请选择</option>
							</select>
							  </div>
					</div>
					<div class="col-sm-2">
					</div>
                    </div>
                    <span id="seachdistrict_div">
                    <div class="row margin">
                      <div class="col-sm-2">
					</div>
					<div class="col-sm-8">
							<div class="col-sm-6">
							  <label >区</label>
								</div>
								<div class="col-sm-6">
								 <select id="seachdistrict" class="form-control pull-left" name="seachdistrict"></select>
							  </div>
					</div>
					<div class="col-sm-2">
					</div>
                    </div>
                    
                    </span><!-- ./seachdistrict_div -->
					<input type = "text" style = "display: none;" id = "corpCrmPOma007" name = "corpCrmPO.ma007"/>
					<input type = "text" style = "display: none;" id = "corpCrmPOma008" name = "corpCrmPO.ma008"/>
					<input type = "text" style = "display: none;" id = "corpCrmPOma009" name = "corpCrmPO.ma009"/>
					<input type = "text" style = "display: none;" id = "corpCrmPOma015" name = "corpCrmPO.ma015"/>
					
					<div class="row margin">
                      <div class="col-sm-2">
					</div>
					<div class="col-sm-8">
							<div class="col-sm-6">
							  <label >备注</label>
								</div>
								<div class="col-sm-6">
							 <textarea class="form-control" id = "corpCrmPOma013" name = "corpCrmPO.ma013" rows="3" placeholder="备注 ..."></textarea>
							  </div>
					</div>
					<div class="col-sm-2">
					</div>
                    </div>
					<div class="row margin">
                      <div class="col-sm-2">
					</div>
					<div class="col-sm-8">
							<div class="col-sm-6">
							  <label >是否关注</label>
								</div>
								<div class="col-sm-6">
											是
											<input type="radio" name = "corpCrmPO.ma011" id= "corpCrmPO.ma011" value=1 checked="checked">
											
											否
											<input type="radio" name = "corpCrmPO.ma011" id= "corpCrmPO.ma011" value=0 >
											
							  </div>
					</div>
					<div class="col-sm-2">
					</div>
                    </div>

					<div class="row margin">
                      <div class="col-sm-2">
					</div>
					<div class="col-sm-8">
							<div class="col-sm-6">
							  <label >耗材类型1</label>
								</div>
								<div class="col-sm-6">
							 		<select  id = "ids" name = "ids" class="form-control ids">
							 		<option value = "-1" >=请选择=</option>
							 			<c:forEach items="${materialPOList }" var="material">  
										        
										        <option value ="${material.ma001}">${material.ma002}(${material.ma003}天)</option>
										            
										</c:forEach>  
						 		 </select> 
						    	</div>
					</div>
					<div class="col-sm-2">
					 <button type="button" id = "addMaterialType"  class="btn btn-default btn-flat">+</button>
					 <button type="button" id = "subMaterialType"  class="btn btn-default btn-flat">-</button>
						
					</div>
                    </div>
                    
                    
                    	
                    </div>
                    	<div class="row margin">
                      <div class="col-sm-2">
					</div>
					<div class="col-sm-8">
							<div class="col-sm-6">
							  </div>
								<div class="col-sm-6">
							 		<div id="message" style = "color: #f00;">
							 		
									</div>
						    	</div>
					
					<div class="col-sm-2">
					 	
					</div>
                    </div>
                    
                    
                    
                  </div><!-- /.box-body -->
				</form>
                  <div class="box-footer">
				  <div class="col-sm-2">
				  </div>
				  <div class="col-sm-8">
								<div class="col-sm-6">
								</div>
								<div class="col-sm-6">
										<div class="col-sm-4">
										
				
										</div>
										<div class="col-sm-3">
										<button onclick="doSaveAdd();" class="btn btn-primary">保存</button>
										</div>
										<div class="col-sm-3">
										<button id = "goback" class="btn btn-primary">返回</button>
										</div>
								</div>
				  </div>
				  <div class="col-sm-2">
				  </div>
                    
                  </div>
                
              </div>

  </section>
    <!-- right col --> 
  </div>
  <!-- /. row --> 
  </section>
</div>
<!-- /.content-wrapper-->
<footer class="main-footer" style = "margin-left:0px;">
  <div class="pull-right hidden-xs"> <b>Version</b> 2.0 </div>
  <strong>Copyright &copy; 2014-2015 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All rights reserved. </footer>

<!-- jquery-ui-1.11.2.min.js --> 
<script src="<%=basePath%>public/toB/plugins/jQueryUI/jquery-ui-1.11.2.min.js" type="text/javascript"></script> 




<!-- Bootstrap 3.3.2 JS --> 
<script src="<%=basePath%>public/toB/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>public/toB/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>

<!-- InputMask -->
    <script src="<%=basePath%>public/toB/plugins/input-mask/jquery.inputmask.js" type="text/javascript"></script>
    <script src="<%=basePath%>public/toB/plugins/input-mask/jquery.inputmask.date.extensions.js" type="text/javascript"></script>
    <script src="<%=basePath%>public/toB/plugins/input-mask/jquery.inputmask.extensions.js" type="text/javascript"></script>

 
<!-- datepicker --> 
<script src="<%=basePath%>public/toB/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script> 
<!-- datepicker --> 
<script src="<%=basePath%>public/toB/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script> 

<!-- Bootstrap WYSIHTML5
<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>   
<!-- Slimscroll --> 
<script src="<%=basePath%>public/toB/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script> 
    <!-- iCheck 1.0.1 -->
    <script src="<%=basePath%>public/toB/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
    <!-- FastClick -->
    <script src='<%=basePath%>public/toB/plugins/fastclick/fastclick.min.js'></script><!-- AdminLTE App --> 
<script src="<%=basePath%>public/toB/dist/js/app.min.js" type="text/javascript"></script> 
<!-- AdminLTE for demo purposes -->
<script src="<%=basePath%>public/toB/dist/js/demo.js" type="text/javascript"></script>






<!-- 获取所有省份的城市 -->
<script src="<%=basePath%>public/toB/dist/js/Area.js" type="text/javascript"></script>
<script src="<%=basePath%>public/toB/dist/js/AreaData_min.js" type="text/javascript"></script>


<script>
$(function (){
	$("[data-mask]").inputmask();
	initComplexArea('seachprov', 'seachcity', 'seachdistrict', area_array, sub_array, '0', '0', '0');
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
	//alert("您选择的地区码：" + areaID + "      地区名：" + areaName);            
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
					$('#sellDate').daterangepicker();
					//$('#toggleNavigation').();
					//$("#crm").dataTable();
			/*		$('#crm').dataTable({
					  "bPaginate": true,
					  "bLengthChange": false,
					  "bFilter": false,
					  "bSort": true,
					  "bInfo": true,
					  "bAutoWidth": false
					}); 
			  */
				 }); 

			
			//全选的位置
			//alert($("#table_head").postion());


		

				
			

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