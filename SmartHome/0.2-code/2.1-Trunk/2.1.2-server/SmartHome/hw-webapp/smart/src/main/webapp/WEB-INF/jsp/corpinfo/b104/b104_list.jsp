<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<title><s:text name="应急救援-应急资源" />-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,my97,validatorJS,grid,commonJS)" executeResult="true"/>
<script type="text/javascript">
var gridOption = {
        id:'gridTable',
        formId:'searchForm',
        caption:'修改申请记录表',
        colModel:[
            {label:'主键ID', name:'ma001',hidden:true, index:'ma001',hidedlg:true},          
            {label:'企业ID', name:'ma002',hidden:true, index:'ma002',hidedlg:true},          
            {label:'申请人', name:'MA003d', index:'MA003d'},
            {label:'申请企业', name:'MA004d', index:'MA004d'},
            {label:'申请理由', name:'ma004', index:'ma004'},
            {label:'申请时间', name:'ma005d', index:'ma005d'},
            {label:'内容', name:'ma006', index:'ma006'},
            {label:'表名', name:'ma007', index:'ma007'},
            {label:'状态', name:'MA008D', index:'MA008D'}, 
            {label:'状态', name:'ma008',hidden:true, index:'ma008',hidedlg:true}
        ],
        winTitle:'修改申请记录表'
    };

var actionOption = {
		init:false,
        id:'operating',
        gridId:'gridTable'
    };

$(function(){
    $$.bindButton('delete',function(){
   	 	var blag = true;
        var gridId="gridTable";
        if ($$.checkSelRow(0)) return;
        var ids = getSelectedRowID("#"+gridId);
        for(var i = 0 ; i < ids.length; i++){
        	if(jQuery("#"+gridId).jqGrid('getCell',ids[i],'ma008')=='0'){
        		showMsg('您选择的删除数据中有待审核数据，不能进行删除，请重新选择！',4);
        		blag = false;
        		break;
        	}
        }
        if(blag){
	         var url = $$.getContextPath()+'corpinfo/b104!doGetDelete',
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
	     }
   });	
});


function spsj(){
	if ($$.checkSelRow(0)) return;
	var divId = "showd",
		title = "审批数据",
		url = $$.getContextPath()+"corpinfo/b104!doGetShow",
		param = {'b104PO.ma001' : ''+ getSelectedRowID("#gridTable")},
		callback = function(){};
     $$.openDiv(divId, title,url,param,callback);
}

function passpetition(id){
	var url = $$.getContextPath()+"services/b10401!doGetPetition?b104PO.ma001="+id,
	callback = function(data){
		if(data.type==1){
				showMsg(data.content,data.type,{buttons:{
					'确定':function(){
							$(this).dialog('close');
							$("#showd").dialog('close');
							reloadGrid("gridTable");
						}
				}});
			}else{
				showMsg(data.content,data.type);
			}
		
	};
	$.post(url,callback);
}
//弹出审核不通过的页面
function refuse(id){
	var url = $$.getContextPath()+"corpinfo/b104!doGetRefuseAdd?b104PO.ma001="+id,
		divid='showdiv',
		title='审核不通过',
		param='',
		callback = function(){
			
		};
	$$.openDiv(divid, title, url, param, callback);
}
//把不通过的理由通过pushlet发给申请人
function refuseAdd(){
	var refuseMsg = $("#refuseMsg").val(),
		id = $("#b104Id").val(),
	    url = $$.getContextPath()+"corpinfo/b104!doGetRefuseSaveAdd?b104PO.ma001="+id+"&refuseMsg="+refuseMsg,
		callback = function(data){
		showMsg(data.content,data.type,{buttons:{
            '确定':function(){
                $(this).dialog('close');
                closeDiv();
                closeShowd();
                reloadGrid("gridTable");
            }
        }});
	};
	$.post(url,callback);
}

function closeDiv(){
	$("#showdiv").dialog('close');
}
function closeShowd(){
	$("#showd").dialog('close');
}

</script>
</head>
<body> 
<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">
	<s:form  method="post" cssClass="formmargin" id="searchForm"> 
	    <table  border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td style="width:60px;"align="right" nowrap="nowrap">申请企业</td>
							<td style="width:200px;"><s:textfield name="" cssClass="input2" /></td>
							<td width="98" rowspan="2"><s:submit value="" cssClass="search" /></td>
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
		<hwsoft:operation code="${code}" param="delete,spsj"/>
	</div>
		<div class="tablestyle">
		<div>
			<table id="gridTable"></table>
		</div>
	</div>
	<div id="showd" style="display: none;"></div>
	<div id="showdiv" style="display: none;"></div>
</body>
</html>