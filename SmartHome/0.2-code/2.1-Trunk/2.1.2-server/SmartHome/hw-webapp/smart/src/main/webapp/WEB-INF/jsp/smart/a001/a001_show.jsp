<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
			<th scope="col">设备名称</th>
			<td class="tb_inputtwo" scope="col">${ d002PO.ma008}</td>
			<th scope="col">设备标识</th>
			<td class="tb_inputtwo" scope="col">${d002PO.ma004 }</td>
		</tr>
		<tr>
			<th scope="col">设备类型</th>
			<td class="tb_inputtwo" scope="col">${d002PO.ma003 }</td>
			<th scope="col"></th>
			<td class="tb_inputtwo" scope="col"></td>
		</tr>
		<tr>
			<th scope="row">备注</th>
			<td colspan="3"><textarea name="d002PO.ma006"
					>${d002PO.ma006 }</textarea></td>
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

