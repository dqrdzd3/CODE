<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form id="addM001">

	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>设备新增</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<div>
						<table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
							<tr>
								<th scope="col"  width="150px"><span>*</span>设备名称</th>
								<td class="tb_inputtwo" scope="col">
									<s:textfield name="m001PO.MA002" cssClass="{required:true,stringMaxLength:30,messages:{required:'请输入设备名称'}}" />
							 	</td>
								<th scope="col"  width="150px"><span>*</span>设备ID</th>
								<td class="tb_inputtwo" scope="col">
									<s:textfield name="m001PO.MA003" cssClass="{required:true,string:true,stringMaxLength:60,messages:{required:'请输入设备ID'}}" />
								</td>
							</tr>
							<tr>
								<th scope="col"  width="150px">客户端程序版本</th>
								<td class="tb_inputtwo" scope="col">
									<s:textfield name="m001PO.MA004" cssClass="{string:true,stringMaxLength:20}" />
								</td>
								<th scope="col"  width="150px">客户端系统平台</th>
								<td class="tb_inputtwo" scope="col">
									<s:textfield name="m001PO.MA005" cssClass="{string:true,stringMaxLength:20}" />
								</td>
							</tr>
							<tr>
								<th scope="col"  width="150px">客户端系统版本</th>
								<td class="tb_inputtwo" scope="col">
									<s:textfield name="m001PO.MA006" cssClass="{string:true,stringMaxLength:20}" />
								</td>
								<th scope="col"  width="150px">客户端型号</th>
								<td class="tb_inputtwo" scope="col">
									<s:textfield name="m001PO.MA007" cssClass="{string:true,stringMaxLength:20}" />
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
							onclick="closeDiv('m001Div')">
						</li>
						<li class="save" style="margin-right: 15px;"><input
							type="button" value="" class="save" onclick="doSubmitAddData();">
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>


</s:form>