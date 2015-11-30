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
var sceneNameMap = new Map();    //主键 场景id  值  场景名称
var sceneMap = new Map();    //场景map  主键sensorid   值：场景名称
var devMap = new Map();       //同deviceMap  主键sensorid  值：设备名称
var cityISO;
var cityname;




function GetQueryString(name) 
{ 
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
     var r = window.location.search.substr(1).match(reg); 
     if(r!=null)return  unescape(r[2]); return null; 
} 



var _tempFlag = GetQueryString("DEVID");



$(document).ready(function() {
	
	basePath = localStorage.getItem('basePath','');
	
	//setCurrentSensor(_tempFlag);
	
	//setChart();
	
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
									  
								
									deviceMap.put(device.air.sensorId,device.air.name);
									devMap.put(device.air.sensorId,device.air.name);
									
								}
							 	 if (device.gas != null){
										if (!_currentGasId) _currentGasId = device.gas.sensorId;
									
										
							 		deviceMap.put(device.gas.sensorId,device.gas.name);
							 		devMap.put(device.gas.sensorId,device.gas.name);
							 	}
								if (device.ctrl != null){
									deviceMap.put(device.ctrl.deviceId,device.ctrl.name);
									devMap.put(device.ctrl.deviceId,device.ctrl.name);
								} 
							}
							if (typeof(_currentSensorId)=="undefined"){
								
							}
							getScene();
							
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
						
						 $("#scene_name").append( "<option value='"+dataObj[i].ma001+"'>"+dataObj[i].ma004+"</option>" );
						 
				
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
	                            	 	sceneMap.put(ss[1],dataObj[i].ma004);
	                            	 	sceneNameMap.put(dataObj[i].ma001,dataObj[i].ma004);
	                            		 deviceStr +=" <li><a  onclick=setCurrentSensor('"+ss[1]+"')>"+ deviceMap.get(ss[1])+" ["+ss[1]+"]" +"</a></li>"; 
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
					 for(var ii in arrays) {      //未分配
					 	deviceNo += " <li class = 'active'><a href='#' onclick=setCurrentSensor('"+arrays[ii]+"')>"+ deviceMap.get(arrays[ii])+" ["+arrays[ii]+"]" +"</a></li>";
					 	sceneMap.put(arrays[ii],"未分配");
					 	$('#dev_type').append( "<option value='"+arrays[ii]+"'>"+arrays[ii]+"--["+devMap.get(arrays[ii])+"]</option>" );
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
					 sceneOnChange();
				
				});	
		
	}
		
		
//初始化图片
setMyPic();

	

});
//场景联动
function sceneOnChange(){
	//var _sceneName = $('#scene_type').find("option:selected").text();

	$('#dev_type').empty();
	$('#dev_type').append( "<option value='0'>全部</option>" );
	$('#dev_type_in').empty();
	$('#dev_type_in').append( "<option value='0'>请选择</option>" );
	$('#dev_type_out').empty();
	$('#dev_type_out').append( "<option value='0'>请选择</option>" );
	var arrays = sceneMap.keySet();
	 for(var ii in arrays) {      //未分配
	
	 	//if (sceneMap.get(arrays[ii]) == _sceneName){
	 		$('#dev_type').append( "<option value='"+arrays[ii]+"'>"+arrays[ii]+"--["+devMap.get(arrays[ii])+"]</option>" );
		 	$('#dev_type_out').append( "<option value='"+arrays[ii]+"'>"+arrays[ii]+"--["+devMap.get(arrays[ii])+"]</option>" );
		 	$('#dev_type_in').append( "<option value='"+arrays[ii]+"'>"+arrays[ii]+"--["+devMap.get(arrays[ii])+"]</option>" );
	 	//}
	 }
	
}
//循环联动
function freqOnChange(){
	var objv = $('#freq_type').find("option:selected").val();
	if (objv == "100")
		$('#spn_frq').show();
	else
		$('#spn_frq').hide(); 
	if (objv == "0" || objv=="4" || objv=="2" || objv=="6" || objv=="7"){
		$('#spn_noloop').show();
		$('#dev_type').hide();
		$('#spn_scene').hide();
	}else if(objv == "3"){
		$('#spn_noloop').hide(); 
		$('#dev_type').hide();
		$('#spn_scene').show();
	}
	else{
		$('#spn_noloop').hide(); 
		$('#dev_type').show();
		$('#spn_scene').hide();
	}
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

function getPM25FromNoise(ad){
	var Actual_Data=new Array(5,12,70,250,500,700,1450,1800,2000,2100,2200); 
	var Real_Data=new Array(0,8,30,80,150,200,300,400,500,750,1000);
	var rd = 0;
	var j = 0;
	for(var i=0;i<Actual_Data.length;i++){
		if (i == Actual_Data.length - 1)
		{
			return 1000;
		}
		if (ad < Actual_Data[0])
		{
			return 0;
		}
		if(ad>=Actual_Data[i] && ad<=Actual_Data[i+1]){
		     j = i;
			 break;
		}
		
	}
	var fm = Actual_Data[j+1] - Actual_Data[j];
	var fz = Real_Data[j+1] - Real_Data[j];
	var fm1  = ad -  Actual_Data[j];
	rd = (fm1 / fm) * fz + Real_Data[j];
	return Math.round(rd);

	
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