<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form name="addfrom" action="gov/c002" method="post" id="addfrom">
	<s:hidden name="c002PO.ma004" />
	<s:hidden name="c002PO.ma001" id="ma001"/>
	<div id="windows">
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
							<th><span> * </span>政府机构名称</th>
							<td class="tb_inputtwo">
								<s:textfield name="c002PO.ma003" id="c002Name"
									cssClass="{required:true,maxlength:20,remote:{type:'post',async:false,url:'gov/c002!checkOnlyName',data:{c002Name:function(){return $('#c002Name').val()},ma001:function(){return $('#ma001').val();}}},messages:{remote:'政府机构名称已被使用'}}" 
									maxLength="20"></s:textfield>
							</td>
							<th><span> * </span>组织机构代码</th>
							<td class="tb_inputtwo"><s:textfield name="c002PO.ma002"
									id="ma002"
									cssClass="{required:true,maxlength:10,alnum:true,remote:{type:'post',async:false,url:'gov/c002!checkOnlyCode',data:{ma002:function(){return $('#ma002').val()},ma001:function(){return $('#ma001').val();}}},messages:{remote:'组织机构代码已被使用'}}"
									maxLength="10"></s:textfield></td>
						</tr>
						<tr>
							<th>联系电话</th>
							<td class="tb_inputtwo"><s:textfield name="c002PO.ma005"
									cssClass="{isTel:true,maxlength:20}"
									maxLength="20" /></td>
							<th><span> * </span>手机号</th>
							<td class="tb_inputtwo"><s:textfield name="c002PO.ma006"
									cssClass="{required:true,isMobile:true,maxlength:11,messages:{required:'请正确填写您的手机号码'}}"
									maxLength="11" /></td>
						</tr>
						<tr>
							<th><span> * </span>邮箱</th>
							<td class="tb_inputtwo"><s:textfield name="c002PO.ma007" id="ma007"
									cssClass="{required:true,email:true,maxlength:25,remote:{type:'post',async:false,url:'gov/c002!checkOnlyMa007',data:{ma007:function(){return $('#ma007').val()},ma001:function(){return $('#ma001').val();}}},messages:{remote:'注册邮箱已被使用'}}"
									maxLength="25" /></td>
							<th><span> * </span>是否有效</th>
							<td class="tb_inputtwo"><s:radio name="c002PO.ma008"
									list="#{'10':'有效','00':'无效'}" value="10"></s:radio></td>
						</tr>
						<tr>
							<th><span> * </span>同时在线人数</th>
							<td class="tb_inputtwo"><s:textfield name="c002PO.ma014"
									cssClass="{required:true,digits:true,maxlength:4}" maxLength="4"
									id="c002PO.ma014" value="1">
								</s:textfield></td>
							<th>上级主管单位</th>
							<td class="tb_inputtwo"><input type="text" name="parentDepar" id="parentDepar" disabled="true"/></td>
						</tr>
						<tr>
							<th>备注</th>
							<td colspan="3"><s:textarea name="c002PO.ma009" cols="45"
									rows="5" cssClass="{maxlength:140} textarea1" maxLength="140" />
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
						<li class="save"><s:submit value="" cssClass="back"
								method="doList" style="border:none;">
							</s:submit></li>
						<li class="cancel" style="margin-right: 15px;"><s:submit
								value="" cssClass="submit" method="doSaveAdd"
								style="border:none;">
							</s:submit></li>

						<li>( 说明：<span>*</span>号位必填项)
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</s:form>