<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<title>文件预览</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/flexpaper.css" />
<script type="text/javascript" src="<%=path %>/res/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/res/flexpaper/swfobject/flexpaper_flash.js"></script>
<script type="text/javascript" src="<%=path %>/res/flexpaper/swfobject/flexpaper_flash_debug.js"></script>
<script type="text/javascript" src="<%=path %>/res/flexpaper/swfobject/swfobject.js"></script>
</head>
<body>
	<div style="position: absolute; left: 10px; top: 10px;">
		<a id="viewerPlaceHolder"
			style="width: 660px; height: 480px; display: block"></a>
		<script type="text/javascript">
		var swfurl = '<%=path%>/${requestScope.swfPath}';
		var swfVersionStr = "10.0.0";
	     var xiSwfUrlStr = "<%=path %>/res/flexpaper/playerProductInstall.swf";
	     var flashvars = {
	           SwfFile : escape(swfurl),
				  Scale : 0.6,
				  ZoomTransition : "easeOut",
				  ZoomTime : 0.5,
				  ZoomInterval : 0.1,
				  FitPageOnLoad : false,
				  FitWidthOnLoad : true,
				  PrintEnabled : true,
				  FullScreenAsMaxWindow : false,
				  ProgressiveLoading : true,

				  PrintToolsVisible : true,
				  ViewModeToolsVisible : true,
				  ZoomToolsVisible : true,
				  FullScreenVisible : true,
				  NavToolsVisible : true,
				  CursorToolsVisible : true,
				  SearchToolsVisible : true,
				  localeChain: "zh_CN"
				  };

		 var params = {};
	     params.quality = "high";
	     params.bgcolor = "#ffffff";
	     params.allowscriptaccess = "sameDomain";
	     params.allowfullscreen = "true";
	     var attributes = {};
	     attributes.id = "FlexPaperViewer";
	     attributes.name = "FlexPaperViewer";
	     
	     swfobject.embedSWF(
	         "<%=path %>/res/flexpaper/FlexPaperViewer.swf", "viewerPlaceHolder",
	         "724", "400",
	         swfVersionStr, xiSwfUrlStr,
	         flashvars, params, attributes);
			swfobject.createCSS("#viewerPlaceHolder", "display:block;text-align:left;");
		</script>
	</div>
</body>
</html>