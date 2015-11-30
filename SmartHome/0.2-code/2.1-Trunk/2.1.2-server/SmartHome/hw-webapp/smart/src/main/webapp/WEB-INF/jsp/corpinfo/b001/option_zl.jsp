<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<option value="">请选择行业中类</option>
<s:iterator value="zlList">
	<option value="${ID}">${DISPLAY}</option>
</s:iterator>
