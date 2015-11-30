/**
 * 系统资源页面js 
 * @author:杜群星
 * @date:2012-10-18
 */

/**
 *提交资源的数据 
 */
function doSubmitAddData(){
    if(!$("#addResource").valid()){
        return;
    }
    
    var url = 'manager/sysresource!doSaveAdd';
    var param = $('#addResource').serialize();
    
    $.post(url,param,function(data){
        var gridId = 'gridTable';
        var divId = 'resourceDiv';
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
 *提交修改资源的数据 
 */
function doSubmitEditData(){
    if(!$("#editResource").valid()){
        return;
    }
    
    var url = 'manager/sysresource!doSaveEdit';
    var param = $('#editResource').serialize();
    
    $.post(url,param,function(data){
        var gridId = 'gridTable';
        var divId = 'resourceDiv';
        
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
 *删除资源的数据 
 */
function doDelete(){
    var gridId = "gridTable";
    if ($$.checkSelRow(0)) return;
    var ids = getSelectedRowID("#"+gridId);
    
    for (var i=0; i < ids.length; i++) {
                     
         var isDefault = jQuery("#"+gridId).jqGrid('getCell',ids[i],'IS_DEFAULT');
         
         if(isDefault == 0){
            var buttons = {
                    '确定':function(){
                        $(this).dialog('close');
                    }
                };
                showMsg('默认资源不可删除！',MSG.INFO);
                return;
         }
    };
    
    
    var url = 'manager/sysresource!doDelete',
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
/**
 *关闭并清空层 ，刷新列表
 */
          
function closeDiv (divId,gridId) {
  var divId = divId || 'operating_input_form';
  var gridId = gridId || 'gridTable';
  
  $$.closeDiv(divId);
  $("#"+divId).empty();
  reloadGrid(gridId);
}