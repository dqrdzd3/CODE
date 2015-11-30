<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="b00401" method="post">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>设备设施</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tbody>

							<s:hidden name="b00401PO.MA001" id="MA001"></s:hidden>
							<s:hidden name="b00401PO.MA002" id="MA002"></s:hidden>
							<s:hidden name="b00401PO.MA003" id="MA003"></s:hidden>
							<tr>
								<th scope="col">设备名称</th>
								<td class="tb_inputtwo"><s:textfield name="b00401PO.MA004"
										id="MA004" cssClass='required'></s:textfield></td>
								<th scope="col">规格型号</th>
								<td class="tb_inputtwo"><s:textfield name="b00401PO.MA005"
										id="MA005"></s:textfield></td>
							</tr>
							<tr>
								<th scope="col">设备类型</th>
								<td class="tb_inputtwo"><s:select name="b00401PO.MA006" cssClass="required"
										list="codeValueSBLX" listKey="value" listValue="name"
										headerKey="" headerValue="请选择"></s:select></td>
								<th scope="col">制造单位</th>
								<td class="tb_inputtwo"><s:textfield name="b00401PO.MA007"
										id="MA007"></s:textfield></td>
							</tr>
							<tr>
								<th scope="col">制造日期</th>
								<td class="tb_inputtwo">
								<s:textfield name="b00401PO.MA008" id="MA008" cssClass="Wdate"	onclick="hwDatePicker({maxDate:$dp.$('MA010').value,dateFmt:'yyyy-MM-dd',readOnly:'true'})">
								<s:param name="value"><s:date name="b00401PO.MA008"  format="yyyy-MM-dd"/></s:param></s:textfield></td>
								<th style="width: 140px;">安装单位</th>
								<td class="tb_inputtwo"><s:textfield name="b00401PO.MA009"
										id="MA009"></s:textfield></td>
							</tr>
							<tr>
								<th scope="col">启用日期</th>
								<td class="tb_inputtwo"><s:textfield name="b00401PO.MA010"
										id="MA010" cssClass="Wdate"
										onclick="hwDatePicker({minDate:$dp.$('MA008').value,maxDate:$dp.$('MA011').value,dateFmt:'yyyy-MM-dd',readOnly:'true'})"
										><s:param name="value"><s:date name="b00401PO.MA010"  format="yyyy-MM-dd"/></s:param></s:textfield></td>

								<th scope="col">有效截止日期</th>
								<td class="tb_inputtwo"><s:textfield name="b00401PO.MA011"
										id="MA011" cssClass=" Wdate required"
										onclick="hwDatePicker({minDate:$dp.$('MA010').value,dateFmt:'yyyy-MM-dd',readOnly:'true'})"
										><s:param name="value"><s:date name="b00401PO.MA011"  format="yyyy-MM-dd"/></s:param></s:textfield></td>
							</tr>
							<tr>
								<th scope="col">数量</th>
								<td class="tb_inputtwo"><s:textfield name="b00401PO.MA021" cssClass="required digits"
										id="MA021"></s:textfield></td>
								<th scope="col">是否是应急资源</th>
								<td class="tb_inputtwo"><s:select name="b00401PO.MA015"
										id="MA015" cssClass='required' list="#{'0':'是','1':'否'}"
										headerKey="" headerValue="请选择"></s:select></td>
							</tr>
							<tr>
								<th scope="col">监控管理负责人</th>
								<td class="tb_inputtwo"><s:textfield name="b00401PO.MA012"
										id="MA012"></s:textfield></td>
								
								<th scope="col"></th>
								<td class="tb_inputtwo"></td>
							</tr>

							<tr>
								<th scope="col">定期检测情况</th>
								<td colspan="3"><s:textarea name="b00401PO.MA013"
										id="MA013" cols="45" rows="5"
										cssClass="{maxlength:500,minlength:0} textareamodify"></s:textarea></td>
							</tr>

							<tr>
								<th scope="col">保养及检查情况</th>
								<td colspan="3"><s:textarea name="b00401PO.MA014"
										id="MA014" cols="45" rows="5"
										cssClass="{maxlength:500,minlength:0} textareamodify"></s:textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</DIV>
			</DIV>
		</DIV>
		<p></p>
	</div>
	<div id="windowsbottom">
		<div id="windowsbottomleft">
			<div id="windowsbottomright">
				<ul>
					 <li class="cancel"><input type="button" class="back" onclick="closediv('showcont')"/></li>
				</ul>
			</div>
		</div>
	</div>
</s:form>

