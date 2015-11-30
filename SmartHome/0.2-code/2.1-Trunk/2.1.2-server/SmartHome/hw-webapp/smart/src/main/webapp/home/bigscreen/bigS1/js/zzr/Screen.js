/* 设置室内数据 */
function getInData(basePath,SESSIONID,USERID,currentSensorId) {
	
    $.post(
            basePath+'hwmobile/smart/weather!doContentDetail',
        {
            SESSIONID : SESSIONID,//localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
            USERID : USERID//localStorage.getItem('USERID','')//'f3d46750-e022-4bf6-88d0-2893caed8b24',
        },
        function(data) {
            if(data.dataObject){
                //alert(data.dataObject[0].sensorList[0].air.temperature);
                //alert(data);
                for (var i = 0; i < data.dataObject[0].sensorList.length ; i++) {
                    if (currentSensorId != null && data.dataObject[0].sensorList[i].sensorId==currentSensorId){
                        if (data.dataObject[0].sensorList[i].air != null){

                        	
                        
                        	
                            /* 室内二氧化碳 赋值 */
                            if(data.dataObject[0].sensorList[i].air.co2<=800){
                                $('#in_co2').css({"border-color": "#10E600"});
                                $("#tip_co2").html("空气适宜，沁人心脾");
                            }
                            if(data.dataObject[0].sensorList[i].air.co2>800 && data.dataObject[0].sensorList[i].air.co2<=1200){
                                $('#in_co2').css({"border-color": "#CDE00E"});
                                $("#tip_co2").html("令人昏昏欲睡，请立即开启新风换气");
                            }
                            if(data.dataObject[0].sensorList[i].air.co2>1200 && data.dataObject[0].sensorList[i].air.co2<=2000){
                                $('#in_co2').css({"border-color": "#E66013"});
                                $("#tip_co2").html("身体开始感觉不适，请立即开启新风换气");
                            }
                            if(data.dataObject[0].sensorList[i].air.co2>2000){
                                $('#in_co2').css({"border-color": "#FF0100"});
                                $("#tip_co2").html("警惕二氧化碳中毒，请立即开启新风换气");
                            }
                            $('#in_co2').html(data.dataObject[0].sensorList[i].air.co2);


                            /* PM2.5 赋值 */
                            if(data.dataObject[0].sensorList[i].air.pm25<=50){
                                $('#in_pm25').css({"border-color": "#10E600"});
                                $("#tip_pm25").html("可打开空气净化器；");
                            }
                            if(data.dataObject[0].sensorList[i].air.pm25>50 && data.dataObject[0].sensorList[i].air.pm25<=100){
                                $('#in_pm25').css({"border-color": "#CDE00E"});
                                $("#tip_pm25").html("请立即打开空气净化器；");
                            }
                            if(data.dataObject[0].sensorList[i].air.pm25>100 && data.dataObject[0].sensorList[i].air.pm25<=200){
                                $('#in_pm25').css({"border-color": "#E66013"});
                                $("#tip_pm25").html("身体开始感觉不适，请立即打开空气净化器；");
                            }
                            if(data.dataObject[0].sensorList[i].air.pm25>200){
                                $('#in_pm25').css({"border-color": "#FF0100"});
                                $("#tip_pm25").html("对身体严重危害，请立即打开空气净化器；");
                            }
                            $('#in_pm25').html(data.dataObject[0].sensorList[i].air.pm25);
                            /* ./PM2.5 */


                            /* voc 赋值 */
                            if(data.dataObject[0].sensorList[i].air.voc<=10){
                                $('#in_voc').css({"border-color": "#10E600"});
                            }
                            if(data.dataObject[0].sensorList[i].air.voc>10 && data.dataObject[0].sensorList[i].air.voc<=20){
                                $('#in_voc').css({"border-color": "#CDE00E"});
                            }
                            if(data.dataObject[0].sensorList[i].air.voc>20 && data.dataObject[0].sensorList[i].air.voc<=40){
                                $('#in_voc').css({"border-color": "#E66013"});
                            }
                            if(data.dataObject[0].sensorList[i].air.voc>40){
                                $('#in_voc').css({"border-color": "#FF0100"});
                            }
                            $('#in_voc').html(data.dataObject[0].sensorList[i].air.voc);
                            /* ./voc */


                            /* 湿度 赋值 */
                            if(data.dataObject[0].sensorList[i].air.humidity<=35){
                                $('#in_hum').css({"border-color": "#E7E632"});
                            }
                            if(data.dataObject[0].sensorList[i].air.humidity>35 && data.dataObject[0].sensorList[i].air.humidity<=65){
                                $('#in_hum').css({"border-color": "#04E500"});
                            }

                            if(data.dataObject[0].sensorList[i].air.humidity>65){
                                $('#in_hum').css({"border-color": "#3AF4FF"});
                            }
                            $('#in_hum').html(data.dataObject[0].sensorList[i].air.humidity+"%");
                            /* ./湿度 */


                            /* 温度 赋值 */
                            if(data.dataObject[0].sensorList[i].air.temperature<=10){
                                $('#in_temp').css({"border-color": "#09098F"});
                            }
                            if(data.dataObject[0].sensorList[i].air.temperature>10 && data.dataObject[0].sensorList[i].air.temperature<=15){
                                $('#in_temp').css({"border-color": "#3030FF"});
                            }
                            if(data.dataObject[0].sensorList[i].air.temperature>15 && data.dataObject[0].sensorList[i].air.temperature<=22){
                                $('#in_temp').css({"border-color": "#25FED3"});
                            }
                            if(data.dataObject[0].sensorList[i].air.temperature>22 && data.dataObject[0].sensorList[i].air.temperature<=28){
                                $('#in_temp').css({"border-color": "#06E500"});
                            }
                            if(data.dataObject[0].sensorList[i].air.temperature>28 && data.dataObject[0].sensorList[i].air.temperature<=32){
                                $('#in_temp').css({"border-color": "#E65D13"});
                            }

                            if(data.dataObject[0].sensorList[i].air.temperature>32){
                                $('#in_temp').css({"border-color": "#E60000"});
                            }
                            $('#in_temp').html(data.dataObject[0].sensorList[i].air.temperature+"℃");
                            /* ./温度 */

                            /* 室外发布时间赋值 */
                            var createTime = data.dataObject[0].sensorList[i].air.createTime;
                            $('#in_time').html(createTime.substring(5,createTime.length-3));
                            /* ./室外发布时间赋值 */

                            
                            roundRect("can1",x-10,10,5,data.dataObject[0].sensorList[i].air.pm25);
                            
                            
                            //$('#airradio_voc').html(data.dataObject[0].sensorList[i].air.voc);

                            //set_air_quilty(data.dataObject[0].sensorList[i].air.pm25);
                        }

                    }

                }
            }

        });


    setTimeout(function(){getInData(basePath,SESSIONID,USERID,currentSensorId)},15*1000);
}

