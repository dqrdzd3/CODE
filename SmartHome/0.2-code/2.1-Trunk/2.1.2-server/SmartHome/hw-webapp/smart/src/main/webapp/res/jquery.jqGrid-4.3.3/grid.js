/* 初始化Grid */

/* ************ 配置选项 ************
var gridOption = {
			id:'gridTable', // table容器ID
			subGridId:'',	// 子表table容器ID
			url:'corpinfo/b002!queryData.action',  // 模块URL
			pk:'MA001',		// 数据表中主键
			colModel:[		// 列模型
				{label:'机构编号', name:'MA002', index:'MA002'},
			],
			formId:'searchForm', 	// 查询条件form ID
			caption:'企业安全机构表',	// 列表标题
			table:'',				// 数据表名
			poName:'b002PO',		// action中收集查询条件的po名称
			po_pId:'ma002'			// 父表po中主键id
};
********************************** */

function initGrid(opt) {
	// 20130408 mfb : 从表默认不初始化
	if (opt.po_pId) return;
	
	_initGrid(_setDefault(opt));
}

function _setDefault(opt) {
	// 处理一些默认值
//	var opt = option || {};
	opt.id = opt.id || 'gridTable',	// 未配置时，默认值为gridTable
	opt.url = opt.url || $$.getActionUrl() + '!queryData.action',
	opt.pk = opt.pk || 'ma001',
//	opt.formId = opt.formId || 'searchForm',
	opt.caption = opt.caption || '表格';
	return opt;
}

function _initSubGrid(opt) {
	_initGrid(_setDefault(opt));
}

function _showOperting(gridId) {
	$("#" + _AllGridOptions[gridId].operatingId).show();
}

/**
 * @param funName
 */
function _getFormatterStr(funName) {
	var cArgs = _getFormatterStr.caller.arguments;
	return "<a href=\"javascript:;\" class=\"viewLinkClass\" onclick=\"" + funName + "('" 
	+ cArgs[1].rowId + "')\">" + cArgs[0] + "</a>";
}

/**
 * 20130412 mfb : 名称列链接格式化函数
 * @param cellValue
 * @param options
 * @param rowObject
 * @returns 格式化后在表格中显示的字符串
 */
function viewLinkFormatter(cellValue, options, rowObject) {
//	return "<a href=\"javascript:;\" class=\"viewLinkClass\" onclick=\"_viewContent('" 
//				+ rowObject[0] + "')\">" + cellValue + "</a>";
	
	return _getFormatterStr("_viewContent");
}

