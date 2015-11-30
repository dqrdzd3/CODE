<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<s:form action="tregulation" method="post" enctype="multipart/form-data"
	id="regulation">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>规章制度库</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0" style="width:98%;"
						class="windowdivmaintable">
						<tr>
							<th >制度名称</th>
							<td colspan="3"><s:property value="tregulationPO.MA004"/></td>
						</tr>
						<tr>
							<th >编制单位</th>
							<td class="tb_inputtwo"><s:property value="tregulationPO.MA006"/></td>
							<th>编制人</th>
							<td class="tb_inputtwo"><s:property value="tregulationPO.MA009"/></td>
						</tr>
						<tr>
							<th >编制日期</th>
							<td class="tb_inputtwo"><s:textfield name="tregulationPO.MA008"><s:param name="value"><s:date name="tregulationPO.MA008" format="yyyy-MM-dd"></s:date></s:param></s:textfield></td>

							<th>生效日期</th>
							<td class="tb_inputtwo"><s:textfield name="tregulationPO.MA010"><s:param name="value"><s:date name="tregulationPO.MA010" format="yyyy-MM-dd"></s:date></s:param></s:textfield></td>

						</tr>
						<tr>
							<th >制度内容摘要</th>
							<td colspan="3" style="width:600px;"><s:property value="tregulationPO.MA005"/></td>

						</tr>
						<tr>
							<th >备注</th>
							<td colspan="3" style="width:600px;"><s:property value="tregulationPO.MA011"/></td>
						</tr>

<!-- 						上传文件展示区 -->
						<tr>
							<th >附件列表</th>
							<td colspan="3" height="320"><iframe id="img" width="100%"
										height="320"
										src="<%=path%>/upload/fileupload!doAddupload?MA002=<s:property value="tregulationPO.MA001"/>&from=view"
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
							onclick="closediv('showcont')"></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</s:form>
