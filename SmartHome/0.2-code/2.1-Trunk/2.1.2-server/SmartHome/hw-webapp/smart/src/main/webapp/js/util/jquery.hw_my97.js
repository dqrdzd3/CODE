/*****************start*********************/
/**
 *自定义的事件升级时需保留此部分代码
 *  */
;(function($,w){
    $.extend({
        'my97DateFormat':function(context,dpSltr,fmt){
                    var context = context || document,
                        dpSltr = dpSltr || '.Wdate',
                        fmt = fmt || 'yyyy-MM-dd HH:mm:ss'; 
                        
                    var dateArr = $(dpSltr, context);
                    
                    $.each(dateArr, function(i,dateObj) {
                        
                        var val = $(dateObj).val();
                        
                        var dateformat = $(dateObj).attr("dateformat");
                        
                        fmt = dateformat != null ? dateformat : fmt;
                        
                        if(val){
                            var formatVal = $.formatDate(val,fmt);
                            $(dateObj).val(formatVal);
                        }else{
                            $(dateObj).val("");
                        }
                        
                        
                    });
        },
        'formatDate':function(val,fmt){
                var formatDate="";
                
                //传进的值格式：2012-01-02T15:50:55 || 2012-01-02 15:50:55
               if(fmt == 'yyyy-MM-dd'){
                    formatDate = val.substr(0,10);
                }else if(fmt == 'HH:mm:ss'){
                    formatDate = val.substr(11,8);
                }else if(fmt == 'yyyy-MM-dd HH:mm:ss'){
                    var date = val.substr(0,10);
                    var time = val.substr(11,8);
                    formatDate = date+" "+time;
                }
                
                return formatDate;

        }
    });
    w.hwDatePicker=function(opt){
            var defaults={skin:'blue',lang:'auto'};
            var settings={readOnly:true};
            WdatePicker($.extend(defaults,settings,opt));

    };
})(jQuery,window);
/*****************end*********************/