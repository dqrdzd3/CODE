<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
 <%
 	String path = request.getContextPath();
 	String basePath = request.getScheme() + "://"
 			+ request.getServerName() + ":" + request.getServerPort()
 			+ path + "/";
 %>
<script type="text/javascript">
	function closeDiv(id){
		$("#" + id).dialog('close');
	}
</script>
<s:form method="post" action="k004" enctype="multipart/form-data"
	id="k004">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>安全法律法规</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable" id="windowdivmaintable">
						<tr>
							<s:hidden name="k004PO.ma001"></s:hidden>
							<th> 标准名称</th>
							<td class="tb_input"><s:property value="k004PO.ma002"/></td>
							<th> 标准编号</th>
							<td class="tb_input"><s:property value="k004PO.ma003"/></td>
							<th align="right"> 标准类别</th>
							<td class="tb_input"><s:property value="k004PO.ma004"/></td>
						</tr>
						<tr>

							<th>颁布部门</th>
							<td><s:property value="k004PO.ma009"/></td>
							<th>颁布地区</th>
							<td><s:property value="k004PO.ma010"/></td>
							<th align="right">状态</th>
							<td><s:radio name="k004PO.ma007" list="#{'1':'有效','0':'作废'}"
									value="1" cssClass="required radiostyle" /></td>
						</tr>
						<tr>
							<th align="right">发布日期</th>
							<td><s:textfield value="k004PO.ma005"><s:param name="value"><s:date name="k004PO.ma005" format="yyyy-MM-dd"></s:date></s:param></s:textfield></td>
							<th>实施日期</th>
							<td><s:textfield value="k004PO.ma006"><s:param name="value"><s:date name="k004PO.ma006" format="yyyy-MM-dd"></s:date></s:param></s:textfield></td>
							<th align="right">失效日期</th>
			            	<td><s:textfield value="k004PO.ma008"><s:param name="value"><s:date name="k004PO.ma008" format="yyyy-MM-dd"></s:date></s:param></s:textfield></td>
						</tr>
						<tr>
							<th align="right">标准内容</th>
							<td colspan="5"><s:property value="k004PO.ma012"/></td>
						</tr>
						<!-- 上传文件展示区 -->
						<tr>
							<th scope="col">附件列表</th>
							<td colspan="5" height="320"><iframe id="img" width="100%"
										height="320"
										src="<%=path%>/upload/fileupload!doAddupload?MA002=<s:property value="k004PO.ma001"/>&from=view"
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
						<li class="back"><input type="button" value="" class="back"
							onclick="closeDiv('showcont')"></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</s:form>