function _initGrid(initOption) {
	// param
	var id = initOption.id,
		idSelector = "#" + id,
		url = initOption.url,
		colModel = initOption.colModel,
		formId = initOption.formId,
		subGridId = initOption.subGridId,
		table = initOption.table || url.substring(url.lastIndexOf("/") + 1, url.indexOf("!")),
		poName = initOption.poName || table + "PO",
		pk = initOption.pk,
		po_pId = initOption.po_pId,
		winTitle = initOption.winTitle || "",
		
		// pager bar id
		pagerId = "grid_pager_id_" + $$.getIdx(), 
		pagerIdSelector = "#" + pagerId,
		cellIndexArr = [], 
		cellIndex;
	
	// search form
	if (formId) {
		$("#" + formId).submit(function(){
			// 重新加载表格
			reloadGrid(id);
			return false;
		});
	}
	
	// 未配置容器，自动添加
	if (!$(idSelector).length)
		$("body").append($('<div class="tablestyle"/>').append('<table id="' + id + '"/>'));
	// pager div
	$(idSelector).after("<div id=\"" + pagerId + "\"></div>");
	
	// cell index， 列索引
	for (i = 0, len = colModel.length; i < len; i++)
		cellIndexArr.push(colModel[i]["index"]);
	
	cellIndex = cellIndexArr.join();
	
	// data，存储数据
	$(idSelector).data("pk", pk);
	$(idSelector).data("originalUrl", url);
	$(idSelector).data("actionUrl", url.split("!")[0].replace($$.getContextPath(),""));
	if (po_pId) $(idSelector).data("pId", po_pId);
	$(idSelector).data("winTitle", winTitle);
	
	// 处理从表：选中主表记录时，根据主表ID，刷新所有从表数据
	if (subGridId) {
	/*		
		var subGridSltr = "#" + subGridId;
		initOption.onSelectRow = function(id) {
			$(subGridSltr).jqGrid("setGridState",'visible');
			$(subGridSltr).data('pId_value', id);
			reloadGrid(subGridId);
		};
	*/	
		// 隐藏从表
		var oldLoadComplete = initOption.loadComplete || function(){};
		initOption.loadComplete = function () {
			oldLoadComplete();
			_hideSubTable(id);
		};
		
		
		var subArr = subGridId instanceof Array ? subGridId : [subGridId];
		//为checkbox加点击事件
		
		
		initOption.onSelectRow = function(rowId) {
			$.each(subArr,function(i,x){
				var sltr = "#" + x;
				$(sltr).jqGrid("setGridState",'visible');
				$(sltr).data('pId_value', rowId);
				if (!$(sltr).data("init")) {
					_initSubGrid(_AllGridOptions[x]);
					_initActionButtons(_AllActionOptions[x]);
				} else {
					reloadGrid(x);
				}
			});
			
			// 显示从表
			_showSubTable(id);
		};
		
	}
	
	
	
	// before request， grid发送请求之前的处理函数
	var beforeRequest = function() {
		var url = $(idSelector).data("originalUrl");
	
	/************** url 传参 ************	
		// add param
		if (url.indexOf("?") == -1)
			url += "?cellIndex=" + cellIndex + "&pk=" + pk;
		else 
			url += "&cellIndex=" + cellIndex + "&pk=" + pk;
		
		if (po_pId && $(idSelector).data('pId_value'))
			url += "&" + poName + "." + po_pId + "=" + $(idSelector).data('pId_value');
		
		var queryParam;
		if (formId && (queryParam = $("#" + formId).serialize()) && queryParam.length > 0) 
			url += "&" + queryParam;
		
		// set url
		$(idSelector).jqGrid("setGridParam", {url: url});
	*/
		
		
		
		/***************************
		   mtype:"POST"
		   use appendPostData
		 ***************************/
	};
	
	// serializeGridData，  post方式传参
	var serializeGridData = function(postData) {
		// 列索引
		postData.cellIndex = cellIndex;
		postData.pk = pk;
		
		// 主表ID
		if (po_pId && $(idSelector).data('pId_value'))
			postData[poName + "." + po_pId] = $(idSelector).data('pId_value');
		
		// 序列化
		var param = $.param(postData);
		
		// 查询条件
		var formParam;
		if (formId && (formParam = $("#" + formId).serialize()) && formParam.length > 0)
			param += "&" + formParam;
		
		return param;
	};
	
	// option， grid选项
	var option = {
		datatype:"json",
		
		pager:"#" + pagerId,
		rowNum:10,	// 每页条数
		rowList:[5, 10],	// 页数下拉菜单中可选值
		viewrecords:true,
		
		autowidth:true,	// 初始化时自适应宽度
	//	height:'100%',
		height:230,
		// shrinkToFit:false,
		rownumbers:true,	// 显示行号
		
		multiselect:true,	// 行多选
		multiboxonly:true,
		
		//hoverrows:false,
		altRows:false,		// 隔行变色
		altclass:'alt_tr_class',
		
		sortable:true,		// 拖动列排序
		
		//loadonce:true,
		//rowTotal:2000,
		//gridview:true,
		
		cmTemplate:{		// 列模型模板
			editable:true
		},
		
		//postData:{},
		//userData:{},
		
		 mtype:"POST",		// post方式提交数据
		
		beforeRequest:beforeRequest,
		serializeGridData:serializeGridData,
		beforeSelectRow : function(rowid,e){
			/*
			var $myGrid = $(this),
	        i = $.jgrid.getCellIndex($(e.target).closest('td')[0]),
	        cm = $myGrid.jqGrid('getGridParam', 'colModel');
			return (cm[i].name === 'cb');
			*/
			if(e.target.type == 'checkbox'){
				return false;
			}else{
				return true;
			}
		}
	};
	
	
	// option
	$.extend(option, initOption);
	
	// initialize ...
	$(idSelector)
		.jqGrid(option)
		// 右下角“增删改查”四小按钮
		.jqGrid("navGrid", pagerIdSelector, {refresh:true,search:false,add:false,del:false,edit:false},{},{},{},{})
		// 行排序
	//	.jqGrid("sortableRows")
		// 表格缩放
	//	.jqGrid("gridResize", {minWidth:100,minHeight:50})
		/*
		.jqGrid("navButtonAdd", pagerIdSelector, {id:"select_column",caption:"列选择器",title:"选择显示隐藏的列名",
			onClickButton:function(){
				$(idSelector).jqGrid("columnChooser");
			}
		})
		*/
		;
	// 修正“选择列”字体不居中
//	$("#select_column", pagerIdSelector).css("line-height", "18px");
	
	(window.GridID_Array = window.GridID_Array || []).push(id);
	
	// 初始化完成
	$(idSelector).data("init", true);
}

