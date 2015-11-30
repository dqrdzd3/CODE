/**
 *角色页面js 
 */

/**
 *提交添加角色数据 
 */
function doSubmitAddData(){
    if(!$("#addRole").valid()){
        return;
    }
    
    var url = 'manager/sysrole!doSaveAdd';
    var param = $('#addRole').serialize();
    
    $.post(url,param,function(data){
        var gridId = 'gridTable';
        var divId = 'roleDiv';
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
 *提交修改角色的数据 
 */
function doSubmitEditData(){
    if(!$("#editRole").valid()){
        return;
    }
    
    var url = 'manager/sysrole!doSaveEdit';
    var param = $('#editRole').serialize();
    
    $.post(url,param,function(data){
        var gridId = 'gridTable';
        var divId = 'roleDiv';
        
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
 *删除角色的数据 
 */
function doDelete(){
    var gridId = "gridTable";
    if ($$.checkSelRow(0)) return;
    var ids = getSelectedRowID("#"+gridId);
    
    var tag = false;
    $.each(ids, function(key, value){
    	if(value == 'c909c4e6-1245-41c5-9b8a-42729fcda0e9'){
    		tag = true;
    	}
	});
    
    if(tag){
    	showMsg("企业管理员不能删除！",1,"");
    	return;
    }
    
    
    var url = 'manager/sysrole!doDelete',
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
     
     // $.post(url,param,function(data){
             // callback(data);
         // });
         
  showMsg('确定要删除所选的记录吗？',MSG.WARNING,{buttons:{
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
 *业务授权 
 */
function assignPermission(){

    if (!$$.checkSelRow(1))
        return;
    var ids = getSelectedRowID("#gridTable");
    var roleId = ids[0];
    
    var divId = 'roleDiv',
        title = '业务授权',
        url = 'manager/sysrole!doAssignPermi',
        param = 'sysRolePO.UUID=' + roleId,
        callback=function(){};
    $$.openDiv(divId, title, url,param, callback); 

}
/**
 * 角色分配
 */
function assignRole(){

    if (!$$.checkSelRow(1))
        return;

    var ids = getSelectedRowID("#gridTable");
    var roleId = ids[0];
    
    var divId = 'roleDiv',
        title = '角色分配',
        url = 'manager/sysrole!doAssignUser',
        param = 'roleId='+roleId;
        callback=function(){};
    $$.openDiv(divId, title, url, param, callback); 

}



/**
 * 提交用户角色数据
 */
function doSubmitUserData(){
    var ids = $('#userList').jqGrid('getGridParam','selarrrow');
    if(ids.length > 0){
        
        var url = 'manager/sysrole!doSaveAssignUser',
            param = 'ids='+ids.join()+'&roleId='+$("#roleUserTb #roleId").val(),
            callback = function(data){
                var gridId = 'gridTable';
                var divId = 'roleDiv';
                var buttons = {
                        '确定':function(){
                            reloadGrid(gridId);
                            $(this).dialog('close');
                            $$.closeDiv(divId);
                        }
            };
        showMsg(data.content, data.type, {title:data.title,buttons:buttons});
        };
        $.post(url,param,callback);
    }else{
        showMsg("请选择要分配的用户!", MSG.INFO);
        return;
    }
    
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