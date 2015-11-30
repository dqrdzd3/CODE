/**
 * 纯jquery分页插件
 * 从jquery-quickSearch-1.0.js中提取出来，可以替换其中的分页
 * @author:duqunxing
 * @date:2013-05-21 
 * @version:1.0
 */
;(function($){
     
    $.fn.PageBar = function(options){
        /**
         * 扩展属性 
         */
        var opts = $.extend({}, $.fn.PageBar.defaults, options);
        /**
         * 可用主题 
         */
        var themes = {
            //加“''”号避免与关键字重复
            'default':'defaultPageCss',
            jqgrid:'jqgridPageCss'
        };
        var pageFun = {
            'default':defaultPage,
            jqgrid:jqgridPage
        };
        
        createPage();
        
        function createPage(){
            init();
            var cssUrl = opts.baseDir + 'theme/'+opts.theme+'/page.css';
            loadCss(cssUrl);
            createPageDiv();
            createPageBar();
        }
        /**
         *空属性设置默认值 
         */
        function init(){
          opts.theme = (!opts.theme || opts.theme == '') ? 'default' : opts.theme;
          opts.barId = opts.barId || opts.theme+"PageId";
          opts.pageSize = $.isNumeric(parseInt(opts.pageSize)) ? parseInt(opts.pageSize) : 10;
          opts.pageNo = $.isNumeric(parseInt(opts.pageNo)) ? parseInt(opts.pageNo) : 1;
          opts.totalCount = $.isNumeric(parseInt(opts.totalCount)) ? parseInt(opts.totalCount) : 0;
        }
        /**
         * 创建分页div
         */
        function createPageDiv(){
          if($("#"+opts.pageId).length > 0){
              $("#"+opts.pageId).empty();
          }

          $("<div id='"+opts.barId+"' class='"+themes[opts.theme]+"'></div>")
            .appendTo($("#"+opts.pageId)||"body");
        }
        /**
         *创建分页工具栏 
         */
        function createPageBar(){
            var fun =  pageFun[opts.theme] || function(){
                alert("错误：主题有误！");
            };
            fun();
        }

        /**
         * 默认风格 工具条
         */
        function defaultPage(){
            var _pageSize = parseInt(opts.pageSize || 10);
            var _pageNo = parseInt(opts.pageNo || 1);
            var _totalCount = parseInt(opts.totalCount || 0);
            var _pageNum = parseInt(_totalCount%_pageSize == 0 ? _totalCount/_pageSize : _totalCount/_pageSize+1);
            
            var _pagePrev = _pageNo > 1 ? _pageNo-1 : 1;
            var _pageNext = _pageNo < _pageNum ? _pageNo+1 : _pageNum;  
            var $pageOut,$pageBar;
            /**
             *生成默认分页条 
             */
            defaultPageBar();
            
            /**
             *添加到分页div中 
             */
            $pageOut = $("div[id='"+opts.barId+"']",$("#"+opts.pageId));
            if($pageOut.length > 0){
               $pageOut.empty();
            }
            $pageBar.appendTo($pageOut);
            /**
             *1.绑定分页标签点击事件 
             *2.禁用不可用标签事件 
             */
           bindDefaultPageEvent();
           forbidBtn();
            
            /**
             *生成默认风格分页工具条的内部函数
             */
            function defaultPageBar(){
                var str = "", prefixStr = "",suffixStr = "";
                
                prefixStr = "<a id='prev' href='javascript:;'><  前一页</a>";
                suffixStr = "<a id='next' href='javascript:;'>后一页  > </a>";
                
                if(_pageNum > 5){
                    for (var i = 1; i < 3; i++) {
                        if(_pageNo == i){
                            str +="<a id='page"+i+"' class='current' href='javascript:;'>"+i+"</a>";
                        }else{
                            str +="<a id='page"+i+"' href='javascript:;'>"+i+"</a>";
                        }
                    };
                    
                    if(_pageNo == 1 || _pageNo == 2 || _pageNo == _pageNum-1 || _pageNo == _pageNum){
                        str +="......";
                    }
                    
                    if(_pageNo == 3){
                        str +="<a id='page"+_pageNo+"' class='current' href='javascript:;' >"+_pageNo+"</a>";
                        str +="...";
                    }
                    
                    if(_pageNo > 3 && _pageNo < _pageNum-2){
                        str +="...";
                        str +="<a id='page"+_pageNo+"' class='current' href='javascript:;' >"+_pageNo+"</a>";
                        str +="...";
                    }
                    
                    if(_pageNo == _pageNum-2){
                        str +="...";
                        str +="<a id='page"+_pageNo+"' class='current' href='javascript:;' >"+_pageNo+"</a>";
                    }
                    
                    for (var j = _pageNum -1; j < _pageNum+1; j++) {
                        if(_pageNo == j){
                            str +="<a id='page"+j+"' class='current' href='javascript:;' >"+j+"</a>";
                            
                        }else{
                            str +="<a id='page"+j+"' href='javascript:;' >"+j+"</a>";
                            
                        }
                    };
                    
                }else{
                    
                    for (var i=1; i < _pageNum+1; i++) {
                        if(_pageNo == i){
                            str +="<a id='page"+i+"' class='current' href='javascript:;'>"+i+"</a>";
                        }else{
                            str +="<a id='page"+i+"' href='javascript:;'>"+i+"</a>";
                        }
                    };
                }
                $pageBar = $(prefixStr+str+suffixStr);
            }
            /**
             * 绑定分页的内部函数 
             */
            function　bindDefaultPageEvent(){
                 $pageOut.find("a[id=prev]")
                           .bind('click', function() {
                              opts.load(_pagePrev);
                           })
                           .end()
                           .find("a[id=next]")
                           .bind('click', function() {
                               opts.load(_pageNext);
                           })
                           .end()
                           .find("a[id^=page]")
                           .each(function(i) {
                             var no = isNaN(parseInt($(this).text())) ? 0 : parseInt($(this).text());
                             $(this).bind('click', function() {
                               opts.load(no);
                             });
                           });
            }
           /**
            *禁用不可用标签事件的内部函数
            */
            function forbidBtn(){
                if(_totalCount == 0){
                    forbidPrevBtn();
                    forbidNextBtn();
                }
                
                if(_pageNo == 1){
                    forbidPrevBtn();
                }
                
                if(_pageNo == _pageNum){
                    forbidNextBtn();
                }
            }
            /**
             * 禁止前一页按钮
             */
            function forbidPrevBtn(){
                $("a[id=prev]",$pageOut)
                  .unbind('click')
                  .addClass("disabled");
            }
             /**
             * 禁止后一页按钮
             */
            function forbidNextBtn(){
               $("a[id=next]",$pageOut)
                  .unbind('click')
                  .addClass("disabled");
            }
            
        }
        /**
         *jqgrid主题工具条 
         */
        function jqgridPage(){
            var _pageSize = parseInt(opts.pageSize || 10);
            var _pageNo = parseInt(opts.pageNo || 1);
            var _totalCount = parseInt(opts.totalCount || 0);
            var _pageNum = parseInt(_totalCount%_pageSize == 0 ? _totalCount/_pageSize : _totalCount/_pageSize+1);
            var _pagePrev = _pageNo > 1 ? _pageNo-1 : 1;//上一页
            var _pageNext = _pageNo < _pageNum ? _pageNo+1 : _pageNum;//下一页  
            var _pageFirst = 1,_pageLast = _pageNum;//首页，最后页
            var $pageOut,$pageBar;
            /**
             * 生成jqgrid风格分页条 
             */
            jqgridPageBar();
             /**
             *添加到分页div中 
             */
            $pageOut = $("div[id='"+opts.barId+"']",$("#"+opts.pageId));
            if($pageOut.length > 0){
               $pageOut.empty();
            }
            $pageBar.appendTo($pageOut);
            /**
             *1.设置当前 页
             *2.设置可选择列表数量
             *3.设置数据显示信息
             *4.绑定按钮事件
             *5.禁止按钮事件 
             */
            setCurPageNO();
            setPageSize();
            setSizeAndData();
            bindDefaultPageEvent();
            forbidBtn();
            
            //以下是内部函数
            /**
             * 创建分页条 
             */
            function jqgridPageBar(){
                    // <div id="jqGridPage" class="defaultPageCss">
                        // <a href="javascript:void(0);" id="firstPage">首页</a>
                        // <a href="javascript:void(0);" id="firstPage">上一页</a>
                        // <span><input id="curPage" style="width:25px;" value="1"/></span>
                        // <span>共0页</span>
                        // <a href="javascript:void(0);" id="firstPage">下一页</a>
                        // <a href="javascript:void(0);" id="firstPage">尾页</a>
                        // <select id="pageSizeSelect" style="width:45px;">
                            // <option value="5">5</option>
                            // <option value="10">10</option>
                            // <option value="15">15</option>
                        // </select>
                        // <span id="noData" style="float:right;">无数据显示</span>
                        // <span id="haveData" style="display:none;float:right;">#startRow#-#endRow#&nbsp;&nbsp;共#totalCount#条</span>
                    // </div>
                    
                                   
                            
                   $pageBar = $('<table border="0"  align="center" cellpadding="0" cellspacing="0" width="100%"><tr>'+
                              '<td width="40%" nowrap="nowrap">&nbsp;</td>'+
                              '<td width="20%" align="center"  nowrap="nowrap"><div style="width:250px;"><ul>'+
                              '<li class="shouye"><a href="javascript:void(0);" id="firstPage"></a></li>'+
                              '<li class="shouye"><a href="javascript:void(0);" id="prev"></a></li>'+
                              '<li class="pad_t f"><span>|</span></li>'+
                              '<li class="pad_t"><input id="curPage" style="width: 20px; float: left; height: 10px;line-height:10px;margin-top:2px;text-align:middle;" value="1" /></li>'+
                              '<li class="pad_t f12"><span id="pageNum" style="width: 50px;">共&nbsp;0页</span></li>'+
                              '<li class="pad_t f"><span>|</span></li>'+
                              '<li class="xiayiye"><a href="javascript:void(0);" id="next"></a></li>'+
                              '<li class="weiye"><a href="javascript:void(0);" id="lastPage"></a></li>'+
                              '<li class="pad_t"><select id="pageSize" style="width: 40px; float: left; height: 17px;margin-top:2px;"></select></li>'+
                              '</ul></div></td> '+
                              '<td width="40%"  nowrap="nowrap"><span id="dataInfo" style="float:right;">暂无数据</span></td>'+
                              '</tr></table>'
                            );
            }
            /**
             *  设置总页数和数据条数
             */
            function setSizeAndData(){
                $("#pageNum",$pageOut).html("共&nbsp;"+_pageNum+"页");
                var dataInfo = "#startRow#-#endRow#&nbsp;&nbsp;共#totalCount#条";
                var startRow=endRow=totalCount=0;
                if(_totalCount != 0 && _totalCount >(_pageNo-1)*_pageSize+1){
                    startRow = (_pageNo-1)*_pageSize+1;
                    endRow = _pageNo*_pageSize;
                    totalCount = _totalCount;
                    if(_totalCount < endRow){
                        endRow = _totalCount;
                    }
                }
                dataInfo = dataInfo.replace("#startRow#",startRow).replace("#endRow#",endRow).replace("#totalCount#",totalCount);
                $("#dataInfo",$pageOut).html(dataInfo);
            }
            /**
             * 设置当前页
             */
            function setCurPageNO(){
                $("#curPage",$pageOut).val(_pageNo)
                .bind('keydown',function(event){
                    if(event.keyCode == 13){
                        var _curPage = $("#curPage",$pageOut).val();
                        _curPage = parseInt(_curPage);
                        if(!$.isNumeric(_curPage)){
                            _curPage = 1;
                        }
                        if(_curPage>_pageNum){
                            _curPage = _pageNum;
                        }else if(_curPage<1){
                            _curPage = 1;
                        }
                        
                        opts.load(_curPage,_pageSize);
                    }
                });
                
            }
            /**
             * 设置可选页容量
             */
            function setPageSize(){
                var $pageSize = $("#pageSize",$pageOut);
                 $pageSize.empty().bind('change',function(){
                    _pageSize = $pageSize.val();//修改页面容量
                     opts.load(_pageNo,_pageSize);
                 });
                $.each(opts.listSize, function(i, n) {
                    var _num = parseInt(n);
                    if(!$.isNumeric(_num) || _num < 1){
                      return true;
                    }
                    if(_num==_pageSize){
                        $pageSize.append('<option value="'+_num+'" selected="selected">'+_num+'</option>');
                    }else{
                        $pageSize.append('<option value="'+_num+'">'+_num+'</option>');
                    }
                });
            }
            /**
             * 绑定分页的内部函数 
             */
            function　bindDefaultPageEvent(){
                 $pageOut.find("a[id=prev]")
                           .bind('click', function() {
                              opts.load(_pagePrev,_pageSize);
                           })
                           .end()
                           .find("a[id=next]")
                           .bind('click', function() {
                               opts.load(_pageNext,_pageSize);
                           })
                           .end()
                           .find("a[id=firstPage]")
                           .bind('click',function(){
                               opts.load(_pageFirst,_pageSize);
                           })
                           .end()
                           .find("a[id=lastPage]")
                           .bind('click',function(){
                               opts.load(_pageLast,_pageSize);
                           });
                           
            }
           /**
            *禁用不可用标签事件的内部函数
            */
            function forbidBtn(){
                if(_totalCount == 0){
                    forbidPrevBtn();
                    forbidNextBtn();
                    forbidFirstBtn();
                    forbidLastBtn();
                }
                
                if(_pageNo == 1){
                    forbidPrevBtn();
                    forbidFirstBtn();
                }
                
                if(_pageNo == _pageNum){
                    forbidNextBtn();
                    forbidLastBtn();
                }
            }
                        
            /**
             * 禁止前一页按钮
             */
            function forbidPrevBtn(){
                $("a[id=prev]",$pageOut)
                  .unbind('click')
                  .addClass("shangyiyedisabled")
                  .removeAttr('href');
            }
             /**
             * 禁止后一页按钮
             */
            function forbidNextBtn(){
               $("a[id=next]",$pageOut)
                  .unbind('click')
                  .addClass("xiayiyedisabled")
                  .removeAttr('href');
            }
            /**
             * 禁止首页按钮 
             */
            function forbidFirstBtn(){
                $("a[id=firstPage]",$pageOut)
                  .unbind('click')
                  .addClass("shouyedisabled")
                  .removeAttr('href');
            }
             /**
             * 禁止尾页按钮
             */
            function forbidLastBtn(){
               $("a[id=lastPage]",$pageOut)
                  .unbind('click')
                  .addClass("weiyedisabled")
                  .removeAttr('href');
            }
        }

        /** 
         * 加载css文件。和加载js文件一样，动态创建一个link标签，然后追加到head标签中去 
         * @param url css的url 
         * @param callback 回调函数，加载完成后调用此函数 
         */  
        function loadCss(url, callback){  
            var link = document.createElement('link');  
            link.rel = 'stylesheet';  
            link.type = 'text/css';  
            link.media = 'screen';  
            link.href = url;  
            document.getElementsByTagName('head')[0].appendChild(link);  
            if (callback){  
                callback.call(link);  
            }  
        }  

};

$.fn.PageBar.defaults = {
    //默认为当前目录
    baseDir     : '.',
    barId       : '',
    theme       : 'default',
    listSize    : [5,10,15],//jqgrid分页用于显示可选的加载数量
    //须传参数
    pageId      : 'pageId',
    pageSize    : 10,
    pageNo      : 1,
    totalCount  : 0,
    load        : function() {}
};

})(jQuery);



