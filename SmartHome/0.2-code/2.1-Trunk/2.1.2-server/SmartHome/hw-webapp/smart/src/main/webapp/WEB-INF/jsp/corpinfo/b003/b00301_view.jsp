<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<s:form action="b00301" method="post">
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
							<th>培训名称</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00301PO.MA003" cssClass="required"></s:textfield></td>
							<th>培训机构名称</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00301PO.MA004" cssClass="required"></s:textfield></td>
						</tr>
						<tr>
							<th>学时</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00301PO.MA006"></s:textfield></td>
							<th>考核结果</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00301PO.MA005"></s:textfield></td>
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
							<th>培训内容</th>
							<td colspan="5"><s:textarea name="b00301PO.MA008" id="MA008"
									cols="45" rows="5"
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
						<li class="cancel"><input type="button" class="back"
								onclick="closediv('showcont')"></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</s:form>

