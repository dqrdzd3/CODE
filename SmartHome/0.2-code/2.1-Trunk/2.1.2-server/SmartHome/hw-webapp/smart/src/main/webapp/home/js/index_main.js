/*!
 * jQuery Transit - CSS3 transitions and transformations
 * (c) 2011-2014 Rico Sta. Cruz
 * MIT Licensed.
 *
 * http://ricostacruz.com/jquery.transit
 * http://github.com/rstacruz/jquery.transit
 */

/* jshint expr: true */
var basePath;
var _timerWeather;
var _timerRealData;
var _timerHistoryData;

var _currentSensorId;
var _currentGasId;
var deviceMap = new Map();
var cityISO;
var cityname;

var dataArray=new Array();    //存放历史数据
for(var i=0;i<7;i++){
	dataArray[i]=new Array(); 
}

var _currentHistoryType = 2;     //0:24小时    1:过去7天   2:过去30天   
var _historyTimeType = [1,2,2];
var _historyInterval = [24,7,30];

var dataT_24 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[]];

var dataT_7 = [[],[],[],[],[],[],[]];
var dataT_30  = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[],[],[],[],[],[],[]];
var dataH_24 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[]];
var dataH_7 = [[],[],[],[],[],[],[]];
var dataH_30 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[],[],[],[],[],[],[]];
var dataC_24 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[]];
var dataC_7 = [[],[],[],[],[],[],[]];
var dataC_30 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[],[],[],[],[],[],[]];
var dataP_7 = [[],[],[],[],[],[],[]];
var dataP_24 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[]];
var dataP_30 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[],[],[],[],[],[],[]];
var dataV_24 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[]];
var dataV_7 = [[],[],[],[],[],[],[]];
var dataV_30 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[],[],[],[],[],[],[]];

var dataCH_7 = [[],[],[],[],[],[],[]];
var dataCH_24 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[]];
var dataCH_30 =[[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[],[],[],[],[],[],[]];
var dataCO_24 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[]];
var dataCO_7 = [[],[],[],[],[],[],[]];
var dataCO_30 = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[,],[],[],[],[],[],[],[],[],[],[],[],[],[],[]];

//温度
var plot_temp_1;
var plot_temp_2;
var plot_temp_3;
//湿度
var plot_humidty_1; 
var plot_humidty_2; 
var plot_humidty_3; 
//pm2.5
var plot_pm25_1; 
var plot_pm25_2;
var plot_pm25_3; 
//co2
var plot_co2_1;
var plot_co2_2;
var plot_co2_3;
//voc
var plot_voc_1;
var plot_voc_2;
var plot_voc_3;

//ch4
var plot_ch4_1;
var plot_ch4_2;
var plot_ch4_3;

//co
var plot_co_1;
var plot_co_2;
var plot_co_3;

