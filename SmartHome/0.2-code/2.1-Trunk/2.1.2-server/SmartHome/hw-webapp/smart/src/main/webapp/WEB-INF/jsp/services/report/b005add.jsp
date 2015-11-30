<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>" />
<s:form action="b005" method="post" id="b005addform">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>企业执照</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
			</div>
			<div id="windowdiv">
				<div id="windowdivmain">
					<table cellspacing="0" cellpadding="0" class="windowdivmaintable">
						<tbody>
							<s:hidden name="b005PO.MA001" id="ma001" />
							<s:hidden name="b005PO.MA003" />
							<s:hidden name="b005PO.MA008" />
							<s:hidden name="b005PO.MA012" />
							<s:hidden name="b005PO.MA010" />

							<tr>
								<th><span> *</span> 执照名称</th>
								<td class="tb_inputtwo"><s:textfield name="b005PO.MA004"
										id="MA004" cssClass='{required:true,maxlength:50}'></s:textfield></td>
								<th><span> * </span>执照编号</th>
								<td class="tb_inputtwo"><s:textfield name="b005PO.MA002"
										id="MA002" cssClass='{required:true,maxlength:25}'></s:textfield></td>

							</tr>
							<tr>
								<th><span> *</span>发证单位</th>
								<td class="tb_inputtwo"><s:textfield name="b005PO.MA005"
										id="MA005" cssClass='{required:true,maxlength:50}'></s:textfield></td>
								<th><span> *</span> 有效期</th>
								<td class="tb_inputtwo"><s:textfield name="b005PO.MA007" id="MA007"
										 cssClass='required Wdate' 
										 onclick="hwDatePicker({minDate:$dp.$('MA006').value,dateFmt:'yyyy-MM-dd',readOnly:'true'})"></s:textfield>
										
								</td>
							</tr>
							<tr>
								<th><span> *</span> 发证时间</th>
								<td colspan="3"><s:textfield name="b005PO.MA006" id="MA006"
										cssClass='required Wdate' 
										onclick="hwDatePicker({maxDate:$dp.$('MA007').value,dateFmt:'yyyy-MM-dd',readOnly:'true'})"
										style=" width:247px;"><s:param name="value"><s:date name="b005PO.MA006" format="yyyy-MM-dd"/></s:param></s:textfield></td>
							</tr>
							<tr>
								<th>图片文件</th>
								<td colspan="5" height="320"><iframe id="img" width="100%"
										height="320"
										src=""
										frameborder=0 border=0 marginwidth=0 marginheight=0
										scrolling=0></iframe> <input type="hidden" id="c004ids"
									name="c004ids" /></td>
							</tr>
							<tr>
								<th>备注<br><span>(100字以内)</span></th>
								<td colspan="3"><s:textarea name="b005PO.MA009" id="MA009"
										cols="45" rows="5" maxlength="100"
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
