<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="b003" id="ab003" method="post">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>安全管理</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable" id="windowdivmaintable">
						<tbody>

							<s:hidden name="b003PO.MA001" id="MA001"></s:hidden>
							<s:hidden name="b003PO.MA003" id="MA003"></s:hidden>
							<s:hidden name="b003PO.MA013" id="MA013"></s:hidden>

							<tr>
								<th>人员编号</th>
								<td class="tb_input"><s:textfield name="b003PO.MA002"
										id="MA002" cssClass='required'></s:textfield></td>
								<th> 姓名</th>
								<td class="tb_input"><s:textfield name="b003PO.MA004"
										id="MA004" cssClass='required'></s:textfield></td>
								<th>联系电话</th>
								<td class="tb_input"><s:textfield name="b003PO.MA009"
										id="MA009" cssClass='{isPhone:true,required:true}'></s:textfield></td>
							</tr>
							<tr>
								<th>性别</th>
								<td><s:select list="sex" id="sex"
										name="b003PO.MA016" listKey="value" listValue="name"
										headerKey="" headerValue="请选择" ength="25"></s:select></td>

								<th>民族</th>
								<td><s:select list="codeValueMZ" id="mz"
										name="b003PO.MA017" listKey="value" listValue="name"
										headerKey="" headerValue="请选择" ength="25"></s:select></td>

								<th>出生日期</th>
								<td><s:textfield name="b003PO.MA018"><s:param name="value"><s:date name="b003PO.MA018" format="yyyy-MM-dd"></s:date></s:param></s:textfield></td>
							</tr>
							<tr>
								<th>学历</th>
								<td><s:select list="codeValueXL" name="b003PO.MA019"
										listKey="value" listValue="name" headerKey=""
										headerValue="请选择" ength="25"></s:select></td>

								<th>技术职称</th>
								<td><s:select list="codeValueJSZC" name="b003PO.MA020"
										listKey="value" listValue="name" headerKey=""
										headerValue="请选择" ength="25"></s:select></td>

								<th> 职务</th>
								<td><s:textfield name="b003PO.MA005" id="MA005"
										cssClass='required'></s:textfield></td>
							</tr>
							<tr>
								<th>工作部门</th>
								<td><s:textfield name="b003PO.MA010" id="MA010"></s:textfield>
								</td>
								<th>人员类别</th>
								<td><s:select list="codeValueAQRYLB"
										onchange="roletochange1()" id="ma006" name="b003PO.MA006"
										listKey="value" listValue="display" headerKey=""
										headerValue="请选择" ength="25" cssClass='required'></s:select>
								</td>
								<th>特殊工种</th>
								<td><s:select list="codeValueTSGZ" id="tsgz"
										disabled='true' name="b003PO.MA027" listKey="value"
										listValue="name" headerKey="" headerValue="请选择" ength="25"></s:select></td>
							</tr>
							<tr style="display: none;" id="_aptitude">
								<th>主要负责人资质证书</th>
								<td colspan="5"><s:textfield name="b003PO.MA007" id="MA007"></s:textfield>
								</td>
							</tr>
							<tr style="display: none;" id="_grade">
								<th>级别</th>
								<td colspan="5"><s:textfield name="b003PO.MA011" id="MA011"></s:textfield>
								</td>
							</tr>
							<tr style="display: none;" id="_complexion">
								<th>注册情况</th>
								<td colspan="5"><s:textfield name="b003PO.MA008" id="MA008"></s:textfield>
								</td>
							</tr>
							<tr>
								<th>备注</th>
								<td colspan="5"><s:textarea name="b003PO.MA015" id="b003MA015"
										cols="45" rows="5"
										cssClass="{maxlength:100,minlength:0} textareamodify"></s:textarea>
								</td>
							</tr>
						</tbody>
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
<script type="text/javascript">
	function roletochange1() {
		if (jQuery('#ab003 #ma006').val() == '3') {
			jQuery('#ab003 #tsgz').attr('disabled', false);

		} else {
			jQuery('#ab003 #tsgz').val("");
			jQuery('#ab003 #tsgz').attr('disabled', true);
		}
	}
</script>
