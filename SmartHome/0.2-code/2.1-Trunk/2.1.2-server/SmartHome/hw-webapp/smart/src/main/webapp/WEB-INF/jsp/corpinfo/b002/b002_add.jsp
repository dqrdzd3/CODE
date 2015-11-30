<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action name="include(tabTip)" executeResult="true" />
<s:form action="b002" method="post" id="b002form">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>特种人员</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tbody>
							<s:hidden name="b002PO.MA001" id="MA001"></s:hidden>
							<s:hidden name="b002PO.MA003" id="MA003"></s:hidden>
							<s:hidden name="b002PO.MA019" value="0"></s:hidden>
							<s:hidden name="b002PO.MA020" value="0"></s:hidden>
							<tr>
								<th><span> *</span> 机构编号</th>
								<td class="tb_input"><s:textfield name="b002PO.MA002"
										id="MA002"
										cssClass='{required:true,maxlength:25,stringCode:true}'></s:textfield></td>
								<th><span> *</span> 机构名称</th>
								<td class="tb_input"><s:textfield name="b002PO.MA004"
										id="MA004" cssClass='{required:true,maxlength:25}'></s:textfield></td>
								<th><span> *</span> 资金投入(万元)</th>
								<td class="tb_input"><s:textfield name="b002PO.MA005" id="MA005"
										cssClass="{required:true,number:true,decimal:true,maxlength:17,min:0,messages:{decimal:'小数点后最多包含两位数字'}}" /></td>
							</tr>
							<tr>
								<th><span> *</span> 安全培训人数</th>
								<td class="tb_input"><s:textfield name="b002PO.MA007" id="MA007"
										cssClass='{required:true,digits:true,range:[0,9999],maxlength:4,messages:{digits:"请输入正整数"}} '></s:textfield>
								</td>
								<th><span> *</span>工伤保险人数</th>
								<td class="tb_input"><s:textfield name="b002PO.MA008" id="MA008"
										cssClass='{required:true,digits:true,range:[0,9999],maxlength:4,messages:{digits:"请输入正整数"}} '></s:textfield>
								</td>
								<th><span> *</span> 从业人数</th>
								<td class="tb_input"><s:textfield name="b002PO.MA006" id="MA006"
										cssClass='{required:true,digits:true,range:[0,9999],maxlength:4,messages:{digits:"请输入正整数"}} '></s:textfield>
								</td>
							</tr>
							<tr>
								<th>机构负责人姓名</th>
								<td><s:textfield name="b002PO.MA009"
										cssClass='{maxlength:10}'></s:textfield></td>
								<th>办公室电话</th>
								<td><s:textfield name="b002PO.MA010" id="MA010"
										cssClass='isPhone'></s:textfield></td>
								<th>机构负责人手机</th>
								<td><s:textfield name="b002PO.MA011" id="MA011"
										cssClass='isMobile'></s:textfield></td>
							</tr>
							<tr>
								<th>传真</th>
								<td><s:textfield name="b002PO.MA012"
										cssClass="{isTel:true,messages:{isTel:'请输入正确的传真号'}}"></s:textfield>
								</td>
								<th>机构联系人姓名</th>
								<td><s:textfield name="b002PO.MA013" id="MA013"
										cssClass='{maxlength:10}'></s:textfield></td>
								<th>办公电话</th>
								<td><s:textfield name="b002PO.MA014" id="MA014"
										cssClass='isPhone'></s:textfield></td>
							</tr>
							<tr>
								<th>机构联系人手机</th>
								<td><s:textfield name="b002PO.MA015" id="MA015"
										cssClass='isMobile'></s:textfield></td>
								<th>传真</th>
								<td><s:textfield name="b002PO.MA016"
										cssClass="{isTel:true,messages:{isTel:'请输入正确的传真号'}}"></s:textfield>
								</td>
								<th >安全管理人员数</th>
								<td id="aqglrynum"><s:property value="0" ></s:property></td>
							</tr>
							
							<!-- 动态结束 -->

							<tr>
								<th >备注</th>
								<td colspan="5"><s:textarea name="b002PO.MA024" id="MA024"
										ols="45" rows="5" maxlength="100"
										cssClass="{maxlength:100,minlength:0} textareamodify"></s:textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</DIV>
			</DIV>
		</div>
	</div>
	<div id="windowsbottom">
		<div id="windowsbottomleft">
			<div id="windowsbottomright">
				<ul>
					<li class="cancel"><s:submit value="" cssClass="back"
							method="doList"></s:submit></li>
					<li class="save" style="margin-right: 15px;"><s:submit
							value="" cssClass="submit" method="doSaveEdit"></s:submit></li>
					<li>( 说明：<span>*</span>号位必填项)
					</li>
				</ul>
			</div>
		</div>
	</div>
</s:form>