$(document).ready(function() {
	
	basePath = localStorage.getItem('basePath','');
	
	//setChart();
	$.post(
			basePath+'hwmobile/smart/s006!doListMessage',
			{
				SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
			USERID : localStorage.getItem('USERID','')//'f3d46750-e022-4bf6-88d0-2893caed8b24',
			

			}, function(data) {
			$("#loading_msg").hide();
			//data.dataObject 
			for(var i=0 ; i<data.dataObject.length ;i++){
				$("#message_leave").append('<div class="item dblue out"><div class="arrow"></div><div class="text">'+data.dataObject[i].ma002+'</div><div class="date">'+data.dataObject[i].ma003+'</div></div>');
				
						if(data.dataObject[i].ma005){
								$("#message_leave").append('<div class="item blue"><div class="arrow"></div><div class="text">'+data.dataObject[i].ma005+'</div><div class="date">'+data.dataObject[i].ma007+'</div></div>');
						}
					}
			
			
			$('#message_leave').scrollTop( $('#message_leave')[0].scrollHeight );
			
			
			
			}); 
	
	
	
	$("#msg_reload").click(function(){
		$.post(
				basePath+'hwmobile/smart/s006!doListMessage',
				{
					SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
				USERID : localStorage.getItem('USERID','')//'f3d46750-e022-4bf6-88d0-2893caed8b24',
				

				}, function(data) {
				//$("#loading_msg").hide();
				//data.dataObject 
					$("#message_leave").html("");
					$("#message_leave").append('<div class="item blue"><div class="arrow"></div><div class="text">空气电台反馈</div><div class="date"></div></div>');
				for(var i=0 ; i<data.dataObject.length ;i++){
					$("#message_leave").append('<div class="item dblue out"><div class="arrow"></div><div class="text">'+data.dataObject[i].ma002+'</div><div class="date">'+data.dataObject[i].ma003+'</div></div>');
					if(data.dataObject[i].ma005){
						$("#message_leave").append('<div class="item blue"><div class="arrow"></div><div class="text">'+data.dataObject[i].ma005+'</div><div class="date">'+data.dataObject[i].ma007+'</div></div>');
				}
						}
				
				$('#message_leave').scrollTop( $('#message_leave')[0].scrollHeight );
				
				}); 
	});
	

	
	$("#discuss_btn").click(function(){
		if($("#discuss_text").val()){
			
			$.post(
					basePath+'hwmobile/smart/s006!doAddMessage',
					{
						SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
						USERID : localStorage.getItem('USERID',''),//'f3d46750-e022-4bf6-88d0-2893caed8b24',
						MSG: $("#discuss_text").val()
		
					}, function(data) {
						//alert(data.code);
						$("#discuss_text").val('');
					}); 
			
			
			
			
		$("#message_leave").html("");
		$("#message_leave").append('<div class="item blue"><div class="arrow"></div><div class="text">空气电台反馈</div><div class="date"></div></div>');
		
		
		
		$.post(
				basePath+'hwmobile/smart/s006!doListMessage',
				{
				SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
				USERID : localStorage.getItem('USERID','')//'f3d46750-e022-4bf6-88d0-2893caed8b24',
				

				}, function(data) {
				//$("#loading_msg").hide();
				//data.dataObject 
				for(var i=0 ; i<data.dataObject.length ;i++){
					$("#message_leave").append('<div class="item dblue out"><div class="arrow"></div><div class="text">'+data.dataObject[i].ma002+'</div><div class="date">'+data.dataObject[i].ma003+'</div></div>');
					if(data.dataObject[i].ma005){
						$("#message_leave").append('<div class="item blue"><div class="arrow"></div><div class="text">'+data.dataObject[i].ma005+'</div><div class="date">'+data.dataObject[i].ma007+'</div></div>');
				}
						}
				
				$('#message_leave').scrollTop( $('#message_leave')[0].scrollHeight );
				}); 
		
		
		
		$.post(
				basePath+'hwmobile/smart/s006!doListMessage',
				{
					SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
				USERID : localStorage.getItem('USERID','')//'f3d46750-e022-4bf6-88d0-2893caed8b24',
				

				}, function(data) {
				//$("#loading_msg").hide();
				//data.dataObject 
					$("#message_leave").html("");
					$("#message_leave").append('<div class="item blue"><div class="arrow"></div><div class="text">空气电台反馈</div><div class="date"></div></div>');
				for(var i=0 ; i<data.dataObject.length ;i++){
					$("#message_leave").append('<div class="item dblue out"><div class="arrow"></div><div class="text">'+data.dataObject[i].ma002+'</div><div class="date">'+data.dataObject[i].ma003+'</div></div>');
					if(data.dataObject[i].ma005){
						$("#message_leave").append('<div class="item blue"><div class="arrow"></div><div class="text">'+data.dataObject[i].ma005+'</div><div class="date">'+data.dataObject[i].ma007+'</div></div>');
				}
						}
				
				$('#message_leave').scrollTop( $('#message_leave')[0].scrollHeight );
				
				}); 
		
		
		
		
		
		}
	});


	
	
	 var $items = $('#lanrenzhijia>ul>li');
     $items.mouseover(function() {
         $items.removeClass('selected');
         $(this).addClass('selected');

         var index = $items.index($(this));
         $('#lanrenzhijia>div').hide().eq(index).show();
     }).eq(1).mouseover();
	
	
//初始化设备信息
   $('#username').html(localStorage.getItem('username',''));
   $('#tel').html(localStorage.getItem('tel',''));
   $('#quit').click(function(){
	   localStorage.setItem("USERID","");
 	  localStorage.setItem("SESSIONID","");
 	  localStorage.setItem("username","");
 	  localStorage.setItem("tel","");
 	  window.location=basePath+"hwmobile/smart/weather!other";
   });
   //ip city
   cityISO = encodeURI(remote_ip_info.city);
   cityname = remote_ip_info.city;
 
   //保存设备和别名


	$.post(
			basePath+'hwmobile/smart/d002!doListEquip',
						{
							SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
							USERID : localStorage.getItem('USERID',''),//'f3d46750-e022-4bf6-88d0-2893caed8b24',
					

						}, function(data) {
							
					
							var dataObj=eval("("+data.data+")");
					
							for (var i = 0; i < dataObj.sensorList.length ; i++)
							{
							
								var device = dataObj.sensorList[i];
								if (device.air != null){
									if (!_currentSensorId) _currentSensorId = device.air.sensorId;
									  
								
									   $('#Tab_Air').show();
									   $('#Tab_Gas').hide();
							
									//deviceStr += " <li ><a href='#' onclick=setCurrentSensor('"+device.air.sensorId+"')>"+ device.air.name+" ["+device.air.sensorId+"]" +"</a></li>";
								
									deviceMap.put(device.air.sensorId,device.air.name);
									
								}
							 	 if (device.gas != null){
										if (!_currentGasId) _currentGasId = device.gas.sensorId;
									
								
									
								
							 		//r1Str += " <li><a href='#'>"+ device.gas.name+" ["+device.gas.sensorId+"]" +"</a></li>";
							 		deviceMap.put(device.gas.sensorId,device.gas.name);
							 	}
								if (device.ctrl != null){
									deviceMap.put(device.ctrl.deviceId,device.ctrl.name);
									//ctrStr += " <li><a href='#'>"+ device.ctrl.name+" ["+device.ctrl.deviceId+"]" +"</a></li>";
								} 
							}
							if (typeof(_currentSensorId)=="undefined"){
								  $('#Tab_Air').hide();
								  $('#Tab_Gas').show();
							}
							getScene();
							setHistoryData();
						}); 
		

function getScene(){
	
	$.post(
			basePath+'hwmobile/smart/scene!getAllScene',
			{
				SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
				USERID : localStorage.getItem('USERID',''),//'f3d46750-e022-4bf6-88d0-2893caed8b24',
		

			}, function(data) {
				//alert(data);
				$("#loading_nav").hide();
				var deviceStr = '';
			 
				var dataObj=eval("("+data.data+")");
				
				for (var i = 0; i < dataObj.length ; i++)
				{
					
					deviceStr += '<li class = "active">';
					
		
                    deviceStr += '<a href="#" class="blyellow">'+dataObj[i].ma004+'</a>';
                    deviceStr += ' <div class="open"></div>';
                    deviceStr += '<ul>';
    
                         var arStr = dataObj[i].ma002;
                   
                         if (arStr != undefined){
                        	 var names = new Array();
                             
                             names = arStr.split(";");
                     
                             for (var k=0;k<names.length;k++){
                            	 var ss = new Array();
                            	 	ss = names[k].split(',');
                            	 	if (deviceMap.get(ss[1])==undefined) continue;
                            		 deviceStr +=" <li><a href='#' onclick=setCurrentSensor('"+ss[1]+"')>"+ deviceMap.get(ss[1])+" ["+ss[1]+"]" +"</a></li>"; 
                            		deviceMap.remove(ss[1]);
                            
                             }
                        	 
                         }
                        
                        	var ctrStr = dataObj[i].ma003;
                        	if (ctrStr != undefined){
                        		var names = new Array();
                                
                                names = ctrStr.split(";");
                        
                                for (var k=0;k<names.length;k++){
                               	 var ss = new Array();
                               	 	ss = names[k].split(',');
   									if (deviceMap.get(ss[1])==undefined) continue;
                               		 deviceStr +=" <li><a href='#'>"+ deviceMap.get(ss[1])+" ["+ss[1]+"]" +"</a></li>"; 
                              		deviceMap.remove(ss[1]);
                               
                                }
                        	}
                       
                        
                         
                     deviceStr += '</ul></li>';
      				
					
					
				}
				var te = document.getElementById("navigation11").innerHTML;  // $('#navigation11').innerHTML;
				document.getElementById("navigation11").innerHTML = te + deviceStr;
				
				var deviceNo ='';
				var arrays = deviceMap.keySet();
				 for(var ii in arrays) {
				 	deviceNo += " <li class = 'active'><a href='#' onclick=setCurrentSensor('"+arrays[ii]+"')>"+ deviceMap.get(arrays[ii])+" ["+arrays[ii]+"]" +"</a></li>";
				}
	
				
				$('#devices_nop').html(deviceNo);
		
				 $(".sidebar .navigation > li > a, .sidebar .navigation > li > .open").click(function(){
				        if($(this).parent('li').find('ul').length > 0){
				            if($(this).parent('li').hasClass('active')){
				                $(this).parent('li').removeClass('active');            
				            }else{
				                $(this).parent('li').addClass('active');
				            }    
				            return false;
				        }
				    });
			
			});	
	
}
		
		
//初始化图片
setMyPic();

getPM25();
//初始化天气
setWeather();
setRealData();

_timerWeather = window.setInterval(setWeather, 10000);
_timerRealData = window.setInterval(setRealData, 10000);





//温度
plot_temp_1 = $.jqplot('h_temp_chart', [dataT_24], setChartOption("温度","#FF327A"));
plot_temp_2 = $.jqplot('w_temp_chart', [dataT_7], setChartOption("温度","#FF427F"));
plot_temp_3 = $.jqplot('m_temp_chart', [dataT_30], setChartOption("温度","#FF367F"));
//湿度
plot_humidty_1 = $.jqplot('h_humidty_chart', [dataH_24],setChartOption("湿度","#FFAB22"));
plot_humidty_2 = $.jqplot('w_humidty_chart', [dataH_7], setChartOption("湿度","#FFAB22"));
plot_humidty_3 = $.jqplot('m_humidty_chart', [dataH_30], setChartOption("湿度","#FFAC22"));
//pm2.5
plot_pm25_1 = $.jqplot('h_pm25_chart', [dataP_24], setChartOption("pm2.5","#00327A"));
plot_pm25_2 = $.jqplot('w_pm25_chart', [dataP_7], setChartOption("pm2.5","#00327A"));
plot_pm25_3 = $.jqplot('m_pm25_chart', [dataP_30], setChartOption("pm2.5","#00327A"));
//co2
plot_co2_1 = $.jqplot('h_co2_chart', [dataC_24], setChartOption("二氧化碳","#0F327A"));
plot_co2_2 = $.jqplot('w_co2_chart', [dataC_7], setChartOption("二氧化碳","#2F327A"));
plot_co2_3 = $.jqplot('m_co2_chart', [dataC_30], setChartOption("二氧化碳","#4F327A"));
//voc

plot_voc_1 = $.jqplot('h_voc_chart', [dataV_24],  setChartOption("VOC","#0F327A"));
plot_voc_2 = $.jqplot('w_voc_chart', [dataV_7],  setChartOption("VOC","#0F327A"));
plot_voc_3 = $.jqplot('m_voc_chart', [dataV_30], setChartOption("VOC","#0F327A"));

//ch4

plot_ch4_1 = $.jqplot('h_ch4_chart', [dataCH_24],  setChartOption("天然气","#0F327A"));
plot_ch4_2 = $.jqplot('w_ch4_chart', [dataCH_7],  setChartOption("天然气","#0F327A"));
plot_ch4_3 = $.jqplot('m_ch4_chart', [dataCH_30], setChartOption("天然气","#0F327A"));

//co

plot_co_1 = $.jqplot('h_co_chart', [dataCO_24],  setChartOption("一氧化碳","#0F327A"));
plot_co_2 = $.jqplot('w_co_chart', [dataCO_7],  setChartOption("一氧化碳","#0F327A"));
plot_co_3 = $.jqplot('m_co_chart', [dataCO_30], setChartOption("一氧化碳","#0F327A"));




	
	

});

