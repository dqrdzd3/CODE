var gridOption = {
    id:'gridTable',
    formId:'searchForm',
    caption:'法律法规',
    colModel:[
        {label:'主键ID', name:'ma001',hidden:true, index:'ma001',hidedlg:true},          
        {label:'法律名称', name:'ma002', index:'ma002',formatter:showcontent},
        {label:'类别', name:'ma004', index:'ma004'},
        {label:'发布日期', name:'ma005d', index:'ma005d'},
        {label:'发布机构', name:'ma007', index:'ma007'}
    ],
    winTitle:'法律法规'
};

var actionOption ={init:false };

$(function(){
    $$.bindButton('view',function() {
        var i = getSelectedRowID("#gridTable").length;
        if (i == 0) {
            showMsg("请选择一条记录!", 2);
            return;
        } else if (i > 1) {
            showMsg("请选择一条记录!", 2);
            return;
        } else {
            $$.openDiv('showd', '法律法规详情查看',$$.getContextPath()+'knowledge/k008!doGetShowById', {'k008PO.ma001' : ''+ getSelectedRowID("#gridTable")}, function() {
              });
        }
    }, 'operating');
		
		
    $$.bindButton('add',function(){
        var divId = 'showd',
            title = '添加记录',
            url = $$.getContextPath()+'knowledge/k008!add',
            param = '',
            callback=function(){};
        $$.getFrame('rightFrame').Tabs.openTab(divId+(Math.floor(Math.random()*11)), title, url);
    });   
			    
    $$.bindButton('edit',function(){
        var gridId="#gridTable";
        if(!$$.checkSelRow(1)) return;
        var ids = getSelectedRowID(gridId);
    	var id = ids[0];
        var divId = 'showd',
            title = '修改法律法规',
            url = $$.getContextPath()+'knowledge/k008!edit?k008PO.ma001='+id,
            callback=function(){};
        $$.getFrame('rightFrame').Tabs.openTab(divId+(Math.floor(Math.random()*11)), title, url);
        
    });	
    $$.bindButton('delete',function(){
         var gridId="gridTable";
         if ($$.checkSelRow(0)) return;
         var ids = getSelectedRowID("#"+gridId);
	     var url = $$.getContextPath()+'knowledge/k008!doGetDelete',
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
	         
    	showMsg('确定要删除所选的记录吗？',4,{buttons:{
             '取消':function(){
                 $(this).dialog('close');
             },
             '确定':function(){
                 $.post(url,param,function(data){
                         callback(data);
                     });
                 
             }
         }});
	     
    });	
});

function closeshowd(){
	$("#showd").dialog('close');
} 



function showcontent(celv, opts, obj) {
    return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+ obj[0] + '\')">' + celv + '</a>';
}

function showContentById(id) {
    if (id == null || id == '') {
        showMsg("请选择一条记录!", 2);
    } else {
        $$.openDiv('showd', '法律法规详情查看',$$.getContextPath()+'knowledge/k008!doGetShow', {'k008PO.ma001' : id});
    }
}  



