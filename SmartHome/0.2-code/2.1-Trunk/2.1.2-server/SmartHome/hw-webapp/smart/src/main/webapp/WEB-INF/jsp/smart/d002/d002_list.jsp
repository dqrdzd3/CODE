<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath %>"/>
<title>设备管理-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS,my97)" executeResult="true"/>
<script type="text/javascript">

var gridOption = {
		id : 'gridTable',
		caption:'设备管理信息',
		colModel:[
            {label:'', name:'ma001', index:'ma001',hidden:true,hidedlg:true},
			{label:'设备名称', name:'ma008', index:'ma008',formatter:showcontent},
			{label:'设备标识', name:'ma004', index:'ma004'},
			{label:'设备类型', name:'ma003d', index:'ma003d'},
			{label:'设备类型', name:'ma003', index:'ma003',hidden:true,hidedlg:true},
			{label:'注册用户', name:'USERNAME', index:'USERNAME'},
			{label:'注册时间', name:'ma005d', index:'ma005d'}
			
		],
		formId:'searchForm',
		winTitle:'设备管理信息'
};

var actionOption ={init:true };

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
            $$.openDiv('showd', '设备详情查看',$$.getContextPath()+'smart/d002!doShow', {'d002PO.ma001' : ''+ getSelectedRowID("#gridTable")}, function() {
              });
        }
    }, 'operating');
		
		
    $$.bindButton('add',function(){
        var divId = 'showd',
            title = '添加记录',
            url = $$.getContextPath()+'smart/d002!doAdd',
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
	        url = $$.getContextPath()+'smart/d002!doEdit',
	        param = 'd002PO.ma001='+id,
	        callback=function(){};
	    $$.openDiv(divId, title, url, param, callback);
    });	
    $$.bindButton('delete',function(){
         var gridId="gridTable";
         if ($$.checkSelRow(0)) return;
         var ids = getSelectedRowID("#"+gridId);
	     var url = $$.getContextPath()+'smart/d002!doDelete',
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
        $$.openDiv('showd', '设备详情查看',$$.getContextPath()+'smart/d002!doShow', {'d002PO.ma001' : id});
    }
}  
 
function saveD002(){
	if($('#d002Add').valid()){
		var param = $('#d002Add').serialize();
		var url = $$.getContextPath()+'smart/d002!doSaveAdd';
		$.post(url,param,function(data){
			showMsg(data.content, data.type, {
	  		buttons:{
	  			'确定' : function(){
	  				closeshowd();
	  				$(this).dialog('close');
	  				reloadGrid("gridTable");
	  			}}
	   		});
		});
	};
}

function modifyD002(){
	if($('#d002Edit').valid()){
		var param = $('#d002Edit').serialize();
		var url = $$.getContextPath()+'smart/d002!doSaveEdit';
		$.post(url,param,function(data){
			showMsg(data.content, data.type, {
	  		buttons:{
	  			'确定' : function(){
	  				closeshowd();
	  				$(this).dialog('close');
	  				reloadGrid("gridTable");
	  			}}
	   		});
		});
	};
}

function showInstant(){
	 var gridId="#gridTable";
     if(!$$.checkSelRow(1)) return;
     var ids = getSelectedRowID(gridId);
     var id = ids[0];
	    var divId = 'showd',
	        title = '实时数据',
	        url = $$.getContextPath()+'smart/d002!doShowInstant',
	        param = 'd002PO.ma001='+id,
	        callback=function(){};
	    $$.openDiv(divId, title, url, param, callback);
}

function showhistory(){
	 var gridId="#gridTable";
    if(!$$.checkSelRow(1)) return;
    var ids = getSelectedRowID(gridId);
    var id = ids[0];
	var divId = 'showd',
	    title = '历史明细数据',
	    url = $$.getContextPath()+'smart/d002!doShowHistory1',
	    param = 'd002PO.ma001='+id,
	    callback=function(){};
	$$.openDiv(divId, title, url, param, callback);
}

</script>
</head>

<body> 
	<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">
			<s:form action="k001" method="post" cssClass="formmargin" id="searchForm"> 
				<table border="0" cellspacing="8" cellpadding="0">
			        <tr>
			          <td align="right" style="width:76px;">设备名称 </td>
			          <td style="width:300px;"> <s:textfield name="d002PO.ma008" cssClass="input2"/> </td>
			          <td align="right" style="width:76px;">设备标识</td>
			          <td style="width:300px;"> <s:textfield name="d002PO.ma004" cssClass="input2"/> </td>
			          <td align="right" style="width:76px;">设备类型</td>
			          <td style="width:300px;"> 
			          		<select name="d002PO.ma003" >
			          				<option value="">请选择</option>
			          				<option value="1">可燃气体传感器</option>
			          				<option value="2">空气传感 器</option>
			          				<option value="3">烟雾传感器</option>
			          		</select>
			          </td>
			          <td width="98"><s:submit value="" cssClass="search"/></td>
			        </tr>
			     </table>
			</s:form>
		</div>
		</div>
	</div>
	<div id="hiddlebutton">
		<a href="#"><img src="images/searchhiddleicon.jpg" width="71" height="13" /></a>
	</div>
	<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="delete,instant,showhis"/>
	</div>
	<div class="tablestyle">
		<table id="gridTable"></table>
	</div>
	<div id="showd"></div>
</body>
</html>