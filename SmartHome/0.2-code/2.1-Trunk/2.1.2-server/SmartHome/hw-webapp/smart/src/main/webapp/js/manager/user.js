/**
 *用户页面js 
 */
		var ztreeSetting = {
			view: {
				showLine: false
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};


/**
 *提交添加用户数据 
 */
function doSubmitAddData(){
    if(!$("#addUser").valid()){
        return;
    }
    
    var url = 'manager/sysuser!doSaveAdd';
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
 *提交修改用户的数据 
 */
function doSubmitEditData(){
    if(!$("#editUser").valid()){
        return;
    }
    
    var url = 'manager/sysuser!doSaveEdit';
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
 *删除用户的数据 
 */
function doDelete(){
    var gridId = "gridTable";
    if ($$.checkSelRow(0)) return;
    var ids = getSelectedRowID("#"+gridId);
    
    
    var url = 'manager/sysuser!doDelete',
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
 *业务授权 
 */
function assignPermission(){

    if (!$$.checkSelRow(1))
        return;
    var ids = getSelectedRowID("#gridTable");
    var userId = ids[0];
    
    var divId = 'userDiv',
        title = '用户授权',
        url = 'manager/sysuser!doAssignRole',
        param = 'userId=' + userId,
        callback=function(){};
    $$.openDiv(divId, title, url,param, callback); 

}

/**
 *重置密码 
 */
function reSetPassWord(){
    var gridId = "gridTable";
    if ($$.checkSelRow(0)) return;
    var ids = getSelectedRowID("#"+gridId);
    
    
    var url = 'manager/sysuser!doReSetPassWord',
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
     
     showMsg("确定要重置密码吗？",MSG.QUESTION,{buttons:{
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
    $("#treeBtn").show();
    $("#orglist").attr("title","");
    $("#orglist").val("");
    $("#treeBtn").val("选择");
    if(val == 'SYS'){
        $("#userTb #orgInfoTr").css("display","none");
        $("#isAdmin").val('0');
        $("#orglist").attr("title","SYS");
        //queryOrgList(val);
    }
    if(val == 'ENT'){
        $("#userTb #orgInfoTr").css("display","");
        $("#isAdmin").val('1');
        $("#orglist").attr("title","ENT");
        //queryOrgList(val);
    }
    if(val == 'GOV'){
        $("#userTb #orgInfoTr").css("display","");
        $("#isAdmin").val('1');
        $("#orglist").attr("title","GOV");
        //queryOrgList(val);
    }
    
}

//选择
function selectName(){
	 var id = $("#orglist").attr("title");
	 var title,url,param='',callback = function(){};
	 if(id == null || id ==""){
		 showMsg("请先选择用户类型",5);
		 return; 
	 }else{
		 if(id == 'GOV'){
			 title = '政府组织机构信息';
		 }
		 if(id == 'ENT'){
			 title = '企业组织机构信息';
		 }else{
			 title = '系统组织机构信息';
		 } 
	 }
	 url = $$.getContextPath()+'manager/sysuser!doGetXZ?selectType='+id,
	 $$.openDiv('xzDiv',title,url,param,callback);	 
}

/**
 *企业政府联动 
 */
/*function queryOrgList(val){
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
                    	jQuery.fn.zTree.init($("#orglist"), ztreeSetting,data); 
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
    
}*/






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
