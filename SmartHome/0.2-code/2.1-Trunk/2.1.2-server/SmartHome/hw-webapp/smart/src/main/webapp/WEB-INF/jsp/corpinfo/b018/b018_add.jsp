<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="b018" method="post" id="b018addform">
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
								<th><span>*</span>急救药品资源代码</th>
								<td class="tb_input"><s:textfield name="b018PO.MA003"
										id="MA003" cssClass="{required:true,maxlength:18,stringCode:true}"></s:textfield></td>
								<th><span>*</span>药品名称</th>
								<td class="tb_input"><s:textfield name="b018PO.MA004"
										id="MA004" cssClass="{required:true,maxlength:18}"></s:textfield></td>
								<th><span>*</span>管理部门</th>
								<td class="tb_input"><s:textfield name="b018PO.MA005"
										id="MA005" cssClass="{required:true,maxlength:18}"></s:textfield></td>
							</tr>
							<tr>
								<th>数量</th>
								<td class="tb_input"><s:textfield name="b018PO.MA007"
										id="MA007" cssClass="{number:true,digits:true,maxlength:4,messages:{digits:'请输入正整数'}}"></s:textfield></td>
								<th>计量单位</th>
								<td class="tb_input"><s:textfield name="b018PO.MA006"
										id="MA006" cssClass="{maxlength:10}"></s:textfield></td>
								<th>单价</th>
								<td class="tb_input"><s:textfield name="b018PO.MA008"
										id="MA008" cssClass="{number:true,decimal:true,maxlength:2,min:0}"></s:textfield></td>
							</tr>
							<tr>
								<th>金额</th>
								<td class="tb_input"><s:textfield name="b018PO.MA009"
										id="MA009" cssClass="{number:true,maxlength:6, decimal:true,min:'0'}"></s:textfield></td>
								<th><span>*</span>有效期</th>
								<td colspan="3"><s:textfield name="b018PO.MA011"
										id="MA011" cssClass="required  Wdate" 
										onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})"></s:textfield></td>
							</tr>
							<tr>
								<th><span>*</span>存放场所</th>
								<td colspan="5"><s:textarea name="b018PO.MA010" id="MA010" maxlength="1000"
										cssClass="required {maxlength:1000,minlength:0} textareamodify"></s:textarea></td>
							</tr>
							<tr>
								<th><span>*</span>主要用途</th>
								<td colspan="5"><s:textarea name="b018PO.MA012" id="MA012" maxlength="1000"
										cssClass="required {maxlength:1000,minlength:0} textareamodify"></s:textarea></td>
							</tr>
							<tr>
								<th><span>*</span>使用方法</th>
								<td colspan="5"><s:textarea name="b018PO.MA013" id="MA013" maxlength="1000"
										cssClass="required {maxlength:1000,minlength:0} textareamodify"></s:textarea></td>
							</tr>
							<tr>
								<th><span>*</span>姓名<br>(具体负责人)</th>
								<td class="tb_input"><s:textfield name="b018PO.MA014"
										id="MA014"cssClass="{required:true,maxlength:18}"></s:textfield></td>
								<th><span>*</span>办公电话<br>(具体负责人)</th>
								<td class="tb_input"><s:textfield name="b018PO.MA015"
										id="MA015" cssClass="required isPhone"></s:textfield></td>
								<th><span>*</span>手机<br>(具体负责人)</th>
								<td class="tb_input"><s:textfield name="b018PO.MA016"
										id="MA016" cssClass="required isMobile"></s:textfield></td>
							</tr>
							<tr>
								<th><span>*</span>家庭电话<br>(具体负责人)</th>
								<td class="tb_input"><s:textfield name="b018PO.MA017"
										id="MA017" cssClass="required isPhone"></s:textfield></td>
								<th><span>*</span>姓名<br>(具体负责人)</th>
								<td class="tb_input"><s:textfield name="b018PO.MA018"
										id="MA018" cssClass="{required:true,maxlength:18}"></s:textfield></td>
								<th><span>*</span>办公电话<br>(具体负责人)</th>
								<td class="tb_input"><s:textfield name="b018PO.MA019"
										id="MA019" cssClass="required isPhone"></s:textfield></td>
							</tr>
							<tr>
								<th><span>*</span>手机<br>(具体负责人)</th>
								<td class="tb_input"><s:textfield name="b018PO.MA020"
										id="MA020" cssClass="required isMobile"></s:textfield></td>
								<th><span>*</span>家庭电话<br>(具体负责人)</th>
								<td colspan="3"><s:textfield name="b018PO.MA021" id="MA021"
										cssClass="required isPhone"></s:textfield></td>
							</tr>
							<tr>
								<th>备注<br><span>(100字以内)</span></th>
								<td colspan="5"><s:textarea name="b018PO.MA023" id="MA023" maxlength="100"
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
					<li class="cancel" style="margin-right: 15px;"><input
						type="button" class="back" onclick="alert(1111)" /></li>
					<li class="save"><s:submit value="" cssClass="submit"
							method="doSaveEdit"></s:submit></li>
					<li>( 说明：<span>*</span>号位必填项)
					</li>
				</ul>
			</div>
		</div>
	</div>
</s:form>