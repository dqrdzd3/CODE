<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println("basePath="+basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>淘宝销售记录表</title>
<base href="<%=basePath%>">
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
        <li class="dropdown user user-menu"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"></a>
          
        </li>
      </ul>
    </div>
  </nav>
</header>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" style = "margin-left:0px;">
<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>淘宝销售记录详细信息</h1>
  <ol class="breadcrumb">
    <li><i class="fa fa-send (alias)"></i>&nbsp;&nbsp;<a href="javascript:history.back(-1);">淘宝销售记录表首页</a>&nbsp;>>&nbsp;<a href="javascript:void(0);">详细信息</a></li>
  </ol>
</section>




<!-- Main content -->
<section class="content">

<!-- Main row -->
<div class="row"> 

  <!-- col -->
  <section class="col-lg-12 connectedSortable"> 
    
		<div class="box box-primary">
                <div class="box-header">
                  <h3 class="box-title">详细信息</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form action="update" method="post" name="fm">
                  <div class="box-body">
                   <input type="hidden" name="salesId" value="${sales.salesId}">
                   <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >客户名称：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="customerName" value="${sales.customerName}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >收货人姓名：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="consigneeName" value="${sales.consigneeName}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >收货人地址：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="receiverAddr" value="${sales.receiverAddr}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >联系电话：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="phoneNo" value="${sales.phoneNo}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >发货时间：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="deliveryTime" value="${deliveryTime}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >买家旺旺昵称：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="buyersNickname" value="${sales.buyersNickname}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >产品名称：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="productName" value="${sales.productName}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >产品编号：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="productId" value="${sales.productId}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >型号：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="modelType" value="${sales.modelType}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >颜色：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="color" value="${sales.color}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >单价：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="unitPrice" value="${sales.unitPrice}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >数量：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="quantity" value="${sales.quantity}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >金额：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="totalPrice" value="${sales.totalPrice}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >到款情况：</label>
								</div>
								<div class="col-sm-6">
							  <%-- <input type="text" class="form-control" name="moneyStatus" value="${sales.moneyStatus}" readonly="readonly"> --%>
							  
							  <select name="moneyStatus" disabled="disabled" class="form-control">
  									<c:if test="${sales.moneyStatus==0}">
  										<option value ="0">全部</option>
  									</c:if>
  									<c:if test="${sales.moneyStatus==1}">
  										<option value ="1">已支付</option>
  									</c:if>
  									<c:if test="${sales.moneyStatus==2}">
  										<option value ="2">未支付</option>
  									</c:if>
							  </select>
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >是否开票：</label>
								</div>
								<div class="col-sm-6">
							  <%-- <input type="text" class="form-control" name="invoice" value="${sales.invoice}" readonly="readonly"> --%>
							  <select name="invoice" disabled="disabled" class="form-control">
  									<c:if test="${sales.invoice==0}">
  										<option value ="0">全部</option>
  									</c:if>
  									<c:if test="${sales.invoice==1}">
  										<option value ="1">是</option>
  									</c:if>
  									<c:if test="${sales.invoice==2}">
  										<option value ="2">否</option>
  									</c:if>
							  </select>
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >发票号：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="invoiceNo" value="${weeklySales.invoiceNo}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >销售平台：</label>
								</div>
								<div class="col-sm-6">
							  <%-- <input type="text" class="form-control" name="salesPlatform" value="${sales.salesPlatform}" readonly="readonly"> --%>
							  <select name="salesPlatform" disabled="disabled" class="form-control">
							  				<c:if test="${sales.salesPlatform==0}">
							  					<option value ="0">空</option>
							  				</c:if>
							  				<c:if test="${sales.salesPlatform==1}">
							  					<option value ="1">微店</option>
							  				</c:if>
							  				<c:if test="${sales.salesPlatform==2}">
							  					<option value ="2">微信小店</option>
							  				</c:if>
							  				<c:if test="${sales.salesPlatform==3}">
							  					<option value ="3">威果诚品</option>
							  				</c:if>
							  				<c:if test="${sales.salesPlatform==4}">
							  					<option value ="4">企业淘宝</option>
							  				</c:if>
							  				<c:if test="${sales.salesPlatform==5}">
							  					<option value ="5">线下</option>
							  				</c:if>
							  	</select>
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >快递公司：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="courierCompany" value="${sales.courierCompany}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >快递单号：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="courierCost" value="${sales.courierCost}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >签收时间：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="signTime" value="${signTime}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >快递费：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="courierCost" value="${sales.courierCost}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label>备注：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="remark" value="${sales.remark}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                  </div><!-- /.box-body -->

                  <div class="box-footer">
                  </div>
                </form>
              </div>
    </section>
  

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



</body>
</html>