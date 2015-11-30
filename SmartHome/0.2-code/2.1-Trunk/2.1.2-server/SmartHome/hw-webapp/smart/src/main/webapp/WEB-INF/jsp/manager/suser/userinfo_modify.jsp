<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="hwsoft" uri="http://www.hanwei.com/tag"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<s:action name="include(mainCSS,jquery,grid,commonJS)"
	executeResult="true" />
<title><s:text name="用户管理" />-<s:text name="application.title" /></title>
<style type="text/css">
<!--
body,html {overflow: hidden;
	background: url(images/perbg.jpg) center no-repeat;
}
#inner {
	 position:absolute;
	top:50%;
	left:50%;
	margin:-150px 0 0 -200px;
	width:300px;
	height:300px;

}/*for explorer only */
div.greenBorder input{
	border: #BFF0ED solid 1px;
	border-left-color: #35C0AA;
	border-top-color: #35C0AA;
	width: 190px;
	height: 21px;
	color:#666; font-size:12px;
	padding-left:5px;
}
.radiostyle{
	border: #BFF0ED solid 0px !important;
	border-left-color: #35C0AA;
	border-top-color: #35C0AA;
	border-style:none;
	width: 16px !important;
	height: auto;
}
.greenBorder {
	padding-top: 74px; padding-left:20px;
}


-->
</style>
<script type="text/javascript">
	function subForm() {
		if (jQuery("#editfrom").valid()) {
			jQuery.post($$.getContextPath()+'manager/sysuser!doSaveEditUserInfo', jQuery(
					'#editfrom').serialize(), function(msg) {
				showMsg(msg.content, msg.type);
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

							<td align="right" style="width: 100px;" nowrap="nowrap" >账户名称</td>
							<td><s:textfield name="userinfoPO.LOGIN_NAME"
									disabled="true"/></td>
						</tr>
						<tr>

							<td align="right" nowrap="nowrap" >真实姓名</td>
							<td><s:textfield name="userinfoPO.REAL_NAME"
									cssClass="required"></s:textfield></td>
						</tr>
						<tr>

							<td align="right" nowrap="nowrap" >年龄</td>
							<td><s:textfield name="userinfoPO.AGE"
									cssClass="{number:true,digits:true,min:0,max:120}"></s:textfield></td>

						</tr>
						<tr>

							<td align="right"  nowrap="nowrap" >性别</td>
							<td class="tb_inputtwo"><s:radio list="sex" listKey="value"
									listValue="display" name="userinfoPO.SEX" cssClass="radiostyle"/></td>
						</tr>
						<tr>

							<td align="right" nowrap="nowrap" ><span> * </span>手机</td>
							<td><s:textfield name="userinfoPO.MOBILE_NUMBER"
									id="userMobile" cssClass="{required:true,isPhone:true,maxlength:11,messages:{required:'请输入手机号码'}}"
									maxLength="11"></s:textfield></td>

						</tr>
						<tr>

							<td align="right" nowrap="nowrap" valign="top"><span> * </span>邮箱</td>
							<td class="tb_inputwo"><s:textfield name="userinfoPO.EMAIL"
									id="userEmail" cssClass="{required:true,email:true,maxlength:25,messages:{required:'请输入邮箱地址',email:'请正确输入邮箱地址'}}"
									maxLength="25"></s:textfield></td>
						</tr>
						<tr>
							<td></td>
							<td height="35" align="center" valign="bottom"><img
								onclick="subForm()" src="images/update.png" width="88"
								height="29" /></td>
						</tr>
					</table>
				</div>
		
	</s:form>
</body>
</html>
