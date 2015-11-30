/**
 *快速查询js 
 */
var defaults = {
        id             : '',                                                                           //调用对象id
        qsId           : 'qsDiv',                                                                      //弹出层id
        qsHeardId      : 'qsHead',                                                                     //表头id
        qsContentId    : 'qsContent',                                                                  //内容id
        qsFooterId     : 'qsFooter',                                                                   //表尾id
        pageSize       : 10,
        pageNo         : 1, 
        idName         : '',                                                                           //主键字段名
        hideCols       : [],                                                                         //隐藏字段集合
        selCol         : '',                                                                           //选择的字段（当selCols为空时有效）         
        colNames       : [],                                                                         //表头字段名称集合['字段1','字段2','字段3','字段4','字段5','字段6']     
        colVals        : [],                                                                         //表头字段对应字段值['MA001','MA002','MA003','MA004','MA015','MA016']
        selCols        : [],                                                                             //要填入值的对象Id:字段名称
        param          : {},                                                                           //附加参数
        setting        : {width:'500',height:'300',x:'5',y:'5',zIndex:'100000'}                        //默认的设置
    },//默认值
    options = {},//全局属性
    jsonData = [],//json数据
    e,//事件
    key='',//调用对象的值
    id='',//调用对象id
    idSel,//调用对象的选择器
    obj,//调用对象
    qsBody,//div对象
    startRow = 1,//开始行
    endRow = 10,//结束行
    totalCount = 0,//总记录数
    undefined//未定义对象
    ;

//var qsDivCss = {zIndex: '100000', width:'500px', height:'300px',position:'relative',left:'5px',top:'5px'};
//var qsHeadCss = {height: '25px',width: '500px'};
//var qsContentCss = {height: '250px',width: '500px',overflow:'scroll'};
//var qsFooterCss = {height: '25px',width: '500px'};

function quickSearch(opt){

    init(opt);
    
    searchData();
    
    createDiv();
}

/**
 *初始化数据 
 */
function init(opt){
    //初始化参数
    $.extend(options,defaults,opt);
    setPageData();
    id = options.id;
    idSel = "#"+id;
    obj = $(idSel);
    key = $(obj).val();
    
}

/**
 *查询数据 
 * @param {Object} key
 */
function searchData(){
    var url ="performed/hdcheck!queryAllEnts",
        param = $.extend(options.param,{key:key,startRow:startRow,endRow:endRow});
        
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
    
    if(data.qsData){
        jsonData = data.qsData;
    }
    if(data.totalCount){
        totalCount = data.totalCount;
    }
    
    setData();

    fillHeadData();
    
    fillContentData();
    
    createPageBar();

    bindEvent();

    setEvenTrColor(); 

}

function onError(data){
    alert("错误信息："+data);
}


function createDiv(){
    var qsId = options.qsId;
    if(qsBody){
        $(qsBody).remove();
    }
    
    var qsAssistDiv = $(obj).closest('div');
    if(!qsAssistDiv){
        qsAssistDiv = "<div id='qsAssistDiv' style='display: none;'></div>";
        $(obj).wrap(qsAssistDiv);
    }
    
    //var position = $(obj).position();
    
    qsBody = $("<div id='"+qsId+"'></div>")
                .addClass('all')
                .insertAfter(qsAssistDiv)
                .show();
                
     $("<div id='"+options.qsHeardId+"'></div>")
        .addClass('tablehead')
        .appendTo(qsBody);
     $("<div id='"+options.qsContentId+"'></div>")
        .addClass('tablecontent')
        .appendTo(qsBody);
     $("<div id='"+options.qsFooterId+"'></div>")
        .append('<div id="pageDiv" class="pageDivCss"></div>')
        .appendTo(qsBody);
     
     $("#"+options.qsHeardId).append("<table width='540' border='0' cellspacing='0' cellpadding='0' class='tableword'><thead></thead></table>");
     $("#"+options.qsContentId).append("<table width='540' border='0' cellspacing='0' cellpadding='0' class='tableword'><tbody></tbody></table>");
              
    
}

/**
 *填充表头数据 
 */
