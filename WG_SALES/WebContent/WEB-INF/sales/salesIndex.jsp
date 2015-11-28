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
<!--  <script src="performance/js/ajaxfileupload.js" type="text/javascript"></script> -->
<script type="text/javascript">
	var _fm=document.fm;

	function search(){		
				//document.fm.action="weeklySales.do?method=searchAll";
				document.fm.action="sales.do?method=search";
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
				document.fm.action="sales.do?method=search";
				if(validfor()){
					document.fm.submit();
				} 
			} 
		}  
	} 
	
	function toPost(method,currentPage,size,productName,modelType){
		alert('aaa');
		document.fm.action="sales.do?method="+method+"&currentPage="+currentPage+"&size="+size+"&productName="+productName+"&modelType="+modelType;
		document.fm.method="post";
		document.fm.submit();
	}	
	
	
	 function exportExcel(){
		  swal({
				title: "您确定要导出数据吗？", 
				type: "info",
				showCancelButton: true,
				closeOnConfirm: false,
				animation: "slide-from-top",  
				confirmButtonText: "是的",
				confirmButtonColor: "#8ED84A"
				}, function() {
					//window.location.href="salesExcel";
					window.location.href="sales.do?method=export";
					swal("操作成功!", "已成功导出数据！", "success");
				});	 	 
	 }
	 
	 function importExcel(){
		 swal({
				title: "您确定要导入数据吗？", 
				type: "info",
				showCancelButton: true,
				closeOnConfirm: false,
				animation: "slide-from-top",  
				confirmButtonText: "是的",
				confirmButtonColor: "#8ED84A"
				}, function() {
					//window.location.href="salesExcel";
					window.location.href="sales.do?method=import";
					swal("操作成功!", "已成功导出数据！", "success");
				});	
	 }

