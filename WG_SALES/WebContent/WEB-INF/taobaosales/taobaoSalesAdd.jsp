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
<!-- Date Picker -->
    <link href="performance/plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
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
		document.fm.action = "taobaoSales.do?method=add";
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
        <li class="dropdown user user-menu"> <a href="#" class="dropdown-toggle" data-toggle="dropdown">  </a>
          
        </li>
      </ul>
    </div>
  </nav>
</header>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" style = "margin-left:0px;">
<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>淘宝销售记录表</h1>
  <ol class="breadcrumb">
    <li><i class="fa fa-send (alias)"></i>&nbsp;&nbsp;<a href="javascript:history.back(-1);">淘宝销售记录表首页</a>&nbsp;>>&nbsp;<a href="javascript:void(0);">添加记录</a></li>
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
                <form id="addForm" action="taobaoSalesIndex.jsp" method="post" name="fm">
                  <div class="box-body">
                   <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3">
							</div>
							<div class="col-sm-3">
							  <label >买家会员名：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="buyersUsername" placeholder="买家会员名">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >买家支付宝账号：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="buyersAlipayAccount" placeholder="买家支付宝账号">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                   <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3">
							</div>
							<div class="col-sm-3">
							  <label >应付货款：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="payables" placeholder="应付货款">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >实际支付：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="actualPayment" placeholder="实际支付">
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
							  <input type="text" class="form-control" name="totalPrice" placeholder="货品总金额">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >订单状态：</label>
								</div>
								<div class="col-sm-6">
							  <!-- <input type="text" class="form-control" name="orderStatus" placeholder="订单状态"> -->
							  <select class="form-control" name="orderStatus">
 									 <option value ="0">交易失败</option>
									 <option value ="1">交易成功</option>
							  </select>
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >买家留言：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="buyersMessage" placeholder="买家留言">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >收货人姓名：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="consigneeName" placeholder="收货人姓名">
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
							  <input type="text" class="form-control" name="receiverAddr" placeholder="收货人地址">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >运送方式：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="transportMethods" placeholder="运送方式">
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
							  <input type="text" class="form-control" name="phoneNo" placeholder="联系电话">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >手机号码：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="cellphoneNo" placeholder="手机号码">
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
							  <!-- <input type="text" class="form-control" name="ordersCreatedTime" placeholder="订单创建时间"> -->
							  				<div class="input-group">
                      										<input type="text" name="ordersCreatedTime" style="background-color:white" class="form-control pull-right" readonly="readonly" id="ordersCreatedTime"/>
                    										<div class="input-group-addon">
                       											<i class="fa fa-calendar"></i>
                      										</div>
                    						</div>
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >订单付款时间：</label>
								</div>
								<div class="col-sm-6">
							  <!-- <input type="text" class="form-control" name="ordersPayedTime" placeholder="订单付款时间"> -->
							  				<div class="input-group">
                      										<input type="text" name="ordersPayedTime" style="background-color:white" class="form-control pull-right" readonly="readonly" id="ordersPayedTime"/>
                    										<div class="input-group-addon">
                       											<i class="fa fa-calendar"></i>
                      										</div>
                    						</div>
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
							  <input type="text" class="form-control" name="productTitle" placeholder="宝贝标题">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >宝贝种类：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="productSort" placeholder="宝贝种类">
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
							  <input type="text" class="form-control" name="quantity" placeholder="宝贝总数量">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >物流单号：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="logisticsNo" placeholder="物流单号">
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
							  <input type="text" class="form-control" name="logisticsCompany" placeholder="物流公司">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >店铺ID：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="storeId" placeholder="店铺ID">
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
							  <input type="text" class="form-control" name="storeName" placeholder="店铺名称">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >订单关闭原因：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="closedReason" placeholder="订单关闭原因">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                                
                  </div><!-- /.box-body -->

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
										<input type="button" class="btn btn-primary" value="保存" onclick="add()">
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
 <!-- datepicker --> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js" type="text/javascript"></script>  
 <!-- datepicker --> 
<script src="performance/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script> 
<!-- 日期选择控件 -->
<script type="text/javascript">
				 $(function () {
				 //Date range picker
					$('#ordersCreatedTime').datepicker();
					$('#ordersPayedTime').datepicker();	
					
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
     						    	payables:{
     						    		check1:[]
     						    	},
     						    	actualPayment:{
     						    		check1:[]
     						    	},
     						    	totalPrice:{
     						    		check1:[]
     						    	}
							  },
							  messages: {
								  quantity:{
									  digits:"请输入0以上的整数"
								  }
								}
							})
					}); 
			  
</script>

</body>
</html>