function setChartOption(name,y){
	
	var x = [{label: name}];

	var color = new Array();
	color.push(y);
	var options_temp =      
	{     
			 seriesColors: color,
		      
			  series:x,    
			         
			  legend: {     
			        show: true,//设置是否出现分类名称框（即所有分类的名称出现在图的某个位置）     
			        location: 'ne',     // 分类名称框出现位置, nw, n, ne, e, se, s, sw, w.     
			        xoffset: 12,        // 分类名称框距图表区域上边框的距离（单位px）     
			        yoffset: 12,        // 分类名称框距图表区域左边框的距离(单位px)     
			        background:'' ,     //分类名称框距图表区域背景色     
			        textColor:'' ,  //分类名称框距图表区域内字体颜色  
					
					
			       // ..其他关于样式设计参考官方文档     
			    }, 
			    axesDefaults: {
                  tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                  tickOptions: {
                      angle: 10,  //倾斜角度 
                      fontSize: '10pt'
                  }
              },
		      axes:{
		        xaxis:{
		          renderer:$.jqplot.DateAxisRenderer,
		          tickOptions:{
		            formatString:'%#m - %#d'
		          } 
		        },
		        yaxis:{
		          tickOptions:{
		            formatString:'%.2f',
		            pad:5
		            }
		        }
		      },
		      highlighter: {
		        show: true,
		        sizeAdjust: 7.5
		      },
		      cursor: {
		        show: false
		      }

	} 
	
	return options_temp;
	
}

