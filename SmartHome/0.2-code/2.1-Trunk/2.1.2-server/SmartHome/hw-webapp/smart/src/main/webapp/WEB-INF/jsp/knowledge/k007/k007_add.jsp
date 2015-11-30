<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:action name="include(uploadify)" executeResult="true" />
<s:form action="k007" method="post" name="test"
	enctype="multipart/form-data" id="k007">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>预案库</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<s:hidden name="k007PO.MA001" />
						<s:hidden name="k007PO.MA002" />
						<tr>
							<th scope="col"><span class="red">* </span>预案名称</th>
							<td scope="col" class="tb_input"><s:textfield
									name="k007PO.MA004" id="Ma004"
									cssClass="{required:true,maxlength:25}"></s:textfield></td>
							<th scope="col"><span class="red">*</span> 事故类型</th>
							<td valign="middle" scope="col" class="tb_input"><s:doubleselect
									label="事故类型" name="k007PO.MA006" list="codeValueList"
									listKey="value" listValue="name" value="value"
									doubleList="codeValueZlMap.get(top.value)"
									doubleListKey="value" doubleListValue="name"
									doubleValue="value" doubleName="k007PO.MA007" formName="test" />
							</td>
							<th scope="col"><span><span class="red">*</span></span>
								预案类型</th>
							<td scope="col" class="tb_input"><s:select list="yazlList"
									name="k007PO.MA005" id="k007PO.MA005" listKey="value"
									listValue="name" headerKey="" headerValue="请选择" ength="25"
									cssClass="required"></s:select></td>
						</tr>
						<tr>
							<th scope="row">内&nbsp;&nbsp;&nbsp;&nbsp;容</th>
							<td colspan="5"><s:textarea name="k007PO.MA009"
									id="k007PO.MA009" maxlength="2000" cols="45" rows="5"
									cssClass="{maxlength:2000,minlength:0} textarea1">
								</s:textarea></td>
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
						<li><input type="button" class="back" onclick="closeDiv()" /></li>
						<li class="save" style="margin-right: 15px;"><s:submit
								value="" method="doSaveAdd" cssClass="submit"></s:submit></li>
						<li>( 说明：<span>*</span>号位必填项)
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</s:form>
