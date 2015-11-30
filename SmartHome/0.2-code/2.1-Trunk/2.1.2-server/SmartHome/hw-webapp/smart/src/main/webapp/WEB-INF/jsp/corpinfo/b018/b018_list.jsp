<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS,my97)" executeResult="true"/>
<title>	应急药品信息-<s:text name="application.title" /></title>

<script type="text/javascript">
var gridOption = {
		caption:'应急药品信息',
		colModel:[
            {label:'', name:'ma001', index:'ma001',hidden:true,hidedlg:true},
			{label:'药品名称', name:'MA004', index:'MA004',formatter:showcontent},
			{label:'应急药品资源代码', name:'MA003', index:'MA003'},
			{label:'管理部门', name:'MA005', index:'MA005'},
			{label:'存放场所', name:'MA010', index:'MA010'},
			{label:'主要用途', name:'MA012', index:'MA012'},
			{label:'使用方法', name:'MA013', index:'MA013'}
		],
		pk:"MA001",
		formId:'searchForm',
		winTitle:'应急药品信息'
};
function showcontent(celv,opts,obj){
	return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
};
function showContentById(id){
	if(id == null || id == ''){
		showMsg("至少应该选择一条记录!",5,'错误');
	}else{
		$$.openDiv('showcont','企业安全机构详情',$$.getContextPath()+'/corpinfo/b018!view',{'b018PO.MA001':id},function(){
			$$.clearInput('#showcont');
			$('#b018addform *').attr('disabled',false);
		});
	}
};
function closediv(id){
	jQuery("#"+id).dialog('close');
}

</script>
</head>
<body>
	<!-- 搜索区 -->
	<div>
		<div id="searcharea">
			<div id="searcharealeft">
				<div id="searcharearight">
					<s:form action="b018!query" method="post" cssClass="formmargin" id="searchForm"> 	
						<s:hidden name="b018PO.MA002"/>
						<table border="0" cellspacing="8" cellpadding="0">
							<tr>
						        <td align="right" style="width:80px;">应急药品名称 </td>
								<td colspan="1" style="width:200px;"> <s:textfield name="b018PO.MA004" cssClass="input2"/> </td>
								<td align="right" style="width:100px;">急救药品资源代码 </td>
								<td colspan="1" style="width:200px;"> <s:textfield name="b018PO.MA003" cssClass="input2"/> </td>
								<td width="98" rowspan="2"><s:submit value="" cssClass="search" /></td>
							</tr>
						</table>
					</s:form>
				</div>
			</div>
		</div>
		<div id="hiddlebutton"><a href="#"><img src="images/searchhiddleicon.jpg" width="71" height="13" /></a></div>
	<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="add,edit,delete"/>
	</div>		
	</div>
			<div id="showcont"></div>
</body>

</html>