<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<s:action name="include(mainCSS,jquery,grid,commonJS)" executeResult="true" />
<title><s:text name="用户管理" />-<s:text name="application.title" /></title>
<style type="text/css">
<!--
body,html{overflow:hidden;
	background:url(images/perbg.jpg) center no-repeat; 
}


#inner {
	 position:absolute;
	top:50%;
	left:50%;
	margin:-150px 0 0 -200px;
	width:500px;
	height:300px;

}/*for explorer only */
div.greenBorder input{ border:#BFF0ED solid 1px; border-left-color:#35C0AA;border-top-color:#35C0AA; width:195px; height:21px;}
.greenBorder { padding-top:74px;padding-left:70px;}
-->
</style>
<script type="text/javascript">
	function subForm() {
		if (jQuery("#editfrom").valid()) {
			jQuery.post($$.getContextPath()+'manager/sysuser!editUserPassWord', jQuery(
					'#editfrom').serialize(), function(msg) {
				showMsg(msg.content, msg.type);
				$(":password").val("");
			});
		}
	}
</script>
</head>

<body>
<s:form name="editfrom" action="user!saveEdit" method="post"
	id="editfrom">
	<s:hidden name="userinfoPO.UUID" />

    <div id="inner" class="greenBorder">
      <table width="100%" border="0" cellspacing="12" cellpadding="0">
  <tr>
    <td align="right" style="width:100px;">原始密码</td>
    <td><s:password name="userinfoPO.PASSWORD" id="oldpassword" cssClass="{required:true,rangelength:[6,20],messages:{rangelength:'输入长度6-20位字符和数字组合'}}" /></td>
    
  </tr>
  <tr>
    <td align="right">新&nbsp;密&nbsp;&nbsp;码</td>
   <td><s:password name="userinfoPO.newpassword" id="newpassword"  cssClass="{required:true,rangelength:[6,20],messages:{rangelength:'输入长度6-20位字符和数字组合'}}"></s:password></td>
  </tr>
  <tr>
    <td align="right">重复密码</td>
   <td><s:password name="repassword" id="repassword" cssClass="{required:true,equalTo:'#newpassword',messages:{equalTo:'两次密码输入不一致，请重新输入!'}}"/></td>
    
  </tr>
  <tr>
    <td></td>
    <td height="35" align="left" valign="bottom"><img onclick="subForm()" src="images/update.png" width="88" height="29" /></td>
    </tr>
</table>
    </div>

</s:form>
</body>
</html>
