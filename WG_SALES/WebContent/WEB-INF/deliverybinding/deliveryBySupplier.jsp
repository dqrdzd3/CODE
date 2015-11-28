<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>报表数据统计系统</title>
<base href="<%=basePath%>">
<!-- start -->
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<!-- Bootstrap 3.3.4 -->
<link href="performance/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- FontAwesome 4.3.0 -->
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons 2.0.0 -->
<!--  -->
<link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link href="performance/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
<!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
<link href="performance/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
<!-- iCheck -->
<link href="performance/resourceplugins/iCheck/flat/blue.css" rel="stylesheet" type="text/css" />

<!-- Date Picker -->
<link href="performance/plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />

<!-- DATA TABLES 曾凡 20150626 污染排名 -->
<link href="performance/plugins/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css">

<!-- DATApickerS  -->
<link href="plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
<!-- excel显示 -->
<link href="performance/excel/sweetalert.css" rel="stylesheet" type="text/css">
 <!-- excel -->
 <script src="performance/excel/sweetalert.min.js" type="text/javascript"></script>
 <script src="performance/js/jquery-1.11.3.js" type="text/javascript"></script>
<!-- end -->
</head>
<body>
<body class="skin-blue sidebar-mini">
<div class="wrapper">
<header class="main-header"> 
  <!-- Logo --> 
  <a href="index.jsp" class="logo"> 
  
  <!-- mini logo for sidebar mini 50x50 pixels --> 
  
  <span class="logo-mini"><b>报表</b></span> 
  
  <!-- logo for regular state and mobile devices --> 
  
  <span class="logo-lg">报表数据统计系统</span> </a> 
  <!-- Header Navbar: style can be found in header.less -->
  <nav class="navbar navbar-static-top" role="navigation"> 
    <!-- Sidebar toggle button--> 
    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button"> <span class="sr-only">Toggle navigation</span> </a>
    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">
        <!-- User Account: style can be found in dropdown.less -->
        <li class="dropdown user user-menu"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> </a>
          
        </li>
      </ul>
    </div>
  </nav>
</header>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar"> 
  <!-- sidebar: style can be found in sidebar.less -->
  <section class="sidebar"> 
    <!-- search form -->
    <form action="#" method="get" class="sidebar-form">
      <div class="input-group">
        <input type="text" name="q" class="form-control" placeholder="查询">
        <span class="input-group-btn">
        <button type="submit" name="search" id="search-btn" class="btn btn-flat"> <i class="fa fa-search"></i> </button>
        </span> </div>
    </form>
    <!-- /.search form --> 
    <!-- sidebar menu: : style can be found in sidebar.less -->
    <ul class="sidebar-menu">
      <li class="header">报表列表</li>
      <li class="treeview"> <a href="weeklySalesIndex"> <i class="fa fa-table"></i> <span>销售周报表</span></a>
      </li>
      <li class="treeview"> <a href="salesIndex"> <i class="fa fa-table"></i> <span>销售统计明细表</span></a>
      </li>
      <li class="treeview"> <a href="taobaoSalesIndex"> <i class="fa fa-table"></i> <span>淘宝明细表</span></a>
      </li>
      <li class="treeview"> <a href="otherShipsIndex"> <i class="fa fa-table"></i> <span>威果其他发货表</span></a>
      </li>
      <li class="treeview"> <a href="#"> <i class="fa fa-table"></i> <span>分销商绑定</span></a>
      		 <ul class="treeview-menu">
                 <li><a href="deliveryByThirdParty"><i class="fa fa-circle-o"></i>第三方接口发货</a></li>
				 <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>供应商发货</a></li>              
             </ul>
      </li>
    </ul>
  </section>
  <!-- /.sidebar --> 
</aside>
<div class="content-wrapper"> 
  <!-- Content Header (Page header) -->
  <section class="content-header">
    <h1>供应商发货</h1>
    <ol class="breadcrumb">
      <li><i class="fa fa-send (alias)"></i>&nbsp;&nbsp;<a href="javascript:void(0);">供应商发货</a></li>
    </ol>
  </section>
  
  <!-- Main content -->
  <section class="content">
  		<div class="row"> 
  			<section class="col-lg-12 connectedSortable"> 
  				<div class="box box-primary">
  				<div class="box-header">
                  <h3 class="box-title">绑定信息</h3>
                </div>
                <form action="salesIndex.jsp" method="post" name="fm">
                  <div class="box-body">
                  	<div class="row margin">
	                  	<div class="col-sm-3">
	                  	</div>
						<div class="col-sm-6">
								<div class="col-sm-6">
								  <label >设备id：</label>
									</div>
									<div class="col-sm-6">
								  <input type="text" class="form-control" name="customerName" placeholder="设备id">
								  </div>
						</div>
						<div class="col-sm-3">
	                  	</div>
					</div>
					<div class="row margin">
						<div class="col-sm-3">
	                  	</div>
						<div class="col-sm-6">
								<div class="col-sm-6">
								  <label >公司：</label>
									</div>
								<div class="col-sm-6">
								  <input type="text" class="form-control" name="consigneeName" placeholder="公司">
								  </div>
						</div>
						<div class="col-sm-3">
	                  	</div>
                    </div>
                  </div> 
                  
                  <div class="box-footer">
				  <div class="col-sm-2">
				  </div>
				  <div class="col-sm-8">
								<div class="col-sm-3">
								</div>
								<div class="col-sm-6">
										<div class="col-sm-4">
										</div>
										<div class="col-sm-3">
										<!-- <button type="submit" class="btn btn-primary"  onclick="add()">保存</button> -->
										<input type="button" class="btn btn-primary" value="绑定" onclick="bind()">
										</div>
										<div class="col-sm-3">
										<input type="reset" class="btn btn-primary" value="重置">
										</div>
								</div>
								<div class="col-sm-3">
								</div>
				  </div>
				  <div class="col-sm-2">
				  </div>                  
                  </div>					
  				</form>
  			 </div>
  			</section>
  		</div>
  </section>
    <!-- right col --> 
  </div>
  <!-- /. row --> 
</div>

<!-- /.content-wrapper-->
<footer class="main-footer">
  <div class="pull-right hidden-xs"> <b>Version</b> 2.0 </div>
  <strong>Copyright © 2014-2015 <a href="http://www.airradio.cn/ ">北京威果智能科技有限公司</a>.</strong> All rights reserved. </footer>
<!-- jQuery 1.9.1 --> 
<script src="performance/plugins/jQuery/jquery-1.9.1.min.js"></script> 
<!-- jquery-ui-1.11.2.min.js --> 
<script src="performance/plugins/jQueryUI/jquery-ui-1.11.2.min.js" type="text/javascript"></script> 

<!-- Bootstrap 3.3.2 JS --> 
<script src="performance/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js" type="text/javascript"></script> 
<script src="performance/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script> 

<!-- Bootstrap WYSIHTML5 --> 
<script src="performance/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script> 
<!-- Slimscroll --> 
<script src="performance/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script> 
<!-- FastClick --> 
<script src='performance/plugins/fastclick/fastclick.min.js'></script> 
<!-- AdminLTE App --> 
<script src="performance/dist/js/app.min.js" type="text/javascript"></script> 

<!-- DATA TABES SCRIPT 曾凡 20150626 污染排名--> 

<script src="performance/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script> 

<!-- DATA TABES SCRIPT 曾凡 20150626 污染排名--> 

 <script src="performance/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script> 
</body>
</html>