/**
 * jquery 分页自定义配置文件
 * 作用：1.配置使用的主题
 *     2.加载对应主题的css文件 
 *     3.加载jquery.page.js文件
 */
;(function($){
    /**
    {
        //必须传参数
        pageId          : '',//分页工具条要添加到的id
        pageSize    : 10,//默认页容量10
        pageNo      : 1,//默认页号1
        totalCount  : 0,//默认总记录数0
        //默认函数
        load        : function() {
            alert('未指定分页调用的函数');
        }
    }
    **/
    var customOptions = {
        //调用名称如：'pageBar' 用于调用分页函数 $.pageBar({});
        funName     : 'hwPage',
        //基本路径
        baseDir     : '.',
        //主题
        theme       : 'jqgrid'
    }
    
    
    var scripts = document.getElementsByTagName('script');  
    for(var i=0; i<scripts.length; i++){  
        var src = scripts[i].src;  
        if (!src) continue;  
        var m  = src.indexOf('jquery.page.custom.js');
        if (m != -1){  
            customOptions.baseDir = src.substring(0, m);  
            break;
        }  
    }  
    
    /**
     * 用自定义属性修改默认属性 
     */
    $.extend($.fn.PageBar.defaults, customOptions);
    /**
     * 修改调用函数名称为自定义名称 
     */
    $[customOptions.funName] = $.fn.PageBar;
    
})(jQuery);
