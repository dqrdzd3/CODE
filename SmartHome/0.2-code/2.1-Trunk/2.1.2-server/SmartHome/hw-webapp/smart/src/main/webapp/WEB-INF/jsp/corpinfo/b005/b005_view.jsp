<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<base href="<%=basePath%>" />
<s:form action="b005" method="post" id="b005Form">
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
				<div id="windowtitle">
					<div id="windowtitleleft">
						<div>企业执照表</div>
					</div>
				</div>
				<div id="windowdivmain">
					<table cellspacing="0" cellpadding="0" class="windowdivmaintable">
						<tbody>
							<s:hidden name="b005PO.MA001" id="ma001" />
							<s:hidden name="b005PO.MA003" />
							<s:hidden name="b005PO.MA008" />
							<s:hidden name="b005PO.MA012" />
							<s:hidden name="b005PO.MA010" />

							<tr>
								<th> 执照名称</th>
								<td class="tb_inputtwo"><s:textfield name="b005PO.MA004"
										id="MA004" cssClass='required'></s:textfield></td>
								<th>执照编号</th>
								<td class="tb_inputtwo"><s:textfield name="b005PO.MA002"
										id="MA002" cssClass='required'></s:textfield></td>

							</tr>
							<tr>
								<th>发证单位</th>
								<td class="tb_inputtwo"><s:textfield name="b005PO.MA005"
										id="MA005" cssClass='required'></s:textfield></td>
								<th> 有效期</th>
								<td class="tb_inputtwo"><s:textfield name="b005PO.MA007" ><s:param name="value"><s:date name="b005PO.MA007" format="yyyy-MM-dd"></s:date></s:param></s:textfield>
										
								</td>
							</tr>  
							<tr>
								<th>发证时间</th>
								<td colspan="3"><s:textfield name="b005PO.MA006" id="MA006"
										cssClass='required Wdate' 
										onclick="hwDatePicker({maxDate:$dp.$('MA007').value,dateFmt:'yyyy-MM-dd',readOnly:'true'})"
										style=" width:247px;"><s:param name="value"><s:date name="b005PO.MA006" format="yyyy-MM-dd"/></s:param></s:textfield></td>
							</tr>
							<tr> 
								<th>图片文件</th>
								<td colspan="5" height="320"><iframe id="img" width="100%"
										height="320"
										src="<%=path%>/upload/initfileuploadnew?MA002=<s:property value="b005PO.MA001"/>&from=view"
										frameborder=0 border=0 marginwidth=0 marginheight=0
										scrolling=0>
										</iframe><input type="hidden" id="c004ids" name="c004ids" ></td>
							</tr>
							<tr>
								<th>备注</th>
								<td colspan="3"><s:textarea name="b005PO.MA009" id="MA009"
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