/* 	 function uploadSubmit(){
		 var b2 = document.getElementById("btn2");
			b2.disabled = false;
			document.uploadSubmit.method = "POST";
			document.uploadSubmit.submit();			
	 } */
	 
	 function changeVisible(){
		 var b2 = document.getElementById("btn2");
			b2.disabled = false;
		 document.getElementById("upload").style.display='block';
	 }
	 
	 /* function ajaxFileUpload() {
		 //alert("outside");
		 $.ajaxFileUpload
         (		
             {	
                 url: 'sales.do?method=upload', //用于文件上传的服务器端请求地址
                 type: 'post',
                 //data: { Id: '123', name: 'lunis' }, //此参数非常严谨，写错一个引号都不行
                 secureuri: false, //一般设置为false
                 fileElementId: 'file', //文件上传空间的id属性  <input type="file" id="file" name="file" />
                 //dataType: 'json', //返回值类型 一般设置为json
                 success: function (data, status)  //服务器成功响应处理函数
                 {
                	 alert("success");
                   //  alert(data);
                    // $('#result').html('添加成功');
                 },
                 error: function (data, status, e)//服务器响应失败处理函数
                 {
                	 alert("error");
                   //  alert(e);
                  //   $('#result').html('添加失败');
                 }
             }
         );
     } */
	 
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
      <li class="treeview"> <a href="javascript:void(0);"> <i class="fa fa-table"></i> <span>销售统计明细表</span></a>
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
    <h1>销售统计明细表</h1>
    <ol class="breadcrumb">
      <li><i class="fa fa-send (alias)"></i>&nbsp;&nbsp;<a href="javascript:void(0);">销售统计明细表</a></li>
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
        
        
			<!-- ajax file upload -->
			<!-- <form method="post" action="sales.do?method=upload" enctype="multipart/form-data">   
        			<input type="file" id="file" name="file"/>  
       				<input type="button" value="提交" onclick="ajaxFileUpload()"/>  
    		</form>  
    		<div id="result"></div>  -->
			<!--  -->        
		  <!-- add by self form for submit -->
		  <form action="search" method="post" name="fm">
        
          <div class="box-body">
			<!-- search form -->
            <form action="#" method="get">
			  <!-- row -->
            <div class="row">
              <div class="col-sm-3">
			    <div class="">收货人姓名</div>
                <input id="range_1" type="text" name="consigneeName" value="${sales.consigneeName}" placeholder = "收货人姓名" class = "form-control">
              </div>                        
 
 			  <div class="col-sm-3">
			    <div class="">是否开票</div>
                <%-- <input id="range_1" type="text" name="invoice" value="${sales.invoice}" placeholder = "是否开票" class = "form-control"> --%>
             	<select name="invoice" class="form-control">
  									<c:if test="${sales.invoice==0}">
							  			<option value ="0" selected='selected'>全部</option>
							  			<option value ="1">是</option>
							  			<option value ="2">否</option>
							  		</c:if>
							  		<c:if test="${sales.invoice==1}">
							  			<option value ="0">全部</option>
							  			<option value ="1" selected='selected'>是</option>
							  			<option value ="2">否</option>
							  		</c:if>
							  		<c:if test="${sales.invoice==2}">
							  			<option value ="0">全部</option>
							  			<option value ="1">是</option>
							  			<option value ="2" selected='selected'>否</option>
							  		</c:if>
							  </select>
              </div> 
 
              <div class="col-sm-3">
			    <div class="">到款情况</div>
                <%-- <input id="range_1" type="text" name="moneyStatus" value="${sales.moneyStatus}" placeholder = "到款情况" class = "form-control"> --%>
             	<select name="moneyStatus" class="form-control">
  									<c:if test="${sales.moneyStatus==0}">
							  				<option value ="0" selected='selected'>全部</option>
							  				<option value ="1">已支付</option>
							  				<option value ="2">未支付</option>
							  			</c:if>
							  			<c:if test="${sales.moneyStatus==1}">
							  				<option value ="0">全部</option>
							  				<option value ="1" selected='selected'>已支付</option>
							  				<option value ="2">未支付</option>
							  			</c:if>
							  			<c:if test="${sales.moneyStatus==2}">
							  				<option value ="0">全部</option>
							  				<option value ="1">已支付</option>
							  				<option value ="2" selected='selected'>未支付</option>
							  			</c:if>
							  </select>
              </div>              
           
              <div class="col-sm-3">
			    <div class="">销售平台</div>
                <%-- <input id="range_1" type="text" name="salesPlatform" value="${sales.salesPlatform}" placeholder = "销售平台" class = "form-control"> --%>
              	<select name="salesPlatform" class="form-control">
  									<c:if test="${sales.salesPlatform==0}">
							  						<option value ="0" selected='selected'>空</option>
							  						<option value ="1">微店</option>
							  						<option value ="2">微信小店</option>
							  						<option value ="3">威果诚品</option>
							  						<option value ="4">企业淘宝</option>
							  						<option value ="5">线下</option>
							  					</c:if>
  												<c:if test="${sales.salesPlatform==1}">
  													<option value ="0">空</option>
							  						<option value ="1" selected='selected'>微店</option>
							  						<option value ="2">微信小店</option>
							  						<option value ="3">威果诚品</option>
							  						<option value ="4">企业淘宝</option>
							  						<option value ="5">线下</option>
  												</c:if>
  												<c:if test="${sales.salesPlatform==2}">
  													<option value ="0">空</option>
							  						<option value ="1">微店</option>
							  						<option value ="2" selected='selected'>微信小店</option>
							  						<option value ="3">威果诚品</option>
							  						<option value ="4">企业淘宝</option>
							  						<option value ="5">线下</option>
  												</c:if>
  												<c:if test="${sales.salesPlatform==3}">
  													<option value ="0">空</option>
							  						<option value ="1">微店</option>
							  						<option value ="2">微信小店</option>
							  						<option value ="3" selected='selected'>威果诚品</option>
							  						<option value ="4">企业淘宝</option>
							  						<option value ="5">线下</option>
  												</c:if>
  												<c:if test="${sales.salesPlatform==4}">
  													<option value ="0">空</option>
							  						<option value ="1">微店</option>
							  						<option value ="2">微信小店</option>
							  						<option value ="3">威果诚品</option>
							  						<option value ="4" selected='selected'>企业淘宝</option>
							  						<option value ="5">线下</option>
  												</c:if>
  												<c:if test="${sales.salesPlatform==5}">
  													<option value ="0">空</option>
							  						<option value ="1">微店</option>
							  						<option value ="2">微信小店</option>
							  						<option value ="3">威果诚品</option>
							  						<option value ="4">企业淘宝</option>
							  						<option value ="5" selected='selected'>线下</option>
  												</c:if>
							  </select>
              </div>              
           
          </div>
		  <!-- /. row -->	
		  <!--search form -->
		  </form>
		  <!-- row -->
		  

          <div class="row margin">
            <div class="col-sm-5" > 
            	<div id="upload" style="display:none">
            	<form action="smartUpload.jsp" method="post" enctype="multipart/form-data" name="upload">
            			请选择要上传的文件：<input type="file" name="pic">						
							<input id="" type="submit" value="上传"> 
							<!-- <input type="button" id="btn3" value="上传" onclick="uploadSubmit()"/> -->
				</form>
				</div>
			</div>
            <div class="col-sm-7">
            	<div class="col-sm-2">
              	</div>
              	<div class="col-sm-2">
                <input type="button" id="btn2" class="btn btn-block btn-primary btn-sm" value="数据导入" onclick="importExcel()"/>
              </div>
              <div class="col-sm-2">
                <input type="button" id="btn1" class="btn btn-block btn-primary btn-sm" value="上传文件" onclick="changeVisible()"/>
              </div>
              <div class="col-sm-2">
               <input type="button" class="btn btn-block btn-primary btn-sm" value="数据导出" onclick="exportExcel()"/>
              </div>
              <div class="col-sm-2">
                <input type="button" class="btn btn-block btn-primary btn-sm" value="添加记录" onclick="window.location.href='salesAdd'">
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
              	<th>型号</th>
              	<th>产品编号</th>
                <th>发货时间</th>
                <th>客户名称</th>
                <th>收货人姓名</th>
                <th>买家旺旺昵称</th>
                <th>联系电话</th>
                <th>数量</th>
                <th>金额</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
             <c:forEach var="li" items="${list}">
    		<tr>
    			<td>${li.productName}</td>
    			<td>${li.modelType}</td>    			
    			<td>${li.productId}</td>
    			<td>
    			<fmt:formatDate value="${li.deliveryTime}" pattern="MM/dd/yyyy" />
    			</td>
    			<td>${li.customerName}</td>
    			<td>${li.consigneeName}</td>  
    			<td>${li.buyersNickname}</td> 
    			<td>${li.phoneNo}</td>
    			<td>${li.quantity}</td>
    			<td>${li.totalPrice}</td>
    			<td><a href="salesSearchById?salesId=${li.salesId}">修改</a> <a href="salesDelete?salesId=${li.salesId}" onclick="return  isDel()">删除</a>
    				<a href="salesSearchByIdForDetails?salesId=${li.salesId}">详细信息</a>
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
					//sweetAlert("Oops...", "Something went wrong!", "error");
					$('#crm').dataTable({
						//"bFilter": false
					});		
				 })
				</script>

</body>
</html>