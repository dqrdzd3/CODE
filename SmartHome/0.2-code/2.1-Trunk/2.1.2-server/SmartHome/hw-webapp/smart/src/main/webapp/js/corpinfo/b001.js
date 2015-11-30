var audialog=null;
var gridOption = {
		caption:'检查内容信息列表',
		colModel:[
	        {name:"ma001", index:"ma001",hidden:true},
			{label:'内容名称', name:"ma005", index:"ma005"},
			{label:'检查内容', title:'ma006title', name:'ma006', index:'ma006'},
			{label:'所属大类', name:'ltype', index:'ltype'},
			{label:'所属中类', name:'mtype', index:'mtype'},
			{label:'所属小类', name:'stype', index:'stype'},
			{label:'检查要求', title:'ma007title', name:'ma007', index:'ma007'},
			{label:'依据条款', title:'ma008title', name:'ma008', index:'ma008'},
			{label:'依据条款内容', title:'ma009title', name:'ma009', index:'ma009'},
			{label:'处罚条款', title:'ma010title', name:'ma010', index:'ma010'},
			{label:'处罚条款内容', title:'ma011title', name:'ma011', index:'ma011'}
		],
		pk:'ma001',
		id:'s004table',
		url:$$.getContextPath()+'safety/s004!queryData.action?ran='+Math.random(),
		formId:'searchForm'
};

var actionOption={
		id:'optionbuttons',
		gridId:'s004table',
		actionUrl:'safety/s004',
		winTitle:'检查内容信息',
		buttonInited:function(){
			$$.bindButton('edit',function(){
				 var i=getSelectedRowID('#s004table').length;
				 if(i==0){
					 showMsg("请选择需要修改的记录!",5,"");
					 return;
				 }else if(i>1){
					 showMsg("只能修改一条记录",5,"");
					 return;
				 }else{
					 $$.openDiv('showdia','检查内容修改',$$.getContextPath()+'safety/s004!doEdit',{'s004Map.MA001':''+getSelectedRowID('#s004table')});
				 }
			},'optionbuttons');	
			
			$$.bindButton('view',function(){
				 var i=getSelectedRowID('#s004table')length;
				 if(i==0){
					 showMsg("请选择需要查看的记录!",5,"");
					 return;
				 }else if(i>1){
					 showMsg("只能查看一条记录",5,"");
					 return;
				 }else{
					 $$.openDiv(
						 'showdia',
						 '检查内容查看',
						 $$.getContextPath()+'safety/s004!doEdit',
						 {'s004Map.MA001':''+getSelectedRowID('#s004table')},
						 function(){
							 $$.clearInput('#showdia');
							 jQuery('.save').hide();
						 }
					);
				 }
				
			},'optionbuttons');	
		}
};
///////查询条件操作
/**
 * 大类选项改变
 */
function qlsc(){
	jQuery('#qsts').html('<option>请选择</option>');
	jQuery.post(
		$$.getContextPath()+'safety/s004!getmtypeList',
		{ltypeId:jQuery('#qlts').val()},
		function(option){
			alert(option);
			jQuery('#qmts').html(option);
		}
	);
}

/**
 * 中类选项改变
 */
function qmsc(){
	jQuery.post(
		$$.getContextPath()+'safety/s004!getstypeList',
		{mtypeId:jQuery('#qmts').val()},
		function(option){
			jQuery('#qsts').html(option);
		}
	);
}

///////新增修改表单操作
/**
 * 大类选项改变
 */
function lsc(){
	jQuery('#sts').html('<option>请选择</option>');
	jQuery.post(
		$$.getContextPath()+'safety/s004!getmtypeList',
		{ltypeId:jQuery('#lts').val()},
		function(option){
			jQuery('#mts').html(option);
		}
	);
}

/**
 * 中类选项改变
 */
function msc(){
	jQuery.post(
		$$.getContextPath()+'safety/s004!getstypeList',
		{mtypeId:jQuery('#mts').val()},
		function(option){
			jQuery('#sts').html(option);
		}
	);
}


///////修改冲突


/**
 * 大类选项改变
 */
function ulsc(){
	jQuery('#usts').html('<option>请选择</option>');
	jQuery.post(
		$$.getContextPath()+'safety/s004!getmtypeList',
		{ltypeId:jQuery('#ults').val()},
		function(option){
			jQuery('#umts').html(option);
		}
	);
}

/**
 * 中类选项改变
 */
function umsc(){
	jQuery.post(
		$$.getContextPath()+'safety/s004!getstypeList',
		{mtypeId:jQuery('#umts').val()},
		function(option){
			jQuery('#usts').html(option);
		}
	);
}

/**
 * 修改关闭
 */
function closeshow(){
	jQuery('#showdia').dialog('close');
}

/**
 * 信息修改
 */
function upds004Info(){
	jQuery.post(
		$$.getContextPath()+'safety/s004!doSaveEdit',
		jQuery('#upds004form').serialize(),
		function(msg){
			showMsg(msg.content,msg.type,"");
			if(msg.type==1){
				reloadGrid("s004table");
			}
		}
	);
}

/**
 * 检查内容查看
 */
function showview(){
	
}