function set24_Option(name,y,last_hour){
	
	var x = [{label: name}];
	//var tick2 = ['-6', '-5', '-4', '-3' ,'-2', '-1' ,'0','1', '2'];
	var tick2 = new Array();
	for(var i=0; i< 24; i++){
		var index = i+last_hour; 
		tick2.push(index.toString());
	}
	var color = new Array();
	color.push(y);
	var options_temp =      
	{     
			 seriesColors: color,
		      
			  series:x,    
			         
			  legend: {     
			        show: true,//设置是否出现分类名称框（即所有分类的名称出现在图的某个位置）     
			        location: 'ne',     // 分类名称框出现位置, nw, n, ne, e, se, s, sw, w.     
			        xoffset: 12,        // 分类名称框距图表区域上边框的距离（单位px）     
			        yoffset: 12,        // 分类名称框距图表区域左边框的距离(单位px)     
			        background:'' ,     //分类名称框距图表区域背景色     
			        textColor:'' ,  //分类名称框距图表区域内字体颜色  
					
					
			       // ..其他关于样式设计参考官方文档     
			    }, 
			    axesDefaults: {
                    tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                    tickOptions: {
                        angle: 10,  //��б�Ƕ� 
                        fontSize: '10pt'
                    }
                },
		      axes:{
        xaxis:{
          //renderer:$.jqplot.DateAxisRenderer,
		  renderer: $.jqplot.CategoryAxisRenderer,
		  tickInterval: '1hour',
          ticks: tick2,
                        tickOptions: {

                            fontSize: '10pt',
							formatString:'%b&nbsp;%#d'
         },
		  mark: 'cross'
        },
        yaxis:{
          tickOptions:{
            formatString:'%.2f'
            }
        }
      },
		      highlighter: {
		        show: true,
		        sizeAdjust: 7.5
		      },
		      cursor: {
		        show: false
		      }

	} 
	
	return options_temp;
	
}



function setWeather() {
	//var _ip = $('#keleyivisitorip').text();
	$("#city").html(cityname);
	if (cityname.length == 0) return;
	$
			.post(
					basePath+'hwmobile/smart/weather!getWeatherContent',
					{
						city:cityname,
					}, function(data) {

						
						
						$("#outside_temp").html(data.dataObject.weatherinfo.temp1);
						$("#outside_hum").html(data.dataObject.weatherinfo.temp2);
						
						/* $("#date_y")
								.val(data.dataObject.weatherinfo.date_y);
						$("#week").val(data.dataObject.weatherinfo.week);
						$("#temp1").val(data.dataObject.weatherinfo.temp1);
						$("#temp2").val(data.dataObject.weatherinfo.temp2);
						$("#weather1").val(
								data.dataObject.weatherinfo.weather1);
						$("#weather2").val(
								data.dataObject.weatherinfo.weather2);
						$("#index_d").val(
								data.dataObject.weatherinfo.index_d); */
						//var myjson='';
						// eval_r('myjson=' + data + ';');
						
						if(data.dataObject.weatherinfo.city != null){
						//_timerWeather.clearInterval;
						clearInterval(_timerWeather);
						//alert("yes");
						}
					});
	
	
	
}
//获得实时数据
function setRealData() {

	
	
	$.post(
			basePath+'hwmobile/smart/weather!doContentDetail',
					{
						SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
						USERID : localStorage.getItem('USERID',''),//'f3d46750-e022-4bf6-88d0-2893caed8b24',
					},
					function(data) {
						$("#loading_real").hide();
						//alert(data.dataObject[0].sensorList[2].air.temperature);
					 	//alert(data);
							  for (var i = 0; i < data.dataObject[0].sensorList.length ; i++) {
									if (_currentSensorId != null && data.dataObject[0].sensorList[i].sensorId==_currentSensorId){
										if (data.dataObject[0].sensorList[i].air != null){
											$('#airradio_temp').html(data.dataObject[0].sensorList[i].air.temperature);
											$('#airradio_hum').html(data.dataObject[0].sensorList[i].air.humidity);
			
											
											  $('#airradio_co2').html(data.dataObject[0].sensorList[i].air.co2);
			
											  $('#airradio_pm25').html(data.dataObject[0].sensorList[i].air.pm25);
			
											$('#airradio_voc').html(data.dataObject[0].sensorList[i].air.voc);
											
											set_air_quilty(data.dataObject[0].sensorList[i].air.pm25);
										}
										
									}
									if (_currentGasId != null && data.dataObject[0].sensorList[i].sensorId==_currentGasId){
									
										if (data.dataObject[0].sensorList[i].gas != null){
											$('#airradio_ch4').html(data.dataObject[0].sensorList[i].gas.ch4);
											$('#airradio_co').html(data.dataObject[0].sensorList[i].gas.co);
										}
											
									}
							  }
							
						
						
						//$("#temp").val(data.dataObject[0].sensorList[0].air.createTime);
/* 	<tr>
                                        <td width="55" class="bl_blue"><span class="label label-info">ȼ��</span></td>
                                        <td width="50">#AA-325 <span class="mark">23/02/2013</span></td>
                                        <td><span class="cblue">...</span> <span class="mark">Added by Dmitry Ivaniuk</span></td>                                        
                                    </tr>
                                               
                                    <tr>
                                        <td class="bl_green"><span class="label label-success">�����̨</span></td>
                                        <td>#AC-857 <span class="mark">21/02/2013</span></td>
                                        <td><a href="#" class="cgreen">Buy on themeforest this great template...</a> <span class="mark">Added by Dmitry Ivaniuk</span><span >Added by Dmitry Ivaniuk</span></td>                                        
                                    </tr> */
							  var deviceDataAll = "" ;
							    for (var i = 0; i < data.dataObject[0].sensorList.length ; i++) {

							    	if (data.dataObject[0].sensorList[i].air.temperature != undefined){
							    		deviceDataAll += "<tr>"; 
							    		deviceDataAll += '<td class="bl_green"><span class="label label-success">空气电台['+data.dataObject[0].sensorList[i].sensorId+']</span></td>';
							    		deviceDataAll += '<td width="50">'+data.dataObject[0].sensorList[i].air.temperature+'<br><span>'+data.dataObject[0].sensorList[i].air.humidity+'</span><br><span>'+data.dataObject[0].sensorList[i].air.co2+'</span><br><span>'+data.dataObject[0].sensorList[i].air.pm25+'</span><br><span>'+data.dataObject[0].sensorList[i].air.voc+'</span></td>';
							    		deviceDataAll += '<td><span class="cblue">温度</span> <br><span class="cblue">湿度</span><br><span class="cblue">二氧化碳</span><br><span class="cblue">PM2.5</span><br><span class="cblue">VOC</span></td> ';
							    		deviceDataAll += "</tr>"; 
										
									}
									if (data.dataObject[0].sensorList[i].gas.ch4 != undefined){
				
										deviceDataAll += "<tr>"; 
							    		deviceDataAll += '<td class="bl_blue"><span class="label label-info">燃气['+data.dataObject[0].sensorList[i].sensorId+']</span></td>';
							    		deviceDataAll += '<td width="50">'+data.dataObject[0].sensorList[i].gas.ch4+'<br><span>'+data.dataObject[0].sensorList[i].gas.co+'</span></td>';
							    		deviceDataAll += '<td><span class="cblue">天然气</span> <br><span class="cblue">一氧化碳</span></td> ';
							    		deviceDataAll += "</tr>"; 
									}
									if (data.dataObject[0].sensorList[i].ctrl.deviceType != undefined){
										
										for(var key in data.dataObject[0].sensorList[i].ctrl.ctrlContent){
											
											/* 	alert(data.dataObject[0].sensorList[i].ctrl.ctrlContent[key].switchState); */
											deviceDataAll += "<tr>"; 
								    		deviceDataAll += '<td class="bl_red"><span class="label label-important">控制设备['+data.dataObject[0].sensorList[i].sensorId+']</span></td>';
								    		deviceDataAll += '<td width="50">'+getCtrlType(data.dataObject[0].sensorList[i].ctrl.ctrlContent[key].switchType)+'<br><span>'+getCtrlState(data.dataObject[0].sensorList[i].ctrl.ctrlContent[key].switchState)+'</span></td>';
								    		deviceDataAll += '<td><span class="cblue">类型</span> <br><span class="cblue">状态</span></td> ';
								    		deviceDataAll += "</tr>"; 
										}
										
									
									}
								}

								$("#device_all").html(deviceDataAll);

							});

		}
