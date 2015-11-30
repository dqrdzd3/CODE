/**
 * 系统模块操作页面js 
 * @author:杜群星
 * @date:2012-10-15
 */
/**
 *提交添加模块的数据 
 */
function doSubmitAddData(){
    
    if(!$("#addModule").valid()){
        return;
    }
    
    var url = 'manager/sysmodule!doSaveAdd';
    var param = $('#addModule').serialize();
    
    $.post(url,param,function(data){
        var gridId = 'gridTable';
        var divId = 'moduleDiv';
        var buttons = {
                '确定':function(){
                    if(data.map.sysModulePO){
                        var id = data.map.sysModulePO.UUID;
                        var pId = data.map.sysModulePO.PARENT_UUID;
                        var name = data.map.sysModulePO.MENU_NAME;
                        var isParent = data.map.sysModulePO.MENU_TYPE=='10'?true:false; 
                        var node = {'id':id,'pId':pId,'name':name,'isParent':isParent};
                        
                        if(node.isParent){
                            window.parent.frames['moduletree'].addNodeFun(node);
                        }
                    }
                    $(this).dialog('close');
                    closeDiv(divId);
                }
            };
        showMsg(data.content, data.type, {title:data.title,buttons:buttons});
    });
}

/**
 *提交修改模块的数据 
 */
function doSubmitEditData(){
    if(!$("#editModule").valid()){
        return;
    }
    
    var url = 'manager/sysmodule!doSaveEdit';
    var param = $('#editModule').serialize();
    
    $.post(url,param,function(data){
        var gridId = 'gridTable';
        var divId = 'moduleDiv';
        
        var buttons = {
                '确定':function(){
                    if(data.map.sysModulePO){
                        var id = data.map.sysModulePO.UUID;
                        var pId = data.map.sysModulePO.PARENT_UUID;
                        var name = data.map.sysModulePO.MENU_NAME;
                        var isParent = data.map.sysModulePO.MENU_TYPE=='10'?true:false; 
                        var node = {'id':id,'pId':pId,'name':name,'isParent':isParent};
                        if(node.isParent){
                            window.parent.frames['moduletree'].updateNodeFun(node);
                        }
                    }
                    $(this).dialog('close');
                    closeDiv(divId);
                }
            };
        showMsg(data.content, data.type, {title:data.title,buttons:buttons});
    });
}

/**
 *删除模块的数据 
 */
function doDelete(){
    var gridId = "gridTable";
    if ($$.checkSelRow(0)) return;
    var ids = getSelectedRowID("#"+gridId);

    var url = 'manager/sysmodule!doDelete',
        param = 'ids='+ids.join(),
        callback = function(data){
            var buttons = {
                    '确定':function(){
                        if(data.map.ids){
                            var ids = data.map.ids;
                            
                            var nodes = {'ids':ids};
                            window.parent.frames['moduletree'].removeNodeFun(nodes);
                        }
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
 *动态添加操作行  
 */
function doAddOperation(){
    var maxNum = 0;
    $.each($("#operTb tr:not(:first)"), function(i, obj) {
      var num = $(obj).find("td").first().text();
      if(parseInt(num)>parseInt(maxNum)){
          maxNum = num;
      }
    });
    
    var cloneRow = $("#operTb thead tr:last").clone(true).css('display','');
    
    $(cloneRow).find('input[type=radio]').attr('name','is_valid_'+(parseInt(maxNum)+1));
    $(cloneRow).find('td').first().text((parseInt(maxNum)+1));
    
    $("#operTb tbody").append(cloneRow);
}
/**
 *删除行 
 */
function doDeleteRow(obj){
    
    $(obj).closest("tr").remove();
    
}

/**
 *切换操作表显示 
 */
function doHideOperTb(obj){
   var val = $(obj).val();
   
   if(val == '10'){
       $("#operTb").css('display','none');
       $("ul li").filter(".addbutton").css('display','none');
   }
   if(val == '20'){
       $("#operTb").css('display','');
       $("ul li").filter(".addbutton").css('display','');
   }
}

/**
 *查询所有操作进行修改 
 */
function doRetrieveOperations(){
    var gridId = "gridTable";
    if (!$$.checkSelRow(1)) return;
    var ids = getSelectedRowID("#"+gridId);
    var id = ids[0];
    
    var MENU_TYPE = jQuery("#"+gridId).jqGrid('getCell',id,'MENU_TYPE');
    
    if(MENU_TYPE == '10'){
        var buttons = {
            '确定':function(){
                $(this).dialog('close');
            }
        };
        showMsg('目录类型不允许进行业务操作！',MSG.INFO);
        return;
    }
    
    var divId = 'moduleDiv',
        title = '操作列表',
        url = 'manager/sysoperation!doList',
        param = 'MENU_UUID='+id;
        callback=function(){};
        
    
    $$.openDiv(divId, title, url, param, callback);
}

/**
 *提交操作列表数据 
 */
function doSubmitOperData(){
    if(!$("#operList").valid()){
        return;
    }
    
    var url = 'manager/sysoperation!doSaveAdd';
    var param = $('#operList').serialize();
    
        param += '&MENU_UUID='+$('#menu_uuid').val();
    
    $.post(url,param,function(data){
        var gridId = 'gridTable';
        var divId = 'moduleDiv';
        var buttons = {
                '确定':function(){
                    $(this).dialog('close');
                    closeDiv(divId);
                }
            };
        showMsg(data.content, data.type, {title:data.title,buttons:buttons});
    });
    
    
}

/**
 *检查父节点类型，授权人类型必须与父节点类型保持一致 
 */
function checkPNodeType(obj){
    var val = $(obj).val();
    
    var type =  window.parent.frames['moduletree'].checkNodeType();
    if(type){
         if(type != val){
             showMsg('授权人类别必须与父模块的类别相同，\r\n已自动处理！',MSG.INFO);
             $(obj).find("option[value='"+type+"']").attr('selected',true);
             $(obj).find("option[value!='"+type+"']").remove();
             return;
         }
    }
}

/**
 *关闭并清空层 ，刷新列表
 */
          
function closeDiv(divId,gridId){
  var divId = divId || 'operating_input_form';
  var gridId = gridId || 'gridTable';
  $$.closeDiv(divId);
  $("#"+divId).empty();
  reloadGrid(gridId);
}
