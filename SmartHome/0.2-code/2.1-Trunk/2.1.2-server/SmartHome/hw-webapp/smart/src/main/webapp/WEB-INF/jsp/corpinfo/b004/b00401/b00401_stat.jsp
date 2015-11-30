<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<s:action
	name="include(mainCSS,jquery,datepicker,validatorJS,grid,commonJS,plotCharts,utilJS)"
	executeResult="true" />

<script type="text/javascript">
	jQuery(function() {
		initPlot("pied", 'b00401!doGetstatChart', null, 'pie', '3d', {
			width : '700',
			height : '430'
		});
		jQuery("a[id^=typecode]").click(function(){
			var typecode = jQuery(this).attr("id").substring(9);
			var name = jQuery(this).attr("title");
			var url = $$.getContextPath()+'corpinfo/b00401!doGetB00401Details?b00401PO.MA006='+typecode;
			$$.getFrame('rightFrame').Tabs.openTab(typecode,name+'详情',url);
		});
	});
</script>
</head>
<body>

	<div class="countcontent">
		<div class="counttitle">设备统计</div>
		<div class="counttitle" id="counttitle"></div>

		<!-- 图形表 -->
		<div id="pied"></div>

		<div>
			<!-- 数据表 -->
			<div>
				<table border="0" align="center" cellpadding="4" cellspacing="0"
					class="tablecss1" style="width: 90%">
					<tr>
						<th>设备类型</th>
						<th>数量</th>
					</tr>
					<s:iterator value="list">
						<tr>
						<s:url var="urlId" namespace="/corpinfo" action="b00401" method="doGetB00401Details">
    				    <s:param name="b00401PO.MA006" value="%{typeCode}"/>
				   </s:url>
				 
							<td>${CD}</td>
							<td><s:if test="CV ==0"><s:property value="CV"/></s:if><s:else>
<%-- 				<s:a href="%{urlId}"><s:property value="CV"/></s:a></s:else></td> --%>
				<a href="javascript:void(0)" id="typecode_<s:property value='typeCode'/>" title="<s:property value="CD"/>" ><s:property value="CV"/></a></s:else>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>

	</div>
	<!-- 钻取数据层 -->
</body>
</html>