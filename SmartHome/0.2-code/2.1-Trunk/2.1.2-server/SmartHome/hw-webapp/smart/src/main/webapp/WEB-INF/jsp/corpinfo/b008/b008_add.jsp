<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="b008" method="post" id="b008addform">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>添加安全评价情况</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
							<s:hidden name="b008PO.ma001" />
							<s:hidden name="b008PO.ma002" />
							<s:hidden name="b008PO.ma003" />
							<th><span>* </span>评价名称</th>
							<td class="tb_inputtwo" scope="col"><s:textfield
									name="b008PO.ma004" cssClass='{required:true,maxlength:30}' /></td>
							<th>评价单位</th>
							<td class="tb_inputtwo" scope="col"><s:textfield
									name="b008PO.ma005" cssClass='{maxlength:30}' /></td>
						</tr>
						<tr>
							<th>评价时间</th>
							<td class="tb_inputtwo" scope="col"><s:textfield
									name="b008PO.ma006" cssClass=" Wdate"
									onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})" /></td>
						</tr>
						<tr>
							<th><span>(100字以内)</span>评价内容</th>
							<td colspan="3"><s:textarea name="b008PO.ma007" maxlength="100"
									cssClass="{maxlength:100,minlength:0} textareamodify" /></td>
						</tr>
						<tr>
							<th><span>(100字以内)</span>评价报告</th>
							<td colspan="3"><s:textarea name="b008PO.ma008" cols="45" maxlength="100"
									rows="5" cssClass="{maxlength:100,minlength:0} textareamodify"></s:textarea></td>
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
								method="doList"></s:submit></li>
						<li class="cancel" style="margin-right: 15px;"><s:submit
								value="" cssClass="submit" method="doSaveEdit"></s:submit></li>
						<li>( 说明：<span>*</span>号位必填项)
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

</s:form>

