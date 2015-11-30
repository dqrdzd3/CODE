<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="c006" method="post" id="c006" > 
	<s:hidden name="c006PO.ma001" id="ma001" />
	<s:hidden name="c006PO.ma021" id="ma021" />
	<s:hidden name="c006PO.ma011" id="ma011" />
	<s:hidden name="c006PO.ma009" id="ma009" />

	<div id="windows">

		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
							<th width="140" scope="row"><span>* </span> 企业名称：</th>
							<td colspan="2"><s:textfield name="c006PO.ma002"
									></s:textfield></td>
						</tr>
						<tr>
							<th scope="row"><span>* </span> 组织机构代码：</th>
							<td colspan="2"><s:textfield name="c006PO.ma003"
									></s:textfield></td>
						</tr>
						<tr>
							<th scope="row"><span>* </span> 经济类型：</th>
							<td colspan="2"><s:select list="codeValueJJLX"
									listKey="value" listValue="display" headerValue="--请选择---"
									cssClass='required' name="c006PO.ma007" id="ma007"></s:select>
							</td>
						</tr>
						<!--  
						<tr>
							<th scope="row"><span>* </span> 行业类型：</th>
							<td colspan="2"><s:select list="codeValueSSHY"
									listKey="value" listValue="display" headerValue="--请选择--"
									cssClass='required' name="c006PO.ma008" id="ma008"></s:select></td>
						</tr>-->
						
						<tr>
							<th scope="row"><span>* </span> 行业类型：</th>
							<td colspan="2"><s:select list="codeValueSSHY"
									listKey="value" listValue="display" headerValue="--请选择--"
									cssClass='required' name="c006PO.ma008" id="ma008"></s:select></td>
						</tr>

						<tr>
							<th scope="row"><span>* </span> 企业负责人姓名：</th>
							<td colspan="2"><s:textfield name="c006PO.ma012"
									readonly="true"></s:textfield></td>
						</tr>
						<tr>
							<th scope="row">负责人电话：</th>
							<td colspan="2"><s:textfield name="c006PO.ma013"
									readonly="true"></s:textfield></td>
						</tr>
						<tr>
							<th scope="row"><span>* </span> 负责人手机：</th>
							<td colspan="2"><s:textfield name="c006PO.ma014"
									readonly="true" /></td>
						</tr>
						<tr>
							<th scope="row"><span>* </span> 负责人邮箱：</th>
							<td colspan="2"><s:textfield name="c006PO.ma015"
									readonly="true"></s:textfield></td>
						</tr>
						<tr>
							<th scope="row"><span>* </span> 用户名：</th>
							<td colspan="2"><s:textfield name="c006PO.ma010"
									readonly="true"></s:textfield></td>
						</tr>
	

					</table>
				</div>
			</div>
		</div>
		<div id="windowsbottom">
			<div id="windowsbottomleft">
				<div id="windowsbottomright">
			      <ul>
			       <li class="save"><s:submit value="" cssClass="back" method="doList" style="border:none;"></s:submit></li>
				   <li class="cancel" style="margin-right:15px;">     
			        <s:submit value="" cssClass="submit" method="doSaveEdit" style="border:none;"></s:submit>
			       </li>
			      
			      <li>( 说明：<span>*</span>号位必填项)</li>
			      </ul>
				</div>
			</div>
		</div>
	</div>
</s:form>
