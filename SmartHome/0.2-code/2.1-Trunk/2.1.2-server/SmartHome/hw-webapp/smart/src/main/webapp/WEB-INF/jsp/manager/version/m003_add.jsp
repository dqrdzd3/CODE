<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
window.uploadOptions={
		'fileTypeExts' : '*.apk;',
		'multi' : false,
		'uploadLimit' : 1,
};
</script>
<s:form id="addM003">

	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>安装程序版本新增</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<div>
						<table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
							<tr>
								<th scope="col"  width="150px"><span> * </span>版本名称</th>
								<td class="tb_inputtwo" scope="col">
									<s:textfield name="m003PO.MA002" cssClass="{required:true,stringMaxLength:30,messages:{required:'请输入版本名称'}}" />
							 	</td>
								<th scope="col"  width="150px">版本代号</th>
								<td class="tb_inputtwo" scope="col">
									<s:textfield name="m003PO.MA003" cssClass="number decimal" />
								</td>
							</tr>
							<tr>
								<th scope="col"  width="150px">版本说明</th>
								<td colspan="3" class="tb_inputtwo" scope="col">
									<s:textarea name="m003PO.MA004" cssClass="{maxlength:2000,minlength:0} textareamodify" />
								</td>
							</tr>
							<tr>	
								<th scope="col"  width="150px">程序上传</th>
								<td colspan="3" class="tb_inputtwo" scope="col">
									<!-- 放2个按钮 -->
						      		<iframe id="img" width="100%" height="150" src="<%=path%>/upload/fileupload!doAddupload?type=*.apk;&limit=1&MA002=" frameborder=0 border=0  marginwidth=0  marginheight=0  scrolling=0 ></iframe> 
						            <input type="hidden" id="c004ids" name="c004ids" />
								</td>
							</tr>
							
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="windowsbottom">
			<div id="windowsbottomleft">
				<div id="windowsbottomright">
					<ul>
						<li class="back"><input type="button" value="" class="back"
							onclick="closeDiv('m003Div')">
						</li>
						<li class="save" style="margin-right: 15px;"><input
							type="button" value="" class="save" onclick="doSubmitAddData();">
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>


</s:form>