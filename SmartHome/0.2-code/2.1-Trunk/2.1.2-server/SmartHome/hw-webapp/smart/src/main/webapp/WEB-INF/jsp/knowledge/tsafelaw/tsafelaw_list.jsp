<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
<title><s:text name="安全法律法规" />-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS)" executeResult="true"/>

<script type="text/javascript">
var gridOption = {
		caption:'安全法律法规知识库表',
		colModel:[
			{label:'标准名称', name:'NAME', index:'NAME'},
			{label:'标准编号', name:'CODE', index:'CODE'},
			{label:'标准类别', name:'TYPE', index:'TYPE'},
			{label:'发布日期', name:'PUBDATE', index:'PUBDATE'},
			{label:'实施日期', name:'TAKEDATE', index:'TAKEDATE'},
			{label:'颁布部门', name:'PUBDEPT', index:'PUBDEPT'},
			{label:'颁布地区', name:'AREA', index:'AREA'}

		],		
		pk:'objId'
};
var actionOption = {
		poName:'tsafelawPO',

			table:'tsafelaw'
	};
</script>
</head>
<body>
<div>
<div id="searcharea">
  <div id="searcharealeft">
    <div id="searcharearight">
		<s:form action="tsafelaw!query" method="post" cssClass="formmargin" id="searchForm"> 	
			<table border="0" cellspacing="8" cellpadding="0">
	        <tr>
	          <td align="right" style="width:76px;">法律法规名称 </td>
	          <td style="width:300px;"> <s:textfield name="tsafelawPO.name" cssClass="input2"/> </td>
	          
	          <td width="98"><s:submit value="" cssClass="search"/></td>
	        </tr>
	      </table>
		</s:form>
	    </div>
  </div>
</div>
<div id="hiddlebutton"><a href="#"><img src="images/searchhiddleicon.jpg" width="71" height="13" /></a></div>
	<div class="operating" id="operating"><hwsoft:operation code="${code}" param="view,add,edit,delete"/></div>
</div>
	
	<div id="operating">
		<s:property value="actionButtons" escape="false"/>
	</div>
	

<div id="tablestyle">
 	<table id="gridTable"></table>
</div>
</body>
</html>