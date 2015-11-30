
var gridOption = {
		caption:'健康知识信息',
		colModel:[
            {label:'', name:'ma001', index:'ma001',hidden:true,hidedlg:true},
			{label:'题目', name:'ma002', index:'ma002',formatter:showcontent},
			{label:'内容', name:'ma003', index:'ma003'},
			{label:'关键字', name:'ma004', index:'ma004'},
			{label:'创建日期', name:'ma005', index:'ma005'}
		],
		formId:'searchForm',
		winTitle:'健康知识信息'
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
            $$.openDiv('showd', '健康知识详情查看',$$.getContextPath()+'knowledge/k001!doShow', {'k001PO.ma001' : ''+ getSelectedRowID("#gridTable")}, function() {
              });
        }
    }, 'operating');
		
		
    $$.bindButton('add',function(){
        var divId = 'showd',
            title = '添加记录',
            url = $$.getContextPath()+'knowledge/k001!doAdd',
            param = '',
            callback=function(){};
        $$.openDiv(divId, title, url, param, callback);
    });   
			    
    $$.bindButton('edit',function(){
        var gridId="#gridTable";
        if(!$$.checkSelRow(1)) return;
        var ids = getSelectedRowID(gridId);
        var id = ids[0];
	    var divId = 'showd',
	        title = '修改记录',
	        url = $$.getContextPath()+'knowledge/k001!doEdit',
	        param = 'k001PO.ma001='+id,
	        callback=function(){};
	    $$.openDiv(divId, title, url, param, callback);
    });	
    $$.bindButton('delete',function(){
         var gridId="gridTable";
         if ($$.checkSelRow(0)) return;
         var ids = getSelectedRowID("#"+gridId);
	     var url = $$.getContextPath()+'knowledge/k001!doGetDelete',
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
        $$.openDiv('showd', '健康知识详情查看',$$.getContextPath()+'knowledge/k001!doShow', {'k001PO.ma001' : id});
    }
}  
 
function saveK001(){
	if($('#k001Add').valid()){
		var param = $('#k001Add').serialize();
		var url = $$.getContextPath()+'knowledge/k001!doSaveAdd';
		$.post(url,param,function(data){
			showMsg(data.content, data.type, {
	  		buttons:{
	  			'确定' : function(){
	  				closeshowd();
	  				$(this).dialog('close');
	  			}}
	   		});
		});
	};
}

function modifySaveA016(){
	if($('#k001Edit').valid()){
		var param = $('#k001Edit').serialize();
		var url = $$.getContextPath()+'knowledge/k001!doSaveEdit';
		$.post(url,param,function(data){
			showMsg(data.content, data.type, {
	  		buttons:{
	  			'确定' : function(){
	  				closeshowd();
	  				$(this).dialog('close');
	  			}}
	   		});
		});
	};
}
