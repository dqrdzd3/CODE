<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println("basePath="+basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>报表数据统计系统</title>
<base href="<%=basePath%>">
<!--start  -->
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<!-- Bootstrap 3.3.4 -->
<link href="performance/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- FontAwesome 4.3.0 -->
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons 2.0.0 -->
<link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link href="performance/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
<!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
<link href="performance/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
<!-- iCheck -->
<link href="performance/plugins/iCheck/flat/blue.css" rel="stylesheet" type="text/css" />
<!-- Morris chart -->
<link href="performance/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
<!-- jvectormap
    <link href="plugins/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" /> -->
<!-- Date Picker --
    <link href="plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />-->
<!-- Daterange picker 
    <link href="plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />-->
<!-- bootstrap wysihtml5 - text editor -->
<link href="performance/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />

<!-- DATA TABLES 曾凡 20150626 污染排名 -->
<link href="performance/plugins/datatables/dataTables.bootstrap.css" rel="stylesheet"
        type="text/css">
        
<!-- jQuery 1.9.1 --> 
<script src="performance/plugins/jQuery/jquery-1.9.1.min.js"></script> 
<!-- end -->

<!-- jquery验证所需js -->
<script src="performance/validate/jquery.validate.js"></script>
<script src="performance/validate/additional.js"></script>
<script src="performance/validate/messages_cn.js"></script>
<script src="performance/validate/metadata.js"></script>

<!-- js by self -->
<script type="text/javascript">
	var _fm = document.fm;
	
	function add(){
		document.fm.action = "weeklySales.do?method=add";
		document.fm.submit();
	}
	
	function  subCheck(val) {  
			var reg = new RegExp("^[0-9]*$");
			if(!reg.test(val)){
				//var id = document.getElementById("unitPrice");				
				alert("请输入正确数字！");
			}  
	}
</script>
</head>


<body class="skin-blue sidebar-mini">
<div class="wrapper">
<header class="main-header"> 
  <!-- Logo --> 
  
  <!-- Header Navbar: style can be found in header.less -->
  <nav class="navbar navbar-static-top" role="navigation" style = "margin-left:0px;"> 
    <!-- Sidebar toggle button--> 
    
    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">
        <!-- User Account: style can be found in dropdown.less -->
        <li class="dropdown user user-menu"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <!-- <img src="performance/dist/img/user_admin.jpg" class="user-image" alt="User Image"> <span class="hidden-xs">空净厂商A</span> --> </a>
          <!-- <ul class="dropdown-menu">
            User image
            <li class="user-header"> <img src="performance/dist/img/user_admin.jpg" class="img-circle" alt="User Image">
              <p>空净厂商A <small>联系人：猫咪</small> <small>联系人手机号：110</small> </p>
            </li>
            Menu Footer
            <li class="user-footer">
              <div class="pull-left"> <a href="#" class="btn btn-default btn-flat">账号管理</a> </div>
              <div class="pull-right"> <a href="#" class="btn btn-default btn-flat">退出登录</a> </div>
            </li>
          </ul> -->
        </li>
      </ul>
    </div>
  </nav>
</header>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" style = "margin-left:0px;">
<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>周销售报表</h1>
  <ol class="breadcrumb">
    <li><i class="fa fa-send (alias)"></i>&nbsp;&nbsp;<a href="javascript:history.back(-1);">销售周报表首页</a>&nbsp;>>&nbsp;<a href="javascript:void(0);">添加记录</a></li>
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
                  <h3 class="box-title">添加记录</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form id="addForm" action="weeklySalesIndex.jsp" method="post" name="fm">
                  <div class="box-body">
                   
                   <div class="row margin">
                      <div class="col-sm-2">
					</div>
					<div class="col-sm-8">
							<div class="col-sm-6">
							  <label >产品名称：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="productName" placeholder="产品名称">
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
							  <label>规格型号：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="specificationsModel" placeholder="规格型号">
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
							  <label>单价：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="unitPrice" placeholder="单价">
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
							  <label>数量：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="quantity" placeholder="数量">
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
							  <label>金额：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="totalPrice" placeholder="金额">
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
							  <label>周数：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="week" placeholder="周数">
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
							  <label>年数：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="year" placeholder="年数">
							  </div>
					</div>
					<div class="col-sm-2">
					</div>
                    </div>
               
                  </div><!-- /.box-body -->

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
										<!-- <button type="submit" class="btn btn-primary"  onclick="add()">保存</button> -->
										<input type="button" class="btn btn-primary" value="保存" onclick="add()">
										</div>
										<div class="col-sm-3">
										<input type="reset" class="btn btn-primary" value="重置">
										</div>
								</div>
				  </div>
				  <div class="col-sm-2">
				  </div>
                    
                  </div>
                </form>
              </div>

  </section>
  <!-- right col --> 
  

</div>

</div>

<footer class="main-footer " style = "margin-left:0px;">
  <div class="pull-right hidden-xs "> <b>Version</b> 2.0 </div>
  <strong>Copyright © 2014-2015 <a href="http://www.airradio.cn/ ">北京威果智能科技有限公司</a>.</strong> All rights reserved. </footer>
</div>

<script>
      $.widget.bridge('uibutton', $.ui.button);
    </script> 
<!-- Bootstrap 3.3.2 JS --> 
<script src="performance/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
<!-- Morris.js charts --> 
<script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script> 
<script src="performance/plugins/morris/morris.min.js" type="text/javascript"></script> 

<!-- Bootstrap WYSIHTML5 --> 
<script src="performance/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script> 
<!-- Slimscroll --> 
<script src="performance/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script> 
<!-- FastClick --> 
<script src='performance/plugins/fastclick/fastclick.min.js'></script> 
<!-- AdminLTE App --> 
<script src="performance/dist/js/app.min.js" type="text/javascript"></script> 

<script type="text/javascript">
				 $(function () {					
					jQuery.validator.addMethod("check1", function(value, element) {   
					    var tel = /^\d{0,8}\.{0,1}(\d{1,2})?$/;
					    return this.optional(element) || (tel.test(value));
					},"请输入长度不超过16位小数点不超过2位的数字");
					//validate
					$("#addForm").validate({
     						    rules: {
     						    	quantity:{
     						    		digits:true,
     						    		min:0
     						    	},
     						    	week:{
     						    		digits:true,
     						    		min:0
     						    	},
     						    	year:{
     						    		digits:true,
     						    		min:0
     						    	},
     						    	unitPrice:{
     						    		check1:[]
     						    	},
     						    	totalPrice:{
     						    		check1:[]
     						    	}
							  },
							  messages: {
								  quantity:{
									  digits:"请输入0以上的整数"
								  },
							  	  week:{
							  		  digits:"请输入0以上的整数"
							  	  },
							  	  year:{
							  		  digits:"请输入0以上的整数"
							  	  }
								}
							})
					}); 
			  
</script>

</body>
</html>