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
<title>企业资质证书信息-<s:text name="application.title" /></title>

<script type="text/javascript">
var gridOption = {
		caption:'企业证照',
		formId:'searchForm',
		colModel:[
            {label:'', name:'MA001', index:'MA001',hidden:true,hidedlg:true},
			{label:'执照名称', name:'MA004', index:'MA004',formatter:showcontent},
			{label:'执照编号', name:'MA002', index:'MA002'},
			{label:'发证单位', name:'MA005', index:'MA005'},
			{label:'有效期', name:'MA007', index:'MA007'},
			{label:'是否上报', name:'MA010D', index:'MA010D'},
			{label:'是否上报', name:'MA010', index:'MA010',hidden:true,hidedlg:true}
		],
		pk:"MA001",
		winTitle:"企业证照"
};
var actionOption={
       beforeAdd : function(form) {
    	   $("#img",form).attr("src","<%=path%>/upload/initfileuploadnew?MA002=");
		},
		beforeEdit : function() {
        	var idArray=getSelectedRowID("#gridTable");
            for(var i = 0 ; i < idArray.length ; i ++ ){
                if(jQuery('#gridTable').jqGrid('getCell',idArray[i]+'','MA010')!=0){
                    showMsg("所选数据有已上报数据，不能修改，请重新选择!",4,'');
                    return false;
                };
            }
        },
        beforeDelete : function() {
        	var idArray=getSelectedRowID("#gridTable");
            for(var i = 0 ; i < idArray.length ; i ++ ){
                if(jQuery('#gridTable').jqGrid('getCell',idArray[i]+'','MA010')!=0){
                    showMsg("所选数据有已上报数据，不能删除，请重新选择!",4,'');
                    return false;
                };
            }
        },
         afterFillForm : function(id, jsonPO, form) {
        	 var id=jsonPO.MA001;
			 $("#img",form).attr("src","<%=path%>/upload/initfileuploadnew?MA002="+id);
		}
};
function showcontent(celv,opts,obj){
	return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
}
function showContentById(id){
	if(id == null || id == ''){
		showMsg("至少应该选择一条记录!",5,'错误');
	}else{
		$$.openDiv('showcont','企业证照详情',$$.getContextPath()+'/corpinfo/b005!view',{'b005PO.MA001':id},function(){
			$$.clearInput('#showcont');
			$('#b005addform #MA009').attr('disabled',false);
		});
	}
}
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
		var flag = jQuery('#gridTable').jqGrid('getCell', ids[i], 'MA010');
		if (flag == 0) {
			continue;
		} else {
			showMsg('提交的数据中有已经申报的数据，请重新选择!', 2);
			return false;
		}
	}
	jQuery.post($$.getContextPath() + 'corpinfo/b005!doGetUpdateSBSJ', {
		ids : '' + ids
	}, function(msg) {
		showMsg(msg.content, msg.type);
		reloadGrid('gridTable');
	});
}
</script>
</head>
<body>
	<div>
		<div id="searcharea">
			<div id="searcharealeft">
				<div id="searcharearight">
					<s:form action="b005!query" method="post" cssClass="formmargin" id="searchForm">
						<s:hidden name="b005PO.MA003"></s:hidden>
						<input type="hidden" name="govType" value="${param.govType}"/>
						<table  border="0" cellspacing="8" cellpadding="0">
							<tr>
								<td align="right" style="width: 50px;">执照名称</td>
								<td colspan="1" style="width:300px;"><s:textfield name="b005PO.MA004" cssClass="input2" /></td>
								<td align="right" style="width: 50px;">发证单位</td>
								<td colspan="1" style="width:200px;"><s:textfield name="b005PO.MA005" cssClass="input2" /></td>
								<td width="98" rowspan="2"><s:submit value="" cssClass="search" /></td>
						</table>
					</s:form>
				</div>
			</div>
		</div>
		<div id="hiddlebutton">
			<a href="#"><img src="images/searchhiddleicon.jpg" width="71" height="13" /></a>
		</div>
	<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="add,edit,delete,sbsj"/>
	</div>
	<div id="showcont"></div>
	</div>
</body>
</html>