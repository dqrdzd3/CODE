<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<option value="">请选择行业小类</option>
<s:iterator value="xlList">
	<option value="${ID}">${DISPLAY}</option>
</s:iterator>

