<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src='<s:url value="/js/util/noclick.js"></s:url>'></script>
<script type="text/javascript">
$(function(){
	$("#treeBtn").hide();
});
</script>
<s:form id="addUser">
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
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable" id="userTb">
						<tr>
							<th><span> * </span>账户名称</th>
							<td class="tb_inputtwo">
								<s:textfield name="sysUserPO.LOGIN_NAME"
									id="loginName"
									cssClass="{required:true,alnum:true,rangelength:[6,25],remote:{type:'post',async:false,url:'manager/sysuser!checkOnlyLoginName',data:{loginName:function(){return $('#loginName').val();},'orgId':function(){return $('#orglist').val();}}},messages:{required:'请输入账户名称',remote:'账户名称已被使用'}}"
									/></td>
							<th><span> * </span>真实姓名</th>
							<td class="tb_inputtwo"><s:textfield name="sysUserPO.REAL_NAME"
									id="userName" cssClass="{required:true,rangelength:[2,10],messages:{required:'请输入姓名'}} " maxlength="10"  />
							</td>
						</tr>
						<tr>
							<th><span> * </span>密码</th>
							<td class="tb_inputtwo"><s:password name="sysUserPO.PASSWORD"
									id="loginPwd"
									cssClass="{required:true,rangelength:[6,25],alnum:true,messages:{required:'请输入密码'}}"
									/></td>
							<th><span> * </span>密码确认</th>
							<td class="tb_inputtwo"><s:password name="reptLoginPwd"
									id="reptLoginPwd"
									cssClass="{required:true,rangelength:[6,25],alnum:true,equalTo:'#loginPwd',messages:{required:'请输入密码',equalTo:'两次密码输入不一致'}}"
									/></td>
						</tr>
						<tr>
							<th>年龄</th>
							<td class="tb_inputtwo"><s:textfield name="sysUserPO.AGE" cssClass="{digits:true,max:100,min:15}"/></td>
							<th>性别</th>
							<td class="tb_inputtwo">
								<s:radio list="sex" listKey="value" listValue="display" name="sysUserPO.SEX" value="'M'"/>
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
								<s:select id="userType" list="userType" listKey="value" listValue="display" name="sysUserPO.USER_TYPE" headerKey="" headerValue="请选择" onchange="doChangeUserType(this)" cssClass="{required:true,messages:{required : '请选择用户类型'}}"/>
							</td>
							<th><span> * </span>状态</th>
							<td class="tb_inputtwo">
								<s:select list="userStatus" listKey="value" listValue="display" name="sysUserPO.USER_STATUS" value="'20'" cssClass="{required:true,messages:{required : '请选择用户类型'}}"/>
							</td>
						</tr>
						<tr>
							<th><span> * </span>是否支持手持设备</th>
							<td class="tb_inputtwo">
								<s:select id="isHand" list="isHand" listKey="value" listValue="display" name="sysUserPO.MOBILE_DEVICE_ACCESS" value="'0'" cssClass="{required:true,messages:{required : '请选择是否支持手持设备'}}"/>
							</td>
							<th><span> * </span>是否可外网访问</th>
							<td class="tb_inputtwo">
								<s:select id="isOuterNet" list="isOuterNet" listKey="value" listValue="display" name="sysUserPO.OUTER_NET_ACCESS" value="'0'" cssClass="{required:true,messages:{required : '请选择是否可外网访问'}}"/>
							</td>
						</tr>
						<tr  id="orgInfoTr">
							<th>企业/政府</th>
							<td class="tb_inputtwo" >
 								<input  id="treeBtn" type="button"
										onclick='selectName()' value="选择" style="width: auto" />
								<input type="hidden" id="orglist" name="sysUserPO.ORGAN_UUID" title="" />
							</td>
							<th>员工编号</th>
							<td class="tb_inputtwo">
								<s:textfield id="employeeId" name="sysUserPO.EMPLOYEE_NUM" cssClass="{remote:{type:'post',async:false,url:'manager/sysuser!checkOnlyEmployeeId',data:{employeeId:function(){return $('#employeeId').val();},'orgId':function(){return $('#orglist').val();}}},messages:{remote:'员工编号重复'}}"/>
							</td>
						</tr>
						<tr style="display: none">
							<th>是否管理员</th>
							<td class="tb_inputtwo">
								<s:textfield id="isAdmin" name="sysUserPO.IS_ADMIN" value="1"/>
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
						 	<input type="button" class="save" onclick="doSubmitAddData();">
				       	</li>
						<li>( 说明：<span>*</span>号位必填项)
						</li>


					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="divTop"     
        style=" background-color:#99CCFF; border: solid 2px #ff0000; position:absolute; display:none; width:400px; height:200px;">    
	<div style="text-align:center;">点击本区域或空白隐藏弹出层</div>    
</s:form>