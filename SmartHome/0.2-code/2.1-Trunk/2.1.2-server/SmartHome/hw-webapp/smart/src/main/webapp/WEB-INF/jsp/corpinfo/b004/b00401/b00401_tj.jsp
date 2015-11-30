<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
	function dangertj(){
		var num = $('#selectid').val();
		var srcArr = ['<%=basePath%>superintend/b02020',
		              '<%=basePath%>superintend/b02020!doGetB02021',
		              '<%=basePath%>superintend/b02020!doGetDangerLevel.action'
		              ];
		$('#iframeid').attr('src',srcArr[num]);
	}
</script>
</head>
<body>
	<div>
		<div class="counttitle">危险源统计</div>
		<div id="windowsmain">
			<div id="tt" >
				<div style="margin-top: 10px;">
					<table border="0" align="center" cellpadding="4" cellspacing="0" style="width:583px;' " class="tablecss1">
						<tr>
							 <td style="width: 50px;text-align: center">统计维度</td>
							 <td colspan="1" style="width:300px;"><select onchange="dangertj()" id="selectid">
							 <option value="0" selected="selected">危险源类别</option>
							 <option value="1">企业危险源统计</option>
							 <option value="2">危险源级别统计</option>
							 </select>
							</td>
                        </tr>
					</table>
				</div>
				<!-- 危险源统计 -->
				<DIV id="tab1" closable="false" class="tab">
					<iframe id="iframeid" width="100%" height="650" name="iframe02002" src="<%=basePath%>superintend/b02020" frameborder=0 border=0  marginwidth=0  marginheight=0  scrolling=0></iframe>
				</DIV>
			 </div>
	    </div>
	</div>
</body>
</html>