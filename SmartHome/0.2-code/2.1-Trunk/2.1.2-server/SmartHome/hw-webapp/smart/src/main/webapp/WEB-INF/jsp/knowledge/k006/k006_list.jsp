<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath %>"/>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS)" executeResult="true"/>
<title><s:text name="职业病知识库" />-<s:text name="application.title" /></title>
<script type="text/javascript">
var gridOption = {
		caption:'职业病表',
		colModel:[
            {label:'', name:'MA001', index:'MA001',hidden:true,hidedlg:true},
			{label:'疾病名称', name:'MA002', index:'MA002',formatter:showcontent},
			{label:'疾病别名', name:'MA003', index:'MA003'},
			{label:'相关系统', name:'MA011', index:'MA011'}

		],
		pk:"MA001",
		formId:'searchForm',
		winTitle:'职业病知识库'
};
function showcontent(celv,opts,obj){
	return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
};
function showContentById(id){
	if(id == null || id == ''){
		showMsg("请选择一条记录!",2,'信息');
	}else{
		$$.openDiv('showcont','安全标准知识详情',$$.getContextPath()+'/knowledge/k006!doView',{'k006PO.MA001':id},function(){
			$$.clearInput('#showcont');
		});
	}
};
</script>
</head>
<body> 
	<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">
	<s:form action="k006" method="post" cssClass="formmargin" id="searchForm"> 
	<table  border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td style="width:50px;"align="right" nowrap="nowrap">疾病名称</td>
							<td style="width:200px;"><s:textfield name="k006PO.MA002" cssClass="input2" /></td>
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
<div class="operating" id="operating"><hwsoft:operation code="${code}" param="add,edit,delete"/></div>	
<div id="showcont"></div>	
</body>
</html>