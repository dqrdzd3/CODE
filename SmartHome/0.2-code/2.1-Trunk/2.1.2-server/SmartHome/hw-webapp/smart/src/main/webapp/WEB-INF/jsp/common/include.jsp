<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	request.setAttribute("names", request.getParameter("name") != null ? request.getParameter("name").trim().split("\\s*,\\s*") : new String[]{});
%>
<s:set name="names" value="#request.names"/>

<script type="text/javascript">
<!--
	(window.$$ = window.$$ || {}).ctx = '<%=request.getContextPath()%>/';
//-->
</script>


<%-- mainCSS --%>
<s:if test="'mainCSS' in #names">
	<link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css" />
</s:if>

<%-- validatorJS --%>
<s:if test="'validatorJS' in #names">
	<script type="text/javascript" src="<s:url value="/js/validator.js"/>"></script>
</s:if>

<%-- jqueryJS --%>
<s:if test="'jqueryJS' in #names">
	<script type="text/javascript" src="<s:url value="/res/jquery-1.7.2.min.js"/>"></script>
</s:if>
<%-- jquery --%>
<s:if test="'jquery' in #names">
	<script type="text/javascript" src="<s:url value="/res/jquery-1.7.2.min.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/js/jquery.validate.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/js/jquery.metadata.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/js/jquery.validate.self.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/js/json2form.js"/>"></script>
	<link type="text/css" rel="stylesheet" href="<s:url value="/res/jquery-ui-1.8.2/themes/redmond/jquery-ui-1.8.2.custom.css"/>"/>
	<script type="text/javascript" src="<s:url value="/res/jquery-ui-1.8.2/jquery-ui.js"/>"></script>
	
	<%-- 解决IE6 下拉列表悬浮于弹出层之上的问题 。(20130521 add by mfb) --%>
	<!--[if IE 6]> 
		<script type="text/javascript" src="<s:url value="/js/jquery.bgiframe.js"/>"></script>
	<![endif]-->
</s:if>

<s:if test="'flowplayer' in #names">
	<script type="text/javascript" src="<s:url value="/js/flowplayer-3.2.11.min.js"/>"></script>
    
</s:if>
<s:if test="'plexpaper' in #names">
	<script type="text/javascript" src="<s:url value="/res/flexpaper/swfobject/swfobject.js"/>"></script>
</s:if>

<%-- easyui --%>
<s:if test="'easyui' in #names">
	<link  type="text/css" rel="stylesheet" href="<s:url value="/res/jquery-easyui-1.2.6/themes/default/easyui.css"/>"/>
	<link  type="text/css" rel="stylesheet" href="<s:url value="/res/jquery-easyui-1.2.6/themes/icon.css"/>"/>
	<script type="text/javascript" src="<s:url value="/res/jquery-easyui-1.2.6/jquery.easyui.min.js"/>"></script>
</s:if>

<!-- tabs easy ui  -->
<s:if test="'easytabs' in #names">
<%-- 表单tab带滚动条样式
	<link  type="text/css" rel="stylesheet" href="<s:url value="/res/jquery-easyui-1.2.6/themes/default/easyui.css"/>"/>
	<link  type="text/css" rel="stylesheet" href="<s:url value="/res/jquery-easyui-1.2.6/themes/icon.css"/>"/>
	<script type="text/javascript" src="<s:url value="/res/jquery-easyui-1.2.6/easyloader.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/res/jquery-easyui-1.2.6/plugins/jquery.panel.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/res/jquery-easyui-1.2.6/plugins/jquery.tabs.js"/>"></script>
 --%>
 <%-- 表单tab自动换行样式   --%>
	<script type="text/javascript" src="<s:url value="/js/commons/multiLineTab.js"/>"></script>
</s:if>

<%-- tab验证提醒 --%>
<s:if test="'tabTip' in #names">
	<script type="text/javascript" src="<s:url value="/js/commons/tabTip.js"/>"></script>
</s:if>


<s:if test="'my97' in #names">
	<script type="text/javascript" src="<s:url value="/res/My97DatePicker/WdatePicker.js"/>"></script>
	<script type="text/javascript" src='<s:url value="/js/util/jquery.hw_dateutil.js"></s:url>'></script>
	<script type="text/javascript" src="<s:url value="/js/util/jquery.hw_my97.js"/>"></script>
	
</s:if>

<%-- grid --%>
<s:if test="'grid' in #names">
	<script type="text/javascript" src="<s:url value="/res/jquery.jqGrid-4.3.3/js/i18n/grid.locale-cn.js"/>"></script>
	
	<link type="text/css" rel="stylesheet" href="<s:url value="/res/jquery.jqGrid-4.3.3/plugins/ui.multiselect.css"/>"/>
	<script type="text/javascript" src="<s:url value="/res/jquery.jqGrid-4.3.3/plugins/ui.multiselect.js"/>"></script>
	
