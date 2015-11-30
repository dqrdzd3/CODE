/**
 * 此文件是基于jqGrid组件的二次开发
 * 2012-07-26
 * 杜群星
 * */

/**
 * 弹出窗口
 **/

function doDetail(id,title,url){
	$$.openDiv(id,title,url);
}
/**
 * 选择点击行
 * */
function doSelectRow(gridId,id){
	jQuery("#"+gridId).setSelection(id,true);
}
$(function(){
	$("body").append($("<div></div>").attr({id:'detailDiv','class':'hide'}));
});

/**
 * 新打开tab页
 */
function doCreateTab(tabId,tabName,url,isNew){
	
	 if(isNew){
		 tabId = 'docpreviewid'+new Date().getTime();
	 }
	 
	 $$.getFrame('rightFrame').Tabs.openTab(tabId,tabName,url);
	
}
/** ********************自定义链接格式start****************** */
/**
 * 默认调用查看按钮， 需要自定义页面时设置needURL=true
 * 
 * 1.查看明细链接 必须包含主键列，参数名称和列位置可自定 eg: colModel:[ {label:'id', name:'ma001',
 * index:'ma001',formatter:queryDetails,formatoptions:{divId:'',baseURL:$$.getContextPath()+'test',action:'/k004!doEdit',param:'&1=1',idName:'testname',postion:'1',needURL:true,fun:getDeptId}}
 * ..... } ]
 * 2.打开tab页 eg: colModel:[ {label:'id', name:'ma001',
 * index:'ma001',formatter:queryDetails,formatoptions:{baseURL:$$.getContextPath()+'test',action:'/k004!doEdit',param:'&1=1',idName:'testname',postion:'1',isTab:false,tabId:'',tabName:'',isNew:false}}
 * ..... } ]
 */
function queryDetails(cellvalue, options, rowObject) {
	if (!options) {
		options = {};
	}
	if (!rowObject) {
		rowObject = {};
	}
	
	if (!cellvalue) {
		cellvalue = "";
	}
	
	var needURL = options.colModel.formatoptions.needURL || true;
	var baseURL = options.colModel.formatoptions.baseURL || '';
	var action = options.colModel.formatoptions.action || '';
	var param = options.colModel.formatoptions.param || '';
	var idName = options.colModel.formatoptions.idName || "ma001";
	var postion = options.colModel.formatoptions.postion || 0;
	var title = options.colModel.formatoptions.title || '详细信息';
	var divId = options.colModel.formatoptions.divId || 'detailDiv';
	var gridId = options.colModel.formatoptions.gridId || 'gridTable';
	var isTab = options.colModel.formatoptions.isTab || false;
	var tabId = options.colModel.formatoptions.tabId || 'newTabId';
	var tabName = options.colModel.formatoptions.tabName || '信息窗口';
	var isNew = options.colModel.formatoptions.isNew || false;
	var fun = options.colModel.formatoptions.fun;
	var value = rowObject[postion];
	
	if (value == null) {
		value = "";
	}
	
	var rootURL = "";
	var url = "";
	
	url = dealURL(url, rootURL, baseURL, action, param);
	
	if (url.indexOf("?") != -1) {
	    if(url.charAt(url.length-1) == '?'){
	        url +=  idName + "=" + value;
	    }else{
    		url = url + "&" + idName + "=" + value;
	    }
	} else {
		url = url + "?" +  + idName + "=" + value;
	}
	
	if(fun){
	    url += "&"+fun();
	}
	if (cellvalue == null) {
		cellvalue = "";
	}
	
	url += "&ran=" + Math.random();
	
	if(isTab){
		return "<a href=javascript:; onclick=javascript:doCreateTab(\'"+tabId+"\',\'"+tabName+"\',\'"+url+"\',\'"+isNew+"\'); class=''>" + cellvalue + "</a>";
	}
	
	if(!needURL){
		return "<a href=javascript:; onclick=javascript:doSelectRow(\'"+gridId+"\',\'"+value+"\');$$.clickButton('view'); class=''>" + cellvalue + "</a>";
	}
	
	
	return "<a href=javascript:; onclick=javascript:doDetail('"+divId+"','"+title+"','"+url+"'); class=''>" + cellvalue + "</a>";
}
/**
 * 附件下载链接链接 必须包含下载路径和下载文件名称2列，参数名称和列位置可自定 eg: colModel:[ {label:'id',
 * name:'ma001',
 * index:'ma001',formatter:downloadURL,formatoptions:{baseURL:$$.getContextPath()+'test11',action:'/k004',param:'&2=2',filename:'tsetname',filepath:'testpath',fileNamePostion:9,filePathPostion:8}}
 * ..... } ]
 */
function downloadURL(cellvalue, options, rowObject) {
	if (!options) {
		options = {};
	}
	;
	if (!rowObject) {
		rowObject = {};
	}
	;
	if (!cellvalue) {
		cellvalue = "";
	}
	;
	var len = rowObject.length;
	var baseURL = options.colModel.formatoptions.baseURL || '';
	var action = options.colModel.formatoptions.action ||'';
	var param = options.colModel.formatoptions.param || '';
	var filename = options.colModel.formatoptions.fileName
			|| "downloadFileName";
	var filePath = options.colModel.formatoptions.fileName
			|| "downloadFilePath";
	var fileNamePostion = options.colModel.formatoptions.fileNamePostion || len
			- 1;
	var filePathPostion = options.colModel.formatoptions.filePathPostion || len
			- 2;
	var downloadFileName = rowObject[fileNamePostion];
	if (downloadFileName == null) {
		downloadFileName = "";
	}
	downloadFileName = encodeURI(encodeURI(downloadFileName));
	var downloadFilePath = rowObject[filePathPostion];

	var rootURL = "upload/fileupload!doDownLoad";
	var url = "";
	url = dealURL(url, rootURL, baseURL, action, param);
	if (url.indexOf("?") != -1) {
	     if(url.charAt(url.length-1) == '?'){
    		url +=  filePath + "=" + downloadFilePath + "&" + filename + "=" + downloadFileName;
	     }else{
    		url +=  "&" + filePath + "=" + downloadFilePath + "&" + filename + "=" + downloadFileName;
	     }
	} else {
		url = url + "?" + "&" + filePath + "=" + downloadFilePath + "&"
				+ filename + "=" + downloadFileName;
	}
	if (cellvalue == null) {
		cellvalue = "";
	}
	url += "&ran=" + Math.random();
	return "<a href=" + url + " class=''>" + cellvalue + "</a>";
}
/**
 *处理url 
 */
function dealURL(url, rootURL, baseURL, action, param) {
	url = rootURL;
	if (baseURL != null && baseURL != "") {
		if (baseURL.indexOf("?") != -1) {
			alert("baseURL不能包含?");
			return;
		}
		rootURL = baseURL;
	}
	rootURL += action;
	if(param.charAt(0) == '&'){
	    param = param.replace('&','');
	}
	if (rootURL.indexOf("?") != -1) {
	     if(rootURL.charAt(rootURL.length-1) == '?'){
    		  rootURL = rootURL + param;
	     }else{
    		  rootURL = rootURL +"&"+ param;
	     }
	} else {
		rootURL = rootURL + "?" + param;
	}
	url = rootURL;
	return url;
}
/** ********************自定义链接格式end****************** */

