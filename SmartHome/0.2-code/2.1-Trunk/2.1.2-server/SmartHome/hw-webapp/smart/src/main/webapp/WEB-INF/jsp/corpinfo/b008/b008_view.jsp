<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="b008" method="post">
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
							<th>评价名称</th>
							<td class="tb_inputtwo" scope="col"><s:textfield
									name="b008PO.ma004" cssClass='required' /></td>
							<th>评价单位</th>
							<td class="tb_inputtwo" scope="col"><s:textfield
									name="b008PO.ma005" /></td>
						</tr>
						<tr>
							<th>评价时间</th>
							<td class="tb_inputtwo" colspan="3" scope="col">
							<s:textfield name="b008PO.ma006"><s:param name="value"><s:date name="b008PO.ma006" format="yyyy-MM-dd"></s:date></s:param></s:textfield></td>
						</tr>
						<tr>
							<th>评价内容</th>
							<td colspan="3"><s:textarea name="b008PO.ma007"
									cssClass="{maxlength:100,minlength:0} textareamodify" /></td>
						</tr>
						<tr>
							<th>评价报告</th>
							<td colspan="3"><s:textarea name="b008PO.ma008" cols="45"
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
						<li class="cancel"><input type="button" class="back"
								onclick="closediv('showcont')"></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

</s:form>

