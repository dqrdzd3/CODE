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
<title><s:text name="法律法规库" />-<s:text name="application.title" /></title>
<script type="text/javascript">
var gridOption = {
		caption:'安全法律法规知识库表',
		colModel:[
            {label:'', name:'ma001', index:'ma001',hidden:true,hidedlg:true},
			{label:'法规名称', name:'ma002', index:'ma002',formatter:showcontent},
			{label:'法规编号', name:'ma003', index:'ma003'},
			{label:'法规类别', name:'ma004', index:'ma004'},
			{label:'发布日期', name:'ma005', index:'ma005'},
			{label:'实施日期', name:'ma006', index:'ma006'},
			{label:'颁布部门', name:'ma009', index:'ma009'},
			{label:'颁布地区', name:'ma010', index:'ma010'}
			
		],
		formId:'searchForm',
		winTitle:'安全法律法规知识库'
};
var actionOption = {
		 beforeAdd : function(form) {
	    	   $("#img",form).attr("src","<%=path%>/upload/fileupload!doAddupload?MA002=")
			},
	         afterFillForm : function(id, jsonPO, form) {
	        	 var id=jsonPO.ma001;
				 $("#img",form).attr("src","<%=path%>/upload/fileupload!doAddupload?MA002="+id)
			},
}
function showcontent(celv,opts,obj){
	return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
};
function showContentById(id){
	if(id == null || id == ''){
		showMsg("请选择一条记录!",2,'信息');
	}else{
		$$.openDiv('showcont','法律法规详情',$$.getContextPath()+'/knowledge/k003!doView',{'k003PO.ma001':id},function(){
			$$.clearInput('#showcont');
		});
	}
};
function closeDiv(){
	$('#operating_input_form').dialog('close');
};
</script>
</head>
<body> 
	<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">
			<s:form action="k003" method="post" cssClass="formmargin" id="searchForm"> 
					<table border="0" cellspacing="8" cellpadding="0">
			        <tr>
			          <td align="right" style="width:76px;">法律法规名称 </td>
			          <td style="width:300px;"> <s:textfield name="k003PO.ma002" cssClass="input2"/> </td>
			          
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
		<hwsoft:operation code="${code}" param="add,edit,delete"/>
	</div>
	<div id="showcont"></div>
</body>
</html>