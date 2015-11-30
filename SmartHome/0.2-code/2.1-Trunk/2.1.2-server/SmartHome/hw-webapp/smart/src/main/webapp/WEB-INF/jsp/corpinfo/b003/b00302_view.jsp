<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="b00301" method="post">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>人员证书信息</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table width="100%" cellpadding="0" cellspacing="0"
						class="windowdivmaintable">
						<s:hidden name="b00302PO.MA001" />
						<s:hidden name="b00302PO.MA002" />

						<tr>
							<th>证书编号</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00302PO.MA003" cssClass="required"></s:textfield></td>
							<th>证书名称</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00302PO.MA004" cssClass="required"></s:textfield></td>
						</tr>
						<tr>
							<th>发证部门</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00302PO.MA005"></s:textfield></td>
							<th>发证日期</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00302PO.MA006"  id="b00302MA006" cssClass="Wdate"  
									onclick="hwDatePicker({maxDate:$dp.$('b00302MA007').value,dateFmt:'yyyy-MM-dd'})"><s:param name="value"><s:date name="b00302PO.MA007" format="yyyy-MM-dd"/></s:param></s:textfield></td>
						</tr>
						<tr>
							<th>有效日期</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00302PO.MA007"  id="b00302MA007" cssClass="Wdate" 
									onclick="hwDatePicker({minDate:$dp.$('b00302MA006').value,dateFmt:'yyyy-MM-dd'})"><s:param name="value"><s:date name="b00302PO.MA007" format="yyyy-MM-dd"/></s:param></s:textfield></td>
							<th></th>
							<td colspan="2" class="tb_inputtwo"></td>
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

