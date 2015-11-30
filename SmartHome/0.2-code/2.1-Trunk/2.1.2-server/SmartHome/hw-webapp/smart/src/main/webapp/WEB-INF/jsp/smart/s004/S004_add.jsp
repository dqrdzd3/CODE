<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form  method="post" id="s004Add" >
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul><li>讨论区管理</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv"><div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
							<th scope="col" ><span>*</span>标题:</th>
							<td class="tb_inputtwo" scope="col"><s:textfield
									name="s004po.ma002"
									cssClass="required {maxlength:256,minlength:0} ,isMobile" /></td>
						</tr>
						<tr>
							<th scope="row" ><span>* </span>内容:</th>
							<td colspan="3"><s:textarea name="s004po.ma003"
									cssClass="textareamodify required {maxlength:256,minlength:0}" /></td>
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
	       <li class="save"><input type="button" class="save" style="border:none;" id="saveBtn" onclick="saveS004()" /></li>
	      <li>( 说明：<span>*</span>号为必填项)</li>
	      </ul>
      </div>
    </div>
  </div>
</div>
</s:form>
