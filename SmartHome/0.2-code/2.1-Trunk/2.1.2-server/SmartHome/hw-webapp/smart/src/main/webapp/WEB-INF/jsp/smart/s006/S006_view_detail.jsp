<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul><li>用户反馈</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv"><div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
							<th scope="col">用户名</th>
							<td class="tb_inputtwo" scope="col"><s:textarea name="s006po.ma009" style="height: 20px; width: 650px"
									disabled="true"/></td>
						</tr>
						<tr>
							<th scope="col">反馈内容</th>
							<td class="tb_inputtwo" scope="col"><s:textarea name="s006po.ma002" style="height: 100px; width: 650px"
									cssClass="textareamodify required {maxlength:256,minlength:0}"  disabled="true"/></td>
						</tr>
						<tr>
							<th scope="col">回复内容</th>
							<td class="tb_inputtwo" scope="col"><s:textarea name="s006po.ma005" style="height: 100px; width: 650px"
									cssClass="textareamodify required {maxlength:256,minlength:0}"  disabled="true"/></td>
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

