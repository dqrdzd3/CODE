<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:action name="include(uploadify)" executeResult="true" />
<s:form method="post" action="k003" id="k003"
	enctype="multipart/form-data">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>安全法律法规</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable" id="windowdivmaintable">
						<tr>
							<s:hidden name="k003PO.ma001"></s:hidden>
							<th><span class="red">*</span> 法规名称</th>
							<td class="tb_input"><s:textfield name="k003PO.ma002"
									cssClass="{required:true,maxlength:250}" /></td>
							<th><span class="red">*</span> 法规编号</th>
							<td class="tb_input"><s:textfield name="k003PO.ma003"
									cssClass="{required:true,maxlength:50}" /></td>
							<th align="right"><span class="red">*</span> 法规类别</th>
							<td class="tb_input"><s:textfield name="k003PO.ma004"
									cssClass="{required:true,maxlength:25}" /></td>
						</tr>
						<tr>
							<th>颁布部门</th>
							<td><s:textfield name="k003PO.ma009" id="pubdept"
									cssClass="{maxlength:50}" /></td>
							<th>颁布地区</th>
							<td><s:textfield name="k003PO.ma010" id="area"
									cssClass="{maxlength:50}" /></td>
							<th align="right">状态</th>
							<td><s:radio name="k003PO.ma007" list="#{'1':'有效','0':'作废'}"
									value="1" cssClass="required" /></td>
						</tr>
						<tr>
							<th align="right">发布日期</th>
							<td><s:textfield id="publishDate" name="k003PO.ma005"
									cssClass="Wdate"
									onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true', maxDate:$dp.$('implementDate').value})" />
							</td>
							<th>实施日期</th>
							<td><s:textfield id="implementDate" name="k003PO.ma006"
									cssClass="Wdate"
									onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true', minDate:$dp.$('publishDate').value, maxDate:$dp.$('invalidDate').value})" />
							</td>
							<th align="right">失效日期</th>
							<td><s:textfield id="invalidDate" name="k003PO.ma008"
									cssClass="Wdate"
									onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true', minDate:$dp.$('implementDate').value})" />
							</td>
						</tr>

						<tr>
							<th align="right"><span>(100字以内)</span>法规内容</th>
							<td colspan="5"><s:textarea name="k003PO.ma012"
									maxlength="100" cssClass="textarea1 {maxlength:100}" /></td>
						</tr>
						<tr>
							<th scope="col">上传文件</th>
							<td colspan="5" class="tb_input" scope="col"><iframe
									id="img" width="100%" height="150" src="" frameborder=0
									border=0 marginwidth=0 marginheight=0 scrolling=0></iframe> <input
								type="hidden" id="c004ids" name="c004ids" /></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id="windowsbottom">
			<div id="windowsbottomleft">
				<div id="windowsbottomright">
					<ul>
						<li class="save"><input type="button" class="back"
							onclick="closeDiv()" /></li>
						<li class="cancel" style="margin-right: 15px;"><s:submit
								value="" cssClass="submit" method="doSaveAdd"
								style="border:none;"></s:submit></li>
						<li>( 说明：<span>*</span>号位必填项)
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</s:form>


