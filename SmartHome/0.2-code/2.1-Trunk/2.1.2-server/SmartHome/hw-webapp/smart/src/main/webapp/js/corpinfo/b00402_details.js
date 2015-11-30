var cseedialog=null;//从表查看对话框
var fseedialog=null;//主表查看对话框
//重大危险源列表
var gridOption1=null;
var btnId=null;

/**
 * 查看详细信息  主表
 */
function fquery(id,type,fname){
	var url='';
	if(type=='WXY_B'){//贮罐区
		url= $$.getContextPath()+'danger/b01001!edit?b01001PO.MA001='+id;
		setitle='贮罐区信息查看';
	}else if(type=='WXY_C'){//库区
		url=$$.getContextPath()+'danger/b01002!edit?b01002PO.MA001='+id;
		setitle='库区信息查看';
	}else if(type=='WXY_D'){//生产场所
		url=$$.getContextPath()+'danger/b01003!edit?b01003PO.MA001='+id;
		setitle='生产场所信息查看';
	}else if(type=='WXY_K'){//煤矿
		url=$$.getContextPath()+'danger/b0100701!edit?b0100701PO.MA001='+id;
		setitle='煤矿信息查看';
	}else if(type=='WXY_M'){//露天矿
		url='';
		setitle='露天矿信息查看';
	}else if(type=='WXY_N'){//尾矿库
		url=$$.getContextPath()+'danger/b01009!edit?b01009PO.MA001='+id;
		setitle='尾矿库信息查看';
	}else if(type=='WXY_P'){//金属非金属地下矿
		url=$$.getContextPath()+'danger/b0100801!edit?b0100801PO.MA001='+id;
		setitle='金属非金属地下矿信息查看';
	}else if(type=='WXY_H'){//压力容器
		url=$$.getContextPath()+'danger/b01006!edit?b01006PO.MA001='+id;
		setitle='压力容器信息查看';
	}else if(type=='WXY_F'){//压力管道
		url=$$.getContextPath()+'danger/b01004!edit?b01004PO.MA001='+id;
		setitle='压力管道信息查看';
	}else if(type=='WXY_G'){//锅炉
		url=$$.getContextPath()+'danger/b01005!edit?b01005PO.MA001='+id;
		setitle='锅炉信息查看';
	}else if(type=='whpzdwxy'){//危化品
		url='';
		setitle='危化品信息查看';
	}
	
	$$.openDiv('supershow','危险源信息详情',url,'',function(data){
		jQuery('#supershow').html(data);
		$$.clearInput('#supershow');
		jQuery("#windowsbottomright .back").click(function(){
			jQuery("#supershow").dialog('close');
		});
		jQuery("#windowsbottomright .save").hide();
	});
	/**
	 * if(type == 'WXY_D'){
		
		$$.openDiv('supershow','危险源信息详情',url,'',function(data){
			//childTableInit2(id,fname);
		    jQuery('#dangerDiv').html(data);
			$$.clearInput('#dangerDiv');
			jQuery("#windowsbottomright").hide();
		});

		
	}else{
		$$.openDiv('supershow','危险源信息详情',url,'',function(data){
			jQuery('#supershow').html(data);
			$$.clearInput('#supershow');
			jQuery("#windowsbottomright .back").click(function(){
				jQuery("#supershow").dialog('close');
			});
			jQuery("#windowsbottomright .save").hide();
		});
	}
	
	**/

}






/**
 * 从表信息读取  贮罐区/生产场所/锅炉/库区/煤矿
 */
function childTableInit1(id,fname){
	

	gridOption1 = {
			caption:fname+' 贮罐区下的贮罐信息',
			colModel:[
		        {label:'parent', name:'MA002', index:'MA002',hidden:true},
				{label:'贮罐名称', name:'MA004', index:'MA004'},
				{label:'贮罐序号', name:'MA003', index:'MA003'}
			],
			pk:'MA001',
			id:'childTable',
			url:$$.getContextPath()+'danger/b0100101!queryData.action?b0100101PO.MA002='+id+'&b01001id='+id,
			pagerId:'cl1id'

	};
	_initGrid(gridOption1);
}

function childTableInit2(id,fname){
	var gridOption2 = {
			caption:fname+'生产场所下的危险物品',
			colModel:[
	          
				{label:'名称', name:'MA004', index:'MA004'},
				{label:'编号', name:'MA003', index:'MA003'},
				{label:'物质类别', name:'MA008', index:'MA008'}
			],
			pk:'MA001',
			id:'childTable',
			url:$$.getContextPath()+'danger/b0100301!queryData.action?b0100301PO.MA002='+id+'&rid='+id,
			pagerId:'cl2id'+Math.random()
	};
	jQuery('#childTable').html('');
	_initGrid(gridOption2);
	
	
}

function childTableInit3(id,fname){
	
	jQuery('#allclb').empty();
	//var btnId = "but" + $$.getIdx();
	jQuery('#allclb').append('<div id="'+btnId+'" class="operating"></div>');
	var gridOption3 = {
			caption:fname+' 的锅炉维修信息',
			colModel:[
				{label:'记录编号', name:'MA003', index:'MA003'},
				{label:'维修原因', name:'MA004', index:'MA004'}
			],
			pk:'MA001',
			id:'chilt',
			url:$$.getContextPath()+'danger/b0100501!queryData.action?b0100501PO.MA002='+id+'&b01005id='+id,
			pagerId:'cl3id'
	};
	_initGrid(gridOption3);
	 
}
/**
 * 库区二级从表
 */
