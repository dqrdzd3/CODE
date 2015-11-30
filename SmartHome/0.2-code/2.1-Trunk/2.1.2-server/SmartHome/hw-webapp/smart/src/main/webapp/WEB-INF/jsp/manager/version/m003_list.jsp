<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<s:action name="include(mainCSS,jquery,grid,commonJS)" executeResult="true"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src='<s:url value="/js/util/links.js" ></s:url>' ></script>
<script type="text/javascript" src='<s:url value="/js/manager/device/m003.js" ></s:url>' defer=true></script>
</head>

<body>
	<div id="searcharea" style="margin-left:10px;">
		<div id="searcharealeft">
			<div id="searcharearight">
				<s:form action="" method="post" cssClass="formmargin" name="searchForm" id="searchForm"> 
					<table border="0" cellspacing="8" cellpadding="0">
				      <tr>
				        <td align="right" style="width:50px;"  nowrap="nowrap">版本名称</td>
				          <td style="width:250px;" nowrap="nowrap">
					          	<s:textfield name="m003PO.MA002" cssClass="input2"/>
					       </td>
				          <td  colspan="2" align="left" ><s:submit value="" cssClass="search"/></td>
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
		<hwsoft:operation code="${code}" param="view,add,edit,delete"/>
	</div>
	<div style="display: none;" id="m003Div"></div>
	
</body>
</html>