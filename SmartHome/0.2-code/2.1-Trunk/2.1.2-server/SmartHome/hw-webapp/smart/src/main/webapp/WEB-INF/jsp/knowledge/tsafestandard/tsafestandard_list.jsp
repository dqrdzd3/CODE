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
<title><s:text name="安全法律法规标准" />-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS)" executeResult="true"/>

<script type="text/javascript">
var gridOption = {
		caption:'事故案例知识库',
		colModel:[
			{label:'标准名称', name:'name', index:'name'},
			{label:'标准编号', name:'code', index:'code'},
			{label:'标准类别', name:'type', index:'type'},
			{label:'发布日期', name:'pubDate', index:'pubDate'},
			{label:'实施日期', name:'takeDate', index:'takeDate'},
			{label:'状态', name:'status', index:'status'},
			{label:'取消日期', name:'cancelDate', index:'cancelDate'},
			{label:'颁布部门', name:'pubdept', index:'pubdept'}

		],
};

</script>
</head>
<body>

<div>
<div id="searcharea">
  <div id="searcharealeft">
    <div id="searcharearight">
		<s:form action="tsafestandard!query" method="post" cssClass="formmargin" id="searchForm"> 	
			<table border="0" cellspacing="8" cellpadding="0">
	        <tr>
	          <td align="right" style="width:50px;">生产标准 </td>
	          <td style="width:300px;"> <s:textfield name="tsafestandardPO.name" cssClass="input2"/> </td>
	          
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
		<ul>
			<s:property value="actionButtons" escape="false"/>
		</ul>
	</div>
	
<div id="tablestyle">
	<table id="gridTable"></table>
</div>
</body>
</html>