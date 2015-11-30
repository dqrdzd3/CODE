/**
 * 弹出div层js类
 * $.hw.popup
 * @extends jquery-UI-1.8.6
 * @author:杜群星
 * @date:2013-05-14
 */
;(function($,undefined){
    /**
     *扩展命名空间 
     */
    $.hwPopup = $.hwPopup || {};
    $.extend($.hwPopup, {
        /**
         * 默认选项，对外暴露，可以修改
         * 如： $.hwPopup.popupOpts.autoOpen = true;
         */
          popupOpts:{
                width:762,
                height:500,
                //关闭之前回调函数
                beforeClose:null,
                //关闭时回调函数
                close:null,
                //打开时回调函数
                open:function(){},
                //获得焦点时回调函数
                focus:function(){},
               //弹出层id
                id:'',
                //弹出层标题
                title:'',
                //操作按钮对象
                buttons:{}
          },
         
          /**
           * 外部调用弹出层的方法
           * 如：$.hwPopup.openPopup(); 
           */
          openPopup:function(options,url,params,callback){
                    /**
                     *弹出层属性 
                     */
                    // var options ={
                         // //弹出层id
                         // id:id,
                         // //弹出层标题
                         // title:title,
                         // //操作按钮对象
                         // buttons:buttons,
                         // //关闭之前回调函数
                         // beforeClose:onBeforeClose,
                        // //关闭时回调函数
                         // close:onClose,
                        //打开时回调函数
                        // open:function(){},
                        //获得焦点时回调函数
                       // focus:function(){},
                    // };
                    
                    
                    /**
                     * 用传递的属性修改默认属性 
                     */
                    var _opts = $.extend({},this.popupOpts,options);
                    /**
                     *创建私有数据处理对象 
                     */
                    var _reqData = new oData();
                    /**
                     * 调用发送请求函数,返回数据
                     */
                   _reqData.sendRequset(_opts,url,params,callback);

                 },
              /**
               * 外部调用具有iframe弹出层的方法
               * 如：$.hwPopup.openFramePopup(); 
               */
            openFramePopup:function(options,url,params,callback){
                    /**
                     * 弹出层属性 
                     */
                    // var options = {
                        // //弹出层id
                         // id:id,
                         // //弹出层标题
                         // title:title,
                         // //操作按钮对象
                         // buttons:buttons,
                         // //关闭之前回调函数
                         // beforeClose:onBeforeClose,
                        // //关闭时回调函数
                         // close:onClose,
                         //打开时回调函数
                        // open:function(){},
                        //获得焦点时回调函数
                       // focus:function(){},
                    // };
                    
                    /**
                     * 用传递的属性修改默认属性 
                     */
                    var _opts = $.extend({},this.popupOpts,options);
                    /**
                     *创建私有数据处理对象 
                     */
                    var _reqData = new oData();
                    /**
                     * 加载frame
                     */
                   _reqData.loadFrame(_opts,url,params,callback);
                 },

                 /**
                  *关闭弹出层 
                  */
                closePopup:function(id){
                     
                     //还原默认属性
                    var _popup = new oPopup();
                     _popup.closeDiv(id);
                 },
                 /**
                  *关闭弹出层 
                  */
                closeFramePopup:function(id){
                     
                     //还原默认属性
                    var _popup = new oPopup();
                     _popup.closeFrameDiv(id);
                 }

    });
    
    /**
     * 私有对象 
     */
    var oPopup = function(options){
            
              /**
              * 私有默认属性  
              */
             this.defaults = {
                    modal:true,
                    position:'center',
                    show:{effect:'slide',direction:'up'},
                    hide:{effect:'slide',direction:'down'},
                    dialogClass:'jumpdiv-dialog',
                    autoOpen:false,
                    bgiframe:true,
                    width:762,
                    height:500,
                    maxWidth:1024,
                    maxHeight:768,
                    minWidth:400,
                    minHeight:150,
                    resizable:false,
                    //打开时回调函数
                    open:function(){},
                    //获得焦点时回调函数
                    focus:function(){},
                    //关闭之前回调函数
                    beforeClose:function(){},
                    //关闭时回调函数
                    close:function(){},
                   //弹出层id
                    id:'',
                    //弹出层标题
                    title:'',
                    //操作按钮对象
                    buttons:{}
             };
             this.settings = {};//扩展后供最后使用属性
             this.options  = options || {};//传递进来的参数
            
    };
    oPopup.prototype={
          /**
           *可修改属性集合
           */
          modifiableProperty:['id','title','buttons','close','beforeClose','focus','open','width','height'],
          /**
           * 扩展和修改私有属性
           */
          init:function(){
            var _modifiableOpts ={};
            var _self = this;
            //取出可修改的属性进行修改
            $.each(this.modifiableProperty, function(i, n) {
              _modifiableOpts[n] = _self.options[n];
            }); 
            $.extend(this.settings,this.defaults, _modifiableOpts);
          },
         /**
           * 弹出窗口 
           */
          openDiv:function(){
             var oPopup = this;
             /**
             * 扩展私有属性 
             */
            this.init();
            var _idSelStr = "#"+this.settings.id;
            var $div = $(_idSelStr);
            if($div.length > 0){
                $div.unbind('dialogopen').bind('dialogopen',function(){
                    oPopup.resizeDiv();
                });
                $div.dialog(this.settings).dialog("open");
            }else{
                alert("找不到id为"+id+"的div");
            }
          },
         /**
           * 弹出iframe窗口 
           */
          openFrameDiv:function(){
             var oPopup = this;
             /**
             * 扩展私有属性 
             */
            this.init();
            var id = this.settings.id;
            var _idSelStr = "#"+id;
            var $div = $(_idSelStr);
            if($div.length > 0){
                $div.unbind('dialogopen').bind('dialogopen',function(){
                    $div.removeAttr("src");
                    oPopup.resizeDiv();
                });
                $div.unbind('dialogbeforeclose').bind('dialogbeforeclose',function(id){
                     $div.removeAttr("src");
                });
                $div.dialog(this.settings).dialog("open");
            }else{
                alert("找不到id为"+id+"的div");
            }
             return false;
          },
          /**
           *关闭窗口 
           */
          closeDiv:function(id){
             // var _idSelStr = "#"+id;
             // $(_idSelStr).dialog("close");   
             // $(_idSelStr).dialog("destory");  
             var _idSelStr = "#"+id;
             var $div = $(_idSelStr);
             if($div.length==0){
                  $div = window.parent.$(_idSelStr);
             }
             $div.dialog("close");   
             $div.dialog("destory");  
          },
          /**
           *关闭窗口 
           */
          closeFrameDiv:function(id){
             this.closeDiv(id);
          },
          /**
           * 调整布局大小 
           */
          resizeDiv:function(){
              if($.isEmptyObject(this.settings)){
                  this.init();
              }
              
              var _id = this.settings.id;
              var $divCont = $("#"+_id);
              var divH = $divCont.height();
              var divW = $divCont.width();
              var bottomH = $divCont.find("#windowsbottom").height() || 57;
              var bottomW = $divCont.find("#windowsbottom").width() || 762;
              var tableH=$divCont.find("#windowdiv").height() || 400;
              if(tableH+bottomH-2 > divH){
            	  $divCont.find("#windowdiv").height(divH-bottomH-2);
              }else{
            	  $divCont.height(tableH+bottomH+2);              }
              if(divW < bottomW){
            	  $divCont.width(bottomW);
              }
          },
          /**
           * 调整frame布局大小 
           */
          resizeFrameDiv:function(){
              if($.isEmptyObject(this.settings)){
                  this.init();
              }
              
              var _id = this.settings.id;
              var $divCont = $("#"+_id);
              var $frame = $(window.frames["yhlbFrame"].document || window.frames["yhlbFrame"].contentDocument);
              var frameH = $frame.height();
              var divH = $divCont.height();
              var bottomH = $frame.find("#windowsbottom").height() || 57;
              var tableH = $frame.find("#windowdiv").height() || 300;    
              $frame.height(divH-bottomH);
              $frame.find("#windowdiv").height(divH-bottomH);
          }
        
    };
    
  var oData = function(){};
    
  oData.prototype={
       /**
          * 发送请求
          */
         sendRequset:function (options,url,params,callback){
            var _idSelStr = "#"+options.id;
            $.ajax({
                   url:url,
                   data:params,
                   type:'POST',
                   success:function(data){
                       
                       if (data && data.IDENTITY == 'message') {
                            $.hwDialog.showMsg(data.content,data.type,{title:data.title,buttons:{
                                '确定':function(){
                                    $(this).dialog('close');
                                }
                            }});
                        }else{
                            $(_idSelStr).html(data);
                           /**
                            * 处理数据，执行回调函数，弹出窗口 
                            */
                           if(data && callback){
                               callback(data);
                           }
                           
                           /**
                             *创建私有弹出层对象 
                             */
                            var _popup = new oPopup(options);
                           /**
                            *打开div 
                            */
                           _popup.openDiv();
                        }
                        
                    },
                   error:function(XMLHttpRequest, textStatus, errorThrown){
                       var _errMsg = '';
                           if(textStatus && errorThrown){
                               _errMsg += '错误信息：'+textStatus;
                               _errMsg += '\r\n异常信息信息：'+errorThrown;
                           }else if(textStatus){
                               _errMsg += '错误信息：'+textStatus;
                           }else if(errorThrown){
                               _errMsg += '异常信息信息：'+errorThrown;
                           }
                           alert(_errMsg);
                       }
                  });
            },
         /**
          *加载frame 
          */
         loadFrame:function(options,url,params,callback){
             var _params,_src,_idSelStr,$frame;
             if($.isPlainObject(params)){
                 _params = $.param(params);
             }else{
                 _params = params;
             }
             
             _src =  url.indexOf("?") == -1 ? (url+"?"+ _params) : (url + _params)  + "&radom="+Math.random();
            var _id = options.id;
            _idSelStr = "#"+_id;
            $frame = $(_idSelStr);
            if($frame.length == 0){
                alert("找不到id为"+id+"的Frame");
            }
            /**
             *创建私有弹出层对象 
             */
           var _popup = new oPopup(options);
           /**
            *打开div 
            */
            _popup.openFrameDiv();
             $frame.attr('src',_src);
         }
  };
})(jQuery);