//获得历史数据
function setHistoryData() {
	//var _historyTimeType = [1,2,2];
//var _historyInterval = [24,7,30];
	//alert(dataT_30);	

 $.post(
		 basePath+'hwmobile/smart/weather!doListDetailHistory',
					{
						SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
						USERID : localStorage.getItem('USERID',''),//'f3d46750-e022-4bf6-88d0-2893caed8b24',
						SENSORID : _currentSensorId,
						TYPE: _historyTimeType[_currentHistoryType],
						LASTPAR: _historyInterval[_currentHistoryType],

					}, function(data) {
						$("#loading_history").hide();
						if(_currentSensorId.indexOf("5") == 0){
							$(".deviceName").html("[" + _currentSensorId + "]");
							$("#real_air_name").html(_currentSensorId);
							$("#real_gas_name").html();
							
						}else if(_currentSensorId.indexOf("1") == 0){
							$(".deviceName").html("[" + _currentSensorId + "]");
							$("#real_gas_name").html(_currentSensorId);
							$("#real_air_name").html();
							}
							
						
					  
								if (_currentHistoryType==0){
								
									
									if (data.dataObject[0].sensorList.length>0){
										if(data.dataObject[0].sensorList[0].air.temperature != undefined){
											for (var i = 0; i < data.dataObject[0].sensorList.length; i++)
												{
												
													dataT_24[i][0]=getTimeDif(24-i);
													dataH_24[i][0]=getTimeDif(24-i);
													dataP_24[i][0]=getTimeDif(24-i);
													dataC_24[i][0]=getTimeDif(24-i);
													dataV_24[i][0]=getTimeDif(24-i);
										
													
													dataT_24[i][1]=(parseInt(data.dataObject[0].sensorList[i].air.temperature));
													dataH_24[i][1]=(parseInt(data.dataObject[0].sensorList[i].air.humidity));
													dataP_24[i][1]=(parseInt(data.dataObject[0].sensorList[i].air.pm25));
													dataC_24[i][1]=(parseInt(data.dataObject[0].sensorList[i].air.co2));
													dataV_24[i][1]=(parseInt(data.dataObject[0].sensorList[i].air.voc));
												
												}
								/* 			dataT_24[0] = dataArray[0];
											dataH_24[0] = dataArray[1];
											dataP_24[0] = dataArray[2];
											dataC_24[0] = dataArray[3];
											dataV_24[0] = dataArray[4]; */
											
											 dataT_24[0].length = 24;
												dataH_24[0].length = 24;
												dataP_24[0].length = 24;
												dataC_24[0].length = 24;
												dataV_24[0].length = 24;
											
											
										}
										if(data.dataObject[0].sensorList[0].gas.ch4 != undefined){
											
											for (var i = 0; i < data.dataObject[0].sensorList.length ; i++)
											{
												dataCH_24[i][1]=(getTimeDif(24-i));
												dataCO_24[i][1]=(getTimeDif(24-i));
												dataCH_24[i][1]=(parseInt(data.dataObject[0].sensorList[i].gas.ch4));
												dataCO_24[i][1]=(parseInt(data.dataObject[0].sensorList[i].gas.co));
											
											}
											
									
											dataCH_24[0].length = 24;
											dataCO_24[0].length = 24;
											
										}
										setChart('24');
								}
									
									
									
								
									
		 						}
								if (_currentHistoryType==1){
									
									
									if (data.dataObject[0].sensorList.length>0){
										if(data.dataObject[0].sensorList[0].air.temperature != undefined){
											for (var i = 0; i < data.dataObject[0].sensorList.length; i++)
												{
												
												/* dataArray[0][i]=parseInt(data.dataObject[0].sensorList[i].air.temperature);
												dataArray[1][i]=parseInt(data.dataObject[0].sensorList[i].air.humidity);
												dataArray[2][i]=parseInt(data.dataObject[0].sensorList[i].air.pm25);
												dataArray[3][i]=parseInt(data.dataObject[0].sensorList[i].air.co2);
												dataArray[4][i]=parseInt(data.dataObject[0].sensorList[i].air.voc); */
												
												dataT_7[i][0] = getDateDif(7-i);
												dataH_7[i][0] = getDateDif(7-i);
												dataP_7[i][0] = getDateDif(7-i);
												dataC_7[i][0] = getDateDif(7-i);
												dataV_7[i][0] = getDateDif(7-i);
									
												
												dataT_7[i][1] = parseInt(data.dataObject[0].sensorList[i].air.temperature);
												dataH_7[i][1] = parseInt(data.dataObject[0].sensorList[i].air.humidity);
												dataP_7[i][1] = parseInt(data.dataObject[0].sensorList[i].air.pm25);
												dataC_7[i][1] = parseInt(data.dataObject[0].sensorList[i].air.co2);
												dataV_7[i][1] = parseInt(data.dataObject[0].sensorList[i].air.voc);
												
												
												
												
												}
											//alert(dataT_7); 
											//dataT_7[0] = dataArray[0];
											/* dataH_7[0] = dataArray[1];
											dataP_7[0] = dataArray[2];
											dataC_7[0] = dataArray[3];
											dataV_7[0] = dataArray[4]; */
											
											dataT_7[0].length = 7;
											dataH_7[0].length = 7;
											dataP_7[0].length = 7;
											dataC_7[0].length = 7;
											dataV_7[0].length = 7;
											
										}
										if(data.dataObject[0].sensorList[0].gas.ch4 != undefined){
											
											for (var i = 0; i < data.dataObject[0].sensorList.length ; i++)
											{
												/* dataArray[5][i]=parseInt(data.dataObject[0].sensorList[i].gas.ch4);
												dataArray[6][i]=parseInt(data.dataObject[0].sensorList[i].gas.co); */
												dataCH_7[i][0] = getDateDif(7-i);
												dataCO_7[i][0] = getDateDif(7-i);
												dataCH_7[i][1] = parseInt(data.dataObject[0].sensorList[i].gas.ch4);
												dataCO_7[i][1] = parseInt(data.dataObject[0].sensorList[i].gas.co);
												
											}
					/* 						dataCH_7[0] = dataArray[5];
											dataCO_7[0] = dataArray[6]; */
											
											dataCH_7[0].length = 7;
											dataCO_7[0].length = 7;
											
										}
										setChart('7');
								}
									
							
								
									
		 						}
								
								if (_currentHistoryType==2){
				
									
									if (data.dataObject[0].sensorList.length>0){
										if(data.dataObject[0].sensorList[0].air.temperature != undefined){
											for (var i = 0; i < data.dataObject[0].sensorList.length; i++)
												{
												
								/* 				dataArray[0][i]=parseInt(data.dataObject[0].sensorList[i].air.temperature);
												dataArray[1][i]=parseInt(data.dataObject[0].sensorList[i].air.humidity);
												dataArray[2][i]=parseInt(data.dataObject[0].sensorList[i].air.pm25);
												dataArray[3][i]=parseInt(data.dataObject[0].sensorList[i].air.co2);
												dataArray[4][i]=parseInt(data.dataObject[0].sensorList[i].air.voc);
												*/
												dataT_30[i][0] = getDateDif(30-i);
												dataH_30[i][0] = getDateDif(30-i);
												dataP_30[i][0] = getDateDif(30-i);
												dataC_30[i][0] = getDateDif(30-i);
												dataV_30[i][0] = getDateDif(30-i);
									
												
												dataT_30[i][1] =  parseInt(data.dataObject[0].sensorList[i].air.temperature);
												dataH_30[i][1] = parseInt(data.dataObject[0].sensorList[i].air.humidity);
												dataP_30[i][1] = parseInt(data.dataObject[0].sensorList[i].air.pm25);
												dataC_30[i][1] = parseInt(data.dataObject[0].sensorList[i].air.co2);
												dataV_30[i][1] = parseInt(data.dataObject[0].sensorList[i].air.voc);
												
												}
								/* 			dataT_30[0] = dataArray[0];
											dataH_30[0] = dataArray[1];
											dataP_30[0] = dataArray[2];
											dataC_30[0] = dataArray[3];
											dataV_30[0] = dataArray[4]; */
											
											dataT_30[0].length = 30;
											dataH_30[0].length = 30;
											dataP_30[0].length = 30;
											dataC_30[0].length = 30;
											dataV_30[0].length = 30;
											
											
										}
										if(data.dataObject[0].sensorList[0].gas.ch4 != undefined){
											
											for (var i = 0; i < data.dataObject[0].sensorList.length ; i++)
											{
												dataCH_30[i][0] = getDateDif(30-i);
												dataCO_30[i][0] = getDateDif(30-i);
												dataCH_30[i][1] = parseInt(data.dataObject[0].sensorList[i].gas.ch4);
												dataCO_30[i][1] = parseInt(data.dataObject[0].sensorList[i].gas.co);
											
											}
									
											
											dataCH_30[0].length = 30;
											dataCO_30[0].length = 30;
											
										}
										setChart('30');
								}
								
									
									
									
		 						}
						
						
				
						//setChart();
						//$("#bton").click();
					}); 
	
	
}
function setChart(historyType){
/* 	var dataArray = new Array();
	for(var i = 0;i<7;i++)
	dataArray[i] = new Array();

	dataArray[0].push("23-May-08");
	dataArray[0].push(dataT_7[0][i]);
	dataArray[1].push("24-May-08");
	dataArray[1].push(566.5);
	dataArray[2].push("25-May-08");
	dataArray[2].push(578.55);
	dataArray[3].push("26-May-08");
	dataArray[3].push(566.5);
	dataArray[4].push("27-May-08");
	dataArray[4].push(234.55);
	dataArray[5].push("28-May-08");
	dataArray[5].push(56.5);
	dataArray[6].push("29-May-08");
	dataArray[6].push(878.55); */

	if (historyType == '24'){
		 plot_temp_1 = $.jqplot('h_temp_chart', [dataT_24], set24_Option("温度","#FF327A",parseInt(dataT_24[0][0])));
		  plot_humidty_1 = $.jqplot('h_humidty_chart', [dataH_24],set24_Option("湿度","#30CC00",parseInt(dataH_24[0][0])));
		   plot_pm25_1 = $.jqplot('h_pm25_chart', [dataP_24], set24_Option("PM2.5","#00327A",parseInt(dataP_24[0][0])));
	    plot_co2_1 = $.jqplot('h_co2_chart', [dataC_24], set24_Option("二氧化碳","#0F327A",parseInt(dataC_24[0][0])));
	     plot_voc_1 = $.jqplot('h_voc_chart', [dataV_24],  set24_Option("VOC","#0F327A",parseInt(dataV_24[0][0])));
	      plot_ch4_1 = $.jqplot('h_ch4_chart', [dataCH_24],  set24_Option("天然气","#0F327A",parseInt(dataCH_24[0][0])));
	       plot_co_1 = $.jqplot('h_co_chart', [dataCO_24],  set24_Option("一氧化碳","#0F327A",parseInt(dataCO_24[0][0])));
	        plot_temp_1.replot();
		plot_humidty_1.replot();
		plot_pm25_1.replot();
		plot_co2_1.replot();
		plot_voc_1.replot();
		plot_ch4_1.replot();


		plot_co_1.replot();


}


if (historyType == '7'){

	 plot_temp_2 = $.jqplot('w_temp_chart', [dataT_7], setChartOption("温度","#FF427F"));
	  plot_humidty_2 = $.jqplot('w_humidty_chart', [dataH_7], setChartOption("湿度","#FFAB22"));
	   plot_pm25_2 = $.jqplot('w_pm25_chart', [dataP_7], setChartOption("PM2.5","#00327A"));
	    plot_co2_2 = $.jqplot('w_co2_chart', [dataC_7], setChartOption("二氧化碳","#2F327A"));
	     plot_voc_2 = $.jqplot('w_voc_chart', [dataV_7],  setChartOption("VOC","#0F327A"));
	     	 plot_ch4_2 = $.jqplot('w_ch4_chart', [dataCH_7],  setChartOption("天然气","#0F327A"));
		  plot_co_2 = $.jqplot('w_co_chart', [dataCO_7],  setChartOption("一氧化碳","#0F327A"));
		  plot_temp_2.replot();
		  plot_humidty_2.replot();
		  plot_pm25_2.replot();
		  plot_co2_2.replot();
		  plot_voc_2.replot();

		plot_ch4_2.replot();
	
		plot_co_2.replot();

}

if (historyType == '30'){
//alert(dataT_30);
plot_temp_3 = $.jqplot('m_temp_chart', [dataT_30], setChartOption("温度","#FF367F"));
 plot_humidty_3 = $.jqplot('m_humidty_chart', [dataH_30], setChartOption("湿度","#FFAC22"));
  plot_pm25_3 = $.jqplot('m_pm25_chart', [dataP_30], setChartOption("PM2.5","#00327A"));
   plot_co2_3 = $.jqplot('m_co2_chart', [dataC_30], setChartOption("二氧化碳","#4F327A"));
    plot_voc_3 = $.jqplot('m_voc_chart', [dataV_30], setChartOption("VOC","#0F327A"));
     plot_ch4_3 = $.jqplot('m_ch4_chart', [dataCH_30], setChartOption("天然气","#0F327A"));
      plot_co_3 = $.jqplot('m_co_chart', [dataCO_30], setChartOption("一氧化碳","#0F327A"));
      plot_temp_3.replot();
      plot_humidty_3.replot();
      plot_pm25_3.replot();
      plot_co2_3.replot();
      	plot_voc_3.replot(); 

plot_ch4_3.replot();


plot_co_3.replot();
}

	

 

	
	
}
//获得控制设备类型和状态״̬
function getCtrlType(_type){
	if (_type == '00') return '无连接';
	if (_type == '01') return '灯';
	if (_type == '02') return '风扇';
	if (_type == '03') return '加湿器';
	if (_type == '04') return '空气净化器';
	if (_type == '05') return '空调';
}
function getCtrlState(_state){
	if (_state == '00') return '关';
	if (_state == '01') return '开';
}

