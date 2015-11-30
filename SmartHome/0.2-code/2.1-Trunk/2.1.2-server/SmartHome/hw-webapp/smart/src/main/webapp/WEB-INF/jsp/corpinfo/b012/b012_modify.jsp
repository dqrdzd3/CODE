<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>危化品信息-<s:text name="application.title" /></title>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<s:action name="include(mainCSS,jquery,validatorJS,grid)" executeResult="true"/>

</head>
<body>
<s:form action="b012" method="post" > 
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul>
      <li>危化品登记</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv"><div id="windowdivmain">
     <table width="100%" cellpadding="0" cellspacing="0"  class="windowdivmaintable" id="windowdivmaintable">
     
     <s:hidden name="b012PO.MA001"></s:hidden>
     <s:hidden name="b012PO.MA003"></s:hidden>
     <s:hidden name="b012PO.MA010"></s:hidden>
     <s:hidden name="b012PO.MA015"></s:hidden>
   <tr>
    <th><span>*</span>危险货物编号</th>
    <td class="tb_input"><s:textfield name="b012PO.MA006" id="MA006" cssClass='{required:true,maxlength:10,stringCode:true}'></s:textfield></td>
    <th><span>*</span>中文名称</th>
    <td class="tb_input"><s:textfield name="b012PO.MA004" id="MA004" cssClass='{chcharacter:true,required:true,maxlength:25}'></s:textfield></td>
    <th>别名</th>
    <td class="tb_input"><s:textfield name="b012PO.MA005" id="MA005" cssClass='{maxlength:25}'></s:textfield></td>
  </tr>
  <tr>
    
    <th><span>*</span> 产品类型</th>
    <td><s:select list="codeValueCPLX" name="b012PO.MA008"  listKey="value" listValue="name" headerKey="" headerValue="请选择" ength="25"  cssClass='required'></s:select>
    <th><span>*</span> 数量 </th>
    <td><s:textfield name="b012PO.MA007" id="MA007" cssClass='{required:true,number:true,decimal:true,min:0}' ></s:textfield></td>
     <td colspan="2"><s:select list="codeValueDW" name="b012PO.MA002"  listKey="value" listValue="name" headerKey="" headerValue="请选择单位" ength="25" cssClass='required' ></s:select>
  </tr>
  
  <tr>
    <th style="width:120px;"><span>25字以内</span>危险物质名称说明</th>
    <td colspan="5"><s:textarea name="b012PO.MA009" id="MA009" cols="45" rows="5" maxlength="25"cssClass="{maxlength:25,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>1000字以内</span>备注</th>
    <td colspan="5"><s:textarea name="b012PO.MA013" id="MA013 " cols="45" rows="5" maxlength="1000"cssClass="{maxlength:1000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
</table>
</div>
    </div>
  </div>
  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
      <ul>
      <li class="cancel"><s:submit value="" cssClass="back" method="doList" ></s:submit></li>
       <li class="save" style="margin-right:15px;"><s:submit value="" cssClass="submit" method="doSaveEdit"></s:submit></li>
      <li>( 说明：<span>*</span>号位必填项)</li>
      </ul>
      </div>
    </div>
  </div>
</div>
</s:form>
</body>
</html>
