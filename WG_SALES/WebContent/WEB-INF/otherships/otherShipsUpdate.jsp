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
	
	function update(){
		document.fm.action="otherShips.do?method=update";
		document.fm.submit();
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
        <li class="dropdown user user-menu"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> </a>
          
        </li>
      </ul>
    </div>
  </nav>
</header>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" style = "margin-left:0px;">
<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>威果其他发货表</h1>
  <ol class="breadcrumb">
    <li> <a href="javascript:history.back(-1);"><i class="fa fa-send (alias)"></i>威果其他发货表首页</a>&nbsp;>>&nbsp;<a href="javascript:void(0);">修改记录</a></li>
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
                  <h3 class="box-title">修改记录</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form id="updateForm" action="update" method="post" name="fm">
                  <div class="box-body">
                   <input type="hidden" name="otherShipsId" value="${otherShips.otherShipsId}">
                   <!--  -->
                   <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >客户名称：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="customerName" value="${otherShips.customerName}">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >收货人姓名：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="consigneeName" value="${otherShips.consigneeName}">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >收货地址：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="receiverAddr" value="${otherShips.receiverAddr}">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >联系电话：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="phoneNo" value="${otherShips.phoneNo}">
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
							  		<%-- <input type="text" class="form-control" name="deliveryTime" value="${otherShips.deliveryTime}"> --%>
							  						<div class="input-group">
                      										<input type="text" name="deliveryTime" value="${deliveryTime}" style="background-color:white" class="form-control pull-right" readonly="readonly" id="deliveryTime"/>
                    										<div class="input-group-addon">
                       											<i class="fa fa-calendar"></i>
                      										</div>
                    								</div>
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >产品名称：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="productName" value="${otherShips.productName}">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >产品编号：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="productId" value="${otherShips.productId}">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >单价：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="unitPrice" value="${otherShips.unitPrice}">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >数量：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="quantity" value="${otherShips.quantity}">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >金额：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="totalPrice" value="${otherShips.totalPrice}">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >到款情况：</label>
								</div>
								<div class="col-sm-6">
							 <%--  <input type="text" class="form-control" name="moneyStatus" value="${otherShips.moneyStatus}"> --%>
							  <select class="form-control" name="moneyStatus">
									  <c:if test="${otherShips.moneyStatus==0}">
		 									 <option value ="0" selected='selected'>未支付</option>
											 <option value ="1">已支付</option>
									  </c:if>	
									  <c:if test="${otherShips.moneyStatus==1}">
		 									 <option value ="0">未支付</option>
											 <option value ="1" selected='selected'>已支付</option>
									  </c:if> 
							  </select>
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >是否开票：</label>
								</div>
								<div class="col-sm-6">
							  <%-- <input type="text" class="form-control" name="invoice" value="${otherShips.invoice}"> --%>
							  <select class="form-control" name="invoice">
							 			 <c:if test="${otherShips.invoice==0}">
							  				<option value ="0" selected='selected'>否</option>
							  				<option value ="1">是</option>
							  			</c:if>
							  			<c:if test="${otherShips.invoice==1}">
							  				<option value ="0">否</option>
							  				<option value ="1" selected='selected'>是</option>
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
							  <label >发票号：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="invoiceNo" value="${otherShips.invoiceNo}">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >销售平台：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="salesPlatform" value="${otherShips.salesPlatform}">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >快递公司：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="courierCompany" value="${otherShips.courierCompany}">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >快递单号：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="courierNo" value="${otherShips.courierNo}">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >签收时间：</label>
								</div>
								<div class="col-sm-6">
							  			<%-- <input type="text" class="form-control" name="signTime" value="${otherShips.signTime}"> --%>
							  						<div class="input-group">
                      										<input type="text" name="signTime" value="${signTime}" style="background-color:white" class="form-control pull-right" readonly="readonly" id="signTime"/>
                    										<div class="input-group-addon">
                       											<i class="fa fa-calendar"></i>
                      										</div>
                    								</div>
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >快递费：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="courierCost" value="${otherShips.courierCost}">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >保值：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="insuredCost" value="${otherShips.insuredCost}">
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >城市：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="city" value="${otherShips.city}">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >性别：</label>
								</div>
								<div class="col-sm-6">
							 <%--  <input type="text" class="form-control" name="gender" value="${otherShips.gender}"> --%>
							  <select class="form-control" name="gender">
							  			<c:if test="${otherShips.gender==1}">
							  				<option value ="0">女</option>
							  				<option value ="1" selected='selected'>男</option>
							  			</c:if>
							  			<c:if test="${otherShips.gender==0}">
							  				<option value ="0" selected='selected'>女</option>
							  				<option value ="1">男</option>
							  			</c:if>
							  </select>
							  </div>
					</div>
					<div class="col-sm-6">
							<div class="col-sm-3">
							  <label >年龄：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="age" value="${otherShips.age}">
							  </div>
							  <div class="col-sm-3"></div>
					</div>
                    </div>
                    <div class="row margin">
					<div class="col-sm-6">
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
							  <label >职业：</label>
								</div>
								<div class="col-sm-6">
							  <input type="text" class="form-control" name="jobs" value="${otherShips.jobs}">
							  </div>
					</div>
                    </div>
                    
                   <!-- -->
               
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
										<input type="button" class="btn btn-primary" value="保存" onclick="update()">
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
					$('#deliveryTime').datepicker();
					$('#signTime').datepicker();
					
					jQuery.validator.addMethod("check1", function(value, element) {   
					    var tel = /^\d{0,14}\.{0,1}(\d{1,2})?$/;
					    return this.optional(element) || (tel.test(value));
					},"请输入长度不超过16位小数点不超过2位的数字");
					//validate
					$("#updateForm").validate({
     						    rules: {
     						    	quantity:{
     						    		digits:true,
     						    		min:0
     						    	},
     						    	 unitPrice:{
     						    		check1:[]
     						    	},
     						    	totalPrice:{
     						    		check1:[]
     						    	},
     						    	courierCost:{
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
