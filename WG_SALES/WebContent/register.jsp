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
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
        <!-- jQuery 2.1.4 -->
    <script src="performance/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script type="text/javascript">
    function register(){
    		    var pwd1 = document.fm.pwd1.value;
    			var pwd2 = document.fm.pwd2.value;
    			if(pwd1 == "" || pwd2 == ""){
    				alert("请输入密码！");
    				document.getElementById("btn").disabled=true;
    				return false;
    			} 
    			document.fm.action = "user.do?method=add";
    			document.fm.submit();
    		}
    
    function checkpwd(){
    	  var p1=document.fm.pwd1.value;//获取密码框的值
    	  var p2=document.fm.pwd2.value;//获取重新输入的密码值
    	  if(p1==""){
    	   alert("请输入密码！");//检测到密码为空，提醒输入//
    	   document.form1.pwd1.focus();//焦点放到密码框
    	   document.getElementById("btn").disabled=true;
    	   return false;//退出检测函数
    	  }//如果允许空密码，可取消这个条件    	  
    	  if(p1!=p2){//判断两次输入的值是否一致，不一致则显示错误信息
    	   document.getElementById("msg").style.visibility="visible";//在div显示错误信息
    	   document.getElementById("btn").disabled=true;
    	   return false;
    	  }else{
    		//document.getElementById("msg").style.visibility="hidden";   		
    	  }
    	 }
    </script>
</head>
<body class="register-page">
    <div class="register-box">
      <div class="register-logo">
        <b>报表数据统计系统</b>
      </div>

      <div class="register-box-body">
        <p class="login-box-msg">用户注册</p>
        <form action="index.jsp" method="post" name="fm" id="register">
          <div class="form-group has-feedback">
            <input type="text" class="form-control" name="userName" placeholder="用户名"/>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="text" class="form-control" name="name" placeholder="姓名"/>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" id="pwd1" class="form-control" name="password" placeholder="密码"/>
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" id="pwd2" class="form-control" name="password" onblur="checkpwd()" placeholder="再次输入密码"/>
            <div id="msg" style="color:red;visibility:hidden;">两次密码不一致!</div>
            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">                          
            </div><!-- /.col -->
            <div class="col-xs-4">
              <input type="button" id="btn" value="注册" class="btn btn-primary btn-block btn-flat" onclick="register()"></button>
            </div><!-- /.col -->
          </div>
        </form>        
        <a href="index.jsp" class="text-center">已注册用户</a>
      </div><!-- /.form-box -->
    </div><!-- /.register-box -->

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