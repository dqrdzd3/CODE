<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
<!-- Date Picker --
    <link href="plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />-->
<!-- Daterange picker 
    <link href="plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />-->
<!-- bootstrap wysihtml5 - text editor -->
<link href="<%=basePath%>public/toB/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />

<!-- DATA TABLES 曾凡 20150626 污染排名 -->
<link href="<%=basePath%>public/toB/plugins/datatables/dataTables.bootstrap.css" rel="stylesheet"
        type="text/css">


<!-- DATApickerS  --
<link href="plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <!-- jQuery 1.9.1 --> 
<script src="<%=basePath%>public/toB/plugins/jQuery/jquery-1.9.1.min.js"></script>
 
 <script>
 
 


 $(function(){
	 
	 $('.breadcrumb li:first').click(function(){
			back2home();
		});
	 
		$("#cancel").click(function(){
			back2home();
		});
		
 });
//手机号验证
 function checkMobile( s ){   
		var re = /^[1-9]\d{4,10}$/i; 
		if(s){
		if (re.test(s)) { 
		return true; 
		}else{ 
		return false; 
		} 
		}
		} 
//邮箱验证 
 function checkEmail(str){
    var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
    if(str){
    if(re.test(str)){
        return true;
    }else{
        return false;
    }
    }
}
 //输入框验证
 function doValidate(){
	 	var businessPOma003 = $("#businessPOma003").val();
		var businessPOma004 = $("#businessPOma004").val();
		var businessPOma005 = $("#businessPOma005").val();
		var businessPOma007 = $("#businessPOma007").val();
		var businessPOma014 = $("#businessPOma014").val();
		
		
		
		if(businessPOma003.length > 16){
			$("#message").html("请输入16位以下的联系人！！");
			return false;
		}
		if(businessPOma004.length > 16){
			$("#message").html("请输入11位的联系方式！！");
			return false;
		}

		if(checkMobile(businessPOma004) == false){
			$("#message").html("请输入11位的联系方式！！");
			return false;
		}
			
		
		if(businessPOma005.length > 64){
			$("#message").html("请输入64位以下的通讯地址！！");
			return false;
		}
		if(businessPOma007.length > 16){
			$("#message").html("请输入11位的法人联系！！");
			return false;
		}

		if(checkMobile(businessPOma007) == false){
			$("#message").html("请输入11位的法人联系！！");
			return false;
		}
		
		
		if(businessPOma014.length > 16){
			$("#message").html("请输入16位以下的邮箱！！");
			return false;
		}
		
		if(checkEmail(businessPOma014) == false){
			$("#message").html("请输入规范的邮箱格式！！");
			return false;
		}
		
		
		else
			return true;
		
	}
 
 function saveDevice(){
	
	 	//输入框验证
	 	if(!doValidate())
			return;
		
			 var param = $('#updataDevice').serialize();
			var url = '<%=basePath%>hwmobile/smart/corpbusiness!doSaveEdit';
			$.post(url,param,function(data){
				
				if(data.code == 1)
					{
					$("#message").html("");
					$("#message").html("修改成功！");
					}
				if(data.code == 0)
					{
					$("#message").html("");
					$("#message").html("修改失败");
					}
				
			}); 
		
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

<body class="skin-blue sidebar-mini">
<div class="wrapper">
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
<jsp:include page="../system/topSmall.jsp"></jsp:include>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" style = "margin-left:0px;">
<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>首页</h1>
  <ol class="breadcrumb">
    <li> <a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
	 <li class="active"><i class="fa fa-circle-o" style = "margin-right:5px;"></i>账号管理</li>
  </ol>
</section>




<!-- Main content -->
<section class="content">

<!-- Main row -->
<div class="row"> 

  <!-- col -->
  <section class="col-lg-12 connectedSortable"> 
    <!-- 手动录入 -->
    
	<div class="box box-primary">
                <div class="box-header">
                  <h3 class="box-title">账号管理</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form role="form"  method="post" name = "form1"  id="updataDevice">
                  <div class="box-body">
                    <div class="row margin">
					<div class="col-sm-2">
					</div>
					<div class="col-sm-8">
							<div class="col-sm-6">
							  <label >公司名</label>
								</div>
								<div class="col-sm-6">
							<input type="hidden"  id = "businessPO.ma001" name="businessPO.ma001" value = "${ businessPO.ma001}"/>
							  <input type="text" class="form-control" id = "businessPO.ma002" name="businessPO.ma002" placeholder="公司名" value = "${ businessPO.ma002}" readonly="readonly">
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
							  <label>联系人</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" id = "businessPOma003" name="businessPO.ma003" placeholder="联系人" value = "${ businessPO.ma003}">
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
							  <label >联系方式</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" id = "businessPOma004" name="businessPO.ma004" placeholder="联系方式"  value = "${ businessPO.ma004}">
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
							  <label >通讯地址</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" id = "businessPOma005" name="businessPO.ma005" placeholder="通讯地址" value = "${ businessPO.ma005}">
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
							  <label >法人</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" id = "businessPO.ma006" name="businessPO.ma006" placeholder="法人" value = "${ businessPO.ma006}" readonly="readonly">
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
							  <label >法人联系</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" id = "businessPOma007" name="businessPO.ma007" placeholder="法人联系" value = "${ businessPO.ma007}">
							  </div>
					</div>
					<div class="col-sm-2">
					</div>
                    </div>
                    <div class="row margin" style = "display: none;">
                      <div class="col-sm-2">
					</div>
					<div class="col-sm-8">
							<div class="col-sm-6">
							  <label ></label>
								</div>
								<div class="col-sm-6">
							 
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
							  <label >邮箱</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" id = "businessPOma014" name="businessPO.ma014"  placeholder="邮箱" value = "${ businessPO.ma014}">
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
							  <label >LOGO</label>
								</div>
								<div class="col-sm-6">
							  <img src="data:image/png;base64,${ businessPO.ma019}"  width="64px"/>
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
							  <label >LOGO</label>
								</div>
								<div class="col-sm-6">
							<img src="data:image/png;base64,${ businessPO.ma020}" width="512px"/>
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
										
										<button type="button"  onclick="saveDevice()" class="btn btn-primary">保存</button>
										</div>
										<div class="col-sm-3">
										<button type="button" id = "cancel" class="btn btn-primary">取消</button>
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
</div>
<!-- /.content-wrapper-->





  
 



<footer class="main-footer " style = "margin-left:0px;">
  <div class="pull-right hidden-xs "> <b>Version</b> 2.0 </div>
  <strong>Copyright © 2014-2015 <a href="http://www.airradio.cn/ ">北京威果智能科技有限公司</a>.</strong> All rights reserved. </footer>

<!-- ./wrapper --> 
<!-- jQuery 2.1.4 --> 

<!-- jquery-ui-1.11.2.min.js --> 

<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip --> 



<!-- Bootstrap 3.3.2 JS --> 
<script src="<%=basePath%>public/toB/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
<!-- Morris.js charts --> 
<script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script> 
<script src="<%=basePath%>public/toB/plugins/morris/morris.min.js" type="text/javascript"></script> 
<!-- Sparkline --> 
<script src="<%=basePath%>public/toB/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script> 
<!-- jvectormap --> 
<script src="<%=basePath%>public/toB/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>public/toB/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script> 
<!-- jQuery Knob Chart 
<script src="plugins/knob/jquery.knob.js" type="text/javascript"></script> 
<!-- daterangepicker 
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js" type="text/javascript"></script>
    <script src="plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>--> 
<!-- datepicker --
    <script src="plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>--> 
<!-- datepicker --
<script src="plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>--> 

<!-- Bootstrap WYSIHTML5 --> 
<script src="<%=basePath%>public/toB/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script> 
<!-- Slimscroll --> 
<script src="<%=basePath%>public/toB/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script> 
<!-- FastClick --> 
<script src='<%=basePath%>public/toB/plugins/fastclick/fastclick.min.js'></script> 
<!-- AdminLTE App --> 
<script src="<%=basePath%>public/toB/dist/js/app.min.js" type="text/javascript"></script> 

<!-- AdminLTE dashboard demo (This is only for demo purposes)
    <script src="dist/js/pages/dashboard.js" type="text/javascript"></script>    --> 

<!-- AdminLTE for demo purposes 
    <script src="dist/js/demo.js" type="text/javascript"></script>--> 

<!-- DATA TABES SCRIPT 曾凡 20150626 污染排名

<script src="plugins/datatables/jquery.dataTables.min.js" type="text/javascript"></script> 

<!-- DATA TABES SCRIPT 曾凡 20150626 污染排名

<script src="plugins/datatables/dataTables.bootstrap.min.js" type="text/javascript"></script> 

<!-- page script 曾凡 20150626 污染排名 --> 




</body>
</html>