function fillHeadData(){
    var qsHeadObj = $(qsBody).find("div[id="+options.qsHeardId+"] table thead");
    
    if(qsHeadObj){
        $(qsHeadObj).empty();
    }
    
    //添加表头     
    var qsHeadTrObj = $("<tr id='qsHead_tr'></tr>").appendTo($(qsHeadObj));
    
    //填充表头字段
    $.each(options.colNames, function(i, n) {
        var flag = options.hideCols[i] != undefined ? options.hideCols[i] : true ;
        var display = flag == true ? '' : 'hide';
        $(qsHeadTrObj).append("<th class='"+display+"'>"+n+"</th>");
    });       
    
}
/**
 *填充内容数据 
 */
function fillContentData(){
    
    var qsContentObj = $(qsBody).find("div[id="+options.qsContentId+"] table tbody");
    
    if(qsContentObj){
        $(qsContentObj).empty();
    }
    
    $.each(jsonData, function(i, n) {
        //添加内容行
//        var qsContentTrObj = $("<tr id='qsConten_tr_"+i+"' onclick='onClickFun()' onmousemove=$(this).addClass('focuscolor') onmouseout=$(this).removeClass('focuscolor') ></tr>").appendTo($(qsContentObj));
        var qsContentTrObj = $("<tr id='qsConten_tr_"+i+"' ></tr>").appendTo($(qsContentObj));
        //填充内容行数据
        $.each(options.colVals, function(j, m) {
            var flag = options.hideCols[j] != undefined ? options.hideCols[j] : true ;
            var display = flag == true ? '' : 'hide';
            $(qsContentTrObj).append("<td class='"+display+"'>"+n[m]+"</td>");
        });
        //存储数据到行dom
         $(qsContentTrObj).data("trData",n);
    });  
}

function bindEvent(){
    $("div[id="+options.qsContentId+"] table tbody tr")
        .unbind()
        .bind({
             click:onClickFun,
             mouseout:onMouseOutFun,
             mousemove:onMouseMoveFun
         })
        .on('dealdata',dealData)
        .on('onscroll',onScroll);
    
    $(obj).unbind()
          .bind({
                keydown:onKeyDownFun,
                blur:onObjBlurFun
           });
    
    $(qsBody).unbind()
             .bind('mouseout',function() {
                 //关闭快速查询层
                 closeQS();
            });
    
}

function onObjBlurFun(e){
}

function onClickFun(){
    $(this).trigger('dealdata');
}
function onFocusFun(){
    $(this).siblings('tr')
           .removeClass('focuscolor')
           .end()
           .addClass('focuscolor');
}

function onBlurFun(){
    alert(1);

}

function onMouseOutFun(){
    $(this).removeClass('focuscolor');
}
function onMouseMoveFun(){
    $(this).addClass('focuscolor');
}

function onKeyDownFun(e) {
    if (e.keyCode == 13) {
        reload();
    }
}




function onScroll(e){
    var type = e.type;
    var keyCode = e.target.accessKey;
    alert(type);
    alert(keyCode);
    alert(e.keyCode);
}

/**
 * 已过时 
 */
function onChangeFun(){
    key = $(obj).val();
    
    $(qsBody).find("div[id="+options.qsContentId+"] table tbody").empty();
    
    var matchArr = [];
    
    if(jsonData){
        
        
        //抽取匹配行，隐藏字段匹配时不抽取
        $.each(jsonData, function(i, n) {
            
            $.each(options.colVals, function(j, m) {
                   var colVal = n[m];
                   var regx = new RegExp("^"+key);
                   if(regx.test(colVal) && m != options.idName && options.hideCols[j]){
                       matchArr.push(n);
                       return false;
                   }
            });
            
        });    
        
     }
     
     //重新生成内容行
    $.each(matchArr, function(k, o) {
        
        var qsContentTrObj = $("<tr id='qsConten_tr_"+k+"'></tr>").appendTo($(qsBody).find("div[id="+options.qsContentId+"] table tbody"));
        $.each(options.colVals, function(l, p) {
                var flag = options.hideCols[l] != undefined ? options.hideCols[l] : true ;
                var display = flag == true ? '' : 'hide';
                $(qsContentTrObj).append("<td class='"+display+"'>"+o[p]+"</td>");
        });
        $(qsContentTrObj).data("trData",o);
      
    });
    
    //绑定事件
    bindEvent();
}


function reload(){
    //重新获取对象值
    setKey();
    
    setPageData();
    
    searchData();
    
}

/**
 *设置偶数行颜色 
 */
function setEvenTrColor(){
     $(qsBody).find("div[id="+options.qsContentId+"] table tbody tr:even").addClass("evencolor");
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
    startRow = (options.pageNo-1)*options.pageSize+1;
    endRow = options.pageNo*options.pageSize;  
}