/* 设置室外数据 */
function getOutData(basePath,SESSIONID,USERID,currentSensorId) {

    $.post(
            basePath+'hwmobile/smart/weather!doContentDetail',
        {
            SESSIONID : SESSIONID,//localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
            USERID : USERID//localStorage.getItem('USERID','')//'f3d46750-e022-4bf6-88d0-2893caed8b24',
        },
        function(data) {
            if(data.dataObject){
                //alert(data.dataObject[0].sensorList[0].air.temperature);
                //alert(data);
                for (var i = 0; i < data.dataObject[0].sensorList.length ; i++) {
                    if (currentSensorId != null && data.dataObject[0].sensorList[i].sensorId==currentSensorId){
                        if (data.dataObject[0].sensorList[i].air != null){


                            /* 室内二氧化碳 赋值 */
                            if(data.dataObject[0].sensorList[i].air.co2<=800){
                                $('#out_co2').css({"border-color": "#10E600"});
                            }
                            if(data.dataObject[0].sensorList[i].air.co2>800 && data.dataObject[0].sensorList[i].air.co2<=1200){
                                $('#out_co2').css({"border-color": "#CDE00E"});
                            }
                            if(data.dataObject[0].sensorList[i].air.co2>1200 && data.dataObject[0].sensorList[i].air.co2<=2000){
                                $('#out_co2').css({"border-color": "#E66013"});
                            }
                            if(data.dataObject[0].sensorList[i].air.co2>2000){
                                $('#out_co2').css({"border-color": "#FF0100"});
                            }
                            $('#out_co2').html(data.dataObject[0].sensorList[i].air.co2);


                            /* PM2.5 赋值 */
                            if(data.dataObject[0].sensorList[i].air.pm25<=50){
                                $('#out_pm25').css({"border-color": "#10E600"});
                            }
                            if(data.dataObject[0].sensorList[i].air.pm25>50 && data.dataObject[0].sensorList[i].air.pm25<=100){
                                $('#out_pm25').css({"border-color": "#CDE00E"});
                            }
                            if(data.dataObject[0].sensorList[i].air.pm25>100 && data.dataObject[0].sensorList[i].air.pm25<=200){
                                $('#out_pm25').css({"border-color": "#E66013"});
                            }
                            if(data.dataObject[0].sensorList[i].air.pm25>200){
                                $('#out_pm25').css({"border-color": "#FF0100"});
                            }
                            $('#out_pm25').html(data.dataObject[0].sensorList[i].air.pm25);
                            /* ./PM2.5 */


                            /* voc 赋值 */
                            if(data.dataObject[0].sensorList[i].air.voc<=10){
                                $('#out_voc').css({"border-color": "#10E600"});
                            }
                            if(data.dataObject[0].sensorList[i].air.voc>10 && data.dataObject[0].sensorList[i].air.voc<=20){
                                $('#out_voc').css({"border-color": "#CDE00E"});
                            }
                            if(data.dataObject[0].sensorList[i].air.voc>20 && data.dataObject[0].sensorList[i].air.voc<=40){
                                $('#out_voc').css({"border-color": "#E66013"});
                            }
                            if(data.dataObject[0].sensorList[i].air.voc>40){
                                $('#out_voc').css({"border-color": "#FF0100"});
                            }
                            $('#out_voc').html(data.dataObject[0].sensorList[i].air.voc);
                            /* ./voc */


                            /* 湿度 赋值 */
                            if(data.dataObject[0].sensorList[i].air.humidity<=35){
                                $('#out_hum').css({"border-color": "#E7E632"});
                            }
                            if(data.dataObject[0].sensorList[i].air.humidity>35 && data.dataObject[0].sensorList[i].air.humidity<=65){
                                $('#out_hum').css({"border-color": "#04E500"});
                            }

                            if(data.dataObject[0].sensorList[i].air.humidity>65){
                                $('#out_hum').css({"border-color": "#3AF4FF"});
                            }
                            $('#out_hum').html(data.dataObject[0].sensorList[i].air.humidity+"%");
                            /* ./湿度 */


                            /* 温度 赋值 */
                            if(data.dataObject[0].sensorList[i].air.temperature<=10){
                                $('#out_temp').css({"border-color": "#09098F"});
                            }
                            if(data.dataObject[0].sensorList[i].air.temperature>10 && data.dataObject[0].sensorList[i].air.temperature<=15){
                                $('#out_temp').css({"border-color": "#3030FF"});
                            }
                            if(data.dataObject[0].sensorList[i].air.temperature>15 && data.dataObject[0].sensorList[i].air.temperature<=22){
                                $('#out_temp').css({"border-color": "#25FED3"});
                            }
                            if(data.dataObject[0].sensorList[i].air.temperature>22 && data.dataObject[0].sensorList[i].air.temperature<=28){
                                $('#out_temp').css({"border-color": "#06E500"});
                            }
                            if(data.dataObject[0].sensorList[i].air.temperature>28 && data.dataObject[0].sensorList[i].air.temperature<=32){
                                $('#out_temp').css({"border-color": "#E65D13"});
                            }
                            if(data.dataObject[0].sensorList[i].air.temperature>32){
                                $('#out_temp').css({"border-color": "#E60000"});
                            }
                            $('#out_temp').html(data.dataObject[0].sensorList[i].air.temperature+"℃");
                            /* ./温度 */

                            /* 室外发布时间赋值 */
                            var createTime = data.dataObject[0].sensorList[i].air.createTime;
                            $('#out_time').html(createTime.substring(5,createTime.length-3));
                            /* ./室外发布时间赋值 */

                            roundRect("can2",x-10,10,5,data.dataObject[0].sensorList[i].air.pm25);		
                            //$('#airradio_voc').html(data.dataObject[0].sensorList[i].air.voc);

                            //set_air_quilty(data.dataObject[0].sensorList[i].air.pm25);
                        }

                    }

                }
            }

        });


    setTimeout(function(){getOutData(basePath,SESSIONID,USERID,currentSensorId)},15*1000);
}