//获得系统前n天的日期
function getDateDif(d){


	var   today=new   Date();     
       var   yesterday_milliseconds=today.getTime()-1000*60*60*24*d;     
       var   yesterday=new   Date();     
       yesterday.setTime(yesterday_milliseconds);     
         
       var strYear=yesterday.getFullYear();  
       var strDay=yesterday.getDate();  
       var strMonth=yesterday.getMonth()+1;  
      
       var strYesterday=strMonth+"-"+strDay +"-" + strYear; 
       return strYesterday;
	
}
//获得前24小时
function getTimeDif(t){
    var date = new Date ();
    var tim = date.getHours () - t;
    
    return tim;
}
//设置实时数据的air的空气整体状况
function set_air_quilty(temp){
	var pmValue = temp;
	var pm25State;
	if (pmValue >= 0 && pmValue <= 50) {
		pm25State = "优";
	}
	if (pmValue >= 51 && pmValue <= 100) {
		pm25State = "良好";
	}
	if (pmValue >= 101 && pmValue <= 150) {
		pm25State = "轻度";
	}
	if (pmValue >= 151 && pmValue <= 200) {
		pm25State = "中度";
	}
	if (pmValue >= 201 && pmValue <= 300) {
		pm25State = "重度";
	}
	if (pmValue >= 301) {
		pm25State = "严重";
	}
	
	$("#air_quilty").html(pm25State);

}


