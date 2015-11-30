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
							<th><span>*</span>证书编号</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00302PO.MA003" cssClass="{required:true,maxlength:20}"></s:textfield></td>
							<th><span>*</span>证书名称</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00302PO.MA004" cssClass="{required:true,maxlength:50}"></s:textfield></td>
						</tr>
						<tr>
							<th>发证部门</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00302PO.MA005" cssClass="{maxlength:50}"></s:textfield></td>
							<th>发证日期</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00302PO.MA006"  id="b00302MA006" cssClass="Wdate"  
									onclick="hwDatePicker({maxDate:$dp.$('b00302MA007').value,dateFmt:'yyyy-MM-dd'})"></s:textfield></td>
						</tr>
						<tr>
							<th><span>*</span>有效日期</th>
							<td colspan="2" class="tb_inputtwo"><s:textfield
									name="b00302PO.MA007"  id="b00302MA007" cssClass="Wdate required" 
									onclick="hwDatePicker({minDate:$dp.$('b00302MA006').value,dateFmt:'yyyy-MM-dd'})"></s:textfield></td>
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

