<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul><li>空气质量实时数据</li>
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
			<td class="tb_inputtwo" scope="col">${ sensorAirDetail.name}</td>
			<th scope="col">温度(℃)</th>
			<td class="tb_inputtwo" scope="col">${sensorAirDetail.temperature }</td>
		</tr>
		<tr>
			<th scope="col">湿度(RH%)</th>
			<td class="tb_inputtwo" scope="col">${sensorAirDetail.humidity }</td>
			<th scope="col">二氧化碳(ppm)</th>
			<td class="tb_inputtwo" scope="col">${sensorAirDetail.co2 }</td>
		</tr>
		<tr>
			<th scope="col">PM2.5(ug/m3)</th>
			<td class="tb_inputtwo" scope="col">${sensorAirDetail.pm25 }</td>
			<th scope="col">甲醛(ppm)</th>
			<td class="tb_inputtwo" scope="col">${sensorAirDetail.ch2o }</td>
		</tr>
		<tr>
			<th scope="col">苯(ppm)</th>
			<td class="tb_inputtwo" scope="col">${sensorAirDetail.c6h6 }</td>
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

