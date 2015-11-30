<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<title><s:text name="法律法规" />-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,my97,validatorJS,utilJS,grid,commonJS)" executeResult="true"/>
<script type="text/javascript" src='<s:url value="/js/knowledge/k008.js"></s:url>'></script>
</head>
<body> 
<div id="searcharea">
	<div id="searcharealeft">
		<div id="searcharearight">
			<s:form  method="post" cssClass="formmargin" id="searchForm"> 
			    <table  border="0" cellspacing="8" cellpadding="0">
					<tr>
						<td style="width:60px;"align="right" nowrap="nowrap">法律名称</td>
						<td style="width:200px;"><s:textfield name="k008PO.ma002" cssClass="input2" /></td>
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
<div class="operating" id="operating">
	<hwsoft:operation code="${code}" param="add,edit,delete"/>
</div>
<div class="tablestyle">
	<div><table id="gridTable"></table></div>
</div>
<div id="showd"></div>
</body>
</html>