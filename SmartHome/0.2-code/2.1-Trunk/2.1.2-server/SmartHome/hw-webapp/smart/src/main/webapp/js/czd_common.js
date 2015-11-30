//陈浙东定义消息
//message box， 消息提示框
// 参数：msg:消息 , type:图标类型 , option:自定义dialog选项
// eg: 自定义按钮时:
/*	showMsg('这是消息内容', 1, {
 * 		title : '这是消息框的标题',
 * 		buttons:{
 * 			'确定' : function(){}
 * 			'取消' : function(){}
 * 		}
 *  });
 * 
 */
function showMsg(msg, type, option) {
	var msgBoxId = "msg_box",
		msgBoxSltr = "#" + msgBoxId,
		msgDivClass = 'msg-div',
		
		// style control
		dialogClass = 'msgbox-dialog',
		icoType = (typeof type) == "number" ? type : 0,
		option = (typeof type) == "object" ? type : (option || {});		
		

	if ($(msgBoxSltr).length == 0) {
		$("body").append(
			$("<div>").attr({id:msgBoxId, 'class':'hide'})
					  .append($('<div class="' + msgDivClass + '"/>'))
		);
	}
		
	// icon， 左侧类型图标
	var iconClass = {0:'ico-none', 1:'ico-ok', 2:'ico-info', 3:'ico-question', 4:'ico-warning', 5:'ico-error'}[icoType] || 'ico-none';
	$(msgBoxSltr + " ." + msgDivClass).attr('class', msgDivClass + " " + iconClass);

	// message， 消息内容
	$(msgBoxSltr + " ." + msgDivClass).html(msg||'');
	
	
	// 消息框的按钮
	var buttons = option.buttons || {'确定':'close'},
		close = function() {$(this).dialog('close');};
	
	for (var p in buttons)
		if (buttons[p] == 'close') buttons[p] = close;
	
	delete option.buttons;
	option.title = option.title || '消息';
	
	var initOption = $.extend({
		modal:true,
		resizable:false,
		buttons:buttons,
		dialogClass: dialogClass
		//zIndex:999999 // greater than resize handler	
			
	}, option);
	
	
	$(msgBoxSltr).dialog(initOption);
};

/**
 *提示消息类型常量 
 */
var MSG = {'NONE':0,'OK':1,'INFO':2,'QUESTION':3,'WARNRING':4,'ERROR':5};



function showQuestionMsg(msg, type, option) {
	var msgBoxId = "msg_box",
		msgBoxSltr = "#" + msgBoxId,
		msgDivClass = 'msg-div',
		
		// style control
		dialogClass = 'msgbox-dialog',
		icoType = (typeof type) == "number" ? type : 0,
		option = (typeof type) == "object" ? type : (option || {});		
		

	if ($(msgBoxSltr).length == 0) {
		$("body").append(
			$("<div>").attr({id:msgBoxId, 'class':'hide'})
					  .append($('<div class="' + msgDivClass + '"/>'))
		);
	}
		
	// icon， 左侧类型图标
	var iconClass = {0:'ico-none', 1:'ico-ok', 2:'ico-info', 3:'ico-question', 4:'ico-warning', 5:'ico-error'}[icoType] || 'ico-none';
	$(msgBoxSltr + " ." + msgDivClass).attr('class', msgDivClass + " " + iconClass);

	// message， 消息内容
	$(msgBoxSltr + " ." + msgDivClass).html(msg||'');
	
	
	// 消息框的按钮
	var buttons = {'确定':'ok' , '取消':'cancle'},
		cancle = function() {$(this).dialog('close');},
		ok = function() {
			//继续处理
			jQuery('#infocontent').dialog('close');
			$(this).dialog('close');
			//刷新列表
			reloadGrid('b02000table');
			
		};
	
	for (var p in buttons)
		if (buttons[p] == 'close') buttons[p] = close;
	
	delete option.buttons;
	option.title = option.title || '消息';
	
	var initOption = $.extend({
		modal:true,
		resizable:false,
		buttons:buttons,
		dialogClass: dialogClass
		//zIndex:999999 // greater than resize handler	
			
	}, option);
	
	
	$(msgBoxSltr).dialog(initOption);
};


function checkDate(beginTime , endTime){
	var now = new Date();
    
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
    
    if(parseInt(month) < 10) month = '0'+month;
    if(parseInt(day) < 10) day = '0'+day;
	var str = year + '-' + month + '-' + day ;

	
    if(beginTime < str){
    	showMsg("开始日期不能小于今天！",5,"");
    	return false;
    }
    
    if(beginTime > endTime){
    	showMsg("开始日期不能大于结束日期！",5,"");
    	return false;	
    }
    return true;
}