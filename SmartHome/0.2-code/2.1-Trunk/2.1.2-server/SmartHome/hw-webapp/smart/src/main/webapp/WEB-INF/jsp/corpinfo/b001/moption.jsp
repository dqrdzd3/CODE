<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<option >请选择</option>
<s:iterator value="cityl">
	<option value="${code}">${desc}</option>
</s:iterator>

