<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form  method="post" id="k001Add" >
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul><li>健康知识</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv">
    <div id="windowdivmain">
	<table border="0" cellspacing="0" cellpadding="0"
		class="windowdivmaintable">
		<tr>
			<th scope="col"><span>*</span>题目</th>
			<td class="tb_inputtwo" scope="col"><s:textfield
					name="k001PO.ma002"
					cssClass="required {maxlength:30,minlength:0} " /></td>
			<th scope="col"><span>*</span>关键字</th>
			<td class="tb_inputtwo" scope="col"><s:textfield
					name="k001PO.ma004" cssClass="required {maxlength:30,minlength:0}" /></td>
		</tr>
		<tr>
			<th scope="row"><span>* </span>内容</th>
			<td colspan="3"><s:textarea name="k001PO.ma003"
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
	       <li class="save"><input type="button" class="save" style="border:none;" id="saveBtn" onclick="saveK001()" /></li>
	      <li>( 说明：<span>*</span>号位必填项)</li>
	      </ul>
      </div>
    </div>
  </div>
</div>
</s:form>
