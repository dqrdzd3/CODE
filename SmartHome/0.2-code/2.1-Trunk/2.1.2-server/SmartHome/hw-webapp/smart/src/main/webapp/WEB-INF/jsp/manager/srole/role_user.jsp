<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	var num = 1 ;
	var gridOption = {
		caption : '用户管理',
		colModel : [ {
			label : '账户名称',
			name : 'LOGIN_NAME',
			index : 'LOGIN_NAME'
		}, {
			label : '真实姓名',
			name : 'REAL_NAME',
			index : 'REAL_NAME'
		}, {
			label : '企业/政府',
			name : 'ORGAN_UUID',
			index : 'ORGAN_UUID',hidden:true
		}, {
			label : '企业/政府',
			name : 'ORGNAME',
			index : 'ORGNAME'
		} ],
		id:'userList',
		formId:'searchUserForm',
		pk:'UUID',
		winTitel:"角色分配",
	 	url:'manager/sysuser!queryUserList.action'
	};

	var actionOption = {
		init : false
	};
	$(function(){
		if(userType == 'ENT'){
			num = 1;
		}
		if(userType == 'GOV'){
			num = 2;
		}
		initGrid(gridOption);
	});
	/**
	 *关闭弹出层，刷新列表 
	 */
	function closeRefresh(){
		closeDiv('roleDiv','gridTable');
	}
</script>
<div id="windows">
	<div id="windowsmain">

		<div id="windowdiv">
			<div id="windowdivmain">
				<div id="searcharea" style="margin-left: 10px;">
					<div id="searcharealeft">
						<div id="searcharearight">
						<div id="radiv"	>
							<table border="0" cellspacing="8" cellpadding="0" id="roleUserTb">
								<tr>
									<td style="width: 50px;" align="right">角色名称</td>
									<td style="width: 250px;">
										<s:hidden id="roleId" name="sysRolePO.UUID" /> 
										<s:textfield name="sysRolePO.ROLE_NAME" readonly="true" cssClass="input2" />
									</td>
								</tr>
							</table>
						</div>
							<s:form action="" method="post" cssClass="formmargin"
								id="searchUserForm">
								<table border="0" cellspacing="8" cellpadding="0">
									<tr>
										<td style="width: 80px;" align="right" scope="col">账户名称：</td>
										<td style="width: 280px;" scope="col">
											<input name="roleType" value="${sysRolePO.USER_TYPE}" type="hidden"/>
											<input name="roleOrgId" value="${sysRolePO.ORGAN_UUID}" type="hidden"/>
											<s:textfield  name="sysUserPO.LOGIN_NAME" cssClass="input2" />
										</td>
										<td style="width: 80px;" align="right" scope="col">真实姓名：</td>
										<td style="width: 280px;" scope="col">
											<s:textfield name="sysUserPO.REAL_NAME" cssClass="input2" />
										</td>
<!-- 										<td style="width: 80px;" align="right">企业：</td> -->
<!-- 										<td style="width: 280px;"> -->
<%-- 											<s:textfield name="sysUserPO.ORGAN_UUID" cssClass="input2" /> --%>
<!-- 										</td> -->
										<td width="98" rowspan="2" scope="col">
											<s:submit value="" cssClass="search"></s:submit>
										</td>
									</tr>
								</table>
							</s:form>
						</div>
					</div>
				</div>
		   		 <div class="tablestyle" style="width:100%;padding-bottom:0px;">
					<table id="userList" style="width:100%;"></table>
				</div>
			</div>
		</div>
	</div>
	<div id="windowsbottom">
		<div id="windowsbottomleft">
			<div id="windowsbottomright">
				<ul>
					<li class="back"><input type="button" class="back"
						onclick="closeRefresh()"></li>
					<li class="save" style="margin-right: 15px;"><input
						type="button" class="save" onclick="doSubmitUserData()"></li>
				</ul>
			</div>
		</div>
	</div>
</div>