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
<!-- Date Picker -->
    <link href="performance/plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
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
                   <input type="hidden" name="taobaoSalesId" value="${taobaoSales.taobaoSalesId}" readonly="readonly">
                   <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >买家会员名：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="buyersUsername" value="${taobaoSales.buyersUsername}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >买家支付宝账号：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="buyersAlipayAccount" value="${taobaoSales.buyersAlipayAccount}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >应付货款：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="payables" value="${taobaoSales.payables}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >实际支付：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="actualPayment" value="${taobaoSales.actualPayment}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >货品总金额：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="totalPrice" value="${taobaoSales.totalPrice}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >订单状态：</label>
								</div>
								<div class="col-sm-6">
							  <%-- <input type="text" class="form-control" name="orderStatus" value="${taobaoSales.orderStatus}" readonly="readonly"> --%>
							  <select class="form-control" name="orderStatus" disabled="disabled">
 									<c:if test="${taobaoSales.orderStatus==0}">
 										<option value ="0">交易失败</option>
 									</c:if>
 									<c:if test="${taobaoSales.orderStatus==1}">
 										<option value ="1">交易成功</option>
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
										<label>买家留言：</label>
									</div>
									<div class="col-sm-6">
										<input type="text" class="form-control" name="buyersMessage"
											value="${taobaoSales.buyersMessage}" readonly="readonly">
									</div>
								</div>
								<div class="col-sm-6">
									<div class="col-sm-3">
										<label>收货人姓名：</label>
									</div>
									<div class="col-sm-6">
										<input type="text" class="form-control" name="consigneeName"
											value="${taobaoSales.consigneeName}" readonly="readonly">
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
							  <input type="text" class="form-control" name="receiverAddr" value="${taobaoSales.receiverAddr}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >运送方式：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="transportMethods" value="${taobaoSales.transportMethods}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >联系电话：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="phoneNo" value="${taobaoSales.phoneNo}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >手机号码：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="cellphoneNo" value="${taobaoSales.cellphoneNo}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >订单创建时间：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="orderCreatedTime" value="${ordersCreatedTime}" readonly="readonly"> 
<!-- 														<div class="input-group">
                      										<input type="text" name="ordersCreatedTime" style="background-color:white" class="form-control pull-right" disabled="disabled" id="ordersCreatedTime"/>
                    										<div class="input-group-addon">
                       											<i class="fa fa-calendar"></i>
                      										</div>
                    									</div> -->
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >订单付款时间：</label>
								</div>
								<div class="col-sm-6">
							   <input type="text" class="form-control" name="ordersPayedTime" value="${ordersPayedTime}" readonly="readonly"> 
                     									<!-- <div class="input-group">
                      										<input type="text" name="ordersPayedTime" style="background-color:white" class="form-control pull-right" disabled="disabled" id="ordersPayedTime"/>
                    										<div class="input-group-addon">
                       											<i class="fa fa-calendar"></i>
                      										</div>
                    									</div> -->
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >宝贝标题：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="productTitle" value="${taobaoSales.productTitle}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >宝贝种类：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="productSort" value="${taobaoSales.productSort}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >宝贝总数量：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="quantity" value="${taobaoSales.quantity}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >物流单号：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="logisticsNo" value="${taobaoSales.logisticsNo}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >物流公司：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="logisticsCompany" value="${taobaoSales.logisticsCompany}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >店铺ID：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="storeId" value="${taobaoSales.storeId}" readonly="readonly">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >店铺名称：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="storeName" value="${taobaoSales.storeName}" readonly="readonly">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >订单关闭原因：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="closedReason" value="${taobaoSales.closedReason}" readonly="readonly">
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