<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS,my97)" executeResult="true"/>
<script type="text/javascript" src='<s:url value="/js/util/links.js"></s:url>'></script>
<title><s:text name="规章制度知识库" />-<s:text name="application.title" /></title>

<script type="text/javascript">
var gridOption = {
		caption:'企业规章制度',
		colModel:[
            {label:'', name:'MA001', index:'MA001',hidden:true,hidedlg:true},
			{label:'制度名称', name:'MA004', index:'MA004',formatter:showcontent},
			{label:'编制人', name:'MA009', index:'MA009'},
			{label:'编制单位', name:'MA006', index:'MA006'},
			{label:'是否上报', name:'MA013D', index:'MA013D'},
			{label:'是否上报', name:'MA013', index:'MA013',hidden:true,hidedlg:true},
			{label:'编制日期', name:'MA008', index:'MA008',formatter:'date',formatoptions:{srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d'}}
		],
		pk:"MA001",
		formId:'searchForm',
		winTitle:'企业规章制度'
};
var actionOption = {
		validate:true,
		gridId:'gridTable',
		 beforeAdd : function(form) {
	    	   $("#img",form).attr("src","<%=path%>/upload/fileupload!doAddupload?MA002=")
			},
	         afterFillForm : function(id, jsonPO, form) {
	        	 var id=jsonPO.MA001;
				 $("#img",form).attr("src","<%=path%>/upload/fileupload!doAddupload?MA002="+id)
			},
			beforeEdit : function() {
	        	var idArray=getSelectedRowID("#gridTable");
                for(var i = 0 ; i < idArray.length ; i ++ ){
                    if(jQuery('#gridTable').jqGrid('getCell',idArray[i]+'','MA013')!=0){
                        showMsg("所选数据有已上报数据，不能修改，请重新选择!",4,'');
                        return false;
                    };
                }
	        },
	        beforeDelete : function() {
	        	var idArray=getSelectedRowID("#gridTable");
                for(var i = 0 ; i < idArray.length ; i ++ ){
                    if(jQuery('#gridTable').jqGrid('getCell',idArray[i]+'','MA013')!=0){
                        showMsg("所选数据有已上报数据，不能删除，请重新选择!",4,'');
                        return false;
                    };
                }
	        },
		buttonInited : function() {
	    	$$.bindButton('view', function(){
	    		var gridId = "gridTable";
	            gridIdSelector="#"+gridId;
		        if(!$$.checkSelRow(1,gridId)) return;
		        var ids = getSelectedRowID(gridIdSelector);
		        var id = ids[0];
		    
		       var divId = 'tregulationDiv',
		       title = '规则制度信息',
		       url = 'knowledge/tregulation!doView',
		       param='',
		       callback=function(){};
		       
		       param += 'tregulationPO.MA001='+id;
		       $$.openDiv(divId, title, url, param, callback);
		    });
	
		}
};

function showcontent(celv,opts,obj){
	return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
};
function showContentById(id){
	if(id == null || id == ''){
		showMsg("至少应该选择一条记录!",5,'错误');
	}else{
		$$.openDiv('showcont','规章制度详情',$$.getContextPath()+'knowledge/tregulation!view',{'tregulationPO.MA001':id},function(){
			$$.clearInput('#showcont');
		//	$('#b005addform #MA009').attr('disabled',false);
		});
	}
};
function closediv(id){
	jQuery("#"+id).dialog('close');
}


function hwsafesbsj() {
	var ids = getSelectedRowID("#gridTable");
	if (ids.length < 1) {
		showMsg('请选中一条记录', 2);
		return;
	}
	for ( var i = 0; i < ids.length; i++) {
		var flag = jQuery('#gridTable').jqGrid('getCell', ids[i], 'MA013');
		if (flag == 0) {
			continue;
		} else {
			showMsg('提交的数据中有已经申报的数据，请重新选择!', 2);
			return false;
		}
	}
	jQuery.post($$.getContextPath() + 'knowledge/tregulation!doGetUpdateSBSJ', {
		ids : '' + ids
	}, function(msg) {
		showMsg(msg.content, msg.type);
		reloadGrid('gridTable');
	});
}
</script>
</head>
<body>
	
	<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">
				<s:form action="tregulation!query" method="post" cssClass="formmargin" id="searchForm">
					<s:hidden name="ORGAN_UUID" value="%{#parameters.ORGAN_UUID[0]}"/>
					<s:hidden name="govType" value="%{#parameters.govType[0]}"/>
					<s:hidden name="tregulationPO.MA002"/>
					<table  border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td align="right" style="width:76px;" nowarp="nowarp">制度名称</td>
							<td style="width:200px;"><s:textfield name="tregulationPO.MA004" cssClass="input2" /></td>
							
							<td align="right" style="width:50px;" nowarp="nowarp">编制单位</td>
							<td style="width:200px;"><s:textfield name="tregulationPO.MA006" cssClass="input2" /></td>
						
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
	<div class="operating" id="operating"><hwsoft:operation code="${code}" param="add,edit,delete,sbsj"/></div>
	<div id="showcont"></div>
</body>
</html>