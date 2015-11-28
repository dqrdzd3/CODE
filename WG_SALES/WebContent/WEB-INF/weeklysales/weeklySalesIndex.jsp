<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<script type="text/javascript">
	var _fm=document.fm;

	function search(){
		//document.fm.action="weeklySales.do?method=searchAll";
		document.fm.action="weeklySales.do?method=search";
		document.fm.method = "POST";
		document.fm.submit();
	}	
	
	function add(){
		
	}
	/**/
	function isDel(){
		if(confirm("确认要删除该条记录？")){
			return true;
		}else{
			return false;
		}
	}
	
	function  subCheck(val) {  
		if(event.keyCode==13){
			var reg = new RegExp("^[0-9]*$");
			if(!reg.test(val)){
				alert("请输入正确数字！");
			}else{
				//document.fm.action="weeklySales.do?method=searchAll";
				document.fm.action="weeklySales.do?method=search";
				if(validfor()){
					document.fm.submit();
				} 
			} 
		}  
	} 
	
	function toPost(method,currentPage,size,productName,specificationsModel){
		alert('aaa');
		document.fm.action="weeklySales.do?method="+method+"&currentPage="+currentPage+"&size="+size+"&productName="+productName+"&specificationsModel="+specificationsModel;
		document.fm.method="post";
		document.fm.submit();
	}		
</script>

<!-- end -->
</head>


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
        <li class="dropdown user user-menu"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <!-- <img src="dist/img/user_admin.jpg" class="user-image" alt="User Image"> <span class="hidden-xs">空净厂商A</span> --> </a>
          <!-- <ul class="dropdown-menu">
            User image
            <li class="user-header"> <img src="dist/img/user_admin.jpg" class="img-circle" alt="User Image">
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
      <li class="treeview"> <a href="javascript:void(0);"> <i class="fa fa-table"></i> <span>销售周报表</span></a>
      </li>
      <li class="treeview"> <a href="salesIndex"> <i class="fa fa-table"></i> <span>销售统计明细表</span></a>
      </li>
      <li class="treeview"> <a href="taobaoSalesIndex"> <i class="fa fa-table"></i> <span>淘宝明细表</span></a>
      </li>
      <li class="treeview"> <a href="otherShipsIndex"> <i class="fa fa-table"></i> <span>威果其他发货表</span></a>
      </li>
      <li class="treeview"> <a href="deliveryBinding"> <i class="fa fa-table"></i> <span>分销商绑定</span></a>
      </li>
    </ul>
  </section>
  <!-- /.sidebar --> 
</aside>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper"> 
  <!-- Content Header (Page header) -->
  <section class="content-header">
    <h1>周销售报表</h1>
    <ol class="breadcrumb">
      <li><i class="fa fa-send (alias)"></i>&nbsp;&nbsp;<a href="javascript:void(0);">销售周报表</a></li>
    </ol>
  </section>
  
  <!-- Main content -->
  <section class="content">
  
  <!-- Main row -->
  <div class="row"> 
    
    <!-- col -->
    <section class="col-lg-12 connectedSortable"> 
      <!-- 设备管理 -->
      <div class="box box-success" style="position: relative; ">
        <div class="box-header with-border">
        
        
		  <!-- add by self form for submit -->
		  <form action="search" method="post" name="fm">
        
          <div class="box-body">
			<!-- search form -->
            <form action="#" method="get">
			  <!-- row -->
            <div class="row">
              <div class="col-sm-3">
			    <div class="">产品名称</div>
                <input id="range_1" type="text" name="productName" value="${weeklySales.productName}" placeholder = "产品名称" class = "form-control">
              </div>

              <div class="col-sm-3">
			    <div class="">规格型号</div>
                <input id="range_1" type="text" name="productName" value="${weeklySales.specificationsModel}" placeholder = "规格型号" class = "form-control">
              </div>                          
 
              <div class="col-sm-3">
			    <div class="">周数</div>
                <input id="range_1" type="text" name="week" value="${weeklySales.week}" placeholder = "周数" class = "form-control">
              </div>              
           
              <div class="col-sm-3">
			    <div class="">年数</div>
                <input id="range_1" type="text" name="year" value="${weeklySales.year}" placeholder = "年数" class = "form-control">
              </div>              
           
          </div>
		  <!-- /. row -->	
		  <!--search form -->
		  </form>
		  <!-- row -->
		  

          <div class="row margin">
            <div class="col-sm-5"> </div>
            <div class="col-sm-7">
            	<div class="col-sm-4">
              	</div>
              <div class="col-sm-2">
                <input type="button" class="btn btn-block btn-primary btn-sm" value="批量录入" onclick="javascript:void(0);">
              </div>
              <div class="col-sm-2">
                 <input type="button" class="btn btn-block btn-primary btn-sm" value="数据导出" onclick="javascript:void(0);">
              </div>
              <div class="col-sm-2">
                <input type="button" class="btn btn-block btn-primary btn-sm" value="添加记录" onclick="window.location.href='weeklySalesAdd'">
              </div>
              <div class="col-sm-2">
                <input type="button" class="btn btn-block btn-primary btn-sm" value="查询" onclick="search()"/>
              </div>
            </div>
			<!-- /. row -->	
          </div>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
          <table id="crm" class="table table-bordered">
            <thead>
              <tr>
                <th>产品名称</th>
                <th>规格型号</th>
                <th>单价</th>
                <th>数量</th>
                <th>金额</th>
                <th>周数</th>
                <th>年数</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
             <c:forEach var="li" items="${list}">
    		<tr>
    			<td>${li.productName}</td>
    			<td>${li.specificationsModel}</td>    			
    			<td>${li.unitPrice}</td>
    			<td>${li.quantity}</td>
    			<td>${li.totalPrice}</td>  
    			<td>${li.week}</td> 
    			<td>${li.year}</td>		
    			<td><a href="weeklySalesSearchById?weeklySalesId=${li.weeklySalesId}">修改</a> <a href="weeklySalesDelete?weeklySalesId=${li.weeklySalesId}" onclick="return  isDel()">删除</a>
    			</td>
    		</tr>
    		</c:forEach>
            </tbody>
          </table>
        </div><!-- /.box-body -->
        
        <!-- add by self -->
        </form>
        
      </div>
      <!-- /.box --> 
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
<script>
				 $(function () {
				 //Date range picker
					//$('#sellDate').daterangepicker();

					$('#crm').dataTable({
						//"bFilter": false
						//"bPaginate": false
					});
					  
				  });
				</script>

</body>
</html>