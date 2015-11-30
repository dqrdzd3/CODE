<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src='<s:url value="/js/util/noclick.js"></s:url>'></script>
<s:form id="roleView">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>角色修改</li>
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
							<td><s:textfield name="sysRolePO.ROLE_NAME" readonly="true"
									cssClass="{required:true,rangelength:[1,20]} stringCheck" /></td>
						</tr>
						<tr>
							<th><span class="red">*</span>授权类别</th>
							<td><s:select list="userType" listKey="value"
									listValue="display" headerKey="" headerValue="请选择"
									name="sysRolePO.USER_TYPE" disabled="true" cssClass="" /></td>
						</tr>
						<tr>
							<th>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</th>
							<td><s:textarea name="sysRolePO.REMARK" cols="45" rows="5" readonly="true"
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
						 	<input type="button" value="" class="back" onclick="closeDiv('roleDiv','gridTable');">
		      			</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

</s:form>
