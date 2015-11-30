<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<s:form action="b006" method="post">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>更新企业周边环境</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">

						<tr>
							<th scope="col">方位</th>
							<td class="tb_inputtwo" scope="col"><s:select
									list="#{'东':'东','南':'南','西':'西','北':'北','东南':'东南','东北':'东北','西南':'西南','西北':'西北'}"
									name="b006PO.ma004" headerKey="" headerValue="请选择" ength="25"></s:select>
							</td>
							<th scope="col">单位名称</th>
							<td class="tb_inputtwo"><s:textfield name="b006PO.ma006"
									cssClass="required"></s:textfield>
							<s:hidden name="b006PO.ma001" id="ma001"></s:hidden>
							<s:hidden name="b006PO.ma002" id="ma002"></s:hidden>
							<s:hidden name="b006PO.ma003" id="ma003"></s:hidden>
									</td>

						</tr>
						<tr>
							<th scope="col">距离（米）</th>
							<td class="tb_inputtwo" scope="col"><s:textfield
									name="b006PO.ma007"></s:textfield></td>
							<th scope="col">人员数量</th>
							<td class="tb_inputtwo" scope="col"><s:textfield
									name="b006PO.ma011"></s:textfield></td>
						</tr>
						<tr>
							<th scope="col">建筑高度（米）</th>
							<td class="tb_inputtwo" scope="col"><s:textfield
									name="b006PO.ma009"></s:textfield></td>
							<th scope="col">人员类型</th>
							<td class="tb_inputtwo" scope="col"><s:textfield
									name="b006PO.ma010"></s:textfield></td>
						</tr>
						<tr>
							<th scope="col">建筑结构</th>
							<td class="tb_inputtwo" scope="col" colspan="3"><s:textfield
									name="b006PO.ma008"></s:textfield></td>
						</tr>
						<tr>
							<th scope="col">相关图片</th>
							<td colspan="3" height="320"><iframe id="img" width="100%"
										height="320"
										src="<%=path%>/upload/initfileuploadnew?MA002=<s:property value="b006PO.ma001"/>&from=view"
										frameborder=0 border=0 marginwidth=0 marginheight=0
										scrolling=0>
										</iframe><input type="hidden" id="c004ids" name="c004ids" ></td>
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

