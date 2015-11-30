<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	jQuery(function() {
		//核查駁回
		jQuery("#aaa").click(function() {
			var url = $$.getContextPath() + 'register/c006!doSaveHCN';
			var param = jQuery("#hcfrom").serialize();
			jQuery.post(url, param, function(data) {
				showMsg(data.content, data.type, {
					title : data.title,
					buttons : {
						'确定' : function() {
							$(this).dialog('close');
							$("#showd").dialog('close');
							reloadGrid("gridTable");
						}
					}
				});
			});

		});
		//核查通過
		jQuery("#bbb").click(function() {
			var url = $$.getContextPath() + 'register/c006!doSaveHCT';
			var param = jQuery("#hcfrom").serialize();
			jQuery.post(url, param, function(data) {
				showMsg(data.content, data.type, {
					title : data.title,
					buttons : {
						'确定' : function() {
							$(this).dialog('close');
							$("#showd").dialog('close');
							reloadGrid("gridTable");
						}
					}
				});
			});

		});
		var i=1;
		jQuery('.test').each(function(){
			if($(this).text()!=""){
				
				i++;
				$(this).parent().show();
			}
		});
		$("#hytype").attr('rowspan',i);
			
		
		//取消按鈕
		jQuery("#cancel").click(function() {
			var url = $$.getContextPath() + 'register/c006!doList';
			var param = jQuery("#hcfrom").serialize();
			jQuery.post(url, param, function(data) {
				$("#showd").dialog('close');
				reloadGrid("gridTable");
			});

		});

	});
</script>
<s:form name="hcfrom" action="c006" method="post" id="hcfrom">
	<s:hidden name="c006PO.ma001" />
	<s:hidden name="c006PO.ma021" />
	<s:hidden name="c006PO.ma011" />
	<s:hidden name="c006PO.ma009" />
	<s:hidden name="c006PO.ma002" />
	<s:hidden name="c006PO.ma003" />
	<s:hidden name="c006PO.ma007" />
	<s:hidden name="c006PO.ma012" />
	<s:hidden name="c006PO.ma013" />
	<s:hidden name="c006PO.ma014" />
	<s:hidden name="c006PO.ma015" />
	<s:hidden name="c006PO.ma010" />
	<s:hidden name="c006PO.hymlID" />
	<s:hidden name="c006PO.hydlID" />
	<s:hidden name="c006PO.hyzlID" />
	<s:hidden name="c006PO.hyxlID" />
	<div id="windows">

		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
							<th width="140" scope="row">企业名称：</th>
							<td colspan="2"><s:property value="c006PO.ma002" /></td>
						</tr>
						<tr>
							<th scope="row">组织机构代码：</th>
							<td colspan="2"><s:property value="c006PO.ma003" /></td>
						</tr>
						<tr>
							<th scope="row">经济类型：</th>
							<td colspan="2"><s:property value="c006PO.ma007Name" /></td>
						</tr>
						<tr>
							<th rowspan="4" id="hytype" scope="row">行业类型：</th>
							<td colspan="2"><s:property value="c006PO.hymlName" /></td>
						</tr>
						<tr  style="display: none">
							<td class="test" colspan="2" valign="top"><s:property
									value="c006PO.hydlName" /></td>
						</tr>
						<tr  style="display: none">
							<td colspan="2" valign="top" class="test"><s:property
									value="c006PO.hyzlName" /></td>
						</tr>
						<tr  style="display: none">
							<td colspan="2" valign="top" class="test"><s:property
									value="c006PO.hyxlName" /></td>
						</tr>
						<tr>
							<th scope="row">安全负责人姓名：</th>
							<td colspan="2"><s:property value="c006PO.ma012" /></td>
						</tr>
						<tr>
							<th scope="row">安全负责人电话：</th>
							<td colspan="2"><s:property value="c006PO.ma013" /></td>
						</tr>
						<tr>
							<th scope="row">安全负责人手机：</th>
							<td colspan="2"><s:property value="c006PO.ma014" /></td>
						</tr>
						<tr>
							<th scope="row">负责人邮箱：</th>
							<td colspan="2"><s:property value="c006PO.ma015" /></td>
						</tr>
						<tr>
							<th scope="row">用户名：</th>
							<td colspan="2"><s:property value="c006PO.ma010" /></td>
						</tr>
						<tr>
							<th scope="row">核查意见：</th>
							<td colspan="2"><s:textarea name="c006PO.ma024" cssClass="textareamodify" rows="10" cols="45"></s:textarea>

							
							</td>
						</tr>

					</table>
				</div>
			</div>
		</div>
		<div id="windowsbottom">
			<div id="windowsbottomleft">
				<div id="windowsbottomright">
					<ul>
						<a id="cancel">
							<li class="cancel"></li>
						</a>

						<a id="aaa"><li class="shbh">
								<%-- <s:submit value="" cssClass="shbh"method="doSaveHCN" style="border:none;"></s:submit> --%>

						</li></a>
						<a id="bbb"><li class="shtg" style="margin-right: 15px;">
								<%-- <s:submit value="" cssClass="shtg" method="doSaveHCT" style="border:none;"></s:submit> --%>
						</li></a>

					</ul>
				</div>
			</div>
		</div>
	</div>
</s:form>
