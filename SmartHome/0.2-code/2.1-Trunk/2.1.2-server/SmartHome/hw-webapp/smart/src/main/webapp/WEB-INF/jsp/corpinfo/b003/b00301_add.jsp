<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<script type="text/javascript">
</script>
<s:form action="b00301" method="post" id="b00301addform">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>人员培训信息</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table width="100%" cellpadding="0" cellspacing="0"
						class="windowdivmaintable">
						<s:hidden name="b00301PO.MA001" />
						<s:hidden name="b00301PO.MA002" />

						<tr>
							<th><span>*</span>培训名称</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00301PO.MA003" cssClass="{required:true,maxlength:50}"></s:textfield></td>
							<th><span>*</span>培训机构名称</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00301PO.MA004" cssClass="{required:true,maxlength:50}"></s:textfield></td>
						</tr>
						<tr>
							<th>学时</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00301PO.MA006" cssClass="{maxlength:20}"></s:textfield></td>
							<th>考核结果</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00301PO.MA005" cssClass="{maxlength:10}"></s:textfield></td>
						</tr>
						<tr>
							<th>培训时间</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00301PO.MA007" cssClass="Wdate"  
									onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'}) "><s:param name="value"><s:date name="b00301PO.MA007" format="yyyy-MM-dd"/></s:param></s:textfield></td>
							<th></th>
							<td colspan="2" class="tb_inputtwo"></td>
						</tr>
						<tr>
							<th><span>(100字以内)</span>培训内容</th>
							<td colspan="5"><s:textarea name="b00301PO.MA008" id="MA008"
									cols="45" rows="5" maxlength="100"
									cssClass="{maxlength:100,minlength:0} textareamodify"></s:textarea>
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
						<li class="save"><s:submit value="" cssClass="back"
								method="doListB00301" style="border:none;"></s:submit></li>
						<li class="cancel" style="margin-right: 15px;"><s:submit
								value="" cssClass="submit" method="doSaveB00301"
								style="border:none;"></s:submit></li>
						<li>( 说明：<span>*</span>号位必填项)
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</s:form>

