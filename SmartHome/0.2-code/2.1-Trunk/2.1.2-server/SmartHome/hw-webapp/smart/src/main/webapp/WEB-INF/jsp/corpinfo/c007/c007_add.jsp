<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form name="addfrom" action="c007" method="post" id="addfrom">
	<s:hidden name="c007PO.parentid" id="parentId"/>
	<s:hidden name="c007PO.id" id="uuid"/>
    <s:hidden name="c007PO.levelnum" id="levelnum"/>
	<div id="windows">

		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
						<tr>
							<th><span> * </span>编码</th>
							<td class="tb_inputtwo">
								<s:hidden name="c007PO.parentKey" id="parentKey"/>
								<s:textfield name="c007PO.key" id="dictKey" 
									cssClass="{required:true,alnum:true,maxlength:10,remote:{type:'post',async:false,url:'manager/sysDict!checkOnlyKey',data:{dictKey:function(){return $('#dictKey').val();},parentKey:function(){return $('#parentKey').val();},uuid:function(){return $('#uuid').val();}}},messages:{remote:'编码已被使用'}}" maxLength="10" onkeyup="changeFullKey();"></s:textfield></td>
							<th><span> * </span>值</th>
							<td class="tb_inputtwo"><s:textfield name="c007PO.value" id="dictValue"
									cssClass="{required:true,alnum:true,maxlength:10,remote:{type:'post',async:false,url:'manager/sysDict!checkOnlyValueUnderParentNode',data:{dictValue:function(){return $('#dictValue').val();},parentId:function(){return $('#parentId').val();},uuid:function(){return $('#uuid').val();}}},messages:{remote:'值已被使用'}}" maxLength="10"></s:textfield></td>
						</tr>
						<tr>
							<th><span> * </span>显示内容</th>
							<td class="tb_inputtwo"><s:textfield name="c007PO.display" cssClass="{required:true,stringMaxLength:50}" maxLength="50"></s:textfield></td>
							<th><span> * </span>排序</th>
							<td class="tb_inputtwo"><s:textfield name="c007PO.ordernum" cssClass="{required:true,digits:true,range:[0,9999]}" maxLength="4"></s:textfield></td>
						</tr>
						<tr>
							<th>完整编码</th>
							<td colspan="3">
								<input type="text" disabled name="fullKey" class="{maxlength:50}"/>
							</td>
						</tr>
						<tr>
							<th>备注</th>
							<td colspan="3"><s:textarea name="c007PO.describe" cols="45"
									rows="5" cssClass="{stringMaxLength:100} textarea1" maxLength="100"/></td>
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
								method="doList" style="border:none;"></s:submit></li>
						<li class="cancel" style="margin-right: 15px;"><s:submit
								value="" cssClass="submit" method="doSaveAdd"
								style="border:none;"></s:submit></li>

						<li>( 说明：<span>*</span>号位必填项)
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</s:form>