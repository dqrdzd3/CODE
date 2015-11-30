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
<s:form action="k007" method="post" name="test"
	enctype="multipart/form-data" id="k007">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>预案库</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">

						<tr>
							<th scope="col">预案名称</th>
							<td scope="col" class="tb_input"><s:property value="k007PO.MA004"/></td>
							<th scope="col">事故类型</th>
							<td valign="middle" scope="col" class="tb_input">
							<s:property value="k007PO.MA006"/>————<s:property value="k007PO.MA007"/>
							</td>
							<th scope="col">预案类型</th>
							<td scope="col" class="tb_input">
							<s:select list="yazlList" name="k007PO.MA005"  listKey="value" listValue="name" headerValue="请选择" headerKey="" ></s:select>
							</td>
						</tr>
						<tr>
							<th scope="row">内&nbsp;&nbsp;&nbsp;&nbsp;容</th>
							<td colspan="5"><s:property value="k007PO.MA009"/></td>
						</tr>
						<tr>
							<th scope="col">附件列表</th>
							<td colspan="5" height="320"><iframe id="img" width="100%"
										height="320"
										src="<%=path%>/upload/fileupload!doAddupload?MA002=<s:property value="k007PO.MA001"/>&from=view"
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
