<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<title><s:text name="危险源详情查看" />-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,utilJS,easytabs,my97,validatorJS,grid,commonJS)" executeResult="true"/>
<script type="text/javascript" src='<%=basePath%>/js/corpinfo/b00101.js' /></script>
<script type="text/javascript" src='<%=basePath%>/js/corpinfo/b00402_details.js' /></script>
</head>
<script type="text/javascript">
var gridOption = 
		{
			id:'gridTable',
			formId:'searchForm',
			url:$$.getContextPath() + 'corpinfo/b00401!queryDate3.action',
			caption:'危险源详情 ',
			colModel:[
				{label:'企业ID', name:'ma001',hidden:true, index:'ma001',hidedlg:true},          
				{label:'危险源ID', name:'dangerId',hidden:true, index:'dangerId',hidedlg:true},
				{label:'危险源类型', name:'typeCode', index:'typeCode',hidden:true,hidedlg:true},
				{label:'企业名称', name:'ma004', index:'ma004',formatter:showcontent},
				{label:'危险源名称', name:'dname', index:'dname',formatter:showcontent1},
				{label:'危险源类型', name:'typeDesc', index:'typeDesc'},
				{label:'危险源级别', name:'wlevel', index:'wlevel'},
				{label:'危险源申报状态', name:'state', index:'state'}
			],
			winTitle:'危险源详情'
		}
;

var actionOption = 
        {
        	init:false,
        	id:'operating',
        	gridId:'gridTable'
        }  	
;

function showcontent1(celv,opts,obj){
	
	return '<a href="javascript:;" style="text-decoration: none;" onclick="fquery(\''+obj[1]+'\',\''+obj[2]+'\',\''+obj[4]+'\')">'+celv+'</a>';
}
</script>
<body> 
<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">
	<s:form  method="post" cssClass="formmargin" id="searchForm"> 
	    <table  border="0" cellspacing="8" cellpadding="0">
			<tr><td>
			<s:hidden  name="b00401PO.typeCode" />
			<s:hidden  name="b00401PO.wlevel" />
			</td></tr>
		</table>
	</s:form>

	</div>
		</div>
	</div>
		<div class="tablestyle">
		<div>
			<table id="gridTable"></table>
		</div>
	</div>
	<div id="showd"></div>
	<div id="showcontent"></div>
	<div id="supershow" ></div>
</body>
</html>