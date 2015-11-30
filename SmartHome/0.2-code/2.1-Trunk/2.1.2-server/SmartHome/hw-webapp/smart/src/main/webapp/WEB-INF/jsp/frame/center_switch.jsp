<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>center-switch</title>
<s:action name="include(mainCSS,jquery)" namespace="" executeResult="true"/>
<style type="text/css">
body,html{height:100%;overflow:hidden;background:url(images/switchbg.jpg) left top repeat-y;}
</style>
<script type="text/javascript">
	$(function(){
		// cols = "3,198,13,*,7"
		var mainFrameSet = parent.document.getElementById("mainFrameSet"),
			show = true,
			leftImg = 'images/hiddlebutton_left.jpg',
			rightImg = 'images/hiddlebutton_right.jpg';
		$("#imgBtn")
			.css({cursor:'pointer'})	
			.click(function(){
				mainFrameSet.cols = "3," + (show ? 0 : 198) + ",13,*,7";
				$(this).attr('src',show ? rightImg : leftImg);
				show = !show;
			});
	});
</script>
</head>

<body style="background:url(images/switchbottom.jpg) left bottom no-repeat;">
<table width="13" height="100%" border="0" cellpadding="0" cellspacing="0" style="background:url(images/switchtop.jpg) top left no-repeat;">
  <tr>
    <td height="33%">&nbsp;</td>
  </tr>
  <tr>
    <td align="left" valign="middle">
    	<img id="imgBtn" src="images/hiddlebutton_left.jpg" width="9" height="54" />
   	</td>
  </tr>
  <tr>
    <td height="33%">&nbsp;</td>
  </tr>
</table>

</body>
</html>
