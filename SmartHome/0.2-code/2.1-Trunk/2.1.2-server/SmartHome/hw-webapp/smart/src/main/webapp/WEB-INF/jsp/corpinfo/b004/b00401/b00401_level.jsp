<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>危险源类别统计</title>
<s:action name="include(mainCSS,jquery,plotCharts,commonJS,validatorJS,grid,utilJS)" executeResult="true" />
<script type="text/javascript">
	jQuery(function(){
		initPlot("pie1", $$.getContextPath()+'corpinfo/b00401!doGroupLevel.action', null, 'pie','3d', {
			width : '700',
			height : '400'
		});
		
		jQuery("a[id^=wlevel]").click(function(){
			var typecode = jQuery(this).attr("id").substring(7);
			var url = $$.getContextPath()+'corpinfo/b00401!doGetDetails?b00401PO.wlevel='+typecode;
			$$.getFrame('rightFrame').Tabs.openTab(typecode,'危险源详情',url);
		});
	});
</script>
</head>
<body>
<div class="countcontent">
<div class="counttitle">危险源级别统计</div>
    <div id="pie1"></div>
	<div class="counttitle" id="counttitle">
	<table border="1" align="center" class="tablecss1" style="width: 95%">
		<tr><s:iterator id="mapList" var="m" value="mapList"><th><s:property value="CD"/></th></s:iterator></tr>
		<tr>
		<s:iterator id="mapList" var="m" value="mapList">
			<td>
				<s:if test="CV ==0"><s:property value="CV"/></s:if><s:else>
				<a href="javascript:void(0)" id="wlevel_<s:property value='WLEVEL'/>" ><s:property value="CV"/></a></s:else>
			</td>
		</s:iterator>
		</tr>
	</table>
	</div>
	</div>
</body>
</html>