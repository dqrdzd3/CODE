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
<meta charset="UTF-8">
    <title>报表数据统计系统|登录</title>
    <base href="<%=basePath%>">
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.4 -->
    <link href="performance/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="performance/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="performance/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
    	function login(){
    		document.fm.action="user.do?method=exist";
    		document.fm.submit();
    	}
    	document.onkeydown=function() 
    	{ 
    	  if (event.keyCode==13)
    	  { 
    		 document.fm.action="user.do?method=exist";
      		document.fm.submit();
    	  }	
    	} 
    </script>
</head>
<body class="login-page">
    <div class="login-box">
      <div class="login-logo">
        <b>报表数据统计系统</b>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">用户登录</p>
        <form action="salesIndex" method="post" name="fm">
          <div class="form-group has-feedback">
            <input type="text" name="userName" class="form-control" placeholder="用户名"/>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" name="password" class="form-control" placeholder="密码"/>
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">    
            	<div class="col-sm-2"></div>
              <div class="checkbox icheck col-sm-10">
                <label>
                  <input type="checkbox"> 记住密码
                </label>
              </div>                        
            </div><!-- /.col -->
            <div class="col-xs-4">
              <input type="button" value="登录" onclick="login()" class="btn btn-primary btn-block btn-flat">
            </div><!-- /.col -->
          </div>
        </form>

        <a href="register.jsp" class="text-center">注册一个新用户</a>

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <!-- jQuery 2.1.4 -->
    <script src="performance/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="performance/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- iCheck -->
    <script src="performance/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>
  </body>
</html>