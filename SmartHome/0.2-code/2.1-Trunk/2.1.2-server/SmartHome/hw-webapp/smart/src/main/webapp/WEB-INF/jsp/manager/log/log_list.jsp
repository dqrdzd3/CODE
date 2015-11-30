<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="label.log" />-<s:text name="application.title" /></title>
</head>
<body>
	<h1>
		<s:text name="label.log" />
	</h1>
	<table class="borderAll">
		<tr>
			<th><s:text name="label.log.type" /></th>
			<th><s:text name="label.log.content" /></th>
			<th><s:text name="label.log.user" /></th>
			<th><s:text name="label.log.time" /></th>
			<th><s:text name="label.system.operator" /></th>
		</tr>
		<s:iterator value="logList" status="status">
			<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
				<td class="nowrap"><s:property value="type" /></td>
				<td class="nowrap"><s:property value="content" /></td>
				<td class="nowrap"><s:property value="userName" /></td>
				<td class="nowrap"><s:property value="time" /></td>
				<td class="nowrap">
					<s:url action="log!delete" id="url">
						<s:param name="logPO.id" value="id" />
					</s:url> 
					<a href="<s:property value="#url"/>"><s:text name="label.system.delete" /></a>
				</td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>