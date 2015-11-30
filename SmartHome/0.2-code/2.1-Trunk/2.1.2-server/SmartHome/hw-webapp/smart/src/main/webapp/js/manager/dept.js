/**
 * 部门js
 * @author:杜群星
 * 2013-04-27 17:00 
 */
/**
 *提交添加模块的数据 
 */
function doSubmitAddData(){
    if(!$("#addDeptForm").valid()){
        return;
    }
    var url = 'manager/department!doSaveAdd';
    var param = $('#addDeptForm').serialize();
    
    $.post(url,param,function(data){
        var gridId = 'gridTable';
        var divId = 'deptDiv';
        var buttons = {
                '确定':function(){
                    if(data.map.deptPO){
                        var id = data.map.deptPO.uuid;
                        var pId = data.map.deptPO.parent_uuid;
                        var name = data.map.deptPO.depart_name;
                        var node = {id:id,pId:pId,name:name,iconSkin:'pIcon'};
                        window.parent.frames['depttree'].addNodeFun(node);
                    }
                    $(this).dialog('close');
                    closeDiv(divId,gridId);
                }
            };
        showMsg(data.content, data.type, {title:data.title,buttons:buttons});
    });
}

/**
 *提交修改模块的数据 
 */
function doSubmitEditData(){
    if(!$("#editDeptform").valid()){
        return;
    }
    
    var url = 'manager/department!doSaveEdit';
    var param = $('#editDeptform').serialize();
    
    $.post(url,param,function(data){
        var gridId = 'gridTable';
        var divId = 'deptDiv';
        
        var buttons = {
                '确定':function(){
                    if(data.map.deptPO){
                        var id = data.map.deptPO.uuid;
                        var pId = data.map.deptPO.parent_uuid;
                        var name = data.map.deptPO.depart_name;
                        var node = {id:id,pId:pId,name:name};
                        window.parent.frames['depttree'].updateNodeFun(node);
                    }
                    $(this).dialog('close');
                    closeDiv(divId,gridId);
                }
            };
        showMsg(data.content, data.type, {title:data.title,buttons:buttons});
    });
}
