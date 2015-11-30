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
<title>日志管理-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS,my97)" executeResult="true"/>
<script type="text/javascript">

var gridOption = {
		id : 'gridTable',
		caption:'日志管理信息',
		url:$$.getContextPath()+'smart/d00501!queryData1',
		colModel:[
            {label:'', name:'ma001', index:'ma001',hidden:true,hidedlg:true},
			{label:'移动设备型号', name:'ma007', index:'ma007',formatter:showcontent},
			{label:'系统版本号', name:'ma003', index:'ma003'},
			{label:'应用名称', name:'ma004', index:'ma004'},
			{label:'创建用户', name:'ma008', index:'ma008'},
			{label:'创建时间', name:'ma009', index:'ma009'}
		],
		rowList:[20,30,40,50,100],
		rowNum:10,
		formId:'searchForm',
		winTitle:'日志管理信息'
};

var actionOption ={init:false };

$(function(){
    $$.bindButton('delete',function(){
         var gridId="gridTable";
         if ($$.checkSelRow(0)) return;
         var ids = getSelectedRowID("#"+gridId);
	     var url = $$.getContextPath()+'smart/d005!doDelete',
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
        $$.openDiv('showd', '日志详情查看',$$.getContextPath()+'smart/d005!doShow', {'d005PO.ma001' : id});
    }
}  

function groupInstance(){
	$$.getFrame('rightFrame').Tabs.openTab("showdiv"+Math.ceil(Math.random()*10),"统计查看",$$.getContextPath()+'smart/d00501!doGetGroup1',""); 
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
			          <td align="right" style="width:76px;">移动设备型号 </td>
			          <td style="width:300px;"> <s:textfield name="d005PO.ma007" cssClass="input2"/> </td>
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
		<hwsoft:operation code="${code}" param="delete,group"/>
	</div>
	<div class="tablestyle">
		<table id="gridTable"></table>
	</div>
	<div id="showd"></div>
	<div id="showdiv"></div>
</body>
</html>