<%-- 将jqGrid版本升级到4.4.4解决google浏览器滚动条的问题   --%>
<%-- 	<link type="text/css" rel="stylesheet" href="<s:url value="/res/jquery.jqGrid-4.3.3/css/ui.jqgrid.css"/>"/> --%>
<%-- 	<script type="text/javascript" src="<s:url value="/res/jquery.jqGrid-4.3.3/js/jquery.jqGrid.min.js"/>"></script> --%>
	<link type="text/css" rel="stylesheet" href="<s:url value="/res/jquery.jqGrid-4.4.4/css/ui.jqgrid.css"/>"/>
	<link type="text/css" rel="stylesheet" href="<s:url value="/res/jquery.jqGrid-4.3.3/css/change.css"/>"/>
	<script type="text/javascript" src="<s:url value="/res/jquery.jqGrid-4.4.4/js/jquery.jqGrid.min.js"/>"></script>
	
	<script type="text/javascript" src="<s:url value="/res/jquery.jqGrid-4.3.3/grid.js"/>"></script>
</s:if>

<%-- commonJS --%>
<s:if test="'commonJS' in #names">
	<script type="text/javascript" src="<s:url value="/js/util.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/js/common_added.js"/>"></script>
</s:if>

<%-- 陈浙东修改的新的common.js --%>
<s:if test="'new_commonJS' in #names">
	<script type="text/javascript" src="<s:url value="/js/util.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/js/new_common.js"/>"></script>
</s:if>

<%-- 陈浙东修改的新的grid.js --%>
<s:if test="'new_grid' in #names">
	<script type="text/javascript" src="<s:url value="/res/jquery.jqGrid-4.3.3/js/i18n/grid.locale-cn.js"/>"></script>
	<link type="text/css" rel="stylesheet" href="<s:url value="/res/jquery.jqGrid-4.3.3/plugins/ui.multiselect.css"/>"/>
	<script type="text/javascript" src="<s:url value="/res/jquery.jqGrid-4.3.3/plugins/ui.multiselect.js"/>"></script>
	<link type="text/css" rel="stylesheet" href="<s:url value="/res/jquery.jqGrid-4.4.4/css/ui.jqgrid.css"/>"/>
	<link type="text/css" rel="stylesheet" href="<s:url value="/res/jquery.jqGrid-4.3.3/css/change.css"/>"/>
	<script type="text/javascript" src="<s:url value="/res/jquery.jqGrid-4.4.4/js/jquery.jqGrid.min.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/res/jquery.jqGrid-4.3.3/new_grid.js"/>"></script>
</s:if>

<%-- utilJS --%>
<s:if test="'utilJS' in #names">
	<script type="text/javascript" src="<s:url value="/js/util.js"/>"></script>
</s:if>

<%-- viewJS --%>
<s:if test="'viewJS' in #names">
	<script type="text/javascript" src="<s:url value="/js/view.js"/>"></script>
</s:if>

<%-- ztree --%>
<s:if test="'ztree' in #names">
	<link rel="stylesheet" type="text/css" href="<s:url value="/res/ztree/zTreeStyle.css"/>">
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/ztree.css"/>">
	<script type="text/javascript" src="<s:url value="/res/ztree/jquery.ztree.all-3.2.min.js"/>"></script>
</s:if>

<!-- plug-in1 common.js中公用自定义方法抽离-->
<s:if test="'plug_in1' in #names">
	<script type="text/javascript" src="<s:url value="/js/commons/plug-in1.js"/>"></script>
</s:if>

<!-- uploadify 上传插件JS -->
<s:if test="'uploadify' in #names">
	<link  style="text/css" rel="stylesheet" href='<s:url value="/res/uploadify-v3.0.0/uploadify.css"></s:url>' />
	<script type="text/javascript" src='<s:url value="/res/uploadify-v3.0.0/jquery.uploadify.js"></s:url>'></script>
</s:if>

<!-- fusionCharts -->
<s:if test="'plotCharts' in #names">
	<script type="text/javascript" src='<s:url value="/res/plotCharts/FusionCharts.js"></s:url>'></script>
	<script type="text/javascript" src='<s:url value="/res/plotCharts/FusionCharts.jqueryplugin.js"></s:url>'></script>
	<script type="text/javascript" src='<s:url value="/js/plotCharts/initPlot.js"></s:url>'></script>
</s:if>

<!-- dateutil自定义日期工具类 -->
<s:if test="'dateutil' in #names">
	<script type="text/javascript" src='<s:url value="/js/util/jquery.hw_dateutil.js"></s:url>'></script>
