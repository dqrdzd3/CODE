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
<title>	讨论区管理-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS,my97)" executeResult="true"/>
<script type="text/javascript">
var gridOption = {
		id : 'gridTable',
		caption:'讨论区管理',
		colModel:[
            {label:'', name:'ma001', index:'ma001',hidden:true,hidedlg:true},
			{label:'主题', name:'ma002', index:'ma002',formatter:showcontent},
			{label:'解决办法', name:'ma003', index:'ma003'},
			{label:'创建时间', name:'ma004', index:'ma004'}
		],
		formId:'searchForm',
		winTitle:'讨论区管理'
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
            $$.openDiv('showd', '常见问题及解决信息查询',$$.getContextPath()+'smart/s001!doShow', {'s001PO.ma001' : ''+ getSelectedRowID("#gridTable")}, function() {
              });
        }
    }, 'operating');
		
		
    $$.bindButton('add',function(){
        var divId = 'showd',
            title = '讨论区管理',
            url = $$.getContextPath()+'smart/s004!doAdd',
            param = '',
            callback=function(){};
        $$.openDiv(divId, title, url, param, callback);
    });   
			    
    $$.bindButton('delete',function(){
         var gridId="gridTable";
         if ($$.checkSelRow(0)) return;
         var ids = getSelectedRowID("#"+gridId);
	     var url = $$.getContextPath()+'smart/s004!doDelete',
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
	         
    	showMsg('确定要删除所选的记录吗？如果该主题下有人讨论，则无法删除',MSG.INFO,{buttons:{
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
function showcontent(celv, opts, obj) {
    return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+ obj[0] + '\')">' + celv + '</a>';
}
function showContentById(id) {
	var url = $$.getContextPath()+'smart/s005?s004po.ma001='+id;
	$$.getFrame('rightFrame').Tabs.openTab("titleid", "讨论区",url, "讨论区 ", true);
}  
function closeshowd(){
	$("#showd").dialog('close');
} 
function saveS004(){
	if($('#s004Add').valid()){
		var param = $('#s004Add').serialize();
		var url = $$.getContextPath()+'smart/s004!doSaveAdd';
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
/* function gotitle()
{
	 var i = getSelectedRowID("#gridTable").length;
	 if (i == 0) {
        showMsg("请选择一条记录!", 2);
        return;
    } else if (i > 1) {
        showMsg("请选择一条记录!", 2);
        return;
    } else { 
    
     var url = $$.getContextPath()+'smart/s005?s004po.ma001='+id;
    	$$.getFrame('rightFrame').Tabs.openTab("titleid", "讨论区",url, "讨论区 ", true);
    }
} */
</script>
</head>

<body>
	<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">
			<s:form action="s004" method="post" cssClass="formmargin" id="searchForm"> 
				<table border="0" cellspacing="8" cellpadding="0">
			      <tr>
			          <td align="right" style="width:76px;">主题：</td>
			          <td style="width:300px;"> <s:textfield name="s004po.ma002" cssClass="input2"/> </td>
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
		<hwsoft:operation code="${code}" param="add,delete"/>
	</div>
	<div class="tablestyle">
		<table id="gridTable"></table>
	</div>
	<div id="showd"></div>
</body>
</html>