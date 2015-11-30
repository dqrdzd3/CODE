<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<style>
<!--
.loading{
filter:Alpha(Opacity=30); 
/* IE */ 
-moz-opacity:0.4; 
/* Moz + FF */ 
opacity: 0.4; 

}
-->
</style>

<script type="text/javascript">
	function showLoading(){
		
		$("#Loading").show();
		$("#LoadingImage").show();
	}
	
	function hideLoading(){
		$("#LoadingImage").hide();
		$("#Loading").hide();
	}
</script>


<div id="Loading" class="loading" 
  style="position:absolute;  left:0px;  top:0px;  width:100%;  height:100%;	filter:alpha(opacity=30);	background:#f0f8ff;	display:none;  z-index:99998;">
	<iframe id="LoadingFrame" frameborder="0" width="100%" height="100%" style="position: absolute; "></iframe>
</div>
<div id="LoadingImage" class="" 
  style="position:absolute;  left:0px;  top:45%;  width:100%;  text-align:center;  display:none;  z-index:99999; ">
  <img alt="数据处理中..." style="" src='<s:url value="/images/pageloading.gif"></s:url>'>数据处理中...
</div>
