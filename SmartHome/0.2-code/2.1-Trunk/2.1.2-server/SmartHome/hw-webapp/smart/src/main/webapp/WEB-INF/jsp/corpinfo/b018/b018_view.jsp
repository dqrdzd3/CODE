<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="b018" method="post">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>应急药品</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table cellpadding="0" cellspacing="0" class="windowdivmaintable">
						<tbody>
							<s:hidden name="b018PO.MA001"></s:hidden>
							<s:hidden name="b018PO.MA002"></s:hidden>
							<s:hidden name="b018PO.MA022"></s:hidden>
							<tr>
								<th>急救药品资源代码</th>
								<td class="tb_input"><s:textfield name="b018PO.MA003"
										id="MA003" cssClass="required"></s:textfield></td>
								<th>药品名称</th>
								<td class="tb_input"><s:textfield name="b018PO.MA004"
										id="MA004" cssClass="required"></s:textfield></td>
								<th>管理部门</th>
								<td class="tb_input"><s:textfield name="b018PO.MA005"
										id="MA005" cssClass="required"></s:textfield></td>
							</tr>
							<tr>
								<th>数量</th>
								<td class="tb_input"><s:textfield name="b018PO.MA007"
										id="MA007" cssClass="{number:true,digits:true,messages:{digits:'请输入正整数'}}"></s:textfield></td>
								<th>计量单位</th>
								<td class="tb_input"><s:textfield name="b018PO.MA006"
										id="MA006"></s:textfield></td>
								<th>单价</th>
								<td class="tb_input"><s:textfield name="b018PO.MA008"
										id="MA008" cssClass="number decimal"></s:textfield></td>
							</tr>
							<tr>
								<th>金额</th>
								<td class="tb_input"><s:textfield name="b018PO.MA009"
										id="MA009" cssClass="{number:true, decimal:true,min:'0'}"></s:textfield></td>
								<th>有效期</th>
								<td colspan="3"><s:textfield name="b018PO.MA011"id="MA011" cssClass="required  Wdate"><s:param name="value"><s:date name="b018PO.MA011" format="yyyy-MM-dd"></s:date></s:param></s:textfield></td>
							</tr>
							<tr>
								<th>存放场所</th>
								<td colspan="5"><s:textarea name="b018PO.MA010" id="MA010"
										cssClass="required {maxlength:1500,minlength:0} textareamodify"></s:textarea></td>
							</tr>
							<tr>
								<th>主要用途</th>
								<td colspan="5"><s:textarea name="b018PO.MA012" id="MA012"
										cssClass="required {maxlength:1000,minlength:0} textareamodify"></s:textarea></td>
							</tr>
							<tr>
								<th>使用方法</th>
								<td colspan="5"><s:textarea name="b018PO.MA013" id="MA013"
										cssClass="required {maxlength:1000,minlength:0} textareamodify"></s:textarea></td>
							</tr>
							<tr>
								<th>具体负责人-姓名</th>
								<td class="tb_input"><s:textfield name="b018PO.MA014"
										id="MA014" cssClass="required"></s:textfield></td>
								<th>具体负责人-办公电话</th>
								<td class="tb_input"><s:textfield name="b018PO.MA015"
										id="MA015" cssClass="required isPhone"></s:textfield></td>
								<th>具体负责人-手机</th>
								<td class="tb_input"><s:textfield name="b018PO.MA016"
										id="MA016" cssClass="required isPhone"></s:textfield></td>
							</tr>
							<tr>
								<th>具体负责人-家庭电话</th>
								<td class="tb_input"><s:textfield name="b018PO.MA017"
										id="MA017" cssClass="required isPhone"></s:textfield></td>
								<th>单位负责人-姓名</th>
								<td class="tb_input"><s:textfield name="b018PO.MA018"
										id="MA018" cssClass="required"></s:textfield></td>
								<th>单位负责人-办公电话</th>
								<td class="tb_input"><s:textfield name="b018PO.MA019"
										id="MA019" cssClass="required isPhone"></s:textfield></td>
							</tr>
							<tr>
								<th>单位负责人-手机</th>
								<td class="tb_input"><s:textfield name="b018PO.MA020"
										id="MA020" cssClass="required isPhone"></s:textfield></td>
								<th>单位负责人-家庭电话</th>
								<td colspan="3"><s:textfield name="b018PO.MA021" id="MA021"
										cssClass="required isPhone"></s:textfield></td>
							</tr>
							<tr>
								<th>备注</th>
								<td colspan="5"><s:textarea name="b018PO.MA023" id="MA023"
										cssClass="{maxlength:100,minlength:0} textareamodify"></s:textarea></td>
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
				<li class="cancel"><input type="button" class="back"onclick="closediv('showcont')"></li>
				</ul>
			</div>
		</div>
	</div>
</s:form>