<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src='<s:url value="/js/util/noclick.js"></s:url>'></script>
<s:form  id="viewUser">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li></li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0" id="userTb"
						class="windowdivmaintable">
						<tr>
							<th><span> * </span>账户名称</th>
							<td class="tb_inputtwo">
								<s:textfield name="sysUserPO.LOGIN_NAME"  readonly="true"
									id="loginName"
									maxLength="20" /></td>
							<th><span> * </span>真实姓名</th>
							<td class="tb_inputtwo"><s:textfield name="sysUserPO.REAL_NAME" readonly="true"
									id="userName" cssClass="{required:true,maxlength:20}"
									maxLength="20"/></td>
						</tr>
						<tr style="display: none">
							<th><span> * </span>密码</th>
							<td class="tb_inputtwo"><s:password name="sysUserPO.PASSWORD" 
									id="loginPwd"
									cssClass="{required:true,rangelength:[6,25],alnum:true}"
									maxLength="20"/></td>
							<th><span> * </span>密码确认</th>
							<td class="tb_inputtwo"><s:password name="reptLoginPwd"
									id="reptLoginPwd"
									cssClass="{required:true,rangelength:[6,25],alnum:true,equalTo:'#loginPwd',messages:{equalTo:'两次密码输入不一致'}}"
									maxLength="20"/></td>
						</tr>
						<tr>
							<th>年龄</th>
							<td class="tb_inputtwo"><s:textfield name="sysUserPO.AGE" readonly="true"/></td>
							<th>性别</th>
							<td class="tb_inputtwo">
							<s:radio list="sex" listKey="value" listValue="display" name="sysUserPO.SEX" disabled="true"/>
							</td>
						</tr>
						<tr>
							<th><span> * </span>手机</th>
							<td class="tb_inputtwo"><s:textfield name="sysUserPO.MOBILE_NUMBER" readonly="true"/></td>
							<th><span> * </span>邮箱</th>
							<td class="tb_inputtwo"><s:textfield name="sysUserPO.EMAIL" readonly="true"/></td>
						</tr>
						<tr>
							<th><span> * </span>用户类型</th>
							<td class="tb_inputtwo">
<%-- 								<s:select list="userType" listKey="value" listValue="display" disabled="true" name="sysUserPO.USER_TYPE" headerKey="" headerValue="请选择"  onchange="doChangeUserType(this)"/> --%>
								<s:hidden name="sysUserPO.USER_TYPE"/>
								<s:textfield name="sysUserPO.userTypeName" readonly="true"/>
							</td>
							<th><span> * </span>状态</th>
							<td class="tb_inputtwo">
								<s:select list="userStatus" listKey="value" listValue="display" disabled="true" name="sysUserPO.USER_STATUS" />
							</td>
						</tr>
						<tr>
							<th><span> * </span>是否支持手持设备</th>
							<td class="tb_inputtwo">
								<s:select id="isHand" list="isHand" listKey="value" listValue="display" disabled="true" name="sysUserPO.MOBILE_DEVICE_ACCESS" />
							</td>
							<th><span> * </span>是否可外网访问</th>
							<td class="tb_inputtwo">
								<s:select id="isOuterNet" list="isOuterNet" listKey="value" listValue="display" disabled="true" name="sysUserPO.OUTER_NET_ACCESS" />
							</td>
						</tr>
						<tr   id="orgInfoTr">
							<th>企业/政府</th>
							<td class="tb_inputtwo">
								<s:hidden name="sysUserPO.ORGAN_UUID" readonly="true"/>
								<s:textfield name="sysUserPO.orgName" readonly="true"/>
							</td>
							<th>员工编号</th>
							<td class="tb_inputtwo"><s:textfield name="sysUserPO.EMPLOYEE_NUM" readonly="true"/></td>
						</tr>
						<tr>
							<th>是否管理员</th>
							<td class="tb_inputtwo">
								否
							</td>
							<th>部门名称</th>
							<td class="tb_inputtwo">
								${departmentPO.depart_name}
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id="windowsbottom">
			<div id="windowsbottomleft">
				<div id="windowsbottomright">
					<ul>
  						<li class="back">
						 	<input type="button" class="back" onclick="closeDiv('userDiv');">
		      			</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</s:form>