/**
 *设置调用对象的值 
 */
function setKey(){
    key = $(obj).val();
}

/**
 * 分页加载数据 
 */
function load(pageOpt){
    $.extend(options, pageOpt);
    reload();
}

function loadPage(val){
    load({pageNo:val});
}

/**
 *关闭快速查询弹出层 
 */
function closeQS(){
   if(qsBody){
       $(qsBody).remove();
   }
}

/**
 *创建分页工具栏 
 * <span class="disabled"> <  前一页</span>
 * <span class="current">1</span>
 * <a href="javascript:;">2</a>
 * ...
 * <a href="javascript:;">199</a><a href="javascript:;">200</a>
 * <a href="javascript:;" onclick="load({pageNo:2})">后一页  > </a>
 */
function createPageBar(){
    var _pageSize = parseInt(options.pageSize || 10);
    var _pageNo = parseInt(options.pageNo || 1);
    var _totalCount = parseInt(totalCount || 700);
    var _pageNum = parseInt(_totalCount%_pageSize == 0 ? _totalCount/_pageSize : _totalCount/_pageSize+1);
    
    var pagePrev = _pageNo > 1 ? _pageNo-1 : 1;
    var pageNext = _pageNo < _pageNum ? _pageNo+1 : _pageNum;  
    
    var prefixStr = "<a id='prev' href='javascript:;'><  前一页</a>";
    var suffixStr = "<a id='next' href='javascript:;'>后一页  > </a>";
    var str = "";
    
    if(_pageNum > 5){
        for (var i = 1; i < 3; i++) {
            if(_pageNo == i){
                str +="<a id='page"+i+"' class='current' href='javascript:;' onclick='loadPage("+i+")'>"+i+"</a>";
            }else{
                str +="<a id='page"+i+"' href='javascript:;' onclick='loadPage("+i+")'>"+i+"</a>";
            }
        };
        
        if(_pageNo == 1 || _pageNo == _pageNum){
            str +="......";
        }
        
        if(_pageNo == 3){
            str +="<a id='page"+_pageNo+"' class='current' href='javascript:;' onclick='loadPage("+_pageNo+")'>"+_pageNo+"</a>";
            str +="......";
        }
        
        if(_pageNo > 3 && _pageNo < _pageNum-2){
            str +="...";
            str +="<a id='page"+_pageNo+"' class='current' href='javascript:;' onclick='loadPage("+_pageNo+")'>"+_pageNo+"</a>";
            str +="...";
        }
        
        if(_pageNo == _pageNum-2){
            str +="......";
            str +="<a id='page"+_pageNo+"' class='current' href='javascript:;' onclick='loadPage("+_pageNo+")'>"+_pageNo+"</a>";
        }
        
        for (var j = _pageNum -1; j < _pageNum+1; j++) {
            if(_pageNo == j){
                str +="<a id='page"+j+"' class='current' href='javascript:;' onclick='loadPage("+j+")'>"+j+"</a>";
                
            }else{
                str +="<a id='page"+j+"' href='javascript:;' onclick='loadPage("+j+")'>"+j+"</a>";
                
            }
        };
        
    }else{
        
        for (var i=1; i < _pageNum+1; i++) {
            if(_pageNo == i){
                str +="<a id='page"+i+"' class='current' href='javascript:;' onclick='loadPage("+i+")'>"+i+"</a>";
            }else{
                str +="<a id='page"+i+"' href='javascript:;' onclick='loadPage("+i+")'>"+i+"</a>";
            }
        };
    }    
        var pageBar = $(prefixStr+str+suffixStr);
    
    
    if($("div[id=pageDiv]",qsBody).length > 0){
        $("div[id=pageDiv]",qsBody).empty();
    }
    
    $(pageBar).appendTo($("div[id=pageDiv]",qsBody));
    
    $(qsBody).find("a[id=prev]")
               .bind('click', function() {
                  loadPage(pagePrev);
               })
               .end()
               .find("a[id=next]")
               .bind('click', function() {
                   loadPage(pageNext);
               });
    
    if(_pageNo == 1){
        $("a[id=prev]",qsBody)
                  .unbind('click')
                  .addClass("disabled");
    }
    
    if(_pageNo == _pageNum){
       $("a[id=next]",qsBody)
                  .unbind('click')
                  .addClass("disabled");
    }
}
