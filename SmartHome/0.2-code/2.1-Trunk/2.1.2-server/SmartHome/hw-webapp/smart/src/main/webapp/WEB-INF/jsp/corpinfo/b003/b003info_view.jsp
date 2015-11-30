<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
function closediv(){
	
$('#showinfo').dialog('close');
}
</script>
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
							<s:hidden name="paramPO.MA001" id="MA001"></s:hidden>
							<s:hidden name="paramPO.MA003" id="MA003"></s:hidden>
							<s:hidden name="paramPO.MA013" id="MA013"></s:hidden>

							<tr>
								<th>人员编号</th>
								<td class="tb_input"><s:textfield name="paramPO.MA002"
										id="MA002" cssClass='required'></s:textfield></td>
								<th> 姓名</th>
								<td class="tb_input"><s:textfield name="paramPO.MA004"
										id="MA004" cssClass='required'></s:textfield></td>
								<th>联系电话</th>
								<td class="tb_input"><s:textfield name="paramPO.MA009"
										id="MA009" cssClass='{isPhone:true,required:true}'></s:textfield></td>
							</tr>
							<tr>
								<th>性别</th>
								<td><s:select list="sex" id="sex"
										name="paramPO.MA016" listKey="value" listValue="name"
										headerKey="" headerValue="请选择" ength="25"></s:select></td>

								<th>民族</th>
								<td><s:select list="codeValueMZ" id="mz"
										name="paramPO.MA017" listKey="value" listValue="name"
										headerKey="" headerValue="请选择" ength="25"></s:select></td>

								<th>出生日期</th>
								<td><s:textfield name="paramPO.MA018"><s:param name="value"><s:date name="paramPO.MA018" format="yyyy-MM-dd"></s:date></s:param></s:textfield></td>
							</tr>
							<tr>
								<th>学历</th>
								<td><s:select list="codeValueXL" name="paramPO.MA019"
										listKey="value" listValue="name" headerKey=""
										headerValue="请选择" ength="25"></s:select></td>

								<th>技术职称</th>
								<td><s:select list="codeValueJSZC" name="paramPO.MA020"
										listKey="value" listValue="name" headerKey=""
										headerValue="请选择" ength="25"></s:select></td>

								<th> 职务</th>
								<td><s:textfield name="paramPO.MA005" id="MA005"
										cssClass='required'></s:textfield></td>
							</tr>
							<tr>
								<th>获得的奖项</th>
								<td colspan="5">无</td>
							</tr>
							<tr>	
								<th>获得的荣誉</th>
								<td colspan="5">无</td>
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
								onclick="closediv()"></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</s:form>
