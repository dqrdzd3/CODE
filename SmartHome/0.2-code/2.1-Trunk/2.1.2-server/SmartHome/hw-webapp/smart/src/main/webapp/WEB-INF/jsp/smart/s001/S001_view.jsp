<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul><li>常见问题解决</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv"><div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
							<th scope="col">标题</th>
							<td class="tb_inputtwo" scope="col">${s001PO.ma002 }</td>
						</tr>
						<tr>
							<th scope="row">解决方法</th>
							<td colspan="3"><s:textarea name="s001PO.ma003"
									cssClass="textareamodify required {maxlength:2000,minlength:0}"  disabled="true"/></td>
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
	      </ul>
      </div>
    </div>
  </div>
</div>

