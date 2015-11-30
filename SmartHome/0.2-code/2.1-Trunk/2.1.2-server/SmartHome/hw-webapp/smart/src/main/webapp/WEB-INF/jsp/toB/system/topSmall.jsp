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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">

<!-- jQuery 1.9.1 --> 
<script src="<%=basePath%>public/toB/plugins/jQuery/jquery-1.9.1.min.js"></script>
<style>
.noneb{
	font-color:write;
	border: 0px;
	background: #3C8DBC;
}
.noneb:hover {
     -moz-transform: scale(1.2,1.2);
     -webkit-transform: scale(1.2,1.2);
     -o-transform: scale(1.2,1.2);
     -ms-transform: scale(1.2,1.2);
     transform: scale(1.2,1.2);          
 }
 .newB{
 	background:yellow;
 	color:write;
 	border: 2px solid #f00;
 }
</style>
<script>
 $(function(){
	 
	 $(".user-image").attr("src","data:image/png;base64,"+localStorage.getItem('logo',''));  
	 $(".img-circle").attr("src","data:image/png;base64,"+localStorage.getItem('logo','')); 
	  $(".noneb").mousedown(function(){
		  $(this).addClass(".newB");
		});
	 $(".noneb").mouseup(function(){
		  $(this).removeClass(".newB");
		}); 
	 
	 $('#navi button:first').click(function(){
			back2home();
		});
		$("#device").click(function(){
			back2crm();
		});
		
		$("#material1").click(function(){
			back2material();
		});
		
		$("#potential").click(function(){
			 back2potential();
		});
		
		$("#weiguoDiscuss").click(function(){
			 var url = "http://www.airradio.cn/bbs/forum.php";
			    //alert(url);
			 window.open(url);
		});
		
		$("#weiguoShop").click(function(){
			 var url = "http://shop124651414.taobao.com/";
			    //alert(url);
			window.open(url);
		});
		
		
		$("#updateDevice").click(function(){

				    back2client();
			
			
		});
		
		
		$("#user_online a:last").click(function(){
			
			 back2online();
		});
		$("#user_all a:last").click(function(){
			
			 back2crm();
		});
		$("#user_out a:last").click(function(){
			
			 back2material();
		});
		$("#user_focus a:last").click(function(){
			
			 back2potential();
		});
		
		
	});
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

</HEAD>
<body>
<header id = "headerTitle" class="main-header"> 
  <!-- logo --> 
  <a href="#" class="logo"> 
  
  <!-- mini logo for sidebar mini 50x50 pixels --> 
  
  <span class="logo-mini"><b>空气电台</b></span> 
  
  <!-- logo for regular state and mobile devices --> 
  
  <span class="logo-lg">空气电台数据系统</span> </a> 
  <!-- Header Navbar: style can be found in header.less -->
  <nav class="navbar navbar-static-top" role="navigation" id="navi"> 
    <!-- Sidebar toggle button--> 
    <!-- 头部的按钮  -->
   
    <button type="button" class="btn btn-info btn-lg noneb" style = "margin-top:2px">主页</button>
    <button type="button" class="btn btn-info btn-lg noneb" style = "margin-top:2px" id = "device">设备管理</button>
    <button type="button" class="btn btn-info btn-lg noneb" style = "margin-top:2px" id = "material1">耗材维护</button>
    <button type="button" class="btn btn-info btn-lg noneb" style = "margin-top:2px" id = "potential">潜在用户</button>
    <button type="button" class="btn btn-info btn-lg noneb" style = "margin-top:2px;color:write" id = "weiguoDiscuss">威果论坛</button>
    <button type="button" class="btn btn-info btn-lg noneb" style = "margin-top:2px" id = "weiguoShop">威果商城</button>
    
    <!-- /.头部按钮 -->
    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">
        <!-- User Account: style can be found in dropdown.less -->
        <li class="dropdown user user-menu"> 
        		<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
        			<img  src="<%=basePath%>public/toB/dist/img/user_admin.jpg" class="user-image" alt="User Image"> 
        			<span class="hidden-xs">${businessPO.ma002}</span> 
        		</a>
          <ul class="dropdown-menu">
            <!-- User image -->
            <li class="user-header"> <img  src="<%=basePath%>public/toB/dist/img/user_admin.jpg" class="img-circle" alt="User Image">
              <p><span id = "companyName">${businessPO.ma002}</span><small>联系人：<span id = "contactName">${businessPO.ma003}</span></small> <small>联系人手机号：<span id = "TelContent">${businessPO.ma004}</span></small> </p>
            </li>
            <!-- Menu Footer-->
            <li class="user-footer">
              <div class="pull-left"> <a id = "updateDevice" class="btn btn-default btn-flat">账号管理</a> </div>
              <div class="pull-left"> <a href="<%=basePath%>hwmobile/smart/corpbusiness!doChange" class="btn btn-default btn-flat">修改密码</a> </div>
              
              <div class="pull-right"> <a href="<%=basePath%>hwmobile/smart/corpbusiness!doLogin" class="btn btn-default btn-flat">退出登录</a> </div>
            </li>
          </ul>
        </li>
      </ul>
      
     
      
    </div>
  </nav>
</header>
</body>
</HTML>