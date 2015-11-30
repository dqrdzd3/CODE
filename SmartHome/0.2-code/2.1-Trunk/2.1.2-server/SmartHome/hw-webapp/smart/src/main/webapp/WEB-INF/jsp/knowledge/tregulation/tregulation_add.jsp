<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<s:form action="tregulation" method="post" enctype="multipart/form-data"
	id="regulation">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>规章制度库</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain" style="width:100%;">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable" style="width: 98%;">
						<s:hidden name="tregulationPO.MA001" id="MA001"></s:hidden>
						<s:hidden name="tregulationPO.MA002" id="MA002"></s:hidden>
						<s:hidden name="tregulationPO.MA013" id="MA013"></s:hidden>
						<s:hidden name="tregulationPO.MA014" id="MA014"></s:hidden>
						<s:hidden name="tregulationPO.MA015" id="MA015"></s:hidden>
						<tr>
							<th scope="row" ><span class="red">* </span>制度名称</th>
							<td colspan="3"><s:textfield name="tregulationPO.MA004"
									cssClass="{required:true,maxlength:25}"></s:textfield></td>
						</tr>
						<tr>
							<th scope="row"><span class="red">* </span>编制单位</th>
							<td class="tb_inputtwo"><s:textfield
									name="tregulationPO.MA006"
									cssClass="{required:true,maxlength:100}"></s:textfield></td>
							<th>编制人</th>
							<td class="tb_inputtwo"><s:textfield
									name="tregulationPO.MA009" cssClass="{maxlength:25}"></s:textfield>
							</td>
						</tr>
						<tr>
							<th scope="row"><span>*</span>编制日期</th>
							<td class="tb_inputtwo"><s:textfield id="authorizedDate"
									name="tregulationPO.MA008" cssClass=" required Wdate"
									onfocus="hwDatePicker({maxDate:$dp.$(effectiveDate).value,dateFmt:'yyyy-MM-dd',readOnly:'true'})" />
							</td>

							<th>生效日期</th>
							<td class="tb_inputtwo"><s:textfield id="effectiveDate"
									name="tregulationPO.MA010" cssClass="dataISO  Wdate"
									onfocus="hwDatePicker({minDate:$dp.$(authorizedDate).value,dateFmt:'yyyy-MM-dd',readOnly:'true'})" />
							</td>

						</tr>
						<tr>
							<th scope="row"><span>(200字以内)</span>制度内容摘要</th>
							<td colspan="3"><s:textarea name="tregulationPO.MA005"
									maxlength="200" cssClass="textareamodify {maxlength:200}"></s:textarea>
							</td>
						</tr> 
						<tr>
							<th scope="row"><span>(200字以内)</span>备注</th>
							<td colspan="3"><s:textarea name="tregulationPO.MA011"
									maxlength="200" cssClass="textareamodify {maxlength:200}"></s:textarea>
							</td>
						</tr>

						<tr>
							<th scope="col">上传文件</th>
							<td colspan="5" class="tb_input" scope="col"><iframe
									id="img"  height="150" style="width:100%;"
									src=""
									frameborder=0 border=0 marginwidth=0 marginheight=0 scrolling=0></iframe>
								<input type="hidden" id="c004ids" name="c004ids" /></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id="windowsbottom">
			<div id="windowsbottomleft">
				<div id="windowsbottomright">
					<ul>
						<li><s:submit value="" method="doList" cssClass="back"></s:submit></li>
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
