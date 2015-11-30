
;(function($) {
    
$.fn.QS = function(opts) {
    
    var options = {},//全局属性
        jsonData = [],//json数据
        key,//调用对象的值
        id='',//调用对象id
        idSel,//调用对象的选择器
        $obj,//调用对象
        $qsBody,//div对象
        $qsHeard,//div表头对象
        $qsContent,//div内容对象
        $qsHeadTbObj,//表头对象
        $qsContentTbObj,//内容表对象
        $qsPageObj,//分页对象
        oStartRow = 1,//开始行
        oEndRow = 10,//结束行
        oPageSize = 10,
        oPageNo =  1,
        oTotalCount = 0,//总记录数,
        undefined//未定义对象
        ;
    
        options = $.extend({}, $.fn.QS.defaults, opts);

        quickSearch(options);
        
      //private function in the following  
      function quickSearch(opt){
            
            init(opt);
            
            if(getVal() != '' && getVal() == getOldVal()){
                   return false; 
            }
            
            setOldVal();
                        
            reDefine();
            
            if($qsBody.length > 0){
                $qsBody.show();    
                $qsHeadTbObj.empty();    
                $qsContentTbObj.empty();
                $qsPageObj.empty();
            }else{
                createDiv();
            }
            
            setPosition();
            
            searchData();
            
        }
        
        // We should hide the qs selector if a click event happens outside of it
         function hideIfClickOutside(event) {
            if (event.target != $obj[0] && !insideSelector(event)) {
              closeQS();
            };
          }
          
          // Returns true if the given event occurred inside the qs selector
          function insideSelector(event) {
            var offset = $qsBody.position();
            offset.right = offset.left + $qsBody.outerWidth();
            offset.bottom = offset.top + $qsBody.outerHeight();
            
            return event.pageY < offset.bottom &&
                   event.pageY > offset.top &&
                   event.pageX < offset.right &&
                   event.pageX > offset.left;
          }
        
        /**
         * 重新定位弹出层
         */
        function setPosition(){
            var offset = $obj.offset();
            $qsBody.css({left:offset.left,top:offset.top+$obj.outerHeight()})
        }
        
        function getObj(id){
            return $("#"+id);
        }
        
        function reDefine(){
            $qsBody = getObj(options.qsId);
            $qsHeard = getObj(options.qsHeadId);
            $qsContent = getObj(options.qsContentId)
            $qsHeadTbObj = getObj(options.qsHeadId).find("table thead");
            $qsContentTbObj = getObj(options.qsContentId).find('table tbody');
            $qsPageObj = getObj(options.qsPageId);
        }
        
        /**
         *初始化数据 
         */
        function init(opt){
            id = options.id;
            oPageSize = options.pageSize;
            oPageNo = options.pageNo;
            idSel = "#"+id;
            $obj = $(idSel);
            setPageData();
            setVal();
        }
        
        /**
         *查询数据 
         * @param {Object} key
         */
        function searchData(){
            var url = options.url,
                param = $.extend(options.param,{key:key,startRow:oStartRow,endRow:oEndRow});
                
            $.ajax({
                url:url,
                data:param,
                type:'POST',
                dataType:'json',
                success:onSuccess,
                error:onError
            });
        }
        
        function onSuccess(data){
            jsonData = data.qsData || [];
            
            oTotalCount = data.totalCount || 0;
            
            setData();
        
            fillHeadData();
            
            fillContentData();
            
            $.fn.PageBar(
                {
                    id           : options.qsId,
                    pageId       : options.qsPageId,
                    pageSize     : oPageSize,
                    pageNo       : oPageNo,
                    totalCount   : oTotalCount,
                    load         : load
                }
            );
            
            bindEvent();
        
            setEvenTrColor(); 
            
        }
        
        function onError(data){
            alert("错误信息："+data);
        }
        
        
        function createDiv(){
            var qsId = options.qsId;
            if($qsBody){
                $qsBody.remove();
            }
            
            var offset = $obj.offset();
            
            $qsBody = $("<div id='"+qsId+"'></div>")
                        .addClass('all')
                        .appendTo($('body'))
                        .show();
                        
            $qsHeard = $("<div id='"+options.qsHeardId+"'></div>")
                .addClass('tablehead')
                .appendTo($qsBody);
            $qsContent = $("<div id='"+options.qsContentId+"'></div>")
                .addClass('tablecontent')
                .appendTo($qsBody);
             
             $qsHeard.append("<table width='540' border='0' cellspacing='0' cellpadding='0' class='tableword'><thead></thead></table>");
             $qsContent.append("<table width='540' border='0' cellspacing='0' cellpadding='0' class='tableword'><tbody></tbody></table>");
                      
            
        }
        
        /**
         *填充表头数据 
         */
        function fillHeadData(){
            $qsHeadTbObj = $qsBody.find("div[id="+options.qsHeardId+"] table thead");
            
            if($qsHeadTbObj.length > 0){
                $qsHeadTbObj.empty();
            }
            
            //添加表头     
            var $qsHeadTrObj = $("<tr id='qsHead_tr'></tr>").appendTo($qsHeadTbObj);
            
            //填充表头字段
            $.each(options.colNames, function(i, n) {
                var flag = options.hideCols[i] != undefined ? options.hideCols[i] : true ;
                var display = flag == true ? '' : 'hide';
                $qsHeadTrObj.append("<th class='"+display+"'>"+n+"</th>");
            });       
            
        }
        /**
         *填充内容数据 
         */
        function fillContentData(){
            
            $qsContentTbObj = $qsBody.find("div[id="+options.qsContentId+"] table tbody");
            
            if($qsContentTbObj.length > 0){
                $qsContentTbObj.empty();
            }
            
            $.each(jsonData, function(i, n) {
                //添加内容行
                var $qsContentTrObj = $("<tr id='qsConten_tr_"+i+"' ></tr>").appendTo($qsContentTbObj);
                //填充内容行数据
                $.each(options.colVals, function(j, m) {
                    var flag = options.hideCols[j] != undefined ? options.hideCols[j] : true ;
                    var display = flag == true ? '' : 'hide';
                    $qsContentTrObj.append("<td title='"+n[m]+"' class='"+display+"'>"+n[m]+"</td>");
                });
                //存储数据到行dom
                 $qsContentTrObj.data("trData",n);
            });  
        }
        
        /**
         * 绑定事件
         */
        function bindEvent(){
            //绑定行事件
            $("div[id="+options.qsContentId+"] table tbody tr")
                .unbind()
                .bind({
                     click:onClickFun,
                     mouseout:onMouseOutFun,
                     mousemove:onMouseMoveFun
                 })
                .on('dealdata',dealData);
            
           //点击时判断是否隐藏窗体         
          $([window, document.body]).bind("click",hideIfClickOutside);
          $(document.body).bind("keydown", keydownHandler);
        }
        
        
        /**
         * 点击行
         */
        function onClickFun(){
            $(this).trigger('dealdata');
        }
        
        
        
        /**
         * 鼠标离开行
         */
        function onMouseOutFun(){
            $(this).removeClass('focuscolor');
        }
        /**
         * 鼠标移动到行
         */
        function onMouseMoveFun(){
            $(this).addClass('focuscolor');
        }
        
        /**
         * 当输入框按下enter键时触发
         */
        function onKeyDown() {
            if(getOldVal() != '' && getOldVal() == getVal()){
                return false;
            }
            reload();
        }
        
        
      // Respond to various different keyboard events
      function keydownHandler(event) {
        switch (event.keyCode)
        {
          case 9: // tab
          case 27: // esc
            closeQS();
            return;
            break;
          case 13: // enter
            onKeyDown();
            return;
            break;
          case 33: // page up
            oPageNo = oPageNo == 1 ? oPageNo : oPageNo-1;
            load(oPageNo);
            return;
            break;
          case 34: // page down
            oPageNo = oPageNo == parseInt(oTotalCount%oPageSize==0 ? oTotalCount/oPageSize : oTotalCount/oPageSize+1) ? oPageNo : oPageNo+1;
            load(oPageNo);
            return;
            break;
          case 38: // up
          break;
          case 40: // down
          break;
          default:
            return;
        }
        event.preventDefault();
      }
                
        
        /**
         * 重新加载数据
         */
        function reload(){
            //重新获取对象值
            setVal();
            
            setPageData();
            
            searchData();
            
        }
        
        /**
         *设置偶数行颜色 
         */
        function setEvenTrColor(){
             $qsBody.find("div[id="+options.qsContentId+"] table tbody tr:even").addClass("evencolor");
        }
        
        /**
         * 处理选择行数据
         */
        function dealData(){
           var data =  $(this).data("trData");
           if(!$.isEmptyObject(options.selCols)){
               $.each(options.selCols, function(id,col) {
                 $("#"+id).val(data[col]);
               });
           }else{
               $(idSel).val(data[options.selCol]);
           }
           
           //关闭快速查询层
           closeQS();
            
        }
        
        /**
         * 设置全局值 
         */
        function setOptions(key,val){
            options[key]=val;
        }
        
        /**
         *设置数据 
         */
        function setData(){
            var _colNames=[], _colVals=[];
            //提取表头字段和数据字段数组
            $.each(jsonData, function(i, n) {
                var str = $.param(n);
                var arr = str.split("&");
                _colNames = _colVals = $.map(arr, function(elem) {
                  return elem.split("=")[0];
                });
                return false;
            });  
            
            //设置全局选项数据
            if($.isEmptyObject(options.colNames)){
                setOptions("colNames",_colNames);
            }
            if($.isEmptyObject(options.colVals)){
                setOptions("colVals",_colVals);
            }
        }
        
        /**
         *设置分页用的数据 
         */
        function setPageData(){

            oStartRow = (oPageNo-1)*oPageSize+1;
            oEndRow = oPageNo*oPageSize;  
        }
        
        /**
         *设置调用对象的值 
         */
        function setVal(){
            key = $obj.val() || '';
        }
        
        function getVal(){
            return $obj.val() || '';
        }
        
        function getOldVal(){
            return $obj.data('oldValue') || '';
        }
        
        function setOldVal(){
            $obj.data('oldValue',getVal());
        }
        /**
         * 分页加载数据 
         */
        function load(no){
            oPageNo = no;
            reload();
        }
        
        
        /**
         *关闭快速查询弹出层 
         */
        function closeQS(){
           if($qsBody){
               $qsBody.hide();
               // $qsBody.remove();
           }
          
           //清空对象中存储的值
            $obj.removeData('oldValue');
           //取消事件
           unBindEvent();
        }
        
        function unBindEvent(){
             $([window, document.body]).unbind("click", hideIfClickOutside);
             $(document.body).unbind("keydown", keydownHandler);
        }
  

};

// default options
$.fn.QS.defaults = {
        id             : '',                                                                           //调用对象id
        qsId           : 'qsDiv',                                                                      //弹出层id
        qsHeardId      : 'qsHead',                                                                     //表头id
        qsContentId    : 'qsContent',                                                                  //内容id
        qsFooterId     : 'qsFooter',   
        qsPageId       : 'qsPageId',                                                                //表尾id
        pageSize       : 10,
        pageNo         : 1, 
        idName         : '',                                                                           //主键字段名
        hideCols       : [],                                                                         //隐藏字段集合
        selCol         : '',                                                                           //选择的字段（当selCols为空时有效）         
        colNames       : [],                                                                         //表头字段名称集合['字段1','字段2','字段3','字段4','字段5','字段6']     
        colVals        : [],                                                                         //表头字段对应字段值['MA001','MA002','MA003','MA004','MA015','MA016']
        selCols        : [],                                                                             //要填入值的对象Id:字段名称
        param          : {},                                                                           //附加参数
        url            : '',                                                                           //url
        setting        : {width:'500',height:'300',x:'5',y:'5',zIndex:'100000'}                        //默认的设置
};

$.fn.PageBar = function(options){
    
        var opts = $.extend({}, $.fn.PageBar.defaults, options);
        
        
        createPage();
        
        function createPage(){
            createPageDiv();
            createPageBar();
        }
        
    
        /**
         *创建分页工具栏 
         */
        function createPageBar(){
                var _pageSize = parseInt(opts.pageSize || 10);
                var _pageNo = parseInt(opts.pageNo || 1);
                var _totalCount = parseInt(opts.totalCount || 0);
                var _pageNum = parseInt(_totalCount%_pageSize == 0 ? _totalCount/_pageSize : _totalCount/_pageSize+1);
                
                var pagePrev = _pageNo > 1 ? _pageNo-1 : 1;
                var pageNext = _pageNo < _pageNum ? _pageNo+1 : _pageNum;  
                
                var prefixStr = "<a id='prev' href='javascript:;'><  前一页</a>";
                var suffixStr = "<a id='next' href='javascript:;'>后一页  > </a>";
                var str = "";
                
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
                
                
                var $pageBar = $(prefixStr+str+suffixStr);
                
                
                if($("div[id='"+opts.barId+"']",$("#"+opts.pageId)).length > 0){
                   $("div[id='"+opts.barId+"']",$("#"+opts.pageId)).empty();
                }
                
                $pageBar.appendTo($("div[id='"+opts.barId+"']",$("#"+opts.pageId)));
                
                $("#"+opts.barId).find("a[id=prev]")
                           .bind('click', function() {
                              opts.load(pagePrev);
                           })
                           .end()
                           .find("a[id=next]")
                           .bind('click', function() {
                               opts.load(pageNext);
                           })
                           .end()
                           .find("a[id^=page]")
                           .each(function(i) {
                             var no = isNaN(parseInt($(this).text())) ? 0 : parseInt($(this).text());
                             $(this).bind('click', function() {
                               opts.load(no);
                             });
                           });
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
         * 创建分页div
         */
        function createPageDiv(){
          if($("#"+opts.pageId,$("#"+opts.id)).length > 0){
              $("#"+opts.pageId,$("#"+opts.id)).remove();
          }
            
          $("<div id='"+opts.pageId+"'></div>")
            .append("<div id='"+opts.barId+"' class='"+opts.pageCss+"'></div>")
            .appendTo($("#"+opts.id));
        }
        
        /**
         * 禁止前一页按钮
         */
        function forbidPrevBtn(){
            $("a[id=prev]",$("#"+opts.barId))
              .unbind('click')
              .addClass("disabled");
        }
         /**
         * 禁止后一页按钮
         */
        function forbidNextBtn(){
           $("a[id=next]",$("#"+opts.barId))
              .unbind('click')
              .addClass("disabled");
        }


};

$.fn.PageBar.defaults = {
    pageId      : 'pageId',
    barId       : 'barId',
    pageCss     : 'pageDivCss',
    //须传参数
    id          : '',
    pageSize    : 10,
    pageNo      : 1,
    totalCount  : 0,
    load        : function() {}
};


})(jQuery);
