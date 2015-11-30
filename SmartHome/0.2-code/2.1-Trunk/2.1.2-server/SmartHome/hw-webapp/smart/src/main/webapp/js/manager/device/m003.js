var gridOption = {
        caption:'安装程序版本维护',
        colModel:[
            {label:'id', name:'MA001', index:'MA001',hidden:true,hidedlg:true},
            {label:'url', name:'ma007Id', index:'ma007Id',hidden:true,hidedlg:true},
            {label:'版本名称', name:'MA002', index:'MA002',
                formatter:queryDetails,formatoptions:{divId:'m003Div',action:'manager/m003!doView',idName:'m003PO.MA001',postion:'0',needURL:true}},
            {label:'版本代号', name:'MA003', index:'MA003'},
            {label:'版本说明', name:'MA004', index:'MA004'},
            {label:'程序名称', name:'MA007', index:'MA007',formatter:showcontent}
        ],
        formId:'searchForm',
        winTitle:'安装程序版本维护',
        pk:'MA001'
};

var actionOption={
    init:false
};


function addFun(){
    var divId = 'm003Div',
        title = '添加安装程序版本',
        url = 'manager/m003!doAdd',
        param='',
        callback=function(){};
    $$.openDiv(divId, title, url, param, callback);
}

function editFun(){
     var gridId = "gridTable";
         gridIdSelector="#"+gridId;
     if(!$$.checkSelRow(1,gridId)) return;
     var ids = getSelectedRowID(gridIdSelector);
     var id = ids[0];
         
    var divId = 'm003Div',
        title = '修改安装程序版本',
        url = 'manager/m003!doEdit',
        param='m003PO.MA001='+id,
        callback=function(){};
        
    
    $$.openDiv(divId, title, url, param, callback);
}

/**
 *删除数据 
 */
function deleteFun(){
    var gridId = "gridTable";
    if ($$.checkSelRow(0)) return;
    var ids = getSelectedRowID("#"+gridId);
    var url = 'manager/m003!doDelete',
        param = 'ids='+ids,
        callback = function(data){
            var buttons = {
                    '确定':function(){
                        reloadGrid(gridId);
                        $(this).dialog('close');
                    }
                };
            showMsg(data.content, data.type, {title:data.title,buttons:buttons});
    };
     
              
     showMsg('确定要删除所选的记录吗？',MSG.INFO,{buttons:{
         '取消':function(){
             $(this).dialog('close');
         },
         '确定':function(){
             $.post(url,param,function(data){
                     callback(data);
                 });
             
         }
         
     }});
}

function viewFun(){
     var gridId = "gridTable";
         gridIdSelector="#"+gridId;
     if(!$$.checkSelRow(1,gridId)) return;
     var ids = getSelectedRowID(gridIdSelector);
     var id = ids[0];
 
    var divId = 'm003Div',
    title = '浏览安装程序版本',
    url = 'manager/m003!doView',
    param='',
    callback=function(){};
    
    param += 'm003PO.MA001='+id;

    $$.openDiv(divId, title, url, param, callback);
}

function closeDiv (divId,gridId) {
  var divId = divId || 'operating_input_form';
  var gridId = gridId || 'gridTable';
  
  $$.closeDiv(divId);
  $("#"+divId).empty();
  reloadGrid(gridId);
}

/**
 *提交添加的数据 
 */
function doSubmitAddData(){
     if(!$("#addM003").valid()){
        return;
    }
    
    var url = 'manager/m003!doSaveAdd';
    var param = $('#addM003').serialize();
    
    $.post(url,param,function(data){
        var gridId = 'gridTable';
        var divId = 'm003Div';
        var buttons = {
                '确定':function(){
                    $(this).dialog('close');
                    closeDiv(divId,gridId);
                }
            };
        showMsg(data.content, data.type, {title:data.title,buttons:buttons});
    });
}

/**
 *提交修改的数据 
 */
function doSubmitEditData(){
    if(!$("#editM003").valid()){
        return;
    }
    
    var url = 'manager/m003!doSaveEdit';
    var param = $('#editM003').serialize();
    
    $.post(url,param,function(data){
        var gridId = 'gridTable';
        var divId = 'm003Div';
        
        var buttons = {
                '确定':function(){
                   $(this).dialog('close');
                   closeDiv(divId,gridId);
                }
            };
        showMsg(data.content, data.type, {title:data.title,buttons:buttons});
    });
}


$(function(){
    $$.bindButton('add',addFun);
    $$.bindButton('edit',editFun);
    $$.bindButton('delete',deleteFun);
    $$.bindButton('view',viewFun);
});


function showcontent(celv,opts,obj){
	return '<a href="javascript:download(\''+obj[1]+'\')">'+celv+'</a>';
}

function download(str){
	window.location.href  = 'upload/download?id='+str;
}
