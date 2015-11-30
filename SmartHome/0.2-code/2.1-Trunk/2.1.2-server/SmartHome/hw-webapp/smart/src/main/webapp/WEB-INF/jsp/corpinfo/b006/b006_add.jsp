<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>" />
<s:form action="b006" method="post" id="b006addform">
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
							<s:hidden name="b006PO.ma001" id="ma001"></s:hidden>
							<s:hidden name="b006PO.ma002" id="ma002"></s:hidden>
							<s:hidden name="b006PO.ma003" id="ma003"></s:hidden>
							<s:hidden name="b006PO.ma017" id="ma017"></s:hidden>
							<th scope="col"><span>*</span>方位</th>
							<td class="tb_inputtwo" scope="col"><s:select
									list="#{'东':'东','南':'南','西':'西','北':'北','东南':'东南','东北':'东北','西南':'西南','西北':'西北'}"
									name="b006PO.ma004" headerKey="" headerValue="请选择" ength="25" cssClass="required"></s:select>
							</td>
							<th scope="col"><span>* </span>单位名称</th>
							<td class="tb_inputtwo"><s:textfield name="b006PO.ma006"
									cssClass="{required:true,maxlength:100}"></s:textfield></td>
						</tr>
						<tr>
							<th scope="col">距离（米）</th>
							<td class="tb_inputtwo" scope="col"><s:textfield
									name="b006PO.ma007" cssClass="{number:true,maxlength:17,min:0}"></s:textfield></td>
							<th scope="col">人员数量</th>
							<td class="tb_inputtwo" scope="col"><s:textfield
									name="b006PO.ma011" cssClass="{min:0,digits:true,maxlength:19}"></s:textfield></td>
						</tr>
						<tr>
							<th scope="col">建筑高度（米）</th>
							<td class="tb_inputtwo" scope="col"><s:textfield
									name="b006PO.ma009" cssClass="{number:true,maxlength:17,min:0}"></s:textfield></td>
							<th scope="col">人员类型</th>
							<td class="tb_inputtwo" scope="col"><s:textfield
									name="b006PO.ma010" cssClass="{maxlength:30}"></s:textfield></td>
						</tr>
						<tr>
							<th scope="col">建筑结构</th>
							<td class="tb_inputtwo" scope="col"><s:textfield
									name="b006PO.ma008" cssClass="{maxlength:30}"></s:textfield></td>
						</tr>
						<tr>
							<th scope="col">相关图片</th>
							<td colspan="5" height="320"><iframe id="img" width="100%"
									height="320" src="" frameborder=0 border=0 marginwidth=0
									marginheight=0 scrolling=0></iframe> <input type="hidden"
								id="c004ids" name="c004ids" /></td>
						</tr>
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

