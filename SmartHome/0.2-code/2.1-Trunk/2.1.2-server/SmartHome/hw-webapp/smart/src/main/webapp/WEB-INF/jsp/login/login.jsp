<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>汉威智能家居业务处理平台</title>
<s:action name="include(mainCSS,jquery,mCustomScrollbar,commonJS,utilJS)" executeResult="true" />
<script type="text/javascript" src="js/authority/authority.js"></script>
    <link rel="icon" type="image/ico" href="favicon.ico"/>
<style type="text/css">
<!--
#Layer1 {
	position: absolute;
	left: 827px;
	top: 352px;
	width: 170px;
	height: 31px;
	z-index: 1;
}

#Layer2 {
	position: absolute;
	left: 828px;
	top: 402px;
	width: 170px;
	height: 28px;
	z-index: 2;
}

#Layer3 {
	position: absolute;
	left: 988px;
	top: 359px;
	width: 86px;
	height: 74px;
	z-index: 3;
}
.inputbg{ height:24px; width:150px; margin-right:0px; padding-right:0px;}
.password{
	
	height:24px;  width:150px; margin-right:0px; padding-right:0px;
}
-->
</style>
<script type="text/javascript">
		function subForm() {
			if (checkForm()) document.logForm.submit();
		}
		function resetForm() {
			document.logForm.reset();
		}
		$(document).keydown(function(e){
			var t = e.target;
			if (e.which == 13) {
				if (t.id == "regBtn") return;
				else if (t.id == "resetBtn") resetForm();
				else subForm();
			}
		});
		
		$(function(){
			var errmsg = $('#errmsg').val();
			if(errmsg !=null && errmsg !=''){
				if(errmsg =="badnameorpwd"){
                    showMsg("用户名或密码错误！",5,{buttons:{
                        '确定':function(){
                            $(this).dialog('close');
                            $("#loginName").val("");
                            $("#loginPwd").val("");
                            $("#loginName").focus();
                        }
                    }});
					
				}
				if(errmsg =="bannedaccount"){
					showMsg("当前用户被禁用，请联系管理员！",5);
					$("#loginName").focus();
				}
				if(errmsg =="notauditaccount"){
					showMsg("当前用户未通过审核，请联系管理员！",5);
					$("#loginName").focus();
				}
			}
		});
	</script>
<script type="text/JavaScript">
		<!--
		function MM_swapImgRestore() { //v3.0
		  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
		}
		
		function MM_preloadImages() { //v3.0
		  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
		    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
		    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
		}
		
		function MM_findObj(n, d) { //v4.01
		  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
		    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
		  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
		  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
		  if(!x && d.getElementById) x=d.getElementById(n); return x;
		}
		
		function MM_swapImage() { //v3.0
		  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
		   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
		}
		//-->
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="MM_preloadImages('images/88.jpg')">
	<s:form name="logForm" action="login!authen.action" method="post" vadate="true" theme="simple" >
		<div id="Layer1">
			<label> 
				<input id="loginName" name="userPO.loginName" type="text" class="inputbg" tabindex="1" />
			</label>
		</div>
		<div id="Layer2">
			<label> 
				<input id="loginPwd" name="userPO.password" type="password" class="password" tabindex="2" />
			</label>
		</div>
		<div id="Layer3">
			<a href="javascript:void(0);" onclick="subForm()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image2','','images/88.jpg',1)">
			<img src="images/87.gif" name="Image2" width="59" height="53" border="0"></a>
		</div>
	</s:form>
	<img src="images/aa_login.jpg" width="1440" height="800" alt="">
	<input type="hidden" value="<%= request.getParameter("msg") %>" id="errmsg" />
</body>

</html>
