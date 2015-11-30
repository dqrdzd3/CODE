<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
 $(function(){
	 if(userType == 'ENT' || userType == 'GOV'){
		 $("#roleDiv #userType").find("option[value='"+userType+"']").attr("selected",true);
		 $("#roleDiv #userType").find("option[value!='"+userType+"']").remove();
	 }
	 
 });
</script>

<s:form id="addRole">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>添加角色</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
							<th><span class="red">*</span>角色名称</th>
							<td>
								<s:hidden id="roleId" name="sysRolePO.UUID"/>
								<s:textfield name="sysRolePO.ROLE_NAME" id="roleName"  cssClass="{required:true,rangelength:[1,20],remote:{type:'post',async:false,url:'manager/sysrole!checkOnlyRoleName',data:{roleName:function(){return $('#roleName').val();},roleId:function(){return $('#roleId').val();}}},messages:{required : '请输入角色名称',remote:'角色名称已被使用'}}" />
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>授权类别</th>
							<td>
								<s:select id="userType" list="userType" listKey="value" listValue="display" headerKey="" headerValue="请选择" name="sysRolePO.USER_TYPE" cssClass="{required:true,messages:{required : '请选择授权人类型'}}"/>
							</td>
						</tr>
						<tr>
							<th>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</th>
							<td><s:textarea name="sysRolePO.REMARK"
									cols="45" rows="5"
									style="width:99%; height:160px; "
									cssClass="textareamodify {maxlength:100}"></s:textarea></td>
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
						 	<input type="button" class="back" onclick="closeDiv('roleDiv','gridTable');">
		      			</li>
				       	<li class="save" style="margin-right:15px;">
						 	<input type="button" class="save" onclick="doSubmitAddData();">
				       	</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</s:form>