/**
 * 查询总条数
 * url: 
 * searchForm: 查询条件表单的ID
 * callback: 回调
 *  
 * */
function _queryCount(url, searchForm, callback) {
//	searchForm = '#searchForm';
	if(url != undefined && url != 'undefined'){
		if (url.indexOf("?") != -1) {
			url += "&qc=true";
		} else {
			url += "?qc=true";
		}
		
		$$.getPO(url,$(searchForm).serialize(),function(po){
			if (callback) 
				callback(po.records);
		});
	}
}

/* 重新加载Grid */
function reloadGrid(id) {
	var	url = $("#" + id).jqGrid("getGridParam", "url"),
	 	page = $("#" + id).jqGrid("getGridParam", "page"),
		pageSize = $("#" + id).jqGrid("getGridParam", "rowNum");
	
	// 删除列表最后一页的所有数据时，刷新页面将显示前一页内容。
	_queryCount(url, "#searchForm", function(totalCount){
		var totalPage = Math.ceil(totalCount / pageSize);
		page = page <= totalPage ? page : totalPage;
		
		// 重新加载时显示当前页
		$("#" + id).jqGrid("setGridParam", {page:page});
		$("#" + id).trigger("reloadGrid");
		
	});
	
	
}

function _hideSubTable(gridId) {
	var subArr = _getSubArr(_AllGridOptions[gridId].subGridId);
	$.each(subArr,function(i,id){
		$("#" + _AllGridOptions[id].operatingId).hide();
		$("#" + "gbox_" + id).hide();
		
	});
}

function _showSubTable(gridId) {
	var subArr = _getSubArr(_AllGridOptions[gridId].subGridId);
	$.each(subArr,function(i,id){
		$("#" + _AllGridOptions[id].operatingId).show();
		$("#" + "gbox_" + id).show();
		
	});
}

function _getSubArr(subGridId) {
	return subGridId instanceof Array ? subGridId : [subGridId];
}

// 获取选中行的ID数组
function getSelectedRowID(gridSelector) {
	//alert($(gridSelector).jqGrid("getGridParam", "selarrrow"));
	//return $(gridSelector).jqGrid("getGridParam", "selarrrow");
	
	
	var newIdArr = '';
	$.each($(gridSelector+' input[type="checkbox"]'), function(key, value){
	    if(value.name != null && value.name != ''){
	    	if(value.checked){
	    		var str = value.parentNode.parentNode.id;
	    		newIdArr = newIdArr + str + ',';
	    	}
	    }
	});
	if(newIdArr != ''){
		newIdArr = newIdArr.substring(0, newIdArr.length - 1);
		var arr = newIdArr.split(',');
		return arr;
	}else{
		return new Array(0);
	}
}

function  getSelectCheckboxRowId(gridSelector){
	var newIdArr = '';
	$.each($(gridSelector+' input[type="checkbox"]'), function(key, value){
	    if(value.name != null && value.name != ''){
	    	if(value.checked){
	    		var str = value.parentNode.parentNode.id;
	    		newIdArr = newIdArr + str + ',';
	    	}
	    }
	});
	newIdArr = newIdArr.substring(0, newIdArr.length - 1);
	return newIdArr;
}
//自适应Grid宽度
function resizeGrids(){
    try{
    	$.each(window.GridID_Array, function(i, id){
    		$("#"+id).jqGrid("setGridWidth", $("#"+id).parents(".tablestyle").width()-3);
    	});
    }catch(e){}
}
// grid自适应宽度
$(window).resize(function(){
	resizeGrids();
});