//获取当前时间
function getRealTime(){
	var date = new Date();
	return date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes(); 

}

function setMyPic(){
	$.post(
			basePath+'hwmobile/smart/u001!getPic',
		      {
		    	  SESSIONID : localStorage.getItem('SESSIONID',''),//'2F37FE55ECB0E3D27607A01E5312EE86',
					USERID : localStorage.getItem('USERID',''),//'f3d46750-e022-4bf6-88d0-2893caed8b24',
		        
		      },
		      function (data) //回传函数
		      {
		  
		    	  
		    	  $("#userpic").attr("src","data:image/png;base64,"+data.data);  


		      }
		    );
}
function getPM25(){

	
	$.ajax({
		type : 'GET',
		url : basePath+"hwmobile/smart/weather!getPM25",
		data : {
			
			city : cityISO,
		},
		dataType : 'json',
		success:function(data){
		
			$('#air_quality').html(data.message);
			$('#outside_pm25').html(data.count);
		}
	});

}
//设置CurrentSensor
function setCurrentSensor(obj){
	_currentSensorId = obj;
	_currentHistoryType = 2;
	var navList = $('.tabTagList'), navs = navList.children('li'),contentBoxs = $('.tabcon');
	navs.removeClass('current');
	navs.eq(_currentHistoryType).addClass('current');
	contentBoxs.hide().eq(_currentHistoryType).show();
	
	var navList1 = $('.tabTagList1'), navs1 = navList1.children('li'),contentBoxs1 = $('.tabcon1');
	navs1.removeClass('current1');
	navs1.eq(_currentHistoryType).addClass('current1');
	contentBoxs1.hide().eq(_currentHistoryType).show();
	
	var navList2 = $('.tabTagList2'), navs2 = navList2.children('li'),contentBoxs2 = $('.tabcon2');
	navs2.removeClass('current2');
	navs2.eq(_currentHistoryType).addClass('current2');
	contentBoxs2.hide().eq(_currentHistoryType).show();
	
	var navList3 = $('.tabTagList3'), navs3 = navList3.children('li'),contentBoxs3 = $('.tabcon3');
	navs3.removeClass('current3');
	navs3.eq(_currentHistoryType).addClass('current3');
	contentBoxs3.hide().eq(_currentHistoryType).show();
	
	var navList4 = $('.tabTagList4'), navs4 = navList4.children('li'),contentBoxs4 = $('.tabcon4');
	navs4.removeClass('current4');
	navs4.eq(_currentHistoryType).addClass('current4');
	contentBoxs4.hide().eq(_currentHistoryType).show();
	
	var navList5 = $('.tabTagList5'), navs5 = navList5.children('li'),contentBoxs5 = $('.tabcon5');
	navs5.removeClass('current5');
	navs5.eq(_currentHistoryType).addClass('current5');
	contentBoxs5.hide().eq(_currentHistoryType).show();
	
	var navList6 = $('.tabTagList6'), navs6 = navList6.children('li'),contentBoxs6 = $('.tabcon6');
	navs6.removeClass('current6');
	navs6.eq(_currentHistoryType).addClass('current6');
	contentBoxs6.hide().eq(_currentHistoryType).show();
	
	if(_currentSensorId.indexOf("5") == 0){
		   $('#Tab_Air').show();
		   $('#Tab_Gas').hide();
		}else if(_currentSensorId.indexOf("1") == 0){
		  $('#Tab_Air').hide();
		  $('#Tab_Gas').show();
		}
	setRealData();
	setHistoryData();
}


function Map(){
	this.container = new Object();
	}


	Map.prototype.put = function(key, value){
	this.container[key] = value;
	}


	Map.prototype.get = function(key){
	return this.container[key];
	}


	Map.prototype.keySet = function() {
	var keyset = new Array();
	var count = 0;
	for (var key in this.container) {
	//跳过object的extend函数
	if (key == 'extend') {
	continue;
	}
	keyset[count] = key;
	count++;
	}
	return keyset;
	}


	Map.prototype.size = function() {
	var count = 0;
	for (var key in this.container) {
	//跳过object的extend函数
	if (key == 'extend'){
	continue;
	}
	count++;
	}
	return count;
	}


	Map.prototype.remove = function(key) {
	delete this.container[key];
	}


	Map.prototype.toString = function(){
	var str = "";
	for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
	str = str + keys[i] + "=" + this.container[keys[i]] + ";\n";
	}
	return str;
	}