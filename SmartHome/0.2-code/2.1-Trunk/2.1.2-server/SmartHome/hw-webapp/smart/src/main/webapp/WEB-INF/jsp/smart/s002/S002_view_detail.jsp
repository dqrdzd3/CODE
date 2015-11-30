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
							<!-- <td ><img src="images/push/tongyong_01.png"  height="48" width="740"></td> -->
							<td ><img src="images/push/tongyong200.png"  height="31" width="740"></td>
						</tr>
						<tr>
							<td> <s:textarea id="nameTextArea" name="s002PO.ma006" maxlength="100" style="height:74px" disabled="true"/></td>
						</tr>
						<tr>
		                   <td><img src="images/push/tongyong_11.png"  height="31" width="740"></td>
						</tr>
						<tr>
							<td> <s:textarea id="contentTextArea" name="s002PO.ma005" maxlength="100" style="height:258px" disabled="true"/></td>
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
