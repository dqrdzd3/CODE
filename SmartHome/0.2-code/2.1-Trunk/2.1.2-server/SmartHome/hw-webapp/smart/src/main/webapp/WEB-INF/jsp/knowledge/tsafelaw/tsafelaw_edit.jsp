<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath %>"/>
<title><s:text name="法律法规知识库" />-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,validatorJS,grid)" executeResult="true"/>


</head>
<body>
     
     <s:form name="myfrom"  method="post" action="tsafelaw" enctype="multipart/form-data" cssClass="formmargin">
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul>
      <li>经典事故案例库</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv">
      <div id="windowdivmain"> 
 
      <table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable" id="windowdivmaintable">
    <tr>
    <th><span class="red">*</span> 标准名称</th>
    <td><s:textfield name="tsafelawPO.name" id="name"    /></td>
    <th><span class="red">*</span> 标准编号</th>
    <td><s:textfield name="tsafelawPO.code" id="code"    /></td>
     <th align="right"><span class="red">*</span> 标准类别</th>
    <td ><s:textfield name="tsafelawPO.type" id="type"    /></td>
  </tr>
  <tr>
   
    <th >颁布部门 </th>
    <td><s:textfield name="tsafelawPO.pubdept" id="pubdept"    /></td>
      <th>颁布地区 </th>
    <td><s:textfield name="tsafelawPO.area" id="area"     onfocus="WdatePicker({maxDate:'%y-%M-%d'})"/></td>
  <th align="right">状态</th>
    <td><s:radio name="tsafelawPO.status" list="#{'1':'有效','0':'作废'}" value="" cssClass="radiostyle" /></td>
  </tr>
  <tr>
    <th align="right">发布日期</th>
    <td>
      <s:textfield name="tsafelawPO.pubDate" id="pubDate"  cssClass="Wdate"   onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})"/>
    </td>
    <th>实施日期</th>
    <td><s:textfield name="tsafelawPO.takeDate" id="takeDate"  cssClass="Wdate"   onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})"/></td>
 <th align="right">失效日期</th>
    <td><s:textfield name="tsafelawPO.cancelDate" id="cancelDate"   cssClass="Wdate"  onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})"/></td>
  </tr>
 
    <tr>
    <th align="right">法规内容</th>
    <td colspan="5"><s:textarea name="tsafelawPO.content" id="content"   cssClass="textarea1"  onfocus="WdatePicker({maxDate:'%y-%M-%d'})"/></td>
    </tr>
  
  <tr>
    <th align="right"><span class="red">*</span>上传文件 </th>
    <td colspan="5"><s:file name="upload" style="width:100%; vertical-align:top; margin-right:0px;" cssClass="inputmodify"></s:file>
   
    </td>
    </tr>
</table>

</div>
    </div></div>
  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
      <ul><li class="save"><s:submit value="" method="doList" cssClass="cancel"></s:submit></li>
       <li class="save" style="margin-right:15px;"><s:submit value="" method="doModify"  cssClass="save"></s:submit></li>
      <li>( 说明：<span>*</span>号位必填项)</li>
     </ul>
      </div>
    </div>
  </div>
</div>
</s:form>
</body>
</html>