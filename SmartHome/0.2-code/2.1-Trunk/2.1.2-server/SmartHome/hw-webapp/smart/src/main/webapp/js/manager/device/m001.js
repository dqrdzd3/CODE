var gridOption = {
        caption:'移动设备维护',
        colModel:[
            {label:'id', name:'MA001', index:'MA001',hidden:true,hidedlg:true},
            {label:'设备名称', name:'MA002', index:'MA002',
             formatter:queryDetails,formatoptions:{divId:'m001Div',action:'manager/m001!doView',idName:'m001PO.MA001',postion:'0',needURL:true}},
            {label:'设备ID', name:'MA003', index:'MA003'},
            {label:'客户端程序版本', name:'MA004', index:'MA004'},
            {label:'客户端系统平台', name:'MA005', index:'MA005'},
            {label:'客户端系统版本', name:'MA006', index:'MA006'},
            {label:'客户端型号', name:'MA007', index:'MA007'}
        ],
        formId:'searchForm',
        winTitle:'移动设备信息',
        pk:'MA001'
};

var actionOption={
    init:false
};


function addFun(){
    var divId = 'm001Div',
        title = '添加设备',
        url = 'manager/m001!doAdd',
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
         
    var divId = 'm001Div',
        title = '修改设备',
        url = 'manager/m001!doEdit',
        param='m001PO.MA001='+id,
        callback=function(){};
        
    
    $$.openDiv(divId, title, url, param, callback);
}

/**
 *删除数据 
 */
function deleteFun(){
    var gridId = "gridTable";
    if ($$.checkSelRow(0,gridId)) return;
    var ids = getSelectedRowID("#"+gridId);

    var url = 'manager/m001!doDelete',
        param = 'ids='+ids.join(),
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
 
    var divId = 'm001Div',
    title = '浏览设备',
    url = 'manager/m001!doView',
    param='',
    callback=function(){};
    
    param += 'm001PO.MA001='+id;

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
     if(!$("#addM001").valid()){
        return;
    }
    
    var url = 'manager/m001!doSaveAdd';
    var param = $('#addM001').serialize();
    
    $.post(url,param,function(data){
        var gridId = 'gridTable';
        var divId = 'm001Div';
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
    if(!$("#editM001").valid()){
        return;
    }
    
    var url = 'manager/m001!doSaveEdit';
    var param = $('#editM001').serialize();
    
    $.post(url,param,function(data){
        var gridId = 'gridTable';
        var divId = 'm001Div';
        
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
