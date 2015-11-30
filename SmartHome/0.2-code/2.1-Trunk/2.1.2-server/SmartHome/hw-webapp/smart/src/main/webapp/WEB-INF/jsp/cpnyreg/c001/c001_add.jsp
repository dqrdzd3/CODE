<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
$(function() {
	$.getJSON('gov/c002!queryAllValidInstanceToJson', function(data){
		var data = $.parseJSON(data);
		$.each(data, function(i, v) {
			$("#ma004").append("<option value='"+v.ma001+"'>"+v.ma003+"</option>");
		});
	});
});
</script>
<s:form name="addfrom" action="c001" method="post">
	<s:hidden name="c001PO.MA001" id="ma001"/>
	<div id="windows">

		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
						<tr>
							<th><span> * </span>企业名称</th>
							<td class="tb_inputtwo">
								<s:textfield name="c001PO.MA002" id="ma002" cssClass="{required:true,stringMinLength:4,stringMaxLength:25,remote:{type:'post',async:false,url:'register/c001!checkOnlyMa002',data:{ma002:function(){return $('#ma002').val();},ma001:function(){return $('#ma001').val();}}},messages:{remote:'企业名称已被使用'}}" maxLength="25"></s:textfield>
							</td>
							<th><span> * </span>组织机构代码</th>
							<td class="tb_inputtwo">
								<s:textfield name="c001PO.MA003" id="ma003" cssClass="{required:true,maxlength:10,alnum:true,remote:{type:'post',async:false,url:'register/c001!checkOnlyMa003',data:{ma003:function(){return $('#ma003').val();},ma001:function(){return $('#ma001').val();}}},messages:{remote:'组织机构代码已被使用'}}" maxLength="10"></s:textfield>
							</td>
						</tr>
						<tr>
							<th>联系电话</th>
							<td class="tb_inputtwo">
								<s:textfield name="c001PO.MA005" cssClass="{isTel:true,maxlength:20}" maxLength="20"></s:textfield>
							</td>
							<th><span> * </span>手机号</th>
							<td class="tb_inputtwo">
								<s:textfield name="c001PO.MA006" cssClass="{required:true,isMobile:true,maxlength:11,messages:{required:'请正确填写您的手机号码'}}" maxLength="11" />
							</td>
						</tr>
						<tr>
							<th><span> * </span>邮箱</th>
							<td class="tb_inputtwo">
								<s:textfield name="c001PO.MA007" id="ma007" cssClass="{required:true,email:true,maxlength:25,remote:{type:'post',async:false,url:'register/c001!checkOnlyMa007',data:{ma007:function(){return $('#ma007').val();},ma001:function(){return $('#ma001').val()}}},messages:{remote:'注册邮箱已被使用'}}" maxLength="25"></s:textfield>
							</td>
							<th><span> * </span>是否有效</th>
							<td class="tb_inputtwo">
								<s:radio name="c001PO.MA008" list="#{'10':'有效','00':'无效'}" value="10" />
							</td>
						</tr>
						<tr>
							<th><span> * </span>同时在线人数</th>
							<td class="tb_inputtwo">
								<s:textfield name="c001PO.MA009" cssClass="{required:true,digits:true,range:[0,999]}" value="1" maxLength="3"></s:textfield>
							</td>
							<th>安全生产管理机构</th>
							<td class="tb_inputtwo">
								<s:select list="#{'':'请选择'}" name="c001PO.MA004" id="ma004"></s:select>
							</td>
						</tr>
						<tr>
							<th>备注</th>
							<td colspan="3"><s:textarea name="c001PO.MA010" cols="45"
									rows="5" cssClass="{maxlength:180} textarea1" maxLength="180" /></td>
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