/* 获取中央气象数据 */

function getCentralWeather(cityISO) {
    $.ajax({
        url: 'http://wthrcdn.etouch.cn/WeatherApi?city=' + cityISO,
        type: 'post',
        dataType: 'xml',
        success: function (xml) {
            //alert(xml);
            $(xml).find("resp").each(function (i) {
                var cen_pm25 = $(this).children("environment").children("pm25").text();

                var cen_aqi = $(this).children("environment").children("aqi").text();
                var cen_quality = $(this).children("environment").children("quality").text();
                var cen_time = $(this).children("updatetime").text();

    

                var cen_temp = $(this).children("wendu").text();
                var cen_hum = $(this).children("shidu").text();
                var cen_high = $(this).children("forecast").children("weather").children("high").eq(0).text();
                var cen_low = $(this).children("forecast").children("weather").children("low").eq(0).text();

                cen_high = cen_high.substring(2, cen_high.length);
                cen_low = cen_low.substring(2, cen_low.length);

                var cen_type = $(this).children("forecast").children("weather").children("day").eq(0).children("type").text();
                var cen_windDirection = $(this).children("forecast").children("weather").children("day").eq(0).children("fengxiang").text();
                var cen_windForce = $(this).children("forecast").children("weather").children("day").eq(0).children("fengli").text();
                //alert(cityname);
                //document.getElementById("city").innerHTML = cityname;
                //document.getElementById("fengxiang").innerHTML = "哈哈哈";
                $("#cen_quality").html(cen_quality);


                $("#cen_windForce").html(cen_windForce);
                //$("#tianqi").html(temp+"℃");
                $("#cen_hum").html(cen_hum);
                $("#cen_pm25").html(cen_pm25);
                $("#cen_type").html(cen_type);
                $("#cen_aqi").html(cen_aqi);
                $("#cen_time").html(cen_time);
                $("#cen_windDirec").html(cen_windDirection);
                $("#cen_temp").html(cen_temp+"℃");

                /*	alert("城市："+cityname+"\n"+" 日期： "+date+"\n"+" 天气状况："+type+"\n"+" 当前温度："+temp+"\n"+" PM2.5："+pm25+"\n"+" 空气质量："+quality+"\n"+" 湿度："+humidty+"\n"+" 最高气温："+high+"\n"+" 最低气温："+low+"\n"+" PM10："+pm10+"\n");     */

            });
        }
    });
    
    setTimeout(function(){getCentralWeather(cityISO)},60*1000*5);
    
}



