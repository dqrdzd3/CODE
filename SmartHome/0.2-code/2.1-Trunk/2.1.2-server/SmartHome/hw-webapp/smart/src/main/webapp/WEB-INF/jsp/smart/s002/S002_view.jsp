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
		caption:'推送记录',
		colModel:[
            {label:'', name:'ma001', index:'ma001',hidden:true,hidedlg:true},
			{label:'消息内容', name:'ma005', index:'ma005',formatter:showDetail},
			{label:'接收人员', name:'ma06', index:'ma006',formatter:showcontent1},
			{label:'发送状态', name:'ma07', index:'ma007',align:"center"},
			{label:'创建时间', name:'ma004', index:'ma004'}
		],
		formId:'searchForm',
		winTitle:'推送记录'
};
var actionOption ={init:true };

$(function(){
    	
		
	$$.bindButton('delete',function(){
        var gridId="gridTable";
        if ($$.checkSelRow(0)) return;
        var ids = getSelectedRowID("#"+gridId);
	     var url = $$.getContextPath()+'smart/s002!doDelete',
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
//格式化消息内容的字符串长度
function showcontent1(celv, opts, obj){
	 if(obj[2].length>30)
		 {
		     return obj[2].substr(0,28)+"...更多";
		 }
	 return obj[2]; 
} 
function showcontent2(celv, opts, obj){
	 if(obj[3].length>25)
	 {
	     return obj[3].substr(0,25)+"...更多";
	 }
	 return obj[3]; 
} 
//显示详细
function showDetail(celv, opts, obj){
	
	 return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+ obj[0] + '\')">' + celv + '</a>';
} 
function showContentById(id) {
    if (id == null || id == '') {
        showMsg("请选择一条记录!", 2);
    } else {
        $$.openDiv('showd', '推送记录',$$.getContextPath()+'smart/s002record!doShowPushDetail', {'s002PO.ma001' : id});
    }
}  
</script>
</head>

<body>
	<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">
			<s:form action="s002" method="post" id="searchForm" cssClass="formmargin">

					<table border="0" cellspacing="8" cellpadding="0">
						 <tr>
							<td align="right" style="width: 80px;">开始日期</td>
							<td colspan="1" style="width: 200px;">
								<s:textfield id="beginDate" name="s002PO.queryStartDate" cssClass="dateISO Wdate" dateformat="yyyy-MM-dd HH" width="50px"
										onclick="hwDatePicker({maxDate:$dp.$('endDate').value,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true'})"/>
							</td>
							<td colspan="1" style="width: 100px;">
								至
							</td>
							<td colspan="1" style="width: 200px;">
								<s:textfield id="endDate" name="s002PO.queryEndDate" cssClass="dateISO Wdate" dateformat="yyyy-MM-dd" width="50px"
										onclick="hwDatePicker({minDate:$dp.$('beginDate').value,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true'})"/>
							</td>
							<td width="98" rowspan="2">
								<s:submit value="" cssClass="search" />
							</td>
						</tr> 
						<%--  <tr>
			          <td align="right" style="width:76px;">消息标题:</td>
			          <td style="width:300px;"> <s:textfield name="s002PO.ma002" cssClass="input2"/> </td>
			          <td width="98"><s:submit value="" cssClass="search"/></td>
			        </tr> --%>
					</table>
				</s:form>
		</div>
		</div>
	</div>
	<div id="hiddlebutton">
		<a href="#"><img src="images/searchhiddleicon.jpg" width="71" height="13" /></a>
	</div>
	<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="delete"/>
	</div>
	<div class="tablestyle">
		<table id="gridTable"></table>
	</div>
	<div id="showd"></div>
</body>
</html>