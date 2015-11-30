<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul><li>可燃气体实时数据</li>
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
			<td class="tb_inputtwo" scope="col">${sensorGasDetail.name}</td>
			<th scope="col">甲烷(LEL)</th>
			<td class="tb_inputtwo" scope="col">${sensorGasDetail.ch4 }</td>
		</tr>
		<tr>
			<th scope="col">一氧化碳(ppm)</th>
			<td class="tb_inputtwo" scope="col">${sensorGasDetail.co }</td>
			<th scope="col"></th>
			<td class="tb_inputtwo" scope="col"></td>
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

