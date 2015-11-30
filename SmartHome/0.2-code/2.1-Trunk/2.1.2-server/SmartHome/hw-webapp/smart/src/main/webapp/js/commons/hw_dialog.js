/**
 * 对话框js类
 * $.hw.showMsg
 * @extends jquery-UI-1.8.6
 * @author:杜群星
 * @date:2013-05-14
 */
;(function($,undefined){
        /**
         *扩展命名空间 
         */
         $.hwDialog = $.hwDialog || {};
         $.extend($.hwDialog, {
                 /**
                  * 消息框 
                  */
                 showMsg:function(msg, type, option){
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
                    }, option);
                    
                    $(msgBoxSltr).dialog(initOption);
                }
         });
         /**
         *提示消息类型常量 
         */
         MSG={NONE:0,OK:1,INFO:2,QUESTION:3,WARNING:4,ERROR:5};
})(jQuery);
