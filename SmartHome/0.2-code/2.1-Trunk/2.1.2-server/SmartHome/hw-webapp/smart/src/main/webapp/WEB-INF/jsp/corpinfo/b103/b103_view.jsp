<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
$(function(){
	$("#tsgz").hide();
	$("#tsgzbt").hide();
	$("#testid").hide();
	$("#upload").hide();
	if($('#ma006').val()=='3'){
		$("#upload").show();
	}
 	$("#tsgzbt").parent().append('<td colspan="4" id="colid"></td>');
});
</script>
<s:form action="b103" id="ab103" method="post">
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

							<s:hidden name="b103PO.MA001" id="MA001"></s:hidden>
							<s:hidden name="b103PO.MA003" id="MA003"></s:hidden>
							<s:hidden name="b103PO.MA005" id="MA005" ></s:hidden>
							<s:hidden name="b103PO.MA013" id="MA013"></s:hidden>

							<tr>
								<th><span> * </span>人员编号</th>
								<td class="tb_input"><s:textfield name="b103PO.MA002"
										id="MA002" cssClass='{required:true,maxlength:25}'></s:textfield></td>
								<th><span> *</span> 姓名</th>
								<td class="tb_input"><s:textfield name="b103PO.MA004"
										id="MA004" cssClass='{required:true,maxlength:10}'></s:textfield></td>
								<th><span>*</span>联系电话</th>
								<td class="tb_input"><s:textfield name="b103PO.MA009"
										id="MA009" cssClass='{isPhone:true,required:true}'></s:textfield></td>
							</tr>
							<tr>
								<th>性别</th>
								<td><s:radio list="sex"  listKey="value" listValue="name" name="b103PO.MA016" cssClass="radiostyle"  disabled="true" value="1"></s:radio></td>
								<th>民族</th>
								<td><s:select list="codeValueMZ" id="mz"
										name="b103PO.MA017" listKey="value" listValue="name"
										headerKey="" headerValue="请选择" ength="25"></s:select></td>

								<th>出生日期</th>
								<td><s:date name="b103PO.MA018" format="yyyy-MM-dd" ></s:date></td>
							</tr>
							<tr>
								<th>学历</th>
								<td><s:select list="codeValueXL" name="b103PO.MA019"
										listKey="value" listValue="name" headerKey=""
										headerValue="请选择" ength="25"></s:select></td>

								<th>技术职称</th>
								<td><s:select list="codeValueJSZC" name="b103PO.MA020"
										listKey="value" listValue="name" headerKey=""
										headerValue="请选择" ength="25"></s:select></td>

                                <th>传真</th>
								<td><s:textfield name="b103PO.MA010" id="MA010" cssClass="{isPhone:true,messages:{isPhone:'请输入正确的传真号码'}}"></s:textfield>
								</td>
							</tr>
							<tr>
								<th><span> *</span> 人员类别123</th>
								<td><s:select list="codeValueAQRYLB"
										id="ma006" name="b103PO.MA006"  disabled="true" headerKey=""
										headerValue="请选择"
										listKey="value" listValue="display"  ength="25" cssClass='required'></s:select>
									<input name="b103PO.MA024" id="MA024" type="hidden"/>
								</td>
								<th id="tsgzbt">特殊工种</th>
								<td id="testid"><s:select list="codeValueTSGZ" id="tsgz"
										 name="b103PO.MA023" listKey="value"
										listValue="display" headerKey="" headerValue="请选择" ength="25"></s:select></td>
							
							</tr>
							<tr>
								<th><span>(100字以内)</span>备注</th>
								<td colspan="5"><s:textarea name="b103PO.MA015" id="b103MA015"
										cols="45" rows="5" maxlength="100"
										cssClass="{maxlength:100,minlength:0} textareamodify"></s:textarea>
								</td>
							</tr>
							<tr id="upload">
								<th>图片文件</th>
								<td colspan="5" height="320"><iframe id="img" width="100%"
										height="320"
										src="<%=path%>/upload/initfileuploadnew?MA002=<s:property value="b103PO.MA001"/>&from=view"
										frameborder=0 border=0 marginwidth=0 marginheight=0
										scrolling=0></iframe> <input type="hidden" id="c004ids"
									name="c004ids" /></td>
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
								onclick="closediv('showcont1')"></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</s:form>
