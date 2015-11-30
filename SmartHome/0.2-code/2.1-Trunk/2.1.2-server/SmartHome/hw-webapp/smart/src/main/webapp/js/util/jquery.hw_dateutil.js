/**
 *日期工具类,jquery插件 
 *@author:杜群星
 *@date:201209251605
 */
;(function($,W){
  W.HwDateUtil = {YEAR:0,QUARTER:1,MONTH:2,WEEK:3,DAY:4,HOUR:5,MINUTE:6,SECOND:7,MILLSECOND:8};
  $.extend(W.HwDateUtil,{
      /**
       *增加日期的某个字段值 
       * t 类型，n 数量，d 日期字符串(格式：yyyy-MM-dd HH:mm:ss.SSS)
       */
      'add': function(d,t,n){
            if(typeof d == 'string'){
                d = this.parseDate(d);
            }
            var date = d,//日期
                type = parseInt(t),//类型
                num = parseInt(n);//数量

            switch(type) {
                case 0 :
                    //年
                    date.setFullYear(date.getFullYear() + num);
                    break;
                case 1 :
                    //季度
                    date.setMonth(date.getMonth() + (num * 3));
                    break;
                case 2 :
                    //月
                    date.setMonth(date.getMonth() + num);
                    break;
                case 3 :
                    //周
                    date.setDate(date.getDate() + (num*7));
                    break;
                case 4 :
                    //天
                    date.setDate(date.getDate() + num);
                    break;
                case 5 :
                    //时
                    date.setHours(date.getHours() + num);
                    break;
                case 6 :
                    //分
                    date.setMinutes(date.getMinutes() + num);
                    break;
                case 7 :
                    //秒
                    date.setSeconds(date.getSeconds() + num);
                    break;
                case 8 :
                    //毫秒
                    date.setMilliseconds(date.getMilliseconds());
                    break;
                default:
                    break;

            }
            return date.getFullYear() +'-' +  this.check(date.getMonth()+1) + '-' +this.check(date.getDate())+ ' '+ this.check(date.getHours())+':'+this.check(date.getMinutes())+':'+this.check(date.getSeconds())+'.'+(date.getMilliseconds());
 
      },
       /**
        *字符串类型转成Date类型 
        * 主要针对'yyyy-MM-dd HH:mm:ss.SSS'格式
        * 'yyyy/MM/dd HH:mm:ss'直接调用Date.parse(yyyy/MM/dd HH:mm:ss);进行转换
        */
      'parseDate':function(str){
          /*将String类型解析为Date类型.  
          parseDate('2006-1-1') return new Date(2006,0,1)  
          parseDate(' 2006-1-1 ') return new Date(2006,0,1)  
          parseDate('2006-1-1 15:14:16') return new Date(2006,0,1,15,14,16)  
          parseDate(' 2006-1-1 15:14:16 ') return new Date(2006,0,1,15,14,16);  
          parseDate('2006-1-1 15:14:16.254') return new Date(2006,0,1,15,14,16,254)  
          parseDate(' 2006-1-1 15:14:16.254 ') return new Date(2006,0,1,15,14,16,254)  
          parseDate('不正确的格式') retrun null  
        */  
      if(typeof str == 'string'){   
        var results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) *$/);   
        if(results && results.length>3){   
          return new Date(parseInt(this.delZero(results[1])),parseInt(this.delZero(results[2])) -1,parseInt(this.delZero(results[3])));
        }
        
        results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2}) *$/);   
        if(results && results.length>6){
          return new Date(parseInt(this.delZero(results[1])), parseInt(this.delZero(results[2]))-1, parseInt(this.delZero(results[3])), parseInt(this.delZero(results[4])), parseInt(this.delZero(results[5])), parseInt(this.delZero(results[6])));
        }
        
        results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2})\.(\d{1,9}) *$/);   
        if(results && results.length>7){
          return new Date(parseInt(this.delZero(results[1])),parseInt(this.delZero(results[2]))-1,parseInt(this.delZero(results[3])),parseInt(this.delZero(results[4])),parseInt(this.delZero(results[5])),parseInt(this.delZero(results[6])),parseInt(results[7]||0));
        }
      }   
      return null;   
      },
      'delZero':function(str){
            if (str.charAt(0) == '0') {
                return str.substring(1);
            }
            return str;

      },
      /**
       * 判断是否闰年 
       */
      'isRunYear':function(year){
           if (0==year%4&&((year%100!=0)||(year%400==0))){
             return true;
           }else{
             return false;
           }  
      },
      'check':function(i){
          if(i<10){
            i="0"+i;   
          }
          return i;
      },
      /**
       * 格式化日期，传入的日期必须是"yyyy-MM-dd HH:mm:ss"类型格式或直接是个Date对象
       * 可转成任意格式
       */
      'formatDate':function(date,fmt){
          // var date;
          if(typeof date == 'string'){
              date = this.parseDate(date);
          }
          var o = {
           "M+" : date.getMonth()+1, //月份   
           "d+" : date.getDate(), //日   
           "h+" : date.getHours()%12 == 0 ? 12 : date.getHours()%12, //小时   
           "H+" : date.getHours(), //小时   
           "m+" : date.getMinutes(), //分   
           "s+" : date.getSeconds(), //秒   
           "q+" : Math.floor((date.getMonth()+3)/3), //季度   
           "S" : date.getMilliseconds() //毫秒   
           };   
           var week = {   
           "0" : "\u65e5",   
           "1" : "\u4e00",   
           "2" : "\u4e8c",   
           "3" : "\u4e09",   
           "4" : "\u56db",   
           "5" : "\u4e94",   
           "6" : "\u516d"  
           };   
           if(/(y+)/.test(fmt)){   
               fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
           }   
           if(/(E+)/.test(fmt)){   
               fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[date.getDay()+""]);   
           }   
           for(var k in o){   
               if(new RegExp("("+ k +")").test(fmt)){ 
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
                
               }   
           }   
           return fmt;   
       },
       /**
        * 判断的日期d1是否大于日期d2
        */
       'compare':function(d1,d2){
           return d1>d2;
       }
  }
  );
  
})(jQuery,window);

