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
			<th scope="col">移动设备厂商</th>
			<td class="tb_inputtwo" scope="col">${d005PO.ma002}</td>
			<th scope="col">系统版本号</th>
			<td class="tb_inputtwo" scope="col">${d005PO.ma003}</td>
		</tr>
		<tr>
			<th scope="col">APP应用名称</th>
			<td class="tb_inputtwo" scope="col">${d005PO.ma004}</td>
			<th scope="col">设备型号</th>
			<td class="tb_inputtwo" scope="col">${d005PO.ma007}</td>
		</tr>
		<tr>
			<th scope="col">创建人或手机号</th>
			<td class="tb_inputtwo" scope="col" colspan="3">${d005PO.ma008 }</td>
		</tr>
		<tr>
			<th scope="col">日志内容</th>
			<td class="tb_inputtwo" scope="col" colspan="3">
				${d005PO.ma005}
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
	       <li class="cancel" style="margin-right:15px;"><input type="button" class="back" style="border:none;" id="backBtn" onclick="closeshowd()" /></li>
	      </ul>
      </div>
    </div>
  </div>
</div>

