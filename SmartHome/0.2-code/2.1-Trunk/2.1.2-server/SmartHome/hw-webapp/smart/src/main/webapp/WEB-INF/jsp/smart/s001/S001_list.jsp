<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath %>"/>
<title>	常见问题及解决-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS,my97)" executeResult="true"/>
<script type="text/javascript">
var gridOption = {
		id : 'gridTable',
		caption:'智能设备或APP常见问题及解决信息',
		colModel:[
            {label:'', name:'ma001', index:'ma001',hidden:true,hidedlg:true},
			{label:'标题', name:'ma002', index:'ma002',formatter:showcontent},
			{label:'解决方法', name:'ma003', index:'ma003'},
			{label:'添加时间', name:'ma004d', index:'ma004d'}
		],
		formId:'searchForm',
		winTitle:'常见问题及解决信息'
};

var actionOption ={init:true };

$(function(){
    $$.bindButton('view',function() {
    	var url = $$.getContextPath()+'smart/s001!doBrowse';
    	$$.getFrame('rightFrame').Tabs.openTab("titleid", "常见问题浏览",url, "常见问题浏览 ", true);
    }, 'operating');
		
		
    $$.bindButton('add',function(){
        var divId = 'showd',
            title = '常见问题解决',
            url = $$.getContextPath()+'smart/s001!doAdd',
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
	        title = '常见问题解决',
	        url = $$.getContextPath()+'smart/s001!doEdit',
	        param = 's001PO.ma001='+id,
	        callback=function(){};
	    $$.openDiv(divId, title, url, param, callback);
    });	
    $$.bindButton('delete',function(){
         var gridId="gridTable";
         if ($$.checkSelRow(0)) return;
         var ids = getSelectedRowID("#"+gridId);
	     var url = $$.getContextPath()+'smart/s001!doDelete',
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
        $$.openDiv('showd', '常见问题及解决信息查询',$$.getContextPath()+'smart/s001!doShow', {'s001PO.ma001' : id});
    }
}  
 
function saveS001(){
	if($('#s001Add').valid()){
		var param = $('#s001Add').serialize();
		var url = $$.getContextPath()+'smart/s001!doSaveAdd';
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

function modifyS001(){
	if($('#s001Edit').valid()){
		var param = $('#s001Edit').serialize();
		var url = $$.getContextPath()+'smart/s001!doSaveEdit';
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



</script>
</head>

<body>
	<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">
			<s:form action="s001" method="post" cssClass="formmargin" id="searchForm"> 
				<table border="0" cellspacing="8" cellpadding="0">
			        <tr>
			          <td align="right" style="width:76px;">标题</td>
			          <td style="width:300px;"> <s:textfield name="s001PO.ma002" cssClass="input2"/> </td>
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
		<hwsoft:operation code="${code}" param="add,edit,delete"/>
	</div>
	<div class="tablestyle">
		<table id="gridTable"></table>
	</div>
	<div id="showd"></div>
</body>
</html>