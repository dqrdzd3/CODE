/**
 *企业/政府用户页面js 
 */

/**
 *提交添加企业/政府用户数据 
 */
function doSubmitOrgAddData(){
    if(!$("#addUser").valid()){
        return;
    }
    
    var url = 'manager/orguser!doSaveAdd';
    var param = $('#addUser').serialize();
    
    $.post(url,param,function(data){
        var gridId = 'gridTable';
        var divId = 'userDiv';
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
 *提交修改企业/政府用户的数据 
 */
function doSubmitOrgEditData(){
    if(!$("#editUser").valid()){
        return;
    }
    
    var url = 'manager/orguser!doSaveEdit';
    var param = $('#editUser').serialize();
    
    $.post(url,param,function(data){
        var gridId = 'gridTable';
        var divId = 'userDiv';
        
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
 *删除企业/政府角色的数据 
 */
function doOrgDelete(){
    var gridId = "gridTable";
    if ($$.checkSelRow(0)) return;
    var ids = getSelectedRowID("#"+gridId);
    
    
    var url = 'manager/orguser!doDelete',
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
 *企业/政府用户的业务授权 
 */
function assignOrgPermission(){

    if (!$$.checkSelRow(1))
        return;
    var ids = getSelectedRowID("#gridTable");
    var userId = ids[0];
    
    var divId = 'userDiv',
        title = '用户授权',
        url = 'manager/orguser!doAssignRole',
        param = 'userId=' + userId,
        callback=function(){};
    $$.openDiv(divId, title, url,param, callback); 

}

/**
 *企业/政府用户的重置密码 
 */
function reSetOrgPassWord(){
    var gridId = "gridTable";
    if ($$.checkSelRow(0)) return;
    var ids = getSelectedRowID("#"+gridId);
    
    
    var url = 'manager/orguser!doReSetPassWord',
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
         // });     showMsg("确定要重置密码吗？",MSG.QUESTION,{buttons:{
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
 *处理用户类型改变事件 
 */
function doChangeUserType(obj){
    var val = $(obj).val();
    
    if(val == 'SYS'){
        $("#userTb #orgInfoTr").css("display","none");
        queryOrgList(val);
    }
    if(val == 'ENT'){
        $("#userTb #orgInfoTr").css("display","");
        queryOrgList(val);
    }
    if(val == 'GOV'){
        $("#userTb #orgInfoTr").css("display","");
        queryOrgList(val);
    }
    
}

/**
 *企业政府联动 
 */
function queryOrgList(val){
    var url = '',
        param = '',
        callback=function(data){
            var data = $.parseJSON(data);
            $("#userTb #orglist").find("option").remove();
            if(data.length>0){
                for(var i in data){
                    var orgId = '',orgName='';
                    if(val == 'ENT'){
                         orgId = data[i].MA001;
                         orgName = data[i].MA002;
                    }
                    if(val == 'GOV'){
                         orgId = data[i].ma001;
                         orgName = data[i].ma003;
                    }
                    if(orgId == window.s_orgId){
                        $("#userTb #orglist").append("<option value='"+orgId+"' selected='selected'>"+orgName+"</option>");
                    }
                }
            }
        };
    if(val == '' || val == 'SYS'){
        $("#userTb #orglist").find("option").remove();
        $("#userTb #employeeId").val('');
        return;
    }
    if(val == 'ENT'){
        url = 'register/c001!queryAllValidInstanceToJson';
    }
    if(val == 'GOV'){
        url = 'gov/c002!queryAllValidInstanceToJson';
    }
    
    $.post(url,param,callback);
    
}

/**
 *移动设备列表页面 
 */
function queryME(){

    if (!$$.checkSelRow(1))
        return;
    var ids = getSelectedRowID("#gridTable");
    var userId = ids[0];
    
    var orgId = jQuery('#gridTable').jqGrid('getCell',userId,'ORGAN_UUID');
    var isMEAccess = jQuery('#gridTable').jqGrid('getCell',userId,'MOBILE_DEVICE_ACCESS');
    if(isMEAccess == 0){
         showMsg("此用户不支持手持设备！",MSG.WARNRING,{buttons:{
             '确定':function(){
                $(this).dialog('close');
             }
         
         }});
                return;
    }
    
    
    var divId = 'MEDiv',
        title = '移动设备绑定',
        url = 'manager/m001!doQueryME',
        param = 'userId=' + userId+ '&orgId=' + orgId,
        callback=function(){};
    $$.openDiv(divId, title, url, param, callback); 
}

function bindME(obj){
    
    var userId = $(obj).attr('value').split('|')[0],
        deviceId = $(obj).attr('value').split('|')[1];
        val = $(obj).attr('isBound');
        
    var url = 'manager/m001!doSaveBound',
    param = 'isBound='+val+'&userId='+userId+'&deviceId='+deviceId,
    callback=function(data){
        if(data == 'true' && val == 1){
            $(obj).attr('isBound','0');
            $(obj).text('解除');
            $(obj).parent().siblings("td[id=isValid]").html("<a href=\"javascript:;\"  value=\""+userId+"|"+deviceId+"\" isValid=\"0\" onclick=\"enableME(this);\">禁用</a>");
            
        }
        if(data == 'true' && val == 0){
            $(obj).attr('isBound','1');
            $(obj).text('绑定');
            $(obj).parent().siblings("td[id=isValid]").html('');
        }
    };
    $.post(url,param,callback,'text');//修改请求数据类型为'text'
}
function enableME(obj){
    
    var userId = $(obj).attr('value').split('|')[0],
        deviceId = $(obj).attr('value').split('|')[1];
        val = $(obj).attr('isValid');
        
    var url = 'manager/m001!doSaveValid',
    param = 'isValid='+val+'&userId='+userId+'&deviceId='+deviceId,
    callback=function(data){
        if(data == 'true' && val == 1){
            $(obj).attr('isValid','0');
            $(obj).text('禁用');
        }
        if(data == 'true' && val == 0){
            $(obj).attr('isValid','1');
            $(obj).text('启用');
        }
    };
    $.post(url,param,callback,'text');//修改请求数据类型为'text'
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