<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form method="post" id="s006Reply">
	<s:hidden id="ma001" name="s001PO.ma001" />
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>回复反馈</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
							<th scope="col" >用户名：</th>
							<td class="tb_inputtwo" scope="col"><s:textfield name="s006po.ma009" disabled="true"/></td>
						</tr>
						<tr>
							<th scope="row">内容：</th>
							<td colspan="2"><s:textarea name="s006po.ma002" disabled="true"/></td>
						</tr>
						<tr>
							<th scope="row"><span>*</span>回复：</th>
							<s:hidden name="s006po.ma006" />
							<s:hidden name="s006po.ma001" />
							<s:hidden name="s006po.ma008" />
							<td colspan="2" ><s:textarea  name="s006po.ma005" style="height:150px" cssClass="textareamodify required {maxlength:256,minlength:0}" /></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id="windowsbottom">
			<div id="windowsbottomleft">
				<div id="windowsbottomright">
					<ul>
						<li class="cancel" style="margin-right: 15px;"><input
							type="button" class="back" style="border: none;" id="backBtn"
							onclick="closeshowd()" /></li>
						<li class="save"><input type="button" class="save"
							style="border: none;" id="saveBtn" onclick="doReply()" /></li>
						<li>( 说明：<span>*</span>号为必填项)
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</s:form>
