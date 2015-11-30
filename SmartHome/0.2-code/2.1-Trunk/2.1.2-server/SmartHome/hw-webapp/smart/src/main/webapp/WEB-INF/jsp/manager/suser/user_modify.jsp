<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src='<s:url value="/js/util/noclick.js"></s:url>'></script>
<s:form  id="editUser">
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
								<s:hidden id="uuid" name="sysUserPO.UUID" />
								<s:textfield name="sysUserPO.LOGIN_NAME" readonly="true" cssClass="noedit"
									id="loginName"
									/></td>
<!-- 									cssClass="alnum {required:true,maxlength:20,remote:{type:'post',url:'manager/orguser!checkOnlyLoginName',data:{loginName:function(){return $('#loginName').val();}}},messages:{required:'请输入账号名称',remote:'登录用户名已被使用'}}" -->
							<th><span> * </span>真实姓名</th>
							<td class="tb_inputtwo"><s:textfield name="sysUserPO.REAL_NAME"
									id="userName" cssClass="{required:true,rangelength:[2,10],messages:{required:'请输入账号名称'}} " maxlength="10"
									/></td>
						</tr>
						<tr>
							<th>年龄</th>
							<td class="tb_inputtwo"><s:textfield name="sysUserPO.AGE" cssClass="{digits:true,max:100,min:15}"/></td>
							<th>性别</th>
							<td class="tb_inputtwo">
								<s:radio list="sex" listKey="value" listValue="display" name="sysUserPO.SEX"/>
							</td>
						</tr>
						<tr>
							<th><span> * </span>手机</th>
							<td class="tb_inputtwo"><s:textfield name="sysUserPO.MOBILE_NUMBER" cssClass="{required:true,isPhone:true,messages:{required:'请输入手机号码'}}"/></td>
							<th><span> * </span>邮箱</th>
							<td class="tb_inputtwo"><s:textfield name="sysUserPO.EMAIL" cssClass="{required:true,email:true,messages:{required:'请输入邮箱地址',email:'请正确输入邮箱地址'}}"/></td>
						</tr>
						<tr>
							<th><span> * </span>用户类型</th>
							<td class="tb_inputtwo">
								<s:hidden name="sysUserPO.USER_TYPE"/>
								<s:textfield name="sysUserPO.userTypeName" readonly="true"/>
							</td>
							<th><span> * </span>状态</th>
							<td class="tb_inputtwo">
								<s:select list="userStatus" listKey="value" listValue="display"  name="sysUserPO.USER_STATUS" />
							</td>
						</tr>
						<tr>
							<th><span> * </span>是否支持手持设备</th>
							<td class="tb_inputtwo">
								<s:select id="isHand" list="isHand" listKey="value" listValue="display" name="sysUserPO.MOBILE_DEVICE_ACCESS"  cssClass="{required:true,messages:{required : '请选择是否支持手持设备'}}"/>
							</td>
							<th><span> * </span>是否可外网访问</th>
							<td class="tb_inputtwo">
								<s:select id="isOuterNet" list="isOuterNet" listKey="value" listValue="display" name="sysUserPO.OUTER_NET_ACCESS" cssClass="{required:true,messages:{required : '请选择是否可外网访问'}}"/>
							</td>
						</tr>
						<tr  id="orgInfoTr">
							<th>企业/政府</th>
							<td class="tb_inputtwo" >
								<s:hidden id="orgId" name="sysUserPO.ORGAN_UUID"/>
								<s:textfield name="sysUserPO.orgName" readonly="true" cssClass="noedit"/>
							</td>
							<th>员工编号</th>
							<td>
								<s:textfield id="employeeId" name="sysUserPO.EMPLOYEE_NUM"  cssClass="{remote:{type:'post',async:false,url:'manager/sysuser!checkOnlyEmployeeId',data:{employeeId:function(){return $('#employeeId').val();},uuid:function(){return $('#uuid').val();},'orgId':function(){return $('#orgId').val();}}},messages:{remote:'员工编号重复'}}"/>
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
						 	<input type="button" class="back" onclick="closeDiv('userDiv','gridTable');">
		      			</li>
				       	<li class="save" style="margin-right:15px;">
						 	<input type="button" class="save" onclick="doSubmitEditData();">
				       	</li>
						<li>( 说明：<span>*</span>号位必填项)
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</s:form>