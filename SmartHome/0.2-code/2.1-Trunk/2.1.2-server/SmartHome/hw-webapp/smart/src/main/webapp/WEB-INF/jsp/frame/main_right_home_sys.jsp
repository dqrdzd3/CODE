<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<table id="welPicTable" width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" >
	<tr>
	  <td height="15%" align="right" valign="bottom"><img src="images/welcomepictitle_2.jpg" style="margin-right:5%;"/></td>
	</tr>
	<tr>
	  <td height="85%" align="center" valign="middle"><img src="" usemap="#Map" border="0" /></td>
	</tr>
</table>



<script type="text/javascript">
	$("#Map area").each(function(){
		$(this).attr("href","javascript:;");
	});
// 	$("#Map area").each(function(){
// 		$(this).attr("href","javascript:;")
// 			.unbind("click")
// 			.click(function(){
// 				$$.getFrame("leftFrame").accorActive($(this).attr("id"),$(this).attr("title"));	
// 				return false;
// 			});
// 	});
</script>
