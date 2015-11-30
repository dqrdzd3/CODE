<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="hwsoft" uri="http://www.hanwei.com/tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>" />
<title>讨论区管理-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS,my97)"
	executeResult="true" />
<script type="text/javascript">
	
</script>
</head>
常见问题
<br>
<hr>
<s:if test="#request.qaList!=null">
	<s:if test="#request.qaList.size!=0">
	   <s:iterator value="#request.qaList" id="column">  
	      Q: <s:property value="#column.MA002"/><br>     
	       A: <s:property value="#column.MA003"/>  <br>    
        </s:iterator> 
	</s:if>
	<s:else>
		<div>还没有问题呢</div>
	</s:else>
</s:if>
</html>