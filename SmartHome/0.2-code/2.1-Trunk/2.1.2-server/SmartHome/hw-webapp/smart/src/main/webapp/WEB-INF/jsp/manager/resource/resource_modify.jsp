<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src='<s:url value="/js/util/noclick.js"></s:url>'></script>
<s:form id="editResource">

	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>资源修改</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<div>
						<table border="0" cellspacing="0" cellpadding="0"
							class="windowdivmaintable">

							<tr>
								<th scope="col" width="250px">资源名称</th>
								<td class="tb_inputtwo" scope="col">
									<s:hidden name="sysResourcePO.UUID" /> 
									<s:textfield name="sysResourcePO.RESOURCE_NAME" cssClass="{required:true,stringMaxLength:20,messages:{required:'请输入资源名称'}}" />
								</td>
							</tr>
							<tr>
								<th scope="col"  width="250px">URL链接</th>
								<td class="tb_inputtwo" scope="col">
									<s:textfield name="sysResourcePO.URL" cssClass="{stringMaxLength:200}" />
								</td>
							</tr>
							<tr>
								<th scope="col"  width="250px">绑定函数</th>
								<td class="tb_inputtwo" scope="col">
									<s:textfield name="sysResourcePO.BIND_FUNC" cssClass="{stringMaxLength:200}" />
								</td>
							<tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="windowsbottom">
			<div id="windowsbottomleft">
				<div id="windowsbottomright">
					<ul>
						<li class="back"><input type="button" value="" class="back"
							onclick="closeDiv('resourceDiv','gridTable');">
						</li>
						<li class="save" style="margin-right: 15px;"><input
							type="button" value="" class="save" onclick="doSubmitEditData();">
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

</s:form>