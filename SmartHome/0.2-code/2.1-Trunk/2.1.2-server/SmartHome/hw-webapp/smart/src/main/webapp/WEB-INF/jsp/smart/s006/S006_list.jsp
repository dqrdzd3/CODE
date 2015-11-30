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
<title>	用户反馈管理-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS,my97)" executeResult="true"/>
<script type="text/javascript">
var gridOption = {
		id : 'gridTable',
		caption:'用户反馈管理',
		colModel:[
            {label:'', name:'ma001', index:'ma001',hidden:true,hidedlg:true},
			{label:'用户名', name:'ma009', index:'ma009',formatter:showDetail},
			{label:'反馈内容', name:'ma002', index:'ma002',formatter:showcontent1},
			{label:'回复内容', name:'ma05', index:'ma005',formatter:showcontent2},
			{label:'回复状态', name:'ma06', index:'ma006',formatter:showZh},
			{label:'创建时间', name:'ma003', index:'ma003'}
		],
		formId:'searchForm',
		winTitle:'用户反馈管理'
};
var actionOption ={init:true };

$(function(){
	$$.bindButton('delete',function(){
        var gridId="gridTable";
        if ($$.checkSelRow(0)) return;
        var ids = getSelectedRowID("#"+gridId);
	     var url = $$.getContextPath()+'smart/s006!doDelete',
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
//0：未回复，1：已回复
function showZh(celv, opts, obj){
	if(obj[4]==0)
	{
	    return "未回复";
	}
	 return "已回复";
} 
//弹出回复框
 function reply()
{
	 var i = getSelectedRowID("#gridTable").length;
     if (i == 0) {
         showMsg("请选择一条记录!", 2);
         return;
     } else if (i > 1) {
         showMsg("请选择一条记录!", 2);
         return;
     } else {
    	 
         $$.openDiv('showd', '用户反馈管理',$$.getContextPath()+'smart/s006!doShow', {'s006po.ma001' : ''+ getSelectedRowID("#gridTable")}, function() {
           });
     }
}

//格式化反馈内容的字符串长度
 function showcontent1(celv, opts, obj){
 	 if(obj[2].length>15)
 		 {
 		     return obj[2].substr(0,15)+"...更多";
 		 }
 	 return obj[2];  
 } 
//格式化回复内容的字符串长度
 function showcontent2(celv, opts, obj){
	var content=obj[3];
	if(content==null)
	{
		content=" ";
	}
 	 if(content.length>15)
     {
 		content=content.substr(0,15)+"...更多";
 	 }
 	 return content;  
 } 

//点击弹出框上的回复
 function doReply(){
		if($('#s006Reply').valid()){
			var param = $('#s006Reply').serialize();
			var url = $$.getContextPath()+'smart/s006!doReply';
			$.post(url,param,function(data){
				showMsg("回复成功", data.type, {
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
function closeshowd(){
	$("#showd").dialog('close');
} 
//显示详细
function showDetail(celv, opts, obj){
	
	 return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+ obj[0] + '\')">' + celv + '</a>';
} 
function showContentById(id) {
    if (id == null || id == '') {
        showMsg("请选择一条记录!", 2);
    } else {
        $$.openDiv('showd', '用户反馈',$$.getContextPath()+'smart/s006!doShowDetail', {'s006po.ma001' : id});
    }
}  
</script>
</head>

<body>
	<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">
			<s:form action="s006" method="post" id="searchForm" cssClass="formmargin">
					<table border="0" cellspacing="8" cellpadding="0">
					 <tr>
			          <td align="right" style="width:76px;">用户名:</td>
			          <td style="width:300px;"> <s:textfield name="s006po.ma009" cssClass="input2"/> </td>
			          <td align="right" style="width:76px;">回复状态:</td>
			          <td style="width:300px;"> 
					       <s:select  name="s006po.ma006" 
								       headerKey="-1" headerValue="请选择" 
								       list="#{'0':'未回复', '1':'已回复'}"/>
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
		<hwsoft:operation code="${code}" param="reply,delete"/>
	</div>
	<div class="tablestyle">
		<table id="gridTable"></table>
	</div>
	<div id="showd"></div>
</body>
</html>