/* 获取小时 */
function getTimeDif(t){
    var date = new Date ();
    var yy=date.getFullYear();
    var mon=date.getMonth()+1;  //因为1月这个方法返回为0，所以加1
    var dd=date.getDate();

    var tim = date.getHours () - t;

    return yy+"-"+mon+"-"+dd+" "+tim+":00";
}

/*  图表初始化  */

function setChart(dataP,dataC,id){

    $("#"+id).html("");
    line = Morris.Line({
        element: id,
        resize: true,
        data: [
            {y: getTimeDif(6), item1: dataP[0]},
            {y: getTimeDif(5), item1: dataP[1]},
            {y: getTimeDif(4), item1: dataP[2]},
            {y: getTimeDif(3), item1: dataP[3]},
            {y: getTimeDif(2), item1: dataP[4]},
            {y: getTimeDif(1), item1: dataP[5]},
            {y: getTimeDif(0)},
            {y: getTimeDif(6), item2: dataC[0]},
            {y: getTimeDif(5), item2: dataC[1]},
            {y: getTimeDif(4), item2: dataC[2]},
            {y: getTimeDif(3), item2: dataC[3]},
            {y: getTimeDif(2), item2: dataC[4]},
            {y: getTimeDif(1), item2: dataC[5]},
            {y: getTimeDif(0)}
        ],

        xkey: 'y',
        ykeys: ['item1','item2'],
        labels: ['Item 1'],
        lineColors: ['#D6AED4','#8AF6DB'],
        lineWidth: 2,
        hideHover: 'always',
        gridTextColor: "#fff",
        gridStrokeWidth: 0.4,
        pointSize: 2,
        pointStrokeColors: ['#D6AED4','#8AF6DB'],
        gridLineColor: "#efefef",
        gridTextFamily: "Open Sans",
        gridTextSize: 10
    });
}
/*  空气电台历史数据获取  */
/* 异步请求数据 */
function getHisData(basePath,SESSIONID,USERID,currentSensorId,type,lastpar,id){
	
    var dataP_lastPar = [];
    var dataC_lastPar = [];
    $.post(	 basePath+'hwmobile/smart/weather!doListDetailHistory',
        {
            SESSIONID : SESSIONID,//localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
            USERID : USERID,//localStorage.getItem('USERID',''),//'f3d46750-e022-4bf6-88d0-2893caed8b24',
            SENSORID : currentSensorId,// 室内A1  设备编号
            TYPE: type,
            LASTPAR: lastpar

        }, function(data) {

            if(data.dataObject){

                if (data.dataObject[0].sensorList.length>0){
                    if(data.dataObject[0].sensorList[0].air.temperature != undefined){
                        for (var i = 0; i < data.dataObject[0].sensorList.length; i++)
                        {
                            dataP_lastPar[i]=(parseInt(data.dataObject[0].sensorList[i].air.pm25));
                            dataC_lastPar[i]=(parseInt(data.dataObject[0].sensorList[i].air.co2));
                        }
                        dataP_lastPar.length = lastpar;
                        dataC_lastPar.length = lastpar;
                      

                    }
                    setChart(dataP_lastPar,dataC_lastPar,id);
                }

            }

        });
    setTimeout(function(){getHisData(basePath,SESSIONID,USERID,currentSensorId,type,lastpar,id)},60*1000);

}
