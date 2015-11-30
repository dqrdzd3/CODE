<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag"%>
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
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS,my97)"
	executeResult="true" />

<title>设备设施信息-<s:text name="application.title" /></title>
<link href="<%=path %>/css/suggest.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	var gridOptions = [{
		caption : '设施信息',
		formId : 'searchForm',
		subGridId : ['gridTable1'],
		id : 'gridTable',
		colModel : [
		            
	    {
			label : '',
			name : 'MA001',
			index : 'MA001',
			hidden:true,
			hidedlg:true
		}, {
			label : '设备名称',
			name : 'MA004',
			index : 'MA004',
			formatter:showcontent
		}, 
		{
			label : '数量',
			name : 'MA021',
			index : 'MA021'
		},
		{
			label : '设备类型',
			name : 'MA006',
			index : 'MA006'
		}, {
			label : '启用日期',
			name : 'MA010',
			index : 'MA010',
			formatter:'date',
			formatoptions:{newformat:'Y-m-d'}
		}, {
			label : '有效截至日期',
			name : 'MA011',
			index : 'MA011',
			formatter:'date',
			formatoptions:{newformat:'Y-m-d'}
		} ],
		pk : "MA001",
		winTitle : "特殊设备信息"
	},{
		id : 'gridTable1',
		caption : '特殊设备年检记录信息',
		po_pId : 'ma002',
		url : $$.getContextPath()+'corpinfo/b00402!queryData.action',
		colModel : [
		            
	    {
			label : '',
			name : 'ma001',
			index : 'ma001',
			hidden:true,
			hidedlg:true
		}, {
			label : '检验机构',
			name : 'ma003',
			index : 'ma003'
			
		}, 
		{
			label : '是否通过检验',
			name : 'ma006',
			index : 'ma006'
		},
		{
			label : '检验结果',
			name : 'ma007',
			index : 'ma007'
		}, {
			label : '周期',
			name : 'ma008',
			index : 'ma008'
			
		}, {
			label : '检验时间',
			name : 'ma005',
			index : 'ma005',
			formatoptions:{newformat:'Y-m-d'}
		} ],
		pk : "ma001",
		rowNum : 5,
		height : 115,
		winTitle : "特殊设备年检记录信息"
		
	}];
	
	var actionOptions = [
	         			{
	         				id : 'operating',
	         				gridId : 'gridTable'
	         				
	         			}, {
	         				id : 'operating1',
	         				gridId : 'gridTable1',
	         				beforeAdd : function(form) {
	         					if ($$.checkSelRow(1)) {
	         						form['b00402PO.ma002'].value = getSelectedRowID('#gridTable')[0];;
	         						return true;
	         					} else {
	         						return false;
	         					}

	         				}
	         			} ];
	function closediv(){
		jQuery(this).dialog("close");
	};
	function showcontent(celv,opts,obj){
		return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
	};
	function showContentById(id){
		if(id == null || id == ''){
			showMsg("至少应该选择一条记录!",5,'错误');
		}else{
			$$.openDiv('showcont','设备设施详情',$$.getContextPath()+'corpinfo/b00401!view',{'b00401PO.MA001':id},function(){
				$$.clearInput('#showcont');
		//		$('#b005addform #MA009').attr('disabled',false);
			});
		}
	};
	
	function closediv(id){
		jQuery("#"+id).dialog('close');
	}
	
</script>
<style type="text/css">
<!--
.radiostyle{
	border: #BFF0ED solid 0px !important;
	border-left-color: #35C0AA;
	border-top-color: #35C0AA;
	border-style:none;
	width: 16px !important;
	height: auto;
}
-->
</style>
</head>
<body>
	<div>
		<div id="searcharea">
			<div id="searcharealeft">
				<div id="searcharearight">
					<s:form action="b00401" method="post" cssClass="formmargin"
						id="searchForm">
						<table border="0" cellspacing="8" cellpadding="0">
							<tr>
								<td align="right" style="width: 100px;">设备名称</td>
								<td colspan="1" style="width: 300px;"><s:textfield id="MA004" cssClass="input2"
										name="b00401PO.MA004" />
								<div id="suggest_input" class="suggestiDiv" style="display: none"></div>		
								</td>
								<td align="right" style="width: 100px;">设备类型</td>
								<td colspan="1" style="width: 300px;">
									<s:select name="b00401PO.MA006" list="codeValueSBLX" listKey="value" id="MA006"
										listValue="name" headerKey="" headerValue="请选择"></s:select>  
								</td>
								<s:hidden name="b00401PO.MA002"/> 
							<td width="98" rowspan="2"><s:submit value=""
										cssClass="search" /></td> 
							</tr>
						</table>
					</s:form>
				</div>
			</div>
		</div>
		<div id="hiddlebutton">
			<a href="#"><img src="images/searchhiddleicon.jpg" width="71"
				height="13" /></a>
		</div>
      <s:iterator var="code" value="codes"  status="s">
			<s:if test="#code == 'QY_JCZLYWHGL_JBQKGL_SBSS'">
				<div class="operating" id="operating">
					<hwsoft:operation code="${code}" param="add,edit,delete"/>
				</div>
				<div class="tablestyle">
					<table id="gridTable">
					</table>
				</div>
			</s:if>
			<s:elseif test="#code == 'QY_JCZLYWHGL_JBQKGL_TSSBNJXX'">
				<div class="operating" id="operating1">
					<hwsoft:operation code="${code}" param="add,edit,delete"/>
				</div>
				<div class="tablestyle">
					<table id="gridTable1">
					</table>
				</div>
			</s:elseif>
	</s:iterator>
		<div id="showcont"></div>
	</div>
</body>
</html>