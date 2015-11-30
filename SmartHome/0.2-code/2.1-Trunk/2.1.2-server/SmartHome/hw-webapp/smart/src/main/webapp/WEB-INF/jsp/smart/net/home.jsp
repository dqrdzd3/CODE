<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE >
<HTML>
<HEAD>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<TITLE>空气电台</TITLE>
<s:action name="include(mainCSS,commonJS,jqueryJS,bootstrap)"
	executeResult="true" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, minimum-scale=1, maximum-scale=1">

<style>
body {
	margin: 0pt;
	padding: 0pt;
	background: rgb(136, 164, 183)
<%-- 		url(<%=basePath%>images/ui_login_background.jpg) repeat-x scroll --%>
		center top;
	font-family: Tahoma, Arial, Helvetica, sans-serif;
	font-size: 11px;
	font-color: rgb(0, 0, 255);
}
</style>


</HEAD>

<BODY >


	<input type="button" class="btn" value="生成" />
	<span id="keleyivisitorip" class="txt" ></span>
	<script type="text/javascript"
		src="http://tool.keleyi.com/ip/visitoriphost/"></script>


	<form>
		城市： <input type="text" name="city" id="city" /> <br /> 日期： <input
			type="text" name="date_y" id="date_y" /><br /> week： <input
			type="text" name="week" id="week" /><br /> 当天温度： <input type="text"
			name="temp1" id="temp1" /><br /> 当天天气状况： <input type="text"
			name="weather1" id="weather1" /><br /> 明日温度： <input type="text"
			name="temp2" id="temp2" /><br /> 明日天气状况： <input type="text"
			name="weather2" id="weather2" /><br /> 着装建议： <input type="text"
			name="index_d" id="index_d" style="width: 520px;" /><br />
	</form>
	<table align="center" name="content" id="content">

	</table>

	<div>
		温度：<input type="text" name="temp" id="temp" style="width: 520px;" /><br />

	</div>


	<table align="center">
		<tr>
			<td>&nbsp;</td>
			<td>温度</td>
			<td>湿度</td>
			<td>二氧化碳</td>
			<td>pm2.5</td>
			<td>VOC</td>
			<td>一氧化碳</td>
			<td>燃气</td>

		</tr>
		<s:iterator value="sensorDetailList">
			<tr>
				<td align="center">${air.sensorId}</td>
				<td align="center">${air.temperature}</td>
				<td align="center">${air.humidity}</td>
				<td align="center">${air.co2}</td>
				<td align="center">${air.pm25}</td>
				<td align="center">${air.voc}</td>
				<td align="center">${gas.voc}</td>
				<td align="center">${gas.voc}</td>

				<!-- 'P'是previous，即更新之前 -->
			</tr>
		</s:iterator>

	</table>

</BODY>
</HTML>
<script type="text/javascript">
	var _timerWeather;
	var _timerRealData;
	var _timerHistoryData;
	var _ip ='dd';
	$(function() {
		setWeather();
		setRealData();

		_timerWeather = window.setInterval(setWeather, 10000);
		_timerRealData = window.setInterval(setRealData, 10000);
		

	});

	function setWeather() {
	    _ip = $('#keleyivisitorip').text();

	    if (_ip.length == 0 ) return;

		$
				.post(
						'http://192.168.111.186:8080/smart/hwmobile/smart/weather!getWeatherContent?IP='+_ip,
						{

						}, function(data) {
							//alert(data.dataObject.weatherinfo.city);
							$("#city").val(data.dataObject.weatherinfo.city);
							$("#date_y")
									.val(data.dataObject.weatherinfo.date_y);
							$("#week").val(data.dataObject.weatherinfo.week);
							$("#temp1").val(data.dataObject.weatherinfo.temp1);
							$("#temp2").val(data.dataObject.weatherinfo.temp2);
							$("#weather1").val(
									data.dataObject.weatherinfo.weather1);
							$("#weather2").val(
									data.dataObject.weatherinfo.weather2);
							$("#index_d").val(
									data.dataObject.weatherinfo.index_d);
							//var myjson='';
							// eval_r('myjson=' + data + ';');
						});
	}

	function setRealData() {

		$
				.post(
						'http://192.168.111.186:8080/smart/hwmobile/smart/weather!doContentDetail',
						{

						},
						function(data) {
							alert();

							$("#temp")
									.val(
											data.dataObject[0].sensorList[0].air.createTime);
		
							var context = "	<tr><td>&nbsp;</td><td>温度</td><td>湿度</td><td>二氧化碳</td><td>pm2.5</td><td>VOC</td><td>一氧化碳</td><td>燃气</td></tr>";
							for (var i = 0; i < data.dataObject[0].sensorList.length - 1; i++) {

								context += '<tr><td>'
										+ data.dataObject[0].sensorList[i].air.sensorId
										+ '</td>';

								context += '<td>'
										+ data.dataObject[0].sensorList[i].air.temperature
										+ '</td>';

								context += '<td>'
										+ data.dataObject[0].sensorList[i].air.humidity
										+ '</td>';

								context += '<td>'
										+ data.dataObject[0].sensorList[i].air.co2
										+ '</td>';

								context += '<td>'
										+ data.dataObject[0].sensorList[i].air.pm25
										+ '</td>';

								context += '<td>'
										+ data.dataObject[0].sensorList[i].air.voc
										+ '</td>';

								context += '<td>'
										+ data.dataObject[0].sensorList[i].gas.co
										+ '</td>';

								context += '<td>'
										+ data.dataObject[0].sensorList[i].gas.ch4
										+ '</td></tr>';

							}

							$("#content").html(context);

						});
	}
</script>