function childTableInit4(id,fname){
	jQuery('#allclb').empty();
	//var btnId = "but" + $$.getIdx();
	jQuery('#allclb').append('<div id="'+btnId+'" class="operating"></div>');
	var gridOption4 = {
			caption:fname+' 库区下的库房信息',
			colModel:[ 
				{label:'库房名称', name:'MA005', index:'MA005'},
				{label:'库房序号', name:'MA004', index:'MA004'},
				{label:'库房形式', name:'MA006', index:'MA006'},
				{label:'库房结构', name:'MA008', index:'MA008'},
				{label:'层数', name:'MA007', index:'MA007'}
			],
			pk:'MA001',
			id:'chilt',
			url:$$.getContextPath()+'danger/b0100201!queryData.action?b0100201PO.MA002='+id+'&b0100201id='+id,
			pagerId:'cl4id',
			onSelectRow: function(id) {
				var fname=''+jQuery("#dangertable").jqGrid("getCell", id, "MA005");
				childTbleInit401(id,fname);
				
			}
	};
	_initGrid(gridOption4);
	
}
/**
 * 库区三级从表
 */
function childTbleInit401(id,fname){
	//alert(id);
	//window.open($$.getContextPath()+'danger/b010020101.action?b0100201PO.MA001='+id);
	jQuery("#cl401").empty();
	//var btnId = "but" + $$.getIdx();
	jQuery("#cl401").appentd('<div id="'+btnId+'" class="operating"></div>');
	jQuery("#cl401").appentd('<table id="cl4table"></table>');
	var gridOption401 = {
			caption:fname+'　库房下的设备信息',
			colModel:[
				{label:'物质名称', name:'MA004', index:'MA004'},
				{label:'物质状态', name:'MA006', index:'MA006'},
				{label:'危险特性', name:'MA007', index:'MA007'},
				{label:'贮存位置', name:'MA012', index:'MA012'}
			],
			pk:"MA001",
			id:'cl4table',
			url:$$.getContextPath()+'danger/b010020101!queryData.action?b010020101PO.MA003='+id+'&b0100201id='+id,
			pagerId:'cl4id'
	};
	_initGrid(gridOption401);
	
}

function childTableInit5(id,fname){
	jQuery('#allclb').empty();
	//var btnId = "but" + $$.getIdx();
	jQuery('#allclb').append('<div id="'+btnId+'" class="operating"></div>');
	var gridOption5 = {
			caption:fname+' 煤矿设备信息',
			colModel:[
				{label:'设备名称', name:'MA003', index:'MA003'},
				{label:'设备类型', name:'MA004', index:'MA004'},
				{label:'设备型号', name:'MA005', index:'MA005'}
			],
			pk:'MA001',
			id:'chilt',
			url:$$.getContextPath()+'danger/b010070001!queryData.action?b010070001PO.MA002='+id+'&b0100701id='+id,
			pagerId:'cl5id'
	};
	
	_initGrid(gridOption5);
	
}


//从表信息操作  贮罐区/生产场所/锅炉/库区/煤矿
/**
 * 查看
 */
function cquery(){
	//alert(jQuery('#curtype').val());
	var count=getSelectedRowID('#chilt').length;
	if(count==0){showMsg('请选择需求查看的信息',1);return;}else if(count>1){alert('只能查看一条记录');return;}
	var type=''+jQuery('#curtype').val();
	var id=''+getSelectedRowID('#chilt');
	var ctitle='';
	var url='';
	if(type=='WXY_B'){//贮罐
		url=$$.getContextPath()+'danger/b01001!EditB0100101?b0100101PO.MA001='+id;
		ctitle='贮罐信息查看';
	}else if(type=='WXY_C'){//库区
		url=$$.getContextPath()+'danger/b0100201!EditB0100201?b0100201PO.MA001='+id;
		ctitle='库房信息查看';
	}else if(type=='WXY_D'){//生产场所
		url=$$.getContextPath()+'danger/b01003!EditB0100301?b0100301PO.MA001='+id;
		ctitle='危险物品信息查看';
	}else if(type=='WXY_K'){//煤矿
		url=$$.getContextPath()+'danger/b0100701!EditB010070001?b010070001PO.MA001='+id;
		ctitle='煤矿设备信息查看';
	}else if(type=='WXY_G'){//锅炉
		url=$$.getContextPath()+'danger/b01005!EditB0100501?b0100501PO.MA001='+id;
		ctitle='锅炉维修信息查看';
	}
	jQuery.post(
			url,{a:'a'},
			function(data){
				jQuery('#qc').html(data);
				
			}
		);

		cseedialog=jQuery('#qc').dialog({
			autoOpen:false,
			height:'auto',
			width:'760',
			title:ctitle,
			modal:true
		});
		$$.clearInput('#qc');
		cseedialog.dialog('open');
}






function  runOnLoadFunctions() {
	// 伸缩按钮
	(function(){
		var seltor = "#hiddlebutton",
			upClass = "upArrow",
			downClass = "downArrow";
		$(seltor).empty().append($("<a>"))
			.children("a")
			.attr("href","javascript:;").addClass(upClass)
			.click(function(){
				var obj = this;
				$("div:first", $(seltor).parent()).slideToggle("fast", function(){
					$(obj).toggleClass(downClass);
				});
			});
	})();

}



