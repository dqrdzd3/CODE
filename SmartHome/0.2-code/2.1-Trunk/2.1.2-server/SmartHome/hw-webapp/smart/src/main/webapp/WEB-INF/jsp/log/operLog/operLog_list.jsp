<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<s:action name="include(mainCSS,jquery,ztree,validatorJS,grid,commonJS,my97)" executeResult="true" />
<script type="text/javascript" src="<s:url value="/js/log/log.js"/>"></script>

<script type="text/javascript">

var gridOption = {
		caption:'操作日志',
		colModel:[
			{label:'id', name:'ID', index:'ID', hidden:true, hidedlg:true},
			{label:'日志编号', name:'CODE', index:'CODE', formatter:showcontent},
			{label:'操作人ID', name:'OPERATOR_ID', index:'OPERATOR_ID',hidden:true,hidedlg:true},
			{label:'操作人姓名', name:'OPERATOR_NAME', index:'OPERATOR_NAME'},
			{label:'记录时间', name:'RECORDING_TIME', index:'RECORDING_TIME', formatter:'date', formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}},
			{label:'类名', name:'CLASS_NAME', index:'CLASS_NAME'},
			{label:'方法名', name:'METHOD_NAME', index:'METHOD_NAME'},
			{label:'内容', name:'CONTENT', index:'CONTENT'},
			{label:'IP', name:'IP', index:'IP'}
		],
		pk:'ID',
		formId:'searchForm',
		winTitle:'操作日志 ',
		multiselect:false
};

function showContentById(id){
	if(id == null || id == ''){
		showMsg("至少应该选择一条记录!",5,'错误');
	}else{
		$$.openDiv('logDiv','查看操作日志','log/operLog!doView',{'logId':id},function(){});
	}
};

var actionOption = {
		init:false
};

</script>
</head>

<body>

	<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">

				<s:form action="log/operLog" method="post" id="searchForm" cssClass="formmargin">

					<table border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td style="width: 50px;" align="right">
								日志编号
							</td>
							<td colspan="4" style="width: 200px;">
								<s:textfield cssStyle="width: 200px;" name="operLogPO.code" cssClass="input2"/>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 80px;">开始日期</td>
							<td colspan="1" style="width: 200px;">
								<s:textfield id="beginDate" name="operLogPO.queryStartDate" cssClass="dateISO Wdate" dateformat="yyyy-MM-dd HH" width="50px"
										onclick="hwDatePicker({maxDate:$dp.$('endDate').value,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true'})"/>
							</td>
							<td colspan="1" style="width: 100px;">
								至
							</td>
							<td colspan="1" style="width: 200px;">
								<s:textfield id="endDate" name="operLogPO.queryEndDate" cssClass="dateISO Wdate" dateformat="yyyy-MM-dd" width="50px"
										onclick="hwDatePicker({minDate:$dp.$('beginDate').value,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true'})"/>
							</td>
							<td width="98" rowspan="2">
								<s:submit value="" cssClass="search" />
							</td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>
	</div>
	<div id="hiddlebutton">
		<a href="#">
			<img src="images/searchhiddleicon.jpg" width="71" height="13" />
		</a>
	</div>
	<div id="logDiv" style="display:none;"></div>
</body>
</html>