<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form  method="post" id="d002Add" >
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul><li>设备管理</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv">
    <div id="windowdivmain">
	<table border="0" cellspacing="0" cellpadding="0"
		class="windowdivmaintable">
		<tr>
			<th scope="col"><span>*</span>设备名称</th>
			<td class="tb_inputtwo" scope="col"><s:textfield
					name="d002PO.ma008"
					cssClass="required {maxlength:30,minlength:0} " /></td>
			<th scope="col"><span>*</span>设备标识</th>
			<td class="tb_inputtwo" scope="col"><s:textfield
					name="d002PO.ma004" cssClass="required {maxlength:30,minlength:0}" /></td>
		</tr>
		<tr>
			<th scope="col"><span>*</span>设备类型</th>
			<td class="tb_inputtwo" scope="col"><s:textfield
					name="d002PO.ma003"
					cssClass="required {maxlength:30,minlength:0} " /></td>
			<th scope="col"></th>
			<td class="tb_inputtwo" scope="col"></td>
		</tr>
		<tr>
			<th scope="row">备注</th>
			<td colspan="3"><s:textarea name="d002PO.ma006"
					cssClass="textareamodify required {maxlength:2000,minlength:0}" /></td>
		</tr>
	</table>
	</div>
    </div>
  </div>
  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
	      <ul>
	       <li class="cancel" style="margin-right:15px;"><input type="button" class="back" style="border:none;" id="backBtn" onclick="closeshowd()" /></li>
	       <li class="save"><input type="button" class="save" style="border:none;" id="saveBtn" onclick="saveD002()" /></li>
	      <li>( 说明：<span>*</span>号为必填项)</li>
	      </ul>
      </div>
    </div>
  </div>
</div>
</s:form>
