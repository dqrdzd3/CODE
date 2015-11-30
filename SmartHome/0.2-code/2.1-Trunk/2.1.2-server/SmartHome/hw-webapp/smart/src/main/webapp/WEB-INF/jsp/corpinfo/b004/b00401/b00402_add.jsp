<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="b00402" method="post">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>设备设施年检信息</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tbody>
							<s:hidden name="b00402PO.ma001" id="MA001"></s:hidden>
							<s:hidden name="b00402PO.ma002" id="MA002"></s:hidden>
							<s:hidden name="b00402PO.ma009" ></s:hidden>
							<s:hidden name="b00402PO.ma010" ></s:hidden>
							<s:hidden name="b00402PO.ma011" ></s:hidden>
							<tr>
								<th scope="col"><span>* </span>检验机构</th>
								<td class="tb_inputtwo"><s:textfield name="b00402PO.ma003" cssClass='{required:true,maxlength:50}'></s:textfield></td>
								<th scope="col"><span>* </span>检验时间</th>
								<td class="tb_inputtwo"><s:textfield name="b00402PO.ma005"  cssClass=" Wdate" onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})"></s:textfield></td>
							</tr>
							<tr>
								<th scope="col"><span>*</span>是否通过检验</th>
								<td class="tb_inputtwo">
								<s:radio name="b00402PO.ma006" cssClass="required"list="codeValueSF" listKey="value" listValue="name"></s:radio>
								</td>
								<th scope="col">周期</th>
								<td class="tb_inputtwo">
								<s:textfield name="b00402PO.ma008" ></s:textfield></td>
							</tr>
							<tr>
								<th scope="col">检验结果</th>
								<td class="tb_inputtwo"><s:textarea name="b00402PO.ma007" cssClass='{maxlength:50}'></s:textarea></td>
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
					 <li class="cancel"><input type="button" class="back"/></li>
					<li class="save" style="margin-right: 15px;"><s:submit
							value="" cssClass="submit" method="doSaveEdit"></s:submit></li>
					<li>( 说明：<span>*</span>号位必填项)
					</li>
				</ul>
			</div>
		</div>
	</div>

</s:form>