</s:if>
<!--jqgrid扩展 自定义格式化数据 -->
<s:if test="'formatter' in #names">
	<script type="text/javascript" src='<s:url value="/js/util/jquery.jqgrid.custom.hw_format.js"></s:url>'></script>
</s:if>

<!-- jqueryUI单独使用时引用的js -->
<s:if test="'jqueryUI' in #names">
	<link type="text/css" rel="stylesheet" href="<s:url value="/res/jquery-ui-1.8.2/themes/redmond/jquery-ui-1.8.2.custom.css"/>"/>
	<script type="text/javascript" src="<s:url value="/res/jquery-ui-1.8.2/jquery-ui.js"/>"></script>
</s:if>

<!-- 快速查询时引用的js -->
<s:if test="'quickSearch' in #names">
	<link type="text/css" rel="stylesheet" href="<s:url value="/res/jquery-quickSearch-1.0/skin/default/quickSearch.css"/>"/>
	<script type="text/javascript" src="<s:url value="/res/jquery-quickSearch-1.0/jquery.hw_quickSearch.js"/>"></script>
</s:if>

<%-- mCustomScrollbar  自定义滚动条 --%>
<s:if test="'mCustomScrollbar' in #names">
	<link type="text/css" rel="stylesheet" href="<s:url value="/res/mCustomScrollbar/jquery.mCustomScrollbar.css"/>"/>
	<script type="text/javascript" src="<s:url value="/res/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js"/>"></script>
</s:if>
<!-- 新 UI 引入文件 start-->
<!-- jquery 1.7.2 -->
<s:if test="'jquery-1.7.2' in #names">
	<script type="text/javascript" src="<s:url value="/res/jquery-1.7.2.min.js"/>"></script>
</s:if>
<!-- jquery validate -->
<s:if test="'validate' in #names">
	<script type="text/javascript" src="<s:url value="/js/jquery.validate.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/js/jquery.metadata.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/js/jquery.validate.self.js"/>"></script>
</s:if>
<!-- jquery ui 1.8.2 -->
<s:if test="'jqueryUI-1.8.2' in #names">
	<link type="text/css" rel="stylesheet" href="<s:url value="/res/jquery-ui-1.8.2/themes/redmond/jquery-ui-1.8.2.custom.css"/>"/>
	<script type="text/javascript" src="<s:url value="/res/jquery-ui-1.8.2/jquery-ui.js"/>"></script>
</s:if>
<!-- hwPopup -->
<s:if test="'hwPopup' in #names">
	<script type="text/javascript" src="<s:url value="/js/commons/hw_popup.js"/>"></script>
</s:if>
<!-- hwDialog -->
<s:if test="'hwDialog' in #names">
	<script type="text/javascript" src="<s:url value="/js/commons/hw_dialog.js"/>"></script>
</s:if>
<!-- pageJS1.1 -->
<s:if test="'pageJS1.1' in #names">
	<script type="text/javascript"  src='<s:url value="/res/jquery-page-1.1/jquery.page.custom.js"></s:url>'></script>
</s:if>

<!-- pageJS1.1 -->
<s:if test="'tableJS' in #names">
	<script type="text/javascript"  src='<s:url value="/res/table/table.js"></s:url>'></script>
	<link type="text/css" rel="stylesheet" href="<s:url value="/res/table/table.css"/>"/>
</s:if>
<!-- tabJS -->
<s:if test="'tabJS' in #names">
	<script type="text/javascript"  src='<s:url value="/js/commons/hw_tab.js"></s:url>'></script>
</s:if>
<!-- popupDialog -->
<s:if test="'popupDialog' in #names">
	<script type="text/javascript"  src='<s:url value="/js/commons/hw_bind.js"></s:url>'></script>
	<script type="text/javascript"  src='<s:url value="/js/commons/hw_dialog.js"></s:url>'></script>
	<script type="text/javascript"  src='<s:url value="/js/commons/hw_popup.js"></s:url>'></script>
</s:if>
<!-- bootstrap -->
<s:if test="'bootstrap' in #names">
	<link  style="text/css" rel="stylesheet" href='<s:url value="/js/bootstrap/bootstrap-combined.min.css"></s:url>' />
	<link  style="text/css" rel="stylesheet" href='<s:url value="/js/bootstrap/layoutit.css"></s:url>' />
	<script type="text/javascript"  src='<s:url value="/js/bootstrap/bootstrap.min.js"></s:url>'></script>
	<script type="text/javascript"  src='<s:url value="/js/bootstrap/jquery-ui.js"></s:url>'></script>
	<script type="text/javascript"  src='<s:url value="/js/bootstrap/jquery.ui.touch-punch.min.js"></s:url>'></script>
	<script type="text/javascript"  src='<s:url value="/js/bootstrap/jquery.htmlClean.js"></s:url>'></script>
	
	
</s:if>
<!-- 新 UI 引入文件 end-->

