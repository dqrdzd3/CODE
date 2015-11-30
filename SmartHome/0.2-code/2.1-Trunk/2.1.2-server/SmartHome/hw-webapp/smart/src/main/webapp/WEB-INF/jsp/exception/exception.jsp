<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提示信息</title>
<s:action name="include(mainCSS)" executeResult="true"/>
<script type="text/javascript">
	function showDeatilMsg(){
		var divObj = document.getElementById("detailMsg");
		if(divObj.style.display != "none"){
			divObj.style.display="none";
		}else{
			divObj.style.display="";
		}
	}
	function goBack(){
		if(history.back()){
			history.back();
		}else{
			window.close();
		}

	}
</script>
</head>
<body>
<div id="radiv" style="width:98%;padding-left:4px;margin-top:10px;"><table width="100%" border="0" cellspacing="0" cellpadding="0" style="width:100%;">
<tr>
						<td   style="width:60px;" nowrap="nowrap" align="right"><span>服务器错误,请联系系统管理员.</span></td>
						<td>
							<%-- ${exception } --%>
							错误编号:&nbsp;${logCode}
						</td>
					</tr>
					<tr>
					<td></td>
						<td align="right">
							
						</td>
					</tr>
</table>
</div>
</body>
</html>